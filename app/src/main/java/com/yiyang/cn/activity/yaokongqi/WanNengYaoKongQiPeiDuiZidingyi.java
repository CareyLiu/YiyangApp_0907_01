package com.yiyang.cn.activity.yaokongqi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.yaokongqi.dialog.YaokongNameDialog;
import com.yiyang.cn.activity.yaokongqi.dialog.YaokongPeiDialog;
import com.yiyang.cn.activity.yaokongqi.model.YaokongKeyModel;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;
import com.yiyang.cn.util.SoundPoolUtils;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class WanNengYaoKongQiPeiDuiZidingyi extends BaseActivity {


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
        return R.layout.layout_wanneng_yaokongqi_peidui_zidingyi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("电视遥控器配网");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void actionStart(Context context, String shebeiMa, List<YaokongKeyModel> keyModels) {
        Intent intent = new Intent(context, WanNengYaoKongQiPeiDuiZidingyi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("shebeiMa", shebeiMa);
        intent.putExtra("keyModels", (Serializable) keyModels);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
        initHuidiao();
        initDialog();
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
                            if (shebeiMaTwo.equals("28")) {
                                if (keyCode.equals("14")) {
                                    llKey1.setEnabled(false);
                                    llKey1.setTextColor(Y.getColor(R.color.color_main));
                                    llKey1.setText(keyName);
                                    tvKey1.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "14", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("15")) {
                                    llKey2.setEnabled(false);
                                    llKey2.setTextColor(Y.getColor(R.color.color_main));
                                    llKey2.setText(keyName);
                                    tvKey2.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "15", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("16")) {
                                    llKey3.setEnabled(false);
                                    llKey3.setTextColor(Y.getColor(R.color.color_main));
                                    llKey3.setText(keyName);
                                    tvKey3.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "16", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("17")) {
                                    llKey4.setEnabled(false);
                                    llKey4.setTextColor(Y.getColor(R.color.color_main));
                                    llKey4.setText(keyName);
                                    tvKey4.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "17", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("18")) {
                                    llKey5.setEnabled(false);
                                    llKey5.setTextColor(Y.getColor(R.color.color_main));
                                    llKey5.setText(keyName);
                                    tvKey5.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "18", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("06")) {
                                    llKey6.setEnabled(false);
                                    llKey6.setTextColor(Y.getColor(R.color.color_main));
                                    llKey6.setText(keyName);
                                    tvKey6.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "06", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                }
                            } else if (shebeiMaTwo.equals("38")) {//空气净化
                                if (keyCode.equals("11")) {
                                    llKey1.setEnabled(false);
                                    llKey1.setTextColor(Y.getColor(R.color.color_main));
                                    llKey1.setText(keyName);
                                    tvKey1.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "11", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("12")) {
                                    llKey2.setEnabled(false);
                                    llKey2.setTextColor(Y.getColor(R.color.color_main));
                                    llKey2.setText(keyName);
                                    tvKey2.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "12", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("13")) {
                                    llKey3.setEnabled(false);
                                    llKey3.setTextColor(Y.getColor(R.color.color_main));
                                    llKey3.setText(keyName);
                                    tvKey3.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "13", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("14")) {
                                    llKey4.setEnabled(false);
                                    llKey4.setTextColor(Y.getColor(R.color.color_main));
                                    llKey4.setText(keyName);
                                    tvKey4.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "14", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("15")) {
                                    llKey5.setEnabled(false);
                                    llKey5.setTextColor(Y.getColor(R.color.color_main));
                                    llKey5.setText(keyName);
                                    tvKey5.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "15", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("16")) {
                                    llKey6.setEnabled(false);
                                    llKey6.setTextColor(Y.getColor(R.color.color_main));
                                    llKey6.setText(keyName);
                                    tvKey6.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "16", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                }
                            } else {//空调
                                if (keyCode.equals("08")) {
                                    llKey1.setEnabled(false);
                                    llKey1.setTextColor(Y.getColor(R.color.color_main));
                                    llKey1.setText(keyName);
                                    tvKey1.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "08", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("09")) {
                                    llKey2.setEnabled(false);
                                    llKey2.setTextColor(Y.getColor(R.color.color_main));
                                    llKey2.setText(keyName);
                                    tvKey2.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "09", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("10")) {
                                    llKey3.setEnabled(false);
                                    llKey3.setTextColor(Y.getColor(R.color.color_main));
                                    llKey3.setText(keyName);
                                    tvKey3.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "10", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("11")) {
                                    llKey4.setEnabled(false);
                                    llKey4.setTextColor(Y.getColor(R.color.color_main));
                                    llKey4.setText(keyName);
                                    tvKey4.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "11", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("12")) {
                                    llKey5.setEnabled(false);
                                    llKey5.setTextColor(Y.getColor(R.color.color_main));
                                    llKey5.setText(keyName);
                                    tvKey5.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "12", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                } else if (keyCode.equals("13")) {
                                    llKey6.setEnabled(false);
                                    llKey6.setTextColor(Y.getColor(R.color.color_main));
                                    llKey6.setText(keyName);
                                    tvKey6.setText(keyName);

                                    YaokongKeyModel keyModel = new YaokongKeyModel(shebeiMa + "13", keyName, "1");
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI;
                                    notice.content = keyModel;
                                    sendRx(notice);

                                    tishiDialog.show();
                                }
                            }
                        } else {
                            tishiDialog.setTextContent(keyName + "键配对失败，请重新配对!");
                            tishiDialog.show();
                        }

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

        keyModels = (List<YaokongKeyModel>) getIntent().getSerializableExtra("keyModels");
        shebeiMa = getIntent().getStringExtra("shebeiMa");
        shebeiMaTwo = shebeiMa.substring(0, 2);

        initView();
    }

    private void initView() {
        if (shebeiMa.equals("28")) {
            for (int i = 12; i < keyModels.size(); i++) {
                YaokongKeyModel keyModel = keyModels.get(i);
                String mark_id = keyModel.getMark_id();
                String mark_status = keyModel.getMark_status();
                String mark_name = keyModel.getMark_name();
                if (mark_status.equals("1")) {
                    if (mark_id.equals("14")) {
                        llKey1.setEnabled(false);
                        llKey1.setTextColor(Y.getColor(R.color.color_main));
                        llKey1.setText(mark_name);
                        tvKey1.setText(mark_name);
                    } else if (mark_id.equals("15")) {
                        llKey2.setEnabled(false);
                        llKey2.setTextColor(Y.getColor(R.color.color_main));
                        llKey2.setText(mark_name);
                        tvKey2.setText(mark_name);
                    } else if (mark_id.equals("16")) {
                        llKey3.setEnabled(false);
                        llKey3.setTextColor(Y.getColor(R.color.color_main));
                        llKey3.setText(mark_name);
                        tvKey3.setText(mark_name);
                    } else if (mark_id.equals("17")) {
                        llKey4.setEnabled(false);
                        llKey4.setTextColor(Y.getColor(R.color.color_main));
                        llKey4.setText(mark_name);
                        tvKey4.setText(mark_name);
                    } else if (mark_id.equals("18")) {
                        llKey5.setEnabled(false);
                        llKey5.setTextColor(Y.getColor(R.color.color_main));
                        llKey5.setText(mark_name);
                        tvKey5.setText(mark_name);
                    } else if (mark_id.equals("19")) {
                        llKey6.setEnabled(false);
                        llKey6.setTextColor(Y.getColor(R.color.color_main));
                        llKey6.setText(mark_name);
                        tvKey6.setText(mark_name);
                    }
                }
            }
        } else if (shebeiMa.equals("38")) {
            for (int i = 11; i < keyModels.size(); i++) {
                YaokongKeyModel keyModel = keyModels.get(i);
                String mark_id = keyModel.getMark_id();
                String mark_status = keyModel.getMark_status();
                String mark_name = keyModel.getMark_name();
                if (mark_status.equals("1")) {
                    if (mark_id.equals("11")) {
                        llKey1.setEnabled(false);
                        llKey1.setTextColor(Y.getColor(R.color.color_main));
                        llKey1.setText(mark_name);
                        tvKey1.setText(mark_name);
                    } else if (mark_id.equals("12")) {
                        llKey2.setEnabled(false);
                        llKey2.setTextColor(Y.getColor(R.color.color_main));
                        llKey2.setText(mark_name);
                        tvKey2.setText(mark_name);
                    } else if (mark_id.equals("13")) {
                        llKey3.setEnabled(false);
                        llKey3.setTextColor(Y.getColor(R.color.color_main));
                        llKey3.setText(mark_name);
                        tvKey3.setText(mark_name);
                    } else if (mark_id.equals("14")) {
                        llKey4.setEnabled(false);
                        llKey4.setTextColor(Y.getColor(R.color.color_main));
                        llKey4.setText(mark_name);
                        tvKey4.setText(mark_name);
                    } else if (mark_id.equals("15")) {
                        llKey5.setEnabled(false);
                        llKey5.setTextColor(Y.getColor(R.color.color_main));
                        llKey5.setText(mark_name);
                        tvKey5.setText(mark_name);
                    } else if (mark_id.equals("16")) {
                        llKey6.setEnabled(false);
                        llKey6.setTextColor(Y.getColor(R.color.color_main));
                        llKey6.setText(mark_name);
                        tvKey6.setText(mark_name);
                    }
                }
            }
        } else {
            for (int i = 5; i < keyModels.size(); i++) {
                YaokongKeyModel keyModel = keyModels.get(i);
                String mark_id = keyModel.getMark_id();
                String mark_status = keyModel.getMark_status();
                String mark_name = keyModel.getMark_name();
                if (mark_status.equals("1")) {
                    if (mark_id.equals("08")) {
                        llKey1.setEnabled(false);
                        llKey1.setTextColor(Y.getColor(R.color.color_main));
                        llKey1.setText(mark_name);
                        tvKey1.setText(mark_name);
                    } else if (mark_id.equals("09")) {
                        llKey2.setEnabled(false);
                        llKey2.setTextColor(Y.getColor(R.color.color_main));
                        llKey2.setText(mark_name);
                        tvKey2.setText(mark_name);
                    } else if (mark_id.equals("10")) {
                        llKey3.setEnabled(false);
                        llKey3.setTextColor(Y.getColor(R.color.color_main));
                        llKey3.setText(mark_name);
                        tvKey3.setText(mark_name);
                    } else if (mark_id.equals("11")) {
                        llKey4.setEnabled(false);
                        llKey4.setTextColor(Y.getColor(R.color.color_main));
                        llKey4.setText(mark_name);
                        tvKey4.setText(mark_name);
                    } else if (mark_id.equals("12")) {
                        llKey5.setEnabled(false);
                        llKey5.setTextColor(Y.getColor(R.color.color_main));
                        llKey5.setText(mark_name);
                        tvKey5.setText(mark_name);
                    } else if (mark_id.equals("13")) {
                        llKey6.setEnabled(false);
                        llKey6.setTextColor(Y.getColor(R.color.color_main));
                        llKey6.setText(mark_name);
                        tvKey6.setText(mark_name);
                    }
                }
            }
        }
    }

    private void initDialog() {
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

    @OnClick({R.id.ll_key1, R.id.ll_key2, R.id.ll_key3, R.id.ll_key4, R.id.ll_key5, R.id.ll_key6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_key1:
                if (shebeiMaTwo.equals("28")) {
                    showNameDialog("14");
                } else if (shebeiMaTwo.equals("38")) {
                    showNameDialog("11");

                } else {
                    showNameDialog("08");
                }
                break;
            case R.id.ll_key2:
                if (shebeiMaTwo.equals("28")) {
                    showNameDialog("15");
                } else if (shebeiMaTwo.equals("38")) {
                    showNameDialog("12");
                } else {
                    showNameDialog("09");
                }
                break;
            case R.id.ll_key3:
                if (shebeiMaTwo.equals("28")) {
                    showNameDialog("16");
                } else if (shebeiMaTwo.equals("38")) {
                    showNameDialog("13");
                } else {
                    showNameDialog("10");
                }
                break;
            case R.id.ll_key4:
                if (shebeiMaTwo.equals("28")) {
                    showNameDialog("17");
                } else if (shebeiMaTwo.equals("38")) {
                    showNameDialog("14");
                } else {
                    showNameDialog("11");
                }
                break;
            case R.id.ll_key5:
                if (shebeiMaTwo.equals("28")) {
                    showNameDialog("18");
                } else if (shebeiMaTwo.equals("38")) {
                    showNameDialog("15");
                } else {
                    showNameDialog("12");
                }
                break;
            case R.id.ll_key6:
                if (shebeiMaTwo.equals("28")) {
                    showNameDialog("19");
                } else if (shebeiMaTwo.equals("38")) {
                    showNameDialog("16");
                } else {
                    showNameDialog("13");
                }
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
