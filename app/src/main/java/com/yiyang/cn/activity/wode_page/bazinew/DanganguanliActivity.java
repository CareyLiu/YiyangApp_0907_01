package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.model.DanganModel;
import com.yiyang.cn.activity.wode_page.bazinew.adapter.DananguanliAdapter;
import com.yiyang.cn.activity.wode_page.bazinew.model.PaipanModel;
import com.yiyang.cn.activity.wode_page.bazinew.utils.BaziCode;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DanganguanliActivity extends BaziBaseActivity {
    @BindView(R.id.lv_dangan)
    ListView lv_dangan;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.bt_add)
    Button bt_add;

    private List<DanganModel.DataBean> list = new ArrayList<>();
    private DananguanliAdapter adapter;
    private int code;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_danganguanli;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("档案管理");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNet();
    }

    private void init() {
        code = getIntent().getIntExtra("code", 0);
        initAdapter();
        initSM();
    }

    private void initAdapter() {
        adapter = new DananguanliAdapter(list, this);
        lv_dangan.setAdapter(adapter);
        lv_dangan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list != null && list.size() > position) {
                    DanganModel.DataBean dataBean = list.get(position);
                    if (code == BaziCode.ST_mingpan) {
                        getMingPan(dataBean.getMingpan_id());
                    } else if (code == BaziCode.ST_nian || code == BaziCode.ST_yue || code == BaziCode.ST_ri) {
                        Intent intent = new Intent(DanganguanliActivity.this, YunshiActivity.class);
                        intent.putExtra("mingpan_id", dataBean.getMingpan_id());
                        intent.putExtra("code", code);
                        startActivity(intent);
                    } else if (code == BaziCode.ST_yanpan) {
                        Intent intent = new Intent(DanganguanliActivity.this, YanpanActivity.class);
                        intent.putExtra("model", dataBean);
                        startActivity(intent);
                    } else if (code == BaziCode.ST_chuanyi) {
                        Intent intent = new Intent(DanganguanliActivity.this, YunshiChuanyiActivity.class);
                        intent.putExtra("mingpan_id", dataBean.getMingpan_id());
                        intent.putExtra("code", code);
                        intent.putExtra("name_text", dataBean.getName() + "   " + dataBean.getSex_text());
                        intent.putExtra("birthday_text", dataBean.getLunar_birthday());
                        startActivity(intent);
                    }
                }
            }
        });

        adapter.setEditClick(new DananguanliAdapter.OnEditClick() {
            @Override
            public void click(int pos) {
                if (list != null && list.size() > pos) {
                    DanganModel.DataBean dataBean = list.get(pos);
                    Intent intent = new Intent(DanganguanliActivity.this, DanganEditActivity.class);
                    intent.putExtra("model", dataBean);
                    startActivity(intent);
                }
            }
        });
    }

    private void getMingPan(String mingpan_id) {
        Intent intent = new Intent(DanganguanliActivity.this, MingpanActivity.class);
        intent.putExtra("mingpan_id", mingpan_id);
        startActivity(intent);
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                page_number = 0;
                getNet();
            }
        });


        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                page_number++;
                getNet();
            }
        });
    }


    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11013");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<DanganModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DanganModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DanganModel.DataBean>> response) {
                        smartRefreshLayout.finishLoadMore();
                        smartRefreshLayout.finishRefresh();

                        list = response.body().data;
                        adapter.setList(list);
                    }

                    @Override
                    public void onError(Response<AppResponse<DanganModel.DataBean>> response) {
                        super.onError(response);
                        Y.tError(response);
                    }
                });
    }

    @OnClick(R.id.bt_add)
    public void onViewClicked() {
        openActivity(DanganNewActivity.class);
    }
}
