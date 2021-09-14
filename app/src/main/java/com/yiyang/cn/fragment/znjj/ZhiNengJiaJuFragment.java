package com.yiyang.cn.fragment.znjj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.ZhiNengFamilyManageDetailActivity;
import com.yiyang.cn.activity.ZhiNengHomeListActivity;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.zhinengjiaju.peinet.PeiWangYinDaoPageActivity;
import com.yiyang.cn.adapter.NewsFragmentPagerAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.fragment.znjj.model.ZhiNengModel;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ZhiNengFamilyManageBean;
import com.yiyang.cn.util.DensityUtils;
import com.yiyang.cn.util.GlideShowImageUtils;
import com.yiyang.cn.view.NoSlidingViewPager;
import com.yiyang.cn.view.magicindicator.MagicIndicator;
import com.yiyang.cn.view.magicindicator.ViewPagerHelper;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;


/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class ZhiNengJiaJuFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.ll_checkhome)
    LinearLayout ll_checkhome;
    @BindView(R.id.tv_family_name)
    TextView tv_family_name;
    @BindView(R.id.tv_device_num)
    TextView tv_device_num;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewPager)
    NoSlidingViewPager viewPager;
    @BindView(R.id.iv_zhuji_zhuangtai)
    ImageView ivZhujiZhuangtai;
    @BindView(R.id.ll_connect_device)
    LinearLayout llConnectDevice;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.tv_zhuji_zhuangtai)
    TextView tvZhujiZhuangtai;
    @BindView(R.id.bt_add_camera)
    ImageView bt_add_camera;
    @BindView(R.id.iv_tianqi)
    ImageView iv_tianqi;
    @BindView(R.id.tv_tianqi)
    TextView tv_tianqi;
    @BindView(R.id.tv_tianqi_wendu)
    TextView tv_tianqi_wendu;
    @BindView(R.id.iv_tianqi_enter)
    ImageView iv_tianqi_enter;
    @BindView(R.id.ll_tianqi_click)
    LinearLayout ll_tianqi_click;

    ImageView ivXiaoChengXu;

    private List<String> tabs = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private NewsFragmentPagerAdapter viewPagerAdapter;

    private ZhiNengDeviceFragment zhiNengDeviceFragment;
    private ZhiNengRoomFragment zhiNengRoomFragment;
    private ZhiNengChangJingFragment zhiNengJiaJuChangJingFragment;

    private List<ZhiNengModel.DataBean> dataBean = new ArrayList<>();
    private String zhuJiWenShiDu;
    private boolean isSettianqi;

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).statusBarColor(R.color.white).init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.zhinengjiaju;
    }

    private int lastX;
    private int lastY;

    private boolean isIntercept = false;
    /**
     * 按下时的位置控件相对屏幕左上角的位置X
     */
    private int startDownX;
    /**
     * 按下时的位置控件距离屏幕左上角的位置Y
     */
    private int startDownY;
    /**
     * 控件相对屏幕左上角移动的位置X
     */
    private int lastMoveX;
    /**
     * 控件相对屏幕左上角移动的位置Y
     */
    private int lastMoveY;

    @Override
    protected void initView(View view) {
        view.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件
        ivXiaoChengXu = view.findViewById(R.id.iv_xiaochengxu);
        ivXiaoChengXu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appId = "wxafadbee6859e4228"; // 填移动应用(App)的 AppId，非小程序的 AppID
                IWXAPI api = WXAPIFactory.createWXAPI(getActivity(), appId);

                WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                req.userName = "gh_f4043d63b846"; // 填小程序原始id
                //   req.path = path;                  ////拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
                req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// 可选打开 开发版，体验版和正式版
                api.sendReq(req);
            }
        });

        ivXiaoChengXu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Log.i("ImageButton-00", "x" + String.valueOf(x));
                Log.i("ImageButton-00", "y" + String.valueOf(y));
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = x;
                        lastY = y;

                        startDownX = lastMoveX = (int) event.getRawX();
                        startDownY = lastMoveY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int offsetX = x - lastX;
                        int offsetY = y - lastY;
                        Log.i("view_00", "差值" + (Float.valueOf(pingMuHeight) - v.getTop()) + "");
                        Log.i("view_00", "底部栏和控件高度" + DensityUtils.dp2px(getActivity(), 110) + "");

                        v.layout(v.getLeft() + offsetX,
                                v.getTop() + offsetY,
                                v.getRight() + offsetX,
                                v.getBottom() + offsetY);
                        break;
                    case MotionEvent.ACTION_UP:
                        // adsorbAnim(event.getRawX(), event.getRawY());
                        if (v.getTop() < 0) {
                            RelativeLayout.LayoutParams lpFeedback = new RelativeLayout.LayoutParams(
                                    DensityUtils.dp2px(getActivity(), 60), DensityUtils.dp2px(getActivity(), 60));
                            lpFeedback.leftMargin = v.getLeft();
                            lpFeedback.topMargin = v.getTop();
                            lpFeedback.setMargins(v.getLeft(), 0, 0, 0);
                            v.setLayoutParams(lpFeedback);

                        } else if ((Float.valueOf(pingMuHeight) - v.getTop()) < DensityUtils.dp2px(getActivity(), 110)) {
                            RelativeLayout.LayoutParams lpFeedback = new RelativeLayout.LayoutParams(
                                    DensityUtils.dp2px(getActivity(), 60), DensityUtils.dp2px(getActivity(), 60));
                            lpFeedback.leftMargin = v.getLeft();
                            lpFeedback.topMargin = v.getTop();
                            lpFeedback.setMargins(v.getLeft(), Integer.valueOf(pingMuHeight) - DensityUtils.dp2px(getActivity(), 140), 0, 0);
                            v.setLayoutParams(lpFeedback);

                        } else {
                            RelativeLayout.LayoutParams lpFeedback = new RelativeLayout.LayoutParams(
                                    DensityUtils.dp2px(getActivity(), 60), DensityUtils.dp2px(getActivity(), 60));
                            lpFeedback.leftMargin = v.getLeft();
                            lpFeedback.topMargin = v.getTop();
                            lpFeedback.setMargins(v.getLeft(), v.getTop(), 0, 0);
                            v.setLayoutParams(lpFeedback);
                            int lastMoveDx = Math.abs((int) event.getRawX() - startDownX);
                            int lastMoveDy = Math.abs((int) event.getRawY() - startDownY);
                            if (0 != lastMoveDx || 0 != lastMoveDy) {
                                isIntercept = true;
                            } else {
                                isIntercept = false;
                            }
                        }

                        adsorbAnim(v, event.getRawX(), event.getRawY());

                        break;
                }
                return isIntercept;
            }
        });
    }


    private void adsorbAnim(View view, float rawX, float rawY) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;

        view.animate().setDuration(400)
                .setInterpolator(new OvershootInterpolator())
                .xBy(screenWidth - view.getX() - view.getWidth())
                .start();

    }


    String pingMuHeight;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initSM();
        initHuidiao();

        pingMuHeight = DensityUtils.getScreenHeight(getActivity()) + "";
        Log.i("view_00", pingMuHeight);
    }

    private void initData() {
        ll_checkhome.setOnClickListener(this);
        bt_add_camera.setOnClickListener(this);
        ll_tianqi_click.setOnClickListener(this);
        initViewpager();
        initMagicIndicator();
        getnet("初始化");
    }

    private void initViewpager() {
        viewPager.setScroll(false);
        zhiNengDeviceFragment = new ZhiNengDeviceFragment();
        fragmentList.add(zhiNengDeviceFragment);
        zhiNengRoomFragment = new ZhiNengRoomFragment();
        fragmentList.add(zhiNengRoomFragment);
        zhiNengJiaJuChangJingFragment = new ZhiNengChangJingFragment();
        fragmentList.add(zhiNengJiaJuChangJingFragment);
        tabs.add("设备");
        tabs.add("房间");
        tabs.add("场景");
        viewPagerAdapter = new NewsFragmentPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabs == null ? 0 : tabs.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(tabs.get(index));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.sousuo));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.black_111111));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(getResources().getColor(R.color.car_text_black));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private void initSM() {
        srLSmart.setEnableLoadMore(false);
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getnet("刷新");
            }
        });
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_PEIWANG_SUCCESS) {
                    ivZhujiZhuangtai.setBackgroundResource(R.mipmap.zhikong_zhujizaixian);
                    tvZhujiZhuangtai.setText("主机在线");
                } else if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN) {
                    getnet("MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN");
                } else if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_ZHUJI) {
                    getnet("MSG_ZHINENGJIAJU_ZHUJI");
                } else if (message.type == ConstanceValue.MSG_DEVICE_ADD) {
                    getnet("MSG_DEVICE_ADD");
                } else if (message.type == ConstanceValue.MSG_DEVICE_DELETE) {//删除设备
                    getnet("MSG_DEVICE_DELETE");
                } else if (message.type == ConstanceValue.MSG_TIANJIASHEBEI) {//添加普通设备
                    getnet("MSG_TIANJIASHEBEI");
                } else if (message.type == ConstanceValue.MSG_DEVICE_ROOM_NAME_CHANGE) {//设备转移
                    getnet("MSG_DEVICE_ROOM_NAME_CHANGE");
                } else if (message.type == ConstanceValue.MSG_DELETE_FAMILY) {//删除家庭刷新
                    getnet("MSG_DELETE_FAMILY");
                } else if (message.type == ConstanceValue.MSG_NONEZHINENGJIAJU) {

                }
            }
        }));
    }

    private void getnet(String code) {
        Y.e("接口从哪里执行的  " + code);

        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16001");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengModel.DataBean>> response) {
                        dataBean = response.body().data;
                        String wendu = dataBean.get(0).getTemperature();//温度
                        String shidu = dataBean.get(0).getHumidity();//湿度

//                        Notice notice = new Notice();
//                        notice.type = ConstanceValue.MSG_ZHINENGJIAJU_ZI_SHUAXIN;
//                        notice.content = dataBean;
//                        sendRx(notice);

                        zhiNengDeviceFragment.getData(dataBean);
                        zhiNengRoomFragment.getData(dataBean);
                        zhiNengJiaJuChangJingFragment.getData(dataBean);


                        if (StringUtils.isEmpty(wendu) && StringUtils.isEmpty(shidu)) {
                            zhuJiWenShiDu = "";
                        } else {
                            zhuJiWenShiDu = "室内温度" + wendu + "℃" + " " + "室内湿度" + shidu + "℃" + " ";
                        }

//                        tv_tianqi_wendu.setText(zhuJiWenShiDu);

                        if (dataBean.get(0).getMember_type().equals("1") || dataBean.get(0).getMember_type().equals("3")) {
                            tv_family_name.setText(dataBean.get(0).getFamily_name());
                        } else {
                            tv_family_name.setText(dataBean.get(0).getFamily_name() + "(共享家庭)");
                        }
                        tv_device_num.setText(dataBean.get(0).getDevice_num() + "个设备");

                        if (dataBean.get(0).getMember_type().equals("1") || dataBean.get(0).getMember_type().equals("3")) {
                            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.ZHINENGJIAJUGUANLIYUAN, "1");
                        } else {
                            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.ZHINENGJIAJUGUANLIYUAN, "0");
                        }


                        List<ZhiNengModel.DataBean.DeviceBean> device = dataBean.get(0).getDevice();
                        if (device.size() > 0) {
                            ZhiNengModel.DataBean.DeviceBean deviceBean = device.get(0);
                            if (TextUtils.isEmpty(deviceBean.getTy_device_ccid())) {
                                PreferenceHelper.getInstance(getActivity()).putString(App.HAS_ZHUJI, "1");

//                                双城主机ccid
//                                String nowData = "zn/app/" + deviceBean.getServer_id() + "aaaaaaaaaaaaa01000140118";
//                                String hardwareData = "zn/hardware/" + deviceBean.getServer_id() + "aaaaaaaaaaaaa01000140118";

                                String nowData = "zn/app/" + deviceBean.getServer_id() + deviceBean.getDevice_ccid();
                                String hardwareData = "zn/hardware/" + deviceBean.getServer_id() + deviceBean.getDevice_ccid();
                                try {
                                    AndMqtt.getInstance().subscribe(new MqttSubscribe()
                                            .setTopic(nowData)
                                            .setQos(2), new IMqttActionListener() {
                                        @Override
                                        public void onSuccess(IMqttToken asyncActionToken) {

                                        }

                                        @Override
                                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                                        }
                                    });


                                    AndMqtt.getInstance().subscribe(new MqttSubscribe()
                                            .setTopic(hardwareData)
                                            .setQos(2), new IMqttActionListener() {
                                        @Override
                                        public void onSuccess(IMqttToken asyncActionToken) {

                                        }

                                        @Override
                                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                PreferenceHelper.getInstance(getActivity()).putString(App.HAS_ZHUJI, "");
                            }
                        } else {
                            PreferenceHelper.getInstance(getActivity()).putString(App.HAS_ZHUJI, "");
                        }

                        String familyId = dataBean.get(0).getFamily_id();
                        PreferenceHelper.getInstance(getActivity()).putString(AppConfig.PEIWANG_FAMILYID, familyId);
                        String ty_family_id = dataBean.get(0).getTy_family_id();
                        if (TextUtils.isEmpty(ty_family_id)) {
                            if (dataBean.get(0).getMember_type().equals("1") || dataBean.get(0).getMember_type().equals("3")) {
                                List<String> addRooms = new ArrayList<>();
                            }
                        } else {
                            PreferenceHelper.getInstance(getActivity()).putLong(AppConfig.TUYA_HOME_ID, Y.getLong(ty_family_id));
                        }

                        for (int i = 0; i < dataBean.get(0).getDevice().size(); i++) {
                            if (dataBean.get(0).getDevice().get(i).getDevice_type().equals("00")) {
                                PreferenceHelper.getInstance(getActivity()).putString(AppConfig.HAVEZHUJI, "1");
                                return;
                            }
                            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.HAVEZHUJI, "0");
                        }
                        /**
                         * online_state	在线状态：1.在线 2.离线
                         */
                        if (dataBean.get(0).getDevice().size() == 0) {
                            ivZhujiZhuangtai.setBackgroundResource(R.mipmap.img_connect_device);
                            tvZhujiZhuangtai.setText("添加主机");
                            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.HAVEZHUJI, "0");
                            llConnectDevice.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PeiWangYinDaoPageActivity.actionStart(getActivity());
                                }
                            });
                        } else {
                            if (dataBean.get(0).getDevice().get(0).getOnline_state() == null) {
                                ivZhujiZhuangtai.setBackgroundResource(R.mipmap.zhikong_zhujilixian);
                                tvZhujiZhuangtai.setText("主机离线");
                            } else if (dataBean.get(0).getDevice().get(0).getOnline_state().equals("1")) {
                                ivZhujiZhuangtai.setBackgroundResource(R.mipmap.zhikong_zhujizaixian);
                                tvZhujiZhuangtai.setText("主机在线");
                            } else {
                                ivZhujiZhuangtai.setBackgroundResource(R.mipmap.zhikong_zhujilixian);
                                tvZhujiZhuangtai.setText("主机离线");
                            }
                        }


                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        srLSmart.finishRefresh();
                    }
                });
    }

    private void addTuyaHome(String family_id, long ty_family_id) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16045");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("family_id", family_id);
        map.put("ty_family_id", ty_family_id + "");
        map.put("ty_room_id", "0");
        Gson gson = new Gson();
        OkGo.<AppResponse<ZhiNengFamilyManageBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyManageBean.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ZhiNengFamilyManageBean.DataBean>> response) {
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyManageBean.DataBean>> response) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_checkhome:
                startActivity(new Intent(getActivity(), ZhiNengHomeListActivity.class));
                break;
            case R.id.ll_tianqi_click:
                clickSetTianqi();
                break;
            case R.id.bt_add_camera:
                clickAddCamera();
                break;
        }
    }

    private void clickSetTianqi() {
        if (isSettianqi) {

        } else {
            Bundle bundle = new Bundle();
            bundle.putString("family_id", dataBean.get(0).getFamily_id());
            bundle.putString("ty_family_id", dataBean.get(0).getTy_family_id());
            startActivity(new Intent(getContext(), ZhiNengFamilyManageDetailActivity.class).putExtras(bundle));
        }
    }

    private TishiDialog tishiDialog;

    private void clickAddCamera() {
        // TODO: 2021/2/19 判断是不是管理员
        String str = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.ZHINENGJIAJUGUANLIYUAN, "0");
        if (str.equals("0")) {
            tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
                @Override
                public void onClickCancel(View v, TishiDialog dialog) {

                }

                @Override
                public void onClickConfirm(View v, TishiDialog dialog) {
                    tishiDialog.dismiss();
                }

                @Override
                public void onDismiss(TishiDialog dialog) {

                }
            });
            tishiDialog.setTextContent("非管理员无法添加主机及设备");
            tishiDialog.show();

        } else if (str.equals("1")) {
            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.MC_DEVICE_CCID, "aaaaaaaaaaaaaaaa80140018");
        }
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        getnet("显示");
    }
}
