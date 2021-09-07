package com.yiyang.cn.activity.zhinengjiaju;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bigkoo.pickerview.view.TimePickerView;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.KongQiJianCeModel;
import com.yiyang.cn.model.WenShiDuChuanGanQiModel;
import com.yiyang.cn.util.icon_util.QuXianLineChart02View;
import com.yiyang.cn.util.icon_util.SplineChart03View;
import com.yiyang.cn.util.icon_util.SplineChart03View_new;
import com.yiyang.cn.util.icon_util.SplineChart03View_xiangxi;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class KongQiJianCeXiangXiActivity extends BaseActivity {
    @BindView(R.id.tv_tian)
    TextView tvTian;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_nian)
    TextView tvNian;
    @BindView(R.id.ll_main)
    LinearLayout llMain;


    @BindView(R.id.rrl_time)
    RoundRelativeLayout rrlTime;
    @BindView(R.id.tv_riqi)
    TextView tvRiqi;

    @BindView(R.id.tv_two)
    TextView tvTwo;

    private String deviceId;
    private String dataType = "1";

    private TimePickerView pvDate, pvTime;
    private String chooseHour = "";//小时
    private String chooseMin = "";//分钟
    private String time = "2021-01-09";
    String hangshu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceId = getIntent().getStringExtra("device_id");
        hangshu = getIntent().getStringExtra("hangshu");
        tvNian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNian.setBackgroundResource(R.drawable.blue_back);
                tvYue.setBackgroundResource(R.color.white);
                tvTian.setBackgroundResource(R.color.white);
                tvNian.setTextColor(mContext.getResources().getColor(R.color.white));
                tvYue.setTextColor(mContext.getResources().getColor(R.color.black));
                tvTian.setTextColor(mContext.getResources().getColor(R.color.black));
                dataType = "3";
                getnet();

            }
        });
        tvYue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvNian.setBackgroundResource(R.color.white);
                tvYue.setBackgroundResource(R.drawable.blue_back);
                tvTian.setBackgroundResource(R.color.white);

                tvNian.setTextColor(mContext.getResources().getColor(R.color.black));
                tvYue.setTextColor(mContext.getResources().getColor(R.color.white));
                tvTian.setTextColor(mContext.getResources().getColor(R.color.black));
                dataType = "2";
                getnet();

            }
        });
        tvTian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNian.setBackgroundResource(R.color.white);
                tvYue.setBackgroundResource(R.color.white);
                tvTian.setBackgroundResource(R.drawable.blue_back);
                tvNian.setTextColor(mContext.getResources().getColor(R.color.black));
                tvYue.setTextColor(mContext.getResources().getColor(R.color.black));
                tvTian.setTextColor(mContext.getResources().getColor(R.color.white));
                dataType = "1";
                getnet();
            }
        });
        getnet();

        rrlTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                if (dataType.equals("1")) {
                    showTime("1");
                } else if (dataType.equals("2")) {
                    showTime("2");
                } else if (dataType.equals("3")) {
                    showTime("3");
                }
            }
        });

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
        int scrHeight = (int) (dm.heightPixels * 0.4);
        layoutParams = new RelativeLayout.LayoutParams(
                scrWidth, scrHeight);

        //居中显示
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showTime(String str) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext);

        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (str.equals("1")) {
                    time = year + "-" + month + 1 + "-" + dayOfMonth;
                } else if (str.equals("2")) {
                    time = year + "-" + month + 1;
                } else if (str.equals("3")) {
                    time = year + "";
                }


                if (str.equals("1")) {
                    time = year + "-" + month + 1 + "-" + dayOfMonth;
                } else if (str.equals("2")) {
                    time = year + "-" + month + 1;
                } else if (str.equals("3")) {
                    time = year + "";
                }
                time = year + "-" + month + "-" + dayOfMonth;
                tvRiqi.setText(time);
                getnet();
            }
        });
        datePickerDialog.show();

    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_kongqijiance_xiangxi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("数据曲线");
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


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String deviceId, String str) {
        Intent intent = new Intent(context, KongQiJianCeXiangXiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_id", deviceId);
        intent.putExtra("hangshu", str);
        context.startActivity(intent);
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
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(scrWidth, scrHeight);
        //居中显示
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        //图表view放入布局中，也可直接将图表view放入Activity对应的xml文件中
        final RelativeLayout chartLayout = new RelativeLayout(this);

        chartLayout.addView(new QuXianLineChart02View(mContext, humListBeans, temListBeans, dataType), layoutParams);
        llMain.addView(chartLayout);
    }

    RelativeLayout.LayoutParams layoutParams;

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16074");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", deviceId);
        map.put("date_type", dataType);
        Calendar now = Calendar.getInstance();

        int month = now.get(Calendar.MONTH) + 1;
        String month_last;
        if (month < 10) {
            month_last = "0" + month;
        } else {
            month_last = String.valueOf(month);
        }
        String ri = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
        if (ri.length() == 1) {
            ri = "0" + ri;
        }
        String nianYueRi = now.get(Calendar.YEAR) + "-" + month_last + "-" + ri;
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
                        llMain.removeAllViews();
                        SplineChart03View_new splineChart03View = new SplineChart03View_new(mContext, response.body().data.get(0).getGd_list(), hangshu, dataType);
                        llMain.addView(splineChart03View, layoutParams);

//                        if (hangshu.equals("1")) {
//                            tvOne.setText("甲醛");
//                            tvTwo.setText("pm2.5");
//                        } else {
//                            tvOne.setText("空气质量");
//                            tvTwo.setText("co2指数");
//                        }
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


}
