package com.yiyang.cn.activity.zhinengjiaju.function;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.ZhiNengRoomManageActivity;
import com.yiyang.cn.activity.zhinengjiaju.GengDuoJingBaoActivity;
import com.yiyang.cn.adapter.YanGanListAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
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
import com.yiyang.cn.util.DoMqttValue;
import com.yiyang.cn.util.SoundPoolUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

//智能家居 门磁
public class YanGanActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.tv_room_delete)
    TextView tvRoomDelete;

    private YanGanListAdapter menCiListAdapter;
    private List<AlarmListBean> mDatas;

    private TextView tvJiaTingName;
    private TextView tvRoomName;
    private TextView tvMingChengName;
    private TextView tvLeiXingName;
    private Switch switch1;
    private View viewZhongJian;
    private ImageView ivYanGan;
    private LordingDialog lordingDialog;
    private ImageView ivShebeiZaixianzhuangtaiImg;//在线离线 红标
    private TextView zaiXianLiXian;//在线离线
    private RelativeLayout rlMingCheng;
    private RelativeLayout rlFangJian;
    private int pageNumber=0;


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

        menCiListAdapter = new YanGanListAdapter(R.layout.item_yangan_list, R.layout.item_menci_header, mDatas);
        rlvList.setLayoutManager(new LinearLayoutManager(mContext));
        rlvList.setAdapter(menCiListAdapter);

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
        View view = View.inflate(mContext, R.layout.yangan_header, null);

        tvJiaTingName = view.findViewById(R.id.tv_jiating_name);
        tvLeiXingName = view.findViewById(R.id.tv_leixing_name);
        tvMingChengName = view.findViewById(R.id.tv_mingcheng_name);
        tvRoomName = view.findViewById(R.id.tv_room_name);
        switch1 = view.findViewById(R.id.btn_gaojing);
        viewZhongJian = view.findViewById(R.id.view_zhongjian);
        ivYanGan = view.findViewById(R.id.iv_yangan);
        ivYanGan.setBackgroundResource(R.mipmap.tuya_yanwu_pic_normal);
        rlMingCheng = view.findViewById(R.id.rl_mingcheng);
        rlMingCheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(mContext, ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME);
                zhiNengFamilyAddDIalog.show();
            }
        });
        rlFangJian = view.findViewById(R.id.rl_fangjian);
        rlFangJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("device_id", dataBean.getDevice_id());
                bundle.putString("family_id", dataBean.getFamily_id());
                bundle.putString("member_type", memberType);
                startActivity(new Intent(mContext, ZhiNengRoomManageActivity.class).putExtras(bundle));
            }
        });
        zaiXianLiXian = view.findViewById(R.id.tv_shebei_zaixian_huashu);
        ivShebeiZaixianzhuangtaiImg = view.findViewById(R.id.iv_shebei_zaixianzhuangtai_img);


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
        Switch switchBaoJingTishiYin = view.findViewById(R.id.btn_baojing_tishiyin);


        String strBaoJing = PreferenceHelper.getInstance(mContext).getString(AppConfig.BAOJING_YANGAN, "2");
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
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.BAOJING_YANGAN, "1");
                } else {
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.BAOJING_YANGAN, "0");
                }
            }
        });


        menCiListAdapter.addHeaderView(view);
        menCiListAdapter.setNewData(mDatas);
        srLSmart.setEnableLoadMore(false);
        getNet();

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice notice) {
                if (notice.type == ConstanceValue.MSG_ZHINENGJIAJU_MENCIPAGE) {
                    getNet();
                } else if (notice.type == ConstanceValue.MSG_SHEBEIZHUANGTAI) {
                    List<String> messageList = (List<String>) notice.content;
                    String zhuangZhiId = messageList.get(0);
                    String kaiGuanDengZhuangTai = messageList.get(1);
                    if (kaiGuanDengZhuangTai.equals("2")) {//开
                        ivYanGan.setBackgroundResource(R.mipmap.tuya_yanwu_pic_normal);
                    } else if (kaiGuanDengZhuangTai.equals("1")) {//关
                        ivYanGan.setBackgroundResource(R.mipmap.tuya_yanwu_pic_no);
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
                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(YanGanActivity.this,
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
        return R.layout.layout_yangan;
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_id, String memberType) {
        Intent intent = new Intent(context, YanGanActivity.class);
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
        Intent intent = new Intent(context, YanGanActivity.class);
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
                        mDatas.clear();
                        if (dataBean.getWarn_state().equals("1")) {
                            ivYanGan.setBackgroundResource(R.mipmap.tuya_yanwu_pic_normal);
                        } else if (dataBean.getWarn_state().equals("2")) {
                            ivYanGan.setBackgroundResource(R.mipmap.tuya_yanwu_pic_no);

                        }
                        String onlineState = dataBean.getOnline_state();
                        if (onlineState.equals("1")) {
                            zaiXianLiXian.setText("设备在线");
                            ivShebeiZaixianzhuangtaiImg.setBackgroundResource
                                    (R.drawable.bg_zhineng_device_online);

                        } else if (onlineState.equals("2")) {
                            zaiXianLiXian.setText("设备离线");
                            ivShebeiZaixianzhuangtaiImg.setBackgroundResource
                                    (R.drawable.bg_zhineng_device_offline);
                        }
                        tvJiaTingName.setText(dataBean.getFamily_name());
                        tvLeiXingName.setText(dataBean.getDevice_type_name());
                        tvMingChengName.setText(dataBean.getDevice_name());
                        tvRoomName.setText(dataBean.getRoom_name());

                        if (dataBean.getIs_alarm().equals("1")) {//1 是
                            switch1.setChecked(true);
                        } else if (dataBean.getIs_alarm().equals("2")) { //2 否
                            switch1.setChecked(false);
                        }

                        if (dataBean.getAlarm_list().size() == 0) {
                            return;
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
                        lordingDialog.setTextMsg("正在加载，请稍后");
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

                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(YanGanActivity.this,
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

    public int BAOJING = 0X11;
    public int BUBAOJING = 0X12;
//    private class SosThread extends Thread {
//        private int i = 0;
//
//        public void run() {
//            while (i != 4) {
//                try {
//                    if (i == 0) {
//                        Message message = new Message();
//                        message.what = BAOJING;
//                        handler.sendMessage(message);
//                    } else if (i == 1) {
//                        Message message = new Message();
//                        message.what = BUBAOJING;
//                        handler.sendMessage(message);
//                    } else if (i == 2) {
//                        Message message = new Message();
//                        message.what = BAOJING;
//                        handler.sendMessage(message);
//                    } else if (i == 3) {
//                        Message message = new Message();
//                        message.what = BUBAOJING;
//                        handler.sendMessage(message);
//                    }
//                    i = i + 1;
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    break;
//
//                }
//            }
//        }
//    }


}
