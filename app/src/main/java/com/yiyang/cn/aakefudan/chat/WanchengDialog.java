package com.yiyang.cn.aakefudan.chat;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.aakefudan.model.ZixunModel;

import androidx.annotation.NonNull;

public class WanchengDialog implements View.OnClickListener {

    private TextView bt_ok;


    private Dialog mDialog;
    private View mView;
    private DisplayMetrics dm;
    private WindowManager windowManager;
    private int mStyle = R.style.UserinfoDialogStyle;
    private Context mContext;

    public WanchengDialog(@NonNull Context context) {
        this.mContext = context;
        initView();
    }

    private void initView() {
        mDialog = new Dialog(mContext, mStyle);
        mView = LayoutInflater.from(mContext).inflate(R.layout.a_dialog_service_wancheng, null);

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

        bt_ok = mView.findViewById(R.id.bt_ok);
        bt_ok.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (cilck != null) {
            cilck.click();
        }
    }

    private OnDaohangCilck cilck;

    public void setCilck(OnDaohangCilck cilck) {
        this.cilck = cilck;
    }

    public interface OnDaohangCilck {
        void click();
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
