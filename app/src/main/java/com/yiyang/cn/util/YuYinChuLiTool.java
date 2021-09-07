package com.yiyang.cn.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
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
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.VoiceWakeuper;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.WakeuperResult;
import com.iflytek.cloud.util.ResourceUtil;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.yiyang.cn.R;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.Logger;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.inter.YuYinInter;
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
import static com.yiyang.cn.activity.shuinuan.Y.getString;
import static com.yiyang.cn.config.MyApplication.CAR_NOTIFY;

public class YuYinChuLiTool {
    private String TAG = YuYinChuLiTool.class.getSimpleName();
    private Context context;
    private VoiceWakeuper mIvw;
    // 语音合成对象
    private SpeechSynthesizer mTts;

    private int curThresh = 1450;
    private String threshStr = "门限值：";
    private String keep_alive = "1";
    private String ivwNetMode = "0";
    // 唤醒结果内容
    private String resultString;
    ResultModel resultModel;
    String chazuo = "";
    String caozuo = "";
    String weizhi = "000";
    String shebei = "";

    //当前状态，取值参考Constant中STATE定义
    private int mState;
    private int mAIUIState = AIUIConstant.STATE_IDLE;
    public AIUIAgent mAIUIAgent = null;
    private String mSyncSid = "";

    private String getResource() {
        final String resPath = ResourceUtil.generateResourcePath(context, ResourceUtil.RESOURCE_TYPE.assets, "ivw/" + getString(R.string.app_id) + ".jet");
        Log.d(TAG, "resPath: " + resPath);
        return resPath;
    }

    public YuYinChuLiTool(Context context, YuYinInter yuYinInter) {
        this.context = context;
        mIvw = VoiceWakeuper.createWakeuper(context, null);
        // 初始化合成对象
        mTts = SpeechSynthesizer.createSynthesizer(context, mTtsInitListener);
        this.yuYinInter = yuYinInter;
    }

    public YuYinChuLiTool(Context context, List<String> roomList, List<String> sheBeiList) {
        this.context = context;
        this.roomList = roomList;
        this.sheBeiList = sheBeiList;
        createAgent();
        zhiXingYiCi = "1";
    }


    YuYinInter yuYinInter = null;

    //唤醒功能
    public void beginWakeUp() {
        //   非空判断，防止因空指针使程序崩溃
        mIvw = VoiceWakeuper.getWakeuper();
        if (mIvw != null) {
            //setRadioEnable(false);
            resultString = "";
            // textView.setText(resultString);

            // 清空参数
            mIvw.setParameter(SpeechConstant.PARAMS, null);
            // 唤醒门限值，根据资源携带的唤醒词个数按照“id:门限;id:门限”的格式传入
            mIvw.setParameter(SpeechConstant.IVW_THRESHOLD, "0:" + curThresh);
            // 设置唤醒模式
            mIvw.setParameter(SpeechConstant.IVW_SST, "wakeup");
            // 设置持续进行唤醒
            mIvw.setParameter(SpeechConstant.KEEP_ALIVE, keep_alive);
            // 设置闭环优化网络模式
            mIvw.setParameter(SpeechConstant.IVW_NET_MODE, ivwNetMode);
            // 设置唤醒资源路径
            mIvw.setParameter(SpeechConstant.IVW_RES_PATH, getResource());
            // 设置唤醒录音保存路径，保存最近一分钟的音频
            mIvw.setParameter(SpeechConstant.IVW_AUDIO_PATH, Environment.getExternalStorageDirectory().getPath() + "/msc/ivw.wav");
            mIvw.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
            // 如有需要，设置 NOTIFY_RECORD_DATA 以实时通过 onEvent 返回录音音频流字节
            //mIvw.setParameter( SpeechConstant.NOTIFY_RECORD_DATA, "1" );
            // 启动唤醒
            /*	mIvw.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");*/
            mIvw.startListening(mWakeuperListener);
				/*File file = new File(Environment.getExternalStorageDirectory().getPath() + "/msc/ivw1.wav");
				byte[] byetsFromFile = getByetsFromFile(file);
				mIvw.writeAudio(byetsFromFile,0,byetsFromFile.length);*/
            //	mIvw.stopListening();

        } else {
            // showTip("唤醒未初始化");
            UIHelper.ToastMessage(context, "唤醒未初始化");
        }
    }

