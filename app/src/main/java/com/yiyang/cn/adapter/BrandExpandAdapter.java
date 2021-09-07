package com.yiyang.cn.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.CarBrandDetailsActivity;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.model.BrandItem;
import com.yiyang.cn.model.CarBrand;


import java.util.ArrayList;
import java.util.List;

public class BrandExpandAdapter extends ExpandableRecyclerAdapter<BrandItem> {
    public static final int TYPE_PERSON = 1001;
    private LRecyclerView recyclerView;
    public BrandExpandAdapter(Context context, LRecyclerView recyclerView) {
        super(context);
        this.recyclerView = recyclerView;
    }


    public class CommentViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        TextView tvFirst;

        CommentViewHolder(View view, LRecyclerView recyclerView) {
            super(view,recyclerView);
            tvFirst = view.findViewById(R.id.tv_first);

        }

        public void bind(int position) {
            super.bind(position);
            tvFirst.setText(visibleItems.get(position).first);
        }
    }

    public class CommentChildViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        TextView tvBrand;
        ImageView ivPic;

        View contentView;
        CommentChildViewHolder(View view) {
            super(view);
            tvBrand = view.findViewById(R.id.tv_brand);
            ivPic = view.findViewById(R.id.iv_pic);
            contentView = view;
        }

        public void bind(final int position) {
            tvBrand.setText(visibleItems.get(position).name);
            Glide.with(mContext).load(visibleItems.get(position).Avatar).into(ivPic);

            contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceHelper.getInstance(mContext).putString("brand_id",visibleItems.get(position).id);
                    PreferenceHelper.getInstance(mContext).putString("brand_name",visibleItems.get(position).name);
                    PreferenceHelper.getInstance(mContext).putString("brand_pic",visibleItems.get(position).Avatar.toString());
                    mContext.startActivity(new Intent(mContext,CarBrandDetailsActivity.class));

                }
            });




        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new CommentViewHolder(inflate(R.layout.item_first, parent), recyclerView);
            case TYPE_PERSON:
            default:
                return new CommentChildViewHolder(inflate(R.layout.item_child_brand, parent));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ExpandableRecyclerAdapter.ViewHolder holder, final int position) {
//        holder.setIsRecyclable(false);
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                ((CommentViewHolder) holder).bind(position);
                break;
            case TYPE_PERSON:
            default:
                ((CommentChildViewHolder) holder).bind(position);
                break;
        }
    }
    public List<BrandItem> getList(List<CarBrand.DataBean> list){
        ArrayList<BrandItem> items = new ArrayList<>();
        for (int i = 0;i<list.size();i++){
            items.add(new BrandItem(list.get(i).getInitials()));
            for (int j = 0;j<list.get(i).getCc().size();j++){
                items.add(new BrandItem(list.get(i).getCc().get(j).getBrand_name(),list.get(i).getCc().get(j).getPic_url(),list.get(i).getCc().get(j).getBrand_id()));
            }
        }
        return items;
    }

    public void clear() {
        visibleItems.clear();
        notifyDataSetChanged();
    }

    public int getFirstPositionByChar(char sign) {
        for (int i = 0; i < visibleItems.size(); i++) {
            if (visibleItems.get(i).getHeadLetter() == sign) {
                return i;
            }
        }
        return -1;
    }
}
