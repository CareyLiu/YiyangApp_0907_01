package com.yiyang.cn.dialog.newdia;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yiyang.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FaBuLanMuDialog extends Dialog {


    private FaBuDialogListener mListener;
    private Context context;
    protected boolean dismissAfterClick = false;//是否点击按钮后关闭

    public FaBuLanMuDialog(Context context, FaBuDialogListener mListener) {
        this(context, R.style.dialogBaseBlur);
        this.mListener = mListener;
        this.context = context;
        init();
    }

    private FaBuLanMuDialog(Context context, int theme) {
        super(context, theme);
    }

    private void init() {
        View view = View.inflate(context, R.layout.layout_fabulanmu, null);

        Window window = this.getWindow();
        window.setContentView(view);
        ButterKnife.bind(this, view);
        // setContentView(R.layout.layout_fabu);
        setCanceledOnTouchOutside(true);
        setCancelable(true);


    }


    public interface FaBuDialogListener {
        void shangJiaFaBu();

        void gongJiangFaBu();

        void bianMinFabu();
    }

}
