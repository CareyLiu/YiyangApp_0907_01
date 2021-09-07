package com.yiyang.cn.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.model.HostModel;
import com.yiyang.cn.model.SerializableMap;
import com.yiyang.cn.service.HeaterMqttService;
import com.yiyang.cn.util.ConstantUtil;
import com.yiyang.cn.util.DialogManager;
import com.yiyang.cn.view.CircleMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ControCarActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_windmill)
    ImageView ivWindmill;
    @BindView(R.id.sb_speed)
    SeekBar sbSpeed;
    @BindView(R.id.tv_wd)
    TextView tvWd;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_car)
    ImageView ivCar;
    @BindView(R.id.iv_left_head_door)
    ImageView ivLeftHeadDoor;
    @BindView(R.id.iv_right_head_door)
    ImageView ivRightHeadDoor;
    @BindView(R.id.iv_left_rear_door)
    ImageView ivLeftRearDoor;
    @BindView(R.id.iv_right_rear_door)
    ImageView ivRightRearDoor;
    @BindView(R.id.iv_trunk_covers)
    ImageView ivTrunkCovers;
    @BindView(R.id.iv_head_light)
    ImageView ivHeadLight;
    @BindView(R.id.iv_back_light)
    ImageView ivBackLight;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.circle_menu)
    CircleMenuView circleMenu;
    @BindView(R.id.iv_tyre)
    ImageView ivTyre;
    @BindView(R.id.iv_fuel)
    ImageView ivFuel;
    @BindView(R.id.tv_fuel)
    TextView tvFuel;
    @BindView(R.id.iv_coolant)
    ImageView ivCoolant;
    @BindView(R.id.tv_coolant)
    TextView tvCoolant;
    @BindView(R.id.iv_glass_water)
    ImageView ivGlassWater;
    @BindView(R.id.tv_glass_water)
    TextView tvGlassWater;
    @BindView(R.id.iv_speed)
    ImageView ivSpeed;
    @BindView(R.id.tv_speed)
    TextView tvSpeed;
    private Boolean isStart = false;
    private Animation rotate_wind, rotate_tyre,alpha_light;
    public static Handler stateHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_control);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        toolbar.setNavigationIcon(R.drawable.back_icon);
        @SuppressLint("ResourceType") ColorStateList csl = getResources().getColorStateList(R.drawable.menu_item_color);
        navigationView.setItemTextColor(csl);
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbarTitle.setText(PreferenceHelper.getInstance(this).getString("name",""));
        rotate_wind = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        rotate_tyre = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        alpha_light = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);


        initHandler();
        HeaterMqttService.handler = stateHandler;
        HeaterMqttService.subscribe();
        //查询时时数据
        HeaterMqttService.mqttService.publish("N9.", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);
        DialogManager.getManager((Activity) ControCarActivity.this).showMessage("正在获取车辆信息");



        sbSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvTime.setText("启动时间："+(progress+5)+"(min)");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        circleMenu.setOnMenuItemClickListener(new CircleMenuView.OnMenuItemClickListener() {
            @Override
            public void itemClick(int pos) {
                switch (pos) {
                    case 0://开启后备箱
                        break;
                    case 1://关闭后备箱
                        break;
                    case 2://开启大灯
                        break;
                    case 3://关闭大灯
                        break;
                    case 4://开锁
                        break;
                    case 5://上锁
                        break;
                    case 6://鸣笛
                        break;
                    case 7://开启加热器
                        break;
                }
            }

            @Override
            public void itemCenterClick() {
                if (circleMenu.isStart()){
                    ivWindmill.startAnimation(rotate_wind);
                    ivTyre.startAnimation(rotate_tyre);
                    ivHeadLight.setVisibility(View.VISIBLE);
                    ivBackLight.setVisibility(View.VISIBLE);
                    ivBackLight.startAnimation(alpha_light);
                }else {
                    ivWindmill.clearAnimation();
                    ivTyre.clearAnimation();
                    ivHeadLight.setVisibility(View.GONE);
                    ivBackLight.setVisibility(View.GONE);
                    ivBackLight.clearAnimation();
                }


            }
        });


    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        stateHandler = new Handler() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void handleMessage(Message msg) {

                Bundle b = msg.getData();
                switch (msg.what) {
                    case ConstantUtil.MSG_HEATER_ACTUAL_DATA:
                        //加载实时数据
                        DialogManager.getManager((Activity) ControCarActivity.this).dismiss();
                        SerializableMap serializableMap = (SerializableMap) b.get("map");
                        assert serializableMap != null;
                        Log.d("version", serializableMap.getMap().get("version_feng"));
                        HostModel.version = serializableMap.getMap().get("version_feng");
                        tvSpeed.setText(serializableMap.getMap().get("mph")+"km/h");
                        tvCoolant.setText(serializableMap.getMap().get("coolant"));
                        tvFuel.setText(serializableMap.getMap().get("benzin"));

                        break;


                }
                super.handleMessage(msg);
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.heater_menu_option, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_bluetooth:
                //切换蓝牙模式
                showToast("暂不支持");
                break;
            case R.id.nav_trajectory:
                //历史轨迹
                startActivity(new Intent(this, HistoryLocusActivity.class));
                break;
            case R.id.nav_location:
                //定位
                startActivity(new Intent(this, LocationActivity.class));
                break;
            case R.id.nav_timing:
                //定时
                startActivity(new Intent(this, AppointmentActivity.class));
                break;
            case R.id.nav_record:
                //维修记录
                startActivity(new Intent(this, RepairOrderAcitivity.class));
                break;
            case R.id.nav_state:
                //加热器状态
                startActivity(new Intent(this, DeviceStateActivity.class));
                break;
            case R.id.nav_report:
                //故障报警
                startActivity(new Intent(this, DiagnosisActivity.class));
                break;
            case R.id.nav_corral:
                //地理围栏
                startActivity(new Intent(this, WebViewActivity.class).
                        putExtra("url", PreferenceHelper.getInstance(this).getString("fence_url", "")).putExtra("title", "地理围栏"));
                break;
            case R.id.nav_setting:
                startActivity(new Intent(this, HeaterSettingActivity.class));
                //设置
                break;
        }
        return false;
    }
}
