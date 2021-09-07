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
import com.yiyang.cn.aakefudan.chat.MyMessage;
import com.yiyang.cn.aakefudan.model.ZixunModel;

import androidx.annotation.NonNull;

public class DaohangDialog implements View.OnClickListener {
    private TextView tv_name;
    private TextView tv_dizhi;
    private TextView tv_chepai;
    private TextView bt_ok;


    private Dialog mDialog;
    private View mView;
    private DisplayMetrics dm;
    private WindowManager windowManager;
    private int mStyle = R.style.UserinfoDialogStyle;

    private Context mContext;
    private ZixunModel.DataBean.ListBean model;

    public DaohangDialog(@NonNull Context context, ZixunModel.DataBean.ListBean model) {
        this.mContext = context;
        this.model = model;
        initView();
    }

    private void initView() {
        mDialog = new Dialog(mContext, mStyle);
        mView = LayoutInflater.from(mContext).inflate(R.layout.a_dialog_service_daohang, null);

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
        tv_name = mView.findViewById(R.id.tv_name);
        tv_chepai = mView.findViewById(R.id.tv_chepai);
        tv_dizhi = mView.findViewById(R.id.tv_dizhi);

        tv_name.setText(model.getInst_name());
        tv_dizhi.setText(model.getAddr() + "  " + model.getMeter() + "km");

        bt_ok.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (cilck != null) {
            cilck.click(model);
        }
    }

    private OnDaohangCilck cilck;

    public void setCilck(OnDaohangCilck cilck) {
        this.cilck = cilck;
    }

    public interface OnDaohangCilck {
        void click(ZixunModel.DataBean.ListBean model);
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
