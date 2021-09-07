package com.yiyang.cn.activity.xiupeichang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.WebViewActivity;
import com.yiyang.cn.activity.tuangou.TuanGouShangJiaListActivity;
import com.yiyang.cn.adapter.xiupeichang.XiuPeiChangAdapter;
import com.yiyang.cn.adapter.xiupeichang.XiuPeiChangHomeAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.GlideImageLoader;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.TuanGouShangJiaListBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.app.App.JINGDU;
import static com.yiyang.cn.app.App.WEIDU;
import static com.yiyang.cn.get_net.Urls.LIBAOLIST;

public class XiuPeiChangHomeActivity extends BaseActivity {

    @BindView(R.id.cyb_banner)
    Banner banner;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.ll_quanbu)
    LinearLayout llQuanbu;
    @BindView(R.id.ll_zhinengpaixu)
    LinearLayout llZhinengpaixu;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;
    @BindView(R.id.rl_shaixuan)
    LinearLayout rlShaixuan;
    @BindView(R.id.rlv_xiupeichang)
    RecyclerView rlvXiupeichang;
    private XiuPeiChangHomeAdapter xiuPeiChangHomeAdapter;
    private List<TuanGouShangJiaListBean.DataBean.IconBean> mDatas;
    private List<TuanGouShangJiaListBean.DataBean.StoreListBean> storeListBeans;
    private XiuPeiChangAdapter xiuPeiChangAdapter;
    private int choosePosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getNet();
        mDatas = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 5);
        rlvList.setLayoutManager(gridLayoutManager);

        xiuPeiChangHomeAdapter = new XiuPeiChangHomeAdapter(R.layout.item_xiupeichang, mDatas);
        rlvList.setAdapter(xiuPeiChangHomeAdapter);

        storeListBeans = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rlvXiupeichang.setLayoutManager(linearLayoutManager);
        xiuPeiChangAdapter = new XiuPeiChangAdapter(R.layout.item_xiupeichang_list, storeListBeans);
        rlvXiupeichang.setAdapter(xiuPeiChangAdapter);

        xiuPeiChangHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_xiupeichang_home;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, XiuPeiChangHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "08011");
        map.put("key", Urls.key);
        map.put("y", PreferenceHelper.getInstance(mContext).getString(JINGDU, "0X11"));
        map.put("x", PreferenceHelper.getInstance(mContext).getString(WEIDU, "0X11"));
        map.put("img_type", "7");
        map.put("item_id", "");
        map.put("neibour", "");
        map.put("three_img_id", "");
        map.put("order", "");
        map.put("inst_id", "");
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<TuanGouShangJiaListBean.DataBean>>post(LIBAOLIST)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouShangJiaListBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouShangJiaListBean.DataBean>> response) {

                        List<String> items = new ArrayList<>();
                        if (response.body().data != null) {
                            for (int i = 0; i < response.body().data.get(0).getBanner_list().size(); i++) {
                                items.add(response.body().data.get(0).getBanner_list().get(i).getImg_url());
                            }
                        }

                        //设置图片加载器
                        banner.setImageLoader(new GlideImageLoader());
                        if (banner != null) {
                            //设置图片集合
                            banner.setImages(items);
                            //banner设置方法全部调用完毕时最后调用
                            banner.start();
                            banner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    startActivity(new Intent(mContext, WebViewActivity.class).putExtra("url", response.body().data.get(0).getBanner_list().get(position).getHtml_url()));
                                }
                            });
                        }

                        mDatas.addAll(response.body().data.get(0).getIcon());
                        mDatas.get(0).chooseFlag = true;
                        xiuPeiChangHomeAdapter.setNewData(mDatas);

                        storeListBeans.addAll(response.body().data.get(0).getStore_list());
                        xiuPeiChangAdapter.setNewData(storeListBeans);

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
        tv_title.setText("修配厂");
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
