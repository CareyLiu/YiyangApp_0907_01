package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttUnSubscribe;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_Success;
import com.yiyang.cn.dialog.ZhiNengFamilyAddDIalog;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.SuiYiTieModel;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
import com.yiyang.cn.model.ZhiiNengRoomDeviceRoomBean;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;
import com.yiyang.cn.util.SoundPoolUtils;
import com.suke.widget.SwitchButton;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengChuangLianActivity extends BaseActivity {

    //    @BindView(R.id.iv_head)
//    ImageView ivHead;
    @BindView(R.id.ll_online_state)
    LinearLayout llOnlineState;
    @BindView(R.id.tv_family_title)
    TextView tvFamilyTitle;
    @BindView(R.id.tv_family)
    TextView tvFamily;
    @BindView(R.id.tv_room_title)
    TextView tvRoomTitle;
    @BindView(R.id.tv_room)
    TextView tvRoom;
    @BindView(R.id.img_room_into)
    ImageView imgRoomInto;
    @BindView(R.id.rl_room)
    RelativeLayout rlRoom;
    @BindView(R.id.tv_device_name_title)
    TextView tvDeviceNameTitle;
    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;
    @BindView(R.id.img_device_into)
    ImageView imgDeviceInto;
    @BindView(R.id.rl_device_name)
    RelativeLayout rlDeviceName;
    @BindView(R.id.tv_device_type_title)
    TextView tvDeviceTypeTitle;
    @BindView(R.id.tv_device_type)
    TextView tvDeviceType;
    @BindView(R.id.tv_device_state_title)
    TextView tvDeviceStateTitle;
    @BindView(R.id.tv_device_state)
    TextView tvDeviceState;
    @BindView(R.id.rl_device_state)
    RelativeLayout rlDeviceState;
    @BindView(R.id.tv_auto_switch_title)
    TextView tvAutoSwitchTitle;
    @BindView(R.id.auto_switch_button)
    SwitchButton autoSwitchButton;
    @BindView(R.id.rl_auto_switch)
    RelativeLayout rlAutoSwitch;
    @BindView(R.id.iv_tishi)
    ImageView ivTishi;
    @BindView(R.id.tv_noti)
    TextView tvNoti;
    @BindView(R.id.tv_room_delete)
    TextView tvRoomDelete;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.iv_guanchuang)
    ImageView ivGuanchuang;
    @BindView(R.id.rl_guanchuang)
    RelativeLayout rlGuanchuang;
    @BindView(R.id.iv_kaichuang)
    ImageView ivKaichuang;
    @BindView(R.id.rl_kaichuang)
    RelativeLayout rlKaichuang;
    @BindView(R.id.iv_zanting)
    ImageView ivZanting;
    @BindView(R.id.rl_zanting)
    RelativeLayout rlZanting;
    @BindView(R.id.rrl_guanchuang)
    RelativeLayout rrlGuanchuang;
    @BindView(R.id.rrl_kaichuang)
    RelativeLayout rrlKaichuang;
    @BindView(R.id.rrl_zanting)
    RelativeLayout rrlZanting;
    @BindView(R.id.tv_guanchuanglian)
    TextView tvGuanchuanglian;
    @BindView(R.id.tv_kaichuanglian)
    TextView tvKaichuanglian;
    @BindView(R.id.tv_zanting)
    TextView tvZanting;
    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.iv_zanting_tupian)
    ImageView ivZantingTupian;

    @BindView(R.id.tv_caozuotishiyin)
    TextView tvCaozuotishiyin;
    @BindView(R.id.sbtn_caozuotishiyin)
    Switch sbtnCaozuotishiyin;
    @BindView(R.id.rl_caozuotishiyin)
    RelativeLayout rlCaozuotishiyin;


    private String device_id;
    ZnjjMqttMingLing mqttMingLing;
    private String device_ccid;
    private String member_type = "";
    private String isVoice = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            device_id = getIntent().getStringExtra("device_id");
            member_type = getIntent().getStringExtra("member_type");
        }
        getnet();

        tvRoomDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(mContext,
                        "提示", "确定要删除设备吗？", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {

                    }

                    @Override
                    public void clickRight() {

                        //删除设备
                        deleteDevice();

                    }
                });
                myCarCaoZuoDialog_caoZuoTIshi.show();
            }
        });

        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getnet();
                srLSmart.finishRefresh();
            }
        });

        ivGuanchuang.setBackgroundResource(R.mipmap.chuanglian_button_guan_nor);
        ivKaichuang.setBackgroundResource(R.mipmap.chuanglian_button_kai_nor);
        ivZanting.setBackgroundResource(R.mipmap.chuanglian_button_stop_nor);

        animationView.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.chuanglian_pic_chuanglian_open));

        rlKaichuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(mContext, "点击了开窗");
                if (isVoice.equals("1"))
                    SoundPoolUtils.soundPool(mContext, R.raw.kaiqizhinengchuanglian);
                rrlGuanchuang.setBackground(mContext.getResources().getDrawable(R.drawable.chuanglian_no_sel));
                tvGuanchuanglian.setTextColor(mContext.getResources().getColor(R.color.black_333333));
                ivGuanchuang.setBackgroundResource(R.mipmap.chuanglian_button_guan_nor);

                rrlZanting.setBackground(mContext.getResources().getDrawable(R.drawable.chuanglian_no_sel));
                tvZanting.setTextColor(mContext.getResources().getColor(R.color.black_333333));
                ivZanting.setBackgroundResource(R.mipmap.chuanglian_button_stop_nor);

                rrlKaichuang.setBackground(mContext.getResources().getDrawable(R.drawable.chuagnlian_sel));
                tvKaichuanglian.setTextColor(mContext.getResources().getColor(R.color.white));
                ivKaichuang.setBackgroundResource(R.mipmap.chuanglian_button_kai_sel);

                mqttMingLing.setAction(device_ccid, "01", new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        // UIHelper.ToastMessage(mContext, "执行成功");

                        animationView.setAnimation("cloth_open_data.json");
                        animationView.setImageAssetsFolder("open_images/");
                        animationView.loop(false);
                        animationView.playAnimation();

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });


            }
        });
        rlGuanchuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // UIHelper.ToastMessage(mContext, "点击了关窗");
                if (isVoice.equals("1"))
                    SoundPoolUtils.soundPool(mContext, R.raw.guanbizhinengchuanglian);
                rrlGuanchuang.setBackground(mContext.getResources().getDrawable(R.drawable.chuagnlian_sel));
                tvGuanchuanglian.setTextColor(mContext.getResources().getColor(R.color.white));
                ivGuanchuang.setBackgroundResource(R.mipmap.chuanglian_button_guan_sel);

                rrlZanting.setBackground(mContext.getResources().getDrawable(R.drawable.chuanglian_no_sel));
                tvZanting.setTextColor(mContext.getResources().getColor(R.color.black_333333));
                ivZanting.setBackgroundResource(R.mipmap.chuanglian_button_stop_nor);

                rrlKaichuang.setBackground(mContext.getResources().getDrawable(R.drawable.chuanglian_no_sel));
                tvKaichuanglian.setTextColor(mContext.getResources().getColor(R.color.black_333333));
                ivKaichuang.setBackgroundResource(R.mipmap.chuanglian_button_kai_nor);

                mqttMingLing.setAction(device_ccid, "02", new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        //UIHelper.ToastMessage(mContext, "执行成功");
                        animationView.setAnimation("cloth_close_data.json");
                        animationView.setImageAssetsFolder("close_images/");
                        animationView.loop(false);
                        animationView.playAnimation();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            }
        });
        rlZanting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // UIHelper.ToastMessage(mContext, "点击了暂停");

                rrlGuanchuang.setBackground(mContext.getResources().getDrawable(R.drawable.chuanglian_no_sel));
                tvGuanchuanglian.setTextColor(mContext.getResources().getColor(R.color.black_333333));
                ivGuanchuang.setBackgroundResource(R.mipmap.chuanglian_button_guan_nor);

                rrlZanting.setBackground(mContext.getResources().getDrawable(R.drawable.chuagnlian_sel));
                tvZanting.setTextColor(mContext.getResources().getColor(R.color.white));
                ivZanting.setBackgroundResource(R.mipmap.chuanglian_button_stop_sel);

                rrlKaichuang.setBackground(mContext.getResources().getDrawable(R.drawable.chuanglian_no_sel));
                tvKaichuanglian.setTextColor(mContext.getResources().getColor(R.color.black_333333));
                ivKaichuang.setBackgroundResource(R.mipmap.chuanglian_button_kai_nor);

                mqttMingLing.setAction(device_ccid, "03", new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        //   UIHelper.ToastMessage(mContext, "执行成功");
                        //  animationView.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.chuanglian_pic_chuanglian_close));
                        animationView.cancelAnimation();
                        animationView.setProgress(0f);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            }
        });
        sbtnCaozuotishiyin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isPressed()) {
                    return;
                }
                if (isChecked) {
                    sheZhiTiShiYin("1");
                } else {
                    sheZhiTiShiYin("2");
                }

            }
        });


        rlDeviceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(mContext, ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME);
                zhiNengFamilyAddDIalog.show();
            }
        });


        rlRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("device_id", dataBean.getDevice_id());
                bundle.putString("family_id", dataBean.getFamily_id());
                bundle.putString("member_type", member_type);
                startActivity(new Intent(mContext, ZhiNengRoomManageActivity.class).putExtras(bundle));
            }
        });

        initHuidiao();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME) {
                    changeDevice(message.content.toString());
                }
            }
        }));
    }

    /**
     * 修改设备名字
     */
    private void changeDevice(String deviceName) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16033");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("family_id", dataBean.getFamily_id());
        map.put("device_id", dataBean.getDevice_id());
        map.put("old_name", dataBean.getDevice_name());
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
                            tvDeviceName.setText(deviceName);
                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(ZhiNengChuangLianActivity.this,
                                    "成功", "名字修改成功咯", "好的", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {
                                    Notice notice = new Notice();
                                    notice.type = ConstanceValue.MSG_CAOZUODONGTAISHITI;
                                    sendRx(notice);
                                }
                            });
                            myCarCaoZuoDialog_success.show();
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


    @Override
    public int getContentViewResId() {
        return R.layout.layout_zhinengchuanglian_activity;
    }

    private ZhiiNengRoomDeviceRoomBean.DataBean dataBean;


    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16035");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>> response) {
                        if (srLSmart != null) {
                            srLSmart.setEnableRefresh(true);
                            srLSmart.finishRefresh();
                            srLSmart.setEnableLoadMore(false);
                        }
                        if (response.body().msg.equals("ok")) {
                            dataBean = response.body().data.get(0);
                            // Glide.with(mContext).load(dataBean.getDevice_type_pic()).into(ivHead);
                            tvFamily.setText(dataBean.getFamily_name());
                            tvRoom.setText(dataBean.getRoom_name());
                            tvDeviceName.setText(dataBean.getDevice_name());
                            tvDeviceType.setText(dataBean.getDevice_type_name());
                            device_ccid = dataBean.getDevice_ccid();

                            mqttMingLing = new ZnjjMqttMingLing(mContext, dataBean.getDevice_ccid_up(), dataBean.getServer_id());
                        }

                        if (StringUtils.isEmpty(response.body().data.get(0).is_voice)) {
                            isVoice = "1";
                            sbtnCaozuotishiyin.setChecked(true);
                        } else {
                            isVoice = response.body().data.get(0).is_voice;

                            if (isVoice.equals("1")) {
                                sbtnCaozuotishiyin.setChecked(true);
                            } else if (isVoice.equals("2")) {
                                sbtnCaozuotishiyin.setChecked(false);
                            }
                        }
                        showLoadSuccess();
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);

                    }

                    @Override
                    public void onStart(Request<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_id, String device_type, String member_type) {
        Intent intent = new Intent(context, ZhiNengChuangLianActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_id", device_id);
        intent.putExtra("device_type", device_type);
        intent.putExtra("member_type", member_type);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设备详情");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }

    TishiDialog tishiDialog;

    /**
     * 删除设备
     */
    private void deleteDevice() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16034");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("family_id", dataBean.getFamily_id());
        map.put("device_id", dataBean.getDevice_id());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            Notice notice = new Notice();
                            notice.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                            sendRx(notice);

                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(ZhiNengChuangLianActivity.this,
                                    "成功", "设备删除成功", "好的", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {
                                    finish();
                                }
                            });
                            myCarCaoZuoDialog_success.show();
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

    private void sheZhiTiShiYin(String isVoice) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16053");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);
        map.put("is_voice", isVoice);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<SuiYiTieModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SuiYiTieModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SuiYiTieModel.DataBean>> response) {

                        if (isVoice.equals("1")) {
                            UIHelper.ToastMessage(mContext, "开启提示音设置成功");
                            ZhiNengChuangLianActivity.this.isVoice = "1";
                        } else if (isVoice.equals("2")) {
                            UIHelper.ToastMessage(mContext, "关闭提示音设置成功");
                            ZhiNengChuangLianActivity.this.isVoice = "2";
                        }
                        showLoadSuccess();
                    }

                    @Override
                    public void onError(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<SuiYiTieModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(mContext, "未连接主机,请重新尝试");
            return;
        }

        if (mqttMingLing != null) {
            mqttMingLing.unSubscribeShiShiXinXi(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
            mqttMingLing.unSubscribeAppShiShiXinXi(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
            mqttMingLing.unSubscribeHardware(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        }
    }
}
