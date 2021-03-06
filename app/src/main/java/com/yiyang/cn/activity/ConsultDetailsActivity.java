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

    //AMap???????????????
    private AMap aMap;
    private MapView mapView;
    //??????AMapLocationClient???????????????????????????
    private AMapLocationClient mLocationClient = null;
    //??????mLocationOption?????????????????????
    public AMapLocationClientOption mLocationOption = null;
    //??????mListener????????????????????????
    private OnLocationChangedListener mListener = null;
    //???????????????????????????????????????????????????????????????????????????
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
            case "master"://?????????
                break;
            case "service"://?????????
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

                map.onCreate(savedInstanceState);// ?????????????????????
                aMap = map.getMap();
                aMap.setOnMarkerClickListener(this);



                break;
        }


    }

    //?????????????????????
    @Override
    public void onMapClick(LatLng latLng) {
        //??????????????????marker ??????????????????inforwindow
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
        //???activity??????onDestroy?????????mMapView.onDestroy()???????????????
        map.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //???activity??????onResume?????????mMapView.onResume ()???????????????????????????
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //???activity??????onPause?????????mMapView.onPause ()????????????????????????
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //???activity??????onSaveInstanceState?????????mMapView.onSaveInstanceState (outState)??????????????????????????????
        map.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //?????????????????????????????????????????????
                aMapLocation.getLocationType();//????????????????????????????????????????????????????????????????????????????????????
                aMapLocation.getLatitude();//????????????
                aMapLocation.getLongitude();//????????????
                aMapLocation.getAccuracy();//??????????????????
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//????????????
                aMapLocation.getAddress();//???????????????option?????????isNeedAddress???false??????????????????????????????????????????????????????????????????GPS??????????????????????????????
                aMapLocation.getCountry();//????????????
                aMapLocation.getProvince();//?????????
                aMapLocation.getCity();//????????????
                aMapLocation.getDistrict();//????????????
                aMapLocation.getStreet();//????????????
                aMapLocation.getStreetNum();//?????????????????????
                aMapLocation.getCityCode();//????????????
                aMapLocation.getAdCode();//????????????

                // ???????????????????????????????????????????????????????????????????????????????????????????????????
                if (isFirstLoc) {
                    //???????????????????????????
//                  aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));

                    //?????????????????? ??????????????????????????????????????????
                    mListener.onLocationChanged(aMapLocation);
                    //??????????????????
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
                //??????????????????ErrCode???????????????errInfo???????????????????????????????????????
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_LONG).show();
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

        return false; //?????? ???false???????????????????????????????????????????????????????????????
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
                            //???????????????mark
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
                        markerOption.draggable(true);//??????Marker?????????
                        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.mylocation)));
                        markerOption.setFlat(true);//??????marker??????????????????
                        //??????????????????
                        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
                        markerOption.title("??????????????????");
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
