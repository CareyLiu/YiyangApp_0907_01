package com.yiyang.cn.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.BianMinXinXiModel;
import com.yiyang.cn.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.app.App.JINGDU;
import static com.yiyang.cn.app.App.WEIDU;
import static com.yiyang.cn.get_net.Urls.TONGCHENG;

public class BianMinXinXiActivity extends BaseActivity {
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.lianxi_fangshi)
    TextView lianxiFangshi;
    @BindView(R.id.tv_jianjie)
    TextView tvJianjie;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.iv_shenhe_icon)
    ImageView ivShenheIcon;
    @BindView(R.id.tv_shenhe)
    TextView tvShenhe;
    @BindView(R.id.rl_shenhezhuangtai)
    RelativeLayout rlShenhezhuangtai;
    @BindView(R.id.rrl_chongxinfabu)
    RoundRelativeLayout rrlChongxinfabu;
    @BindView(R.id.ll_jujue)
    LinearLayout llJujue;
    @BindView(R.id.tv_jujue_yuanyin)
    TextView tvJujueYuanyin;
    private String irId;
    private String state;
    private String stateName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        irId = getIntent().getStringExtra("irId");
        state = getIntent().getStringExtra("state");
        stateName = getIntent().getStringExtra("stateName");

        if (!StringUtils.isEmpty(state)) {
            //1.待审核 2.已审核 2.审核拒绝
            rlShenhezhuangtai.setVisibility(View.VISIBLE);

            if (state.equals("1")) {
                rlShenhezhuangtai.setBackgroundResource(R.color.tongchneg_shz_beijing);
                tvShenhe.setTextColor(mContext.getResources().getColor(R.color.tongchneg_shz));
                tvShenhe.setText("审核中");
                ivShenheIcon.setBackgroundResource(R.mipmap.gongjiangxinxi_pic_shenhezhong);
            } else if (state.equals("2")) {
                rlShenhezhuangtai.setBackgroundResource(R.color.tongcheng_yfb_beijing);
                tvShenhe.setTextColor(mContext.getResources().getColor(R.color.tongcheng_yfb));
                tvShenhe.setText("已发布");
                ivShenheIcon.setBackgroundResource(R.mipmap.gongjiangxinxi_pic_yifabu);
            } else if (state.equals("3")) {
                rlShenhezhuangtai.setBackgroundResource(R.color.tongcheng_yjj_beijing);
                tvShenhe.setTextColor(mContext.getResources().getColor(R.color.tongcheng_yjj));
                tvShenhe.setText("已拒绝");
                ivShenheIcon.setBackgroundResource(R.mipmap.gongjiangxinxi_pic_yijujue);
                rrlChongxinfabu.setVisibility(View.VISIBLE);

                rrlChongxinfabu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //重新发布
                        BianMinFaBuBianJiActivity.actionStart(mContext, irId);
                        finish();
                    }
                });
                llJujue.setVisibility(View.VISIBLE);


            }
            rlShenhezhuangtai.setVisibility(View.VISIBLE);
        } else {
            iv_rightTitle.setVisibility(View.GONE);
            rlShenhezhuangtai.setVisibility(View.GONE);
            operate_type = "1";
        }
        getData();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_bianminxinxi;
    }

    private String operate_type = "2";//默认查询审核类型

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17008");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("x", PreferenceHelper.getInstance(mContext).getString(WEIDU, ""));
        map.put("y", PreferenceHelper.getInstance(mContext).getString(JINGDU, ""));
        map.put("ir_id", irId);
        map.put("operate_type", operate_type);
        Gson gson = new Gson();
        OkGo.<AppResponse<BianMinXinXiModel.DataBean>>post(TONGCHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<BianMinXinXiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<BianMinXinXiModel.DataBean>> response) {
                        Logger.d(gson.toJson(response.body()));
                        irId = response.body().data.get(0).getIr_id();
                        //标题
                        tvTitleName.setText(response.body().data.get(0).getIr_title());

                        //距离和地址
                        if (!StringUtils.isEmpty(response.body().data.get(0).getMeter())) {
                            tvDizhi.setText(response.body().data.get(0).getMeter() + "km" + "  " + response.body().data.get(0).getAddr());
                        } else {
                            tvDizhi.setText(response.body().data.get(0).getAddr());
                        }
                        lianxiFangshi.setText(response.body().data.get(0).getIr_contact_name() + response.body().data.get(0).getIr_user_phone());

                        tvJianjie.setText(response.body().data.get(0).getIr_validity());

                        linearLayout.removeAllViews();
                        for (int i = 0; i < response.body().data.get(0).getImgList().size(); i++) {
                            View view = View.inflate(mContext, R.layout.item_big_image, null);
                            ImageView iv = view.findViewById(R.id.iv_img);

                            if (!StringUtils.isEmpty(response.body().data.get(0).getImgList().get(i).getHeight())) {
                                RequestOptions requestOptions = new RequestOptions();
                                requestOptions.override(Integer.valueOf(response.body().data.get(0).getImgList().get(i).getWidth()), Integer.valueOf(response.body().data.get(0).getImgList().get(i).getHeight()));
                                Glide.with(mContext).load(response.body().data.get(0).getImgList().get(i).getIr_img_url()).apply(requestOptions).into(iv);
                            }
                            linearLayout.addView(view);
                        }
                        tvJujueYuanyin.setText(response.body().data.get(0).getIr_manage_text());

                    }

                    @Override
                    public void onError(Response<AppResponse<BianMinXinXiModel.DataBean>> response) {

                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<BianMinXinXiModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    public static void actionStart(Context context, String irId, String state, String stateName) {
        Intent intent = new Intent(context, BianMinXinXiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("irId", irId);
        intent.putExtra("state", state);
        intent.putExtra("stateName", stateName);
        context.startActivity(intent);
    }

    public static void actionStart(Context context, String irId) {
        Intent intent = new Intent(context, BianMinXinXiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("irId", irId);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    TishiDialog tishiDialog;

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("便民信息");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setImageResource(R.mipmap.def_more);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.待审核 2.已审核 2.审核拒绝

                tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
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

//                if (state.equals("1")) {
//
//
//                } else if (state.equals("2")) {
//
//
//                } else if (state.equals("3")) {
//
//
//                }
            }
        });
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }

    private void deleteNet() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17014");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("ir_id", irId);
        Gson gson = new Gson();
        OkGo.<AppResponse<BianMinXinXiModel.DataBean>>post(TONGCHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<BianMinXinXiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<BianMinXinXiModel.DataBean>> response) {
                        Logger.d(gson.toJson(response.body()));
                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse<BianMinXinXiModel.DataBean>> response) {

                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<BianMinXinXiModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }
}
