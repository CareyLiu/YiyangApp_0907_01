package com.yiyang.cn.fragment.taoke_shangcheng;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.taokeshagncheng.TaokeListActivity;
import com.yiyang.cn.adapter.TaoKeHomeSecondViewAdapter;
import com.yiyang.cn.adapter.TaoKeListAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.TaoKeDetailList;
import com.yiyang.cn.model.TaoKeTitleListModel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

import static com.yiyang.cn.get_net.Urls.TAOKELIST;


public class TaoKeMallListFragment extends BaseFragment {
    List<TaoKeTitleListModel.DataBean.ChildBean> childBeanList = new ArrayList<>();
    private RecyclerView recyclerView;
    //   List<MessageModel.DataBean> mDatas = new ArrayList<>();
    private TaoKeListAdapter taoKeListAdapter;

    List<TaoKeDetailList.DataBean> dataBeanList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        strTitle = args.getString("title");
        beanList.clear();
        childBeanList = (List<TaoKeTitleListModel.DataBean.ChildBean>) args.getSerializable("beanList");

    }


//    public void getNet() {
//        Map<String, String> map = new HashMap<>();
//        map.put("code", "03003");
//        map.put("key", Urls.key);
//        map.put("token", PreferenceHelper.getInstance(getActivity()).getString("app_token", "0"));
//        if (strTitle.equals("全部")) {
//
//        } else if (strTitle.equals("未读")) {
//            map.put("notify_read", "1");
//        } else if (strTitle.equals("已读")) {
//            map.put("notify_read", "2");
//
//        } else if (strTitle.equals("已处理")) {
//            map.put("notify_state", "2");
//        } else if (strTitle.equals("未处理")) {
//            map.put("notify_state", "2");
//        }
//        NetUtils<MessageModel.DataBean> netUtils = new NetUtils<>();
//        netUtils.requestData(map, Urls.MESSAGE_URL, getActivity(), new JsonCallback<AppResponse<MessageModel.DataBean>>() {
//            @Override
//            public void onSuccess(Response<AppResponse<MessageModel.DataBean>> response) {
//                List<MessageModel.DataBean> dataBean = new ArrayList<>();
//                dataBean.addAll(response.body().data);
//                if (dataBean.size() > 0) {
//                    for (int i = 0; i < dataBean.size(); i++) {
//                        Log.i("dataBean", dataBean.get(i).getNotify_text());
//
//                    }
//                    messageListAdapter.setNewData(dataBean);
//                    messageListAdapter.notifyDataSetChanged();
//
//
//                } else {
//                    recyclerView.setVisibility(View.GONE);
//                    ivNone.setVisibility(View.VISIBLE);
//                }
//
//
//            }
//
//            @Override
//            public void onError(Response<AppResponse<MessageModel.DataBean>> response) {
//                super.onError(response);
//            }
//        });
//    }

    String strTitle;
    ImageView ivNone;

    @Override
    protected void initLogic() {

        //refreshRequest();


    }

    RelativeLayout rlMain;
    View view;

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_taoke_second_list;
    }

    View headerView;//男装下面的分类view，包括（长袖t桖 polo衫）
    RecyclerView secondListView;//下面二级分类view
    SmartRefreshLayout smartRefreshLayout;
    TaoKeHomeSecondViewAdapter taoKeHomeSecondViewAdapter;
    List<String> stringList = new ArrayList<>();
    public int pageNumber = 1;
    public int pagesize = 20;
    List<TaoKeTitleListModel.DataBean> beanList = new ArrayList<>();//女装二级类目

    @Override
    protected void initView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.swipe_target);
        //   rlMain = rootView.findViewById(R.id.rl_main);
        //   ivNone = rootView.findViewById(R.id.iv_none);
        headerView = View.inflate(getActivity(), R.layout.layout_taoke_header, null);
        secondListView = headerView.findViewById(R.id.list);
        smartRefreshLayout = rootView.findViewById(R.id.refreshLayout);
        smartRefreshLayout.setEnableRefresh(true);
        GridLayoutManager gridLayoutManagerChi_He = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        gridLayoutManagerChi_He.setOrientation(LinearLayoutManager.VERTICAL);
        //   revisionRecycler.setLayoutManager(gridLayoutManager);

        secondListView.setLayoutManager(gridLayoutManagerChi_He);
//        stringList.clear();
//        stringList.add("1");
//        stringList.add("1");
//        stringList.add("1");
//        stringList.add("1");
//        stringList.add("1");
//
//        stringList.add("1");
//        stringList.add("1");
//        stringList.add("1");
//        stringList.add("1");
//        stringList.add("1");

