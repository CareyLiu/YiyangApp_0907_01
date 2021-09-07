package com.yiyang.cn.activity.taokeshagncheng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.common.utils.AlibcLogger;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.TaoKeListAdapter;
import com.yiyang.cn.adapter.TaoKeListAdapter1;
import com.yiyang.cn.adapter.TaoKeListAdapter2;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.TaoKeDetailList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.TAOKELIST;

public class TaokeListActivity extends BaseActivity {


    TaoKeListAdapter2 taoKeListAdapter;
    List<String> mDatas;
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public int getContentViewResId() {
        return R.layout.layout_taoke_list;
    }

    List<TaoKeDetailList.DataBean> dataBeanList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        strTitle = getIntent().getStringExtra("strTitle");
        initToolbar();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        swipeTarget.setLayoutManager(gridLayoutManager);
        initAdapter();
        getNet();
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 0;
                refreshLayout.setEnableLoadMore(true);
                getNet();

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNumber++;
                getNet();
            }
        });

    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String strTitle) {
        Intent intent = new Intent(context, TaokeListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("strTitle", strTitle);
        context.startActivity(intent);

    }

    public int pageNumber = 1;
    public int pagesize = 8;
    String strTitle;

    public void getNet() {

        //访问网络获取数据 下面的列表数据

        Map<String, String> map = new HashMap<>();
        map.put("code", "04500");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("q", strTitle);
        map.put("page_size", String.valueOf(pagesize));
        map.put("page_no", String.valueOf(pageNumber));
//        NetUtils<TaoKeDetailList.DataBean> netUtils = new NetUtils<>();
//        netUtils.requestData(map, TAOKELIST, getActivity(), new JsonCallback<AppResponse<TaoKeDetailList.DataBean>>() {
//            @Override
//            public void onSuccess(Response<AppResponse<TaoKeDetailList.DataBean>> response) {
//               // Log.i("response_data", new Gson().toJson(response.body()));
//                dataBeanList.addAll(response.body().data);
//                taoKeListAdapter.setNewData(dataBeanList);
//                taoKeListAdapter.notifyDataSetChanged();
//
//
//            }
//        });

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<TaoKeDetailList.DataBean>>post(TAOKELIST)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TaoKeDetailList.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TaoKeDetailList.DataBean>> response) {
                        // Log.i("response_data", new Gson().toJson(response.body()));

                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();

                        if (pageNumber == 0) {
                            dataBeanList.clear();
                        }
                        dataBeanList.addAll(response.body().data);
                        // beautyHomeListAdapter.notifyDataSetChanged();

                        if (dataBeanList.size() == 0) {
                            refreshLayout.setEnableLoadMore(false);
                        }

                      //  taoKeListAdapter.setNewData(dataBeanList);
                        taoKeListAdapter.notifyDataSetChanged();


                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText(strTitle);
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


    private void initAdapter() {
        taoKeListAdapter = new TaoKeListAdapter2(R.layout.layout_taokeshop, dataBeanList);
        taoKeListAdapter.openLoadAnimation();//默认为渐显效果
        swipeTarget.setAdapter(taoKeListAdapter);

        taoKeListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AlibcShowParams showParams = new AlibcShowParams();
                showParams.setOpenType(OpenType.Native);
                showParams.setClientType("taobao");
                showParams.setBackUrl("alisdk://");
//                showParams.setNativeOpenFailedMode(AlibcFailModeType.AlibcNativeFailModeJumpDOWNLOAD);

                AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
                taokeParams.setPid("mm_1075130017_1502650362_110240000445");

                Map<String, String> trackParams = new HashMap<>();

//                // 以显示传入url的方式打开页面（第二个参数是套件名称）
//                AlibcTrade.openByUrl(getActivity(), "", "https://temai.m.taobao.com/", null,
//                        new WebViewClient(), new WebChromeClient(), showParams,
//                        taokeParams, trackParams, new AlibcTradeCallback() {
//                            @Override
//                            public void onTradeSuccess(AlibcTradeResult tradeResult) {
//                              //  AlibcLogger.i(TAG, "request success");
//                            }
//                            @Override
//                            public void onFailure(int code, String msg) {
//                               // AlibcLogger.e(TAG, "code=" + code + ", msg=" + msg);
//                              //  if (code == -1) {
//                               //     Toast.makeText(FeatureActivity.this, msg, Toast.LENGTH_SHORT).show();
//                               // }
//                            }
//                        });


                AlibcBasePage page = new AlibcDetailPage(taoKeListAdapter.getData().get(position).getItem_id() + "");
                AlibcTrade.openByBizCode(TaokeListActivity.this, page, null, new WebViewClient(), new WebChromeClient(),
                        "detail", showParams, taokeParams, trackParams, new AlibcTradeCallback() {
                            @Override
                            public void onTradeSuccess(AlibcTradeResult tradeResult) {
                                // 交易成功回调（其他情形不回调）
                                AlibcLogger.i("taobaoSuccess", "open detail page success");
                                Log.i("taobaoSuccess", tradeResult.toString() + "--");
                            }

                            @Override
                            public void onFailure(int code, String msg) {
                                AlibcLogger.e("MainActivity", "code=" + code + ", msg=" + msg);
                                if (code == -1) {
                                    Toast.makeText(TaokeListActivity.this, "唤端失败，失败模式为none", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

    }
}
