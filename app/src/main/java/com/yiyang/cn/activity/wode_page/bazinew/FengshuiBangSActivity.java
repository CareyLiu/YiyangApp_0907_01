package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceBottomEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.LoginActivity;
import com.yiyang.cn.activity.SettingActivity;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.model.FengshuiDetails;
import com.yiyang.cn.activity.wode_page.bazinew.model.FengshuiModel;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class FengshuiBangSActivity extends BaziBaseActivity {

    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.iv_baijian)
    ImageView iv_baijian;
    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.iv_switch)
    ImageView iv_switch;
    @BindView(R.id.iv_yuanshi)
    ImageView iv_yuanshi;
    @BindView(R.id.bt_paipan)
    Button bt_paipan;

    private String mingpan_id;
    private String mingpan_goods_switch;
    private String ccid;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_fengshui_bangs;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("已绑定");
    }

    public static void actionStart(Context context, String mingpan_id) {
        Intent intent = new Intent(context, FengshuiBangSActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mingpan_id", mingpan_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mingpan_id = getIntent().getStringExtra("mingpan_id");
        getNet();
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11022");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", mingpan_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<FengshuiDetails.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<FengshuiDetails.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<FengshuiDetails.DataBean>> response) {
                        showLoadSuccess();
                        FengshuiDetails.DataBean dataBean = response.body().data.get(0);
                        String ls_jx = dataBean.getLs_jx();
                        if (ls_jx.equals("1")) {
                            iv_yuanshi.setImageResource(R.mipmap.baijian_pic_ji);
                        } else {
                            iv_yuanshi.setImageResource(R.mipmap.baijian_pic_xiong);
                        }
                        ccid = dataBean.getCcid();
                        mingpan_goods_switch = dataBean.getMingpan_goods_switch();
                        if (mingpan_goods_switch.equals("1")) {
                            iv_switch.setImageResource(R.mipmap.swich_on);
                            tv_state.setText("摆件已开启");
                        } else {
                            iv_switch.setImageResource(R.mipmap.swich_off);
                            tv_state.setText("摆件已关闭");
                        }

                        tv_title_name.setText(dataBean.getGoods_name());

                        Glide.with(mContext).load(dataBean.getGoods_img()).into(iv_baijian);
                    }

                    @Override
                    public void onStart(Request<AppResponse<FengshuiDetails.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onError(Response<AppResponse<FengshuiDetails.DataBean>> response) {
                        super.onError(response);
                        showLoadFailed();
                    }
                });
    }

    @OnClick({R.id.iv_switch, R.id.bt_paipan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_switch:
                clickSwitch();
                break;
            case R.id.bt_paipan:
                showJiebang();
                break;
        }
    }

    private BaseAnimatorSet mBasIn = new BounceBottomEnter();
    private BaseAnimatorSet mBasOut = new SlideBottomExit();

    private void showJiebang() {
        NormalDialog normalDialog = new NormalDialog(this);
        normalDialog.content("是否解除绑定?").showAnim(mBasIn).dismissAnim(mBasOut).show();
        normalDialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        normalDialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        jiebang();
                    }
                });
    }

    private void clickSwitch() {
        if (mingpan_goods_switch.equals("1")) {
            mingpan_goods_switch = "2";
        } else {
            mingpan_goods_switch = "1";
        }

        Map<String, String> map = new HashMap<>();
        map.put("code", "11032");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", mingpan_id);
        map.put("mingpan_goods_switch", mingpan_goods_switch);
        map.put("ccid", ccid);
        Gson gson = new Gson();
        OkGo.<AppResponse<FengshuiDetails.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<FengshuiDetails.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<FengshuiDetails.DataBean>> response) {
                        showLoadSuccess();
                        if (mingpan_goods_switch.equals("1")) {
                            iv_switch.setImageResource(R.mipmap.swich_on);
                            tv_state.setText("摆件已开启");
                        } else {
                            iv_switch.setImageResource(R.mipmap.swich_off);
                            tv_state.setText("摆件已关闭");
                        }
                    }

                    @Override
                    public void onStart(Request<AppResponse<FengshuiDetails.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onError(Response<AppResponse<FengshuiDetails.DataBean>> response) {
                        super.onError(response);
                        showLoadFailed();
                        if (mingpan_goods_switch.equals("1")) {
                            mingpan_goods_switch = "2";
                        } else {
                            mingpan_goods_switch = "1";
                        }
                    }
                });
    }

    private void jiebang() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11026");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", mingpan_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<FengshuiDetails.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<FengshuiDetails.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<FengshuiDetails.DataBean>> response) {
                        showLoadSuccess();
                        t("解绑成功");
                        Notice notice = new Notice();
                        notice.type = ConstanceValue.MSG_BAZI_FSBJ2;
                        sendRx(notice);
                        finish();
                    }

                    @Override
                    public void onStart(Request<AppResponse<FengshuiDetails.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onError(Response<AppResponse<FengshuiDetails.DataBean>> response) {
                        super.onError(response);
                        showLoadFailed();
                    }
                });
    }
}