//        beanList.clear();
//        Bundle bundle = new Bundle();
//        beanList = (List<TaoKeTitleListModel.DataBean>) bundle.getSerializable("beanList");

        taoKeHomeSecondViewAdapter = new TaoKeHomeSecondViewAdapter(R.layout.item_taoke_home_second, childBeanList);
        taoKeHomeSecondViewAdapter.openLoadAnimation();//默认为渐显效果
        secondListView.setAdapter(taoKeHomeSecondViewAdapter);
        taoKeHomeSecondViewAdapter.notifyDataSetChanged();
        view = rootView.findViewById(R.id.view);
        // mDatas = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManagerChi_He.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        initAdapter();
        //  initAdapter();


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 1;
                refreshLayout.setEnableLoadMore(true);
                refreshRequest();

            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNumber++;
                refreshRequest();
            }
        });

        taoKeHomeSecondViewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        TaokeListActivity.actionStart(getActivity(), childBeanList.get(position).getItem_name());
                        break;
                }
            }
        });
        //refreshRequest();
        // smartRefreshLayout.autoRefresh();
        refreshRequest();
    }


    public void getNet() {

        //访问网络获取数据 下面的列表数据

        Map<String, String> map = new HashMap<>();
        map.put("code", "04500");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
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
                .tag(getActivity())//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TaoKeDetailList.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TaoKeDetailList.DataBean>> response) {
                        // Log.i("map_data", new Gson().toJson(response.body().data));
                        smartRefreshLayout.finishLoadMore();
                        //   Log.i("body", "请求数据code" + response.body().msg_code);
                        if (response.body().msg_code.equals("0021")) {
                            getNet();
                        } else {
                            smartRefreshLayout.finishRefresh();
                            if (pageNumber == 1) {
                                dataBeanList.clear();
                                dataBeanList.addAll(response.body().data);
                                taoKeListAdapter.setNewData(dataBeanList);
                            } else {
                                dataBeanList.addAll(response.body().data);
                                //taoKeListAdapter.setNewData(dataBeanList);
                            }

                            // beautyHomeListAdapter.notifyDataSetChanged();

                            taoKeListAdapter.notifyDataSetChanged();

                            // smartRefreshLayout.finishRefresh();

                            //  smartRefreshLayout.setEnableLoadMore(true);

                            if (response.body().next.equals("0")) {
                                smartRefreshLayout.setEnableLoadMore(false);
                            }
                        }


                    }

                    @Override
                    public void onError(Response<AppResponse<TaoKeDetailList.DataBean>> response) {
                        super.onError(response);
                    }
                });
    }

    public void getNet1() {

        //访问网络获取数据 下面的列表数据

        Map<String, String> map = new HashMap<>();
        map.put("code", "04500");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("q", strTitle);
        map.put("page_size", String.valueOf(pagesize));
        map.put("page_no", String.valueOf(pageNumber+1));


        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<TaoKeDetailList.DataBean>>post(TAOKELIST)
                .tag(getActivity())//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TaoKeDetailList.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TaoKeDetailList.DataBean>> response) {

                    }

                    @Override
                    public void onError(Response<AppResponse<TaoKeDetailList.DataBean>> response) {
                        super.onError(response);
                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    //    private void initAdapter() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        messageListAdapter = new MessageListAdapter(R.layout.item_messagelist, mDatas);
//        messageListAdapter.openLoadAnimation();//默认为渐显效果
//        recyclerView.setAdapter(messageListAdapter);
//    }
    // List<TaoKeDetailList.DataBean> mDatas;

    private void initAdapter() {
//        for (int i = 0; i < 50; i++) {
//            TaoKeDetailList.DataBean dataBean = new TaoKeDetailList.DataBean();
//            mDatas.add(dataBean);
//        }

        taoKeListAdapter = new TaoKeListAdapter(R.layout.layout_taokeshop, dataBeanList);
        taoKeListAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(taoKeListAdapter);
        smartRefreshLayout.setEnableAutoLoadMore(true);
        //  smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        taoKeListAdapter.addHeaderView(headerView);
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



//
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
                Map<String, String> trackParams = new HashMap<>();

                AlibcBasePage page = new AlibcDetailPage(taoKeListAdapter.getData().get(position).getItem_id() + "");
                AlibcTrade.openByBizCode(getActivity(), page, null, new WebViewClient(), new WebChromeClient(),
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
                                    Toast.makeText(getActivity(), "唤端失败，失败模式为none", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });

    }

    public void refreshRequest() {
        getNet();
        //   smartRefreshLayout.autoRefresh();
    }

}

