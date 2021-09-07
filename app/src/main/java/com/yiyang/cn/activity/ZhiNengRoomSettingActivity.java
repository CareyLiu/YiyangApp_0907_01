package com.yiyang.cn.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.camera.TuyaCameraActivity;
import com.yiyang.cn.activity.tuya_device.device.DeviceChazuoActivity;
import com.yiyang.cn.activity.tuya_device.device.DeviceWangguanActivity;
import com.yiyang.cn.activity.tuya_device.device.DeviceWgCzActivity;
import com.yiyang.cn.activity.tuya_device.utils.TuyaConfig;
import com.yiyang.cn.activity.zckt.AirConditionerActivity;
import com.yiyang.cn.activity.zhinengjiaju.WenShiDuChuanGanQiActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.LouShuiActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.MenCiActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.MenSuoActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.SosActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.YanGanActivity;
import com.yiyang.cn.adapter.ZhiNengRoomManageSettingAdapter;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_Success;
import com.yiyang.cn.dialog.ZhiNengFamilyAddDIalog;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
import com.yiyang.cn.model.ZhiNengRoomManageSettingBean;
import com.yiyang.cn.tools.NetworkUtils;
import com.tuya.smart.api.MicroContext;
import com.tuya.smart.panelcaller.api.AbsPanelCallerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengRoomSettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_room_name)
    RelativeLayout rl_room_name;
    @BindView(R.id.tv_room_name)
    TextView tv_room_name;
    @BindView(R.id.tv_room_num)
    TextView tv_room_num;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_room_delete)
    TextView tv_room_delete;
    @BindView(R.id.tv_add_room_device)
    TextView tvAddRoomDevice;
    private Context context = ZhiNengRoomSettingActivity.this;
    private String member_type = "";
    private String room_id = "";
    private String family_id = "";
    private String room_name = "";
    private List<ZhiNengRoomManageSettingBean.DataBean> dataBeanList = new ArrayList<>();
    private ZhiNengRoomManageSettingAdapter zhiNengRoomManageSettingAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhineng_room_setting;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightMode(this);
        initToolbar();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        member_type = getIntent().getStringExtra("member_type");
        if (member_type == null) {
            member_type = "";
        }
        room_id = getIntent().getStringExtra("room_id");
        if (room_id == null) {
            room_id = "";
        }
        family_id = getIntent().getStringExtra("family_id");
        if (family_id == null) {
            family_id = "";
        }
        room_name = getIntent().getStringExtra("room_name");
        if (room_name == null) {
            room_name = "";
        }
        getnet();

        if (TextUtils.isEmpty(room_id)||"0".equals(room_id)){
            tvAddRoomDevice.setVisibility(View.GONE);
        }else {
            tvAddRoomDevice.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        tvAddRoomDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomAddDeviceActivity.actionStart(mContext, family_id, member_type, room_id, room_name);
            }
        });

        rl_room_name.setOnClickListener(this);
        tv_room_delete.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        zhiNengRoomManageSettingAdapter = new ZhiNengRoomManageSettingAdapter(R.layout.item_zhineng_room_setting, dataBeanList);
        zhiNengRoomManageSettingAdapter.setEmptyView(LayoutInflater.from(context).inflate(R.layout.activity_zhineng_room_setting_none, null));
        zhiNengRoomManageSettingAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(zhiNengRoomManageSettingAdapter);

        zhiNengRoomManageSettingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZhiNengRoomManageSettingBean.DataBean deviceBean = (ZhiNengRoomManageSettingBean.DataBean) adapter.getItem(position);
               if (TextUtils.isEmpty(room_id)||"0".equals(room_id)){
                   tiaozhuan(deviceBean);
               }else {
                   showClickDia(deviceBean, position);
               }

            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ROOM_MANAGE_CHANGENAME) {
                    changeRoom(message.content.toString());
                }
            }
        }));
    }

    private void showClickDia(ZhiNengRoomManageSettingBean.DataBean deviceBean, int pos) {

        String items[] = {"进入详情", "移除房间"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, items, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        tiaozhuan(deviceBean);
                        break;
                    case 1:
                        deviceTransfer(deviceBean.getDevice_id(), pos);
                        break;
                }
                dialog.dismiss();

            }
        });
    }


    /**
     * 设备转移
     */
    private void deviceTransfer(String device_id, int position) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16025");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("move_type", "2");
        map.put("room_id", room_id);
        map.put("device_id", device_id);
        map.put("family_id", family_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        if (response.body().msg.equals("ok")) {
                            Notice notice = new Notice();
                            notice.type = ConstanceValue.MSG_DEVICE_ROOM_NAME_CHANGE;
                            notice.content = room_name;
                            notice.devId = device_id;
                            RxBus.getDefault().sendRx(notice);

                            dataBeanList.remove(position);
                            zhiNengRoomManageSettingAdapter.notifyDataSetChanged();

                            MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(ZhiNengRoomSettingActivity.this,
                                    "成功", "成功将设备移出该房间", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {

                                }
                            });
                            dialog_success.show();
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
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

    private void tiaozhuan(ZhiNengRoomManageSettingBean.DataBean deviceBean) {
        /**
         / 00 主机 01.灯 02.插座 03.喂鱼 04.浇花 05门锁 06.空调电视(开关，加风，减风，讯飞语音配置)
         / 07.车库门  08.开关 09.晾衣架 10.窗磁 11.烟雾报警 12.门磁 13.漏水14.雷达
         / 15.紧急开关 16.窗帘 17.电视(开关，加减音量，加减亮暗，讯飞语音配置) 18.摄像头
         / 19.空气检测 20.温湿度检测 21.煤气管道关闭 22.自来水管道关闭 23.宠物喂食 24.宠物喂水
         / 25.智能手环 26.排风 27背景音乐显示控制 28.电视遥控 29.空气净化 30.体质检测
         / 31.光敏控制 32.燃气报警 33.风扇 34.雷达
         */
        if (deviceBean.getDevice_type().equals("20")) {//空调
            String ccid = deviceBean.getDevice_ccid();
            PreferenceHelper.getInstance(mContext).putString("ccid", ccid);
            PreferenceHelper.getInstance(mContext).putString("share_type", "1");
            PreferenceHelper.getInstance(mContext).putString("sim_ccid_save_type", "1");
            if (NetworkUtils.isConnected(mContext)) {
                Activity currentActivity = AppManager.getAppManager().currentActivity();
                if (currentActivity != null) {
                    AirConditionerActivity.actionStart(mContext, ccid, "智能空调");
                }
            } else {
                UIHelper.ToastMessage(mContext, "请连接网络后重新尝试");
            }
        } else if (deviceBean.getDevice_type().equals("16")) {//窗帘
            ZhiNengChuangLianActivity.actionStart(mContext, deviceBean.getDevice_id(), "", member_type);
        } else if (deviceBean.getDevice_type().equals("01")) {//灯
            ZhiNengDianDengActivity.actionStart(mContext, deviceBean.getDevice_id(), deviceBean.getDevice_type(), member_type);
        } else if (deviceBean.getDevice_type().equals("03")) {//喂鱼
            ZhiNengJiajuWeiYuAutoActivity.actionStart(mContext, deviceBean.getDevice_id(), deviceBean.getDevice_type(), member_type);
        } else if (deviceBean.getDevice_type().equals("04")) {//浇花
            ZhiNengJiaoHuaAutoActivity.actionStart(mContext, deviceBean.getDevice_id(), deviceBean.getDevice_type(), member_type);
        } else if (deviceBean.getDevice_type().equals("02")) {
            Bundle bundle = new Bundle();
            bundle.putString("device_id", deviceBean.getDevice_id());
            bundle.putString("device_type", deviceBean.getDevice_type());
            bundle.putString("member_type", member_type);
            bundle.putString("work_state", deviceBean.getWork_state());
            startActivity(new Intent(mContext, ZhiNengJiaJuChaZuoActivity.class).putExtras(bundle));
        } else if (deviceBean.getDevice_type().equals("12")) {
            MenCiActivity.actionStart(mContext, deviceBean.getDevice_id(), member_type);
        } else if (deviceBean.getDevice_type().equals("11")) {
            YanGanActivity.actionStart(mContext, deviceBean.getDevice_id());
        } else if (deviceBean.getDevice_type().equals("15")) {
            SosActivity.actionStart(mContext, deviceBean.getDevice_id(), member_type);
        } else if (deviceBean.getDevice_type().equals("05")) {
            MenSuoActivity.actionStart(mContext, deviceBean.getDevice_id(), member_type);
        } else if (deviceBean.getDevice_type().equals("13")) {
            LouShuiActivity.actionStart(mContext, deviceBean.getDevice_id(), member_type);
        } else if (deviceBean.getDevice_type().equals("08")) {//随意贴
            //SuiYiTieActivity.actionStart(mContext, deviceBean.getDevice_ccid(), deviceBean.getDevice_ccid_up());
            String strJiLian = deviceBean.getDevice_ccid().substring(2, 4);
            if (strJiLian.equals("01")) {
                SuiYiTieOneActivity.actionStart(mContext, deviceBean.getDevice_ccid(), deviceBean.getDevice_ccid_up());
            } else if (strJiLian.equals("02")) {
                SuiYiTieTwoActivity.actionStart(mContext, deviceBean.getDevice_ccid(), deviceBean.getDevice_ccid_up());
            } else if (strJiLian.equals("03")) {
                SuiYiTieThreeActivity.actionStart(mContext, deviceBean.getDevice_ccid(), deviceBean.getDevice_ccid_up());
            }
        } else if (deviceBean.getDevice_type().equals("36")) {
            WenShiDuChuanGanQiActivity.actionStart(mContext, deviceBean.getDevice_id(), deviceBean.getDevice_ccid(), deviceBean.getDevice_ccid_up());
        } else {
            String ty_device_ccid = deviceBean.getTy_device_ccid();
            if (TextUtils.isEmpty(ty_device_ccid)) {
                Bundle bundle = new Bundle();
                bundle.putString("device_id", deviceBean.getDevice_id());
                bundle.putString("device_type", deviceBean.getDevice_type());
                bundle.putString("member_type", member_type);
                bundle.putString("work_state", deviceBean.getWork_state());
                startActivity(new Intent(mContext, ZhiNengRoomDeviceDetailAutoActivity.class).putExtras(bundle));
            } else {
                Y.e("设备的信息是什么啊  " + "device_category:" + deviceBean.getDevice_type() + "  produco:" + deviceBean.getDevice_category() + "  device_category_code:" + deviceBean.getDevice_category_code());

                if (deviceBean.getDevice_type().equals(TuyaConfig.CATEGORY_CAMERA)) {//涂鸦摄像机
                    TuyaCameraActivity.actionStart(mContext, member_type, deviceBean.getDevice_id(), ty_device_ccid, deviceBean.getDevice_name(), deviceBean.getRoom_name());
                } else if (deviceBean.getDevice_type().equals(TuyaConfig.CATEGORY_CHAZUO)) {//涂鸦插座
                    if (deviceBean.getDevice_category().equals(TuyaConfig.PRODUCTID_CHAZUO_WG)) {
                        DeviceWgCzActivity.actionStart(mContext, member_type, deviceBean.getDevice_id(), ty_device_ccid, deviceBean.getDevice_name(), deviceBean.getRoom_name());
                    } else {
                        DeviceChazuoActivity.actionStart(mContext, member_type, deviceBean.getDevice_id(), ty_device_ccid, deviceBean.getDevice_name(), deviceBean.getRoom_name());
                    }
                } else if (deviceBean.getDevice_type().equals(TuyaConfig.CATEGORY_WANGGUAN)) {//涂鸦网关
                    DeviceWangguanActivity.actionStart(mContext, member_type, deviceBean.getDevice_id(), ty_device_ccid, deviceBean.getDevice_name(), deviceBean.getRoom_name());
                } else if (deviceBean.getDevice_type().equals(TuyaConfig.CATEGORY_WNYKQ)) {//万能遥控器
                    AbsPanelCallerService service = MicroContext.getServiceManager().findServiceByInterface(AbsPanelCallerService.class.getName());
                    service.goPanelWithCheckAndTip(ZhiNengRoomSettingActivity.this, ty_device_ccid);
                } else {//其他涂鸦设备
                    AbsPanelCallerService service = MicroContext.getServiceManager().findServiceByInterface(AbsPanelCallerService.class.getName());
                    service.goPanelWithCheckAndTip(ZhiNengRoomSettingActivity.this, ty_device_ccid);
                }
            }
        }
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("房间设置");
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_room_name:
                if (member_type.equals("1") || member_type.equals("3")) {
                    //管理员身份，可以编辑房间名字
                    if (room_id.equals("0")) {
                        //默认房间不能修改名字
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomSettingActivity.this,
                                "提示", "默认房间无法修改名字", "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {

                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    } else {
                        ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(context, ConstanceValue.MSG_ROOM_MANAGE_CHANGENAME);
                        zhiNengFamilyAddDIalog.show();
                    }
                } else {
                    //成员身份，不可以编辑房间名字
                    MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomSettingActivity.this,
                            "提示", "共享家庭成员无权限", "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                        @Override
                        public void clickLeft() {

                        }

                        @Override
                        public void clickRight() {

                        }
                    });
                    myCarCaoZuoDialog_caoZuoTIshi.show();
                }
                break;
            case R.id.tv_room_delete:
                if (member_type.equals("1") || member_type.equals("3")) {
                    //管理员身份，可以删除房间
                    if (room_id.equals("0")) {
                        //默认房间不能修改名字
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomSettingActivity.this,
                                "提示", "默认房间无法删除", "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {

                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    } else {
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomSettingActivity.this,
                                "提示", "确定要删除该房间吗？", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {
                                deleteRoom();
                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    }
                } else {
                    //成员身份，不可以删除房间
                    MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomSettingActivity.this,
                            "提示", "共享家庭成员无权限", "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                        @Override
                        public void clickLeft() {

                        }

                        @Override
                        public void clickRight() {

                        }
                    });
                    myCarCaoZuoDialog_caoZuoTIshi.show();
                }
                break;
        }
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16026");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("room_id", room_id);
        map.put("family_id", family_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengRoomManageSettingBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengRoomManageSettingBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengRoomManageSettingBean.DataBean>> response) {
                        dataBeanList.clear();
                        dataBeanList.addAll(response.body().data);
                        tv_room_name.setText(room_name);
                        tv_room_num.setText("该房间有" + dataBeanList.size() + "个设备");
                        zhiNengRoomManageSettingAdapter.notifyDataSetChanged();
                    }
                });
    }


    TishiDialog tishiDialog;

    /**
     * 修改房间名字
     */
    private void changeRoom(String roomName) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16021");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("family_id", family_id);
        map.put("room_name", roomName);
        if (!room_id.isEmpty()) {
            map.put("room_id", room_id);
        }
        if (!room_name.isEmpty()) {
            map.put("old_name", room_name);
        }
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));

        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        if (response.body().msg.equals("ok")) {
                            room_name = roomName;
                            tv_room_name.setText(room_name);

                            Notice notice = new Notice();
                            notice.type = ConstanceValue.MSG_CAOZUODONGTAISHITI;
                            sendRx(notice);
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

    /**
     * 删除房间
     */
    private void deleteRoom() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16027");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("room_id", room_id);
        map.put("family_id", family_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));

        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        Notice notice = new Notice();
                        notice.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                        sendRx(notice);

                        if (response.body().msg.equals("ok")) {
                            MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(ZhiNengRoomSettingActivity.this,
                                    "成功", "您已经成功删除该房间", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {
                                    finish();
                                }
                            });
                            dialog_success.show();
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
