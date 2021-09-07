package com.yiyang.cn.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.yiyang.cn.R;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.util.NavigationUtils;

/**
 * Created by Sl on 2019/7/4.
 */

public class CustomInfoWindow implements AMap.InfoWindowAdapter {
    private Context mContext = MyApplication.getAppContext();
    private String title;
    private LatLng latLng;
    View infoWindow = null;


    @Override
    public View getInfoWindow(Marker marker) {
        latLng = marker.getPosition();
        title = marker.getTitle();
        if (infoWindow == null) {
            infoWindow = LayoutInflater.from(mContext).inflate(R.layout.custom_info_window, null);
            TextView tvSnippet = infoWindow.findViewById(R.id.tv_snippet);
            TextView tvTitle = infoWindow.findViewById(R.id.tv_title);
            tvTitle.setText(title);
            tvSnippet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        NavigationUtils.Navigation(latLng);
                    } catch (Exception e) {
                        UIHelper.ToastMessage(MyApplication.getApp().getApplicationContext(), "请下载高德后重新尝试", Toast.LENGTH_SHORT);
                    }


                }
            });

        }
        render(marker, infoWindow);
        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {
        //如果想修改自定义Infow中内容，请通过view找到它并修改
    }
}
