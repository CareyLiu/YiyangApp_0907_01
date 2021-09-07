package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.SheBeiBianHua_SheBeiLieBiaoAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ChangJingXiangQingModel;
import com.yiyang.cn.model.ChangJingZhiXingModel;
import com.yiyang.cn.model.ZhiNengSheBeiLieBiaoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

//设备变化时候点击进入的页面
public class SheBeiBianHuaActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;

    SheBeiBianHua_SheBeiLieBiaoAdapter sheBeiBianHua_sheBeiLieBiaoAdapter;
    List<ZhiNengSheBeiLieBiaoModel.DataBean> mDatas;
    private String familyid;
    private String leiXingSanBiaoShi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        familyid = getIntent().getStringExtra("familyid");
        leiXingSanBiaoShi = getIntent().getStringExtra("leiXingSanBiaoShi");
        initAdapter();
        getZhiXingSheBeiLieBiao();
    }

    private void initAdapter() {
        mDatas = new ArrayList<>();


        sheBeiBianHua_sheBeiLieBiaoAdapter = new SheBeiBianHua_SheBeiLieBiaoAdapter(R.layout.item_shebeilieb_shebebianhua, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(sheBeiBianHua_sheBeiLieBiaoAdapter);
        View view = View.inflate(mContext, R.layout.layout_dp15, null);
        sheBeiBianHua_sheBeiLieBiaoAdapter.setFooterView(view);
        sheBeiBianHua_sheBeiLieBiaoAdapter.setNewData(mDatas);
        sheBeiBianHua_sheBeiLieBiaoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                /**
                 *     public String device_id_one;
                 *     public String device_name_one;
                 *     //二级设备类型id
                 *     public String device_id_two;
                 *     //设备类型名称
                 *     public String device_name_two;
                 *     //我的智能设备id
                 *     public String user_device_id;
                 *     //执行方式：
                 *     //1.开 2.关
                 *     public String pro_go_one;
                 *     //设备名称
                 *     public String device_name;
                 *     //设备图标url
                 *     public String img_url;
                 *     //二级设备类型id
                 */
                ChangJingZhiXingModel changJingZhiXingModel = new ChangJingZhiXingModel();
                changJingZhiXingModel.device_id_one = mDatas.get(position).getDevice_id_one();
                changJingZhiXingModel.device_id_two = mDatas.get(position).getDevice_id_two();
                changJingZhiXingModel.device_name_two = mDatas.get(position).getDevice_name_two();
                changJingZhiXingModel.user_device_id = mDatas.get(position).getUser_device_id();
                changJingZhiXingModel.device_name = mDatas.get(position).getDevice_name();
                changJingZhiXingModel.img_url = mDatas.get(position).getImg_url();
                changJingZhiXingModel.device_name_one = mDatas.get(position).getDevice_name_one();
                if (!StringUtils.isEmpty(leiXingSanBiaoShi)) {
                    ChangJingBianHuaXuanZeGongNengActivity.actionStart(mContext, familyid, mDatas.get(position).getUser_device_id(), changJingZhiXingModel, leiXingSanBiaoShi);
                } else {
                    XuanZeGongNengActivity.actionStart(mContext, familyid, mDatas.get(position).getUser_device_id(), changJingZhiXingModel, leiXingSanBiaoShi);
                }
                finish();
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_shebeiliebiao_shebeibianhua;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设备列表");
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
    public static void actionStart(Context context, String familyId, String leiXingSanBiaoShi) {
        Intent intent = new Intent(context, SheBeiBianHuaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("familyid", familyId);
        intent.putExtra("leiXingSanBiaoShi", leiXingSanBiaoShi);
        context.startActivity(intent);
    }

    private void getZhiXingSheBeiLieBiao() {
        Map<String, String> map = new HashMap<>();
        if (!StringUtils.isEmpty(leiXingSanBiaoShi)) {
            map.put("code", "16058");
        } else {
            map.put("code", "16059");
        }
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("family_id", familyid);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengSheBeiLieBiaoModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengSheBeiLieBiaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengSheBeiLieBiaoModel.DataBean>> response) {
                        mDatas.clear();
                        mDatas.addAll(response.body().data);
                        if (mDatas.size() == 0) {
                            sheBeiBianHua_sheBeiLieBiaoAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.activity_zhineng_room_setting_none, null));
                        }
                        sheBeiBianHua_sheBeiLieBiaoAdapter.notifyDataSetChanged();

                        dismissProgressDialog();
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengSheBeiLieBiaoModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);
                    }

                    @Override
                    public void onStart(Request<AppResponse<ZhiNengSheBeiLieBiaoModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog("加载中,请稍后");

                    }
                });
    }

}
