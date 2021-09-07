package com.yiyang.cn.activity;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;
import com.example.zhouwei.library.CustomPopWindow;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.yiyang.cn.R;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.Locus;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.DateUtil;




import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sl on 2019/7/3.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class LocusActivity extends BaseActivity implements OnDateSelectedListener, SeekBar.OnSeekBarChangeListener {

    LatLng latLng;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.iv_before)
    ImageView ivBefore;
    @BindView(R.id.tv_today)
    TextView tvToday;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.sb_speed)
    SeekBar sbSpeed;
    @BindView(R.id.sb_time)
    SeekBar sbTime;
    @BindView(R.id.cb_switch)
    CheckBox cbSwitch;
    SmoothMoveMarker smoothMarker;
    //AMap是地图对象
    private AMap aMap;
    private MapView mapView;
    private Date date;
    CustomPopWindow popWindow;
    //当前日期全部轨迹
    private List<LatLng> allPoints = new ArrayList<>();
    //默认显示的轨迹线路
    private List<LatLng> showPoints = new ArrayList<>();
    //滑动的总时长
    private int duration = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locus);
        StatusBarUtil.setDarkMode(this);
        ButterKnife.bind(this);
        date = DateUtil.parseServerTime(getIntent().getStringExtra("time"), "yyyy-MM-dd");
        tvToday.setText(DateUtil.getDateStr(date, "yyyy-MM-dd"));
        requestData(tvToday.getText().toString());
        map.onCreate(savedInstanceState);// 此方法必须重写
        aMap = map.getMap();
        sbSpeed.setOnSeekBarChangeListener(this);
        sbTime.setOnSeekBarChangeListener(this);

    }

    private void initView(int progress) {
        aMap.clear();
        showPoints.clear();
        for (int i = 0; i<allPoints.size() - 20 + progress;i++){
            showPoints.add(allPoints.get(i));
        }
        if (showPoints.size() > 2) {
            addPolylineInPlayGround();
//        // 获取轨迹坐标点
            LatLngBounds bounds = new LatLngBounds(showPoints.get(showPoints.size() - 2), showPoints.get(0));
            aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
            smoothMarker = new SmoothMoveMarker(aMap);
            View markerView = LayoutInflater.from(this).inflate(R.layout.marker_view, mapView, false);
            // 设置滑动的图标
            smoothMarker.setDescriptor(BitmapDescriptorFactory.fromView(markerView));
            LatLng drivePoint = showPoints.get(0);
            Pair<Integer, LatLng> pair = SpatialRelationUtil.calShortestDistancePoint(showPoints, drivePoint);
            showPoints.set(pair.first, drivePoint);
            List<LatLng> subList = showPoints.subList(pair.first, showPoints.size());
            // 设置滑动的轨迹左边点
            smoothMarker.setPoints(subList);
            // 设置滑动的总时间
            smoothMarker.setTotalDuration(duration);

            // 开始滑动
            smoothMarker.startSmoothMove();
        } else {
            showToast("当前日期暂无历史轨迹");
        }

    }


    private void addPolylineInPlayGround() {
        aMap.addPolyline(new PolylineOptions().setCustomTexture(BitmapDescriptorFactory.fromResource(R.drawable.select_blue)) //setCustomTextureList(bitmapDescriptors)
                .addAll(showPoints)
                .useGradient(true)
                .width(18));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        map.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
    }


    @OnClick({R.id.rl_back, R.id.iv_before, R.id.tv_today, R.id.iv_next,R.id.cb_switch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_before:
                date = DateUtil.parseServerTime(tvToday.getText().toString(), "yyyy-MM-dd");
                tvToday.setText(DateUtil.getOldDateByDay(date, -1, null));
                requestData(tvToday.getText().toString());
                break;
            case R.id.tv_today:
                View contentView = LayoutInflater.from(this).inflate(R.layout.view_date, frameLayout, false);
                MaterialCalendarView materialCalendarView = contentView.findViewById(R.id.calendar_view);
                popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView)//显示的布局，还可以通过设置一个View
                        //     .size(600,400) //设置显示的大小，不设置就默认包裹内容
                        .setFocusable(true)//是否获取焦点，默认为ture
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.8f) // 控制亮度
                        .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                        .create()//创建PopupWindow
                        .showAsDropDown(tvToday, -200, 0);//显示PopupWindow
                materialCalendarView.setOnDateChangedListener(this);
                break;
            case R.id.iv_next:
                date = DateUtil.parseServerTime(tvToday.getText().toString(), "yyyy-MM-dd");
                tvToday.setText(DateUtil.getOldDateByDay(date, 1, null));
                requestData(tvToday.getText().toString());
                break;
            case R.id.cb_switch:
                if (cbSwitch.isChecked()){
                    //停止滑动
                    smoothMarker.stopMove();
                }else {
                    // 开始滑动
                    smoothMarker.startSmoothMove();
                }
                break;
        }
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
        popWindow.dissmiss();


    }

    public void requestData(String time) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04147");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", "30");
//        map.put("user_car_id", PreferenceHelper.getInstance(LocusActivity.this).getString("car_id", ""));
        map.put("time", time);
        Gson gson = new Gson();
        OkGo.<AppResponse<Locus.DataBean>>post(Urls.SERVER_URL + "wit/car/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Locus.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<Locus.DataBean>> response) {
                        allPoints.clear();
                        for (int i = 0; i < response.body().data.size(); i++) {
                            allPoints.add(i, new LatLng(Double.parseDouble(response.body().data.get(i).getGaode_x()), Double.parseDouble(response.body().data.get(i).getGaode_y())));
                        }
                        initView(0);

                    }

                    @Override
                    public void onError(Response<AppResponse<Locus.DataBean>> response) {
                        AlertUtil.t(LocusActivity.this, response.getException().getMessage());
                    }
                });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.sb_speed:
                duration = duration - progress;
                smoothMarker.setTotalDuration(duration);
                break;
            case R.id.sb_time:
                initView(progress);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
