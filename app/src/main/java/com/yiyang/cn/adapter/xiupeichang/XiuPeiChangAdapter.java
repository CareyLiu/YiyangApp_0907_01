package com.yiyang.cn.adapter.xiupeichang;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.model.TuanGouShangJiaListBean;

import java.util.List;

public class XiuPeiChangAdapter extends BaseQuickAdapter<TuanGouShangJiaListBean.DataBean.StoreListBean, BaseViewHolder> {
    public XiuPeiChangAdapter(int layoutResId, @Nullable List<TuanGouShangJiaListBean.DataBean.StoreListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuanGouShangJiaListBean.DataBean.StoreListBean item) {
        Glide.with(mContext).load(item.getInst_photo_url()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_xiupeichang_name, item.getInst_name());
        helper.setText(R.id.tv_diqu, item.getArea_name());
        helper.setText(R.id.tv_mendian, item.getInst_number_name());
        helper.setText(R.id.tv_xiaoliang, item.getInst_number());



    }
}
