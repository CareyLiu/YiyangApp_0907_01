package com.yiyang.cn.view.wheelview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;


import com.yiyang.cn.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 日期时间选择控件 使用方法： private EditText inputDate;//需要设置的日期时间文本编辑框 private String
 * initDateTime="2012年9月3日 14:44",//初始日期时间值 在点击事件中使用：
 * inputDate.setOnClickListener(new OnClickListener() {
 *
 * @author
 * @Override public void onClick(View v) { DateTimePickDialogUtil
 * dateTimePicKDialog=new
 * DateTimePickDialogUtil(SinvestigateActivity.this,initDateTime);
 * dateTimePicKDialog.dateTimePicKDialog(inputDate);
 * <p>
 * } });
 */
public class DateTimePickDialogUtil implements OnDateChangedListener,
        OnTimeChangedListener {
    private static final String tag = DateTimePickDialogUtil.class.getSimpleName();

    private DatePicker datePicker;
    private TimePicker timePicker;
    private AlertDialog ad;
    private String dateTime;
    private String initDateTime;
    private Activity activity;
    private SimpleDateFormat defsdf;
    private Date date;

    /**
     * 日期时间弹出选择框构造函数
     *
     * @param activity     ：调用的父activity
     * @param initDateTime 初始日期时间值，作为弹出窗口的标题和日期时间初始值
     */
    RespCallback respCallback;

    public DateTimePickDialogUtil(Activity activity, String initDateTime, SimpleDateFormat sdf) {


        this.activity = activity;
        if (TextUtils.isEmpty(initDateTime)) {
            this.initDateTime = "设置日期时间";
        } else {
            this.initDateTime = initDateTime;
        }

        if (sdf != null) {
            this.defsdf = sdf;
        } else {
            this.defsdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        }
    }

    public void init(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();
//        if (!(null == initDateTime || "".equals(initDateTime))) {
//            calendar = this.getCalendarByInintData(initDateTime);
//        } else {
//            initDateTime = calendar.get(Calendar.YEAR) + "年"
//                    + calendar.get(Calendar.MONTH) + "月"
//                    + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
//                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
//                    + calendar.get(Calendar.MINUTE);
//        }
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

    /**
     * 弹出日期时间选择框方法
     *
     * @param inputDate :为需要设置的日期时间文本编辑框
     * @return
     */
    public AlertDialog dateTimePicKDialog(final TextView inputDate) {
        LinearLayout dateTimeLayout = (LinearLayout) activity
                .getLayoutInflater().inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
        init(datePicker, timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);


        ad = new AlertDialog.Builder(activity)
                .setTitle(initDateTime)
                .setView(dateTimeLayout)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Long l1 = date.getTime();
                        Long l2 = Long.valueOf(String.valueOf(System.currentTimeMillis()));
                        if (l1 > l2) {
                            Toast.makeText(activity, "选择的日期不正确，请重新选择。", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            inputDate.setText(dateTime);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inputDate.setText("");
                    }
                }).show();
        onDateChanged(null, 0, 0, 0);
        return ad;
    }


    public AlertDialog dateTimePicKDialog(final TextView inputDate, long maxDate) {
        LinearLayout dateTimeLayout = (LinearLayout) activity
                .getLayoutInflater().inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
        init(datePicker, timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);

        datePicker.setMaxDate(maxDate);


        ad = new AlertDialog.Builder(activity)
                .setTitle(initDateTime)
                .setView(dateTimeLayout)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inputDate.setText(dateTime);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inputDate.setText("");
                    }
                }).show();
        onDateChanged(null, 0, 0, 0);
        return ad;
    }

    public int dateDay;

    //添加最小的日期
    public String dateTimePicKDialog(final TextView inputDate, String minDate, long maxDate,
                                     final RespCallback respCallback) {
        LinearLayout dateTimeLayout = (LinearLayout) activity
                .getLayoutInflater().inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
        init(datePicker, timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);
        datePicker.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View view, MotionEvent motionEvent) {
                return false;
            }
        });




        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date minDateTime = null;
        try {
            minDateTime = df.parse(minDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(minDateTime);
        long timestamp = cal.getTimeInMillis();
        datePicker.setMaxDate(maxDate);
        datePicker.setMinDate(timestamp);//设置最小日期
        ad = new AlertDialog.Builder(activity)
                .setTitle(initDateTime)
                .setView(dateTimeLayout)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dateDay = datePicker.getMonth();
                        dateDay = dateDay + 1;
                        inputDate.setText(datePicker.getYear() + "年" + dateDay + "月");
                        if (respCallback != null) {
                            List<String> list = new ArrayList<String>();
                            list.add(datePicker.getYear() + "年" + dateDay + "月");
                            list.add(datePicker.getYear() + "-" + dateDay);
                            respCallback.onFinished(list);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
//                        inputDate.setText("");
                    }
                }).show();
        onDateChanged(null, 0, 0, 0);
        return datePicker.getYear() + "年" + dateDay + "月";
    }


    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        dateTime = defsdf.format(calendar.getTime());
        date = calendar.getTime();
//        ad.setTitle(dateTime);
    }


    /**
     * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
     *
     * @param initDateTime 初始日期时间值 字符串型
     * @return Calendar
     */
    private Calendar getCalendarByInintData(String initDateTime) {
        Calendar calendar = Calendar.getInstance();

        // 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
        String date = spliteString(initDateTime, "日", "index", "front"); // 日期
        String time = spliteString(initDateTime, "日", "index", "back"); // 时间

        String yearStr = spliteString(date, "年", "index", "front"); // 年份
        String monthAndDay = spliteString(date, "年", "index", "back"); // 月日

        String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
        String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日

        String hourStr = spliteString(time, ":", "index", "front"); // 时
        String minuteStr = spliteString(time, ":", "index", "back"); // 分

        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
        int currentHour = Integer.valueOf(hourStr.trim()).intValue();
        int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

        calendar.set(currentYear, currentMonth, currentDay, currentHour,
                currentMinute);
        return calendar;
    }

    /**
     * 截取子串
     *
     * @param srcStr      源串
     * @param pattern     匹配模式
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String spliteString(String srcStr, String pattern,
                                      String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        } else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }

}
