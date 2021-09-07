package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.RenTiGanYingSetting;
import com.yiyang.cn.activity.SuiYiTieSetting;
import com.yiyang.cn.adapter.MenCiListAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.LordingDialog;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_Success;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.AlarmListBean;
import com.yiyang.cn.model.MenCiListModel;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
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

//智能家居 门磁
public class RenTiGanYingActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.tv_room_delete)
    TextView tvRoomDelete;

    private MenCiListAdapter menCiListAdapter;
    private List<AlarmListBean> mDatas;

    private TextView tvJiaTingName;
    private TextView tvRoomName;
    private TextView tvMingChengName;
    private TextView tvLeiXingName;
    private Switch switch1;
    private ImageView ivYiDong;

    View headerView;
    private LinearLayout ll_caozuo_jilu;
    ZnjjMqttMingLing znjjMqttMingLing;
    private String deviceCCid = "";
    LordingDialog lordingDialog;
    private ImageView ivShebeiZaixianzhuangtaiImg;//在线离线 红标
    private TextView zaiXianLiXian;//在线离线

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lordingDialog = new LordingDialog(mContext);
        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.ZHINENGJIAJU);
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });
        device_id = getIntent().getStringExtra("device_id");
        memberType = getIntent().getStringExtra("memberType");

        if (memberType == null) {
            tvRoomDelete.setVisibility(View.GONE);
        }

        mDatas = new ArrayList<>();
        //header 设备在线和离线
