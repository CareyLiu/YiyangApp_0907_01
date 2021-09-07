package com.yiyang.cn.view;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.DiagnosisActivity;
import com.yiyang.cn.app.UIHelper;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import static com.yiyang.cn.config.MyApplication.CAR_CTROL;
import static com.yiyang.cn.config.MyApplication.getAppContext;

public class UISetDialog {
    private CustomBaseDialog dialog;
    public void showDialog(String content) {
        if (dialog == null) {
            dialog = new CustomBaseDialog(getAppContext());
            dialog.setTitle("清除");
            dialog.setContent(content);
            dialog.setPic(getAppContext().getResources().getDrawable(R.drawable.tishi));
            dialog.setCancelClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.setConfirmClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //   HeaterMqttService.mqttService.publish("M691.", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);

                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M691.").setRetained(false)
                            .setQos(2)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(清除故障 --- 发布成功");
                            UIHelper.ToastMessage(getAppContext(), "故障清除中，请稍候", Toast.LENGTH_SHORT);
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                        }
                    });

                }
            });
        }
        dialog.show();
    }
}


