package com.yiyang.cn.activity.dingdan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.TaoKeDetailList;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;
import static com.yiyang.cn.get_net.Urls.TAOKELIST;

public class DingDanShenQingTuikuanActivity extends BaseActivity {


    @BindView(R.id.tv_shenqingtuikuan_huashu)
    TextView tvShenqingtuikuanHuashu;
    @BindView(R.id.tv_shouhuozhuagntai_huashu)
    TextView tvShouhuozhuagntaiHuashu;
    @BindView(R.id.tv_shenqingyuanyin_huashu)
    TextView tvShenqingyuanyinHuashu;
    @BindView(R.id.tv_tuikuanjine)
    TextView tvTuikuanjine;
    @BindView(R.id.tv_jine)
    TextView tvJine;
    @BindView(R.id.tv_shuoming)
    TextView tvShuoming;
    @BindView(R.id.tv_phone_huashu)
    TextView tvPhoneHuashu;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.rrl_tijiaoshenqing)
    RoundRelativeLayout rrlTijiaoshenqing;
    @BindView(R.id.tv_shenqingtuikuan)
    TextView tvShenqingtuikuan;
    @BindView(R.id.et_content)
    EditText etContent;
    private String str;

    private String shop_form_id, refund_type;
    private String dingDanId;
    private String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        str = getIntent().getStringExtra("str");
        shop_form_id = getIntent().getStringExtra(" ");
        money = getIntent().getStringExtra("money");
        if (!StringUtils.isEmpty(money)) {
            tvJine.setText("¥" + money);
        } else {
            tvJine.setText("¥ 0");
        }
        tvShenqingtuikuan.setText(str);
        dingDanId = getIntent().getStringExtra("dingDanId");
        if (StringUtils.isEmpty(dingDanId)) {
            UIHelper.ToastMessage(DingDanShenQingTuikuanActivity.this, "缺少必要参数,请退出app重新尝试");
            finish();
            return;
        }
        shop_form_id = dingDanId;
        if (str.equals("我要退款(无需退货)")) {
            refund_type = "1";
        } else if (str.equals("我要退货退款")) {
            refund_type = "2";
        }

        rrlTijiaoshenqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (StringUtils.isEmpty(etContent.getText().toString())) {
                    UIHelper.ToastMessage(DingDanShenQingTuikuanActivity.this, "请填写退款原因");
                    return;
                }

                if (StringUtils.isEmpty(etPhone.getText().toString())) {
                    UIHelper.ToastMessage(DingDanShenQingTuikuanActivity.this, "请填写手机号");
                    return;
                }

                Map<String, String> map = new HashMap<>();
                map.put("code", "04154");
                map.put("key", Urls.key);
                map.put("token", UserManager.getManager(DingDanShenQingTuikuanActivity.this).getAppToken());
                map.put("shop_form_id", shop_form_id);
                map.put("refund_type", refund_type);
                map.put("refund_cause", etContent.getText().toString());
                map.put("refund_phone", etPhone.getText().toString());

                Gson gson = new Gson();
                Log.e("map_data", gson.toJson(map));
                OkGo.<AppResponse<TaoKeDetailList.DataBean>>post(HOME_PICTURE_HOME)
                        .tag(this)//
                        .upJson(gson.toJson(map))
                        .execute(new JsonCallback<AppResponse<TaoKeDetailList.DataBean>>() {
                            @Override
                            public void onSuccess(Response<AppResponse<TaoKeDetailList.DataBean>> response) {
                                // Log.i("response_data", new Gson().toJson(response.body()));
                                finish();
                                UIHelper.ToastMessage(DingDanShenQingTuikuanActivity.this, "提交成功");
                            }
                        });


            }
        });

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_ding_dan_shen_qing_tuikuan;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("申请退款");
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
    public boolean showToolBar() {
        return true;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String str, String dingDanId, String money) {
        Intent intent = new Intent(context, DingDanShenQingTuikuanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("str", str);
        intent.putExtra("money", money);
        intent.putExtra("dingDanId", dingDanId);
        context.startActivity(intent);
    }


}
