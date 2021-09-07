package com.yiyang.cn.activity.zhinengjiaju.function;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.ZhiNengDianDengActivity;
import com.yiyang.cn.activity.ZhiNengJiaJuChaZuoActivity;
import com.yiyang.cn.activity.ZhiNengRoomManageActivity;
import com.yiyang.cn.activity.zhinengjiaju.GengDuoJingBaoActivity;
import com.yiyang.cn.adapter.MenCiListAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.LordingDialog;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_Success;
import com.yiyang.cn.dialog.ZhiNengFamilyAddDIalog;
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

import static com.yiyang.cn.config.MyApplication.CAR_CTROL;
import static com.yiyang.cn.dialog.newdia.TishiDialog.TYPE_CAOZUO;
import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

//智能家居 门磁
public class MenCiActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.tv_room_delete)
    TextView tvRoomDelete;

    private MenCiListAdapter menCiListAdapter;
    private List<AlarmListBean> mDatas;
    RoundRelativeLayout rlQingKong;
    private TextView tvJiaTingName;
    private TextView tvRoomName;
    private TextView tvMingChengName;
    private TextView tvLeiXingName;
    private Switch switch1;
    private View viewZhongJian;
    View headerView;
    private LinearLayout ll_caozuo_jilu;
    ZnjjMqttMingLing znjjMqttMingLing;
    private String deviceCCid = "";
    LordingDialog lordingDialog;
    RelativeLayout rlGaoJingSheZhi;
    TextView tvSheBeiZaiXianHuaShu;
    private int pageNumber = 0;
    RelativeLayout rlDeviceName;
    RelativeLayout rl_fangjian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lordingDialog = new LordingDialog(mContext);
        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.ZHINENGJIAJU);
        srLSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNumber = pageNumber + 1;
                getNet();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 0;
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


        headerView = View.inflate(mContext, R.layout.menci_header, null);


        tvJiaTingName = headerView.findViewById(R.id.tv_jiating_name);
        tvLeiXingName = headerView.findViewById(R.id.tv_leixing_name);
        tvMingChengName = headerView.findViewById(R.id.tv_mingcheng_name);
        tvRoomName = headerView.findViewById(R.id.tv_room_name);
        switch1 = headerView.findViewById(R.id.btn_gaojing);
        viewZhongJian = headerView.findViewById(R.id.view_zhongjian);
        ll_caozuo_jilu = headerView.findViewById(R.id.ll_caozuo_jilu);
        rlGaoJingSheZhi = headerView.findViewById(R.id.rl_gaojing_shezhi);
        tvSheBeiZaiXianHuaShu = headerView.findViewById(R.id.tv_shebei_zaixian_huashu);
        Switch switchBaoJingTishiYin = headerView.findViewById(R.id.btn_baojing_tishiyin);
        rlQingKong = headerView.findViewById(R.id.rl_qingkong);
        rlDeviceName = headerView.findViewById(R.id.rl_device_name);
        rl_fangjian = headerView.findViewById(R.id.rl_fangjian);
        rl_fangjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("device_id", dataBean.getDevice_id());
                bundle.putString("family_id", dataBean.getFamily_id());
                bundle.putString("member_type", memberType);
                startActivity(new Intent(mContext, ZhiNengRoomManageActivity.class).putExtras(bundle));
            }
        });
        rlDeviceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(mContext, ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME);
                zhiNengFamilyAddDIalog.show();
            }
        });


        rlQingKong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TishiDialog tishiDialog = new TishiDialog(mContext, TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
                    @Override
                    public void onClickCancel(View v, TishiDialog dialog) {

                    }

                    @Override
                    public void onClickConfirm(View v, TishiDialog dialog) {
                        // UIHelper.ToastMessage(mContext, "清空所有数据");
                        getQingKongNet();
                    }

                    @Override
                    public void onDismiss(TishiDialog dialog) {

                    }
                });
                tishiDialog.setTextContent("是否清空所有数据？");
                tishiDialog.show();
            }
        });

        String strBaoJing = PreferenceHelper.getInstance(mContext).getString(AppConfig.BAOJINGTISHIYIN, "2");
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
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.BAOJINGTISHIYIN, "1");
                } else {
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.BAOJINGTISHIYIN, "0");
                }
            }
        });
        rlGaoJingSheZhi.setVisibility(View.VISIBLE);
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
        srLSmart.setEnableRefresh(true);
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
                            viewZhongJian.setVisibility(View.VISIBLE);
                        } else if (kaiGuanDengZhuangTai.equals("1")) {//门磁关
                            viewZhongJian.setVisibility(View.GONE);
                        }
                    }
                    getNet();

                } else if (notice.type == ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME) {
                    changeDevice(notice.content.toString());
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

//        srLSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//
//                getNet();
//            }
//
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//
//            }
//        });

    }

    TishiDialog tishiDialog;

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
                            tvMingChengName.setText(deviceName);
                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(MenCiActivity.this,
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

    private void getQingKongNet() {
        //清空所有接口
        Map<String, String> map = new HashMap<>();
        map.put("code", "16067");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<MenCiListModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MenCiListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MenCiListModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "清空成功");
                    }

                    @Override
                    public void onError(Response<AppResponse<MenCiListModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }
                });
    }

    private class N9Thread extends Thread {
        boolean flag = true;

        public void run() {
            while (true) {
                try {

                    if (flag) {
                        AndMqtt.getInstance().publish(new MqttPublish()
                                .setMsg("i12010101197.")
                                .setQos(2)
                                .setTopic("zn/app/1/aaaaaaaaaaaaaaaa90140018")
                                .setRetained(false), new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                Log.i("Rair", "模拟硬件发送数据 开");

                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                            }
                        });


                    } else {
                        AndMqtt.getInstance().publish(new MqttPublish()
                                .setMsg("i12010101297.")
                                .setQos(2)
                                .setTopic("zn/app/1/aaaaaaaaaaaaaaaa90140018")
                                .setRetained(false), new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                Log.i("Rair", "模拟硬件发送数据 关");

                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                            }
                        });

                    }
                    flag = !flag;
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public int getContentViewResId() {
        return R.layout.layout_menci;
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_id, String memberType) {
        Intent intent = new Intent(context, MenCiActivity.class);
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
        Intent intent = new Intent(context, MenCiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_id", device_id);

        context.startActivity(intent);
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
        map.put("page_num", String.valueOf(pageNumber));

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
                        mDatas.clear();
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

                        if (dataBean.getWarn_state() != null) {
                            if (dataBean.getWarn_state().equals("1")) {
                                viewZhongJian.setVisibility(View.GONE);
                            } else if (dataBean.getWarn_state().equals("2")) {
                                viewZhongJian.setVisibility(View.VISIBLE);

                            }
                        }

                        if (dataBean.getOnline_state() != null) {
                            if (dataBean.getOnline_state().equals("1")) {
                                tvSheBeiZaiXianHuaShu.setText("设备在线");
                            } else if (dataBean.getOnline_state().equals("2")) {
                                tvSheBeiZaiXianHuaShu.setText("设备离线");
                            }
                        }

                        tvJiaTingName.setText(dataBean.getFamily_name());
                        tvLeiXingName.setText(dataBean.getDevice_type_name());
                        tvMingChengName.setText(dataBean.getDevice_name());
                        tvRoomName.setText(dataBean.getRoom_name());

                        if (dataBean.getIs_alarm() != null) {
                            if (dataBean.getIs_alarm().equals("1")) {//1 是
                                switch1.setChecked(true);
                            } else if (dataBean.getIs_alarm().equals("2")) { //2 否
                                switch1.setChecked(false);
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
                        if (dataBean.getAlarm_list().size() == 0) {
                            if (view == null) {
                                view = View.inflate(mContext, R.layout.zhinneng_jiaju_empty_view, null);
                                //ll_caozuo_jilu.addView(view);
                                menCiListAdapter.addFooterView(view);
                                menCiListAdapter.notifyDataSetChanged();
                            }

                        } else {
                            if (view != null) {
                                menCiListAdapter.removeAllHeaderView();
                            }
                        }
                        menCiListAdapter.notifyDataSetChanged();

                        if (response.body().next.equals("1")) {
                            srLSmart.setEnableLoadMore(true);
                        } else {
                            srLSmart.setEnableLoadMore(false);
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<MenCiListModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                        srLSmart.finishRefresh();
                    }

                    @Override
                    public void onStart(Request<AppResponse<MenCiListModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
//                        lordingDialog.setTextMsg("数据加载中，请稍后");
//                        lordingDialog.show();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
//                        if (lordingDialog.isShowing()) {
//                            lordingDialog.dismiss();
//                        }
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

                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(MenCiActivity.this,
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
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(mContext,
                                "提示", response.getException().getMessage(), "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {

                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
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
