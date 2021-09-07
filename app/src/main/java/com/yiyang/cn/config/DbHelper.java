package com.yiyang.cn.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private Context context;

    // 表名
    public static final String TABLE_ADDRESS_DICT = "address_dict";//地址库字典表
    //字段名
    public static final String FIELD_ID = "id"; //公用的id
    //地址库字典表的字段名
    public static final String ADDRESS_DICT_FIELD_PARENTID = "parentId";//父id，自关联id主键
    public static final String ADDRESS_DICT_FIELD_ID = "id";
    public static final String ADDRESS_DICT_FIELD_CODE = "code";//地址编号
    public static final String ADDRESS_DICT_FIELD_NAME = "name";//中文名

    //创建地址库字典表sql语句
    public static final String CREATE_ADDRESS_DICT_SQL = "create table " + TABLE_ADDRESS_DICT + "("+ ADDRESS_DICT_FIELD_ID + " integer not null," +  ADDRESS_DICT_FIELD_PARENTID + " integer not null,"+ ADDRESS_DICT_FIELD_CODE + " text," + ADDRESS_DICT_FIELD_NAME + " text)";


    private  static final String dbName = "address.db";
    public DbHelper(Context context){
        super(context,dbName,null,1);
        this.context = context;
    }


    //创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ADDRESS_DICT_SQL);
    }
    //删除数据库
    public void deleteDb(){
        context.deleteDatabase(dbName);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
