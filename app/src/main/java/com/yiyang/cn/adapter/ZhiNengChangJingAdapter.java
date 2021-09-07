package com.yiyang.cn.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ChangJingModel;
import com.yiyang.cn.model.ChangJingXiangQingModel;
import com.suke.widget.SwitchButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengChangJingAdapter extends BaseQuickAdapter<ChangJingModel.DataBean.SceneBean, BaseViewHolder> {
    public ZhiNengChangJingAdapter(int layoutResId, @Nullable List<ChangJingModel.DataBean.SceneBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChangJingModel.DataBean.SceneBean item) {
        helper.addOnClickListener(R.id.rrl_main);
        helper.addOnClickListener(R.id.iv_zhixing);
        Glide.with(mContext).load(item.getScene_pic()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getScene_title());
        TextView tvJianJie = helper.getView(R.id.tv_text);
        ImageView doImage = helper.getView(R.id.iv_zhixing);
        /**
         * 场景类型：1.一键执行  2.定时 3.动作触发
         */
        tvJianJie.setText(item.getScene_type_name());
        if (item.getScene_type().equals("1")) {
            doImage.setVisibility(View.VISIBLE);
        } else if (item.getScene_type().equals("2")) {
            doImage.setVisibility(View.GONE);
        } else if (item.getScene_type().equals("3")) {
            doImage.setVisibility(View.GONE);
        }

        SwitchButton switchButton = helper.getView(R.id.csw_switch_button);
        if (item.getScene_state().equals("3")) {
            switchButton.setChecked(true);
            if (item.getScene_type().equals("1")) {
                doImage.setVisibility(View.VISIBLE);
            } else {
                doImage.setVisibility(View.GONE);
            }
        } else if (item.getScene_state().equals("2")) {
            switchButton.setChecked(false);
            doImage.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.view);
    }
}
