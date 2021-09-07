package com.yiyang.cn.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.tuangou.TuanGouYouHuiJuanAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.TuanGouYouHuiJuanModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TuanGouDiYongQuanActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private List<TuanGouYouHuiJuanModel.DataBean> dataBeans = new ArrayList<>();
    private TuanGouYouHuiJuanAdapter tuanGouYouHuiJuanAdapter;

    private String inst_id;
    private String money;
    private String shop_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        money = getIntent().getStringExtra("money");
        inst_id = getIntent().getStringExtra("inst_id");
        shop_type = getIntent().getStringExtra("shop_type");
        initSM();
        initAdapter();
        getNet();
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_kaquan_main;
    }


    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(TuanGouDiYongQuanActivity.this).getAppToken());
        map.put("code", "08024");
        map.put("inst_id", inst_id);
        map.put("money", money);
        map.put("shop_type", shop_type);
        Gson gson = new Gson();
        OkGo.<AppResponse<TuanGouYouHuiJuanModel.DataBean>>post(Urls.HOME_PICTURE_HOME)
                .tag(TuanGouDiYongQuanActivity.this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouYouHuiJuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouYouHuiJuanModel.DataBean>> response) {
                        dataBeans = response.body().data;
                        tuanGouYouHuiJuanAdapter.setNewData(dataBeans);
                        tuanGouYouHuiJuanAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                        smartRefreshLayout.finishRefresh();
                    }

                    @Override
                    public void onStart(Request<AppResponse<TuanGouYouHuiJuanModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }
                });

    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("抵用券");
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

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String inst_id, String money, String shop_type) {
        Intent intent = new Intent(context, TuanGouDiYongQuanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("inst_id", inst_id);
        intent.putExtra("money", money);
        intent.putExtra("shop_type", shop_type);
        context.startActivity(intent);
    }


    public void initAdapter() {
        tuanGouYouHuiJuanAdapter = new TuanGouYouHuiJuanAdapter(R.layout.item_diyongquan_new, dataBeans);
        rlvList.setLayoutManager(new LinearLayoutManager(TuanGouDiYongQuanActivity.this));
        rlvList.setAdapter(tuanGouYouHuiJuanAdapter);
        tuanGouYouHuiJuanAdapter.setXianBtn(true);
        tuanGouYouHuiJuanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        Notice n1 = new Notice();
                        n1.type = ConstanceValue.MSG_DIYONGQUAN;
                        n1.content = tuanGouYouHuiJuanAdapter.getData().get(position).getAgio_money() + "," + tuanGouYouHuiJuanAdapter.getData().get(position).getUser_agio_id();
                        RxBus.getDefault().sendRx(n1);
                        finish();
                        break;
                }
            }
        });
    }
}
