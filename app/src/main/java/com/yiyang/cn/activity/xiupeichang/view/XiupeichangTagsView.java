package com.yiyang.cn.activity.xiupeichang.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyang.cn.R;

public class XiupeichangTagsView extends LinearLayout {
    private Context context;
    private TextView tv_tag;

    public XiupeichangTagsView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_xiupeichang_tag, this, true);
        tv_tag = view.findViewById(R.id.tv_tag);
    }

    public void setTag(String tag) {
        tv_tag.setText(tag);
    }
}
