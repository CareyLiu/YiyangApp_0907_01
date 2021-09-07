package com.yiyang.cn.activity.zhinengjiaju;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.ZhiNengJiaJuZhuangZhiSetting;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.KongQiJianCeShuoMingDialog;
import com.yiyang.cn.fragment.Co2Fragment;
import com.yiyang.cn.fragment.JiaQuanFragment;
import com.yiyang.cn.fragment.KongQiZhiLiangFragment;
import com.yiyang.cn.fragment.Pm2Dian5Fragment;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.KongQiJianCeModel;
import com.yiyang.cn.model.KongQiJianCeZ;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class KongQiJianCe_NewActvity extends BaseActivity {


    @BindView(R.id.tv_show_text)
    TextView tvShowText;
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.rl_yuanhuan)
    RelativeLayout rlYuanhuan;
    @BindView(R.id.iv_shuoming)
    ImageView ivShuoming;
    @BindView(R.id.iv_icon_kongqizhiliang)
    ImageView ivIconKongqizhiliang;
    @BindView(R.id.tv_kongqi_zhiliang)
    TextView tvKongqiZhiliang;
    @BindView(R.id.ll_kongqizhiliang)
    LinearLayout llKongqizhiliang;
    @BindView(R.id.iv_icon_pm)
    ImageView ivIconPm;
    @BindView(R.id.tv_pm_text)
    TextView tvPmText;
    @BindView(R.id.ll_pm2dian5)
    LinearLayout llPm2dian5;
    @BindView(R.id.iv_icon_jiaquan)
    ImageView ivIconJiaquan;
    @BindView(R.id.tv_jiaquan)
    TextView tvJiaquan;
    @BindView(R.id.ll_jiaquan)
    LinearLayout llJiaquan;
    @BindView(R.id.iv_icon_eryanghuatan)
    ImageView ivIconEryanghuatan;
    @BindView(R.id.tv_co2)
    TextView tvCo2;
    @BindView(R.id.ll_co2)
    LinearLayout llCo2;
    @BindView(R.id.tv_kongqizhiliang_danwei)
    TextView tvKongqizhiliangDanwei;
    @BindView(R.id.tv_kongqizhiliang_text)
    TextView tvKongqizhiliangText;
    @BindView(R.id.tv_pm2dian5_danwei)
    TextView tvPm2dian5Danwei;
    @BindView(R.id.tv_pm2dian5_text)
    TextView tvPm2dian5Text;
    @BindView(R.id.tv_jiaquan_danwei)
    TextView tvJiaquanDanwei;
    @BindView(R.id.tv_jiaquan_text)
    TextView tvJiaquanText;
    @BindView(R.id.tv_eryanghuatan_danwei)
    TextView tvEryanghuatanDanwei;
    @BindView(R.id.tv_co2_text)
    TextView tvCo2Text;
    @BindView(R.id.ll_kongqizhiliang_quxian)
    LinearLayout llKongqizhiliangQuxian;
    @BindView(R.id.rl_yan)
    RelativeLayout rlYan;
    @BindView(R.id.rl_jiaquan)
    RelativeLayout rlJiaquan;
    @BindView(R.id.tv_max)
    TextView tvMax;
    @BindView(R.id.rl_kongqizhiliang)
    RelativeLayout rlKongqizhiliang;
    @BindView(R.id.rl_pm2dian5)
    RelativeLayout rlPm2dian5;
    @BindView(R.id.ll_pm2dian5_quxian)
    LinearLayout llPm2dian5Quxian;
    @BindView(R.id.ll_jiaquan_quxian)
    LinearLayout llJiaquanQuxian;
    @BindView(R.id.rl_co2)
    RelativeLayout rlCo2;
    @BindView(R.id.ll_co2_quxian)
    LinearLayout llCo2Quxian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device_id = getIntent().getStringExtra("device_id");
        Locale locale = Locale.CHINA;

        // UIHelper.ToastMessage(mContext, "我的device_id是" + device_id);
