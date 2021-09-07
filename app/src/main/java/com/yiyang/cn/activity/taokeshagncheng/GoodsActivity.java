package com.yiyang.cn.activity.taokeshagncheng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcShopPage;
import com.alibaba.baichuan.trade.biz.applink.adapter.AlibcFailModeType;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.alibaba.baichuan.trade.common.utils.AlibcLogger;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class GoodsActivity extends BaseActivity {
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.logout)
    Button logout;
    @BindView(R.id.isLogin)
    Button isLogin;
    @BindView(R.id.open_by_url)
    Button openByUrl;
    @BindView(R.id.open_by_code)
    Button openByCode;
    @BindView(R.id.open_by_webview)
    Button openByWebview;
    final String url = "https://detail.tmall.com/item.htm?spm=a219t.11817213.0.d74b5b3a5.705f75a5cxvBzm&id=580302723446&scm=1007.15348.109552.0_28030&pvid=3f7cbf6e-d79a-42f5-8e3c-d12c96c1cbad&app_pvid=59590_11.15.108.255_514_1586322750314&ptl=floorId:28030;originalFloorId:28030;pvid:3f7cbf6e-d79a-42f5-8e3c-d12c96c1cbad;app_pvid:59590_11.15.108.255_514_1586322750314&union_lens=lensId:OPT@1586322750@3f7cbf6e-d79a-42f5-8e3c-d12c96c1cbad_580302723446@1&skuId=4305395376197";

    @Override
    public int getContentViewResId() {
        return R.layout.layout_goods_demo;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //AlibcBasePage page = new AlibcDetailPage(itemId);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlibcLogin alibcLogin = AlibcLogin.getInstance();
                alibcLogin.showLogin(new AlibcLoginCallback() {
                    @Override
                    public void onSuccess(int result, String userId, String nick) {
                        Toast.makeText(GoodsActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(GoodsActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlibcLogin alibcLogin = AlibcLogin.getInstance();
                alibcLogin.logout(new AlibcLoginCallback() {
                    @Override
                    public void onSuccess(int i, String s, String s1) {
                        Toast.makeText(GoodsActivity.this, "登出成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(GoodsActivity.this, "登出成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        openByUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlibcShowParams showParams = new AlibcShowParams();
                showParams.setOpenType(OpenType.Auto);
                showParams.setClientType("taobao");
                showParams.setBackUrl("");
                showParams.setNativeOpenFailedMode(AlibcFailModeType.AlibcNativeFailModeJumpH5);

                AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
                taokeParams.setPid("mm_1075130017_1502650362_110240000445");

                Map<String, String> trackParams = new HashMap<>();
                // 通过百川内部的webview打开页面
                AlibcTrade.openByUrl(GoodsActivity.this, "", url, null,
                        new WebViewClient(), new WebChromeClient(),
                        showParams, taokeParams, trackParams, new AlibcTradeCallback() {
                            @Override
                            public void onTradeSuccess(AlibcTradeResult tradeResult) {
                                AlibcLogger.i("GoodsActivity", "request success");
                            }

                            @Override
                            public void onFailure(int code, String msg) {
                                AlibcLogger.e("GoodsActivity", "code=" + code + ", msg=" + msg);
                                if (code == -1) {
                                    Toast.makeText(GoodsActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        openByCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlibcShowParams showParams = new AlibcShowParams();
                showParams.setOpenType(OpenType.Native);
                showParams.setClientType("taobao");
                showParams.setBackUrl("");
//                showParams.setNativeOpenFailedMode(AlibcFailModeType.AlibcNativeFailModeJumpDOWNLOAD);

                AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
                taokeParams.setPid("mm_1075130017_1502650362_110240000445");

                Map<String, String> trackParams = new HashMap<>();

                AlibcBasePage page = new AlibcDetailPage("529245330612");
                AlibcTrade.openByBizCode(GoodsActivity.this, page, null, new WebViewClient(), new WebChromeClient(),
                        "detail", showParams, taokeParams, trackParams, new AlibcTradeCallback() {
                            @Override
                            public void onTradeSuccess(AlibcTradeResult tradeResult) {
                                // 交易成功回调（其他情形不回调）
                                AlibcLogger.i("MainActivity", "open detail page success");
                            }

                            @Override
                            public void onFailure(int code, String msg) {
                                AlibcLogger.e("MainActivity", "code=" + code + ", msg=" + msg);
                                if (code == -1) {
                                    Toast.makeText(GoodsActivity.this, "唤端失败，失败模式为none", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

//                AlibcBasePage shopPage = new AlibcShopPage("65626181");

//                AlibcTrade.openByBizCode(GoodsActivity.this, shopPage, null, new WebViewClient(), new WebChromeClient(),
//                        "shop", showParams, taokeParams, trackParams, new AlibcTradeCallback() {
//                            @Override
//                            public void onTradeSuccess(AlibcTradeResult tradeResult) {
//                                // 交易成功回调（其他情形不回调）
//                                AlibcLogger.i("MainActivity", "open detail page success");
//                            }
//
//                            @Override
//                            public void onFailure(int code, String msg) {
//                                AlibcLogger.e("MainActivity", "code=" + code + ", msg=" + msg);
//                                if (code == -1) {
//                                    Toast.makeText(GoodsActivity.this, "唤端失败，失败模式为none", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });

            }
        });

    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, GoodsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }


}
