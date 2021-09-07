package com.yiyang.cn.activity.tuya_device.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.device.tongyong.DeviceSetActivity;
import com.yiyang.cn.activity.tuya_device.utils.TuyaConfig;
import com.yiyang.cn.activity.tuya_device.utils.TuyaDialogUtils;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.yiyang.cn.activity.tuya_device.device.adapter.WangguanAdapter;
import com.yiyang.cn.activity.tuya_device.device.model.ZishebeiModel;
import com.yiyang.cn.activity.tuya_device.add.TuyaWangguanAddActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
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

public class DeviceWangguanActivity extends TuyaBaseDeviceActivity {

    @BindView(R.id.tv_device_zaixian)
    TextView tv_device_zaixian;
    @BindView(R.id.tv_device_name)
    TextView tv_device_name;
    @BindView(R.id.rv_device_content)
    RecyclerView rv_device_content;
    @BindView(R.id.bt_add_device)
    TextView bt_add_device;

    private String ty_device_ccid;
    private String old_name;
    private String member_type;
    private List<ZishebeiModel.DataBean> deviceBeans = new ArrayList<>();
    private WangguanAdapter adapter;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String member_type, String device_id, String ty_device_ccid, String old_name, String room_name) {
        Intent intent = new Intent(context, DeviceWangguanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("member_type", member_type);
        intent.putExtra("device_id", device_id);
        intent.putExtra("ty_device_ccid", ty_device_ccid);
        intent.putExtra("old_name", old_name);
        intent.putExtra("room_name", room_name);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_wangguan;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("网关");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setImageResource(R.mipmap.mine_shezhi);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set();
            }
        });
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
        init();
        initAdapter();
        initHuidiao();
    }

    private void init() {
        ty_device_ccid = getIntent().getStringExtra("ty_device_ccid");
        old_name = getIntent().getStringExtra("old_name");
        member_type = getIntent().getStringExtra("member_type");
        tv_device_name.setText(old_name);
        DeviceBean haveDevice = TuyaHomeManager.getHomeManager().isHaveDevice(ty_device_ccid);
        if (haveDevice != null) {
            TuyaDeviceManager.getDeviceManager().initDevice(haveDevice);
            getWangguanList();
        } else {
            TuyaDialogUtils.t(mContext, "设备已失效!");
        }
    }

    private void initAdapter() {
        adapter = new WangguanAdapter(R.layout.item_tuya_device_wangguan, deviceBeans);
        rv_device_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_device_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (deviceBeans != null) {
                    ZishebeiModel.DataBean deviceBean = deviceBeans.get(position);
                    String device_type = deviceBean.getDevice_type();
                    if (device_type.equals(TuyaConfig.CATEGORY_MENCI)){
                        DeviceMenciActivity.actionStart(mContext, member_type, deviceBean.getDevice_id(), deviceBean.getTy_device_ccid(), deviceBean.getDevice_name(), deviceBean.getRoom_name());
                    }else  if (device_type.equals(TuyaConfig.CATEGORY_SOS)){

                    }
                }
            }
        });
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_ADD) {
                    getWangguanList();
                }else if (message.type == ConstanceValue.MSG_DEVICE_DELETE) {
                    finish();
                }
            }
        }));
    }

    private void getWangguanList() {
        showProgressDialog();
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16047");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("wg_device_ccid", ty_device_ccid);
        Gson gson = new Gson();
        OkGo.<AppResponse<ZishebeiModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZishebeiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZishebeiModel.DataBean>> response) {
                        dismissProgressDialog();
                        deviceBeans = response.body().data;
                        adapter.setNewData(deviceBeans);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<ZishebeiModel.DataBean>> response) {
                        super.onError(response);
                        dismissProgressDialog();
                        Y.tError(response);
                    }
                });
    }

    private void set() {
        DeviceSetActivity.actionStart(mContext,
                getIntent().getStringExtra("member_type"),
                getIntent().getStringExtra("device_id"),
                getIntent().getStringExtra("old_name"),
                getIntent().getStringExtra("room_name")
        );
    }

    @OnClick(R.id.bt_add_device)
    public void onViewClicked() {
        TuyaWangguanAddActivity.actionStart(mContext);
    }
}
