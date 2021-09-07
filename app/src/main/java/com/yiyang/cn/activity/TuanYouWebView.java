package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.util.x5.utils.MyX5WebView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.PDDWEB;

public class TuanYouWebView extends BaseActivity {
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
            Log.e("url=", url);
            try {
                /*|| url.startsWith("http:") || url.startsWith("https:")*/
                if (url.startsWith("weixin://") || url.contains("alipays://platformapi")) {//如果微信或者支付宝，跳转到相应的app界面,
                    webview.goBack();
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(TuanYouWebView.this, "未安装相应的客户端", Toast.LENGTH_LONG).show();
                    }
                    return true;
                }


                /**
                 *
                 * 设置 Header 头方法
                 * window.czb.extraHeaders(String key, String value)
                 */
                if (customNavigationActivity != null && customNavigationActivity.getKey() != null) {
                    Map extraHeaders = new HashMap();
                    extraHeaders.put(customNavigationActivity.getKey(), customNavigationActivity.getValue());
                    webview.loadUrl(url, extraHeaders);
                } else {
                    webview.loadUrl(url);
                }
                return true;
            } catch (Exception e) {
                return false;

            }

        }
    };


    @Override
    public void initImmersion() {
        super.initImmersion();
        mImmersionBar.titleBar(R.id.title_header).init();
    }

    CustomNavigationJsObject1 customNavigationActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String strWeb = getIntent().getStringExtra("tuanYou");
        webview.loadUrl(strWeb);
        customNavigationActivity = new CustomNavigationJsObject1(this);
        webview.addJavascriptInterface(customNavigationActivity, "czb");//第二个参数czb不可更改，

        webview.setWebViewClient(client);
        webview.setWebChromeClient(new WebChromeClient());


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

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_SAOMASUCCESS) {
                    // x5WebView.loadUrl("javascript:java_js('appToJsPaySuccess')");
                    // x5WebView.loadUrl("http://www.baidu.com");
                    // 通过Handler发送消息
                    webview.post(new Runnable() {
                        @Override
                        public void run() {

                            // 注意调用的JS方法名要对应上
                            // 调用javascript的callJS()方法
                            webview.loadUrl("javascript:appToJsPaySuccess()");
                        }
                    });
                    Log.i("x5webviewsuccess", "webview_success");
                    UIHelper.ToastMessage(TuanYouWebView.this, "支付成功");
                } else if (message.type == ConstanceValue.MSG_SAOMAFAILE) {
                    //x5WebView.loadUrl("javascript:java_js('appToJsPayFaile')");

                    webview.post(new Runnable() {
                        @Override
                        public void run() {

                            // 注意调用的JS方法名要对应上
                            // 调用javascript的callJS()方法
                            webview.loadUrl("javascript:appToJsPayFaile()");
                        }
                    });
                    UIHelper.ToastMessage(TuanYouWebView.this, "支付失败");
                } else if (message.type == ConstanceValue.MSG_DAILISHANG_TIXIAN) {
                    if (message.content.toString().equals("0")) {
                        webview.loadUrl("javascript:appToJsTXResult(0)");
                        webview.reload();
                    } else {
                        webview.loadUrl("javascript:appToJsTXResult(1)");
                        webview.reload();
                    }
                }
            }
        }));

    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String tuanYouUrl) {
        Intent intent = new Intent(context, TuanYouWebView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("tuanYou", tuanYouUrl);
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

