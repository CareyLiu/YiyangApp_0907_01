package com.yiyang.cn.activity.dingdan;

import android.annotation.SuppressLint;
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

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.DingDanDetailsModel;
import com.yiyang.cn.model.OrderListModel;
import com.yiyang.cn.model.YuZhiFuModel;
import com.yiyang.cn.model.YuZhiFuModel_AliPay;
import com.yiyang.cn.pay_about.alipay.PayResult;
import com.yiyang.cn.util.PaySuccessUtils;
import com.yiyang.cn.util.Tools;
import com.yiyang.cn.util.phoneview.sample.ImageShowActivity;
import com.yiyang.cn.util.phoneview.sample.ImageShow_OnePictureActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;

//订单详情
public class DaiFuKuanDingDanActivity extends BaseActivity {


    @BindView(R.id.tv_dingdan_zhuangtai)
    TextView tvDingdanZhuangtai;
    @BindView(R.id.conlayout_1)
    ConstraintLayout conlayout1;
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.constrain2)
    ConstraintLayout constrain2;
    @BindView(R.id.view_1)
    View view1;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_shop)
    TextView tvShop;
    @BindView(R.id.constrain3)
    ConstraintLayout constrain3;
    @BindView(R.id.iv_product)
    ImageView ivProduct;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_kuanshi)
    TextView tvKuanshi;
    @BindView(R.id.tv_danjia)
    TextView tvDanjia;
    @BindView(R.id.tv_paycount)
    TextView tvPaycount;
    @BindView(R.id.constrain4)
    ConstraintLayout constrain4;
    @BindView(R.id.tv_shifujine)
    TextView tvShifujine;
    @BindView(R.id.constrain5)
    ConstraintLayout constrain5;
    @BindView(R.id.view_2)
    View view2;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.tv_go_pay)
    TextView tvGoPay;
    @BindView(R.id.tv_quxiaodingdan)
    TextView tvQuxiaodingdan;
    @BindView(R.id.cl_daifukuan)
    ConstraintLayout clDaifukuan;
    @BindView(R.id.tv_daifahuo_shenqingtuikuan)
    TextView tvDaifahuoShenqingtuikuan;
    @BindView(R.id.tv_daifahuo_cuifahuo)
    TextView tvDaifahuoCuifahuo;
    @BindView(R.id.cl_daifahuo)
    ConstraintLayout clDaifahuo;
    @BindView(R.id.tvdaipingjia)
    TextView tvdaipingjia;
    @BindView(R.id.tv_shangchudingdan)
    TextView tvShangchudingdan;
    @BindView(R.id.cl_daiingjia)
    ConstraintLayout clDaiingjia;
    @BindView(R.id.tv_yanzhengma)
    TextView tvYanzhengma;
    @BindView(R.id.iv_yanzhengma)
    ImageView ivYanzhengma;
    @BindView(R.id.cl_erweima)
    ConstraintLayout clErweima;
    @BindView(R.id.view_mengban)
    View viewMengban;
    @BindView(R.id.iv_yiwancheng)
    ImageView ivYiwancheng;
    private Context cnt;
    private OrderListModel.DataBean dataBean;
    private IWXAPI api;
    private String form_id;//订单id

    /**
     * ArrayList<String> list = new ArrayList<>();
     * list.add(response.body().data.get(0).getBannerList().get(position).getImg_url());
     * ImageShowActivity.actionStart(ZiJianShopMallDetailsActivity.this, list);
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cnt = DaiFuKuanDingDanActivity.this;

        dataBean = (OrderListModel.DataBean) getIntent().getSerializableExtra("dataBean");
        progressDialog = new ProgressDialog(cnt);
        //code	请求码(04162)	6	是
        // key	身份标识	10	是
        //token	token		是
        //shop_form_id	订单id		是
        //user_pay_check	订单状态		是
        //wares_go_type	消费方式：2.邮递3.到店		是
        //wares_type	订单类型：1.普通2.拼单 3.团购		是
        clDaifukuan.setVisibility(View.GONE);
        clDaifahuo.setVisibility(View.GONE);
        if (dataBean.getUser_pay_check() != null) {
            switch (dataBean.getUser_pay_check()) {
                case "1":
                    // tv_title.setText("待付款");
                    break;
                case "2":
                    tv_title.setText("待分享");
                    break;
                case "3":
                    //  tv_title.setText("待发货");
                    clDaifahuo.setVisibility(View.VISIBLE);
                    break;
                case "4":
                    //  tv_title.setText("已发货");
                    break;
                case "5":

                    //  tv_title.setText("到店消费");
                    clErweima.setVisibility(View.VISIBLE);

                    break;
                case "6":
                    //  tv_title.setText("待评价");
                    clDaiingjia.setVisibility(View.VISIBLE);
                    break;
                case "7":
                    // tv_title.setText("完成");
                    break;
                case "8":
                    // tv_title.setText("退款申请");
                    break;
                case "9":
                    //  tv_title.setText("退款中");
                    break;
                case "10":
                    //   tv_title.setText("退款/退货中");
                    break;
                case "11":
                    //   tv_title.setText("失效订单");
                    break;
            }
        }


        getNet(dataBean.getShop_form_id(), dataBean.getUser_pay_check(), dataBean.getWares_go_type(), dataBean.getWares_type());
        tvGoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (dataBean.getUser_pay_check()) {

                    case "1":
//                        conlayout1.setBackgroundResource(R.mipmap.qianbaobeijing);
//                        tvDingdanZhuangtai.setText("等待付款");
                        showSingSelect(dataBean);
                        break;

                    case "11":
                        //订单失效
//                        tvDingdanZhuangtai.setText("订单失效");
//                        conlayout1.setBackgroundResource(R.mipmap.qianbaobeijing);
//                        tvQuxiaodingdan.setVisibility(View.VISIBLE);
//                        tvQuxiaodingdan.setText("删除订单");

                        showDngDanCaoZuo(dataBean, "是否删除订单", "04157");
                        break;

                    case "6":

                        break;


                }

            }
        });


        tvQuxiaodingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dataBean.getUser_pay_check()) {

                    case "1":
//                        conlayout1.setBackgroundResource(R.mipmap.qianbaobeijing);
//                        tvDingdanZhuangtai.setText("等待付款");
                        showDngDanCaoZuo(dataBean, "是否取消订单", "04156");
                        break;

                    case "11":
                        //订单失效
//                        tvDingdanZhuangtai.setText("订单失效");
//                        conlayout1.setBackgroundResource(R.mipmap.qianbaobeijing);
//                        tvQuxiaodingdan.setVisibility(View.VISIBLE);
//                        tvQuxiaodingdan.setText("删除订单");

                        showDngDanCaoZuo(dataBean, "是否删除订单", "04157");
                        break;


                }

            }
        });


        //待发货
        tvDaifahuoCuifahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNetCuiFaHuo(dataBean);
            }
        });

        tvDaifahuoShenqingtuikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShenQingTuiKuanActivity.actionStart(DaiFuKuanDingDanActivity.this, dataBean.getShop_form_id(), dataBean.getTotal_money(), dataBean.getUser_pay_check());
            }
        });

        tvdaipingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                AccessActivity.actionStart(DaiFuKuanDingDanActivity.this, dataBean.getIndex_photo_url(), dataBean.getShop_form_id());
            }
        });

        tvShangchudingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDngDanCaoZuo(dataBean, "是否删除订单", "04157");
            }
        });
    }
    TishiDialog tishiDialog;
    private void getNetCuiFaHuo(OrderListModel.DataBean dataBean) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04167");
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(DaiFuKuanDingDanActivity.this).getString("app_token", "0"));
        map.put("shop_form_id", dataBean.getShop_form_id());

        // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(HOME_PICTURE_HOME)
                .tag(DaiFuKuanDingDanActivity.this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        UIHelper.ToastMessage(DaiFuKuanDingDanActivity.this, response.body().msg);
                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        super.onError(response);
                        //   UIHelper.ToastMessage(getActivity(), response.body().msg);

                        String str = response.getException().getMessage();
                        tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {
                                tishiDialog.dismiss();
                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {

                                finish();
                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        tishiDialog.setTextContent(str);
                        tishiDialog.show();

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


    @Override
    public int getContentViewResId() {
        return R.layout.activity_dai_fu_kuan_ding_dan;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, OrderListModel.DataBean dataBean) {
        Intent intent = new Intent(context, DaiFuKuanDingDanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("dataBean", dataBean);
        context.startActivity(intent);
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String dingdanId) {
        Intent intent = new Intent(context, DaiFuKuanDingDanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("dingdanId", dingdanId);
        context.startActivity(intent);
    }

    private int choice;

    /**
     * 单选 dialog
     */
    private void showSingSelect(OrderListModel.DataBean dataBean) {

        //默认选中第一个
        final String[] items = {"微信", "支付宝"};
        choice = -1;
        AlertDialog.Builder builder = new AlertDialog.Builder(DaiFuKuanDingDanActivity.this).setIcon(R.mipmap.logi_icon).setTitle("支付方式")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice = i;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice == -1) {
                            Toast.makeText(DaiFuKuanDingDanActivity.this, "窗口关闭，未选择支付方式", Toast.LENGTH_LONG).show();
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

    ProgressDialog progressDialog;
    private String appId;//支付id 给支付宝

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
        productDetailsForJava.add(productDetails);

        //OrderListModel.DataBean dataBean = orderListAdapter.getData().get(position);
        if (pay_id.equals("1")) {//1支付宝
            Map<String, Object> map = new HashMap<>();
            map.put("key", Urls.key);
            map.put("token", UserManager.getManager(cnt).getAppToken());
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
                    .tag(cnt)//
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
            map.put("token", UserManager.getManager(cnt).getAppToken());
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
                    .tag(cnt)//
                    .upJson(myHeaderInfo)
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel.DataBean>> response) {
                            api = WXAPIFactory.createWXAPI(cnt, response.body().data.get(0).getPay().getAppid());
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
        api = WXAPIFactory.createWXAPI(cnt, out.getPay().getAppid());
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

    List<ProductDetails> productDetailsForJava = new ArrayList<>();

    private class ProductDetails {
        private String form_product_id;
        private String shop_product_id;
        private String pay_count;
        private String shop_form_text;
        private String wares_go_type;
    }

    private static final int SDK_PAY_FLAG = 1;


    /**
     * 支付宝支付业务
     */
    public void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(DaiFuKuanDingDanActivity.this);
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
                        PaySuccessUtils.getNet(cnt, form_id);
                        UIHelper.ToastMessage(cnt, "支付成功", Toast.LENGTH_SHORT);
                        //  cnt.finish();

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(cnt, "支付失败", Toast.LENGTH_SHORT).show();
                        PaySuccessUtils.getNetFail(cnt, form_id);

                    }
                    break;
                }
                default:
                    break;
            }
        }

    };


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
                        /**
                         * msg_code	返回码
                         * msg	应答描述
                         * data	应答数据
                         * shop_form_id	订单id
                         * inst_id	卖家机构id
                         * inst_img_url	卖家头像
                         * inst_name	卖家名称
                         * inst_accid	卖家accid
                         * wares_type	订单类型：1.普通2.拼单 3.团购
                         * user_pay_check	付款状态
                         * user_pay_check_name	付款状态名称
                         * pay_check_name	款项名称
                         * pay_money	总金额
                         * shop_product_id	商品颜色分类id
                         * index_photo_url	商品颜色分类封面url
                         * shop_product_title	商品标题
                         * product_title	商品颜色分类标题
                         * form_product_money	商品单价
                         * pay_count	购买数量
                         * form_money_go	运费
                         * inst_x	卖家坐标x（到店消费显示）
                         * inst_y	卖家坐标y（到店消费显示）
                         * inst_addr_all	卖家详细地址（到店消费显示）
                         * pay_code	到店消费二维码（到店消费显示）
                         * pay_code_state	二维码状态：1.未使用2.已使用
                         * user_name	买家姓名（邮递消费显示）
                         * user_phone	买家联系电话（邮递消费显示）
                         * user_addr_all	买家收货地址（邮递消费显示）
                         * express_id	快递公司id（邮递消费显示）
                         * express_no	快递单号
                         * express_name	快递公司名称
                         * express_url	查看物流url
                         * order_info_arr	订单信息数组
                         */
                        DingDanDetailsModel.DataBean dataBean = response.body().data.get(0);
                        DaiFuKuanDingDanActivity.this.dataBean.setUser_pay_check(dataBean.getUser_pay_check());


                        if (dataBean.getOperate_type().equals("26")) {
                            tvAddress.setText(response.body().data.get(0).getInst_addr_all());
                        } else {
                            tvAddress.setText(response.body().data.get(0).getUser_addr_all());
                        }

                        tvShop.setText(response.body().data.get(0).getInst_name());
                        Glide.with(DaiFuKuanDingDanActivity.this).load(dataBean.getInst_img_url()).into(ivImage);
                        Glide.with(DaiFuKuanDingDanActivity.this).load(dataBean.getIndex_photo_url()).into(ivProduct);//商品图
                        tvTitle.setText(dataBean.getShop_product_title());
                        tvKuanshi.setText(dataBean.getProduct_title());
                        tvDanjia.setText(dataBean.getForm_product_money());
                        //消费方式：2.邮递3.到店4.直接下单
                        if (dingDanLeixing != null) {
                            if (dingDanLeixing.equals("1")) {
                                tvShifujine.setText("实付：¥" + dataBean.getPay_money() + "（运费:" + dataBean.getForm_money_go() + ")");
                            } else if (dingDanLeixing.equals("3")) {
                                tvShifujine.setText("实付：¥" + dataBean.getPay_money() + "(到店)");


                            } else if (dingDanLeixing.equals("4")) {
                                tvShifujine.setText("实付：¥" + dataBean.getPay_money());
                            }
                        }

                        if (dataBean.getOrder_info_arr() != null) {


                            for (int i = 0; i < dataBean.getOrder_info_arr().size(); i++) {
                                View view = View.inflate(DaiFuKuanDingDanActivity.this, R.layout.layout_view_info, null);
                                TextView tv = view.findViewById(R.id.tv_text);
                                tv.setText(dataBean.getOrder_info_arr().get(i));
                                llInfo.addView(view);

                            }
                        }
                        tvPaycount.setText("x" + dataBean.getPay_count());

