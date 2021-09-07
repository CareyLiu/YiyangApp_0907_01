package com.yiyang.cn.activity.gouwuche;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.AddressActivity;
import com.yiyang.cn.adapter.GouWuCheAdapter;
import com.yiyang.cn.adapter.GouWuCheQueRenDingDanAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.AddressListModel;
import com.yiyang.cn.model.AddressModel;
import com.yiyang.cn.model.GoodsDetails_f;
import com.yiyang.cn.model.GouWuCheZhengHeModel;
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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class GouWuCheQueRenDingDanActivity extends BaseActivity implements QueRenDingDanInterface {
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
    private GouWuCheQueRenDingDanAdapter gouWuCheQueRenDingDanAdapter;
    Response<AppResponse<AddressListModel.DataBean>> response;
    String pay_id = "2";//支付方式-- 1 支付宝 2 微信
    String payType = "4";//1 支付宝 4 微信
    private String appId;//支付id 给支付宝
    private IWXAPI api;
    private String form_id;//订单id
    GoodsDetails_f.DataBean.ProductListBean productListBean;
    String warseId;
    boolean visibleOrGon;
    AddressModel.DataBean addressDataBean;
    public String shouHuoAddress = "0";//0否 1 是

    ProgressDialog progressDialog;

    List<GouWuCheZhengHeModel> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(GouWuCheQueRenDingDanActivity.this);

        mDatas = (List<GouWuCheZhengHeModel>) getIntent().getSerializableExtra("mDatas");
        Log.i("mDatas", mDatas.size() + "");


        getNet();
        llNowPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (shouHuoAddress.equals("0")) {
                    //没有收货地址
                    UIHelper.ToastMessage(GouWuCheQueRenDingDanActivity.this, "请您填写收货地址再进行付款操作");
                    return;
                }

                if (visibleOrGon) {
                    if (StringUtils.isEmpty(etYaoQingMa.getText().toString())) {

                        //UIHelper.ToastMessage(GouWuCheQueRenDingDanActivity.this, "请填写邀请码后再进行下单");

                        progressDialog.setMessage("正在校验并支付，请稍后...");
                        progressDialog.show();
                        //立即支付
                        PreferenceHelper.getInstance(GouWuCheQueRenDingDanActivity.this).putString(App.ZIYING_PAY, "ZIYING_PAY");
                        getWeiXinOrZhiFuBao();
                    } else {
                        getNet_butian(etYaoQingMa.getText().toString());
                    }

                } else {
                    progressDialog.setMessage("正在校验并支付，请稍后...");
                    progressDialog.show();
                    //立即支付
                    PreferenceHelper.getInstance(GouWuCheQueRenDingDanActivity.this).putString(App.ZIYING_PAY, "ZIYING_PAY");
                    getWeiXinOrZhiFuBao();
                }
            }
        });

        //  setPrice();//设置合计价格
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ZIYING_PAY_FAIL) {
                    UIHelper.ToastMessage(GouWuCheQueRenDingDanActivity.this, "订单支付失败");
                    finish();
                } else if (message.type == ConstanceValue.MSG_GETADDRESS) {
                    addressDataBean = (AddressModel.DataBean) message.content;
                    UIHelper.ToastMessage(GouWuCheQueRenDingDanActivity.this, "address" + addressDataBean.getAddr());
                    shouHuoAddress = "1";
                    tvNone.setVisibility(View.GONE);
                    tvName.setVisibility(View.VISIBLE);
                    tvAddr.setVisibility(View.VISIBLE);
                    users_addr_id = addressDataBean.getUsers_addr_id();
                    tvName.setText(addressDataBean.getUser_name());
                    tvAddr.setText(addressDataBean.getAddr_all());
                    gouWuCheQueRenDingDanAdapter.notifyDataSetChanged();

                } else if (message.type == ConstanceValue.MSG_NONEADDRESS) {
                    tvNone.setVisibility(View.VISIBLE);
                    tvName.setVisibility(View.GONE);
                    tvAddr.setVisibility(View.GONE);
                    users_addr_id = "";
                    gouWuCheQueRenDingDanAdapter.notifyDataSetChanged();
                } else if (message.type == ConstanceValue.MSG_ZIYING_PAY) {
                    PaySuccessUtils.getNet(GouWuCheQueRenDingDanActivity.this, form_id);
                    UIHelper.ToastMessage(GouWuCheQueRenDingDanActivity.this, "订单支付成功");
                    finish();
                }
            }
        }));


    }


    private void getNet_butian(String et) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04343");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(GouWuCheQueRenDingDanActivity.this).getAppToken());
        map.put("invitation_code", et);

        Log.i("taoken_gg", UserManager.getManager(GouWuCheQueRenDingDanActivity.this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<Object>> response) {
                        //校码成功

                        getWeiXinOrZhiFuBao();
                        visibleOrGon = false;
                        rlYaoQingMa.setVisibility(View.GONE);
                        PreferenceHelper.getInstance(GouWuCheQueRenDingDanActivity.this).putString(App.SHIFOUYOUSHANGJI, "1");

                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        // AlertUtil.t(QueRenDingDanActivity.this, response.getException().getMessage());
                        UIHelper.ToastMessage(GouWuCheQueRenDingDanActivity.this, "邀请码填写错误");
                    }
                });
    }

    private static final int SDK_PAY_FLAG = 1;


    /**
     * 支付宝支付业务
     */
    public void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(GouWuCheQueRenDingDanActivity.this);
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

                        PaySuccessUtils.getNet(GouWuCheQueRenDingDanActivity.this, form_id);
                        UIHelper.ToastMessage(GouWuCheQueRenDingDanActivity.this, "支付成功", Toast.LENGTH_SHORT);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(GouWuCheQueRenDingDanActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        PaySuccessUtils.getNetFail(GouWuCheQueRenDingDanActivity.this, form_id);
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
     * 微信支付
     *
     * @param out
     */
    private void goToWeChatPay(YuZhiFuModel.DataBean out) {
        api = WXAPIFactory.createWXAPI(GouWuCheQueRenDingDanActivity.this, dataBean_pay.getPay().getAppid());
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


    //访问网络
    @Override
    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04243");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(GouWuCheQueRenDingDanActivity.this).getAppToken());

        Log.i("taoken_gg", UserManager.getManager(GouWuCheQueRenDingDanActivity.this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<AddressListModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<AddressListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<AddressListModel.DataBean>> response) {
                        GouWuCheQueRenDingDanActivity.this.response = response;
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
                        balance = response.body().available_balance;

                        if (!StringUtils.isEmpty(balance)) {
                            tvPrice.setText("¥ " + getJieSuanJinE().subtract(new BigDecimal(balance)).toString());
                        } else {
                            tvPrice.setText("¥ " + getJieSuanJinE().toString());
                        }

                        visibleOrGon = NeedYanZheng.yanZheng(GouWuCheQueRenDingDanActivity.this, mDatas, strings);
                        //测试一下
                        setShouHuoAddress();
                        setTurn();
                        showLoadSuccess();
                    }

                    @Override
                    public void onError(Response<AppResponse<AddressListModel.DataBean>> response) {
                        AlertUtil.t(GouWuCheQueRenDingDanActivity.this, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<AddressListModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }
                });
    }

    //展示地址
    @Override
    public void setShouHuoAddress() {

    }

    //展示商品列表
    @Override
    public void showProductList() {

        gouWuCheQueRenDingDanAdapter = new GouWuCheQueRenDingDanAdapter(mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GouWuCheQueRenDingDanActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(gouWuCheQueRenDingDanAdapter);
    }

    private TextView tvName;
    private TextView tvAddr;
    private TextView tvNone;
    private ConstraintLayout viewAddress;
    private View view_line;

    @Override
    public void setHeaderView() {
        View view = View.inflate(GouWuCheQueRenDingDanActivity.this, R.layout.queren_dingdan_header, null);
        tvName = view.findViewById(R.id.tv_name);
        tvAddr = view.findViewById(R.id.tv_addr);
        tvNone = view.findViewById(R.id.tv_qing_tianjia_shouhuo_dizhi);
        viewAddress = view.findViewById(R.id.view_address);
        view_line = view.findViewById(R.id.view_line);
        viewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressActivity.actionStart(GouWuCheQueRenDingDanActivity.this);
            }
        });

        if (response.body().data.size() == 0) {
            tvNone.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.GONE);
            tvAddr.setVisibility(View.GONE);
        } else {
            tvNone.setVisibility(View.GONE);
            tvName.setVisibility(View.VISIBLE);
            tvAddr.setVisibility(View.VISIBLE);
            users_addr_id = response.body().data.get(0).getUsers_addr_id();
            tvName.setText(response.body().data.get(0).getUser_name());
            tvAddr.setText(response.body().data.get(0).getAddr_all());

        }
        gouWuCheQueRenDingDanAdapter.setHeaderView(view);
    }

    private String userHongBao = "2";//1 否 2是
    EditText etLiuYan;
    private RelativeLayout rlYaoQingMa;
    EditText etYaoQingMa;
    String balance;

    @Override
    public void setFooterView() {
        View view = View.inflate(GouWuCheQueRenDingDanActivity.this, R.layout.queren_dingdan_footer, null);
        gouWuCheQueRenDingDanAdapter.setFooterView(view);
        rlYaoQingMa = view.findViewById(R.id.rl_yaoqingma);
        etYaoQingMa = view.findViewById(R.id.et_yaoqingma);
        if (visibleOrGon) {
            rlYaoQingMa.setVisibility(View.VISIBLE);
        } else {
            rlYaoQingMa.setVisibility(View.GONE);
        }
        final TextView tv_dikoujine = view.findViewById(R.id.tv_dikoujine);

        final ImageView ivChoose = view.findViewById(R.id.iv_choose);
        RelativeLayout rl3 = view.findViewById(R.id.rl_3);
        View viewWeiXin = view.findViewById(R.id.view_weixin);
        View viewZhiFuBao = view.findViewById(R.id.view_zhifubao);
        etLiuYan = view.findViewById(R.id.et_liuyan);
        final TextView tvDanqianDikou = view.findViewById(R.id.tv_danqian_dikou);
        final ImageView ivZhifubaoChoose = view.findViewById(R.id.iv_zhifubao_choose);
        final ImageView ivWeixinChoose = view.findViewById(R.id.iv_weixin_choose);

        viewWeiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pay_id = "2";//支付方式-- 1 支付宝 2 微信
                payType = "4";//1 支付宝 4 微信
                ivZhifubaoChoose.setVisibility(View.INVISIBLE);
                ivWeixinChoose.setVisibility(View.VISIBLE);

            }
        });

        viewZhiFuBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pay_id = "1";//支付方式-- 1 支付宝 2 微信
                payType = "1";//1 支付宝 4 微信
                ivZhifubaoChoose.setVisibility(View.VISIBLE);
                ivWeixinChoose.setVisibility(View.INVISIBLE);


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
                        userHongBao = "1";//不用
                        tvDanqianDikou.setVisibility(View.GONE);
                        tv_dikoujine.setVisibility(View.GONE);

                        //不用红包 重新计算金额
                        if (null != zongJiaBigDecimal) {
                            tvPrice.setText("¥ " + getJieSuanJinE().toString());
                        }
                    } else {
                        ivChoose.setVisibility(View.VISIBLE);
                        userHongBao = "2";//用
                        tvDanqianDikou.setVisibility(View.VISIBLE);
                        tv_dikoujine.setVisibility(View.VISIBLE);
                        //使用红包 重新计算金额
                        if (null != zongJiaBigDecimal) {
                            tvPrice.setText("¥ " + getJieSuanJinE().subtract(new BigDecimal(balance)).toString());
                        }
                    }

                }
            });
        }

    }

    public void setTurn() {
        showProductList();
        setHeaderView();
        setFooterView();
        gouWuCheQueRenDingDanAdapter.notifyDataSetChanged();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_que_ren_ding_dan;
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, List<GouWuCheZhengHeModel> mDatas) {
        Intent intent = new Intent(context, GouWuCheQueRenDingDanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mDatas", (Serializable) mDatas);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("确认订单");
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


    private String users_addr_id;//地址id

    private void getWeiXinOrZhiFuBao() {

        if (pay_id.equals("1")) {//1支付宝
            Map<String, Object> map = new HashMap<>();
            map.put("key", Urls.key);
            map.put("token", UserManager.getManager(GouWuCheQueRenDingDanActivity.this).getAppToken());
            map.put("operate_type", "21");
            map.put("pay_id", pay_id);
            map.put("pay_type", payType);
            map.put("users_addr_id", users_addr_id);
            map.put("pro", setPro());
            map.put("deduction_type", userHongBao);
            String myHeaderLog = new Gson().toJson(map);
            String myHeaderInfo = StringEscapeUtils.unescapeJava(myHeaderLog);
            Log.i("request_log", myHeaderInfo);
            Gson gson = new Gson();
            OkGo.<AppResponse<YuZhiFuModel_AliPay.DataBean>>post(Urls.DALIBAO_PAY)
                    .tag(GouWuCheQueRenDingDanActivity.this)//
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
                                payV2(appId);//这里填写后台返回的支付信息
                            }

                            progressDialog.dismiss();
                        }

                        @Override
                        public void onError(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {
                            super.onError(response);
                            String str = response.getException().getMessage();
                            UIHelper.ToastMessage(mContext, str);
                            progressDialog.dismiss();
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
            map.put("token", UserManager.getManager(GouWuCheQueRenDingDanActivity.this).getAppToken());
            map.put("operate_type", "21");
            map.put("pay_id", pay_id);
            map.put("pay_type", payType);
            map.put("users_addr_id", users_addr_id);
            map.put("pro", setPro());
            map.put("deduction_type", userHongBao);
            String myHeaderLog = new Gson().toJson(map);
            String myHeaderInfo = StringEscapeUtils.unescapeJava(myHeaderLog);
            Log.i("request_log", myHeaderInfo);

            // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
            Gson gson = new Gson();
            OkGo.<AppResponse<YuZhiFuModel.DataBean>>post(Urls.DALIBAO_PAY)
                    .tag(GouWuCheQueRenDingDanActivity.this)//
                    .upJson(myHeaderInfo)
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel.DataBean>> response) {
                            UIHelper.ToastMessage(GouWuCheQueRenDingDanActivity.this, response.body().msg);
                            //   appId = response.body().data.get(0).getPay().getAppid();
                            dataBean_pay = response.body().data.get(0);
                            api = WXAPIFactory.createWXAPI(GouWuCheQueRenDingDanActivity.this, dataBean_pay.getPay().getAppid());

                            form_id = dataBean_pay.getPay().getOut_trade_no();

                            if (pay_id.equals("2")) {
                                //     finish();
                                goToWeChatPay(dataBean_pay);

                            } else if (pay_id.equals("1")) {
                                payV2(appId);//这里填写后台返回的支付信息
                            }

                            progressDialog.dismiss();
                        }

                        @Override
                        public void onError(Response<AppResponse<YuZhiFuModel.DataBean>> response) {
                            super.onError(response);
                            String str = response.getException().getMessage();
                            UIHelper.ToastMessage(mContext, str);
                            progressDialog.dismiss();
                        }
                    });
        }

    }

    int choice;
    private AlertDialog.Builder builder;

    /**
     * 单选 dialog
     */
