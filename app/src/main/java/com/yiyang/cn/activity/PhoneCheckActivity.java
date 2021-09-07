package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.MyQianBaoActivity;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.config.Wetch_S;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.Message;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.TimeCount;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneCheckActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private TimeCount timeCount;
    private String smsId;
    private String weixinOrZhiFuBao = "1";
    private boolean weixin_flag;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_phone_check;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);

        TextView tvShouJiYanZheng = findViewById(R.id.tv_shoujiyanzheng);
        if (getIntent().getStringExtra("mod_id").equals("0006")) {
            tvShouJiYanZheng.setText("支付密码");
        }
        if (getIntent().getStringExtra("weixinOrZhiFuBao") != null) {
            weixinOrZhiFuBao = getIntent().getStringExtra("weixinOrZhiFuBao");

            if (weixinOrZhiFuBao.equals("1")) {

            } else if (weixinOrZhiFuBao.equals("2")) {
                btnSubmit.setText("去微信授权");
            }
        }

        weixin_flag = getIntent().getBooleanExtra("weixin_flag", true);

        timeCount = new TimeCount(60000, 1000, tvGetCode);
        tvPhone.setText(PreferenceHelper.getInstance(this).getString("user_phone", ""));
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 4) {
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.bg_shape_app));
                    btnSubmit.setEnabled(true);
                } else {
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.bg_shape_app_disabled));
                    btnSubmit.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.tv_get_code, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_get_code:
                getCode(getIntent().getStringExtra("mod_id"));
                break;
            case R.id.btn_submit:

                requestData();
                break;
        }
    }


    /**
     * 获取短信验证码
     */
    private void getCode(String modeId) {

        Map<String, String> map = new HashMap<>();
        map.put("code", "00001");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getApplicationContext()).getAppToken());
        map.put("user_phone", PreferenceHelper.getInstance(PhoneCheckActivity.this).getString("user_phone", ""));
        map.put("mod_id", modeId);
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        // showToast(response.body().msg);
                        UIHelper.ToastMessage(mContext, response.body().msg);
                        timeCount.start();
                        smsId = response.body().data.get(0).getSms_id();
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        AlertUtil.t(PhoneCheckActivity.this, response.getException().getMessage());
                        timeCount.cancel();
                        timeCount.onFinish();
                    }
                });

    }


    /**
     * 提交验证
     */
    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00006");
        map.put("key", Urls.key);
        map.put("sms_id", smsId);
        map.put("sms_code", etCode.getText().toString());
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        //AlertUtil.t(PhoneCheckActivity.this, response.body().msg);
                        switch (getIntent().getStringExtra("mod_id")) {
                            case "0006"://修改支付密码
                                startActivity(new Intent(PhoneCheckActivity.this, RevisePayActivity.class).putExtra("sms_id", smsId).putExtra("sms_code", etCode.getText().toString()));
                                PreferenceHelper.getInstance(PhoneCheckActivity.this).putString(App.CUNCHU_ZHIFUMIMA, "1");
                                finish();
                                break;
                            case "0007"://修改登录密码
                                finish();
                                startActivity(new Intent(PhoneCheckActivity.this, ReviseLoginActivity.class).putExtra("sms_id", smsId).putExtra("sms_code", etCode.getText().toString()));
                                break;
                            case "0008"://修改提现账号
                                if (weixinOrZhiFuBao.equals("1")) {
                                    startActivity(new Intent(PhoneCheckActivity.this, CashAccountActivity.class).putExtra("sms_id", smsId)
                                            .putExtra("sms_code", etCode.getText().toString()).putExtra("weixinOrZhiFuBao", weixinOrZhiFuBao));
                                } else if (weixinOrZhiFuBao.equals("2")) {

                                    //保存 微信回调使用
                                    PreferenceHelper.getInstance(mContext).putString(AppConfig.SMS_ID, smsId);
                                    PreferenceHelper.getInstance(mContext).putString(AppConfig.SMS_CODE, etCode.getText().toString());
                                    IWXAPI api;
                                    api = WXAPIFactory.createWXAPI(mContext, Wetch_S.APP_ID);
                                    SendAuth.Req req = new SendAuth.Req();
                                    req.scope = "snsapi_userinfo";
                                    req.state = "wechat_sdk_demo_test";
                                    api.sendReq(req);
                                }

                                finish();
                                break;

                            case "0320"://微信解绑
                                if (weixin_flag) {
                                    //保存 微信回调使用
                                    PreferenceHelper.getInstance(mContext).putString(AppConfig.SMS_ID, smsId);
                                    PreferenceHelper.getInstance(mContext).putString(AppConfig.SMS_CODE, etCode.getText().toString());
                                    IWXAPI api;
                                    api = WXAPIFactory.createWXAPI(mContext, Wetch_S.APP_ID);
                                    SendAuth.Req req = new SendAuth.Req();
                                    req.scope = "snsapi_userinfo";
                                    req.state = "wechat_sdk_demo_test";
                                    api.sendReq(req);
                                } else {
                                    weiXinJieBang();
                                }
                                finish();
                                break;
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        AlertUtil.t(PhoneCheckActivity.this, response.getException().getMessage());

                    }
                });

    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param weixinOrZhiFuBao 1支付宝 2微信
     */
    public static void actionStart(Context context, String mod_id, String weixinOrZhiFuBao) {
        Intent intent = new Intent(context, PhoneCheckActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mod_id", mod_id);
        intent.putExtra("weixinOrZhiFuBao", weixinOrZhiFuBao);
        context.startActivity(intent);
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String mod_id) {
        Intent intent = new Intent(context, PhoneCheckActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mod_id", mod_id);
        context.startActivity(intent);
    }

    /**
     * 用于其他Activty跳转到该Activity
     * flag  true  绑定  false 解绑
     */
    public static void actionStart_WeiBind(Context context, String mod_id, boolean flag) {
        Intent intent = new Intent(context, PhoneCheckActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mod_id", mod_id);
        intent.putExtra("weixin_flag", flag);
        context.startActivity(intent);
    }


    /**
     * 	JSON请求数据示例
     * {
     * "code":"04185",
     * "key":"20180305124455yu",
     * "token":"1541309Q02920100l000v000e000H0"
     * }
     * 	请求参数说明
     * 参数	说明	长度	是否必填
     * code	请求码(04185)	6	是
     * key	身份标识	10	是
     * token	token		是
     * sms_id	短信验证码id		是
     * sms_code	短信验证码		是
     */

    private void weiXinJieBang() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04185");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getApplication()).getAppToken());
        map.put("sms_id", smsId);
        map.put("sms_code", etCode.getText().toString());


        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(Response<AppResponse> response) {
                        // AlertUtil.t(getApplicationContext(), response.body().msg);
                        UIHelper.ToastMessage(mContext, "微信解绑成功");
                        finish();
                        hideInput();
                        //  AppManager.getAppManager().finishActivity(PhoneCheckActivity.class);
                        //AppManager.getAppManager().finishActivity();
                        PreferenceHelper.getInstance(mContext).putString(App.CUNCHUBIND_WEIXINPAY, "2");
                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        AlertUtil.t(getApplication(), response.getException().getMessage());

                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideInput();
    }
}
