package com.yiyang.cn.activity.base_class;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yiyang.cn.app.BaseActivity;

/**
 * 创建日期：2017/2/13 0013 on 10:28
 * 描述:webview 设置webview基础设置
 * 作者:刘佳钊
 */
public abstract class BaseWebViewActivity extends BaseActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = getWebView();
        initWebViewSetting(webView);

    }

    public abstract WebView getWebView();

    private void initWebViewSetting(WebView webView) {
        WebSettings webSettings;
        //开启DOM缓存，关闭的话H5自身的一些操作是无效的
        webView.getSettings().setDomStorageEnabled(true);
        webSettings = webView.getSettings();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setBlockNetworkImage(false);//解决-图片加载不出来


        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置加载进来的页面自适应手机屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        // 使能JavaScript
        webSettings.setJavaScriptEnabled(true);
        // 支持中文，否则页面中中文显示乱码
        webSettings.setDefaultTextEncodingName("GBK");
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            // webView闪烁问题----7.0系统必须加此行代码  原因未知
            //原因:解决这个问题的方法是在过渡期前将WebView的硬件加速临时关闭，
            // 过渡期后再开启
            webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        }

    }

    @Override
    protected void onPause() {
        //播放关闭时候声音还在
        webView.reload();
        super.onPause();
    }
}
