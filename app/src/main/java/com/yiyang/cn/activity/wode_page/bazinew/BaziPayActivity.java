package com.yiyang.cn.activity.wode_page.bazinew;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.DefaultX5WebView_HaveNameActivity;
import com.yiyang.cn.activity.homepage.DaLiBaoZhiFuActivity;
import com.yiyang.cn.activity.taokeshagncheng.QueRenDingDanActivity;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.utils.TimeUtils;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.YuZhiFuModel;
import com.yiyang.cn.model.YuZhiFuModel_AliPay;
import com.yiyang.cn.pay_about.alipay.PayResult;
import com.yiyang.cn.util.PaySuccessUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BaziPayActivity extends BaziBaseActivity {


    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.iv_select_wx)
    ImageView iv_select_wx;
    @BindView(R.id.ll_select_wx)
    LinearLayout ll_select_wx;
    @BindView(R.id.iv_select_zfb)
    ImageView iv_select_zfb;
    @BindView(R.id.ll_select_zfb)
    LinearLayout ll_select_zfb;
    @BindView(R.id.bt_paipan)
    Button bt_paipan;
    private int payType;
    private String mingpan_id;
    private YuZhiFuModel.DataBean dataBean;
    private IWXAPI api;
    private String pay_id;//1支付宝 2微信
    private String pay_type;//1支付宝 4微信
    private String operate_id;
    private String time;
    private String form_id;
    private String appId;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_pay;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("在线支付");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        payType = getIntent().getIntExtra("payType", 0);
        mingpan_id = getIntent().getStringExtra("mingpan_id");

        time = getIntent().getStringExtra("time");

        if (payType == 1) {
            operate_id = "4";
            tv_type.setText("流日运势单次支付");
            tv_money.setText("￥1元");
        } else if (payType == 101) {
            operate_id = "4";
            tv_type.setText("穿衣指数单次支付");
            tv_money.setText("￥1元");
        }else if (payType == 102) {
            operate_id = "0";
            tv_type.setText("穿衣指数包年支付");
            tv_money.setText("￥100元");
        } else {
            operate_id = "0";
            tv_type.setText("八紫流年盘包年会员");
            tv_money.setText("￥100元");
        }

        selectWx();


        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_SAOMASUCCESS) {
                    PaySuccessUtils.getNet(BaziPayActivity.this, form_id);
                    t("支付成功");
                    setResult(100);
                    finish();
                } else if (message.type == ConstanceValue.MSG_SAOMAFAILE) {
                    PaySuccessUtils.getNet(BaziPayActivity.this, form_id);
                    t("支付失败");
                }
            }
        }));
    }

    @OnClick({R.id.ll_select_wx, R.id.ll_select_zfb, R.id.bt_paipan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_select_wx:
                selectWx();
                break;
            case R.id.ll_select_zfb:
                selectZfb();
                break;
            case R.id.bt_paipan:
                pay();
                break;
        }
    }

    private void selectWx() {
        iv_select_wx.setImageResource(R.mipmap.zhifu_icon_select_on);
        iv_select_zfb.setImageResource(R.mipmap.zhifu_icon_select_off);
        pay_id = "2";
        pay_type = "4";
    }

    private void selectZfb() {
        iv_select_wx.setImageResource(R.mipmap.zhifu_icon_select_off);
        iv_select_zfb.setImageResource(R.mipmap.zhifu_icon_select_on);
        pay_id = "1";
        pay_type = "1";
    }

    private void pay() {
        if (pay_id.equals("1")) {
            zfbPay();
        } else {
            wxPay();
        }
    }

    private void zfbPay() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("operate_type", "27");
        map.put("project_type", "bz");
        map.put("pay_id", pay_id);
        map.put("pay_type", pay_type);
        map.put("operate_id", operate_id);
        map.put("mingpan_id", mingpan_id);
        map.put("time", time);

        Gson gson = new Gson();
        OkGo.<AppResponse<YuZhiFuModel_AliPay.DataBean>>post(Urls.DALIBAO_PAY)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<YuZhiFuModel_AliPay.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {
                        appId = response.body().data.get(0).getPay();
                        form_id = response.body().data.get(0).getOut_trade_no();
                        payV2(appId);//这里填写后台返回的支付信息
                    }
                });
    }

    private void wxPay() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("operate_type", "27");
        map.put("project_type", "bz");
        map.put("pay_id", pay_id);
        map.put("pay_type", pay_type);
        map.put("operate_id", operate_id);
        map.put("mingpan_id", mingpan_id);
        map.put("time", time);

        Gson gson = new Gson();
        OkGo.<AppResponse<YuZhiFuModel.DataBean>>post(Urls.DALIBAO_PAY)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<YuZhiFuModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<YuZhiFuModel.DataBean>> response) {
                        PreferenceHelper.getInstance(BaziPayActivity.this).putString(App.BAZI_PAY, App.BAZI_PAY);
                        dataBean = response.body().data.get(0);
                        form_id = response.body().data.get(0).getPay().getOut_trade_no();
                        api = WXAPIFactory.createWXAPI(BaziPayActivity.this, dataBean.getPay().getAppid());
                        api.registerApp(dataBean.getPay().getAppid());
                        PayReq req = new PayReq();
                        req.appId = dataBean.getPay().getAppid();
                        req.partnerId = dataBean.getPay().getPartnerid();
                        req.prepayId = dataBean.getPay().getPrepayid();
                        req.timeStamp = dataBean.getPay().getTimestamp();
                        req.nonceStr = dataBean.getPay().getNoncestr();
                        req.sign = dataBean.getPay().getSign();
                        req.packageValue = dataBean.getPay().getPackageX();
                        api.sendReq(req);
                    }
                });
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
                        PaySuccessUtils.getNet(BaziPayActivity.this, form_id);
                        t("支付成功");
                        setResult(100);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        PaySuccessUtils.getNetFail(BaziPayActivity.this, form_id);
                        t("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    /**
     * 支付宝支付业务
     */
    public void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(BaziPayActivity.this);
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

    private static final int SDK_PAY_FLAG = 1;
}
