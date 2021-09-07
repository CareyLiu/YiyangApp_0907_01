package com.yiyang.cn.view;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.ProductAdapter;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.model.GoodsDetails_f;
import com.yiyang.cn.util.HorizontalItemDecorator;

import java.util.List;


public class CustomBottomDialog extends BottomBaseDialog implements View.OnClickListener {

    private ImageView ivClose;//关闭
    private ImageView ivGoodsPic;//商品图片
    private ImageView ivAdd;//购买数量增加
    private ImageView ivCut;//购买数量减少
    private TextView tvPrice;//商品价格
    private TextView tvStoce;//商品库存
    private int stoce = 0;
    private TextView tvPackage;//默认套餐
    private EditText etCount;//购买数量
    private RecyclerView list;//套餐列表
    private Button btnAdd,btnBuy;//加入购物车，立即购买
    private ProductAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<GoodsDetails_f.DataBean.ProductListBean> productList; //套餐列表;

    View.OnClickListener addClickListener;
    View.OnClickListener buyClickListener;

    public CustomBottomDialog(Context context, View animateView) {
        super(context, animateView);
    }

    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.dialog_buy_parameter, null);
        ivClose = inflate.findViewById(R.id.iv_close);
        ivGoodsPic = inflate.findViewById(R.id.iv_goods_pic);
        ivAdd = inflate.findViewById(R.id.iv_add);
        ivCut = inflate.findViewById(R.id.iv_cut);
        tvPackage = inflate.findViewById(R.id.tv_package);
        tvPrice = inflate.findViewById(R.id.tv_price);
        tvStoce = inflate.findViewById(R.id.tv_stock);
        etCount = inflate.findViewById(R.id.et_count);
        list = inflate.findViewById(R.id.package_list);
        btnAdd = inflate.findViewById(R.id.btn_add);
        btnBuy = inflate.findViewById(R.id.btn_buy);
        adapter = new ProductAdapter(getContext(),productList);
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        list.setAdapter(adapter);
        list.setLayoutManager(layoutManager);
        list.addItemDecoration(new HorizontalItemDecorator(5));
        Glide.with(MyApplication.getAppContext()).load(productList.get(0).getIndex_photo_url()).into(ivGoodsPic);

        for (int i = 0; i<productList.size();i++){
            if (productList.get(i).getFlag()){
                tvPrice.setText(String.format("￥%s", productList.get(i).getMoney_now()));
                tvStoce.setText(String.format("库存(%s)", productList.get(i).getShop_product_count()));
                tvPackage.setText(productList.get(i).getProduct_title());
                stoce = Integer.parseInt(productList.get(i).getShop_product_count());
            }
        }

        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Glide.with(mContext).load(productList.get(position).getIndex_photo_url()).into(ivGoodsPic);
                tvPrice.setText(String.format("￥%s", productList.get(position).getMoney_now()));
                tvStoce.setText(String.format("库存(%s)", productList.get(position).getShop_product_count()));
                tvPackage.setText(productList.get(position).getProduct_title());
                stoce = Integer.parseInt(productList.get(position).getShop_product_count());
            }
        });

        btnAdd.setOnClickListener(addClickListener);
        btnBuy.setOnClickListener(buyClickListener);
        ivClose.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        ivCut.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {

    }

    public void setDataBean(List<GoodsDetails_f.DataBean.ProductListBean> productList){
        this.productList = productList;

    }



    @SuppressLint("DefaultLocale")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.iv_add:
                if (Integer.parseInt(etCount.getText().toString()) < stoce)
                etCount.setText(String.format("%d", Integer.parseInt(etCount.getText().toString()) + 1));
                break;
            case R.id.iv_cut:
                if (Integer.parseInt(etCount.getText().toString())>1)
                etCount.setText(String.format("%d", Integer.parseInt(etCount.getText().toString()) - 1));
                break;
        }
    }


    public void setAddClickListener(View.OnClickListener onClickListener){
        this.addClickListener = onClickListener;
    }
    public void setBuyClickListener(View.OnClickListener onClickListener){
        this.buyClickListener = onClickListener;
    }

    public String getCount(){
        return etCount.getText().toString();
    }

}
