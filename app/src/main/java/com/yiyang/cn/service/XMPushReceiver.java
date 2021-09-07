package com.yiyang.cn.service;

import android.content.Context;
import android.util.Log;

import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import cn.jpush.android.service.PluginXiaomiPlatformsReceiver;

public class XMPushReceiver extends PushMessageReceiver {

    final PluginXiaomiPlatformsReceiver receiver = new PluginXiaomiPlatformsReceiver();

    @Override
    public void onReceivePassThroughMessage(final Context context, final MiPushMessage message) {
        receiver.onReceivePassThroughMessage(context, message);
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        receiver.onNotificationMessageClicked(context, message);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        receiver.onNotificationMessageArrived(context, message);
        Log.i("jgpush", message.getContent().toString());
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        receiver.onCommandResult(context, message);
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        receiver.onReceiveRegisterResult(context, message);
    }


}
