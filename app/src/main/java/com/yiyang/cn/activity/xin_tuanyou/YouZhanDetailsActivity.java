package com.yiyang.cn.activity.xin_tuanyou;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flyco.roundview.RoundRelativeLayout;
import com.github.jdsjlzx.recyclerview.AppBarStateChangeListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.saoma.ScanActivity;
import com.yiyang.cn.adapter.xin_tuanyou.DetailsAllProductAdapter;
import com.yiyang.cn.adapter.xin_tuanyou.DetailsJiaYouJinEAdapter;
import com.yiyang.cn.adapter.xin_tuanyou.DetailsQiangHaoAdapter;
import com.yiyang.cn.adapter.xin_tuanyou.DetailsYouHaoAdapter;
import com.yiyang.cn.adapter.xin_tuanyou.XinTuanYouShengChengDingDanActivity;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.YouZhanDetailsModel;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.GridSectionAverageGapItemDecoration;
import com.yiyang.cn.util.GridSectionAverageUItemDecoration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE;

public class YouZhanDetailsActivity extends BaseActivity {

    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_youzhanname)
    TextView tvYouzhanname;
    @BindView(R.id.civ_youzhan_img)
    CircleImageView civYouzhanImg;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.ll_jiang)
    RelativeLayout llJiang;
    @BindView(R.id.iv_daohang)
    ImageView ivDaohang;
    @BindView(R.id.constrain)
    ConstraintLayout constrain;

    @BindView(R.id.all_product)
    TextView allProduct;
    @BindView(R.id.rlv_all_pro)
    RecyclerView rlvAllPro;
    @BindView(R.id.view_white)
    View viewWhite;
    @BindView(R.id.tv_choose_youhao)
    TextView tvChooseYouhao;
    @BindView(R.id.rlv_youhao_list)
    RecyclerView rlvYouhaoList;
    @BindView(R.id.view_white_1)
    View viewWhite1;
    @BindView(R.id.tv_qianghao)
    TextView tvQianghao;
    @BindView(R.id.rlv_qianghao_list)
    RecyclerView rlvQianghaoList;
    @BindView(R.id.view_white_2)
    View viewWhite2;
    @BindView(R.id.tv_jiayou_jine_huashu)
    TextView tvJiayouJineHuashu;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.rlv_jiayou_jine)
    RecyclerView rlvJiayouJine;
    @BindView(R.id.rlv_next)
    RoundRelativeLayout rlvNext;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rlv_youzhan_info)
    CardView rlvYouzhanInfo;
    @BindView(R.id.tv_biguobiao)
    TextView tvBiguobiao;
    @BindView(R.id.tv_biyouzhan)
    TextView tvBiyouzhan;
    @BindView(R.id.tv_km)
    TextView tvKm;

    ProgressDialog waitdialog;
    private List<YouZhanDetailsModel.DataBean.OilPriceListBean> allProductList = new ArrayList<>();
    private List<YouZhanDetailsModel.DataBean.OilPriceListBean.OilNosBean.GunNosBean> qiangHaoList = new ArrayList<>();
    private List<YouZhanDetailsModel.DataBean.OilPriceListBean.OilNosBean> youHaoList = new ArrayList<>();
    private List<MyModel> jiaYouJinEList = new ArrayList<>();

    private DetailsAllProductAdapter detailsAllProductAdapter;//商品
    private DetailsJiaYouJinEAdapter detailsJiaYouJinEAdapter;//加油
    private DetailsQiangHaoAdapter detailsQiangHaoAdapter;//枪号
    private DetailsYouHaoAdapter detailsYouHaoAdapter;//油耗
    Response<AppResponse<YouZhanDetailsModel.DataBean>> response;

