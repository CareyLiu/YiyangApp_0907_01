package com.yiyang.cn.activity.yaokongqi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.yaokongqi.model.YaokongDetailsModel;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class ZhenWanNengYaoKongQiKongZhi extends BaseActivity {


    @BindView(R.id.ll_key1)
    TextView llKey1;
    @BindView(R.id.tv_key1)
    TextView tvKey1;
    @BindView(R.id.ll_key2)
    TextView llKey2;
    @BindView(R.id.tv_key2)
    TextView tvKey2;
    @BindView(R.id.ll_key3)
    TextView llKey3;
    @BindView(R.id.tv_key3)
    TextView tvKey3;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.ll_key4)
    TextView llKey4;
    @BindView(R.id.tv_key4)
    TextView tvKey4;
    @BindView(R.id.ll_key5)
    TextView llKey5;
    @BindView(R.id.tv_key5)
    TextView tvKey5;
    @BindView(R.id.ll_key6)
    TextView llKey6;
    @BindView(R.id.tv_key6)
    TextView tvKey6;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.ll_key7)
    TextView llKey7;
    @BindView(R.id.tv_key7)
    TextView tvKey7;
    @BindView(R.id.ll_key8)
    TextView llKey8;
    @BindView(R.id.tv_key8)
    TextView tvKey8;
    @BindView(R.id.ll_key9)
    TextView llKey9;
    @BindView(R.id.tv_key9)
    TextView tvKey9;
    private String device_id;
    private String ccid;
    private String serverId;
    private ZnjjMqttMingLing znjjMqttMingLing;
    private String topic;

    private YaokongDetailsModel.DataBean dataBean;
    private String label_header;
    private List<YaokongDetailsModel.DataBean.ControlKeysListBean> control_keys_list;
    private String member_type;

    @Override
    public int getContentViewResId() {
        return R.layout.layout_zhen_wanneng_yaokongqi_peidui_zidingyi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("万能遥控器");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("编辑");
        tv_rightTitle.setTextSize(17);
        tv_rightTitle.setTextColor(Y.getColor(R.color.color_main));
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set();
            }
        });

        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void set() {
        WanNengYaoKongQiSet.actionStart(mContext, device_id, member_type);
    }

    public static void actionStart(Context context, String device_id, String member_type) {
        Intent intent = new Intent(context, ZhenWanNengYaoKongQiKongZhi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_id", device_id);
        intent.putExtra("member_type", member_type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initMqtt();
        initHuidiao();
        getNet();
    }

    private void initView() {
        llKey1.setEnabled(false);
        llKey2.setEnabled(false);
        llKey3.setEnabled(false);
        llKey4.setEnabled(false);
        llKey5.setEnabled(false);
        llKey6.setEnabled(false);
        llKey7.setEnabled(false);
        llKey8.setEnabled(false);
        llKey9.setEnabled(false);
    }

    private void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16035");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<YaokongDetailsModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<YaokongDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<YaokongDetailsModel.DataBean>> response) {
                        dataBean = response.body().data.get(0);
                        label_header = dataBean.getLabel_header();
                        control_keys_list = dataBean.getControl_keys_list();

                        initKey();
                    }
                });
    }

    private void initKey() {
        for (int i = 0; i < control_keys_list.size(); i++) {
            YaokongDetailsModel.DataBean.ControlKeysListBean listBean = control_keys_list.get(i);
            String mark_id = listBean.getMark_id();
            String mark_name = listBean.getMark_name();
            String mark_status = listBean.getMark_status();
            if (mark_id.equals(label_header + "01")) {
                if (mark_status.equals("1")) {
                    llKey1.setTextColor(Y.getColor(R.color.color_main));
                    llKey1.setText(mark_name);
                    tvKey1.setText(mark_name);
                    llKey1.setEnabled(true);
                } else {
                    llKey1.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "02")) {
                if (mark_status.equals("1")) {
                    llKey2.setTextColor(Y.getColor(R.color.color_main));
                    llKey2.setText(mark_name);
                    tvKey2.setText(mark_name);
                    llKey2.setEnabled(true);
                } else {
                    llKey2.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "03")) {
                if (mark_status.equals("1")) {
                    llKey3.setTextColor(Y.getColor(R.color.color_main));
                    llKey3.setText(mark_name);
                    tvKey3.setText(mark_name);
                    llKey3.setEnabled(true);
                } else {
                    llKey3.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "04")) {
                if (mark_status.equals("1")) {
                    llKey4.setTextColor(Y.getColor(R.color.color_main));
                    llKey4.setText(mark_name);
                    tvKey4.setText(mark_name);
                    llKey4.setEnabled(true);
                } else {
                    llKey4.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "05")) {
                if (mark_status.equals("1")) {
                    llKey5.setTextColor(Y.getColor(R.color.color_main));
                    llKey5.setText(mark_name);
                    tvKey5.setText(mark_name);
                    llKey5.setEnabled(true);
                } else {
                    llKey5.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "06")) {
                if (mark_status.equals("1")) {
                    llKey6.setTextColor(Y.getColor(R.color.color_main));
                    llKey6.setText(mark_name);
                    tvKey6.setText(mark_name);
                    llKey6.setEnabled(true);
                } else {
                    llKey6.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "07")) {
                if (mark_status.equals("1")) {
                    llKey7.setTextColor(Y.getColor(R.color.color_main));
                    llKey7.setText(mark_name);
                    tvKey7.setText(mark_name);
                    llKey7.setEnabled(true);
                } else {
                    llKey7.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "08")) {
                if (mark_status.equals("1")) {
                    llKey8.setTextColor(Y.getColor(R.color.color_main));
                    llKey8.setText(mark_name);
                    tvKey8.setText(mark_name);
                    llKey8.setEnabled(true);
                } else {
                    llKey8.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "09")) {
                if (mark_status.equals("1")) {
                    llKey9.setTextColor(Y.getColor(R.color.color_main));
                    llKey9.setText(mark_name);
                    tvKey9.setText(mark_name);
                    llKey9.setEnabled(true);
                } else {
                    llKey9.setEnabled(false);
                }
            }
        }
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_DELETE) {
                    finish();
                }
            }
        }));
    }

    private void initMqtt() {
        device_id = getIntent().getStringExtra("device_id");
        member_type = getIntent().getStringExtra("member_type");
        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
        topic = "zn/hardware/" + serverId + ccid;
        znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(ccid, serverId, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        znjjMqttMingLing.subscribeServerShiShiXinXi_WithCanShu(ccid, serverId, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    private void sendMsg(int pos) {
        YaokongDetailsModel.DataBean.ControlKeysListBean bean = control_keys_list.get(pos);
        String mingLingMa = "M18" + bean.getMark_id() + ".";
        znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    @OnClick({R.id.ll_key1, R.id.ll_key2, R.id.ll_key3, R.id.ll_key4, R.id.ll_key5, R.id.ll_key6, R.id.ll_key7, R.id.ll_key8, R.id.ll_key9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_key1:
                sendMsg(0);
                break;
            case R.id.ll_key2:
                sendMsg(1);
                break;
            case R.id.ll_key3:
                sendMsg(2);
                break;
            case R.id.ll_key4:
                sendMsg(3);
                break;
            case R.id.ll_key5:
                sendMsg(4);
                break;
            case R.id.ll_key6:
                sendMsg(5);
                break;
            case R.id.ll_key7:
                sendMsg(6);
                break;
            case R.id.ll_key8:
                sendMsg(7);
                break;
            case R.id.ll_key9:
                sendMsg(8);
                break;

        }
    }
}
