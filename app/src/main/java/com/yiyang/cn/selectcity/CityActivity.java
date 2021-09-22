package com.yiyang.cn.selectcity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.yiyang.cn.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;


public class CityActivity extends Activity implements OnScrollListener {//, ICityView
    public final static String tag = CityActivity.class.getSimpleName();
    @BindView(R.id.layout)
    CoordinatorLayout layout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private BaseAdapter adapter;
    private ResultListAdapter resultListAdapter;
    private ClearEditText sh;
    private ListView personList;
    private ListView resultList;
    private TextView tv_noresult;
    private TextView overlay; // 对话框首字母textview
    private MyLetterListView letterListView; // A-Z listview
    private HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
    private String[] sections;// 存放存在的汉语拼音首字母
    private Handler handler;
    private OverlayThread overlayThread; // 显示首字母对话框
    private ArrayList<CityBean> allCity_lists; // 所有城市列表
    private ArrayList<CityBean> city_lists;// 城市列表
    private ArrayList<CityBean> city_hot;
    private ArrayList<CityBean> city_result;
    private ArrayList<String> city_history;
    private DBHelperConsumer helper;
    // 定位相关

    private String currentCity; // 用于保存定位到的城市
    private String address = ""; // 用于保存定位到的地址
    private Double Longitude = 0.0;//经度坐标
    private Double Latitude = 0.0;//纬度坐标
    private int locateProcess = -1; // 记录当前定位的状态 1,正在定位-2,定位成功-3,定位失败

    private static String source = "-1";// -1 默认进入，1从选择城市页面进入，2 返回城市名
    public String locationCity;

//    private CityPresenter presenter;

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CityActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (context instanceof SelectCityActivity) {
//            source = "1";
//        } else {
//            source = "-1";
//        }
        context.startActivity(intent);
    }


    private boolean flag;//通过左上角有没有存储判断  是否开启整个app  如果有存储证明不是首次进入 所以就不用开启bootoom 如果没有要开启

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        if (getIntent().getStringExtra("source") != null) {
            source = getIntent().getStringExtra("source");
        }


