package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.AccessListAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.AccessListModel;
import com.yiyang.cn.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccessListActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    private String page_num = "0";
    private String waresId;
    private AccessListAdapter accessListAdapter;
    private List<AccessListModel.DataBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        waresId = getIntent().getStringExtra("waresId");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AccessListActivity.this);
        rlvList.setLayoutManager(linearLayoutManager);
        accessListAdapter = new AccessListAdapter(R.layout.item_zijian_pinglun, mDatas);
        rlvList.setAdapter(accessListAdapter);
        getData();

        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page_num = "0";
                getData();
            }
        });
        srLSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int pageNumber = Integer.valueOf(page_num) + 1;
                page_num = String.valueOf(pageNumber);
                getData();
            }
        });

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_access_list;
    }

    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04134");
        map.put("key", Urls.key);
        map.put("wares_id", waresId);
        map.put("page_num", page_num);
        Gson gson = new Gson();
        OkGo.<AppResponse<AccessListModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<AccessListModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<AccessListModel.DataBean>> response) {

                        if (response.body().data.size() > 0) {

                            if (page_num.equals("0")) {
                                mDatas = new ArrayList<>();
                                mDatas.addAll(response.body().data);
                                accessListAdapter.setNewData(mDatas);
                            } else {
                                mDatas.addAll(response.body().data);
                                accessListAdapter.notifyDataSetChanged();
                            }
                        }

                        if (response.body().next.equals("0")) {
                            srLSmart.setEnableLoadMore(false);
                        } else {
                            srLSmart.setEnableLoadMore(true);
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<AccessListModel.DataBean>> response) {
                        AlertUtil.t(AccessListActivity.this, response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        srLSmart.finishLoadMore();
                        srLSmart.finishRefresh();
                    }
                });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String waresId) {
        Intent intent = new Intent(context, AccessListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("waresId", waresId);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("全部评论");
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
}
