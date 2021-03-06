package com.yiyang.cn.activity.gaiban;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.roundview.RoundRelativeLayout;
import com.github.jdsjlzx.ItemDecoration.GridItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.DefaultX5WebView_HaveNameActivity;
import com.yiyang.cn.activity.SheBeiLieBiaoActivity;
import com.yiyang.cn.activity.TuanYouWebView;
import com.yiyang.cn.activity.a_yiyang.YiyangTuTActivity;
import com.yiyang.cn.activity.gouwuche.GouWuCheActivity;
import com.yiyang.cn.activity.homepage.DaLiBaoActivity;
import com.yiyang.cn.activity.jd_taobao_pinduoduo.TaoBao_Jd_PinDuoDuoActivity;
import com.yiyang.cn.activity.saoma.ScanActivity;
import com.yiyang.cn.activity.tongcheng58.GongJiangLieBiaoNewActivity;
import com.yiyang.cn.activity.tongcheng58.TongChengMainActivity;
import com.yiyang.cn.activity.tongcheng58.TongchengTagItemFragment;
import com.yiyang.cn.activity.tongcheng58.model.TcHomeModel;
import com.yiyang.cn.activity.tuangou.TuanGouShangJiaListActivity;
import com.yiyang.cn.activity.xin_tuanyou.TuanYouList;
import com.yiyang.cn.activity.zijian_shangcheng.FenLeiThirdActivity;
import com.yiyang.cn.activity.zijian_shangcheng.ZiJianShopMallActivity;
import com.yiyang.cn.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.yiyang.cn.adapter.ChiHeWanLeListAdapter;
import com.yiyang.cn.adapter.DirectAdapter;
import com.yiyang.cn.adapter.HotGoodsAdapter;
import com.yiyang.cn.adapter.NewsFragmentPagerAdapter;
import com.yiyang.cn.adapter.ShengHuoListAdapter;
import com.yiyang.cn.adapter.ZhiKongListAdapter;
import com.yiyang.cn.adapter.gaiban.HomeReMenAdapter;
import com.yiyang.cn.adapter.gaiban.HomeZiYingAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.Radius_GlideImageLoader;
import com.yiyang.cn.config.Radius_XiuPeiChangImageLoader;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.LordingDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.Home;
import com.yiyang.cn.model.TuiGuangMaModel;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.GlideShowImageUtils;
import com.yiyang.cn.util.GridAverageUIDecoration;
import com.yiyang.cn.util.Utils;
import com.yiyang.cn.view.ObservableScrollView;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.viewpager.widget.ViewPager;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.app.App.JINGDU;
import static com.yiyang.cn.app.App.WEIDU;
import static com.yiyang.cn.get_net.Urls.HOME_PICTURE;

/**
 * Created by Administrator on 2018/3/29 0029.
 */
public class HomeFragment_New extends BaseFragment implements ObservableScrollView.ScrollViewListener, View.OnClickListener {
    private static final String TAG = "HomeFragment";
    ImageView ivGouwuche;
    private View view;
    private Unbinder unbinder;
    private ImageView iv_taoke;
    HotGoodsAdapter hotGoodsAdapter = null;//?????????????????????
    LRecyclerViewAdapter hotLRecyclerViewAdapter = null;
    List<Home.DataBean.IndexShowListBean> remenListBean = new ArrayList<>();
    List<Home.DataBean.ShopListBean> groupList = new ArrayList<>();
    DirectAdapter directAdapter = null;//?????????????????????
    LRecyclerViewAdapter directLRecyclerViewAdapter = null;
    List<Home.DataBean.ProShowListBean> ziYingListBean = new ArrayList<>();
    public int choosePostion = 99999;
    LinearLayout llMain;
    Banner banner;
    private View topPanel, middlePanel;
    private int topHeight;
    private ConstraintLayout clZiYing_Middle, clReMen_Middle;
    private ConstraintLayout clZiYing_Top, clReMen_Top;
    private LinearLayout llBackground_Top, llBackground_Middle;
    private TextView tvZiYingTop, tvRemTop, tvZiYingZhiGongTop, tvReMenShangPinTop;
    private TextView tvZiYingMiddle, tvReMenMiddle, tvZiYingZhiGongMiddle, tvReMenShangPinMiddle;
    private RelativeLayout rlXiuPeiChang;
    private Banner bannerXiuPeiChang;

    private View viewLineTop, viewLineMiddle, remenViewLineTop;
    RecyclerView chiHeWanLeList, zhiKongList, shengHuoList;//?????????????????????
    private RelativeLayout llShengHuo;
    /**
     * ??????????????????????????????
     */
    private static final int REQUEST_COUNT = 10;

    ChiHeWanLeListAdapter chiHeWanLeListAdapter;
    List<Home.DataBean.IconListBean> chiHeWanLeListBeans;

    ZhiKongListAdapter zhiKongListAdapter;
    ShengHuoListAdapter shengHuoListAdapter;
    List<Home.DataBean.IntellectListBean> intellectListBeanList;
    List<Home.DataBean.LifeListBean> lifeListBeans;
    ImageView ivDaLiBao;//?????????
    ImageView ivZiJian;
    ImageView tianMaoOrTaoBao;

    ImageView ziYingZhiGon;
    ImageView reMenShangPin;
    ConstraintLayout ll_shagnchengzhuanqu;

