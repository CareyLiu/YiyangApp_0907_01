package com.yiyang.cn.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tongcheng58.model.ShangjiaDetailModel;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.Radius_GlideImageLoader;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.BianMinXinXiModel;
import com.yiyang.cn.view.AutoNextLineLinearlayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.TONGCHENG;

public class ShangjiaWodeActivity extends BaseActivity {

    @BindView(R.id.iv_shenhe_state)
    ImageView iv_shenhe_state;
    @BindView(R.id.tv_shenhe_state)
    TextView tv_shenhe_state;
    @BindView(R.id.ll_shenhe_state)
    LinearLayout ll_shenhe_state;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_juli_address)
    TextView tv_juli_address;
    @BindView(R.id.tv_name_phone)
    TextView tv_name_phone;
    @BindView(R.id.tv_yingye_time)
    TextView tv_yingye_time;
    @BindView(R.id.ll_dianneisheshi_add)
    AutoNextLineLinearlayout ll_dianneisheshi_add;
    @BindView(R.id.tv_gonggao)
    TextView tv_gonggao;
    @BindView(R.id.tv_jieshao)
    TextView tv_jieshao;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_zhekou)
    TextView tv_zhekou;

    private String ir_id;
    private String x_jingdu;
    private String y_weidu;
    private ShangjiaDetailModel.DataBean dataBean;

    @Override
    public int getContentViewResId() {
        return R.layout.tongcheng_act_shangjia_wode;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("店铺详情");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setImageResource(R.mipmap.def_more);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.待审核 2.已审核 2.审核拒绝
                TishiDialog tishiDialog = new TishiDialog(mContext, TishiDialog.TYPE_DELETE, new TishiDialog.TishiDialogListener() {
                    @Override
                    public void onClickCancel(View v, TishiDialog dialog) {

                    }

                    @Override
                    public void onClickConfirm(View v, TishiDialog dialog) {
                        deleteNet();
                    }

                    @Override
                    public void onDismiss(TishiDialog dialog) {

                    }
                });
                tishiDialog.setTextContent("是否确定删除此信息");
                tishiDialog.show();
            }
        });
    }

    public static void actionStart(Context context, String ir_id) {
        Intent intent = new Intent(context, ShangjiaWodeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ir_id", ir_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ir_id = getIntent().getStringExtra("ir_id");
        x_jingdu = PreferenceHelper.getInstance(mContext).getString(App.JINGDU, "");
        y_weidu = PreferenceHelper.getInstance(mContext).getString(App.WEIDU, "");
        initSM();
        getData();
    }

    private void initBanner() {
        List<ShangjiaDetailModel.DataBean.ImgListBean> imgList = dataBean.getImgList();
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < imgList.size(); i++) {
            imgs.add(imgList.get(i).getIr_img_url());
        }
        banner.setImageLoader(new Radius_GlideImageLoader());
        banner.setImages(imgs);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }


    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "17007");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("x", x_jingdu);
        map.put("y", y_weidu);
        map.put("ir_id", ir_id);
        map.put("operate_type", "2");
        Gson gson = new Gson();
        OkGo.<AppResponse<ShangjiaDetailModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ShangjiaDetailModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ShangjiaDetailModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            dataBean = response.body().data.get(0);
                            tv_name.setText(dataBean.getIr_inst_name());
                            tv_juli_address.setText(dataBean.getAddr());
                            tv_name_phone.setText(dataBean.getIr_contact_phone());
                            tv_yingye_time.setText(dataBean.getIr_inst_open_time() + "-" + dataBean.getIr_inst_close_time());

                            tv_gonggao.setText(dataBean.getIr_inst_notice());
                            tv_jieshao.setText(dataBean.getIr_validity());

                            initBanner();

                            ll_dianneisheshi_add.removeAllViews();
                            List<ShangjiaDetailModel.DataBean.InstDeviceListBean> inst_device_list = dataBean.getInst_device_list();
                            for (int i = 0; i < inst_device_list.size(); i++) {
                                View view = View.inflate(mContext, R.layout.tongcheng_item_shangjia_details_tag, null);
                                TextView tv_tag = view.findViewById(R.id.tv_tag);
                                tv_tag.setText(inst_device_list.get(i).getInst_device_name());
                                ll_dianneisheshi_add.addView(view);
                            }

                            String ir_audit_state = dataBean.getIr_audit_state();
                            String ir_audit_state_name = dataBean.getIr_audit_state_name();
                            tv_shenhe_state.setText(ir_audit_state_name);
                            if (ir_audit_state.equals("2")) {
                                ll_shenhe_state.setBackgroundResource(R.color.tongcheng_yfb_beijing);
                                tv_shenhe_state.setTextColor(mContext.getResources().getColor(R.color.tongcheng_yfb));
                                iv_shenhe_state.setBackgroundResource(R.mipmap.gongjiangxinxi_pic_yifabu);
                            } else if (ir_audit_state.equals("3")) {
                                ll_shenhe_state.setBackgroundResource(R.color.tongcheng_yjj_beijing);
                                tv_shenhe_state.setTextColor(mContext.getResources().getColor(R.color.tongcheng_yjj));
                                iv_shenhe_state.setBackgroundResource(R.mipmap.gongjiangxinxi_pic_yijujue);
                            } else {
                                ll_shenhe_state.setBackgroundResource(R.color.tongchneg_shz_beijing);
                                tv_shenhe_state.setTextColor(mContext.getResources().getColor(R.color.tongchneg_shz));
                                iv_shenhe_state.setBackgroundResource(R.mipmap.gongjiangxinxi_pic_shenhezhong);
                            }

                            String ir_agio = dataBean.getIr_agio();
                            if (TextUtils.isEmpty(ir_agio)) {
                                tv_zhekou.setText("");
                            } else {
                                String ir_inst_begin_time = dataBean.getIr_inst_begin_time();
                                String ir_inst_end_time = dataBean.getIr_inst_end_time();
                                tv_zhekou.setText("限时：" + ir_agio + "折  开始时间：" + ir_inst_begin_time + "  结束时间：" + ir_inst_end_time);
                            }
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    private void deleteNet() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17014");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("ir_id", ir_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<BianMinXinXiModel.DataBean>>post(TONGCHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<BianMinXinXiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<BianMinXinXiModel.DataBean>> response) {
                        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {
                                finish();
                            }
                        });
                        dialog.setTextTitle("提示");
                        dialog.setTextContent("刪除成功");
                        dialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<BianMinXinXiModel.DataBean>> response) {
                        Y.tError(response);
                    }
                });
    }
}
