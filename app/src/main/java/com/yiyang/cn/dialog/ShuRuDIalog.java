package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.ShuRuInterView;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.util.Util;


public class ShuRuDIalog implements View.OnClickListener {

    private Context mContext;
    private int noticeType;
    private Dialog mDialog;
    public View mView;
    private DisplayMetrics dm;
    private WindowManager windowManager;
    private int mStyle = R.style.UserinfoDialogStyle;
    private TextView tv_dialog_name, tv_clean, tv_submit;
    private EditText et_name;

    private ShuRuInterView shuRuInterView;

    public ShuRuDIalog(@NonNull Context context, ShuRuInterView shuRuInterView) {
        mContext = context;
        this.shuRuInterView = shuRuInterView;
        initView();
    }

    private void initView() {
        mDialog = new Dialog(mContext, mStyle);
        mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_zhineng_family_add_1, null);
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
        mDialog.setCanceledOnTouchOutside(false);
        tv_dialog_name = mView.findViewById(R.id.tv_dialog_name);
        et_name = mView.findViewById(R.id.et_name);
        tv_clean = mView.findViewById(R.id.tv_clean);
        tv_submit = mView.findViewById(R.id.tv_submit);
        tv_clean.setOnClickListener(this);
        tv_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_clean:
                Util.pickKey(mContext, mView);
                dismiss();
                break;
            case R.id.tv_submit:
                shuRuInterView.submit(et_name.getText().toString());
                Util.pickKey(mContext, mView);
                dismiss();
                break;
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
}
