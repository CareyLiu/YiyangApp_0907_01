package com.yiyang.cn.model;


import com.yiyang.cn.adapter.ExpandableRecyclerAdapter;
import com.yiyang.cn.util.Util;

import static com.yiyang.cn.adapter.BrandExpandAdapter.TYPE_PERSON;
import static com.yiyang.cn.adapter.ExpandableRecyclerAdapter.TYPE_HEADER;

public class BrandItem extends ExpandableRecyclerAdapter.ListItem implements Comparable {

    public String first;
    public String name;
    public String id;
    public Object Avatar;
    private char headLetter;

    public char getHeadLetter() {
        return headLetter;
    }

    public void setHeadLetter(char headLetter) {
        this.headLetter = headLetter;
    }


    public BrandItem(String first) {
        super(TYPE_HEADER);
        this.first = first;
    }

    public BrandItem(String name, Object Avatar, String id) {
        super(TYPE_PERSON);
        this.name = name ;
        this.Avatar = Avatar;
        this.id = id;
        headLetter = Util.getHeadChar(name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        BrandItem that = (BrandItem) object;
        return name.equals(that.name);
    }

    @Override
    public int compareTo(Object object) {
        if (object instanceof BrandItem) {
            BrandItem that = (BrandItem) object;
            if (getHeadLetter() == ' ') {
                if (that.getHeadLetter() == ' ') {
                    return 0;
                }
                return -1;
            }
            if (that.getHeadLetter() == ' ') {
                return 1;
            } else if (that.getHeadLetter() > getHeadLetter()) {
                return -1;
            } else if (that.getHeadLetter() == getHeadLetter()) {
                return 0;
            }
            return 1;
        } else {
            throw new ClassCastException();
        }
    }
}
