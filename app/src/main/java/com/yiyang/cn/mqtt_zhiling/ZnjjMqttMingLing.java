package com.yiyang.cn.mqtt_zhiling;

import android.content.Context;
import android.util.Log;

import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.config.MyApplication;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import static com.yiyang.cn.config.MyApplication.CAR_NOTIFY;

public class ZnjjMqttMingLing {
    private Context context;
    private String ccid;
    private String serverId;
    private String topic;
    private String shishiTopic;//实时数据的topic

    public ZnjjMqttMingLing(Context context, String ccid, String serverId) {
        this.context = context;
        this.ccid = ccid;
        this.serverId = serverId;

        topic = "zn/hardware/" + serverId + ccid;
        Log.i("Rair", topic);

        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(topic)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "订阅成功:" + topic);

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });
    }

    public ZnjjMqttMingLing(Context context) {
        this.context = context;
    }


    public void setTopic(String serverId, String ccid) {
        topic = "zn/hardware/" + serverId + ccid;
        this.serverId = serverId;
        this.ccid = ccid;
    }


    /**
     * 控制装置 M020301011*******.
     * M02 : 命令码
     * 0301：装置id，见装置ID命名规则
     * 01：打开；  02：关闭
     * 1.语音控制（需返回语音结果）2.app控制
     * *******：参数 （预留字段，如灯的颜色亮度等，目前用*占位）
     * 浇花或者喂鱼 都传1
     */

    /**
     * str 0 发送失败 1 发送成功
     *
     * @param zhuangZhiId   装置id
     * @param caoZuoFangShi 方式 打开或关闭
     * @return
     */
    public void setAction(String zhuangZhiId, String caoZuoFangShi, IMqttActionListener listener) {

        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }

        String zhiLing = "M02" + zhuangZhiId + caoZuoFangShi + "2" + ".";
        Log.i("Rair", "M02  行为指令  " + "装置id: " + zhuangZhiId + " 操作方式：" + caoZuoFangShi + " 控制方式: 2");
        Log.i("Rair", zhiLing);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }


    /**
     * 儿童模式 M09030101.（喂鱼、浇花装置使用，开启时物理按键失效）
     * M09 : 命令码
     * 0301：装置id
     * 01：开启；  02：关闭
     */
    /**
     * @param zhuangZhiId   装置id
     * @param caoZuoFangShi 方式 打开或关闭
     * @return
     */
    public void setErTongMoShi(String zhuangZhiId, String caoZuoFangShi, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        String zhiLing = "M09" + zhuangZhiId + caoZuoFangShi + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }

    /**
     * 解除报警M100301.
     * M10 : 命令码
     * 0301：装置id
     */

    /**
     * @param zhuangZhiId 装置id
     * @param listener
     */
    public void setJieChuJingBao(String zhuangZhiId, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        String zhiLing = "M10" + zhuangZhiId + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }


    /**
     * 由app/服务器 向主机发送，请求某装置的实时数据，通过i 返回数据
     * 参数	说明	长度	是否必填
     * 请求码	N	1	是
     * 装置id	0301	4	是
     * .	点:结束符号	1	是
     */

    public void getSheBeiShiShiData(String zhuangZhiId, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("N" + zhuangZhiId + ".")
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }


    /**
     * 由app 向主机发送
     * 参数	说明	长度	是否必填
     * 请求码	W	1	是
     * 装置id	0301	4	是
     * 儿童模式	1.开2.关（开启状态物理按键失效）	1	是
     * 自动模式状态	1.开启2.关闭	1	是
     * 注：设备类型根据装置id前两位判断，自动模式关闭用*占位
     * 浇花：
     * 1、2、3位（浇水时长，100=100秒，取值范围001-999）
     * 4、5、6位（浇水间隔，18h=18小时,02d=2天，取值范围01h-99h，01d-99d）	6	是
     */

    /**
     * @param zhuangZhiId   装置id
     * @param erTongMoShi   儿童模式 1.开2.关（开启状态物理按键失效）
     * @param ziDongMoShi   自动模式 1.开启2.关闭
     * @param kaiShiShiJian 开始时间 100秒
     * @param shiJianJianGe 时间间隔  18h
     * @param listener      监听
     */
    public void getJiaoHuaAction(String zhuangZhiId, String erTongMoShi, String ziDongMoShi, String kaiShiShiJian, String shiJianJianGe, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        if (kaiShiShiJian.length() != 3) {
            UIHelper.ToastMessage(context, "请输入三位_开始时间");
            return;
        }
        if (shiJianJianGe.length() != 3) {
            UIHelper.ToastMessage(context, "请输入三位_时间间隔");
            return;
        }
        if (shiJianJianGe.contains("天")) {
            kaiShiShiJian.replace("天", "d");
        }
        if (shiJianJianGe.contains("小时")) {
            kaiShiShiJian.replace("小时", "h");
        }
        String zhiLing = "W" + zhuangZhiId + erTongMoShi + ziDongMoShi + kaiShiShiJian + shiJianJianGe + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }


    /**
     * 由app 向主机发送
     * 参数	说明	长度	是否必填
     * 请求码	W	1	是
     * 装置id	0301	4	是
     * 儿童模式	1.开2.关（开启状态物理按键失效）	1	是
     * 自动模式状态	1.开启2.关闭	1	是
     * 注：设备类型根据装置id前两位判断，自动模式关闭用*占位
     * 喂鱼：
     * 1、2位（开始时间，HH，取值范围00-23）
     * 3、4位（时间间隔，单位：小时，取值范围01-99）
     * 5、6位 ** 补齐
     */

    /**
     * @param zhuangZhiId   装置id
     * @param erTongMoShi   儿童模式 1.开2.关（开启状态物理按键失效）
     * @param ziDongMoShi   自动模式 1.开启2.关闭
     * @param kaiShiShiJian 开始时间  0101**  56位补齐
     * @param shiJianJianGe 时间间隔  01h 或 01day
     * @param listener      监听
     */
    public void getWeiYuAction(String zhuangZhiId, String erTongMoShi, String ziDongMoShi, String kaiShiShiJian, String shiJianJianGe, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        if (kaiShiShiJian.length() != 2) {
            UIHelper.ToastMessage(context, "请输入两位_开始时间");
            return;
        }

        if (shiJianJianGe.length() != 2) {
            UIHelper.ToastMessage(context, "请输入两位_时间间隔");
            return;
        }
        String zhiLing = "W" + zhuangZhiId + erTongMoShi + ziDongMoShi + kaiShiShiJian + shiJianJianGe + "**" + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }

    /**
     * 通过订阅获得主机的实时信息
     *
     * @param listener
     */
    public void subscribeShiShiXinXi(IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        shishiTopic = "zn/hardware/" + serverId + "/" + ccid;
        Log.i("Rair", "订阅实时数据" + shishiTopic);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("N")
                .setQos(2)
                .setRetained(false)
                .setTopic(shishiTopic), listener);