        allCity_lists = new ArrayList<>();
        city_hot = new ArrayList<>();
        city_result = new ArrayList<>();
        city_history = new ArrayList<>();
//        presenter = new CityPresenterImpl(this);
        initialize();
        helper = new DBHelperConsumer(this);
        sh.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString() == null || "".equals(s.toString())) {
                    letterListView.setVisibility(View.VISIBLE);
                    personList.setVisibility(View.VISIBLE);
                    resultList.setVisibility(View.GONE);
                    tv_noresult.setVisibility(View.GONE);
                } else {
                    city_result.clear();
                    letterListView.setVisibility(View.GONE);
                    personList.setVisibility(View.GONE);
                    getResultCityList(s.toString());
                    if (city_result.size() <= 0) {
                        tv_noresult.setVisibility(View.VISIBLE);
                        resultList.setVisibility(View.GONE);
                    } else {
                        tv_noresult.setVisibility(View.GONE);
                        resultList.setVisibility(View.VISIBLE);
                        resultListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        letterListView = (MyLetterListView) findViewById(R.id.MyLetterListView01);
        letterListView.setOnTouchingLetterChangedListener(new LetterListViewListener());
        alphaIndexer = new HashMap<>();
        handler = new Handler();
        overlayThread = new OverlayThread();

        personList.setAdapter(adapter);
        personList.setOnScrollListener(this);
        resultListAdapter = new ResultListAdapter(this, city_result);
        resultList.setAdapter(resultListAdapter);
        resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {//

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InsertCity(city_result.get(position).getName());
            }
        });
        cityInit();
        hotCityInit();
        hisCityInit();
        setAdapter(allCity_lists, city_hot, city_history);
        initOverlay();
    }

    private void initialize() {
        sh = (ClearEditText) findViewById(R.id.sh);
        personList = (ListView) findViewById(R.id.list_view);
        resultList = (ListView) findViewById(R.id.search_result);
        tv_noresult = (TextView) findViewById(R.id.tv_noresult);
        letterListView = (MyLetterListView) findViewById(R.id.MyLetterListView01);
    }

    public void InsertCity(String name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DBHelperConsumer.table1 + " where name = '"
                + name + "'", null);
        if (cursor.getCount() > 0) { //
            db.delete(DBHelperConsumer.table1, "name = ?", new String[]{name});
        }
        db.execSQL("insert into " + DBHelperConsumer.table1 + "(name, date) values('" + name + "', " + System.currentTimeMillis() + ")");
        db.close();
    }

    private void getResultCityList(String keyword) {
        DBHelperConsumer dbHelper = new DBHelperConsumer(this);
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery(
                    "select * from city where name like \"%" + keyword
                            + "%\" or pinyin like \"%" + keyword + "%\"", null);
            CityBean city;
            Log.e("info", "length = " + cursor.getCount());
            while (cursor.moveToNext()) {
                city = new CityBean(cursor.getString(1), cursor.getString(2));
                city_result.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(city_result, comparator);
    }


//    private void getResultCityList(String keyword) {
//        DBHelperConsumer dbHelper = new DBHelperConsumer(this);
//        try {
//            dbHelper.createDataBase();
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//            Cursor cursor = db.rawQuery(
//                    "select * from " + DBHelperConsumer.table + " where name like \"%" + keyword
//                            + "%\" or pinyin like \"%" + keyword + "%\"", null);
//            CityBean city;
//            Log.e("info", "length = " + cursor.getCount());
//            while (cursor.moveToNext()) {
//                city = new CityBean(cursor.getString(1), cursor.getString(2));
//                city_result.add(city);
//            }
//            cursor.close();
//            db.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Collections.sort(city_result, comparator);
//    }


    private void cityInit() {
        CityBean city = new CityBean("定位", "0"); // 当前定位城市
        allCity_lists.add(city);
        city = new CityBean("最近", "1"); // 最近访问的城市
        allCity_lists.add(city);
        city = new CityBean("热门", "2"); // 热门城市
        allCity_lists.add(city);
        city_hot = getHotCityList();
        city = new CityBean("全部", "3"); // 全部城市
        allCity_lists.add(city);
        city_lists = getCityList();//getAllCityList(); // getCityList();
        allCity_lists.addAll(city_lists);
    }

    /**
     * 热门城市
     * 南京，杭州，成都，无锡，长沙市，郑州，北京，上海，沈阳，哈尔滨，广州，武汉
     */
    public void hotCityInit() {
        CityBean city = new CityBean("南京市", "2");
        city_hot.add(city);
        city = new CityBean("杭州市", "2");
        city_hot.add(city);
        city = new CityBean("成都市", "2");
        city_hot.add(city);
        city = new CityBean("无锡市", "2");
        city_hot.add(city);
        city = new CityBean("长沙市", "2");
        city_hot.add(city);
        city = new CityBean("郑州市", "2");
        city_hot.add(city);
        city = new CityBean("北京市", "2");
        city_hot.add(city);
        city = new CityBean("上海市", "2");
        city_hot.add(city);
        city = new CityBean("沈阳市", "2");
        city_hot.add(city);
        city = new CityBean("哈尔滨市", "2");
        city_hot.add(city);
        city = new CityBean("广州市", "2");
        city_hot.add(city);
        city = new CityBean("武汉市", "2");
        city_hot.add(city);
    }

    private void hisCityInit() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from " + DBHelperConsumer.table1 + " order by date desc limit 0, 3", null);
        while (cursor.moveToNext()) {
            city_history.add(cursor.getString(1));
        }
        cursor.close();
        db.close();
    }

    @SuppressWarnings("unchecked")
    private ArrayList<CityBean> getCityList() {
        DBHelperConsumer dbHelper = new DBHelperConsumer(this);
        ArrayList<CityBean> list = new ArrayList<>();
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from city", null);
            CityBean city;
            while (cursor.moveToNext()) {
                city = new CityBean(cursor.getString(1), cursor.getString(2));
                list.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(list, comparator);
        return list;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<CityBean> getAllCityList() {
        ArrayList<CityBean> list = new ArrayList<>();
        try {
            helper.createDataBase();
            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from " + DBHelperConsumer.table + "", null);
            CityBean city;
            while (cursor.moveToNext()) {
                city = new CityBean(cursor.getString(1), cursor.getString(2));
                list.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(list, comparator);
        return list;
    }

    //获取总数
    public long getCount(String table) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from " + table + "", null);
        cursor.moveToFirst();
        return cursor.getLong(0);
    }

    @SuppressWarnings("unchecked")
    private ArrayList<CityBean> getHotCityList() {
        ArrayList<CityBean> list = new ArrayList<>();
        try {
            helper.createDataBase();
            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from " + DBHelperConsumer.table2 + "", null);
            CityBean city;
            while (cursor.moveToNext()) {
                city = new CityBean(cursor.getString(1), cursor.getString(2));
                list.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * a-z排序
     */
    @SuppressWarnings("rawtypes")
    Comparator comparator = new Comparator<CityBean>() {
        @Override
        public int compare(CityBean lhs, CityBean rhs) {
            String a = lhs.getPinyi().substring(0, 1);
            String b = rhs.getPinyi().substring(0, 1);
            int flag = a.compareTo(b);
            if (flag == 0) {
                return a.compareTo(b);
            } else {
                return flag;
            }
        }
    };

    private void setAdapter(List<CityBean> list, List<CityBean> hotList,
                            List<String> hisCity) {
        adapter = new ListAdapter(this, list, hotList, hisCity);
        personList.setAdapter(adapter);
    }


    private class ResultListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<CityBean> results = new ArrayList<CityBean>();

        public ResultListAdapter(Context context, ArrayList<CityBean> results) {
            inflater = LayoutInflater.from(context);
            this.results = results;
        }

        @Override
        public int getCount() {
            return results.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_city_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) convertView
                        .findViewById(R.id.name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText(results.get(position).getName());
            return convertView;
        }

        class ViewHolder {
            TextView name;
        }
    }

    public class ListAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<CityBean> list;
        private List<CityBean> hotList;
        private List<String> hisCity;
        final int VIEW_TYPE = 5;

        public ListAdapter(Context context, List<CityBean> list,
                           List<CityBean> hotList, List<String> hisCity) {
            this.inflater = LayoutInflater.from(context);
            this.list = list;
            this.context = context;
            this.hotList = hotList;
            this.hisCity = hisCity;
            alphaIndexer = new HashMap<String, Integer>();
            sections = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                // 当前汉语拼音首字母
                String currentStr = getAlpha(list.get(i).getPinyi());
                // 上一个汉语拼音首字母，如果不存在为" "
                String previewStr = (i - 1) >= 0 ? getAlpha(list.get(i - 1).getPinyi()) : " ";
                if (!previewStr.equals(currentStr)) {
                    String name = getAlpha(list.get(i).getPinyi());
                    alphaIndexer.put(name, i);
                    sections[i] = name;
                }
            }
        }


        @Override
        public int getViewTypeCount() {
            return VIEW_TYPE;
        }

        @Override
        public int getItemViewType(int position) {
            return position < 4 ? position : 4;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        ViewHolder holder;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final TextView city;
            int viewType = getItemViewType(position);
            if (viewType == 0) { // 定位
                convertView = inflater.inflate(R.layout.activity_city_frist_list_item, null);
                TextView locateHint = (TextView) convertView.findViewById(R.id.locateHint);
                city = (TextView) convertView.findViewById(R.id.lng_city);
                locationCity = city.getText().toString();
                city.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (locateProcess == 2) {
                            InsertCity(city.getText().toString());
                            savejump(String.valueOf(city.getText()));
                        } else {
                            locateProcess = 1;
                            personList.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            currentCity = "";

                        }
                    }
                });
                ProgressBar pbLocate = (ProgressBar) convertView.findViewById(R.id.pbLocate);


                if (locateProcess == 1) { // 正在定位
                    locateHint.setText("正在定位");
                    city.setVisibility(View.GONE);
                    pbLocate.setVisibility(View.VISIBLE);
                } else if (locateProcess == 2) { // 定位成功
                    locateHint.setText("当前定位城市");
                    city.setVisibility(View.VISIBLE);
                    city.setText(currentCity);
                    pbLocate.setVisibility(View.GONE);
                } else if (locateProcess == 3) {
                    locateHint.setText("未定位到城市,请选择");
                    city.setVisibility(View.VISIBLE);
                    city.setText("重新定位");
                    pbLocate.setVisibility(View.GONE);
                }
            } else if (viewType == 1) { // 最近访问城市
                convertView = inflater.inflate(R.layout.activity_city_recent_city, null);
                GridView rencentCity = (GridView) convertView.findViewById(R.id.recent_city);
                rencentCity.setAdapter(new HitCityAdapter(context, this.hisCity));
                rencentCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        savejump(city_history.get(position));
                    }
                });
                TextView recentHint = (TextView) convertView.findViewById(R.id.recentHint);
                recentHint.setText("最近访问的城市");
                if (hisCity.size() <= 0) {
                    TextView norencent = (TextView) convertView.findViewById(R.id.norecentHint);
                    norencent.setVisibility(View.VISIBLE);
                    norencent.setText("暂无最近选择城市");
                    rencentCity.setVisibility(View.GONE);
                } else {
                    rencentCity.setVisibility(View.VISIBLE);
                }

            } else if (viewType == 2) {
                convertView = inflater.inflate(R.layout.activity_city_recent_city, null);
                GridView hotCity = (GridView) convertView.findViewById(R.id.recent_city);
                hotCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        InsertCity(city_hot.get(position).getName());
                        savejump(city_hot.get(position).getName());
                    }
                });
                hotCity.setAdapter(new HotCityAdapter(context, this.hotList));
                TextView hotHint = (TextView) convertView.findViewById(R.id.recentHint);
                hotHint.setText("热门城市");
                if (hotList.size() <= 0) {
                    TextView norencent = (TextView) convertView.findViewById(R.id.norecentHint);
                    norencent.setVisibility(View.VISIBLE);
                    norencent.setText("暂无热门城市");
                    hotCity.setVisibility(View.GONE);
                } else {
                    hotCity.setVisibility(View.VISIBLE);
                }
            } else if (viewType == 3) {
                convertView = inflater.inflate(R.layout.activity_city_total_item, null);
            } else {
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.activity_city_list_item, null);
                    holder = new ViewHolder();
                    holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
                    holder.ll_name_item = (LinearLayout) convertView.findViewById(R.id.ll_name_item);
                    holder.name = (TextView) convertView.findViewById(R.id.name);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                if (position >= 1) {
                    holder.name.setText(list.get(position).getName());
                    String currentStr = getAlpha(list.get(position).getPinyi());
                    String previewStr = (position - 1) >= 0 ? getAlpha(list.get(position - 1).getPinyi()) : " ";
                    if (!previewStr.equals(currentStr)) {
                        holder.alpha.setVisibility(View.VISIBLE);
                        holder.alpha.setText(currentStr);
                    } else {
                        holder.alpha.setVisibility(View.GONE);
                    }
                }

                if (position >= 4) {
                    holder.ll_name_item.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            InsertCity(list.get(position).getName());
                            savejump(list.get(position).getName());
                        }
                    });
                }
            }
            return convertView;
        }

        private class ViewHolder {
            TextView alpha; // 首字母标题
            LinearLayout ll_name_item;//城市名外布局
            TextView name; // 城市名字
        }
    }


    class HotCityAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<CityBean> hotCitys;

        public HotCityAdapter(Context context, List<CityBean> hotCitys) {
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            this.hotCitys = hotCitys;
        }

        @Override
        public int getCount() {
            return hotCitys.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.activity_city_item_city, null);
            TextView city = (TextView) convertView.findViewById(R.id.city);
            city.setText(hotCitys.get(position).getName());
            return convertView;
        }
    }

    class HitCityAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<String> hotCitys;

        public HitCityAdapter(Context context, List<String> hotCitys) {
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            this.hotCitys = hotCitys;
        }

        @Override
        public int getCount() {
            return hotCitys.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.activity_city_item_city, null);
            TextView city = (TextView) convertView.findViewById(R.id.city);
            city.setText(hotCitys.get(position));
            return convertView;
        }
    }

    private boolean mReady;

    // 初始化汉语拼音首字母弹出提示框
    private void initOverlay() {
        mReady = true;
        LayoutInflater inflater = LayoutInflater.from(this);
        overlay = (TextView) inflater.inflate(R.layout.activity_city_overlay, null);
        overlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        try {
            windowManager.addView(overlay, lp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean isScroll = false;

    private class LetterListViewListener implements
            MyLetterListView.OnTouchingLetterChangedListener {

        @Override
        public void onTouchingLetterChanged(final String s) {
            isScroll = false;
            if (alphaIndexer.get(s) != null) {
                int position = alphaIndexer.get(s);
                personList.setSelection(position);
                overlay.setText(s);
                overlay.setVisibility(View.VISIBLE);
                handler.removeCallbacks(overlayThread);
                // 延迟一秒后执行，让overlay为不可见
                handler.postDelayed(overlayThread, 1000);
            }
        }
    }

    // 设置overlay不可见
    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            overlay.setVisibility(View.GONE);
        }
    }

    // 获得汉语拼音首字母
    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals("0")) {
            return "定位";
        } else if (str.equals("1")) {
            return "最近";
        } else if (str.equals("2")) {
            return "热门";
        } else if (str.equals("3")) {
            return "全部";
        } else {
            return "#";
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL
                || scrollState == SCROLL_STATE_FLING) {
            isScroll = true;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (!isScroll) {
            return;
        }
     /*   if (mReady) {
            String text;
            String name = allCity_lists.get(firstVisibleItem).getName();
            String pinyin = allCity_lists.get(firstVisibleItem).getPinyi();
            if (firstVisibleItem < 4) {
                text = name;
            } else {
                text = PingYinUtil.converterToFirstSpell(pinyin).substring(0, 1).toUpperCase();
            }
            overlay.setText(text);
            overlay.setVisibility(View.VISIBLE);
            handler.removeCallbacks(overlayThread);
            // 延迟一秒后执行，让overlay为不可见
            handler.postDelayed(overlayThread, 1000);
        }*/
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    @Override
    protected void onStart() {
        super.onStart();
        // -----------location config ------------

//        try {
//            presenter.checkCity();
//        } catch (AppException e) {
//            e.printStackTrace();
//        }
//        if (getCount(DBHelperConsumer.table) > 0) {
//            init();
//        }
    }


//    @Override
//    public Activity getActivity() {
//        return this;
//    }

//    @Override
//    public void cityList(AreaCityListDtoOut out) {
//        Utils.logE("city----", new Gson().toJson(out));
//        InsertAllCity(out.areaCityDtoOutLists);
//        InsertHotCity(out.areaCityHotDtoOuts);
//        init();
//    }
//
//    @Override
//    public void checkCityVersion(CheckAreaCityDtoOut out) {
//        if (TextUtils.isEmpty(App.getInstance().getProperty(AppConfig.CONF_CITYVERSION))) {
//            App.getInstance().setProperty(AppConfig.CONF_CITYVERSION, out.versionCity);
//            requestCity();
//        } else {
//            int local = Integer.valueOf(App.getInstance().getProperty(AppConfig.CONF_CITYVERSION));
//            if (local < Integer.valueOf(out.versionCity)) {
//                App.getInstance().setProperty(AppConfig.CONF_CITYVERSION, out.versionCity);
//                requestCity();
//            }
//        }
//    }


    public void InsertAllCity(List<AreaCityDtoOut> out) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DBHelperConsumer.table + "", null);
        if (cursor.getCount() > 0) {
            db.execSQL("delete from " + DBHelperConsumer.table);
        }
        int i = 0;
        while (i < out.size()) {
            db.execSQL("insert into " + DBHelperConsumer.table + "(name, pinyin) values('" + out.get(i).cityName + "', '" + out.get(i).pinyinName + "')");
            i++; //变量的值增加1
        }
        cursor.close();
        db.close();
    }

//    public void InsertHotCity(List<AreaCityHotDtoOut> out) {
//        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from " + DBHelperConsumer.table2 + "", null);
//        if (cursor.getCount() > 0) {
//            db.execSQL("delete from " + DBHelperConsumer.table2);
//        }
//        int i = 0;
//        while (i < out.size()) {
//            db.execSQL("insert into " + DBHelperConsumer.table2 + "(name, pinyin) values('" + out.get(i).cityName + "', '" + out.get(i).pinyinName + "')");
//            i++; //变量的值增加1
//        }
//        cursor.close();
//        db.close();
//    }


//    public void requestCity() {
//        waitdialog.show();
//        try {
//            presenter.getCityList();
//        } catch (AppException e) {
//            e.printStackTrace();
//        }
//    }

    private void savejump(String str) {
        Toast.makeText(CityActivity.this, str, Toast.LENGTH_LONG).show();
    }

}
