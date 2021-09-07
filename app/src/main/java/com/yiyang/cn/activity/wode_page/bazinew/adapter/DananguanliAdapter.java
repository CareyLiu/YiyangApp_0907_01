package com.yiyang.cn.activity.wode_page.bazinew.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.model.DanganModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DananguanliAdapter extends BaseAdapter {
    private List<DanganModel.DataBean> list;
    private Context context;
    private ViewHolder holder;
    private OnEditClick editClick;

    public DananguanliAdapter(List<DanganModel.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<DanganModel.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setEditClick(OnEditClick editClick) {
        this.editClick = editClick;
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
            convertView = View.inflate(context, R.layout.bazi_item_dananguanli, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editClick != null) {
                    editClick.click(position);
                }
            }
        });

        if (list != null) {
            DanganModel.DataBean danganModel = list.get(position);
            holder.tv_name.setText(danganModel.getName());
            holder.tv_sex.setText(danganModel.getSex_text());
            holder.tv_yangli.setText("阳历：" + danganModel.getSolar_birthday());
            holder.tv_yinli.setText("阴历：" + danganModel.getLunar_birthday());

            String mingpan_user = danganModel.getMingpan_user();
            if ("1".equals(mingpan_user)) {
                holder.tv_moren.setVisibility(View.VISIBLE);
            } else {
                holder.tv_moren.setVisibility(View.GONE);
            }
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_sex)
        TextView tv_sex;
        @BindView(R.id.tv_yangli)
        TextView tv_yangli;
        @BindView(R.id.tv_yinli)
        TextView tv_yinli;
        @BindView(R.id.tv_edit)
        TextView tv_edit;
        @BindView(R.id.tv_moren)
        TextView tv_moren;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnEditClick {
        void click(int pos);
    }
}