    private String rimenOrZiYing = "0"; //0 ???????????? 1 ????????????
    ConstraintLayout clQuanBu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getYaoQingNet(getActivity());
        //???????????????
        initLocation();
        startLocation();
        // getZhuJiNet();
    }

    SmartRefreshLayout smartRefreshLayout;
    ObservableScrollView nestedScrollView;
    RelativeLayout rl_bottom;

    private ImageView ivClose;

    RecyclerView rlv_ziYing;
    HomeZiYingAdapter homeZiYingAdapter;
    RecyclerView rlvRemen;
    HomeReMenAdapter homeReMenAdapter;
    private ImageView iv_home_xiaoxi;



    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_homefragment_new;
    }

    LottieAnimationView animationView;


    private ImageView iv_banner;
    private LinearLayout ll_kuaisuwenzhen;
    private LinearLayout ll_jiatingyisheng;
    private LinearLayout ll_tag_list;
    private ViewPager vpg_tag_list;


    private void initNewView(View view) {
        iv_banner = view.findViewById(R.id.iv_banner);
        ll_kuaisuwenzhen = view.findViewById(R.id.ll_kuaisuwenzhen);
        ll_jiatingyisheng = view.findViewById(R.id.ll_jiatingyisheng);
        ll_tag_list = view.findViewById(R.id.ll_tag_list);
        vpg_tag_list = view.findViewById(R.id.vpg_tag_list);

        iv_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_kuaisuwenzhen);
            }
        });

        ll_kuaisuwenzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_kuaisuwenzhen);
            }
        });
        ll_jiatingyisheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_zaixianyisheng);
            }
        });

        initIconList();
        initTag();
    }

    private List<TcHomeModel.DataBean.IconListBean> iconList = new ArrayList<>();

    private void initIconList() {
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhihuiyanglao, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_dianhuojizhen, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zaixianyisheng, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_yibingchaxun, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_yuyueguanhao, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jijiufuwu, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_hujaiozhongxin, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_huodongzhongxin, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_yanglaoshitang, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jiankangshujun, "????????????"));

        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhihuiyanglao, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_dianhuojizhen, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zaixianyisheng, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_yibingchaxun, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_yuyueguanhao, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jijiufuwu, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_hujaiozhongxin, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_huodongzhongxin, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_yanglaoshitang, "????????????"));
        iconList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jiankangshujun, "????????????"));
    }

    private void initTag() {
        int size = iconList.size();
        int count = 0;
        ll_tag_list.removeAllViews();
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        final List<View> views = new ArrayList<>();

        for (int i = 0; i < size; i = i + 10) {

            List<TcHomeModel.DataBean.IconListBean> listBeans = new ArrayList<>();
            if (i + 10 <= size) {
                for (int j = i; j < i + 10; j++) {
                    listBeans.add(iconList.get(j));
                }
            } else {
                for (int j = i; j < size; j++) {
                    listBeans.add(iconList.get(j));
                }
            }
            View view = View.inflate(getContext(), R.layout.tongcheng_item_home_tag_xian_yl, null);
            View view_dian = view.findViewById(R.id.view_dian);
            if (i == 0) {
                view_dian.setBackgroundColor(Color.parseColor("#5064EB"));
            }
            ll_tag_list.addView(view);
            views.add(view_dian);

            TongchengTagItemFragment fragment = new TongchengTagItemFragment(listBeans);
            fragmentList.add(fragment);
            count++;
        }

        NewsFragmentPagerAdapter pagerAdapter = new NewsFragmentPagerAdapter(getChildFragmentManager(), fragmentList);
        vpg_tag_list.setAdapter(pagerAdapter);
        vpg_tag_list.setOffscreenPageLimit(count);
        vpg_tag_list.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < views.size(); i++) {
                    views.get(i).setBackgroundColor(Color.parseColor("#D2D2D2"));
                }
                views.get(position).setBackgroundColor(Color.parseColor("#5064EB"));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    protected void initView(View view) {
        initNewView(view);

        nestedScrollView = view.findViewById(R.id.scrollView);
        topPanel = view.findViewById(R.id.topPanel);
        middlePanel = view.findViewById(R.id.middlePanel);
        clZiYing_Top = topPanel.findViewById(R.id.cl_ziying);
        clReMen_Top = topPanel.findViewById(R.id.cl_remen);
        clZiYing_Middle = middlePanel.findViewById(R.id.cl_ziying);
        clReMen_Middle = middlePanel.findViewById(R.id.cl_remen);
        tvZiYingTop = topPanel.findViewById(R.id.tv_ziying);
        tvRemTop = topPanel.findViewById(R.id.tv_remen);
        llMain = view.findViewById(R.id.ll_main);

        banner = view.findViewById(R.id.banner);
        ivDaLiBao = view.findViewById(R.id.iv_dalibao);
        clQuanBu = view.findViewById(R.id.cl_quanbu);
        ll_shagnchengzhuanqu = view.findViewById(R.id.ll_shagnchengzhuanqu);

        ziYingZhiGon = view.findViewById(R.id.iv_ziying);
        reMenShangPin = view.findViewById(R.id.iv_remen);
        ivGouwuche = view.findViewById(R.id.iv_gouwuche);
        animationView = view.findViewById(R.id.animation_view);
        bannerXiuPeiChang = view.findViewById(R.id.banner_xiupeichang);
        animationView.setAnimation("freec3_data.json");
        animationView.setImageAssetsFolder("gifs/");
        animationView.loop(true);
        animationView.playAnimation();

        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaLiBaoActivity.actionStart(getActivity());
            }
        });
        rl_bottom = view.findViewById(R.id.rl_bottom);

        ivClose = view.findViewById(R.id.iv_close);
        clQuanBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rimenOrZiYing.equals("0")) {
                    FenLeiThirdActivity.actionStart(getActivity(), "????????????", "2");
                } else {
                    FenLeiThirdActivity.actionStart(getActivity(), "????????????", "1");
                }
            }
        });

        /**
         *     private TextView tvZiYingTop, tvRemTop, tvZiYingZhiGongTop, tvReMenShangPinTop;
         *     private TextView tvZiYingMiddle, tvReMenMiddle, tvZiYingZhiGongMiddle, tvReMenShangPinMiddle;
         */
        tvZiYingZhiGongTop = topPanel.findViewById(R.id.ziyingzhigong);
        tvReMenShangPinTop = topPanel.findViewById(R.id.remenshangpin);

        tvZiYingMiddle = middlePanel.findViewById(R.id.tv_ziying);
        tvReMenMiddle = middlePanel.findViewById(R.id.tv_remen);
        tvZiYingZhiGongMiddle = middlePanel.findViewById(R.id.ziyingzhigong);
        tvReMenShangPinMiddle = middlePanel.findViewById(R.id.remenshangpin);
        //????????????????????????
        chiHeWanLeList = view.findViewById(R.id.rv_chihe_wanle_list);
        chiHeWanLeList.setFocusable(false);
        zhiKongList = view.findViewById(R.id.zhikong_list);
        zhiKongList.setFocusable(false);
        shengHuoList = view.findViewById(R.id.rlv_shenghuo);
        shengHuoList.setFocusable(false);
        llShengHuo = view.findViewById(R.id.ll_shenghuo1);
        ivZiJian = view.findViewById(R.id.iv_zijian);
        tianMaoOrTaoBao = view.findViewById(R.id.iv_tianmao_or_taobao);
        nestedScrollView.setScrollViewListener(this);
        rlv_ziYing = view.findViewById(R.id.rlv_ziying_or_remen);
        rlv_ziYing.setFocusable(false);
        viewLineTop = topPanel.findViewById(R.id.view_line);
        rlvRemen = view.findViewById(R.id.rlv_remen);
        rlvRemen.setFocusable(false);
        remenViewLineTop = topPanel.findViewById(R.id.view_line_remen);
        rlXiuPeiChang = view.findViewById(R.id.rl_xiupeichang);
        rlXiuPeiChang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(getActivity(), "??????????????????");
                //XiuPeiChangHomeActivity.actionStart(getActivity());
                TuanGouShangJiaListActivity.actionStart(getActivity(), "7");
            }
        });
        clZiYing_Top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(getActivity(), "???????????????");
                rlv_ziYing.setVisibility(View.VISIBLE);
                rlvRemen.setVisibility(View.GONE);
                setZiYingOrReMenLine("0");
                rimenOrZiYing = "0";
            }
        });
        clReMen_Top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(getActivity(), "???????????????");
                rlv_ziYing.setVisibility(View.GONE);
                rlvRemen.setVisibility(View.VISIBLE);
                setZiYingOrReMenLine("1");
                rimenOrZiYing = "1";
            }
        });

        clReMen_Middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(getActivity(), "???????????????");
                rlv_ziYing.setVisibility(View.GONE);
                rlvRemen.setVisibility(View.VISIBLE);
                setZiYingOrReMenLine("1");
                rimenOrZiYing = "1";
            }
        });

        clZiYing_Middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(getActivity(), "???????????????");
                rlv_ziYing.setVisibility(View.VISIBLE);
                rlvRemen.setVisibility(View.GONE);
                setZiYingOrReMenLine("0");
                rimenOrZiYing = "0";
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rlv_ziYing.addItemDecoration(new GridAverageUIDecoration(14, 10));
        rlv_ziYing.setLayoutManager(layoutManager);
        homeZiYingAdapter = new HomeZiYingAdapter(R.layout.item_home_ziying, ziYingListBean);
        rlv_ziYing.setAdapter(homeZiYingAdapter);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 2);
        rlvRemen.addItemDecoration(new GridAverageUIDecoration(9, 10));
        rlvRemen.setLayoutManager(layoutManager1);
        homeReMenAdapter = new HomeReMenAdapter(R.layout.item_home_remen, remenListBean);
        rlvRemen.setAdapter(homeReMenAdapter);

        ivGouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GouWuCheActivity.actionStart(getActivity());
            }
        });

        view.setClickable(true);// ??????????????????????????????fragment??????????????????????????????
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
        smartRefreshLayout.setEnableLoadMore(false);
        unbinder = ButterKnife.bind(this, view);
        iv_home_xiaoxi = view.findViewById(R.id.iv_home_xiaoxi);

        iv_home_xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(getActivity(), "????????????????????????????????????");
            }
        });

        ImageView ivSaoMa = view.findViewById(R.id.iv_saoma);
        ivSaoMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CrashReport.testJavaCrash();
                RxPermissions rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) { // ???android 6.0?????????????????????true
                            ScanActivity.actionStart(getActivity());
                        } else {
                            Toast.makeText(getActivity(), "??????????????????????????????????????????????????????????????????????????????", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        GridLayoutManager gridLayoutManagerChi_He = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        chiHeWanLeList.setLayoutManager(gridLayoutManagerChi_He);
        chiHeWanLeListAdapter = new ChiHeWanLeListAdapter(R.layout.item_chihewanle, chiHeWanLeListBeans);
        chiHeWanLeListAdapter.openLoadAnimation();//?????????????????????
        chiHeWanLeList.setAdapter(chiHeWanLeListAdapter);

        shengHuoListFuc();
        GridLayoutManager gridLayoutManagerZHiKong = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        zhiKongList.setLayoutManager(gridLayoutManagerZHiKong);
        zhiKongListAdapter = new ZhiKongListAdapter(R.layout.item_zhikong, intellectListBeanList);
        zhiKongListAdapter.openLoadAnimation();//?????????????????????
        zhiKongList.setAdapter(zhiKongListAdapter);
        zhiKongListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        /**
                         *  1.???????????? 2.??????3.?????? 4.?????? 5.????????????
                         */
                        Home.DataBean.IntellectListBean intellectListBean = (Home.DataBean.IntellectListBean) adapter.getData().get(position);
                        if (intellectListBean.getId().equals("1")) {
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_ZHINENGJIAJU;
                            RxBus.getDefault().sendRx(n);
                            UIHelper.ToastMessage(getActivity(), "");
                        } else if (intellectListBean.getId().equals("2")) {
                            SheBeiLieBiaoActivity.actionStart(getActivity(), "1");
                        } else if (intellectListBean.getId().equals("3")) {
                            SheBeiLieBiaoActivity.actionStart(getActivity(), "6");
                        } else if (intellectListBean.getId().equals("4")) {//??????
                            SheBeiLieBiaoActivity.actionStart(getActivity(), "5");
                        } else if (intellectListBean.getId().equals("5")) {//????????????
                            UIHelper.ToastMessage(getActivity(), "?????????,????????????");
                        }
                        break;
                }
            }
        });
        chiHeWanLeListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {

                switch (view.getId()) {
                    case R.id.constrain:
                        /**
                         * iconList
                         * ???????????????	id	??????id   1.?????? 2.?????? 3.?????? 4.???????????? 5.?????? 6.??????
                         * 7.????????? 8.?????? 9.??????/?????? 10?????? 14 ??????
                         */
                        //??????????????????
                        if (chiHeWanLeListAdapter.getData().get(position).getId().equals("6")) {
                            String jingdu = PreferenceHelper.getInstance(getActivity()).getString(JINGDU, "0X11");
                            String weidu = PreferenceHelper.getInstance(getActivity()).getString(WEIDU, "0X11");
                            if (!jingdu.equals("0X11")) {
                                String str = chiHeWanLeListAdapter.getData().get(position).getHref_url() + "?i=" + JiaMiToken + "&" + "gps_x=" + weidu + "&" + "gps_y=" + jingdu;
                                TuanYouWebView.actionStart(getActivity(), str);
                            } else {
                                choosePostion = position;
                                if (chiHeWanLeListAdapter.getData().get(position).getId().equals("6")) {
                                    String str = chiHeWanLeListAdapter.getData().get(position).getHref_url() + "?i=" + JiaMiToken + "&" + "gps_x=45.666043" + "&" + "gps_y=126.605713";
                                    TuanYouWebView.actionStart(getActivity(), str);
                                    Toast.makeText(getActivity(), "???????????????????????????????????????", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else if (chiHeWanLeListAdapter.getData().get(position).getId().equals("11")) {
                            TuanYouList.actionStart(getActivity());
                        } else if (chiHeWanLeListAdapter.getData().get(position).getId().equals("14")) {
                            DefaultX5WebView_HaveNameActivity.actionStart(getActivity(), chiHeWanLeListAdapter.getData().get(position).getHref_url(), chiHeWanLeListAdapter.getData().get(position).getName());
                        } else if (chiHeWanLeListAdapter.getData().get(position).getId().equals("9")) {
                            TuanGouShangJiaListActivity.actionStart(getActivity(), chiHeWanLeListAdapter.getData().get(position).getId());
                        } else {
                            TuanGouShangJiaListActivity.actionStart(getActivity(), chiHeWanLeListAdapter.getData().get(position).getId());

                        }
                }
            }
        });

        GridItemDecoration divider = new GridItemDecoration.Builder(getActivity())
                .setHorizontal(R.dimen.default_divider_padding_5dp)
                .setVertical(R.dimen.default_divider_padding_5dp)
                .setColorResource(R.color.white)
                .build();

        hotLRecyclerViewAdapter = new LRecyclerViewAdapter(hotGoodsAdapter);
        directLRecyclerViewAdapter = new LRecyclerViewAdapter(directAdapter);

        //?????????????????????
        banner.setImageLoader(new Radius_GlideImageLoader());
        bannerXiuPeiChang.setImageLoader(new Radius_XiuPeiChangImageLoader());
        getData();
        hotLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TuanGouShangJiaListActivity.actionStart(getActivity(), "7");
            }
        });


        homeZiYingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZiJianShopMallDetailsActivity.actionStart(getActivity(), ziYingListBean.get(position).getShop_product_id(), ziYingListBean.get(position).getWares_id());
            }
        });

        homeReMenAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZiJianShopMallDetailsActivity.actionStart(getActivity(), remenListBean.get(position).getShop_product_id(), remenListBean.get(position).getWares_id());
            }
        });

        tianMaoOrTaoBao.setOnClickListener(this);
        ivDaLiBao.setOnClickListener(this);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DALIBAO_SUCCESS) {
                    ivDaLiBao.setVisibility(View.GONE);
                }
            }
        }));

        setZiYingOrReMenLine("0");
    }

    private void shengHuoListFuc() {
        GridLayoutManager gridLayoutManagerZHiKong = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        shengHuoList.setLayoutManager(gridLayoutManagerZHiKong);
        shengHuoListAdapter = new ShengHuoListAdapter(R.layout.item_zhikong, lifeListBeans);
        shengHuoListAdapter.openLoadAnimation();//?????????????????????
        shengHuoList.setAdapter(shengHuoListAdapter);
        shengHuoListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        String service_type = lifeListBeans.get(position).service_type;
                        switch (service_type) {
                            case "6":
                            case "11":
                            case "8":
                            case "3":
                                GongJiangLieBiaoNewActivity.actionStart(getActivity(), service_type);
                                break;
                            case "31"://
                                TongChengMainActivity.actionStart(getActivity());
                                break;
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).statusBarColor(R.color.white).init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    String taobaoPicture, jingdongPicture, pinduoduoPicture;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_tianmao_or_taobao:
                TaoBao_Jd_PinDuoDuoActivity.actionStart(getActivity(), taobaoPicture, jingdongPicture, pinduoduoPicture);
                break;
            case R.id.iv_dalibao:
                DaLiBaoActivity.actionStart(getActivity());
                break;
        }
    }

    public static String JiaMiToken;
    public static List<String> items = new ArrayList<>();
    public static List<Object> items_xiupeichang = new ArrayList<>();
    LordingDialog lordingDialog;

    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04131");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("gps_x", PreferenceHelper.getInstance(getActivity()).getString(WEIDU, ""));
        map.put("gps_y", PreferenceHelper.getInstance(getActivity()).getString(JINGDU, ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<Home.DataBean>>post(HOME_PICTURE)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Home.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Home.DataBean>> response) {
                        Logger.d(gson.toJson(response.body()));
                        if (smartRefreshLayout != null) {
                            smartRefreshLayout.setEnableRefresh(true);
                            smartRefreshLayout.finishRefresh();
                            smartRefreshLayout.setEnableLoadMore(false);
                        }

                        remenListBean = response.body().data.get(0).getIndexShowList();
                        groupList = response.body().data.get(0).getShopList();
                        ziYingListBean = response.body().data.get(0).getProShowList();
                        JiaMiToken = response.body().data.get(0).getI();

                        homeZiYingAdapter.setNewData(ziYingListBean);
                        homeZiYingAdapter.notifyDataSetChanged();

                        homeReMenAdapter.setNewData(remenListBean);
                        homeReMenAdapter.notifyDataSetChanged();
                        hotLRecyclerViewAdapter.notifyDataSetChanged();

                        items = new ArrayList<>();
                        if (response.body().data != null) {
                            for (int i = 0; i < response.body().data.get(0).getBannerList().size(); i++) {
                                items.add(response.body().data.get(0).getBannerList().get(i).getImg_url());
                            }
                        }

                        if (banner != null) {
                            //??????????????????
                            banner.setImages(items);
                            //banner?????????????????????????????????????????????
                            banner.start();
                            banner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    if (response.body().data.get(0).getBannerList().get(position).getRotation_img_type().equals("1")) {
                                        ZiJianShopMallDetailsActivity.actionStart(getActivity(), response.body().data.get(0).getBannerList().get(position).getShop_product_id(), response.body().data.get(0).getBannerList().get(position).getWares_id());
                                    } else if (response.body().data.get(0).getBannerList().get(position).getRotation_img_type().equals("2")) {
                                        // startActivity(new Intent(getActivity(), WebViewActivity.class).putExtra("url", response.body().data.get(0).getBannerList().get(position).getHtml_url()));
                                        DefaultX5WebView_HaveNameActivity.actionStart(getActivity(), response.body().data.get(0).getBannerList().get(position).getHtml_url(), "????????????");
                                    } else if (response.body().data.get(0).getBannerList().get(position).getRotation_img_type().equals("3")) {
                                        DaLiBaoActivity.actionStart(getActivity());
                                    }
                                }
                            });
                        }

                        items_xiupeichang = new ArrayList<>();

                        items_xiupeichang.add(R.mipmap.banner_xiupeichang_1);
                        items_xiupeichang.add(R.mipmap.banner_xiupeichang_2);

                        if (bannerXiuPeiChang != null) {
                            //??????????????????
                            bannerXiuPeiChang.setImages(items_xiupeichang);
                            //banner?????????????????????????????????????????????
                            bannerXiuPeiChang.start();
                            bannerXiuPeiChang.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    TuanGouShangJiaListActivity.actionStart(getActivity(), "7");
                                }
                            });
                        }

                        intellectListBeanList = new ArrayList<>();
                        lifeListBeans = new ArrayList<>();
                        chiHeWanLeListBeans = new ArrayList<>();
                        //??????????????????????????????
                        intellectListBeanList.addAll(response.body().data.get(0).getIntellectList());
                        if (response.body().data.get(0).lifeList != null) {
                            lifeListBeans.addAll(response.body().data.get(0).lifeList);
                        } else {
                            llShengHuo.setVisibility(View.GONE);
                        }
                        if (response.body().data.get(0).getIconList() != null) {
                            chiHeWanLeListBeans.addAll(response.body().data.get(0).getIconList());
                        }
                        chiHeWanLeListAdapter.setNewData(chiHeWanLeListBeans);
                        zhiKongListAdapter.setNewData(intellectListBeanList);
                        shengHuoListAdapter.setNewData(lifeListBeans);

                        chiHeWanLeListAdapter.notifyDataSetChanged();
                        zhiKongListAdapter.notifyDataSetChanged();

                        if (!response.body().data.get(0).getBuy_state().equals("1")) {
                            ivDaLiBao.setVisibility(View.GONE);
                            animationView.setVisibility(View.GONE);
                        } else {
                            ivDaLiBao.setVisibility(View.VISIBLE);
                            animationView.setVisibility(View.VISIBLE);
                        }

                        RoundedCorners roundedCorners = new RoundedCorners(1);
                        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                        //?????????
                        Glide.with(getActivity()).applyDefaultRequestOptions(GlideShowImageUtils.showBannerCelve()).load(response.body().data.get(0).getGift_img()).into(ivDaLiBao);
                        Glide.with(getActivity()).applyDefaultRequestOptions(options).load(response.body().data.get(0).getWaresTypeList().get(1).getImg_url()).into(ziYingZhiGon);
                        Glide.with(getActivity()).applyDefaultRequestOptions(options).load(response.body().data.get(0).getWaresTypeList().get(3).getImg_url()).into(reMenShangPin);

                        taobaoPicture = response.body().data.get(0).getTao_shop_img();
                        jingdongPicture = response.body().data.get(0).getJindong_shop_img();
                        pinduoduoPicture = response.body().data.get(0).getPin_shop_img();
                        ivZiJian.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ZiJianShopMallActivity.actionStart(getActivity());
                            }
                        });

                        ziYingZhiGon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                FenLeiThirdActivity.actionStart(getActivity(), "????????????", "2");
                            }
                        });
                        reMenShangPin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FenLeiThirdActivity.actionStart(getActivity(), "????????????", "1");
                            }
                        });

                        if (response.body().data.get(0).is_activity == null) {
                            return;
                        }
                        if (response.body().data.get(0).is_activity.equals("1")) {
                            return;
                        }
                        setHuoDong(response.body().data.get(0).getActivity());
                    }

                    @Override
                    public void onError(Response<AppResponse<Home.DataBean>> response) {
                        AlertUtil.t(getActivity(), response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<Home.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        lordingDialog = new LordingDialog(getActivity());
                        lordingDialog.setTextMsg("?????????????????????...");
                        lordingDialog.show();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        lordingDialog.dismiss();
                    }
                });
    }

    // public String shiFouYanzheng;
    public void getYaoQingNet(Context cnt) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04341");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(cnt).getAppToken());
        Log.i("taoken_gg", UserManager.getManager(cnt).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<TuiGuangMaModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(cnt)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuiGuangMaModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TuiGuangMaModel.DataBean>> response) {
                        if (response.body().data.get(0).getDisplay().equals("0")) {
                            //????????????
                            //shiFouYanzheng = "0";
                            PreferenceHelper.getInstance(getActivity()).putString(App.SHIFOUYOUSHANGJI, "0");
                        } else {
                            //?????????
                            //shiFouYanzheng = "1";
                            PreferenceHelper.getInstance(getActivity()).putString(App.SHIFOUYOUSHANGJI, "1");
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<TuiGuangMaModel.DataBean>> response) {
                        AlertUtil.t(cnt, response.getException().getMessage());
                    }
                });
    }


    /**
     * ????????????
     */
    AMapLocationListener gaodeDingWeiListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                StringBuffer sb = new StringBuffer();
                //errCode??????0????????????????????????????????????????????????????????????????????????????????????????????????
                if (location.getErrorCode() == 0) {
                    sb.append("????????????" + "\n");
                    sb.append("????????????: " + location.getLocationType() + "\n");
                    sb.append("???    ???    : " + location.getLongitude() + "\n");
                    sb.append("???    ???    : " + location.getLatitude() + "\n");
                    sb.append("???    ???    : " + location.getAccuracy() + "???" + "\n");
                    sb.append("?????????    : " + location.getProvider() + "\n");

                    sb.append("???    ???    : " + location.getSpeed() + "???/???" + "\n");
                    sb.append("???    ???    : " + location.getBearing() + "\n");
                    // ?????????????????????????????????????????????
                    sb.append("???    ???    : " + location.getSatellites() + "\n");
                    sb.append("???    ???    : " + location.getCountry() + "\n");
                    sb.append("???            : " + location.getProvince() + "\n");
                    sb.append("???            : " + location.getCity() + "\n");
                    sb.append("???????????? : " + location.getCityCode() + "\n");
                    sb.append("???            : " + location.getDistrict() + "\n");
                    sb.append("?????? ???   : " + location.getAdCode() + "\n");
                    sb.append("???    ???    : " + location.getAddress() + "\n");
                    sb.append("?????????    : " + location.getPoiName() + "\n");
                    //?????????????????????
                    sb.append("????????????: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");

                    PreferenceHelper.getInstance(getActivity()).putString(JINGDU, String.valueOf(location.getLongitude()));
                    PreferenceHelper.getInstance(getActivity()).putString(WEIDU, String.valueOf(location.getLatitude()));
                    PreferenceHelper.getInstance(getActivity()).putString(AppConfig.LOCATION_CITY_NAME, location.getCity());
                    PreferenceHelper.getInstance(getActivity()).putString(AppConfig.ADDRESS, location.getAddress());

                    stopLocation();
                } else {

                    //"gps_x=45.666043" + "&" + "gps_y=126.605713";
                    // x ?????? y ??????
                    PreferenceHelper.getInstance(getActivity()).putString(WEIDU, "45.666043");
                    PreferenceHelper.getInstance(getActivity()).putString(JINGDU, "126.605713");
                    PreferenceHelper.getInstance(getActivity()).putString(AppConfig.LOCATION_CITY_NAME, "?????????");
                    //????????????
                    sb.append("????????????" + "\n");
                    sb.append("?????????:" + location.getErrorCode() + "\n");
                    sb.append("????????????:" + location.getErrorInfo() + "\n");
                    sb.append("????????????:" + location.getLocationDetail() + "\n");
                }
                sb.append("***??????????????????***").append("\n");
                sb.append("* WIFI?????????").append(location.getLocationQualityReport().isWifiAble() ? "??????" : "??????").append("\n");
                sb.append("* GPS?????????").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
                sb.append("* GPS?????????").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
                sb.append("* ???????????????" + location.getLocationQualityReport().getNetworkType()).append("\n");
                sb.append("* ???????????????" + location.getLocationQualityReport().getNetUseTime()).append("\n");
                sb.append("****************").append("\n");
                //???????????????????????????
                sb.append("????????????: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //?????????????????????
                String result = sb.toString();
                Log.i("Location_result", result);
            } else {
                PreferenceHelper.getInstance(getActivity()).putString(WEIDU, "45.666043");
                PreferenceHelper.getInstance(getActivity()).putString(JINGDU, "126.605713");
                PreferenceHelper.getInstance(getActivity()).putString(AppConfig.LOCATION_CITY_NAME, "?????????");
                Log.i("Location_result", "????????????");
            }
        }
    };

    /**
     * ??????GPS??????????????????
     *
     * @param statusCode GPS?????????
     * @return
     */
    private String getGPSStatusString(int statusCode) {
        String str = "";
        switch (statusCode) {
            case AMapLocationQualityReport.GPS_STATUS_OK:
                str = "GPS????????????";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPROVIDER:
                str = "???????????????GPS Provider???????????????GPS??????";
                break;
            case AMapLocationQualityReport.GPS_STATUS_OFF:
                str = "GPS?????????????????????GPS?????????????????????";
                break;
            case AMapLocationQualityReport.GPS_STATUS_MODE_SAVING:
                str = "?????????????????????????????????GPS???????????????????????????GPS????????????????????????????????????";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPERMISSION:
                str = "??????GPS???????????????????????????gps????????????";
                break;
        }
        return str;
    }

    /**
     * ?????????????????????
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//????????????????????????????????????????????????????????????????????????????????????????????????????????????
        mOption.setGpsFirst(false);//?????????????????????gps??????????????????????????????????????????????????????
        mOption.setHttpTimeOut(30000);//???????????????????????????????????????????????????30?????????????????????????????????
        mOption.setInterval(2000);//???????????????????????????????????????2???
        mOption.setNeedAddress(true);//????????????????????????????????????????????????????????????true
        mOption.setOnceLocation(false);//?????????????????????????????????????????????false
        mOption.setOnceLocationLatest(false);//???????????????????????????wifi??????????????????false.???????????????true,?????????????????????????????????????????????????????????
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//????????? ????????????????????????????????????HTTP??????HTTPS????????????HTTP
        mOption.setSensorEnable(false);//????????????????????????????????????????????????false
        mOption.setWifiScan(true); //???????????????????????????wifi??????????????????true??????????????????false??????????????????????????????????????????????????????????????????????????????????????????????????????
        mOption.setLocationCacheEnable(true); //???????????????????????????????????????????????????true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//??????????????????????????????????????????????????????????????????????????????????????????????????????
        return mOption;
    }

    /**
     * ???????????????
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private void initLocation() {
        //?????????client
        locationClient = new AMapLocationClient(getActivity().getApplicationContext());
        locationOption = getDefaultOption();
        //??????????????????
        locationClient.setLocationOption(locationOption);
        // ??????????????????
        locationClient.setLocationListener(gaodeDingWeiListener);
    }

    /**
     * ????????????
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //????????????????????????????????????????????????
        //resetOption();
        // ??????????????????
        locationClient.setLocationOption(locationOption);
        // ????????????
        locationClient.startLocation();
    }

    /**
     * ????????????
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // ????????????
        locationClient.stopLocation();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    /**
     * is_activity	?????????????????? 1.????????? 2.?????????
     * img_url	???url
     * img_width	??????
     * img_height	??????
     * html_url	????????????url???app??????
     * activity_type_id	?????? 1.?????? 2.??????
     * wares_id	??????id
     * shop_product_id	??????id
     * is_share	???????????? 1.?????? 2.?????????
     * share_title	????????????
     * share_detail	????????????
     * share_url	????????????
     * share_img	????????????
     */
    private String strFirst = "0";//0????????? 1?????????

    private void setHuoDong(List<Home.DataBean.activity> activity) {

        if (activity.size() == 0) {
            return;
        }
        if (strFirst.equals("1")) {
            return;
        }
        strFirst = "1";


//      HuoDongTanCengActivity.actionStart(getActivity(), activity);


    }

    //????????????viewline
    private String strViewLine; // 0????????? 1??????

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        int[] location = new int[2];
        clZiYing_Middle.getLocationOnScreen(location);
        int locationY = location[1];
        // Log.e("locationY", locationY + " " + "topHeight????????????" + topHeight);

        if (locationY <= topHeight && (topPanel.getVisibility() == View.GONE || topPanel.getVisibility() == View.INVISIBLE)) {
            topPanel.setVisibility(View.VISIBLE);
            tvRemTop.setVisibility(View.GONE);
            tvZiYingTop.setVisibility(View.GONE);
            //  llBackground_Top.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            clReMen_Top.setBackgroundResource(R.color.white);
            clZiYing_Top.setBackgroundResource(R.color.white);
            strViewLine = "1";
            //  viewLineTop.setVisibility(View.VISIBLE);
            rl_bottom.setVisibility(View.VISIBLE);

        }

        if (locationY > topHeight && topPanel.getVisibility() == View.VISIBLE) {
            topPanel.setVisibility(View.GONE);
            tvRemTop.setVisibility(View.VISIBLE);
            tvZiYingTop.setVisibility(View.VISIBLE);
            //llBackground_Top.setBackgroundColor(getActivity().getResources().getColor(R.color.grayfff5f5f5));
            clReMen_Top.setBackgroundResource(R.color.grayfff5f5f5);
            clZiYing_Top.setBackgroundResource(R.color.grayfff5f5f5);
            strViewLine = "0";
            // viewLineTop.setVisibility(View.GONE);
            rl_bottom.setVisibility(View.GONE);

        }
    }


