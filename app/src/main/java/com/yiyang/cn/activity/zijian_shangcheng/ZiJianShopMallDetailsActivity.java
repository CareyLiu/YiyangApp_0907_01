package com.yiyang.cn.activity.zijian_shangcheng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.AccessListActivity;
import com.yiyang.cn.activity.ChooseTaoCanActivity;
import com.yiyang.cn.activity.gouwuche.GouWuCheActivity;
import com.yiyang.cn.adapter.ZiJianPingLunAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.GlideImageLoader;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.GoodsDetails_f;
import com.yiyang.cn.project_A.zijian_interface.ZijianDetailsInterface;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.phoneview.sample.ImageShowActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;

public class ZiJianShopMallDetailsActivity extends BaseActivity implements ZijianDetailsInterface {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;

    View headerView;//header 头
    View footerView;//footer 尾

    ZiJianPingLunAdapter ziJianPingLunAdapter;

    List<GoodsDetails_f.DataBean.AssListBean> assListBeans = new ArrayList<>();
    @BindView(R.id.iv_dianpu_iamge)
    ImageView ivDianpuIamge;
    @BindView(R.id.tv_dianpu)
    TextView tvDianpu;
    @BindView(R.id.iv_kefu_image)
    ImageView ivKefuImage;
    @BindView(R.id.iv_shoucang_iamge)
    ImageView ivShoucangIamge;
    @BindView(R.id.ll_jiaru_gouwuche)
    LinearLayout llJiaruGouwuche;
    @BindView(R.id.ll_liji_goumai)
    LinearLayout llLijiGoumai;
    @BindView(R.id.tv_shoucang)
    TextView tvShoucang;
    @BindView(R.id.tv_kefu)
    TextView tvKefu;
    private Response<AppResponse<GoodsDetails_f.DataBean>> response;
    GoodsDetails_f.DataBean dataBean;
    private String productId;
    private String warseId;
    private String title;
    private String shopName;//店铺名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_zi_jian_shop_mall_details);
        productId = this.getIntent().getStringExtra("productId");
        warseId = this.getIntent().getStringExtra("wareId");
        ButterKnife.bind(this);
        getNet();

