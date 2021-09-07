package com.yiyang.cn.activity.wode_page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.MingxiModel;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;

public class MingxiActivity extends BaseActivity {

    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_zhuangtai)
    TextView tv_zhuangtai;
    @BindView(R.id.tv_tixianjine)
    TextView tv_tixianjine;
    @BindView(R.id.ll_tixianjine)
    LinearLayout ll_tixianjine;
    @BindView(R.id.tv_fuwufei)
    TextView tv_fuwufei;
    @BindView(R.id.ll_fuwufei)
    LinearLayout ll_fuwufei;
    @BindView(R.id.tv_shenqingshijian)
    TextView tv_shenqingshijian;
    @BindView(R.id.ll_shenqingshijian)
    LinearLayout ll_shenqingshijian;
    @BindView(R.id.tv_daozhangshijian)
    TextView tv_daozhangshijian;
    @BindView(R.id.ll_daozhangshijian)
    LinearLayout ll_daozhangshijian;
    @BindView(R.id.tv_tixianfangshi)
    TextView tv_tixianfangshi;
    @BindView(R.id.ll_tixianfangshi)
    LinearLayout ll_tixianfangshi;
    @BindView(R.id.tv_tixiandanhao)
    TextView tv_tixiandanhao;
    @BindView(R.id.ll_tixiandanhao)
    LinearLayout ll_tixiandanhao;
    @BindView(R.id.tv_shangpin)
    TextView tv_shangpin;
    @BindView(R.id.ll_shangping)
    LinearLayout ll_shangping;
    @BindView(R.id.tv_shanghu)
    TextView tv_shanghu;
    @BindView(R.id.ll_shanghu)
    LinearLayout ll_shanghu;
    @BindView(R.id.tv_zhifushijian)
    TextView tv_zhifushijian;
    @BindView(R.id.ll_zhifushijian)
    LinearLayout ll_zhifushijian;
    @BindView(R.id.tv_zhifupay)
    TextView tv_zhifupay;
    @BindView(R.id.ll_zhifupay)
    LinearLayout ll_zhifupay;
    @BindView(R.id.tv_jianyidanhao)
    TextView tv_jianyidanhao;
    @BindView(R.id.ll_jianyidanhao)
    LinearLayout ll_jianyidanhao;
    @BindView(R.id.tv_shou_type)
    TextView tv_shou_type;
    @BindView(R.id.ll_shou_type)
    LinearLayout ll_shou_type;
    @BindView(R.id.tv_shou_time)
    TextView tv_shou_time;
    @BindView(R.id.ll_shou_time)
    LinearLayout ll_shou_time;
    @BindView(R.id.tv_tuikuan_danhao)
    TextView tv_tuikuan_danhao;
    @BindView(R.id.ll_tuikuan_danhao)
    LinearLayout ll_tuikuan_danhao;


    private String user_pay_id;
    private String er_type;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_my_mingxi;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("明细");
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


    @Override
    public boolean showToolBar() {
        return true;
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String user_pay_id, String er_type) {
        Intent intent = new Intent(context, MingxiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user_pay_id", user_pay_id);
        intent.putExtra("er_type", er_type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        user_pay_id = getIntent().getStringExtra("user_pay_id");
        er_type = getIntent().getStringExtra("er_type");
        if (er_type.equals("1")) {
            iv_icon.setBackgroundResource(R.mipmap.mingxi_icon_dailifenrun);
        } else if (er_type.equals("2")) {
            iv_icon.setBackgroundResource(R.mipmap.mingxi_icon_zhifu);
        } else if (er_type.equals("3")) {
            iv_icon.setBackgroundResource(R.mipmap.mingxi_icon_tixian);
        } else if (er_type.equals("9")) {
            iv_icon.setBackgroundResource(R.mipmap.mingxi_icon_tuikuan);
        }

        getMingXiNet();
    }


    public void getMingXiNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "04142");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("user_pay_id", user_pay_id);

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<MingxiModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MingxiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MingxiModel.DataBean>> response) {
                        showLoadSuccess();

                        MingxiModel.DataBean dataBean = response.body().data.get(0);
                        tv_title_name.setText(dataBean.getPay_title());
                        tv_money.setText(dataBean.getUser_money());

                        tv_zhuangtai.setText("¥" + dataBean.getForm_state_name());
                        String er_type = dataBean.getEr_type();

                        if (er_type.equals("1")) {
                            ll_shou_type.setVisibility(View.VISIBLE);
                            ll_shou_time.setVisibility(View.VISIBLE);

                            tv_shou_type.setText(dataBean.getPay_detail());
                            tv_shou_time.setText(dataBean.getCreate_time());
                        } else if (er_type.equals("2")) {
                            ll_shangping.setVisibility(View.VISIBLE);
                            ll_shanghu.setVisibility(View.VISIBLE);
                            ll_zhifushijian.setVisibility(View.VISIBLE);
                            ll_zhifupay.setVisibility(View.VISIBLE);
                            ll_jianyidanhao.setVisibility(View.VISIBLE);

                            tv_shangpin.setText(dataBean.getPay_detail());
                            tv_shanghu.setText(dataBean.getInst_name());
                            tv_zhifushijian.setText(dataBean.getPay_time());
                            tv_zhifupay.setText(dataBean.getPay_type_name());
                            tv_jianyidanhao.setText(dataBean.getForm_no());
                        } else if (er_type.equals("3")) {
                            ll_tixianjine.setVisibility(View.VISIBLE);
                            ll_fuwufei.setVisibility(View.VISIBLE);
                            ll_shenqingshijian.setVisibility(View.VISIBLE);
                            ll_daozhangshijian.setVisibility(View.VISIBLE);
                            ll_tixianfangshi.setVisibility(View.VISIBLE);
                            ll_tixiandanhao.setVisibility(View.VISIBLE);

                            tv_shenqingshijian.setText(dataBean.getCreate_time());
                            tv_daozhangshijian.setText(dataBean.getCreate_time());
                            tv_tixianfangshi.setText(dataBean.getPay_type_name());
                            tv_tixianjine.setText("¥" + dataBean.getOrder_money());
                            tv_fuwufei.setText("¥" + dataBean.getCharge_money());
                            tv_tixiandanhao.setText(dataBean.getForm_no());
                        } else if (er_type.equals("9")) {
                            ll_shangping.setVisibility(View.VISIBLE);
                            ll_zhifushijian.setVisibility(View.VISIBLE);
                            ll_zhifupay.setVisibility(View.VISIBLE);
                            ll_tuikuan_danhao.setVisibility(View.VISIBLE);

                            tv_shangpin.setText(dataBean.getPay_detail());
                            tv_zhifushijian.setText(dataBean.getRefund_time());
                            tv_zhifupay.setText(dataBean.getPay_type_name());
                            tv_tuikuan_danhao.setText(dataBean.getRollback_no());
                        }
                    }

                    @Override
                    public void onStart(Request<AppResponse<MingxiModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onError(Response<AppResponse<MingxiModel.DataBean>> response) {
                        super.onError(response);
                        showLoadFailed();
                    }
                });
    }

}