    /**
     * 初始化监听。
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d(TAG, "InitListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败,错误码：" + code + ",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };

    private void showTip(final String str) {
    }

    private WakeuperListener mWakeuperListener = new WakeuperListener() {

        @Override
        public void onResult(WakeuperResult result) {
            Log.d(TAG, "onResult");
            if (!"1".equalsIgnoreCase(keep_alive)) {
                //  setRadioEnable(true);
            }
            try {
                String text = result.getResultString();
                JSONObject object;
                object = new JSONObject(text);
                StringBuffer buffer = new StringBuffer();
                buffer.append("【RAW】 " + text);
                buffer.append("\n");
                buffer.append("【操作类型】" + object.optString("sst"));
                buffer.append("\n");
                buffer.append("【唤醒词id】" + object.optString("id"));
                buffer.append("\n");
                buffer.append("【得分】" + object.optString("score"));
                buffer.append("\n");
                buffer.append("【前端点】" + object.optString("bos"));
                buffer.append("\n");
                buffer.append("【尾端点】" + object.optString("eos"));
                resultString = buffer.toString();
            } catch (JSONException e) {
                resultString = "结果解析出错";
                e.printStackTrace();
            }
            //textView.setText(resultString);
            Log.i("RESULT_STRING", resultString);
            beginYuYIn();
//            mIvw.stopListening();
//            int code = mTts.startSpeaking("在呢", mTtsListener);
//           Intent nlpIntent = new Intent(getActivity(), NlpDemo.class);
//           startActivity(nlpIntent);
//            //激活唤醒，开启语音识别
//            createAgent();
//            startVoiceNlp();
        }


        @Override
        public void onError(SpeechError error) {
            //showTip(error.getPlainDescription(true));
            UIHelper.ToastMessage(context, error.getPlainDescription(true));
            // setRadioEnable(true);
        }

        @Override
        public void onBeginOfSpeech() {
        }

        @Override
        public void onEvent(int eventType, int isLast, int arg2, Bundle obj) {
            switch (eventType) {
                // EVENT_RECORD_DATA 事件仅在 NOTIFY_RECORD_DATA 参数值为 真 时返回
                case SpeechEvent.EVENT_RECORD_DATA:
                    final byte[] audio = obj.getByteArray(SpeechEvent.KEY_EVENT_RECORD_DATA);
                    Log.i(TAG, "ivw audio length: " + audio.length);
                    break;
            }
        }

        @Override
        public void onVolumeChanged(int volume) {

        }
    };

    /**
     * 合成回调监听。
     */
    private SynthesizerListener mTtsListener = new SynthesizerListener() {

        @Override
        public void onSpeakBegin() {
            //showTip("开始播放");
            Log.d(TAG, "开始播放：" + System.currentTimeMillis());
            stopVoiceNlp();
        }

        @Override
        public void onSpeakPaused() {
            showTip("暂停播放");
        }

        @Override
        public void onSpeakResumed() {
            showTip("继续播放");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {
//            // 合成进度
//            mPercentForBuffering = percent;
//            showTip(String.format(getString(R.string.tts_toast_format),
//                    mPercentForBuffering, mPercentForPlaying));
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
//            mPercentForPlaying = percent;
//            showTip(String.format(getString(R.string.tts_toast_format),
//                    mPercentForBuffering, mPercentForPlaying));
        }

        @Override
        public void onCompleted(SpeechError error) {
            startVoiceNlp();
            if (error == null) {
                showTip("播放完成");
                if (resultModel != null) {
                    if (resultModel.getAnswer().getText() != null) {
                        if (resultModel.getAnswer().getText().equals("正在为您操作")) {
//                    yuYinInter.dismissMianBan();
//                    closeMianBan();
//                    if (thread5CloseExit != null) {
//                        thread5CloseExit.interrupt();
//                        thread5CloseExit = null;
//                        guanBiYuYinThread = "0";
//                    }
//                    guanBiYuYinThread = "1";
//                    thread5CloseExit = new Exit5CloseThread();
//                    thread5CloseExit.start();
                        }
                    }
                }

            } else if (error != null) {
                showTip(error.getPlainDescription(true));
            }
            Log.d(TAG, "播放完成：" + System.currentTimeMillis());

        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                String sid = obj.getString(SpeechEvent.KEY_EVENT_AUDIO_URL);
                Log.d(TAG, "session id =" + sid);
            }

            //实时音频流输出参考
			/*if (SpeechEvent.EVENT_TTS_BUFFER == eventType) {
				byte[] buf = obj.getByteArray(SpeechEvent.KEY_EVENT_TTS_BUFFER);
				Log.e("MscSpeechLog", "buf is =" + buf);
			}*/
        }
    };

    private void startVoiceNlp() {
        if (null == mAIUIAgent) {
            showTip("AIUIAgent为空，请先创建");
            return;
        }

        Log.i(TAG, "start voice nlp");
        //mNlpText.setText("");

        // 先发送唤醒消息，改变AIUI内部状态，只有唤醒状态才能接收语音输入
        // 默认为oneshot模式，即一次唤醒后就进入休眠。可以修改aiui_phone.cfg中speech参数的interact_mode为continuous以支持持续交互
        if (AIUIConstant.STATE_WORKING != mAIUIState) {
            AIUIMessage wakeupMsg = new AIUIMessage(AIUIConstant.CMD_WAKEUP, 0, 0, "", null);
            mAIUIAgent.sendMessage(wakeupMsg);
        }

        // 打开AIUI内部录音机，开始录音。若要使用上传的个性化资源增强识别效果，则在参数中添加pers_param设置
        // 个性化资源使用方法可参见http://doc.xfyun.cn/aiui_mobile/的用户个性化章节
        // 在输入参数中设置tag，则对应结果中也将携带该tag，可用于关联输入输出
        String params = "sample_rate=16000,data_type=audio";
        AIUIMessage startRecord = new AIUIMessage(AIUIConstant.CMD_START_RECORD, 0, 0, params, null);
        mAIUIAgent.sendMessage(startRecord);
    }

    private void stopVoiceNlp() {
        if (null == mAIUIAgent) {
            showTip("AIUIAgent 为空，请先创建");
            return;
        }

        Log.i(TAG, "stop voice nlp");
        // 停止录音
        String params = "sample_rate=16000,data_type=audio";
        AIUIMessage stopRecord = new AIUIMessage(AIUIConstant.CMD_STOP_RECORD, 0, 0, params, null);

        mAIUIAgent.sendMessage(stopRecord);
    }

