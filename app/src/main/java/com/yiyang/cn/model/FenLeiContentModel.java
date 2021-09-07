package com.yiyang.cn.model;

import com.chad.library.adapter.base.entity.SectionEntity;


public class FenLeiContentModel extends SectionEntity {
    public boolean isHeader;
    public String header;


    public String one_item;
    public String two_item;
    public String three_item;

    public String ccid;
    public String serverId;

    public String is_product;
    public String item_id_up;
    public String order_flag;
    public String item_detail;
    public String img_detail_url;
    public String item_id;
    public String img_url;
    public String item_name;
    public String category;
    public String type;

    public FenLeiContentModel(boolean isHeader, String header) {
        super(isHeader, header);
        this.header = header;
        this.isHeader = isHeader;
    }


    /**
     * item_id : 97
     * item_name : 长袖连衣裙
     * img_url : https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=4732
     */



    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}

