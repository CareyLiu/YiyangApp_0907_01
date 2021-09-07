package com.yiyang.cn.activity.taokeshagncheng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.common.utils.AlibcLogger;
import com.flyco.roundview.RoundLinearLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.Custom5HistoryAdapter;
import com.yiyang.cn.adapter.TaoKeListAdapter2;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.db.DBManager;
import com.yiyang.cn.db.HistoryRecordDao;
import com.yiyang.cn.db.table.HistoryRecord;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.TaoKeDetailList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yiyang.cn.db.DbField.DOUDARENSEARCH;
import static com.yiyang.cn.get_net.Urls.TAOKELIST;


public class Custom5SearchThingActivity extends BaseActivity {
    private static final String tag = Custom5SearchThingActivity.class.getSimpleName();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_serarchKey)
    EditText etSerarchKey;
    @BindView(R.id.rl_search)
    RoundLinearLayout rlSearch;
    @BindView(R.id.iv_cancel)
    TextView ivCancel;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    String strTitle;
    @BindView(R.id.ll_none)
    LinearLayout llNone;
    @BindView(R.id.activity_custom3_search)
    RelativeLayout activityCustom3Search;
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_zonghe)
    TextView tvZonghe;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.ll_zonghe_xiaoliang)
    LinearLayout llZongheXiaoliang;
    @BindView(R.id.tv_zhekou)
    TextView tvZhekou;
    @BindView(R.id.view_1)
    View view1;
    @BindView(R.id.tv_jiagesheng)
    TextView tvJiagesheng;
    @BindView(R.id.view_2)
    View view2;
    @BindView(R.id.tv_jiagejiang)
    TextView tvJiagejiang;
    @BindView(R.id.view_4)
    View view4;
    @BindView(R.id.tv_xiaoliagnjiang)
    TextView tvXiaoliagnjiang;
    @BindView(R.id.constrain)
    ConstraintLayout constrain;
    @BindView(R.id.constrain_xx)
    ConstraintLayout constrainXx;
    private Custom5HistoryAdapter historyAdapter;
    private TaoKeListAdapter2 taoKeListAdapter2;
    private InputMethodManager imm;
    List<HistoryRecord> searchKeywords1;
    public DBManager dbManager;
    private Context context = Custom5SearchThingActivity.this;
    private List<TaoKeDetailList.DataBean> mDatas = new ArrayList<>();
    int pagesize = 20;
    int pageNumber = 0;
    List<TaoKeDetailList.DataBean> dataBeanList = new ArrayList<>();

    /**
     * 用于其他activity跳转到该activity
     */
    public static void actionStart(Context context, String string) {
        Intent intent = new Intent(context, Custom5SearchThingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("string", string);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setBaseTitleGone();

        if (getIntent() == null) {
            return;
        }
        strTitle = getIntent().getStringExtra("string");
        etSerarchKey.setText(strTitle);
//        douDaRen5_6SearchPresenter = new DouDaRenSearchPresenterImpl(this);
        dbManager = DBManager.getInstance(this);
        dbManager.setDebug();
        searchKeywords1 = new ArrayList<>();
        context = this;
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
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
        historyAdapter();
        etSerarchKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                insertKeyword(textView.getText().toString().trim(), 1);
                strTitle = textView.getText().toString();
                pageNumber = 0;
                //getNet();
                //      BeautifulTellArticleSearchDtoIn in = new BeautifulTellArticleSearchDtoIn();
                //      App app = App.getInstance();
                //    if (app.isLogin()) {
                //      in.personId = app.getLoginUid();
                // }
                //        in.areaName = String.valueOf(App.getInstance().getProperty(AppConfig.CONF_CITY));
                // in.setPageNumber(pageNumber);
                // in.setPageSize(10);
                //if (StringUtils.isEmpty(textView.getText().toString().trim())) {
                //   UIHelper.ToastMessage(Custom5SearchThingActivity.this, "检索内容不能为空", Toast.LENGTH_SHORT);
                //} else {
                //  in.searchValue = textView.getText().toString().trim();
                // tv = textView;
                // try {
                //   douDaRen5_6SearchPresenter.getDouDaRenSearch(in);
                // } catch (AppException e) {
                //    e.printStackTrace();
                // }
                // }

                getNet();

                return false;
            }
        });

        searchKeywords1 = queryKeywordList(DOUDARENSEARCH);
        // historyAdapter.setNewData(searchKeywords1);


        constrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constrain.setVisibility(View.GONE);
            }
        });

        constrainXx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        changePage();
        tvJiagejiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Notice n = new Notice();
//                n.type = ConstanceValue.MSG_SETFZONGHE;
//                n.content = "3";
//                //  n.content = message.toString();
//                RxBus.getDefault().sendRx(n);
                constrain.setVisibility(View.GONE);
                sort = "price";
                sortBenTi = sort + shengxu;
                pageNumber = 0;
                getNet();
            }
        });
        tvJiagesheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Notice n = new Notice();
//                n.type = ConstanceValue.MSG_SETFZONGHE;
//                n.content = "2";
//                //  n.content = message.toString();
//                RxBus.getDefault().sendRx(n);
                sort = "price";
                sortBenTi = sort + jiangxu;
                pageNumber = 0;
                getNet();
                constrain.setVisibility(View.GONE);
            }
        });
        tvXiaoliagnjiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Notice n = new Notice();
//                n.type = ConstanceValue.MSG_SETFZONGHE;
//                n.content = "4";
//                //  n.content = message.toString();
//                RxBus.getDefault().sendRx(n);
                sort = "total_sales";
                sortBenTi = sort + shengxu;
                pageNumber = 0;
                getNet();
                constrain.setVisibility(View.GONE);
            }
        });
        tvZhekou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Notice n = new Notice();