        PreferenceHelper.getInstance(ZiJianShopMallDetailsActivity.this).putString(App.PRODUCTID, warseId);
        //setClick();


    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("详情");
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
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setBackgroundResource(R.mipmap.shop_xiangqing_gouwuche_slip);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(ZiJianShopMallDetailsActivity.this, "添加到购物车");
                // UIHelper.ToastMessage(ZiJianShopMallDetailsActivity.this, "功能开发中");
                GouWuCheActivity.actionStart(ZiJianShopMallDetailsActivity.this);
            }
        });

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zi_jian_shop_mall_details;
    }


    private String isCollection = "0";//0 没有收藏 1 已收藏

    @Override
    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04133");
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(mContext).getString("app_token", "0"));
        map.put("shop_product_id", productId);
        map.put("wares_id", warseId);
        Gson gson = new Gson();
        OkGo.<AppResponse<GoodsDetails_f.DataBean>>post(Urls.SERVER_URL + "shop_new/app")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GoodsDetails_f.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GoodsDetails_f.DataBean>> response) {
                        ZiJianShopMallDetailsActivity.this.response = response;
                        assListBeans.addAll(response.body().data.get(0).getAssList());

                        if (assListBeans.size() == 0) {
                            //      View view = View.inflate(ZiJianShopMallDetailsActivity.this, R.layout.layout_none_ass, null);
                            //    ziJianPingLunAdapter.setEmptyView(view);
                            GoodsDetails_f.DataBean.AssListBean assListBean = new GoodsDetails_f.DataBean.AssListBean();
                            assListBean.setNonDataShow("1");
                            assListBeans.add(assListBean);

                        }
                        loadList();
                        //ziJianPingLunAdapter.notifyDataSetChanged();
                        setHeader();
                        //ziJianPingLunAdapter.notifyDataSetChanged();
                        setFooter();
                        setClick();
                        //用户是否收藏了该商品:
                        //1.已收藏0.未收藏
                        if (response.body().data.get(0).getIsCollection().equals("0")) {
                            ivShoucangIamge.setBackgroundResource(R.mipmap.shopdetails_weishoucang);
                        } else if (response.body().data.get(0).getIsCollection().equals("1")) {
                            ivShoucangIamge.setBackgroundResource(R.mipmap.shop_icon_weishoucang);
                        }

                        isCollection = response.body().data.get(0).getIsCollection();

                        ivShoucangIamge.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isCollection.equals("0")) {
                                    setShouCang("04126");
                                } else if (isCollection.equals("1")) {
                                    setShouCang("04127");
                                }
                            }
                        });

                        tvShoucang.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isCollection.equals("0")) {
                                    setShouCang("04126");
                                } else if (isCollection.equals("1")) {
                                    setShouCang("04127");
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Response<AppResponse<GoodsDetails_f.DataBean>> response) {
                        AlertUtil.t(ZiJianShopMallDetailsActivity.this, response.getException().getMessage());
                    }
                });

    }

    private void setShouCang(String code) {
        //收藏商品
        /**
         * code	请求码(04126)	6	是
         * key	身份标识	10	是
         * token	token	18	是
         * collection_type	收藏类型 1.商品 2.商家		是
         * wares_id	商品id(收藏类型为1时传)		否
         * shop_product_id	产品id(收藏类型为1时传)		否
         * inst_id	商家id(收藏类型为2时传)		否
         */

        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(mContext).getString("app_token", "0"));
        map.put("collection_type", "1");
        map.put("wares_id", warseId);
        map.put("shop_product_id", productId);

        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(HOME_PICTURE_HOME)
                .tag(mContext)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        if (code.equals("04126")) {
                            UIHelper.ToastMessage(ZiJianShopMallDetailsActivity.this, "添加成功");
                            ivShoucangIamge.setBackgroundResource(R.mipmap.shop_icon_weishoucang);
                            isCollection = "1";
                        } else {
                            UIHelper.ToastMessage(ZiJianShopMallDetailsActivity.this, "取消收藏成功");
                            ivShoucangIamge.setBackgroundResource(R.mipmap.shopdetails_weishoucang);
                            isCollection = "0";
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        super.onError(response);
                        String str = response.getException().getMessage();
                        String[] str1 = str.split("：");

                        if (str1.length == 3) {
                            UIHelper.ToastMessage(ZiJianShopMallDetailsActivity.this, str1[2]);
                        }
                    }
                });
    }

    @Override
    public void loadList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rlvList.setLayoutManager(linearLayoutManager);
        ziJianPingLunAdapter = new ZiJianPingLunAdapter(assListBeans);
        // ziJianPingLunAdapter.notifyDataSetChanged();
        rlvList.setAdapter(ziJianPingLunAdapter);
    }

    Banner banner;
    RelativeLayout rlTaoCan;
    RelativeLayout rlCanshu;

    @Override
    public void setHeader() {
        headerView = View.inflate(this, R.layout.zijian_shopmall_header, null);
        banner = headerView.findViewById(R.id.banner);
        GoodsDetails_f.DataBean dataBean = response.body().data.get(0);
        TextView tvSeeMore = headerView.findViewById(R.id.tv_see_more);
        ImageView ivSeeMore = headerView.findViewById(R.id.iv_see_more_back);
        TextView tvPrice = headerView.findViewById(R.id.tv_price);
        TextView tvTitle = headerView.findViewById(R.id.tv_title);
        TextView tvKuaidi = headerView.findViewById(R.id.tv_kuaidi);
        TextView tvYueXiao = headerView.findViewById(R.id.tv_yuexiao);
        TextView tvDiZhi = headerView.findViewById(R.id.tv_dizhi);
        rlTaoCan = headerView.findViewById(R.id.rl_taocan);
        rlCanshu = headerView.findViewById(R.id.rl_canshu);
        List<String> items = new ArrayList<>();
        if (response.body().data != null) {
            for (int i = 0; i < response.body().data.get(0).getBannerList().size(); i++) {
                items.add(response.body().data.get(0).getBannerList().get(i).getImg_url());
            }
        }



        tvPrice.setText("¥" + response.body().data.get(0).getMoney_begin() + "-" + response.body().data.get(0).getMoney_end());
        tvTitle.setText(response.body().data.get(0).getShop_product_title());
        String kuaidi = response.body().data.get(0).getWares_go_type();
        //     消费方式：1.邮递/到店
        //     2.邮递 3.到店

        if (kuaidi.equals("1")) {
            tvKuaidi.setText("邮递/到店");
        } else if (kuaidi.equals("2")) {
            tvKuaidi.setText("邮递");
        } else if (kuaidi.equals("3")) {
            tvKuaidi.setText("到店");
        }

        PreferenceHelper.getInstance(ZiJianShopMallDetailsActivity.this).putString(App.KUAIDITYPE, kuaidi);

        PreferenceHelper.getInstance(ZiJianShopMallDetailsActivity.this).putString(App.KUAIDIFEI, dataBean.getWares_money_go());

        tvYueXiao.setText(dataBean.getWares_sales_volume());
        tvDiZhi.setText(dataBean.getAddr());
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        if (banner != null) {
            //设置图片集合
            banner.setImages(items);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    //startActivity(new Intent(ZiJianShopMallDetailsActivity.this, WebViewActivity.class).putExtra("url", response.body().data.get(0).getBannerList().get(position).getImg_url()));
                    ArrayList<String> list = new ArrayList<>();
                    list.add(response.body().data.get(0).getBannerList().get(position).getImg_url());
                    ImageShowActivity.actionStart(ZiJianShopMallDetailsActivity.this, list);

                }
            });
        }


        if (response.body().data.get(0).getAssList().size() == 0) {
            ivSeeMore.setVisibility(View.GONE);
            tvSeeMore.setVisibility(View.GONE);
        }

        tvSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessListActivity.actionStart(mContext, warseId);
            }
        });

        ziJianPingLunAdapter.addHeaderView(headerView);
    }


    @Override
    public void setFooter() {
        footerView = View.inflate(this, R.layout.zijian_shopmall_footer, null);
        LinearLayout linearLayout = footerView.findViewById(R.id.ll_footer);
        List<GoodsDetails_f.DataBean.DetailImgListBean> productListBeans = response.body().data.get(0).getDetailImgList();
        for (int i = 0; i < productListBeans.size(); i++) {
            //  ImageView iv = new ImageView(ZiJianShopMallDetailsActivity.this);

            View view = View.inflate(ZiJianShopMallDetailsActivity.this, R.layout.item_big_image, null);
            ImageView iv = view.findViewById(R.id.iv_img);
//
//            int screenWidth = getScreenWidth(this); // 获取屏幕宽度
//            ViewGroup.LayoutParams para;
//            para = iv.getLayoutParams();
//
//            Log.d("zijianshopmalldetail", "layout height0: " + para.height);
//            Log.d("zijianshopmalldetail", "layout width0: " + para.width);
//
//            para.height = Integer.parseInt(productListBeans.get(i).getImg_height());
//            para.width = screenWidth;
//            iv.setLayoutParams(para);

            if (!StringUtils.isEmpty(productListBeans.get(i).getImg_height())) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.override(Integer.valueOf(productListBeans.get(i).getImg_width()), Integer.valueOf(productListBeans.get(i).getImg_height()));
                Glide.with(ZiJianShopMallDetailsActivity.this).load(productListBeans.get(i).getImg_url()).apply(requestOptions).into(iv);
            }
            linearLayout.addView(view);
        }
        ziJianPingLunAdapter.addFooterView(footerView);
        ziJianPingLunAdapter.notifyDataSetChanged();
    }

    @Override
    public void setClick() {
        ivDianpuIamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击店铺
                ShopDetailsActivity.actionStart(mContext, response.body().data.get(0).getInst_id());
            }
        });
        tvDianpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopDetailsActivity.actionStart(mContext, response.body().data.get(0).getInst_id());
            }
        });
        ivKefuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //客服
                //Demo_rongyun.actionStart(mContext,response.body().data.get(0).getInst_accid());
                Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
                String targetId = response.body().data.get(0).getInst_accid();
                String instName = response.body().data.get(0).getInst_name();
                Bundle bundle = new Bundle();
                bundle.putString("dianpuming", instName);
                bundle.putString("inst_accid", response.body().data.get(0).getInst_accid());
                bundle.putString("shoptype","1");
                RongIM.getInstance().startConversation(mContext, conversationType, targetId, instName, bundle);
            }
        });
        tvKefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //客服
                //Demo_rongyun.actionStart(mContext,response.body().data.get(0).getInst_accid());
                Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
                String targetId = response.body().data.get(0).getInst_accid();
                String instName = response.body().data.get(0).getInst_name();
                Bundle bundle = new Bundle();
                bundle.putString("dianpuming", instName);
                bundle.putString("inst_accid", response.body().data.get(0).getInst_accid());
                bundle.putString("shoptype","1");
                RongIM.getInstance().startConversation(mContext, conversationType, targetId, instName, bundle);
            }
        });

        llJiaruGouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加入购物车
                dataBean = response.body().data.get(0);
                ChooseTaoCanActivity.actionStart(ZiJianShopMallDetailsActivity.this, dataBean, "1", dataBean.getMoney_begin(), dataBean.getMoney_end(), response.body().data.get(0).getShop_product_title(), dataBean.getInst_name(), dataBean.getInst_logo_url(), response.body().data.get(0).getWares_go_type(), "0");
            }
        });
        llLijiGoumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //立即购买
                dataBean = response.body().data.get(0);
                ChooseTaoCanActivity.actionStart(ZiJianShopMallDetailsActivity.this, dataBean, "1", dataBean.getMoney_begin(), dataBean.getMoney_end(), response.body().data.get(0).getShop_product_title(), dataBean.getInst_name(), dataBean.getInst_logo_url(), response.body().data.get(0).getWares_go_type(), "1");
            }
        });
        rlTaoCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean = response.body().data.get(0);
                ChooseTaoCanActivity.actionStart(ZiJianShopMallDetailsActivity.this, dataBean, "1", dataBean.getMoney_begin(), dataBean.getMoney_end(), response.body().data.get(0).getShop_product_title(), dataBean.getInst_name(), dataBean.getInst_logo_url(), response.body().data.get(0).getWares_go_type(), "0");

            }
        });
        rlCanshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean = response.body().data.get(0);
                ChooseTaoCanActivity.actionStart(ZiJianShopMallDetailsActivity.this, dataBean, "2", dataBean.getMoney_begin(), dataBean.getMoney_end(), response.body().data.get(0).getShop_product_title(), dataBean.getInst_name(), dataBean.getInst_logo_url(), response.body().data.get(0).getWares_go_type(), "0");
            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String productId, String wareId) {
        Intent intent = new Intent(context, ZiJianShopMallDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("productId", productId);
        intent.putExtra("wareId", wareId);
        context.startActivity(intent);
    }

}