    private void createAgent() {
//        if (null == mAIUIAgent) {
//
//
//            //  syncContactsRoom();
//        }

        Log.i(TAG, "create aiui agent");

        mAIUIAgent = AIUIAgent.createAgent(context, getAIUIParams(), mAIUIListener);
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

    String uid;
    public String zhiXingYiCi = "0";
    List<String> roomList = new ArrayList<>();
    List<String> sheBeiList = new ArrayList<>();
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
                        syncContactsSheBei(sheBeiList);
                        syncContactsRoom(roomList);

                        YanChi5ZhiXing();
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
                    try {
                        JSONObject bizParamJson = new JSONObject(event.info);
                        JSONObject data = bizParamJson.getJSONArray("data").getJSONObject(0);
                        JSONObject params = data.getJSONObject("params");
                        JSONObject content = data.getJSONArray("content").getJSONObject(0);

                        if (content.has("cnt_id")) {
                            String cnt_id = content.getString("cnt_id");
                            String cntStr = new String(event.data.getByteArray(cnt_id), "utf-8");

                            // 获取该路会话的id，将其提供给支持人员，有助于问题排查
                            // 也可以从Json结果中看到
                            String sid = event.data.getString("sid");
                            String tag = event.data.getString("tag");

                            showTip("tag=" + tag);

                            // 获取从数据发送完到获取结果的耗时，单位：ms
                            // 也可以通过键名"bos_rslt"获取从开始发送数据到获取结果的耗时
                            long eosRsltTime = event.data.getLong("eos_rslt", -1);
                            // mTimeSpentText.setText(eosRsltTime + "ms");

                            if (TextUtils.isEmpty(cntStr)) {
                                return;
                            }
                            JSONObject cntJson = new JSONObject(cntStr);
                            String sub = params.optString("sub");
                            if ("nlp".equals(sub)) {
                                // 解析得到语义结果
                                String resultStr = cntJson.optString("intent");
                                Log.i(TAG, "语义结果: " + resultStr);

                                if (resultStr.equals("{}")) {
                                    return;
                                }

                                resultModel = new Gson().fromJson(resultStr, ResultModel.class);
                                // 设置参数
                                setParam();


                                if (resultModel != null) {
//                                    if (thread5CloseExit != null) {
//                                        thread5CloseExit.interrupt();
//                                        thread5CloseExit = null;
//                                        guanBiYuYinThread = "0";
//                                    }
                                    if (resultModel.getRc() == 0) {
                                        if (resultModel.getSemantic() == null) {
                                            return;
                                        }
                                        int code = mTts.startSpeaking(resultModel.getAnswer().getText(), mTtsListener);

                                        ResultModel.SemanticBean semanticBean = resultModel.getSemantic().get(0);
                                        yuYinInter.yuYinResult(resultModel.getText());

                                        Log.i("语义结果", "code" + code);
                                        String deviceCCID = PreferenceHelper.getInstance(context).getString(AppConfig.DEVICECCID, "");
                                        String serverId = PreferenceHelper.getInstance(context).getString(AppConfig.SERVERID, "");
                                        String topic = "zn/server/" + serverId + deviceCCID;
                                        //topic = "zn/server/8/aaaaaaaaaaaaaaaa90140018";
                                        YuYinMqtt yuYinMqtt = new YuYinMqtt(context, topic);
                                        yuYinMqtt.subscribeMingLing();

                                        /**
                                         *     /**
                                         *      * answer : {"text":"正在为您操作","type":"T"}
                                         *      * category : OS8569425439.ceshi
                                         *      * data : {"result":null}
                                         *      * intentType : custom
                                         *      * rc : 0
                                         *      * semantic : [{"entrypoint":"ent","hazard":false,"intent":"dengl","score":1,"slots":[{"begin":0,"end":2,"name":"caozuo","normValue":"打开","value":"打开"},{"begin":2,"end":4,"name":"weizhi","normValue":"客厅","value":"客厅"},{"begin":4,"end":5,"name":"shebei","normValue":"灯","value":"灯"}],"template":"{caozuo}{weizhi}{shebei}"}]
                                         *      * semanticType : 0
                                         *      * service : OS8569425439.ceshi
                                         *      * sessionIsEnd : false
                                         *      * shouldEndSession : true
                                         *      * sid : atn04b05556@dx00011341fd48a11100
                                         *      * state : null
                                         *      * text : 打开客厅灯
                                         *      * uuid : atn04b05556@dx00011341fd48a11100
                                         *      * vendor : OS8569425439
                                         *      * version : 7.0
                                         *      * voice_answer : [{"content":"正在为您操作","type":"TTS"}]
                                         *      */

                                        String msg = "";
                                        //获得操作后拼接msg
                                        String intentName = resultModel.getSemantic().get(0).getIntent();
                                        if (intentName.equals("feedingFish")) {

                                            if (resultModel.getAnswer().getText().equals("正在为您操作")) {
                                                String circle = "0" + semanticBean.getSlots().get(0).getNormValue() + "c";
                                                msg = "v{'intentName':" + "feedingFish" + ",'operate':" + "打开" + ",'device':" + "喂鱼" + ",'room':" + "客厅" + ",'time':" + circle + "}.";
                                                Log.i("语义结果", msg);
                                                yuYinMqtt.pushMingLing(msg);
                                                caozuo = "";
                                                shebei = "";
                                                weizhi = "000";
                                            }
                                        } else if (intentName.equals("actionByName") || intentName.equals("actionByRoom")) {
                                            for (int i = 0; i < semanticBean.getSlots().size(); i++) {
                                                if (semanticBean.getSlots().get(i).getName().equals("operate")) {
                                                    caozuo = semanticBean.getSlots().get(i).getNormValue();
                                                } else if (semanticBean.getSlots().get(i).getName().equals("device")) {
                                                    shebei = semanticBean.getSlots().get(i).getNormValue();
                                                } else if (semanticBean.getSlots().get(i).getName().equals("cus_room")) {
                                                    weizhi = semanticBean.getSlots().get(i).getNormValue();
                                                } else if (semanticBean.getSlots().get(i).getName().equals("device_name")) {
                                                    shebei = semanticBean.getSlots().get(i).getNormValue();
                                                } else if (semanticBean.getSlots().get(i).getName().equals("device_operate")) {
                                                    caozuo = semanticBean.getSlots().get(i).getNormValue();
                                                }
                                            }

                                            if (resultModel.getAnswer().getText().equals("正在为您操作")) {
                                                msg = "v{'intentName':" + resultModel.getSemantic().get(0).getIntent() + ",'operate':" + caozuo + ",'device':" + shebei + ",'room':" + weizhi + "}.";
                                                Log.i("语义结果", msg);
                                                yuYinMqtt.pushMingLing(msg);
                                                caozuo = "";
                                                shebei = "";
                                                weizhi = "000";
                                            }

                                            Log.i("语义结果", msg);
                                        } else {
                                            //控车相关
                                            faSongKongCheFangFa(resultModel);
                                        }
                                    }

                                }

                            }
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();

                    }

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
                        // 送入音频前端点超时
                        Log.i(TAG, "前端点超时");
                        closeMianBan();
                        yuYinInter.dismissMianBan();
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
                        // 获取同步类型
                        int syncType = event.data.getInt("sync_dtype", -1);
                        if (AIUIConstant.SYNC_DATA_QUERY == syncType) {
                            // 若是同步数据查询，则获取查询结果，结果中error字段为0则表示上传数据打包成功，否则为错误码
                            String result = event.data.getString("result");

                            showTip(result);

                            Notice notice = new Notice();
                            notice.type = ConstanceValue.MSG_XIUGAIDONGTAISHITIFINISH;
                            RxBus.getDefault().sendRx(notice);


                            Log.i(TAG, result);
                        }
                        mAIUIAgent.destroy();
                        mAIUIAgent = null;
                    }
                }
                break;

