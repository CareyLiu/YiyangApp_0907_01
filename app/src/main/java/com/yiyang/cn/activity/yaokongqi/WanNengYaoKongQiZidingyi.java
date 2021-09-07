package com.yiyang.cn.activity.yaokongqi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.yaokongqi.model.YaokongDetailsModel;
import com.yiyang.cn.activity.yaokongqi.model.YaokongKeyModel;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.OnClick;

public class WanNengYaoKongQiZidingyi extends BaseActivity {

    @BindView(R.id.ll_key1)
    TextView ll_key1;
    @BindView(R.id.tv_key1)
    TextView tv_key1;
    @BindView(R.id.ll_key2)
    TextView ll_key2;
    @BindView(R.id.tv_key2)
    TextView tv_key2;
    @BindView(R.id.ll_key3)
    TextView ll_key3;
    @BindView(R.id.tv_key3)
    TextView tv_key3;
    @BindView(R.id.ll_key4)
    TextView ll_key4;
    @BindView(R.id.tv_key4)
    TextView tv_key4;
    @BindView(R.id.ll_key5)
    TextView ll_key5;
    @BindView(R.id.tv_key5)
    TextView tv_key5;
    @BindView(R.id.ll_key6)
    TextView ll_key6;
    @BindView(R.id.tv_key6)
    TextView tv_key6;
    private String ccid;
    private String serverId;
    private ZnjjMqttMingLing znjjMqttMingLing;
    private String topic;
    private String deviceID;
    private String shebeima;
    private List<YaokongDetailsModel.DataBean.ControlKeysListBean> control_keys_list;
    private String shebeiMaTwo;

