package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.yiyang.cn.R;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.util.Util;


public class ZhiNengFamilyAddDIalog implements View.OnClickListener {

    private Context mContext;
    private int noticeType;
    private Dialog mDialog;
    private View mView;
    private DisplayMetrics dm;
    private WindowManager windowManager;
    private int mStyle = R.style.UserinfoDialogStyle;
    private TextView tv_dialog_name, tv_clean, tv_submit;
    private EditText et_name;

    public ZhiNengFamilyAddDIalog(@NonNull Context context, int noticeType) {
        mContext = context;
        this.noticeType = noticeType;
        initView();
    }

    private void initView() {
        mDialog = new Dialog(mContext, mStyle);
        mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_zhineng_family_add, null);
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

        switch (noticeType) {
            case ConstanceValue.MSG_FAMILY_MANAGE_ADD:
                tv_dialog_name.setText("请输入新建家庭名字");
                break;
            case ConstanceValue.MSG_FAMILY_MANAGE_CHANGENAME:
                tv_dialog_name.setText("请输入新建家庭名字");
                break;
            case ConstanceValue.MSG_ROOM_MANAGE_ADD:
                tv_dialog_name.setText("请输入新建房间名字");
                break;
            case ConstanceValue.MSG_ROOM_MANAGE_CHANGENAME:
                tv_dialog_name.setText("请输入新建房间名字");
                break;
            case ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME:
                tv_dialog_name.setText("请输入设备新名字");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_clean:
                Util.pickKey(mContext, mView);
                dismiss();
                break;
            case R.id.tv_submit:
                String family_name = et_name.getText().toString();
                if (family_name.isEmpty()) {
                    switch (noticeType){
                        case ConstanceValue.MSG_FAMILY_MANAGE_ADD:
                            Toast.makeText(mContext, "家庭名不能为空", Toast.LENGTH_SHORT).show();
                            break;
                        case ConstanceValue.MSG_FAMILY_MANAGE_CHANGENAME:
                            Toast.makeText(mContext, "家庭名不能为空", Toast.LENGTH_SHORT).show();
                            break;
                        case ConstanceValue.MSG_ROOM_MANAGE_ADD:
                            Toast.makeText(mContext, "房间名不能为空", Toast.LENGTH_SHORT).show();
                            break;
                        case ConstanceValue.MSG_ROOM_MANAGE_CHANGENAME:
                            Toast.makeText(mContext, "房间名不能为空", Toast.LENGTH_SHORT).show();
                            break;
                        case ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME:
                            Toast.makeText(mContext, "设备名不能为空", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    return;
                }
                Notice n = new Notice();
                n.type = noticeType;
                n.content = family_name;
                RxBus.getDefault().sendRx(n);
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