//                n.type = ConstanceValue.MSG_SETFZONGHE;
//                n.content = "1";
//                //  n.content = message.toString();
//                RxBus.getDefault().sendRx(n);

                constrain.setVisibility(View.GONE);
            }
        });

    }

    /**
     * 插入
     *
     * @param keyword
     */
    public void insertKeyword(String keyword, int type) {
        if (keyword == null || keyword.isEmpty()) {
            return;
        }
        HistoryRecordDao historyRecordDao = dbManager.getDaoSession().getHistoryRecordDao();
        List<HistoryRecord> searchKeywordList = queryKeywordList(type);
        if (searchKeywordList.size() > 9) {
            historyRecordDao.queryBuilder().where(HistoryRecordDao.Properties.Date.eq(searchKeywordList.get(0).getDate())).buildDelete().executeDeleteWithoutDetachingEntities();
        }

        Iterator<HistoryRecord> iterator = searchKeywordList.iterator();
        while (iterator.hasNext()) {
            HistoryRecord next = iterator.next();
            if (keyword.equals(next.getName())) {
                iterator.remove();
                historyRecordDao.queryBuilder().where(HistoryRecordDao.Properties.Name.eq(next.getName())).buildDelete().executeDeleteWithoutDetachingEntities();
            }
        }

        HistoryRecord sk = new HistoryRecord();
        sk.setName(keyword);
        sk.setDate(System.currentTimeMillis());
        sk.setType(type);
        historyRecordDao.insertInTx(sk);

        if (historyAdapter != null) {
            searchKeywords1.clear();
            searchKeywords1 = queryKeywordList(1);
            historyAdapter.setNewData(searchKeywords1);
        }
    }

    /**
     * 查询
     */
    public List<HistoryRecord> queryKeywordList(int type) {
        HistoryRecordDao historyRecordDao = dbManager.getDaoSession().getHistoryRecordDao();
        List<HistoryRecord> searchKeywordList = historyRecordDao.queryBuilder().where(HistoryRecordDao.Properties.Type.eq(type)).orderAsc(HistoryRecordDao.Properties.Date).build().list();
        return searchKeywordList;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private void initAdapter() {
        taoKeListAdapter2 = new TaoKeListAdapter2(R.layout.layout_taokeshop, mDatas);
        taoKeListAdapter2.openLoadAnimation();//默认为渐显效果
        swipeTarget.setAdapter(taoKeListAdapter2);
        taoKeListAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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


                AlibcBasePage page = new AlibcDetailPage(taoKeListAdapter2.getData().get(position).getItem_id() + "");
                AlibcTrade.openByBizCode(Custom5SearchThingActivity.this, page, null, new WebViewClient(), new WebChromeClient(),
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
                                    Toast.makeText(Custom5SearchThingActivity.this, "唤端失败，失败模式为none", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_custom5_search_only;
    }

    @OnClick({R.id.iv_cancel, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel:
                //imm.hideSoftInputFromWindow(activityCustom3Search.getWindowToken(), 0);
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    //历史记录
    private void historyAdapter() {
        historyAdapter = new Custom5HistoryAdapter(searchKeywords1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    private String sort = "total_sales";//
    private String shengxu = "_asc";
    private String jiangxu = "_des";

    private String sortBenTi = sort + jiangxu;

    //  private String
    public void getNet() {

        // 排序_des（降序），排序_asc（升序），销量（total_sales），淘客佣金比率（tk_rate）， 累计推广量（tk_total_sales），总支出佣金（tk_total_commi），价格（price）
        //访问网络获取数据 下面的列表数据

        Map<String, String> map = new HashMap<>();
        map.put("code", "04500");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("q", strTitle);
        map.put("sort", sortBenTi);
        map.put("page_size", String.valueOf(pagesize));
        map.put("page_no", String.valueOf(pageNumber));

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

                        if (pageNumber == 0) {
                            taoKeListAdapter2.setNewData(dataBeanList);
                        }

                        //  taoKeListAdapter.setNewData(dataBeanList);
                        taoKeListAdapter2.notifyDataSetChanged();
                        hideKeyboard(activityCustom3Search);
                    }
                });
    }

    String str = "0";
    String tanchuCon = "0";//0否 1 是

    public void changePage() {

        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (str.equals("0")) {
                    //弹出con
                    if (tanchuCon.equals("0")) {
                        constrain.setVisibility(View.VISIBLE);
                        constrain.getBackground().setAlpha(200);// 0~255透明度值
                        tanchuCon = "1";
                    } else if (tanchuCon.equals("1")) {
                        constrain.setVisibility(View.GONE);
                        tanchuCon = "0";
                    }
                    return;
                } else {
                    constrain.setVisibility(View.VISIBLE);
                    constrain.getBackground().setAlpha(200);// 0~255透明度值
                }
                tvZonghe.setTextColor(getResources().getColor(R.color.red_61832));
                tvXiaoliang.setTextColor(getResources().getColor(R.color.black_111111));


                str = "0";
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.equals("1")) {
                    return;
                }
                constrain.setVisibility(View.GONE);
                tanchuCon = "0";
                tvZonghe.setTextColor(getResources().getColor(R.color.black_111111));
                tvXiaoliang.setTextColor(getResources().getColor(R.color.red_61832));
                sort = "total_sales";
                sortBenTi = sort + jiangxu;
                pageNumber = 0;
                getNet();
                str = "1";
            }
        });

    }

}
