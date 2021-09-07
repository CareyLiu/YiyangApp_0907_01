package com.yiyang.cn.activity.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.DaLiBaoAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.DaLiBaoModel;
import com.yiyang.cn.model.TaoKeDetailList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE;
import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;
import static com.yiyang.cn.get_net.Urls.TAOKELIST;

public class LiBaoPageActivity extends BaseActivity {


    @BindView(R.id.iv_background)
    ImageView ivBackground;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    List<DaLiBaoModel.DataBean.WaresListBean> dataBeans = new ArrayList<>();
    DaLiBaoAdapter daLiBaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_li_bao_page);
        //  ivImage.setBackgroundResource(R.drawable.bigbig_dalibao);
        // Glide.with(MyApplication.mContext).load(R.drawable.bigbig_dalibao).into(ivImage);

        setAdapter();
        getNet();


    }

    private void setAdapter() {
        daLiBaoAdapter = new DaLiBaoAdapter(R.layout.item_dalibao_list, dataBeans);
        rvList.setAdapter(daLiBaoAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(linearLayoutManager);

        daLiBaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DaLiBaoModel.DataBean.WaresListBean waresListBean = (DaLiBaoModel.DataBean.WaresListBean) adapter.getData().get(position);

                ZiJianShopMallDetailsActivity.actionStart(mContext, waresListBean.getShop_product_id(), waresListBean.getWares_id());
            }
        });

    }


    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04244");
        map.put("key", Urls.key);
        map.put("inst_id", "205");
        // map.put("token",UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<DaLiBaoModel.DataBean>>post(HOME_PICTURE)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DaLiBaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DaLiBaoModel.DataBean>> response) {
                        dataBeans.addAll(response.body().data.get(0).getWares_list());

                        daLiBaoAdapter.setNewData(dataBeans);
                        daLiBaoAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_li_bao_page;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LiBaoPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("免费送大礼包");
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
}
