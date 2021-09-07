package com.yiyang.cn.activity.wode_page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.yiyang.cn.adapter.ShangPinShouCangListAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ShangJiaListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;

public class ShangPinShouCangActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    List<ShangJiaListModel.DataBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page_number = 0;
                getNet();
            }
        });


        srLSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page_number++;
                getNet();
            }
        });


        setListAndAdapter();

        getNet();
    }

    ShangPinShouCangListAdapter shangPinShouCangListAdapter;

    private void setListAndAdapter() {
        shangPinShouCangListAdapter = new ShangPinShouCangListAdapter(R.layout.shangpin_shoucang_item, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShangPinShouCangActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(shangPinShouCangListAdapter);

        shangPinShouCangListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        if (collection_type.equals("1")) {
                            ZiJianShopMallDetailsActivity.actionStart(ShangPinShouCangActivity.this, shangPinShouCangListAdapter.getData().get(position).getShop_product_id(), shangPinShouCangListAdapter.getData().get(position).getWares_id());
                        } else if (collection_type.equals("2")) {

                        }
                        break;
                }
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shang_pin_shou_cang;
    }

    private String collection_type = "1";
    private int page_number = 0;
    private String collection_id = "";

    public void getNet() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "04125");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("collection_type", collection_type);
        map.put("page_number", String.valueOf(page_number));
        map.put("collection_id", collection_id);

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ShangJiaListModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ShangJiaListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ShangJiaListModel.DataBean>> response) {
                        srLSmart.finishLoadMore();
                        srLSmart.finishRefresh();

                        if (page_number == 0) {
                            mDatas.clear();
                            shangPinShouCangListAdapter.setNewData(response.body().data);
                        } else {

                            mDatas.addAll(response.body().data);
                        }
                        shangPinShouCangListAdapter.notifyDataSetChanged();

                        srLSmart.setEnableLoadMore(false);

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
        tv_title.setText("商品收藏");
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

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShangPinShouCangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
