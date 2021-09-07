package com.yiyang.cn.config;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.util.GlideShowImageUtils;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class GlideImageLoader_x extends ImageLoader {

    public Activity activity;

    public GlideImageLoader_x(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(MyApplication.getAppContext()).applyDefaultRequestOptions(GlideShowImageUtils.showBannerCelve()).load(path).into(imageView);
    }


}
