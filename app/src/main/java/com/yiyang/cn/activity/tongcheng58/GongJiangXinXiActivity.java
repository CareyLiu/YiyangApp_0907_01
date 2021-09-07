package com.yiyang.cn.activity.tongcheng58;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.fenxiang_tuisong.ShareActivity;
import com.yiyang.cn.activity.tongcheng58.model.ShangjiaDetailModel;
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
import com.yiyang.cn.model.GongJiangXinXiModel;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.view.AutoNextLineLinearlayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.yiyang.cn.app.App.JINGDU;
import static com.yiyang.cn.app.App.WEIDU;
import static com.yiyang.cn.get_net.Urls.TONGCHENG;

public class GongJiangXinXiActivity extends BaseActivity {
    @BindView(R.id.iv_image)
    CircleImageView ivImage;
    @BindView(R.id.tv_gongjiang_ming)
    TextView tvGongjiangMing;
    @BindView(R.id.ll_tag)
    AutoNextLineLinearlayout llTag;
    @BindView(R.id.rl_item)
    RelativeLayout rlItem;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_dadianhua)
    RoundRelativeLayout rlDadianhua;
    @BindView(R.id.rl_jiaweixin)
    RoundRelativeLayout rlJiaweixin;
    @BindView(R.id.rl_yuefuwu)
    RoundRelativeLayout rlYuefuwu;
    @BindView(R.id.rl_fenxiang)
    RoundRelativeLayout rlFenxiang;

    @BindView(R.id.iv_shenhe_icon)
    ImageView ivShenheIcon;
    @BindView(R.id.tv_shenhe)
    TextView tvShenhe;
    @BindView(R.id.rl_shenhezhuangtai)
    RelativeLayout rlShenhezhuangtai;
    @BindView(R.id.tv_jujue_yuanyin)
    TextView tvJujueYuanyin;
    @BindView(R.id.ll_jujue)
    LinearLayout llJujue;
    @BindView(R.id.tv_gonglishu)
    TextView tvGonglishu;
    @BindView(R.id.ll_tupianheji)
    LinearLayout llTupianheji;
    @BindView(R.id.tv_jianjie)
    TextView tvJianjie;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.rrl_chongxinfabu)
    RoundRelativeLayout rrlChongxinfabu;
    @BindView(R.id.iv_dizhi_icon)
    ImageView ivDizhiIcon;

    private String state;

    String phone;
    String weiXinHao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smartRefreshLayout.setEnableLoadMore(false);
        irId = getIntent().getStringExtra("irId");
        operate_type = getIntent().getStringExtra("operate_type");
        state = getIntent().getStringExtra("state");
        rlDadianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(mContext, "打电话");
                callPhone(phone);
            }
        });
        rlJiaweixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(mContext, "加微信");

                copyContentToClipboard(weiXinHao, mContext);
                try {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // TODO: handle exception
                    Toast.makeText(mContext, "检查到您手机没有安装微信，请安装后使用该功能", Toast.LENGTH_SHORT).show();
                }


            }
        });
        rlYuefuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "约服务");
            }
        });
        rlFenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickShare();
            }
        });

        getData();

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
                        GongJiangRuZhuBianJiActivity.actionStart(mContext, irId);
                        finish();
                    }
                });
                llJujue.setVisibility(View.VISIBLE);
            }

            llBottom.setVisibility(View.GONE);
            rlShenhezhuangtai.setVisibility(View.VISIBLE);

        } else {
            rlShenhezhuangtai.setVisibility(View.GONE);
            iv_rightTitle.setVisibility(View.GONE);
            llBottom.setVisibility(View.VISIBLE);
            rrlChongxinfabu.setVisibility(View.GONE);
            operate_type = "1";
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_gongjiang_xinxi;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    GongJiangXinXiModel.DataBean dataBean;

    private void clickShare() {
        if (dataBean != null) {
            ShareActivity.actionStart(mContext,
                    dataBean.getShare_title(),
                    dataBean.getShare_detail(),
                    dataBean.getShare_url() + "&x=" + dataBean.getX() + "&y=" + dataBean.getY(),
                    dataBean.getShare_img());
        }
    }

    TishiDialog tishiDialog;

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("工匠信息");
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

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String irId, String operate_type, String state, String stateName) {
        Intent intent = new Intent(context, GongJiangXinXiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("irId", irId);
        intent.putExtra("operate_type", operate_type);
        intent.putExtra("state", state);
        intent.putExtra("stateName", stateName);
        context.startActivity(intent);
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String irId, String operate_type) {
        Intent intent = new Intent(context, GongJiangXinXiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("irId", irId);
        intent.putExtra("operate_type", operate_type);
        context.startActivity(intent);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    /**
     * 复制内容到剪贴板
     *
     * @param content
     * @param context
     */
    public void copyContentToClipboard(String content, Context context) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", content);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);

        UIHelper.ToastMessage(mContext, "已复制微信号");
    }

    public String irId;
    public String operate_type = "2";

    public void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17006");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("x", PreferenceHelper.getInstance(mContext).getString(WEIDU, ""));
        map.put("y", PreferenceHelper.getInstance(mContext).getString(JINGDU, ""));
        map.put("ir_id", irId);
        map.put("operate_type", operate_type);
        Gson gson = new Gson();
        OkGo.<AppResponse<GongJiangXinXiModel.DataBean>>post(TONGCHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GongJiangXinXiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GongJiangXinXiModel.DataBean>> response) {
                        Logger.d(gson.toJson(response.body()));
                        irId = response.body().data.get(0).getIr_id();
                        dataBean = response.body().data.get(0);
                        GongJiangXinXiModel.DataBean dataBean = response.body().data.get(0);
                        Glide.with(mContext).load(dataBean.getIr_personnal_img_url()).into(ivImage);
                        tvGongjiangMing.setText(dataBean.getIr_personnal_name());
                        tvJianjie.setText(dataBean.getIr_validity());

                        if (StringUtils.isEmpty(dataBean.meter)) {
                            ivDizhiIcon.setVisibility(View.GONE);
                        } else {
                            tvGonglishu.setText(dataBean.meter + "km");
                        }


                        llTupianheji.removeAllViews();
                        for (int i = 0; i < response.body().data.get(0).getImgList().size(); i++) {
                            View view = View.inflate(mContext, R.layout.item_big_image, null);
                            ImageView iv = view.findViewById(R.id.iv_img);

                            if (!StringUtils.isEmpty(response.body().data.get(0).getImgList().get(i).getHeight())) {
                                RequestOptions requestOptions = new RequestOptions();
                                requestOptions.override(Integer.valueOf(response.body().data.get(0).getImgList().get(i).getWidth()), Integer.valueOf(response.body().data.get(0).getImgList().get(i).getHeight()));
                                Glide.with(mContext).load(response.body().data.get(0).getImgList().get(i).getIr_img_url()).apply(requestOptions).into(iv);
                            }
                            llTupianheji.addView(view);
                        }
                        tvJujueYuanyin.setText(response.body().data.get(0).getIr_manage_text());

                        llTag.removeAllViews();

                        if (dataBean.getType_array() != null) {
                            for (int i = 0; i < dataBean.getType_array().size(); i++) {
                                String inst_device_name = dataBean.getType_array().get(i).getName();
                                View view = View.inflate(mContext, R.layout.tongcheng_item_home_item_type, null);
                                TextView tv_tag = view.findViewById(R.id.tv_tag);
                                tv_tag.setText(inst_device_name);
                                llTag.addView(view);
                            }
                        }
                        tvJujueYuanyin.setText("拒绝原因：" + dataBean.getIr_manage_text());
                        phone = dataBean.getIr_user_phone();
                        weiXinHao = dataBean.getIr_wx_number();
                    }

                    @Override
                    public void onError(Response<AppResponse<GongJiangXinXiModel.DataBean>> response) {

                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<GongJiangXinXiModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
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

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
