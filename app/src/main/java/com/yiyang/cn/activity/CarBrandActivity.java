package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.BrandExpandAdapter;
import com.yiyang.cn.adapter.ExpandableRecyclerAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.CarBrand;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.DialogManager;
import com.yiyang.cn.view.HintSideBar;
import com.yiyang.cn.view.SideBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarBrandActivity extends BaseActivity implements SideBar.OnChooseLetterChangedListener {

    @BindView(R.id.list)
    LRecyclerView mList;
    @BindView(R.id.hintSideBar)
    HintSideBar mHintSideBar;

    BrandExpandAdapter brandExpandAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    LinearLayoutManager manager;

    public static List<CarBrand.DataBean> dataList = new ArrayList<>();
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;

    private boolean isInitCache = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_brand);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);
        brandExpandAdapter = new BrandExpandAdapter(this, mList);
        brandExpandAdapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(brandExpandAdapter);

        mList.setAdapter(lRecyclerViewAdapter);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mList.setLayoutManager(manager);

        mHintSideBar.setOnChooseLetterChangedListener(this);
        mList.setPullRefreshEnabled(false);
        mList.setLoadMoreEnabled(false);

        requestData();
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onChooseLetter(String s) {
        int i = brandExpandAdapter.getFirstPositionByChar(s.charAt(0));
        if (i == -1) {
            return;
        }
        manager.scrollToPositionWithOffset(i, 0);
    }

    @Override
    public void onNoChooseLetter() {

    }

    public void requestData() {
        //首次加载数据，使用loading提示
        if (PreferenceHelper.getInstance(CarBrandActivity.this).getBoolean("isFirstLoad", true))
            DialogManager.getManager(this).showMessage("加载中，请稍候");
        Map<String, String> map = new HashMap<>();
        map.put("code", "00005");
        map.put("key", Urls.key);
        map.put("type_id", "car_brand_type");
        Gson gson = new Gson();
        OkGo.<AppResponse<CarBrand.DataBean>>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //缓存模式先使用缓存,然后使用网络数据
                .execute(new JsonCallback<AppResponse<CarBrand.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<CarBrand.DataBean>> response) {
                        if (PreferenceHelper.getInstance(CarBrandActivity.this).getBoolean("isFirstLoad", true))
                            DialogManager.getManager(CarBrandActivity.this).dismiss();
                        dataList = response.body().data;
                        brandExpandAdapter.setItems(brandExpandAdapter.getList(dataList));
                        brandExpandAdapter.notifyDataSetChanged();
                        mList.refreshComplete(10);
                        brandExpandAdapter.expandAll();
                        //设置首次加载标记
                        PreferenceHelper.getInstance(CarBrandActivity.this).putBoolean("isFirstLoad", false);
                    }

                    @Override
                    public void onCacheSuccess(Response<AppResponse<CarBrand.DataBean>> response) {
                        if (!isInitCache) {
                            //缓存回调成功直接回调onSuccess
                            onSuccess(response);
                            isInitCache = true;
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<CarBrand.DataBean>> response) {

                        AlertUtil.t(CarBrandActivity.this, response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_car_brand;
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CarBrandActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
