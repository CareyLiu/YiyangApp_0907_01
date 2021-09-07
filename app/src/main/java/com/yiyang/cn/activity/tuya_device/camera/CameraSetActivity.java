package com.yiyang.cn.activity.tuya_device.camera;

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
import com.yiyang.cn.activity.tuya_device.TuyaBaseCameraDeviceActivity;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaBottomDialog;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaBottomDialogView;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaTishiDialog;
import com.yiyang.cn.activity.tuya_device.utils.TuyaDialogUtils;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManagerTwo;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaHomeManager;
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

public class CameraSetActivity extends TuyaBaseCameraDeviceActivity {


    @BindView(R.id.ll_device_info)
    LinearLayout ll_device_info;
    @BindView(R.id.ll_device_weizhi)
    LinearLayout ll_device_weizhi;
    @BindView(R.id.ll_device_name)
    LinearLayout ll_device_name;
    @BindView(R.id.ll_jichu)
    LinearLayout ll_jichu;
    @BindView(R.id.ll_yeshi)
    LinearLayout ll_yeshi;
    @BindView(R.id.ll_baojing)
    LinearLayout ll_baojing;
    @BindView(R.id.ll_cunchu)
    LinearLayout ll_cunchu;
    @BindView(R.id.bt_delete)
    TextView bt_delete;
    @BindView(R.id.tv_yeshi)
    TextView tv_yeshi;
    @BindView(R.id.tv_room_name)
    TextView tv_room_name;
    @BindView(R.id.tv_device_name)
    TextView tv_device_name;
    @BindView(R.id.iv_switch_yinsi)
    ImageView iv_switch_yinsi;
    @BindView(R.id.ll_other_view)
    LinearLayout ll_other_view;
    @BindView(R.id.ll_yinsi_mode)
    LinearLayout ll_yinsi_mode;
    private ITuyaDevice device;
    private DeviceBean deviceBeen;
    private String yeshiPos;
    private String family_id;
    private String device_id;
    private String member_type;
    private String old_name;
    private String room_name;
    private boolean isYinsi;
    private String tuyaDevId;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String member_type, String device_id, String old_name, String room_name) {
        Intent intent = new Intent(context, CameraSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("member_type", member_type);
        intent.putExtra("device_id", device_id);
        intent.putExtra("old_name", old_name);
        intent.putExtra("room_name", room_name);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera_set;
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

        deviceBeen = TuyaDeviceManagerTwo.getDeviceManager().getDeviceBeen();
        if (deviceBeen != null) {
            device = TuyaDeviceManagerTwo.getDeviceManager().getDevice();
            tuyaDevId = deviceBeen.getDevId();
            Map<String, Object> dps = deviceBeen.getDps();
            yeshiPos = (String) dps.get("108");
            if (yeshiPos.equals("0")) {
                tv_yeshi.setText("自动");
            } else if (yeshiPos.equals("2")) {
                tv_yeshi.setText("开启");
            } else if (yeshiPos.equals("1")) {
                tv_yeshi.setText("关闭");
            }
            Object o = dps.get("105");
            if (o != null) {
                isYinsi = (boolean) o;
            } else {
                isYinsi = false;
                ll_yinsi_mode.setVisibility(View.GONE);
            }
            if (isYinsi) {
                iv_switch_yinsi.setImageResource(R.mipmap.switch_open);
                ll_other_view.setVisibility(View.GONE);
            } else {
                iv_switch_yinsi.setImageResource(R.mipmap.switch_close);
                ll_other_view.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(deviceBeen.getDevId())) {
                        getData((String) message.content);
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_STATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_NETWORKSTATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_DEVINFOUPDATE) {


                } else if (message.type == ConstanceValue.MSG_CAMERA_FAIL) {
                    TuyaDialogUtils.t(mContext, (String) message.content);
                } else if (message.type == ConstanceValue.MSG_DEVICE_ROOM_NAME_CHANGE) {
                    tv_room_name.setText(message.content.toString());
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
            String value = jsonObject.getString(key);
            jieData(key, value, jsonObject);
        }
    }

    private void jieData(String key, String value, JSONObject jsonObject) {
        Y.e("解析出的数据:  " + "key: " + key + ",value:" + value);
        if (key.equals("108")) {
            dismissProgressDialog();
            yeshiPos = value;
            if (yeshiPos.equals("0")) {
                tv_yeshi.setText("自动");
            } else if (yeshiPos.equals("2")) {
                tv_yeshi.setText("开启");
            } else if (yeshiPos.equals("1")) {
                tv_yeshi.setText("关闭");
            }
        } else if (key.equals("105")) {
            dismissProgressDialog();
            isYinsi = jsonObject.getBoolean("105");
            if (isYinsi) {
                iv_switch_yinsi.setImageResource(R.mipmap.switch_open);
                ll_other_view.setVisibility(View.GONE);
            } else {
                iv_switch_yinsi.setImageResource(R.mipmap.switch_close);
                ll_other_view.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick({R.id.iv_switch_yinsi, R.id.bt_delete, R.id.ll_device_info, R.id.ll_device_weizhi, R.id.ll_device_name, R.id.ll_jichu, R.id.ll_yeshi, R.id.ll_baojing, R.id.ll_cunchu})
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
            case R.id.ll_jichu:
                clickJichu();
                break;
            case R.id.ll_yeshi:
                clickYeshi();
                break;
            case R.id.ll_baojing:
                if (deviceBeen == null) {
                    TuyaDialogUtils.t(mContext, "设备已失效!");
                    return;
                }
                CameraSetBaojingActivity.actionStart(mContext);
                break;
            case R.id.ll_cunchu:
                if (deviceBeen == null) {
                    TuyaDialogUtils.t(mContext, "设备已失效!");
                    return;
                }
                CameraSetCunchuActivity.actionStart(mContext);
                break;
            case R.id.iv_switch_yinsi:
                clickYinsi();
                break;
            case R.id.bt_delete:
                clickDelete();
                break;
        }
    }

    private void clickJichu() {
        if (deviceBeen == null) {
            TuyaDialogUtils.t(mContext, "设备已失效!");
            return;
        }
        CameraSetJichuActivity.actionStart(mContext);
    }

    private void clickYinsi() {
        if (deviceBeen == null) {
            TuyaDialogUtils.t(mContext, "设备已失效!");
            return;
        }

        showProgressDialog();
        Map<String, Boolean> dps = new HashMap<>();
        dps.put("105", !isYinsi);
        device.publishDps(TuyaDeviceManagerTwo.getDeviceManager().getJosnString(dps), new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                dismissProgressDialog();
                Y.e("设置失败 " + error);
            }

            @Override
            public void onSuccess() {
                Y.e("设置成功");
            }
        });
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
                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(CameraSetActivity.this,
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
                        Y.tError(response);
                    }
                });
    }

    private void clickRoom() {
        Bundle bundle = new Bundle();
        bundle.putString("device_id", device_id);
        bundle.putString("family_id", family_id);
        bundle.putString("member_type", member_type);
        startActivity(new Intent(mContext, ZhiNengRoomManageActivity.class).putExtras(bundle));
    }

    private void clickYeshi() {
        if (deviceBeen == null) {
            TuyaDialogUtils.t(mContext, "设备已失效!");
            return;
        }

        List<String> names = new ArrayList<>();
        names.add("自动");
        names.add("开启");
        names.add("关闭");
        final TuyaBottomDialog bottomDialog = new TuyaBottomDialog(this);
        bottomDialog.setModles(names);
        bottomDialog.setClickListener(new TuyaBottomDialogView.ClickListener() {
            @Override
            public void onClickItem(int pos) {
                bottomDialog.dismiss();
                if (pos == 0) {
                    setYeshi(0);
                } else if (pos == 1) {
                    setYeshi(2);
                } else if (pos == 2) {
                    setYeshi(1);
                }
            }

            @Override
            public void onClickCancel(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.showBottom();
    }

    private void setYeshi(int pos) {
        if (device != null) {
            showProgressDialog();
            Map<String, String> dps = new HashMap<>();
            dps.put("108", pos + "");
            device.publishDps(TuyaDeviceManagerTwo.getDeviceManager().getJosnString(dps), new IResultCallback() {
                @Override
                public void onError(String code, String error) {
                    dismissProgressDialog();
                    Y.e("设置失败 " + error);
                }

                @Override
                public void onSuccess() {
                    Y.e("设置成功");
                }
            });
        } else {
            Y.t("设备已失效");
        }
    }

    private void clickDelete() {
        if (member_type.equals("1") || member_type.equals("3")) {
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
        } else {
            Y.t("需要管理员权限才可删除设备！");
        }
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
                                notice.devId = tuyaDevId;
                                RxBus.getDefault().sendRx(notice);
                                finish();
                            }
                        });
                        dialog.setTextCont("设备移除成功");
                        dialog.setTextCancel("");
                        dialog.show();

                        if (device != null) {
                            TuyaDeviceManagerTwo.getDeviceManager().unRegisterDevListenerTwo();
                            device.removeDevice(new IResultCallback() {
                                @Override
                                public void onError(String errorCode, String errorMsg) {
                                    Y.e("移除设备失败:" + errorMsg);
                                }

                                @Override
                                public void onSuccess() {
                                    Y.e("涂鸦设备移除成功");
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
}
