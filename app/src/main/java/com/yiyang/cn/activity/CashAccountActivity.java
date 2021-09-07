package com.yiyang.cn.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CashAccountActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.btn_save)
    Button btnSave;
    private String weixinOrZhiFuBao;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_cash_account;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        weixinOrZhiFuBao = getIntent().getStringExtra("weixinOrZhiFuBao");
        if (weixinOrZhiFuBao.equals("1")) {
            //UIHelper.ToastMessage(mContext, "支付宝");
            tv_title.setText("提现到支付宝");
        } else if (weixinOrZhiFuBao.equals("2")) {
            //UIHelper.ToastMessage(mContext, "微信");
            tv_title.setText("提现到微信");
        }
        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !etName.getText().toString().equals("")) {
                    btnSave.setEnabled(true);
                    btnSave.setBackground(getResources().getDrawable(R.drawable.bg_shape_app));
                } else {
                    btnSave.setEnabled(false);
                    btnSave.setBackground(getResources().getDrawable(R.drawable.bg_shape_app_disabled));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && !etAccount.getText().toString().equals("")) {
                    btnSave.setEnabled(true);
                    btnSave.setBackground(getResources().getDrawable(R.drawable.bg_shape_app));
                } else {
                    btnSave.setEnabled(false);
                    btnSave.setBackground(getResources().getDrawable(R.drawable.bg_shape_app_disabled));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                requestData();
                break;
        }
    }


    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04238");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getApplication()).getAppToken());
        map.put("alipay_uname", etName.getText().toString());
        map.put("alipay_number", etAccount.getText().toString());
        map.put("sms_id", getIntent().getStringExtra("sms_id"));
        map.put("sms_code", getIntent().getStringExtra("sms_code"));

        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(Response<AppResponse> response) {
                        // AlertUtil.t(getApplicationContext(), response.body().msg);
                            UIHelper.ToastMessage(CashAccountActivity.this, "保存成功");
                            PreferenceHelper.getInstance(CashAccountActivity.this).putString(App.CUNCHUBIND_ALIPAY, "1");
                            finish();
                        //  AppManager.getAppManager().finishActivity(PhoneCheckActivity.class);
                        //AppManager.getAppManager().finishActivity();

                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        AlertUtil.t(getApplication(), response.getException().getMessage());

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
}
