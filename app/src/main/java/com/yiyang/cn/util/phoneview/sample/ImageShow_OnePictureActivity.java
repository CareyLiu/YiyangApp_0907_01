package com.yiyang.cn.util.phoneview.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.util.Tools;

import butterknife.BindView;


public class ImageShow_OnePictureActivity extends BaseActivity {


    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.full_image_root)
    RelativeLayout fullImageRoot;
    /**
     * PagerAdapter
     */
    private ImagePagerAdapter mAdapter;
    private ImmersionBar mImmersionBar;


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String bitmap) {
        Intent intent = new Intent(context, ImageShow_OnePictureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("bitmap", bitmap);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImmersionBar = ImmersionBar.with(this);

        initData();

        mImmersionBar();


    }

    private void initData() {
        Intent i = getIntent();
        if (i == null) {
            return;
        }
        String bitmap = getIntent().getStringExtra("bitmap");

        Bitmap picture = Tools.converStringToIcon(bitmap);
        ivImage.setImageBitmap(picture);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_one_image_show;
    }


    private void mImmersionBar() {
        mImmersionBar
                .statusBarDarkFont(true)
                .init();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }
}
