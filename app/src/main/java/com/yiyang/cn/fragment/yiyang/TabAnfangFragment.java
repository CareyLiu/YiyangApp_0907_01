package com.yiyang.cn.fragment.yiyang;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.yiyang.AnfangAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.model.yiyang.AnfangModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabAnfangFragment extends BaseFragment {
    @BindView(R.id.tv_select_name)
    TextView tv_select_name;
    @BindView(R.id.tv_title_baojing)
    TextView tv_title_baojing;
    @BindView(R.id.tv_title_rizhi)
    TextView tv_title_rizhi;
    @BindView(R.id.rv_baojing)
    RecyclerView rv_baojing;
    @BindView(R.id.ll_group_baojing)
    LinearLayout ll_group_baojing;
    @BindView(R.id.ll_group_rizhi)
    LinearLayout ll_group_rizhi;
    private List<AnfangModel> anfangModels;
    private AnfangAdapter anfangAdapter;

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .statusBarColor(R.color.white)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.yiyang_frag_tab_anfang;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        selectBaojing();
        initAdapter();
    }

    private void initAdapter() {
        anfangModels = new ArrayList<>();
        anfangAdapter = new AnfangAdapter(R.layout.yiyang_item_anfang, anfangModels);
        rv_baojing.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_baojing.setAdapter(anfangAdapter);


        anfangAdapter.setNewData(anfangModels);
        anfangAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.tv_title_baojing, R.id.tv_title_rizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title_baojing:
                selectBaojing();
                break;
            case R.id.tv_title_rizhi:
                selectRizhi();
                break;
        }
    }


    private void selectBaojing() {
        tv_title_baojing.setBackgroundResource(R.drawable.yiyang_title_select);
        tv_title_rizhi.setBackground(null);

        tv_title_baojing.setTextColor(Color.WHITE);
        tv_title_rizhi.setTextColor(Color.parseColor("#C1C1C1"));

        ll_group_baojing.setVisibility(View.VISIBLE);
        ll_group_rizhi.setVisibility(View.GONE);
    }


    private void selectRizhi() {
        tv_title_rizhi.setBackgroundResource(R.drawable.yiyang_title_select);
        tv_title_baojing.setBackground(null);

        tv_title_rizhi.setTextColor(Color.WHITE);
        tv_title_baojing.setTextColor(Color.parseColor("#C1C1C1"));

        ll_group_rizhi.setVisibility(View.VISIBLE);
        ll_group_baojing.setVisibility(View.GONE);
    }
}
