package com.yiyang.cn.activity.tuya_device.add.zi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.tuya.smart.home.sdk.builder.ActivatorBuilder;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaActivator;
import com.tuya.smart.sdk.api.ITuyaActivatorGetToken;
import com.tuya.smart.sdk.api.ITuyaSmartActivatorListener;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.enums.ActivatorModelEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class TuyaAddCameraWifiActivity extends BaseActivity {

    @BindView(R.id.rv_shebei)
    RecyclerView rv_shebei;
    @BindView(R.id.rl_shuoming)
    RelativeLayout rl_shuoming;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.bt_chongxinsousuo)
    TextView bt_chongxinsousuo;
    @BindView(R.id.rl_sousuo)
    RelativeLayout rl_sousuo;
    private long homeId;
    private String familyId;
    private String wifiName;
    private String mima;
    private ITuyaActivator mTuyaActivator;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String wifi, String wifiPwd) {
        Intent intent = new Intent(context, TuyaAddCameraWifiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("wifi", wifi);
        intent.putExtra("wifiPwd", wifiPwd);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_add_camera_wifi;
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
        starPeiwang();
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

        wifiName = getIntent().getStringExtra("wifi");
        mima = getIntent().getStringExtra("wifiPwd");
    }


    private void starPeiwang() {
        rl_sousuo.setVisibility(View.VISIBLE);
        rl_shuoming.setVisibility(View.VISIBLE);
        rv_shebei.setVisibility(View.GONE);
        bt_chongxinsousuo.setVisibility(View.GONE);

        startAnimation();
        TuyaHomeSdk.getActivatorInstance().getActivatorToken(homeId,
                new ITuyaActivatorGetToken() {
                    @Override
                    public void onSuccess(String token) {
                        Y.e("获取Token成功：" + token + "   账号：" + wifiName + "  密码：" + mima);
                        startWifiPeiwang(token);
                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        Y.t(s1);
                        stopPeiwang();
                    }
                });
    }

    private void startWifiPeiwang(String token) {
        ActivatorBuilder builder = new ActivatorBuilder()
                .setSsid(wifiName)
                .setContext(mContext)
                .setPassword(mima)
                .setActivatorModel(ActivatorModelEnum.TY_EZ)
                .setTimeOut(60)
                .setToken(token)
                .setListener(new ITuyaSmartActivatorListener() {
                                 @Override
                                 public void onError(String errorCode, String errorMsg) {
                                     Y.t("获取设备失败,请重新搜索" + errorMsg);
                                     stopPeiwang();
                                 }

                                 @Override
                                 public void onActiveSuccess(DeviceBean devResp) {
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


    private void stopPeiwang() {
        bt_chongxinsousuo.setVisibility(View.VISIBLE);
        stopAnimation();
        stopSearch();
    }

    private void stopSearch() {
        if (mTuyaActivator != null) {
            mTuyaActivator.stop();
            mTuyaActivator.onDestroy();
        }
    }

    private void startAnimation() {
        Animation rotate = AnimationUtils.loadAnimation(mContext, R.anim.search_anim);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        if (rotate != null) {
            iv_search.startAnimation(rotate);
        } else {
            iv_search.setAnimation(rotate);
            iv_search.startAnimation(rotate);
        }
    }

    private void stopAnimation() {
        iv_search.clearAnimation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPeiwang();
    }
}
