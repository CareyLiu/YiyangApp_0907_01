package com.yiyang.cn.model;

/**
 * Created by Sl on 2019/6/20.
 *
 */

public class HostModel {
    public static String ctsl;
    public static String jrs;
    public static String jqgl;
    public static String dfdy;
    public static String cgq;
    public static String dy;
    public static String version;
    public static void build(String message) {
        ctsl = message.substring(message.indexOf("i") + 1, message.indexOf("i") + 2);
        jrs = message.substring(message.indexOf("i") + 2, message.indexOf("i") + 3);
        jqgl = message.substring(message.indexOf("i") + 3, message.indexOf("i") + 4);
        dfdy = message.substring(message.indexOf("i") + 4, message.indexOf("i") + 5);
        cgq = message.substring(message.indexOf("i") + 5, message.indexOf("i") + 6);
        dy = message.substring(message.indexOf("i") + 6, message.indexOf("i") + 7);
//        version = message.substring(message.indexOf("i") + 7, message.indexOf("i") + 11);
    }
    public static String pack(String ver){
        return ctsl+jrs+jqgl+dfdy+cgq+dy+ver;
    }
}
