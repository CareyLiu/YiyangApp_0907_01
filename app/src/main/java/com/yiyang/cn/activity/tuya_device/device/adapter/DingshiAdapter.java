package com.yiyang.cn.activity.tuya_device.device.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuya_device.utils.TuyaConfig;
import com.tuya.smart.sdk.bean.Timer;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import androidx.annotation.Nullable;

public class DingshiAdapter extends BaseQuickAdapter<Timer, BaseViewHolder> {
    private String productId;

    public DingshiAdapter(int layoutResId, @Nullable List<Timer> data, String productId) {
        super(layoutResId, data);
        this.productId = productId;
    }

    @Override
    protected void convert(BaseViewHolder helper, Timer item) {
        helper.setText(R.id.tv_time, item.getTime());

        String remark = item.getRemark();
        TextView tv_beizhu = helper.getView(R.id.tv_beizhu);
        if (TextUtils.isEmpty(remark)) {
            tv_beizhu.setVisibility(View.GONE);
        } else {
            tv_beizhu.setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.tv_beizhu, remark);

        String loops = item.getLoops();
        String chongfu;
        if (loops.equals("1111111")) {
            chongfu = "每天重复";
        } else if (loops.equals("0000000")) {
            chongfu = "仅限一次";
        } else {
            String a = loops.substring(0, 1);
            String b = loops.substring(1, 2);
            String c = loops.substring(2, 3);
            String d = loops.substring(3, 4);
            String e = loops.substring(4, 5);
            String f = loops.substring(5, 6);
            String g = loops.substring(6);

            String riqi = "";
            if (a.equals("1")) {
                riqi = riqi + "星期日,";
            }

            if (b.equals("1")) {
                riqi = riqi + "星期一,";
            }

            if (c.equals("1")) {
                riqi = riqi + "星期二,";
            }

            if (d.equals("1")) {
                riqi = riqi + "星期三,";
            }

            if (e.equals("1")) {
                riqi = riqi + "星期四,";
            }

            if (f.equals("1")) {
                riqi = riqi + "星期五,";
            }

            if (g.equals("1")) {
                riqi = riqi + "星期六,";
            }

            chongfu = riqi.substring(0, riqi.length() - 1);
        }
        helper.setText(R.id.tv_chongfu, chongfu);

        ImageView iv_switch = helper.getView(R.id.iv_switch);
        int status = item.getStatus();
        if (status == 1) {
            iv_switch.setImageResource(R.mipmap.switch_open);
        } else {
            iv_switch.setImageResource(R.mipmap.switch_close);
        }
        helper.addOnClickListener(R.id.iv_switch);

        getData(item.getValue(), helper);
    }

    public void getData(String dpStr, BaseViewHolder helper) {
        JSONObject jsonObject = JSON.parseObject(dpStr);
        Set<String> strings = jsonObject.keySet();
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            // 获得key
            String key = it.next();
            String value = jsonObject.getString(key);
            jieData(key, jsonObject, helper);
        }
    }

    private void jieData(String key, JSONObject jsonObject, BaseViewHolder helper) {
        switch (productId) {
            case TuyaConfig.PRODUCTID_CHAZUO_A:
            case TuyaConfig.PRODUCTID_CHAZUO_B:
            case TuyaConfig.PRODUCTID_CHAZUO_WG:
                chazuo(key, jsonObject, helper);
                break;
            case TuyaConfig.PRODUCTID_SWITCH_THREE:
                switchSan(key, jsonObject, helper);
                break;
        }
    }

    private void switchSan(String key, JSONObject jsonObject, BaseViewHolder helper) {
        if (key.equals("1")) {//switch右键
            helper.setText(R.id.tv_dps_key, "右键：");
            Boolean kaiguan = jsonObject.getBoolean(key);
            if (kaiguan) {
                helper.setText(R.id.tv_dps_value, "开启");
            } else {
                helper.setText(R.id.tv_dps_value, "关闭");
            }
        } else if (key.equals("2")) {//switch中键
            helper.setText(R.id.tv_dps_key, "中键：");
            Boolean kaiguan = jsonObject.getBoolean(key);
            if (kaiguan) {
                helper.setText(R.id.tv_dps_value, "开启");
            } else {
                helper.setText(R.id.tv_dps_value, "关闭");
            }
        } else if (key.equals("3")) {//switch左键
            helper.setText(R.id.tv_dps_key, "左键：");
            Boolean kaiguan = jsonObject.getBoolean(key);
            if (kaiguan) {
                helper.setText(R.id.tv_dps_value, "开启");
            } else {
                helper.setText(R.id.tv_dps_value, "关闭");
            }
        }
    }

    private void chazuo(String key, JSONObject jsonObject, BaseViewHolder helper) {
        if (key.equals("1")) {//switch开关
            helper.setText(R.id.tv_dps_key, "开关：");
            Boolean kaiguan = jsonObject.getBoolean(key);
            if (kaiguan) {
                helper.setText(R.id.tv_dps_value, "开启");
            } else {
                helper.setText(R.id.tv_dps_value, "关闭");
            }
        }
    }
}
