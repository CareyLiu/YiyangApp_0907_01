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
import com.tuya.smart.wrapper.api.TuyaWrapper;
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
    @BindView(R.id.tv_yuyin_image)
    ImageView tvYuyinImage;
    @BindView(R.id.tv_shangchuan)
    TextView tvShangchuan;
    @BindView(R.id.tv_chaxun_dabao_zhuangtai)
    TextView tvChaxunDabaoZhuangtai;
    @BindView(R.id.tv_shezhi)
    TextView tvShezhi;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.cl_top)
    ConstraintLayout clTop;
    @BindView(R.id.tv_shishishuo)
    TextView tvShishishuo;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.rrl_yuyin_mianban)
    RelativeLayout rrlYuyinMianban;

    private boolean isExit;
    private SparseIntArray items;
    TishiDialog tishiDialog;

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

        TuyaWrapper.onLogin();

        initView();
        initData();
        initEvent();

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice notice) {
                if (notice.type == ConstanceValue.MSG_GOTOXIAOXI) {
                    mVp.setCurrentItem(3, false);
                } else if (notice.type == ConstanceValue.MSG_P) {
                    handler.removeCallbacks(runnable);
                } else if (notice.type == ConstanceValue.MSG_ZHINENGJIAJU) {
                    mVp.setCurrentItem(1, false);
                } else if (notice.type == ConstanceValue.MSG_ZHINENGJIAJU_MENCI) {
                    zhiNengJiaJuCaoZuo(notice);
                } else if (notice.type == ConstanceValue.MSG_CAOZUODONGTAISHITI) {
                    dognTaiShiTiUrl();
                } else if (notice.type == ConstanceValue.MSG_XIUGAIDONGTAISHITIFINISH) {
                    xiuGaiDongTaiShiTiFinish();
                }
            }
        }));

        rrlYuyinMianban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //要做的事情
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("O.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_NOTIFY), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "订阅O.成功");

                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.postDelayed(this, 5000);
            }
        };

        handler.postDelayed(runnable, 5000);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rrlYuyinMianban.setVisibility(View.GONE);
            }
        });

        tvShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YuYinSheZhiActivity.actionStart(HomeActivity.this);
            }
        });

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
        //访问网络获取数据 下面的列表数据
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
                            //非首次
                            if (response.body().change_state.equals("1")) {//1.没有改动过 2.改动过

                            } else {
                                new YuYinChuLiTool(context, roomList, deviceList);
                            }

                        } else if (firstInstallDongTaiShiTi.equals("1")) {
                            //首次
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
        //访问网络获取数据 下面的列表数据
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
         / 00 主机 01.灯 02.插座 03.喂鱼 04.浇花 05门锁 06.空调电视(开关，加风，减风，讯飞语音配置)
         / 07.车库门  08.开关 09.晾衣架 10.窗磁 11.烟雾报警 12.门磁 13.漏水14.雷达
         / 15.紧急开关 16.窗帘 17.电视(开关，加减音量，加减亮暗，讯飞语音配置) 18.摄像头
         / 19.空气检测 20.温湿度检测 21.煤气管道关闭 22.自来水管道关闭 23.宠物喂食 24.宠物喂水
         / 25.智能手环 26.排风 27背景音乐显示控制 28.电视遥控 29.空气净化 30.体质检测
         / 31.光敏控制 32.燃气报警 33.风扇 34.雷达
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
            tishiDialog.setTextContent("您家庭中的门磁有新的状况，是否前去查看?");
            //MenCiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("11")) {
            tishiDialog.setTextContent("您家庭中的烟雾感应器有新的状况，是否前去查看?");
            //YanGanActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("15")) {
            tishiDialog.setTextContent("您家庭中的sos紧急报警有新的状况，是否前去查看?");
            //SosActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id(), true);
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("05")) {
            tishiDialog.setTextContent("您家庭中的门锁有新的状况，是否前去查看?");
            //MenSuoActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("13")) {
            tishiDialog.setTextContent("您家庭中的漏水有新的状况，是否前去查看?");
            //LouShuiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("34")) {
            tishiDialog.setTextContent("您家庭中的人体感应有新的状况，是否前去查看?");
            //RenTiGanYingActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else {
            tishiDialog.setTextContent("您家庭中有新的状况，是否前去查看?");
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
                AppToast.makeShortToast(this, "再按一次返回键退出");
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
    private void initData() {
        List<Fragment> fragments = new ArrayList<>(4);
        items = new SparseIntArray(4);

        TabHomeFragment tabHomeFragment = new TabHomeFragment();
        TabAnfangFragment tabAnfangFragment = new TabAnfangFragment();
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
