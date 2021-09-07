package com.yiyang.cn.dialog.newdia;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyang.cn.R;

import butterknife.BindView;


public class YuYinZhiFuDialog extends Dialog {


    @BindView(R.id.tv_yuefen)
    TextView tvYuefen;
    @BindView(R.id.rl_diyige)
    RelativeLayout rlDiyige;
    @BindView(R.id.tv_yuefen1)
    TextView tvYuefen1;
    @BindView(R.id.rl_dierge)
    RelativeLayout rlDierge;
    @BindView(R.id.tv_yuefen2)
    TextView tvYuefen2;
    @BindView(R.id.rl_disange)
    RelativeLayout rlDisange;
    private YuYinZhiFuDialogListener mListener;
    protected boolean dismissAfterClick = false;//是否点击按钮后关闭

    public YuYinZhiFuDialog(Context context, YuYinZhiFuDialogListener mListener) {
        this(context, R.style.dialogBaseBlur);
        this.mListener = mListener;
        init();
    }

    private YuYinZhiFuDialog(Context context, int theme) {
        super(context, theme);
    }

    private void init() {
        setContentView(R.layout.dialog_yuyin_zhifu);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        rlDiyige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllBackground();
                rlDiyige.setBackgroundColor(R.drawable.yuyin_zhifu_choose);
            }
        });

        rlDierge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllBackground();
                rlDierge.setBackgroundColor(R.drawable.yuyin_zhifu_choose);
            }
        });

        rlDisange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllBackground();
                rlDisange.setBackgroundColor(R.drawable.yuyin_zhifu_choose);
            }
        });

    }


    public interface YuYinZhiFuDialogListener {
        void kaiTong(View v, YuYinZhiFuDialog dialog);

    }

    public void setAllBackground() {
        rlDiyige.setBackgroundColor(R.drawable.yuyin_zhifu_no_choose);
        rlDierge.setBackgroundColor(R.drawable.yuyin_zhifu_no_choose);
        rlDisange.setBackgroundColor(R.drawable.yuyin_zhifu_no_choose);
    }

}
