package com.yiyang.cn.fragment.yiyang;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.adapter.yiyang.AnfangAdapter;
import com.yiyang.cn.adapter.yiyang.XiaoxiAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.model.yiyang.AnfangModel;
import com.yiyang.cn.model.yiyang.XiaoxiModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabXiaoxiFragment extends BaseFragment {

    @BindView(R.id.tv_title_liaotian)
    TextView tv_title_liaotian;
    @BindView(R.id.line_title_liaotian)
    View line_title_liaotian;
    @BindView(R.id.rl_title_liaotian)
    RelativeLayout rl_title_liaotian;
    @BindView(R.id.tv_title_fuwu)
    TextView tv_title_fuwu;
    @BindView(R.id.line_title_fuwu)
    View line_title_fuwu;
    @BindView(R.id.rl_title_fuwu)
    RelativeLayout rl_title_fuwu;
    @BindView(R.id.tv_title_xitong)
    TextView tv_title_xitong;
    @BindView(R.id.line_title_xitong)
    View line_title_xitong;
    @BindView(R.id.rl_title_xitong)
    RelativeLayout rl_title_xitong;
    @BindView(R.id.rv_liaotian)
    RecyclerView rv_liaotian;
    @BindView(R.id.ll_group_liaotian)
    LinearLayout ll_group_liaotian;
    @BindView(R.id.ll_group_fuwu)
    LinearLayout ll_group_fuwu;
    @BindView(R.id.ll_group_xitong)
    LinearLayout ll_group_xitong;

    private List<XiaoxiModel> xiaoxiModels;
    private XiaoxiAdapter xiaoxiAdapter;

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
        return R.layout.yiyang_frag_tab_xiaoxi;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        select(0);
        initAdapter();
    }


    private void initAdapter() {
        xiaoxiModels = new ArrayList<>();
        xiaoxiAdapter = new XiaoxiAdapter(R.layout.yiyang_item_xiaoxi, xiaoxiModels);
        rv_liaotian.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_liaotian.setAdapter(xiaoxiAdapter);

        xiaoxiModels.add(new XiaoxiModel("李医生", "昨天19:58", 5));
        xiaoxiModels.add(new XiaoxiModel("王医生", "前天13:22", 0));
        xiaoxiModels.add(new XiaoxiModel("张护士", "2021-09-08", 0));

        xiaoxiAdapter.setNewData(xiaoxiModels);
        xiaoxiAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.rl_title_liaotian, R.id.rl_title_fuwu, R.id.rl_title_xitong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_title_liaotian:
                select(0);
                break;
            case R.id.rl_title_fuwu:
                select(1);
                break;
            case R.id.rl_title_xitong:
                select(2);
                break;
        }
    }

    private void select(int count) {
        tv_title_liaotian.setTextColor(Y.getColor(R.color.color_c));
        tv_title_fuwu.setTextColor(Y.getColor(R.color.color_c));
        tv_title_xitong.setTextColor(Y.getColor(R.color.color_c));

        line_title_liaotian.setVisibility(View.INVISIBLE);
        line_title_fuwu.setVisibility(View.INVISIBLE);
        line_title_xitong.setVisibility(View.INVISIBLE);

        ll_group_liaotian.setVisibility(View.GONE);
        ll_group_fuwu.setVisibility(View.GONE);
        ll_group_xitong.setVisibility(View.GONE);

        switch (count) {
            case 0:
                tv_title_liaotian.setTextColor(Y.getColor(R.color.color_1));
                line_title_liaotian.setVisibility(View.VISIBLE);
                ll_group_liaotian.setVisibility(View.VISIBLE);
                break;
            case 1:
                tv_title_fuwu.setTextColor(Y.getColor(R.color.color_1));
                line_title_fuwu.setVisibility(View.VISIBLE);
                ll_group_fuwu.setVisibility(View.VISIBLE);
                break;
            case 2:
                tv_title_xitong.setTextColor(Y.getColor(R.color.color_1));
                line_title_xitong.setVisibility(View.VISIBLE);
                ll_group_xitong.setVisibility(View.VISIBLE);
                break;
        }
    }
}
