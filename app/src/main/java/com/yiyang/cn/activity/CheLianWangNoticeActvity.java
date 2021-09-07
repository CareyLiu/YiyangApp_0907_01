package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMapOptions;


import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.common.UIHelper;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.GuZhangDetailsModel;
import com.yiyang.cn.util.NavigationUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class CheLianWangNoticeActvity extends BaseActivity {
    String notifyId;
    @BindView(R.id.tv_yonghuming)
    TextView tvYonghuming;
    @BindView(R.id.tv_cheliang_xinghao)
    TextView tvCheliangXinghao;
    @BindView(R.id.tv_chepaihaoma)
    TextView tvChepaihaoma;
    @BindView(R.id.tv_fashengshijian)
    TextView tvFashengshijian;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.cl_1)
    ConstraintLayout cl1;
    @BindView(R.id.view_line1)
    View viewLine1;
    @BindView(R.id.tv_anzhuangshijian)
    TextView tvAnzhuangshijian;
    @BindView(R.id.tv_xinghao)
    TextView tvXinghao;
    @BindView(R.id.tv_shouhou_dianhua)
    TextView tvShouhouDianhua;
    @BindView(R.id.tv_changjia)
    TextView tvChangjia;
    @BindView(R.id.cl_2)
    ConstraintLayout cl2;
    @BindView(R.id.view_line_2)
    View viewLine2;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.iv_icon_2)
    ImageView ivIcon2;
    @BindView(R.id.cl_3)
    ConstraintLayout cl3;
    @BindView(R.id.tv_guanzhang_info)
    TextView tvGuanzhangInfo;
    @BindView(R.id.map)
    MapView mMapView;
    com.amap.api.maps2d.AMap aMap;
    MyLocationStyle myLocationStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notifyId = getIntent().getStringExtra("notifyId");
        requestData();

        if (aMap == null) {

            aMap = mMapView.getMap();
            //UiSettings 主要是对地图上的控件的管理，比如指南针、logo位置（不能隐藏）.....
            UiSettings settings = aMap.getUiSettings();

            //设置了定位的监听,这里要实现LocationSource接口
            // aMap.setLocationSource(this);

            // 是否显示定位按钮（据我所知不能更改，知道的麻烦告我一声）
            settings.setMyLocationButtonEnabled(true);
            //添加指南针
            //settings.setCompassEnabled(true);

            //aMap.getCameraPosition(); 方法可以获取地图的旋转角度


            //管理缩放控件
            settings.setZoomControlsEnabled(true);
            //设置logo位置，左下，底部居中，右下（隐藏logo：settings.setLogoLeftMargin(9000)）
            settings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
            //设置显示地图的默认比例尺
            settings.setScaleControlsEnabled(true);
            //每像素代表几米
            //float scale = aMap.getScalePerPixel();

            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase

        }
        mMapView.onCreate(savedInstanceState);
        aMap.setOnMarkerClickListener(mMarkerListener);

        //开始定位


    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_che_lian_wang_notice_actvity;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String notifyId) {
        Intent intent = new Intent(context, CheLianWangNoticeActvity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("notifyId", notifyId);
        context.startActivity(intent);
    }

    public void requestData() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "03004");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("notify_id", notifyId);
        Gson gson = new Gson();
        OkGo.<AppResponse<GuZhangDetailsModel.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GuZhangDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<GuZhangDetailsModel.DataBean>> response) {
                        GuZhangDetailsModel.DataBean dataBean = response.body().data.get(0);
                        tvYonghuming.setText(dataBean.getUser_name());
                        tvCheliangXinghao.setText("车辆型号：" + dataBean.getCar_brand_name());
                        tvChepaihaoma.setText("车牌号码：" + dataBean.getPlate_number());
                        tvFashengshijian.setText("发生时间：" + dataBean.getCreate_time());
                        tvAddress.setText("发生地点：" + dataBean.getGps_addr());
                        tvAnzhuangshijian.setText("安装时间：" + dataBean.getInstall_time());
                        tvXinghao.setText("型号：" + dataBean.getXinghao());
                        tvShouhouDianhua.setText("售后电话" + dataBean.getSell_phone());
                        tvChangjia.setText("厂家：" + dataBean.getChangjia_name());
                        tvGuanzhangInfo.setText(dataBean.getFailure_name());

                        LatLng marker1;
                        if (StringUtils.isEmpty(dataBean.getBaidu_x())) {

                            String lng_x = PreferenceHelper.getInstance(mContext).getString(App.WEIDU, "");
                            String lng_y = PreferenceHelper.getInstance(mContext).getString(App.JINGDU, "");
                            Double x = Double.valueOf(lng_x);
                            Double y = Double.valueOf(lng_y);
                            marker1 = new LatLng(x, y);

                            Log.i("getBaidu_x", dataBean.getBaidu_x());
                            Log.i("getbaidu_y", dataBean.getBaidu_y());


                        } else {
                            Double x = Double.valueOf(dataBean.getBaidu_x());
                            Double y = Double.valueOf(dataBean.getBaidu_y());
                            marker1 = new LatLng(x, y);

                            Log.i("getBaidu_x", dataBean.getBaidu_x());
                            Log.i("getbaidu_y", dataBean.getBaidu_y());
                        }


//                        //设置中心点和缩放比例
//                        aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
                        aMap.moveCamera(CameraUpdateFactory.zoomTo(13));

                        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.weizhiduodingbiaotilan));
                        com.amap.api.maps2d.model.Marker marker = aMap.addMarker(new MarkerOptions().position(marker1).icon(bitmapDescriptor));




                    }

                    @Override
                    public void onError(Response<AppResponse<GuZhangDetailsModel.DataBean>> response) {
                        //  AlertUtil.t(AtmosActivity.this, response.getException().getMessage());
                    }
                });
    }



    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("详情");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 设置marker的点击事件
     */
    AMap.OnMarkerClickListener mMarkerListener = new AMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            //UIHelper.ToastMessage(mContext, "点击了这个");
            try {
                LatLng latLng = marker.getPosition();
                NavigationUtils.Navigation(latLng);
            } catch (Exception e) {
                com.yiyang.cn.app.UIHelper.ToastMessage(MyApplication.getApp().getApplicationContext(), "请下载高德后重新尝试", Toast.LENGTH_SHORT);
            }

            return true; // 返回:true 表示点击marker 后marker 不会移动到地图中心；返回false 表示点击marker 后marker 会自动移动到地图中心
        }


    };

}
