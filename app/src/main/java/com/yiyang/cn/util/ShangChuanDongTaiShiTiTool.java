package com.yiyang.cn.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import com.iflytek.aiui.AIUIAgent;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIListener;
import com.iflytek.aiui.AIUIMessage;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.model.DongTaiShiTiModel;
import com.yiyang.cn.model.ResultModel;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.yiyang.cn.activity.shuinuan.Y.getResources;
import static com.yiyang.cn.config.MyApplication.CAR_NOTIFY;

public class ShangChuanDongTaiShiTiTool {

    public String TAG = ShangChuanDongTaiShiTiTool.class.getSimpleName();
    private AIUIAgent mAIUIAgent = null;
    private int mAIUIState = AIUIConstant.STATE_IDLE;
    private String mSyncSid = "";
    String uid;
    private Context context;





    private void showTip(final String str) {
    }


    private void createAgent() {
        if (null == mAIUIAgent) {
            Log.i(TAG, "create aiui agent");
            mAIUIAgent = AIUIAgent.createAgent(context, getAIUIParams(), mAIUIListener);
        }

        if (null == mAIUIAgent) {
            final String strErrorTip = "创建AIUIAgent失败！";
            showTip(strErrorTip);
            //  mNlpText.setText(strErrorTip);
        } else {
            showTip("AIUIAgent已创建");
        }
    }

    private String getAIUIParams() {
        String params = "";

        AssetManager assetManager = getResources().getAssets();
        try {
            InputStream ins = assetManager.open("cfg/aiui_phone.cfg");
            byte[] buffer = new byte[ins.available()];

            ins.read(buffer);
            ins.close();

            params = new String(buffer);

            JSONObject paramsJson = new JSONObject(params);
            //paramsJson.put("pers_param", "{\"uid\":\"d3b6d50a9f8194b623b5e2d4e298c9d6\"}");
            params = paramsJson.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }



    public String zhiXingYiCi = "1";

    private AIUIListener mAIUIListener = new AIUIListener() {

        @Override
        public void onEvent(AIUIEvent event) {
            Log.i(TAG, "on event: " + event.eventType);

            switch (event.eventType) {
                case AIUIConstant.EVENT_CONNECTED_TO_SERVER:
                    uid = event.data.getString("uid");
                    Log.i("YuYinCHuLiTool_Uid:", uid);
                    showTip("已连接服务器");
                    if (zhiXingYiCi.equals("1")) {
//                        syncContactsSheBei(sheBeiList);
//                        syncContactsRoom(roomList);
//
//                        YanChi5ZhiXing();
                        zhiXingYiCi = "0";
                    }


                    break;

                case AIUIConstant.EVENT_SERVER_DISCONNECTED:
                    showTip("与服务器断连");
                    break;

                case AIUIConstant.EVENT_WAKEUP:
                    showTip("进入识别状态");
                    break;

                case AIUIConstant.EVENT_RESULT: {


                }
                break;

                case AIUIConstant.EVENT_ERROR: {
                    // mNlpText.append("\n");
                    // mNlpText.append("错误: " + event.arg1 + "\n" + event.info);
                    Log.i(TAG, "ERROR: " + event.arg1 + "\n" + event.info);
                }
                break;

                case AIUIConstant.EVENT_VAD: {
                    //vad事件
                    if (AIUIConstant.VAD_BOS == event.arg1) {
                        //找到语音前端点
                        Log.i(TAG, "找到vad_bos");
                    } else if (AIUIConstant.VAD_EOS == event.arg1) {
                        //找到语音后端点
                        Log.i(TAG, "找到vad_eos");
                    } else if (AIUIConstant.VAD_BOS_TIMEOUT == event.arg1) {
                    } else {
                        Log.i(TAG, "event vad " + event.arg2);
                    }
                }
                break;

                case AIUIConstant.EVENT_START_RECORD: {
                    showTip("已开始录音");
                }
                break;

                case AIUIConstant.EVENT_STOP_RECORD: {
                    showTip("已停止录音");
                }
                break;

                case AIUIConstant.EVENT_STATE: {    // 状态事件
                    mAIUIState = event.arg1;

                    if (AIUIConstant.STATE_IDLE == mAIUIState) {
                        // 闲置状态，AIUI未开启
                        showTip("STATE_IDLE");
                    } else if (AIUIConstant.STATE_READY == mAIUIState) {
                        // AIUI已就绪，等待唤醒
                        showTip("STATE_READY");
                    } else if (AIUIConstant.STATE_WORKING == mAIUIState) {
                        // AIUI工作中，可进行交互
                        showTip("STATE_WORKING");
                    }
                }
                break;

                case AIUIConstant.EVENT_CMD_RETURN: {

                    if (AIUIConstant.CMD_SYNC == event.arg1) {    // 数据同步的返回
                        int dtype = event.data.getInt("sync_dtype", -1);
                        int retCode = event.arg2;

                        switch (dtype) {
                            case AIUIConstant.SYNC_DATA_SCHEMA: {
                                if (AIUIConstant.SUCCESS == retCode) {
                                    // 上传成功，记录上传会话的sid，以用于查询数据打包状态
                                    // 注：上传成功并不表示数据打包成功，打包成功与否应以同步状态查询结果为准，数据只有打包成功后才能正常使用
                                    mSyncSid = event.data.getString("sid");

                                    // 获取上传调用时设置的自定义tag
                                    String tag = event.data.getString("tag");

                                    // 获取上传调用耗时，单位：ms
                                    long timeSpent = event.data.getLong("time_spent", -1);
                                    if (-1 != timeSpent) {
                                        //mTimeSpentText.setText(timeSpent + "ms");
                                        Log.i(TAG, "调用耗时" + timeSpent);
                                    }

                                    showTip("上传成功，sid=" + mSyncSid + "，tag=" + tag + "，你可以试着说“打电话给刘德华”");
                                    Log.i("YuYin", "打包成功_sid=" + mSyncSid);
                                } else {
                                    mSyncSid = "";
                                    showTip("上传失败，错误码：" + retCode);
                                }
                            }
                            break;
                        }
                    } else if (AIUIConstant.CMD_QUERY_SYNC_STATUS == event.arg1) {    // 数据同步状态查询的返回

                    }
                }
                break;

                default:
                    break;
            }
        }

    };



}
