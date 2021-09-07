package com.yiyang.cn.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.ShengXiaoShiJianChongFuAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.tuya.smart.home.sdk.bean.WeatherBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ShengXiaoShiJianChongFuActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    ShengXiaoShiJianChongFuAdapter shengXiaoShiJianChongFuAdapter;
    List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas = (List<String>) getIntent().getSerializableExtra("mDatas");
        initAdapter();


    }

    private void initAdapter() {

        shengXiaoShiJianChongFuAdapter = new ShengXiaoShiJianChongFuAdapter(R.layout.item_shengxiaoshijian_chongfu, mDatas);
        rlvList.setLayoutManager(new LinearLayoutManager(mContext));

        rlvList.setAdapter(shengXiaoShiJianChongFuAdapter);
        shengXiaoShiJianChongFuAdapter.setNewData(mDatas);
        shengXiaoShiJianChongFuAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rl_choose:
                        if (mDatas.get(position).equals("1")) {
                            mDatas.set(position, "0");
                        } else {
                            mDatas.set(position, "1");
                        }

                        shengXiaoShiJianChongFuAdapter.notifyDataSetChanged();


                        break;
                }
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_shengxiaoshijianchongfu;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("重复");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_rightTitle.setText("保存");
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setTextColor(getResources().getColor(R.color.blue_ff3a85f8));
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "保存成功");
                Notice n = new Notice();
                n.type = ConstanceValue.MSG_RIQICHONGFU;
                n.content = mDatas;
                RxBus.getDefault().sendRx(n);
                finish();

            }
        });
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
    public static void actionStart(Context context, List<String> mDatas) {
        Intent intent = new Intent(context, ShengXiaoShiJianChongFuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mDatas", (Serializable) mDatas);
        context.startActivity(intent);
    }
}