    @Override
    public int getContentViewResId() {
        return R.layout.layout_wanneng_yaokongqi_peidui_zidingyi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("自定义");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void actionStart(Context context, String device_ccid, String shebeima, List<YaokongDetailsModel.DataBean.ControlKeysListBean> keyModels) {
        Intent intent = new Intent(context, WanNengYaoKongQiZidingyi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        intent.putExtra("shebeima", shebeima);
        intent.putExtra("keyModels", (Serializable) keyModels);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMqtt();
        initView();
    }

    private void initMqtt() {
        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
        topic = "zn/hardware/" + serverId + ccid;
        deviceID = getIntent().getStringExtra("device_ccid");
        shebeima = getIntent().getStringExtra("shebeima");
        shebeiMaTwo = shebeima.substring(0, 2);
        control_keys_list = (List<YaokongDetailsModel.DataBean.ControlKeysListBean>) getIntent().getSerializableExtra("keyModels");
    }

    private void initView() {
        if (shebeiMaTwo.equals("28")) {
            for (int i = 0; i < control_keys_list.size(); i++) {
                YaokongDetailsModel.DataBean.ControlKeysListBean bean = control_keys_list.get(i);
                String mark_id = bean.getMark_id();
                String mark_name = bean.getMark_name();
                String mark_status = bean.getMark_status();
                if (mark_id.equals(shebeima + "14")) {
                    if (mark_status.equals("1")) {
                        tv_key1.setText(mark_name);
                        ll_key1.setText(mark_name);
                        ll_key1.setTextColor(Y.getColor(R.color.color_main));
                        ll_key1.setEnabled(true);
                    } else {
                        ll_key1.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "15")) {
                    if (mark_status.equals("1")) {
                        tv_key2.setText(mark_name);
                        ll_key2.setText(mark_name);
                        ll_key2.setTextColor(Y.getColor(R.color.color_main));
                        ll_key2.setEnabled(true);
                    } else {
                        ll_key2.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "16")) {
                    if (mark_status.equals("1")) {
                        tv_key3.setText(mark_name);
                        ll_key3.setText(mark_name);
                        ll_key3.setTextColor(Y.getColor(R.color.color_main));
                        ll_key3.setEnabled(true);
                    } else {
                        ll_key3.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "17")) {
                    if (mark_status.equals("1")) {
                        tv_key4.setText(mark_name);
                        ll_key4.setText(mark_name);
                        ll_key4.setTextColor(Y.getColor(R.color.color_main));
                        ll_key4.setEnabled(true);
                    } else {
                        ll_key4.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "18")) {
                    if (mark_status.equals("1")) {
                        tv_key5.setText(mark_name);
                        ll_key5.setText(mark_name);
                        ll_key5.setTextColor(Y.getColor(R.color.color_main));
                        ll_key5.setEnabled(true);
                    } else {
                        ll_key5.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "19")) {
                    if (mark_status.equals("1")) {
                        tv_key6.setText(mark_name);
                        ll_key6.setText(mark_name);
                        ll_key6.setTextColor(Y.getColor(R.color.color_main));
                        ll_key6.setEnabled(true);
                    } else {
                        ll_key6.setEnabled(false);
                    }
                }
            }
        } else if (shebeiMaTwo.equals("37")) {
            for (int i = 0; i < control_keys_list.size(); i++) {
                YaokongDetailsModel.DataBean.ControlKeysListBean bean = control_keys_list.get(i);
                String mark_id = bean.getMark_id();
                String mark_name = bean.getMark_name();
                String mark_status = bean.getMark_status();
                if (mark_id.equals(shebeima + "08")) {
                    if (mark_status.equals("1")) {
                        tv_key1.setText(mark_name);
                        ll_key1.setText(mark_name);
                        ll_key1.setTextColor(Y.getColor(R.color.color_main));
                        ll_key1.setEnabled(true);
                    } else {
                        ll_key1.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "09")) {
                    if (mark_status.equals("1")) {
                        tv_key2.setText(mark_name);
                        ll_key2.setText(mark_name);
                        ll_key2.setTextColor(Y.getColor(R.color.color_main));
                        ll_key2.setEnabled(true);
                    } else {
                        ll_key2.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "10")) {
                    if (mark_status.equals("1")) {
                        tv_key3.setText(mark_name);
                        ll_key3.setText(mark_name);
                        ll_key3.setTextColor(Y.getColor(R.color.color_main));
                        ll_key3.setEnabled(true);
                    } else {
                        ll_key3.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "11")) {
                    if (mark_status.equals("1")) {
                        tv_key4.setText(mark_name);
                        ll_key4.setText(mark_name);
                        ll_key4.setTextColor(Y.getColor(R.color.color_main));
                        ll_key4.setEnabled(true);
                    } else {
                        ll_key4.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "12")) {
                    if (mark_status.equals("1")) {
                        tv_key5.setText(mark_name);
                        ll_key5.setText(mark_name);
                        ll_key5.setTextColor(Y.getColor(R.color.color_main));
                        ll_key5.setEnabled(true);
                    } else {
                        ll_key5.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "13")) {
                    if (mark_status.equals("1")) {
                        tv_key6.setText(mark_name);
                        ll_key6.setText(mark_name);
                        ll_key6.setTextColor(Y.getColor(R.color.color_main));
                        ll_key6.setEnabled(true);
                    } else {
                        ll_key6.setEnabled(false);
                    }
                }
            }
        } else if (shebeiMaTwo.equals("38")) {
            for (int i = 0; i < control_keys_list.size(); i++) {
                YaokongDetailsModel.DataBean.ControlKeysListBean bean = control_keys_list.get(i);
                String mark_id = bean.getMark_id();
                String mark_name = bean.getMark_name();
                String mark_status = bean.getMark_status();
                if (mark_id.equals(shebeima + "11")) {
                    if (mark_status.equals("1")) {
                        tv_key1.setText(mark_name);
                        ll_key1.setText(mark_name);
                        ll_key1.setTextColor(Y.getColor(R.color.color_main));
                        ll_key1.setEnabled(true);
                    } else {
                        ll_key1.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "12")) {
                    if (mark_status.equals("1")) {
                        tv_key2.setText(mark_name);
                        ll_key2.setText(mark_name);
                        ll_key2.setTextColor(Y.getColor(R.color.color_main));
                        ll_key2.setEnabled(true);
                    } else {
                        ll_key2.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "13")) {
                    if (mark_status.equals("1")) {
                        tv_key3.setText(mark_name);
                        ll_key3.setText(mark_name);
                        ll_key3.setTextColor(Y.getColor(R.color.color_main));
                        ll_key3.setEnabled(true);
                    } else {
                        ll_key3.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "14")) {
                    if (mark_status.equals("1")) {
                        tv_key4.setText(mark_name);
                        ll_key4.setText(mark_name);
                        ll_key4.setTextColor(Y.getColor(R.color.color_main));
                        ll_key4.setEnabled(true);
                    } else {
                        ll_key4.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "15")) {
                    if (mark_status.equals("1")) {
                        tv_key5.setText(mark_name);
                        ll_key5.setText(mark_name);
                        ll_key5.setTextColor(Y.getColor(R.color.color_main));
                        ll_key5.setEnabled(true);
                    } else {
                        ll_key5.setEnabled(false);
                    }
                } else if (mark_id.equals(shebeima + "16")) {
                    if (mark_status.equals("1")) {
                        tv_key6.setText(mark_name);
                        ll_key6.setText(mark_name);
                        ll_key6.setTextColor(Y.getColor(R.color.color_main));
                        ll_key6.setEnabled(true);
                    } else {
                        ll_key6.setEnabled(false);
                    }
                }
            }
        }
    }

    @OnClick({R.id.ll_key1, R.id.ll_key2, R.id.ll_key3, R.id.ll_key4, R.id.ll_key5, R.id.ll_key6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_key1:
                if (shebeiMaTwo.equals("28")) {
                    sendMsg("14");
                } else if (shebeiMaTwo.equals("37")) {
                    sendMsg("08");
                } else if (shebeiMaTwo.equals("38")) {
                    sendMsg("11");
                }
                break;
            case R.id.ll_key2:
                if (shebeiMaTwo.equals("28")) {
                    sendMsg("15");
                } else if (shebeiMaTwo.equals("38")) {
                    sendMsg("12");
                } else {
                    sendMsg("09");
                }
                break;
            case R.id.ll_key3:
                if (shebeiMaTwo.equals("28")) {
                    sendMsg("16");
                } else if (shebeiMaTwo.equals("38")) {
                    sendMsg("13");
                } else {
                    sendMsg("10");
                }
                break;
            case R.id.ll_key4:
                if (shebeiMaTwo.equals("28")) {
                    sendMsg("17");
                } else if (shebeiMaTwo.equals("38")) {
                    sendMsg("14");
                } else {
                    sendMsg("11");
                }
                break;
            case R.id.ll_key5:
                if (shebeiMaTwo.equals("28")) {
                    sendMsg("18");
                } else if (shebeiMaTwo.equals("38")) {
                    sendMsg("15");
                } else {
                    sendMsg("12");
                }
                break;
            case R.id.ll_key6:
                if (shebeiMaTwo.equals("28")) {
                    sendMsg("19");
                } else if (shebeiMaTwo.equals("38")) {
                    sendMsg("16");
                } else {
                    sendMsg("13");
                }
                break;
        }
    }

    private void sendMsg(String code) {
        String mingLingMa = "M18" + shebeima + code + ".";
        znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }
}
