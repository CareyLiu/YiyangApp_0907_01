package com.yiyang.cn.activity.dingdan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuangou.TuanGouShangPinDetailsActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.DingDanDetailsModel;
import com.yiyang.cn.model.OrderListModel;
import com.yiyang.cn.model.YuZhiFuModel;
import com.yiyang.cn.model.YuZhiFuModel_AliPay;
import com.yiyang.cn.pay_about.alipay.PayResult;
import com.yiyang.cn.util.PaySuccessUtils;
import com.yiyang.cn.util.Tools;
import com.yiyang.cn.util.phoneview.sample.ImageShow_OnePictureActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;

public class TuanGouDingDanDetails extends BaseActivity {

    @BindView(R.id.iv_product)
    ImageView ivProduct;
    @BindView(R.id.tv_jieshao)
    TextView tvJieshao;
    @BindView(R.id.ll_taocan)
    LinearLayout llTaocan;
    @BindView(R.id.iv_erweima)
    ImageView ivErweima;
    @BindView(R.id.tv_yanzhengma)
    TextView tvYanzhengma;
    @BindView(R.id.cl_erweima_daishiyong)
    ConstraintLayout clErweimaDaishiyong;
    @BindView(R.id.cl_yishiyong)
    ConstraintLayout clYishiyong;
    @BindView(R.id.ll_quzhifu)
    LinearLayout llQuzhifu;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.ll_erweima)
    LinearLayout llErweima;
    @BindView(R.id.ll_taocanxiangqing)
    LinearLayout llTaocanxiangqing;
    @BindView(R.id.tv_quzhifu)
    TextView tvQuzhifu;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.cl_taocan)
    ConstraintLayout clTaocan;

    private OrderListModel.DataBean dataBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBean = (OrderListModel.DataBean) getIntent().getSerializableExtra("dataBean");
        getNet(dataBean.getShop_form_id(), dataBean.getUser_pay_check(), dataBean.getWares_go_type(), dataBean.getWares_type());
        progressDialog = new ProgressDialog(mContext);
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet(dataBean.getShop_form_id(), dataBean.getUser_pay_check(), dataBean.getWares_go_type(), dataBean.getWares_type());
            }
        });
        srLSmart.setEnableLoadMore(false);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tuangou_dingdan_details;
    }

    public static void actionStart(Context context, OrderListModel.DataBean dataBean) {
        Intent intent = new Intent(context, TuanGouDingDanDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("dataBean", dataBean);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("订单详情");
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

    //  private String
    public void getNet(String dingDanId, String dingDanZhuangTai, String xiaoFeiFangShi, String dingDanLeixing) {
        //code	请求码(04162)	6	是
        // key	身份标识	10	是
        //token	token		是
        //shop_form_id	订单id		是
        //user_pay_check	订单状态		是
        //wares_go_type	消费方式：2.邮递3.到店		是
        //wares_type	订单类型：1.普通2.拼单 3.团购		是

        Map<String, String> map = new HashMap<>();
        map.put("code", "04162");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("shop_form_id", dingDanId);
        map.put("user_pay_check", dingDanZhuangTai);
        map.put("wares_go_type", xiaoFeiFangShi);
        map.put("wares_type", dingDanLeixing);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<DingDanDetailsModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DingDanDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DingDanDetailsModel.DataBean>> response) {
                        srLSmart.finishRefresh();
                        clTaocan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        });
                        DingDanDetailsModel.DataBean dataBean = response.body().data.get(0);

                        Glide.with(TuanGouDingDanDetails.this).load(dataBean.getIndex_photo_url()).into(ivProduct);

                        tvPrice.setText("￥" + dataBean.getForm_product_money());

                        llInfo.removeAllViews();
                        if (dataBean.getOrder_info_arr() != null) {
                            for (int i = 0; i < dataBean.getOrder_info_arr().size(); i++) {
                                View view = View.inflate(TuanGouDingDanDetails.this, R.layout.layout_view_info, null);
                                TextView tv = view.findViewById(R.id.tv_text);
                                tv.setText(dataBean.getOrder_info_arr().get(i));
                                llInfo.addView(view);
                            }
                        }
                        /**
                         * 买家状态:1.待付款 2.待分享(拼)3.待发货 4.已发货 5.到店消费6.待评价 7.完成 8.退款申请 9.退款中 10.退款/退货11订单失效
                         */
                        switch (dataBean.getUser_pay_check()) {
                            case "1"://待付款
                                llErweima.setVisibility(View.GONE);
                                llTaocanxiangqing.setVisibility(View.VISIBLE);
                                clErweimaDaishiyong.setVisibility(View.GONE);

                                llTaocan.removeAllViews();
                                for (int i = 0; i < dataBean.getTaocan_list().size(); i++) {
                                    View view = View.inflate(mContext, R.layout.item_tuangou_dingdan_taocan, null);

                                    TextView tvMing = view.findViewById(R.id.tv_taocanming);
                                    TextView tvNumber = view.findViewById(R.id.tv_number);
                                    TextView tvprice = view.findViewById(R.id.tv_jiage);

                                    tvMing.setText(dataBean.getTaocan_list().get(i).getMenu_text());
                                    tvNumber.setText("x" + dataBean.getTaocan_list().get(i).getMenu_count());
                                    tvprice.setText("￥" + dataBean.getTaocan_list().get(i).getMenu_pay());

                                    llTaocan.addView(view);
                                }

                                tvQuzhifu.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        showSingSelect(dataBean);
                                    }
                                });
                                break;
                            case "2":
                                break;
                            case "3"://待使用


                                break;
                            case "4"://已使用
                                break;
                            case "5":
                                clYishiyong.setVisibility(View.GONE);
                                llErweima.setVisibility(View.VISIBLE);
                                llTaocanxiangqing.setVisibility(View.VISIBLE);
                                tvQuzhifu.setVisibility(View.GONE);
                                clErweimaDaishiyong.setVisibility(View.VISIBLE);
                                Bitmap b = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
                                Bitmap bitmap = Tools.createQRImage(mContext, dataBean.getPay_code(), b);
                                ivErweima.setImageBitmap(bitmap);
                                ivErweima.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String picture = Tools.converIconToString(bitmap);
                                        ImageShow_OnePictureActivity.actionStart(mContext, picture);
                                    }
                                });

                                llTaocan.removeAllViews();
                                for (int i = 0; i < dataBean.getTaocan_list().size(); i++) {
                                    View view = View.inflate(mContext, R.layout.item_tuangou_dingdan_taocan, null);

                                    TextView tvMing = view.findViewById(R.id.tv_taocanming);
                                    TextView tvNumber = view.findViewById(R.id.tv_number);
                                    TextView tvprice = view.findViewById(R.id.tv_jiage);

                                    tvMing.setText(dataBean.getTaocan_list().get(i).getMenu_text());
                                    tvNumber.setText("x" + dataBean.getTaocan_list().get(i).getMenu_count());
                                    tvprice.setText("￥" + dataBean.getTaocan_list().get(i).getMenu_pay());

                                    llTaocan.addView(view);
                                }
                                break;

                            case "6"://待评价


                                break;

                        }
                    }
                });
    }

    /**
     * 单选 dialog
     */
    private int choice;
    ProgressDialog progressDialog;
    private String appId;//支付id 给支付宝

    private void showSingSelect(DingDanDetailsModel.DataBean dataBean) {

        //默认选中第一个
        final String[] items = {"微信", "支付宝"};
        choice = -1;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setIcon(R.mipmap.logi_icon).setTitle("支付方式")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice = i;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice == -1) {
                            Toast.makeText(mContext, "窗口关闭，未选择支付方式", Toast.LENGTH_LONG).show();
                        } else if (items[choice].equals("微信")) {
                            progressDialog.setMessage("正在拉起支付，请稍后...");
                            progressDialog.show();
                            //微信
                            String pay_id = "2";
                            getWeiXinOrZhiFuBao(pay_id, dataBean);
                            dialogInterface.dismiss();
                        } else {
                            progressDialog.setMessage("正在拉起支付，请稍后...");
                            progressDialog.show();
                            String pay_id = "1";
                            getWeiXinOrZhiFuBao(pay_id, dataBean);
                            dialogInterface.dismiss();
                        }
                    }
                });
        builder.create().show();
    }

    private IWXAPI api;
    private String form_id;//订单id
    private static final int SDK_PAY_FLAG = 1;

    private void getWeiXinOrZhiFuBao(String pay_id, DingDanDetailsModel.DataBean dataBean) {
        //   productDetailsForJava.get(0).shop_form_text = etLiuYan.getText().toString();
//        form_product_id 	购物车产品id
//        shop_product_id	商品套餐id
//        pay_count	购买数量
//        shop_form_text	订单备注(买家留言)
//         wares_go_type	消费方式：2.邮递 3.到店
//
//        DaiFuKuanDingDanActivity.ProductDetails productDetails = new DaiFuKuanDingDanActivity.ProductDetails();
//        productDetails.shop_product_id = dataBean.getShop_product_id();
//        productDetails.pay_count = dataBean.getPay_count();
//        productDetails.shop_form_text = dataBean.getShop_form_text();
//        productDetails.wares_go_type = dataBean.getWares_go_type();
//        productDetailsForJava.add(productDetails);

        //OrderListModel.DataBean dataBean = orderListAdapter.getData().get(position);
        if (pay_id.equals("1")) {//1支付宝
            Map<String, Object> map = new HashMap<>();
            map.put("key", Urls.key);
            map.put("token", UserManager.getManager(mContext).getAppToken());
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
                    .tag(mContext)//
                    .upJson(myHeaderInfo)
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel_AliPay.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {
                            progressDialog.dismiss();
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
            map.put("token", UserManager.getManager(mContext).getAppToken());
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
                    .tag(mContext)//
                    .upJson(myHeaderInfo)
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel.DataBean>> response) {
                            api = WXAPIFactory.createWXAPI(mContext, response.body().data.get(0).getPay().getAppid());
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
     * 支付宝支付业务
     */
    public void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) mContext);
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

    /**
     * 微信支付
     *
     * @param out
     */
    private void goToWeChatPay(YuZhiFuModel.DataBean out) {
        api = WXAPIFactory.createWXAPI(mContext, out.getPay().getAppid());
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
                        PaySuccessUtils.getNet(mContext, form_id);
                        UIHelper.ToastMessage(mContext, "支付成功", Toast.LENGTH_SHORT);
                        //  cnt.finish();

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                        PaySuccessUtils.getNetFail(mContext, form_id);

                    }
                    break;
                }
                default:
                    break;
            }
        }

    };


}
