package com.yiyang.cn.view.wheelview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.yiyang.cn.R;
import com.yiyang.cn.view.wheelview.adapter.ArrayWheelAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2016/6/27.
 */
public class DatePopup extends PopupWindow {
    private WheelView year;
    private WheelView month;
    private WheelView day;
    private final int endYear = 2100;
    private final int startYear = 1950;
    private int mMonth = 0;
    private int mDay = 1;

    private String tYear;
    private String tMonth;
    private String tDay;

    private View view = null;

    private Context context;

    private OnClickOk onClickOk;

    public DatePopup(Context context, int w, int h, int x, int y) {
        super(context);

        this.context = context;

        this.setContentView(getDataPick(context, x, y));

        this.setWidth(w);

        this.setHeight(h);

        this.setFocusable(true);

        ColorDrawable dw = new ColorDrawable(0x00000000);

        this.setBackgroundDrawable(dw);

    }

    public void setVisibleItems(int lines) {
        year.setVisibleItems(lines);
    }

    private View getDataPick(Context context, int x, int y) {
        view = LayoutInflater.from(context).inflate(R.layout.wheel_date_picker_jz, null);
//        App.scaleScreenHelper.loadView((ViewGroup) view);
        year = (WheelView) view.findViewById(R.id.year);
        year.setCyclic(false);//是否可循环滑动
//        year.addScrollingListener(scrollListener);
        year.setWheelBackground(R.color.background);

        if (y == 1) {
            getDate1(x);
            year.setVisibleItems(datesCopy.length);//设置显示行数
        } else {
            getDate(x);
        }

        TextView bt = (TextView) view.findViewById(R.id.tv_ok);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onClickOk != null) {
                    tYear = dates[year.getCurrentItem() + 1];
                    onClickOk.onClickOk(tYear);
                }
                dismiss();
            }
        });
        TextView cancel = (TextView) view.findViewById(R.id.tv_no);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

//    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
//        @Override
//        public void onScrollingStarted(WheelView wheel) {
//        }
//
//        @Override
//        public void onScrollingFinished(WheelView wheel) {
//
//        }
//    };

    public interface OnClickOk {
        void onClickOk(String year);
    }

    public void setOnClickOk(OnClickOk onClickOk) {
        this.onClickOk = onClickOk;
    }

    String[] dates;
    String[] datesCopy;

    private void getDate(int count) {
        dates = new String[count];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int today = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year1 = c.get(Calendar.YEAR);
        for (int i = 0; i < count; i++) {
            int temp = c.get(Calendar.DAY_OF_MONTH) + i;
            c.set(Calendar.DAY_OF_MONTH, temp);
            dates[i] = format.format(c.getTime());
            c.set(Calendar.DAY_OF_MONTH, today);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.YEAR, year1);
        }
        datesCopy = new String[dates.length - 1];
        try {
            for (int i = 0; i < dates.length; i++) {
                System.out.println(dates[i]);

                if (i == dates.length - 1) {
                    break;
                }
                datesCopy[i] = dates[i + 1];

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        ArrayWheelAdapter<String> dateAdapter = new ArrayWheelAdapter<String>(context, datesCopy);
        year.setViewAdapter(dateAdapter);

    }

    private void getDate1(int count) {
        dates = new String[count];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        int beginToday = 2;
        int beginMonth = 7;
        int beginYear1 = 2017;
        c.set(Calendar.DAY_OF_MONTH, beginToday);
        c.set(Calendar.MONTH, beginMonth);
        c.set(Calendar.YEAR, beginYear1);

        int today = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year1 = c.get(Calendar.YEAR);

        int nowToday = c1.get(Calendar.DAY_OF_MONTH);
        int nowMonth = c1.get(Calendar.MONTH);
        int nowYear1 = c1.get(Calendar.YEAR);

        Log.i("nowToday", nowToday + "");
        Log.i("nowMonth", nowMonth + "");
        Log.i("nowYear1", nowYear1 + "");

        Log.i("c1", format.format(c1.getTime()));

        for (int i = 0; i < count; i++) {

            int temp = c.get(Calendar.DAY_OF_MONTH) + i;
            c.set(Calendar.DAY_OF_MONTH, temp);
            dates[i] = format.format(c.getTime());

            if (format.format(c.getTime()).equals(format.format(c1.getTime()))) {
                break;
            }
            c.set(Calendar.DAY_OF_MONTH, today);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.YEAR, year1);
            Log.i("DAY_OF_MONTH", c.get(Calendar.DAY_OF_MONTH) + "");

        }
        datesCopy = new String[dates.length - 1];
        try {
            for (int i = 0; i < dates.length; i++) {
                System.out.println(dates[i]);


                if (i == dates.length - 1) {
                    break;
                }
                if (dates[i + 1] == null) {
                    break;
                } else {
                    datesCopy[i] = dates[i + 1];
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        ArrayWheelAdapter<String> dateAdapter = new ArrayWheelAdapter<String>(context, datesCopy);
        year.setViewAdapter(dateAdapter);

    }

}
