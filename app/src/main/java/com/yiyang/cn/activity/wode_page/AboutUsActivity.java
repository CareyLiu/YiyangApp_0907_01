package com.yiyang.cn.activity.wode_page;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.DefaultX5WebViewActivity;
import com.yiyang.cn.app.BaseActivity;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.ARGEMENTS;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_yinsi)
    TextView tvYinsi;
    @BindView(R.id.tv_yonghushiyong)
    TextView tvYonghushiyong;
    @BindView(R.id.tv_banben_hao)
    TextView tvBanbenHao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvYonghushiyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DefaultX5WebViewActivity.actionStart(AboutUsActivity.this, ARGEMENTS);
            }
        });
        tvYinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DefaultX5WebViewActivity.actionStart(AboutUsActivity.this, "https://shop.hljsdkj.com/shop_new/privacy_clause");
            }
        });
        tvBanbenHao.setText("版本号: v" + getAppVersionName(AboutUsActivity.this));
    }

    private String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.yiyang.cn", 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_about_us;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("关于我们");
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


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AboutUsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
