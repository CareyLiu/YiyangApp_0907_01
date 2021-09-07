package com.yiyang.cn.activity.tuya_device.device.tongyong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.device.adapter.DingshiAdapter;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.tuya.smart.android.device.enums.TimerDeviceTypeEnum;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.constant.TimerUpdateEnum;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaDataCallback;
import com.tuya.smart.sdk.bean.Timer;
import com.tuya.smart.sdk.bean.TimerTask;

import java.util.ArrayList;
import java.util.List;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class DeviceDingshiActivity extends TuyaBaseDeviceActivity {

    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.ll_youshuju)
    NestedScrollView ll_youshuju;
    @BindView(R.id.bt_add)
    TextView bt_add;
    @BindView(R.id.ll_meishuju)
    LinearLayout ll_meishuju;
    @BindView(R.id.bt_add_two)
    TextView bt_add_two;

    private String devId;
    private String productId;
    private ArrayList<Timer> timerList = new ArrayList<>();
    private DingshiAdapter adapter;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DeviceDingshiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_dingshi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("定时");
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
        devId = TuyaDeviceManager.getDeviceManager().getDevId();
        productId = TuyaDeviceManager.getDeviceManager().getDeviceBeen().getProductId();
        initAdapter();
        initHuidiao();
        showProgressDialog();
        getDingshi();
    }

    private void getDingshi() {
        TuyaHomeSdk.getTimerInstance().getTimerList(null, devId, TimerDeviceTypeEnum.DEVICE, new ITuyaDataCallback<TimerTask>() {
            @Override
            public void onSuccess(TimerTask timerTask) {
                dismissProgressDialog();
                timerList = timerTask.getTimerList();
                if (timerList.size() > 0) {
                    bt_add_two.setVisibility(View.VISIBLE);
                    ll_youshuju.setVisibility(View.VISIBLE);
                    ll_meishuju.setVisibility(View.GONE);
                } else {
                    bt_add_two.setVisibility(View.GONE);
                    ll_youshuju.setVisibility(View.GONE);
                    ll_meishuju.setVisibility(View.VISIBLE);
                }

                adapter.setNewData(timerList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorCode, String errorMessage) {
                dismissProgressDialog();
            }
        });
    }

    private void initAdapter() {
        adapter = new DingshiAdapter(R.layout.item_tuya_dingshi, timerList, productId);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Timer timer = timerList.get(position);
                TuyaDeviceManager.getDeviceManager().setTimer(timer);
                DeviceDingshiEditActivity.actionStart(mContext);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                setDingshiqi(position);
            }
        });
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DINGSHI) {
                    getDingshi();
                }
            }
        }));
    }

    @OnClick({R.id.bt_add, R.id.bt_add_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
            case R.id.bt_add_two:
                DeviceDingshiSetActivity.actionStart(mContext);
                break;
        }
    }


    private void setDingshiqi(int position) {
        Timer timer = timerList.get(position);
        String timeId = timer.getTimerId();
        int status = timer.getStatus();

        TimerUpdateEnum timerUpdateEnum;
        if (status == 1) {
            timerUpdateEnum = TimerUpdateEnum.CLOSE;
            status = 0;
        } else {
            timerUpdateEnum = TimerUpdateEnum.OPEN;
            status = 1;
        }

        List<String> list = new ArrayList<>();
        list.add(timeId);
        int finalStatus = status;
        TuyaHomeSdk.getTimerInstance().updateTimerStatus(devId, TimerDeviceTypeEnum.DEVICE, list, timerUpdateEnum, new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                Y.t("操作失败:" + error);
            }

            @Override
            public void onSuccess() {
                timer.setStatus(finalStatus);
                timerList.set(position, timer);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