//                        if (dataBean.getUser_pay_check().equals(""))
//                        user_pay_check
                        /**
                         * 买家状态:1.待付款 2.待分享(拼)3.待发货 4.已发货 5.到店消费6.待评价 7.完成 8.退款申请 9.退款中 10.退款/退货 11订单失效
                         */

                        tvQuxiaodingdan.setVisibility(View.GONE);
                        tvGoPay.setVisibility(View.GONE);
                        //conlayout1.setBackgroundResource(0);
                        switch (dataBean.getUser_pay_check()) {

                            case "1":
                                tvQuxiaodingdan.setVisibility(View.VISIBLE);
                                tvGoPay.setVisibility(View.VISIBLE);
                                tvGoPay.setText("去支付");
                                tvQuxiaodingdan.setText("取消订单");

                                conlayout1.setBackgroundResource(R.mipmap.qianbaobeijing);
                                tvDingdanZhuangtai.setText("等待付款");

                                if (dataBean.getOperate_type().equals("29")) {
                                    constrain2.setVisibility(View.GONE);
                                    view1.setVisibility(View.GONE);
                                }

                                break;
                            case "2":
                                tvDingdanZhuangtai.setText("等待您的分享");
                                break;
                            case "3":
                                tvDingdanZhuangtai.setText("等待卖家发货");
                                tvQuxiaodingdan.setVisibility(View.VISIBLE);
                                tvGoPay.setVisibility(View.VISIBLE);
                                tvGoPay.setText("去支付");
                                tvQuxiaodingdan.setText("取消订单");
                                conlayout1.setBackgroundResource(R.mipmap.wodedingdan_daifahuo);
                                break;
                            case "4":
                                tvDingdanZhuangtai.setText("已发货");

                                conlayout1.setBackgroundResource(R.mipmap.order_yifahuo);
                                break;
                            case "5":

                                conlayout1.setBackgroundResource(R.mipmap.daishiyong);
                                tvDingdanZhuangtai.setText("到店消费");
                                break;
                            case "6":
                                constrain2.setVisibility(View.GONE);
                                conlayout1.setBackgroundResource(R.mipmap.jiaoyichenggong);
                                tvDingdanZhuangtai.setText("待评价");
                                if (dataBean.getWares_type().equals("3")) {
                                    clErweima.setVisibility(View.VISIBLE);
                                    constrain2.setVisibility(View.VISIBLE);
                                    conlayout1.setBackgroundResource(R.mipmap.yishiyong);
                                }
                                break;
                            case "7":
                                conlayout1.setBackgroundResource(R.mipmap.jiaoyichenggong);
                                tvDingdanZhuangtai.setText("订单已完成");
                                break;
                            case "8":
                                tvDingdanZhuangtai.setText("申请退款中");
                                break;
                            case "9":
                                tvDingdanZhuangtai.setText("退款中");
                                break;
                            case "10":
                                tvDingdanZhuangtai.setText("退货/退款中");
                                break;
                            case "11":
                                //订单失效
                                constrain2.setVisibility(View.GONE);
                                tvDingdanZhuangtai.setText("订单失效");
                                conlayout1.setBackgroundResource(R.mipmap.dingdan_details_x);
                                tvQuxiaodingdan.setVisibility(View.VISIBLE);
                                tvQuxiaodingdan.setText("删除订单");
                                break;

                            case "99":
                                constrain2.setVisibility(View.GONE);
                                tvDingdanZhuangtai.setText("订单失效");
                                conlayout1.setBackgroundResource(R.mipmap.dingdan_details_x);
                                tvQuxiaodingdan.setVisibility(View.VISIBLE);
                                tvQuxiaodingdan.setText("删除订单");

                                break;
                        }


                        setDaoDian(dataBean);

                    }
                });
    }

    private void setDaoDian(DingDanDetailsModel.DataBean dataBean) {
        tvYanzhengma.setText("验证码：" + dataBean.getPay_code());
        Bitmap b = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        Bitmap bitmap = Tools.createQRImage(this, dataBean.getPay_code(), b);
        ivYanzhengma.setImageBitmap(bitmap);
//pay_code_state	二维码状态：1.未使用2.已使用
        if (dataBean.getPay_code_state().equals("1")) {

            viewMengban.setVisibility(View.GONE);
            ivYiwancheng.setVisibility(View.GONE);

        } else if (dataBean.getPay_code_state().equals("2")) {
            viewMengban.setVisibility(View.VISIBLE);
            ivYiwancheng.setVisibility(View.VISIBLE);
        }

        ivYanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String picture = Tools.converIconToString(bitmap);
                ImageShow_OnePictureActivity.actionStart(mContext, picture);
            }
        });

    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        /**
         * user_pay_check	买家状态:1.待付款 2.待分享(拼)3.待发货 4.已发货 5.到店消费6.待评价 7.完成 8.退款申请 9.退款中 10.退款/退货11订单失效
         */

        tv_title.setTextSize(17);
        tv_title.setText("订单详情");
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

    private AlertDialog.Builder builder;

    private void showDngDanCaoZuo(OrderListModel.DataBean dataBean, String quXiaoDingDanHuaShu, String code) {

        builder = new AlertDialog.Builder(mContext).setIcon(R.mipmap.logi_icon).setTitle("订单操作")
                .setMessage(quXiaoDingDanHuaShu).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Toast.makeText(getActivity(), "确定按钮", Toast.LENGTH_LONG).show();
                        //    getNet(dataBean.getShop_form_id(), dataBean.getUser_pay_check(), dataBean.getWares_go_type(), dataBean.getWares_type());
                        getNet_CaoZuo(dataBean.getShop_form_id(), code);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }

    public void getNet_CaoZuo(String form_id, String code) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(mContext).getString("app_token", "0"));
        map.put("shop_form_id", form_id);//全部

        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(HOME_PICTURE_HOME)
                .tag(mContext)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        //orderListAdapter.remove(position);
                        //  getNet(dataBean.getShop_form_id(), dataBean.getUser_pay_check(), dataBean.getWares_go_type(), dataBean.getWares_type());
                        // getNet(dataBean.getShop_form_id(), "", "", "");

                        if (code.equals("04157")) {//取消订单

                            UIHelper.ToastMessage(cnt, "操作成功");
                            finish();
                        } else if (code.equals("04156")) {
                            UIHelper.ToastMessage(cnt, "操作成功");
                            getNet(dataBean.getShop_form_id(), "", "", "");
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        super.onError(response);
                    }
                });

    }


}


