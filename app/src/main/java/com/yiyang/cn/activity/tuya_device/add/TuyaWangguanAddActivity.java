package com.yiyang.cn.activity.tuya_device.add;

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
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.add.adapter.TuyaDeviceAdapter;
import com.yiyang.cn.activity.tuya_device.add.model.TuyaAddDeviceModel;
import com.yiyang.cn.activity.tuya_device.utils.OnTuyaItemClickListener;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ZhiNengHomeBean;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.builder.TuyaGwSubDevActivatorBuilder;
import com.tuya.smart.sdk.api.ITuyaActivator;
import com.tuya.smart.sdk.api.ITuyaSmartActivatorListener;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class TuyaWangguanAddActivity extends TuyaBaseDeviceActivity {


    @BindView(R.id.rv_shebei)
    RecyclerView rv_shebei;
    @BindView(R.id.rl_shuoming)
    RelativeLayout rl_shuoming;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.bt_xiayibu)
    TextView bt_xiayibu;
    @BindView(R.id.rl_faxian)
    RelativeLayout rl_faxian;
    private ITuyaActivator mTuyaGWSubActivator;
    private List<DeviceBean> deviceBeans = new ArrayList<>();
    private TuyaDeviceAdapter adapter;

    private String familyId;
    private String devId;
    private long homeId;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TuyaWangguanAddActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_wangguan_add;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("搜索设备");
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
        ButterKnife.bind(this);
        homeId = TuyaHomeManager.getHomeManager().getHomeId();
        devId = TuyaDeviceManager.getDeviceManager().getDevId();
        familyId = PreferenceHelper.getInstance(mContext).getString(AppConfig.PEIWANG_FAMILYID, "");

        initAdapter();
        starPeiwang();
        initHuidiao();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_ADD) {
                    finish();
                }
            }
        }));
    }

    private void starPeiwang() {
        startAnimation();
        TuyaGwSubDevActivatorBuilder builder = new TuyaGwSubDevActivatorBuilder()
                .setDevId(devId)
                .setTimeOut(120)
                .setListener(new ITuyaSmartActivatorListener() {
                    @Override
                    public void onError(String errorCode, String errorMsg) {
                        Y.e("获取设备失败 " + errorMsg);
                    }

                    @Override
                    public void onActiveSuccess(DeviceBean devResp) {
                        Y.e("成功添加设备" + devResp.getName() + "   " + devResp.getIconUrl());
                        getShebei();
                        addShebei(devResp);
                        deviceBeans.add(devResp);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onStep(String step, Object data) {

                    }
                });
        mTuyaGWSubActivator = TuyaHomeSdk.getActivatorInstance().newGwSubDevActivator(builder);
        //开始配网
        mTuyaGWSubActivator.start();
    }

    private void addShebei(DeviceBean devResp) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16046");
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
        map.put("wg_device_ccid", devId);
        Gson gson = new Gson();
        OkGo.<AppResponse<ZhiNengHomeBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengHomeBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengHomeBean.DataBean>> response) {

                    }
                });
    }

    private void stopPeiwang() {
        stopAnimation();
        if (mTuyaGWSubActivator != null) {
            mTuyaGWSubActivator.stop();
            mTuyaGWSubActivator.onDestroy();
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


    private void getShebei() {
        rv_shebei.setVisibility(View.VISIBLE);
        rl_shuoming.setVisibility(View.GONE);
        rl_faxian.setVisibility(View.VISIBLE);
    }

    private void initAdapter() {
        adapter = new TuyaDeviceAdapter(deviceBeans, mContext);
        rv_shebei.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        rv_shebei.setAdapter(adapter);
        adapter.setListener(new OnTuyaItemClickListener() {
            @Override
            public void onItmeCilck(int pos) {
                DeviceBean deviceBean = deviceBeans.get(pos);
                Y.t("点击的了设备" + deviceBean.getName() + "  " + deviceBean.getIconUrl());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTuyaGWSubActivator != null) {
            //销毁
            mTuyaGWSubActivator.stop();
            mTuyaGWSubActivator.onDestroy();
        }
    }

    @OnClick(R.id.bt_xiayibu)
    public void onViewClicked() {
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
        TuyaDeviceAddFinishActivity.actionStart(mContext, deviceModels);
    }
}
