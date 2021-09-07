package com.yiyang.cn.activity.fenxiang_tuisong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.TuanYouTuiGuangAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.LordingDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.TuiGuangTongJiModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class TuanYouTuiGuangActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    private TuanYouTuiGuangAdapter tuanYouTuiGuangAdapter;

    private List<TuiGuangTongJiModel.DataBean.LevelListBean> mDatas;

    private TextView tvJinYiGeYue;
    private TextView tvJinQiTian;
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    TextView tv_zhijie;
    TextView tv_jianjie;
    private CircleImageView circleImageView;
    private TextView tvName;
    private ImageView ivTypeName;
    private String type = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lordingDialog = new LordingDialog(mContext);
        lordingDialog.setTextMsg("加载中,请稍后");
        rlvList.setLayoutManager(new LinearLayoutManager(mContext));
        mDatas = new ArrayList<>();
        tuanYouTuiGuangAdapter = new TuanYouTuiGuangAdapter(R.layout.item_tuanyou_tuiguang, mDatas);

        View view = View.inflate(mContext, R.layout.tuanyou_tuiguang_header, null);
        rl1 = view.findViewById(R.id.rl_1);
        rl2 = view.findViewById(R.id.rl_2);
        tv_zhijie = view.findViewById(R.id.tv_zhijie);
        tv_jianjie = view.findViewById(R.id.tv_jianjie);

        View viewZhiJie = view.findViewById(R.id.view_zhijie);
        View viewJianJie = view.findViewById(R.id.view_jianjie);

        circleImageView = view.findViewById(R.id.clv_image);
        tvName = view.findViewById(R.id.tv_name);
        ivTypeName = view.findViewById(R.id.iv_type_name);

        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = 0;
                type = "1";
                getNet(type);
                tv_zhijie.setTextColor(mContext.getResources().getColor(R.color.black_333333));
                viewZhiJie.setVisibility(View.VISIBLE);
                tv_jianjie.setTextColor(mContext.getResources().getColor(R.color.color_999999));
                viewJianJie.setVisibility(View.GONE);
                srLSmart.setEnableAutoLoadMore(true);
                mDatas.clear();
                tuanYouTuiGuangAdapter.removeAllFooterView();
            }
        });

        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = 0;
                type = "2";
                getNet(type);
                tuanYouTuiGuangAdapter.removeAllFooterView();

                tv_jianjie.setTextColor(mContext.getResources().getColor(R.color.black_333333));
                viewJianJie.setVisibility(View.VISIBLE);
                tv_zhijie.setTextColor(mContext.getResources().getColor(R.color.color_999999));
                viewZhiJie.setVisibility(View.GONE);
                srLSmart.setEnableAutoLoadMore(true);
                mDatas.clear();
            }
        });

        tuanYouTuiGuangAdapter.setHeaderView(view);
        rlvList.setAdapter(tuanYouTuiGuangAdapter);
        tuanYouTuiGuangAdapter.setNewData(mDatas);
        getNet("1");

        srLSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getNet(type);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });

        srLSmart.setEnableRefresh(false);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tuanyou_tuiguang;
    }

    private LordingDialog lordingDialog;
    private int num = 0;

    public void getNet(String type) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04268");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("type", type);
        map.put("page_num", String.valueOf(num));
        Gson gson = new Gson();
        OkGo.<AppResponse<TuiGuangTongJiModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user ")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuiGuangTongJiModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TuiGuangTongJiModel.DataBean>> response) {


                        if (response.body().next.equals("1")) {
                            num = num + 1;
                        } else {
                            srLSmart.setEnableLoadMore(false);
                        }
                        mDatas.addAll(response.body().data.get(0).getLevel_list());
                        tuanYouTuiGuangAdapter.notifyDataSetChanged();
                        tv_zhijie.setText("直接邀请(" + response.body().data.get(0).getAgent_num_one() + ")");
                        tv_jianjie.setText("间接邀请(" + response.body().data.get(0).getAgent_num_two() + ")");


                        Glide.with(mContext).load(response.body().data.get(0).getUser_img_url()).into(circleImageView);

                        tvName.setText(response.body().data.get(0).getUser_name());


                        ivTypeName.setBackgroundResource(R.mipmap.jyj_wodetuiguang_icon_daren);
                        lordingDialog.dismiss();

                        if (mDatas.size() == 0) {
                            View view = View.inflate(mContext, R.layout.empty_view, null);
                            tuanYouTuiGuangAdapter.setFooterView(view);

                        }

                        srLSmart.finishLoadMore();
                    }

                    @Override
                    public void onError(Response<AppResponse<TuiGuangTongJiModel.DataBean>> response) {
                        lordingDialog.dismiss();
                    }

                    @Override
                    public void onStart(Request<AppResponse<TuiGuangTongJiModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        lordingDialog.show();
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
        tv_title.setText("我的推广");
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
        Intent intent = new Intent(context, TuanYouTuiGuangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
