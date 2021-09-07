package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.ZhiNengFamilyAddDIalog;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
import com.yiyang.cn.model.ZhiNengJiaJuKaiGuanModel;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;
import com.yiyang.cn.util.DoMqttValue;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengJiaJuKaiGuanTwoActivity extends BaseActivity {
    @BindView(R.id.view_switch_left)
    View viewSwitchLeft;
    @BindView(R.id.rl_switch_left)
    RelativeLayout rlSwitchLeft;
    @BindView(R.id.tv_kaiguan_mode)
    TextView tvKaiguanMode;
    @BindView(R.id.tv_beiguang_zhuangtai)
    TextView tvBeiguangZhuangtai;
    @BindView(R.id.ll_quankai)
    LinearLayout llQuankai;
    @BindView(R.id.ll_quanguan)
    LinearLayout llQuanguan;


    String device_id;
    @BindView(R.id.view_switch_right)
    View viewSwitchRight;
    @BindView(R.id.rl_switch_right)
    RelativeLayout rlSwitchRight;
    @BindView(R.id.iv_quankai)
    ImageView ivQuankai;
    @BindView(R.id.tv_quankai)
    TextView tvQuankai;
    @BindView(R.id.iv_quanguan)
    ImageView ivQuanguan;
    @BindView(R.id.tv_quanguan)
    TextView tvQuanguan;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;

    private String kaiGuanZhuagnTaiLeft = "0";//0 关 1开
    private String kaiGuanZhuangTaiRight = "0";//0关 1开


    private String device_ccid;
    private String device_ccid_up;
    private String serverId;

    private String leftZhuangZhiId;//左侧的装置id
    private String rightZhuangZhiId;//右侧的装置id
    private String member_type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.ZHINENGJIAJU);
        device_ccid = getIntent().getStringExtra("device_ccid");
        device_ccid_up = getIntent().getStringExtra("device_ccid_up");
        serverId = getIntent().getStringExtra("serverId");
        member_type = getIntent().getStringExtra("member_type");
        getnet();

        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
        znjjMqttMingLing.setTopic(serverId, device_ccid_up);

        znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(device_ccid_up, serverId, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        rlSwitchLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kaiGuanZhuagnTaiLeft.equals("0")) {
                    daKaiKaiGuan("1", leftZhuangZhiId);
                    kaiGuanZhuagnTaiLeft = "1";
                    viewSwitchLeft.setSelected(true);
                } else {
                    daKaiKaiGuan("0", leftZhuangZhiId);
                    kaiGuanZhuagnTaiLeft = "0";
                    viewSwitchLeft.setSelected(false);
                }
            }
        });

        rlSwitchRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kaiGuanZhuangTaiRight.equals("0")) {
                    daKaiKaiGuan("1", rightZhuangZhiId);
                    kaiGuanZhuangTaiRight = "1";
                    viewSwitchRight.setSelected(true);
                } else {
                    daKaiKaiGuan("0", rightZhuangZhiId);
                    kaiGuanZhuangTaiRight = "0";
                    viewSwitchRight.setSelected(false);
                }
            }
        });

        llQuankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuanKaiQuanGuanBac("1", R.mipmap.tuya_icon_switch_quankai, R.mipmap.kaiguan_pic_quankai_close, R.mipmap.kaiguan_pic_quanguan_open, R.mipmap.tuya_icon_switch_quanguan);
                znjjMqttMingLing.setZhiNengKaiGuan(leftZhuangZhiId + "012_" + rightZhuangZhiId, "012", new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            }
        });

        llQuanguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuanKaiQuanGuanBac("0", R.mipmap.tuya_icon_switch_quankai, R.mipmap.kaiguan_pic_quankai_close, R.mipmap.kaiguan_pic_quanguan_open, R.mipmap.tuya_icon_switch_quanguan);
                znjjMqttMingLing.setZhiNengKaiGuan(leftZhuangZhiId + "022_" + rightZhuangZhiId, "022", new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });

            }
        });
        setQuanKaiQuanGuanBac("3", R.mipmap.tuya_icon_switch_quankai, R.mipmap.kaiguan_pic_quankai_close, R.mipmap.kaiguan_pic_quanguan_open, R.mipmap.tuya_icon_switch_quanguan);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME) {
                    if (chooseTiaoMu == 1) {
                        changeDevice((String) message.content, tvLeft, chooseTiaoMu);
                    } else if (chooseTiaoMu == 2) {
                        changeDevice((String) message.content, tvRight, chooseTiaoMu);
                    }
                } else if (message.type == ConstanceValue.MSG_SHEBEIZHUANGTAI) {
                    getnet();
                } else if (message.type == ConstanceValue.MSG_KAIGUAN_DELETE) {
                    finish();
                }

            }
        }));
        rlSwitchLeft.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                chooseTiaoMu = 1;
                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(mContext, ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME);
                zhiNengFamilyAddDIalog.show();
                return false;
            }
        });

        rlSwitchRight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                chooseTiaoMu = 2;
                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(mContext, ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME);
                zhiNengFamilyAddDIalog.show();
                return false;
            }
        });
    }

    public int chooseTiaoMu;

    // 1全开 2全关
    public void setQuanKaiQuanGuanBac(String str, int quanKaiSrc1, int quanKaiSrc0, int quanGuanScr1, int quanGuanScr0) {
        if (str.equals("1")) {
            ivQuankai.setBackgroundResource(quanKaiSrc1);
            ivQuanguan.setBackgroundResource(quanGuanScr0);
            tvQuankai.setTextColor(mContext.getResources().getColor(R.color.blue_ff3a85f8));
            tvQuanguan.setTextColor(mContext.getResources().getColor(R.color.black_333333));
        } else if (str.equals("0")) {
            ivQuankai.setBackgroundResource(quanKaiSrc0);
            ivQuanguan.setBackgroundResource(quanGuanScr1);

            tvQuankai.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvQuanguan.setTextColor(mContext.getResources().getColor(R.color.blue_ff3a85f8));
        } else {
            ivQuankai.setBackgroundResource(quanKaiSrc0);
            ivQuanguan.setBackgroundResource(quanGuanScr0);
            tvQuankai.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvQuanguan.setTextColor(mContext.getResources().getColor(R.color.black_333333));
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.zhinengkaiguan_two;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("智能开关");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setImageResource(R.mipmap.mine_shezhi);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KaiGuanSettingActivity.actionStart(mContext, device_ccid, device_ccid_up,member_type);
            }
        });
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_ccid, String device_ccid_up, String serverId, String member_type) {
        Intent intent = new Intent(context, ZhiNengJiaJuKaiGuanTwoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        intent.putExtra("device_ccid_up", device_ccid_up);
        intent.putExtra("serverId", serverId);
        intent.putExtra("member_type", member_type);
        context.startActivity(intent);
    }

    /**
     * @param str1 0 关 1开
     * @param str2 0 关 1开
     */
    private void setKaiGuanZhuangTai(String str1, String str2) {
        if (str1.equals("0")) {
            viewSwitchLeft.setSelected(false);
            kaiGuanZhuagnTaiLeft = "0";
        } else {
            viewSwitchLeft.setSelected(true);
            kaiGuanZhuagnTaiLeft = "1";
        }
        if (str2.equals("0")) {
            viewSwitchRight.setSelected(false);
            kaiGuanZhuagnTaiLeft = "0";
        } else {
            viewSwitchRight.setSelected(true);
            kaiGuanZhuangTaiRight = "1";
        }
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16068");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("device_ccid_up", device_ccid_up);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>> response) {
                        showLoadSuccess();

                        leftZhuangZhiId = response.body().data.get(0).getDevice_list().get(0).getDevice_ccid();
                        rightZhuangZhiId = response.body().data.get(0).getDevice_list().get(1).getDevice_ccid();
                        //1开 2 关
                        if (response.body().data.get(0).getDevice_list().get(0).getWork_state().equals("1")) {

                            kaiGuanZhuagnTaiLeft = "1";
                        } else if (response.body().data.get(0).getDevice_list().get(0).getWork_state().equals("2")) {
                            kaiGuanZhuagnTaiLeft = "0";
                        }

                        if (response.body().data.get(0).getDevice_list().get(1).getWork_state().equals("1")) {
                            kaiGuanZhuangTaiRight = "1";
                        } else {
                            kaiGuanZhuangTaiRight = "0";
                        }

                        setKaiGuanZhuangTai(kaiGuanZhuagnTaiLeft, kaiGuanZhuangTaiRight);
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());


                    }

                    @Override
                    public void onStart(Request<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    ZnjjMqttMingLing znjjMqttMingLing;

    public void daKaiKaiGuan(String str, String zhuangZhiId) {


        if (str.equals("0")) {
            znjjMqttMingLing.setAction(zhuangZhiId, "02", new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // UIHelper.ToastMessage(mContext, "当前装置开启");
                    List<String> stringList = new ArrayList<>();
                    stringList.add(device_ccid);
                    stringList.add("1");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    UIHelper.ToastMessage(mContext, "未发送指令");
                }
            });
        } else {
            znjjMqttMingLing.setAction(zhuangZhiId, "01", new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // UIHelper.ToastMessage(mContext, "当前装置开启");
                    List<String> stringList = new ArrayList<>();
                    stringList.add(device_ccid);
                    stringList.add("1");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    UIHelper.ToastMessage(mContext, "未发送指令");
                }
            });
        }
    }


    TishiDialog tishiDialog;
    Response<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>> response;
    /**
     * 修改设备名字
     */
    /**
     * @param deviceName
     * @param familyId
     * @param device_id
     * @param oldDeviceName
     * @param tvText
     * @param i             第几个按钮
     */
    private void changeDevice(String deviceName, TextView tvText, int i) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16033");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        String familyId = response.body().data.get(0).getDevice_list().get(i).getFamily_id();
        String device_id = response.body().data.get(0).getDevice_list().get(i).getDevice_id();
        String oldDeviceName = response.body().data.get(0).getDevice_list().get(i).getDevice_name();

        map.put("family_id", familyId);
        map.put("device_id", device_id);
        map.put("old_name", oldDeviceName);
        map.put("device_name", deviceName);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            tvText.setText(deviceName);

                            tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                                @Override
                                public void onClickCancel(View v, TishiDialog dialog) {
                                    tishiDialog.dismiss();
                                }

                                @Override
                                public void onClickConfirm(View v, TishiDialog dialog) {
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_CAOZUODONGTAISHITI;
                                    sendRx(notice);
                                    finish();
                                }

                                @Override
                                public void onDismiss(TishiDialog dialog) {

                                }
                            });
                            tishiDialog.setTextContent("名字修改成功咯");
                            tishiDialog.show();
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        String str = response.getException().getMessage();
                        tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {
                                tishiDialog.dismiss();
                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {
                                finish();
                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        tishiDialog.setTextContent(str);
                        tishiDialog.show();
                    }
                });
    }
}
