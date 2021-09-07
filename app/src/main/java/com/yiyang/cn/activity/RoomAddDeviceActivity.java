package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.yiyang.cn.activity.zhinengjiaju.peinet.PeiWangYinDaoPageActivity;
import com.yiyang.cn.adapter.RoomAddDeviceAdapter;
import com.yiyang.cn.adapter.ZhiNengRoomManageSettingAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_Success;
import com.yiyang.cn.fragment.znjj.model.ZhiNengModel;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class RoomAddDeviceActivity extends BaseActivity {


    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    private List<ZhiNengModel.DataBean.DeviceBean> device;
    private RoomAddDeviceAdapter deviceAdapter;
    private String family_id;
    private String member_type;
    private String room_id;
    private String room_name;

    @Override
    public int getContentViewResId() {
        return R.layout.act_add_device_room;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("添加设备到房间");
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

    public static void actionStart(Context context, String family_id, String member_type, String room_id, String room_name) {
        Intent intent = new Intent(context, RoomAddDeviceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("family_id",family_id);
        intent.putExtra("member_type",member_type);
        intent.putExtra("room_id",room_id);
        intent.putExtra("room_name",room_name);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initData();

        initAdapter();

        getnet();

    }

    private void initData() {
        family_id = getIntent().getStringExtra("family_id");
        member_type = getIntent().getStringExtra("member_type");
        room_id = getIntent().getStringExtra("room_id");
        room_name = getIntent().getStringExtra("room_name");
    }

    private void initAdapter() {
        deviceAdapter = new RoomAddDeviceAdapter(R.layout.item_zhineng_room_setting, device);
        deviceAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.activity_zhineng_room_setting_none, null));
        deviceAdapter.openLoadAnimation();//默认为渐显效果
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(deviceAdapter);

        deviceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZhiNengModel.DataBean.DeviceBean deviceBean = device.get(position);
                //调设备转移接口
                MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(mContext,
                        "提示", "确定把该设备要转移" + room_name + "吗？", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {

                    }

                    @Override
                    public void clickRight() {
                        if (member_type.equals("1") || member_type.equals("3")) {
                            deviceTransfer(deviceBean.getDevice_id());
                        } else {
                            Toast.makeText(mContext, "操作失败，需要管理员身份", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                myCarCaoZuoDialog_caoZuoTIshi.show();

            }
        });
    }


    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16001");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengModel.DataBean>> response) {
                        ZhiNengModel.DataBean dataBean = response.body().data.get(0);
                        device = dataBean.getDevice();
                        deviceAdapter.setNewData(device);
                        deviceAdapter.notifyDataSetChanged();
                    }
                });
    }


    /**
     * 设备转移
     */
    private void deviceTransfer(String device_id) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16025");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("move_type", "1");
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

                            MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(RoomAddDeviceActivity.this,
                                    "成功", "成功将设备移入该房间", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
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
}
