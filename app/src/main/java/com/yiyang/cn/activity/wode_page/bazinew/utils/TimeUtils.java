package com.yiyang.cn.activity.wode_page.bazinew.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    /**
     * 获取年 * @return
     */
    public static int getYear() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.YEAR);
    }

    /**
     * 获取月 * @return
     */
    public static int getMonth() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日 * @return
     */
    public static int getDay() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.DATE);
    }

    /**
     * 获取时 * @return
     */
    public static int getHour() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.HOUR);
    }

    /**
     * 获取分 * @return
     */
    public static int getMinute() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.MINUTE);

    }


    /**
     * 获取当前时间的时间戳 * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentTime() {
        return getFormatedDateTime(System.currentTimeMillis());
    }

    /**
     * 将long转换为日期（yyyy-MM-dd HH:mm）
     *
     * @param dateTime
     * @return 到分
     */
    @SuppressLint("SimpleDateFormat")
    public static String getFormatedDateTime(long dateTime) {
        String time = "";
        try {
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            time = sDateFormat.format(new Date(dateTime + 0));
        } catch (Exception e) {

        }
        return time;
    }

    /**
     * 取得当月天数
     */
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    /**
     * 得到指定月的天数
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    /**
     * 指定的日期
     */
    public static final String getData(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String time = formatter.format(date);//格式化数据，取当前时间结果为 2014-10-30
        return time;
    }

    /**
     * 指定的日期
     */
    public static final String getData(Date date, String wei) {
        SimpleDateFormat formatter = new SimpleDateFormat(wei);
        String time = formatter.format(date);//格式化数据，取当前时间结果为 2014-10-30
        return time;
    }

    /**
     * 日期转Data
     */
    public static final Date getData(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date time = formatter.parse(date);//格式化数据，取当前时间结果为 2014-10-30
        return time;
    }

    /**
     * 日期转Data
     */
    public static final Date getData(String date, String wei) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(wei);
        Date time = formatter.parse(date);//格式化数据，取当前时间结果为 2014-10-30
        return time;
    }
}
