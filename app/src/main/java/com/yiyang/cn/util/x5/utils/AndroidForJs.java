package com.yiyang.cn.util.x5.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.PhoneCheckActivity;
import com.yiyang.cn.activity.fenxiang_tuisong.ShouYeFenXiangActivity;
import com.yiyang.cn.activity.wode_page.TiXianActivity;
import com.yiyang.cn.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.config.Wetch_S;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.DaiLiShangQianBaoModel;
import com.yiyang.cn.model.Home;
import com.yiyang.cn.model.MenSuoModel;
import com.yiyang.cn.model.SaoMaPayModel;
import com.yiyang.cn.model.SaoMaWeiXinPayModel;
import com.yiyang.cn.model.SaoMaZhiFuBaoPayModel;
import com.yiyang.cn.pay_about.alipay.PayResult;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Florent on 02/10/13.
 */
public class AndroidForJs {
    public final static String tag = AndroidForJs.class.getSimpleName();
    private Context mContext;
    private Activity ac;
    String str;
    int iflag = 1;
    public static int shareType = 0;


    private Gson gson;

    //title 广播
    public static final String title_action = "broadcast.action.title";

    //幸运汇重定向 广播
    public static final String getluckyurl_action = "broadcast.action.getluckyurl";

    //开心汇 重定向 广播
    public static final String switchhome_action = "broadcast.action.switchhome";


    long personId = -1;

    public AndroidForJs(Context context) {
        this.mContext = context;
        this.ac = (Activity) context;
        gson = new Gson();
    }

    //JS请求java
    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        String[] imgs = img.split(",");
        ArrayList<String> imgsUrl = new ArrayList<String>();
        for (String s : imgs) {
            imgsUrl.add(s);
            Log.e("-------URL---------", s);
        }
        Intent intent = new Intent();
        intent.putStringArrayListExtra("infos", imgsUrl);
        // intent.setClass(mContext, ImageShowActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    private String formId;

    //JS请求java
    @JavascriptInterface
    public void jsToAppPayAction(String para) {
        Bundle bundle = new Bundle();
        SaoMaPayModel saoMaPayModel = new Gson().fromJson(para, SaoMaPayModel.class);
        PreferenceHelper.getInstance(mContext).putString(App.SAOMA_PAY, "saoma_pay");
        if (saoMaPayModel.getType().equals("1")) {//调支付宝支付

            SaoMaZhiFuBaoPayModel saoMaZhiFuBaoPayModel = new Gson().fromJson(para, SaoMaZhiFuBaoPayModel.class);
            payV2(saoMaZhiFuBaoPayModel.getPay());//这里填写后台返回的支付信息
        } else if (saoMaPayModel.getType().equals("2")) {//调微信支付
            SaoMaWeiXinPayModel saoMaWeiXinPayModel = new Gson().fromJson(para, SaoMaWeiXinPayModel.class);
            SaoMaWeiXinPayModel.PayBean payBean = saoMaWeiXinPayModel.getPay();

            goToWeChatPay(saoMaWeiXinPayModel);
        }
    }

    @JavascriptInterface
    public void jsToAppMap(String startLat) {
        //去做想做的事情。比如导航，直接带着开始和结束的经纬度Intent到导航activity就可以
    }

    //JS请求java
    @JavascriptInterface
    public void jsToAppProductAction(String para) {
        Bundle bundle = new Bundle();
        MenSuoModel menSuoModel = new Gson().fromJson(para, MenSuoModel.class);
        if (menSuoModel.getWares_id() != null && menSuoModel.getShop_product_id() != null) {
            ZiJianShopMallDetailsActivity.actionStart(mContext, menSuoModel.getShop_product_id(), menSuoModel.getWares_id());
        }
    }

    //JS请求java
    @JavascriptInterface
    public void jsToAppDlsTXActionn(String para) {
        getDaiLiQianBaoNet();
    }

    //JS请求java
    @JavascriptInterface
    public void jsToAppTyShare(String para) {
        Home.DataBean.activity activity = new Gson().fromJson(para, Home.DataBean.activity.class);
        ShouYeFenXiangActivity.actionStart(mContext, activity);
    }
    //支付宝支付

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
                        UIHelper.ToastMessage(mContext, "支付成功", Toast.LENGTH_SHORT);
                        //finish();
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_SAOMASUCCESS;
                        //  n.content = message.toString();
                        RxBus.getDefault().sendRx(n);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_SAOMAFAILE;
                        RxBus.getDefault().sendRx(n);
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
    private void goToWeChatPay(SaoMaWeiXinPayModel out) {
        IWXAPI api;
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

    public void getDaiLiQianBaoNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04344");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());

        Gson gson = new Gson();
        OkGo.<AppResponse<DaiLiShangQianBaoModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user ")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DaiLiShangQianBaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<DaiLiShangQianBaoModel.DataBean>> response) {
                        /**
                         * msg_code	返回码
                         * msg	应答描述
                         * data	应答数据
                         * user_money	可提现余额
                         * inst_money_cash	已提现金额
                         * userable_red	可抵扣金额
                         * pay_num	提现账号（如未设置则为空）
                         * pwd_pay	是否设置过支付密码：0未设置1已设置
                         * userName	用户名
                         * pay_name	支付宝真实姓名
                         */
                        showWeiXinOrZhiFuBaoSelect(response);
                    }


                    @Override
                    public void onError(Response<AppResponse<DaiLiShangQianBaoModel.DataBean>> response) {

                    }

                });
    }

    /**
     * 两个按钮的 dialog
     */
    private void showTwo() {
        builder = new AlertDialog.Builder(mContext).setIcon(R.mipmap.logi_icon).setTitle("账号绑定")
                .setMessage("是否前去绑定支付宝账号").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mContext.startActivity(new Intent(mContext, PhoneCheckActivity.class).putExtra("mod_id", "0008"));
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }

    private AlertDialog.Builder builder;

    private int choice = 0;

    /**
     * 单选 dialog
     */
    private void showWeiXinOrZhiFuBaoSelect(Response<AppResponse<DaiLiShangQianBaoModel.DataBean>> response) {

        //默认选中第一个
        final String[] items = {"微信", "支付宝"};
        builder = new AlertDialog.Builder(mContext).setIcon(R.mipmap.ic_launcher).setTitle("选择您的提现方式")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice = i;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice == 0) {//微信
                            String weixinPay = PreferenceHelper.getInstance(mContext).getString(App.CUNCHUBIND_WEIXINPAY, "0x11");
                            if (weixinPay.equals("1")) {
                                TiXianActivity.actionStart(mContext, response.body().data.get(0).getUser_money(), "1", "2");
                            } else {
                                IWXAPI api;
                                api = WXAPIFactory.createWXAPI(mContext, Wetch_S.APP_ID);
                                SendAuth.Req req = new SendAuth.Req();
                                req.scope = "snsapi_userinfo";
                                req.state = "wechat_sdk_demo_test";
                                api.sendReq(req);
                            }
                        } else {//支付宝
                            String checkAliPay = PreferenceHelper.getInstance(mContext).getString(App.CUNCHUBIND_ALIPAY, "0x11");
                            if (checkAliPay.equals("1")) {//已经设置
                                TiXianActivity.actionStart(mContext, response.body().data.get(0).getUser_money(), "1", "1");
                            } else {//2 未设置
                                showTwo();
                            }
                        }
                    }
                });
        builder.create().show();
    }
}

