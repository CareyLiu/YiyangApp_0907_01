package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyco.roundview.RoundRelativeLayout;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.KeShiLieBiaoSearchActivity;
import com.yiyang.cn.activity.a_yiyang.KeShiLieBiaoSouSuoActivity;
import com.yiyang.cn.adapter.KeShiYouCeListAdapter;
import com.yiyang.cn.adapter.KeShiZuoCeListAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.model.yiyang.KeShiMingChengModel;
import com.yiyang.cn.model.yiyang.MenZhenLieBiaoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class KeShiLieBiaoActivity extends BaseActivity {
    @BindView(R.id.iv_icon_sousuo)
    ImageView ivIconSousuo;
    @BindView(R.id.rrl_sousuo)
    RoundRelativeLayout rrlSousuo;
    @BindView(R.id.view_line1)
    View viewLine1;
    @BindView(R.id.rlv_left_list)
    RecyclerView rlvLeftList;

    List<KeShiMingChengModel> leftListStr = new ArrayList<>();
    KeShiZuoCeListAdapter keShiZuoCeListAdapter;
    KeShiMingChengModel keShiMingChengModel;
    MenZhenLieBiaoModel menZhenLieBiaoModel;
    @BindView(R.id.rlv_right_list)
    RecyclerView rlvRightList;
    List<MenZhenLieBiaoModel> menZhenListStr = new ArrayList<>();
    KeShiYouCeListAdapter keShiYouCeListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "1";
        keShiMingChengModel.name = "特色门诊";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "内科";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "外科";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "妇产科";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "儿科";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "神经内科";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "眼科";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "耳鼻咽喉科";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "口腔科";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "感染病科";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "肿瘤科";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "其他";
        leftListStr.add(keShiMingChengModel);

        keShiMingChengModel = new KeShiMingChengModel();
        keShiMingChengModel.shiFouXuanZe = "0";
        keShiMingChengModel.name = "临床试验";
        leftListStr.add(keShiMingChengModel);


        initLeft();
        initRight();

        menZhenLieBiaoModel = new MenZhenLieBiaoModel();
        menZhenLieBiaoModel.name = "疑难病多学科（MDT）门诊";
        menZhenListStr.add(menZhenLieBiaoModel);

        menZhenLieBiaoModel = new MenZhenLieBiaoModel();
        menZhenLieBiaoModel.name = "肥胖和代谢病外科门诊";
        menZhenListStr.add(menZhenLieBiaoModel);

        menZhenLieBiaoModel = new MenZhenLieBiaoModel();
        menZhenLieBiaoModel.name = "便秘与代谢病外科门诊";
        menZhenListStr.add(menZhenLieBiaoModel);

        menZhenLieBiaoModel = new MenZhenLieBiaoModel();
        menZhenLieBiaoModel.name = "胃肠道间质瘤专病门诊";
        menZhenListStr.add(menZhenLieBiaoModel);

        menZhenLieBiaoModel = new MenZhenLieBiaoModel();
        menZhenLieBiaoModel.name = "高压氧治疗与中毒门诊";
        menZhenListStr.add(menZhenLieBiaoModel);

        menZhenLieBiaoModel = new MenZhenLieBiaoModel();
        menZhenLieBiaoModel.name = "美容毛发移植门诊";
        menZhenListStr.add(menZhenLieBiaoModel);

        menZhenLieBiaoModel = new MenZhenLieBiaoModel();
        menZhenLieBiaoModel.name = "小耳畸形耳廓再造专病门诊";
        menZhenListStr.add(menZhenLieBiaoModel);


    }


    int qianYiGePosition = 0;

    private void initLeft() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        keShiZuoCeListAdapter = new KeShiZuoCeListAdapter(R.layout.item_keshi_left, leftListStr);
        rlvLeftList.setLayoutManager(linearLayoutManager);
        rlvLeftList.setAdapter(keShiZuoCeListAdapter);
        keShiZuoCeListAdapter.setNewData(leftListStr);
        keShiZuoCeListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


                keShiMingChengModel = new KeShiMingChengModel();
                keShiMingChengModel.shiFouXuanZe = "0";
                keShiMingChengModel.name = leftListStr.get(qianYiGePosition).name;

                leftListStr.set(qianYiGePosition, keShiMingChengModel);


                qianYiGePosition = position;
                keShiMingChengModel = new KeShiMingChengModel();
                keShiMingChengModel.shiFouXuanZe = "1";
                keShiMingChengModel.name = leftListStr.get(position).name;
                leftListStr.set(position, keShiMingChengModel);

                keShiZuoCeListAdapter.notifyDataSetChanged();

            }
        });


    }

    private void initRight() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        keShiYouCeListAdapter = new KeShiYouCeListAdapter(R.layout.item_keshi_right, menZhenListStr);
        rlvRightList.setLayoutManager(linearLayoutManager);
        rlvRightList.setAdapter(keShiYouCeListAdapter);
        keShiYouCeListAdapter.setNewData(menZhenListStr);
        keShiYouCeListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                UIHelper.ToastMessage(mContext, menZhenListStr.get(position).name);

            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_keshiliebiao;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, KeShiLieBiaoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("科室列表");
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

    @OnClick(R.id.rrl_sousuo)
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.rrl_sousuo:
                KeShiLieBiaoSearchActivity.actionStart(mContext);
                break;
        }

    }
}
