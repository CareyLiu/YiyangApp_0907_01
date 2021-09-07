package com.yiyang.cn.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.roundview.RoundTextView;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.WebViewActivity;
import com.yiyang.cn.activity.homepage.DaLiBaoActivity;
import com.yiyang.cn.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.yiyang.cn.adapter.ShangJiaNearbyAdapter;
import com.yiyang.cn.adapter.tuangou.TuanGouShangJiaDetailsAdapter;
import com.yiyang.cn.adapter.tuangou.TuanGouShangJiaHighScoreList1Adapter;
import com.yiyang.cn.adapter.tuangou.TuanGouShangJiaHighScoreListAdapter;
import com.yiyang.cn.adapter.tuangou.TuanGouShangJiaListAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.GlideImageLoader;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.TuanGouShangJiaDetailsModel;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.NavigationUtils;
import com.yiyang.cn.util.phoneview.sample.ImageShowActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE;
import static com.yiyang.cn.get_net.Urls.LIBAOLIST;

public class TuanGouShangJiaDetailsActivity extends AbStracTuanGouShangJiaDetails {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;

    private List<TuanGouShangJiaDetailsModel.DataBean.FavourableListBean> favourableListBeans = new ArrayList<>();
    private List<TuanGouShangJiaDetailsModel.DataBean.HighScoreListBean> highScoreListBeans = new ArrayList<>();

    private String typeID;
    private String inst_id;
    private String shangJiaId;
    private Response<AppResponse<TuanGouShangJiaDetailsModel.DataBean>> response;
    private TuanGouShangJiaDetailsAdapter tuanGouShangJiaDetailsAdapter;
    private String inst_phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shangJiaId = getIntent().getStringExtra("shangJiaID");
        typeID = getIntent().getStringExtra("typeID");
        getNet();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tuan_gou_shang_jia_details;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    void getTurn() {
        super.getTurn();
    }


