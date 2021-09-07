package com.yiyang.cn.activity.wode_page.bazinew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiyang.cn.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JiexiAdapter extends RecyclerView.Adapter<JiexiAdapter.JiexiHodler> {
    private String[] titles;
    private Context context;
    private int count = -1;
    private TitlesClick click;

    public JiexiAdapter(String[] titles, Context context) {
        this.titles = titles;
        this.context = context;
    }

    public void setCount(int count) {
        this.count = count;
        notifyDataSetChanged();
    }

    public void setClick(TitlesClick click) {
        this.click = click;
    }

    @NonNull
    @Override
    public JiexiHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bazi_item_jiexi_title, parent, false);
        JiexiHodler hodler = new JiexiHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull JiexiHodler holder, int position) {
        if (titles != null && titles.length > position) {
            holder.tv_title.setText(titles[position]);
            if (count == position) {
                holder.line.setVisibility(View.VISIBLE);
            } else {
                holder.line.setVisibility(View.GONE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (click != null) {
                        click.click(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class JiexiHodler extends RecyclerView.ViewHolder {
        private View line;
        private TextView tv_title;

        public JiexiHodler(@NonNull View itemView) {
            super(itemView);
            line = itemView.findViewById(R.id.view_line);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }


    public interface TitlesClick {
        void click(int pos);

    }
}
