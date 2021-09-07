package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.yiyang.cn.R;

public class BuTianOrFenXiangDialog extends Dialog {

    private View theView;
    private Context context;


    public BuTianOrFenXiangDialog(@NonNull Context context, OnDialogItemClickListener listener) {
        super(context, R.style.turntable_dialog);
        this.context = context;
        this.listener = listener;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theView = inflater.inflate(R.layout.layout_butian_or_fenxiang, null);
        getWindow().setGravity(Gravity.BOTTOM);//设置显示在底部 默认在中间

        LinearLayout llbuTian = theView.findViewById(R.id.ll_butian);
        LinearLayout llfenXiang = theView.findViewById(R.id.ll_fenxiang);
        TextView tvQuXiao = theView.findViewById(R.id.tv_quxiao);

        llbuTian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.butianClick();
                dismiss();
            }
        });


        llfenXiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.fenXiangClick();
                dismiss();
            }
        });

        tvQuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.quXiaoClick();
                dismiss();
            }
        });
        setContentView(theView);
    }

    private OnDialogItemClickListener listener;

    public interface OnDialogItemClickListener {
        void butianClick();

        void fenXiangClick();

        void quXiaoClick();
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity=Gravity.BOTTOM;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        getWindow().setAttributes(layoutParams);
    }
}
