package com.yiyang.cn.util.phoneview.sample;

import android.content.Context;
import android.net.Uri;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.yiyang.cn.util.phoneview.PhotoView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;


/**
 * 图片浏览的PagerAdapter
 */
public class ImagePagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> imgsUrl;
    LayoutInflater inflater = null;


    //view内控件
    PhotoView photoView;

    public ImagePagerAdapter(Context context, ArrayList<String> imgsUrl) {
        this.context = context;
        this.imgsUrl = imgsUrl;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 动态加载数据
     */
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

//		((ImageShowViewPager) container).mCurrentView = ((TouchImageView) ((View)object).findViewById(R.id.full_image));

//		//把这个position赋值到一个全局变量，通过这个就会知道滑动到哪个页面了
//		setPosition(position);
//		//因为这个方法每次跳转会调用多次，在这里设置一个标示，只执行一次就可以了
//		if (positionValue[position] == false) {
//			//这个方法就是联网获取数据
//			getNewsData(categoryId, position);
//			if (Logs.IS_DEBUG) {
//				Logs.v("guo", "联网请求............."+itemPosttion);
//			}
//			positionValue[position] = true;
//		}

    }

    @Override
    public int getCount() {
        return imgsUrl == null ? 0 : imgsUrl.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//		View view = inflater.inflate(R.layout.activity_image_show_item, container, false);
//        View view = inflater.from(context).inflate(R.layout.activity_image_show_item, null);
//        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_image_show_item, container, false);

//        App.scaleScreenHelper.loadView((ViewGroup) view);
//        photoView = (PhotoView) view.findViewById(R.id.photo_view);
//        Picasso.with(context)
//                .load(imgsUrl.get(position) + AppConfig.IMG_SOURCE)
//                .into(photoView);

        PhotoView photoView = new PhotoView(container.getContext());
//		photoView.setImageResource(sDrawables[position]);

        if (imgsUrl.get(position).startsWith("http://") || imgsUrl.get(position).startsWith("https://")) {
            Picasso.with(context)
                    .load(imgsUrl.get(position))
                    .into(photoView);
        }else {
            photoView.setImageURI(Uri.fromFile(new File(imgsUrl.get(position))));
        }
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // Now just add PhotoView to ViewPager and return it
        // container.addView(view);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