//        AndMqtt.getInstance().subscribe(new MqttSubscribe()
//                .setTopic(shishiTopic)
//                .setQos(2), listener);
    }

    /**
     * 取消订阅主机的实时信息
     *
     * @param listener
     */
    public void unSubscribeShiShiXinXi(IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(shishiTopic)
                .setQos(2), listener);
    }

    /**
     * 取消订阅主机的实时信息
     *
     * @param listener
     */
    public void unSubscribeHardware(IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(topic)
                .setQos(2), listener);
    }

    /**
     * 控制装置 M020301011*******.
     * M02 : 命令码
     * 0301：装置id，见装置ID命名规则
     * 01：打开；  02：关闭
     * 1.语音控制（需返回语音结果）2.app控制
     * *******：参数 （预留字段，如灯的颜色亮度等，目前用*占位）
     * 浇花或者喂鱼 都传1
     */

    /**
     * str 0 发送失败 1 发送成功
     *
     * @param zhuangZhiId   装置id
     * @param caoZuoFangShi 方式 打开或关闭
     * @return
     */
    public void setWeiYuAction(String zhuangZhiId, String caoZuoFangShi, String quanshu, IMqttActionListener listener) {

        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }

        if (quanshu == null) {
            quanshu = "01";
        }
        String zhiLing = "M02" + zhuangZhiId + caoZuoFangShi + "2" + quanshu + "c" + ".";
        Log.i("Rair", "M02  行为指令  " + "装置id: " + zhuangZhiId + " 操作方式：" + caoZuoFangShi + " 控制方式: 2");
        Log.i("Rair", zhiLing);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }


    /**
     * app 通过订阅获得主机的实时信息
     *
     * @param listener
     */
    public void subscribeAppShiShiXinXi(IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        shishiTopic = "zn/app/" + serverId + ccid;
        Log.i("Rair", "订阅实时数据" + shishiTopic);
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(shishiTopic)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "订阅成功:" + topic);

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });
//        AndMqtt.getInstance().subscribe(new MqttSubscribe()
//                .setTopic(shishiTopic)
//                .setQos(2), listener);
    }

    /**
     * 取消订阅主机的实时信息
     *
     * @param listener
     */
    public void unSubscribeAppShiShiXinXi(IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(shishiTopic)
                .setQos(2), listener);
    }

    public void jieXiZhuangZhi(String str) {


    }

    /**
     * 通过订阅获得主机的实时信息
     *
     * @param listener
     */
    public void subscribeShiShiXinXi_WithCanShu(String ccid, String serverId, IMqttActionListener listener) {

        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        shishiTopic = "zn/hardware/" + serverId + "/" + ccid;
        Log.i("Rair", "订阅实时数据" + shishiTopic);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("N")
                .setQos(2)
                .setRetained(false)
                .setTopic(shishiTopic), listener);
