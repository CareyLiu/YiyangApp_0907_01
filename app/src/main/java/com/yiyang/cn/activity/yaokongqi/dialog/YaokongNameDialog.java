package com.yiyang.cn.activity.yaokongqi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yiyang.cn.R;

public class YaokongNameDialog extends Dialog {
    private EditText ed_name;
    private TextView tv_num;
    private TextView bt_cancel;
    private TextView bt_confirm;
    private ClickSure clickSure;

    public void setClickSure(ClickSure clickSure) {
        this.clickSure = clickSure;
    }

    public YaokongNameDialog(Context context) {
        this(context, R.style.dialogBaseBlur);
        init();
    }

    private YaokongNameDialog(Context context, int theme) {
        super(context, theme);
    }

    private void init() {
        setContentView(R.layout.layout_wanneng_yaokongqi_dialog_name);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        ed_name = findViewById(R.id.ed_name);
        tv_num = findViewById(R.id.tv_num);
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_confirm = findViewById(R.id.bt_confirm);

        ed_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                tv_num.setText(text.length() + "/6");
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickSure != null) {
                    clickSure.clickSure();
                }
            }
        });
    }

    public interface ClickSure {
        void clickSure();
    }

    public String getText() {
        String string = ed_name.getText().toString();
        return string;
    }
}
