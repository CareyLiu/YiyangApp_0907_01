package com.yiyang.cn.activity.dingdan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.OrderTuiKuanDetailsModel;
import com.yiyang.cn.util.AuditProgressView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class OrderTuiKuanDetailsActivity extends BaseActivity {


    @BindView(R.id.tv_tuihuodanhao)
    TextView tvTuihuodanhao;
    @BindView(R.id.tv_dingdanhao)
    TextView tvDingdanhao;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.view_1)
    View view1;
    @BindView(R.id.ll_jindutiao)
    LinearLayout llJindutiao;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.view_2)
    View view2;
    @BindView(R.id.tv_shouhuoren)
    TextView tvShouhuoren;
    @BindView(R.id.tv_shouhuodizhi)
    TextView tvShouhuodizhi;
    @BindView(R.id.cl_2)
    ConstraintLayout cl2;
    @BindView(R.id.view_3)
    View view3;
    @BindView(R.id.tv_wuliudanhao)
    TextView tvWuliudanhao;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.tv_tijioao)
    TextView tvTijioao;
    @BindView(R.id.cl_3)
    ConstraintLayout cl3;
    @BindView(R.id.iv_product)
    ImageView ivProduct;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_xinghao)
    TextView tvXinghao;
    @BindView(R.id.tv_danjia)
    TextView tvDanjia;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_haisheng)
    TextView tvHaisheng;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_wuliudanhao_maijia)
    TextView tvWuliudanhaoMaijia;
    @BindView(R.id.cl_product)
    ConstraintLayout clProduct;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    private String shopFormId;//订单id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        shopFormId = getIntent().getStringExtra("shopFormId");
        getNet();

        tvTijioao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etText.getText().toString())) {
                    UIHelper.ToastMessage(OrderTuiKuanDetailsActivity.this, "请填写您发回的订单编号");
                } else {
                    getTiJiaoWuLiuDanHao(etText.getText().toString());
                }
            }
        });
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04163");
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(OrderTuiKuanDetailsActivity.this).getString("app_token", "0"));
        map.put("shop_form_id", shopFormId);

        // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
        Gson gson = new Gson();
        OkGo.<AppResponse<OrderTuiKuanDetailsModel.DataBean>>post(Urls.HOME_PICTURE_HOME)
                .tag(OrderTuiKuanDetailsActivity.this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<OrderTuiKuanDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<OrderTuiKuanDetailsModel.DataBean>> response) {
                        /**
                         * msg_code	返回码
                         * msg	应答描述
                         * data	应答数据
                         * shop_form_id	订单id
                         * refund_no	退货单号
                         * form_no	订单号
                         * refund_rate	退款进度：1.发起申请2.商家审核3.用户发货4.商家收货
                         * 5.退款成功6.商家拒绝
                         * refund_type	退款类型：1.仅退款2.退款退货
                         * index_photo_url	商品封面图
                         * shop_product_title	商品标题
                         * product_title	颜色分类标题
                         * pay_money	退款金额
                         * inst_addr_all 	收货地址
                         * inst_worker_name	收货人及联系电话
                         * refund_over_time	退款结束时间
                         * refund_express_no	退货物流单号
                         * refund_arr	退款进度名称
                         */
                        OrderTuiKuanDetailsModel.DataBean dataBean = response.body().data.get(0);
                        tvDingdanhao.setText("订单编号：" + dataBean.getForm_no());
                        tvTuihuodanhao.setText("退货单号：" + dataBean.getRefund_no());
                        Glide.with(OrderTuiKuanDetailsActivity.this).load(dataBean.getIndex_photo_url()).into(ivProduct);
                        tvTitle.setText(dataBean.getShop_product_title());
                        tvXinghao.setText(dataBean.getProduct_title());
                        tvHaisheng.setText("最后审核时间" + dataBean.getRefund_over_time() + "(过期视为默认通过)");
                        //  tvNumber.setText(dataBean.);
                        tvPay.setText("退款金额：" + dataBean.getPay_money());

                        if (dataBean.getRefund_type().equals("1")) {
                            //仅退款
                            //   * refund_rate	退款进度：1.发起申请2.商家审核3.用户发货4.商家收货
                            //   * 5.退款成功6.商家拒绝
                            switch (dataBean.getRefund_rate()) {
                                case "1":
                                    llJindutiao.addView(createView(5, true, false, true, false, dataBean.getRefund_arr().get(0)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(1)));
                                    llJindutiao.addView(createView(5, false, true, false, true, dataBean.getRefund_arr().get(2)));
                                    cl3.setVisibility(View.GONE);
                                    viewLine.setVisibility(View.GONE);
                                    view3.setVisibility(View.GONE);
                                    cl2.setVisibility(View.GONE);
                                    break;
                                case "2":
                                    llJindutiao.addView(createView(5, false, false, true, false, dataBean.getRefund_arr().get(0)));
                                    llJindutiao.addView(createView(5, true, true, false, false, dataBean.getRefund_arr().get(1)));
                                    llJindutiao.addView(createView(5, false, true, false, true, dataBean.getRefund_arr().get(2)));
                                    cl3.setVisibility(View.GONE);
                                    viewLine.setVisibility(View.GONE);
                                    view3.setVisibility(View.GONE);
                                    cl2.setVisibility(View.GONE);
                                    break;
                                case "3":

                                    break;
                                case "4":

                                    break;
                                case "5":
                                    tvHaisheng.setText("您的退款已通过");
                                    llJindutiao.addView(createView(5, false, false, true, false, dataBean.getRefund_arr().get(0)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(1)));
                                    llJindutiao.addView(createView(5, true, true, false, true, dataBean.getRefund_arr().get(2)));
                                    cl3.setVisibility(View.GONE);
                                    viewLine.setVisibility(View.GONE);
                                    view3.setVisibility(View.GONE);
                                    cl2.setVisibility(View.GONE);
                                    break;

                                case "6":
                                    llJindutiao.addView(createView(5, false, false, true, false, dataBean.getRefund_arr().get(0)));
                                    llJindutiao.addView(createView(5, true, true, false, false, dataBean.getRefund_arr().get(1)));
                                    llJindutiao.addView(createView(5, false, true, false, true, dataBean.getRefund_arr().get(2)));
                                    cl3.setVisibility(View.GONE);
                                    viewLine.setVisibility(View.GONE);
                                    view3.setVisibility(View.GONE);
                                    cl2.setVisibility(View.GONE);
                                    break;
                            }


                        } else if (dataBean.getRefund_type().equals("2")) {
                            //退货退款
/**
 *   * refund_rate	退款进度：1.发起申请2.商家审核3.用户发货4.商家收货
 *  * 5.退款成功6.商家拒绝
 */
                            switch (dataBean.getRefund_rate()) {
                                case "1":

                                    llJindutiao.addView(createView(5, true, false, true, false, dataBean.getRefund_arr().get(0)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(1)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(2)));
                                    llJindutiao.addView(createView(5, false, false, false, false, dataBean.getRefund_arr().get(3)));
                                    llJindutiao.addView(createView(5, false, false, false, true, dataBean.getRefund_arr().get(4)));
                                    tvShouhuoren.setVisibility(View.GONE);
                                    tvShouhuodizhi.setVisibility(View.GONE);
                                    view3.setVisibility(View.GONE);
                                    cl2.setVisibility(View.GONE);

                                    cl3.setVisibility(View.GONE);
                                    viewLine.setVisibility(View.GONE);
                                    break;
                                case "2":

                                    viewLine.setVisibility(View.VISIBLE);
                                    cl3.setVisibility(View.VISIBLE);
                                    tvHaisheng.setVisibility(View.VISIBLE);
                                    llJindutiao.addView(createView(5, false, false, true, false, dataBean.getRefund_arr().get(0)));
                                    llJindutiao.addView(createView(5, true, true, false, false, dataBean.getRefund_arr().get(1)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(2)));
                                    llJindutiao.addView(createView(5, false, false, false, false, dataBean.getRefund_arr().get(3)));
                                    llJindutiao.addView(createView(5, false, false, false, true, dataBean.getRefund_arr().get(4)));
                                    tvShouhuoren.setText("收货人：" + dataBean.getInst_worker_name());
                                    tvShouhuodizhi.setText("收货地址:" + dataBean.getInst_addr_all());

                                    break;
                                case "3":
                                    tvWuliudanhaoMaijia.setVisibility(View.VISIBLE);
                                    tvWuliudanhaoMaijia.setText("订单编号：" + dataBean.getRefund_express_no());
                                    viewLine.setVisibility(View.GONE);
                                    cl3.setVisibility(View.GONE);
                                    tvHaisheng.setVisibility(View.GONE);
                                    llJindutiao.addView(createView(5, false, false, true, false, dataBean.getRefund_arr().get(0)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(1)));
                                    llJindutiao.addView(createView(5, true, true, false, false, dataBean.getRefund_arr().get(2)));
                                    llJindutiao.addView(createView(5, false, false, false, false, dataBean.getRefund_arr().get(3)));
                                    llJindutiao.addView(createView(5, false, false, false, true, dataBean.getRefund_arr().get(4)));
                                    tvShouhuoren.setText("收货人：" + dataBean.getInst_worker_name());
                                    tvShouhuodizhi.setText("收货地址:" + dataBean.getInst_addr_all());
                                    break;
                                case "4":
                                    viewLine.setVisibility(View.GONE);
                                    cl3.setVisibility(View.GONE);
                                    tvHaisheng.setVisibility(View.GONE);
                                    llJindutiao.addView(createView(5, false, false, true, false, dataBean.getRefund_arr().get(0)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(1)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(2)));
                                    llJindutiao.addView(createView(5, true, false, false, false, dataBean.getRefund_arr().get(3)));
                                    llJindutiao.addView(createView(5, false, false, false, true, dataBean.getRefund_arr().get(4)));
                                    tvShouhuoren.setText("收货人：" + dataBean.getInst_worker_name());
                                    tvShouhuodizhi.setText("收货地址:" + dataBean.getInst_addr_all());
                                    break;
                                case "5":
                                    viewLine.setVisibility(View.GONE);
                                    cl3.setVisibility(View.GONE);
                                    tvHaisheng.setVisibility(View.GONE);
                                    llJindutiao.addView(createView(5, false, false, true, false, dataBean.getRefund_arr().get(0)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(1)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(2)));
                                    llJindutiao.addView(createView(5, false, false, false, false, dataBean.getRefund_arr().get(3)));
                                    llJindutiao.addView(createView(5, true, false, false, true, dataBean.getRefund_arr().get(4)));
                                    tvShouhuoren.setText("收货人：" + dataBean.getInst_worker_name());
                                    tvShouhuodizhi.setText("收货地址:" + dataBean.getInst_addr_all());
                                    break;
                                case "6":

                                    viewLine.setVisibility(View.GONE);
                                    cl3.setVisibility(View.GONE);
                                    tvHaisheng.setVisibility(View.GONE);
                                    cl2.setVisibility(View.GONE);
                                    llJindutiao.addView(createView(5, false, false, true, false, dataBean.getRefund_arr().get(0)));
                                    llJindutiao.addView(createView(5, true, true, false, false, dataBean.getRefund_arr().get(1)));
                                    llJindutiao.addView(createView(5, false, true, false, false, dataBean.getRefund_arr().get(2)));
                                    llJindutiao.addView(createView(5, false, false, false, false, dataBean.getRefund_arr().get(3)));
                                    llJindutiao.addView(createView(5, false, false, false, true, dataBean.getRefund_arr().get(4)));

                                    break;
                            }
                        }

                        if (dataBean.getOrder_info_arr() != null) {


                            for (int i = 0; i < dataBean.getOrder_info_arr().size(); i++) {
                                View view = View.inflate(mContext, R.layout.layout_view_info, null);
                                TextView tv = view.findViewById(R.id.tv_text);
                                tv.setText(dataBean.getOrder_info_arr().get(i));
                                llInfo.addView(view);

                            }
                        }


                    }

                    @Override
                    public void onError(Response<AppResponse<OrderTuiKuanDetailsModel.DataBean>> response) {
                        super.onError(response);
                        //   UIHelper.ToastMessage(getActivity(), response.body().msg);

                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);

                    }

                    @Override
                    public void onCacheSuccess(Response<AppResponse<OrderTuiKuanDetailsModel.DataBean>> response) {
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
        return R.layout.activity_order_tui_kuan_details;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("退款详情");
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


    @Override
    public boolean showToolBar() {
        return true;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String str) {
        Intent intent = new Intent(context, OrderTuiKuanDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("shopFormId", str);
        context.startActivity(intent);
    }

    public AuditProgressView createView(int stepCount, boolean isCurrentComplete, boolean isNextComplete, boolean isFirstStep, boolean isLastStep, String text) {
        AuditProgressView view = new AuditProgressView(this);
        view.setStepCount(stepCount);
        view.setIsCurrentComplete(isCurrentComplete);
        view.setIsNextComplete(isNextComplete);
        view.setIsFirstStep(isFirstStep);
        view.setIsLastStep(isLastStep);
        view.setText(text);
        return view;
    }

    /**
     * {
     * "code":"04166",
     * "key":"20180305124455yu",
     * "token":"1541309Q02920100l000v000e000H0",
     * "shop_form_id":"14",
     * "refund_express_no":"125433841288621"
     * }
     */
    private void getTiJiaoWuLiuDanHao(String dingDanBianHao) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04166");
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(OrderTuiKuanDetailsActivity.this).getString("app_token", "0"));
        map.put("shop_form_id", shopFormId);
        map.put("refund_express_no", dingDanBianHao);
        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(Urls.HOME_PICTURE_HOME)
                .tag(OrderTuiKuanDetailsActivity.this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        UIHelper.ToastMessage(OrderTuiKuanDetailsActivity.this, "单号已提交");
                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        super.onError(response);

                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);

                    }

                });

    }
}
