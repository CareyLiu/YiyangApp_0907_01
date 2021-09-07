package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.model.TuanGouYouHuiJuanModel;

import androidx.annotation.NonNull;

public class KaquanZengDialog extends Dialog {
    private TextView tv_title;
    private TextView tv_money;
    private TextView tv_time;
    private TextView tv_detail;
    private TextView tv_quxiao;
    private TextView tv_queding;
    private EditText ed_phone;

    private Context context;

    public KaquanZengDialog(@NonNull Context context, KaquanZengDialog.OnDialogItemClickListener listener) {
        super(context, R.style.turntable_dialog);
        this.context = context;
        this.listener = listener;
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_kaquan_zengsong);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        getWindow().setGravity(Gravity.TOP);//设置显示在底部 默认在中间
        tv_title = findViewById(R.id.tv_title);
        tv_money = findViewById(R.id.tv_money);
        tv_time = findViewById(R.id.tv_time);
        tv_detail = findViewById(R.id.tv_detail);
        tv_queding = findViewById(R.id.tv_queding);
        tv_quxiao = findViewById(R.id.tv_quxiao);
        ed_phone = findViewById(R.id.ed_phone);

        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                String phone = ed_phone.getText().toString().trim();
                listener.qudingclick(phone, item);
            }
        });
        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.quxiaoclick();
                dismiss();
            }
        });
    }

    private KaquanZengDialog.OnDialogItemClickListener listener;
    private TuanGouYouHuiJuanModel.DataBean item;

    public interface OnDialogItemClickListener {
        void qudingclick(String phone, TuanGouYouHuiJuanModel.DataBean item);

        void quxiaoclick();

    }

    public KaquanZengDialog setModel(TuanGouYouHuiJuanModel.DataBean item) {
        this.item = item;
        tv_title.setText(item.getAgio_title());
        tv_detail.setText(item.getAgio_detail());
        tv_time.setText(item.getUser_time() + "到期");
        tv_money.setText("¥" + item.getAgio_money());
        return this;
    }
}
