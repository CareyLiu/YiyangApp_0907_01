package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.LoginActivity;
import com.yiyang.cn.activity.wode_page.bazinew.adapter.FengshuiAdapter;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.model.DanganModel;
import com.yiyang.cn.activity.wode_page.bazinew.model.FengshuiModel;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.util.phoneview.sample.ImageShowActivity;

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

public class FengshuiDanganActivity extends BaziBaseActivity {


    @BindView(R.id.lv_dangan)
    RecyclerView lv_dangan;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private List<FengshuiModel.DataBean> data = new ArrayList<>();


    private String mingpan_id;
    private FengshuiAdapter adapter;


    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_fengshuibaijian;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("风水摆件");
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, FengshuiDanganActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mingpan_id = "";
        initAdapter();
        initSM();
        getNet();
        initHuidiao();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_BAZI_FSBJ2) {//刷新列表
                    getNet();
                }
            }
        }));
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });


        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getMore();
            }
        });
    }

    private void initAdapter() {
        adapter = new FengshuiAdapter(R.layout.bazi_item_fengshuibaijian, data);
        lv_dangan.setLayoutManager(new LinearLayoutManager(mContext));
        lv_dangan.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (data != null && data.size() > position) {
                    FengshuiModel.DataBean dataBean = data.get(position);
                    String goods_state = dataBean.getGoods_state();
                    String mingpan_id = dataBean.getMingpan_id();
                    if (view.getId() == R.id.iv_baijian) {
                        if (goods_state.equals("1")) {
                            ArrayList<String> imgs = new ArrayList<>();
                            imgs.add(dataBean.getGoods_img());
                            ImageShowActivity.actionStart(mContext, imgs);
                        } else {
                            FengshuiBindActivity.actionStart(mContext, mingpan_id);
                        }
                    }
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (data != null && data.size() > position) {
                    FengshuiModel.DataBean dataBean = data.get(position);
                    String goods_state = dataBean.getGoods_state();
                    String mingpan_id = dataBean.getMingpan_id();
                    if (goods_state.equals("1")) {
                        FengshuiBangSActivity.actionStart(mContext, mingpan_id);
                    } else {
                        MyCarCaoZuoDialog_CaoZuoTIshi caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(mContext, "提示", "您还未绑定风水摆件", "绑定", "取消", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {
                                FengshuiBindActivity.actionStart(mContext, mingpan_id);
                            }

                            @Override
                            public void clickRight() {

                            }
                        });
                        caoZuoTIshi.show();
                    }
                }
            }
        });
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11021");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<FengshuiModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<FengshuiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<FengshuiModel.DataBean>> response) {
                        data = response.body().data;
                        if (data != null && data.size() > 0) {
                            mingpan_id = FengshuiDanganActivity.this.data.get(data.size() - 1).getMingpan_id();
                        } else {
                            mingpan_id = "";
                        }
                        adapter.setNewData(data);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    private void getMore() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11021");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", mingpan_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<FengshuiModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<FengshuiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<FengshuiModel.DataBean>> response) {
                        List<FengshuiModel.DataBean> dataBeans = response.body().data;
                        data.addAll(dataBeans);
                        if (FengshuiDanganActivity.this.data != null && FengshuiDanganActivity.this.data.size() > 0) {
                            mingpan_id = FengshuiDanganActivity.this.data.get(data.size() - 1).getMingpan_id();
                        } else {
                            mingpan_id = "";
                        }
                        adapter.setNewData(FengshuiDanganActivity.this.data);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }
}
