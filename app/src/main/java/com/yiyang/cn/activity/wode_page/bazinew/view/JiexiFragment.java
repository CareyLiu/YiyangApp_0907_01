package com.yiyang.cn.activity.wode_page.bazinew.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.basicmvp.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class JiexiFragment extends BaseFragment {
    @BindView(R.id.tv_yunshi)
    TextView tvYunshi;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private String text;
    private String title;

    public JiexiFragment(String text, String title) {
        this.text = text;
        this.title = title;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.bazi_fragment_jiexi;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        tvContent.setText(text);
        tvYunshi.setText(title);
        return rootView;
    }
}
