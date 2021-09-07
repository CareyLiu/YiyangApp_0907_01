package com.yiyang.cn.activity.yaokongqi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yiyang.cn.R;

public class YaokongPeiFirstDialog extends Dialog {
    public View iv_colse;

    public YaokongPeiFirstDialog(Context context) {
        this(context, R.style.dialogBaseBlur);
        init();
    }

    private YaokongPeiFirstDialog(Context context, int theme) {
        super(context, theme);
    }

    private void init() {
        setContentView(R.layout.layout_wanneng_yaokongqi_dialog_first);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        iv_colse = findViewById(R.id.iv_colse);

        iv_colse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
