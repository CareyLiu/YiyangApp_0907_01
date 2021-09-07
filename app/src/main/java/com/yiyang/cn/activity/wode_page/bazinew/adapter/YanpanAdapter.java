package com.yiyang.cn.activity.wode_page.bazinew.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.model.YanpanModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YanpanAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private ViewHolder holder;

    public YanpanAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.bazi_item_yanpan, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (list != null && list.size() > position) {
            holder.tv_time.setText(list.get(position));
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_time)
        TextView tv_time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
