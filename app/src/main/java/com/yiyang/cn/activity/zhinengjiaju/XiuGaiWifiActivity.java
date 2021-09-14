package com.yiyang.cn.activity.zhinengjiaju;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.espressif.iot.esptouch.EsptouchTask;
import com.espressif.iot.esptouch.IEsptouchResult;
import com.espressif.iot.esptouch.IEsptouchTask;
import com.espressif.iot.esptouch.util.ByteUtil;
import com.espressif.iot.esptouch.util.TouchNetUtil;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.zhinengjiaju.peinet.EspTouchActivityAbsBase;
import com.yiyang.cn.activity.zhinengjiaju.peinet.PeiWangHelpActivity;
import com.yiyang.cn.activity.zhinengjiaju.peinet.v1.EspTouchActivity;
import com.yiyang.cn.adapter.OneImageAdapter;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.dialog.newdia.TishiPhoneDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.Message;
import com.yiyang.cn.model.PeiwangOtherModel;
import com.yiyang.cn.model.TianJiaZhuJiMoel;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
import com.yiyang.cn.model.ZhiNengJiaJu_0007Model;
import com.yiyang.cn.model.ZhiNengJiaJu_0009Model;
import com.yiyang.cn.mqtt_zhiling.NewZhiNengJiajuMqttMingLing;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.app.ConstanceValue.MSG_PEIWANG_SUCCESS;
import static com.yiyang.cn.get_net.Urls.SERVER_URL;
import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class XiuGaiWifiActivity extends BaseActivity {

    private static final String TAG = EspTouchActivity.class.getSimpleName();
    private static final int REQUEST_PERMISSION = 0x01;
    @BindView(R.id.tv_huashu1)
    TextView tvHuashu1;
    @BindView(R.id.ll_huashu2)
    LinearLayout llHuashu2;
    @BindView(R.id.tv_huashu3)
    TextView tvHuashu3;
    @BindView(R.id.tv_wifi_ming)
    EditText tvWifiMing;
    @BindView(R.id.ll_wifi_name)
    LinearLayout llWifiName;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.iv_icon_mima)
    ImageView ivIconMima;

    @BindView(R.id.iv_see_mima)
    ImageView ivSeeMima;
    @BindView(R.id.ll_wifi_mima)
    RelativeLayout llWifiMima;
    @BindView(R.id.view_line_1)
    View viewLine1;
    @BindView(R.id.tv_huashu4)
    TextView tvHuashu4;
    @BindView(R.id.rll_kaishilianjie)
    RoundLinearLayout rllKaishilianjie;
    @BindView(R.id.et_wifi_mima)
    EditText etWifiMima;
    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.shebeilianjie)
    TextView shebeilianjie;
    @BindView(R.id.tv_peiduiguochegn)
    TextView tvPeiduiguochegn;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.cl_shebei_lianjie)
    ConstraintLayout clShebeiLianjie;
    @BindView(R.id.cl_zhanghaomima)
    ConstraintLayout clZhanghaomima;
    @BindView(R.id.iv_shibai)
    ImageView ivShibai;
    @BindView(R.id.tv_shibai)
    TextView tvShibai;
    @BindView(R.id.tv_shibaiyuanyin)
    TextView tvShibaiyuanyin;
    @BindView(R.id.ll_1234)
    LinearLayout ll1234;
    @BindView(R.id.rll_kaishilianjie_shibaihou)
    RoundLinearLayout rllKaishilianjieShibaihou;
    @BindView(R.id.cl_shibai)
    ConstraintLayout clShibai;
    @BindView(R.id.iv_peiwang_mima)
    ImageView ivPeiwangMima;
    @BindView(R.id.shibai_bangzhu)
    TextView shibaiBangzhu;


    OneImageAdapter oneImageAdapter;
    List<ZhiNengJiaJu_0007Model.MatchListBean> mDatas = new ArrayList<>();
    ZhiNengJiaJu_0007Model zhiNengJiaJu_0007Model;
    ZhiNengJiaJu_0009Model zhiNengJiaJu_0009Model;

    public EsptouchAsyncTask4 mTask;

    @BindView(R.id.rlv_shebeilist)
    RecyclerView rlvShebeilist;
    @BindView(R.id.btn_moni)
    Button btnMoni;
    @BindView(R.id.nes_scroll)
    NestedScrollView nesScroll;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_text)
    TextView tvText;

    @BindView(R.id.ll_main_tianjia)
    RelativeLayout llMainTianjia;
    @BindView(R.id.rl_tuichu)
    RoundRelativeLayout rlTuichu;
    private String jiZhuMiMa = "1";//0 不记住  1 记住

    private String zhuangZhiLeixing;//装置类型
    private String zhuangZhiLeiXingXingHao;//装置类型的型号
    private String cd_device_ccid;
    private ZnjjMqttMingLing znjjMqttMingLing;
    private String zhuji_device_ccid_up;
    private String serverId;
    private PeiwangOtherModel peiwangOtherModel;
    TishiDialog tishiDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clZhanghaomima.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String strCCid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "0");
                String strServerId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "0");
                NewZhiNengJiajuMqttMingLing zhiNengJiajuMqttMingLing = new NewZhiNengJiajuMqttMingLing(mContext, strCCid, strServerId);

                String str = "m081308yingjian_wifiabc123452";
                zhiNengJiajuMqttMingLing.setActionApp(str, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        //  showProgressDialog("修改中，请稍后");
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
                return false;
            }
        });

        WifiManager wifiMgr = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        int wifiState = wifiMgr.getWifiState();
        WifiInfo info = wifiMgr.getConnectionInfo();
        String wifiMing = info != null ? info.getSSID() : null;

        String wifiInfo1 = wifiMing.replace("\"", "");


        tvWifiMing.setText(wifiInfo1);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permissions, REQUEST_PERMISSION);
        }


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ivPeiwangMima.setBackgroundResource(R.mipmap.peiwang_icon_mima_jizhu);
        setPeiWangMiMa();
        rllKaishilianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(tvWifiMing.getText().toString())) {
                    UIHelper.ToastMessage(mContext, "请输入wifi用户名");
                    return;
                }
                tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                    @Override
                    public void onClickCancel(View v, TishiDialog dialog) {

                    }

                    @Override
                    public void onClickConfirm(View v, TishiDialog dialog) {
                        // UIHelper.ToastMessage(mContext, "开始连接");
                        xiuGaiWifi();
                    }

                    @Override
                    public void onDismiss(TishiDialog dialog) {

                    }
                });
                tishiDialog.setTextContent("是否确认修改网络？");
                tishiDialog.setTextConfirm("确定");
                tishiDialog.setTextCancel("取消");
                tishiDialog.show();
            }
        });

        ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_buxianshimima);
        ivSeeMima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seeMiMa.equals("0")) {
                    seeMiMa = "1";
                    ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_xianshimima);
                    etWifiMima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    seeMiMa = "0";
                    ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_buxianshimima);
                    etWifiMima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        animationView.setAnimation("lottie.json");
        animationView.setImageAssetsFolder("images/");
        animationView.loop(true);
        animationView.playAnimation();

        setZhuangTaiZhanShi("1");
        String wifiMiMa = PreferenceHelper.getInstance(mContext).getString(AppConfig.PEIWANG_MIMA, "");
        if (!StringUtils.isEmpty(wifiMiMa)) {
            etWifiMima.setText(wifiMiMa);
        }

        rllKaishilianjieShibaihou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setZhuangTaiZhanShi("1");
            }
        });
        shibaiBangzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeiWangHelpActivity.actionStart(mContext);
            }
        });
        oneImageAdapter = new OneImageAdapter(R.layout.item_rlv_shebeilist, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rlvShebeilist.setLayoutManager(linearLayoutManager);
        rlvShebeilist.setAdapter(oneImageAdapter);

        oneImageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_image:

                        break;
                }
            }
        });


        jieShouMqttTianJiaSheBei();


    }

    private void setPeiWangMiMa() {


        ivPeiwangMima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jiZhuMiMa.equals("0")) {
                    ivPeiwangMima.setBackgroundResource(R.mipmap.peiwang_icon_mima_jizhu);
                    jiZhuMiMa = "1";
                } else if (jiZhuMiMa.equals("1")) {
                    ivPeiwangMima.setBackgroundResource(R.mipmap.peiwang_icon_mima_weixuanze);
                    jiZhuMiMa = "0";
                }
            }
        });
    }

    private String seeMiMa = "0";//0 隐藏 1显示


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_xiugai_wifi;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("修改网络");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
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
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, XiuGaiWifiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * @param str 0连接失败 1开始连接页面 2连接中
     */
    private void setZhuangTaiZhanShi(String str) {
        if (str.equals("0")) {
            clShibai.setVisibility(View.VISIBLE);
            clShebeiLianjie.setVisibility(View.GONE);
            clZhanghaomima.setVisibility(View.GONE);
        } else if (str.equals("1")) {
            clShibai.setVisibility(View.GONE);
            clShebeiLianjie.setVisibility(View.GONE);
            clZhanghaomima.setVisibility(View.VISIBLE);
        } else if (str.equals("2")) {
            clShibai.setVisibility(View.GONE);
            clShebeiLianjie.setVisibility(View.VISIBLE);
            clZhanghaomima.setVisibility(View.GONE);
        }

    }

    public void xiuGaiWifi() {


        if (!AndMqtt.getInstance().isConnect()) {
            UIHelper.ToastMessage(mContext, "未连接主机,请重新尝试");
            return;
        }

        String strCCid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "0");
        String strServerId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "0");


        NewZhiNengJiajuMqttMingLing zhiNengJiajuMqttMingLing = new NewZhiNengJiajuMqttMingLing(mContext, strCCid, strServerId);


        String mSsid = tvWifiMing.getText().toString();
        byte[] a = tvWifiMing.getText().toString().getBytes();

        int yonghuming_length = a.length;
        int password_length = etWifiMima.getText().toString().length();

        String YongHuMing_length = String.format("%02d", yonghuming_length);
        String Passsword_length = String.format("%02d", password_length);

        String str = "M23" + YongHuMing_length + Passsword_length + tvWifiMing.getText().toString() + etWifiMima.getText().toString() + ".";

        zhiNengJiajuMqttMingLing.setActionHardware(str, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                showProgressDialog("修改中，请稍后");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });


    }

    public void jieShouMqttTianJiaSheBei() {

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ZHUJIXIUGAIXINXI) {
                    dismissProgressDialog();
                    String str1 = (String) message.content;
                    String str = str1.substring(str1.length() - 2, str1.length() - 1);

                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {

                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            if (str.equals("2")) {
                                tishiDialog.dismiss();
                            } else if (str.equals("1")) {
                                finish();
                            }

                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });
                    if (str.equals("2")) {
                        tishiDialog.setTextContent("当前网络修改失败，请检查您的网络重新尝试");
                    } else if (str.equals("1")) {
                        tishiDialog.setTextContent("网络修改成功");
                    }
                    tishiDialog.setTextConfirm("知道了");
                    tishiDialog.setTextCancel("");
                    tishiDialog.show();


                }
            }
        }));
    }


}
