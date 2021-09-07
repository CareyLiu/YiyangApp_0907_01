package com.yiyang.cn.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.aakefudan.adapter.ZixunAdapter;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ConsultModel;
import com.yiyang.cn.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imlib.model.Conversation;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class ServiceConsultFragment extends BaseFragment {
    @BindView(R.id.lv_dangan)
    RecyclerView lv_dangan;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private Unbinder unbinder;

    private List<ConsultModel.DataBean> modelList = new ArrayList<>();
    private ZixunAdapter adapter;
    private View mEmptyView;

    private boolean isOn = false;

    @Override
    public void onResume() {
        super.onResume();
        isOn = true;
        if (isSupportVisible()) {
            getNet();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isOn = false;
    }


    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_service_consult;
    }

    @Override
    protected void initView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        mEmptyView = rootView.findViewById(R.id.empty_view);
        initAdapter();
        initSM();
        getNet();
        initHuidiao();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_RONGYUN_REVICE) {
                    if (isOn){
                        getNet();
                    }
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
        adapter = new ZixunAdapter(R.layout.item_consult, modelList);
        lv_dangan.setLayoutManager(new LinearLayoutManager(getContext()));
        lv_dangan.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ConsultModel.DataBean dataBean = modelList.get(position);
                Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
                String of_user_accid = dataBean.getOf_user_accid();
                String service_form_id = dataBean.getService_form_id();
                String user_name_car = dataBean.getUser_name_car();
                Bundle bundle = new Bundle();
                bundle.putString("user_name_car", user_name_car);
                bundle.putString("of_user_accid", of_user_accid);
                bundle.putString("service_form_id", service_form_id);
                startConversation(getContext(), conversationType, of_user_accid, user_name_car, bundle);
            }
        });
    }


    public void startConversation(Context context, Conversation.ConversationType conversationType, String targetId, String title, Bundle bundle) {
        if (context != null && !TextUtils.isEmpty(targetId) && conversationType != null) {
            Uri uri = Uri.parse("rong://" + context.getApplicationInfo().processName).buildUpon().appendPath("conversationnew").appendPath(conversationType.getName().toLowerCase(Locale.US)).appendQueryParameter("targetId", targetId).appendQueryParameter("title", title).build();
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            context.startActivity(intent);
        }
    }

    public void getNet() {
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", "03317");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("state", "21");
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<ConsultModel.DataBean>>post(Urls.SERVER_URL + "wit/app/car/witAgent")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ConsultModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ConsultModel.DataBean>> response) {
                        modelList = response.body().data;
                        adapter.setNewData(modelList);
                        adapter.notifyDataSetChanged();
                        if (modelList.size() > 0) {
                            mEmptyView.setVisibility(View.GONE);
                        } else {
                            mEmptyView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ConsultModel.DataBean>> response) {
                        AlertUtil.t(getActivity(), response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }


    private int page_number = 0;

    public void getMore() {
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", "03317");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("page_number", page_number + "");
        map.put("state", "2");
        Gson gson = new Gson();
        OkGo.<AppResponse<ConsultModel.DataBean>>post(Urls.SERVER_URL + "wit/app/car/witAgent")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ConsultModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ConsultModel.DataBean>> response) {
                        modelList.addAll(response.body().data);
                        adapter.setNewData(modelList);
                        adapter.notifyDataSetChanged();
                        if (modelList.size() > 0) {
                            mEmptyView.setVisibility(View.GONE);
                        } else {
                            mEmptyView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ConsultModel.DataBean>> response) {
                        AlertUtil.t(getActivity(), response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }
}
