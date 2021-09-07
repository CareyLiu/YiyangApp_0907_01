package com.yiyang.cn.activity.yaokongqi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class KongQiJingHuaKongZhiActivity extends BaseActivity {

    @BindView(R.id.iv_dianyuan)
    ImageView ivDianyuan;
    @BindView(R.id.rl_dianyuan)
    RelativeLayout rlDianyuan;
    @BindView(R.id.iv_zidong)
    ImageView ivZidong;
    @BindView(R.id.rl_zidong)
    RelativeLayout rlZidong;
    @BindView(R.id.iv_moshi)
    ImageView ivMoshi;
    @BindView(R.id.rl_moshi)
    RelativeLayout rlMoshi;
    @BindView(R.id.iv_dingshi)
    ImageView ivDingshi;
    @BindView(R.id.rl_dingshi)
    RelativeLayout rlDingshi;
    @BindView(R.id.iv_fengsu)
    ImageView ivFengsu;
    @BindView(R.id.rl_fengsu)
    RelativeLayout rlFengsu;
    @BindView(R.id.iv_shuimian)
    ImageView ivShuimian;
    @BindView(R.id.rl_shuimian)
    RelativeLayout rlShuimian;
    @BindView(R.id.iv_tongsuo)
    ImageView ivTongsuo;
    @BindView(R.id.rl_tongsuo)
    RelativeLayout rlTongsuo;
    @BindView(R.id.iv_zidingyi)
    TextView ivZidingyi;
    @BindView(R.id.rl_zidingyi)
    RelativeLayout rlZidingyi;
    @BindView(R.id.iv_jinghua)
    ImageView ivJinghua;
    @BindView(R.id.rl_jinghua)
    RelativeLayout rlJinghua;
    @BindView(R.id.iv_chuyan)
    ImageView ivChuyan;
    @BindView(R.id.rl_chuyan)
    RelativeLayout rlChuyan;
    @BindView(R.id.iv_fulizi)
    ImageView ivFulizi;
    @BindView(R.id.rl_fulizi)
    RelativeLayout rlFulizi;
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
        return R.layout.layout_kongqijinghua;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("空气净化");
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
        Intent intent = new Intent(context, KongQiJingHuaKongZhiActivity.class);
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
        rlDianyuan.setEnabled(false);
        rlZidong.setEnabled(false);
        rlMoshi.setEnabled(false);
        rlDingshi.setEnabled(false);
        rlFengsu.setEnabled(false);
        rlShuimian.setEnabled(false);
        rlTongsuo.setEnabled(false);

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
                    ivDianyuan.setImageResource(R.mipmap.yaokong_icon_guanbi_bl);
                    rlDianyuan.setEnabled(true);
                } else {
                    rlDianyuan.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "02")) {
                if (mark_status.equals("1")) {
                    ivZidong.setImageResource(R.mipmap.yaokong_icon_zidong_black);
                    rlZidong.setEnabled(true);
                } else {
                    rlZidong.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "03")) {
                if (mark_status.equals("1")) {
                    ivMoshi.setImageResource(R.mipmap.jinghuaqi_icon_moshi_bl);
                    rlMoshi.setEnabled(true);
                } else {
                    rlMoshi.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "04")) {
                if (mark_status.equals("1")) {
                    ivDingshi.setImageResource(R.mipmap.jinghuaqi_icon_shijian_bl);
                    rlDingshi.setEnabled(true);
                } else {
                    rlDingshi.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "05")) {
                if (mark_status.equals("1")) {
                    ivFengsu.setImageResource(R.mipmap.jinghuaqi_icon_fengsu_bk);
                    rlFengsu.setEnabled(true);
                } else {
                    rlFengsu.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "06")) {
                if (mark_status.equals("1")) {
                    ivShuimian.setImageResource(R.mipmap.jinghuaqi_icon_shuimian_bk);
                    rlShuimian.setEnabled(true);
                } else {
                    rlShuimian.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "10")) {
                if (mark_status.equals("1")) {
                    ivTongsuo.setImageResource(R.mipmap.jinghuaqi_icon_tongsuo_bl_blue);
                    rlTongsuo.setEnabled(true);
                } else {
                    rlTongsuo.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "08")) {
                if (mark_status.equals("1")) {
                    ivJinghua.setImageResource(R.mipmap.jinghuaqi_icon_jinghua_bl);
                    rlTongsuo.setEnabled(true);
                } else {
                    rlTongsuo.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "07")) {
                if (mark_status.equals("1")) {
                    ivChuyan.setImageResource(R.mipmap.jinghuaqi_icon_yanwu_bl);
                    rlTongsuo.setEnabled(true);
                } else {
                    rlTongsuo.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "09")) {
                if (mark_status.equals("1")) {
                    ivFulizi.setImageResource(R.mipmap.jinghuaqi_icon_fulizi_bl);
                    rlTongsuo.setEnabled(true);
                } else {
                    rlTongsuo.setEnabled(false);
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

    @OnClick({R.id.rl_dianyuan, R.id.rl_zidong, R.id.rl_moshi, R.id.rl_dingshi, R.id.rl_fengsu, R.id.rl_shuimian, R.id.rl_tongsuo, R.id.rl_zidingyi, R.id.rl_jinghua, R.id.rl_chuyan, R.id.rl_fulizi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_dianyuan:
                sendMsg(0);
                break;
            case R.id.rl_zidong:
                sendMsg(1);
                break;
            case R.id.rl_moshi:
                sendMsg(2);
                break;
            case R.id.rl_dingshi:
                sendMsg(3);
                break;
            case R.id.rl_fengsu:
                sendMsg(4);
                break;
            case R.id.rl_shuimian:
                sendMsg(5);
                break;
            case R.id.rl_tongsuo:
                sendMsg(10);
                break;
            case R.id.rl_jinghua:
                sendMsg(7);
                break;
            case R.id.rl_chuyan:
                sendMsg(6);
                break;
            case R.id.rl_fulizi:
                sendMsg(8);
                break;
            case R.id.rl_zidingyi:
                WanNengYaoKongQiZidingyi.actionStart(mContext, device_id, label_header, control_keys_list);
                break;
        }
    }
}
