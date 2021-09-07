package com.yiyang.cn.tools.demo;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.readystatesoftware.chuck.internal.ui.MainActivity;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.AddressActivity;
import com.yiyang.cn.activity.HomeActivity;

public class YanShiActivity extends LauncherActivity {

    //定义两个Activity的名称
    String[] names = {"设置程序参数", "查看星际兵种"};
    //定义两个Activity对应的实现类
    Class<?>[] clazzs = {HomeActivity.class
            , AddressActivity.class};

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);
        // 设置该窗口显示的列表所需的Adapter
        setListAdapter(adapter);

    }

    @Override
    public Intent intentForPosition(int position) {
        return new Intent(YanShiActivity.this, clazzs[position]);
    }

}
