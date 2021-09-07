package com.yiyang.cn.mqtt_zhiling;

import android.content.Context;
import android.util.Log;

import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.yiyang.cn.app.UIHelper;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class NewZhiNengJiajuMqttMingLing {
    private Context context;
    private String ccid;
    private String serverId;
    private String topicHardware;
    private String topicServer;
    private String topicApp;

    private String shishiTopic;//实时数据的topic

    public NewZhiNengJiajuMqttMingLing(Context context, String ccid, String serverId) {
        this.context = context;
        this.ccid = ccid;
        this.serverId = serverId;

        topicHardware = "zn/hardware/" + serverId + ccid;
        topicServer = "zn/server/" + serverId + ccid;
        topicApp = "zn/app/" + serverId + ccid;


        Log.i("Rair", topicHardware);

        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(topicHardware)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "新智能家居发出的指令:" + topicHardware);

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });

        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(topicServer)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "新智能家居发出的指令:" + topicServer);

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });

        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(topicApp)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "新智能家居发出的指令:" + topicApp);

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });


    }


    /**
     * 取消app订阅
     *
     * @param listener
     */
    public void unSubscribeShiShiXinXi_App(String ccid, String serverId, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        shishiTopic = "zn/app/" + serverId + ccid;
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(shishiTopic)
                .setQos(2), listener);
    }


    /**
     * 示例:M231308yingjian_wifiabc12345.
     * M23 命令码
     * 13：表示Wi-Fi 名称长度
     * 08:表示Wi-Fi密码长度
     * yingjian_wifi：Wi-Fi名称示例
     * abc12345：Wi-Fi密码示例
     * .结束符
     */

    /**
     * 通用给app配对相关
     *
     * @param str
     * @param listener
     */
    public void setActionApp(String str, IMqttActionListener listener) {

        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }

        Log.i("Rair", str);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(str)
                .setQos(2).setRetained(false)
                .setTopic(topicApp), listener);
    }

    /**
     * 通用给app配对相关
     *
     * @param str
     * @param listener
     */
    public void setActionHardware(String str, IMqttActionListener listener) {

        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }

        Log.i("Rair", str);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(str)
                .setQos(2).setRetained(false)
                .setTopic(topicHardware), listener);
    }

}
