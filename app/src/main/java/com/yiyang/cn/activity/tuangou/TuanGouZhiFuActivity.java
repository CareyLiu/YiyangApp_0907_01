package com.yiyang.cn.activity.tuangou;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.TuanGouZhiFuAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.LordingDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.YuZhiFuModel;
import com.yiyang.cn.model.YuZhiFuModel_AliPay;
import com.yiyang.cn.model.ZhiFuTypeModel;
import com.yiyang.cn.pay_about.alipay.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;

public class TuanGouZhiFuActivity extends BaseActivity {


    String pay_id = "2";//支付方式-- 1 支付宝 2 微信
    String payType = "4";//1 支付宝 4 微信
    @BindView(R.id.view_weixin)
    View viewWeixin;
    @BindView(R.id.view_zhifubao)
    View viewZhifubao;
    @BindView(R.id.tv_choose_zhifufangshi)
    TextView tvChooseZhifufangshi;
    @BindView(R.id.iv_icon_1)
    ImageView ivIcon1;
    @BindView(R.id.iv_weixin_choose)
    ImageView ivWeixinChoose;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_icon_2)
    ImageView ivIcon2;
    @BindView(R.id.iv_zhifubao_choose)
    ImageView ivZhifubaoChoose;
    @BindView(R.id.frtv_pay)
    RoundTextView frtvPay;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;

    private String appId;//支付id 给支付宝
    private IWXAPI api;

    private String war_id;
    TuanGouZhiFuAdapter tuanGouZhiFuAdapter;
    List<ZhiFuTypeModel.DataBean> list;

    LordingDialog lordingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        lordingDialog = new LordingDialog(mContext);
        id = getIntent().getStringExtra("id");
        userHongBao = getIntent().getStringExtra("userHongBao");
        number = getIntent().getStringExtra("count");
        war_id = getIntent().getStringExtra("war_id");
        list = new ArrayList<>();
        //   } else {
        //      UIHelper.ToastMessage(DaLiBaoZhiFuActivity.this, "请联网后重新尝试");
        //  }


        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_TUANGOUPAY) {
                    // MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(DaLiBaoZhiFuActivity.this, "支付成功", "恭喜您支付成功");
                    // myCarCaoZuoDialog_success.show();
                    finish();
                    UIHelper.ToastMessage(TuanGouZhiFuActivity.this, "支付成功");
                }
            }
        }));


        frtvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceHelper.getInstance(TuanGouZhiFuActivity.this).putString(App.TUANGOU_PAY, "tuangoupay");
                if (pay_id.equals("2")) {
                    //     finish();

                    getNet();


                } else if (pay_id.equals("1")) {
                    getNet();

                }
            }
        });

