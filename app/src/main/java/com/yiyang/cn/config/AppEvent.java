package com.yiyang.cn.config;

import java.util.Observable;

/**
 * Created by Administrator
 *
 * 利用观察者 传值
 *
 */
public class AppEvent extends Observable {

    private static AppEvent classEvent;


    public static AppEvent getClassEvent(){
        if(classEvent ==null){
            classEvent = new AppEvent();
        }
        return classEvent;
    }

    public static void setMessage(Object msg){
        if(classEvent ==null){
            classEvent = new AppEvent();
        }
        classEvent.setChanged();
        classEvent.notifyObservers(msg);
    }

}
