package com.yiyang.cn.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.ChooseFuWuAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.FuWuGongZhongBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ChooseFuWuGongZhongActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    ChooseFuWuAdapter chooseFuWuAdapter;

    List<FuWuGongZhongBean.DataBean> mDatas = new ArrayList<>();
    @BindView(R.id.tv_rl_queding)
    RelativeLayout tvRlQueding;

    private int xuanZhongNum = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chooseFuWuAdapter = new ChooseFuWuAdapter(R.layout.item_fuwu_gongzhong, mDatas);

        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        rlvList.setLayoutManager(manager);

        rlvList.setAdapter(chooseFuWuAdapter);

        chooseFuWuAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_main:
                        if (mDatas.get(position).type.equals("0")) {
                            if (xuanZhongNum == 5) {
                                UIHelper.ToastMessage(mContext, "只能选择5个项目哦");
                                return;
                            }
                            mDatas.get(position).type = "1";
                            xuanZhongNum = xuanZhongNum + 1;
                        } else {
                            mDatas.get(position).type = "0";
                            xuanZhongNum = xuanZhongNum - 1;
                        }
                        chooseFuWuAdapter.notifyDataSetChanged();
                        break;

                }
            }
        });
        saveData();
        tvRlQueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < mDatas.size(); i++) {

                    if (mDatas.get(i).type.equals("1")) {
                        fuWuGongZhong = fuWuGongZhong + mDatas.get(i).getName() + "  ";
                        fuWuGongZhongChuanZhi = fuWuGongZhongChuanZhi + mDatas.get(i).getId() + ",";
                    }
                }

                fuWuGongZhongChuanZhi = fuWuGongZhongChuanZhi.substring(0, fuWuGongZhongChuanZhi.length() - 1);

                fuWuGongZhongList.add(fuWuGongZhong);
                fuWuGongZhongList.add(fuWuGongZhongChuanZhi);


                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_FUWUGONGZHONG;
                notice.content = fuWuGongZhongList;
                sendRx(notice);

                finish();
            }
        });
    }

    String fuWuGongZhong = "  ";
    String fuWuGongZhongChuanZhi = "";

    List<String> fuWuGongZhongList = new ArrayList<>();

    @Override
    public int getContentViewResId() {
        return R.layout.layout_choose_fuwu_gongzhong;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("选择服务工种");
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
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChooseFuWuGongZhongActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void saveData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "17013");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("screening_condition", "service_type");
        Gson gson = new Gson();
        OkGo.<AppResponse<FuWuGongZhongBean.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<FuWuGongZhongBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<FuWuGongZhongBean.DataBean>> response) {

                        mDatas.addAll(response.body().data);
                        chooseFuWuAdapter.notifyDataSetChanged();
                    }
                });
    }

}