//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        Rect frame = new Rect();
//        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        int statusBarHeight = frame.top;//???????????????
//
//        int titleBarHeight = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();//???????????????
//        topHeight = titleBarHeight + statusBarHeight;
//    }


    /**
     * private TextView tvZiYingTop, tvRemTop, tvZiYingZhiGongTop, tvReMenShangPinTop;
     * private TextView tvZiYingMiddle, tvReMenMiddle, tvZiYingZhiGongMiddle, tvReMenShangPinMiddle;
     */
    //0 ?????? 1 ??????
    private void setZiYingOrReMenLine(String remenOrZiYing) {

        if (remenOrZiYing.equals("0")) {

            tvZiYingTop.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));
            tvZiYingZhiGongTop.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));

            tvZiYingMiddle.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));
            tvZiYingZhiGongMiddle.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));


            tvRemTop.setTextColor(getActivity().getResources().getColor(R.color.black_666666));
            tvReMenShangPinTop.setTextColor(getActivity().getResources().getColor(R.color.black_333333));

            tvReMenMiddle.setTextColor(getActivity().getResources().getColor(R.color.black_666666));
            tvReMenShangPinMiddle.setTextColor(getActivity().getResources().getColor(R.color.black_333333));
//
//            if (strViewLine.equals("0")) {
//                viewLineTop.setVisibility(View.GONE);
//                remenViewLineTop.setVisibility(View.GONE);
//            } else {
//               viewLineTop.setVisibility(View.VISIBLE);
//                remenViewLineTop.setVisibility(View.GONE);
//            }

            viewLineTop.setVisibility(View.VISIBLE);
            remenViewLineTop.setVisibility(View.GONE);

            tvZiYingMiddle.setBackgroundResource(R.drawable.bg_color_fc0100_1a);
            tvReMenMiddle.setBackgroundResource(0);
        } else {


            tvZiYingTop.setTextColor(getActivity().getResources().getColor(R.color.black_666666));
            tvZiYingZhiGongTop.setTextColor(getActivity().getResources().getColor(R.color.black_333333));
            tvZiYingMiddle.setTextColor(getActivity().getResources().getColor(R.color.black_666666));
            tvZiYingZhiGongMiddle.setTextColor(getActivity().getResources().getColor(R.color.black_333333));


            tvRemTop.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));
            tvReMenShangPinTop.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));

            tvReMenMiddle.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));
            tvReMenShangPinMiddle.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));

            tvReMenMiddle.setBackgroundResource(R.drawable.bg_color_fc0100_1a);
            tvZiYingMiddle.setBackgroundResource(0);
//            if (strViewLine.equals("0")) {
//                viewLineTop.setVisibility(View.GONE);
//                remenViewLineTop.setVisibility(View.GONE);
//            } else {
//                viewLineTop.setVisibility(View.GONE);
//                remenViewLineTop.setVisibility(View.VISIBLE);
//            }


            viewLineTop.setVisibility(View.GONE);
            remenViewLineTop.setVisibility(View.VISIBLE);


        }
    }
}


