package com.yiyang.cn.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.LocusListAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.Locus;
import com.yiyang.cn.model.SmartDevices;
import com.yiyang.cn.util.AlertUtil;
import com.yiyang.cn.util.Util;

import org.jaaksi.pickerview.picker.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sl on 2019/7/5.
 *
 */

public class HistoryLocusActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_map)
    RelativeLayout rlMap;
    @BindView(R.id.tv_begin_date)
    TextView tvBeginDate;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.layout_screen)
    LinearLayout layoutScreen;
    @BindView(R.id.tv_brand_pic)
    ImageView tvBrandPic;
    @BindView(R.id.tv_car_type)
    TextView tvCarType;
    @BindView(R.id.tv_car_number)
    TextView tvCarNumber;
    @BindView(R.id.list)
    LRecyclerView list;

    private TimePicker timePicker;
    private List<Locus.DataBean> data = new ArrayList<>();
    private LocusListAdapter locusListAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private String gps_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_locus);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);
        View mEmptyView = findViewById(R.id.empty_view);
        locusListAdapter = new LocusListAdapter(this);
        locusListAdapter.setDataList(data);
        list.setEmptyView(mEmptyView);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(locusListAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(lRecyclerViewAdapter);

        tvBeginDate.setText(Util.getTime(new Date(),"yyyy-MM-dd hh:mm:ss"));
        tvEndDate.setText(Util.getTime(new Date(),"yyyy-MM-dd hh:mm:ss"));

        //设置头部加载颜色
        list.setHeaderViewColor(R.color.blue_light, R.color.blue_light, android.R.color.white);
        //设置底部加载颜色
        list.setFooterViewColor(R.color.blue_light, R.color.blue_light, android.R.color.white);
        //设置底部加载文字提示
        list.setFooterViewHint("正在加载更多信息", "我是有底线的", "网络不给力啊，点击再试一次吧");

        list.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                requestData(gps_id);
            }
        });
        list.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                requestData("");
            }
        });
        list.refresh();
    }

    @OnClick({R.id.rl_back, R.id.rl_map, R.id.tv_begin_date, R.id.tv_end_date, R.id.layout_screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_map:
                break;
            case R.id.tv_begin_date:
                timePicker = new TimePicker.Builder(this, TimePicker.TYPE_ALL, new TimePicker.OnTimeSelectListener() {
                   @Override
                   public void onTimeSelect(TimePicker picker, Date date) {
                       tvBeginDate.setText(getTime(date));
                   }
               }).setSelectedDate(System.currentTimeMillis()).create();
                timePicker.show();
                break;
            case R.id.tv_end_date:
                timePicker = new TimePicker.Builder(this, TimePicker.TYPE_ALL, new TimePicker.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(TimePicker picker, Date date) {
                        tvEndDate.setText(getTime(date));
                    }
                }).setSelectedDate(System.currentTimeMillis()).create();
                timePicker.show();
                break;
            case R.id.layout_screen:
                requestData(gps_id);
                break;
        }
    }


    public void requestData(final String id){
        Map<String, String> map = new HashMap<>();
        map.put("code", "04148");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(this).getString("car_id",""));
        map.put("begin_time",tvBeginDate.getText().toString());
        map.put("end_time",tvEndDate.getText().toString());
//        map.put("user_car_id", "30");
//        map.put("begin_time","2019-05-22 00:00");
//        map.put("end_time","2019-05-22 23:59");
        map.put("car_gps_id",id);
        Gson gson = new Gson();
        OkGo.<AppResponse<Locus.DataBean>>post(Urls.SERVER_URL + "wit/car/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Locus.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<Locus.DataBean>> response) {
                        data.addAll(response.body().data);
                        locusListAdapter.setDataList(data);
                        list.refreshComplete(12);
                        lRecyclerViewAdapter.notifyDataSetChanged();
                        if (response.body().data.size()>0){
                            gps_id = data.get(data.size()-1).getCar_gps_id();
                        }else {
                            if (!id.equals(""))
                            list.setNoMore(true);
                        }


                    }

                    @Override
                    public void onError(Response<AppResponse<Locus.DataBean>> response) {
                        AlertUtil.t(HistoryLocusActivity.this,response.getException().getMessage());
                    }
                });
    }

    @SuppressLint("SimpleDateFormat")
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format;
        format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = format.format(date);
        return strDate;
    }

}