//        FrameLayout content = new FrameLayout(this);
//
//        //缩放控件放置在FrameLayout的上层，用于放大缩小图表
//        FrameLayout.LayoutParams frameParm = new FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        frameParm.gravity = Gravity.BOTTOM | Gravity.RIGHT;

		   /*
		  //缩放控件放置在FrameLayout的上层，用于放大缩小图表
	       mZoomControls = new ZoomControls(this);
	       mZoomControls.setIsZoomInEnabled(true);
	       mZoomControls.setIsZoomOutEnabled(true);
		   mZoomControls.setLayoutParams(frameParm);
		   */

//        //图表显示范围在占屏幕大小的90%的区域内
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        int scrWidth = (int) (dm.widthPixels * 0.9);
//        int scrHeight = (int) (dm.heightPixels * 0.4);
//        layoutParams = new RelativeLayout.LayoutParams(
//                scrWidth, scrHeight);
//
//        //居中显示
//        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        //图表view放入布局中，也可直接将图表view放入Activity对应的xml文件中
//        final RelativeLayout chartLayout = new RelativeLayout(this);


//        rlJiaquan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KongQiJianCeXiangXiActivity.actionStart(mContext, device_id, "1");
//            }
//        });

//        rlKongqizhiliang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KongQiJianCeXiangXiActivity.actionStart(mContext, device_id, "2");
//            }
//        });
        getnet();
        getFouData();

        ivShuoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (strType.equals("kongQiZhiLiang")) {
                    KongQiJianCeShuoMingDialog kongQiJianCeShuoMingDialog = new KongQiJianCeShuoMingDialog(mContext, "空气质量值小于等于350：\n" +
                            "说明空气质量为优。\n" +
                            "空气质量值大于350 且小于等于750：\n" +
                            "表明空气质量良好。\n" +
                            "空气质量值大于750且小于1150：\n" +
                            "表明空气质量为轻度污染，长期接触，易感人群病状\n" +
                            "有轻度加剧，健康人群出现刺激症状。\n" +
                            "空气质量值大于1150：\n" +
                            "表明空气质量较差，一定时间接触后，对人体危害较大", "空气检测说明");
                    kongQiJianCeShuoMingDialog.show();
                } else if (strType.equals("pm2dian5")) {
                    KongQiJianCeShuoMingDialog kongQiJianCeShuoMingDialog = new KongQiJianCeShuoMingDialog(mContext, "24小时PM2.5平均值标准值\n" +
                            "优 0~35、良 35~75、轻度污染 75~115、\n" +
                            "中度污染 115~150、重度污染 150~250、\n" +
                            "严重污染 大于250及以上。", "pm2.5说明");
                    kongQiJianCeShuoMingDialog.show();
                } else if (strType.equals("jiaQuan")) {
                    KongQiJianCeShuoMingDialog kongQiJianCeShuoMingDialog = new KongQiJianCeShuoMingDialog(mContext, "根据国家强制性标准，关闭门窗1小时后，每立方米室内空气中，甲醛释放量不得大于80微克/立方米；如达到100-200微克/立方米，50%的正常人能闻到臭气;达到200-500微克/立方米，眼睛、气管将受到强烈刺激，出现打喷嚏、咳嗽等症状；达到1000微克/立方米以上，呼吸困难;达到5000微克/立方米以上，会\n" +
                            "引发肺炎等危重疾病，甚至导致死亡。", "甲醛说明");
                    kongQiJianCeShuoMingDialog.show();
                } else if (strType.equals("erYangHuaTan")) {
                    KongQiJianCeShuoMingDialog kongQiJianCeShuoMingDialog = new KongQiJianCeShuoMingDialog(mContext, "·150～350：是不可能的\n" +
                            "·350～450ppm：同一般室外环境 　　\n" +
                            "·350～1200ppm：空气清新，呼吸顺畅 　　\n" +
                            "·1200～2500ppm：感觉空气浑浊,并开始觉得昏昏欲睡 　　\n" +
                            "·2500～5000ppm：感觉头痛、嗜睡、呆滞、注意力无\n" +
                            "法集中、心跳加速、轻度恶心 　　\n" +
                            "·大于5000ppm：可能导致严重缺氧，造成永久性脑损\n" +
                            "伤、昏迷、甚至死亡\n", "二氧化碳说明");
                    kongQiJianCeShuoMingDialog.show();
                }


            }
        });
    }

    private void getFouData() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16035");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);

        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<KongQiJianCeZ.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<KongQiJianCeZ.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<KongQiJianCeZ.DataBean>> response) {
                        showLoadSuccess();
                        tvShowText.setText("AQI");
                        setChuShi4XiangZhi(R.id.ll_kongqizhiliang);
                        if (response.body().data.size() == 0) {
                            return;
                        }
                        if (response.body().data.get(0).getGas_detection_list().size() == 0) {
                            return;
                        }

//String str = String.valueOf(Integer.parseInt(s));
                        tvJiaquan.setText(String.valueOf(Integer.parseInt(response.body().data.get(0).getGd_cascophen())));
                        tvPmText.setText(String.valueOf(Integer.parseInt(response.body().data.get(0).getGd_particulate_matter())));
                        tvKongqiZhiliang.setText(String.valueOf(Integer.parseInt(response.body().data.get(0).getGd_air_quality())));
                        tvCo2.setText(String.valueOf(Integer.parseInt(response.body().data.get(0).getGd_carbon_dioxide())));

                        tvShow.setText(String.valueOf(Integer.parseInt(response.body().data.get(0).getGd_air_quality())));

                    }

                    @Override
                    public void onError(Response<AppResponse<KongQiJianCeZ.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                        Activity activity = new Activity();
                        activity.closeContextMenu();

                    }

                    @Override
                    public void onStart(Request<AppResponse<KongQiJianCeZ.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16074");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);
        map.put("date_type", "1");
        Calendar now = Calendar.getInstance();

        int month = now.get(Calendar.MONTH) + 1;
        String month_last;
        if (month < 10) {
            month_last = "0" + month;
        } else {
            month_last = String.valueOf(month);
        }
        String nianYueRi = now.get(Calendar.YEAR) + "-" + month_last + "-" + now.get(Calendar.DAY_OF_MONTH);
        map.put("time", nianYueRi);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<KongQiJianCeModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<KongQiJianCeModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<KongQiJianCeModel.DataBean>> response) {
                        showLoadSuccess();
                        // SplineChart03View splineChart03View = new SplineChart03View(mContext, response.body().data.get(0).getGd_list(), "1");
                        // llMain.addView(splineChart03View, layoutParams);
                        // SplineChart03View splineChart03View1 = new SplineChart03View(mContext, response.body().data.get(0).getGd_list(), "2");
                        // llMain2.addView(splineChart03View1, layoutParams);
                        getSupportFragmentManager()    //
                                .beginTransaction()
                                .add(R.id.ll_kongqizhiliang_quxian, new KongQiZhiLiangFragment(response.body().data.get(0).getGd_list(), "0"))   // 此处的R.id.fragment_container是要盛放fragment的父容器
                                .commit();

                        getSupportFragmentManager()    //
                                .beginTransaction()
                                .add(R.id.ll_jiaquan_quxian, new JiaQuanFragment(response.body().data.get(0).getGd_list(), "0"))   // 此处的R.id.fragment_container是要盛放fragment的父容器
                                .commit();

                        getSupportFragmentManager()    //
                                .beginTransaction()
                                .add(R.id.ll_co2_quxian, new Co2Fragment(response.body().data.get(0).getGd_list(), "0"))   // 此处的R.id.fragment_container是要盛放fragment的父容器
                                .commit();

                        getSupportFragmentManager()    //
                                .beginTransaction()
                                .add(R.id.ll_pm2dian5_quxian, new Pm2Dian5Fragment(response.body().data.get(0).getGd_list(), "0"))   // 此处的R.id.fragment_container是要盛放fragment的父容器
                                .commit();
                    }

                    @Override
                    public void onError(Response<AppResponse<KongQiJianCeModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());

                    }

                    @Override
                    public void onStart(Request<AppResponse<KongQiJianCeModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });

    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_kongqi_jiance;
    }


    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    String device_id;

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("空气检测");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SuiYiTieSetting.actionStart(mContext, "", "");
                ZhiNengJiaJuZhuangZhiSetting.actionStart(mContext, device_id);
            }
        });


        iv_rightTitle.setBackgroundResource(R.mipmap.fengnuan_icon_shezhi);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */

    public static void actionStart(Context context, String device_id) {
        Intent intent = new Intent(context, KongQiJianCe_NewActvity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_id", device_id);
        context.startActivity(intent);
    }

    String strType = "kongQiZhiLiang";

    @OnClick({R.id.ll_kongqizhiliang, R.id.ll_pm2dian5, R.id.ll_jiaquan, R.id.ll_co2, R.id.rl_kongqizhiliang, R.id.rl_pm2dian5, R.id.rl_jiaquan, R.id.rl_co2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_kongqizhiliang:
                strType = "kongQiZhiLiang";
                tvShowText.setText("AQI");
                //UIHelper.ToastMessage(mContext, "空气质量");
                setChuShi4XiangZhi(R.id.ll_kongqizhiliang);
                rlYan.setBackgroundResource(R.mipmap.airmonitor_smoke_green);
                rlYan.setVisibility(View.GONE);
                tvMax.setText("Max 5000");

                if (!StringUtils.isEmpty(tvKongqiZhiliang.getText().toString().trim())) {
                    Integer kongQiZhiLiang = Integer.valueOf(tvKongqiZhiliang.getText().toString().trim());
                    if (kongQiZhiLiang < 350) {
                        tvText.setText("优");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (kongQiZhiLiang > 350 && kongQiZhiLiang < 750) {
                        tvText.setText("良");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (kongQiZhiLiang > 750 && kongQiZhiLiang < 1150) {
                        tvText.setText("轻度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (kongQiZhiLiang > 1150 && kongQiZhiLiang < 1500) {
                        tvText.setText("中度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (kongQiZhiLiang > 1500) {
                        tvText.setText("重度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    }
                }
                break;

            case R.id.ll_pm2dian5:
                strType = "pm2dian5";
                tvShowText.setText("PM2.5");
                //UIHelper.ToastMessage(mContext, "pm2.5");
                setChuShi4XiangZhi(R.id.ll_pm2dian5);
                rlYan.setBackgroundResource(R.mipmap.airmonitor_smoke_yellow);
                /**
                 * 1.小于35 非常好
                 * 2.大于35且小于75 良
                 * 3.大于75 且小于115 轻度污染
                 * 4.大于150且小于250 中度污染
                 * 5.大于250及以上    严重污染
                 */
                tvMax.setText("Max 1000");
                if (!StringUtils.isEmpty(tvPmText.getText().toString())) {
                    Integer pm2Dian5 = Integer.valueOf(tvPmText.getText().toString().trim());
                    if (pm2Dian5 < 35) {
                        tvText.setText("优");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (pm2Dian5 > 35 && pm2Dian5 < 75) {
                        tvText.setText("良");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (pm2Dian5 > 75 && pm2Dian5 < 115) {
                        tvText.setText("轻度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (pm2Dian5 > 115 && pm2Dian5 < 150) {
                        tvText.setText("中度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (pm2Dian5 > 150 && pm2Dian5 < 250) {
                        tvText.setText("重度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (pm2Dian5 > 250) {
                        tvText.setText("严重污染");
                        rlYan.setVisibility(View.VISIBLE);
                    }
                }


                break;
            case R.id.ll_jiaquan:
                tvShowText.setText("CH2O");
                strType = "jiaQuan";
                //UIHelper.ToastMessage(mContext, "甲醛");
                setChuShi4XiangZhi(R.id.ll_jiaquan);
                rlYan.setBackgroundResource(R.mipmap.airmonitor_smoke_red);
                tvMax.setText("Max 1000");

                if (!StringUtils.isEmpty(tvJiaquan.getText().toString().trim())) {
                    Integer jiaquan = Integer.valueOf(tvJiaquan.getText().toString().trim());
                    /**
                     * 单位 - 微米
                     * 1.小于80 正常
                     * 2.100-200 轻度污染
                     * 3.200-500 中度污染
                     * 4.500及以上 重度污染
                     */
                    if (jiaquan < 80) {
                        tvText.setText("正常");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (jiaquan > 80 && jiaquan < 100) {
                        tvText.setText("轻度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (jiaquan > 100 && jiaquan < 200) {
                        tvText.setText("中度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (jiaquan > 200 && jiaquan < 500) {
                        tvText.setText("重度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (jiaquan > 500) {
                        tvText.setText("严重污染");
                        rlYan.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.ll_co2:
                strType = "erYangHuaTan";
                tvShowText.setText("CO2");
                //UIHelper.ToastMessage(mContext, "CO2");
                setChuShi4XiangZhi(R.id.ll_co2);
                rlYan.setBackgroundResource(R.mipmap.airmonitor_smoke_pink);
                tvMax.setText("Max 5000");

                if (!StringUtils.isEmpty(tvCo2.getText().toString().trim())) {
                    Integer co2Int = Integer.valueOf(tvCo2.getText().toString().trim());
                    /**
                     * 1.0-450 优
                     * 2.450-1000 良
                     * 3.1000-2000 轻度污染
                     * 4.2000-5000 中度污染
                     * 5.5000以上  重度污染
                     */
                    if (co2Int < 450) {
                        tvText.setText("优");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (co2Int > 450 && co2Int < 1000) {
                        tvText.setText("良");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (co2Int > 1000 && co2Int < 2000) {
                        tvText.setText("轻度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (co2Int > 2000 && co2Int < 5000) {
                        tvText.setText("中度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (co2Int > 5000) {
                        tvText.setText("重度污染");
                        rlYan.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.rl_kongqizhiliang:
                KongQiJianCeXiangXi_NewActivity.actionStart(mContext, device_id, "1");
                break;
            case R.id.rl_pm2dian5:
                KongQiJianCeXiangXi_NewActivity.actionStart(mContext, device_id, "2");
                break;
            case R.id.rl_jiaquan:
                KongQiJianCeXiangXi_NewActivity.actionStart(mContext, device_id, "3");
                break;
            case R.id.rl_co2:
                KongQiJianCeXiangXi_NewActivity.actionStart(mContext, device_id, "4");
                break;
        }
    }


    public void setChuShi4XiangZhi(int id) {

        if (id == R.id.ll_kongqizhiliang) {
            ivIconKongqizhiliang.setBackgroundResource(R.mipmap.airmonitor_kongqizhiliang_wt);
            llKongqizhiliang.setBackgroundResource(R.drawable.blue_con_8dp);
            //            ivIconKongqizhiliang.setBackgroundResource(R.mipmap.airmonitor_kongqizhiliang_wt);
//            llKongqizhiliang.setBackgroundResource(R.drawable.blue_con_8dp);


            tvShow.setText(tvKongqiZhiliang.getText().toString());


            ivIconPm.setBackgroundResource(R.mipmap.airmonitor_pm_bk);
            llPm2dian5.setBackgroundResource(R.drawable.gray_con_8dp);

            ivIconJiaquan.setBackgroundResource(R.mipmap.airmonitor_jiaquan_bk);
            llJiaquan.setBackgroundResource(R.drawable.gray_con_8dp);

            ivIconEryanghuatan.setBackgroundResource(R.mipmap.airmonitor_co2_bk);
            llCo2.setBackgroundResource(R.drawable.gray_con_8dp);

            tvKongqizhiliangDanwei.setTextColor(mContext.getResources().getColor(R.color.white));
            tvKongqiZhiliang.setTextColor(mContext.getResources().getColor(R.color.white));
            tvKongqizhiliangText.setTextColor(mContext.getResources().getColor(R.color.white));

            tvPm2dian5Danwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvPm2dian5Text.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvPmText.setTextColor(mContext.getResources().getColor(R.color.black_333333));

            tvJiaquan.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvJiaquanDanwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvJiaquanText.setTextColor(mContext.getResources().getColor(R.color.black_333333));

            tvCo2.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvCo2Text.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvEryanghuatanDanwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));


        } else if (id == R.id.ll_pm2dian5) {
            tvShow.setText(tvPmText.getText().toString());
            ivIconPm.setBackgroundResource(R.mipmap.airmonitor_pm_bk);
            llPm2dian5.setBackgroundResource(R.drawable.blue_con_8dp);

            ivIconKongqizhiliang.setBackgroundResource(R.mipmap.airmonitor_kongqizhiliang_bk);
            llKongqizhiliang.setBackgroundResource(R.drawable.gray_con_8dp);

//            ivIconPm.setBackgroundResource(R.mipmap.airmonitor_pm_bk);
//            llPm2dian5.setBackgroundResource(R.drawable.gray_con_8dp);

            ivIconJiaquan.setBackgroundResource(R.mipmap.airmonitor_jiaquan_bk);
            llJiaquan.setBackgroundResource(R.drawable.gray_con_8dp);

            ivIconEryanghuatan.setBackgroundResource(R.mipmap.airmonitor_co2_bk);
            llCo2.setBackgroundResource(R.drawable.gray_con_8dp);

            tvKongqizhiliangDanwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvKongqiZhiliang.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvKongqizhiliangText.setTextColor(mContext.getResources().getColor(R.color.black_333333));

            tvPm2dian5Danwei.setTextColor(mContext.getResources().getColor(R.color.white));
            tvPm2dian5Text.setTextColor(mContext.getResources().getColor(R.color.white));
            tvPmText.setTextColor(mContext.getResources().getColor(R.color.white));

            tvJiaquan.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvJiaquanDanwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvJiaquanText.setTextColor(mContext.getResources().getColor(R.color.black_333333));

            tvCo2.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvCo2Text.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvEryanghuatanDanwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));

        } else if (id == R.id.ll_jiaquan) {
            tvShow.setText(tvJiaquan.getText().toString());
            ivIconJiaquan.setBackgroundResource(R.mipmap.airmonitor_jiaquan_wt);
            llJiaquan.setBackgroundResource(R.drawable.blue_con_8dp);

            ivIconKongqizhiliang.setBackgroundResource(R.mipmap.airmonitor_kongqizhiliang_bk);
            llKongqizhiliang.setBackgroundResource(R.drawable.gray_con_8dp);

            ivIconPm.setBackgroundResource(R.mipmap.airmonitor_pm_bk);
            llPm2dian5.setBackgroundResource(R.drawable.gray_con_8dp);

            ivIconEryanghuatan.setBackgroundResource(R.mipmap.airmonitor_co2_bk);
            llCo2.setBackgroundResource(R.drawable.gray_con_8dp);

            tvKongqizhiliangDanwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvKongqiZhiliang.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvKongqizhiliangText.setTextColor(mContext.getResources().getColor(R.color.black_333333));

            tvPm2dian5Danwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvPm2dian5Text.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvPmText.setTextColor(mContext.getResources().getColor(R.color.black_333333));

            tvJiaquan.setTextColor(mContext.getResources().getColor(R.color.white));
            tvJiaquanDanwei.setTextColor(mContext.getResources().getColor(R.color.white));
            tvJiaquanText.setTextColor(mContext.getResources().getColor(R.color.white));

            tvCo2.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvCo2Text.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvEryanghuatanDanwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));


        } else if (id == R.id.ll_co2) {
            tvShow.setText(tvCo2.getText().toString());
            ivIconEryanghuatan.setBackgroundResource(R.mipmap.airmonitor_co2_wt);
            llCo2.setBackgroundResource(R.drawable.blue_con_8dp);

            ivIconKongqizhiliang.setBackgroundResource(R.mipmap.airmonitor_kongqizhiliang_bk);
            llKongqizhiliang.setBackgroundResource(R.drawable.gray_con_8dp);

            ivIconPm.setBackgroundResource(R.mipmap.airmonitor_pm_bk);
            llPm2dian5.setBackgroundResource(R.drawable.gray_con_8dp);

            ivIconJiaquan.setBackgroundResource(R.mipmap.airmonitor_jiaquan_bk);
            llJiaquan.setBackgroundResource(R.drawable.gray_con_8dp);

            tvKongqizhiliangDanwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvKongqiZhiliang.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvKongqizhiliangText.setTextColor(mContext.getResources().getColor(R.color.black_333333));

            tvPm2dian5Danwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvPm2dian5Text.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvPmText.setTextColor(mContext.getResources().getColor(R.color.black_333333));


            tvJiaquan.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvJiaquanDanwei.setTextColor(mContext.getResources().getColor(R.color.black_333333));
            tvJiaquanText.setTextColor(mContext.getResources().getColor(R.color.black_333333));

            tvCo2.setTextColor(mContext.getResources().getColor(R.color.white));
            tvCo2Text.setTextColor(mContext.getResources().getColor(R.color.white));
            tvEryanghuatanDanwei.setTextColor(mContext.getResources().getColor(R.color.white));
        }
    }


}
