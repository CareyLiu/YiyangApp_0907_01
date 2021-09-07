package com.yiyang.cn.activity.yaokongqi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.dialog.newdia.TishiDialog;

import androidx.annotation.NonNull;

public class YaokongPeiDialog extends Dialog {
    private TextView tv_anjian;

    public YaokongPeiDialog(Context context) {
        this(context, R.style.dialogBaseBlur);
        init();
    }

    private YaokongPeiDialog(Context context, int theme) {
        super(context, theme);
    }

    private void init() {
        setContentView(R.layout.layout_wanneng_yaokongqi_dialog);
        setCanceledOnTouchOutside(false);
        setCancelable(true);

        tv_anjian = findViewById(R.id.tv_anjian);
    }


    public void setKey(String key) {
        String content = "点按「" + key + "」";
        tv_anjian.setText(content);
    }
}
