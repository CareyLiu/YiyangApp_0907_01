package com.yiyang.cn.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.heytap.msp.push.HeytapPushManager;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.vivo.push.PushClient;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.zhinengjiaju.RenTiGanYingActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.LouShuiActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.MenCiActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.MenSuoActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.SosActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.YanGanActivity;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.fragment.yiyang.TabAnfangFragment;
import com.yiyang.cn.fragment.yiyang.TabHomeFragment;
import com.yiyang.cn.fragment.yiyang.TabWodeFragment;
import com.yiyang.cn.fragment.yiyang.TabXiaoxiFragment;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.DongTaiShiTiZhuangTaiModel;
import com.yiyang.cn.model.ZhiNengJiaJuNotifyJson;
import com.yiyang.cn.util.AppToast;
import com.yiyang.cn.util.DoMqttValue;
import com.yiyang.cn.util.SoundPoolUtils;
import com.yiyang.cn.util.YuYinChuLiTool;
import com.yiyang.cn.view.NoScrollViewPager;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.config.MyApplication.CAR_NOTIFY;
import static com.yiyang.cn.config.MyApplication.context;
import static com.yiyang.cn.config.MyApplication.getUser_id;
import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;


public class HomeActivity extends BaseActivity {


    @BindView(R.id.bnve)
    BottomNavigationViewEx mBnve;
    @BindView(R.id.vp)
    NoScrollViewPager mVp;
    @BindView(R.id.activity_with_view_pager)
    RelativeLayout activityWithViewPager;




    private boolean isExit;
    private SparseIntArray items;
    private TishiDialog tishiDialog;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        boolean vPush = PushClient.getInstance(context).isSupport();
        Log.i("vPush", "" + vPush);
        boolean OPush = HeytapPushManager.isSupportPush();
        Log.i("OPush", "" + OPush);

        StatusBarUtil.setLightMode(this);
        ButterKnife.bind(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        initView();
        initData(savedInstanceState);
        initEvent();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //???????????????
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("O.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_NOTIFY), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "??????O.??????");

                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.postDelayed(this, 5000);
            }
        };

        handler.postDelayed(runnable, 5000);



        dognTaiShiTiUrl();

        if (AndMqtt.getInstance().isConnect()) {
            AndMqtt.getInstance().subscribe(new MqttSubscribe()
                    .setTopic("wit/server/01/" + getUser_id())
                    .setQos(2), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        }


    }

    private List<String> roomList = new ArrayList<>();
    private List<String> deviceList = new ArrayList<>();


