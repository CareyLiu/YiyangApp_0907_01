package com.yiyang.cn.activity.yaokongqi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.yaokongqi.dialog.YaokongPeiDialog;
import com.yiyang.cn.activity.yaokongqi.dialog.YaokongPeiFirstDialog;
import com.yiyang.cn.activity.yaokongqi.model.YaokongKeyModel;
import com.yiyang.cn.activity.yaokongqi.model.YaokongTagModel;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;
import com.yiyang.cn.util.SoundPoolUtils;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.app.ConstanceValue.MSG_PEIWANG_SUCCESS;
import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class YaokongKTPei extends BaseActivity {

    @BindView(R.id.ll_zidingyi)
    TextView ll_zidingyi;
    @BindView(R.id.iv_dianyuan)
    ImageView iv_dianyuan;
    @BindView(R.id.ll_dianyuan)
    LinearLayout ll_dianyuan;
    @BindView(R.id.iv_moshi)
    ImageView iv_moshi;
    @BindView(R.id.ll_moshi)
    LinearLayout ll_moshi;
    @BindView(R.id.iv_fengsu)
    ImageView iv_fengsu;
    @BindView(R.id.ll_fengsu)
    LinearLayout ll_fengsu;
    @BindView(R.id.tv_wendu_add)
    ImageView tv_wendu_add;
    @BindView(R.id.ll_wendu_add)
    LinearLayout ll_wendu_add;
    @BindView(R.id.tv_wendu_jian)
    ImageView tv_wendu_jian;
    @BindView(R.id.ll_wendu_jian)
    LinearLayout ll_wendu_jian;
    @BindView(R.id.iv_fengxiang)
    ImageView iv_fengxiang;
    @BindView(R.id.ll_fengxiang)
    LinearLayout ll_fengxiang;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv_fengxiang)
    TextView tvFengxiang;
    @BindView(R.id.iv_off)
    ImageView ivOff;
    @BindView(R.id.ll_off)
    LinearLayout llOff;

    private String shebeiMa;
    private String keyMa;
    private String keyName;

    private YaokongPeiFirstDialog firstDialog;
    private YaokongPeiDialog peiDialog;
    private TishiDialog tishiDialog;

    private ZnjjMqttMingLing znjjMqttMingLing;
    private String ccid;
    private String serverId;
    private String topic;
    private List<YaokongKeyModel> keyModels;

    @Override
    public int getContentViewResId() {
        return R.layout.layout_wanneng_yaokongqi_peidui_kongtiao;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("空调遥控器配网");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("保存");
        tv_rightTitle.setTextSize(17);
        tv_rightTitle.setTextColor(Y.getColor(R.color.color_main));
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePeidui();
            }
        });


        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, YaokongKTPei.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void savePeidui() {
        boolean isOK = false;
        for (int i = 0; i < keyModels.size(); i++) {
            YaokongKeyModel keyModel = keyModels.get(i);
            String mark_status = keyModel.getMark_status();
            if (mark_status.equals("1")) {
                isOK = true;
            }
        }

        if (!isOK) {
            Y.t("请先配对");
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "16072");
        jsonObject.put("key", Urls.key);
        jsonObject.put("token", UserManager.getManager(mContext).getAppToken());
        jsonObject.put("label_header", shebeiMa);
        jsonObject.put("ccid", ccid);
        jsonObject.put("pro", keyModels);
        OkGo.<YaokongTagModel>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(jsonObject.toJSONString())
                .execute(new JsonCallback<YaokongTagModel>() {
                    @Override
                    public void onSuccess(Response<YaokongTagModel> response) {
                        if (response.body().getMsg_code().equals("0000")) {
                            TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
                                @Override
                                public void onClickCancel(View v, TishiDialog dialog) {

                                }

                                @Override
                                public void onClickConfirm(View v, TishiDialog dialog) {

                                }

                                @Override
                                public void onDismiss(TishiDialog dialog) {
                                    Notice notice = new Notice();
                                    notice.type = MSG_PEIWANG_SUCCESS;
                                    RxBus.getDefault().sendRx(notice);
                                    finish();
                                }
                            });
                            dialog.setTextContent("遥控器配对成功");
                            dialog.show();
                        } else {
                            Y.t(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<YaokongTagModel> response) {
                        Y.tError(response);
                    }

                    @Override
                    public void onStart(Request<YaokongTagModel, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    private void back() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                String mingLingMa = "M22" + shebeiMa + ".";
                znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
                finish();
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.setTextContent("确定退出配对么，未保存的的数据将消失！");
        dialog.setTextConfirm("确定");
        dialog.setTextCancel("取消");
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initMqtt();
        initDialog();
        initHuidiao();
        getnet();
    }

    private void initKeyModels() {
        keyModels = new ArrayList<>();
        keyModels.add(new YaokongKeyModel(shebeiMa + "01", "模式", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "02", "开", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "03", "温度加", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "04", "温度减", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "05", "风速", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "06", "风向", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "07", "关", "0"));
    }

    private void initMqtt() {
        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
        topic = "zn/hardware/" + serverId + ccid;
        znjjMqttMingLing.yaoKongQiPeiDui(topic, "M1737.", new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("rair", "发送配电视命令" + topic);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
        znjjMqttMingLing.subscribeLastAppShiShiXinXi_WithCanShu(ccid, serverId, new IMqttActionListener() {
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

    private void initDialog() {
        firstDialog = new YaokongPeiFirstDialog(mContext);
        firstDialog.show();

        peiDialog = new YaokongPeiDialog(mContext);

        tishiDialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {

            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        tishiDialog.setTextTitle("提示");
        tishiDialog.setTextCancel("");
        tishiDialog.setTextConfirm("确定");
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI) {
                    String msg = message.content.toString();
                    String shebeiCode = msg.substring(3, 7);
                    String keyCode = msg.substring(7, 9);
                    String isOk = msg.substring(9, 10);
                    if (shebeiMa.equals(shebeiCode)) {
                        peiDialog.dismiss();
                        if (isOk.equals("1")) {
                            SoundPoolUtils.soundPool(mContext, R.raw.hongwai_learn_suc);
                            tishiDialog.setTextContent(keyName + "键配对成功!");
                            if (keyCode.equals("01")) {
                                iv_moshi.setImageResource(R.mipmap.yaokong_icon_moshi_blue);
                                ll_moshi.setEnabled(false);
                                YaokongKeyModel keyModel = keyModels.get(0);

                                keyModel.setMark_status("1");
                                keyModels.set(0, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("02")) {
                                iv_dianyuan.setImageResource(R.mipmap.yaokong_icon_guanbi_blue);
                                ll_dianyuan.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(1);
                                keyModel.setMark_status("1");
                                keyModels.set(1, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("05")) {
                                iv_fengsu.setImageResource(R.mipmap.yaokong_icon_fengsu_blue);
                                ll_fengsu.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(4);
                                keyModel.setMark_status("1");
                                keyModels.set(4, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("06")) {
                                iv_fengxiang.setImageResource(R.mipmap.yaokong_icon_fengxiang_blue);
                                ll_fengxiang.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(5);
                                keyModel.setMark_status("1");
                                keyModels.set(5, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("03")) {
                                tv_wendu_add.setImageResource(R.mipmap.yaokong_icon_add_blue);
                                ll_wendu_add.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(2);
                                keyModel.setMark_status("1");
                                keyModels.set(2, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("04")) {
                                tv_wendu_jian.setImageResource(R.mipmap.yaokong_icon_reduce_blue);
                                ll_wendu_jian.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(3);
                                keyModel.setMark_status("1");
                                keyModels.set(3, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("07")) {
                                ivOff.setImageResource(R.mipmap.yaokong_icon_guanbi_blue);
                                llOff.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(6);
                                keyModel.setMark_status("1");
                                keyModels.set(6, keyModel);
                                tishiDialog.show();
                            }
                        } else {
                            tishiDialog.setTextContent(keyName + "键配对失败，请重新配对!");
                            tishiDialog.show();
                        }
                    }
                } else if (message.type == ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI) {
                    YaokongKeyModel keyModel = (YaokongKeyModel) message.content;
                    keyModels.add(keyModel);
                }
            }
        }));
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16071");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_type", "37");
        map.put("ccid", ccid);
        Gson gson = new Gson();
        OkGo.<YaokongTagModel>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<YaokongTagModel>() {
                    @Override
                    public void onSuccess(Response<YaokongTagModel> response) {
                        shebeiMa = response.body().getLabel_header();
                        initKeyModels();
                    }

                    @Override
                    public void onError(Response<YaokongTagModel> response) {
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                        shebeiMa = "";
                    }

                    @Override
                    public void onStart(Request<YaokongTagModel, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    @OnClick({R.id.ll_zidingyi, R.id.ll_dianyuan, R.id.ll_moshi, R.id.ll_fengsu, R.id.ll_wendu_add, R.id.ll_wendu_jian, R.id.ll_fengxiang, R.id.ll_off})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zidingyi:
                WanNengYaoKongQiPeiDuiZidingyi.actionStart(mContext, shebeiMa, keyModels);
                break;
            case R.id.ll_dianyuan:
                clickKey("开关开", "02");
                break;
            case R.id.ll_moshi:
                clickKey("模式键", "01");
                break;
            case R.id.ll_fengsu:
                clickKey("风速键", "05");
                break;
            case R.id.ll_wendu_add:
                clickKey("温度加键", "03");
                break;
            case R.id.ll_wendu_jian:
                clickKey("温度减键", "04");
                break;
            case R.id.ll_fengxiang:
                clickKey("风向加键", "06");
                break;
            case R.id.ll_off:
                clickKey("开关关", "07");
                break;

        }
    }

    private void clickKey(String key, String minglingma) {
        keyName = key;
        keyMa = minglingma;

        peiDialog.setKey(key);
        peiDialog.show();

        sendMsg(minglingma);

        SoundPoolUtils.soundPool(mContext, R.raw.hongwai_learn_voice);
    }


    private void sendMsg(String code) {
        String mingLingMa = "M19" + shebeiMa + code + ".";
        znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        back();
    }
}
