package com.yiyang.cn.activity.tongcheng58;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.GongJiangListAdapter;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GongJiangLieBiaoFragment extends BaseFragment {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    String strTitle;

    private GongJiangListAdapter gongJiangListAdapter;

    List<String> strings = new ArrayList<>();

    @Override
    protected void initLogic() {
        Bundle args = getArguments();
        strTitle = args.getString("title");

        if (strTitle.equals("家居拆装")) {

        } else {
            for (int i = 0; i < 10; i++) {
                strings.add("1");
            }
        }

        gongJiangListAdapter = new GongJiangListAdapter(R.layout.item_gongjiang, strings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(gongJiangListAdapter);

        if (strings.size() == 0) {
            View view = View.inflate(getActivity(), R.layout.empty_view, null);
            ImageView noneImage = view.findViewById(R.id.iv_image);
            noneImage.setBackgroundResource(0);
            noneImage.setBackgroundResource(R.mipmap.shop_pic_none);
            gongJiangListAdapter.setEmptyView(view);
        }

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                UIHelper.ToastMessage(getActivity(), "加载更多");
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                UIHelper.ToastMessage(getActivity(), "刷新");
            }
        });

        gongJiangListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rl_item:
                        //  UIHelper.ToastMessage(getActivity(), "点击");
                        //GongJiangXinXiActivity.actionStart(getActivity());
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_gongjiang_page;
    }

    @Override
    protected void initView(View rootView) {

    }
}
