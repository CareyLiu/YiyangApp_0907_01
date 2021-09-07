package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.WebViewActivity;
import com.yiyang.cn.activity.tuangou.TuanGouShangJiaListActivity;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.model.DanganModel;
import com.yiyang.cn.activity.wode_page.bazinew.utils.BaziCode;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BazismMainActivity extends BaziBaseActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.view_tab_zhitianming)
    LinearLayout view_tab_zhitianming;
    @BindView(R.id.view_tab_nianyunshi)
    LinearLayout view_tab_nianyunshi;
    @BindView(R.id.view_tab_yueyunshi)
    LinearLayout view_tab_yueyunshi;
    @BindView(R.id.view_tab_riyunshi)
    LinearLayout view_tab_riyunshi;
    @BindView(R.id.iv_gerendangan)
    ImageView iv_gerendangan;
    @BindView(R.id.ll_gerendangan)
    LinearLayout ll_gerendangan;
    @BindView(R.id.fl_fenrendangan)
    FrameLayout fl_fenrendangan;
    @BindView(R.id.tv_color)
    TextView tv_color;
    @BindView(R.id.tv_dongwu)
    TextView tv_dongwu;
    @BindView(R.id.tv_shuzi)
    TextView tv_shuzi;
    @BindView(R.id.view_chuanyi)
    View view_chuanyi;
    @BindView(R.id.view_tuiguagn)
    View view_tuiguagn;
    @BindView(R.id.view_yanpan)
    View view_yanpan;
    @BindView(R.id.view_baijian)
    View view_baijian;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name_sex)
    TextView tv_name_sex;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_main;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("八紫");
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setBackgroundResource(R.mipmap.home_icon_qiehuan);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDanan(BaziCode.ST_mingpan);
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BazismMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initBanner();
        initSM();
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

    private void initBanner() {//初始化banner
        MyImageLoader mMyImageLoader = new MyImageLoader();
        banner.setImageLoader(mMyImageLoader);
        //  initMagicIndicator1(tagList);
        List imagePath = new ArrayList<>();
        imagePath.add(R.mipmap.banner_1);
        imagePath.add(R.mipmap.banner_2);
        imagePath.add(R.mipmap.banner_3);
        banner.setImages(imagePath);
        //设置图片加载地址

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                clickDanan(BaziCode.ST_mingpan);
            }
        });
        banner.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNet();
    }

    @OnClick({R.id.view_baijian, R.id.view_chuanyi, R.id.view_tuiguagn, R.id.view_yanpan, R.id.view_tab_zhitianming, R.id.view_tab_nianyunshi, R.id.view_tab_yueyunshi, R.id.view_tab_riyunshi, R.id.fl_fenrendangan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_tab_nianyunshi:
                clickDanan(BaziCode.ST_nian);
                break;
            case R.id.view_tab_yueyunshi:
                clickDanan(BaziCode.ST_yue);
                break;
            case R.id.view_tab_riyunshi:
                clickDanan(BaziCode.ST_ri);
                break;
            case R.id.view_tab_zhitianming:
            case R.id.fl_fenrendangan:
                clickDanan(BaziCode.ST_mingpan);
                break;
            case R.id.view_chuanyi:
                clickDanan(BaziCode.ST_chuanyi);
                break;
            case R.id.view_tuiguagn:
                openActivity(TuiguangActivity.class);
                break;
            case R.id.view_yanpan:
                clickDanan(BaziCode.ST_yanpan);
                break;
            case R.id.view_baijian:
                FengshuiDanganActivity.actionStart(mContext);
                break;
        }
    }

    private void clickDanan(int code) {
        Intent intent = new Intent(this, DanganguanliActivity.class);
        intent.putExtra("code", code);
        startActivity(intent);
    }

    public void getNet() {//命盘档案列表  判读是否有数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "11013");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<DanganModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DanganModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DanganModel.DataBean>> response) {
                        List<DanganModel.DataBean> data = response.body().data;
                        if (data.size() > 0) {
                            DanganModel.DataBean dataBean = data.get(0);
                            tv_name_sex.setText(dataBean.getName() + "    " + dataBean.getSex_text());
                            ll_gerendangan.setVisibility(View.VISIBLE);
                            iv_gerendangan.setVisibility(View.GONE);
                            tv_color.setText(dataBean.getLucky_colour());
                            tv_dongwu.setText(dataBean.getLucky_goods());
                            tv_shuzi.setText(dataBean.getLucky_number());

                            String birthday_type = dataBean.getBirthday_type();
                            if (birthday_type.equals("1")) {
                                tv_birthday.setText(dataBean.getSolar_birthday());
                            } else {
                                tv_birthday.setText(dataBean.getLunar_birthday());
                            }
                        } else {
                            ll_gerendangan.setVisibility(View.GONE);
                            iv_gerendangan.setVisibility(View.VISIBLE);
                            tv_color.setText("");
                            tv_dongwu.setText("");
                            tv_shuzi.setText("");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }
}
