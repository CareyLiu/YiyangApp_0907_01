package com.yiyang.cn.activity.wode_page.bazinew.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.wode_page.bazinew.model.TuiguangModel;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

public class TuiguangAdapter extends BaseQuickAdapter<TuiguangModel.DataBean.PromotersListBean, BaseViewHolder> {

    public TuiguangAdapter(int layoutResId, @Nullable List<TuiguangModel.DataBean.PromotersListBean> data) {

        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuiguangModel.DataBean.PromotersListBean item) {
        helper.setText(R.id.tv_name, item.getUser_name());
        helper.setText(R.id.tv_phone, item.getUser_phone());

        //设置图片圆角角度
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗  override(300, 300)
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(mContext).load(item.getUser_img_url()).apply(options).into((ImageView) helper.getView(R.id.iv_head));
    }
}
