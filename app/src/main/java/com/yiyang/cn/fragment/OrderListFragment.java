package com.yiyang.cn.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.DefaultX5WebViewActivity;
import com.yiyang.cn.activity.dingdan.AccessActivity;
import com.yiyang.cn.activity.dingdan.DaiFuKuanDingDanActivity;
import com.yiyang.cn.activity.dingdan.DingDanShenQingTuikuanActivity;
import com.yiyang.cn.activity.dingdan.OrderTuiKuanDetailsActivity;
import com.yiyang.cn.activity.dingdan.ShenQingTuiKuanActivity;
import com.yiyang.cn.activity.dingdan.TuanGouDingDanDetails;
import com.yiyang.cn.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.yiyang.cn.adapter.dingdan.OrderListAdapter;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.AddressModel;
import com.yiyang.cn.model.DataIn;
import com.yiyang.cn.model.OrderListModel;
import com.yiyang.cn.model.SmartDevice_car_0364;
import com.yiyang.cn.model.YuZhiFuModel;
import com.yiyang.cn.model.YuZhiFuModel_AliPay;
import com.yiyang.cn.pay_about.alipay.PayResult;
import com.yiyang.cn.util.PaySuccessUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.app.App.DINGDANZHIFU;
import static com.yiyang.cn.app.ConstanceValue.MSG_DINGDAN_PAY;

public class OrderListFragment extends BaseFragment {

    private RecyclerView recyclerView;
    List<OrderListModel.DataBean> mDatas = new ArrayList<>();
    private OrderListAdapter orderListAdapter;
    OrderListModel.DataBean dataBean = new OrderListModel.DataBean();
    String user_pay_check = "0";
    String str;
    Response<AppResponse<OrderListModel.DataBean>> response;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        progressDialog = new ProgressDialog(getActivity());
        str = bundle.getString("title");

