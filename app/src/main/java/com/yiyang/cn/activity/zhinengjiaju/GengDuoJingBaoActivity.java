package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.GengDuoJingBaoAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.JingBaoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class GengDuoJingBaoActivity extends BaseActivity {
    public int pageNumber = 0;
    public String device_id = "";
    public String time;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    private List<JingBaoModel.DataBean> mDatas = new ArrayList<>();
    private GengDuoJingBaoAdapter gengDuoJingBaoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        time = getIntent().getStringExtra("time");
        tv_title.setText(time + "警报详情");
        device_id = getIntent().getStringExtra("device_id");
        getNet();
        gengDuoJingBaoAdapter = new GengDuoJingBaoAdapter(R.layout.item_menci_list, mDatas);
        rlvList.setAdapter(gengDuoJingBaoAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rlvList.setLayoutManager(linearLayoutManager);

        srLSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 0;
                mDatas.clear();
                getNet();
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_see_more;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();

        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16075");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);
        map.put("page_num", String.valueOf(pageNumber));
        map.put("time", time);

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<JingBaoModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<JingBaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<JingBaoModel.DataBean>> response) {

                        if (response.body().next.equals("0")) {

                            srLSmart.setEnableLoadMore(false);
                        } else if (response.body().next.equals("1")) {
                            pageNumber = pageNumber + 1;
                            srLSmart.setEnableLoadMore(true);
                        }
                        mDatas.addAll(response.body().data);
                        gengDuoJingBaoAdapter.setNewData(mDatas);
                    }

                    @Override
                    public void onError(Response<AppResponse<JingBaoModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());

                    }

                    @Override
                    public void onStart(Request<AppResponse<JingBaoModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        srLSmart.finishLoadMore();
                        srLSmart.finishRefresh();
                    }
                });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_id, String time) {
        Intent intent = new Intent(context, GengDuoJingBaoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_id", device_id);
        intent.putExtra("time", time);
        context.startActivity(intent);
    }
}
