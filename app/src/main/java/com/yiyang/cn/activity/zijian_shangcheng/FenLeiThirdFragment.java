package com.yiyang.cn.activity.zijian_shangcheng;

import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.FenLeiRightAdapter;
import com.yiyang.cn.adapter.FenLeiThirdAdapter;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.FenLeiContentModel;
import com.yiyang.cn.model.FenLeiThirdModel;
import com.yiyang.cn.model.ZiJianFenLeiBean;
import com.yiyang.cn.project_A.zijian_interface.FenLeiContenInterface;
import com.yiyang.cn.util.GridSectionAverageGapItemDecoration;
import com.yiyang.cn.util.ShuangLieDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZIYINGFENLEI;

public class FenLeiThirdFragment extends BaseFragment implements FenLeiContenInterface {

    List<ZiJianFenLeiBean.DataBean.ItemBeanX> itemBeanXES = new ArrayList<>();

    RecyclerView rlvList;

    FenLeiThirdAdapter fenLeiThirdAdapter;

    List<FenLeiThirdModel.DataBean> mDatas = new ArrayList<>();

    private String order_type;
    // boolean flag = true;// true 是第一个 false 不是第一个
    String next;

    private String strTitle;
    private String shouYeId;//

    @Override
    public void getNet() {
        //无需访问网络

        Bundle bundle = getArguments();
        String strPage = bundle.getString("page");
        String item_id = bundle.getString("item_id");
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "04132");
        map.put("key", Urls.key);
        //map.put("item_id_one", item_id);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        //map.put("", "");
        map.put("item_id_one", bundle.getString("one_item"));
        map.put("item_id_two", bundle.getString("two_item"));
        map.put("item_id_three", item_id);

        if (shouYeId == null) {
            map.put("wares_type", "1");
        } else {
            map.put("wares_type", shouYeId);
        }

        map.put("page_number", String.valueOf(pageNumber));

        // if (strPage.equals("1")) {

        map.put("order_type", order_type);

        //} else if (strPage.equals("2")) {
        //  map.put("order_type", order_type);

        //}


        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<FenLeiThirdModel.DataBean>>post(ZIYINGFENLEI)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<FenLeiThirdModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<FenLeiThirdModel.DataBean>> response) {
                        if (pageNumber == 0) {
                            mDatas.clear();
                            mDatas.addAll(response.body().data);
                        } else {
                            mDatas.addAll(response.body().data);
                        }
                        fenLeiThirdAdapter.notifyDataSetChanged();
                        next = response.body().next;
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                        if (next.equals("1")) {
                            smartRefreshLayout.setEnableLoadMore(true);
                        } else {
                            smartRefreshLayout.setEnableLoadMore(false);
                        }
                    }
                });
    }

    @Override
    public void loadList() {
        fenLeiThirdAdapter = new FenLeiThirdAdapter(R.layout.item_ziying_fenlei, mDatas);
        //  rlvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rlvList.addItemDecoration(new ShuangLieDecoration(getActivity()));
        rlvList.setAdapter(fenLeiThirdAdapter);
        fenLeiThirdAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        FenLeiThirdModel.DataBean dataBean = (FenLeiThirdModel.DataBean) adapter.getData().get(position);
                        ZiJianShopMallDetailsActivity.actionStart(getActivity(), dataBean.getShop_product_id(), dataBean.getWares_id());
                        break;
                }
            }
        });
    }

    @Override
    public void getCanshu() {

    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_fenlei_third_fragment;
    }

    String str = "1";
    SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void initView(View rootView) {
        rlvList = rootView.findViewById(R.id.rlv_list);
        loadList();
        smartRefreshLayout = rootView.findViewById(R.id.srL_smart);
        Bundle bundle = getArguments();
        String strPage = bundle.getString("page");
        String item_id = bundle.getString("item_id");

        strTitle = bundle.getString("strTitle");
        shouYeId = bundle.getString("shouYeId");
        if (strPage.equals("1")) {
            order_type = "1";
        } else if (strPage.equals("2")) {
            order_type = "4";
        }
        getNet();

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_SETFZONGHE) {
                    order_type = (String) message.content;

                    getNet();
                }
            }
        }));
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 0;
                getNet();
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (next==null){
                    return;
                }
                if (next.equals("1")) {
                    pageNumber = pageNumber + 1;
                    getNet();
                }
            }
        });

    }

    int pageNumber;
}
