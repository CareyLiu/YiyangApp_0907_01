package com.yiyang.cn.activity;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.zhinengjiaju.function.MenCiActivity;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.model.ZhiNengJiaJuNotifyJson;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class JiGuangReceive extends JPushMessageReceiver {


    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);

        Log.i("jiguang_xx", customMessage.message);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
        Log.i("jiguang_xx", notificationMessage.inAppMsgContentBody);

        // {"code":"jyj_0006","device_ccid":"1201","device_ccid_up":"jjjjjjjjjjjjjjjjjjjjjj11","device_id":"54","device_type":"12","server_id":"1\/"}
        //
        ZhiNengJiaJuNotifyJson zhiNengJiaJuNotifyJson = new Gson().fromJson(notificationMessage.notificationExtras, ZhiNengJiaJuNotifyJson.class);
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
        switch (zhiNengJiaJuNotifyJson.getCode()) {
            case "o"://风暖加热器
                break;
            case "jyj_0000":
                break;
            case "jyj_0001":
                break;
            case "jyj_0002":
                //刷新列表
                Log.i("设备离线", "设备离线刷新列表");
                if (zhiNengJiaJuNotifyJson.online_state.equals("0")) {//离线
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_ZHINENGJIAJU_ZHUJI;
                    notice.content = "0";
                    RxBus.getDefault().sendRx(notice);

                } else if (zhiNengJiaJuNotifyJson.online_state.equals("1")) {//在线
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_ZHINENGJIAJU_ZHUJI;
                    notice.content = "1";
                    RxBus.getDefault().sendRx(notice);


                }
                break;
            case "jyj_0003":
                break;
            case "jyj_0004":
                break;
            case "jyj_0005":
                break;
            case "jyj_0006":
                Y.e("见风使舵老师的打快速反击 ");

                if (AppManager.getAppManager().cunZaiClass(MenCiActivity.class).equals("0")) {
                    //判断页面是否展示
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_ZHINENGJIAJU_MENCI;
                    notice.content = zhiNengJiaJuNotifyJson;
                    RxBus.getDefault().sendRx(notice);
                } else {
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_ZHINENGJIAJU_MENCIPAGE;
                    notice.content = zhiNengJiaJuNotifyJson;
                    RxBus.getDefault().sendRx(notice);
                }
                //如果升级
                break;
            case "jyj_0007":
                break;
        }


    }
}
