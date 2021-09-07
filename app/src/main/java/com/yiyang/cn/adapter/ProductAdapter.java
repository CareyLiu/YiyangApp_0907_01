package com.yiyang.cn.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.yiyang.cn.R;
import com.yiyang.cn.model.GoodsDetails_f;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<GoodsDetails_f.DataBean.ProductListBean> items;
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;
    public ProductAdapter(Context context, List<GoodsDetails_f.DataBean.ProductListBean> items) {
        this.items = items;
        mContext = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product,  null, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {

          holder.cbProduct.setText(items.get(position).getProduct_title());
          if (items.get(position).getFlag()){
              holder.cbProduct.setChecked(true);
              holder.cbProduct.setTextColor(mContext.getResources().getColor(R.color.app_bg));
          } else{
              holder.cbProduct.setChecked(false);
              holder.cbProduct.setTextColor(mContext.getResources().getColor(R.color.black));
          }

          holder.cbProduct.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  for (int i = 0 ; i<items.size();i++){
                     // items.get(i).setFlag(false);
                  }
                //  items.get(position).setFlag(holder.cbProduct.isChecked());
                  mOnItemClickListener.onItemClick(holder.itemView, position);
                  notifyDataSetChanged();

              }
          });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {


        CheckBox cbProduct;

        ProductViewHolder(View view) {
            super(view);
            cbProduct = view.findViewById(R.id.cb_product);

        }

    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
