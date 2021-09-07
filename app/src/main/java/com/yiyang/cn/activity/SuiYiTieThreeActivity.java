package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.zhinengjiaju.RenTiGanYingActivity;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.SuiYiTieModel;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class SuiYiTieThreeActivity extends BaseActivity {
    @BindView(R.id.iv_1)
    ImageButton iv1;
    @BindView(R.id.ll_1)
    RoundLinearLayout ll1;
    @BindView(R.id.iv_2)
    ImageButton iv2;
    @BindView(R.id.ll_2)
    RoundLinearLayout ll2;
    @BindView(R.id.iv_3)
    ImageButton iv3;
    @BindView(R.id.ll_3)
    RoundLinearLayout ll3;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add1)
    ImageView ivAdd1;
    @BindView(R.id.rl_left)
    RoundRelativeLayout rlLeft;
    @BindView(R.id.iv_add2)
    ImageView ivAdd2;
    @BindView(R.id.rl_center)
    RoundRelativeLayout rlCenter;
    @BindView(R.id.iv_add3)
    ImageView ivAdd3;
    @BindView(R.id.rl_right)
    RoundRelativeLayout rlRight;
    @BindView(R.id.rl_touming1)
    RelativeLayout rlTouming1;
    @BindView(R.id.rl_touming2)
    RelativeLayout rlTouming2;
    @BindView(R.id.rl_touming3)
    RelativeLayout rlTouming3;
    @BindView(R.id.tv_add1_name)
    TextView tvAdd1Name;
    @BindView(R.id.tv_add2_name)
    TextView tvAdd2Name;
    @BindView(R.id.tv_add3_name)
    TextView tvAdd3Name;
    @BindView(R.id.tvsuiyitie)
    TextView tvsuiyitie;
    @BindView(R.id.iv_one)
    ImageButton ivOne;
    @BindView(R.id.rll_one)
    RoundRelativeLayout rllOne;

    private String device_ccid = "";
    private String device_ccidup = "";
    private String paiwei = "1";
    private List<SuiYiTieModel.DataBean.DeviceListBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device_ccid = getIntent().getStringExtra("device_ccid");
        device_ccidup = getIntent().getStringExtra("device_ccid_up");
        kongJianClick();
        getnet();

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_KAIGUAN_DELETE) {
                    finish();
                }
            }
        }));
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_suiyitie_two;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("万能随意贴");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setImageResource(R.mipmap.mine_shezhi);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuiYiTieSetting.actionStart(mContext, device_ccid, device_ccidup);
            }
        });
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void kongJianClick() {
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(bangDingLeft)) {
                    if (bangDingLeft.equals("0")) {
                        UIHelper.ToastMessage(mContext, "请绑定设备后重新尝试");
                        return;
                    }
                    if (iv1.isSelected()) {
                        zhiXingMqtt(0, "02");
                        iv1.setSelected(false);
                    } else {
                        zhiXingMqtt(0, "01");
                        iv1.setSelected(true);
                    }
                }
            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(bangDingCenter)) {
                    if (bangDingCenter.equals("0")) {
                        UIHelper.ToastMessage(mContext, "请绑定设备后重新尝试");
                        return;
                    }
                    if (iv2.isSelected()) {
                        zhiXingMqtt(1, "02");
                        iv2.setSelected(false);
                    } else {
                        zhiXingMqtt(1, "01");
                        iv2.setSelected(true);
                    }
                }
            }
        });

        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(bangDingRight)) {
                    if (bangDingRight.equals("0")) {
                        UIHelper.ToastMessage(mContext, "请绑定设备后重新尝试");
                        return;
                    }
                    if (iv3.isSelected()) {
                        zhiXingMqtt(2, "02");
                        iv3.setSelected(false);
                    } else {
                        zhiXingMqtt(2, "01");
                        iv3.setSelected(true);
                    }
                }
            }
        });
        rlLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(bangDingLeft)) {
                    tanChuang("0", bangDingLeft);
                }
            }
        });
        rlCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(bangDingCenter)) {
                    tanChuang("1", bangDingCenter);
                }
            }
        });
        rlRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(bangDingRight)) {
                    tanChuang("2", bangDingRight);
                }
            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_ccid, String device_ccidup) {
        Intent intent = new Intent(context, SuiYiTieThreeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        intent.putExtra("device_ccid_up", device_ccidup);
        context.startActivity(intent);
    }

    public void tanChuang(String paiwei, String bangDingType) {
        if (bangDingType != null) {
            if (bangDingType.equals("1")) {
                String[] items = {"添加新设备", "移除设备"};
                final ActionSheetDialog dialog = new ActionSheetDialog(this, items, null);
                dialog.isTitleShow(false).show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        Uri imageUri = Uri.fromFile(file);
                        switch (position) {
                            case 0:
                                String deviceCcidSt = "";
                                //UIHelper.ToastMessage(mContext, "绑定设备");
                                if (mDatas.get(Integer.valueOf(paiwei)) != null) {
                                    deviceCcidSt = mDatas.get(Integer.valueOf(paiwei)).getDevice_ccid();
                                }
                                SuiYiTieTianJiaSheBeiActivity.actionStart(mContext, paiwei, device_ccid, device_ccidup, "2", deviceCcidSt);
                                break;
                            case 1:
                                yiChuSheBei(paiwei);
                                break;
                        }
                        dialog.dismiss();
                    }
                });
            } else {
                String deviceCcidSt = "";
                //UIHelper.ToastMessage(mContext, "绑定设备");
                if (mDatas.get(Integer.valueOf(paiwei)) != null) {
                    deviceCcidSt = mDatas.get(Integer.valueOf(paiwei)).getDevice_ccid();
                }
                SuiYiTieTianJiaSheBeiActivity.actionStart(mContext, paiwei, device_ccid, device_ccidup, "2", deviceCcidSt);
            }
        } else {
            String deviceCcidSt = "";
            //UIHelper.ToastMessage(mContext, "绑定设备");

            if (mDatas.get(Integer.valueOf(paiwei)) != null) {
                deviceCcidSt = mDatas.get(Integer.valueOf(paiwei)).getDevice_ccid();
            }
            SuiYiTieTianJiaSheBeiActivity.actionStart(mContext, paiwei, device_ccid, device_ccidup, "2", deviceCcidSt);
        }
    }


    public void yiChuSheBei(String paiwei) {


        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16051");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        String kaiGuanType = mDatas.get(Integer.valueOf(paiwei)).getDevice_type();
        if (kaiGuanType.equals("35")) {
            map.put("unbinding_type", "1");
        } else {
            map.put("unbinding_type", "2");
        }

        map.put("device_ccid", mDatas.get(Integer.valueOf(paiwei)).getDevice_ccid());
        map.put("device_ccid_up", device_ccidup);
        String familyId = PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, "0");
        map.put("family_id", familyId);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<SuiYiTieModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SuiYiTieModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "移除设备成功");
                        getnet();
                        showLoadSuccess();
                    }

                    @Override
                    public void onError(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                        showLoadSuccess();
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

    String bangDingLeft;//0否 1 是
    String bangDingCenter;// 0否 1是
    String bangDingRight;//0否 1是


    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16052");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("device_ccid_up", device_ccidup);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<SuiYiTieModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SuiYiTieModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        showLoadSuccess();
                        flag = true;//第一次进入不走onresume


                        ll2.setVisibility(View.VISIBLE);
                        ll1.setVisibility(View.VISIBLE);
                        ll3.setVisibility(View.VISIBLE);

                        rlCenter.setVisibility(View.VISIBLE);
                        rlLeft.setVisibility(View.VISIBLE);
                        rlRight.setVisibility(View.VISIBLE);

                        ivAdd1.setVisibility(View.VISIBLE);
                        ivAdd2.setVisibility(View.VISIBLE);
                        ivAdd3.setVisibility(View.VISIBLE);

                        tvAdd2Name.setVisibility(View.VISIBLE);
                        tvAdd1Name.setVisibility(View.VISIBLE);
                        tvAdd3Name.setVisibility(View.VISIBLE);

                        tvLeft.setVisibility(View.VISIBLE);
                        tvCenter.setVisibility(View.VISIBLE);
                        tvRight.setVisibility(View.VISIBLE);


                        Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd1);
                        Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd2);
                        Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd3);
                        mDatas.clear();
                        mDatas.addAll(response.body().data.get(0).getDevice_list());

                        showAddIcon(response);

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

    private void showAddIcon(Response<AppResponse<SuiYiTieModel.DataBean>> response) {

        if (response.body().data.get(0).getBinding_type() != null) {
            if (response.body().data.get(0).getBinding_type().equals("1")) {
                rlLeft.setVisibility(View.GONE);
                rlCenter.setVisibility(View.GONE);
                rlRight.setVisibility(View.GONE);
                tvsuiyitie.setVisibility(View.VISIBLE);
                String str1 = response.body().data.get(0).getDevice_list().get(0).getBinding_device_name();
                String str = "该随意贴已与" + str1 + "绑定，如需解绑请去设置页设置";
                tvsuiyitie.setText(str);

            } else {
                showSuiYiTie(response);
            }
        } else {
            showSuiYiTie(response);
        }

//        if (StringUtils.isEmpty(response.body().data.get(0).getBinding_type())) {
//            tvsuiyitie.setVisibility(View.GONE);
//            tvAdd1Name.setText("添加");
//            tvAdd2Name.setText("添加");
//            tvAdd3Name.setText("添加");
//        }
    }


    public void showSuiYiTie(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
        if (response.body().data.get(0).getDevice_list().size() == 3) {
            tvsuiyitie.setVisibility(View.GONE);
            if (StringUtils.isEmpty(response.body().data.get(0).getDevice_list().get(0).getBinding_device_name())) {
                Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd1);
                bangDingLeft = "0";
            } else {
                bangDingLeft = "1";
                SuiYiTieModel.DataBean dataBean0 = response.body().data.get(0);
                Glide.with(mContext).load(dataBean0.getDevice_list().get(0).getBinding_device_type_pic()).into(ivAdd1);
                tvAdd1Name.setText(dataBean0.getDevice_list().get(0).getBinding_device_name());

                if (dataBean0.getDevice_list().get(0).getWork_state().equals("1")) {
                    iv1.setSelected(true);
                } else if (dataBean0.getDevice_list().get(0).getWork_state().equals("2")) {
                    iv1.setSelected(false);
                }
            }

            if (StringUtils.isEmpty(response.body().data.get(0).getDevice_list().get(1).getBinding_device_name())) {
                Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd2);
                bangDingCenter = "0";
            } else {
                bangDingCenter = "1";
                SuiYiTieModel.DataBean dataBean1 = response.body().data.get(0);
                Glide.with(mContext).load(dataBean1.getDevice_list().get(1).getBinding_device_type_pic()).into(ivAdd2);
                tvAdd2Name.setText(dataBean1.getDevice_list().get(1).getBinding_device_name());

                if (dataBean1.getDevice_list().get(1).getWork_state().equals("1")) {
                    iv2.setSelected(true);
                } else if (dataBean1.getDevice_list().get(1).getWork_state().equals("2")) {
                    iv2.setSelected(false);
                }
            }

            if (StringUtils.isEmpty(response.body().data.get(0).getDevice_list().get(2).getBinding_device_name())) {
                Glide.with(mContext).load(R.mipmap.tuya_nav_icon_add).into(ivAdd3);
                bangDingRight = "0";
            } else {
                bangDingRight = "1";
                SuiYiTieModel.DataBean dataBean2 = response.body().data.get(0);
                Glide.with(mContext).load(dataBean2.getDevice_list().get(2).getBinding_device_type_pic()).into(ivAdd3);
                tvAdd3Name.setText(dataBean2.getDevice_list().get(2).getBinding_device_name());

                if (dataBean2.getDevice_list().get(2).getWork_state().equals("1")) {
                    iv3.setSelected(true);
                } else if (dataBean2.getDevice_list().get(2).getWork_state().equals("2")) {
                    iv3.setSelected(false);
                }
            }

        }
    }

    private boolean flag = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (flag) {
            getnet();
        }

    }

    public ZnjjMqttMingLing mqttMingLing = null;
    String nowData;

    private String device_ccid_up = null;
    private String serverId = null;

    public void zhiXingMqtt(int position, String caoZuoFangShi) {
        if (mqttMingLing == null) {
            device_ccid_up = mDatas.get(position).getDevice_ccid_up();
            serverId = mDatas.get(position).getServer_id();


            mqttMingLing = new ZnjjMqttMingLing(mContext, mDatas.get(position).getDevice_ccid_up(), mDatas.get(position).getServer_id());
            nowData = "zn/app/" + mDatas.get(position).getServer_id() + mDatas.get(position).getDevice_ccid_up();
            //     hardwareData = "zn/hardware/" + mDatas.get(position).getServer_id() + mDatas.get(position).getDevice_ccid_up();
            AndMqtt.getInstance().subscribe(new MqttSubscribe()
                    .setTopic(nowData)
                    .setQos(2), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("Rair", "订阅的地址:  " + nowData);
                    //  UIHelper.ToastMessage(mContext, "增加订阅次数");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
                }
            });

        }

        String zhuangZhiId = mDatas.get(position).getDevice_ccid();
        String zhiLing = "i" + zhuangZhiId + "1" + "01" + "003" + ".";
        Log.i("Rair", zhiLing);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(zhiLing)
                .setQos(2).setRetained(false)
                .setTopic(nowData), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mqttMingLing != null) {
            mqttMingLing.unSubscribeShiShiXinXi_App(device_ccid_up, serverId, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("Rair", "接收app信息取消订阅成功");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });

            mqttMingLing.unSubscribeHardware_WithCanShu(device_ccid_up, serverId, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("Rair", "发送硬件信息订阅取消成功");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        }
    }
}

