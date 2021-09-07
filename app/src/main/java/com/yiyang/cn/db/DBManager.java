package com.yiyang.cn.db;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Author: hualw
 * Version: V1.0版本
 * Description:
 * Date: 2018/8/16
 * Email: 313312768@qq.com
 */
public class DBManager {
    private final static String dbName = "mfang_consumer.db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        daoMaster = new DaoMaster(openHelper.getWritableDatabase());
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，仅仅是一个接口
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = new DaoMaster(openHelper.getWritableDatabase());
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }


    /**
     * 打开输出日志，默认关闭
     */
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }


    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (openHelper != null) {
            openHelper.close();
            openHelper = null;
        }
    }

    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }
}