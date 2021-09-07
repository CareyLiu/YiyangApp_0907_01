package com.yiyang.cn.activity.shuinuan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.yiyang.cn.R;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ShuinuanZhuangtaiActivity extends ShuinuanBaseNewActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_shebeima)
    TextView tvShebeima;
    @BindView(R.id.tv_zhuangtai)
    TextView tvZhuangtai;
    @BindView(R.id.tv_zhuangtai_shuibeng)
    TextView tvZhuangtaiShuibeng;
    @BindView(R.id.tv_zhuangtai_youbeng)
    TextView tvZhuangtaiYoubeng;
    @BindView(R.id.tv_zhuangtai_fengji)
    TextView tvZhuangtaiFengji;
    @BindView(R.id.tv_dianya)
    TextView tvDianya;
    @BindView(R.id.tv_fengjizhuansu)
    TextView tvFengjizhuansu;
    @BindView(R.id.tv_jiaresaigonglv)
    TextView tvJiaresaigonglv;
    @BindView(R.id.tv_youbengpinlv)
    TextView tvYoubengpinlv;
    @BindView(R.id.tv_rushukouwendu)
    TextView tvRushukouwendu;
    @BindView(R.id.tv_chushuikouwendu)
    TextView tvChushuikouwendu;
    @BindView(R.id.tv_weiqiwendu)
    TextView tvWeiqiwendu;
    @BindView(R.id.tv_dangwei)
    TextView tvDangwei;
    @BindView(R.id.tv_yushewendu)
    TextView tvYushewendu;
    @BindView(R.id.tv_gongzuoshichang)
    TextView tvGongzuoshichang;
    @BindView(R.id.tv_daqiya)
    TextView tvDaqiya;
    @BindView(R.id.tv_haibagaodu)
    TextView tvHaibagaodu;
    @BindView(R.id.tv_hanyangliang)
    TextView tvHanyangliang;
    @BindView(R.id.tv_xinhao)
    TextView tvXinhao;

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_zhuangtai;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShuinuanZhuangtaiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvShebeima.setText(ccid);
        initHuidiao();
        registerKtMqtt();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_SN_DATA) {
                    String msg = message.content.toString();
                    getData(msg);
                }
            }
        }));
    }

    @SuppressLint("SetTextI18n")
    private void getData(String msg) {
        if (msg.contains("j_s")) {
            String sn_state = msg.substring(3, 4);//水暖状态
            String syscTime = msg.substring(4, 7);//加热剩余时长
            String shuibeng_state = msg.substring(7, 8);//水泵状态  1.工作中2.待机中
            String youbeng_state = msg.substring(8, 9);//油泵状态  1.工作中2.待机中
            String fengji_state = msg.substring(9, 10);//风机状态  1.工作中2.待机中
            String dianyan = (Y.getInt(msg.substring(10, 14)) / 10.0f) + "";//电压  0253 = 25.3
            String fengjizhuansu = Y.getInt(msg.substring(14, 19)) + "";//风机转速   13245
            String jairesaigonglv = (Y.getInt(msg.substring(19, 23)) / 10.0f) + "";// 加热塞功率  0264=26.4
            String youbenggonglv = (Y.getInt(msg.substring(23, 27)) / 10.0f) + "";// 油泵频率  0264=26.4

            int rushukowenduInt = (Y.getInt(msg.substring(27, 30)) - 50);
            if (rushukowenduInt <= 0) {
                rushukowenduInt = 0;
            }
            String rushukowendu = rushukowenduInt + "";// 入水口温度（℃）  -50至150（000 = -50，100 = 50）

            int chushuikowenduInt = (Y.getInt(msg.substring(30, 33)) - 50);
            if (chushuikowenduInt <= 0) {
                chushuikowenduInt = 0;
            }
            String chushuikowendu = chushuikowenduInt + "";// 出水口温度（℃）  -50至150（000 = -50，100 = 50）

            int weiqiwenduInt = (Y.getInt(msg.substring(33, 37)) - 100);
            if (weiqiwenduInt <= 0) {
                weiqiwenduInt = 0;
            }
            String weiqiwendu = weiqiwenduInt + "";// 尾气温度（℃）  -50至2000（000 = -50，100 = 50）

            String danqiandangwei = msg.substring(37, 38);// 1.一档2.二档（注：用*占位）
            String yushewendu = msg.substring(38, 40);//预设温度（℃） 预设温度（℃）
            String zongTime = Y.getInt(msg.substring(40, 45)) + "";//总时长 （小时）
            String daqiya = Y.getInt(msg.substring(45, 48)) + "";//大气压
            String haibagaodu = Y.getInt(msg.substring(48, 52)) + "";//海拔高度
            String hanyangliang = Y.getInt(msg.substring(52, 55)) + "";//含氧量
            String xinhaoStr = msg.substring(55, 57);

            int xinhao;
            if (xinhaoStr.contains("a")) {
                xinhao = 22;
            } else {
                xinhao = Y.getInt(xinhaoStr);//信号强度
            }

            String num = "水暖状态" + sn_state + "  加热剩余时长" + syscTime + "  水泵状态" + shuibeng_state + "  油泵状态" + youbeng_state
                    + "  风机状态" + fengji_state
                    + "  电压" + dianyan
                    + "  风机转速" + fengjizhuansu
                    + "  加热塞功率" + jairesaigonglv
                    + "  油泵频率" + youbenggonglv
                    + "    入水口温度" + rushukowendu
                    + "    出水口温度" + chushuikowendu
                    + "    尾气温度" + weiqiwendu
                    + "    尾气温度" + weiqiwendu
                    + "  一档二挡" + danqiandangwei
                    + "  总时长" + zongTime + "   大气压" + daqiya + "    海拔高度" + haibagaodu + "  含氧量" + hanyangliang
                    + "  信号强度" + xinhao;
            Y.e(num);

            tvXinhao.setText(xinhao + "");

            switch (sn_state) {
                case "1"://开机中
                case "2"://加热中
                case "4"://循环水
                    tvZhuangtai.setText("开机");
                    break;
                case "0"://待机中
                case "3"://关机中
                    tvZhuangtai.setText("关机");
                    break;
            }

            switch (shuibeng_state) {
                case "1":
                    tvZhuangtaiShuibeng.setText("工作中");
                    break;
                case "2":
                    tvZhuangtaiShuibeng.setText("待机中");
                    break;
            }

            switch (youbeng_state) {
                case "1":
                    tvZhuangtaiYoubeng.setText("工作中");
                    break;
                case "2":
                    tvZhuangtaiYoubeng.setText("待机中");
                    break;
            }

            switch (fengji_state) {
                case "1":
                    tvZhuangtaiFengji.setText("工作中");
                    break;
                case "2":
                    tvZhuangtaiFengji.setText("待机中");
                    break;
            }

            tvDianya.setText(dianyan + "v");
            tvFengjizhuansu.setText(fengjizhuansu);
            tvJiaresaigonglv.setText(jairesaigonglv);
            tvYoubengpinlv.setText(youbenggonglv);
            tvRushukouwendu.setText(rushukowendu + "℃");
            tvChushuikouwendu.setText(chushuikowendu + "℃");
            tvWeiqiwendu.setText(weiqiwendu + "℃");

            switch (danqiandangwei) {
                case "1":
                    tvDangwei.setText("1档");
                    break;
                case "2":
                    tvDangwei.setText("2档");
                    break;
                default:
                    tvDangwei.setText("暂无档位");
                    break;
            }

            tvYushewendu.setText(yushewendu + "℃");
            tvGongzuoshichang.setText(zongTime + "h");
            tvDaqiya.setText(daqiya + "kpa");
            tvHaibagaodu.setText(haibagaodu + "m");
            tvHanyangliang.setText(hanyangliang + "kg/cm3");
            handlerStart.removeMessages(1);
        }
    }


    /**
     * 注册订阅Mqtt
     */
    private void registerKtMqtt() {
        initHandlerStart();
        getNs();
    }

    private void initHandlerStart() {
        Message message = handlerStart.obtainMessage(1);
        handlerStart.sendMessageDelayed(message, 250);
    }

    private void getNs() {
        //注册水暖加热器订阅
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Send)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Y.i("我订阅了" + SN_Send);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        //模拟水暖加热器订阅app
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Accept)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Y.i("我订阅了" + SN_Accept);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        //向水暖加热器发送获取实时数据
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("N_s.")
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("app端向水暖加热器请求实时数据", "");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    private int time = 0;

    private Handler handlerStart = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                time++;
                if (time >= 20) {

                } else {
                    if (time == 5 || time == 10 || time == 15) {
                        getNs();
                    }
                    initHandlerStart();
                }
            }
            return false;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerStart.removeMessages(1);
    }
}
