package com.yiyang.cn.view.magicindicator;

import androidx.viewpager.widget.ViewPager;

import com.yiyang.cn.view.CustomViewPager;


/**
 * 简化和ViewPager绑定
 * Created by hackware on 2016/8/17.
 */

public class ViewPagerHelper {
    private static int preItem = 0;

    public static void bind(final MagicIndicator magicIndicator, final CustomViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

//                if (position > preItem) {//从左向右滑
//                    preItem = position;
//                    if (position == 1) {
//                        int a = position +1;
//                        magicIndicator.onPageSelected(position + 1);
//                        viewPager.setCurrentItem(a);
//                    } else {
//                        magicIndicator.onPageSelected(position);
//                    }
//                    return;
//                }
//                if (position < preItem) {//从右向左滑
//                    preItem = position;
//                    if (position == 1) {
//                        int a = position - 1;
//                        magicIndicator.onPageSelected(position - 1);
//                        viewPager.setCurrentItem(a);
//                    } else {
//                        magicIndicator.onPageSelected(position);
//                    }
//                    return;
//                }
                magicIndicator.onPageSelected(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }
}
