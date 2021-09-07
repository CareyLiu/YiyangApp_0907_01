package com.yiyang.cn.activity.xin_tuanyou;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.DefaultX5WebView_HaveNameActivity;
import com.yiyang.cn.adapter.xin_tuanyou.KMAdapter;
import com.yiyang.cn.adapter.xin_tuanyou.PaiXuAdapter;
import com.yiyang.cn.adapter.xin_tuanyou.PinPaiAdapter;
import com.yiyang.cn.adapter.xin_tuanyou.XinTuanYouHomeListAdapter;
import com.yiyang.cn.adapter.xin_tuanyou.YouHaoAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.JiaYouFirstModel;
import com.yiyang.cn.model.OilArrayBean;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.GridSectionAverageUItemDecoration;
import com.yiyang.cn.util.GridSectionAverageUItemDecoration;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yiyang.cn.activity.xin_tuanyou.YouZhanDetailsActivity.isInstallApk;
import static com.yiyang.cn.get_net.Urls.HOME_PICTURE;

public class TuanYouList extends BaseActivity {


    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.tv_km)
    TextView tvKm;
    @BindView(R.id.tv_youhao)
    TextView tvYouhao;
    @BindView(R.id.tv_zhinengpaixu)
    TextView tvZhinengpaixu;
    @BindView(R.id.tv_all_pinpai)
    TextView tvAllPinpai;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.iv_km_down)
    ImageView ivKmDown;
    @BindView(R.id.iv_youhao_down)
    ImageView ivYouhaoDown;
    @BindView(R.id.iv_paixu_down)
    ImageView ivPaixuDown;
    @BindView(R.id.iv_all_pinpai_down)
    ImageView ivAllPinpaiDown;
    @BindView(R.id.cl_km)
    ConstraintLayout clKm;
    @BindView(R.id.cl_youhao)
    ConstraintLayout clYouhao;
    @BindView(R.id.cl_zhinengpaixu)
    ConstraintLayout clZhinengpaixu;
    @BindView(R.id.cl_all_pinpai)
    ConstraintLayout clAllPinpai;
    @BindView(R.id.cl_shaming)
    ConstraintLayout clShaming;
    @BindView(R.id.rlv_km_list)
    RecyclerView rlvKmList;
    @BindView(R.id.rlv_youhao_list)
    RecyclerView rlvYouhaoList;
    @BindView(R.id.rlv_paixu_list)
    RecyclerView rlvPaixuList;
    @BindView(R.id.rlv_pinpai_list)
    RecyclerView rlvPinpaiList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_cl_pinpai)
    View viewClPinpai;
    @BindView(R.id.iv_select_img)
    ImageView ivSelectImg;
    @BindView(R.id.ll_queren)
    LinearLayout llQueren;
    @BindView(R.id.cl_pinpai_erji)
    ConstraintLayout clPinpaiErji;
    @BindView(R.id.iv_order)
    ImageView ivOrder;


    private boolean kmFlag = false;
    private boolean youhaoFlag = false;
    private boolean zhinengPaixuFlag = false;
    private boolean allPinPaiFlag = false;
    String quanXuan = "1";//默认全选

    private String isPinPaiAllSelect = "1";//默认全选
    private List<String> kmList = new ArrayList<>();//多少千米
    private List<MyModel> myModels = new ArrayList<>();//我的类


    private List<OilArrayBean> oil_select_list = new ArrayList<>();//油耗
    private List<JiaYouFirstModel.DataBean.OrderListBean> order_list = new ArrayList<>();//排序
    private List<JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean> brand_list = new ArrayList<>();//品牌
    private List<JiaYouFirstModel.DataBean.DiatanceListBean> diatance_list = new ArrayList<>();//距离
    private Response<AppResponse<JiaYouFirstModel.DataBean>> response;

    private List<JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean> pinPaiChooseList = new ArrayList<>();//品牌
    /**
     * {
     * "code":"04245",
     * "key":"20180305124455yu",
     * "distance_type":"5",
     * "order_type":"1",
     * "oilNoType":"24",
     * "whether_all":"1",
     * "page_number":"1",
     * "x":"45.751592",
     * "y":"126.61836"
     * }
     */

    private String distance_type;
    private String order_type;
    private String oilNoType;//油号


    private String oiltype = "1";//油的类型

    private String oilName = "汽油";//

    private String whether_all;
    private int page_number = 0;
    private String x;
    private String y;

    private String brand_type;//品牌

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tuan_you_list);
        //setList();


        clAllPinpai.getBackground().setAlpha(25);
        clKm.getBackground().setAlpha(25);
        clYouhao.getBackground().setAlpha(25);
        clZhinengpaixu.getBackground().setAlpha(25);


        ivKmDown.setBackgroundResource(R.mipmap.down_black);
        ivYouhaoDown.setBackgroundResource(R.mipmap.down_black);
        ivPaixuDown.setBackgroundResource(R.mipmap.down_black);
        ivAllPinpaiDown.setBackgroundResource(R.mipmap.down_black);

        setValue();
        getData();
        setYouHaoList();//油耗
        setPinPaiList();//品牌
        setPaiXuList();//排序


        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page_number = 0;
                refreshLayout.setEnableLoadMore(true);
                getRefreshData();

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page_number++;
                getRefreshData();
            }
        });


        ivOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(TuanYouList.this, "订单页面");
                String url = "https://shop.hljsdkj.com/bdjy/orderList?token="+ UserManager.getManager(TuanYouList.this).getAppToken();
                DefaultX5WebView_HaveNameActivity.actionStart(TuanYouList.this,url,"加油订单");
            }
        });

    }

    private void setValue() {
        distance_type = "5";
        order_type = "1";
        oilNoType = "12";
        whether_all = "1";
        page_number = 0;
        x = PreferenceHelper.getInstance(TuanYouList.this).getString(App.WEIDU, "45.751592");
        y = PreferenceHelper.getInstance(TuanYouList.this).getString(App.JINGDU, "126.61836");

    }

    KMAdapter kmAdapter;//km adapter
    YouHaoAdapter youHaoAdapter;//油耗adapter
    PaiXuAdapter paiXuAdapter;//排序adapter
    PinPaiAdapter pinPaiAdapter;//品牌adapter

    private void setKmList() {

        for (int i = 0; i < 20; i++) {
            MyModel myModel = new MyModel();
            myModel.isSelect = "0";
            myModels.add(myModel);
        }

        kmAdapter = new KMAdapter(R.layout.item_km, diatance_list);

        rlvKmList.setLayoutManager(new GridLayoutManager(this, 4));
        //rlvKmList.addItemDecoration(new FourLieDecoration(this));
        rlvKmList.addItemDecoration(new GridSectionAverageUItemDecoration(10, 0, 20, 15));
        rlvKmList.setAdapter(kmAdapter);
        kmAdapter.notifyDataSetChanged();

        kmAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        for (int i = 0; i < kmAdapter.getData().size(); i++) {
                            diatance_list.get(i).setIsSelect("0");
                        }
                        diatance_list.get(position).setIsSelect("1");
                        distance_type = diatance_list.get(position).getId();
                        tvKm.setText(diatance_list.get(position).getName());
                        clKm.setVisibility(View.GONE);
                        kmFlag = false;
                        kmAdapter.notifyDataSetChanged();
                        page_number = 0;
                        getRefreshData();
                        break;
                }

            }
        });

    }

    private void setYouHaoList() {
        kmList.add("6km内");
        kmList.add("10km内");
        kmList.add("15km内");
        kmList.add("20km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");

        youHaoAdapter = new YouHaoAdapter(oil_select_list);
        rlvYouhaoList.setLayoutManager(new GridLayoutManager(this, 3));
        rlvYouhaoList.addItemDecoration(new GridSectionAverageUItemDecoration(14, 0, 20, 2));
        // rlvKmList.addItemDecoration(new GridSectionAverageUItemDecoration(10, 10, 20, 15));
        rlvYouhaoList.setAdapter(youHaoAdapter);
        youHaoAdapter.notifyDataSetChanged();

        youHaoAdapter.setOnItemChildClickListener(new com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(com.chad.library.adapter.base.BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        for (int i = 0; i < youHaoAdapter.getData().size(); i++) {

                            if (oil_select_list.get(i).isHeader) {

                            } else {
                                oil_select_list.get(i).setIsSelect("0");
                            }

                        }
                        oil_select_list.get(position).setIsSelect("1");


                        oiltype = oil_select_list.get(position).oil_type;
                        oilName = oil_select_list.get(position).oil_type_name;


                        tvYouhao.setText(oil_select_list.get(position).getName());


                        clYouhao.setVisibility(View.GONE);
                        youhaoFlag = false;
                        youHaoAdapter.notifyDataSetChanged();
                        page_number = 0;
                        getRefreshData();
                        break;
                }
            }
        });
    }

    private void setPaiXuList() {
        kmList.add("6km内");
        kmList.add("10km内");
        kmList.add("15km内");
        kmList.add("20km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");

        paiXuAdapter = new PaiXuAdapter(R.layout.item_km, order_list);
        rlvPaixuList.setLayoutManager(new GridLayoutManager(this, 4));
        //   rlvPaixuList.addItemDecoration(new FourLieDecoration(this));
        rlvPaixuList.addItemDecoration(new GridSectionAverageUItemDecoration(10, 10, 20, 15));
        rlvPaixuList.setAdapter(paiXuAdapter);
        paiXuAdapter.notifyDataSetChanged();
        paiXuAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


                switch (view.getId()) {
                    case R.id.constrain:
                        for (int i = 0; i < paiXuAdapter.getData().size(); i++) {
                            order_list.get(i).setIsSelect("0");
                        }
                        order_list.get(position).setIsSelect("1");
                        order_type = diatance_list.get(position).getId();
                        tvZhinengpaixu.setText(order_list.get(position).getName());
                        clZhinengpaixu.setVisibility(View.GONE);
                        zhinengPaixuFlag = false;
                        paiXuAdapter.notifyDataSetChanged();
                        page_number = 0;
                        getRefreshData();
                        break;
                }

            }

        });

    }


    private void setPinPaiList() {
        kmList.add("6km内");
        kmList.add("10km内");
        kmList.add("15km内");
        kmList.add("20km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");
        kmList.add("50km内");

        pinPaiAdapter = new PinPaiAdapter(R.layout.item_km, brand_list);
        rlvPinpaiList.setLayoutManager(new GridLayoutManager(this, 4));
        //rlvPinpaiList.addItemDecoration(new FourLieDecoration(this));
        rlvPinpaiList.addItemDecoration(new GridSectionAverageUItemDecoration(14, 10, 20, 15));
        rlvPinpaiList.setAdapter(pinPaiAdapter);
        pinPaiAdapter.notifyDataSetChanged();

        pinPaiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.constrain:

                        if (brand_list.get(position).getIsSelect().equals("1")) {
                            brand_list.get(position).setIsSelect("0");
                        } else {
                            brand_list.get(position).setIsSelect("1");
                        }

                        quanXuan = "1";
                        for (int i = 0; i < brand_list.size(); i++) {
                            if (brand_list.get(i).getIsSelect().equals("0")) {
                                quanXuan = "0";
                                break;
                            }

                        }


                        if (quanXuan.equals("0")) {
                            ivSelectImg.setBackgroundResource(R.mipmap.jyj_shaixuan_icon_weiquanxuan);
                        } else if (quanXuan.equals("1")) {

                            ivSelectImg.setBackgroundResource(R.mipmap.jyj_shaixuan_icon_quanxuan);
                        }


                        pinPaiAdapter.notifyDataSetChanged();
                        //  getRefreshData();


                        break;
                }
            }
        });

        llQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pinPaiChooseList.clear();
