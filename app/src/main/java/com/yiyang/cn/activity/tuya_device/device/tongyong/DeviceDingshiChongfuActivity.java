package com.yiyang.cn.activity.tuya_device.device.tongyong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.device.adapter.DingshiChongfuAdapter;
import com.yiyang.cn.activity.tuya_device.device.model.DingshiModel;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceDingshiChongfuActivity extends TuyaBaseDeviceActivity {

    @BindView(R.id.rv_chongfu)
    RecyclerView rv_chongfu;
    private String loops;
    private String week_0;
    private String week_1;
    private String week_2;
    private String week_3;
    private String week_4;
    private String week_5;
    private String week_6;
    private List<DingshiModel> loopsWeeks;
    private DingshiChongfuAdapter adapter;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String loops) {
        Intent intent = new Intent(context, DeviceDingshiChongfuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("loops", loops);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_dingshi_chongfu;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("重复");
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
    }

    private void init() {
        loops = getIntent().getStringExtra("loops");

        week_0 = loops.substring(0, 1);
        week_1 = loops.substring(1, 2);
        week_2 = loops.substring(2, 3);
        week_3 = loops.substring(3, 4);
        week_4 = loops.substring(4, 5);
        week_5 = loops.substring(5, 6);
        week_6 = loops.substring(6);

        loopsWeeks = new ArrayList<>();
        loopsWeeks.add(new DingshiModel("周日", week_0));
        loopsWeeks.add(new DingshiModel("周一", week_1));
        loopsWeeks.add(new DingshiModel("周二", week_2));
        loopsWeeks.add(new DingshiModel("周三", week_3));
        loopsWeeks.add(new DingshiModel("周四", week_4));
        loopsWeeks.add(new DingshiModel("周五", week_5));
        loopsWeeks.add(new DingshiModel("周六", week_6));

        adapter = new DingshiChongfuAdapter(R.layout.item_tuya_dingshi_chongfu, loopsWeeks);
        rv_chongfu.setLayoutManager(new LinearLayoutManager(mContext));
        rv_chongfu.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DingshiModel dingshiModel = loopsWeeks.get(position);
                String select = dingshiModel.getSelect();
                if (select.equals("1")) {
                    select = "0";
                } else {
                    select = "1";
                }
                dingshiModel.setSelect(select);
                loopsWeeks.set(position, dingshiModel);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loops = "";
        for (int i = 0; i < loopsWeeks.size(); i++) {
            String select = loopsWeeks.get(i).getSelect();
            loops = loops + select;
        }

        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_DEVICE_DINGSHI_CHONGFU;
        notice.content = loops;
        RxBus.getDefault().sendRx(notice);
    }
}
