package com.yiyang.cn.activity.taokeshagncheng;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.AddressActivity;
import com.yiyang.cn.adapter.zijian_adapter.QueRenDingDanAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.AddressListModel;
import com.yiyang.cn.model.AddressModel;
import com.yiyang.cn.model.GoodsDetails_f;
import com.yiyang.cn.model.YuZhiFuModel;
import com.yiyang.cn.model.YuZhiFuModel_AliPay;
import com.yiyang.cn.pay_about.alipay.PayResult;
import com.yiyang.cn.project_A.zijian_interface.QueRenDingDanInterface;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.NeedYanZheng;
import com.yiyang.cn.util.PaySuccessUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class QueRenDingDanActivity extends BaseActivity implements QueRenDingDanInterface {
    GoodsDetails_f.DataBean dataBean = new GoodsDetails_f.DataBean();
    List<AddressListModel.DataBean> addRessDataBean = new ArrayList<>();
    YuZhiFuModel.DataBean dataBean_pay;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.ll_now_pay)
    LinearLayout llNowPay;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.constrain)
    ConstraintLayout constrain;
    private QueRenDingDanAdapter queRenDingDanAdapter;
    List<GoodsDetails_f.DataBean.ProductListBean> productListBeans;
    Response<AppResponse<AddressListModel.DataBean>> response;
    private String position;
    private String title;
    private String shopName;
    private String imageUrl;
    private String available_balance;

    String pay_id = "2";//????????????-- 1 ????????? 2 ??????
    String payType = "4";//1 ????????? 4 ??????
    private String appId;//??????id ????????????
    private IWXAPI api;
    private String form_id;//??????id
    GoodsDetails_f.DataBean.ProductListBean productListBean;
    private String wares_go_type = "2";//?????? -- ????????????
    String warseId;
    boolean visibleOrGon;
    AddressModel.DataBean addressDataBean;
    public String shouHuoAddress = "0";//0??? 1 ???
    ProductDetails productDetails;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(QueRenDingDanActivity.this);
        dataBean = (GoodsDetails_f.DataBean) getIntent().getSerializableExtra("dataBean");
        warseId = PreferenceHelper.getInstance(QueRenDingDanActivity.this).getString(App.PRODUCTID, "NONE");
        this.position = getIntent().getStringExtra("position");
        title = getIntent().getStringExtra("title");
        imageUrl = getIntent().getStringExtra("imageUrl");
        productListBean = dataBean.getProductList().get(Integer.parseInt(position));
        String count = getIntent().getStringExtra("count");
        shopName = getIntent().getStringExtra("shopName");
        productListBean.setShopImage(imageUrl);
        productListBean.setCount(count);
        productListBean.setProduct_biaoti(title);
        productListBean.setShopName(shopName);
        productListBeans = new ArrayList<>();
        productListBeans.add(productListBean);

        getNet();


        /**
         *
         form_product_id 	???????????????id		???
         shop_product_id	????????????id	20	???
         pay_count	????????????		???
         shop_form_text	????????????(????????????)		???
         wares_go_type	???????????????2.?????? 3.??????		???
         */
        productDetailsForJava = new ArrayList<>();

        productDetails = new ProductDetails();

        productDetails.form_product_id = "";//???????????????id ??????
        productDetails.pay_count = productListBean.getDetails_count();
        productDetails.shop_product_id = productListBean.getShop_product_id();
        wares_go_type = getIntent().getStringExtra("wares_go_type");
        productDetails.wares_go_type = wares_go_type;
        productDetails.pay_count = count.toString();
        productDetailsForJava.add(productDetails);


        // productDetailsForJava.get()


        llNowPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (shouHuoAddress.equals("0")) {
                    //??????????????????
                    UIHelper.ToastMessage(QueRenDingDanActivity.this, "?????????????????????????????????????????????");
                    return;
                }

                if (visibleOrGon) {
                    if (StringUtils.isEmpty(etYaoQingMa.getText().toString())) {

                        UIHelper.ToastMessage(QueRenDingDanActivity.this, "????????????????????????????????????");
                    } else {
                        getNet_butian(etYaoQingMa.getText().toString());
                    }

                } else {
                    progressDialog.setMessage("?????????????????????????????????...");
                    progressDialog.show();
                    //????????????
                    PreferenceHelper.getInstance(QueRenDingDanActivity.this).putString(App.ZIYING_PAY, "ZIYING_PAY");
                    getWeiXinOrZhiFuBao();
                }
            }
        });

        setPrice();//??????????????????
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ZIYING_PAY_FAIL) {
                    // x5WebView.loadUrl("javascript:java_js('appToJsPaySuccess')");
                    //  form_id = "";
                    // PaySuccessUtils.getNet(XinTuanYouShengChengDingDanActivity.this, form_id);
                    //  finish();
                    UIHelper.ToastMessage(QueRenDingDanActivity.this, "??????????????????");
                    finish();
                } else if (message.type == ConstanceValue.MSG_GETADDRESS) {
                    addressDataBean = (AddressModel.DataBean) message.content;
                    UIHelper.ToastMessage(QueRenDingDanActivity.this, "address" + addressDataBean.getAddr());
                    shouHuoAddress = "1";
                    tvNone.setVisibility(View.GONE);
                    tvName.setVisibility(View.VISIBLE);
                    tvAddr.setVisibility(View.VISIBLE);
                    users_addr_id = addressDataBean.getUsers_addr_id();
                    tvName.setText(addressDataBean.getUser_name());
                    tvAddr.setText(addressDataBean.getAddr_all());
                    queRenDingDanAdapter.notifyDataSetChanged();

                } else if (message.type == ConstanceValue.MSG_NONEADDRESS) {
                    tvNone.setVisibility(View.VISIBLE);
                    tvName.setVisibility(View.GONE);
                    tvAddr.setVisibility(View.GONE);
                    users_addr_id = "";
                    queRenDingDanAdapter.notifyDataSetChanged();
                } else if (message.type == ConstanceValue.MSG_ZIYING_PAY) {
                    PaySuccessUtils.getNet(QueRenDingDanActivity.this, form_id);
                    UIHelper.ToastMessage(QueRenDingDanActivity.this, "??????????????????");
                    finish();
                }
            }
        }));


    }


    private void getNet_butian(String et) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04343");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(QueRenDingDanActivity.this).getAppToken());
        // map.put("shop_product_id", productId);
        //map.put("wares_id", warseId);
        map.put("invitation_code", et);

        Log.i("taoken_gg", UserManager.getManager(QueRenDingDanActivity.this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<Object>> response) {
                        //????????????

                        getWeiXinOrZhiFuBao();
                        visibleOrGon = false;
                        rlYaoQingMa.setVisibility(View.GONE);
                        PreferenceHelper.getInstance(QueRenDingDanActivity.this).putString(App.SHIFOUYOUSHANGJI, "1");

                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        // AlertUtil.t(QueRenDingDanActivity.this, response.getException().getMessage());
                        UIHelper.ToastMessage(QueRenDingDanActivity.this, "?????????????????????");
                    }
                });
    }

    private static final int SDK_PAY_FLAG = 1;


    /**
     * ?????????????????????
     */
    public void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(QueRenDingDanActivity.this);
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
                     ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                     */
                    String resultInfo = payResult.getResult();// ?????????????????????????????????
                    String resultStatus = payResult.getResultStatus();
                    // ??????resultStatus ???9000?????????????????????
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        Notice n = new Notice();
//                        n.type = ConstanceValue.MSG_DALIBAO_SUCCESS;
//                        //  n.content = message.toString();
//                        RxBus.getDefault().sendRx(n);
//                        finish();
                        // ??????????????????????????????????????????????????????????????????????????????
                        //   Toast.makeText(DaLiBaoZhiFuActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                        //   finish();
                        // ????????????????????????????????????????????????  ?????????????????? ?????????  ?????????????????????

                        // MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(DaLiBaoZhiFuActivity.this, "????????????", "?????????????????????");
                        // dialog_success.show();

                        PaySuccessUtils.getNet(QueRenDingDanActivity.this, form_id);
                        UIHelper.ToastMessage(QueRenDingDanActivity.this, "????????????", Toast.LENGTH_SHORT);


                    } else {
                        // ???????????????????????????????????????????????????????????????????????????
                        Toast.makeText(QueRenDingDanActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                        PaySuccessUtils.getNetFail(QueRenDingDanActivity.this, form_id);
                        finish();
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    /**
     * ????????????
     *
     * @param out
     */
    private void goToWeChatPay(YuZhiFuModel.DataBean out) {
        api = WXAPIFactory.createWXAPI(QueRenDingDanActivity.this, dataBean_pay.getPay().getAppid());
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


    //????????????
    @Override
    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04243");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(QueRenDingDanActivity.this).getAppToken());
        // map.put("shop_product_id", productId);
        //map.put("wares_id", warseId);

        Log.i("taoken_gg", UserManager.getManager(QueRenDingDanActivity.this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<AddressListModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<AddressListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<AddressListModel.DataBean>> response) {
                        QueRenDingDanActivity.this.response = response;
                        if (response.body().data.size() == 0) {
                            shouHuoAddress = "0";
                        } else {
                            shouHuoAddress = "1";
                        }

                        String str[] = response.body().wares_id_data.split(",");

                        List<String> strings = new ArrayList<>();

                        for (int i = 0; i < str.length; i++) {
                            strings.add(str[i]);
                            Log.i("querendingdan_data", str[i]);
                        }


                        visibleOrGon = NeedYanZheng.yanZheng(QueRenDingDanActivity.this, warseId, strings);
                        //????????????
                        setShouHuoAddress();
                        setTurn();
                    }

                    @Override
                    public void onError(Response<AppResponse<AddressListModel.DataBean>> response) {
                        AlertUtil.t(QueRenDingDanActivity.this, response.getException().getMessage());
                    }
                });
    }

    //  List<AddressListModel.DataBean> dataBeanList = new ArrayList<>();

    //????????????
    @Override
    public void setShouHuoAddress() {


    }

    //??????????????????
    @Override
    public void showProductList() {

        rlvList.setLayoutManager(new LinearLayoutManager(this));
        queRenDingDanAdapter = new QueRenDingDanAdapter(R.layout.querendigndan_order_list, productListBeans);
        rlvList.setAdapter(queRenDingDanAdapter);
        // queRenDingDanAdapter.notifyDataSetChanged();
        queRenDingDanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_kuaidi:
                        if (wares_go_type.equals("1")) {
                            showSingSelect();
                        } else {
                        }

                        break;
                }
            }
        });

    }


    private TextView tvName;
    private TextView tvAddr;
    private TextView tvNone;
    private View viewAddressClick;
    private ConstraintLayout viewAddress;
    private View view_line;

    @Override
    public void setHeaderView() {
        View view = View.inflate(QueRenDingDanActivity.this, R.layout.queren_dingdan_header, null);
        tvName = view.findViewById(R.id.tv_name);
        tvAddr = view.findViewById(R.id.tv_addr);
        tvNone = view.findViewById(R.id.tv_qing_tianjia_shouhuo_dizhi);
        viewAddress = view.findViewById(R.id.view_address);
        view_line = view.findViewById(R.id.view_line);

        viewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressActivity.actionStart(QueRenDingDanActivity.this);
            }
        });
        ImageView ivShop = view.findViewById(R.id.iv_shop_image);
        TextView tvShopName = view.findViewById(R.id.tv_shop_name);
        Glide.with(mContext).load(imageUrl).into((ImageView) ivShop);
        ivShop.setVisibility(View.VISIBLE);
        tvShopName.setText(shopName);
        tvShopName.setVisibility(View.VISIBLE);
        //  dataBeanList.addAll(response.body().data);


        if (response.body().data.size() == 0) {
            //  viewAddress.setVisibility(View.GONE);
            tvNone.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.GONE);
            tvAddr.setVisibility(View.GONE);
        } else {
            //  viewAddress.setVisibility(View.GONE);
            tvNone.setVisibility(View.GONE);
            tvName.setVisibility(View.VISIBLE);
            tvAddr.setVisibility(View.VISIBLE);
            users_addr_id = response.body().data.get(0).getUsers_addr_id();
            tvName.setText(response.body().data.get(0).getUser_name());
            tvAddr.setText(response.body().data.get(0).getAddr_all());

        }
        // ???????????????2.?????? 3.??????
        if (wares_go_type.equals("1")) {
            productDetails.wares_go_type = "2";//????????????

        }
        queRenDingDanAdapter.setHeaderView(view);
    }

    private String userHongBao = "2";//1 ??? 2???
    private String selectHongBaoFlag = "1";//?????? ????????????  1?????? 0 ?????????
    EditText etLiuYan;
    private RelativeLayout rlYaoQingMa;
    EditText etYaoQingMa;

    @Override
    public void setFooterView() {
        View view = View.inflate(QueRenDingDanActivity.this, R.layout.queren_dingdan_footer, null);
        queRenDingDanAdapter.setFooterView(view);
        rlYaoQingMa = view.findViewById(R.id.rl_yaoqingma);
        etYaoQingMa = view.findViewById(R.id.et_yaoqingma);
        if (visibleOrGon) {
            rlYaoQingMa.setVisibility(View.VISIBLE);
        } else {
            rlYaoQingMa.setVisibility(View.GONE);
        }
        final TextView tv_dikoujine = view.findViewById(R.id.tv_dikoujine);
        String balance = response.body().available_balance;
        final ImageView ivChoose = view.findViewById(R.id.iv_choose);

        RelativeLayout rl3 = view.findViewById(R.id.rl_3);

        View viewWeiXin = view.findViewById(R.id.view_weixin);
        View viewZhiFuBao = view.findViewById(R.id.view_zhifubao);

        etLiuYan = view.findViewById(R.id.et_liuyan);
        productDetails.shop_form_text = etLiuYan.getText().toString();

        final TextView tvDanqianDikou = view.findViewById(R.id.tv_danqian_dikou);
        //    final TextView tvDikoujine = view.findViewById(R.id.tv_dikoujine);


        TextView tvXiaoJiPrice = view.findViewById(R.id.tv_xiaoji_price);


        final ImageView ivZhifubaoChoose = view.findViewById(R.id.iv_zhifubao_choose);
        final ImageView ivWeixinChoose = view.findViewById(R.id.iv_weixin_choose);

        viewWeiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pay_id = "2";//????????????-- 1 ????????? 2 ??????
                payType = "4";//1 ????????? 4 ??????

                //  ivIcon1.setBackgroundResource(R.mipmap.dingdan_icon_duihao);
                ivZhifubaoChoose.setVisibility(View.INVISIBLE);
                ivWeixinChoose.setVisibility(View.VISIBLE);
                // getWeiXinOrZhiFuBao();
            }
        });

        viewZhiFuBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pay_id = "1";//????????????-- 1 ????????? 2 ??????
                payType = "1";//1 ????????? 4 ??????

                ivZhifubaoChoose.setVisibility(View.VISIBLE);
                ivWeixinChoose.setVisibility(View.INVISIBLE);
                //ivWeixinChoose.setBackgroundResource(R.mipmap.dingdan_icon_duihao);
                //  getWeiXinOrZhiFuBao();

            }
        });


        tv_dikoujine.setText(balance);


        BigDecimal blanceBigDecimal = new BigDecimal(balance);

        if (blanceBigDecimal.compareTo(BigDecimal.ZERO) == 0) {

        } else {
            rl3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userHongBao.equals("2")) {
                        ivChoose.setVisibility(View.INVISIBLE);
                        userHongBao = "1";//??????
                        tvDanqianDikou.setVisibility(View.GONE);
                        tv_dikoujine.setVisibility(View.GONE);
                        selectHongBaoFlag = "0";
//
//                    if (heJiJinEDecimeal.compareTo(BigDecimal.ZERO) == -1) {
//                        tvHeji.setText("???????????" + "0.01");
//                    } else {
//
//                        tvHeji.setText("???????????" + heJiJinEDecimeal.toString());
//                    }


                    } else {
                        ivChoose.setVisibility(View.VISIBLE);
                        userHongBao = "2";//???

                        tvDanqianDikou.setVisibility(View.VISIBLE);
                        tv_dikoujine.setVisibility(View.VISIBLE);
                        selectHongBaoFlag = "1";

                        // tvHeji.setText("???????????" + finalDecimal.toString());
                    }

                }
            });
        }
        //queRenDingDanAdapter.notifyDataSetChanged();
        //  getWeiXinOrZhiFuBao();
    }

    public void setTurn() {
        showProductList();
        setHeaderView();
        setFooterView();
        queRenDingDanAdapter.notifyDataSetChanged();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_que_ren_ding_dan;
    }

    /**
     * ????????????Activty????????????Activity
     *
     * @param context
     */
    public static void actionStart(Context context, GoodsDetails_f.DataBean dataBean, String position, String number, String title, String shopName, String imageUrl, String wares_go_type) {
        Intent intent = new Intent(context, QueRenDingDanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("dataBean", dataBean);
        intent.putExtra("position", position);
        intent.putExtra("count", number);
        intent.putExtra("title", title);
        intent.putExtra("shopName", shopName);
        intent.putExtra("imageUrl", imageUrl);
        intent.putExtra("wares_go_type", wares_go_type);
        context.startActivity(intent);

    }

    @Override
    public boolean showToolBar() {
        return true;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("????????????");
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


    private class ProductDetails {

        private String form_product_id;
        private String shop_product_id;
        private String pay_count;
        private String shop_form_text;
        private String wares_go_type;
    }


    private String users_addr_id;//??????id
    List<ProductDetails> productDetailsForJava;

    private void getWeiXinOrZhiFuBao() {


       productDetailsForJava.get(0).shop_form_text = etLiuYan.getText().toString();
//        form_product_id 	???????????????id
//        shop_product_id	????????????id
//        pay_count	????????????
//        shop_form_text	????????????(????????????)
//         wares_go_type	???????????????2.?????? 3.??????
//

        if (pay_id.equals("1")) {//1?????????

            //???????????????????????????\

//            users_addr_id 	????????????id		???
//            deduction_type	????????????????????????1.?????? 2.???		???


            Map<String, Object> map = new HashMap<>();
            map.put("key", Urls.key);
            map.put("token", UserManager.getManager(QueRenDingDanActivity.this).getAppToken());
            map.put("operate_type", "21");
            map.put("pay_id", pay_id);
            map.put("pay_type", payType);
            map.put("users_addr_id", users_addr_id);
            map.put("pro", productDetailsForJava);
            map.put("deduction_type", userHongBao);

            String myHeaderLog = new Gson().toJson(map);
            String myHeaderInfo = StringEscapeUtils.unescapeJava(myHeaderLog);
            Log.i("request_log", myHeaderInfo);
            Gson gson = new Gson();
            OkGo.<AppResponse<YuZhiFuModel_AliPay.DataBean>>post(Urls.DALIBAO_PAY)
                    .tag(QueRenDingDanActivity.this)//
                    .upJson(myHeaderInfo)
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel_AliPay.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {

                            appId = response.body().data.get(0).getPay();
                            form_id = response.body().data.get(0).getOut_trade_no();
                            if (pay_id.equals("2")) {
                                //     finish();
                                goToWeChatPay(dataBean_pay);

                            } else if (pay_id.equals("1")) {
                                payV2(appId);//???????????????????????????????????????
                            }

                            progressDialog.dismiss();
                        }

                        @Override
                        public void onError(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {
                            super.onError(response);

                            progressDialog.dismiss();
                        }
                    });

        } else {//2??????

            //???????????????????????????\

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
            map.put("token", UserManager.getManager(QueRenDingDanActivity.this).getAppToken());
            map.put("operate_type", "21");
            map.put("pay_id", pay_id);
            map.put("pay_type", payType);

            //   map.put("pay_type", payType);

            map.put("users_addr_id", users_addr_id);
            map.put("pro", productDetailsForJava);

            map.put("deduction_type", userHongBao);


            String myHeaderLog = new Gson().toJson(map);
            String myHeaderInfo = StringEscapeUtils.unescapeJava(myHeaderLog);
            Log.i("request_log", myHeaderInfo);

            // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
            Gson gson = new Gson();
            OkGo.<AppResponse<YuZhiFuModel.DataBean>>post(Urls.DALIBAO_PAY)
                    .tag(QueRenDingDanActivity.this)//
                    .upJson(myHeaderInfo)
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel.DataBean>> response) {
                            UIHelper.ToastMessage(QueRenDingDanActivity.this, response.body().msg);
                            //   appId = response.body().data.get(0).getPay().getAppid();
                            dataBean_pay = response.body().data.get(0);
                            api = WXAPIFactory.createWXAPI(QueRenDingDanActivity.this, dataBean_pay.getPay().getAppid());

                            form_id = dataBean_pay.getPay().getOut_trade_no();

                            if (pay_id.equals("2")) {
                                //     finish();
                                goToWeChatPay(dataBean_pay);

                            } else if (pay_id.equals("1")) {
                                payV2(appId);//???????????????????????????????????????
                            }

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


    int choice;
    private AlertDialog.Builder builder;

    /**
     * ?????? dialog
     */
    private void showSingSelect() {

        //?????????????????????
        final String[] items = {"??????", "??????"};
        choice = 0;
        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("????????????????????????")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice = i;
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice != -1) {
                            Toast.makeText(QueRenDingDanActivity.this, "????????????" + items[choice], Toast.LENGTH_LONG).show();

                            if (items[choice].equals("??????")) {
                                productDetailsForJava.get(0).wares_go_type = "2";
                                viewAddress.setVisibility(View.VISIBLE);
                                PreferenceHelper.getInstance(mContext).putString(App.KUAIDITYPE, "2");
                                view_line.setVisibility(View.VISIBLE);
                                queRenDingDanAdapter.notifyDataSetChanged();

                                String kuiadiFei = PreferenceHelper.getInstance(QueRenDingDanActivity.this).getString(App.KUAIDIFEI, "0.00");
                                Log.i("kauidifei", "kaudi" + kuiadiFei);

                                if (StringUtils.isEmpty(kuiadiFei)) {
                                    kuiadiFei = "0.00";
                                }
                                BigDecimal bigDecimal = new BigDecimal(productListBeans.get(0).getMoney_now());
                                BigDecimal kuaiDiDecimal = new BigDecimal(kuiadiFei);


                                BigDecimal sumBig = bigDecimal.add(kuaiDiDecimal);

                                tvPrice.setText("??" + sumBig.toString());
                                productDetails.wares_go_type = "2";//????????????
                                //getWeiXinOrZhiFuBao();

                            } else {

                                PreferenceHelper.getInstance(mContext).putString(App.KUAIDITYPE, "3");
                                productDetailsForJava.get(0).wares_go_type = "3";
                                viewAddress.setVisibility(View.GONE);
                                view_line.setVisibility(View.GONE);
                                queRenDingDanAdapter.notifyDataSetChanged();


                                BigDecimal bigDecimal = new BigDecimal(productListBeans.get(0).getMoney_now());
                                BigDecimal kuaiDiDecimal = new BigDecimal("0.00");


                                BigDecimal sumBig = bigDecimal.add(kuaiDiDecimal);

                                tvPrice.setText("??" + sumBig.toString());
                                productDetails.wares_go_type = "3";//????????????
                                //getWeiXinOrZhiFuBao();
                            }
                        }
                    }
                });
        builder.create().show();
    }


    public void setPrice() {

        String kuiadiFei = PreferenceHelper.getInstance(QueRenDingDanActivity.this).getString(App.KUAIDIFEI, "0.00");
        Log.i("kauidifei", "kaudi" + kuiadiFei);

        if (StringUtils.isEmpty(kuiadiFei)) {
            kuiadiFei = "0.00";
        }
        BigDecimal bigDecimal = new BigDecimal(productListBeans.get(0).getMoney_now());
        BigDecimal kuaiDiDecimal = new BigDecimal(kuiadiFei);


        BigDecimal sumBig = bigDecimal.add(kuaiDiDecimal);

        tvPrice.setText("??" + sumBig.toString());
    }


}