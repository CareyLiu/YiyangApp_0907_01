package com.yiyang.cn.activity.tuya_device.add.adapter;




import com.yiyang.cn.basicmvp.BaseFragment;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class MainVpAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> list;

    public void setList(List<BaseFragment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public MainVpAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
