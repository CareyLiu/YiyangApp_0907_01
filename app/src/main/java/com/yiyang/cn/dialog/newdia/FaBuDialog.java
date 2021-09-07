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


public class FaBuDialog extends Dialog {


    @BindView(R.id.iv_shangjiafabu)
    ImageView ivShangjiafabu;
    @BindView(R.id.rl_shangjiafabu)
    RelativeLayout rlShangjiafabu;
    @BindView(R.id.iv_gongjiangfabu)
    ImageView ivGongjiangfabu;
    @BindView(R.id.rl_gongjiangfabu)
    RelativeLayout rlGongjiangfabu;
    @BindView(R.id.iv_bianminfabu)
    ImageView ivBianminfabu;
    @BindView(R.id.rl_bianminfabu)
    RelativeLayout rlBianminfabu;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    private FaBuDialogListener mListener;
    private Context context;
    protected boolean dismissAfterClick = false;//是否点击按钮后关闭

    public FaBuDialog(Context context, FaBuDialogListener mListener) {
        this(context, R.style.dialogBaseBlur);
        this.mListener = mListener;
        this.context = context;
        init();
    }

    private FaBuDialog(Context context, int theme) {
        super(context, theme);
    }

    private void init() {
        View view = View.inflate(context, R.layout.layout_fabu, null);

        Window window = this.getWindow();
        window.setContentView(view);
        ButterKnife.bind(this, view);
        // setContentView(R.layout.layout_fabu);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        rlBianminfabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.bianMinFabu();
            }
        });

        rlShangjiafabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.shangJiaFaBu();
            }
        });
        rlGongjiangfabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gongJiangFaBu();
            }
        });
        rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    public interface FaBuDialogListener {
        void shangJiaFaBu();

        void gongJiangFaBu();

        void bianMinFabu();
    }

}
