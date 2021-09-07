package com.yiyang.cn.activity.tuangou;

import android.app.UiAutomation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.flyco.roundview.RoundTextView;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.common.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TuanGouMaiDanActivity extends BaseActivity {

    @BindView(R.id.et_jine)
    EditText etJine;
    @BindView(R.id.rtv_pay)
    RoundTextView rtvPay;

    private String inst_id;
    private String typeID;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tuan_gou_mai_dan;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("买单");
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        inst_id = getIntent().getStringExtra("inst_id");
        typeID = getIntent().getStringExtra("typeID");
        rtvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etJine.getText().toString().equals("0")) {
                    UIHelper.ToastMessage(TuanGouMaiDanActivity.this, "输入金额不能为0");
                } else if (StringUtils.isEmpty(etJine.getText().toString())) {
                    UIHelper.ToastMessage(TuanGouMaiDanActivity.this, "请填写输入金额");
                } else {
                    TuanGouMaiDanDingDanActivity.actionStart(TuanGouMaiDanActivity.this, etJine.getText().toString().trim(), inst_id, typeID);
                    finish();
                }
            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String inst_id, String typeID) {
        Intent intent = new Intent(context, TuanGouMaiDanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("inst_id", inst_id);
        intent.putExtra("typeID", typeID);
        context.startActivity(intent);

    }
}
