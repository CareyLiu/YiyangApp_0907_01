package com.yiyang.cn.tools;

import android.util.Log;

import com.yiyang.cn.app.AppConfig;


public class LogUtil {
    private static final int LOG_LEVEL = 6;
    public static final String TAG = "SMARTHOME";
    private static final int VERBOSE = 5;
    private static final int DEBUG = 4;
    private static final int INFO = 3;
    private static final int WARN = 2;
    private static final int ERROR = 1;

    public static void v(String tag, String msg) {
        if (LOG_LEVEL > VERBOSE) {
//            Log.v(tag, msg);
            if (tag == null || tag.length() == 0
                    || msg == null || msg.length() == 0)
                return;
            int segmentSize = 3 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.v(tag, msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.v(tag, logContent);
                }
                Log.v(tag, msg);// 打印剩余日志
            }
        }
    }

    public static void d(String tag, String msg) {
        if (LOG_LEVEL > DEBUG) {
//            Log.d(tag, msg);
            if (tag == null || tag.length() == 0
                    || msg == null || msg.length() == 0)
                return;
            int segmentSize = 3 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.d(tag, msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.d(tag, logContent);
                }
                Log.d(tag, msg);// 打印剩余日志
            }
        }
    }

    public static void i(String tag, String msg) {
        if (LOG_LEVEL > INFO) {
//            Log.i(tag, msg);
            if (tag == null || tag.length() == 0
                    || msg == null || msg.length() == 0)
                return;
            int segmentSize = 3 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.i(tag, msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.i(tag, logContent);
                }
                Log.i(tag, msg);// 打印剩余日志
            }
        }
    }

    public static void w(String tag, String msg) {
        if (LOG_LEVEL > WARN) {
//            Log.w(tag, msg);
            if (tag == null || tag.length() == 0
                    || msg == null || msg.length() == 0)
                return;
            int segmentSize = 3 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.w(tag, msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.w(tag, logContent);
                }
                Log.w(tag, msg);// 打印剩余日志
            }
        }
    }

    public static void e(String tag, String msg) {
        if (LOG_LEVEL > ERROR) {
//            Log.e(tag, msg);
            if (tag == null || tag.length() == 0
                    || msg == null || msg.length() == 0)
                return;
            int segmentSize = 3 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.e(tag, msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.e(tag, logContent);
                }
                Log.e(tag, msg);// 打印剩余日志
            }
        }
    }

    public static void v(String msg) {
        v(TAG, "-->>" + msg);
    }

    public static void d(String msg) {
        d(TAG, "-->>" + msg);
    }

    public static void i(String msg) {
        i(TAG, "-->>" + msg);
    }

    public static void w(String msg) {
        w(TAG, "-->>" + msg);
    }

    public static void e(String msg) {
        e(TAG, "-->>" + msg);
    }

    public static void print(String msg) {
        if (true)
            System.out.println("-->>" + msg);
    }
}
