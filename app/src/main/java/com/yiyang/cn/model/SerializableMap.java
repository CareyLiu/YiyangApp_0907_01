package com.yiyang.cn.model;

import java.io.Serializable;
import java.util.Map;

/**
 * 序列化map供Bundle传递map使用
 */
public class SerializableMap implements Serializable {

    private Map<String,String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}