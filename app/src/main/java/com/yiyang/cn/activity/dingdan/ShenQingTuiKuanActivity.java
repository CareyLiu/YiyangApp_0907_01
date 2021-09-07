package com.yiyang.cn.activity.dingdan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.roundview.RoundRelativeLayout;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.model.OrderListModel;
import com.yiyang.cn.util.AuditProgressView;

import butterknife.BindView;

public class ShenQingTuiKuanActivity extends BaseActivity {


    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_tuihuo_tuikuan)
    TextView tvTuihuoTuikuan;
    @BindView(R.id.rrl_1)
    RoundRelativeLayout rrl1;
    @BindView(R.id.iv_image1)
    ImageView ivImage1;
    @BindView(R.id.tv_tuihuo_tuikuan1)
    TextView tvTuihuoTuikuan1;
    @BindView(R.id.rrl_2)
    RoundRelativeLayout rrl2;

    private String dingDanId;
    private String money;
    private String usePayCheck;

    /**
     * user_pay_check	订单状态：0.全部1.待付款 3.待发货
     * 4.待收货5.到店消费6.待评价7.已完成 8.9.10.退货/退款
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dingDanId = getIntent().getStringExtra("dingdanId");
        money = getIntent().getStringExtra("money");
        usePayCheck = getIntent().getStringExtra("usePayCheck");
        rrl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //无需退货退款
                if (usePayCheck.equals("3")) {
                    UIHelper.ToastMessage(ShenQingTuiKuanActivity.this, "请您收货后再进行退货退款操作");
                    return;
                }
                DingDanShenQingTuikuanActivity.actionStart(ShenQingTuiKuanActivity.this, "我要退货退款", dingDanId, money);
            }
        });

        rrl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退货退款
                DingDanShenQingTuikuanActivity.actionStart(ShenQingTuiKuanActivity.this, "我要退款(无需退货)", dingDanId, money);
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shen_qing_tui_kuan;
    }

    @Override
    public boolean showToolBar() {
        return true;
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


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String dingDanId, String money, String usePayCheck) {
        Intent intent = new Intent(context, ShenQingTuiKuanActivity.class);
        intent.putExtra("dingdanId", dingDanId);
        intent.putExtra("money", money);
        intent.putExtra("usePayCheck", usePayCheck);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