//        AndMqtt.getInstance().subscribe(new MqttSubscribe()
//                .setTopic(shishiTopic)
//                .setQos(2), listener);
    }

    /**
     * 取消订阅主机的实时信息
     *
     * @param listener
     */
    public void unSubscribeHardware_WithCanShu(String ccid, String serverId, IMqttActionListener listener) {
        shishiTopic = "zn/hardware/" + serverId + "/" + ccid;
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(shishiTopic)
                .setQos(2), listener);
    }

    /**
     * hareware 通过订阅获得主机的实时信息
     */
    public void subscribeAppShiShiXinXi_WithCanShu(String ccid, String serverId, IMqttActionListener listener) {

        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        shishiTopic = "zn/haredware/" + serverId + ccid;
        Log.i("Rair", "订阅实时数据" + shishiTopic);
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(shishiTopic)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "订阅成功:" + shishiTopic);

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });

    }

    /**
     * app 通过订阅获得主机的实时信息
     */
    public void subscribeServerShiShiXinXi_WithCanShu(String ccid, String serverId, IMqttActionListener listener) {

        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        shishiTopic = "zn/server/" + serverId + ccid;
        Log.i("Rair", "订阅实时数据" + shishiTopic);
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(shishiTopic)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "订阅成功:" + shishiTopic);

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });

    }

    /**
     * hareware 通过订阅获得主机的实时信息
     */
    public void subscribeLastAppShiShiXinXi_WithCanShu(String ccid, String serverId, IMqttActionListener listener) {

        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        shishiTopic = "zn/app/" + serverId + ccid;
        Log.i("Rair", "订阅实时数据" + shishiTopic);
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(shishiTopic)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "订阅成功:" + shishiTopic);

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });

    }

    /**
     * 取消订阅主机的实时信息
     *
     * @param listener
     */
    public void unSubscribeShiShiXinXi_WithCanShu(String ccid, String serverId, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        shishiTopic = "zn/app/" + serverId + ccid;
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(shishiTopic)
                .setQos(2), listener);
    }

    public void setZhiNengKaiGuan(String zhuangZhiId, String quanshu, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }

        String zhiLing = "M15" + zhuangZhiId + quanshu + ".";
        Log.i("Rair", zhiLing);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }

    /**
     * @param ccid_up
     * @param serverId
     * @param listener
     * @param str      发送的信息
     *                 <p>
     *                 App等终端添加装置命令M1201011.
     *                 M12:命令码【添加装置】
     *                 01：装置类型 例如：00全部装置 01、02、03、04 对应各装置类型
     *                 011：装置类型的型号 例如： 01 是装置的A款 02 表示装置的B款
     *                 1：主机语音添加 2：app添加 3:pc添加 4:小程序添加
     */
    public void tianJiaSheBei(String ccid_up, String serverId, String str, IMqttActionListener listener) {

        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }

        if (serverId.contains("/")) {
            serverId = serverId.substring(0, 1);
        }
        shishiTopic = "zn/hardware/" + serverId + "/" + ccid_up;
        Log.i("Rair", "订阅实时数据" + shishiTopic);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(str)
                .setQos(2)
                .setRetained(false)
                .setTopic(shishiTopic), listener);
//        AndMqtt.getInstance().subscribe(new MqttSubscribe()
//                .setTopic(shishiTopic)
//                .setQos(2), listener);
    }


    /**
     * 取消app订阅
     *
     * @param listener
     */
    public void unSubscribeShiShiXinXi_App(String ccid, String serverId, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        shishiTopic = "zn/app/" + serverId + ccid;
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(shishiTopic)
                .setQos(2), listener);
    }


    public void yaoKongQiPeiDui(String topic, String zhiLing, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }


    public void yaoKongQiMingLing(String mingLingMa, String topic, IMqttActionListener listener) {
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }
        String zhiLing = mingLingMa;
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }


    /**
     * 示例:M231308yingjian_wifiabc12345.
     * M23 命令码
     * 13：表示Wi-Fi 名称长度
     * 08:表示Wi-Fi密码长度
     * yingjian_wifi：Wi-Fi名称示例
     * abc12345：Wi-Fi密码示例
     * .结束符
     */
    public void setActionOne(String str, IMqttActionListener listener) {

        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(context, "未连接主机,请重新尝试");
            return;
        }

        String zhiLing = "M02" + str;
        Log.i("Rair", "M02  行为指令  " + str);
        Log.i("Rair", zhiLing);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(topic), listener);
    }


}