                default:
                    break;
            }
        }

    };

    String sheBeiMing = null;
    String chekong_caozuo = null;
    String caoZuoMoShi = null;
    String wenDu = null;
    String snShuiYou = null;//水暖水泵或油泵
    String dangwei = null;//档位
    String cheKongSheBei = null;//车控温度调节

    private void faSongKongCheFangFa(ResultModel resultModel) {
        String caoZuoZhiLing = null;
        List<ResultModel.SemanticBean.SlotsBean> slotsBeans = resultModel.getSemantic().get(0).getSlots();
        String intengName = resultModel.getSemantic().get(0).getIntent();


        switch (intengName) {
            case "fengnuan_tiaojiewendu":

                for (int i = 0; i < slotsBeans.size(); i++) {
                    if (slotsBeans.get(i).getName().equals("Number")) {
                        wenDu = slotsBeans.get(i).getNormValue();
                    } else if (slotsBeans.get(i).getName().equals("fengnuanshebei")) {
                        sheBeiMing = slotsBeans.get(i).getNormValue();
                    }
                }

                caoZuoZhiLing = "v{'intentName':" + intengName + ",'wendu':" + wenDu + "}.";
                intengName = null;
                wenDu = null;
                break;

            case "fengnuan_kaiji":

                for (int i = 0; i < slotsBeans.size(); i++) {
                    if (slotsBeans.get(i).getName().equals("zhikong_caozuo")) {
                        chekong_caozuo = slotsBeans.get(i).getNormValue();
                    } else if (slotsBeans.get(i).getName().equals("dakaifangshi")) {
                        caoZuoMoShi = slotsBeans.get(i).getNormValue();
                    }
                }

                caoZuoZhiLing = "v{'intentName':" + intengName + ",'caoZuoMoShi':" + caoZuoMoShi + "}.";
                break;
            case "fengnuan_guanji":
                caoZuoZhiLing = "v{'intentName':" + intengName + "}.";
                break;
            case "shuinuan_kaiguanji":
                for (int i = 0; i < slotsBeans.size(); i++) {
                    if (slotsBeans.get(i).getName().equals("zhikong_caozuo")) {
                        chekong_caozuo = slotsBeans.get(i).getNormValue();
                    }
                }
                caoZuoZhiLing = "v{'intentName':" + intengName + ",'chekong_caozuo':" + chekong_caozuo + "}.";
                break;
            case "shuinuan_shuiyou_caozuo":
                for (int i = 0; i < slotsBeans.size(); i++) {

                    if (slotsBeans.get(i).getName().equals("chekong_caozuo")) {
                        chekong_caozuo = slotsBeans.get(i).getNormValue();
                    } else if (slotsBeans.get(i).getName().equals("sn_shuiyou")) {
                        snShuiYou = slotsBeans.get(i).getNormValue();
                    }

                }
                caoZuoZhiLing = "v{'intentName':" + intengName + ",'chekong_caozuo':" + chekong_caozuo + ",'sn_shuiyou':" + snShuiYou + "}.";
                break;

            case "shuinuan_wendutiaojie":
                for (int i = 0; i < slotsBeans.size(); i++) {
                    if (slotsBeans.get(i).getName().equals("wendu")) {
                        wenDu = slotsBeans.get(i).getNormValue();
                    }
                }
                caoZuoZhiLing = "v{'intentName':" + intengName + ",'wenDu':" + wenDu + "}.";
                break;

            case "kongtiao_kaiguanji":
                for (int i = 0; i < slotsBeans.size(); i++) {

                    if (slotsBeans.get(i).getName().equals("chekong_caozuo")) {
                        chekong_caozuo = slotsBeans.get(i).getNormValue();
                    }
                }

                caoZuoZhiLing = "v{'intentName':" + intengName + ",'chekong_caozuo':" + chekong_caozuo + "}.";
                break;

            case "kogntiao_wendutiaojie":
                for (int i = 0; i < slotsBeans.size(); i++) {
                    if (slotsBeans.get(i).getName().equals("wendu")) {
                        wenDu = slotsBeans.get(i).getNormValue();
                    }
                }
                caoZuoZhiLing = "v{'intentName':" + intengName + ",'wenDu':" + wenDu + "}.";
                break;
            case "kongtiaodeng_tiaojie":

                for (int i = 0; i < slotsBeans.size(); i++) {
                    if (slotsBeans.get(i).getName().equals("chekong_caozuo")) {
                        chekong_caozuo = slotsBeans.get(i).getNormValue();
                    }
                }
                caoZuoZhiLing = "v{'intentName':" + intengName + ",'chekong_caozuo':" + chekong_caozuo + "}.";
                break;

            case "qiehuan_kongtiao_moshi":
                for (int i = 0; i < slotsBeans.size(); i++) {
                    if (slotsBeans.get(i).getName().equals("kongtiao_moshi")) {
                        caoZuoMoShi = slotsBeans.get(i).getNormValue();
                    }
                }
                caoZuoZhiLing = "v{'intentName':" + intengName + ",'caoZuoMoShi':" + caoZuoMoShi + "}.";
                break;
            case "shuinuan_dangweitiaojie":
                for (int i = 0; i < slotsBeans.size(); i++) {
                    if (slotsBeans.get(i).getName().equals("dangwei")) {
                        dangwei = slotsBeans.get(i).getNormValue();
                    }
                }
                caoZuoZhiLing = "v{'intentName':" + intengName + ",'dangwei':" + dangwei + "}.";
                break;
            case "chekong_wendutiaojie":
                for (int i = 0; i < slotsBeans.size(); i++) {
                    if (slotsBeans.get(i).getName().equals("chekongshebei")) {
                        cheKongSheBei = slotsBeans.get(i).getNormValue();
                    } else if (slotsBeans.get(i).getName().equals("Number")) {
                        wenDu = slotsBeans.get(i).getNormValue();
                    }
                }
                caoZuoZhiLing = "v{'intentName':" + intengName + ",'cheKongSheBei':" + cheKongSheBei + ",'wenDu':" + wenDu + "}.";
                break;
            case "chekong_qeihuandangwei":
                for (int i = 0; i < slotsBeans.size(); i++) {
                    if (slotsBeans.get(i).getName().equals("chekongshebei")) {
                        cheKongSheBei = slotsBeans.get(i).getNormValue();
                    } else if (slotsBeans.get(i).getName().equals("dangwei")) {
                        dangwei = slotsBeans.get(i).getNormValue();
                    }
                }
                caoZuoZhiLing = "v{'intentName':" + intengName + ",'cheKongSheBei':" + cheKongSheBei + ",'dangWei':" + dangwei + "}.";
                break;
        }


        //技能结束了执行发送操作
        if (resultModel.isSessionIsEnd()) {

            Log.i("语义结果", caoZuoZhiLing);
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg(caoZuoZhiLing)
                    .setQos(2).setRetained(false)
                    .setTopic(CAR_NOTIFY), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("Rair", "订阅O.成功");


                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                }
            });
        }


    }


    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    // 默认云端发音人
    public static String voicerCloud = "xiaoyan";
    // 默认本地发音人
    public static String voicerLocal = "xiaoyan";
    public static String voicerXtts = "xiaoyan";

    /**
     * 参数设置
     */
    private void setParam() {
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        //设置合成
        if (mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
            //设置使用云端引擎
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
            //设置发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicerCloud);
        } else if (mEngineType.equals(SpeechConstant.TYPE_LOCAL)) {
            //设置使用本地引擎
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
            //设置发音人资源路径
            mTts.setParameter(ResourceUtil.TTS_RES_PATH, getResourcePath());
            //设置发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicerLocal);
        } else {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_XTTS);
            //设置发音人资源路径
            mTts.setParameter(ResourceUtil.TTS_RES_PATH, getResourcePath());
            //设置发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicerXtts);
        }
        //mTts.setParameter(SpeechConstant.TTS_DATA_NOTIFY,"1");//支持实时音频流抛出，仅在synthesizeToUri条件下支持
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "50");
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        //	mTts.setParameter(SpeechConstant.STREAM_TYPE, AudioManager.STREAM_MUSIC+"");

        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");

        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");


    }


    //获取发音人资源路径
    private String getResourcePath() {
        StringBuffer tempBuffer = new StringBuffer();
        String type = "tts";
        if (mEngineType.equals(SpeechConstant.TYPE_XTTS)) {
            type = "xtts";
        }
        //合成通用资源
        tempBuffer.append(ResourceUtil.generateResourcePath(context, ResourceUtil.RESOURCE_TYPE.assets, type + "/common.jet"));
        tempBuffer.append(";");
        //发音人资源
        if (mEngineType.equals(SpeechConstant.TYPE_XTTS)) {
            tempBuffer.append(ResourceUtil.generateResourcePath(context, ResourceUtil.RESOURCE_TYPE.assets, type + "/" + voicerXtts + ".jet"));
        } else {
            tempBuffer.append(ResourceUtil.generateResourcePath(context, ResourceUtil.RESOURCE_TYPE.assets, type + "/" + voicerLocal + ".jet"));
        }

        return tempBuffer.toString();
    }

    private void setInputState(int state) {
        mState = state;
    }

    public void closeMianBan() {
        if (thread5CloseExit != null) {
            thread5CloseExit.interrupt();
            thread5CloseExit = null;
            guanBiYuYinThread = "0";
        }
        if (mIvw != null) {
            String yuYinEnable = PreferenceHelper.getInstance(context).getString(AppConfig.YUYINZHUSHOU, "0");
            if (yuYinEnable.equals("0")) {
                //  mIvw.startListening(mWakeuperListener);
                mIvw.stopListening();
            } else {
                mIvw.startListening(mWakeuperListener);
            }
        }

        stopVoiceNlp();
    }

    // 打开语音助手5秒 没有接收到语音关闭语音助手页面
    // 一次交互完成后，5秒没有接收到语音，关闭语音助手

    public void beginYuYIn() {
        if (mIvw != null) {
            mIvw.stopListening();
        }
        int code = mTts.startSpeaking("在呢", mTtsListener);
        yuYinInter.showMianBan();
//            Intent nlpIntent = new Intent(getActivity(), NlpDemo.class);
//            startActivity(nlpIntent);

        //激活唤醒，开启语音识别
        createAgent();
        startVoiceNlp();
//        if (thread5Exit != null) {
//            thread5Exit.interrupt();
//            thread5Exit = null;
//            kaiQiYuYinThread = "0";
//        } else {
//            kaiQiYuYinThread = "1";
//            thread5Exit = new Exit5Thread();
//            thread5Exit.start();
//        }

    }

    public void stopHuanXing() {
        if (mIvw != null) {
            mIvw.stopListening();
        }
        stopVoiceNlp();
    }

    //    Exit5Thread thread5Exit = null;
    private volatile String kaiQiYuYinThread = "1";//开启语音

