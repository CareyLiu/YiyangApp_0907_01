package com.yiyang.cn.adapter;

import androidx.annotation.Nullable;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yiyang.cn.R;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.yiyang.cn.model.MessageListBean;
import com.yiyang.cn.model.MessageModel;

import java.util.List;

public class MessageListAdapter extends BaseQuickAdapter<MessageModel.DataBean, BaseViewHolder> {
    public MessageListAdapter(int layoutResId, @Nullable List<MessageModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageModel.DataBean item) {

        //图片展示other_img_url	其它类型网络图片url
        //注：1/2/3/8从本地获取
        //notify_type	通知类型：1.风暖加热器报警
        // 2.水暖加热器故障报警3.汽车报警
        //  8.普通消息 9.广告消息
        //  11.商城消息 12.商城订单消息


        if (item.getNotify_read().equals("1")) {
            helper.setVisible(R.id.iv_xiaohongdian, true);
        } else if (item.getNotify_read().equals("2")) {
            helper.setVisible(R.id.iv_xiaohongdian, false);
        }

        ImageView ivImage = helper.getView(R.id.iv_image);

        if (item.getNotify_type().equals("1")) {

            Glide.with(mContext).load(R.mipmap.xiaoxi_icon_fengnuan).into(ivImage);


        } else if (item.getNotify_type().equals("2")) {
            Glide.with(mContext).load(R.mipmap.xiaoxi_icon_shuinuan).into(ivImage);
            //   ivImage.setBackgroundResource(R.mipmap.xiaoxi_icon_shuinuan);
        } else if (item.getNotify_type().equals("3")) {
            Glide.with(mContext).load(R.mipmap.xiaoxi_icon_car).into(ivImage);
            // ivImage.setBackgroundResource(R.mipmap.xiaoxi_icon_car);
        } else if (item.getNotify_type().equals("8")) {
            Glide.with(mContext).load(R.mipmap.xiaoxi_icon_tonghzhi).into(ivImage);
            //ivImage.setBackgroundResource(R.mipmap.xiaoxi_icon_tonghzhi);
        } else if (item.getNotify_type().equals("9")) {
            Glide.with(mContext)
                    .load(item.getOther_img_url())
                    .into((ImageView) ivImage);


        } else if (item.getNotify_type().equals("11")) {
            Glide.with(mContext)
                    .load(item.getOther_img_url())
                    .into((ImageView) ivImage);
        } else if (item.getNotify_type().equals("12")) {
            Glide.with(mContext)
                    .load(item.getOther_img_url())
                    .into((ImageView) ivImage);
        } else {
            Glide.with(mContext).load(R.mipmap.preview_shop_ffffff).into(ivImage);
        }


        helper.setText(R.id.tv_type, item.getNotify_text());
        helper.setText(R.id.tv_text, item.getCreate_time());
        helper.addOnClickListener(R.id.constrain);
        helper.addOnLongClickListener(R.id.constrain);

    }
}
