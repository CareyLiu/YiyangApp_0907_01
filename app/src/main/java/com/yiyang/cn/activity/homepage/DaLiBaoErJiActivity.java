package com.yiyang.cn.activity.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;

import butterknife.BindView;

public class DaLiBaoErJiActivity extends BaseActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_liji_buy)
    TextView tvLijiBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String strUrl = getIntent().getStringExtra("imgUrl");
        Glide.with(DaLiBaoErJiActivity.this).load(strUrl).into(ivImage);
        title = getIntent().getStringExtra("strName");
        initToolbar();
        tvLijiBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiBaoPageActivity.actionStart(DaLiBaoErJiActivity.this);
            }
        });

        if (title.equals("东北三省旅游景点门票")) {
            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DaLiBaoSanJiActivity.actionStart(DaLiBaoErJiActivity.this);
                }
            });
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_da_li_bao_er_ji;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String imgUrl, String strName) {
        Intent intent = new Intent(context, DaLiBaoErJiActivity.class);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("strName", strName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    String title;

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText(title);
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }
}