    @Override
    public void getNet() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "08012");
        map.put("key", Urls.key);
        map.put("inst_id", shangJiaId);
        Gson gson = new Gson();
        OkGo.<AppResponse<TuanGouShangJiaDetailsModel.DataBean>>post(LIBAOLIST)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouShangJiaDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouShangJiaDetailsModel.DataBean>> response) {
                        TuanGouShangJiaDetailsActivity.this.response = response;
                        TuanGouShangJiaDetailsModel.DataBean.StoreListBean storeList = response.body().data.get(0).getStoreList();
                        inst_phone = storeList.getInst_phone();

                        favourableListBeans = response.body().data.get(0).getFavourableList();
                        highScoreListBeans = response.body().data.get(0).getHighScoreList();
                        neighborListBeans = response.body().data.get(0).getNeighborList();

                        inst_id = response.body().data.get(0).getStoreList().getInst_id();
                        getTurn();
                        setFotter();
                        tuanGouShangJiaDetailsAdapter.setNewData(favourableListBeans);
                        tuanGouShangJiaDetailsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<TuanGouShangJiaDetailsModel.DataBean>> response) {
                        AlertUtil.t(TuanGouShangJiaDetailsActivity.this, response.getException().getMessage());
                    }
                });
    }

    TuanGouShangJiaDetailsModel.DataBean.StoreListBean storeListBean;
    Banner banner;

    @Override
    public void setHeader() {
        View view = View.inflate(TuanGouShangJiaDetailsActivity.this, R.layout.layout_tuangou_shangjia_details_header, null);

        banner = view.findViewById(R.id.banner);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        TextView tvShopName = view.findViewById(R.id.tv_shop_name);
        AppCompatRatingBar ratingBar = view.findViewById(R.id.star);
        TextView tvRenJun = view.findViewById(R.id.tv_renjun);
        TextView tvAddr = view.findViewById(R.id.tv_addr);
        ImageView iv_image2 = view.findViewById(R.id.iv_image2);
        RoundTextView roundTextView = view.findViewById(R.id.rtv_maidan);
        roundTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.isEmpty(inst_id)) {
                    TuanGouMaiDanActivity.actionStart(TuanGouShangJiaDetailsActivity.this, inst_id, typeID);
                }
            }
        });
        iv_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + inst_phone);
                intent.setData(data);
                startActivity(intent);
            }
        });


        storeListBean = response.body().data.get(0).getStoreList();
        //Glide.with(TuanGouShangJiaDetailsActivity.this).load(storeListBean.getInst_photo_url()).into(banner);
        tvShopName.setText(storeListBean.getInst_name());
        String inst_number = storeListBean.getInst_number();
        if (!StringUtils.isEmpty(inst_number)) {
            ratingBar.setRating(Float.parseFloat(inst_number));
        }


        tvRenJun.setText(storeListBean.getAverage_cost());
        tvAddr.setText(storeListBean.getAddr_all());

        tvAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.isEmpty(storeListBean.getX())) {
//                    LatLng latLng = new LatLng(Double.valueOf(storeListBean.getX()), Double.valueOf(storeListBean.getY()));
//                    NavigationUtils.Navigation(latLng);

                    if (TextUtils.isEmpty(storeListBean.getX()) || TextUtils.isEmpty(storeListBean.getY())) {
                        return;
                    }

                    String items[] = {"高德地图导航", "百度地图导航"};
                    final ActionSheetDialog dialog = new ActionSheetDialog(TuanGouShangJiaDetailsActivity.this, items, null);
                    dialog.isTitleShow(false).show();
                    dialog.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    try {
                                        Double x = Double.valueOf(storeListBean.getX());
                                        Double y = Double.valueOf(storeListBean.getY());
                                        com.amap.api.maps2d.model.LatLng latLng = new com.amap.api.maps2d.model.LatLng(x, y);
                                        NavigationUtils.Navigation(latLng);
                                    } catch (Exception e) {
                                        UIHelper.ToastMessage(MyApplication.getApp().getApplicationContext(), "请下载高德地图后重新尝试", Toast.LENGTH_SHORT);
                                    }
                                    break;
                                case 1:
                                    try {
                                        Double x = Double.valueOf(storeListBean.getX());
                                        Double y = Double.valueOf(storeListBean.getY());
                                        com.amap.api.maps2d.model.LatLng latLng = new com.amap.api.maps2d.model.LatLng(x, y);
                                        NavigationUtils.NavigationBaidu(latLng,"");
                                    } catch (Exception e) {
                                        UIHelper.ToastMessage(MyApplication.getApp().getApplicationContext(), "请下载百度地图后重新尝试", Toast.LENGTH_SHORT);
                                    }
                                    break;
                            }
                            dialog.dismiss();
                        }
                    });
                }
            }
        });

        ArrayList<String> items = new ArrayList<>();
        if (response.body().data != null) {
            for (int i = 0; i < response.body().data.get(0).getLunboList().size(); i++) {
                items.add(response.body().data.get(0).getLunboList().get(i).getImg_url());
            }
        }

        if (banner != null) {
            //设置图片集合
            banner.setImages(items);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    ImageShowActivity.actionStart(TuanGouShangJiaDetailsActivity.this, items);

                }
            });
        }

        tuanGouShangJiaDetailsAdapter.addHeaderView(view);
    }

    @Override
    public void setList() {
        tuanGouShangJiaDetailsAdapter = new TuanGouShangJiaDetailsAdapter(R.layout.item_tuangou_taocan, favourableListBeans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        tuanGouShangJiaDetailsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rtv_qianggou:
                        TuanGouShangPinDetailsActivity.actionStart(TuanGouShangJiaDetailsActivity.this, tuanGouShangJiaDetailsAdapter.getData().get(position).getInst_id(), tuanGouShangJiaDetailsAdapter.getData().get(position).getWares_id(), storeListBean.getInst_name(), storeListBean.getInst_photo_url(), storeListBean.getInst_number(), storeListBean.getAddr_all());
                        break;
                }
            }
        });
        rlvList.setAdapter(tuanGouShangJiaDetailsAdapter);

    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String shangjiaId, String typeID) {
        Intent intent = new Intent(context, TuanGouShangJiaDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("shangJiaID", shangjiaId);
        intent.putExtra("typeID", typeID);
        context.startActivity(intent);

    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("商家详情");
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

    List<TuanGouShangJiaDetailsModel.DataBean.NeighborListBean> neighborListBeans = new ArrayList<>();

    public void setFotter() {
        View view = View.inflate(TuanGouShangJiaDetailsActivity.this, R.layout.layout_shangjia_details_footer, null);
        RecyclerView rcvList1 = view.findViewById(R.id.rcv_list1);
        RecyclerView rcvList2 = view.findViewById(R.id.rcv_list2);
        TextView tv_nearby = view.findViewById(R.id.tv_nearby);
        View view_1 = view.findViewById(R.id.view_1);

        if (neighborListBeans.size() == 0) {
            tv_nearby.setVisibility(View.GONE);
            view_1.setVisibility(View.GONE);
            rcvList1.setVisibility(View.GONE);
        } else {
            tv_nearby.setVisibility(View.VISIBLE);
            view_1.setVisibility(View.VISIBLE);
            rcvList1.setVisibility(View.VISIBLE);
        }


        rcvList1.setLayoutManager(new LinearLayoutManager(TuanGouShangJiaDetailsActivity.this));
        final ShangJiaNearbyAdapter shangJiaNearbyAdapter = new ShangJiaNearbyAdapter(R.layout.item_tuangou_nearby, neighborListBeans);
        rcvList1.setAdapter(shangJiaNearbyAdapter);


        //初始化一下
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        rcvList2.setLayoutManager(gridLayoutManager);

        final TuanGouShangJiaHighScoreList1Adapter tuanGouShangJiaListAdapter = new TuanGouShangJiaHighScoreList1Adapter(R.layout.item_shangjia, highScoreListBeans);
        //  taoKeListAdapter = new TaoKeListAdapter(R.layout.layout_taokeshop, dataBeanList);
        tuanGouShangJiaListAdapter.openLoadAnimation();//默认为渐显效果
        rcvList2.setAdapter(tuanGouShangJiaListAdapter);

        shangJiaNearbyAdapter.setNewData(neighborListBeans);
        shangJiaNearbyAdapter.notifyDataSetChanged();

        tuanGouShangJiaListAdapter.setNewData(highScoreListBeans);
        tuanGouShangJiaListAdapter.notifyDataSetChanged();

        tuanGouShangJiaListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        TuanGouShangJiaDetailsActivity.actionStart(TuanGouShangJiaDetailsActivity.this, tuanGouShangJiaListAdapter.getData().get(position).getInst_id(), typeID);
                        break;
                }
            }
        });
        shangJiaNearbyAdapter.setOnItemChildClickListener(new com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    /**
                     *        tvShopName.setText(storeListBean.getInst_name());
                     *         ratingBar.setRating(Float.parseFloat(storeListBean.getInst_number()));
                     *         tvRenJun.setText("人均：" + storeListBean.getAverage_cost());
                     *         tvAddr.setText(storeListBean.getAddr_all());
                     */
                    case R.id.constrain:
                        TuanGouShangPinDetailsActivity.actionStart(TuanGouShangJiaDetailsActivity.this, shangJiaNearbyAdapter.getData().get(position).getInst_id(), shangJiaNearbyAdapter.getData().get(position).getWares_id(), storeListBean.getInst_name(), storeListBean.getInst_photo_url(), storeListBean.getInst_number(), storeListBean.getAddr_all());
                        break;
                }
            }
        });
        tuanGouShangJiaDetailsAdapter.addFooterView(view);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
