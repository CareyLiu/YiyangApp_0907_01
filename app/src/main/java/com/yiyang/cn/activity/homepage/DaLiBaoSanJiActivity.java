package com.yiyang.cn.activity.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yiyang.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaLiBaoSanJiActivity extends AppCompatActivity {

    @BindView(R.id.tv_liji_buy)
    TextView tvLijiBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dalibao_sanji);
        ButterKnife.bind(this);
        tvLijiBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiBaoPageActivity.actionStart(DaLiBaoSanJiActivity.this);
            }
        });
    }
    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LiBaoPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
