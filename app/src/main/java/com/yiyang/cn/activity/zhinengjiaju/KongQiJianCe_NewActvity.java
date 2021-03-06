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

        // UIHelper.ToastMessage(mContext, "??????device_id???" + device_id);
//        FrameLayout content = new FrameLayout(this);
//
//        //?????????????????????FrameLayout????????????????????????????????????
//        FrameLayout.LayoutParams frameParm = new FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        frameParm.gravity = Gravity.BOTTOM | Gravity.RIGHT;

		   /*
		  //?????????????????????FrameLayout????????????????????????????????????
	       mZoomControls = new ZoomControls(this);
	       mZoomControls.setIsZoomInEnabled(true);
	       mZoomControls.setIsZoomOutEnabled(true);
		   mZoomControls.setLayoutParams(frameParm);
		   */

//        //???????????????????????????????????????90%????????????
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        int scrWidth = (int) (dm.widthPixels * 0.9);
//        int scrHeight = (int) (dm.heightPixels * 0.4);
//        layoutParams = new RelativeLayout.LayoutParams(
//                scrWidth, scrHeight);
//
//        //????????????
//        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        //??????view???????????????????????????????????????view??????Activity?????????xml?????????
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
                    KongQiJianCeShuoMingDialog kongQiJianCeShuoMingDialog = new KongQiJianCeShuoMingDialog(mContext, "???????????????????????????350???\n" +
                            "???????????????????????????\n" +
                            "?????????????????????350 ???????????????750???\n" +
                            "???????????????????????????\n" +
                            "?????????????????????750?????????1150???\n" +
                            "?????????????????????????????????????????????????????????????????????\n" +
                            "???????????????????????????????????????????????????\n" +
                            "?????????????????????1150???\n" +
                            "????????????????????????????????????????????????????????????????????????", "??????????????????");
                    kongQiJianCeShuoMingDialog.show();
                } else if (strType.equals("pm2dian5")) {
                    KongQiJianCeShuoMingDialog kongQiJianCeShuoMingDialog = new KongQiJianCeShuoMingDialog(mContext, "24??????PM2.5??????????????????\n" +
                            "??? 0~35?????? 35~75??????????????? 75~115???\n" +
                            "???????????? 115~150??????????????? 150~250???\n" +
                            "???????????? ??????250????????????", "pm2.5??????");
                    kongQiJianCeShuoMingDialog.show();
                } else if (strType.equals("jiaQuan")) {
                    KongQiJianCeShuoMingDialog kongQiJianCeShuoMingDialog = new KongQiJianCeShuoMingDialog(mContext, "??????????????????????????????????????????1?????????????????????????????????????????????????????????????????????80??????/?????????????????????100-200??????/????????????50%???????????????????????????;??????200-500??????/?????????????????????????????????????????????????????????????????????????????????????????????1000??????/??????????????????????????????;??????5000??????/?????????????????????\n" +
                            "???????????????????????????????????????????????????", "????????????");
                    kongQiJianCeShuoMingDialog.show();
                } else if (strType.equals("erYangHuaTan")) {
                    KongQiJianCeShuoMingDialog kongQiJianCeShuoMingDialog = new KongQiJianCeShuoMingDialog(mContext, "??150???350??????????????????\n" +
                            "??350???450ppm???????????????????????? ??????\n" +
                            "??350???1200ppm?????????????????????????????? ??????\n" +
                            "??1200???2500ppm?????????????????????,??????????????????????????? ??????\n" +
                            "??2500???5000ppm????????????????????????????????????????????????\n" +
                            "??????????????????????????????????????? ??????\n" +
                            "????????5000ppm???????????????????????????????????????????????????\n" +
                            "???????????????????????????\n", "??????????????????");
                    kongQiJianCeShuoMingDialog.show();
                }


            }
        });
    }

    private void getFouData() {
        //???????????????????????? ?????????????????????
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
        //???????????????????????? ?????????????????????
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
                                .add(R.id.ll_kongqizhiliang_quxian, new KongQiZhiLiangFragment(response.body().data.get(0).getGd_list(), "0"))   // ?????????R.id.fragment_container????????????fragment????????????
                                .commit();

                        getSupportFragmentManager()    //
                                .beginTransaction()
                                .add(R.id.ll_jiaquan_quxian, new JiaQuanFragment(response.body().data.get(0).getGd_list(), "0"))   // ?????????R.id.fragment_container????????????fragment????????????
                                .commit();

                        getSupportFragmentManager()    //
                                .beginTransaction()
                                .add(R.id.ll_co2_quxian, new Co2Fragment(response.body().data.get(0).getGd_list(), "0"))   // ?????????R.id.fragment_container????????????fragment????????????
                                .commit();

                        getSupportFragmentManager()    //
                                .beginTransaction()
                                .add(R.id.ll_pm2dian5_quxian, new Pm2Dian5Fragment(response.body().data.get(0).getGd_list(), "0"))   // ?????????R.id.fragment_container????????????fragment????????????
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
        tv_title.setText("????????????");
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
     * ????????????Activty????????????Activity
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
                //UIHelper.ToastMessage(mContext, "????????????");
                setChuShi4XiangZhi(R.id.ll_kongqizhiliang);
                rlYan.setBackgroundResource(R.mipmap.airmonitor_smoke_green);
                rlYan.setVisibility(View.GONE);
                tvMax.setText("Max 5000");

                if (!StringUtils.isEmpty(tvKongqiZhiliang.getText().toString().trim())) {
                    Integer kongQiZhiLiang = Integer.valueOf(tvKongqiZhiliang.getText().toString().trim());
                    if (kongQiZhiLiang < 350) {
                        tvText.setText("???");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (kongQiZhiLiang > 350 && kongQiZhiLiang < 750) {
                        tvText.setText("???");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (kongQiZhiLiang > 750 && kongQiZhiLiang < 1150) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (kongQiZhiLiang > 1150 && kongQiZhiLiang < 1500) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (kongQiZhiLiang > 1500) {
                        tvText.setText("????????????");
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
                 * 1.??????35 ?????????
                 * 2.??????35?????????75 ???
                 * 3.??????75 ?????????115 ????????????
                 * 4.??????150?????????250 ????????????
                 * 5.??????250?????????    ????????????
                 */
                tvMax.setText("Max 1000");
                if (!StringUtils.isEmpty(tvPmText.getText().toString())) {
                    Integer pm2Dian5 = Integer.valueOf(tvPmText.getText().toString().trim());
                    if (pm2Dian5 < 35) {
                        tvText.setText("???");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (pm2Dian5 > 35 && pm2Dian5 < 75) {
                        tvText.setText("???");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (pm2Dian5 > 75 && pm2Dian5 < 115) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (pm2Dian5 > 115 && pm2Dian5 < 150) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (pm2Dian5 > 150 && pm2Dian5 < 250) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (pm2Dian5 > 250) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    }
                }


                break;
            case R.id.ll_jiaquan:
                tvShowText.setText("CH2O");
                strType = "jiaQuan";
                //UIHelper.ToastMessage(mContext, "??????");
                setChuShi4XiangZhi(R.id.ll_jiaquan);
                rlYan.setBackgroundResource(R.mipmap.airmonitor_smoke_red);
                tvMax.setText("Max 1000");

                if (!StringUtils.isEmpty(tvJiaquan.getText().toString().trim())) {
                    Integer jiaquan = Integer.valueOf(tvJiaquan.getText().toString().trim());
                    /**
                     * ?????? - ??????
                     * 1.??????80 ??????
                     * 2.100-200 ????????????
                     * 3.200-500 ????????????
                     * 4.500????????? ????????????
                     */
                    if (jiaquan < 80) {
                        tvText.setText("??????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (jiaquan > 80 && jiaquan < 100) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (jiaquan > 100 && jiaquan < 200) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (jiaquan > 200 && jiaquan < 500) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (jiaquan > 500) {
                        tvText.setText("????????????");
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
                     * 1.0-450 ???
                     * 2.450-1000 ???
                     * 3.1000-2000 ????????????
                     * 4.2000-5000 ????????????
                     * 5.5000??????  ????????????
                     */
                    if (co2Int < 450) {
                        tvText.setText("???");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (co2Int > 450 && co2Int < 1000) {
                        tvText.setText("???");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (co2Int > 1000 && co2Int < 2000) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (co2Int > 2000 && co2Int < 5000) {
                        tvText.setText("????????????");
                        rlYan.setVisibility(View.VISIBLE);
                    } else if (co2Int > 5000) {
                        tvText.setText("????????????");
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
