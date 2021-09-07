package com.yiyang.cn.activity.wode_page.bazinew.adapter;

import android.content.Context;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class CiVpAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private Context context;

    public CiVpAdapter(FragmentManager fm, List<Fragment> fragments, Context context) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
