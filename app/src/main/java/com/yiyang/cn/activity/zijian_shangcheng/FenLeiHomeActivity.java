package com.yiyang.cn.activity.zijian_shangcheng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.adapter.FenLeiHomeLeftListAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.fragment.zijian_shopmall.FenLeiRightFragment;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ZiJianFenLeiBean;
import com.yiyang.cn.project_A.zijian_interface.FenLeiInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.yiyang.cn.get_net.Urls.ZIYINGFENLEI;

public class FenLeiHomeActivity extends BaseActivity implements FenLeiInterface {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    FenLeiHomeLeftListAdapter leftListAdapter;
    List<Object> objects = new ArrayList<>();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getNet();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    @Override
//    public boolean showToolBar() {
//        return true;
//    }
//
//    @Override
//    protected void initToolbar() {
//        super.initToolbar();
//        tv_title.setText("更多分类");
//        tv_title.setTextSize(17);
//        tv_title.setTextColor(getResources().getColor(R.color.black));
//        mToolbar.setNavigationIcon(R.mipmap.backbutton);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                finish();
//            }
//        });

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, FenLeiHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

//    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_zijian_fenlei_home;
    }

    List<String> oneItemList = new ArrayList<>();//左侧列表
    List<ZiJianFenLeiBean.DataBean> dataBean;//数据源
    List<ZiJianFenLeiBean.DataBean.ItemBeanX> itemBeanXES;//传送的bean

    @Override
    public void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "04103");
        map.put("key", Urls.key);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZiJianFenLeiBean.DataBean>>post(ZIYINGFENLEI)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZiJianFenLeiBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZiJianFenLeiBean.DataBean>> response) {

                        //    objects.addAll(response.body().data);
                        //    leftListAdapter.setNewData(objects);
                        //1级经营项
                        dataBean = response.body().data;
                        itemBeanXES = new ArrayList<>();
                        itemBeanXES.addAll(dataBean.get(0).getItem());
                        if (dataBean != null) {

                            for (int i = 0; i < dataBean.size(); i++) {
                                oneItemList.add(dataBean.get(i).getItem_name());
                            }

                            leftList();
                            RightFragMent();
                            leftListAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    @Override
    public void leftList() {
        //初始化
        leftListAdapter = new FenLeiHomeLeftListAdapter(R.layout.item_fenlei_left_list, oneItemList, "0");
        rlvList.setAdapter(leftListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        RightFragMent();
    }

    @Override
    public void RightFragMent() {

        //创建Fragment对象
        FenLeiRightFragment fenLeiRightFragment = new FenLeiRightFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_container, fenLeiRightFragment);

        //传递数据到Fragment
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("info", (Serializable) itemBeanXES);
        mBundle.putSerializable("one_item", dataBean.get(0).getItem_id());
        fenLeiRightFragment.setArguments(mBundle);
        fragmentTransaction.commit();

        LeftAndRightAction();
    }

    @Override
    public void LeftAndRightAction() {


        leftListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
//        //选中处理
                        leftListAdapter.strPosition = position;
                        //刷新列表
                        adapter.notifyDataSetChanged();
                        itemBeanXES = new ArrayList<>();
                        itemBeanXES.addAll(dataBean.get(position).getItem());
                        //创建Fragment对象
                        FenLeiRightFragment fenLeiRightFragment = new FenLeiRightFragment();
                        //传递数据到Fragment
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("info", (Serializable) itemBeanXES);
                        mBundle.putString("one_item",dataBean.get(position).getItem_id());
                        fenLeiRightFragment.setArguments(mBundle);
                        replaceFragment(fenLeiRightFragment);

                        break;
                }
            }
        });


    }

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    //Fragment启动方法：

    private void replaceFragment(Fragment fragment) {
// 1.获取FragmentManager，在活动中可以直接通过调用getFragmentManager()方法得到
        fragmentManager = getSupportFragmentManager();
// 2.开启一个事务，通过调用beginTransaction()方法开启
        transaction = fragmentManager.beginTransaction();
// 3.向容器内添加或替换碎片，一般使用replace()方法实现，需要传入容器的id和待添加的碎片实例
        transaction.replace(R.id.fl_container, fragment);  //fr_container不能为fragment布局，可使用线性布局相对布局等。
// 4.使用addToBackStack()方法，将事务添加到返回栈中，填入的是用于描述返回栈的一个名字
        transaction.addToBackStack(null);
// 5.提交事物,调用commit()方法来完成
        transaction.commit();
    }


}
