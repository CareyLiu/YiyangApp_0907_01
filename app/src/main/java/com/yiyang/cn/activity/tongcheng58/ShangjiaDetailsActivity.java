package com.yiyang.cn.activity.tongcheng58;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.LatLng;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.fenxiang_tuisong.ShareActivity;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tongcheng58.model.ShangjiaDetailModel;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.Radius_GlideImageLoader;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.util.NavigationUtils;
import com.yiyang.cn.view.AutoNextLineLinearlayout;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

import static com.yiyang.cn.config.Wetch_S.APP_ID;

public class ShangjiaDetailsActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner banner;
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
    @BindView(R.id.bt_phone)
    RoundRelativeLayout bt_phone;
    @BindView(R.id.bt_weixin)
    RoundRelativeLayout bt_weixin;
    @BindView(R.id.bt_daohang)
    RoundRelativeLayout bt_daohang;
    @BindView(R.id.bt_fenxiang)
    RoundRelativeLayout bt_fenxiang;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_zhekou)
    TextView tv_zhekou;
    private String ir_id;
    private String x_weidu;
    private String y_jingdu;
    private ShangjiaDetailModel.DataBean dataBean;
    private IWXAPI api;

    @Override
    public int getContentViewResId() {
        return R.layout.tongcheng_act_shangjia_details;
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
    }

    public static void actionStart(Context context, String ir_id) {
        Intent intent = new Intent(context, ShangjiaDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ir_id", ir_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);//创建一个实例
        api.registerApp(APP_ID);//注册实例
        ir_id = getIntent().getStringExtra("ir_id");
        x_weidu = PreferenceHelper.getInstance(mContext).getString(App.WEIDU, "");
        y_jingdu = PreferenceHelper.getInstance(mContext).getString(App.JINGDU, "");

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
        map.put("x", x_weidu);
        map.put("y", y_jingdu);
        map.put("ir_id", ir_id);
        map.put("operate_type", "1");
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
                            tv_juli_address.setText(dataBean.getMeter() + "km  " + dataBean.getAddr());
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

    @OnClick({R.id.bt_phone, R.id.bt_weixin, R.id.bt_daohang, R.id.bt_fenxiang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_phone:
                clickPhone();
                break;
            case R.id.bt_weixin:
                copyContentToClipboard();
                try {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // TODO: handle exception
                    Toast.makeText(this, "检查到您手机没有安装微信，请安装后使用该功能", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.bt_daohang:
                clickDaohang();
                break;
            case R.id.bt_fenxiang:
                clickShare();
                break;
        }
    }

    private void clickDaohang() {
        if (dataBean != null) {
            try {
                double x = Y.getDouble(dataBean.getX());
                double y = Y.getDouble(dataBean.getY());
                LatLng latLng = new LatLng(x, y);
                NavigationUtils.Navigation(latLng);
            } catch (Exception e) {
                UIHelper.ToastMessage(MyApplication.getApp().getApplicationContext(), "请下载高德后重新尝试", Toast.LENGTH_SHORT);
            }
        }
    }

    private void clickShare() {
        if (dataBean != null) {
            ShareActivity.actionStart(mContext,
                    dataBean.getShare_title(),
                    dataBean.getShare_detail(),
                    dataBean.getShare_url() + "&x=" + x_weidu + "&y=" + y_jingdu,
                    dataBean.getShare_img());
        }
    }

    private void clickPhone() {
        String ir_contact_phone = dataBean.getIr_contact_phone();
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + ir_contact_phone);
                intent.setData(data);
                startActivity(intent);
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.setTextTitle("拨打电话");
        dialog.setTextContent(ir_contact_phone);
        dialog.show();
    }


    /**
     * 复制内容到剪贴板
     */
    public void copyContentToClipboard() {
        if (dataBean != null) {
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", dataBean.getIr_wx_number());
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);

            UIHelper.ToastMessage(mContext, "已复制微信号");
        }
    }


}
