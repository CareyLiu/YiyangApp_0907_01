package com.yiyang.cn.activity.wode_page.bazinew;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.wode_page.bazinew.adapter.CiVpAdapter;
import com.yiyang.cn.activity.wode_page.bazinew.adapter.JiexiAdapter;
import com.yiyang.cn.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.yiyang.cn.activity.wode_page.bazinew.model.JiexiModel;
import com.yiyang.cn.activity.wode_page.bazinew.utils.BaziCode;
import com.yiyang.cn.activity.wode_page.bazinew.view.JiexiFragment;
import com.yiyang.cn.adapter.NewsFragmentPagerAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import androidx.fragment.app.Fragment;

public class JiexiDetailsActivity extends BaziBaseActivity {

    @BindView(R.id.tv_name_sex)
    TextView tv_name_sex;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.tv_yunshi)
    TextView tv_yunshi;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.ll_jiesuo)
    LinearLayout ll_jiesuo;
    @BindView(R.id.rv_title)
    RecyclerView rv_title;
    @BindView(R.id.vpg_content)
    CustomViewPager vpg_content;

    private int jiexi;
    private String mingpan_id;
    private JiexiModel.DataBean dataBean;
    private String[] titles = {"自身状况", "婚姻感情", "父母关系", "兄弟关系", "财运状况", "健康注意", "精神德行", "子女状况", "田宅家境", "事业发展", "人际关系", "迁移发展"};
    private JiexiAdapter adapter;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_jiexi_text;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("解析");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void initVpg() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            String data = getData(i);
            JiexiFragment fragment = new JiexiFragment(data, titles[i]);
//            JiexiFragment fragment = new JiexiFragment();
            fragments.add(fragment);
        }
        vpg_content.setAdapter(new CiVpAdapter(getSupportFragmentManager(), fragments, mContext));
        vpg_content.setOffscreenPageLimit(titles.length);

        initMagicIndicator1(titles);
    }

    private void init() {
        jiexi = getIntent().getIntExtra("jiexi", 0);
        mingpan_id = getIntent().getStringExtra("mingpan_id");

        adapter = new JiexiAdapter(titles, this);
        rv_title.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rv_title.setAdapter(adapter);
        adapter.setClick(new JiexiAdapter.TitlesClick() {
            @Override
            public void click(int pos) {
                jiexi = pos;
                setView(jiexi);
            }
        });

//        setView(jiexi);
        getNet();
    }

    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11017");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", mingpan_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<JiexiModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<JiexiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<JiexiModel.DataBean>> response) {

                        List<JiexiModel.DataBean> data = response.body().data;
                        if (data != null && data.size() > 0) {
                            dataBean = data.get(0);

                            tv_name_sex.setText(dataBean.getName() + "  " + dataBean.getSex());
                            tv_birthday.setText(dataBean.getBirthday_type() + "：" + dataBean.getBirthday());

                            tv_content.setVisibility(View.VISIBLE);
                            ll_jiesuo.setVisibility(View.GONE);

                            initVpg();
                            setView(jiexi);
                        }
                    }

                    @Override
                    public void onStart(Request<AppResponse<JiexiModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        showLoadSuccess();
                    }
                });
    }

    private void setData(int jiexi) {
        if (dataBean != null) {
            switch (jiexi) {
                case BaziCode.ll_tab_minggong:
                    tv_content.setText(dataBean.getMinggong());
                    break;
                case BaziCode.ll_tab_fuqi:
                    tv_content.setText(dataBean.getFuqi());
                    break;
                case BaziCode.ll_tab_fumu:
                    tv_content.setText(dataBean.getFumu());
                    break;
                case BaziCode.ll_tab_xiongdi:
                    tv_content.setText(dataBean.getXiongdi());
                    break;
                case BaziCode.ll_tab_caibo:
                    tv_content.setText(dataBean.getCaibo());
                    break;
                case BaziCode.ll_tab_jie:
                    tv_content.setText(dataBean.getJie());
                    break;
                case BaziCode.ll_tab_fude:
                    tv_content.setText(dataBean.getFude());
                    break;
                case BaziCode.ll_tab_zinv:
                    tv_content.setText(dataBean.getZinv());
                    break;
                case BaziCode.ll_tab_tianzhai:
                    tv_content.setText(dataBean.getTianzhai());
                    break;
                case BaziCode.ll_tab_guanlu:
                    tv_content.setText(dataBean.getGuanlu());
                    break;
                case BaziCode.ll_tab_puyi:
                    tv_content.setText(dataBean.getPuyi());
                    break;
                case BaziCode.ll_tab_qianyi:
                    tv_content.setText(dataBean.getQianyi());
                    break;
            }
        }
    }


    private String getData(int jiexi) {
        String text = "";
        if (dataBean != null) {
            switch (jiexi) {
                case BaziCode.ll_tab_minggong:
                    text = dataBean.getMinggong();
                    break;
                case BaziCode.ll_tab_fuqi:
                    text = dataBean.getFuqi();
                    break;
                case BaziCode.ll_tab_fumu:
                    text = dataBean.getFumu();
                    break;
                case BaziCode.ll_tab_xiongdi:
                    text = dataBean.getXiongdi();
                    break;
                case BaziCode.ll_tab_caibo:
                    text = dataBean.getCaibo();
                    break;
                case BaziCode.ll_tab_jie:
                    text = dataBean.getJie();
                    break;
                case BaziCode.ll_tab_fude:
                    text = dataBean.getFude();
                    break;
                case BaziCode.ll_tab_zinv:
                    text = dataBean.getZinv();
                    break;
                case BaziCode.ll_tab_tianzhai:
                    text = dataBean.getTianzhai();
                    break;
                case BaziCode.ll_tab_guanlu:
                    text = dataBean.getGuanlu();
                    break;
                case BaziCode.ll_tab_puyi:
                    text = dataBean.getPuyi();
                    break;
                case BaziCode.ll_tab_qianyi:
                    text = dataBean.getQianyi();
                    break;
            }
        }

        return text;
    }

    private void setView(int jiexi) {
        tv_yunshi.setText(titles[jiexi]);
        adapter.setCount(jiexi);
        setData(jiexi);

        vpg_content.setCurrentItem(jiexi);
    }


    private void initMagicIndicator1(final String[] titles) {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator4);
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles == null ? 0 : titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setTextSize(17);
                simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.black_111111));
                simplePagerTitleView.setSelectedColor(magicIndicator.getResources().getColor(R.color.black_111111));
                simplePagerTitleView.setText(titles[index]);
                //   App.scaleScreenHelper.loadViewSize(simplePagerTitleView, 35);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setView(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(Color.parseColor("#6666D3"));
                return linePagerIndicator;
            }
        });
//        commonNavigator.setAdjustMode(true);
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vpg_content);
    }
}
