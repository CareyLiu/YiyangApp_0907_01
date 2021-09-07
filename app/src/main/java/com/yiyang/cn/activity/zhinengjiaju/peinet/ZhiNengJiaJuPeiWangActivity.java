package com.yiyang.cn.activity.zhinengjiaju.peinet;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.rairmmd.andmqtt.MqttPublish;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.AccessListActivity;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.zhinengjiaju.peinet.v1.EspTouchActivity;
import com.yiyang.cn.adapter.OneImageAdapter;
import com.yiyang.cn.app.AppConfig;
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
import com.yiyang.cn.model.AccessListModel;
import com.yiyang.cn.model.Message;
import com.yiyang.cn.model.PeiwangOtherModel;
import com.yiyang.cn.model.TianJiaZhuJiMoel;
import com.yiyang.cn.model.ZhiNengFamilyEditBean;
import com.yiyang.cn.model.ZhiNengJiaJu_0007Model;
import com.yiyang.cn.model.ZhiNengJiaJu_0009Model;
import com.yiyang.cn.mqtt_zhiling.ZnjjMqttMingLing;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.SoundPoolUtils;

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
import static com.yiyang.cn.config.MyApplication.CAR_CTROL;
import static com.yiyang.cn.get_net.Urls.SERVER_URL;
import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengJiaJuPeiWangActivity extends EspTouchActivityAbsBase {

    private static final String TAG = EspTouchActivity.class.getSimpleName();
    private static final int REQUEST_PERMISSION = 0x01;
    @BindView(R.id.tv_huashu1)
    TextView tvHuashu1;
    @BindView(R.id.ll_huashu2)
    LinearLayout llHuashu2;
    @BindView(R.id.tv_huashu3)
    TextView tvHuashu3;
    @BindView(R.id.tv_wifi_ming)
    TextView tvWifiMing;
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

    @Override
    protected String getEspTouchVersion() {
        return getString(R.string.esptouch1_about_version, IEsptouchTask.ESPTOUCH_VERSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onWifiChanged();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.esptouch1_location_permission_title)
                        .setMessage(R.string.esptouch1_location_permission_message)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> finish())
                        .show();
            }

            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnMoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_TIANJIAZHUJI;
                String message = "                        n.type = ConstanceValue.MSG_TIANJIAZHUJI;\n" +
                        "                                ZhiNengJiaJu_0009Model zhiNengJiaJuNotifyJson = new Gson().fromJson(message.toString(), ZhiNengJiaJu_0009Model.class);\n" +
                        "                                n.content = zhiNengJiaJuNotifyJson;";

                ZhiNengJiaJu_0009Model zhiNengJiaJuNotifyJson = new Gson().fromJson(message.toString(), ZhiNengJiaJu_0009Model.class);
                notice.content = zhiNengJiaJuNotifyJson;
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permissions, REQUEST_PERMISSION);
        }

        MyApplication.getInstance().observeBroadcast(this, broadcast -> {
            Log.d(TAG, "onCreate: Broadcast=" + broadcast);
            onWifiChanged();
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ivPeiwangMima.setBackgroundResource(R.mipmap.peiwang_icon_mima_jizhu);
        setPeiWangMiMa();
        rllKaishilianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.isEmpty(etWifiMima.getText().toString())) {
                    setZhuangTaiZhanShi("2");
                    executeEsptouch();
                    if (jiZhuMiMa.equals("1")) {
                        PreferenceHelper.getInstance(mContext).putString(AppConfig.PEIWANG_MIMA, etWifiMima.getText().toString());
                    } else {
                        PreferenceHelper.getInstance(mContext).removeKey(AppConfig.PEIWANG_MIMA);
                    }

                } else {
                    UIHelper.ToastMessage(mContext, "请输入您的配网密码");
                }
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
//                        cd_device_ccid = mDatas.get(position).getCd_device_ccid();
//                        zhuangZhiLeixing = "";
//                        zhuangZhiLeiXingXingHao = "";
//                        TishiDialog tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
//                            @Override
//                            public void onClickCancel(View v, TishiDialog dialog) {
//                            }
//
//                            @Override
//                            public void onClickConfirm(View v, TishiDialog dialog) {
//                                tianJiaSheBeiNet();
//                            }
//
//                            @Override
//                            public void onDismiss(TishiDialog dialog) {
//                            }
//                        });
//                        tishiDialog.setTextContent("已找到您要添加的设备，是否添加此设备？");
//                        tishiDialog.show();
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

    private void executeEsptouch() {

        byte[] ssid = ByteUtil.getBytesByString(mSsid.toString());
        CharSequence password = etWifiMima.getText().toString();


        //  byte[] password = pwdStr == null ? null : ByteUtil.getBytesByString(pwdStr.toString());
        byte[] bssid = TouchNetUtil.parseBssid2bytes(mBssid.toString());
        CharSequence devCountStr = String.valueOf(1);
        byte[] deviceCount = devCountStr == null ? new byte[0] : devCountStr.toString().getBytes();
        byte[] broadcast = {1};

        if (mTask != null) {
            mTask.cancelEsptouch();
        }
        mTask = new EsptouchAsyncTask4(this);

        byte[] a = mSsid.toString().getBytes();

        int yonghuming_length = a.length;
        int password_length = password.toString().length();


        String zhengHeYongHuMing = String.format("%02d", yonghuming_length) + mSsid.toString();


        String zhengHePasssword_length = String.format("%02d", password_length);

        String familyId = PreferenceHelper.getInstance(mContext).getString(AppConfig.PEIWANG_FAMILYID, "");

        if (familyId == null) {
            UIHelper.ToastMessage(mContext, "请退出应用后重新进入");
            return;
        }

        String familyId_length = String.format("%02d", familyId.length());

        Log.i("execute_info", "   mssid  :" + mSsid.toString() + "   mBssid  :" + mBssid + "   password  :" + password);

        Log.i("execute_info", "转换后：" + yonghuming_length + "不转换：" + mSsid.length());

        String zhengHePassWord = zhengHePasssword_length + familyId_length + password + familyId;

        Log.i("execute_info", zhengHePassWord);

        mTask.execute(zhengHeYongHuMing.getBytes(), bssid, zhengHePassWord.getBytes(), deviceCount, broadcast);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhi_neng_jia_ju_pei_wang;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("配网");
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
        Intent intent = new Intent(context, ZhiNengJiaJuPeiWangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    lianWangThread thread;

    private class EsptouchAsyncTask4 extends AsyncTask<byte[], IEsptouchResult, List<IEsptouchResult>> {
        private WeakReference<ZhiNengJiaJuPeiWangActivity> mActivity;

        private final Object mLock = new Object();
        private ProgressDialog mProgressDialog;
        private AlertDialog mResultDialog;
        private IEsptouchTask mEsptouchTask;

        EsptouchAsyncTask4(ZhiNengJiaJuPeiWangActivity activity) {
            mActivity = new WeakReference<>(activity);

        }

        public void cancelEsptouch() {
            Log.i("ZhiNengJiaJuPeiWang", "cancelEsptouch");
            cancel(true);
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
            if (mResultDialog != null) {
                mResultDialog.dismiss();
            }
            if (mEsptouchTask != null) {
                mEsptouchTask.interrupt();
            }
        }

        @Override
        protected void onPreExecute() {
            Log.i(ZhiNengJiaJuPeiWangActivity.class.getSimpleName(), "onPreExecute");
            ZhiNengJiaJuPeiWangActivity activity = mActivity.get();
            activity.setZhuangTaiZhanShi("2");
            mProgressDialog = new ProgressDialog(activity);
        }

        @Override
        protected void onProgressUpdate(IEsptouchResult... values) {
            Log.i(ZhiNengJiaJuPeiWangActivity.class.getSimpleName(), "onProgressUpdate" + values[0].getBssid() + values[0].getInetAddress());
            Activity context = mActivity.get();
            if (context != null) {
                Toast.makeText(context, "配网成功", Toast.LENGTH_SHORT).show();

                thread = new lianWangThread();
                thread.start();
            }
        }

        @Override
        protected List<IEsptouchResult> doInBackground(byte[]... params) {
            ZhiNengJiaJuPeiWangActivity activity = mActivity.get();
            Log.i(ZhiNengJiaJuPeiWangActivity.class.getSimpleName(), "doInBackground"+params.length);
            int taskResultCount;
            synchronized (mLock) {
                byte[] apSsid = params[0];
                byte[] apBssid = params[1];
                byte[] apPassword = params[2];
                byte[] deviceCountData = params[3];
                byte[] broadcastData = params[4];
                taskResultCount = deviceCountData.length == 0 ? -1 : Integer.parseInt(new String(deviceCountData));
                Context context = activity.getApplicationContext();
                mEsptouchTask = new EsptouchTask(apSsid, apBssid, apPassword, context);
                mEsptouchTask.setPackageBroadcast(broadcastData[0] == 1);
                mEsptouchTask.setEsptouchListener(this::publishProgress);
            }
            return mEsptouchTask.executeForResults(taskResultCount);
        }

        @Override
        protected void onPostExecute(List<IEsptouchResult> result) {
            ZhiNengJiaJuPeiWangActivity activity = mActivity.get();
            Log.i(ZhiNengJiaJuPeiWangActivity.class.getSimpleName(), "onProgressUpdate" + result.get(0).getBssid() + result.get(0).getInetAddress());
            activity.mTask = null;
            mProgressDialog.dismiss();
            if (result == null) {
                mResultDialog = new AlertDialog.Builder(activity)
                        .setMessage(R.string.esptouch1_configure_result_failed_port)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
                mResultDialog.setCanceledOnTouchOutside(false);
                return;
            }

            // check whether the task is cancelled and no results received
            IEsptouchResult firstResult = result.get(0);
            if (firstResult.isCancelled()) {
                return;
            }
            // the task received some results including cancelled while
            // executing before receiving enough results

            if (!firstResult.isSuc()) {
                activity.setZhuangTaiZhanShi("0");
                return;
            }

            ArrayList<CharSequence> resultMsgList = new ArrayList<>(result.size());
            for (IEsptouchResult touchResult : result) {
                String message = activity.getString(R.string.esptouch1_configure_result_success_item,
                        touchResult.getBssid(), touchResult.getInetAddress().getHostAddress());
                resultMsgList.add(message);
            }
            CharSequence[] items = new CharSequence[resultMsgList.size()];


        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    private void getZhuJiNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16076");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("family_id", PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<TianJiaZhuJiMoel>>post(Urls.ZHINENGJIAJU)
                .tag(this)
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TianJiaZhuJiMoel>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TianJiaZhuJiMoel>> response) {
                        if (response.body().is_added.equals("1")) {
                            if (tishiDialog == null) {
                                Notice notice1 = new Notice();
                                notice1.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                                sendRx(notice1);

                                tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                                    @Override
                                    public void onClickCancel(View v, TishiDialog dialog) {

                                    }

                                    @Override
                                    public void onClickConfirm(View v, TishiDialog dialog) {
                                        Notice notice = new Notice();
                                        notice.type = MSG_PEIWANG_SUCCESS;
                                        RxBus.getDefault().sendRx(notice);
                                        finish();
                                    }

                                    @Override
                                    public void onDismiss(TishiDialog dialog) {

                                    }
                                });
                                tishiDialog.setTextContent("主机配网成功");
                                tishiDialog.setTextCancel("");
                                tishiDialog.setTextConfirm("完成");
                                tishiDialog.show();

                                thread.interrupt();
                                kaiGuanFlag = false;
                            }
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }

                    @Override
                    public void onError(Response<AppResponse<TianJiaZhuJiMoel>> response) {
                        super.onError(response);
                    }
                });
    }

    CharSequence message;
    CharSequence mSsid;
    byte[] ssidBytes;
    CharSequence mBssid;

    private void onWifiChanged() {
        StateResult stateResult = check();

        message = stateResult.message;
        mSsid = stateResult.ssid;
        ssidBytes = stateResult.ssidBytes;
        mBssid = stateResult.bssid;


        if (mSsid != null && mSsid.length() != 0) {
            tvWifiMing.setText(mSsid.toString());
        }

        if (stateResult.wifiConnected) {
            if (stateResult.is5G) {
                // mViewModel.message = getString(R.string.esptouch1_wifi_5g_message);
                UIHelper.ToastMessage(mContext, R.string.esptouch1_wifi_5g_message);
            }
        } else {
            if (mTask != null) {
                mTask.cancelEsptouch();
                mTask = null;
                new AlertDialog.Builder(mContext)
                        .setMessage(R.string.esptouch1_configure_wifi_change_message)
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
            }
        }
    }

    private StateResult check() {
        StateResult result = checkPermission();
        if (!result.permissionGranted) {
            return result;
        }
        result = checkLocation();
        result.permissionGranted = true;
        if (result.locationRequirement) {
            return result;
        }
        result = checkWifi();
        result.permissionGranted = true;
        result.locationRequirement = false;
        return result;
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

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tishiDialog != null) {
            tishiDialog.dismiss();
        }
        if (tishiPhoneDialog != null) {
            tishiPhoneDialog.dismiss();
        }
        if (mTask != null) {
            mTask.cancelEsptouch();
        }
        if (thread != null) {
            thread.interrupt();
        }
    }

    public void sendMqttTianJiaSheBei() {

        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);

        znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(zhuji_device_ccid_up, serverId, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
        // TODO: 2021/2/2 添加的命令待赋值
        String str = "M12" + zhuangZhiLeixing + zhuangZhiLeiXingXingHao + "2.";

        znjjMqttMingLing.tianJiaSheBei(zhuji_device_ccid_up, serverId, str, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });


    }

    TishiDialog tishiDialog;
    TishiPhoneDialog tishiPhoneDialog;
    TishiDialog tishiDialog_lianwangshibai;
    boolean x = false;

    public void jieShouMqttTianJiaSheBei() {

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_TIANJIAZHUJI) {
                    if (x) {
                        return;
                    }
                    kaiGuanFlag = false;
                    thread.interrupt();
                    if (tishiDialog == null) {

                        llMainTianjia.setVisibility(View.VISIBLE);
                        zhiNengJiaJu_0009Model = (ZhiNengJiaJu_0009Model) message.content;
                        tvText.setText("主机添加成功");
                        Glide.with(mContext).load(zhiNengJiaJu_0009Model.mc_device_url).into(ivImage);

                        rlTuichu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });

                        Notice notice1 = new Notice();
                        notice1.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                        sendRx(notice1);

                        tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {
                                Notice notice = new Notice();
                                notice.type = MSG_PEIWANG_SUCCESS;
                                RxBus.getDefault().sendRx(notice);
                                finish();
                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        tishiDialog.setTextContent("主机配网成功");
                        tishiDialog.setTextCancel("");
                        tishiDialog.setTextConfirm("完成");
                        tishiDialog.show();
                    }

                } else if (message.type == ConstanceValue.MSG_PEIWANG_ERROR) {
                    animationView.pauseAnimation();
                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {

                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            finish();
                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });
                    tishiDialog.setTextContent((String) message.content);
                    tishiDialog.setTextCancel("");
                    tishiDialog.setTextConfirm("退出重试");
                    tishiDialog.show();
                } else if (message.type == ConstanceValue.MSG_ZHUJIBANG_OTHER) {
                    peiwangOtherModel = (PeiwangOtherModel) message.content;
                    tishiPhoneDialog = new TishiPhoneDialog(mContext, new TishiPhoneDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiPhoneDialog dialog) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiPhoneDialog dialog) {
                            if (TextUtils.isEmpty(smsId)) {
                                Y.t("请发送验证码");
                                return;
                            }

                            if (TextUtils.isEmpty(dialog.getEdContent())) {
                                Y.t("请输入验证码");
                                return;
                            }

                            tianJiaSheBeiNet2(dialog.getEdContent());
                        }

                        @Override
                        public void onDismiss(TishiPhoneDialog dialog) {

                        }

                        @Override
                        public void onSendYanZhengMa(View v, TishiPhoneDialog dialog) {
                            get_code();
                        }
                    });
                    tishiPhoneDialog.setTextContent("该设备已被绑定到账号为" + peiwangOtherModel.getPhone() + "的家庭，如继续操作请手机验证");
                    tishiPhoneDialog.show();
                }
            }
        }));
    }




    private String smsId;

    private void get_code() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00001");
        map.put("key", Urls.key);
        map.put("user_phone", peiwangOtherModel.getPhone());
        map.put("mod_id", "0341");
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        Y.t("验证码获取成功");
                        Notice notice = new Notice();
                        notice.type = ConstanceValue.MSG_SENDCODE_HUIDIAO;
                        sendRx(notice);
                        if (response.body().data.size() > 0) {
                            smsId = response.body().data.get(0).getSms_id();
                        }
                        tishiPhoneDialog.startCode();
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        Y.tError(response);
                        tishiPhoneDialog.errorCode();
                    }
                });
    }

    private void tianJiaSheBeiNet2(String smsCode) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16041");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("add_device_path", "1");
        map.put("family_id", PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, ""));
        map.put("xf_device_id", peiwangOtherModel.getXf_device_id());
        map.put("access_token", peiwangOtherModel.getAccess_token());
        map.put("wifi_user", peiwangOtherModel.getWifi_user());
        map.put("wifi_pwd", peiwangOtherModel.getWifi_pwd());
        map.put("mc_device_ccid", peiwangOtherModel.getMc_device_ccid());
        map.put("server_id", peiwangOtherModel.getServer_id());
        map.put("sms_id", smsId);
        map.put("sms_code", smsCode);
        Gson gson = new Gson();
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        UIHelper.ToastMessage(mContext, "设备添加成功");

                        Notice notice = new Notice();
                        notice.type = MSG_PEIWANG_SUCCESS;
                        RxBus.getDefault().sendRx(notice);

                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        super.onError(response);
                        Y.tError(response);
                    }
                });
    }

    boolean kaiGuanFlag = true;

    private class lianWangThread extends Thread {
        private int i = 0;

        public void run() {
            while (kaiGuanFlag) {
                if (tishiDialog != null) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Yes,I am interruted,but I am still running");
                        return;

                    }
                }


                try {
                    Thread.sleep(3000);
                    if (tishiDialog == null) {
                        getZhuJiNet();

                    }
                    i = i + 1;
                    if (i == 2) {
                        tishiDialog_lianwangshibai = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        tishiDialog.setTextContent("主机配网失败，请切换网络重新尝试");
                        tishiDialog.setTextCancel("");
                        tishiDialog.setTextConfirm("确定");
                        tishiDialog.show();
                        thread.interrupt();
                        kaiGuanFlag = false;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
