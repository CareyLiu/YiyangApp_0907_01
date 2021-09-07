package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.ZhiNengFamilyAddDIalog;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
import com.yiyang.cn.model.ZhiNengJiaJuKaiGuanModel;
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

public class ZhiNengJiaJuKaiGuanOneActivity extends BaseActivity {
    @BindView(R.id.view_switch_left)
    View viewSwitchLeft;
    @BindView(R.id.rl_switch_left)
    RelativeLayout rlSwitchLeft;
    @BindView(R.id.tv_kaiguan_mode)
    TextView tvKaiguanMode;
    @BindView(R.id.tv_beiguang_zhuangtai)
    TextView tvBeiguangZhuangtai;
    @BindView(R.id.ll_quankai)
    LinearLayout llQuankai;
    @BindView(R.id.ll_quanguan)
    LinearLayout llQuanguan;

    String device_id;
    @BindView(R.id.iv_quankai)
    ImageView ivQuankai;
    @BindView(R.id.tv_quankai)
    TextView tvQuankai;
    @BindView(R.id.iv_quanguan)
    ImageView ivQuanguan;
    @BindView(R.id.tv_quanguan)
    TextView tvQuanguan;
    @BindView(R.id.tv_text)
    TextView tvText;

    private String kaiGuanZhuagnTai = "0";//0 关 1开

    private String device_ccid;
    private String device_ccid_up;
    private String serverId;
    ZnjjMqttMingLing znjjMqttMingLing;
    private String zhuangZhiId;//装置id


