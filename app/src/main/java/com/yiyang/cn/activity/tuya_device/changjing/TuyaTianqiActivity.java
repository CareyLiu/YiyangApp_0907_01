package com.yiyang.cn.activity.tuya_device.changjing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.changjing.adapter.TuyaTianqiAdapter;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.yiyang.cn.app.BaseActivity;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.DashBoardBean;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.bean.WeatherBean;
import com.tuya.smart.home.sdk.callback.IGetHomeWetherCallBack;
import com.tuya.smart.home.sdk.callback.IIGetHomeWetherSketchCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TuyaTianqiActivity extends BaseActivity {


    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    @BindView(R.id.tv_name)
    TextView tv_name;

    private List<DashBoardBean> tianqiBeen = new ArrayList<>();
    private TuyaTianqiAdapter adapter;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TuyaTianqiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_tianqi;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("天气详情");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
        settianqi();
        initAdapter();
    }

    private void settianqi() {
        HomeBean bean = TuyaHomeManager.getHomeManager().getHomeBean();
        TuyaHomeSdk.newHomeInstance(bean.getHomeId()).getHomeWeatherSketch(bean.getLon(), bean.getLat(), new IIGetHomeWetherSketchCallBack() {
            @Override
            public void onSuccess(WeatherBean result) {
                tv_name.setText(result.getCondition());
                Glide.with(mContext).load(result.getInIconUrl()).into(iv_icon);
            }

            @Override
            public void onFailure(String errorCode, String errorMsg) {

            }
        });
    }

    private void initAdapter() {
        adapter = new TuyaTianqiAdapter(R.layout.item_tuya_tianqi, tianqiBeen);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(adapter);
    }

    private void init() {
        Map<String, Object> units = new HashMap<>();
        units.put("tempUnit", 1);
        TuyaHomeSdk.newHomeInstance(TuyaHomeManager.getHomeManager().getHomeId()).getHomeWeatherDetail(10, units, new IGetHomeWetherCallBack() {
            @Override
            public void onSuccess(List<DashBoardBean> result) {
                tianqiBeen = result;
                adapter.setNewData(tianqiBeen);
                adapter.notifyDataSetChanged();

                for (int i = 0; i < result.size(); i++) {
                    DashBoardBean bean1 = result.get(i);
                    Y.e("天气信息  " + bean1.getFieldName() + "   " +
                            bean1.getIcon() + "   " +
                            bean1.getName() + "   " +
                            bean1.getRoomName() + "   " +
                            bean1.getUnit() + "   " +
                            bean1.getValue() + "   " +
                            bean1.getShow() + "   " +
                            bean1.getId());
                }
            }

            @Override
            public void onFailure(String errorCode, String errorMsg) {
                Y.t("获取天气信息错误:" + errorMsg);
            }
        });
    }

}
