package com.yiyang.cn.activity.tuya_device.add;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.iot.esptouch.util.TouchNetUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.add.adapter.TuyaDeviceAdapter;
import com.yiyang.cn.activity.tuya_device.add.model.TuyaAddDeviceModel;
import com.yiyang.cn.activity.tuya_device.utils.OnTuyaItemClickListener;
import com.yiyang.cn.activity.tuya_device.utils.WifiReceiver;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.yiyang.cn.activity.zhinengjiaju.EsptouchAsyncTask4;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.dialog.newdia.TishiPhoneDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.Message;
import com.yiyang.cn.model.PeiwangOtherModel;
import com.yiyang.cn.model.TianJiaZhuJiMoel;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
import com.yiyang.cn.model.ZhiNengHomeBean;
import com.yiyang.cn.model.ZhiNengJiaJu_0007Model;
import com.yiyang.cn.model.ZhiNengJiaJu_0009Model;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.builder.ActivatorBuilder;
import com.tuya.smart.sdk.api.ITuyaActivator;
import com.tuya.smart.sdk.api.ITuyaActivatorGetToken;
import com.tuya.smart.sdk.api.ITuyaSmartActivatorListener;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.enums.ActivatorModelEnum;

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
import static com.yiyang.cn.get_net.Urls.SERVER_URL;
import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

/**
 * 1.先搜索主机
 */
