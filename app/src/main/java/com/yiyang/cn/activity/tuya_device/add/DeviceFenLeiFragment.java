package com.yiyang.cn.activity.tuya_device.add;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.add.model.DeviceListModel;
import com.yiyang.cn.activity.zhinengjiaju.peinet.PeiWangYinDaoPageActivity;
import com.yiyang.cn.activity.zhinengjiaju.peinet.SheBeiChongZhiActivity;
import com.yiyang.cn.activity.zhinengjiaju.peinet.ZhiNengJiaJuPeiWangActivity;
import com.yiyang.cn.activity.zijian_shangcheng.FenLeiThirdActivity;
import com.yiyang.cn.adapter.FenLeiRightAdapter;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.model.FenLeiContentModel;
import com.yiyang.cn.model.ZiJianFenLeiBean;
import com.yiyang.cn.project_A.zijian_interface.FenLeiContenInterface;
import com.yiyang.cn.util.GridSectionAverageGapItemDecoration;
import com.umeng.commonsdk.UMConfigure;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DeviceFenLeiFragment extends BaseFragment implements FenLeiContenInterface {
    private List<DeviceListModel.DataBean.ItemBeanX> itemBeanXES = new ArrayList<>();
    private RecyclerView rlvList;
    private FenLeiRightAdapter fenLeiRightAdapter;
    private List<FenLeiContentModel> mDatas;

    // boolean flag = true;// true 是第一个 false 不是第一个
    private String one_item;
    private String two_item;
    FenLeiContentModel fenLeiContentModel;

    @Override
    public void getNet() {
        //无需访问网络
        Bundle bundle = getArguments();
        itemBeanXES = (List<DeviceListModel.DataBean.ItemBeanX>) bundle.getSerializable("info");
        one_item = bundle.getString("one_item");
        if (itemBeanXES.size() > 0) {
            mDatas = new ArrayList<>();
            for (int i = 0; i < itemBeanXES.size(); i++) {
                FenLeiContentModel fenLeiContentModel = new FenLeiContentModel(true, itemBeanXES.get(i).getItem_name());
                fenLeiContentModel.one_item = one_item;
                fenLeiContentModel.two_item = itemBeanXES.get(i).getItem_id();
                two_item = itemBeanXES.get(i).getItem_id();
                mDatas.add(fenLeiContentModel);
                for (int j = 0; j < itemBeanXES.get(i).getItem().size(); j++) {
                    DeviceListModel.DataBean.ItemBeanX.ItemBean itemBean = itemBeanXES.get(i).getItem().get(j);
                    FenLeiContentModel contentModel = new FenLeiContentModel(false, itemBeanXES.get(i).getItem_name());
                    contentModel.setImg_url(itemBean.getImg_url());
                    contentModel.setItem_id(itemBean.getItem_id());
                    contentModel.setItem_name(itemBean.getItem_name());
                    contentModel.two_item = two_item;
                    contentModel.one_item = one_item;

                    contentModel.category = itemBean.getCategory();
                    contentModel.img_detail_url = itemBean.getImg_detail_url();
                    contentModel.img_url = itemBean.getImg_url();
                    contentModel.is_product = itemBean.getIs_product();
                    contentModel.item_name = itemBean.getItem_name();
                    contentModel.item_id_up = itemBean.getItem_id_up();
                    contentModel.item_detail = itemBean.getItem_detail();
                    contentModel.item_id = itemBean.getItem_id();
                    contentModel.type = itemBean.getType();
                    mDatas.add(contentModel);
                }
            }
            //有数据
            loadList();
        } else {
            //无数据
        }
    }

    @Override
    public void loadList() {
        fenLeiRightAdapter = new FenLeiRightAdapter(mDatas);
        rlvList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rlvList.addItemDecoration(new GridSectionAverageGapItemDecoration(10, 10, 20, 15));
        rlvList.setAdapter(fenLeiRightAdapter);

        fenLeiRightAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        fenLeiContentModel = mDatas.get(position);
                        // TODO: 2021/2/8  添加是否由主机校验
//                        String strZhuJi = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.HAVEZHUJI, "");
//                        if (fenLeiContentModel.type.equals("00")) {
//                            if (strZhuJi.equals("0")) {
//                                SheBeiChongZhiActivity.actionStart(getActivity(), mDatas.get(position).getItem_name(), mDatas.get(position).getImg_url(), mDatas.get(position).header, fenLeiContentModel);
//                            } else {
//                                UIHelper.ToastMessage(getActivity(), "此家庭已有主机,请切换家庭后重新尝试");
//                            }
//                        } else {
//                            if (strZhuJi.equals("0")) {
//                                UIHelper.ToastMessage(getActivity(), "此家庭没有主机,请先添加主机后重新尝试");
//                            } else {
//
//                            }
//                        }

                        //1 是可用 2是不可用
                        if (fenLeiContentModel.is_product.equals("2")) {
                            //暂未开放此设备，敬请期待
                            UIHelper.ToastMessage(getActivity(), "暂未开放此设备，敬请期待");
                        } else {
                            SheBeiChongZhiActivity.actionStart(getActivity(), mDatas.get(position).getItem_name(), mDatas.get(position).getImg_url(), mDatas.get(position).header, fenLeiContentModel);
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void getCanshu() {

    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_fenleiright_content;
    }

    @Override
    protected void initView(View rootView) {
        rlvList = rootView.findViewById(R.id.rlv_list);
        getNet();
    }

    @Override
    public boolean onBackPressedSupport() {
        getActivity().finish();
        return super.onBackPressedSupport();
    }
}
