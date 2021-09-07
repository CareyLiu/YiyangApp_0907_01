package com.yiyang.cn.activity.tuya_device.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.ZhiNengRoomManageActivity;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaBottomDialog;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaBottomDialogView;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaTishiDialog;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_Success;
import com.yiyang.cn.dialog.ZhiNengFamilyAddDIalog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class DeviceSwitchSetActivity extends TuyaBaseDeviceActivity {


    @BindView(R.id.ll_device_info)
    LinearLayout ll_device_info;
    @BindView(R.id.tv_room_name)
    TextView tv_room_name;
    @BindView(R.id.ll_device_weizhi)
    LinearLayout ll_device_weizhi;
    @BindView(R.id.tv_device_name)
    TextView tv_device_name;
    @BindView(R.id.ll_device_name)
    LinearLayout ll_device_name;
    @BindView(R.id.tv_shangdian)
    TextView tv_shangdian;
    @BindView(R.id.ll_device_shangdian)
    LinearLayout ll_device_shangdian;
    @BindView(R.id.tv_zhishideng)
    TextView tv_zhishideng;
    @BindView(R.id.ll_device_zhishideng)
    LinearLayout ll_device_zhishideng;
    @BindView(R.id.iv_beigunag_switch)
    ImageView iv_beigunag_switch;
    @BindView(R.id.bt_delete)
    TextView bt_delete;

    private String family_id;
    private String device_id;
    private String old_name;
    private String room_name;
    private String member_type;
    private String tuyaDevId;

    private DeviceBean mDeviceBeen;
    private ITuyaDevice mDevice;
    private String relay_status;
    private String light_mode;
    private boolean switch_backlight;


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String member_type, String device_id, String old_name, String room_name) {
        Intent intent = new Intent(context, DeviceSwitchSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("member_type", member_type);
        intent.putExtra("device_id", device_id);
        intent.putExtra("old_name", old_name);
        intent.putExtra("room_name", room_name);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_switch_set;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设置");
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
        initHuidiao();
    }

    private void init() {
        family_id = PreferenceHelper.getInstance(mContext).getString(AppConfig.PEIWANG_FAMILYID, "");
        device_id = getIntent().getStringExtra("device_id");
        old_name = getIntent().getStringExtra("old_name");
        room_name = getIntent().getStringExtra("room_name");
        member_type = getIntent().getStringExtra("member_type");

        tv_room_name.setText(room_name);
        tv_device_name.setText(old_name);

        mDeviceBeen = TuyaDeviceManager.getDeviceManager().getDeviceBeen();
        mDevice = TuyaDeviceManager.getDeviceManager().getDevice();
        tuyaDevId = mDeviceBeen.getDevId();

        Map<String, Object> dps = mDeviceBeen.getDps();
        relay_status = dps.get("14").toString();
        setRelayStatus();
        light_mode = dps.get("15").toString();
        setLightMode();
        switch_backlight = (boolean) dps.get("16");
        setSwitchBacklight();
    }

    private void setRelayStatus() {
        switch (relay_status) {
            case "off":
                tv_shangdian.setText("断电");
                break;
            case "on":
                tv_shangdian.setText("通电");
                break;
            case "memory":
                tv_shangdian.setText("保持重启前的状态");
                break;
        }
    }

    private void setLightMode() {
        switch (light_mode) {
            case "relay":
                tv_zhishideng.setText("指示开关");
                break;
            case "pos":
                tv_zhishideng.setText("指示位置");
                break;
            case "none":
                tv_zhishideng.setText("关闭指示灯");
                break;
        }
    }

    private void setSwitchBacklight() {
        if (switch_backlight) {
            iv_beigunag_switch.setImageResource(R.mipmap.switch_open);
        } else {
            iv_beigunag_switch.setImageResource(R.mipmap.switch_close);
        }
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(mDeviceBeen.getDevId())) {
                        getData(message.content.toString());
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_ROOM_NAME_CHANGE) {
                    if (device_id.equals(message.devId)) {
                        tv_room_name.setText(message.content.toString());
                    }
                } else if (message.type == ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME) {
                    changeDevice(message.content.toString());
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
        Y.e("解析出的数据:  " + "key = " + key + "  |  value = " + value);
        if (key.equals("14")) {
            relay_status = value.toString();
            setRelayStatus();
        } else if (key.equals("15")) {
            light_mode = value.toString();
            setLightMode();
        } else if (key.equals("16")) {
            switch_backlight = (boolean) value;
            setSwitchBacklight();
        }
    }

    @OnClick({R.id.ll_device_info, R.id.ll_device_weizhi, R.id.ll_device_name, R.id.ll_device_shangdian, R.id.ll_device_zhishideng, R.id.iv_beigunag_switch, R.id.bt_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_device_info:
                break;
            case R.id.ll_device_weizhi:
                clickRoom();
                break;
            case R.id.ll_device_name:
                clickName();
                break;
            case R.id.ll_device_shangdian:
                clickShangdian();
                break;
            case R.id.ll_device_zhishideng:
                clickZhishideng();
                break;
            case R.id.iv_beigunag_switch:
                clickBeiguang();
                break;
            case R.id.bt_delete:
                clickDelete();
                break;
        }
    }

    private void clickRoom() {
        Bundle bundle = new Bundle();
        bundle.putString("device_id", device_id);
        bundle.putString("family_id", family_id);
        bundle.putString("member_type", member_type);
        startActivity(new Intent(mContext, ZhiNengRoomManageActivity.class).putExtras(bundle));
    }

    private void clickName() {
        ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(mContext, ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME);
        zhiNengFamilyAddDIalog.show();
    }

    /**
     * 修改设备名字
     */
    private void changeDevice(String deviceName) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16033");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("family_id", family_id);
        map.put("device_id", device_id);
        map.put("old_name", old_name);
        map.put("device_name", deviceName);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            tv_device_name.setText(deviceName);
                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(DeviceSwitchSetActivity.this,
                                    "成功", "名字修改成功咯", "好的", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {

                                }
                            });
                            myCarCaoZuoDialog_success.show();
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        String str = response.getException().getMessage();
                        String[] str1 = str.split("：");
                        if (str1.length == 3) {
                            MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(DeviceSwitchSetActivity.this,
                                    "提示", str1[2], "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {

                                }
                            });
                            myCarCaoZuoDialog_caoZuoTIshi.show();
                        }
                    }
                });
    }


    private void clickDelete() {
        TuyaTishiDialog dialog = new TuyaTishiDialog(mContext, new TuyaTishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TuyaTishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TuyaTishiDialog dialog) {
                deleteDevice();
            }

            @Override
            public void onDismiss(TuyaTishiDialog dialog) {

            }
        });
        dialog.setTextCont("是否移除设备");
        dialog.setTextConfirm("移除");
        dialog.show();
    }

    /**
     * 删除设备
     */
    private void deleteDevice() {
        showProgressDialog();
        Map<String, String> map = new HashMap<>();
        map.put("code", "16034");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("family_id", family_id);
        map.put("device_id", device_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        dismissProgressDialog();
                        TuyaTishiDialog dialog = new TuyaTishiDialog(mContext, new TuyaTishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TuyaTishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TuyaTishiDialog dialog) {

                            }

                            @Override
                            public void onDismiss(TuyaTishiDialog dialog) {
                                Notice notice = new Notice();
                                notice.type = ConstanceValue.MSG_DEVICE_DELETE;
                                notice.devId = device_id;
                                RxBus.getDefault().sendRx(notice);
                                finish();
                            }
                        });
                        dialog.setTextCont("设备移除成功");
                        dialog.setTextCancel("");
                        dialog.show();

                        if (TuyaDeviceManager.getDeviceManager().getDevice() != null) {
                            TuyaDeviceManager.getDeviceManager().getDevice().removeDevice(new IResultCallback() {
                                @Override
                                public void onError(String errorCode, String errorMsg) {
                                    Y.e("移除设备失败:" + errorMsg);
                                }

                                @Override
                                public void onSuccess() {
                                    Y.e("移除设备成功");
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        dismissProgressDialog();
                        Y.tError(response);
                    }
                });
    }

    private void clickZhishideng() {
        List<String> names = new ArrayList<>();
        names.add("指示开关");
        names.add("指示位置");
        names.add("关闭指示灯");
        final TuyaBottomDialog bottomDialog = new TuyaBottomDialog(this);
        bottomDialog.setModles(names);
        bottomDialog.setClickListener(new TuyaBottomDialogView.ClickListener() {
            @Override
            public void onClickItem(int pos) {
                bottomDialog.dismiss();
                if (pos == 0) {
                    setZhishideng(0);
                } else if (pos == 1) {
                    setZhishideng(1);
                } else if (pos == 2) {
                    setZhishideng(2);
                }
            }

            @Override
            public void onClickCancel(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.showBottom();
    }

    private void setZhishideng(int pos) {
        switch (pos) {
            case 0:
                setDp("15", "relay");
                break;
            case 1:
                setDp("15", "pos");
                break;
            case 2:
                setDp("15", "none");
                break;
        }
    }

    private void clickShangdian() {
        List<String> names = new ArrayList<>();
        names.add("断电");
        names.add("通电");
        names.add("保持重启前的状态");
        final TuyaBottomDialog bottomDialog = new TuyaBottomDialog(this);
        bottomDialog.setModles(names);
        bottomDialog.setClickListener(new TuyaBottomDialogView.ClickListener() {
            @Override
            public void onClickItem(int pos) {
                bottomDialog.dismiss();
                if (pos == 0) {
                    setduandian(0);
                } else if (pos == 1) {
                    setduandian(1);
                } else if (pos == 2) {
                    setduandian(2);
                }
            }

            @Override
            public void onClickCancel(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.showBottom();
    }

    private void setduandian(int pos) {
        switch (pos) {
            case 0:
                setDp("14", "off");
                break;
            case 1:
                setDp("14", "on");
                break;
            case 2:
                setDp("14", "memory");
                break;
        }
    }

    private void clickBeiguang() {
        setDp("16", !switch_backlight);
    }
}
