package com.yiyang.cn.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseSectionQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.model.SheBeiModel;

import java.util.List;

public class SheBeiListAdapter extends BaseSectionQuickAdapter<SheBeiModel, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SheBeiListAdapter(int layoutResId, int sectionHeadResId, List<SheBeiModel> data) {
        super(layoutResId, sectionHeadResId, data);

    }


    @Override
    protected void convertHead(BaseViewHolder helper, SheBeiModel item) {
        helper.setText(R.id.tv_name, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, SheBeiModel item) {
        helper.addOnClickListener(R.id.constrain);

        if (!StringUtils.isEmpty(item.share_type)) {
            if (item.share_type.equals("2")) {
                helper.setText(R.id.tv_name, item.device_name + "(共享)");
            } else {
                helper.setText(R.id.tv_name, item.device_name);
            }
        } else {
            helper.setText(R.id.tv_name, item.device_name);
        }

        helper.setText(R.id.tv_ccid, "ccid: " + item.ccid);
        helper.setText(R.id.tv_youxiaoqi, "设备有效期至：" + item.validity_time);
        helper.setText(R.id.tv_shiyong_zhuangtai, item.validity_term);
        Glide.with(mContext).load(item.device_img_url).into((ImageView) helper.getView(R.id.iv_image));
        if (item.validity_state.equals("1")) {
            helper.getView(R.id.tv_shiyong_zhuangtai).setBackgroundResource(R.drawable.bg_shebei_shiyongzhuangtai_red);
            TextView tv = (TextView) helper.getView(R.id.tv_shiyong_zhuangtai);
            tv.setTextColor(mContext.getResources().getColor(R.color.color_FFFC0100));
        } else if (item.validity_state.equals("2")) {
            helper.getView(R.id.tv_shiyong_zhuangtai).setBackgroundResource(R.drawable.bg_shebei_shiyongzhuangtai_gray);
            TextView tv = (TextView) helper.getView(R.id.tv_shiyong_zhuangtai);
            tv.setTextColor(mContext.getResources().getColor(R.color.gray_999999));
        }



        Log.i("SheBeiListAdapter", item.share_type + item.ccid);
    }
}
