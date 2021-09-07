package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.ZhiNengJiaJuZhuangZhiSetting;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.WenShiDuChuanGanQiModel;
import com.yiyang.cn.util.icon_util.LineChart02View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class WenShiDuChuanGanQiActivity extends BaseActivity {
    @BindView(R.id.tv_wendu)
    TextView tvWendu;
    @BindView(R.id.tv_wenduzhi)
    TextView tvWenduzhi;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.tv_shidu)
    TextView tvShidu;
    @BindView(R.id.tv_shiduzhi)
    TextView tvShiduzhi;
    @BindView(R.id.iv_icon_1)
    ImageView ivIcon1;
    @BindView(R.id.tv_dangqianwendu)
    TextView tvDangqianwendu;
    @BindView(R.id.tv_dangqianshidu)
    TextView tvDangqianshidu;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.tv_dangqianwendu_huashu)
    TextView tvDangqianwenduHuashu;
    @BindView(R.id.iv_wendu_icon)
    ImageView ivWenduIcon;
    @BindView(R.id.iv_shidu_icon)
    ImageView ivShiduIcon;
    @BindView(R.id.rll_zhumianban)
    RoundRelativeLayout rllZhumianban;
    private String device_ccid;
    String device_ccid_up;
    private String device_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device_id = getIntent().getStringExtra("device_id");
        device_ccid = getIntent().getStringExtra("device_ccid");
        device_ccid_up = getIntent().getStringExtra("device_ccid_up");
        getnet();
        rllZhumianban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShuJuQuXianActivity.actionStart(mContext, device_id);
            }
        });
    }


    private void huiZhiYeMian(Context mContext, List<WenShiDuChuanGanQiModel.DataBean.HumListBean> humListBeans, List<WenShiDuChuanGanQiModel.DataBean.TemListBean> temListBeans) {
        FrameLayout content = new FrameLayout(this);

        //缩放控件放置在FrameLayout的上层，用于放大缩小图表
        FrameLayout.LayoutParams frameParm = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameParm.gravity = Gravity.BOTTOM | Gravity.RIGHT;

		   /*
		  //缩放控件放置在FrameLayout的上层，用于放大缩小图表
	       mZoomControls = new ZoomControls(this);
	       mZoomControls.setIsZoomInEnabled(true);
	       mZoomControls.setIsZoomOutEnabled(true);
		   mZoomControls.setLayoutParams(frameParm);
		   */

        //图表显示范围在占屏幕大小的90%的区域内
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int scrWidth = (int) (dm.widthPixels * 0.9);
        int scrHeight = (int) (dm.heightPixels * 0.9);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                scrWidth, scrHeight);

        //居中显示
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        //图表view放入布局中，也可直接将图表view放入Activity对应的xml文件中
        final RelativeLayout chartLayout = new RelativeLayout(this);

        chartLayout.addView(new LineChart02View(mContext, humListBeans, temListBeans), layoutParams);
        llMain.addView(chartLayout);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_wenshidu_chuanguanqi;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("温湿度传感器");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhiNengJiaJuZhuangZhiSetting.actionStart(mContext, device_id);
            }
        });
        iv_rightTitle.setBackgroundResource(R.mipmap.fengnuan_icon_shezhi);
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
    public static void actionStart(Context context, String device_id, String device_ccid, String device_ccid_up) {
        Intent intent = new Intent(context, WenShiDuChuanGanQiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_id", device_id);
        intent.putExtra("device_ccid", device_ccid);
        intent.putExtra("device_ccid_up", device_ccid_up);
        context.startActivity(intent);
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16035");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<WenShiDuChuanGanQiModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<WenShiDuChuanGanQiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<WenShiDuChuanGanQiModel.DataBean>> response) {
                        showLoadSuccess();
                        WenShiDuChuanGanQiModel.DataBean dataBean = response.body().data.get(0);
                        tvWenduzhi.setText(dataBean.getDevice_tem());
                        tvShiduzhi.setText(dataBean.getDevice_hum());
                        Glide.with(mContext).load(dataBean.getEnvironment_status_img()).into(ivIcon1);
                        tvDangqianwenduHuashu.setText(dataBean.getEnvironment_status_des());
                        huiZhiYeMian(mContext, dataBean.getHum_list(), dataBean.getTem_list());

                        tvDangqianwendu.setText("当前温度" + dataBean.getDevice_tem() + "℃");
                        tvDangqianshidu.setText("当前湿度" + dataBean.getDevice_hum() + "%");

                    }

                    @Override
                    public void onError(Response<AppResponse<WenShiDuChuanGanQiModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                        showLoadSuccess();
                    }

                    @Override
                    public void onStart(Request<AppResponse<WenShiDuChuanGanQiModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        showLoadSuccess();
                    }
                });
    }
}