//                pinPaiChooseList.addAll(brand_list);
//
//                brand_list.clear();
                //brand_list.addAll(pinPaiChooseList);

                brand_type = "";
                for (int i = 0; i < brand_list.size(); i++) {

                    JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean brandArrayBean = new JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean();
                    brandArrayBean.setIsSelect(brand_list.get(i).getIsSelect());
                    brandArrayBean.setId(brand_list.get(i).getIsSelect());
                    brandArrayBean.setName(brand_list.get(i).getName());
                    pinPaiChooseList.add(brandArrayBean);


                    if (brand_list.get(i).getIsSelect().equals("1")) {
                        brand_type = brand_list.get(i).getName();
                    }
                }


                clAllPinpai.setVisibility(View.GONE);


                if (quanXuan.equals("0")) {

                    tvAllPinpai.setText("部分品牌");
                } else {
                    tvAllPinpai.setText("全部品牌");
                }

                if (quanXuan.equals("1")) {
                    whether_all = "1";
                    brand_type = "";
                } else {
                    whether_all = "2";
                }

                getRefreshData();
            }
        });

        ivSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanXuan.equals("1")) {

                    ivSelectImg.setBackgroundResource(R.mipmap.jyj_shaixuan_icon_weiquanxuan);

                    for (int i = 0; i < brand_list.size(); i++) {
                        brand_list.get(i).setIsSelect("0");
                    }
                    quanXuan = "0";

                    pinPaiAdapter.notifyDataSetChanged();
                } else if (quanXuan.equals("0")) {
                    for (int i = 0; i < brand_list.size(); i++) {
                        brand_list.get(i).setIsSelect("1");
                    }
                    ivSelectImg.setBackgroundResource(R.mipmap.jyj_shaixuan_icon_quanxuan);
                    pinPaiAdapter.notifyDataSetChanged();
                    quanXuan = "1";
                }
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tuan_you_list;
    }

    XinTuanYouHomeListAdapter xinTuanYouHomeListAdapter;
    List<String> list = new ArrayList<>();

    List<JiaYouFirstModel.DataBean.OilListBean> oil_list = new ArrayList<>();

    public void setList() {
        xinTuanYouHomeListAdapter = new XinTuanYouHomeListAdapter(R.layout.item_tuanyou_list, oil_list);
        rlvList.setLayoutManager(new LinearLayoutManager(this));
        rlvList.setAdapter(xinTuanYouHomeListAdapter);


        if (oil_list.size() == 0) {
            View view = View.inflate(TuanYouList.this, R.layout.empty_view, null);
            xinTuanYouHomeListAdapter.setEmptyView(view);
            xinTuanYouHomeListAdapter.notifyDataSetChanged();
        }
        xinTuanYouHomeListAdapter.notifyDataSetChanged();

        xinTuanYouHomeListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        YouZhanDetailsActivity.actionStart(TuanYouList.this, xinTuanYouHomeListAdapter.getData().get(position).getInst_id(), oil_list.get(position).getOilType(), oil_list.get(position).getOilNo(), oil_list.get(position).getDistance());
                        break;
                    case R.id.iv_daohang:
                        String jingdu = PreferenceHelper.getInstance(TuanYouList.this).getString(App.JINGDU, "0");
                        String weidu = PreferenceHelper.getInstance(TuanYouList.this).getString(App.WEIDU, "0");
                        checkGaodeMap(jingdu, weidu, xinTuanYouHomeListAdapter.getData().get(position).getAddr());
                        break;
                }
            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TuanYouList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("加油");
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

    @OnClick({R.id.tv_km, R.id.tv_youhao, R.id.tv_zhinengpaixu, R.id.cl_km, R.id.cl_shaming, R.id.cl_youhao, R.id.cl_zhinengpaixu, R.id.cl_all_pinpai, R.id.tv_all_pinpai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_km:

                clYouhao.setVisibility(View.GONE);
                clAllPinpai.setVisibility(View.GONE);
                clZhinengpaixu.setVisibility(View.GONE);

                tvYouhao.setTextColor(this.getResources().getColor(R.color.black_222222));
                tvAllPinpai.setTextColor(this.getResources().getColor(R.color.black_222222));
                tvZhinengpaixu.setTextColor(this.getResources().getColor(R.color.black_222222));


                ivYouhaoDown.setBackgroundResource(R.mipmap.down_black);
                ivAllPinpaiDown.setBackgroundResource(R.mipmap.down_black);
                ivPaixuDown.setBackgroundResource(R.mipmap.down_black);


                if (clKm.getVisibility() == View.VISIBLE) {
                    clKm.setVisibility(View.GONE);
                    ivKmDown.setBackgroundResource(R.mipmap.down_black);
                    tvKm.setTextColor(this.getResources().getColor(R.color.black_222222));
                } else {
                    clKm.setVisibility(View.VISIBLE);
                    tvKm.setTextColor(this.getResources().getColor(R.color.red_e1938));
                    ivKmDown.setBackgroundResource(R.mipmap.blackdown_red);
                }

                break;
            case R.id.tv_youhao:
                clKm.setVisibility(View.GONE);
                clAllPinpai.setVisibility(View.GONE);
                clZhinengpaixu.setVisibility(View.GONE);

                tvKm.setTextColor(this.getResources().getColor(R.color.black_222222));
                tvAllPinpai.setTextColor(this.getResources().getColor(R.color.black_222222));
                tvZhinengpaixu.setTextColor(this.getResources().getColor(R.color.black_222222));

                ivKmDown.setBackgroundResource(R.mipmap.down_black);
                ivPaixuDown.setBackgroundResource(R.mipmap.down_black);
                ivAllPinpaiDown.setBackgroundResource(R.mipmap.down_black);


                if (clYouhao.getVisibility() == View.VISIBLE) {
                    clYouhao.setVisibility(View.GONE);
                    ivYouhaoDown.setBackgroundResource(R.mipmap.down_black);
                    tvYouhao.setTextColor(this.getResources().getColor(R.color.black_222222));
                } else {
                    clYouhao.setVisibility(View.VISIBLE);
                    tvYouhao.setTextColor(this.getResources().getColor(R.color.red_e1938));
                    ivYouhaoDown.setBackgroundResource(R.mipmap.blackdown_red);
                }

                break;
            case R.id.tv_zhinengpaixu:

                clAllPinpai.setVisibility(View.GONE);
                clYouhao.setVisibility(View.GONE);
                clKm.setVisibility(View.GONE);

                tvKm.setTextColor(this.getResources().getColor(R.color.black_222222));
                tvAllPinpai.setTextColor(this.getResources().getColor(R.color.black_222222));
                tvYouhao.setTextColor(this.getResources().getColor(R.color.black_222222));


                ivKmDown.setBackgroundResource(R.mipmap.down_black);
                ivAllPinpaiDown.setBackgroundResource(R.mipmap.down_black);
                ivYouhaoDown.setBackgroundResource(R.mipmap.down_black);

                if (clZhinengpaixu.getVisibility() == View.VISIBLE) {
                    clZhinengpaixu.setVisibility(View.GONE);
                    ivPaixuDown.setBackgroundResource(R.mipmap.down_black);
                    tvZhinengpaixu.setTextColor(this.getResources().getColor(R.color.black_222222));
                } else {
                    clZhinengpaixu.setVisibility(View.VISIBLE);
                    tvZhinengpaixu.setTextColor(this.getResources().getColor(R.color.red_e1938));
                    ivPaixuDown.setBackgroundResource(R.mipmap.blackdown_red);

                }


                break;
            case R.id.tv_all_pinpai:

                clYouhao.setVisibility(View.GONE);
                clKm.setVisibility(View.GONE);
                clZhinengpaixu.setVisibility(View.GONE);

                tvKm.setTextColor(this.getResources().getColor(R.color.black_222222));
                tvZhinengpaixu.setTextColor(this.getResources().getColor(R.color.black_222222));
                tvYouhao.setTextColor(this.getResources().getColor(R.color.black_222222));

                ivKmDown.setBackgroundResource(R.mipmap.down_black);
                ivPaixuDown.setBackgroundResource(R.mipmap.down_black);
                ivYouhaoDown.setBackgroundResource(R.mipmap.down_black);

                if (clAllPinpai.getVisibility() == View.VISIBLE) {
                    clAllPinpai.setVisibility(View.GONE);
                    ivAllPinpaiDown.setBackgroundResource(R.mipmap.down_black);
                    tvAllPinpai.setTextColor(this.getResources().getColor(R.color.black_222222));
                    brand_list.clear();
                    //brand_list.addAll(pinPaiChooseList);

                    for (int i = 0; i < pinPaiChooseList.size(); i++) {

                        JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean brandArrayBean = new JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean();
                        brandArrayBean.setIsSelect(pinPaiChooseList.get(i).getIsSelect());
                        brandArrayBean.setId(pinPaiChooseList.get(i).getIsSelect());
                        brandArrayBean.setName(pinPaiChooseList.get(i).getName());
                        brand_list.add(brandArrayBean);
                    }
                    pinPaiAdapter.notifyDataSetChanged();


                } else {
                    clAllPinpai.setVisibility(View.VISIBLE);
                    tvAllPinpai.setTextColor(this.getResources().getColor(R.color.red_e1938));
                    ivAllPinpaiDown.setBackgroundResource(R.mipmap.blackdown_red);

                    quanXuan = "1";
                    for (int i = 0; i < brand_list.size(); i++) {
                        if (brand_list.get(i).getIsSelect().equals("0")) {
                            quanXuan = "0";
                            break;
                        }

                    }

                }

                if (quanXuan.equals("0")) {
                    ivSelectImg.setBackgroundResource(R.mipmap.jyj_shaixuan_icon_weiquanxuan);
                } else if (quanXuan.equals("1")) {
                    ivSelectImg.setBackgroundResource(R.mipmap.jyj_shaixuan_icon_quanxuan);
                }
                break;

            case R.id.cl_youhao:
                clYouhao.setVisibility(View.GONE);
                ivYouhaoDown.setBackgroundResource(R.mipmap.down_black);
                tvYouhao.setTextColor(this.getResources().getColor(R.color.black_222222));
                break;
            case R.id.cl_all_pinpai:
                clAllPinpai.setVisibility(View.GONE);
                ivAllPinpaiDown.setBackgroundResource(R.mipmap.down_black);
                tvAllPinpai.setTextColor(this.getResources().getColor(R.color.black_222222));

                brand_list.clear();
                //brand_list.addAll(pinPaiChooseList);

                for (int i = 0; i < pinPaiChooseList.size(); i++) {

                    JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean brandArrayBean = new JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean();
                    brandArrayBean.setIsSelect(pinPaiChooseList.get(i).getIsSelect());
                    brandArrayBean.setId(pinPaiChooseList.get(i).getIsSelect());
                    brandArrayBean.setName(pinPaiChooseList.get(i).getName());
                    brand_list.add(brandArrayBean);
                }

                pinPaiAdapter.notifyDataSetChanged();
                break;
            case R.id.cl_zhinengpaixu:
                clZhinengpaixu.setVisibility(View.GONE);
                ivPaixuDown.setBackgroundResource(R.mipmap.down_black);
                tvZhinengpaixu.setTextColor(this.getResources().getColor(R.color.black_222222));
                break;
            case R.id.cl_km:
                clKm.setVisibility(View.GONE);
                ivKmDown.setBackgroundResource(R.mipmap.down_black);
                tvKm.setTextColor(this.getResources().getColor(R.color.black_222222));
                break;
        }
    }

    private void setShaiXuan() {


        if (allPinPaiFlag) {
            clZhinengpaixu.setVisibility(View.GONE);
            clYouhao.setVisibility(View.GONE);
            clKm.setVisibility(View.GONE);
            clAllPinpai.setVisibility(View.VISIBLE);
        }

        if (kmFlag) {
            clZhinengpaixu.setVisibility(View.GONE);
            clYouhao.setVisibility(View.GONE);
            clKm.setVisibility(View.VISIBLE);
            clAllPinpai.setVisibility(View.GONE);
        }

        if (youhaoFlag) {

            clZhinengpaixu.setVisibility(View.GONE);
            clYouhao.setVisibility(View.VISIBLE);
            clKm.setVisibility(View.GONE);
            clAllPinpai.setVisibility(View.GONE);
        }

        if (zhinengPaixuFlag) {
            clZhinengpaixu.setVisibility(View.VISIBLE);
            clYouhao.setVisibility(View.GONE);
            clKm.setVisibility(View.GONE);
            clAllPinpai.setVisibility(View.GONE);
        }


        ivKmDown.setBackgroundResource(R.mipmap.blackdown_red);
        ivKmDown.setBackgroundResource(R.mipmap.blackdown_red);
        ivKmDown.setBackgroundResource(R.mipmap.blackdown_red);
        ivKmDown.setBackgroundResource(R.mipmap.blackdown_red);


        ivKmDown.setBackgroundResource(R.mipmap.down_black);
        ivKmDown.setBackgroundResource(R.mipmap.down_black);
        ivKmDown.setBackgroundResource(R.mipmap.down_black);
        ivKmDown.setBackgroundResource(R.mipmap.down_black);
    }

    public class MyModel {
        public String a;
        public String isSelect = "0";//0m没有选中 1 选中
    }

    public void getData() {
        /**
         * {
         *  "code":"04245",
         *  "key":"20180305124455yu",
         *  "distance_type":"5",
         *  "order_type":"1",
         *  "oilNoType":"24",
         *  "whether_all":"1",
         *  "page_number":"1",
         *  "x":"45.751592",
         *  "y":"126.61836"
         * }
         */
        Map<String, String> map = new HashMap<>();
        map.put("code", "04245");
        map.put("key", Urls.key);
        map.put("distance_type", distance_type);
        map.put("order_type", order_type);
        map.put("oilNoType", oilNoType);
        map.put("whether_all", whether_all);
        map.put("page_number", String.valueOf(page_number));
        map.put("brand_type", brand_type);
        map.put("x", x);
        map.put("y", y);

        Gson gson = new Gson();
        OkGo.<AppResponse<JiaYouFirstModel.DataBean>>post(HOME_PICTURE)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<JiaYouFirstModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<JiaYouFirstModel.DataBean>> response) {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();

                        if (page_number == 0) {

                            diatance_list.clear();
                            order_list.clear();
                            brand_list.clear();

                        }

                        TuanYouList.this.response = response;
                        diatance_list.addAll(response.body().data.get(0).getDiatance_list());
                        // oil_select_list.addAll(response.body().data.get(0).getOil_select_list().get(0).getOil_array());

                        order_list.addAll(response.body().data.get(0).getOrder_list());
                        brand_list.addAll(response.body().data.get(0).getBrand_list().get(0).getBrand_array());
                        //  pinPaiChooseList.clear();
                        //pinPaiChooseList.addAll(response.body().data.get(0).getBrand_list().get(0).getBrand_array());
                        for (int i = 0; i < brand_list.size(); i++) {

                            JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean brandArrayBean = new JiaYouFirstModel.DataBean.BrandListBean.BrandArrayBean();
                            brandArrayBean.setIsSelect(brand_list.get(i).getIsSelect());
                            brandArrayBean.setId(brand_list.get(i).getIsSelect());
                            brandArrayBean.setName(brand_list.get(i).getName());
                            pinPaiChooseList.add(brandArrayBean);
                        }


                        zhengheYouHaoLieBiao();
                        setKmList();//km
                        setYouHaoList();
                        setPaiXuList();
                        setPinPaiList();

                        oil_list.addAll(response.body().data.get(0).getOil_list());
                        setList();

                        if (response.body().next.equals("0")) {

                            refreshLayout.setEnableLoadMore(false);
                        } else {
                            refreshLayout.setEnableLoadMore(true);
                        }


                    }

                    @Override
                    public void onError(Response<AppResponse<JiaYouFirstModel.DataBean>> response) {
                        AlertUtil.t(TuanYouList.this, response.getException().getMessage());
                    }
                });
    }


    public void getRefreshData() {
        /**
         * {
         *  "code":"04245",
         *  "key":"20180305124455yu",
         *  "distance_type":"5",
         *  "order_type":"1",
         *  "oilNoType":"24",
         *  "whether_all":"1",
         *  "page_number":"1",
         *  "x":"45.751592",
         *  "y":"126.61836"
         * }
         */
        Map<String, String> map = new HashMap<>();
        map.put("code", "04245");
        map.put("key", Urls.key);
        map.put("distance_type", distance_type);
        map.put("order_type", order_type);
        map.put("oilNoType", oilNoType);
        map.put("whether_all", whether_all);
        map.put("page_number", String.valueOf(page_number));
        map.put("brand_type", brand_type);
        map.put("x", x);
        map.put("y", y);

        Gson gson = new Gson();
        OkGo.<AppResponse<JiaYouFirstModel.DataBean>>post(HOME_PICTURE)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<JiaYouFirstModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<JiaYouFirstModel.DataBean>> response) {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                        //   setList();
                        if (page_number == 0) {
                            oil_list.clear();
                        }
                        oil_list.addAll(response.body().data.get(0).getOil_list());

                        if (response.body().next.equals("0")) {//0否1是

                            refreshLayout.setEnableLoadMore(false);
                        } else {

                            refreshLayout.setEnableLoadMore(true);
                            page_number++;
                        }

                        if (oil_list.size() == 0) {
                            View view = View.inflate(TuanYouList.this, R.layout.empty_view, null);
                            xinTuanYouHomeListAdapter.setEmptyView(view);
                            xinTuanYouHomeListAdapter.notifyDataSetChanged();
                        }
                        //page_number++;
                        xinTuanYouHomeListAdapter.notifyDataSetChanged();
                        showLoadSuccess();
                    }

                    @Override
                    public void onError(Response<AppResponse<JiaYouFirstModel.DataBean>> response) {
                        AlertUtil.t(TuanYouList.this, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<JiaYouFirstModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }
                });
    }

    public void zhengheYouHaoLieBiao() {
        // oil_select_list.addAll(response.body().data.get(0).getOil_select_list().get(0).getOil_array());


        List<JiaYouFirstModel.DataBean.OilSelectListBean> oilSelectListBeans = new ArrayList<>();
        List<JiaYouFirstModel.DataBean.OilSelectListBean.OilArrayBean> oilArrayBeans;

        oilSelectListBeans.addAll(response.body().data.get(0).getOil_select_list());

        for (int i = 0; i < oilSelectListBeans.size(); i++) {
            OilArrayBean oilArrayBean = new OilArrayBean(true, oilSelectListBeans.get(i).getOil_type_name(), oilSelectListBeans.get(i).getOil_type());
            oil_select_list.add(oilArrayBean);
            for (int j = 0; j < oilSelectListBeans.get(i).getOil_array().size(); j++) {

                oilArrayBeans = oilSelectListBeans.get(i).getOil_array();

                OilArrayBean oilArrayBean1 = new OilArrayBean(false, oilSelectListBeans.get(i).getOil_type_name(), oilSelectListBeans.get(i).getOil_type());
                oilArrayBean1.setIsSelect(oilArrayBeans.get(j).getIsSelect());
                oilArrayBean1.setId(oilArrayBeans.get(j).getId());
                oilArrayBean1.setName(oilArrayBeans.get(j).getName());
                oil_select_list.add(oilArrayBean1);
            }


        }


    }


    //跳转到高德地图
    private void checkGaodeMap(String latitude, String longtitude, String address) {
        if (isInstallApk(TuanYouList.this, "com.autonavi.minimap")) {// 是否安装了高德地图
            try {
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=&poiname=" + address + "&lat="
                        + latitude
                        + longtitude + "&dev=0");
                TuanYouList.this.startActivity(intent); // 启动调用
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
            }
        } else {// 未安装
            Toast.makeText(TuanYouList.this, "您尚未安装高德地图", Toast.LENGTH_LONG)
                    .show();
            Uri uri = Uri
                    .parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            TuanYouList.this.startActivity(intent);
        }
    }
}
