package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.yiyang.cn.R;

public class BuTianYaoQingMaDialog extends Dialog {
    private View theView;
    private Context context;

    private TextView tvQueDing, tvQuXiao;
    private EditText etText;

    public BuTianYaoQingMaDialog(@NonNull Context context, BuTianYaoQingMaDialog.OnDialogItemClickListener listener) {
        super(context, R.style.turntable_dialog);
        this.context = context;
        this.listener = listener;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theView = inflater.inflate(R.layout.layout_butian_yaoqingma, null);
        getWindow().setGravity(Gravity.TOP);//设置显示在底部 默认在中间
        tvQueDing = theView.findViewById(R.id.tv_queding);
        tvQuXiao = theView.findViewById(R.id.tv_quxiao);
        etText = theView.findViewById(R.id.tv_butian);

        //EditText 获得焦点时hint消失，失去焦点时hint显示
        etText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    ((TextView) v).setHint("补填邀请码");
                } else {
                    ((TextView) v).setHint("");
                }

            }
        });


        tvQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etText.getText().toString().trim();
                listener.qudingclick(str);
            }
        });
        tvQuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.quxiaoclick();
            }
        });

        setContentView(theView);
    }

    private BuTianYaoQingMaDialog.OnDialogItemClickListener listener;

    public interface OnDialogItemClickListener {
        void qudingclick(String str);

        void quxiaoclick();

    }


}