        /**
         *         tagList.add("全部");
         *         tagList.add("待付款");
         *         tagList.add("待分享");
         *         tagList.add("待发货");
         *         tagList.add("待收货");
         *         tagList.add("到店");
         *         tagList.add("待评价");
         *         tagList.add("已完成");
         *         tagList.add("退货/退款");
         *         tagList.add("订单失效");
         *
         *         订单状态：0.全部1.待付款 3.待发货
         * 4.待收货5.到店消费6.待评价7.已完成 8.9.10.退货/退款
         */
        if (str.equals("全部")) {
            user_pay_check = "0";
        } else if (str.equals("待付款")) {
            user_pay_check = "1";
        } else if (str.equals("待发货")) {
            user_pay_check = "3";
        } else if (str.equals("待收货")) {
            user_pay_check = "4";
        } else if (str.equals("到店")) {
            user_pay_check = "5";
        } else if (str.equals("待评价")) {
            user_pay_check = "6";
        } else if (str.equals("待分享")) {
            user_pay_check = "2";
        } else if (str.equals("退货/退款")) {
            user_pay_check = "10";
        } else if (str.equals("订单失效")) {
            user_pay_check = "11";
        } else if (str.equals("已完成")) {
            user_pay_check = "7";
        }

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == MSG_DINGDAN_PAY) {
                    pageNumber = 0;
                    getNet();//请求接口重新刷新列表
                }
            }
        }));
    }

    public int pageNumber = 0;

    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04161");
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(getActivity()).getString("app_token", "0"));
        map.put("user_pay_check", user_pay_check);//全部
        map.put("page_number", String.valueOf(pageNumber));

        // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
        Gson gson = new Gson();
        OkGo.<AppResponse<OrderListModel.DataBean>>post(Urls.HOME_PICTURE_HOME)
                .tag(getActivity())//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<OrderListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<OrderListModel.DataBean>> response) {
                        smartRefreshLayout.finishLoadMore();
                        smartRefreshLayout.finishRefresh();
                        OrderListFragment.this.response = response;
                        if (pageNumber == 0) {
                            mDatas.clear();
                            mDatas.addAll(response.body().data);
                            orderListAdapter.setNewData(mDatas);
                            orderListAdapter.notifyDataSetChanged();
                        } else {
                            mDatas.addAll(response.body().data);
                            orderListAdapter.notifyDataSetChanged();
                        }

                        if (mDatas.size() == 0) {
                            View view = View.inflate(getActivity(), R.layout.layout_orderllist_empty, null);
                            orderListAdapter.setEmptyView(view);
                            orderListAdapter.notifyDataSetChanged();
                        }

                        if (response.body().next.equals("0")) {//没有分页
                            smartRefreshLayout.setEnableLoadMore(false);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<OrderListModel.DataBean>> response) {
                        super.onError(response);
                    }
                });

    }

    String strTitle;
    ImageView ivNone;

    @Override
    protected void initLogic() {
        Bundle args = getArguments();
        strTitle = args.getString("title");
        getNet();
    }

    RelativeLayout rlMain;
    View view;

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    private SmartRefreshLayout smartRefreshLayout;

    @Override
    protected int getLayoutRes() {
        return R.layout.order_list;
    }

    @Override
    protected void initView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rlMain = rootView.findViewById(R.id.rl_main);
        ivNone = rootView.findViewById(R.id.iv_none);
        view = rootView.findViewById(R.id.view);
        smartRefreshLayout = rootView.findViewById(R.id.srL_smart);
        initSmartRefresh();
        initAdapter();
    }

    public void initSmartRefresh() {
        smartRefreshLayout.setEnableAutoLoadMore(true);
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                ++pageNumber;
                getNet();

            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 0;
                getNet();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderListAdapter = new OrderListAdapter(R.layout.item_order_list, mDatas);
        orderListAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(orderListAdapter);

        orderListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderListModel.DataBean dataBean = orderListAdapter.getData().get(position);
                switch (view.getId()) {

                    case R.id.constrain:
                        /**
                         * 订单类型：1.普通2.拼单 3.团购商品 4.非商品，直接付款
                         */
                        if (dataBean.getWares_type().equals("1")) {

                            if (dataBean.getUser_pay_check().equals("8") || dataBean.getUser_pay_check().equals("9") || dataBean.getUser_pay_check().equals("10")) {

                                OrderTuiKuanDetailsActivity.actionStart(getActivity(), dataBean.getShop_form_id());

                            } else {
                                DaiFuKuanDingDanActivity.actionStart(getActivity(), dataBean);
                            }

                        } else if (dataBean.getWares_type().equals("3")) {
                            if (dataBean.getUser_pay_check().equals("8") || dataBean.getUser_pay_check().equals("9") || dataBean.getUser_pay_check().equals("10")) {

                                OrderTuiKuanDetailsActivity.actionStart(getActivity(), dataBean.getShop_form_id());

                            } else if (dataBean.getUser_pay_check().equals("11") || dataBean.getUser_pay_check().equals("7")) {
                                DaiFuKuanDingDanActivity.actionStart(getActivity(), dataBean);

                            } else {

                                DaiFuKuanDingDanActivity.actionStart(getActivity(), dataBean);
                            }

                        }

                        break;
                    case R.id.tv_caozuo:
                        doCaoZuo(dataBean, position);
                        break;
                    case R.id.tv_caozuo1:
                        doCaoZuo1(dataBean, position);
                        break;
                    case R.id.tv_caozuo2:
                        doCaoZuo2(dataBean, position);
                        break;
                }
            }
        });

    }

    /**
     * user_pay_check	买家状态:1.待付款 2.待分享(拼)3.待发货 4.已发货 5.到店消费6.待评价 7.完成 8.退款申请 9.退款中 10.退款/退货 11 订单失效
     */

    public void doCaoZuo(OrderListModel.DataBean dataBean, int position) {
        /**
         * user_pay_check	买家状态:1.待付款 2.待分享(拼)3.待发货 4.已发货 5.到店消费6.待评价 7.完成 8.退款申请 9.退款中 10.退款/退货 11 订单失效
         */
        switch (dataBean.getUser_pay_check()) {
            case "1":
                // helper.setText(R.id.tv_caozuo, "去支付");

//                if (dataBean.getUser_pay_check())
                showSingSelect(dataBean);

                break;
            case "2":
                // helper.setText(R.id.tv_caozuo, "申请退款");
                break;
            case "3":
                ShenQingTuiKuanActivity.actionStart(getActivity(), dataBean.getShop_form_id(), dataBean.getTotal_money(), dataBean.getUser_pay_check());
                break;
            case "4":
                // helper.setText(R.id.tv_caozuo, "确认收款");
                // wares_go_type	消费方式：2.邮递3.到店
                if (dataBean.getWares_go_type().equals("2")) {
                    showDngDanCaoZuo(dataBean, position, "是否确认收货", "04164");
                } else if (dataBean.getWares_go_type().equals("3")) {
                    showDngDanCaoZuo(dataBean, position, "是否已消费完成", "04164");
                }

                break;
            case "5":
                //helper.setText(R.id.tv_caozuo, "查看详情");
                UIHelper.ToastMessage(getActivity(), "联系卖家");

                Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
                String targetId = response.body().data.get(0).getInst_accid();
                String instName = response.body().data.get(0).getInst_name();
                Bundle bundle = new Bundle();
                bundle.putString("dianpuming", instName);
                bundle.putString("inst_accid", response.body().data.get(0).getInst_accid());
                bundle.putString("shoptype","2");
                RongIM.getInstance().startConversation(getActivity(), conversationType, targetId, instName, bundle);

                break;
            case "6":
                showDngDanCaoZuo(dataBean, position, "是否删除订单", "04157");

                // helper.setText(R.id.tv_caozuo, "联系买家");
                // helper.setVisible(R.id.tv_caozuo, false);
                break;
            case "7":

                // helper.setText(R.id.tv_caozuo, "申请退款");

                break;
            case "8":
                // helper.setText(R.id.tv_yipingjia, "退款申请");

                //  helper.setText(R.id.tv_caozuo, "申请退款");

                break;
            case "9":
                //  helper.setText(R.id.tv_yipingjia, "退款中");
                //  helper.setText(R.id.tv_caozuo, "申请退款");

                break;
            case "10":
                //  helper.setText(R.id.tv_yipingjia, "退款/退货");
                //   helper.setText(R.id.tv_caozuo, "再次购买");
                showDngDanCaoZuo(dataBean, position, "是否删除订单", "04157");

                break;
            case "11":
                //  helper.setText(R.id.tv_yipingjia, "订单失效");
                // helper.setText(R.id.tv_caozuo, "删除订单");
                // showDngDanCaoZuo(dataBean, position, "是否删除订单", "04157");

                break;

        }

    }

    private void getNetQueRenShouHuo() {

    }

    public void doCaoZuo1(OrderListModel.DataBean dataBean, int position) {
        /**
         * user_pay_check	买家状态:1.待付款 2.待分享(拼)3.待发货 4.已发货 5.到店消费6.待评价 7.完成 8.退款申请 9.退款中 10.退款/退货 11 订单失效
         */
        switch (dataBean.getUser_pay_check()) {
            case "1":

                // helper.setText(R.id.tv_caozuo1, "取消订单");
                //getNet_QUXIAO(dataBean.getShop_form_id());
                showDngDanCaoZuo(dataBean, position, "是否取消订单", "04156");

                break;
            case "2":

                //helper.setText(R.id.tv_caozuo1, "删除订单");
                showDngDanCaoZuo(dataBean, position, "是否删除订单", "04157");

                break;
            case "3":

                //helper.setText(R.id.tv_caozuo1, "催发货");
                //getNet_CaoZuo(dataBean.getShop_form_id(), "04167", position);
                //催发货
                getNetCuiFaHuo(dataBean, position);
                break;
            case "4":
                DefaultX5WebViewActivity.actionStart(getActivity(), dataBean.getExpress_url());
                //helper.setText(R.id.tv_caozuo1, "查看物流");
                break;
            case "5":
//                helper.setText(R.id.tv_caozuo1, "申请退款");
//
//                break;
                DingDanShenQingTuikuanActivity.actionStart(getActivity(), "我要退款(无需退货)", dataBean.getShop_form_id(), dataBean.getPay_money());
                break;
            case "6":

                // helper.setText(R.id.tv_caozuo1, "查看物流");
                //  DefaultX5WebViewActivity.actionStart(getActivity(), dataBean.getExpress_url());
                // showDngDanCaoZuo(dataBean, position, "是否删除订单", "04157");
                //去评价
                AccessActivity.actionStart(getActivity(), dataBean.getIndex_photo_url(), dataBean.getShop_form_id());
                break;
            case "7":
                showDngDanCaoZuo(dataBean, position, "是否删除订单", "04157");
                //helper.setText(R.id.tv_caozuo1, "删除订单");
                break;
            case "8":
            case "10":
            case "9":
                getActivity().finish();
                /**
                 * 订单类型：1.普通2.拼单 3.团购商品 4.非商品，直接付款
                 */
                if (orderListAdapter.getData().get(position).getWares_type().equals("1")) {
                    ZiJianShopMallDetailsActivity.actionStart(getActivity(), dataBean.getShop_product_id(), dataBean.getWares_id());
                } else if (orderListAdapter.getData().get(position).getWares_type().equals("3")) {

                }


                // helper.setText(R.id.tv_caozuo1, "删除订单");
                break;

            // helper.setText(R.id.tv_caozuo1, "删除订单");

            //  helper.setText(R.id.tv_caozuo1, "联系卖家");
            //  helper.setVisible(R.id.tv_caozuo1, false);
            case "11":

                showDngDanCaoZuo(dataBean, position, "是否删除订单", "04157");
                break;


        }
    }

    public void doCaoZuo2(OrderListModel.DataBean dataBean, int position) {
        /**
         * user_pay_check	买家状态:1.待付款 2.待分享(拼)3.待发货 4.已发货 5.到店消费6.待评价 7.完成 8.退款申请 9.退款中 10.退款/退货 11 订单失效
         */
        switch (dataBean.getUser_pay_check()) {
            case "1":


                break;
            case "2":


                break;
            case "3":


                break;
            case "4":
                ShenQingTuiKuanActivity.actionStart(getActivity(), dataBean.getShop_form_id(), dataBean.getTotal_money(), dataBean.getUser_pay_check());
                break;

            case "6":

                // helper.setText(R.id.tv_caozuo1, "查看物流");
                //  DefaultX5WebViewActivity.actionStart(getActivity(), dataBean.getExpress_url());
                // showDngDanCaoZuo(dataBean, position, "是否删除订单", "04157");
                //去评价
                //AccessActivity.actionStart(getActivity(), dataBean.getInst_img_url(), dataBean.getShop_form_id());
                break;
            case "7":
                // showDngDanCaoZuo(dataBean, position, "是否删除订单", "04157");
                //helper.setText(R.id.tv_caozuo1, "删除订单");
                break;
            case "8":
            case "10":
            case "9":
                // getActivity().finish();
                // ZiJianShopMallDetailsActivity.actionStart(getActivity(), dataBean.getShop_product_id(), dataBean.getWares_id());
                // helper.setText(R.id.tv_caozuo1, "删除订单");
                break;

            // helper.setText(R.id.tv_caozuo1, "删除订单");

            //  helper.setText(R.id.tv_caozuo1, "联系卖家");
            //  helper.setVisible(R.id.tv_caozuo1, false);
            case "11":

                //  showDngDanCaoZuo(dataBean, position, "是否删除订单", "04157");
                break;


        }
    }

    private void getNetCuiFaHuo(OrderListModel.DataBean dataBean, int position) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04167");
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(getActivity()).getString("app_token", "0"));
        map.put("shop_form_id", dataBean.getShop_form_id());

        // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(Urls.HOME_PICTURE_HOME)
                .tag(getActivity())//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        UIHelper.ToastMessage(getActivity(), response.body().msg);
                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        super.onError(response);
                        //  UIHelper.ToastMessage(getActivity(), response.body().msg);
                        String str = response.getException().getMessage();
                        //    Log.i("cuifahuo", str);

                        String[] str1 = str.split("：");

                        if (str1.length == 3) {
                            UIHelper.ToastMessage(getActivity(), str1[2]);
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<AppResponse<Object>> response) {
                        super.onCacheSuccess(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });

    }

