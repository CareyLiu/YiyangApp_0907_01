package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.adapter.FengshuiListAdapter;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FengshuiBangActivity extends BaziBaseActivity {

    private String mingpan_id;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_fengshui_bang;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("绑定");
    }

    public static void actionStart(Context context, String mingpan_id) {
        Intent intent = new Intent(context, FengshuiBangActivity.class);
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
    }
    private void getNet() {

    }
}
