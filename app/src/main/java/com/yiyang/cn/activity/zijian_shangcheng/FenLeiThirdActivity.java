package com.yiyang.cn.activity.zijian_shangcheng;

import android.content.Context;
import android.content.Intent;
import android.location.GnssClock;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.model.FenLeiContentModel;
import com.yiyang.cn.project_A.zijian_interface.FenLeiListInterface;

import butterknife.BindView;


public class FenLeiThirdActivity extends BaseActivity implements FenLeiListInterface {
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    String item_id;
    String item_name;//名
    String oneitem;
    String twoitem;
    //  String threeitem;
    @BindView(R.id.tv_zonghe)
    TextView tvZonghe;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;

    @BindView(R.id.ll_zonghe_xiaoliang)
    LinearLayout llZongheXiaoliang;
    @BindView(R.id.tv_zhekou)
    TextView tvZhekou;
    @BindView(R.id.view_1)
    View view1;
    @BindView(R.id.tv_jiagesheng)
    TextView tvJiagesheng;
    @BindView(R.id.view_2)
    View view2;
    @BindView(R.id.tv_jiagejiang)
    TextView tvJiagejiang;
    @BindView(R.id.view_4)
    View view4;
    @BindView(R.id.tv_xiaoliagnjiang)
    TextView tvXiaoliagnjiang;
    @BindView(R.id.constrain)
    ConstraintLayout constrain;


    @Override
    public int getContentViewResId() {
        return R.layout.layout_fenlei_third;
    }

//    @Override
    //  public boolean showToolBar() {
//        return true;
    //}

    //@Override
    //protected void initToolbar() {
    //  super.initToolbar();
    // tv_title.setText(item_name);
//        tv_title.setTextSize(17);
//        tv_title.setTextColor(getResources().getColor(R.color.black));
//        mToolbar.setNavigationIcon(R.mipmap.backbutton);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                finish();
//            }
//        });
//    }
    FenLeiContentModel fenLeiContentModel;
    /**
     * intent.putExtra("strTitle", strTitle);
     * intent.putExtra("shouYeId", shouYeId);
     */
    private String strTitle;
    private String shouYeId;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fenLeiContentModel = (FenLeiContentModel) getIntent().getSerializableExtra("fenleiContent");
        strTitle = getIntent().getStringExtra("strTitle");
        shouYeId = getIntent().getStringExtra("shouYeId");

        if (fenLeiContentModel != null) {
            item_name = fenLeiContentModel.getItem_name();
            oneitem = fenLeiContentModel.one_item;
            twoitem = fenLeiContentModel.two_item;
            item_id = fenLeiContentModel.getItem_id();
            tvTitle.setText(item_name);
        } else {
            tvTitle.setText(strTitle);
        }

        tvZonghe.setTextColor(getResources().getColor(R.color.red_61832));
        tvXiaoliang.setTextColor(getResources().getColor(R.color.black_111111));

        FenLeiThirdFragment fenLeiThirdFragment = new FenLeiThirdFragment();
        //传递数据到Fragment
        Bundle mBundle = new Bundle();
        //  mBundle.putSerializable("info", (Serializable) itemBeanXES);
        mBundle.putSerializable("page", "1");
        mBundle.putString("item_id", item_id);
        mBundle.putString("one_item", oneitem);
        mBundle.putString("two_item", twoitem);

        mBundle.putString("strTitle", strTitle);
        mBundle.putString("shouYeId", shouYeId);

        fenLeiThirdFragment.setArguments(mBundle);
        replaceFragment(fenLeiThirdFragment);
//        initToolbar();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        changePage();
        //order_type	排序类型：1.折扣2.价格升序
        //3.价格降序4.销量降序		否
        tvJiagejiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice n = new Notice();
                n.type = ConstanceValue.MSG_SETFZONGHE;
                n.content = "3";
                //  n.content = message.toString();
                RxBus.getDefault().sendRx(n);
                constrain.setVisibility(View.GONE);
            }
        });
        tvJiagesheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice n = new Notice();
                n.type = ConstanceValue.MSG_SETFZONGHE;
                n.content = "2";
                //  n.content = message.toString();
                RxBus.getDefault().sendRx(n);
                constrain.setVisibility(View.GONE);
            }
        });
        tvXiaoliagnjiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice n = new Notice();
                n.type = ConstanceValue.MSG_SETFZONGHE;
                n.content = "4";
                //  n.content = message.toString();
                RxBus.getDefault().sendRx(n);
                constrain.setVisibility(View.GONE);
            }
        });
        tvZhekou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice n = new Notice();
                n.type = ConstanceValue.MSG_SETFZONGHE;
                n.content = "1";
                //  n.content = message.toString();
                RxBus.getDefault().sendRx(n);
                constrain.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void getNet() {

    }

    @Override
    public void initList() {

    }

    String str = "0";
    String tanchuCon = "0";//0否 1 是

    @Override
    public void changePage() {

        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (str.equals("0")) {
                    //弹出con
                    if (tanchuCon.equals("0")) {
                        constrain.setVisibility(View.VISIBLE);
                        tanchuCon = "1";
                        tvZonghe.setTextColor(getResources().getColor(R.color.red_61832));
                        tvXiaoliang.setTextColor(getResources().getColor(R.color.black_111111));
                    } else if (tanchuCon.equals("1")) {
                        constrain.setVisibility(View.GONE);
                        tanchuCon = "0";
                    }
                    return;
                }


                FenLeiThirdFragment fenLeiThirdFragment = new FenLeiThirdFragment();
                //传递数据到Fragment
                Bundle mBundle = new Bundle();
                //  mBundle.putSerializable("info", (Serializable) itemBeanXES);
                mBundle.putSerializable("page", "1");
                mBundle.putString("item_id", item_id);
                mBundle.putString("one_item", oneitem);
                mBundle.putString("two_item", twoitem);
                fenLeiThirdFragment.setArguments(mBundle);
                replaceFragment(fenLeiThirdFragment);
                str = "1";
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.equals("1")) {
                    return;
                }
                constrain.setVisibility(View.GONE);
                tanchuCon = "0";
                tvZonghe.setTextColor(getResources().getColor(R.color.black_111111));
                tvXiaoliang.setTextColor(getResources().getColor(R.color.red_61832));

                FenLeiThirdFragment fenLeiThirdFragment = new FenLeiThirdFragment();
                //传递数据到Fragment
                Bundle mBundle = new Bundle();
                //  mBundle.putSerializable("info", (Serializable) itemBeanXES);
                mBundle.putSerializable("page", "2");
                mBundle.putString("item_id", item_id);
                mBundle.putString("one_item", oneitem);
                mBundle.putString("two_item", twoitem);
                fenLeiThirdFragment.setArguments(mBundle);
                replaceFragment(fenLeiThirdFragment);
                str = "0";
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

    public static void actionStart(Context context, FenLeiContentModel fenLeiContentModel) {
        Intent intent = new Intent(context, FenLeiThirdActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("fenleiContent", fenLeiContentModel);
        context.startActivity(intent);

    }

    public static void actionStart(Context context, String strTitle, String shouYeId) {
        Intent intent = new Intent(context, FenLeiThirdActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("strTitle", strTitle);
        intent.putExtra("shouYeId", shouYeId);
        context.startActivity(intent);

    }

}
