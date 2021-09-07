package com.yiyang.cn.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.google.android.material.appbar.AppBarLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.GoodsEvaluateAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.GlideImageLoader;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.GoodsDetails_f;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.view.CustomBottomDialog;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoosDetailsActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.abl_bar)
    AppBarLayout ablBar;
    @BindView(R.id.list)
    LRecyclerView list;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.iv_cart)
    ImageView ivCart;
    @BindView(R.id.banner)
    Banner banner;
    private View includeToolbarTitle;
    private int mMaskColor;//

    private GoodsEvaluateAdapter goodsEvaluateAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<GoodsDetails_f.DataBean.AssListBean> beanList = new ArrayList<>();
    private View header;
    private TextView tvGoodsPrice, tvGoodsName, tvExpressPrice, tvSoldCount, tvAddress;
    private LinearLayout layout_select, layout_parameter, layout_evaluate;

    private CustomBottomDialog customBottomDialog;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_goods_details);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);
        //背景颜色
        mMaskColor = ContextCompat.getColor(this, R.color.white);
        includeToolbarTitle = findViewById(R.id.include_toolbar_title);
        ablBar.addOnOffsetChangedListener(this);
        header = LayoutInflater.from(this).inflate(R.layout.hot_goods_details_header, (ViewGroup) Objects.requireNonNull(this).findViewById(android.R.id.content), false);
        tvGoodsPrice = header.findViewById(R.id.tv_goods_price);
        tvGoodsName = header.findViewById(R.id.tv_goods_name);
        tvExpressPrice = header.findViewById(R.id.tv_express_price);
        tvAddress = header.findViewById(R.id.tv_address);
        tvSoldCount = header.findViewById(R.id.tv_sold_count);
        layout_evaluate = header.findViewById(R.id.layout_evaluate);
        layout_parameter = header.findViewById(R.id.layout_parameter);
        layout_select = header.findViewById(R.id.layout_select);
        customBottomDialog = new CustomBottomDialog(GoosDetailsActivity.this, null);
        goodsEvaluateAdapter = new GoodsEvaluateAdapter(this);
        goodsEvaluateAdapter.setDataList(beanList);
        list.setLayoutManager(new LinearLayoutManager(this));
        lRecyclerViewAdapter = new LRecyclerViewAdapter(goodsEvaluateAdapter);
        list.setAdapter(lRecyclerViewAdapter);
        lRecyclerViewAdapter.addHeaderView(header);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        list.setPullRefreshEnabled(false);
        list.setLoadMoreEnabled(false);
        getData();
        customBottomDialog.setAddClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加购物车
                addShopCart();
            }
        });
        customBottomDialog.setBuyClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        layout_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择套餐

                customBottomDialog.setCanceledOnTouchOutside(false);
                customBottomDialog.show();
            }
        });
        layout_parameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择商品参数
            }
        });
        layout_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看全部评价

            }
        });
    }

    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04133");
        map.put("key", Urls.key);
        map.put("shop_product_id", getIntent().getStringExtra("shop_product_id"));
        map.put("wares_id", getIntent().getStringExtra("wares_id"));
        Gson gson = new Gson();
        OkGo.<AppResponse<GoodsDetails_f.DataBean>>post(Urls.SERVER_URL + "shop_new/app")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GoodsDetails_f.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<GoodsDetails_f.DataBean>> response) {
                        tvGoodsPrice.setText(String.format("￥%s-%s", response.body().data.get(0).getMoney_begin(), response.body().data.get(0).getMoney_end()));
                        tvAddress.setText(response.body().data.get(0).getAddr());
                        tvExpressPrice.setText(String.format("快递:%s", response.body().data.get(0).getWares_money_go()));
                        tvGoodsName.setText(response.body().data.get(0).getShop_product_title());
                        tvSoldCount.setText(response.body().data.get(0).getWares_sales_volume());
                        for (int i = 0; i < 2; i++) {
                            if (i < response.body().data.get(0).getAssList().size())
                                beanList.add(response.body().data.get(0).getAssList().get(i));
                        }
                        beanList = response.body().data.get(0).getAssList();
                        goodsEvaluateAdapter.setDataList(beanList);
                        customBottomDialog.setDataBean(response.body().data.get(0).getProductList());
                        list.refreshComplete(2);
                        List<String> items = new ArrayList<>();
                        if (response.body().data != null) {
                            for (int i = 0; i < response.body().data.get(0).getBannerList().size(); i++) {
                                items.add(response.body().data.get(0).getBannerList().get(i).getImg_url());
                            }
                        }

                        if (banner != null) {
                            //设置图片集合
                            banner.setImages(items);
                            //banner设置方法全部调用完毕时最后调用
                            banner.start();
                        }


                        lRecyclerViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<GoodsDetails_f.DataBean>> response) {
                        AlertUtil.t(GoosDetailsActivity.this, response.getException().getMessage());
                    }
                });
    }

    public void addShopCart() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04151");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("shop_product_id", getIntent().getStringExtra("shop_product_id"));
        map.put("wares_id", getIntent().getStringExtra("wares_id"));
        map.put("pay_count", customBottomDialog.getCount());
        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(final Response<AppResponse> response) {
                        AlertUtil.t(GoosDetailsActivity.this, response.body().msg);
                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        AlertUtil.t(GoosDetailsActivity.this, response.getException().getMessage());
                    }
                });
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int absVerticalOffset = Math.abs(verticalOffset);//AppBarLayout竖直方向偏移距离px
        int totalScrollRange = appBarLayout.getTotalScrollRange();//AppBarLayout总的距离px
        //背景颜色转化成RGB的渐变色
        int argb = Color.argb(absVerticalOffset, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        //int argbDouble = Color.argb(absVerticalOffset * 2, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        //appBarLayout上滑一半距离后标题栏应该由全透明到渐变
        int title_small_offset = (200 - absVerticalOffset) < 0 ? 0 : 200 - absVerticalOffset;
        int title_small_argb = Color.argb(title_small_offset * 2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        //appBarLayout上滑不到一半距离
        if (absVerticalOffset <= totalScrollRange / 2) {
            includeToolbarTitle.setBackgroundColor(argb);
        } else {
            //appBarLayout上滑一半距离后标题栏应该由全透明到渐变
            includeToolbarTitle.setBackgroundColor(title_small_argb);

        }
        //上滑时遮罩由全透明到半透明
        includeToolbarTitle.setBackgroundColor(argb);
    }

    @OnClick({R.id.banner, R.id.rl_back, R.id.iv_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.banner:
                startActivity(new Intent(GoosDetailsActivity.this, WebViewActivity.class));
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_cart:
                startActivity(new Intent(GoosDetailsActivity.this, ShopCartActivity.class));
                break;
        }
    }
}
