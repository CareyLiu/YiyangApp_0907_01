package com.yiyang.cn.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.readystatesoftware.chuck.internal.ui.MainActivity;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.WelcomeAdapter;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.util.AppToast;


import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 实现首次启动的引导页面
 */
public class WelcomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private int []imageIdArray;//图片资源的数组

    //参考自http://stackoverflow.com/questions/32061934/permission-from-manifest-doesnt-work-in-android-6
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    Context context;
    //最后一页的按钮
    private ImageButton ib_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        StatusBarUtil.setTransparent(this);
        context = this;
        ib_start = (ImageButton) findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferenceHelper.getInstance(WelcomeActivity.this).getBoolean("ISLOGIN",false)){
                    startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });

        //加载ViewPager
        initViewPager();

    }






    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        ViewPager vp = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        imageIdArray = new int[]{R.drawable.splash_one,R.drawable.splash_two,R.drawable.splash_three};
        List<View> viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        for (int anImageIdArray : imageIdArray) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(params);
            //使用Glide加载高清图片以减少Viewpage的卡顿现象
            Glide.with(MyApplication.context).load(anImageIdArray).into(imageView);
            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        vp.setAdapter(new WelcomeAdapter(viewList));
        //设置滑动监听
        vp.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动后的监听
     */
    @Override
    public void onPageSelected(int position) {
        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1){
            ib_start.setVisibility(View.VISIBLE);
        }else {
            ib_start.setVisibility(View.GONE);
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}