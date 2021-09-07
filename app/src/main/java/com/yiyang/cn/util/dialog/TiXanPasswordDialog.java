package com.yiyang.cn.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.yiyang.cn.R;
import com.yiyang.cn.inter.PayPassWordInter;
import com.yiyang.cn.view.Keyboard;
import com.yiyang.cn.view.PayEditText;


public class TiXanPasswordDialog extends Dialog {


    Context context;
    PayPassWordInter inter;
    private static final String[] KEY = new String[]{
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "", "0", "<<"
    };

    public TiXanPasswordDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_magic_password);
        PayEditText payEdittext = findViewById(R.id.pay_edittext);
        Keyboard keyboard = findViewById(R.id.KeyboardView_pay);
        keyboard.setKeyboardKeys(KEY);

        keyboard.setOnClickKeyboardListener(new Keyboard.OnClickKeyboardListener() {
            @Override
            public void onKeyClick(int position, String value) {
                if (position < 11) {
                    payEdittext.add(value);
                } else if (position == 11) {
                    payEdittext.remove();
                }
            }
        });
        payEdittext.setOnInputFinishedListener(new PayEditText.OnInputFinishedListener() {
            @Override
            public void onInputFinished(String password) {
                inter = (PayPassWordInter) context;
                inter.setPwd(password);
                dismiss();

            }
        });


    }


}
