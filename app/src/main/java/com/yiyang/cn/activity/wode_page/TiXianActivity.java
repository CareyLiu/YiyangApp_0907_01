package com.yiyang.cn.activity.wode_page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.PhoneCheckActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.inter.PayPassWordInter;
import com.yiyang.cn.model.UserInfo;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.dialog.TiXanPasswordDialog;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.app.App.CUNCHU_ZHIFUMIMA;

public class TiXianActivity extends BaseActivity implements PayPassWordInter {

    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.iv_right_back)
    ImageView ivRightBack;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.ll_2)
    ConstraintLayout ll2;
    @BindView(R.id.tv_tixianhuashu)
    TextView tvTixianhuashu;
    @BindView(R.id.tv_renminbi)
    TextView tvRenminbi;
    @BindView(R.id.cl_3)
    ConstraintLayout cl3;
    @BindView(R.id.tv_zuiditixian)
    TextView tvZuiditixian;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.tv_tixiankouchu)
    TextView tvTixiankouchu;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.show_shui)
    TextView showShui;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_zhifufangshi)
    TextView tvZhifufangshi;
    @BindView(R.id.user_name)
    TextView userName;

    private String moneyUse;
    BigDecimal zhanShiJinE;
    private String puTongUserOrDaiLiShang = "0";//0 普通用户 1 代理商
    private String weiXinOrZhiFuBao = "1";//1 微信 2 支付宝

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moneyUse = getIntent().getStringExtra("money_use");
        etText.setHint("当前可提现金额 " + moneyUse);
        puTongUserOrDaiLiShang = getIntent().getStringExtra("puTongUserOrDaiLiShang");
        weiXinOrZhiFuBao = getIntent().getStringExtra("weixinOrZhiFubao");

        if (weiXinOrZhiFuBao.equals("2")) {
            ivIcon.setBackgroundResource(R.mipmap.dingdan_icon_wexin);
            tvZhifufangshi.setText("微信");
        } else if (weiXinOrZhiFuBao.equals("1")) {
            ivIcon.setBackgroundResource(R.mipmap.dingdan_icon_zhifubao);
            tvZhifufangshi.setText("支付宝");

        }

        tvTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etText.getText().toString())) {
                    UIHelper.ToastMessage(TiXianActivity.this, "请输入您需要提现的金额");
                    return;
                }


                if (zhanShiJinE.compareTo(new BigDecimal("9")) == -1) {
                    UIHelper.ToastMessage(TiXianActivity.this, "提现金额需大于10元");
                    etText.setText("");
                    return;
                }


                String str = PreferenceHelper.getInstance(TiXianActivity.this).getString(CUNCHU_ZHIFUMIMA, "1");

                /**
                 * pay_pwd_check	是否设置支付密码：
                 * 1.已经设置 2.未设置
                 */
                if (str.equals("1")) {
                    TiXanPasswordDialog tiXanPasswordDialog = new TiXanPasswordDialog(TiXianActivity.this);
                    tiXanPasswordDialog.show();

                } else if (str.equals("2")) {

                    startActivity(new Intent(TiXianActivity.this, PhoneCheckActivity.class).putExtra("mod_id", "0006"));
                }


            }
        });

        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s == null) {
                    return;
                }
                if (StringUtils.isEmpty(s.toString())) {
                    return;
                }


                BigDecimal tiXianJinE = new BigDecimal(s.toString());


                BigDecimal shuiLv = new BigDecimal("0.0015");

                BigDecimal kouChuJinE = tiXianJinE.multiply(shuiLv);


                BigDecimal twoBigDecimal = new BigDecimal("2");


                BigDecimal BigDecimal25 = new BigDecimal("25");


                if (kouChuJinE.compareTo(BigDecimal25) == 1) {
                    zhanShiJinE = tiXianJinE.subtract(BigDecimal25);
                    kouChuJinE = new BigDecimal("25");
                } else {

                    if (kouChuJinE.compareTo(twoBigDecimal) == 1) {
                        zhanShiJinE = tiXianJinE.subtract(kouChuJinE);
                    } else {
                        zhanShiJinE = tiXianJinE.subtract(twoBigDecimal);
                        kouChuJinE = new BigDecimal("2");

                    }

                }


                showShui.setText("手续费： ¥" + kouChuJinE.toString());


                tvTixian.setText("提现 " + zhanShiJinE + "元");

            }
        });

        requestData();
    }

    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04201");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<UserInfo.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<UserInfo.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<UserInfo.DataBean>> response) {

                        String weiXinMing = response.body().data.get(0).getWx_user_name();
                        String zhiFuBaoMing = response.body().data.get(0).getAlipay_uname();

                        if (weiXinOrZhiFuBao.equals("2")) {
                            UIHelper.ToastMessage(mContext, "微信支付");

                            if (weiXinMing != null) {
                                userName.setText(weiXinMing);
                            }

                        } else if (weiXinOrZhiFuBao.equals("1")) {

                            if (zhiFuBaoMing != null) {
                                userName.setText(zhiFuBaoMing);
                            }

                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<UserInfo.DataBean>> response) {
                        AlertUtil.t(mContext, response.getException().getMessage());
                    }
                });
    }


    @Override
    public int getContentViewResId() {
        return R.layout.layout_tixian;
    }

    /**
     * pay_pwd	支付密码		是
     * money	提现金额 	是
     */
    public void getNet(String pwd) {

        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(TiXianActivity.this).getAppToken());
        if (puTongUserOrDaiLiShang.equals("0")) {
            map.put("code", "04338");
        } else {
            map.put("code", "04778");
        }
        map.put("pay_pwd", pwd);
        map.put("money", etText.getText().toString());
        map.put("withdraw_type", weiXinOrZhiFuBao);

        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(Urls.HOME_PICTURE_HOME)
                .tag(TiXianActivity.this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        UIHelper.ToastMessage(TiXianActivity.this, "提现成功,系统将于1-2小时之内到账");
                        finish();
                        if (puTongUserOrDaiLiShang.equals("0")) {
                            //  map.put("code", "04338");
                        } else {
                            // map.put("code", "04778");
                            //1.发通知 成功
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_DAILISHANG_TIXIAN;
//                            n.content = message.toString();
                            n.content = "1";
                            RxBus.getDefault().sendRx(n);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        super.onError(response);
                        String str = response.getException().getMessage();
                        //    Log.i("cuifahuo", str);
                        //finish();
                        String[] str1 = str.split("：");

                        if (str1.length == 3) {
                            UIHelper.ToastMessage(mContext, str1[2]);
                        }

                        if (puTongUserOrDaiLiShang.equals("0")) {
                            //  map.put("code", "04338");
                        } else {
                            // map.put("code", "04778");
                            //1.发通知 失败
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_DAILISHANG_TIXIAN;
//                            n.content = message.toString();
                            n.content = "0";
                            RxBus.getDefault().sendRx(n);
                        }
                    }
                });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context weixinorzhifubao 1 支付宝 2 微信
     */
    public static void actionStart(Context context, String money_use, String puTongUserOrDaiLiShang, String weixinOrZhiFubao) {
        Intent intent = new Intent(context, TiXianActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("money_use", money_use);
        intent.putExtra("puTongUserOrDaiLiShang", puTongUserOrDaiLiShang);
        intent.putExtra("weixinOrZhiFubao", weixinOrZhiFubao);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("提现");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }

    @Override
    public void setPwd(String strPwd) {
        //UIHelper.ToastMessage(TiXianActivity.this, strPwd);
        getNet(strPwd);
    }
}
