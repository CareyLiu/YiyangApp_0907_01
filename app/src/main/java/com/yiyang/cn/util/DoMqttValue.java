package com.yiyang.cn.util;

import android.content.Context;
import android.util.Log;

import com.iflytek.cloud.Setting;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.ConfigValue;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.config.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.internal.ListenerClass;
import io.reactivex.exceptions.MissingBackpressureException;

public class DoMqttValue {

    public Context context;//上下文
    public String message;//消息


    public static final String ZHINENGJIAJU = "ZHINENGJIAJU";
    public static final String FENGNUAN = "FENGNUAN";
    public static final String SHUINUAN = "SHUINUAN";
    public static final String KONGTIAO = "KONGTIAO";
    public static final String KONGCHE = "KONGCHE";
    List<String> stringList;

    public void doValue(Context context, String topic, String message) {
        //控制硬件项目  1. 智能家居 2.风暖加热器 3.水暖加热器 4.驻车空调 5.神灯控车
        String chooseXiangMu = PreferenceHelper.getInstance(context).getString(App.CHOOSE_KONGZHI_XIANGMU, "0");
        Log.i("chooseXiangMu", chooseXiangMu);
        String zhiLingMa = PreferenceHelper.getInstance(context).getString(ConfigValue.ZHILINGMA, "");
        Log.i("zhiLingMa", zhiLingMa);

        switch (chooseXiangMu) {
            case ZHINENGJIAJU:
                //  收到的数据信息：  i01082.
//                UIHelper.ToastMessage(context, "接收到的message信息： " + message);
//                if (message.charAt(0) == 'q') {
//                    String zhuangZhiId = message.substring(1, 9);
//                    String kaiGuanDengZhuangTai = message.substring(9, 10);
//
//                    stringList = new ArrayList<>();
//                    stringList.add(zhuangZhiId);
//                    stringList.add(kaiGuanDengZhuangTai);
//
//                    Notice notice = new Notice();
//                    notice.type = ConstanceValue.MSG_SHEBEIZHUANGTAI;
//                    notice.content = stringList;
//                    Log.i("Rair", notice.content.toString());
//                    RxBus.getDefault().sendRx(notice);
//
//                } else
//

                    if (message.charAt(0) == 'i') {

                    if (message.contains("_")) {

                        String[] messageSplit = message.split("_");

//                        for (int i = 0; i < messageSplit.length; i++) {
//
//                            String zhuangZhiId = messageSplit[i].substring(1, 9);
//                            String kaiGuanDengZhuangTai = messageSplit[i].substring(9, 10);
//
//                            stringList = new ArrayList<>();
//                            stringList.add(zhuangZhiId);
//                            stringList.add(kaiGuanDengZhuangTai);
//
//
//                        }
//
//                        Notice notice = new Notice();
//                        notice.type = ConstanceValue.MSG_SHEBEIZHUANGTAI;
//                        notice.content = stringList;
//                        Log.i("Rair", notice.content.toString());
//                        RxBus.getDefault().sendRx(notice);


                        Notice notice1 = new Notice();
                        notice1.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                        RxBus.getDefault().sendRx(notice1);


                    } else {
                        String zhuangZhiId = message.substring(1, 9);
                        String kaiGuanDengZhuangTai = message.substring(9, 10);

                        List<String> stringList = new ArrayList<>();
                        stringList.add(zhuangZhiId);
                        stringList.add(kaiGuanDengZhuangTai);

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                Notice notice = new Notice();
                                notice.type = ConstanceValue.MSG_SHEBEIZHUANGTAI;
                                notice.content = stringList;
                                Log.i("Rair", notice.content.toString());
                                RxBus.getDefault().sendRx(notice);

//                                Notice notice1 = new Notice();
//                                notice1.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
//                                RxBus.getDefault().sendRx(notice1);
//                                Log.i("Rair", "发送了一次");

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                    }

                } else if (message.contains("m0528")) {
                    Y.t(message);
                    String str = message.substring(message.length() - 3, message.length() - 1);
                    //遥控器配对
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQIPEIWANG;
                    notice.content = str;
                    Log.i("Rair", notice.content.toString());
                    RxBus.getDefault().sendRx(notice);
                } else if (message.contains("m0628")) {

                } else if (message.contains("m07")) {//电视配对类型
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI;
                    notice.content = message;
                    RxBus.getDefault().sendRx(notice);
                } else if (message.contains("m08")) {
                    //接受到主机修改信息成功
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_ZHUJIXIUGAIXINXI;
                    notice.content = message;
                    RxBus.getDefault().sendRx(notice);

                }
                break;
            case FENGNUAN:
                if (message.contains("_")) {
                    String messageData = message.substring(2, message.length() - 1);
                    String[] arr = messageData.split("_");

                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].contains("g")) {
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_J_G;
                            n.content = arr[i];
                            Log.i("g--n.content", n.content.toString());
                            RxBus.getDefault().sendRx(n);

                        } else if (arr[i].contains("M")) {
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_J_M;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_J_M", n.content.toString());

                        } else if (arr[i].contains("h")) {
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_h;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_J_M", n.content.toString());

                        } else if (arr[i].contains("l")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_l;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_l", n.content.toString());

                        } else if (arr[i].contains("m")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_l;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_l", n.content.toString());
                        } else if (arr[i].contains("n")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_n;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_n", n.content.toString());
                        } else if (arr[i].contains("p")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_p;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_p", n.content.toString());
                        } else if (arr[i].contains("r")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_r;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_r", n.content.toString());

                        } else if (arr[i].contains("s")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_s;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_s", n.content.toString());

                        } else if (arr[i].contains("Z")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_Z;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_Z", n.content.toString());

                        } else if (message.toString().contains("k")) {
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_K;
                            n.content = message.toString();
                            RxBus.getDefault().sendRx(n);

                        } else if (message.toString().contains("i")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_I;
                            n.content = message.toString();
                            RxBus.getDefault().sendRx(n);
                        }
                    }

                } else if (message.toString().equals("M691.")) { //清除故障成功
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CLEARGUZHANGSUCCESS;
                    n.content = message.toString();
                    RxBus.getDefault().sendRx(n);
                } else if (message.toString().contains("i")) {

//                            Notice n = new Notice();
//                            n.type = ConstanceValue.MSG_CAR_I;
//                            n.content = message.toString();
//                            RxBus.getDefault().sendRx(n);

                } else if (message.toString().equals("k5011.")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_HUI_FU_CHU_CHAGN;
                    RxBus.getDefault().sendRx(n);

                } else if (message.toString().contains("h")) {//h是风油比
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_FEGNYOUBI;
                    n.content = message.toString();
                    RxBus.getDefault().sendRx(n);
                } else if (message.toString().equals("M001.")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_J_G;
//                            n.content = message.toString();
                    n.content = "g0011108122015500026-02500041";
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6111.")) {//档位模式
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6111;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6131.")) {//关机
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6131;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6121.")) {//空调模式
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6121;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6141.")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6141;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6161.")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6161;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6171.")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6171;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals(zhiLingMa)) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_ZHILINGMA;
                    RxBus.getDefault().sendRx(n);
                }
                break;
            case SHUINUAN:
                shuiNuan(topic, message);
                break;
            case KONGTIAO:
                kongtiao(topic, message);
                break;
            case KONGCHE:
                break;
        }
    }

    private void kongtiao(String topic, String message) {
        if (topic.contains("zckt")) {
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_ZCKT;
            n.content = message.toString();
            RxBus.getDefault().sendRx(n);
        }
    }


    //水暖相关代码
    private void shuiNuan(String topic, String message) {
        if (topic.contains("wh/app") || topic.contains("wh/hardware/")) {
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_SN_DATA;
            n.content = message.toString();
            RxBus.getDefault().sendRx(n);
        }
    }
}
