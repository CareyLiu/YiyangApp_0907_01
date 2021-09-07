package com.yiyang.cn.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.taokeshagncheng.QueRenDingDanActivity;
import com.yiyang.cn.adapter.AddressListAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppEvent;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.AddressModel;

import com.yiyang.cn.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity implements Observer {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.list)
    LRecyclerView list;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    private List<AddressModel.DataBean> dataBeanList = new ArrayList<>();
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private AddressListAdapter addressListAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_address_list;
    }


    @Override
    public boolean showToolBar() {
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);
        View mEmptyView = findViewById(R.id.empty_view);
        addressListAdapter = new AddressListAdapter(this);
        addressListAdapter.setDataList(dataBeanList);
        list.setEmptyView(mEmptyView);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(addressListAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(lRecyclerViewAdapter);
        list.setPullRefreshEnabled(false);
        list.setLoadMoreEnabled(false);
        AppEvent.getClassEvent().addObserver(this);
        requestData();

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Notice n1 = new Notice();
                n1.type = ConstanceValue.MSG_GETADDRESS;
                n1.content = dataBeanList.get(position);
                RxBus.getDefault().sendRx(n1);
                finish();
            }
        });
    }


    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04129");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<AddressModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<AddressModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<AddressModel.DataBean>> response) {
                        dataBeanList.clear();
                        dataBeanList.addAll(response.body().data);
                        addressListAdapter.setDataList(dataBeanList);
                        list.refreshComplete(12);
                        lRecyclerViewAdapter.notifyDataSetChanged();


                        if (dataBeanList.size() == 0) {
                            //没有地址了 发送通知
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_NONEADDRESS;
                            RxBus.getDefault().sendRx(n);
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<AddressModel.DataBean>> response) {
                        AlertUtil.t(AddressActivity.this, response.getException().getMessage());
                    }
                });
    }


    @OnClick({R.id.rl_back, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_address:
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class)
                        .putExtra("title", "添加收货地址")
                        .putExtra("code", "04128")

                );
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("address_update")) {
            requestData();
        }
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AddressActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