public class TuyaDeviceAddZiFragment extends BaseFragment {
    @BindView(R.id.iv_wifi)
    ImageView iv_wifi;
    @BindView(R.id.ll_wifi)
    LinearLayout ll_wifi;
    @BindView(R.id.iv_lanya)
    ImageView iv_lanya;
    @BindView(R.id.ll_lanya)
    LinearLayout ll_lanya;
    @BindView(R.id.bt_search_device)
    TextView bt_search_device;
    @BindView(R.id.ll_peizhi)
    RelativeLayout ll_peizhi;
    @BindView(R.id.rv_shebei)
    RecyclerView rv_shebei;
    @BindView(R.id.rl_shuoming)
    RelativeLayout rl_shuoming;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.bt_xiugai)
    TextView bt_xiugai;
    @BindView(R.id.rl_sousuo)
    RelativeLayout rl_sousuo;
    @BindView(R.id.bt_xiayibu)
    TextView bt_xiayibu;
    @BindView(R.id.rl_faxian)
    RelativeLayout rl_faxian;
    @BindView(R.id.bt_chongxinsousuo)
    TextView bt_chongxinsousuo;

    private String wifiSSid;
    private String mima;
    private String bssid;
    private long homeId;
    private boolean isSetWifi;
    private boolean isSetLanya;
    private boolean isSousuozhong;
    private List<DeviceBean> deviceBeans = new ArrayList<>();
    private TuyaDeviceAdapter adapter;
    private BluetoothAdapter bluetoothAdapter;
    private ITuyaActivator mTuyaActivator;
    private String familyId;
    private String isHaiZhuji;

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_device_add_zi;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        init();
        initLanya();
        initHuidiao();
        initAdapter();
        return rootView;
    }

    private void initAdapter() {
        adapter = new TuyaDeviceAdapter(deviceBeans, getContext());
        rv_shebei.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rv_shebei.setAdapter(adapter);
        adapter.setListener(new OnTuyaItemClickListener() {
            @Override
            public void onItmeCilck(int pos) {
                DeviceBean deviceBean = deviceBeans.get(pos);
            }
        });
    }

    private void initLanya() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled()) {
                isSetLanya = true;
                iv_lanya.setVisibility(View.VISIBLE);
            } else {
                isSetLanya = false;
                iv_lanya.setVisibility(View.GONE);
            }
        } else {
            isSetLanya = false;
            iv_lanya.setVisibility(View.GONE);
        }
    }

    ZhiNengJiaJu_0007Model zhiNengJiaJu_0007Model;
    ZhiNengJiaJu_0009Model zhiNengJiaJu_0009Model;
    List<ZhiNengJiaJu_0007Model.MatchListBean> mDatas = new ArrayList<>();
    TishiDialog tishiDialog;
    lianWangThread thread;

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_WIFI_SET) {
                    wifiSSid = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_ADMIN, "");
                    mima = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_MIMA, "");
                    bssid = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_BSSID, "");
                    if (!TextUtils.isEmpty(wifiSSid) && !TextUtils.isEmpty(mima) & !TextUtils.isEmpty(bssid)) {
                        isSetWifi = true;
                        iv_wifi.setVisibility(View.VISIBLE);
                    } else {
                        isSetWifi = false;
                        iv_wifi.setVisibility(View.GONE);
                    }

                    if (isSousuozhong) {
                        if (mTuyaActivator != null) {
                            mTuyaActivator.stop();
                            clickSearch();
                        }
                    }
                } else if (message.type == ConstanceValue.MSG_WIFI_INFO) {
                    if (!TextUtils.isEmpty(wifiSSid) && !TextUtils.isEmpty(mima)) {
                        isSetWifi = true;
                        iv_wifi.setVisibility(View.VISIBLE);
                    } else {
                        isSetWifi = false;
                        iv_wifi.setVisibility(View.GONE);
                    }
                } else if (message.type == ConstanceValue.MSG_WIFI_CLOSE) {
                    isSetWifi = false;
                    iv_wifi.setVisibility(View.GONE);
                } else if (message.type == ConstanceValue.MSG_TIANJIASHEBEI) {
                    if (isOnFrag) {
                        getShebei();
                        stopAnimation();
                        stopSearch();
                        //添加设备
                        zhiNengJiaJu_0007Model = (ZhiNengJiaJu_0007Model) message.content;
                        mDatas.clear();
                        mDatas.addAll(zhiNengJiaJu_0007Model.getMatch_list());

                        //将添加的设备显示出来
                        for (int i = 0; i < mDatas.size(); i++) {
                            DeviceBean devResp = new DeviceBean();
                            devResp.setIconUrl(mDatas.get(i).getDevice_type_pic());
                            devResp.setName(mDatas.get(i).getDevice_name());
                            deviceBeans.add(devResp);
                        }
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }

                        tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
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
                            }
                        });
                        tishiDialog.setTextContent("设备配网成功");
                        tishiDialog.setTextCancel("");
                        tishiDialog.setTextConfirm("完成");
                        tishiDialog.show();
                    }
                } else if (message.type == ConstanceValue.MSG_TIANJIAZHUJI) {//添加主机
                    if (isOnFrag) {
                        if (x) {
                            return;
                        }
                        kaiGuanFlag = false;
                        thread.interrupt();
                        getShebei();
                        stopAnimation();
                        stopSearch();
                        if (tishiDialog == null) {
                            Notice notice1 = new Notice();
                            notice1.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                            sendRx(notice1);

                            zhiNengJiaJu_0009Model = (ZhiNengJiaJu_0009Model) message.content;
                            DeviceBean devResp = new DeviceBean();
                            devResp.setIconUrl(zhiNengJiaJu_0009Model.mc_device_url);
                            deviceBeans.add(devResp);
                            adapter.notifyDataSetChanged();

                            tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
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
                                }
                            });
                            tishiDialog.setTextContent("主机配网成功");
                            tishiDialog.setTextCancel("");
                            tishiDialog.setTextConfirm("完成");
                            tishiDialog.show();
                        }
                    }
                } else if (message.type == ConstanceValue.MSG_PEIWNAG_ESPTOUCH) {

                    // Y.e("连接成功");
                    //  Log.i("TuYaDeviceAdd", String.valueOf(ob));
                    thread = new lianWangThread();
                    thread.start();


                } else if (message.type == ConstanceValue.MSG_PEIWANG_ERROR) {
                    finishPeiwang();
                    tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {

                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            tishiDialog.dismiss();
                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });
                    tishiDialog.setTextContent((String) message.content);
                    tishiDialog.setTextCancel("");
                    tishiDialog.setTextConfirm("知道了");
                    tishiDialog.show();
                } else if (message.type == ConstanceValue.MSG_ZHUJIBANG_OTHER) {
                    if (isOnFrag) {
                        getShebei();
                        stopAnimation();
                        stopSearch();
                        peiwangOtherModel = (PeiwangOtherModel) message.content;
                        tishiPhoneDialog = new TishiPhoneDialog(getActivity(), new TishiPhoneDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiPhoneDialog dialog) {
                                finishPeiwang();
                                dialog.dismiss();
                            }

                            @Override
                            public void onClickConfirm(View v, TishiPhoneDialog dialog) {
                                if (TextUtils.isEmpty(smsId)) {
                                    Y.t("请发送验证码");
                                    return;
                                }

                                if (TextUtils.isEmpty(dialog.getEdContent())) {
                                    Y.t("请输入验证码");
                                    return;
                                }

                                tianJiaSheBeiNet2(dialog.getEdContent());
                            }

                            @Override
                            public void onDismiss(TishiPhoneDialog dialog) {

                            }

                            @Override
                            public void onSendYanZhengMa(View v, TishiPhoneDialog dialog) {
                                get_code();
                            }
                        });
                        tishiPhoneDialog.setCancelable(false);
                        tishiPhoneDialog.setCanceledOnTouchOutside(false);
                        tishiPhoneDialog.setTextContent("该设备已被绑定到账号为" + peiwangOtherModel.getPhone() + "的家庭，如继续操作请手机验证");
                        tishiPhoneDialog.show();
                    }
                }
            }
        }));
    }

    private PeiwangOtherModel peiwangOtherModel;
    private TishiPhoneDialog tishiPhoneDialog;
    private String smsId;

    private void get_code() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00001");
        map.put("key", Urls.key);
        map.put("user_phone", peiwangOtherModel.getPhone());
        map.put("mod_id", "0341");
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        Y.t("验证码获取成功");
                        Notice notice = new Notice();
                        notice.type = ConstanceValue.MSG_SENDCODE_HUIDIAO;
                        sendRx(notice);
                        if (response.body().data.size() > 0) {
                            smsId = response.body().data.get(0).getSms_id();
                        }
                        tishiPhoneDialog.startCode();
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        Y.tError(response);
                        tishiPhoneDialog.errorCode();
                    }
                });
    }

    private void tianJiaSheBeiNet2(String smsCode) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16041");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("add_device_path", "1");
        map.put("family_id", PreferenceHelper.getInstance(getActivity()).getString(AppConfig.FAMILY_ID, ""));
        map.put("xf_device_id", peiwangOtherModel.getXf_device_id());
        map.put("access_token", peiwangOtherModel.getAccess_token());
        map.put("wifi_user", peiwangOtherModel.getWifi_user());
        map.put("wifi_pwd", peiwangOtherModel.getWifi_pwd());
        map.put("mc_device_ccid", peiwangOtherModel.getMc_device_ccid());
        map.put("server_id", peiwangOtherModel.getServer_id());
        map.put("sms_id", smsId);
        map.put("sms_code", smsCode);
        Gson gson = new Gson();
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
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
                            }
                        });
                        tishiDialog.setTextContent("主机配网成功");
                        tishiDialog.setTextCancel("");
                        tishiDialog.setTextConfirm("完成");
                        tishiDialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        super.onError(response);
                        Y.tError(response);
                        finishPeiwang();
                    }
                });
    }

    private void init() {
        homeId = TuyaHomeManager.getHomeManager().getHomeId();
        familyId = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.PEIWANG_FAMILYID, "");
        wifiSSid = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_ADMIN, "");
        mima = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_MIMA, "");
        bssid = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_BSSID, "");
        Y.e("wifi信息   " + wifiSSid + "   " + bssid + "   " + mima);

        if (!TextUtils.isEmpty(wifiSSid) && !TextUtils.isEmpty(mima) && !TextUtils.isEmpty(bssid)) {
            if (wifiSSid.equals(WifiReceiver.WIFI_NAME)) {
                isSetWifi = true;
                iv_wifi.setVisibility(View.VISIBLE);
            } else {
                isSetWifi = false;
                iv_wifi.setVisibility(View.GONE);
            }
        } else {
            isSetWifi = false;
            iv_wifi.setVisibility(View.GONE);
        }

        isSousuozhong = false;
    }


    @OnClick({R.id.ll_wifi, R.id.ll_lanya, R.id.bt_search_device, R.id.bt_xiugai, R.id.bt_chongxinsousuo, R.id.bt_xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_wifi:
                TuyaDevicePeiwangActivity.actionStart(getContext());
                break;
            case R.id.ll_lanya:
                clickLanya();
                break;
            case R.id.bt_search_device:
            case R.id.bt_chongxinsousuo:
                clickSearch();
                break;
            case R.id.bt_xiugai:
                break;
            case R.id.bt_xiayibu:
                clickXiayibu();
                break;
        }
    }

    private ZnjjMqttMingLing znjjMqttMingLing;
    public EsptouchAsyncTask4 mTask;

    /**
     * @param zhuji_device_ccid_up    主机ccid
     * @param serverId                serverid  ccid最后一位
     * @param zhuangZhiLeixing        装置类型 详见mqtt文档
     * @param zhuangZhiLeiXingXingHao 装置类型型号 a款 b款
     */
    private void jiBenPeiWang(String zhuji_device_ccid_up, String serverId, String zhuangZhiLeixing, String zhuangZhiLeiXingXingHao) {
        if (isHaiZhuji.equals("1")) {
            znjjMqttMingLing = new ZnjjMqttMingLing(getActivity());

            znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(zhuji_device_ccid_up, serverId, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
            // TODO: 2021/2/2 添加的命令待赋值
            String str = "M12" + zhuangZhiLeixing + zhuangZhiLeiXingXingHao + "2.";

            znjjMqttMingLing.tianJiaSheBei(zhuji_device_ccid_up, serverId, str, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } else {
            // TODO: 2021/2/2 接入密码  主机配对
            if (isSetWifi){
                executeEsptouch(mima, bssid, wifiSSid);
            }
        }
    }

    /**
     * wifi 密码
     */
    private void executeEsptouch(String password, String mBssid, String mSsid) {
        byte[] bssid = TouchNetUtil.parseBssid2bytes(mBssid.toString());
        CharSequence devCountStr = String.valueOf(1);
        byte[] deviceCount = devCountStr == null ? new byte[0] : devCountStr.toString().getBytes();
        byte[] broadcast = {1};

        if (mTask != null) {
            mTask.cancelEsptouch();
        }
        mTask = new EsptouchAsyncTask4(getActivity(), new EsptouchAsyncTask4.IListener() {
            @Override
            public void successZhuJi() {
                thread = new lianWangThread();
                thread.start();
            }
        });

        int yonghuming_length = mSsid.toString().length();
        int password_length = password.toString().length();

        String zhengHeYongHuMing = String.format("%02d", yonghuming_length) + mSsid.toString();

        String zhengHePasssword_length = String.format("%02d", password_length);

        String familyId = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.PEIWANG_FAMILYID, "");

        if (familyId == null) {
            UIHelper.ToastMessage(getActivity(), "请退出应用后重新进入");
            return;
        }

        String familyId_length = String.format("%02d", familyId.length());

        Log.i("execute_info", "   mssid  :" + mSsid.toString() + "   mBssid  :" + mBssid + "   password  :" + password);

        String zhengHePassWord = zhengHePasssword_length + familyId_length + password + familyId;

        Log.i("execute_info", zhengHePassWord);

        mTask.execute(zhengHeYongHuMing.getBytes(), bssid, zhengHePassWord.getBytes(), deviceCount, broadcast);
    }

    private void clickXiayibu() {
        List<TuyaAddDeviceModel> deviceModels = new ArrayList<>();
        for (int i = 0; i < deviceBeans.size(); i++) {
            DeviceBean deviceBean = deviceBeans.get(i);
            TuyaAddDeviceModel model = new TuyaAddDeviceModel();
            model.setDeviceId(deviceBean.getDevId());
            model.setName(deviceBean.getName());
            model.setIcon(deviceBean.getIconUrl());
            model.setSelect(true);
            deviceModels.add(model);
        }
        TuyaDeviceAddFinishActivity.actionStart(getContext(), deviceModels);
    }

    private void clickSearch() {
        starPeiwang();
        startJyjSearch();
        if (isSetWifi) {
            TuyaHomeSdk.getActivatorInstance().getActivatorToken(homeId,
                    new ITuyaActivatorGetToken() {
                        @Override
                        public void onSuccess(String token) {
                            startWifiPeiwang(token);
                        }

                        @Override
                        public void onFailure(String s, String s1) {
                            Y.t(s1);
                            finishPeiwang();
                        }
                    });
        }
    }

    private String zhuJiDeviceCCidUp;
    private String serverId;

    private void startJyjSearch() {
        // TODO: 2021/2/2 待增加参数
        isHaiZhuji = PreferenceHelper.getInstance(getActivity()).getString(App.HAS_ZHUJI, "");
        zhuJiDeviceCCidUp = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.SERVERID, "");
        jiBenPeiWang(zhuJiDeviceCCidUp, serverId, "00", "00");
    }

    private void starPeiwang() {
        ll_peizhi.setVisibility(View.GONE);
        rl_sousuo.setVisibility(View.VISIBLE);
        rl_shuoming.setVisibility(View.VISIBLE);
        rv_shebei.setVisibility(View.GONE);
        bt_chongxinsousuo.setVisibility(View.GONE);
        bt_xiugai.setVisibility(View.GONE);
        isSousuozhong = true;
        startAnimation();
    }

    private void startWifiPeiwang(String token) {
        ActivatorBuilder builder = new ActivatorBuilder()
                .setSsid(wifiSSid)
                .setContext(getContext())
                .setPassword(mima)
                .setActivatorModel(ActivatorModelEnum.TY_EZ)
                .setTimeOut(60)
                .setToken(token)
                .setListener(new ITuyaSmartActivatorListener() {
                                 @Override
                                 public void onError(String errorCode, String errorMsg) {
                                     finishPeiwang();
                                     tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
                                         @Override
                                         public void onClickCancel(View v, TishiDialog dialog) {

                                         }

                                         @Override
                                         public void onClickConfirm(View v, TishiDialog dialog) {
                                             tishiDialog.dismiss();
                                         }

                                         @Override
                                         public void onDismiss(TishiDialog dialog) {

                                         }
                                     });
                                     tishiDialog.setTextContent("获取设备超时,请重新搜索");
                                     tishiDialog.setTextCancel("");
                                     tishiDialog.setTextConfirm("知道了");
                                     tishiDialog.show();
                                 }

                                 @Override
                                 public void onActiveSuccess(DeviceBean devResp) {
                                     getShebei();
                                     addShebei(devResp);
                                     deviceBeans.add(devResp);
                                     adapter.notifyDataSetChanged();
                                 }

                                 @Override
                                 public void onStep(String step, Object data) {

                                 }
                             }
                );

        mTuyaActivator = TuyaHomeSdk.getActivatorInstance().newMultiActivator(builder);
        mTuyaActivator.start();
    }

    private void addShebei(DeviceBean devResp) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16042");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("family_id", familyId);
        map.put("ty_device_ccid", devResp.getDevId());
        map.put("ty_family_id", homeId + "");
        map.put("ty_room_id", "0");
        map.put("device_type", devResp.getProductBean().getCategory());
        map.put("device_category", devResp.getProductId());
        map.put("device_category_code", devResp.getProductBean().getCategoryCode());
        map.put("device_type_name", devResp.getName());
        map.put("device_type_pic", devResp.getIconUrl());
        Gson gson = new Gson();
        OkGo.<AppResponse<ZhiNengHomeBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengHomeBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengHomeBean.DataBean>> response) {
                        tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
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
                            }
                        });
                        tishiDialog.setTextContent("设备配网成功");
                        tishiDialog.setTextCancel("");
                        tishiDialog.setTextConfirm("完成");
                        tishiDialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengHomeBean.DataBean>> response) {
                        super.onError(response);
                        finishPeiwang();
                        tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {
                                tishiDialog.dismiss();
                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        tishiDialog.setTextContent(response.body().msg);
                        tishiDialog.setTextCancel("");
                        tishiDialog.setTextConfirm("知道了");
                        tishiDialog.show();
                    }
                });
    }

    private void getShebei() {
        rv_shebei.setVisibility(View.VISIBLE);
        rl_shuoming.setVisibility(View.GONE);
        rl_faxian.setVisibility(View.VISIBLE);
        bt_xiugai.setVisibility(View.GONE);
        bt_chongxinsousuo.setVisibility(View.GONE);
    }

    private void clickLanya() {
        if (bluetoothAdapter == null) {
            Y.t("该设备不支持蓝牙设备");
            return;
        }
        if (isSetLanya) {
            bluetoothAdapter.disable();
            isSetLanya = false;
            iv_lanya.setVisibility(View.GONE);
        } else {
            bluetoothAdapter.enable();
            isSetLanya = true;
            iv_lanya.setVisibility(View.VISIBLE);
        }
    }

    private void startAnimation() {
        Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.search_anim);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        if (rotate != null) {
            iv_search.startAnimation(rotate);
        } else {
            iv_search.setAnimation(rotate);
            iv_search.startAnimation(rotate);
        }
    }

    private void finishPeiwang() {
        bt_chongxinsousuo.setVisibility(View.VISIBLE);
        bt_xiugai.setVisibility(View.VISIBLE);
        stopAnimation();
        stopSearch();
    }

    private void stopSearch() {
        if (mTuyaActivator != null) {
            mTuyaActivator.stop();
            mTuyaActivator.onDestroy();
        }
    }

    private void stopAnimation() {
        iv_search.clearAnimation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finishPeiwang();
        if (znjjMqttMingLing != null) {
            znjjMqttMingLing.unSubscribeShiShiXinXi_WithCanShu(zhuJiDeviceCCidUp, serverId, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        }
    }

    private boolean isOnFrag;

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        isOnFrag = true;
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        isOnFrag = false;
    }

    private void getZhuJiNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16076");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("family_id", PreferenceHelper.getInstance(getActivity()).getString(AppConfig.FAMILY_ID, ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<TianJiaZhuJiMoel>>post(Urls.ZHINENGJIAJU)
                .tag(this)
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TianJiaZhuJiMoel>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TianJiaZhuJiMoel>> response) {
                        if (response.body().is_added.equals("1")) {
                            if (tishiDialog == null) {
                                Notice notice1 = new Notice();
                                notice1.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                                sendRx(notice1);

                                tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
                                    @Override
                                    public void onClickCancel(View v, TishiDialog dialog) {

                                    }

                                    @Override
                                    public void onClickConfirm(View v, TishiDialog dialog) {
                                        Notice notice = new Notice();
                                        notice.type = MSG_PEIWANG_SUCCESS;
                                        RxBus.getDefault().sendRx(notice);
                                        getActivity().finish();
                                    }

                                    @Override
                                    public void onDismiss(TishiDialog dialog) {

                                    }
                                });
                                tishiDialog.setTextContent("主机配网成功");
                                tishiDialog.setTextCancel("");
                                tishiDialog.setTextConfirm("完成");
                                tishiDialog.show();

                                thread.interrupt();
                                kaiGuanFlag = false;
                            }
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    boolean kaiGuanFlag = true;
    boolean x = false;

    private class lianWangThread extends Thread {
        private int i = 0;

        public void run() {
            while (kaiGuanFlag) {
                if (tishiDialog != null) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Yes,I am interruted,but I am still running");
                        return;

                    }
                }


                try {
                    Thread.sleep(3000);
                    if (tishiDialog == null) {
                        getZhuJiNet();

                    }
                    i = i + 1;
                    if (i == 2) {
                        tishiDialog_lianwangshibai = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
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
                        tishiDialog.setTextContent("联网失败，请切换网络重新尝试");
                        tishiDialog.setTextCancel("");
                        tishiDialog.setTextConfirm("确定");
                        tishiDialog.show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    TishiDialog tishiDialog_lianwangshibai;
}
