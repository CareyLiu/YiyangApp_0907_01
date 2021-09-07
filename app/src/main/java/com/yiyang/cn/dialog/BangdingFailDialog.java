package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yiyang.cn.R;


public class BangdingFailDialog extends Dialog implements View.OnClickListener {

    public TextView tv_content;
    public TextView bt_ok;
    public TextView bt_jixusao;
    protected boolean dismissAfterClick = true;
    private BangdingClick click;

    public void setClick(BangdingClick click) {
        this.click = click;
    }

    public BangdingFailDialog(Context context) {
        this(context, R.style.dialogBaseBlur);
    }

    public BangdingFailDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    private void init() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_bangding_fail);
        tv_content = findViewById(R.id.tv_content);
        bt_ok = findViewById(R.id.bt_ok);
        bt_jixusao = findViewById(R.id.bt_jixusao);
        bt_ok.setOnClickListener(this);
        bt_jixusao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_ok) {
            dismissAfterClick();
            if (click != null) {
                click.close();
            }
        } else if (v == bt_jixusao) {
            dismissAfterClick();
            if (click != null) {
                click.jixu();
            }
        }
    }

    protected void dismissAfterClick() {
        if (dismissAfterClick) {
            dismiss();
        }
    }

    /**
     * 设置是否点击按钮后自动关闭窗口,默认true(是)
     */
    public BangdingFailDialog setDismissAfterClick(boolean dismissAfterClick) {
        this.dismissAfterClick = dismissAfterClick;
        return this;
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void setTextContent(String msg) {
        tv_content.setText(msg);
    }

    public interface BangdingClick {
        void close();

        void jixu();
    }
}
