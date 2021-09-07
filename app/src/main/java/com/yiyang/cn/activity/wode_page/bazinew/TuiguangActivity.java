package com.yiyang.cn.activity.wode_page.bazinew;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.adapter.TuiguangAdapter;
import com.yiyang.cn.activity.wode_page.bazinew.adapter.YanpanAdapter;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.model.TuiguangModel;
import com.yiyang.cn.activity.wode_page.bazinew.model.YanpanModel;
import com.yiyang.cn.activity.wode_page.bazinew.utils.TimeUtils;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TuiguangActivity extends BaziBaseActivity {

    @BindView(R.id.tv_num_yue)
    TextView tv_num_yue;
    @BindView(R.id.tv_num_ri)
    TextView tv_num_ri;
    @BindView(R.id.tv_num_all)
    TextView tv_num_all;
    @BindView(R.id.tv_data)
    TextView tv_data;
    @BindView(R.id.rv_tuiguang)
    RecyclerView rv_tuiguang;
    private TuiguangModel.DataBean tuiguangModel;
    private TuiguangAdapter adapter;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_tuiguang;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("推广总人数");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tv_data.setText(TimeUtils.getCurrentTime());

        getNet();
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11054");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<TuiguangModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuiguangModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuiguangModel.DataBean>> response) {
                        tuiguangModel = response.body().data.get(0);
                        initView();
                    }
                });
    }

    private void initView() {
        rv_tuiguang.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TuiguangAdapter(R.layout.bazi_item_tuiguang, tuiguangModel.getPromoters_list());
        adapter.openLoadAnimation();
        rv_tuiguang.setAdapter(adapter);

        tv_num_all.setText(tuiguangModel.getAgent_num_total());
        tv_num_yue.setText(tuiguangModel.getAgent_num_month());
        tv_num_ri.setText(tuiguangModel.getAgent_num_week());
    }
}
