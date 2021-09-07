package com.yiyang.cn.activity.wode_page.bazinew.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyang.cn.R;

import androidx.annotation.NonNull;
import butterknife.BindView;


public class JiesuoDialog implements View.OnClickListener {
    TextView tv_nian1;
    TextView tv_nian_wei;
    TextView tv_nian_ci;
    TextView tv_nian_money;
    LinearLayout ll_nian;
    TextView tv_ri1;
    TextView tv_ri_wei;
    TextView tv_ri_ci;
    TextView tv_ri_money;
    LinearLayout ll_ri;
    Button bt_select;

    private Context mContext;
    private Dialog mDialog;
    private View mView;
    private DisplayMetrics dm;
    private WindowManager windowManager;
    private int mStyle = R.style.UserinfoDialogStyle;

    private JieSuoPayClick payClick;

    private int mType;
    private int payType;

    public JiesuoDialog(@NonNull Context context, int type) {
        mContext = context;
        mType = type;
        initView();
    }

    public void setPayClick(JieSuoPayClick payClick) {
        this.payClick = payClick;
    }

    private void initView() {
        mDialog = new Dialog(mContext, mStyle);
        mView = LayoutInflater.from(mContext).inflate(R.layout.bazi_dialog_jiesuo, null);

        dm = new DisplayMetrics();
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        Window window = mDialog.getWindow();
//        window.setWindowAnimations(R.style.dialogWindowAnim); // 添加动画
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        mDialog.getWindow().setAttributes(lp);
        mDialog.setContentView(mView);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        tv_nian1 = mView.findViewById(R.id.tv_nian1);
        tv_nian_wei = mView.findViewById(R.id.tv_nian_wei);
        tv_nian_ci = mView.findViewById(R.id.tv_nian_ci);
        tv_nian_money = mView.findViewById(R.id.tv_nian_money);
        ll_nian = mView.findViewById(R.id.ll_nian);
        tv_ri1 = mView.findViewById(R.id.tv_ri1);
        tv_ri_wei = mView.findViewById(R.id.tv_ri_wei);
        tv_ri_ci = mView.findViewById(R.id.tv_ri_ci);
        tv_ri_money = mView.findViewById(R.id.tv_ri_money);
        ll_ri = mView.findViewById(R.id.ll_ri);
        bt_select = mView.findViewById(R.id.bt_select);

        ll_nian.setOnClickListener(this);
        ll_ri.setOnClickListener(this);
        bt_select.setOnClickListener(this);

        if (mType == 1) {
            ll_ri.setVisibility(View.VISIBLE);
            select(0);
        } else {
            ll_ri.setVisibility(View.GONE);
            payType = 1;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_ri:
                select(0);
                break;
            case R.id.ll_nian:
                select(1);
                break;
            case R.id.bt_select:
                pay();
                break;
        }
    }

    private void pay() {
        dismiss();
        if (payType == 0) {
            if (payClick!=null){
                payClick.payCi();
            }
        } else {
            if (payClick!=null){
                payClick.payNian();
            }
        }
    }

    private void select(int i) {
        if (mType == 1) {
            ll_ri.setBackground(null);
            ll_nian.setBackground(null);
            payType = i;

            tv_nian1.setTextColor(Color.parseColor("#000000"));
            tv_nian_ci.setTextColor(Color.parseColor("#000000"));
            tv_nian_wei.setTextColor(Color.parseColor("#000000"));
            tv_nian_money.setTextColor(Color.parseColor("#666666"));
            tv_ri1.setTextColor(Color.parseColor("#000000"));
            tv_ri_ci.setTextColor(Color.parseColor("#000000"));
            tv_ri_wei.setTextColor(Color.parseColor("#000000"));
            tv_ri_money.setTextColor(Color.parseColor("#666666"));
            if (i == 1) {
                tv_nian1.setTextColor(Color.parseColor("#6666CC"));
                tv_nian_ci.setTextColor(Color.parseColor("#6666CC"));
                tv_nian_wei.setTextColor(Color.parseColor("#6666CC"));
                tv_nian_money.setTextColor(Color.parseColor("#6666CC"));
                ll_nian.setBackgroundResource(R.drawable.bazi_jiexi_select_s);
            } else {
                tv_ri1.setTextColor(Color.parseColor("#6666CC"));
                tv_ri_ci.setTextColor(Color.parseColor("#6666CC"));
                tv_ri_wei.setTextColor(Color.parseColor("#6666CC"));
                tv_ri_money.setTextColor(Color.parseColor("#6666CC"));
                ll_ri.setBackgroundResource(R.drawable.bazi_jiexi_select_s);
            }
        }
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    public boolean isShow() {
        if (mDialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }

    public interface JieSuoPayClick{
        void  payCi();

        void payNian();
    }
}