//    private void showSingSelect() {
//
//        //默认选中第一个
//        final String[] items = {"快递", "到店"};
//        choice = 0;
//        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("选择您的购买方式")
//                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        choice = i;
//                    }
//                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        if (choice != -1) {
//                            Toast.makeText(GouWuCheQueRenDingDanActivity.this, "你选择了" + items[choice], Toast.LENGTH_LONG).show();
//                            if (items[choice].equals("快递")) {
//                                //productDetailsForJava.get(0).wares_go_type = "2";
//                                viewAddress.setVisibility(View.VISIBLE);
//                                PreferenceHelper.getInstance(mContext).putString(App.KUAIDITYPE, "2");
//                                view_line.setVisibility(View.VISIBLE);
//                                queRenDingDanAdapter.notifyDataSetChanged();
//                                String kuiadiFei = PreferenceHelper.getInstance(GouWuCheQueRenDingDanActivity.this).getString(App.KUAIDIFEI, "0.00");
//                                Log.i("kauidifei", "kaudi" + kuiadiFei);
//                                if (StringUtils.isEmpty(kuiadiFei)) {
//                                    kuiadiFei = "0.00";
//                                }
//                                BigDecimal bigDecimal = new BigDecimal(productListBeans.get(0).getMoney_now());
//                                BigDecimal kuaiDiDecimal = new BigDecimal(kuiadiFei);
//                                BigDecimal sumBig = bigDecimal.add(kuaiDiDecimal);
//                                tvPrice.setText("¥" + sumBig.toString());
//
//                            } else {
//
//                                PreferenceHelper.getInstance(mContext).putString(App.KUAIDITYPE, "3");
//                               // productDetailsForJava.get(0).wares_go_type = "3";
//                                viewAddress.setVisibility(View.GONE);
//                                view_line.setVisibility(View.GONE);
//                                queRenDingDanAdapter.notifyDataSetChanged();
//                                BigDecimal bigDecimal = new BigDecimal(productListBeans.get(0).getMoney_now());
//                                BigDecimal kuaiDiDecimal = new BigDecimal("0.00");
//                                BigDecimal sumBig = bigDecimal.add(kuaiDiDecimal);
//                                tvPrice.setText("¥" + sumBig.toString());
//                            }
//                        }
//                    }
//                });
//        builder.create().show();
//    }


