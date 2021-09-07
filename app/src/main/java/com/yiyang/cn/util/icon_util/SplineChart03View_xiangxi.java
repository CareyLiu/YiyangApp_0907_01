package com.yiyang.cn.util.icon_util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.yiyang.cn.model.KongQiJianCeModel;

import org.xclcharts.chart.CustomLineData;
import org.xclcharts.chart.PointD;
import org.xclcharts.chart.SplineChart;
import org.xclcharts.chart.SplineData;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SplineChart03View_xiangxi extends DemoView {


    private String TAG = "SplineChart03View";
    private SplineChart chart = new SplineChart();
    //分类轴标签集合
    private LinkedList<String> labels = new LinkedList<String>();
    private LinkedList<SplineData> chartData = new LinkedList<SplineData>();

    private Paint mPaintTooltips = new Paint(Paint.ANTI_ALIAS_FLAG);

    //setCategoryAxisCustomLines
    // splinechart支持横向和竖向定制线
    private List<CustomLineData> mXCustomLineDataset = new ArrayList<CustomLineData>();
    private List<CustomLineData> mYCustomLineDataset = new ArrayList<CustomLineData>();
    private List<KongQiJianCeModel.DataBean.GdListBean> listBeans;

    private String jiaQuanOrKongQi; //1甲醛那一行 2.空气指数那一行
    private String laber;

    public SplineChart03View_xiangxi(Context context, List<KongQiJianCeModel.DataBean.GdListBean> listBeans, String jiaQuanOrKongQi) {
        super(context);
        this.listBeans = listBeans;
        this.jiaQuanOrKongQi = jiaQuanOrKongQi;
        initView();
    }

    public SplineChart03View_xiangxi(Context context, List<KongQiJianCeModel.DataBean.GdListBean> listBeans, String jiaQuanOrKongQi, String laber) {
        super(context);
        this.listBeans = listBeans;
        this.jiaQuanOrKongQi = jiaQuanOrKongQi;
        this.laber = laber;
        initView();
    }

    public SplineChart03View_xiangxi(Context context, AttributeSet attrs, List<KongQiJianCeModel.DataBean.GdListBean> listBeans) {
        super(context, attrs);
        initView();
    }

    public SplineChart03View_xiangxi(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        chartLabels();
        //chartCustomeLines();
        chartDataSet();
        chartRender();

        //綁定手势滑动事件
        this.bindTouch(this, chart);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //图所占范围大小
        chart.setChartRange(w, h);
    }


    private void chartRender() {
        try {

            //设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....
            int[] ltrb = getBarLnDefaultSpadding();
            chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);

            //显示边框
            chart.showRoundBorder();

            //数据源
            chart.setCategories(labels);
            chart.setDataSource(chartData);

            //坐标系
            //数据轴最大值
            if (jiaQuanOrKongQi.equals("2")) {
                chart.getDataAxis().setAxisMax(5000);
                //chart.getDataAxis().setAxisMin(0);
                //数据轴刻度间隔
                chart.getDataAxis().setAxisSteps(1000);
            } else {
                chart.getDataAxis().setAxisMax(1000);
                //chart.getDataAxis().setAxisMin(0);
                //数据轴刻度间隔
                chart.getDataAxis().setAxisSteps(100);
            }

            chart.setCustomLines(mYCustomLineDataset); //y轴


            //chart.setCustomLines(mXCustomLineDataset); //y轴
            chart.setCategoryAxisCustomLines(mXCustomLineDataset); //x轴

            //设置图的背景色
            chart.setApplyBackgroundColor(true);
            chart.setBackgroundColor(Color.rgb(255, 255, 255));
            chart.getBorder().setBorderLineColor(Color.rgb(179, 147, 197));

            //调轴线与网络线风格
            chart.getCategoryAxis().hideTickMarks();
            chart.getDataAxis().hideAxisLine();
            chart.getDataAxis().hideTickMarks();
            chart.getPlotGrid().hideHorizontalLines();
            //chart.hideTopAxis();
            //chart.hideRightAxis();

            chart.getPlotGrid().getHorizontalLinePaint().setColor(Color.rgb(179, 147, 197));
            chart.getCategoryAxis().getAxisPaint().setColor(
                    chart.getPlotGrid().getHorizontalLinePaint().getColor());
            chart.getCategoryAxis().getAxisPaint().setStrokeWidth(
                    chart.getPlotGrid().getHorizontalLinePaint().getStrokeWidth());


            //定义交叉点标签显示格式,特别备注,因曲线图的特殊性，所以返回格式为:  x值,y值
            //请自行分析定制
            chart.setDotLabelFormatter(new IFormatterTextCallBack() {

                @Override
                public String textFormatter(String value) {
                    String label = "[" + value + "]";
                    return (label);
                }

            });
            //标题
//			chart.setTitle("Spline Chart");
//			chart.addSubtitle("(XCL-Charts Demo)");

            //激活点击监听
            chart.ActiveListenItemClick();
            //为了让触发更灵敏，可以扩大5px的点击监听范围
            chart.extPointClickRange(5);
            chart.showClikedFocus();

            //显示平滑曲线
            chart.setCrurveLineStyle(XEnum.CrurveLineStyle.BEZIERCURVE);

            //图例显示在正下方
            chart.getPlotLegend().setVerticalAlign(XEnum.VerticalAlign.BOTTOM);
            chart.getPlotLegend().setHorizontalAlign(XEnum.HorizontalAlign.CENTER);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }
    }

    private void chartDataSet() {
        //线1的数据集
        //甲醛
        List<PointD> linePoint1 = new ArrayList<PointD>();
        if (jiaQuanOrKongQi.equals("1")) {

//            for (int i = 0; i < listBeans.size(); i++) {
//                String strHeng = listBeans.get(i).getTime().substring(0, 2);
//                linePoint1.add(new PointD(Double.valueOf(strHeng), Double.valueOf(listBeans.get(i).getGd_cascophen())));
//
//            }

            PointD pointD = new PointD();
            pointD.x = 1d;
            pointD.y = 100d;
            linePoint1.add(pointD);

        } else if (jiaQuanOrKongQi.equals("2")) {

            for (int i = 0; i < listBeans.size(); i++) {
                String strHeng = listBeans.get(i).getTime().substring(0, 2);
                linePoint1.add(new PointD(Double.valueOf(strHeng), Double.valueOf(listBeans.get(i).getGd_air_quality())));

            }
        }


        SplineData dataSeries1 = new SplineData("线一", linePoint1,
                Color.rgb(29, 32, 130));
        //把线弄细点
        dataSeries1.getLinePaint().setStrokeWidth(8);
        //dataSeries1.setLabelVisible(true);


        List<PointD> linePoint2 = new ArrayList<PointD>();

        if (jiaQuanOrKongQi.equals("1")) {
            for (int i = 0; i < listBeans.size(); i++) {
                String strHeng = listBeans.get(i).getTime().substring(0, 2);
                linePoint2.add(new PointD(Double.valueOf(strHeng), Double.valueOf(listBeans.get(i).getGd_particulate_matter())));
            }

        } else {
            for (int i = 0; i < listBeans.size(); i++) {
                String strHeng = listBeans.get(i).getTime().substring(0, 2);
                linePoint2.add(new PointD(Double.valueOf(strHeng), Double.valueOf(listBeans.get(i).getGd_carbon_dioxide())));
            }

        }


        SplineData dataSeries2 = new SplineData("线二", linePoint2,
                Color.rgb(100, 137, 247));
        dataSeries2.getLinePaint().setStrokeWidth(8);

        dataSeries2.setDotStyle(XEnum.DotStyle.RING);
        dataSeries2.getDotLabelPaint().setColor(Color.RED);
        dataSeries2.getPlotLine().getPlotDot().setRingInnerColor(Color.rgb(123, 89, 168));

        //线2的数据集
//		List<PointD> linePoint3 = new ArrayList<PointD>();
//		linePoint3.add(new PointD(30d, 60d));
//		linePoint3.add(new PointD(45d, 65d));
//
//		linePoint3.add(new PointD(50d, 75d));
//		linePoint3.add(new PointD(65d, 95d));
//
//		SplineData dataSeries3 = new SplineData("线三",linePoint3,
//				Color.rgb(84, 206, 231) );
//
//		dataSeries3.setDotStyle(XEnum.DotStyle.RING);
//		dataSeries3.getDotPaint().setColor(Color.rgb(75, 166, 51));
//		dataSeries3.getPlotLine().getPlotDot().setRingInnerColor( Color.rgb(123, 89, 168) );
//

        //设定数据源
        chartData.add(dataSeries1);
        chartData.add(dataSeries2);
        //chartData.add(dataSeries3);
    }

    private void chartLabels() {
        int x = 0;
        if (laber.equals("1")) {
            x = 24;
        } else if (laber.equals("2")) {

            x = 30;
        } else if (laber.equals("3")) {
            x = 12;
        }
        for (int i = 0; i < x; i++) {
            labels.add(String.valueOf(i + 1));
        }

    }

    /**
     * 期望线/分界线
     */
    private void chartCustomeLines() {
        CustomLineData cdx1 = new CustomLineData("稍好", 30d, Color.rgb(35, 172, 57), 5);
        CustomLineData cdx2 = new CustomLineData("舒适", 40d, Color.rgb(69, 181, 248), 5);
        cdx1.setLabelVerticalAlign(XEnum.VerticalAlign.MIDDLE);
        mXCustomLineDataset.add(cdx1);
        mXCustomLineDataset.add(cdx2);


        CustomLineData cdy1 = new CustomLineData("定制线", 45d, Color.rgb(69, 181, 248), 5);
        cdy1.setLabelHorizontalPostion(Align.CENTER);
        mYCustomLineDataset.add(cdy1);
    }


    @Override
    public void render(Canvas canvas) {
        try {
            chart.render(canvas);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        super.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_UP) {
            triggerClick(event.getX(), event.getY());
        }
        return true;
    }


    //触发监听
    private void triggerClick(float x, float y) {
        if (!chart.getListenItemClickStatus()) return;

        PointPosition record = chart.getPositionRecord(x, y);
        if (null == record) return;

        if (record.getDataID() >= chartData.size()) return;
        SplineData lData = chartData.get(record.getDataID());
        List<PointD> linePoint = lData.getLineDataSet();
        int pos = record.getDataChildID();
        int i = 0;
        Iterator it = linePoint.iterator();
        while (it.hasNext()) {
            PointD entry = (PointD) it.next();

            if (pos == i) {
                Double xValue = entry.x;
                Double yValue = entry.y;

                float r = record.getRadius();
                chart.showFocusPointF(record.getPosition(), r + r * 0.8f);
                chart.getFocusPaint().setStyle(Style.FILL);
                chart.getFocusPaint().setStrokeWidth(3);
                if (record.getDataID() >= 2) {
                    chart.getFocusPaint().setColor(Color.BLUE);
                } else {
                    chart.getFocusPaint().setColor(Color.RED);
                }
                //在点击处显示tooltip
                mPaintTooltips.setColor(Color.RED);
                chart.getToolTip().setCurrentXY(x, y);
                chart.getToolTip().addToolTip(" Key:" + lData.getLineKey(), mPaintTooltips);
                chart.getToolTip().addToolTip(
                        " Current Value:" + Double.toString(xValue) + "," + Double.toString(yValue), mPaintTooltips);
                chart.getToolTip().getBackgroundPaint().setAlpha(100);
                this.invalidate();

                break;
            }
            i++;
        }//end while

    }

}
