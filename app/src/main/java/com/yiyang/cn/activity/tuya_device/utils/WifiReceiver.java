package com.yiyang.cn.activity.tuya_device.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;

public class WifiReceiver extends BroadcastReceiver {
    private static final String TAG = "wifiReceiver";
    public static String WIFI_NAME;

    @Override
    public void onReceive(Context context, Intent intent) {
        //wifi连接上与否
        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_WIFI_CLOSE;
                notice.content = "wifi断开";
                RxBus.getDefault().sendRx(notice);
            } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                //获取当前wifi名称
                WIFI_NAME = wifiInfo.getSSID().replace("\"", "");
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_WIFI_INFO;
                notice.content = wifiInfo;
                RxBus.getDefault().sendRx(notice);
            }
        }
        //wifi打开与否
        if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);
            if (wifistate == WifiManager.WIFI_STATE_DISABLED) {
                Y.e("系统关闭wifi");
            } else if (wifistate == WifiManager.WIFI_STATE_ENABLED) {
                Y.e("系统开启wifi");
            }
        }
    }
}