//        if (onlineState.equals("1")) {
//            tvShebeiZaixianHuashu.setText("设备在线");
//            ivShebeiZaixianzhuangtaiImg.setBackgroundResource(R.drawable.bg_zhineng_device_online);
//
//        } else if (onlineState.equals("2")) {
//            tvShebeiZaixianHuashu.setText("设备离线");
//            ivShebeiZaixianzhuangtaiImg.setBackgroundResource(R.drawable.bg_zhineng_device_offline);
//        }

        menCiListAdapter = new MenCiListAdapter(R.layout.item_menci_list, R.layout.item_menci_header, mDatas);
        rlvList.setLayoutManager(new LinearLayoutManager(mContext));
        rlvList.setAdapter(menCiListAdapter);


        headerView = View.inflate(mContext, R.layout.rentiganying_header, null);


        tvJiaTingName = headerView.findViewById(R.id.tv_jiating_name);
        tvLeiXingName = headerView.findViewById(R.id.tv_leixing_name);
        tvMingChengName = headerView.findViewById(R.id.tv_mingcheng_name);
        tvRoomName = headerView.findViewById(R.id.tv_room_name);
        switch1 = headerView.findViewById(R.id.btn_gaojing);
        ivYiDong = headerView.findViewById(R.id.iv_yidong);
        ivYiDong.setBackgroundResource(R.mipmap.zanwuyidong);
        ll_caozuo_jilu = headerView.findViewById(R.id.ll_caozuo_jilu);
        zaiXianLiXian = headerView.findViewById(R.id.tv_shebei_zaixian_huashu);
        ivShebeiZaixianzhuangtaiImg = headerView.findViewById(R.id.iv_shebei_zaixianzhuangtai_img);
        Switch switchBaoJingTishiYin = headerView.findViewById(R.id.btn_baojing_tishiyin);


        String strBaoJing = PreferenceHelper.getInstance(mContext).getString(AppConfig.BAOJINGRENTIGANYING, "2");
        if (strBaoJing.equals("0")) {
            switchBaoJingTishiYin.setChecked(false);
        } else {
            switchBaoJingTishiYin.setChecked(true);
        }

        switchBaoJingTishiYin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isPressed()) {
                    return;
                }
                if (isChecked) {
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.BAOJINGRENTIGANYING, "1");
                } else {
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.BAOJINGRENTIGANYING, "0");
                }
            }
        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isPressed()) {
                    return;
                }
                if (isChecked) {
                    getGaoJingTiShiNet("1");
                } else {
                    getGaoJingTiShiNet("2");
                }
            }
        });

        menCiListAdapter.addHeaderView(headerView);
        menCiListAdapter.setNewData(mDatas);
        srLSmart.setEnableLoadMore(false);
        getNet();

        menCiListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rrl_gengduo:
                        //UIHelper.ToastMessage(mContext, mDatas.get(position).sel_alarm_date + "更多");
                        GengDuoJingBaoActivity.actionStart(mContext, device_id, menCiListAdapter.getData().get(position).sel_alarm_date);
                        break;
                }
            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice notice) {
                if (notice.type == ConstanceValue.MSG_ZHINENGJIAJU_MENCIPAGE) {
                    getNet();
                } else if (notice.type == ConstanceValue.MSG_SHEBEIZHUANGTAI) {
                    List<String> messageList = (List<String>) notice.content;
                    String zhuangZhiId = messageList.get(0);
                    String kaiGuanDengZhuangTai = messageList.get(1);

                    if (zhuangZhiId.equals(deviceCCid)) {
                        if (kaiGuanDengZhuangTai.equals("2")) {//门磁开
                            ivYiDong.setBackgroundResource(R.mipmap.yourenyidong);
                        } else if (kaiGuanDengZhuangTai.equals("1")) {//门磁关
                            ivYiDong.setBackgroundResource(R.mipmap.zanwuyidong);
                        }
                    }

                }
            }
        }));

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
                        if (memberType.equals("1")) {

                            //删除设备
                            deleteDevice();

                        } else {
                            Toast.makeText(mContext, "操作失败，需要管理员身份", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                myCarCaoZuoDialog_caoZuoTIshi.show();
            }
        });


    }


    @Override
    public int getContentViewResId() {
        return R.layout.layout_rentiganying;
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_id, String memberType) {
        Intent intent = new Intent(context, RenTiGanYingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_id", device_id);
        intent.putExtra("memberType", memberType);
        context.startActivity(intent);
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_id) {
        Intent intent = new Intent(context, RenTiGanYingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_id", device_id);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("人体感应详情");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        iv_rightTitle.setVisibility(View.GONE);
        iv_rightTitle.setImageResource(R.mipmap.fengnuan_icon_shezhi);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RenTiGanYingSetting.actionStart(mContext, device_id, deviceCCid, device_id);
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

    @Override
    public boolean showToolBar() {
        return true;
    }

    private String device_id;
    private String memberType;
    MenCiListModel.DataBean dataBean;

    private boolean firstEnter = true;
    View view;

    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16043");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);
        map.put("page_num", "0");

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<MenCiListModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MenCiListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MenCiListModel.DataBean>> response) {
                        srLSmart.finishRefresh();
                        dataBean = response.body().data.get(0);
                        deviceCCid = dataBean.getDevice_ccid();
                        String onlineState = dataBean.getOnline_state();
                        if (onlineState != null) {
                            if (onlineState.equals("1")) {
                                zaiXianLiXian.setText("设备在线");
                                ivShebeiZaixianzhuangtaiImg.setBackgroundResource(R.drawable.bg_zhineng_device_online);

                            } else if (onlineState.equals("2")) {
                                zaiXianLiXian.setText("设备离线");
                                ivShebeiZaixianzhuangtaiImg.setBackgroundResource(R.drawable.bg_zhineng_device_offline);
                            }
                        }
                        if (firstEnter) {
                            znjjMqttMingLing = new ZnjjMqttMingLing(mContext, dataBean.getDevice_ccid_up(), dataBean.getServer_id());
                            znjjMqttMingLing.subscribeAppShiShiXinXi(new IMqttActionListener() {
                                @Override
                                public void onSuccess(IMqttToken asyncActionToken) {
                                    getNet();
                                }

                                @Override
                                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                                }
                            });
                            firstEnter = false;
                        }

//                        N9Thread n9Thread = new N9Thread();
//                        n9Thread.start();


                        if (dataBean.getWarn_state().equals("1")) {
                            ivYiDong.setBackgroundResource(R.mipmap.zanwuyidong);
                        } else if (dataBean.getWarn_state().equals("2")) {
                            ivYiDong.setBackgroundResource(R.mipmap.yourenyidong);
                        }
                        tvJiaTingName.setText(dataBean.getFamily_name());
                        tvLeiXingName.setText(dataBean.getDevice_name());
                        tvMingChengName.setText(dataBean.getDevice_type_name());
                        tvRoomName.setText(dataBean.getRoom_name());

                        if (dataBean.getIs_alarm().equals("1")) {//1 是
                            switch1.setChecked(true);
                        } else if (dataBean.getIs_alarm().equals("2")) { //2 否
                            switch1.setChecked(false);
                        }


                        if (view == null) {
                            if (dataBean.getAlarm_list().size() == 0) {
                                view = View.inflate(mContext, R.layout.zhinneng_jiaju_empty_view, null);
                                //ll_caozuo_jilu.addView(view);
                                menCiListAdapter.addFooterView(view);
                                menCiListAdapter.notifyDataSetChanged();
                                return;
                            }
                        }


                        for (int i = 0; i < dataBean.getAlarm_list().size(); i++) {

                            AlarmListBean alarmListBean = new AlarmListBean(true, dataBean.getAlarm_list().get(i).getAlarm_date());
                            alarmListBean.alarm_date = dataBean.getAlarm_list().get(i).getAlarm_date();
                            alarmListBean.is_more = dataBean.getAlarm_list().get(i).is_more;
                            alarmListBean.sel_alarm_date = dataBean.getAlarm_list().get(i).sel_alarm_date;
                            mDatas.add(alarmListBean);

                            for (int j = 0; j < dataBean.getAlarm_list().get(i).getAlerm_time_list().size(); j++) {

                                MenCiListModel.DataBean.AlarmListBean.AlermTimeListBean alermTimeListBean = dataBean.getAlarm_list().get(i).getAlerm_time_list().get(j);
                                AlarmListBean alarmListBean1 = new AlarmListBean(false, dataBean.getAlarm_list().get(i).getAlarm_date());

                                alarmListBean1.alerm_time = alermTimeListBean.getAlerm_time();
                                alarmListBean1.device_state = alermTimeListBean.getDevice_state();
                                alarmListBean1.device_state_name = alermTimeListBean.getDevice_state_name();
                                mDatas.add(alarmListBean1);
                            }

                        }

                        menCiListAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Response<AppResponse<MenCiListModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<MenCiListModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        lordingDialog.setTextMsg("数据加载中，请稍后");
                        lordingDialog.show();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        if (lordingDialog.isShowing()) {
                            lordingDialog.dismiss();
                        }
                    }
                });
    }

    private void getGaoJingTiShiNet(String isAlarm) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16044");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);
        map.put("is_alarm", isAlarm);

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<MenCiListModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MenCiListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MenCiListModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "设置成功");
                    }

                    @Override
                    public void onError(Response<AppResponse<MenCiListModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }
                });

    }

    TishiDialog tishiDialog;

    private void deleteDevice() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "16034");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        if (!StringUtils.isEmpty(dataBean.getFamily_id())) {
            map.put("family_id", dataBean.getFamily_id());
            map.put("device_id", dataBean.getDevice_id());
            UIHelper.ToastMessage(mContext, "系统错误");
            finish();
            return;
        }

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

                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(RenTiGanYingActivity.this,
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (znjjMqttMingLing != null) {
            znjjMqttMingLing.unSubscribeShiShiXinXi(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });

            znjjMqttMingLing.unSubscribeAppShiShiXinXi(new IMqttActionListener() {
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
