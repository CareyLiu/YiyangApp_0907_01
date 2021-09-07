package com.yiyang.cn.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.adapter.tuangou.TuanGouYouHuiJuanAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.KaquanZengDialog;
import com.yiyang.cn.dialog.newdia.TishiDialog;
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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class KaQuanActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private List<TuanGouYouHuiJuanModel.DataBean> dataBeans = new ArrayList<>();
    private TuanGouYouHuiJuanAdapter tuanGouYouHuiJuanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initAdapter();
        initSM();
        getNet();
        initHuidiao();
    }

    /**
     * 初始化回调数据
     */
    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_KAQUAN_DUIHUAN) {
                    getNet();
                }
            }
        }));
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
        map.put("token", UserManager.getManager(KaQuanActivity.this).getAppToken());
        map.put("code", "08024");
        Gson gson = new Gson();
        OkGo.<AppResponse<TuanGouYouHuiJuanModel.DataBean>>post(Urls.HOME_PICTURE_HOME)
                .tag(KaQuanActivity.this)//
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
                        smartRefreshLayout.finishRefresh();
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
        tv_title.setText("卡券");
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
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, KaQuanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void initAdapter() {
        tuanGouYouHuiJuanAdapter = new TuanGouYouHuiJuanAdapter(R.layout.item_diyongquan_new, dataBeans);
        rlvList.setLayoutManager(new LinearLayoutManager(KaQuanActivity.this));
        rlvList.setAdapter(tuanGouYouHuiJuanAdapter);
        tuanGouYouHuiJuanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:

                        break;
                    case R.id.iv_duihuan:
                        KaquanDuihuanActivity.actionStart(mContext, dataBeans.get(position).getUser_agio_id());
                        break;
                    case R.id.iv_zengsong:
                        clickZengsong(dataBeans.get(position));
                        break;
                }
            }
        });
    }

    private void clickZengsong(TuanGouYouHuiJuanModel.DataBean item) {
        KaquanZengDialog dialog = new KaquanZengDialog(mContext, new KaquanZengDialog.OnDialogItemClickListener() {
            @Override
            public void qudingclick(String phone, TuanGouYouHuiJuanModel.DataBean item) {
                Map<String, String> map = new HashMap<>();
                map.put("key", Urls.key);
                map.put("token", UserManager.getManager(KaQuanActivity.this).getAppToken());
                map.put("code", "04265");
                map.put("user_agio_id", item.getUser_agio_id());
                map.put("user_phone", phone);
                Gson gson = new Gson();
                OkGo.<AppResponse<TuanGouYouHuiJuanModel.DataBean>>post(Urls.HOME_PICTURE_HOME)
                        .tag(KaQuanActivity.this)//
                        .upJson(gson.toJson(map))
                        .execute(new JsonCallback<AppResponse<TuanGouYouHuiJuanModel.DataBean>>() {
                            @Override
                            public void onSuccess(Response<AppResponse<TuanGouYouHuiJuanModel.DataBean>> response) {
                                TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
                                    @Override
                                    public void onClickCancel(View v, TishiDialog dialog) {

                                    }

                                    @Override
                                    public void onClickConfirm(View v, TishiDialog dialog) {

                                    }

                                    @Override
                                    public void onDismiss(TishiDialog dialog) {
                                        Notice n = new Notice();
                                        n.type = ConstanceValue.MSG_KAQUAN_DUIHUAN;
                                        RxBus.getDefault().sendRx(n);
                                        finish();
                                    }
                                });
                                dialog.show();
                            }

                            @Override
                            public void onError(Response<AppResponse<TuanGouYouHuiJuanModel.DataBean>> response) {
                                super.onError(response);
                                Y.tError(response);
                            }
                        });


            }

            @Override
            public void quxiaoclick() {

            }
        });
        dialog.setModel(item);
        dialog.show();
    }
}
