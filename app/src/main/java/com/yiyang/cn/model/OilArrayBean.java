package com.yiyang.cn.model;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.google.gson.annotations.SerializedName;

public class OilArrayBean extends SectionEntity {
    /**
     * oilNoType : 11
     * oilNoType_name : 90#
     * default : 0
     */

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(String isSelect) {
        this.isSelect = isSelect;
    }

    private String name;

    private String isSelect;


    public boolean isHeader;

    public String oil_type_name;
    public String oil_type;

    public OilArrayBean(boolean isHeader, String oil_type_name, String oil_type) {
        super(isHeader, oil_type_name);
        this.isHeader = isHeader;
        this.oil_type = oil_type;
        this.oil_type_name = oil_type_name;

    }

}

