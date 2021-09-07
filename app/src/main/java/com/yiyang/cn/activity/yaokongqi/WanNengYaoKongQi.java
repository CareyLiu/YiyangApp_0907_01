package com.yiyang.cn.activity.yaokongqi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;


public class WanNengYaoKongQi extends BaseActivity {


    @BindView(R.id.ll_zidingyi)
    TextView ll_zidingyi;
    @BindView(R.id.iv_dianyuan)
    ImageView iv_dianyuan;
    @BindView(R.id.ll_dianyuan)
    LinearLayout ll_dianyuan;
    @BindView(R.id.iv_jingyin)
    ImageView iv_jingyin;
    @BindView(R.id.ll_jingyin)
    LinearLayout ll_jingyin;
    @BindView(R.id.iv_yinliang_add)
    ImageView iv_yinliang_add;
    @BindView(R.id.ll_yinliang_add)
    LinearLayout ll_yinliang_add;
    @BindView(R.id.iv_yinliang_jian)
    ImageView iv_yinliang_jian;
    @BindView(R.id.ll_yinliang_jian)
    LinearLayout ll_yinliang_jian;
    @BindView(R.id.tv_pindao_add)
    ImageView tv_pindao_add;
    @BindView(R.id.ll_pindao_add)
    LinearLayout ll_pindao_add;
    @BindView(R.id.tv_pindao_jian)
    ImageView tv_pindao_jian;
    @BindView(R.id.ll_pindao_jian)
    LinearLayout ll_pindao_jian;
    @BindView(R.id.iv_caidan)
    ImageView iv_caidan;
    @BindView(R.id.ll_caidan)
    LinearLayout ll_caidan;
    @BindView(R.id.tv_shuchu)
    TextView tv_shuchu;
    @BindView(R.id.ll_shuchu)
    LinearLayout ll_shuchu;
    @BindView(R.id.bt_up)
    TextView bt_up;
    @BindView(R.id.bt_down)
    TextView bt_down;
    @BindView(R.id.bt_left)
    TextView bt_left;
    @BindView(R.id.bt_right)
    TextView bt_right;
    @BindView(R.id.bt_ok)
    TextView bt_ok;
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
        return R.layout.layout_wanneng_yaokongqi_peidui_dianshi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("电视遥控器");
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
        WanNengYaoKongQiSet.actionStart(mContext,device_id, member_type);
    }

    public static void actionStart(Context context, String device_id,String member_type) {
        Intent intent = new Intent(context, WanNengYaoKongQi.class);
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
        ll_jingyin.setEnabled(false);
        ll_dianyuan.setEnabled(false);
        ll_yinliang_add.setEnabled(false);
        ll_yinliang_jian.setEnabled(false);
        ll_caidan.setEnabled(false);
        ll_shuchu.setEnabled(false);
        ll_pindao_add.setEnabled(false);
        ll_pindao_jian.setEnabled(false);
        bt_up.setEnabled(false);
        bt_down.setEnabled(false);
        bt_left.setEnabled(false);
        bt_right.setEnabled(false);
        bt_ok.setEnabled(false);
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
                    iv_jingyin.setImageResource(R.mipmap.yaokong_icon_jingyin_blue);
                    ll_jingyin.setEnabled(true);
                } else {
                    ll_jingyin.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "02")) {
                if (mark_status.equals("1")) {
                    iv_dianyuan.setImageResource(R.mipmap.yaokong_icon_guanbi_blue);
                    ll_dianyuan.setEnabled(true);
                } else {
                    ll_dianyuan.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "03")) {
                if (mark_status.equals("1")) {
                    iv_yinliang_add.setImageResource(R.mipmap.yaokong_icon_add_blue);
                    ll_yinliang_add.setEnabled(true);
                } else {
                    ll_yinliang_add.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "04")) {
                if (mark_status.equals("1")) {
                    iv_yinliang_jian.setImageResource(R.mipmap.yaokong_icon_reduce_blue);
                    ll_yinliang_jian.setEnabled(true);
                } else {
                    ll_yinliang_jian.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "05")) {
                if (mark_status.equals("1")) {
                    iv_caidan.setImageResource(R.mipmap.yaokong_icon_menu_blue);
                    ll_caidan.setEnabled(true);
                } else {
                    ll_caidan.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "06")) {
                if (mark_status.equals("1")) {
                    tv_shuchu.setTextColor(Y.getColor(R.color.color_main));
                    ll_shuchu.setEnabled(true);
                } else {
                    ll_shuchu.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "07")) {
                if (mark_status.equals("1")) {
                    tv_pindao_add.setImageResource(R.mipmap.yaokong_icon_add_blue);
                    ll_pindao_add.setEnabled(true);
                } else {
                    ll_pindao_add.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "08")) {
                if (mark_status.equals("1")) {
                    tv_pindao_jian.setImageResource(R.mipmap.yaokong_icon_reduce_blue);
                    ll_pindao_jian.setEnabled(true);
                } else {
                    ll_pindao_jian.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "09")) {
                if (mark_status.equals("1")) {
                    bt_up.setTextColor(Y.getColor(R.color.color_main));
                    bt_up.setEnabled(true);
                } else {
                    bt_up.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "10")) {
                if (mark_status.equals("1")) {
                    bt_down.setTextColor(Y.getColor(R.color.color_main));
                    bt_down.setEnabled(true);
                } else {
                    bt_down.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "11")) {
                if (mark_status.equals("1")) {
                    bt_left.setTextColor(Y.getColor(R.color.color_main));
                    bt_left.setEnabled(true);
                } else {
                    bt_left.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "12")) {
                if (mark_status.equals("1")) {
                    bt_right.setTextColor(Y.getColor(R.color.color_main));
                    bt_right.setEnabled(true);
                } else {
                    bt_right.setEnabled(false);
                }
            } else if (mark_id.equals(label_header + "13")) {
                if (mark_status.equals("1")) {
                    bt_ok.setTextColor(Y.getColor(R.color.color_main));
                    bt_ok.setEnabled(true);
                } else {
                    bt_ok.setEnabled(false);
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

    @OnClick({R.id.ll_zidingyi, R.id.ll_jingyin, R.id.ll_dianyuan, R.id.ll_yinliang_add, R.id.ll_yinliang_jian, R.id.ll_pindao_add, R.id.ll_pindao_jian, R.id.ll_caidan, R.id.ll_shuchu, R.id.bt_up, R.id.bt_down, R.id.bt_left, R.id.bt_right, R.id.bt_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_dianyuan:
                sendMsg(1);
                break;
            case R.id.ll_yinliang_add:
                sendMsg(2);
                break;
            case R.id.ll_yinliang_jian:
                sendMsg(3);
                break;
            case R.id.ll_pindao_add:
                sendMsg(6);
                break;
            case R.id.ll_pindao_jian:
                sendMsg(7);
                break;
            case R.id.ll_caidan:
                sendMsg(4);
                break;
            case R.id.ll_shuchu:
                sendMsg(5);
                break;
            case R.id.ll_jingyin:
                sendMsg(0);
                break;
            case R.id.bt_up:
                sendMsg(8);
                break;
            case R.id.bt_down:
                sendMsg(9);
                break;
            case R.id.bt_left:
                sendMsg(10);
                break;
            case R.id.bt_right:
                sendMsg(11);
                break;
            case R.id.bt_ok:
                sendMsg(12);
                break;
            case R.id.ll_zidingyi:
                WanNengYaoKongQiZidingyi.actionStart(mContext, device_id, label_header, control_keys_list);
                break;
        }
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
}
