package com.yiyang.cn.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_Success;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.Message;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
import com.yiyang.cn.model.ZhiNengHomeListBean;
import com.yiyang.cn.util.AddShareTimeCount;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.TimeCount;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.MemberBean;
import com.tuya.smart.home.sdk.bean.MemberWrapperBean;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaDataCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengFamilyAddShareActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.ll_code)
    LinearLayout ll_code;
    @BindView(R.id.tv_code)
    TextView tv_code;
    @BindView(R.id.tv_bind)
    TextView tv_bind;
    private Context context = ZhiNengFamilyAddShareActivity.this;
    private AddShareTimeCount timeCount;
    private String family_id = "";
    private String smsId = "";

    private String memberType;
    private int memberRole;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhineng_family_add_share;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightMode(this);
        memberType = getIntent().getStringExtra("memberType");

        if (memberType.equals("2")) {
            memberRole = 0;
        } else {
            memberRole = 1;
        }

        initToolbar();
        initView();
    }

    private void initView() {
        family_id = getIntent().getStringExtra("family_id");
        timeCount = new AddShareTimeCount(60000, 1000, tv_code);
        // ll_code.setOnClickListener(this);
        //  tv_bind.setOnClickListener(this);
        tv_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_phone.getText().toString().isEmpty()) {
                    Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (et_code.getText().toString().isEmpty()) {
                    Toast.makeText(context, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    yaoqing(et_phone.getText().toString());
                }
            }
        });

        ll_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_phone.getText().toString().isEmpty()) {
                    Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    getCode();
                }
            }
        });
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("新增共享成员");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    TishiDialog tishiDialog;

    /**
     * 获取短信验证码
     */
    private void getCode() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00001");
        map.put("key", Urls.key);
        map.put("user_phone", et_phone.getText().toString());
        map.put("mod_id", "0315");
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        Toast.makeText(context, "验证码发送成功，请注意查收", Toast.LENGTH_SHORT).show();
                        timeCount.start();
                        if (response.body().data.size() > 0)
                            smsId = response.body().data.get(0).getSms_id();
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        String str = response.getException().getMessage();

                        tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {
                                tishiDialog.dismiss();
                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {

                                finish();
                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        tishiDialog.setTextContent(str);
                        tishiDialog.show();
                    }
                });
    }

    /**
     * 绑定共享成员
     */
    private void bindMember(long memberId) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16017");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("user_phone", et_phone.getText().toString());
        map.put("ty_member_id", memberId + "");
        map.put("family_id", family_id);
        map.put("sms_id", smsId);
        map.put("sms_code", et_code.getText().toString());
        map.put("member_type", memberType);
        Gson gson = new Gson();
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(ZhiNengFamilyAddShareActivity.this,
                                "成功", "添加家庭成员成功", "知道了", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {
                                finish();
                            }
                        });
                        myCarCaoZuoDialog_success.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        removeMember(memberId);
                        String str = response.getException().getMessage();
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,
                                "提示", str, "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {

                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    }
                });
    }

    private void removeMember(long memberId) {
        TuyaHomeSdk.getMemberInstance().removeMember(memberId, new IResultCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(String code, String error) {

            }
        });
    }

    // TODO: 2021-05-17 添加涂鸦设备  2.成员 3.二级管理员
    private void yaoqing(String phone) {
        long homeId = PreferenceHelper.getInstance(mContext).getLong(AppConfig.TUYA_HOME_ID, 0);
        @SuppressLint("WrongConstant") MemberWrapperBean bean = new MemberWrapperBean.Builder()
                .setHomeId(homeId)
                .setNickName(phone)
                .setAccount(phone)
                .setCountryCode("86")
                .setRole(memberRole)
                .setHeadPic("")
                .setAutoAccept(true)
                .build();

        TuyaHomeSdk.getMemberInstance().addMember(bean, new ITuyaDataCallback<MemberBean>() {
            @Override
            public void onSuccess(MemberBean result) {
                long memberId = result.getMemberId();
                bindMember(memberId);
            }

            @Override
            public void onError(String errorCode, String errorMessage) {
                MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,
                        "提示", errorMessage, "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {

                    }

                    @Override
                    public void clickRight() {

                    }
                });
                myCarCaoZuoDialog_caoZuoTIshi.show();
            }
        });
    }
}
