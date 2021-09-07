package com.yiyang.cn.activity;

import android.util.Log;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;
import com.yiyang.cn.R;
import com.yiyang.cn.util.SoundPoolUtils;

import cn.jpush.android.service.PluginHuaweiPlatformsService;

public class MyHWPushService extends HmsMessageService {
    /**
     * * o	1.风暖加热器
     * 2.水暖加热器
     * 3.汽车
     * 4.货车防盗
     * 5.智能家居
     * 6.驻车空调	带有提示音的风暖、水暖报警提示（字母o为历史版本，特殊）
     *
     * jyj_0000		app_type 1:ios 2:安卓IOS / Android版本有更新
     * jyj_0001		tx_state 0:提现失败1:提现成功提现处理：通知
     * jyj_0002		online_state 0：离线 1：上线智慧医养智能主机离线 、上线提醒
     * jyj_0003		href_url广告推送，跳转h5链接，h5链接放在url字段处
     * jyj_0004		通知消息推送，跳转消息列表
     * jyj_0005		kind_type
     * jyj_0006
     * 0:普通商品 1: 团购商品 2:修配厂店铺类型详情
     * 商品推送，点击推送消息进入商品详情，推送信息中带有商品对应id，字段参考普通商品详情以及团购商品详情
     */
    final PluginHuaweiPlatformsService service = new PluginHuaweiPlatformsService();

    @Override
    public void onNewToken(String s) {
        service.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        service.onMessageReceived(remoteMessage);
        Log.i("huawei_tuisong", remoteMessage.getData().toString());

    }

    @Override
    public void onMessageSent(String s) {
        service.onMessageSent(s);
    }

    @Override
    public void onSendError(String s, Exception e) {
        service.onSendError(s, e);
    }

    @Override
    public void onDeletedMessages() {
        service.onDeletedMessages();
    }
}