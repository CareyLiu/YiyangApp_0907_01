package com.yiyang.cn.activity.tuya_device.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.add.model.DeviceListModel;
import com.yiyang.cn.adapter.FenLeiHomeLeftListAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.get_net.Urls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TuyaDeviceAddShouFragment extends BaseFragment {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;


    private List<String> leftTitle = new ArrayList<>();//左侧標題
    private List<DeviceListModel.DataBean> dataBeans = new ArrayList<>();//数据源
    private List<DeviceListModel.DataBean.ItemBeanX> itemBeanXES = new ArrayList<>();//传送的数据
    private FenLeiHomeLeftListAdapter leftListAdapter;


    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_device_add_shou;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        initAdapter();
        getNet();
    }

    private void initAdapter() {
        leftListAdapter = new FenLeiHomeLeftListAdapter(R.layout.item_fenlei_left_list, leftTitle, "0", true);
        rlvList.setAdapter(leftListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);

        LeftAndRightAction();
    }

    public void getNet() {
        Map<String, String> map = new HashMap<>();
            map.put("code", "00025");
        map.put("key", Urls.key);
        map.put("subsystem_id", "wit");
        map.put("itemtype_id", "3");
        Gson gson = new Gson();
        OkGo.<AppResponse<DeviceListModel.DataBean>>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DeviceListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DeviceListModel.DataBean>> response) {
                        dataBeans = response.body().data;
                        itemBeanXES = dataBeans.get(0).getItem();
                        for (int i = 0; i < dataBeans.size(); i++) {
                            leftTitle.add(dataBeans.get(i).getItem_name());
                        }

                        leftListAdapter.setNewData(leftTitle);
                        leftListAdapter.notifyDataSetChanged();

                        if (dataBeans != null && dataBeans.size() > 0) {
                            setRight();
                        }
                    }
                });
    }

    private void setRight() {
        //创建Fragment对象
        DeviceFenLeiFragment deviceFenLeiFragment = new DeviceFenLeiFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_container, deviceFenLeiFragment);

        //传递数据到Fragment
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("info", (Serializable) itemBeanXES);
        mBundle.putString("one_item", dataBeans.get(0).getItem_id());
        deviceFenLeiFragment.setArguments(mBundle);
        fragmentTransaction.commit();
    }

    public void LeftAndRightAction() {
        leftListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        leftListAdapter.strPosition = position;
                        adapter.notifyDataSetChanged();
                        itemBeanXES = new ArrayList<>();
                        itemBeanXES.addAll(dataBeans.get(position).getItem());
                        //创建Fragment对象
                        DeviceFenLeiFragment deviceFenLeiFragment = new DeviceFenLeiFragment();
                        //传递数据到Fragment
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("info", (Serializable) itemBeanXES);
                        mBundle.putString("one_item", dataBeans.get(position).getItem_id());
                        deviceFenLeiFragment.setArguments(mBundle);
                        replaceFragment(deviceFenLeiFragment);
                        break;
                }
            }
        });
    }

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private void replaceFragment(Fragment fragment) {
        // 1.获取FragmentManager，在活动中可以直接通过调用getFragmentManager()方法得到
        fragmentManager = getActivity().getSupportFragmentManager();
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
