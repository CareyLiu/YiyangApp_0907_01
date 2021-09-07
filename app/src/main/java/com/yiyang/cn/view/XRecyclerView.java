package com.yiyang.cn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class XRecyclerView extends RecyclerView {
    //用来存储添加的headerView和footerView
    private ArrayList<View> mHeadView = new ArrayList<>();
    private ArrayList<View> mFootView = new ArrayList<>();

    //RecyclierView的适配器
    private Adapter mAdapter;


    public XRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    //添加头部view，仿ListView的源码改造
    public void addHeaderView(View headerView) {
        mHeadView.add(headerView);
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeadView, mFootView, mAdapter);
            }
        }
    }

    //添加脚View
    public void addFooterView(View footView) {
        mFootView.add(footView);
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeadView, mFootView, mAdapter);
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        //参考listview方法 setAdapter的源码，对adapter进行更换
        if (mHeadView.size() > 0 || mFootView.size() > 0)
            mAdapter = new HeaderViewRecyclerAdapter(mHeadView, mFootView, adapter);
        else {
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }

    public Adapter getAdapter(){
        return mAdapter;
    }
}
