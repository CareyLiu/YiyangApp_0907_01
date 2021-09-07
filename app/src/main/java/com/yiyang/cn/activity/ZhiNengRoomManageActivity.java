package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.adapter.ZhiNengRoomManageAdapter;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
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
import com.yiyang.cn.model.ZhiNengRoomManageBean;
import com.yiyang.cn.model.ZhiNengRoomManageCreatBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengRoomManageActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_room_add)
    ImageView iv_room_add;
    private Context context = ZhiNengRoomManageActivity.this;
    private String member_type = "";
    private String family_id = "";
    private String device_id = "";
    private List<ZhiNengRoomManageBean.DataBean> dataBeanList = new ArrayList<>();
    private ZhiNengRoomManageAdapter zhiNengRoomManageAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhineng_room_manage;
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
        family_id = getIntent().getStringExtra("family_id");
        if (family_id == null) {
            family_id = "";
        }
        device_id = getIntent().getStringExtra("device_id");
        if (device_id == null) {
            device_id = "";
        }
        if (device_id.equals("")) {
            tv_title.setText("房间管理");
        } else {
            tv_title.setText("设备转移");
        }
        getnet();
    }

    private void initView() {
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getnet();
            }
        });
        srLSmart.setEnableLoadMore(false);
        iv_room_add.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        zhiNengRoomManageAdapter = new ZhiNengRoomManageAdapter(R.layout.item_zhineng_room, dataBeanList);
        zhiNengRoomManageAdapter.setEmptyView(LayoutInflater.from(context).inflate(R.layout.activity_zhineng_room_manage_none, null));
        zhiNengRoomManageAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(zhiNengRoomManageAdapter);
        zhiNengRoomManageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZhiNengRoomManageBean.DataBean dataBean = (ZhiNengRoomManageBean.DataBean) adapter.getItem(position);
                if (device_id.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("room_id", dataBean.getRoom_id());
                    bundle.putString("family_id", dataBean.getFamily_id());
                    bundle.putString("room_name", dataBean.getRoom_name());
                    bundle.putString("member_type", member_type);
                    startActivity(new Intent(context, ZhiNengRoomSettingActivity.class).putExtras(bundle));
                } else {
                    //调设备转移接口
                    MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,
                            "提示", "确定要转移到该房间吗？", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                        @Override
                        public void clickLeft() {

                        }

                        @Override
                        public void clickRight() {
                            if (member_type.equals("1") || member_type.equals("3")) {
                                deviceTransfer(dataBean.getRoom_id(), dataBean.getRoom_name());
                            } else {
                                Toast.makeText(context, "操作失败，需要管理员身份", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    myCarCaoZuoDialog_caoZuoTIshi.show();
                }
            }
        });
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ROOM_MANAGE_ADD) {
                    creatRoom(message.content.toString());
                }
            }
        }));
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
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
            case R.id.iv_room_add:
                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(context, ConstanceValue.MSG_ROOM_MANAGE_ADD);
                zhiNengFamilyAddDIalog.show();
                break;
        }
    }

    private void getnet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16023");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        String familyId = PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, "");
        map.put("family_id", familyId);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengRoomManageBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengRoomManageBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengRoomManageBean.DataBean>> response) {
                        if (srLSmart != null) {
                            srLSmart.setEnableRefresh(true);
                            srLSmart.finishRefresh();
                            srLSmart.setEnableLoadMore(false);
                        }
                        dataBeanList.clear();
                        dataBeanList.addAll(response.body().data);
                        zhiNengRoomManageAdapter.notifyDataSetChanged();
                    }
                });
    }

    TishiDialog tishiDialog;

    /**
     * 创建房间
     */
    private void creatRoom(String roomName) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16021");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        String familyId = PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, "");
        map.put("family_id", familyId);
        map.put("room_name", roomName);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengRoomManageCreatBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengRoomManageCreatBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengRoomManageCreatBean>> response) {
                        getnet();

                        Notice notice = new Notice();
                        notice.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                        sendRx(notice);

                        //重新上传动态实体
                        Notice notice1 = new Notice();
                        notice1.type = ConstanceValue.MSG_CAOZUODONGTAISHITI;
                        sendRx(notice1);
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengRoomManageCreatBean>> response) {
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
     * 设备转移
     */
    private void deviceTransfer(String room_id, String room_name) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16025");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
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

                            MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(ZhiNengRoomManageActivity.this,
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
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,
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
