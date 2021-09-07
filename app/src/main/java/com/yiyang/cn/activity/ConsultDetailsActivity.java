package com.yiyang.cn.activity;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.RepairPlantAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.RepairPlantModel;
import com.yiyang.cn.util.AlertUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsultDetailsActivity extends BaseActivity implements LocationSource, AMapLocationListener, AMap.OnMapClickListener, AMap.OnMarkerClickListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.list)
    LRecyclerView list;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.layout_car_info)
    LinearLayout layoutCarInfo;
    List<RepairPlantModel.DataBean.ListBean> modelList = new ArrayList<>();
    RepairPlantAdapter repairPlantAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;

    LatLng latLng;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_fault)
    TextView tvFault;

    //AMap是地图对象
    private AMap aMap;
    private MapView mapView;
    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    Marker marker;
    MarkerOptions markerOption;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_details);
        ButterKnife.bind(this);
        switch (Objects.requireNonNull(Objects.requireNonNull(getIntent().getData()).getQueryParameter("title"))) {
            case "master"://车主端
                break;
            case "service"://客服端
                layoutCarInfo.setVisibility(View.VISIBLE);
                map.setVisibility(View.VISIBLE);
                repairPlantAdapter = new RepairPlantAdapter(this);
                repairPlantAdapter.setDataList(modelList);
                lRecyclerViewAdapter = new LRecyclerViewAdapter(repairPlantAdapter);
                list.setLayoutManager(new LinearLayoutManager(this));
                list.setAdapter(lRecyclerViewAdapter);
                list.setLoadMoreEnabled(false);
                list.setPullRefreshEnabled(false);
                requestData(PreferenceHelper.getInstance(this).getString("service_form_id", ""));

                map.onCreate(savedInstanceState);// 此方法必须重写
                aMap = map.getMap();
                aMap.setOnMarkerClickListener(this);



                break;
        }


    }

    //地图的点击事件
    @Override
    public void onMapClick(LatLng latLng) {
        //点击地图上没marker 的地方，隐藏inforwindow
        marker.hideInfoWindow();
    }


    @OnClick({R.id.layout_back, R.id.tv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_finish:
                break;
        }
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

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //将地图移动到定位点
//                  aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));

                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false; //返回 “false”，除定义的操作之外，默认操作也将会被执行
    }


    public void requestData(final String fromId) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03318");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("service_form_id", fromId);
        Gson gson = new Gson();
        OkGo.<AppResponse<RepairPlantModel.DataBean>>post(Urls.SERVER_URL + "wit/app/car/witAgent")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<RepairPlantModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<RepairPlantModel.DataBean>> response) {
                        modelList.addAll(response.body().data.get(0).getList());
                        repairPlantAdapter.setDataList(modelList);
                        list.refreshComplete(10);
                        lRecyclerViewAdapter.notifyDataSetChanged();
                        tvName.setText(response.body().data.get(0).getCar_user_name());
                        tvFault.setText(response.body().data.get(0).getError_text());
                        tvNumber.setText(response.body().data.get(0).getPlate_number());

                        for (RepairPlantModel.DataBean.ListBean model:modelList){
                            //添加自定义mark
                            latLng = new LatLng(Double.parseDouble(model.getX()),Double.parseDouble(model.getY()));
                            markerOption = new MarkerOptions();
                            markerOption.position(latLng);
                            markerOption.title(model.getInst_name());
                            markerOption.snippet(model.getAddr());
                            if (model.getType().equals("2"))
                                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.no_cooperation)));
                            else
                                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.already_cooperation)));
                            marker = aMap.addMarker(markerOption);
                            marker.showInfoWindow();
                        }
                        if (!response.body().data.get(0).getX_begin().equals("") && !response.body().data.get(0).getY_begin().equals("") ) {
                            latLng = new LatLng(Double.parseDouble(response.body().data.get(0).getX_begin()),Double.parseDouble(response.body().data.get(0).getY_begin()));
                        }
                        markerOption = new MarkerOptions();
                        markerOption.position(latLng);
                        markerOption.draggable(true);//设置Marker可拖动
                        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.mylocation)));
                        markerOption.setFlat(true);//设置marker平贴地图效果
                        //设置缩放级别
                        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
                        markerOption.title("车主当前位置");
                        markerOption.snippet(response.body().data.get(0).getServicing_addr());
                        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
                        marker = aMap.addMarker(markerOption);
                        marker.showInfoWindow();

                    }

                    @Override
                    public void onError(Response<AppResponse<RepairPlantModel.DataBean>> response) {
                        AlertUtil.t(getApplication(), response.getException().getMessage());
                    }
                });
    }


}
