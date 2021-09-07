package com.yiyang.cn.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.adapter.tuangou.YouhuiquanDuihuanAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.YouhuijuanDuihuanModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KaquanDuihuanActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlv_list;
    @BindView(R.id.bt_ok)
    Button bt_ok;

    private List<YouhuijuanDuihuanModel.DataBean> data = new ArrayList<>();
    private YouhuiquanDuihuanAdapter adapter;
    private String user_agio_id;
    private String inst_id;

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("商家兑换");
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
    public static void actionStart(Context context, String user_agio_id) {
        Intent intent = new Intent(context, KaquanDuihuanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user_agio_id", user_agio_id);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_kaquan_duihuan;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        user_agio_id = getIntent().getStringExtra("user_agio_id");
        initAdapter();
        getNet();
    }

    private void initAdapter() {
        adapter = new YouhuiquanDuihuanAdapter(R.layout.item_diyongquan_duihuan, data);
        rlv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rlv_list.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < data.size(); i++) {
                    YouhuijuanDuihuanModel.DataBean dataBean = data.get(i);
                    if (i == position) {
                        dataBean.setSelect(true);
                        inst_id = dataBean.getInst_id();
                    } else {
                        dataBean.setSelect(false);
                    }
                    data.set(i, dataBean);
                }
                adapter.setNewData(data);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("code", "04264");
        map.put("user_agio_id", user_agio_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<YouhuijuanDuihuanModel.DataBean>>post(Urls.HOME_PICTURE_HOME)
                .tag(mContext)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<YouhuijuanDuihuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<YouhuijuanDuihuanModel.DataBean>> response) {
                        data = response.body().data;
                        adapter.setNewData(data);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    @OnClick(R.id.bt_ok)
    public void onViewClicked() {
        if (TextUtils.isEmpty(inst_id)) {
            Y.t("请选择商家");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("code", "04262");
        map.put("user_agio_id", user_agio_id);
        map.put("inst_id", inst_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<YouhuijuanDuihuanModel.DataBean>>post(Urls.HOME_PICTURE_HOME)
                .tag(mContext)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<YouhuijuanDuihuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<YouhuijuanDuihuanModel.DataBean>> response) {
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
                    public void onError(Response<AppResponse<YouhuijuanDuihuanModel.DataBean>> response) {
                        super.onError(response);
                        Y.tError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }
}