//        viewWeixin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                pay_id = "2";//支付方式-- 1 支付宝 2 微信
//                payType = "4";//1 支付宝 4 微信
//
//                //  ivIcon1.setBackgroundResource(R.mipmap.dingdan_icon_duihao);
//                ivZhifubaoChoose.setVisibility(View.INVISIBLE);
//                ivWeixinChoose.setVisibility(View.VISIBLE);
//
//            }
//        });
//
//        viewZhifubao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                pay_id = "1";//支付方式-- 1 支付宝 2 微信
//                payType = "1";//1 支付宝 4 微信
//
//                ivZhifubaoChoose.setVisibility(View.VISIBLE);
//                ivWeixinChoose.setVisibility(View.INVISIBLE);
//                //ivWeixinChoose.setBackgroundResource(R.mipmap.dingdan_icon_duihao);
//
//            }
//        });
        getZhiFuNet();

        tuanGouZhiFuAdapter = new TuanGouZhiFuAdapter(R.layout.item_zhifutype, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(tuanGouZhiFuAdapter);

        tuanGouZhiFuAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.cl_main:
                        pay_id = list.get(position).pay_id;
                        payType = list.get(position).pay_type;



                        for (int i = 0; i < list.size(); i++) {
                            if (i==position){
                                list.get(position).choose = "1";
                            }else {
                                list.get(i).choose = "0";
                            }
                        }

                        tuanGouZhiFuAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });


    }


    @Override
    public int getContentViewResId() {
        return R.layout.tuangou_choose_pay;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("在线支付");
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

    //支付宝支付

    /**
     * 支付宝支付业务
     */
    public void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(TuanGouZhiFuActivity.this);
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
    private String orderId;

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
//                        Notice n = new Notice();
//                        n.type = ConstanceValue.MSG_DALIBAO_SUCCESS;
//                        //  n.content = message.toString();
//                        RxBus.getDefault().sendRx(n);
//                        finish();
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //   Toast.makeText(DaLiBaoZhiFuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        //   finish();
                        // 通过商品购买正常确认订单支付回调  包括常规商品 大礼包  抢货拼手快商品

                        // MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(DaLiBaoZhiFuActivity.this, "支付成功", "恭喜您支付成功");
                        // dialog_success.show();
                        UIHelper.ToastMessage(TuanGouZhiFuActivity.this, "支付成功", Toast.LENGTH_SHORT);
                        finish();

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(TuanGouZhiFuActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    //微信支付

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String id, String count, String userHongBao, String war_id) {
        Intent intent = new Intent(context, TuanGouZhiFuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("id", id);
        intent.putExtra("count", count);
        intent.putExtra("userHongBao", userHongBao);
        intent.putExtra("war_id", war_id);
        context.startActivity(intent);
    }

    YuZhiFuModel.DataBean dataBean;

    private String number;
    private String id;
    private String userHongBao;//1 没用 2用了

    private void getNet() {

        if (pay_id.equals("1")) {//1支付宝

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

            Map<String, String> map = new HashMap<>();
            map.put("key", Urls.key);
            map.put("token", UserManager.getManager(TuanGouZhiFuActivity.this).getAppToken());
            map.put("operate_id", "1");
            map.put("operate_type", "26");
            map.put("pay_id", pay_id);
            map.put("pay_type", payType);
            map.put("shop_count", number);
            map.put("wares_id", war_id);
            map.put("deduction_type", userHongBao);
//        NetUtils<Object> netUtils = new NetUtils<>();
//        netUtils.requestData(map, Urls.DALIBAO_PAY, DaLiBaoZhiFuActivity.this, new JsonCallback<AppResponse<Object>>() {
//            @Override
//            public void onSuccess(Response<AppResponse<Object>> response) {
//
//            }
//
//            @Override
//            public void onError(Response<AppResponse<Object>> response) {
//                super.onError(response);
//                //UIHelper.ToastMessage(DaLiBaoZhiFuActivity.this, response.message());
//            }
//        });


            // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
            Gson gson = new Gson();
            OkGo.<AppResponse<YuZhiFuModel_AliPay.DataBean>>post(Urls.DALIBAO_PAY)
                    .tag(TuanGouZhiFuActivity.this)//
                    .upJson(gson.toJson(map))
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel_AliPay.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {

                            appId = response.body().data.get(0).getPay();
                            payV2(appId);
                        }

                        @Override
                        public void onError(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {
                            super.onError(response);
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

            Map<String, String> map = new HashMap<>();
            map.put("key", Urls.key);
            map.put("token", UserManager.getManager(TuanGouZhiFuActivity.this).getAppToken());
            map.put("operate_id", "1");
            map.put("operate_type", "26");
            map.put("pay_id", pay_id);
            map.put("pay_type", payType);

            map.put("shop_count", number);
            map.put("wares_id", war_id);
            map.put("deduction_type", userHongBao);
//        NetUtils<Object> netUtils = new NetUtils<>();
//        netUtils.requestData(map, Urls.DALIBAO_PAY, DaLiBaoZhiFuActivity.this, new JsonCallback<AppResponse<Object>>() {
//            @Override
//            public void onSuccess(Response<AppResponse<Object>> response) {
//
//            }
//
//            @Override
//            public void onError(Response<AppResponse<Object>> response) {
//                super.onError(response);
//                //UIHelper.ToastMessage(DaLiBaoZhiFuActivity.this, response.message());
//            }
//        });


            // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
            Gson gson = new Gson();
            OkGo.<AppResponse<YuZhiFuModel.DataBean>>post(Urls.DALIBAO_PAY)
                    .tag(TuanGouZhiFuActivity.this)//
                    .upJson(gson.toJson(map))
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel.DataBean>> response) {

                            //   appId = response.body().data.get(0).getPay().getAppid();
                            dataBean = response.body().data.get(0);
                            api = WXAPIFactory.createWXAPI(TuanGouZhiFuActivity.this, dataBean.getPay().getAppid());

                            goToWeChatPay(dataBean);
                        }

                        @Override
                        public void onError(Response<AppResponse<YuZhiFuModel.DataBean>> response) {
                            super.onError(response);
                        }
                    });

        }

    }

    public void getZhiFuNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "04266");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(TuanGouZhiFuActivity.this).getAppToken());
        map.put("inst_id", id);
        Gson gson = new Gson();
        OkGo.<AppResponse<ZhiFuTypeModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiFuTypeModel.DataBean>>() {
                    @Override
                    public void onStart(Request<AppResponse<ZhiFuTypeModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        lordingDialog.setTextMsg("数据加载中，请稍后");
                        lordingDialog.show();
                    }

                    @Override
                    public void onSuccess(Response<AppResponse<ZhiFuTypeModel.DataBean>> response) {
                        list.clear();
                        list.addAll(response.body().data);

                        for (int i = 0; i < list.size(); i++) {
                            if (i == 0) {
                                list.get(i).choose = "1";
                            } else {
                                list.get(i).choose = "0";
                            }
                        }
                        tuanGouZhiFuAdapter.setNewData(list);

                        pay_id = list.get(0).pay_id;
                        payType = list.get(0).pay_type;
                        lordingDialog.dismiss();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        lordingDialog.dismiss();
                    }
                });
    }
}
