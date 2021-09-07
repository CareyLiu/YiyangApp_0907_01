package com.yiyang.cn.activity.tuya_device.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.TuyaBaseCameraDeviceActivity;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaBottomDialogView;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaBottomDialog;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManagerTwo;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
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

public class CameraSetJichuActivity extends TuyaBaseCameraDeviceActivity {

    @BindView(R.id.iv_switch_huamian)
    ImageView iv_switch_huamian;
    @BindView(R.id.iv_switch_shuiyin)
    ImageView iv_switch_shuiyin;
    @BindView(R.id.tv_duijiang)
    TextView tv_duijiang;
    @BindView(R.id.ll_duijiang)
    LinearLayout ll_duijiang;
    @BindView(R.id.ll_camera_b_fanzhuan)
    LinearLayout ll_camera_b_fanzhuan;
    @BindView(R.id.tv_fanzhuan)
    TextView tv_fanzhuan;
    @BindView(R.id.ll_camera_a_fanzhuan)
    LinearLayout ll_camera_a_fanzhuan;
    private DeviceBean deviceBeen;
    private ITuyaDevice device;
    private boolean fanzhuan;
    private boolean shuiyin;
    private String productId;
    private String fanzhuanFangshi;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CameraSetJichuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera_set_jichu;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("基础功能设置");
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

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(deviceBeen.getDevId())) {
                        getData((String) message.content);
                    }
                }else if (message.type == ConstanceValue.MSG_DEVICE_STATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_NETWORKSTATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_DEVINFOUPDATE) {


                } else if (message.type == ConstanceValue.MSG_CAMERA_FAIL) {

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
        if (key.equals("103")) {
            dismissProgressDialog();
            fanzhuan = jsonObject.getBoolean("103");
            if (fanzhuan) {
                iv_switch_huamian.setImageResource(R.mipmap.switch_open);
            } else {
                iv_switch_huamian.setImageResource(R.mipmap.switch_close);
            }
        } else if (key.equals("104")) {
            dismissProgressDialog();
            shuiyin = jsonObject.getBoolean("104");
            if (shuiyin) {
                iv_switch_shuiyin.setImageResource(R.mipmap.switch_open);
            } else {
                iv_switch_shuiyin.setImageResource(R.mipmap.switch_close);
            }
        } else if (key.equals("102")) {
            dismissProgressDialog();
            fanzhuanFangshi = jsonObject.getString("102");
            if (fanzhuanFangshi.equals("flip_none")) {
                tv_fanzhuan.setText("关闭");
            } else if (fanzhuanFangshi.equals("flip_horizontal_mirror")) {
                tv_fanzhuan.setText("水平翻转");
            } else if (fanzhuanFangshi.equals("flip_vertical_mirror")) {
                tv_fanzhuan.setText("垂直翻转");
            } else if (fanzhuanFangshi.equals("flip_rotate_180")) {
                tv_fanzhuan.setText("旋转180℃");
            }
        }
    }

    private void init() {
        deviceBeen = TuyaDeviceManagerTwo.getDeviceManager().getDeviceBeen();
        device = TuyaDeviceManagerTwo.getDeviceManager().getDevice();
        productId = deviceBeen.getProductId();
        Map<String, Object> dps = deviceBeen.getDps();
        String s = new Gson().toJson(dps);
        Y.e(productId);
        Y.e("考了几分但是落空发送的  " + s);

//        if (productId.equals(TuyaDeviceManager.CAMERA_A)) {
//            ll_camera_a_fanzhuan.setVisibility(View.VISIBLE);
//            ll_camera_b_fanzhuan.setVisibility(View.GONE);
//            fanzhuanFangshi = (String) dps.get("102");
//            if (fanzhuanFangshi.equals("flip_none")) {
//                tv_fanzhuan.setText("关闭");
//            } else if (fanzhuanFangshi.equals("flip_horizontal_mirror")) {
//                tv_fanzhuan.setText("水平翻转");
//            } else if (fanzhuanFangshi.equals("flip_vertical_mirror")) {
//                tv_fanzhuan.setText("垂直翻转");
//            } else if (fanzhuanFangshi.equals("flip_rotate_180")) {
//                tv_fanzhuan.setText("旋转180℃");
//            }
//        } else if (productId.equals(TuyaDeviceManager.CAMERA_B)) {
//            ll_camera_a_fanzhuan.setVisibility(View.GONE);
//            ll_camera_b_fanzhuan.setVisibility(View.VISIBLE);
//            fanzhuan = (boolean) dps.get("103");
//            if (fanzhuan) {
//                iv_switch_huamian.setImageResource(R.mipmap.switch_open);
//            } else {
//                iv_switch_huamian.setImageResource(R.mipmap.switch_close);
//            }
//        }

        ll_camera_a_fanzhuan.setVisibility(View.GONE);
        ll_camera_b_fanzhuan.setVisibility(View.VISIBLE);
        fanzhuan = (boolean) dps.get("103");
        if (fanzhuan) {
            iv_switch_huamian.setImageResource(R.mipmap.switch_open);
        } else {
            iv_switch_huamian.setImageResource(R.mipmap.switch_close);
        }

        shuiyin = (boolean) dps.get("104");
        if (shuiyin) {
            iv_switch_shuiyin.setImageResource(R.mipmap.switch_open);
        } else {
            iv_switch_shuiyin.setImageResource(R.mipmap.switch_close);
        }
    }

    @OnClick({R.id.iv_switch_huamian, R.id.iv_switch_shuiyin, R.id.ll_duijiang, R.id.ll_camera_a_fanzhuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_switch_huamian:
                clickfanzhuan();
                break;
            case R.id.iv_switch_shuiyin:
                clickShuiyin();
                break;
            case R.id.ll_duijiang:
                clickDuijiang();
                break;
            case R.id.ll_camera_a_fanzhuan:
                clickFanzhuanA();
                break;
        }
    }

    private void clickFanzhuanA() {
        List<String> names = new ArrayList<>();
        names.add("关闭");
        names.add("水平翻转");
        names.add("垂直翻转");
        names.add("旋转180℃");
        final TuyaBottomDialog bottomDialog = new TuyaBottomDialog(this);
        bottomDialog.setModles(names);
        bottomDialog.setClickListener(new TuyaBottomDialogView.ClickListener() {
            @Override
            public void onClickItem(int pos) {
                bottomDialog.dismiss();
                if (pos == 0) {
                    fanzhuanFangshi = "flip_none";
                } else if (pos == 1) {
                    fanzhuanFangshi = "flip_horizontal_mirror";
                } else if (pos == 2) {
                    fanzhuanFangshi = "flip_vertical_mirror";
                } else if (pos == 3) {
                    fanzhuanFangshi = "flip_rotate_180";
                }

                if (fanzhuanFangshi.equals("flip_none")) {
                    tv_fanzhuan.setText("关闭");
                } else if (fanzhuanFangshi.equals("flip_horizontal_mirror")) {
                    tv_fanzhuan.setText("水平翻转");
                } else if (fanzhuanFangshi.equals("flip_vertical_mirror")) {
                    tv_fanzhuan.setText("垂直翻转");
                } else if (fanzhuanFangshi.equals("flip_rotate_180")) {
                    tv_fanzhuan.setText("旋转180℃");
                }
//                setFanzhuanA();
            }

            @Override
            public void onClickCancel(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.showBottom();
    }

    private void setFanzhuanA() {
        showProgressDialog();
        Map<String, String> dps = new HashMap<>();
        dps.put("102", fanzhuanFangshi);
        Y.e("我是对方 " + TuyaDeviceManagerTwo.getDeviceManager().getJosnString(dps));
        device.publishDps(TuyaDeviceManagerTwo.getDeviceManager().getJosnString(dps), new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                dismissProgressDialog();
                Y.e("我成功过了发 " + error);
            }

            @Override
            public void onSuccess() {
                Y.e("服务开发经典服饰 " + fanzhuanFangshi);
            }
        });
    }

    private void clickDuijiang() {
        List<String> names = new ArrayList<>();
        names.add("单向对讲");
        names.add("双向对讲");
        final TuyaBottomDialog bottomDialog = new TuyaBottomDialog(this);
        bottomDialog.setModles(names);
        bottomDialog.setClickListener(new TuyaBottomDialogView.ClickListener() {
            @Override
            public void onClickItem(int pos) {
                bottomDialog.dismiss();
                if (pos == 0) {
                    tv_duijiang.setText("单向对讲");
                } else {
                    tv_duijiang.setText("双向对讲");
                }
            }

            @Override
            public void onClickCancel(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.showBottom();
    }

    private void clickShuiyin() {//时间水印
        showProgressDialog();
        Map<String, Boolean> dps = new HashMap<>();
        dps.put("104", !shuiyin);
        device.publishDps(TuyaDeviceManagerTwo.getDeviceManager().getJosnString(dps), new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess() {

            }
        });
    }

    private void clickfanzhuan() {//翻转
        showProgressDialog();
        Map<String, Boolean> dps = new HashMap<>();
        dps.put("103", !fanzhuan);
        device.publishDps(TuyaDeviceManagerTwo.getDeviceManager().getJosnString(dps), new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess() {

            }
        });
    }
}
