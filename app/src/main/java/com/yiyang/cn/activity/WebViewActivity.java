package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jaeger.library.StatusBarUtil;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.web)
    WebView web;
    private String url;
    private String share_title;
    private String share_pic;
    private String share_keyword;
    private String title;
    private String type;
    private Context context;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_webview;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);
        context = this;
        //获取页面传递过来的数据
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        share_title = getIntent().getStringExtra("share_title");
        share_pic = getIntent().getStringExtra("share_pic");
        share_keyword = getIntent().getStringExtra("share_keyword");
        type = getIntent().getStringExtra("type");


        web = (WebView) this.findViewById(R.id.web);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.removeJavascriptInterface("searchBoxJavaBredge_");// webview4.2以下漏洞
        synCookies(WebViewActivity.this, url);// 设置jsession
        if (url != null) web.loadUrl(url);
        web.addJavascriptInterface(WebViewActivity.this, "web");
        web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                if (url.indexOf("app/msg.html") > 0 || url.indexOf("center.html") > 0) {
                    WebViewActivity.this.setResult(11);//回调
                    finish();
                    return false;
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });


    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("神灯科技");
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


    @JavascriptInterface
    public void setShareInfo(String title, String content, String picurl, String url) {
        this.url = url;
        this.share_title = title;
        this.share_pic = picurl;
        this.share_keyword = content;
    }

    @JavascriptInterface
    public void setUrl(String url) {
        web.loadUrl(url);
    }


    @JavascriptInterface
    public String getUserInfo() {
        return "";

    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();// 移除
        //cookieManager.setCookie(ConstantsUtil.DOMAIN, BasicHttpClient.getSessionCookieForWebView());// cookies是在HttpClient中获得的cookie
        // Log.i("web", BasicHttpClient.getSessionCookieForWebView());
        CookieSyncManager.getInstance().sync();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 屏幕横竖屏切换时避免出现window leak的问题
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }


}
