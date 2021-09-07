package com.yiyang.cn.activity.tuya_device.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.device.tongyong.DeviceDingshiActivity;
import com.yiyang.cn.activity.tuya_device.utils.TuyaDialogUtils;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.get_net.Urls;
import com.tuya.smart.sdk.api.IDevListener;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class DeviceSwitchThreeActivity extends TuyaBaseDeviceActivity {

    @BindView(R.id.rl_switch_left)
    RelativeLayout rl_switch_left;
    @BindView(R.id.rl_switch_center)
    RelativeLayout rl_switch_center;
    @BindView(R.id.rl_switch_right)
    RelativeLayout rl_switch_right;
    @BindView(R.id.tv_beiguang_zhuangtai)
    TextView tv_beiguang_zhuangtai;
    @BindView(R.id.ll_quankai)
    LinearLayout ll_quankai;
    @BindView(R.id.ll_quanguan)
    LinearLayout ll_quanguan;
    @BindView(R.id.ll_dingshi)
    LinearLayout ll_dingshi;
    @BindView(R.id.view_switch_left)
    View view_switch_left;
    @BindView(R.id.view_switch_center)
    View view_switch_center;
    @BindView(R.id.view_switch_right)
    View view_switch_right;
    @BindView(R.id.tv_kaiguan_mode)
    TextView tv_kaiguan_mode;
    private String ty_device_ccid;
    private String old_name;
    private DeviceBean mDeviceBeen;
    private ITuyaDevice mDevice;
    private String productId;
    private boolean isOnline;
    private boolean switchLeft;
    private boolean switchCenter;
    private boolean switchRight;
    private boolean switch_backlight;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String member_type, String device_id, String ty_device_ccid, String old_name, String room_name) {
        Intent intent = new Intent(context, DeviceSwitchThreeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("member_type", member_type);
        intent.putExtra("device_id", device_id);
        intent.putExtra("ty_device_ccid", ty_device_ccid);
        intent.putExtra("old_name", old_name);
        intent.putExtra("room_name", room_name);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_switch_three;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setImageResource(R.mipmap.mine_shezhi);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
        initHuidiao();
//        initAdapter();
//        initSM();
//        getRefresh();
    }

    private void init() {
        ty_device_ccid = getIntent().getStringExtra("ty_device_ccid");
        old_name = getIntent().getStringExtra("old_name");
        tv_title.setText(old_name);

        DeviceBean haveDevice = TuyaHomeManager.getHomeManager().isHaveDevice(ty_device_ccid);
        if (haveDevice != null) {
            TuyaDeviceManager.getDeviceManager().initDevice(haveDevice);
            initSwich();
        } else {
            TuyaDialogUtils.t(mContext, "设备已失效!");
        }
    }

    private void initSwich() {
        mDeviceBeen = TuyaDeviceManager.getDeviceManager().getDeviceBeen();
        mDevice = TuyaDeviceManager.getDeviceManager().getDevice();
        productId = mDeviceBeen.getProductId();
        isOnline = mDeviceBeen.getIsOnline();
        setIsOnline();
        initDeviceListener();

        Map<String, Object> dps = mDeviceBeen.getDps();
        switchRight = (boolean) dps.get("1");
        switchCenter = (boolean) dps.get("2");
        switchLeft = (boolean) dps.get("3");
        setSwichState();
        switch_backlight = (boolean) dps.get("16");
        setSwitchBacklight();
    }

    private void setIsOnline() {
        if (isOnline) {
            tv_kaiguan_mode.setText("正常");
        } else {
            tv_kaiguan_mode.setText("离线");
        }
    }

    private void setSwitchBacklight() {
        if (switch_backlight) {
            tv_beiguang_zhuangtai.setText("已开启");
        } else {
            tv_beiguang_zhuangtai.setText("已关闭");
        }
    }

    private void initDeviceListener() {//初始化监听
        mDevice.registerDevListener(new IDevListener() {
            @Override
            public void onDpUpdate(String devId, String dpStr) {
                Y.e("Dp点数据更新啦:" + devId + " | " + dpStr);
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_DEVICE_DATA;
                notice.content = dpStr;
                notice.devId = devId;
                RxBus.getDefault().sendRx(notice);
            }

            @Override
            public void onRemoved(String devId) {
                Y.e("设备被移除了" + devId);
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_DEVICE_REMOVED;
                notice.devId = devId;
                RxBus.getDefault().sendRx(notice);
            }

            @Override
            public void onStatusChanged(String devId, boolean online) {
                Y.e("设备上下线回调  " + devId + "   " + online);
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_DEVICE_STATUSCHANGED;
                notice.devId = devId;
                notice.content = online;
                RxBus.getDefault().sendRx(notice);
            }

            @Override
            public void onNetworkStatusChanged(String devId, boolean status) {
                Y.e("网络状态发生变动时的回调  " + devId + "    " + status);
            }

            @Override
            public void onDevInfoUpdate(String devId) {
                Y.e("设备信息更新回调  " + devId);
            }
        });
    }

    private void setSwichState() {
        Y.e("开关的状态  " + switchLeft + "  " + switchCenter + "   " + switchRight);
        if (switchLeft) {
            rl_switch_left.setBackgroundResource(R.drawable.bg_tuya_switch_left_s);
            view_switch_left.setBackgroundResource(R.drawable.bg_tuya_switch_s);
        } else {
            rl_switch_left.setBackgroundResource(R.drawable.bg_tuya_switch_left);
            view_switch_left.setBackgroundResource(R.drawable.bg_tuya_switch_n);
        }

        if (switchCenter) {
            rl_switch_center.setBackgroundResource(R.drawable.bg_tuya_switch_center_s);
            view_switch_center.setBackgroundResource(R.drawable.bg_tuya_switch_s);
        } else {
            rl_switch_center.setBackgroundResource(R.drawable.bg_tuya_switch_center);
            view_switch_center.setBackgroundResource(R.drawable.bg_tuya_switch_n);
        }

        if (switchRight) {
            rl_switch_right.setBackgroundResource(R.drawable.bg_tuya_switch_right_s);
            view_switch_right.setBackgroundResource(R.drawable.bg_tuya_switch_s);
        } else {
            rl_switch_right.setBackgroundResource(R.drawable.bg_tuya_switch_right);
            view_switch_right.setBackgroundResource(R.drawable.bg_tuya_switch_n);
        }
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(ty_device_ccid)) {
                        getData(message.content.toString());
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_STATUSCHANGED) {
                    if (message.devId.equals(ty_device_ccid)) {
                        isOnline = (boolean) message.content;
                        setIsOnline();
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_REMOVED) {
                    if (message.devId.equals(ty_device_ccid)) {
                        TuyaDeviceManager.getDeviceManager().device_removed(DeviceSwitchThreeActivity.this);
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_DELETE) {
                    String device_id = getIntent().getStringExtra("device_id");
                    if (device_id.equals(message.devId)) {
                        finish();
                    }
                }
            }
        }));
    }

    public void getData(String dpStr) {
        JSONObject jsonObject = JSON.parseObject(dpStr);
        Set<String> strings = jsonObject.keySet();
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            // 获得key
            String key = it.next();
            Object value = jsonObject.get(key);
            jieData(key, value);
        }
    }

    private void jieData(String key, Object value) {
        setDps(key, value);
        if (key.equals("3")) {//switch开关  left
            switchLeft = (boolean) value;
            setSwichState();
        } else if (key.equals("2")) {//switch开关  center
            switchCenter = (boolean) value;
            setSwichState();
        } else if (key.equals("1")) {//switch开关  right
            switchRight = (boolean) value;
            setSwichState();
        } else if (key.equals("16")) {
            switch_backlight = (boolean) value;
            setSwitchBacklight();
        }
    }

    private void setDps(String key, Object value) {
        Y.e("解析出的数据:  " + "key = " + key + "  |  value = " + value);
        Map<String, Object> dps = mDeviceBeen.getDps();
        dps.put(key, value);
        mDeviceBeen.setDps(dps);
    }

    @OnClick({R.id.rl_switch_left, R.id.rl_switch_center, R.id.rl_switch_right, R.id.ll_quankai, R.id.ll_quanguan, R.id.ll_dingshi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_switch_left:
                clickSwitchLeft();
                break;
            case R.id.rl_switch_center:
                clickSwitchCenter();
                break;
            case R.id.rl_switch_right:
                clickSwitchRight();
                break;
            case R.id.ll_quankai:
                clickQuankai();
                break;
            case R.id.ll_quanguan:
                clickQuanguan();
                break;
            case R.id.ll_dingshi:
                DeviceDingshiActivity.actionStart(mContext);
                break;
        }
    }

    private void clickQuanguan() {
        setDp("1", false);
        setDp("2", false);
        setDp("3", false);
    }

    private void clickQuankai() {
        setDp("1", true);
        setDp("2", true);
        setDp("3", true);
    }

    private void clickSwitchLeft() {//开关左边
        setDp("3", !switchLeft);
    }

    private void clickSwitchCenter() {//开关中间
        setDp("2", !switchCenter);
    }

    private void clickSwitchRight() {//开关右边
        setDp("1", !switchRight);
    }

    private void set() {
        DeviceSwitchSetActivity.actionStart(mContext,
                getIntent().getStringExtra("member_type"),
                getIntent().getStringExtra("device_id"),
                getIntent().getStringExtra("old_name"),
                getIntent().getStringExtra("room_name")
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDeviceBeen != null) {
            TuyaDeviceManager.getDeviceManager().initDevice(mDeviceBeen);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDevice != null) {
            mDevice.unRegisterDevListener();
            mDevice.onDestroy();
        }
    }
}
