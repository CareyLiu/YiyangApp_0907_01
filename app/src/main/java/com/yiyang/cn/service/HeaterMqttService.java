package com.yiyang.cn.service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.DiagnosisActivity;
import com.yiyang.cn.config.AudioFocusManager;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.model.HostModel;
import com.yiyang.cn.model.NotifyModel;
import com.yiyang.cn.model.SerializableMap;
import com.yiyang.cn.util.ConstantUtil;
import com.yiyang.cn.util.DialogManager;
import com.yiyang.cn.view.CustomBaseDialog;
import com.zs.easy.mqtt.IEasyMqttCallBack;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.Arrays;
import java.util.Map;



/**
 * Created by Sl on 2019/7/2.
 */

public class HeaterMqttService extends Service {
    private static final String TAG = "HeaterMqttService";
    public static MqttService mqttService;
    public static Handler handler;
    //App通知服务器上线
    public static String TOPIC_SERVER_ONLINE;
    //App向服务端发送控制命令
    public static String TOPIC_SERVER_ORDER;
    //App接收故障报警
    public static String TOPIC_APP_FAULT;
    //App接收车辆参数
    public static String TOPIC_APP_DATA;
    private Map<String, String> map;
    private WitMqttFormatService witMqttFormatService;
    private MediaPlayer player;
    private AudioFocusManager audioFocusManage;
    public static String server_id;
    public static String user_id;
    public static String ccid;
    public static String[] topics;
    private CustomBaseDialog dialog;
    public static NotifyModel notifyModel;
    public static String activity[] = {"activity.WindHeaterActivity", "activity.HostActivity", "activity.DiagnosisActivity", "activity.ControCarActivity"};

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        audioFocusManage = new AudioFocusManager();
        witMqttFormatService = new WitMqttFormatService();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        buildEasyMqttService();
        MqttConnect();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void showDialog(String content) {

        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = new CustomBaseDialog(getApplicationContext());
        dialog.setTitle("操作提示");
        dialog.setContent(content);
        dialog.setCancelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setConfirmClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                MyApplication.getAppContext().startActivity(new Intent( MyApplication.getAppContext(), DiagnosisActivity.class));
            }
        });
        dialog.show();

    }

    public void play(final int res) {
        if (player != null) {  //判断当mPlayer不为空的时候
            player.reset();
        }
        player = MediaPlayer.create( MyApplication.getAppContext(), res);
        if (audioFocusManage != null) {
            //请求语音播放焦点
            int requestCode = audioFocusManage.requestTheAudioFocus(new AudioFocusManager.AudioListener() {
                @Override
                public void start() {
                    player.start();//播放音频的方法
                }

                @Override
                public void pause() {
                    player.stop();
                }
            });
            if (requestCode == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                player.start();//播放音频的方法
            }
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    audioFocusManage.releaseTheAudioFocus();
                }
            });
        }

    }

    /**
     * 构建EasyMqttService对象
     */
    private void buildEasyMqttService() {
        mqttService = new MqttService.Builder()
                //设置自动重连
                .autoReconnect(true)
                //设置不清除回话session 可收到服务器之前发出的推送消息
                .cleanSession(true)
                //收法消息设置持久化
                .retained(false)
                //唯一标示 保证每个设备都唯一就可以 建议 imei
                .clientId("witcboxapp" + PreferenceHelper.getInstance(this).getString("ccid", ""))
                //mqtt服务器地址 格式例如：tcp://10.0.261.159:1883
                .serverUrl("tcp://mqtt.hljsdkj.com:9096")
                .userName("witandroid")
                .passWord("aG678om34buysdi")
                //心跳包默认的发送间隔
                .keepAliveInterval(2)
                //离线遗言主题
                .willTopic("wit/server/" + PreferenceHelper.getInstance(this).getString("of_user_id", ""))
                //离线遗言消息
                .willMessage("K.")
                //离线遗言推送策略
                .wilQos(2)
                //离线遗言持久化
                .wilRetained(true)
                //the number of maxInfligt messages
                .maxInfight(9999999)
                //构建出EasyMqttService 建议用application的context
                .bulid(getApplicationContext());

        Log.i("abc", PreferenceHelper.getInstance(this).getString("ccid", ""));
        Log.i("of_user_id", PreferenceHelper.getInstance(this).getString("of_user_id", ""));

    }

    /**
     * 订阅主题
     */
    public static void subscribe() {
        server_id = PreferenceHelper.getInstance( MyApplication.getAppContext()).getString("server_id", "");
        user_id = PreferenceHelper.getInstance( MyApplication.getAppContext()).getString("of_user_id", "");
        if (server_id.equals("") || user_id.equals("")) {
            Log.d(TAG, "user_id或server_id为空");
        } else {
            ccid = PreferenceHelper.getInstance( MyApplication.getAppContext()).getString("ccid", "");
            TOPIC_APP_FAULT = "wit/app/" + user_id;
            TOPIC_SERVER_ONLINE = "wit/server/" + server_id + user_id;
            if (ccid.length() > 1) {
                TOPIC_SERVER_ORDER = "wit/cbox/hardware/" + server_id + ccid;
                TOPIC_APP_DATA = "wit/cbox/app/" + server_id + ccid;
            }
//            String activity[] = {"activity.WindHeaterActivity", "activity.HostActivity", "activity.DiagnosisActivity"};
            topics = new String[]{TOPIC_SERVER_ONLINE, TOPIC_APP_FAULT};
            int[] qoss = new int[]{2, 2};
            //Log.d(TAG,((Activity) MyApplication.getAppContext()).getLocalClassName());
            if (Arrays.asList(activity).contains(((Activity)  MyApplication.getAppContext()).getLocalClassName())) {
                //当前Activity如果需要实时数据则重新订阅相关主题
                topics = new String[]{TOPIC_SERVER_ORDER, TOPIC_APP_DATA};
                mqttService.subscribe(topics, qoss);

            } else {
                mqttService.subscribe(topics, qoss);
                //通知服务器APP上线
                mqttService.publish("O.", TOPIC_SERVER_ONLINE, 2, false);
            }
        }

    }

    public void MqttConnect() {
        mqttService.connect(new IEasyMqttCallBack() {
            @Override
            public void messageArrived(String topic, String message, int qos) {
                Log.d(TAG, "topic:" + topic + "\n" + "message:" + message);
                if (topic.contains("wit/cbox/app")) {
                    Message msg = Message.obtain();
                    Bundle b = new Bundle();
                    switch (message) {
                        default:
                            try {
                                if (message.contains("j_")) {
                                    //车辆时时数据
                                    msg.what = ConstantUtil.MSG_HEATER_ACTUAL_DATA;
                                    final SerializableMap myMap = new SerializableMap();
                                    map = witMqttFormatService.reqCarData(message);
                                    myMap.setMap(map);
                                    b.putSerializable("map", myMap);
                                    msg.setData(b);
                                } else if (message.contains("i")) {
                                    //主机参数实时数据
                                    HostModel.build(message);
                                    msg.what = ConstantUtil.MSG_HEATER_HOST_DATA;
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        case "k5011.":
                            msg.what = ConstantUtil.MSG_HEATER_HOST_RECOVERY;
                            break;
                        case "k6911.":
                            msg.what = ConstantUtil.MSG_HEATER_CLEAN_FAULT;

                            break;

                    }
                    handler.sendMessage(msg);
                } else if (topic.contains("wit/app/")) {
                    if (message.contains("notify")) {
                        //判断当前不是启动页面或诊断页面
                        if (!((Activity)  MyApplication.getAppContext()).getLocalClassName().equals("com.yiyang.cn.activity.SplashActivity") &&
                                !((Activity)  MyApplication.getAppContext()).getLocalClassName().equals("com.yiyang.cn.activity.DiagnosisActivity")) {
                            //故障、报警
                            Gson gson = new Gson();
                            notifyModel = gson.fromJson(message, NotifyModel.class);
                            showDialog(notifyModel.getNotify_text());
                            switch (notifyModel.getSound()) {
                                case "chSound1.mp3":
                                    play(R.raw.ch_sound1);
                                    break;
                                case "chSound2.mp3":
                                    play(R.raw.ch_sound2);
                                    break;
                                case "chSound3.mp3":
                                    play(R.raw.ch_sound3);
                                    break;
                                case "chSound4.mp3":
                                    play(R.raw.ch_sound4);
                                    break;
                                case "chSound5.mp3":
                                    play(R.raw.ch_sound5);
                                    break;
                                case "chSound6.mp3":
                                    play(R.raw.ch_sound6);
                                    break;
                                case "chSound8.mp3":
                                    play(R.raw.ch_sound8);
                                    break;
                                case "chSound9.mp3":
                                    play(R.raw.ch_sound9);
                                    break;
                                case "chSound10.mp3":
                                    play(R.raw.ch_sound10);
                                    break;
                                case "chSound11.mp3":
                                    play(R.raw.ch_sound11);
                                    break;
                                case "chSound18.mp3":
                                    play(R.raw.ch_sound18);
                                    break;
                            }
                        }


                    }
                }

            }

            @Override
            public void connectionLost(Throwable throwable) {
                Log.d(TAG, "连接断开");
                if (Arrays.asList(activity).contains(((Activity)  MyApplication.getAppContext()).getLocalClassName()))
                    DialogManager.getManager((Activity)  MyApplication.getAppContext()).showMessage("正在重新连接....");
                MqttConnect();

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }

            @Override
            public void connectSuccess(IMqttToken iMqttToken) {
                DialogManager.getManager((Activity)  MyApplication.getAppContext()).dismiss();
                Log.d(TAG, "连接成功");
                subscribe();

            }

            @Override
            public void connectFailed(IMqttToken iMqttToken, Throwable throwable) {
                Log.d(TAG, "连接失败!");
                if (Arrays.asList(activity).contains(((Activity)  MyApplication.getAppContext()).getLocalClassName()))
                    DialogManager.getManager((Activity)  MyApplication.getAppContext()).showMessage("正在重新连接....");
                MqttConnect();

            }
        });
    }

}