//    private class Exit5Thread extends Thread {
//        private int i = 0;
//
//        public void run() {
//            while (kaiQiYuYinThread.equals("1")) {
//
//                try {
//                    if (i == 4) {
//                        closeMianBan();
//                        // yuYinInter.dismissMianBan();
//                        Notice n = new Notice();
//                        n.type = ConstanceValue.MSG_YUYINXIAOSHI;
//                        //  n.content = message.toString();
//                        RxBus.getDefault().sendRx(n);
//                        thread5Exit.interrupt();
//                        thread5Exit = null;
//                        kaiQiYuYinThread = "0";
//                    }
//                    i = i + 1;
//                    //UIHelper.ToastMessage(context, "第" + String.valueOf(i) + "秒");
//                    Logger.i(TAG, i + "");
//
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    break;
//
//                }
//            }
//        }
//    }


    Exit5CloseThread thread5CloseExit = null;
    private String guanBiYuYinThread = "1";//开启语音

    private class Exit5CloseThread extends Thread {
        private int i = 0;

        public void run() {
            while (guanBiYuYinThread.equals("1")) {

                try {
                    if (i == 6) {
                        closeMianBan();
                        // yuYinInter.dismissMianBan();
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_YUYINXIAOSHI;
                        //  n.content = message.toString();
                        RxBus.getDefault().sendRx(n);
                        thread5CloseExit.interrupt();
                        thread5CloseExit = null;
                        guanBiYuYinThread = "0";
                    }
                    i = i + 1;
                    //UIHelper.ToastMessage(context, "第" + String.valueOf(i) + "秒");
                    Logger.i(TAG, i + "");

                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }

    public void syncContactsSheBei() {
        if (null == mAIUIAgent) {
            showTip("AIUIAgent 为空，请先创建");
            return;
        }
        // TODO: 2021/2/22 66666
        //经过研究 初步核实认定 是数据问题 数据对了就可以上传成功
        //明天主要研究数据 对应的键值对是什么

        try {
            // 从文件中读取联系人示例数据
            //String dataStr = FucUtil.readFile(context, "data/data_contact.txt", "utf-8");
            //mNlpText.setText(dataStr);
            //UIHelper.ToastMessage(context, dataStr);

            DongTaiShiTiModel dongTaiShiTiModel = new DongTaiShiTiModel();
            dongTaiShiTiModel.setName("大熊猫");

            // dongTaiShiTiModel.setCus_room("客厅");
            String str = new Gson().toJson(dongTaiShiTiModel);
            Log.i("YuYinChuLiTool", str);


            // 数据进行no_wrap Base64编码
            String dataStrBase64 = Base64.encodeToString(str.getBytes("utf-8"), Base64.NO_WRAP);

            JSONObject syncSchemaJson = new JSONObject();
            JSONObject dataParamJson = new JSONObject();

            // 设置id_name为uid，即用户级个性化资源
            // 个性化资源使用方法可参见http://doc.xfyun.cn/aiui_mobile/的用户个性化章节
            dataParamJson.put("id_name", "uid");
            if (StringUtils.isEmpty(uid)) {
                UIHelper.ToastMessage(context, "uid不能为空");
                return;
            }
            dataParamJson.put("id_value", uid);

            // 设置res_name为联系人
            dataParamJson.put("res_name", "OS8569425439.app_device_name");

            syncSchemaJson.put("param", dataParamJson);
            syncSchemaJson.put("data", dataStrBase64);


            Log.i(TAG, "上传的设备名：  " + syncSchemaJson.toString());
            // 传入的数据一定要为utf-8编码
            byte[] syncData = syncSchemaJson.toString().getBytes("utf-8");

            // 给该次同步加上自定义tag，在返回结果中可通过tag将结果和调用对应起来
            JSONObject paramJson = new JSONObject();
            paramJson.put("tag", "sync-tag1");

            // 用schema数据同步上传联系人
            // 注：数据同步请在连接服务器之后进行，否则可能失败
            AIUIMessage syncAthena = new AIUIMessage(AIUIConstant.CMD_SYNC,
                    AIUIConstant.SYNC_DATA_SCHEMA, 0, paramJson.toString(), syncData);

            mAIUIAgent.sendMessage(syncAthena);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void syncContactsRoom() {
        if (null == mAIUIAgent) {
            showTip("AIUIAgent 为空，请先创建");
            return;
        }
        // TODO: 2021/2/22 66666
        //经过研究 初步核实认定 是数据问题 数据对了就可以上传成功
        //明天主要研究数据 对应的键值对是什么

        try {
            // 从文件中读取联系人示例数据
            //String dataStr = FucUtil.readFile(context, "data/data_contact.txt", "utf-8");
            //mNlpText.setText(dataStr);
            //UIHelper.ToastMessage(context, dataStr);

            DongTaiShiTiModel dongTaiShiTiModel = new DongTaiShiTiModel();
            dongTaiShiTiModel.setName("咸鸭蛋");

            // dongTaiShiTiModel.setCus_room("客厅");
            String str = new Gson().toJson(dongTaiShiTiModel);
            Log.i("YuYinChuLiTool", str);


            // 数据进行no_wrap Base64编码
            String dataStrBase64 = Base64.encodeToString(str.getBytes("utf-8"), Base64.NO_WRAP);

            JSONObject syncSchemaJson = new JSONObject();
            JSONObject dataParamJson = new JSONObject();

            // 设置id_name为uid，即用户级个性化资源
            // 个性化资源使用方法可参见http://doc.xfyun.cn/aiui_mobile/的用户个性化章节
            dataParamJson.put("id_name", "uid");
            if (StringUtils.isEmpty(uid)) {
                UIHelper.ToastMessage(context, "uid不能为空");
                return;
            }
            dataParamJson.put("id_value", uid);

            // 设置res_name为联系人
            dataParamJson.put("res_name", "OS8569425439.app_room");

            syncSchemaJson.put("param", dataParamJson);
            syncSchemaJson.put("data", dataStrBase64);


            Log.i(TAG, "上传的房间名：  " + syncSchemaJson.toString());
            // 传入的数据一定要为utf-8编码
            byte[] syncData = syncSchemaJson.toString().getBytes("utf-8");

            // 给该次同步加上自定义tag，在返回结果中可通过tag将结果和调用对应起来
            JSONObject paramJson = new JSONObject();
            paramJson.put("tag", "sync-tag");

            // 用schema数据同步上传联系人
            // 注：数据同步请在连接服务器之后进行，否则可能失败
            AIUIMessage syncAthena = new AIUIMessage(AIUIConstant.CMD_SYNC,
                    AIUIConstant.SYNC_DATA_SCHEMA, 0, paramJson.toString(), syncData);

            mAIUIAgent.sendMessage(syncAthena);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public void syncContactsSheBei(List<String> sheBeiList) {
        if (null == mAIUIAgent) {
            showTip("AIUIAgent 为空，请先创建");
            return;
        }
        // TODO: 2021/2/22 66666
        //经过研究 初步核实认定 是数据问题 数据对了就可以上传成功
        //明天主要研究数据 对应的键值对是什么

        try {
            // 从文件中读取联系人示例数据
            //String dataStr = FucUtil.readFile(context, "data/data_contact.txt", "utf-8");
            //mNlpText.setText(dataStr);
            //UIHelper.ToastMessage(context, dataStr);

//            DongTaiShiTiModel dongTaiShiTiModel = new DongTaiShiTiModel();
//            dongTaiShiTiModel.setName("大熊猫");
//
//            // dongTaiShiTiModel.setCus_room("客厅");
//            String str = new Gson().toJson(dongTaiShiTiModel);
//            Log.i("YuYinChuLiTool", str);

            ShuJuJieXiZhuanHuaTool shuJuJieXiZhuanHuaTool = new ShuJuJieXiZhuanHuaTool(sheBeiList);
            String str = shuJuJieXiZhuanHuaTool.jieXiShuJu();

            // 数据进行no_wrap Base64编码
            String dataStrBase64 = Base64.encodeToString(str.getBytes("utf-8"), Base64.NO_WRAP);

            JSONObject syncSchemaJson = new JSONObject();
            JSONObject dataParamJson = new JSONObject();

            // 设置id_name为uid，即用户级个性化资源
            // 个性化资源使用方法可参见http://doc.xfyun.cn/aiui_mobile/的用户个性化章节
            dataParamJson.put("id_name", "uid");
            if (StringUtils.isEmpty(uid)) {
                // UIHelper.ToastMessage(context, "uid不能为空");
                return;
            }
            dataParamJson.put("id_value", uid);

            // 设置res_name为联系人
            dataParamJson.put("res_name", "OS8569425439.app_device_name");

            syncSchemaJson.put("param", dataParamJson);
            syncSchemaJson.put("data", dataStrBase64);


            Log.i(TAG, "上传的设备名：  " + syncSchemaJson.toString());
            // 传入的数据一定要为utf-8编码
            byte[] syncData = syncSchemaJson.toString().getBytes("utf-8");

            // 给该次同步加上自定义tag，在返回结果中可通过tag将结果和调用对应起来
            JSONObject paramJson = new JSONObject();
            paramJson.put("tag", "sync-tag1");

            // 用schema数据同步上传联系人
            // 注：数据同步请在连接服务器之后进行，否则可能失败
            AIUIMessage syncAthena = new AIUIMessage(AIUIConstant.CMD_SYNC,
                    AIUIConstant.SYNC_DATA_SCHEMA, 0, paramJson.toString(), syncData);

            mAIUIAgent.sendMessage(syncAthena);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void syncContactsRoom(List<String> roomList) {
        if (null == mAIUIAgent) {
            showTip("AIUIAgent 为空，请先创建");
            return;
        }
        // TODO: 2021/2/22 66666
        //经过研究 初步核实认定 是数据问题 数据对了就可以上传成功
        //明天主要研究数据 对应的键值对是什么

        try {
            // 从文件中读取联系人示例数据
            //String dataStr = FucUtil.readFile(context, "data/data_contact.txt", "utf-8");
            //mNlpText.setText(dataStr);
            //UIHelper.ToastMessage(context, dataStr);

//            DongTaiShiTiModel dongTaiShiTiModel = new DongTaiShiTiModel();
//            dongTaiShiTiModel.setName("咸鸭蛋");
//
//            // dongTaiShiTiModel.setCus_room("客厅");
//            String str = new Gson().toJson(dongTaiShiTiModel);
//            Log.i("YuYinChuLiTool", str);

            ShuJuJieXiZhuanHuaTool shuJuJieXiZhuanHuaTool = new ShuJuJieXiZhuanHuaTool(roomList);
            String str = shuJuJieXiZhuanHuaTool.jieXiShuJu();

            Log.i(TAG, str);
            // 数据进行no_wrap Base64编码
            String dataStrBase64 = Base64.encodeToString(str.getBytes("utf-8"), Base64.NO_WRAP);

            JSONObject syncSchemaJson = new JSONObject();
            JSONObject dataParamJson = new JSONObject();

            // 设置id_name为uid，即用户级个性化资源
            // 个性化资源使用方法可参见http://doc.xfyun.cn/aiui_mobile/的用户个性化章节
            dataParamJson.put("id_name", "uid");
            if (StringUtils.isEmpty(uid)) {
                // UIHelper.ToastMessage(context, "uid不能为空");
                return;
            }
            dataParamJson.put("id_value", uid);

            // 设置res_name为联系人
            dataParamJson.put("res_name", "OS8569425439.app_room");

            syncSchemaJson.put("param", dataParamJson);
            syncSchemaJson.put("data", dataStrBase64);


            Log.i(TAG, "上传的房间名：  " + syncSchemaJson.toString());
            // 传入的数据一定要为utf-8编码
            byte[] syncData = syncSchemaJson.toString().getBytes("utf-8");

            // 给该次同步加上自定义tag，在返回结果中可通过tag将结果和调用对应起来
            JSONObject paramJson = new JSONObject();
            paramJson.put("tag", "sync-tag");

            // 用schema数据同步上传联系人
            // 注：数据同步请在连接服务器之后进行，否则可能失败
            AIUIMessage syncAthena = new AIUIMessage(AIUIConstant.CMD_SYNC,
                    AIUIConstant.SYNC_DATA_SCHEMA, 0, paramJson.toString(), syncData);

            mAIUIAgent.sendMessage(syncAthena);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    Handler handler;
    Runnable runnable;

    private void YanChi5ZhiXing() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                chaXunDaBaoZhuangTai();

            }
        };

        handler.postDelayed(runnable, 5000);
    }

    public void chaXunDaBaoZhuangTai() {
        if (null == mAIUIAgent) {
            showTip("AIUIAgent 为空，请先创建");
            return;
        }


        if (TextUtils.isEmpty(mSyncSid)) {
            showTip("sid 为空");
            return;
        }

        try {
            // 构造查询json字符串，填入同步schema数据返回的sid
            JSONObject queryJson = new JSONObject();
            queryJson.put("sid", mSyncSid);

            // 发送同步数据状态查询消息，设置arg1为schema数据类型，params为查询字符串
            AIUIMessage syncQuery = new AIUIMessage(AIUIConstant.CMD_QUERY_SYNC_STATUS,
                    AIUIConstant.SYNC_DATA_SCHEMA, 0, queryJson.toString(), null);
            mAIUIAgent.sendMessage(syncQuery);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
