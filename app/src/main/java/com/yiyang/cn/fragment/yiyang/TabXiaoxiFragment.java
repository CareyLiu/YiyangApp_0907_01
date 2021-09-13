package com.yiyang.cn.fragment.yiyang;

import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.basicmvp.BaseFragment;

public class TabXiaoxiFragment extends BaseFragment {

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

    }
}
