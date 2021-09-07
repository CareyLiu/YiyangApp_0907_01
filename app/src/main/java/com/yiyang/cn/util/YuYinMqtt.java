package com.yiyang.cn.util;

import android.content.Context;
import android.util.Log;

import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.yiyang.cn.config.MyApplication;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import static com.yiyang.cn.config.MyApplication.CAR_NOTIFY;

public class YuYinMqtt {

    private Context context;
    private String topic;

    public YuYinMqtt(Context context, String topic) {
        this.context = context;
        this.topic = topic;
    }


    public void pushMingLing(String msg) {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(msg)
                .setQos(2).setRetained(false)
                .setTopic(topic), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "发送命令主题:" + topic + "  success");

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "发送命令主题:" + topic + "  fail");
            }
        });
    }

    public void subscribeMingLing() {
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(topic)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "订阅主题命令:" + topic + "  success");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "订阅主题命令:" + topic + "  fail");
            }
        });

    }


}
