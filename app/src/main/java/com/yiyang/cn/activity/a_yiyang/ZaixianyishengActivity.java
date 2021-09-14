package com.yiyang.cn.activity.a_yiyang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yiyang.cn.R;
import com.yiyang.cn.adapter.yiyang.ZaixianyishengAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.model.yiyang.XiaoxiModel;
import com.yiyang.cn.view.ColorFilterImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZaixianyishengActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ColorFilterImageView ivBack;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.banner)
    ImageView banner;
    @BindView(R.id.iv_zixun_mianfei)
    ImageView ivZixunMianfei;
    @BindView(R.id.iv_zixun_zhuanjia)
    ImageView ivZixunZhuanjia;
    @BindView(R.id.iv_zixun_dianhua)
    ImageView ivZixunDianhua;
    @BindView(R.id.iv_zixun_menzhen)
    ImageView ivZixunMenzhen;
    @BindView(R.id.rv_yisheng)
    RecyclerView rvYisheng;
    @BindView(R.id.iv_zixun_wode)
    ImageView ivZixunWode;


    private List<XiaoxiModel> xiaoxiModels=new ArrayList<>();

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_zaixianyisheng;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ZaixianyishengActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        xiaoxiModels.add(new XiaoxiModel("李医生", "昨天19:58", 5));
        xiaoxiModels.add(new XiaoxiModel("王医生", "前天13:22", 0));
        xiaoxiModels.add(new XiaoxiModel("张护士", "2021-09-08", 0));

        ZaixianyishengAdapter adapter=new ZaixianyishengAdapter(R.layout.yiyang_item_zaixianyisheng,xiaoxiModels);
        rvYisheng.setLayoutManager(new LinearLayoutManager(mContext));
        rvYisheng.setAdapter(adapter);
    }

    @OnClick({R.id.iv_back, R.id.ll_search, R.id.iv_zixun_mianfei, R.id.iv_zixun_zhuanjia, R.id.iv_zixun_dianhua, R.id.iv_zixun_menzhen, R.id.iv_zixun_wode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_search:
                break;
            case R.id.iv_zixun_mianfei:
                break;
            case R.id.iv_zixun_zhuanjia:
                break;
            case R.id.iv_zixun_dianhua:
                break;
            case R.id.iv_zixun_menzhen:
                break;
            case R.id.iv_zixun_wode:
                break;
        }
    }
}
