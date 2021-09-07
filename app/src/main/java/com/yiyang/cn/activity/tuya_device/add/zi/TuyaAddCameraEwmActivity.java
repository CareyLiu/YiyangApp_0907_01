package com.yiyang.cn.activity.tuya_device.add.zi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.add.TuyaDeviceAddFinishActivity;
import com.yiyang.cn.activity.tuya_device.add.model.TuyaAddDeviceModel;
import com.yiyang.cn.activity.tuya_device.utils.TuyaConfig;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ZhiNengHomeBean;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.builder.TuyaCameraActivatorBuilder;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaActivatorGetToken;
import com.tuya.smart.sdk.api.ITuyaCameraDevActivator;
import com.tuya.smart.sdk.api.ITuyaSmartCameraActivatorListener;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class TuyaAddCameraEwmActivity extends BaseActivity {

    @BindView(R.id.iv_ewm)
    ImageView ivEwm;
    private String wifiPwd;
    private String wifi;
    private long homeId;
    private String familyId;
    private ITuyaCameraDevActivator mTuyaActivator;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String wifi, String wifiPwd) {
        Intent intent = new Intent(context, TuyaAddCameraEwmActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("wifi", wifi);
        intent.putExtra("wifiPwd", wifiPwd);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_add_camera_ewm;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("摄像机配网");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initHuidiao();
        initPeizhi();
        getTokenTuya();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_ADD) {
                    finish();
                } else if (message.type == ConstanceValue.MSG_PEIWANG_SUCCESS) {
                    finish();
                }
            }
        }));
    }

    private void initPeizhi() {
        homeId = TuyaHomeManager.getHomeManager().getHomeId();
        familyId = PreferenceHelper.getInstance(mContext).getString(AppConfig.PEIWANG_FAMILYID, "");

        wifi = getIntent().getStringExtra("wifi");
        wifiPwd = getIntent().getStringExtra("wifiPwd");
    }

    private void getTokenTuya() {
        TuyaHomeSdk.getActivatorInstance().getActivatorToken(homeId,
                new ITuyaActivatorGetToken() {

                    @Override
                    public void onSuccess(String token) {
                        Y.e("我获取了数据" + token);
                        ewmPeiwang(token);
                    }

                    @Override
                    public void onFailure(String s, String s1) {

                    }
                });
    }

    private void ewmPeiwang(String token) {
        TuyaCameraActivatorBuilder builder = new TuyaCameraActivatorBuilder()
                .setContext(mContext)
                .setSsid(wifi)
                .setPassword(wifiPwd)
                .setToken(token)
                .setTimeOut(180)
                .setListener(new ITuyaSmartCameraActivatorListener() {
                    @Override
                    public void onQRCodeSuccess(String qrcodeUrl) {
                        //返回生成二维码的 url 链接
                        Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(qrcodeUrl, 300);
                        Glide.with(mContext).load(bitmap).into(ivEwm);
                    }

                    @Override
                    public void onError(String errorCode, String errorMsg) {
                        //配网失败
                    }

                    @Override
                    public void onActiveSuccess(DeviceBean devResp) {
                        //配网成功
                        if (devResp.getProductBean().getCategory().equals(TuyaConfig.CATEGORY_CAMERA)) {
                            addShebei(devResp);
                        } else {
                            TuyaHomeSdk.newDeviceInstance(devResp.getDevId()).removeDevice(new IResultCallback() {
                                @Override
                                public void onError(String errorCode, String errorMsg) {
                                    Y.e("移除错误设备失败" + errorMsg);
                                }

                                @Override
                                public void onSuccess() {
                                    Y.e("移除了错误设备");
                                }
                            });
                        }
                    }
                });


        mTuyaActivator = TuyaHomeSdk.getActivatorInstance().newCameraDevActivator(builder);
        mTuyaActivator.createQRCode();
        mTuyaActivator.start();
    }


    private void addShebei(DeviceBean devResp) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16042");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
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
                        add(devResp);
                    }
                });
    }


    private void add(DeviceBean deviceBean) {
        List<TuyaAddDeviceModel> deviceModels = new ArrayList<>();
        TuyaAddDeviceModel model = new TuyaAddDeviceModel();
        model.setDeviceId(deviceBean.getDevId());
        model.setName(deviceBean.getName());
        model.setIcon(deviceBean.getIconUrl());
        model.setSelect(true);
        deviceModels.add(model);
        TuyaDeviceAddFinishActivity.actionStart(mContext, deviceModels);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTuyaActivator != null) {
            mTuyaActivator.stop();
            mTuyaActivator.onDestroy();
        }
    }
}
