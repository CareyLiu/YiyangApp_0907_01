package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.adapter.FengshuiAdapter;
import com.yiyang.cn.activity.wode_page.bazinew.adapter.FengshuiListAdapter;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FengshuiListActivity extends BaziBaseActivity {


    @BindView(R.id.lv_dangan)
    RecyclerView lv_dangan;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private String mingpan_id;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_fengshuibaijian_list;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("风水摆件");
    }

    public static void actionStart(Context context, String mingpan_id) {
        Intent intent = new Intent(context, FengshuiListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mingpan_id", mingpan_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mingpan_id = getIntent().getStringExtra("mingpan_id");
        t(mingpan_id);

        initAdapter();
        initSM();
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });


        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });
    }


    private void initAdapter() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings.add("");
        }
        FengshuiListAdapter adapter = new FengshuiListAdapter(R.layout.bazi_item_fengshuibaijian_list, strings);
        lv_dangan.setLayoutManager(new LinearLayoutManager(mContext));
        lv_dangan.setAdapter(adapter);
    }

    private void getNet() {

    }
}
