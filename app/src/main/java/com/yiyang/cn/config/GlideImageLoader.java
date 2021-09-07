package com.yiyang.cn.config;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.util.GlideShowImageUtils;
import com.yiyang.cn.util.Tools;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (!Tools.isDestroy((Activity) context)) {
            Glide.with(context).applyDefaultRequestOptions(GlideShowImageUtils.showBannerCelve()).load(path).into(imageView);
        }
    }
}
