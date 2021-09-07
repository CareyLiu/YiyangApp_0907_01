package com.yiyang.cn.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelperConsumer extends SQLiteOpenHelper {
    // 数据库文件目标存放路径为系统默认位置，com.mfang.consumer 是你的包名
    public static String db_path = "/data/data/com.mfang.consumer/databases/";
    // 类没有实例化,是不能用作父类构造器的参数,必须声明为静态
    public static final String db_name = "mf_consume.db"; // 数据库名称
    public static final int version = 4; // 数据库版本

    public static final String table = "all_city";//城市表。
    public static final String table_0 = "id";//城市ID
    public static final String table_1 = "name";//城市名字
    public static final String table_2 = "pinyin";//城市全拼

    public static final String table1 = "recentcity";//最近访问的城市表
    public static final String table1_1 = "name";//城市名字
    public static final String table1_2 = "date";//时间

    public static final String table2 = "hot_city";//热门城市表
    public static final String table2_1 = "name";//城市名字
    public static final String table2_2 = "pinyin";//时间

    public static final String table3 = "article_search_history";//文章搜索记录表。
    public static final String table3_1 = "name";//文章搜索的关键字
    public static final String table3_2 = "date";//时间

    public static final String table4 = "convertible_city_search_history";//文章搜索记录表。
    public static final String table4_1 = "city";//文章搜索的关键字
    public static final String table4_2 = "address";
    public static final String table4_3 = "longitude";
    public static final String table4_4 = "latitude";
    public static final String table4_5 = "date";//时间

    public static final String table5 = "doudaren_history";

    private static String DB_NAME = "cities.db";
    private static String ASSETS_NAME = "cities.db";

    private Context context;

    /**
     * 如果数据库文件较大，使用FileSplit分割为小于1M的小文件 此例中分割为 hello.db.101 hello.db.102
     * hello.db.102
     */
    // 第一个文件名后缀
    public static final int ASSETS_SUFFIX_BEGIN = 101;
    // 最后一个文件名后缀
    public static final int ASSETS_SUFFIX_END = 102;


    public DBHelperConsumer(Context context) {
        super(context, db_name, null, version);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("info", "create table");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table + " (id integer primary key autoincrement, name varchar(100), pinyin varchar(100))");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table1 + " (id integer primary key autoincrement, name varchar(40), date INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table2 + " (id integer primary key autoincrement, name varchar(100), pinyin varchar(100))");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table3 + " (id integer primary key autoincrement, name varchar(40), date INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table4 + " (id integer primary key autoincrement, city varchar(40),address varchar(40),longitude varchar(40),latitude varchar(40), date INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table5 + " (id integer primary key autoincrement, name varchar(100))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if(oldVersion == 3){//如果版本是1.0的，升级下面的内容或修改
        onCreate(db);
//        }
    }

    public void createMfDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            // 数据库已存在，do nothing.
        } else {
            // 创建数据库
            File dir = new File(db_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File dbf = new File(db_path + db_name);
            if (dbf.exists()) {
                dbf.delete();
            }
            SQLiteDatabase.openOrCreateDatabase(dbf, null);
        }
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            // 数据库已存在，do nothing.
        } else {
            // 创建数据库
            try {
                File dir = new File(db_path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File dbf = new File(db_path + db_name);
                if (dbf.exists()) {
                    dbf.delete();
                }
               // SQLiteDatabase.openOrCreateDatabase(dbf, null);
                // 复制asseets中的db文件到DB_PATH下
                copyDataBase();
            } catch (IOException e) {
                throw new Error("数据库创建失败");
            }
        }
    }

    // 检查数据库是否有效
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        String myPath = db_path + db_name;
        try {
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // database does't exist yet.
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {
        // Open your local db as the input stream
        InputStream myInput = context.getAssets().open(ASSETS_NAME);//数据库名
        // Path to the just created empty db
        String outFileName = db_path + db_name;
        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        Log.e("数据库行走路线", "gogogogogo");
        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
            Log.e("数据库行走路线", "111111111111111111111");
        }
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    // 复制assets下的大数据库文件时用这个
    @SuppressWarnings("unused")
    private void copyBigDataBase() throws IOException {
        InputStream myInput;
        String outFileName = db_path + db_name;
        OutputStream myOutput = new FileOutputStream(outFileName);
        for (int i = ASSETS_SUFFIX_BEGIN; i < ASSETS_SUFFIX_END + 1; i++) {
            myInput = context.getAssets().open(ASSETS_NAME + "." + i);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myInput.close();
        }
        myOutput.close();
    }
}
