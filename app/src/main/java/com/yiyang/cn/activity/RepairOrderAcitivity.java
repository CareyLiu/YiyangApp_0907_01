package com.yiyang.cn.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.yiyang.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepairOrderAcitivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.list)
    LRecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_record);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
}
