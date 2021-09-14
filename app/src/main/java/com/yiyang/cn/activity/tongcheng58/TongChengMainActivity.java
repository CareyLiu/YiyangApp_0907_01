package com.yiyang.cn.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.adapter.NewsFragmentPagerAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.dialog.newdia.FaBuDialog;
import com.yiyang.cn.view.NoSlidingViewPager;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;

public class TongChengMainActivity extends BaseActivity {

    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.line_title1)
    View lineTitle1;
    @BindView(R.id.ll_title1)
    RelativeLayout llTitle1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.line_title2)
    View lineTitle2;
    @BindView(R.id.ll_title2)
    RelativeLayout llTitle2;
    @BindView(R.id.tv_title3)
    TextView tvTitle3;
    @BindView(R.id.line_title3)
    View lineTitle3;
    @BindView(R.id.ll_title3)
    RelativeLayout llTitle3;
    @BindView(R.id.viewPager)
    NoSlidingViewPager viewPager;
    @BindView(R.id.iv_add)
    ImageView ivAdd;

    @Override
    public int getContentViewResId() {
        return R.layout.tongcheng_act_main;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("生活");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TongChengMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStart();
    }

    private void initStart() {
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        viewPager.setScroll(false);
        fragmentList.add(new TongchengTabHomeFragment());
        fragmentList.add(new TongchengTabBianminFragment());
        fragmentList.add(new TongchengTabWodeFragment());
        NewsFragmentPagerAdapter viewPagerAdapter = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
    }

    @OnClick({R.id.iv_add, R.id.ll_title1, R.id.ll_title2, R.id.ll_title3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title1:
                select(0);
                break;
            case R.id.ll_title2:
                select(1);
                break;
            case R.id.ll_title3:
                select(2);
                break;
            case R.id.iv_add:
                clickAdd();
                break;
        }
    }
    FaBuDialog faBuDialog;
    private void clickAdd() {
         faBuDialog = new FaBuDialog(mContext, new FaBuDialog.FaBuDialogListener() {
            @Override
            public void shangJiaFaBu() {
                ShangjiaruzhuActivity.actionStart(mContext);
                faBuDialog.dismiss();
            }

            @Override
            public void gongJiangFaBu() {
                GongJiangRuZhuActivity.actionStart(mContext);
                faBuDialog.dismiss();
            }

            @Override
            public void bianMinFabu() {
                BianMinFaBuActivity.actionStart(mContext);
                faBuDialog.dismiss();
            }
        });
        faBuDialog.show();

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = faBuDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        faBuDialog.getWindow().setAttributes(lp);
    }

    private void select(int pos) {
        tvTitle1.setTextColor(Y.getColor(R.color.color_3));
        tvTitle2.setTextColor(Y.getColor(R.color.color_3));
        tvTitle3.setTextColor(Y.getColor(R.color.color_3));
        lineTitle1.setVisibility(View.GONE);
        lineTitle2.setVisibility(View.GONE);
        lineTitle3.setVisibility(View.GONE);

        if (pos == 0) {
            tvTitle1.setTextColor(Y.getColor(R.color.text_red));
            lineTitle1.setVisibility(View.VISIBLE);

        } else if (pos == 1) {
            tvTitle2.setTextColor(Y.getColor(R.color.text_red));
            lineTitle2.setVisibility(View.VISIBLE);
        } else {
            tvTitle3.setTextColor(Y.getColor(R.color.text_red));
            lineTitle3.setVisibility(View.VISIBLE);
        }

        viewPager.setCurrentItem(pos);
    }
}
