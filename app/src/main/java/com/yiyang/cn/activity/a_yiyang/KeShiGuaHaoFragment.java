package com.yiyang.cn.activity.a_yiyang;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yiyang.cn.R;
import com.yiyang.cn.adapter.KeShiGuaHaoAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.model.KeShiLieBiaoGuaHaoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KeShiGuaHaoFragment extends BaseFragment {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;


    KeShiGuaHaoAdapter keShiGuaHaoAdapter;
    private List<KeShiLieBiaoGuaHaoModel> keShiLieBiaoGuaHaoModels = new ArrayList<>();
    KeShiLieBiaoGuaHaoModel keShiLieBiaoGuaHaoModel = new KeShiLieBiaoGuaHaoModel();

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_keshiguahao;
    }

    @Override
    protected void initView(View rootView) {


        initAdapter();

    }

    private void initAdapter() {

        keShiLieBiaoGuaHaoModel.isHeader = "1";
        keShiLieBiaoGuaHaoModel.jine = "100";
        keShiLieBiaoGuaHaoModel.riqi = "2020-10-15";

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);

        keShiGuaHaoAdapter = new KeShiGuaHaoAdapter(R.layout.item_keshiguahao, keShiLieBiaoGuaHaoModels);
        rlvList.setAdapter(keShiGuaHaoAdapter);
        keShiGuaHaoAdapter.setNewData(keShiLieBiaoGuaHaoModels);


    }
}
