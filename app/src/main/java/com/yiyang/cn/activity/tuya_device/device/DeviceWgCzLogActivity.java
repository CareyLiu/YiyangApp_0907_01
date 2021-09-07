package com.yiyang.cn.activity.tuya_device.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.device.adapter.TuyaDeviceLogAdapter;
import com.yiyang.cn.activity.tuya_device.device.model.DpModel;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.ITuyaDataCallback;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceWgCzLogActivity extends TuyaBaseDeviceActivity {

    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private DeviceBean mDeviceBeen;
    private String ty_device_ccid;
    private String productId;

    private List<DpModel.DpsBean> dps = new ArrayList<>();
    private TuyaDeviceLogAdapter adapter;
    private int offset;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DeviceWgCzLogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_wangguan_chazuo_log;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("日志");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
        initAdapter();
        initSM();
        getRefresh();
    }

    private void init() {
        mDeviceBeen = TuyaDeviceManager.getDeviceManager().getDeviceBeen();
        ty_device_ccid = mDeviceBeen.getDevId();
        productId = mDeviceBeen.getProductId();
    }

    private void initAdapter() {
        adapter = new TuyaDeviceLogAdapter(R.layout.item_tuya_device_log, dps, productId);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(adapter);
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                lordMore();
            }
        });
    }

    private void getRefresh() {
        offset = 0;
        Map<String, Object> map = new HashMap<>();
        map.put("devId", ty_device_ccid);
        map.put("dpIds", "1");
        map.put("offset", offset);
        map.put("limit", 15);
        TuyaHomeSdk.getRequestInstance().requestWithApiName("tuya.m.smart.operate.all.log", "1.0", map, DpModel.class, new ITuyaDataCallback<DpModel>() {
            @Override
            public void onSuccess(DpModel dpModel) {
                dps = dpModel.getDps();
                offset = offset + dps.size();
                adapter.setNewData(dps);
                adapter.notifyDataSetChanged();
                smartRefreshLayout.finishRefresh();
            }

            @Override
            public void onError(String s, String s1) {
                smartRefreshLayout.finishRefresh();
            }
        });
    }

    private void lordMore() {
        Map<String, Object> map = new HashMap<>();
        map.put("devId", ty_device_ccid);
        map.put("dpIds", "1");
        map.put("offset", offset);
        map.put("limit", 15);
        TuyaHomeSdk.getRequestInstance().requestWithApiName("tuya.m.smart.operate.all.log", "1.0", map, DpModel.class, new ITuyaDataCallback<DpModel>() {
            @Override
            public void onSuccess(DpModel dpModel) {
                List<DpModel.DpsBean> dpsNext = dpModel.getDps();
                dps.addAll(dpsNext);
                offset = offset + dpsNext.size();
                adapter.setNewData(dps);
                adapter.notifyDataSetChanged();
                smartRefreshLayout.finishLoadMore();
            }

            @Override
            public void onError(String s, String s1) {
                smartRefreshLayout.finishLoadMore();
            }
        });
    }
}
