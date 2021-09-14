package com.yiyang.cn.fragment.yiyang;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMapOptions;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.aakefudan.model.ZixunModel;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.adapter.yiyang.AnfangAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.model.yiyang.AnfangModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabAnfangFragment extends BaseFragment {
    @BindView(R.id.tv_select_name)
    TextView tv_select_name;
    @BindView(R.id.tv_title_baojing)
    TextView tv_title_baojing;
    @BindView(R.id.tv_title_rizhi)
    TextView tv_title_rizhi;
    @BindView(R.id.rv_baojing)
    RecyclerView rv_baojing;
    @BindView(R.id.ll_group_baojing)
    LinearLayout ll_group_baojing;
    @BindView(R.id.ll_group_rizhi)
    LinearLayout ll_group_rizhi;
    @BindView(R.id.iv_select_name)
    ImageView iv_select_name;
    @BindView(R.id.iv_saoyisao)
    ImageView iv_saoyisao;
    @BindView(R.id.line_title_baojing)
    View line_title_baojing;
    @BindView(R.id.rl_title_baojing)
    RelativeLayout rl_title_baojing;
    @BindView(R.id.line_title_rizhi)
    View line_title_rizhi;
    @BindView(R.id.rl_title_rizhi)
    RelativeLayout rl_title_rizhi;
    @BindView(R.id.map)
    MapView mMapView;

    private List<AnfangModel> anfangModels;
    private AnfangAdapter anfangAdapter;
    private AMap aMap;
    private Bundle savedInstanceState;

    public TabAnfangFragment(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .statusBarColor(R.color.white)
                .init();
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
        return R.layout.yiyang_frag_tab_anfang;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        selectBaojing();
        initMap();
        initAdapter();
    }

    private void initMap() {
        aMap = mMapView.getMap();
        //UiSettings 主要是对地图上的控件的管理，比如指南针、logo位置（不能隐藏）.....
        UiSettings settings = aMap.getUiSettings();

        //设置了定位的监听,这里要实现LocationSource接口
        // aMap.setLocationSource(this);

        // 是否显示定位按钮（据我所知不能更改，知道的麻烦告我一声）
        settings.setMyLocationButtonEnabled(false);

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

        //aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        mMapView.onCreate(savedInstanceState);
    }

    private void initAdapter() {
        anfangModels = new ArrayList<>();
        anfangAdapter = new AnfangAdapter(R.layout.yiyang_item_anfang, anfangModels);
        rv_baojing.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_baojing.setAdapter(anfangAdapter);

        anfangModels.add(new AnfangModel(R.mipmap.jujiaanfang, "居家安防", "添加日期：2021-09-08", 1));
        anfangModels.add(new AnfangModel(R.mipmap.shengmingtizhengdian, "生命体征垫", "添加日期：2021-09-08", 1));
        anfangModels.add(new AnfangModel(R.mipmap.zhinengshouhuan, "智能手环", "添加日期：2021-09-08", 2));
        anfangModels.add(new AnfangModel(R.mipmap.meiqibaojingqi, "煤气报警器", "添加日期：2021-09-08", 1));
        anfangModels.add(new AnfangModel(R.mipmap.yanwubaojingqi, "烟雾报警器", "添加日期：2021-09-08", 1));
        anfangAdapter.setNewData(anfangModels);
        anfangAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.tv_select_name, R.id.iv_select_name, R.id.iv_saoyisao, R.id.rl_title_baojing, R.id.rl_title_rizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_name:
                break;
            case R.id.iv_select_name:
                break;
            case R.id.iv_saoyisao:
                break;
            case R.id.rl_title_baojing:
                selectBaojing();
                break;
            case R.id.rl_title_rizhi:
                selectRizhi();
                break;
        }
    }

    private void selectBaojing() {
        tv_title_baojing.setTextColor(Y.getColor(R.color.color_1));
        line_title_baojing.setVisibility(View.VISIBLE);

        tv_title_rizhi.setTextColor(Y.getColor(R.color.color_c));
        line_title_rizhi.setVisibility(View.INVISIBLE);

        ll_group_baojing.setVisibility(View.VISIBLE);
        ll_group_rizhi.setVisibility(View.GONE);
    }


    private void selectRizhi() {
        tv_title_rizhi.setTextColor(Y.getColor(R.color.color_1));
        line_title_rizhi.setVisibility(View.VISIBLE);

        tv_title_baojing.setTextColor(Y.getColor(R.color.color_c));
        line_title_baojing.setVisibility(View.INVISIBLE);


        ll_group_rizhi.setVisibility(View.VISIBLE);
        ll_group_baojing.setVisibility(View.GONE);
    }
}