//             "inst_id":"377",
//                     "oilType":"1",
//                     "oilNo":"90"


    private String inst_id = "";
    private String oilType = "1";
    private String oilName = "90";


    private String price;
    private String officePrice;


    private String shenMYou = "";//汽油
    private String shenMYouHao = "";//柴油
    private String shenMQiangHao = "";

    public class MyModel {
        public String a;
        public String isSelect = "0";//0m没有选中 1 选中
    }

    private String km;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        price = getIntent().getStringExtra("price");
        officePrice = getIntent().getStringExtra("officePrice");
        inst_id = getIntent().getStringExtra("inst_id");
        oilType = getIntent().getStringExtra("oilType");
        oilName = getIntent().getStringExtra("oilName");

        km = getIntent().getStringExtra("km");
        //重写AppBarLayout监听事件
        appbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    //toolbar.setVisibility(View.GONE);
                    tvYouzhanname.setVisibility(View.GONE);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    //toolbar.setVisibility(View.VISIBLE);
                    tvYouzhanname.setVisibility(View.VISIBLE);
                } else {
                    //toolbar.setVisibility(View.GONE);
                    tvYouzhanname.setVisibility(View.GONE);
                    //中间状态
                }
            }
        });


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rlvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strPrice = etText.getText().toString().trim();

                if (StringUtils.isEmpty(strPrice)) {

                    UIHelper.ToastMessage(YouZhanDetailsActivity.this, "请输入加油金额");
                    return;
                }

                BigDecimal shuRuJinEDec = new BigDecimal(etText.getText().toString().trim());

                BigDecimal tenDecimal = new BigDecimal("10");


                if (StringUtils.isEmpty(shenMQiangHao)) {

                    UIHelper.ToastMessage(YouZhanDetailsActivity.this, "请手动选择枪号");
                    return;
                } else if (StringUtils.isEmpty(etText.getText().toString().trim())) {

                    UIHelper.ToastMessage(YouZhanDetailsActivity.this, "请手动选择或者手动输入金额");
                    return;
                } else if (shuRuJinEDec.compareTo(tenDecimal) == -1) {
                    UIHelper.ToastMessage(YouZhanDetailsActivity.this, "加油金额不得小于10元");
                } else {

                    if (StringUtils.isEmpty(response.body().data.get(0).getPriceYfq())) {
                        response.body().data.get(0).setPriceYfq("0.00");
                    }

                    BigDecimal danJiaDecimal = new BigDecimal(response.body().data.get(0).getPriceYfq());

                    BigDecimal jine = new BigDecimal(etText.getText().toString().trim());

                    BigDecimal shengShu = jine.divide(danJiaDecimal, 2, RoundingMode.HALF_UP);
                    XinTuanYouShengChengDingDanActivity.actionStart(YouZhanDetailsActivity.this, shenMYou, shenMYouHao, shenMQiangHao, etText.getText().toString(), shengShu.toString(), inst_id);

                }
            }
        });


        MyModel myModel = new MyModel();
        myModel.a = "100";
        myModel.isSelect = "0";


        MyModel myModel1 = new MyModel();
        myModel1.a = "200";
        myModel1.isSelect = "0";


        MyModel myModel2 = new MyModel();
        myModel2.a = "300";
        myModel2.isSelect = "0";


        MyModel myModel3 = new MyModel();
        myModel3.a = "400";
        myModel3.isSelect = "0";

        jiaYouJinEList.add(myModel);
        jiaYouJinEList.add(myModel1);
        jiaYouJinEList.add(myModel2);
        jiaYouJinEList.add(myModel3);
        getNet();

        ivDaohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGaodeMap(PreferenceHelper.getInstance(YouZhanDetailsActivity.this).getString(App.JINGDU, "0"),
                        PreferenceHelper.getInstance(YouZhanDetailsActivity.this).getString(App.WEIDU, "0"), response.body().data.get(0).getAddr());
            }
        });

        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                jiaYouJinEList.get(0).isSelect = "0";
                jiaYouJinEList.get(1).isSelect = "0";
                jiaYouJinEList.get(2).isSelect = "0";
                jiaYouJinEList.get(3).isSelect = "0";
                detailsJiaYouJinEAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().equals("100")) {
                    jiaYouJinEList.get(0).isSelect = "1";
                }
                if (s.toString().equals("200")) {
                    jiaYouJinEList.get(1).isSelect = "1";
                }
                if (s.toString().equals("300")) {
                    jiaYouJinEList.get(2).isSelect = "1";
                }
                if (s.toString().equals("400")) {
                    jiaYouJinEList.get(3).isSelect = "1";
                }
                detailsJiaYouJinEAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_you_zhan_details;
    }


    @Override
    public void initImmersion() {
        // super.initImmersion();
        //   mImmersionBar.with(this).statusBarColor(R.color.FC0100).titleBar(appbar).fitsSystemWindows(true).init();

        mImmersionBar.with(this)
                .titleBar(toolbar)
                .statusBarDarkFont(true)
                .init();
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String inst_id, String oilType, String oilName, String km) {
        Intent intent = new Intent(context, YouZhanDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("inst_id", inst_id);
        intent.putExtra("oilType", oilType);
        intent.putExtra("oilName", oilName);
        intent.putExtra("km", km);
//        intent.putExtra("price", price);
//        intent.putExtra("officePrice", officePrice);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("油站详情");
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


    private void setShangpinList() {

        detailsAllProductAdapter = new DetailsAllProductAdapter(R.layout.item_km1, allProductList);
        rlvAllPro.setLayoutManager(new GridLayoutManager(this, 4));
        //rlvKmList.addItemDecoration(new FourLieDecoration(this));
        rlvAllPro.addItemDecoration(new GridSectionAverageUItemDecoration(10, 0, 20, 15));
        rlvAllPro.setAdapter(detailsAllProductAdapter);
        // detailsAllProductAdapter.notifyDataSetChanged();

        detailsAllProductAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        for (int i = 0; i < detailsAllProductAdapter.getData().size(); i++) {
                            allProductList.get(i).setIsSelect("0");
                        }
                        allProductList.get(position).setIsSelect("1");
                        detailsAllProductAdapter.notifyDataSetChanged();


                        oilType = detailsAllProductAdapter.getData().get(position).getOilType();
                        oilName = "";
                        getNet();
                        break;
                }

            }
        });
    }

    private void setQiangHaoList() {


        detailsQiangHaoAdapter = new DetailsQiangHaoAdapter(R.layout.item_km1, qiangHaoList);


        rlvQianghaoList.setLayoutManager(new GridLayoutManager(this, 4));
        //rlvKmList.addItemDecoration(new FourLieDecoration(this));
        rlvQianghaoList.addItemDecoration(new GridSectionAverageUItemDecoration(10, 0, 20, 15));
        rlvQianghaoList.setAdapter(detailsQiangHaoAdapter);
        //     detailsQiangHaoAdapter.notifyDataSetChanged();

        detailsQiangHaoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        for (int i = 0; i < detailsQiangHaoAdapter.getData().size(); i++) {
                            qiangHaoList.get(i).setIsSelect("0");
                        }
                        qiangHaoList.get(position).setIsSelect("1");
                        detailsQiangHaoAdapter.notifyDataSetChanged();

                        shenMQiangHao = qiangHaoList.get(position).getGunNo();
                        break;
                }

            }
        });
    }

    private void setYouHaoList() {

        detailsYouHaoAdapter = new DetailsYouHaoAdapter(R.layout.item_km1, youHaoList);
        rlvYouhaoList.setLayoutManager(new GridLayoutManager(this, 4));
        //rlvKmList.addItemDecoration(new FourLieDecoration(this));
        rlvYouhaoList.addItemDecoration(new GridSectionAverageUItemDecoration(10, 0, 20, 15));
        rlvYouhaoList.setAdapter(detailsYouHaoAdapter);
        detailsYouHaoAdapter.notifyDataSetChanged();


        detailsYouHaoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        for (int i = 0; i < detailsYouHaoAdapter.getData().size(); i++) {
                            youHaoList.get(i).setIsSelect("0");
                        }
                        youHaoList.get(position).setIsSelect("1");
                        detailsYouHaoAdapter.notifyDataSetChanged();

                        //     oilType = detailsYouHaoAdapter.getData().get(position).getOilName();
                        oilName = detailsYouHaoAdapter.getData().get(position).getOilNo();
                        getNet();
                        break;
                }

            }
        });
    }

    private void setJiaYouJine() {

        detailsJiaYouJinEAdapter = new DetailsJiaYouJinEAdapter(R.layout.item_km1, jiaYouJinEList);
        rlvJiayouJine.setLayoutManager(new GridLayoutManager(this, 4));
        //rlvKmList.addItemDecoration(new FourLieDecoration(this));
        rlvJiayouJine.addItemDecoration(new GridSectionAverageUItemDecoration(10, 0, 20, 15));
        rlvJiayouJine.setAdapter(detailsJiaYouJinEAdapter);
        //   detailsJiaYouJinEAdapter.notifyDataSetChanged();


        detailsJiaYouJinEAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        for (int i = 0; i < detailsJiaYouJinEAdapter.getData().size(); i++) {
                            jiaYouJinEList.get(i).isSelect = "0";
                        }
                        jiaYouJinEList.get(position).isSelect = "1";
                        detailsJiaYouJinEAdapter.notifyDataSetChanged();

                        etText.setText(jiaYouJinEList.get(position).a);
                        break;
                }

            }
        });
    }


    public void getNet() {

        waitdialog = ProgressDialog.show(YouZhanDetailsActivity.this, null, "正在加载，请稍候···", true, true);
        waitdialog.setCancelable(false);
        /**
         {
         "code":"04246",
         "key":"20180305124455yu",
         "inst_id":"377",
         "oilType":"1",
         "oilNo":"90"
         }

         */
        Map<String, String> map = new HashMap<>();
        map.put("code", "04246");
        map.put("key", Urls.key);
        map.put("inst_id", inst_id);
        map.put("oilType", oilType);
        map.put("oilNo", oilName);


        Gson gson = new Gson();
        OkGo.<AppResponse<YouZhanDetailsModel.DataBean>>post(HOME_PICTURE)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<YouZhanDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<YouZhanDetailsModel.DataBean>> response) {
                        YouZhanDetailsActivity.this.response = response;
                        tvYouzhanname.setText(response.body().data.get(0).getInst_name());
                        setHeaderValue();
                        allProductList = response.body().data.get(0).getOilPriceList();//

                        shenMYou = allProductList.get(0).getOilName();
                        youHaoList.clear();
                        for (int i = 0; i < allProductList.size(); i++) {
                            if (allProductList.get(i).getIsSelect().equals("1")) {
                                youHaoList.addAll(response.body().data.get(0).getOilPriceList().get(i).getOilNos());
                            }
                        }


                        shenMYouHao = youHaoList.get(0).getOilName();
                        qiangHaoList.clear();
                        for (int i = 0; i < youHaoList.size(); i++) {
                            if (youHaoList.get(i).getIsSelect().equals("1")) {
                                qiangHaoList.addAll(youHaoList.get(i).getGunNos());
                            }
                        }

                        // shenMQiangHao = qiangHaoList.get(0).getGunNo();

                        setShangpinList();
                        setJiaYouJine();
                        setQiangHaoList();
                        setYouHaoList();

                        detailsAllProductAdapter.notifyDataSetChanged();
                        detailsJiaYouJinEAdapter.notifyDataSetChanged();
                        detailsQiangHaoAdapter.notifyDataSetChanged();
                        detailsYouHaoAdapter.notifyDataSetChanged();
                        waitdialog.dismiss();
                    }

                    @Override
                    public void onError(Response<AppResponse<YouZhanDetailsModel.DataBean>> response) {
                        waitdialog.dismiss();
                        AlertUtil.t(YouZhanDetailsActivity.this, response.getException().getMessage());
                    }
                });
    }


    private void setHeaderValue() {

        Glide.with(YouZhanDetailsActivity.this).load(response.body().data.get(0).getInst_photo_url()).into(civYouzhanImg);
        tvShopName.setText(response.body().data.get(0).getInst_name());
        tvAddress.setText(response.body().data.get(0).getAddr());
        tvBiguobiao.setText("比国标价降" + response.body().data.get(0).getStandard_jiang() + "元");
        tvBiyouzhan.setText("比油站降" + response.body().data.get(0).getOil_jiang() + "元");
        tvPrice.setText(response.body().data.get(0).getPriceYfq());
        tvKm.setText(km + "km");
    }

    //跳转到高德地图
    private void checkGaodeMap(String latitude, String longtitude, String address) {
        if (isInstallApk(YouZhanDetailsActivity.this, "com.autonavi.minimap")) {// 是否安装了高德地图
            try {
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=&poiname=" + address + "&lat="
                        + latitude
                        + longtitude + "&dev=0");
                YouZhanDetailsActivity.this.startActivity(intent); // 启动调用
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
            }
        } else {// 未安装
            Toast.makeText(YouZhanDetailsActivity.this, "您尚未安装高德地图", Toast.LENGTH_LONG)
                    .show();
            Uri uri = Uri
                    .parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            YouZhanDetailsActivity.this.startActivity(intent);
        }
    }

    //跳转到百度地图
    private void checkBaiduMap(double latitude, double longtitude, String address) {
        if (isInstallApk(YouZhanDetailsActivity.this, "com.baidu.BaiduMap")) {// 是否安装了百度地图
            try {
                Intent intent = Intent.getIntent("intent://map/direction?destination=latlng:"
                        + latitude + ","
                        + longtitude + "|name:" + address + // 终点
                        "&mode=driving&" + // 导航路线方式
                        "region=" + //
                        "&src=#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                YouZhanDetailsActivity.this.startActivity(intent); // 启动调用
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
            }
        } else {// 未安装
            Toast.makeText(YouZhanDetailsActivity.this, "您尚未安装百度地图", Toast.LENGTH_LONG)
                    .show();
            Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            YouZhanDetailsActivity.this.startActivity(intent);
        }
    }

    /**
     * 判断手机中是否安装指定包名的软件
     */
    public static boolean isInstallApk(Context context, String name) {
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            if (packageInfo.packageName.equals(name)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

}