    private String oldDeviceName;
    private String deviceId;
    private String deViceName;
    private String familyId;
    private String member_type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.ZHINENGJIAJU);
        device_ccid = getIntent().getStringExtra("device_ccid");
        device_ccid_up = getIntent().getStringExtra("device_ccid_up");
        serverId = getIntent().getStringExtra("serverId");
        member_type = getIntent().getStringExtra("member_type");
        getnet();

        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
        znjjMqttMingLing.setTopic(serverId, device_ccid_up);

        znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(device_ccid_up, serverId, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        rlSwitchLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kaiGuanZhuagnTai.equals("0")) {
                    setQuanKaiQuanGuanBac("1", R.mipmap.tuya_icon_switch_quankai, R.mipmap.kaiguan_pic_quankai_close, R.mipmap.kaiguan_pic_quanguan_open, R.mipmap.tuya_icon_switch_quanguan);

                    daKaiKaiGuan("1", zhuangZhiId);
                    kaiGuanZhuagnTai = "1";
                    viewSwitchLeft.setSelected(true);
                } else {
                    setQuanKaiQuanGuanBac("0", R.mipmap.tuya_icon_switch_quankai, R.mipmap.kaiguan_pic_quankai_close, R.mipmap.kaiguan_pic_quanguan_open, R.mipmap.tuya_icon_switch_quanguan);

                    daKaiKaiGuan("0", zhuangZhiId);
                    kaiGuanZhuagnTai = "0";
                    viewSwitchLeft.setSelected(false);
                }
            }
        });

        rlSwitchLeft.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(mContext, ConstanceValue.MSG_FAMILY_MANAGE_ADD);
                zhiNengFamilyAddDIalog.show();

                return false;
            }
        });

        llQuankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuanKaiQuanGuanBac("1", R.mipmap.tuya_icon_switch_quankai, R.mipmap.kaiguan_pic_quankai_close, R.mipmap.kaiguan_pic_quanguan_open, R.mipmap.tuya_icon_switch_quanguan);
                znjjMqttMingLing.setZhiNengKaiGuan(zhuangZhiId, "012", new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            }
        });

        llQuanguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuanKaiQuanGuanBac("0", R.mipmap.tuya_icon_switch_quankai, R.mipmap.kaiguan_pic_quankai_close, R.mipmap.kaiguan_pic_quanguan_open, R.mipmap.tuya_icon_switch_quanguan);
                znjjMqttMingLing.setZhiNengKaiGuan(zhuangZhiId, "022", new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });

            }
        });
        setQuanKaiQuanGuanBac("3", R.mipmap.tuya_icon_switch_quankai, R.mipmap.kaiguan_pic_quankai_close, R.mipmap.kaiguan_pic_quanguan_open, R.mipmap.tuya_icon_switch_quanguan);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME) {
                    changeDevice((String) message.content, familyId, device_id, oldDeviceName, tvText);
                } else if (message.type == ConstanceValue.MSG_SHEBEIZHUANGTAI) {
                    getnet();
                } else if (message.type == ConstanceValue.MSG_KAIGUAN_DELETE) {
                    finish();
                }
            }
        }));
    }

    public void xiuGaiKaiGuanMingCheng(String kaiGuanMingCheng) {


    }

    // 1全开 2全关
    public void setQuanKaiQuanGuanBac(String str, int quanKaiSrc1, int quanKaiSrc0, int quanGuanScr1, int quanGuanScr0) {
        if (str.equals("1")) {
            ivQuankai.setBackgroundResource(quanKaiSrc1);
            ivQuanguan.setBackgroundResource(quanGuanScr0);
            tvQuankai.setTextColor(mContext.getResources().getColor(R.color.blue_ff3a85f8));
            tvQuanguan.setTextColor(mContext.getResources().getColor(R.color.black_333333));
        } else if (str.equals("0")) {
            ivQuankai.setBackgroundResource(quanKaiSrc0);
            ivQuanguan.setBackgroundResource(quanGuanScr1);

            tvQuankai.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvQuanguan.setTextColor(mContext.getResources().getColor(R.color.blue_ff3a85f8));
        } else {
            ivQuankai.setBackgroundResource(quanKaiSrc0);
            ivQuanguan.setBackgroundResource(quanGuanScr0);
            tvQuankai.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvQuanguan.setTextColor(mContext.getResources().getColor(R.color.black_333333));
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.zhinengkaiguan_one;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("智能开关");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setImageResource(R.mipmap.mine_shezhi);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KaiGuanSettingActivity.actionStart(mContext, device_ccid, device_ccid_up,member_type);
            }
        });
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_ccid, String device_ccid_up, String serverId, String member_type) {
        Intent intent = new Intent(context, ZhiNengJiaJuKaiGuanOneActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        intent.putExtra("device_ccid_up", device_ccid_up);
        intent.putExtra("serverId", serverId);
        intent.putExtra("member_type", member_type);
        context.startActivity(intent);
    }

    Response<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>> response;

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16068");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("device_ccid_up", device_ccid_up);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>> response) {
                        showLoadSuccess();
                        ZhiNengJiaJuKaiGuanOneActivity.this.response = response;
                        familyId = response.body().data.get(0).getDevice_list().get(0).getFamily_id();
                        deviceId = response.body().data.get(0).getDevice_list().get(0).getDevice_id();
                        oldDeviceName = response.body().data.get(0).getDevice_list().get(0).getDevice_name();

                        zhuangZhiId = response.body().data.get(0).getDevice_list().get(0).getDevice_ccid();
                        //1开 2 关
                        if (response.body().data.get(0).getDevice_list().get(0).getWork_state().equals("1")) {
                            setKaiGuanZhuangTai("1");
                            kaiGuanZhuagnTai = "1";
                        } else if (response.body().data.get(0).getDevice_list().get(0).getWork_state().equals("2")) {
                            setKaiGuanZhuangTai("0");
                            kaiGuanZhuagnTai = "0";
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());


                    }

                    @Override
                    public void onStart(Request<AppResponse<ZhiNengJiaJuKaiGuanModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    /**
     * @param str1 0 关 1开
     */
    private void setKaiGuanZhuangTai(String str1) {
        if (str1.equals("0")) {
            viewSwitchLeft.setSelected(false);
            kaiGuanZhuagnTai = "0";
        } else {
            viewSwitchLeft.setSelected(true);
            kaiGuanZhuagnTai = "1";
        }

    }

    public void daKaiKaiGuan(String str, String zhuangZhiId) {


        if (str.equals("0")) {
            znjjMqttMingLing.setAction(zhuangZhiId, "02", new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // UIHelper.ToastMessage(mContext, "当前装置开启");
                    List<String> stringList = new ArrayList<>();
                    stringList.add(device_ccid);
                    stringList.add("1");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    UIHelper.ToastMessage(mContext, "未发送指令");
                }
            });
        } else {
            znjjMqttMingLing.setAction(zhuangZhiId, "01", new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // UIHelper.ToastMessage(mContext, "当前装置开启");
                    List<String> stringList = new ArrayList<>();
                    stringList.add(device_ccid);
                    stringList.add("1");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    UIHelper.ToastMessage(mContext, "未发送指令");
                }
            });
        }
    }

    TishiDialog tishiDialog;

    /**
     * 修改设备名字
     */
    private void changeDevice(String deviceName, String familyId, String device_id, String oldDeviceName, TextView tvText) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16033");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("family_id", familyId);
        map.put("device_id", device_id);
        map.put("old_name", oldDeviceName);
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
                            tvText.setText(deviceName);
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
                            tishiDialog.setTextContent("名字修改成功咯");
                            tishiDialog.show();
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
