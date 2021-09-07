package com.yiyang.cn.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jaeger.library.StatusBarUtil;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.CarBrandAdapter;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.model.CarBrand;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarBrandDetailsActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.list)
    LRecyclerView mList;

    List<CarBrand.DataBean.CcBean.ClBean> dataList;

    CarBrandAdapter carBrandAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_brand_details);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);
        carBrandAdapter = new CarBrandAdapter(this);
        for (int i = 0;i<CarBrandActivity.dataList.size();i++){
            for (int j = 0;j<CarBrandActivity.dataList.get(i).getCc().size();j++){
                if (CarBrandActivity.dataList.get(i).getCc().get(j).getBrand_id().equals(PreferenceHelper.getInstance(this).getString("brand_id",""))){
                    dataList = CarBrandActivity.dataList.get(i).getCc().get(j).getCl();
                    carBrandAdapter.setDataList(dataList);
                }
            }
        }

        lRecyclerViewAdapter = new LRecyclerViewAdapter(carBrandAdapter);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(lRecyclerViewAdapter);
        mList.refreshComplete(10);
        mList.setPullRefreshEnabled(false);
        mList.setLoadMoreEnabled(false);
        lRecyclerViewAdapter.notifyDataSetChanged();

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PreferenceHelper.getInstance(CarBrandDetailsActivity.this).putString("mode_id",dataList.get(position).getBrand_id());
                PreferenceHelper.getInstance(CarBrandDetailsActivity.this).putString("mode_name",dataList.get(position).getBrand_name());
                PreferenceHelper.getInstance(CarBrandDetailsActivity.this).putString("mode_pic",dataList.get(position).getPic_url());
                startActivity(new Intent(CarBrandDetailsActivity.this,BindBoxActivity.class));
            }
        });
    }


    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
}
