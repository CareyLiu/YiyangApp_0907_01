package com.yiyang.cn.fragment.yiyang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarttop.library.bean.City;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.YiyangTuTActivity;
import com.yiyang.cn.activity.a_yiyang.ZaixianyishengActivity;
import com.yiyang.cn.activity.tongcheng58.model.TcHomeModel;
import com.yiyang.cn.adapter.yiyang.HomeZhylAdapter;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.selectcity.CityActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

public class TabHomeFragment extends BaseFragment {
    @BindView(R.id.iv_weizhi)
    ImageView iv_weizhi;
    @BindView(R.id.tv_weizhi)
    TextView tv_weizhi;
    @BindView(R.id.iv_saoma)
    ImageView iv_saoma;
    @BindView(R.id.banner_main)
    Banner banner_main;
    @BindView(R.id.rv_zhihuiyanglao)
    RecyclerView rv_zhihuiyanglao;
    @BindView(R.id.banner_two)
    ImageView banner_two;
    @BindView(R.id.iv_tab_jiatingyisheng)
    ImageView iv_tab_jiatingyisheng;
    @BindView(R.id.iv_tab_yanglaopinggu)
    ImageView iv_tab_yanglaopinggu;
    @BindView(R.id.iv_tab_jitingdangan)
    ImageView iv_tab_jitingdangan;
    @BindView(R.id.rv_jujiashenghuo)
    RecyclerView rv_jujiashenghuo;
    @BindView(R.id.iv_tab_bianminshenghuo)
    ImageView iv_tab_bianminshenghuo;
    @BindView(R.id.iv_tab_zhihuishangcheng)
    ImageView iv_tab_zhihuishangcheng;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;



    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .statusBarColor(R.color.white)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.yiyang_frag_tab_home;
    }

    @Override
    protected void initView(View rootView) {
        initAdapter();

    }

    private void initAdapter() {
        initZhylAdapter();
        initJjshAdapter();
    }

    private List<TcHomeModel.DataBean.IconListBean> zhylList = new ArrayList<>();
    private List<TcHomeModel.DataBean.IconListBean> jjshList = new ArrayList<>();

    private void initZhylList() {
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhihuiyanglao, "????????????"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_dianhuojizhen, "????????????"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zaixianyisheng, "????????????"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_yibingchaxun, "????????????"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_yuyueguanhao, "????????????"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jijiufuwu, "????????????"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_hujaiozhongxin, "????????????"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_huodongzhongxin, "????????????"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_yanglaoshitang, "????????????"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jiankangshujun, "????????????"));
    }

    private void initJjshList() {
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.jujiaanfang, "????????????"));
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.shengmingtizhengdian, "???????????????"));
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.zhinengshouhuan, "????????????"));
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.meiqibaojingqi, "???????????????"));
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yanwubaojingqi, "???????????????"));
    }

    private void initZhylAdapter() {
        initZhylList();
        HomeZhylAdapter zhylAdapter = new HomeZhylAdapter(R.layout.yiyang_item_home_zhyl, zhylList);
        rv_zhihuiyanglao.setLayoutManager(new GridLayoutManager(getContext(), 5));
        rv_zhihuiyanglao.setAdapter(zhylAdapter);
        zhylAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TcHomeModel.DataBean.IconListBean listBean = zhylList.get(position);
                switch (position) {
                    case 0:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_zhihuiyanglao);
                        break;
                    case 1:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_dianhuajizhen);
                        break;
                    case 2:
                        ZaixianyishengActivity.actionStart(getContext());
                        break;
                    case 3:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_jibingchaxun);
                        break;
                    case 4:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_yuyueguahao);
                        break;
                    case 5:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_jijiufuwu);
                        break;
                    case 6:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_hujiaozhongxin);
                        break;
                    case 7:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_huodongzhongxin);
                        break;
                    case 8:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_yanglaoshitanbg);
                        break;
                    case 9:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_jiankangshuju);
                        break;
                }
            }
        });
    }

    private void initJjshAdapter() {
        initJjshList();
        HomeZhylAdapter jjshAdapter = new HomeZhylAdapter(R.layout.yiyang_item_home_jjsh, jjshList);
        rv_jujiashenghuo.setLayoutManager(new GridLayoutManager(getContext(), 5));
        rv_jujiashenghuo.setAdapter(jjshAdapter);
        jjshAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TcHomeModel.DataBean.IconListBean listBean = zhylList.get(position);
                switch (position) {
                    case 0:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_zhihuiyanglao);
                        break;
                    case 1:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_dianhuajizhen);
                        break;
                    case 2:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_zaixianyisheng);
                        break;
                    case 3:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_jibingchaxun);
                        break;
                    case 4:
                        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_yuyueguahao);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.iv_weizhi, R.id.tv_weizhi, R.id.iv_saoma, R.id.banner_two, R.id.iv_tab_jiatingyisheng, R.id.iv_tab_yanglaopinggu, R.id.iv_tab_jitingdangan, R.id.iv_tab_bianminshenghuo, R.id.iv_tab_zhihuishangcheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_weizhi:
            case R.id.tv_weizhi:
              //  UIHelper.ToastMessage(getActivity(),"lkdjflajfkljdsklfjs");
                //CityActivity.actionStart(getActivity());
                break;
            case R.id.iv_saoma:

                break;
            case R.id.banner_two:
                break;
            case R.id.iv_tab_jiatingyisheng:
                break;
            case R.id.iv_tab_yanglaopinggu:
                break;
            case R.id.iv_tab_jitingdangan:
                break;
            case R.id.iv_tab_bianminshenghuo:
                break;
            case R.id.iv_tab_zhihuishangcheng:
                break;
        }
    }
}
