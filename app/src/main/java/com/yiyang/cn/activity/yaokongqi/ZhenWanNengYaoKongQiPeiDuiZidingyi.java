package com.yiyang.cn.activity.yaokongqi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.yaokongqi.dialog.YaokongNameDialog;
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

import java.io.Serializable;
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
import static com.yiyang.cn.get_net.Urls.key;

public class ZhenWanNengYaoKongQiPeiDuiZidingyi extends BaseActivity {


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

    private List<YaokongKeyModel> keyModels;

    private String shebeiMa;
    private String keyMa;
    private String keyName;
    private YaokongPeiDialog peiDialog;
    private TishiDialog tishiDialog;

    private ZnjjMqttMingLing znjjMqttMingLing;
    private String ccid;
    private String serverId;
    private String topic;
    private String shebeiMaTwo;

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

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ZhenWanNengYaoKongQiPeiDuiZidingyi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initMqtt();
        init();
        initHuidiao();
        initDialog();
        getnet();
    }

    YaokongPeiFirstDialog firstDialog;

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

    private void initMqtt() {
        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
        topic = "zn/hardware/" + serverId + ccid;
        znjjMqttMingLing.yaoKongQiPeiDui(topic, "M1739.", new IMqttActionListener() {
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

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16071");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_type", "39");
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

    private void initKeyModels() {
        keyModels = new ArrayList<>();
        keyModels.add(new YaokongKeyModel(shebeiMa + "01", "自定义", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "02", "自定义", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "03", "自定义", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "04", "自定义", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "05", "自定义", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "06", "自定义", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "07", "自定义", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "08", "自定义", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "09", "自定义", "0"));

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
                                llKey1.setEnabled(false);
                                llKey1.setTextColor(Y.getColor(R.color.color_main));
                                llKey1.setText(keyName);
                                tvKey1.setText(keyName);

                                YaokongKeyModel keyModel = keyModels.get(0);
                                keyModel.setMark_name(keyName);
                                keyModel.setMark_status("1");
                                keyModels.set(0, keyModel);
                                tishiDialog.show();

                            } else if (keyCode.equals("02")) {
                                llKey2.setEnabled(false);
                                llKey2.setTextColor(Y.getColor(R.color.color_main));
                                llKey2.setText(keyName);
                                tvKey2.setText(keyName);

                                YaokongKeyModel keyModel = keyModels.get(1);
                                keyModel.setMark_name(keyName);
                                keyModel.setMark_status("1");
                                keyModels.set(1, keyModel);
                                tishiDialog.show();


                            } else if (keyCode.equals("03")) {
                                llKey3.setEnabled(false);
                                llKey3.setTextColor(Y.getColor(R.color.color_main));
                                llKey3.setText(keyName);
                                tvKey3.setText(keyName);

                                YaokongKeyModel keyModel = keyModels.get(2);
                                keyModel.setMark_name(keyName);
                                keyModel.setMark_status("1");
                                keyModels.set(2, keyModel);
                                tishiDialog.show();


                            } else if (keyCode.equals("04")) {
                                llKey4.setEnabled(false);
                                llKey4.setTextColor(Y.getColor(R.color.color_main));
                                llKey4.setText(keyName);
                                tvKey4.setText(keyName);


                                YaokongKeyModel keyModel = keyModels.get(3);
                                keyModel.setMark_name(keyName);
                                keyModel.setMark_status("1");
                                keyModels.set(3, keyModel);
                                tishiDialog.show();

                            } else if (keyCode.equals("05")) {
                                llKey5.setEnabled(false);
                                llKey5.setTextColor(Y.getColor(R.color.color_main));
                                llKey5.setText(keyName);
                                tvKey5.setText(keyName);

                                YaokongKeyModel keyModel = keyModels.get(4);
                                keyModel.setMark_name(keyName);
                                keyModel.setMark_status("1");
                                keyModels.set(4, keyModel);
                                tishiDialog.show();

                            } else if (keyCode.equals("06")) {
                                llKey6.setEnabled(false);
                                llKey6.setTextColor(Y.getColor(R.color.color_main));
                                llKey6.setText(keyName);
                                tvKey6.setText(keyName);

                                YaokongKeyModel keyModel = keyModels.get(5);
                                keyModel.setMark_name(keyName);
                                keyModel.setMark_status("1");
                                keyModels.set(5, keyModel);
                                tishiDialog.show();

                            } else if (keyCode.equals("07")) {
                                llKey7.setEnabled(false);
                                llKey7.setTextColor(Y.getColor(R.color.color_main));
                                llKey7.setText(keyName);
                                tvKey7.setText(keyName);

                                YaokongKeyModel keyModel = keyModels.get(6);
                                keyModel.setMark_name(keyName);
                                keyModel.setMark_status("1");
                                keyModels.set(6, keyModel);
                                tishiDialog.show();

                            } else if (keyCode.equals("08")) {
                                llKey8.setEnabled(false);
                                llKey8.setTextColor(Y.getColor(R.color.color_main));
                                llKey8.setText(keyName);
                                tvKey8.setText(keyName);

                                YaokongKeyModel keyModel = keyModels.get(7);
                                keyModel.setMark_name(keyName);
                                keyModel.setMark_status("1");
                                keyModels.set(7, keyModel);
                                tishiDialog.show();

                            } else if (keyCode.equals("09")) {
                                llKey9.setEnabled(false);
                                llKey9.setTextColor(Y.getColor(R.color.color_main));
                                llKey9.setText(keyName);
                                tvKey9.setText(keyName);

                                YaokongKeyModel keyModel = keyModels.get(8);
                                keyModel.setMark_name(keyName);
                                keyModel.setMark_status("1");
                                keyModels.set(8, keyModel);
                                tishiDialog.show();
                            }

                        }
                    } else {
                        tishiDialog.setTextContent(keyName + "键配对失败，请重新配对!");
                        tishiDialog.show();
                    }

                }
            }

        }));
    }

    private void init() {
        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
        topic = "zn/hardware/" + serverId + ccid;

        keyModels = new ArrayList<>();
        shebeiMa = getIntent().getStringExtra("shebeiMa");
        //shebeiMaTwo = shebeiMa.substring(0, 2);

        initView();
    }

    private void initView() {
    }


    @OnClick({R.id.ll_key1, R.id.ll_key2, R.id.ll_key3, R.id.ll_key4, R.id.ll_key5, R.id.ll_key6, R.id.ll_key7, R.id.ll_key8, R.id.ll_key9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_key1:
                showNameDialog("01");
                break;
            case R.id.ll_key2:
                showNameDialog("02");
                break;
            case R.id.ll_key3:
                showNameDialog("03");
                break;
            case R.id.ll_key4:
                showNameDialog("04");
                break;
            case R.id.ll_key5:
                showNameDialog("05");
                break;
            case R.id.ll_key6:
                showNameDialog("06");
                break;
            case R.id.ll_key7:
                showNameDialog("07");
                break;
            case R.id.ll_key8:
                showNameDialog("08");
                break;
            case R.id.ll_key9:
                showNameDialog("09");
                break;
        }
    }

    private void showNameDialog(String code) {
        YaokongNameDialog nameDialog = new YaokongNameDialog(mContext);
        nameDialog.setClickSure(new YaokongNameDialog.ClickSure() {
            @Override
            public void clickSure() {
                String text = nameDialog.getText();
                if (TextUtils.isEmpty(text)) {
                    Y.t("请输入按键名称");
                    return;
                }
                setKeyText(code, text);
                nameDialog.dismiss();
            }
        });
        nameDialog.show();
    }

    private void setKeyText(String code, String name) {
        keyName = name;
        keyMa = code;

        peiDialog.setKey(keyName);
        peiDialog.show();

        sendMsg(code);
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
}
