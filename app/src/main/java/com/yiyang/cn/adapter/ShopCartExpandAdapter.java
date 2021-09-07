package com.yiyang.cn.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.yiyang.cn.R;
import com.yiyang.cn.model.GoodsItem;
import com.yiyang.cn.model.ShopCartModel;

import java.util.ArrayList;
import java.util.List;

public class ShopCartExpandAdapter extends ExpandableRecyclerAdapter<GoodsItem> {
    public static final int TYPE_ITEM = 1001;
    private LRecyclerView recyclerView;
    public ShopCartExpandAdapter(Context context, LRecyclerView recyclerView) {
        super(context);
        this.recyclerView = recyclerView;
    }


    public class ShopViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        CheckBox cbShop;
        TextView tvShop;
        ImageView ivShop;

        public ShopViewHolder(View view, LRecyclerView recyclerView) {
            super(view,recyclerView);
            cbShop = view.findViewById(R.id.cb_shop);
            tvShop = view.findViewById(R.id.tv_shop);
            ivShop = view.findViewById(R.id.iv_shop);



        }

        public void bind(final int position) {
            super.bind(position);
            tvShop.setText(visibleItems.get(position).shopName);
            Glide.with(mContext).load(visibleItems.get(position).shopPic).into(ivShop);
            cbShop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    visibleItems.get(position).isCheck = isChecked;
                    notifyDataSetChanged();
                }
            });
        }
    }

    public class GoodsViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        CheckBox cbGoods;
        ImageView ivGoods;
        TextView tvPackage;
        TextView tvGoodsName;
        TextView tvPrice;
        TextView tvExPressPrice;
        LinearLayout layout;


        View contentView;
        public GoodsViewHolder(View view) {
            super(view);
            cbGoods = view.findViewById(R.id.cb_goods);
            ivGoods = view.findViewById(R.id.iv_goods);
            tvPackage = view.findViewById(R.id.tv_package);
            tvGoodsName = view.findViewById(R.id.tv_goods_name);
            tvPrice = view.findViewById(R.id.tv_price);
            tvExPressPrice = view.findViewById(R.id.tv_express_price);
            layout = view.findViewById(R.id.layout_goods);
            contentView = view;
        }

        public void bind(final int position) {
            tvPackage.setText(visibleItems.get(position).goosPackage);
            Glide.with(mContext).load(visibleItems.get(position).goodsPic).into(ivGoods);
            tvExPressPrice.setText(visibleItems.get(position).goodsPostage);
            tvPrice.setText(visibleItems.get(position).goodsPrice);
            tvGoodsName.setText(visibleItems.get(position).goodsName);
            if (visibleItems.get(position).isLast){
                layout.setBackground(mContext.getResources().getDrawable(R.drawable.bg_shape_goods));
            }





//            contentView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    PreferenceHelper.getInstance(mContext).putString("brand_id",visibleItems.get(position).id);
//                    PreferenceHelper.getInstance(mContext).putString("brand_name",visibleItems.get(position).name);
//                    PreferenceHelper.getInstance(mContext).putString("brand_pic",visibleItems.get(position).Avatar.toString());
//                    mContext.startActivity(new Intent(mContext,CarBrandDetailsActivity.class));
//
//                }
//            });




        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new ShopViewHolder(inflate(R.layout.item_shop, parent), recyclerView);
            case TYPE_ITEM:
            default:
                return new GoodsViewHolder(inflate(R.layout.item_goods, parent));
        }
    }

    @Override
    public void onBindViewHolder(ExpandableRecyclerAdapter.ViewHolder holder, final int position) {
//        holder.setIsRecyclable(false);
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                ((ShopViewHolder) holder).bind(position);
                break;
            case TYPE_ITEM:
            default:
                ((GoodsViewHolder) holder).bind(position);
                break;
        }
    }
    public List<GoodsItem> getList(List<ShopCartModel.DataBean> list){
        ArrayList<GoodsItem> items = new ArrayList<>();
        for (int i = 0;i<list.size();i++){
            items.add(new GoodsItem(list.get(i).getInst_name(),list.get(i).getInst_logo_url()));
            for (int j = 0;j<list.get(i).getProList().size();j++){

                if (j == list.get(i).getProList().size() - 1){
                    items.add(new GoodsItem(list.get(i).getProList().get(j).getWares_money_go(),
                            list.get(i).getProList().get(j).getForm_product_money(),
                            list.get(i).getProList().get(j).getShop_product_title(),
                            list.get(i).getProList().get(j).getIndex_photo_url(),
                            list.get(i).getProList().get(j).getProduct_title(),
                            true,
                            false));
                }else {
                    items.add(new GoodsItem(list.get(i).getProList().get(j).getWares_money_go(),
                            list.get(i).getProList().get(j).getForm_product_money(),
                            list.get(i).getProList().get(j).getShop_product_title(),
                            list.get(i).getProList().get(j).getIndex_photo_url(),
                            list.get(i).getProList().get(j).getProduct_title(),
                            false,
                            false));
                }


            }
        }
        return items;
    }
    public interface OnViewItemClickListener {
        void onItemClick(boolean isFlang, View view, int position);
    }
    private OnViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void clear() {
        visibleItems.clear();
        notifyDataSetChanged();
    }

}
