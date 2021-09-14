package com.yiyang.cn.fragment.yiyang;

import android.view.View;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.YySetAactivity;
import com.yiyang.cn.basicmvp.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class TabWodeFragment extends BaseFragment {

    @BindView(R.id.iv_dian)
    ImageView ivDian;

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
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
        return R.layout.yiyang_frag_tab_wode;
    }

    @Override
    protected void initView(View rootView) {

    }

    @OnClick(R.id.iv_dian)
    public void onViewClicked() {
        YySetAactivity.actionStart(getContext());
    }
}
