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
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.TuyaBaseCameraDeviceActivity;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaBottomDialog;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaBottomDialogView;
import com.yiyang.cn.activity.tuya_device.dialog.TuyaTishiDialog;
import com.yiyang.cn.activity.tuya_device.utils.TuyaDialogUtils;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManagerTwo;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.get_net.Urls;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class CameraSetCunchuActivity extends TuyaBaseCameraDeviceActivity {// implements ITuyaCameraDeviceControlCallback

    @BindView(R.id.tv_zongrongliang)
    TextView tv_zongrongliang;
    @BindView(R.id.tv_yishiyong)
    TextView tv_yishiyong;
    @BindView(R.id.tv_shengyurongliang)
    TextView tv_shengyurongliang;
    @BindView(R.id.iv_switch_luxiang)
    ImageView iv_switch_luxiang;
    @BindView(R.id.ll_dingshi)
    LinearLayout ll_dingshi;
    @BindView(R.id.bt_geshihua)
    TextView bt_geshihua;
    @BindView(R.id.tv_luxiang_mode)
    TextView tv_luxiang_mode;
    @BindView(R.id.ll_luxiang_mode)
    LinearLayout ll_luxiang_mode;
    private DeviceBean mDeviceBeen;

    private boolean isGeshihua;
    private boolean isluxiang;//是否开启录像
    private int cardZhuangtai;
    private String cardRongliang;
    private String cardLuxiangMode;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CameraSetCunchuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera_set_cunchu;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("存储设置");
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
        showProgressDialog();
        mDeviceBeen = TuyaDeviceManagerTwo.getDeviceManager().getDeviceBeen();
        showProgressDialog();
        getDp("110");
        getDp("109");

        try {
            Map<String, Object> dps = mDeviceBeen.getDps();
            Y.e("我是什么" + dps.toString());

            cardZhuangtai = (int) dps.get("110");

            isluxiang = (boolean) dps.get("150");
            if (isluxiang) {
                iv_switch_luxiang.setImageResource(R.mipmap.switch_open);
                ll_luxiang_mode.setVisibility(View.VISIBLE);
            } else {
                iv_switch_luxiang.setImageResource(R.mipmap.switch_close);
                ll_luxiang_mode.setVisibility(View.GONE);
            }

            cardRongliang = (String) dps.get("109");
            String[] split = cardRongliang.replace("|", ",").split(",");
            if (split.length == 3) {
                String s1 = TuyaDeviceManagerTwo.bytes2kb(Y.getLong(split[0]));
                String s2 = TuyaDeviceManagerTwo.bytes2kb(Y.getLong(split[1]));
                String s3 = TuyaDeviceManagerTwo.bytes2kb(Y.getLong(split[2]));

                tv_zongrongliang.setText(s1);
                tv_yishiyong.setText(s2);
                tv_shengyurongliang.setText(s3);
            }

            cardLuxiangMode = (String) dps.get("151");
            if (cardLuxiangMode.equals("1")) {
                tv_luxiang_mode.setText("事件录像");
            } else if (cardLuxiangMode.equals("2")) {
                tv_luxiang_mode.setText("连续录像");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_switch_luxiang, R.id.ll_dingshi, R.id.bt_geshihua, R.id.ll_luxiang_mode})
    public void onViewClicked(View view) {
        if (cardZhuangtai == 5) {
            TuyaDialogUtils.t(mContext, "未检测到存储卡");
            return;
        }

        switch (view.getId()) {
            case R.id.iv_switch_luxiang:
                clickLuxiang();
                break;
            case R.id.ll_dingshi:
                break;
            case R.id.ll_luxiang_mode:
                clickLuxiangMode();
                break;
            case R.id.bt_geshihua:
                clickGeshihua();
                break;
        }
    }

    private void clickLuxiangMode() {
        List<String> names = new ArrayList<>();
        names.add("事件录像");
        names.add("连续录像");
        final TuyaBottomDialog bottomDialog = new TuyaBottomDialog(this);
        bottomDialog.setModles(names);
        bottomDialog.setClickListener(new TuyaBottomDialogView.ClickListener() {
            @Override
            public void onClickItem(int pos) {
                bottomDialog.dismiss();
                if (pos == 0) {
                    setluxiangMode(0);
                } else if (pos == 1) {
                    setluxiangMode(2);
                }
            }

            @Override
            public void onClickCancel(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.showBottom();
    }

    private void setluxiangMode(int pos) {
        showProgressDialog("");
        setDp("151", pos + "");
    }

    private void clickGeshihua() {
        TuyaTishiDialog dialog = new TuyaTishiDialog(mContext, new TuyaTishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TuyaTishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TuyaTishiDialog dialog) {
                isGeshihua = true;
                setDp("111", true);
            }

            @Override
            public void onDismiss(TuyaTishiDialog dialog) {

            }
        });
        dialog.setTextTitle("格式化");
        dialog.setTextCont("是否格式化存储卡？");
        dialog.show();
    }

    private void clickLuxiang() {
        setDp("150", !isluxiang);
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(mDeviceBeen.getDevId())) {
                        getData((String) message.content);
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
        if (key.equals("109")) {
            cardRongliang = value.toString();
            String[] split = cardRongliang.replace("|", ",").split(",");
            if (split.length == 3) {
                String s1 = TuyaDeviceManagerTwo.bytes2kb(Y.getLong(split[0]));
                String s2 = TuyaDeviceManagerTwo.bytes2kb(Y.getLong(split[1]));
                String s3 = TuyaDeviceManagerTwo.bytes2kb(Y.getLong(split[2]));

                tv_zongrongliang.setText(s1);
                tv_yishiyong.setText(s2);
                tv_shengyurongliang.setText(s3);
            }
        } else if (key.equals("110")) {
            dismissProgressDialog();
            cardZhuangtai = (int) value;
            if (cardZhuangtai == 5) {
                TuyaDialogUtils.t(mContext, "未检测到存储卡");
            }
        } else if (key.equals("111")) {//存储卡格式化

        } else if (key.equals("117")) {//存储卡格式化状态
            if (isGeshihua) {
                showProgressDialog("格式化：" + value.toString() + "%");
            }

            if (Y.getInt(value.toString()) >= 100) {
                isGeshihua = false;
                dismissProgressDialog();
            }
        } else if (key.equals("150")) {
            dismissProgressDialog();
            isluxiang = (boolean) value;
            if (isluxiang) {
                iv_switch_luxiang.setImageResource(R.mipmap.switch_open);
                ll_luxiang_mode.setVisibility(View.VISIBLE);
            } else {
                iv_switch_luxiang.setImageResource(R.mipmap.switch_close);
                ll_luxiang_mode.setVisibility(View.GONE);
            }
        } else if (key.equals("151")) {
            dismissProgressDialog();
            cardLuxiangMode = value.toString();
            if (cardLuxiangMode.equals("1")) {
                tv_luxiang_mode.setText("事件录像");
            } else if (cardLuxiangMode.equals("2")) {
                tv_luxiang_mode.setText("连续录像");
            }
        }
    }

    private void setDps(String key, Object value) {
        Y.e("解析出的数据:  " + "key = " + key + "  |  value = " + value);
        Map<String, Object> dps = mDeviceBeen.getDps();
        dps.put(key, value);
        mDeviceBeen.setDps(dps);
        TuyaDeviceManagerTwo.getDeviceManager().setDeviceBeen(mDeviceBeen);
    }
}
