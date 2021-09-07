package com.yiyang.cn.activity.wode_page.bazinew;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.adapter.YanpanAdapter;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.model.DanganModel;
import com.yiyang.cn.activity.wode_page.bazinew.model.YanpanModel;
import com.yiyang.cn.activity.wode_page.bazinew.model.YunshiModel;
import com.yiyang.cn.activity.wode_page.bazinew.view.MyListView;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YanpanActivity extends BaziBaseActivity {


    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.lv_nian)
    MyListView lv_nian;
    @BindView(R.id.lv_yuew)
    MyListView lv_yuew;
    private DanganModel.DataBean model;


    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_yanpan;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("免费验盘");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        model = (DanganModel.DataBean) getIntent().getSerializableExtra("model");
        tv_name.setText(model.getName());
        tv_sex.setText(model.getSex_text());
        tv_birthday.setText(model.getLunar_birthday());

        getNet();
    }


    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11020");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", model.getMingpan_id());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<YanpanModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<YanpanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<YanpanModel.DataBean>> response) {
                        List<YanpanModel.DataBean> data = response.body().data;
                        if (data != null && data.size() > 0) {
                            YanpanModel.DataBean bean = data.get(0);
                            List<String> month = bean.getMonth();
                            List<String> year = bean.getYear();

                            YanpanAdapter monthAdapter = new YanpanAdapter(month, YanpanActivity.this);
                            lv_yuew.setAdapter(monthAdapter);
                            YanpanAdapter yearAdapter = new YanpanAdapter(year, YanpanActivity.this);
                            lv_nian.setAdapter(yearAdapter);
                        }
                    }
                });
    }
}
