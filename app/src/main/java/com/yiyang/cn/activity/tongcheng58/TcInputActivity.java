package com.yiyang.cn.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class TcInputActivity extends BaseActivity {


    @BindView(R.id.ed_input)
    EditText ed_input;
    @BindView(R.id.tv_input)
    TextView tv_input;
    @BindView(R.id.bt_save)
    TextView bt_save;

    private String type;
    private String text;

    @Override
    public int getContentViewResId() {
        return R.layout.tongcheng_act_input;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("通用输入");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void actionStart(Context context, String type, String text) {
        Intent intent = new Intent(context, TcInputActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("type", type);
        intent.putExtra("text", text);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getStringExtra("type");
        text = getIntent().getStringExtra("text");
        tv_input.setText(text.length() + "/200");
        ed_input.setText(text);
        ed_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                tv_input.setText(text.length() + "/200");
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        save();
    }

    @OnClick(R.id.bt_save)
    public void onViewClicked() {
        save();
    }

    private void save() {
        String input = ed_input.getText().toString();
        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_TONGYONG_INPUT;
        notice.content = input;
        notice.input_type = type;
        sendRx(notice);
        finish();
    }
}
