package com.yiyang.cn.activity.xiupeichang;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.LatLng;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuangou.TuanGouMaiDanActivity;
import com.yiyang.cn.activity.tuangou.TuanGouShengChengDingDanActivity;
import com.yiyang.cn.activity.xiupeichang.adapter.XiupeichangFuwuAdapter;
import com.yiyang.cn.activity.xiupeichang.adapter.XiupeichangPingjiaAdapter;
import com.yiyang.cn.activity.xiupeichang.model.XpcDetailsModel;
import com.yiyang.cn.activity.xiupeichang.view.XiupeichangTagsView;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.Radius_GlideImageLoader;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.util.NavigationUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import static com.yiyang.cn.app.App.JINGDU;
import static com.yiyang.cn.app.App.WEIDU;
import static com.yiyang.cn.get_net.Urls.LIBAOLIST;

public class XiupeichangShangActivity extends BaseActivity {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_yingye_time)
    TextView tv_yingye_time;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rating_bar)
    AppCompatRatingBar rating_bar;
    @BindView(R.id.ll_lianxi)
    LinearLayout ll_lianxi;
    @BindView(R.id.tv_juli)
    TextView tv_juli;
    @BindView(R.id.ll_daohang)
    LinearLayout ll_daohang;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.ll_tag)
    LinearLayout ll_tag;
    @BindView(R.id.bt_maidan)
    TextView bt_maidan;
    @BindView(R.id.tv_select_fuwu)
    TextView tv_select_fuwu;
    @BindView(R.id.vv_select_fuwu)
    View vv_select_fuwu;
    @BindView(R.id.rl_select_fuwu)
    RelativeLayout rl_select_fuwu;
    @BindView(R.id.tv_select_pingjia)
    TextView tv_select_pingjia;
    @BindView(R.id.vv_select_pingjia)
    View vv_select_pingjia;
    @BindView(R.id.rl_select_pingjia)
    RelativeLayout rl_select_pingjia;
    @BindView(R.id.rv_fuwu)
    RecyclerView rv_fuwu;
    @BindView(R.id.rv_pingjia)
    RecyclerView rv_pingjia;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_pinglun)
    TextView ll_pinglun;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.ll_maidan)
    RelativeLayout llMaidan;
    @BindView(R.id.ll_fuwu_pingjia)
    LinearLayout ll_fuwu_pingjia;
    @BindView(R.id.ll_xiaohuoban_pingjia)
    LinearLayout ll_xiaohuoban_pingjia;

    private String inst_id;
    private XpcDetailsModel.DataBean detailsModel;
    private String inst_phone;
    private String lat_x;
    private String lon_y;

    private List<XpcDetailsModel.DataBean.TaocanListBean> fuwuModels = new ArrayList<>();
    private XiupeichangFuwuAdapter fuwuAdapter;
    private List<XpcDetailsModel.DataBean.PinglunListBean> pingjiaModels = new ArrayList<>();
    private XiupeichangPingjiaAdapter pingjiaAdapter;
    private String inst_name;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_xiupeichang_shangjia;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("修配厂");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String inst_id) {
        Intent intent = new Intent(context, XiupeichangShangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("inst_id", inst_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initAdapter();
        initSM();
        getNet();
//        addTag();
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

    private void initData() {
        inst_id = getIntent().getStringExtra("inst_id");
//        inst_id = "229";
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "08032");
        map.put("key", Urls.key);
        map.put("y", PreferenceHelper.getInstance(mContext).getString(JINGDU, "0X11"));
        map.put("x", PreferenceHelper.getInstance(mContext).getString(WEIDU, "0X11"));
        map.put("inst_id", inst_id);

        Gson gson = new Gson();
        OkGo.<AppResponse<XpcDetailsModel.DataBean>>post(LIBAOLIST)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<XpcDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<XpcDetailsModel.DataBean>> response) {
                        detailsModel = response.body().data.get(0);
                        rating_bar.setNumStars(Y.getInt(detailsModel.getInst_number()));
                        inst_name = detailsModel.getInst_name();
                        tv_name.setText(inst_name);
                        tv_address.setText(detailsModel.getAddr_all());
                        tv_juli.setText(detailsModel.getMeter());
                        inst_phone = detailsModel.getInst_phone();
                        lat_x = detailsModel.getX();
                        lon_y = detailsModel.getY();
                        tv_yingye_time.setText("营业时间" + detailsModel.getTime());

                        List<XpcDetailsModel.DataBean.LunboListBean> lunboList = detailsModel.getLunboList();
                        List<String> imgs = new ArrayList<>();
                        for (int i = 0; i < lunboList.size(); i++) {
                            imgs.add(lunboList.get(i).getImg_url());
                        }
                        banner.setImageLoader(new Radius_GlideImageLoader());
                        banner.setImages(imgs);
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();
                        banner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {

                            }
                        });

                        fuwuModels = detailsModel.getTaocanList();
                        if (fuwuModels.size() != 0) {
                            XpcDetailsModel.DataBean.TaocanListBean taocanListBean = fuwuModels.get(fuwuModels.size() - 1);
                            taocanListBean.setHideLine(true);
                            fuwuModels.set(fuwuModels.size() - 1, taocanListBean);
                        }
                        fuwuAdapter.setNewData(fuwuModels);
                        fuwuAdapter.notifyDataSetChanged();


                        pingjiaModels = detailsModel.getPinglunList();
                        if (pingjiaModels.size() != 0) {
                            rv_pingjia.setVisibility(View.VISIBLE);
                            ll_pinglun.setVisibility(View.VISIBLE);
                            ll_xiaohuoban_pingjia.setVisibility(View.VISIBLE);

                            XpcDetailsModel.DataBean.PinglunListBean pinglunListBean = pingjiaModels.get(pingjiaModels.size() - 1);
                            pinglunListBean.setHideLine(true);
                            pingjiaModels.set(pingjiaModels.size() - 1, pinglunListBean);

                            pingjiaAdapter.setNewData(pingjiaModels);
                            pingjiaAdapter.notifyDataSetChanged();
                        } else {
                            rv_pingjia.setVisibility(View.GONE);
                            ll_pinglun.setVisibility(View.GONE);
                            ll_xiaohuoban_pingjia.setVisibility(View.GONE);
                        }

                        if (pingjiaModels.size() == 0 && fuwuModels.size() == 0) {
                            ll_fuwu_pingjia.setVisibility(View.GONE);
                        } else {
                            ll_fuwu_pingjia.setVisibility(View.VISIBLE);
                            if (fuwuModels.size() == 0) {
                                rl_select_fuwu.setVisibility(View.GONE);
                                rl_select_pingjia.setVisibility(View.VISIBLE);
                                clickPingjia();
                            } else if (pingjiaModels.size()==0){
                                rl_select_fuwu.setVisibility(View.VISIBLE);
                                rl_select_pingjia.setVisibility(View.GONE);
                                clickFuwu();
                            }else {
                                rl_select_fuwu.setVisibility(View.VISIBLE);
                                rl_select_pingjia.setVisibility(View.VISIBLE);
                                clickFuwu();
                            }
                        }
                    }

                    @Override
                    public void onStart(Request<AppResponse<XpcDetailsModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    private void addTag() {
        List<String> tags = new ArrayList<>();
        tags.add("修配门店");
        tags.add("8.5折优惠");

        ll_tag.removeAllViews();
        for (int i = 0; i < tags.size(); i++) {
            XiupeichangTagsView tagsView = new XiupeichangTagsView(mContext);
            tagsView.setTag(tags.get(i));
            ll_tag.addView(tagsView);
        }
    }

    private void initAdapter() {
        fuwuAdapter = new XiupeichangFuwuAdapter(R.layout.item_xiupeichang_fuwu, fuwuModels);
        rv_fuwu.setLayoutManager(new LinearLayoutManager(mContext));
        rv_fuwu.setAdapter(fuwuAdapter);
        fuwuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (fuwuModels != null && fuwuModels.size() > position) {
                    XpcDetailsModel.DataBean.TaocanListBean dataBean = fuwuModels.get(position);
                    String inst_id = dataBean.getInst_id();
                    String wares_id = dataBean.getWares_id();
                    String shop_money_now = dataBean.getShop_money_now();
                    String img_url = dataBean.getImg_url();
                    String shop_title = dataBean.getShop_title();
                    TuanGouShengChengDingDanActivity.actionStart(mContext, inst_id, shop_money_now, img_url, shop_title, "7", wares_id);
                }
            }
        });


        pingjiaAdapter = new XiupeichangPingjiaAdapter(R.layout.item_xiupeichang_pingjia, pingjiaModels);
        rv_pingjia.setLayoutManager(new LinearLayoutManager(mContext));
        rv_pingjia.setAdapter(pingjiaAdapter);
    }

    @OnClick({R.id.bt_maidan, R.id.rl_select_fuwu, R.id.rl_select_pingjia, R.id.ll_lianxi, R.id.ll_daohang,R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_maidan:
                TuanGouMaiDanActivity.actionStart(mContext, inst_id, "7");
                break;
            case R.id.rl_select_fuwu:
                clickFuwu();
                break;
            case R.id.rl_select_pingjia:
                clickPingjia();
                break;
            case R.id.ll_lianxi:
                clickLianxi();
                break;
            case R.id.ll_daohang:
            case R.id.tv_address:
                clickDaohang();
                break;
        }
    }

    private void clickDaohang() {
        if (TextUtils.isEmpty(lat_x) || TextUtils.isEmpty(lon_y)) {
            return;
        }

        String items[] = {"高德地图导航", "百度地图导航"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, items, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        try {
                            Double x = Double.valueOf(lat_x);
                            Double y = Double.valueOf(lon_y);
                            LatLng latLng = new LatLng(x, y);
                            NavigationUtils.Navigation(latLng);
                        } catch (Exception e) {
                            UIHelper.ToastMessage(MyApplication.getApp().getApplicationContext(), "请下载高德地图后重新尝试", Toast.LENGTH_SHORT);
                        }
                        break;
                    case 1:
                        try {
                            Double x = Double.valueOf(lat_x);
                            Double y = Double.valueOf(lon_y);
                            LatLng latLng = new LatLng(x, y);
                            NavigationUtils.NavigationBaidu(latLng,inst_name);
                        } catch (Exception e) {
                            UIHelper.ToastMessage(MyApplication.getApp().getApplicationContext(), "请下载百度地图后重新尝试", Toast.LENGTH_SHORT);
                        }
                        break;
                }
                dialog.dismiss();
            }
        });
    }

    private void clickLianxi() {
        if (TextUtils.isEmpty(inst_phone)) {
            Y.t("暂无商家联系方式");
            return;
        }

        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + inst_phone);
        intent.setData(data);
        startActivity(intent);
    }

    private void clickFuwu() {
        vv_select_fuwu.setVisibility(View.VISIBLE);
        vv_select_pingjia.setVisibility(View.INVISIBLE);

        tv_select_fuwu.setTextColor(Color.parseColor("#FC0100"));
        tv_select_pingjia.setTextColor(Color.parseColor("#333333"));

//        scrollView.fullScroll(NestedScrollView.FOCUS_UP);
//        scrollView.scrollTo(0,tv_select_fuwu.getBottom());
        scrollView.scrollTo(0, llMaidan.getBottom());
    }

    private void clickPingjia() {
        vv_select_fuwu.setVisibility(View.INVISIBLE);
        vv_select_pingjia.setVisibility(View.VISIBLE);

        tv_select_fuwu.setTextColor(Color.parseColor("#333333"));
        tv_select_pingjia.setTextColor(Color.parseColor("#FC0100"));

        scrollView.fullScroll(NestedScrollView.FOCUS_DOWN);
    }
}
