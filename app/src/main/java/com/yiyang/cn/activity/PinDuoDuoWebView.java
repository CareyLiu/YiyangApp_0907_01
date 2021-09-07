package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.util.x5.utils.MyX5WebView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.JDWEB;
import static com.yiyang.cn.get_net.Urls.PDDWEB;

public class PinDuoDuoWebView extends BaseActivity {
    @BindView(R.id.webview)
    MyX5WebView webview;
    @BindView(R.id.iv_image_back)
    ImageView ivImageBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.title_header)
    ConstraintLayout titleHeader;
    @BindView(R.id.iv_close)
    ImageView ivClose;

    @Override
    public int getContentViewResId() {
        return R.layout.jd_web;

        //重写此方法，查看浏览器内部跳转
        // Log.i(String.valueOf(DetailsActivity.this), urlStr);
        /*|| url.startsWith("http:") || url.startsWith("https:")*/
        //类型我目前用到的是微信、支付宝、拨号 三种跳转方式，其他类型自加


    }

    WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url == null) {
                return false;
            }
            try {
                /*|| url.startsWith("http:") || url.startsWith("https:")*/
                if (url.startsWith("weixin://") || url.startsWith("alipays://") || url.startsWith("tel://")) {
                    //类型我目前用到的是微信、支付宝、拨号 三种跳转方式，其他类型自加
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            } catch (Exception e) {
                return false;

            }
            view.loadUrl(url);
            return true;

        }
    };


    @Override
    public void initImmersion() {
        super.initImmersion();
        mImmersionBar.titleBar(R.id.title_header).init();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webview.loadUrl(PDDWEB);
        webview.setWebViewClient(client);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack()) {
                    // 返回上一页面
                    //   webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                    webview.goBack();
                }
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack()) {
                    // 返回上一页面
                    //   webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                    webview.goBack();
                }
            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PinDuoDuoWebView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /* 改写物理按键返回的逻辑 */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            // 返回上一页面
            //   webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.destroy();
    }
}

