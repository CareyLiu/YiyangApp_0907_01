package com.yiyang.cn.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.yiyang.cn.R;
import com.yiyang.cn.model.KongQiJianCeModel;

import java.util.ArrayList;
import java.util.List;

public class Co2Fragment extends Fragment {

    private View mView;
    private LineChart line_chart;

    List<KongQiJianCeModel.DataBean.GdListBean> kongQiJianCeList = new ArrayList<>();

    private String type = "0";

    public Co2Fragment(List<KongQiJianCeModel.DataBean.GdListBean> kongQiJianCeList, String type) {
        this.kongQiJianCeList = kongQiJianCeList;
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.chart_seventh, container, false);
        initView();
        return mView;
    }

    private void initView() {
        line_chart = mView.findViewById(R.id.line_chart);
        initData();
        setAxis();
        setLegend();
        //  设置图表控件是否可以进行触控操作
        line_chart.setEnabled(false);
        //  调用图表控件「描述方法」并直接禁用
        line_chart.getDescription().setEnabled(false);
    }

    private void setLegend() {
        //  获得图例的实例
        Legend legend = line_chart.getLegend();
        //  设置图例是否在图表控件内部显示
        legend.setDrawInside(true);
        //  设置图例的排列方向
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        //  设置图例在图表控件中的水平对齐方向
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        //  设置图例在图表控件中的垂直对齐方向
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //  设置图例的色块大小
        legend.setFormSize(15);
        //  设置图例的色块形状
        legend.setForm(Legend.LegendForm.CIRCLE);
        //  设置图例的文字大小
        legend.setTextSize(15);
        //  设置图例的文字颜色
        legend.setTextColor(Color.BLACK);
    }


    private void setAxis() {
        //  获得X轴实例
        XAxis xl = line_chart.getXAxis();
        //  设置X轴显示位置
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        //  设置X轴步长
        xl.setGranularity(1);
        //  设置是否显示X轴的延伸线
        xl.setDrawGridLines(false);
        if (type.equals("1")){
            xl.setAxisMinimum(0);
            xl.setAxisMaximum(24);
        }


        //  获得左边Y轴实例
        YAxis yl = line_chart.getAxisLeft();
        yl.setGranularity(500);
        //  设置左边Y轴最小单位为0
        yl.setAxisMinimum(0);
        yl.setAxisMaximum(5000);


        //  获得右边Y轴的实例
        YAxis yl_right = line_chart.getAxisRight();
        //  设置右边Y轴是否可用
        yl_right.setEnabled(false);
    }

    private void initData() {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < kongQiJianCeList.size(); i++) {
            String strHeng = kongQiJianCeList.get(i).getTime().substring(0, 2);
            entries.add(new Entry(Float.valueOf(strHeng), Float.valueOf(kongQiJianCeList.get(i).getGd_carbon_dioxide())));
        }
//        entries.add(new Entry(0, 6));
//        entries.add(new Entry(1, 12));
//        entries.add(new Entry(2, 3));
//        entries.add(new Entry(3, 4));
//        entries.add(new Entry(4, 8));
        setDataStyle(entries);
    }

    /**
     * 设置折线的样式
     *
     * @param entries
     */
    private void setDataStyle(List<Entry> entries) {
        //        List<ILineDataSet> dataSets = new ArrayList<>();

        LineDataSet dataSet = new LineDataSet(entries, "");
        //  是否设置线的填充色
        dataSet.setDrawFilled(true);
        //  折线的颜色
        dataSet.setFillColor(Color.parseColor("#FF2366"));
        //  填充色的颜色
        dataSet.setColor(Color.parseColor("#FF2366"));
        //  折线的宽度
        dataSet.setLineWidth(2);
        //  是否设置圆点空洞
        dataSet.setDrawCircleHole(false);
        //  设置圆点的半径
        dataSet.setCircleRadius(3);
        //  设置圆的背景颜色
        dataSet.setCircleColor(Color.parseColor("#FF2366"));
        //  设置数值字体大小
        dataSet.setValueTextSize(13);
        //  设置数值字体颜色
        dataSet.setValueTextColor(Color.parseColor("#FF2366"));
        //  设置折线的显示模式
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

//        LineDataSet dataSet2 = new LineDataSet(entries2, "娄底市温度");
//        dataSet2.setDrawFilled(true);
//        dataSet2.setFillColor(Color.parseColor("#009AD1"));
//        dataSet2.setColor(Color.parseColor("#009AD1"));
//        dataSet2.setLineWidth(2);
//        dataSet2.setDrawCircleHole(false);
//        dataSet2.setCircleRadius(3);
//        dataSet2.setCircleColor(Color.parseColor("#009AD1"));
//        dataSet2.setValueTextSize(13);
//        dataSet2.setValueTextColor(Color.parseColor("#009AD1"));
//        dataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);

//        dataSets.add(dataSet);
//        dataSets.add(dataSet2);

        LineData lineData = new LineData(dataSet);
//        LineData lineData = new LineData(dataSets);

        line_chart.setData(lineData);
    }
}
