package com.yiyang.cn.db.table;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Author: hualw
 * Version: V1.0版本
 * Description:
 * Date: 2018/8/16
 * Email: 313312768@qq.com
 */

/*表名 */
@Entity(nameInDb = "history_record_table")
public class HistoryRecord {
    @Id(autoincrement = true)
    private Long _id;   //自增id
    private String name;  //搜索的关键字
    private Long date;  //搜索的时间
    private int type;   //区分 搜索类型   1逗达人搜索

    @Generated(hash = 2127562009)
    public HistoryRecord(Long _id, String name, Long date, int type) {
        this._id = _id;
        this.name = name;
        this.date = date;
        this.type = type;
    }

    @Generated(hash = 725453896)
    public HistoryRecord() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDate() {
        return this.date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