//    public void setPrice() {
//        String kuiadiFei = PreferenceHelper.getInstance(GouWuCheQueRenDingDanActivity.this).getString(App.KUAIDIFEI, "0.00");
//        Log.i("kauidifei", "kaudi" + kuiadiFei);
//        if (StringUtils.isEmpty(kuiadiFei)) {
//            kuiadiFei = "0.00";
//        }
//        BigDecimal bigDecimal = new BigDecimal(productListBeans.get(0).getMoney_now());
//        BigDecimal kuaiDiDecimal = new BigDecimal(kuiadiFei);
//        BigDecimal sumBig = bigDecimal.add(kuaiDiDecimal);
//
//        tvPrice.setText("¥" + sumBig.toString());
//    }
    private BigDecimal zongJiaBigDecimal;

    private BigDecimal getJieSuanJinE() {
        zongJiaBigDecimal = new BigDecimal("0.00");
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getIsSelect().equals("1")) {//选中
                if (!mDatas.get(i).isHeader) {
                    BigDecimal djBigDecimal = new BigDecimal(mDatas.get(i).getForm_product_money());
                    BigDecimal countBigDecimal = new BigDecimal(mDatas.get(i).getPay_count());
                    BigDecimal danShangPinBigDecimal = djBigDecimal.multiply(countBigDecimal);
                    zongJiaBigDecimal = danShangPinBigDecimal.add(zongJiaBigDecimal);
                    zongJiaBigDecimal = zongJiaBigDecimal.add(new BigDecimal(mDatas.get(i).getWares_money_go()));
                }
            }
        }

        return zongJiaBigDecimal;
    }

    private class ProductDetails {
        private String form_product_id;
        private String shop_product_id;
        private String pay_count;
        private String shop_form_text;
        private String wares_go_type;
        private String installation_type_id;//门锁安装方式
    }

    String liuYan = "";

    private List<ProductDetails> setPro() {
        List<ProductDetails> productDetailsList = new ArrayList<>();


        for (int i = mDatas.size() - 1; i > 0; i--) {
            if (!mDatas.get(i).isHeader) {
                if (mDatas.get(i).getBottomYuanJiao().equals("1")) {
                    liuYan = mDatas.get(i).getLiuyan();
                }
                ProductDetails productDetails = new ProductDetails();
                productDetails.form_product_id = mDatas.get(i).getForm_product_id();
                productDetails.pay_count = mDatas.get(i).getPay_count();
                productDetails.shop_form_text = liuYan;
                productDetails.shop_product_id = mDatas.get(i).getShop_product_id();
                productDetails.wares_go_type = mDatas.get(i).getWares_go_type();
                productDetails.installation_type_id = mDatas.get(i).getInstallation_type_id();

                productDetailsList.add(productDetails);
            } else {
                liuYan = "";
            }
        }
        return productDetailsList;
    }
}