//    public void doCaoZuo2(OrderListModel.DataBean dataBean) {
//
//        /**
//         * user_pay_check	买家状态:1.待付款 2.待分享(拼)3.待发货 4.已发货 5.到店消费6.待评价 7.完成 8.退款申请 9.退款中 10.退款/退货 11 订单失效
//         */
//        switch (dataBean.getUser_pay_check()) {
//
//            case "1":
//
//                helper.setText(R.id.tv_caozuo2, "联系买家");
//
//                break;
//            case "2":
//
//                helper.setText(R.id.tv_caozuo2, "联系买家");
//                helper.setVisible(R.id.tv_caozuo2, false);
//                break;
//            case "3":
//
//                helper.setText(R.id.tv_caozuo2, "申请退款");
//                break;
//            case "4":
//
//                helper.setText(R.id.tv_caozuo2, "联系买家");
//                helper.setVisible(R.id.tv_caozuo2, false);
//                break;
//            case "5":
//
//                //helper.setText(R.id.tv_caozuo2, "联系买家");
//                helper.setVisible(R.id.tv_caozuo2, false);
//                break;
//            case "6":
//
//                helper.setText(R.id.tv_caozuo2, "删除订单");
//                helper.setVisible(R.id.tv_caozuo, false);
//                break;
//            case "7":
//
//                helper.setText(R.id.tv_caozuo2, "联系买家");
//                helper.setVisible(R.id.tv_caozuo2, false);
//                break;
//            case "8":
//
//                helper.setText(R.id.tv_caozuo2, "联系买家");
//                helper.setVisible(R.id.tv_caozuo2, false);
//                break;
//            case "9":
//
//                helper.setText(R.id.tv_caozuo2, "联系买家");
//                helper.setVisible(R.id.tv_caozuo2, false);
//                break;
//            case "10":
//
//                helper.setText(R.id.tv_caozuo2, "申请退款");
//                break;
//            case "11":
//                helper.setVisible(R.id.tv_caozuo2, false);
//                break;
//
//        }
//
//    }


    public void enterDetails(String type) {

        switch (type) {
            case "1":

                break;
        }
    }

    String payType = "4";//1 支付宝 4 微信
    private String appId;//支付id 给支付宝
    ProgressDialog progressDialog;
    private IWXAPI api;
    private String form_id;//订单id
    List<ProductDetails> productDetailsForJava = new ArrayList<>();

    public class ProductDetails {
        private String form_product_id;
        private String shop_product_id;
        private String pay_count;
        private String shop_form_text;
        private String wares_go_type;
        private String installation_type_id;
    }

    private void getWeiXinOrZhiFuBao(String pay_id, OrderListModel.DataBean dataBean) {
        //   productDetailsForJava.get(0).shop_form_text = etLiuYan.getText().toString();
//        form_product_id 	购物车产品id
//        shop_product_id	商品套餐id
//        pay_count	购买数量
//        shop_form_text	订单备注(买家留言)
//         wares_go_type	消费方式：2.邮递 3.到店
//
        ProductDetails productDetails = new ProductDetails();
        productDetails.shop_product_id = dataBean.getShop_product_id();
        productDetails.pay_count = dataBean.getPay_count();
        productDetails.shop_form_text = dataBean.getShop_form_text();
        productDetails.wares_go_type = dataBean.getWares_go_type();
        productDetails.form_product_id = dataBean.getInstallation_type_id();
        productDetailsForJava.add(productDetails);

        //OrderListModel.DataBean dataBean = orderListAdapter.getData().get(position);
        if (pay_id.equals("1")) {//1支付宝
            Map<String, Object> map = new HashMap<>();
            map.put("key", Urls.key);
            map.put("token", UserManager.getManager(getActivity()).getAppToken());
            map.put("operate_type", dataBean.getOperate_type());
            map.put("pay_id", pay_id);
            map.put("pay_type", "1");
            //  map.put("users_addr_id", users_addr_id);
            //   map.put("pro", productDetailsForJava);
            // map.put("deduction_type", userHongBao);
            map.put("shop_form_id", dataBean.getShop_form_id());
            //shop_form_id
            String myHeaderLog = new Gson().toJson(map);
            String myHeaderInfo = StringEscapeUtils.unescapeJava(myHeaderLog);
            Log.i("request_log", myHeaderInfo);
            Gson gson = new Gson();
            OkGo.<AppResponse<YuZhiFuModel_AliPay.DataBean>>post(Urls.DALIBAO_PAY)
                    .tag(getActivity())//
                    .upJson(myHeaderInfo)
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel_AliPay.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {

                            appId = response.body().data.get(0).getPay();
                            form_id = response.body().data.get(0).getOut_trade_no();
                            payV2(appId);//这里填写后台返回的支付信息
                            //progressDialog.dismiss();
                        }

                        @Override
                        public void onError(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {
                            super.onError(response);
                            //progressDialog.dismiss();
                        }
                    });

        } else {//2微信

            //获得后台的支付信息\

            /**
             * {
             *   "key":"20180305124455yu",
             *  "token":"1234353453453456",
             *  "operate_id":"12",
             *  "operate_type":"1",
             *  "pay_id":"1"
             * }
             */

            Map<String, Object> map = new HashMap<>();
            map.put("key", Urls.key);
            map.put("token", UserManager.getManager(getActivity()).getAppToken());
            map.put("operate_type", dataBean.getOperate_type());
            map.put("pay_id", pay_id);
            map.put("pay_type", "4");

            //   map.put("pay_type", payType);

            // map.put("users_addr_id", users_addr_id);
            // map.put("pro", productDetailsForJava);

            // map.put("deduction_type", userHongBao);
            map.put("shop_form_id", dataBean.getShop_form_id());

            String myHeaderLog = new Gson().toJson(map);
            String myHeaderInfo = StringEscapeUtils.unescapeJava(myHeaderLog);
            Log.i("request_log", myHeaderInfo);

            // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
            Gson gson = new Gson();
            OkGo.<AppResponse<YuZhiFuModel.DataBean>>post(Urls.DALIBAO_PAY)
                    .tag(getActivity())//
                    .upJson(myHeaderInfo)
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel.DataBean>> response) {
                            api = WXAPIFactory.createWXAPI(getActivity(), response.body().data.get(0).getPay().getAppid());
                            form_id = response.body().data.get(0).getPay().getOut_trade_no();

                            //     finish();
                            goToWeChatPay(response.body().data.get(0));
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onError(Response<AppResponse<YuZhiFuModel.DataBean>> response) {
                            super.onError(response);
                            progressDialog.dismiss();
                        }
                    });
        }

    }

    /**
     * 微信支付
     *
     * @param out
     */
    private void goToWeChatPay(YuZhiFuModel.DataBean out) {
        api = WXAPIFactory.createWXAPI(getActivity(), out.getPay().getAppid());
        api.registerApp(out.getPay().getAppid());
        PayReq req = new PayReq();
        req.appId = out.getPay().getAppid();
        req.partnerId = out.getPay().getPartnerid();
        req.prepayId = out.getPay().getPrepayid();
        req.timeStamp = out.getPay().getTimestamp();
        req.nonceStr = out.getPay().getNoncestr();
        req.sign = out.getPay().getSign();
        req.packageValue = out.getPay().getPackageX();
        api.sendReq(req);
    }

    private static final int SDK_PAY_FLAG = 1;


    /**
     * 支付宝支付业务
     */
    public void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);

            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        PaySuccessUtils.getNet(getActivity(), form_id);
                        UIHelper.ToastMessage(getActivity(), "支付成功", Toast.LENGTH_SHORT);
                        //  getActivity().finish();
                        pageNumber = 0;
                        getNet();//支付成功重新刷新接口

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                        PaySuccessUtils.getNetFail(getActivity(), form_id);

                    }
                    break;
                }
                default:
                    break;
            }
        }

    };


    private int choice;
    private AlertDialog.Builder builder;

    /**
     * 单选 dialog
     */
    private void showSingSelect(OrderListModel.DataBean dataBean) {

        //默认选中第一个
        final String[] items = {"微信", "支付宝"};
        choice = -1;
        builder = new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.logi_icon).setTitle("支付方式")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice = i;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice == -1) {
                            Toast.makeText(getActivity(), "窗口关闭，未选择支付方式", Toast.LENGTH_LONG).show();
                        } else if (items[choice].equals("微信")) {
                            //微信
                            String pay_id = "2";
                            PreferenceHelper.getInstance(getActivity()).putString(DINGDANZHIFU, DINGDANZHIFU);
                            getWeiXinOrZhiFuBao(pay_id, dataBean);
                            dialogInterface.dismiss();
                        } else {

                            String pay_id = "1";
                            getWeiXinOrZhiFuBao(pay_id, dataBean);
                            dialogInterface.dismiss();
                        }
                    }
                });
        builder.create().show();
    }
    TishiDialog tishiDialog;

    public void getNet_CaoZuo(String form_id, String code, int position) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(getActivity()).getString("app_token", "0"));
        map.put("shop_form_id", form_id);//全部

        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(Urls.HOME_PICTURE_HOME)
                .tag(getActivity())//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        //orderListAdapter.remove(position);
                        UIHelper.ToastMessage(getActivity(), "操作成功");
                        //pageNumber = 0;
                        //getNet();
                        smartRefreshLayout.autoRefresh();
                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        super.onError(response);
                        String str = response.getException().getMessage();
                        tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {
                                tishiDialog.dismiss();
                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {

                                getActivity().finish();
                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        tishiDialog.setTextContent(str);
                        tishiDialog.show();
                    }
                });

    }

    /**
     * 两个按钮的 dialog
     */
    private void showDngDanCaoZuo(OrderListModel.DataBean dataBean, int position, String quXiaoDingDanHuaShu, String code) {

        builder = new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.logi_icon).setTitle("订单操作")
                .setMessage(quXiaoDingDanHuaShu).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getActivity(), "确定按钮", Toast.LENGTH_LONG).show();
                        getNet_CaoZuo(dataBean.getShop_form_id(), code, position);


                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }

}

