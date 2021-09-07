package com.yiyang.cn.model;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

public  class AlarmListBean extends SectionEntity {
    /**
     * alarm_date : 今天
     * alerm_time_list : [{"alerm_time":"上午 09:32:00","device_state_name":"发生报警","device_state":"2"},{"alerm_time":"上午 09:31:42","device_state_name":"发生报警","device_state":"1"}]
     */
    public AlarmListBean(boolean isHeader, String header) {
        super(isHeader, header);
    }
    public String alarm_date;
    public String alerm_time;
    public String device_state_name;
    public String device_state;

    public String is_more;
    public String sel_alarm_date;


}