package com.yiyang.cn.view.wheelview;//package com.mfang.consumer.ui.activity.wheelview;
//
//import android.content.Context;
//import android.graphics.drawable.ColorDrawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.mfang.consumer.R;
//import com.mfang.consumer.app.App;
//import com.mfang.consumer.ui.activity.wheelview.adapter.ArrayWheelAdapter;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
///**
// * Created by Administrator on 2016/6/27.
// */
//public class DatePopupUtils extends PopupWindow {
//    private WheelView wvYear;//控件年
//    private WheelView wvMonth;//控件月
//    private WheelView wvDay;//控件日
//    private WheelView wvHour;//控件时
//    private WheelView wvMinute;//控件分
//    private WheelView wvSecond;//控件秒
//    private String tYear;
//    private View view = null;
//    private Context context;
//    private OnClickOk onClickOk;
//
//    public DatePopupUtils(Context context, int w, int h, String startData, String endData) {
//        super(context);
//
//        this.context = context;
//
//
//        this.setContentView(getDataPick(context));
//
//        this.setWidth(w);
//
//        this.setHeight(h);
//
//        this.setFocusable(true);
//
//        ColorDrawable dw = new ColorDrawable(0x00000000);
//
//        this.setBackgroundDrawable(dw);
//
//    }
//
//    public void setVisibleItems(int lines) {
//        wvYear.setVisibleItems(lines);
//    }
//
//    private View getDataPick(Context context, String startData, String endData) {
//        initControl();
//        //设置开始日期 和截至日期
//        getDate(7);
//
//        TextView bt = (TextView) view.findViewById(R.id.tv_ok);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (onClickOk != null) {
//                    tYear = dates[wvYear.getCurrentItem() + 1];
//                    onClickOk.onClickOk(tYear);
//                }
//                dismiss();
//            }
//        });
//        TextView cancel = (TextView) view.findViewById(R.id.tv_no);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//        return view;
//    }
//
//
//    public interface OnClickOk {
//        void onClickOk(String year);
//    }
//
//    public void setOnClickOk(OnClickOk onClickOk) {
//        this.onClickOk = onClickOk;
//    }
//
//    //初始化控件
//    private void initControl() {
//        view = LayoutInflater.from(context).inflate(R.layout.wheel_date_picker_all, null);
//        App.scaleScreenHelper.loadView((ViewGroup) view);
//        wvYear = (WheelView) view.findViewById(R.id.wv_year);
//        wvMonth = (WheelView) view.findViewById(R.id.wv_month);
//        wvDay = (WheelView) view.findViewById(R.id.wv_day);
//        wvHour = (WheelView) view.findViewById(R.id.wv_hour);
//        wvMinute = (WheelView) view.findViewById(R.id.wv_minute);
//        wvSecond = (WheelView) view.findViewById(R.id.wv_second);
//        wvYear.setCyclic(false);//是否可循环滑动
//        wvMonth.setCyclic(false);
//        wvDay.setCyclic(false);
//        wvHour.setCyclic(false);
//        wvMinute.setCyclic(false);
//        wvSecond.setCyclic(false);
//
////        year.addScrollingListener(scrollListener);
//        wvYear.setWheelBackground(R.color.background);
//        wvMonth.setWheelBackground(R.color.background);
//        wvDay.setWheelBackground(R.color.background);
//        wvHour.setWheelBackground(R.color.background);
//        wvMinute.setWheelBackground(R.color.background);
//        wvSecond.setWheelBackground(R.color.background);
//    }
//
//    String[] dates;
//    String[] datesCopy;
//
//    private void getDate(int count) {
//        dates = new String[count];
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar c = Calendar.getInstance();
//        int today = c.get(Calendar.DAY_OF_MONTH);
//        int month = c.get(Calendar.MONTH);
//        int year1 = c.get(Calendar.YEAR);
//        for (int i = 0; i < count; i++) {
//            int temp = c.get(Calendar.DAY_OF_MONTH) + i;
//            c.set(Calendar.DAY_OF_MONTH, temp);
//            dates[i] = format.format(c.getTime());
//            c.set(Calendar.DAY_OF_MONTH, today);
//            c.set(Calendar.MONTH, month);
//            c.set(Calendar.YEAR, year1);
//        }
//        datesCopy = new String[dates.length - 1];
//        try {
//            for (int i = 0; i < dates.length; i++) {
//                System.out.println(dates[i]);
//
//                if (i == dates.length - 1) {
//                    break;
//                }
//                datesCopy[i] = dates[i + 1];
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        ArrayWheelAdapter<String> dateAdapter = new ArrayWheelAdapter<String>(context, datesCopy);
//        wvYear.setViewAdapter(dateAdapter);
//    }
//
//
//}
