package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.shuinuan.gongxiang.GongxiangModel;
import com.yiyang.cn.adapter.SuiYiTieTianjiaSheBeiAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.SuiYiTieModel;
import com.yiyang.cn.model.SuiYiTieSheBeiLieBiaoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class SuiYiTieTianJiaSheBeiActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    private SuiYiTieTianjiaSheBeiAdapter suiYiTieTianjiaSheBeiAdapter;
    List<SuiYiTieSheBeiLieBiaoModel.DataBean> mDatas = new ArrayList<>();
    private String device_ccid;
    private String device_ccid_up;
    private String building_type;

    private String id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rlvList.setLayoutManager(linearLayoutManager);
        suiYiTieTianjiaSheBeiAdapter = new SuiYiTieTianjiaSheBeiAdapter(R.layout.item_suiyitie_shebei, mDatas);
        rlvList.setAdapter(suiYiTieTianjiaSheBeiAdapter);
        device_ccid = getIntent().getStringExtra("device_ccid");
        device_ccid_up = getIntent().getStringExtra("device_ccid_up");
        building_type = getIntent().getStringExtra("building_type");
        id = getIntent().getStringExtra("id");
        suiYiTieTianjiaSheBeiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_main:
                        for (int i = 0; i < mDatas.size(); i++) {
                            mDatas.get(i).flag = "0";
                        }
                        mDatas.get(position).flag = "1";
                        suiYiTieTianjiaSheBeiAdapter.notifyDataSetChanged();
                        getNetTianJia(mDatas.get(position));


                        break;
                }

            }
        });
        getnet(device_ccid_up, building_type);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_suiyitie_tianjiashebei;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("随意贴添加设备");
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

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String paiwei, String device_ccid, String device_ccid_up, String building_type, String id) {
        Intent intent = new Intent(context, SuiYiTieTianJiaSheBeiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("paiwei", paiwei);
        intent.putExtra("device_ccid", device_ccid);
        intent.putExtra("device_ccid_up", device_ccid_up);
        intent.putExtra("building_type", building_type);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    private void getnet(String device_ccid_up, String building_type) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16049");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid_up", device_ccid_up);
        map.put("device_ccid", device_ccid);
        map.put("binding_type", building_type);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<SuiYiTieSheBeiLieBiaoModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SuiYiTieSheBeiLieBiaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SuiYiTieSheBeiLieBiaoModel.DataBean>> response) {
                        showLoadSuccess();
                        mDatas.clear();
                        mDatas.addAll(response.body().data);
                        suiYiTieTianjiaSheBeiAdapter.setNewData(mDatas);
                    }

                    @Override
                    public void onError(Response<AppResponse<SuiYiTieSheBeiLieBiaoModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<SuiYiTieSheBeiLieBiaoModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private void getNetTianJia(SuiYiTieSheBeiLieBiaoModel.DataBean dataBean) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16050");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", dataBean.getDevice_ccid());
        map.put("binding_type", building_type);
        map.put("device_ccid_st", id);
        map.put("device_ccid_up", device_ccid_up);
        map.put("family_id", dataBean.getFamily_id());

        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<SuiYiTieSheBeiLieBiaoModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SuiYiTieSheBeiLieBiaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SuiYiTieSheBeiLieBiaoModel.DataBean>> response) {
                        showLoadSuccess();
                        mDatas.clear();
                        mDatas.addAll(response.body().data);
                        suiYiTieTianjiaSheBeiAdapter.setNewData(mDatas);

                        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {
                                finish();
                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        dialog.setTextContent("已成功添加设备");
                        dialog.setTextCancel("");
                        dialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<SuiYiTieSheBeiLieBiaoModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<SuiYiTieSheBeiLieBiaoModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }
}
