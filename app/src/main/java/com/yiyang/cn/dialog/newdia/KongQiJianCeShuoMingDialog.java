package com.yiyang.cn.dialog.newdia;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyang.cn.R;


public class KongQiJianCeShuoMingDialog extends Dialog {

    protected boolean dismissAfterClick = true;//是否点击按钮后关闭
    private int type;//1.消息推送    2.操作失败    3.操作提示    4.操作成功    5.删除
    private String str;
    private TextView tvStr;
    private String title;
    private TextView tvTitle;

    public KongQiJianCeShuoMingDialog(Context context, String str, String title) {
        this(context, R.style.dialogBaseBlur);
        this.type = type;
        this.str = str;
        this.title = title;
        init();
    }

    private KongQiJianCeShuoMingDialog(Context context, int theme) {
        super(context, theme);
    }

    private void init() {
        setContentView(R.layout.dialog_tishi_shuoming);
        tvStr = findViewById(R.id.tv_text);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(title);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        tvStr.setText(str);

    }


}