    private void dognTaiShiTiUrl() {
        //???????????????????????? ?????????????????????
        Map<String, String> map = new HashMap<>();
        map.put("code", "16069");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        String str = PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, "");
        if (!StringUtils.isEmpty(str)) {
            map.put("family_id", PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, ""));
        } else {
            return;
        }
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>> response) {
                        roomList.clear();
                        deviceList.clear();

                        if (response.body().data.get(0).getRoom_list().size() > 0) {
                            for (int i = 0; i < response.body().data.get(0).getRoom_list().size(); i++) {
                                roomList.add(response.body().data.get(0).getRoom_list().get(i).getName());
                            }
                        }

                        if (response.body().data.get(0).getDevice_list().size() > 0) {
                            for (int i = 0; i < response.body().data.get(0).getDevice_list().size(); i++) {
                                deviceList.add(response.body().data.get(0).getDevice_list().get(i).getName());
                            }

                        }

                        String firstInstallDongTaiShiTi = PreferenceHelper.getInstance(mContext).getString(AppConfig.FIRSTINSTALLDONGTAISHITI, "1");


                        if (firstInstallDongTaiShiTi.equals("0")) {
                            //?????????
                            if (response.body().change_state.equals("1")) {//1.??????????????? 2.?????????

                            } else {
                                new YuYinChuLiTool(context, roomList, deviceList);
                            }

                        } else if (firstInstallDongTaiShiTi.equals("1")) {
                            //??????
                            new YuYinChuLiTool(context, roomList, deviceList);

                        }

                        PreferenceHelper.getInstance(mContext).putString(AppConfig.FIRSTINSTALLDONGTAISHITI, "0");
                    }

                    @Override
                    public void onError(Response<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }


                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private void xiuGaiDongTaiShiTiFinish() {
        //???????????????????????? ?????????????????????
        Map<String, String> map = new HashMap<>();
        map.put("code", "16070");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("family_id", PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, ""));
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>> response) {
                    }

                    @Override
                    public void onError(Response<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }


                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }


    private void zhiNengJiaJuCaoZuo(Notice notice) {
        if (tishiDialog != null && tishiDialog.isShowing()) {
            return;
        }
        /**
         / 00 ?????? 01.??? 02.?????? 03.?????? 04.?????? 05?????? 06.????????????(?????????????????????????????????????????????)
         / 07.?????????  08.?????? 09.????????? 10.?????? 11.???????????? 12.?????? 13.??????14.??????
         / 15.???????????? 16.?????? 17.??????(?????????????????????????????????????????????????????????) 18.?????????
         / 19.???????????? 20.??????????????? 21.?????????????????? 22.????????????????????? 23.???????????? 24.????????????
         / 25.???????????? 26.?????? 27???????????????????????? 28.???????????? 29.???????????? 30.????????????
         / 31.???????????? 32.???????????? 33.?????? 34.??????
         */
        ZhiNengJiaJuNotifyJson zhiNengJiaJuNotifyJson = (ZhiNengJiaJuNotifyJson) notice.content;
        ZhiNengJiaJuNotifyJson finalZhiNengJiaJuNotifyJson1 = zhiNengJiaJuNotifyJson;

        tishiDialog = new TishiDialog(mContext, 1, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {
            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("12")) {
                    MenCiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("11")) {
                    YanGanActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("15")) {
                    SosActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id(), true);
                } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("05")) {
                    MenSuoActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("13")) {
                    LouShuiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("34")) {
                    RenTiGanYingActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                }
                tishiDialog.dismiss();
            }

            @Override
            public void onDismiss(TishiDialog dialog) {
            }
        });


        if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("12")) {
            tishiDialog.setTextContent("??????????????????????????????????????????????????????????");
            //MenCiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("11")) {
            tishiDialog.setTextContent("???????????????????????????????????????????????????????????????????");
            //YanGanActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("15")) {
            tishiDialog.setTextContent("???????????????sos?????????????????????????????????????????????????");
            //SosActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id(), true);
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("05")) {
            tishiDialog.setTextContent("??????????????????????????????????????????????????????????");
            //MenSuoActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("13")) {
            tishiDialog.setTextContent("??????????????????????????????????????????????????????????");
            //LouShuiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("34")) {
            tishiDialog.setTextContent("????????????????????????????????????????????????????????????????");
            //RenTiGanYingActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else {
            tishiDialog.setTextContent("?????????????????????????????????????????????????");
        }
        String simpleName = MyApplication.getApp().activity_main.getClass().getSimpleName();
        boolean menciFlag = !simpleName.equals(MenCiActivity.class.getSimpleName());
        boolean yanganFlag = !simpleName.equals(YanGanActivity.class.getSimpleName());
        boolean sosFlag = !simpleName.equals(SosActivity.class.getSimpleName());
        boolean loushuiFalg = !simpleName.equals(LouShuiActivity.class.getSimpleName());

        if (menciFlag && yanganFlag && sosFlag && loushuiFalg) {
            if (tishiDialog != null && !tishiDialog.isShowing()) {
                tishiDialog.show();
                String strBaoJingYin = PreferenceHelper.getInstance(mContext).getString(AppConfig.BAOJING_YANGAN, "2");
                if (strBaoJingYin.equals("0")) {

                } else {
                    SoundPoolUtils.soundPool(mContext, R.raw.baojingyin3);
                }
            }
        }


    }


    public int position;
    Runnable runnable;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                AppToast.makeShortToast(this, "???????????????????????????");
                isExit = true;
                new Thread() {
                    public void run() {
                        SystemClock.sleep(3000);
                        isExit = false;
                    }

                }.start();
                return true;
            }
            AppManager.getAppManager().finishAllActivity();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * change BottomNavigationViewEx style
     */
    private void initView() {
        mBnve.enableAnimation(false);
        mBnve.enableShiftingMode(false);
        mBnve.enableItemShiftingMode(false);
    }

    /**
     * create fragments
     */
    private void initData(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>(4);
        items = new SparseIntArray(4);

        TabHomeFragment tabHomeFragment = new TabHomeFragment();
        TabAnfangFragment tabAnfangFragment = new TabAnfangFragment(savedInstanceState);
        TabXiaoxiFragment tabXiaoxiFragment = new TabXiaoxiFragment();
        TabWodeFragment tabWodeFragment = new TabWodeFragment();

        fragments.add(tabHomeFragment);
        fragments.add(tabAnfangFragment);
        fragments.add(tabXiaoxiFragment);
        fragments.add(tabWodeFragment);

        items.put(R.id.i_home, 0);
        items.put(R.id.i_zhinengjiaju, 1);
        items.put(R.id.i_message, 2);
        items.put(R.id.i_mine, 3);

        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        mVp.setOffscreenPageLimit(4);
        mVp.setScroll(false);
        mVp.setAdapter(adapter);
    }

    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to change the current item of view pager when click bottom nav item
        mBnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = items.get(item.getItemId());
                if (previousPosition != position) {
                    previousPosition = position;
                    mVp.setCurrentItem(position, false);
                }
                return true;
            }
        });

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBnve.setCurrentItem(position);
                if (position == 1) {
                    PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.ZHINENGJIAJU);
                } else {
                    PreferenceHelper.getInstance(mContext).removeKey(App.CHOOSE_KONGZHI_XIANGMU);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    Handler handler;

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }


    public static HomeActivity getInstance() {
        return new HomeActivity();
    }
}
