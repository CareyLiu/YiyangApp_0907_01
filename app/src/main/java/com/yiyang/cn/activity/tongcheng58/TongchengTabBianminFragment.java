package com.yiyang.cn.activity.tongcheng58;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tongcheng58.adapter.TcBianminAdapter;
import com.yiyang.cn.activity.tongcheng58.model.TcBianminModel;
import com.yiyang.cn.adapter.NewsFragmentPagerAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.view.CustomViewPager;
import com.yiyang.cn.view.magicindicator.MagicIndicator;
import com.yiyang.cn.view.magicindicator.ViewPagerHelper;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.yiyang.cn.view.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TongchengTabBianminFragment extends BaseFragment {
    @BindView(R.id.magic_title)
    MagicIndicator magic_title;
    @BindView(R.id.viewpager)
    CustomViewPager viewpager;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private String ir_column_type;
    private String x_weidu;
    private String y_jingdu;
    private int page_number;
    private boolean isFirst;

    private TcBianminModel.DataBean bianminBean;
    private List<TcBianminModel.DataBean.IrNoticeTypeListBean> irNoticeTypeList = new ArrayList<>();
    private ArrayList<Fragment> messageListFragments = new ArrayList<>();

    private List<TcBianminModel.DataBean.IrNoticeListBean> irNoticeList = new ArrayList<>();
    private TcBianminAdapter adapter;

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.tongcheng_frag_main_bianmin;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initStart();
        return rootView;
    }

    private void initStart() {
        x_weidu = PreferenceHelper.getInstance(getActivity()).getString(App.WEIDU, "");
        y_jingdu = PreferenceHelper.getInstance(getActivity()).getString(App.JINGDU, "");
        page_number = 0;
        isFirst = true;
        ir_column_type = "1852";

        initAdapter();
        initSM();
        getData();
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                lordData();
            }
        });
    }


    private void initAdapter() {
        adapter = new TcBianminAdapter(R.layout.tongcheng_item_bianmin, irNoticeList);
        rv_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_list.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_lianxi) {
                    String ir_contact_phone = irNoticeList.get(position).getIr_contact_phone();
                    TishiDialog dialog = new TishiDialog(getContext(), TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {

                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + ir_contact_phone);
                            intent.setData(data);
                            startActivity(intent);
                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });
                    dialog.setTextTitle("拨打电话");
                    dialog.setTextContent(ir_contact_phone);
                    dialog.show();
                }
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TcBianminModel.DataBean.IrNoticeListBean bean = irNoticeList.get(position);
                String ir_id = bean.getIr_id();
                // Y.t("我的ID是" + ir_id);
                BianMinXinXiActivity.actionStart(getActivity(), ir_id);
            }
        });

        View view = View.inflate(getContext(), R.layout.empty_view, null);
        ImageView noneImage = view.findViewById(R.id.iv_image);
        noneImage.setBackgroundResource(R.mipmap.shop_pic_none);
        adapter.setEmptyView(view);
    }

    private void getData() {
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", "17009");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("ir_column_type", ir_column_type);
        map.put("x", x_weidu);
        map.put("y", y_jingdu);
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<TcBianminModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TcBianminModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcBianminModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            bianminBean = response.body().data.get(0);
                            if (isFirst) {
                                initVpg();
                            }

                            irNoticeList = bianminBean.getIrNoticeList();
                            adapter.setNewData(irNoticeList);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                        dismissProgressDialog();
                    }
                });
    }

    private void initVpg() {
        isFirst = false;
        irNoticeTypeList = bianminBean.getIrNoticeTypeList();
        for (int i = 0; i < irNoticeTypeList.size(); i++) {
            TcBianminModel.DataBean.IrNoticeTypeListBean typeListBean = irNoticeTypeList.get(i);
            Bundle data = new Bundle();
            data.putString("title", typeListBean.getIr_column_type_name());
            TongchengHomeItemFragment newfragment = new TongchengHomeItemFragment();
            newfragment.setArguments(data);
            messageListFragments.add(newfragment);
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getChildFragmentManager(), messageListFragments);
        viewpager.setAdapter(mAdapetr);
        initMagicIndicator();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return irNoticeTypeList == null ? 0 : irNoticeTypeList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Y.getColor(R.color.color_3));
                simplePagerTitleView.setSelectedColor(Y.getColor(R.color.text_red));
                simplePagerTitleView.setText(irNoticeTypeList.get(index).getIr_column_type_name());
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setNet(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(Y.getColor(R.color.text_red));
                return linePagerIndicator;
            }
        });
        commonNavigator.setAdjustMode(true);
        magic_title.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magic_title, viewpager);
    }

    private void setNet(int index) {
        viewpager.setCurrentItem(index);
        ir_column_type = irNoticeTypeList.get(index).getIr_column_type();
        showProgressDialog();
        getData();
    }

    private void lordData() {
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", "17009");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("ir_column_type", ir_column_type);
        map.put("x", x_weidu);
        map.put("y", y_jingdu);
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<TcBianminModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TcBianminModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcBianminModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            bianminBean = response.body().data.get(0);
                            List<TcBianminModel.DataBean.IrNoticeListBean> list = bianminBean.getIrNoticeList();
                            irNoticeList.addAll(list);
                            adapter.notifyDataSetChanged();
                        }
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
