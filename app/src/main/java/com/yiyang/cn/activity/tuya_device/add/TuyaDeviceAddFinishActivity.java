package com.yiyang.cn.activity.tuya_device.add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.add.adapter.TuyaDeviceFinishAdapter;
import com.yiyang.cn.activity.tuya_device.add.model.TuyaAddDeviceModel;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TuyaDeviceAddFinishActivity extends BaseActivity {

    @BindView(R.id.lv_conten)
    ListView lv_conten;

    private List<TuyaAddDeviceModel> deviceModels = new ArrayList<>();
    private TuyaDeviceFinishAdapter adapter;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, List<TuyaAddDeviceModel> deviceModels) {
        Intent intent = new Intent(context, TuyaDeviceAddFinishActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("deviceModels", (Serializable) deviceModels);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_add_finish;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("添加成功");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("完成");
        tv_rightTitle.setTextSize(17);
        tv_rightTitle.setTextColor(Y.getColor(R.color.color_main));
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickXiayibu();
            }
        });
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void clickXiayibu() {
        clickFinish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        deviceModels = (List<TuyaAddDeviceModel>) getIntent().getSerializableExtra("deviceModels");
        adapter = new TuyaDeviceFinishAdapter(deviceModels, mContext);
        lv_conten.setAdapter(adapter);
        lv_conten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        clickFinish();
    }

    private void clickFinish() {
        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_DEVICE_ADD;
        RxBus.getDefault().sendRx(notice);
        finish();
    }
}
