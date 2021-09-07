//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yiyang.cn.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.zs.easy.mqtt.EasyMqttService;
import com.zs.easy.mqtt.IEasyMqttCallBack;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttService {
    private final String TAG;
    private boolean canDoConnect;
    private MqttAndroidClient client;
    private MqttConnectOptions conOpt;
    private Context context;
    private String serverUrl;
    private String userName;
    private String passWord;
    private String clientId;
    private String will_topic;
    private String will_message;
    private int will_qos;
    private boolean will_retained;
    private int maxInflight;
    private int timeOut;
    private int keepAliveInterval;
    private boolean retained;
    private boolean cleanSession;
    private boolean autoReconnect;
    private IEasyMqttCallBack starMQTTCallBack;
    private IMqttActionListener iMqttActionListener;
    private MqttCallback mqttCallback;

    public MqttService(MqttService.Builder builder) {
        this.TAG = "MqttService";
        this.canDoConnect = true;
        this.serverUrl = "";
        this.userName = "admin";
        this.passWord = "password";
        this.clientId = "";
        this.timeOut = 10;
        this.keepAliveInterval = 20;
        this.retained = false;
        this.cleanSession = false;
        this.autoReconnect = true;
        this.iMqttActionListener = new IMqttActionListener() {
            public void onSuccess(IMqttToken arg0) {
                Log.i("MqttService", "mqtt connect success ");
                if(MqttService.this.starMQTTCallBack != null) {
                    MqttService.this.starMQTTCallBack.connectSuccess(arg0);
                }

            }

            public void onFailure(IMqttToken arg0, Throwable arg1) {
                Log.i("MqttService", "mqtt connect failed ");
                if(MqttService.this.starMQTTCallBack != null) {
                    MqttService.this.starMQTTCallBack.connectFailed(arg0, arg1);
                }

            }
        };
        this.mqttCallback = new MqttCallback() {
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String msgContent = new String(message.getPayload());
                String detailLog = topic + ";qos:" + message.getQos() + ";retained:" + message.isRetained();
                Log.i("MqttService", "messageArrived:" + msgContent);
                Log.i("MqttService", detailLog);
                if(MqttService.this.starMQTTCallBack != null) {
                    MqttService.this.starMQTTCallBack.messageArrived(topic, msgContent, message.getQos());
                }

            }

            public void deliveryComplete(IMqttDeliveryToken arg0) {
                if(MqttService.this.starMQTTCallBack != null) {
                    MqttService.this.starMQTTCallBack.deliveryComplete(arg0);
                }

                Log.i("MqttService", "deliveryComplete");
            }

            public void connectionLost(Throwable arg0) {
                if(MqttService.this.starMQTTCallBack != null) {
                    MqttService.this.starMQTTCallBack.connectionLost(arg0);
                }

                Log.i("MqttService", "connectionLost");
            }
        };
        this.context = builder.context;
        this.serverUrl = builder.serverUrl;
        this.userName = builder.userName;
        this.passWord = builder.passWord;
        this.clientId = builder.clientId;
        this.timeOut = builder.timeOut;
        this.keepAliveInterval = builder.keepAliveInterval;
        this.retained = builder.retained;
        this.cleanSession = builder.cleanSession;
        this.autoReconnect = builder.autoReconnect;
        this.will_topic = builder.willTopic;
        this.will_message = builder.willMessage;
        this.will_qos = builder.willQos;
        this.will_retained = builder.willRetained;
        this.maxInflight = builder.maxInfight;
        this.init();
    }

    public void publish(String msg, String topic, int qos, boolean retained) {
        try {
            this.client.publish(topic, msg.getBytes(), qos, retained);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    private void init() {
        this.client = new MqttAndroidClient(this.context, this.serverUrl, this.clientId);
        this.client.setCallback(this.mqttCallback);
        this.conOpt = new MqttConnectOptions();
        this.conOpt.setCleanSession(this.cleanSession);
        this.conOpt.setConnectionTimeout(this.timeOut);
        this.conOpt.setKeepAliveInterval(this.keepAliveInterval);
        this.conOpt.setUserName(this.userName);
        this.conOpt.setPassword(this.passWord.toCharArray());
        this.conOpt.setAutomaticReconnect(this.autoReconnect);
        this.conOpt.setWill(this.will_topic,this.will_message.getBytes(),will_qos,will_retained);
        this.conOpt.setMaxInflight(this.maxInflight);
    }

    public void close() {
        try {
            this.client.close();
        } catch (Exception var2) {
            Log.e("MqttService", var2.toString());
        }

    }

    public void connect(IEasyMqttCallBack starMQTTCallBack) {
        this.starMQTTCallBack = starMQTTCallBack;
        if(this.canDoConnect && !this.client.isConnected()) {
            try {
                this.client.connect(this.conOpt, (Object)null, this.iMqttActionListener);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }

    public void subscribe(String[] topics, int[] qos) {
        try {
            Log.i("MqttService", "execute subscribe -- qos = " + qos.toString());
            this.client.subscribe(topics, qos);
        } catch (Exception var4) {
            Log.e("MqttService", var4.toString());
        }

    }
    public void unSubscribe(String[] topics) {
        try {
            Log.i("MqttService", "execute unSubscribe");
            this.client.unsubscribe(topics);
        } catch (Exception var4) {
            Log.e("MqttService", var4.toString());
        }

    }


    public void disconnect() {
        try {
            this.client.disconnect();
        } catch (Exception var2) {
            Log.e("MqttService", var2.toString());
        }

    }

    public boolean isConnected() {
        try {
            return this.client.isConnected();
        } catch (Exception var2) {
            Log.e("MqttService", var2.toString());
            return false;
        }
    }

    private boolean isConnectIsNomarl() {
        @SuppressLint("WrongConstant") ConnectivityManager connectivityManager = (ConnectivityManager)this.context.getSystemService("connectivity");
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info != null && info.isAvailable()) {
            String name = info.getTypeName();
            Log.i("MqttService", "MQTT current network name：" + name);
            return true;
        } else {
            Log.i("MqttService", "MQTT no network");
            return false;
        }
    }

    public static final class Builder {
        private Context context;
        private String serverUrl;
        private String userName = "admin";
        private String passWord = "password";
        private String clientId;
        private int timeOut = 10;
        private int keepAliveInterval = 20;
        private boolean retained = false;
        private boolean cleanSession = false;
        private boolean autoReconnect = false;
        private String willTopic = "wit/server/499";
        private String willMessage = "K.";
        private int willQos = 2;
        private boolean willRetained = true;
        private int maxInfight = 9999999;

        public Builder() {
        }

        public MqttService.Builder serverUrl(String serverUrl) {
            this.serverUrl = serverUrl;
            return this;
        }

        public MqttService.Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public MqttService.Builder passWord(String passWord) {
            this.passWord = passWord;
            return this;
        }

        public MqttService.Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public MqttService.Builder timeOut(int timeOut) {
            this.timeOut = timeOut;
            return this;
        }

        public MqttService.Builder keepAliveInterval(int keepAliveInterval) {
            this.keepAliveInterval = keepAliveInterval;
            return this;
        }

        public MqttService.Builder retained(boolean retained) {
            this.retained = retained;
            return this;
        }

        public MqttService.Builder autoReconnect(boolean autoReconnect) {
            this.autoReconnect = autoReconnect;
            return this;
        }

        public MqttService.Builder cleanSession(boolean cleanSession) {
            this.cleanSession = cleanSession;
            return this;
        }

        public MqttService.Builder willTopic(String willTopic){
            this.willTopic = willTopic;
            return this;
        }
        public MqttService.Builder willMessage(String willMessage){
            this.willMessage = willMessage;
            return this;
        }
        public MqttService.Builder wilQos(int willQos){
            this.willQos = willQos;
            return this;
        }
        public MqttService.Builder wilRetained(boolean willRetained){
            this.willRetained = willRetained;
            return this;
        }
        public MqttService.Builder maxInfight(int maxInfight){
            this.maxInfight = maxInfight;
            return this;
        }

        public MqttService bulid(Context context) {
            this.context = context;
            return new MqttService(this);
        }
    }
}
