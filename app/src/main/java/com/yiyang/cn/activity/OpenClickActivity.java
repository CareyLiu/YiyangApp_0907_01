package com.yiyang.cn.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.zhinengjiaju.function.LouShuiActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.MenCiActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.MenSuoActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.SosActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.YanGanActivity;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.model.OpenClickModel;
import com.yiyang.cn.model.ZhiNengJiaJuNotifyJson;
import com.yiyang.cn.util.SoundPoolUtils;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by jiguang on 17/7/5.
 */

public class OpenClickActivity extends Activity {
    OpenClickModel openClickModel;
    private static final String TAG = "OpenClickActivity";
    /**
     * 消息Id
     **/
    private static final String KEY_MSGID = "msg_id";
    /**
     * 该通知的下发通道
     **/
    private static final String KEY_WHICH_PUSH_SDK = "rom_type";
    /**
     * 通知标题
     **/
    private static final String KEY_TITLE = "n_title";
    /**
     * 通知内容
     **/
    private static final String KEY_CONTENT = "n_content";
    /**
     * 通知附加字段
     **/
    private static final String KEY_EXTRAS = "n_extras";
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextView = new TextView(this);
        setContentView(mTextView);
        handleOpenClick();
    }


    /**
     * 处理点击事件，当前启动配置的Activity都是使用
     * Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
     * 方式启动，只需要在onCreat中调用此方法进行处理
     */
    private void handleOpenClick() {
        Log.d(TAG, "用户点击打开了通知");
        String data = null;
        //获取华为平台附带的jpush信息
        if (getIntent().getData() != null) {
            data = getIntent().getData().toString();
        }

        //获取fcm、oppo、vivo、华硕、小米平台附带的jpush信息
        if (TextUtils.isEmpty(data) && getIntent().getExtras() != null) {
            data = getIntent().getExtras().getString("JMessageExtra");
        }

        Log.w(TAG, "msg content is " + String.valueOf(data));

        if (TextUtils.isEmpty(data)) return;
        try {
            JSONObject jsonObject = new JSONObject(data);
            String msgId = jsonObject.optString(KEY_MSGID);
            byte whichPushSDK = (byte) jsonObject.optInt(KEY_WHICH_PUSH_SDK);
            String title = jsonObject.optString(KEY_TITLE);
            String content = jsonObject.optString(KEY_CONTENT);
            String extras = jsonObject.optString(KEY_EXTRAS);
            StringBuilder sb = new StringBuilder();
            sb.append("msgId:");
            sb.append(String.valueOf(msgId));
            sb.append("\n");
            sb.append("title:");
            sb.append(String.valueOf(title));
            sb.append("\n");
            sb.append("content:");
            sb.append(String.valueOf(content));
            sb.append("\n");
            sb.append("extras:");
            sb.append(String.valueOf(extras));
            sb.append("\n");
            sb.append("platform:");
            sb.append(getPushSDKName(whichPushSDK));
            mTextView.setText(sb.toString());

            openClickModel = new Gson().fromJson(data, OpenClickModel.class);

            //上报点击事件
            JPushInterface.reportNotificationOpened(this, msgId, whichPushSDK);
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            function(openClickModel);
            finish();
        } catch (JSONException e) {
            Log.w(TAG, "parse notification error");
        }


    }

    private void function(OpenClickModel openClickModel) {

        /**
         * * o	1.风暖加热器
         * 2.水暖加热器
         * 3.汽车
         * 4.货车防盗
         * 5.智能家居
         * 6.驻车空调	带有提示音的风暖、水暖报警提示（字母o为历史版本，特殊）
         *
         * jyj_0000		app_type 1:ios 2:安卓IOS / Android版本有更新
         * jyj_0001		tx_state 0:提现失败1:提现成功提现处理：通知
         * jyj_0002		online_state 0：离线 1：上线智慧医养智能主机离线 、上线提醒
         * jyj_0003		href_url广告推送，跳转h5链接，h5链接放在url字段处
         * jyj_0004		通知消息推送，跳转消息列表
         * jyj_0005		kind_type
         * jyj_0006
         * 0:普通商品 1: 团购商品 2:修配厂店铺类型详情
         * 商品推送，点击推送消息进入商品详情，推送信息中带有商品对应id，字段参考普通商品详情以及团购商品详情
         */
        switch (openClickModel.getN_extras().getCode()) {
            case "o"://风暖加热器
                break;
            case "jyj_0000":
                break;
            case "jyj_0001":
                break;
            case "jyj_0002":
                //刷新列表
                Log.i("设备离线", "设备离线刷新列表");
//                if (zhiNengJiaJuNotifyJson.online_state.equals("0")) {//离线
//                    Notice notice = new Notice();
//                    notice.type = ConstanceValue.MSG_ZHINENGJIAJU_ZHUJI;
//                    notice.content = "0";
//                    RxBus.getDefault().sendRx(notice);
//
//                } else if (zhiNengJiaJuNotifyJson.online_state.equals("1")) {//在线
//                    Notice notice = new Notice();
//                    notice.type = ConstanceValue.MSG_ZHINENGJIAJU_ZHUJI;
//                    notice.content = "1";
//                    RxBus.getDefault().sendRx(notice);
//
//
//                }
                break;
            case "jyj_0003":
                break;
            case "jyj_0004":
                break;
            case "jyj_0005":
                break;
            case "jyj_0006":
                Y.e("见风使舵老师的打快速反击 ");

                if (openClickModel.getN_extras().getDevice_type().equals("12")) {
                    MenCiActivity.actionStart(getApplicationContext(), openClickModel.getN_extras().getDevice_id());
                } else if (openClickModel.getN_extras().getDevice_type().equals("11")) {
                    YanGanActivity.actionStart(getApplicationContext(), openClickModel.getN_extras().getDevice_id());
                } else if (openClickModel.getN_extras().getDevice_type().equals("15")) {
                    SosActivity.actionStart(getApplicationContext(), openClickModel.getN_extras().getDevice_id(), true);
                   // SoundPoolUtils.soundPool(getApplicationContext(), R.raw.baojingyin_1);
                } else if (openClickModel.getN_extras().getDevice_type().equals("05")) {
                    MenSuoActivity.actionStart(getApplicationContext(), openClickModel.getN_extras().getDevice_id());
                } else if (openClickModel.getN_extras().getDevice_type().equals("13")) {
                    LouShuiActivity.actionStart(getApplicationContext(), openClickModel.getN_extras().getDevice_id());
                }

                break;
            case "jyj_0007":
                break;


        }

    }

    private String getPushSDKName(byte whichPushSDK) {
        String name;
        switch (whichPushSDK) {
            case 0:
                name = "jpush";
                break;
            case 1:
                name = "xiaomi";
                break;
            case 2:
                name = "huawei";
                break;
            case 3:
                name = "meizu";
                break;
            case 4:
                name = "oppo";
                break;
            case 5:
                name = "vivo";
                break;
            case 6:
                name = "asus";
                break;
            case 8:
                name = "fcm";
                break;
            default:
                name = "jpush";
        }
        return name;
    }


}