package com.yiyang.cn.config;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.yiyang.cn.util.GlideShowImageUtils;
import com.youth.banner.loader.ImageLoader;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class HuoDongImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        Glide.with(MyApplication.getAppContext()).applyDefaultRequestOptions(GlideShowImageUtils.showBannerCelve()).load(path).into(imageView);
    }
}
