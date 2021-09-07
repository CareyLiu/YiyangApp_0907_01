package com.yiyang.cn.activity.tuya_device.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yiyang.cn.R;


public class TuyaTishiDialog extends Dialog implements View.OnClickListener {

    public TextView tv_content;
    public TextView tv_title;
    public TextView tv_cancel;
    public TextView tv_confirm;
    public View vv_line;

    protected boolean dismissAfterClick = true;
    private TishiDialogListener mListener;

    public TuyaTishiDialog setmListener(TishiDialogListener mListener) {
        this.mListener = mListener;
        return this;
    }

    public TuyaTishiDialog(Context context, TishiDialogListener mListener) {
        this(context, R.style.dialogBaseBlur);
        this.mListener = mListener;
    }

    public TuyaTishiDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    private void init() {
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_tuya_tishi);

        vv_line = findViewById(R.id.vv_line);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_confirm = findViewById(R.id.tv_confirm);


        tv_cancel.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);

        setTextTitle("提示").setTextConfirm("确定").setTextCancel("取消");
    }

    // ---------------------------text
    public TuyaTishiDialog setTextTitle(String text) {
        tv_title.setText(text);
        return this;
    }

    public TuyaTishiDialog setTextCancel(String text) {
        if (TextUtils.isEmpty(text)) {
            tv_cancel.setVisibility(View.GONE);
            vv_line.setVisibility(View.GONE);
        } else {
            tv_cancel.setVisibility(View.VISIBLE);
            vv_line.setVisibility(View.VISIBLE);
            tv_cancel.setText(text);
        }
        return this;
    }

    public TuyaTishiDialog setTextConfirm(String text) {
        tv_confirm.setText(text);
        return this;
    }

    public TuyaTishiDialog setTextCont(String text) {
        tv_content.setText(text);
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == tv_cancel) {
            clickCancel(v);
        } else if (v == tv_confirm) {
            clickConfirm(v);
        }
    }


    private void clickCancel(View v) {
        if (mListener != null) {
            mListener.onClickCancel(v, TuyaTishiDialog.this);
        }
        dismissAfterClick();
    }

    private void clickConfirm(View v) {
        if (mListener != null) {
            mListener.onClickConfirm(v, TuyaTishiDialog.this);
        }
        dismissAfterClick();
    }

    protected void dismissAfterClick() {
        if (dismissAfterClick) {
            dismiss();
        }
    }

    /**
     * 设置是否点击按钮后自动关闭窗口,默认true(是)
     *
     * @param dismissAfterClick
     * @return
     */
    public TuyaTishiDialog setDismissAfterClick(boolean dismissAfterClick) {
        this.dismissAfterClick = dismissAfterClick;
        return this;
    }


    public interface TishiDialogListener {
        void onClickCancel(View v, TuyaTishiDialog dialog);

        void onClickConfirm(View v, TuyaTishiDialog dialog);

        void onDismiss(TuyaTishiDialog dialog);
    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (mListener != null) {
            mListener.onDismiss(TuyaTishiDialog.this);
        }
    }
}
