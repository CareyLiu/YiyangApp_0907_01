package com.yiyang.cn.activity.jd_taobao_pinduoduo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.JDWebView;
import com.yiyang.cn.activity.PinDuoDuoWebView;
import com.yiyang.cn.activity.WebViewActivity;
import com.yiyang.cn.activity.taokeshagncheng.TaoKeHomeActivity;
import com.yiyang.cn.activity.taokeshagncheng.TaokeListActivity;
import com.yiyang.cn.adapter.NewsFragmentPagerAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.fragment.taoke_shangcheng.TbJdPddListFragment;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.MessageListBean;
import com.yiyang.cn.model.TaoKeTitleListModel;
import com.yiyang.cn.project_A.tb_jd_pdd_page.TBJdPdd_Home;
import com.yiyang.cn.view.CustomViewPager;
import com.yiyang.cn.view.magicindicator.MagicIndicator;
import com.yiyang.cn.view.magicindicator.ViewPagerHelper;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.MSG;
import static com.yiyang.cn.get_net.Urls.PDDWEB;

public class TaoBao_Jd_PinDuoDuoActivity extends BaseActivity implements TBJdPdd_Home, Banner.OnClickListener {
    private static final String TAG = "MessageFragment";
    MagicIndicator magicIndicator;

    List<String> tagList;
    CustomViewPager viewPager;
    ArrayList<Fragment> fragments = new ArrayList<>();
    List<MessageListBean> listBeans = new ArrayList<>();
    @BindView(R.id.iv_jd)
    ImageView ivJd;
    @BindView(R.id.iv_pdd)
    ImageView ivPdd;
    @BindView(R.id.iv_taobao)
    ImageView ivTaobao;
    @BindView(R.id.magic_indicator4)
    MagicIndicator magicIndicator4;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ll_tag)
    LinearLayout llTag;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;

    @BindView(R.id.ll_itit)
    LinearLayout llItit;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        tagList = new ArrayList<>();
//        tagList.add("男装");
//        tagList.add("女装");
//        tagList.add("内衣");
//        tagList.add("纹饰");
//        tagList.add("家电");
//
//        tagList.add("生活");
//        tagList.add("猪肉");
//        tagList.add("美食");
//        tagList.add("装修");
//        tagList.add("水果");

        // magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator4);
        viewPager = findViewById(R.id.view_pager);
        // view.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件

        showHeader();
        getNet();

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initMagicIndicator1(final List<TaoKeTitleListModel.DataBean> list) {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator4);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.black_222222));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.pinkff002f));
                simplePagerTitleView.setText(list.get(index).getItem_name());
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                linePagerIndicator.setColors(getResources().getColor(R.color.pinkff002f));
                return linePagerIndicator;
            }
        });
        // commonNavigator.setAdjustMode(true);
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
        commonNavigator.notifyDataSetChanged();

    }


    private void setThisAdapter() {
        // messageListFragments.clear();//清空
        int count = dataBeanList.size();
        for (int i = 0; i < count; i++) {
            Bundle data = new Bundle();
//            if (tagList.get(i).id != null) {
//                data("id", tagList.get(i) + "");
//            }
            //  data.putInt("type", list.get(i).type);

            data.putString("title", dataBeanList.get(i).getItem_name());
            data.putSerializable("beanList", (Serializable) dataBeanList.get(i).getChild());
            TbJdPddListFragment taoKeMallListFragment = new TbJdPddListFragment();
            taoKeMallListFragment.setArguments(data);
            fragments.add(taoKeMallListFragment);
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mAdapetr);

    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tb_jd_pdd;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("智慧医养采购联盟");
        tv_title.setTextSize(17);
        tv_title.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
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

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String taobao, String jingdong, String pinduoduo) {
        Intent intent = new Intent(context, TaoBao_Jd_PinDuoDuoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("taobao", taobao);
        intent.putExtra("jingdong", jingdong);
        intent.putExtra("pinduoduo", pinduoduo);
        context.startActivity(intent);

    }

    List<TaoKeTitleListModel.DataBean> dataBeanList = new ArrayList<>();
    List<TaoKeTitleListModel.DataBean.ChildBean> childBeanList = new ArrayList<>();

    public void getNet() {

        //访问网络获取数据 下面的列表数据

        Map<String, String> map = new HashMap<>();
        map.put("code", "00005");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("type_id", "tbk_items");
//        NetUtils<TaoKeDetailList.DataBean> netUtils = new NetUtils<>();
//        netUtils.requestData(map, TAOKELIST, getActivity(), new JsonCallback<AppResponse<TaoKeDetailList.DataBean>>() {
//            @Override
//            public void onSuccess(Response<AppResponse<TaoKeDetailList.DataBean>> response) {
//               // Log.i("response_data", new Gson().toJson(response.body()));
//                dataBeanList.addAll(response.body().data);
//                taoKeListAdapter.setNewData(dataBeanList);
//                taoKeListAdapter.notifyDataSetChanged();
//
//
//            }
//        });

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<TaoKeTitleListModel.DataBean>>post(MSG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TaoKeTitleListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TaoKeTitleListModel.DataBean>> response) {
                        Log.i("response_data", new Gson().toJson(response.body().data));
                        dataBeanList.addAll(response.body().data);
                        setThisAdapter();
                        initMagicIndicator1(dataBeanList);
                    }
                });
    }

    @Override
    public void showHeader() {
        Intent intent = getIntent();
        String taobaoPic = intent.getStringExtra("taobao");
        String jdPicture = intent.getStringExtra("jingdong");
        String pddPicture = intent.getStringExtra("pinduoduo");

        Glide.with(this).load(taobaoPic).into(ivTaobao);
        Glide.with(this).load(jdPicture).into(ivJd);
        Glide.with(this).load(pddPicture).into(ivPdd);

        ivTaobao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaoKeHomeActivity.actionStart(TaoBao_Jd_PinDuoDuoActivity.this);
            }
        });
        ivJd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JDWebView.actionStart(TaoBao_Jd_PinDuoDuoActivity.this);
            }
        });
        ivPdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PinDuoDuoWebView.actionStart(TaoBao_Jd_PinDuoDuoActivity.this);
            }
        });

        MyImageLoader mMyImageLoader = new MyImageLoader();
        banner.setImageLoader(mMyImageLoader);
        //  initMagicIndicator1(tagList);
        List imagePath = new ArrayList<>();
        imagePath.add(R.mipmap.midbanner_1);
        imagePath.add(R.mipmap.midbanner_2);
        imagePath.add(R.mipmap.midbanner_3);
        imagePath.add(R.mipmap.midbanner_4);
        imagePath.add(R.mipmap.midbanner_5);
        final List<String> imageString = new ArrayList<>();
        imageString.add("华为荣耀30");
        imageString.add("公牛插座");
        imageString.add("闪迪硬盘");
        imageString.add("iphone XS");
        imageString.add("荣耀play 4Tpro");
        banner.setBannerTitles(imageString);
        banner.setImages(imagePath);


        //设置图片加载地址
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                // startActivity(new Intent(getActivity(), WebViewActivity.class).putExtra("url", response.body().data.get(0).getBannerList().get(position).getHtml_url()));
                TaokeListActivity.actionStart(TaoBao_Jd_PinDuoDuoActivity.this, imageString.get(position));
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
