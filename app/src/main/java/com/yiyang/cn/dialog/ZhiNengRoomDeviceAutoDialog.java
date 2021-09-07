package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yiyang.cn.R;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.inter.OnItemClickListener;
import com.yiyang.cn.util.Util;

import java.util.List;


public class ZhiNengRoomDeviceAutoDialog {
    private Context context;
    private List<String> vocationList;
    private String title = "";
    private OnItemClickListener onItemClickListener;

    public ZhiNengRoomDeviceAutoDialog(Context context, List<String> vocationList, String title) {
        this.context = context;
        this.vocationList = vocationList;
        this.title = title;
        initView();
    }

    public void setOnItemDataClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void initView() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                onItemClickListener.onClick(null, options1);
            }
        })
                .setContentTextSize(13)
                .setCancelColor(Color.BLACK)
                .setSubCalSize(13)
                .setTitleSize(15)
                .setTitleText(title)
                .setSubmitText("完成")
                .setSubmitColor(Color.rgb(58, 133, 248))
                .setDividerColor(Color.rgb(238, 238, 238))
                .setTextColorCenter(Color.rgb(51, 51, 51)) //设置选中项文字颜色
                .setContentTextSize(15)
                .setLineSpacingMultiplier(2.0f)//行间距
                .isRestoreItem(true)
                .build();
        pvOptions.setPicker(vocationList);//三级选择器
        pvOptions.show();
    }
}
