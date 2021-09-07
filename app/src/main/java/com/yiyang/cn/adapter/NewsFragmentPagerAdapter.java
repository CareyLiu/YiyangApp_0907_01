package com.yiyang.cn.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> fragments;
	private FragmentManager fm;

	public NewsFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		this.fm = fm;
	}

	public NewsFragmentPagerAdapter(FragmentManager fm,
                                    ArrayList<Fragment> fragments) {
		super(fm);
		this.fm = fm;
		this.fragments = fragments;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public void setFragments(ArrayList<Fragment> fragments) {
		if (this.fragments != null) {
			FragmentTransaction ft = fm.beginTransaction();
//			for (int i = 0; i < fragments.size(); i++) {
//				ft.add(fragments.get(i), "" + i);
//			}
//			ft.commit();
			for (Fragment f : this.fragments) {
//				ft.remove(f);
				ft.hide(f);

			}
			ft.commit();
			ft = null;
			fm.executePendingTransactions();
		}
		this.fragments = fragments;
		notifyDataSetChanged();
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		Object obj = super.instantiateItem(container, position);
		//        String imageString = args != null ? args.getString("imagename") : "";

		return obj;
	}

}
