package com.yiyang.cn.activity.tongcheng58;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.YiyangTuActivity;
import com.yiyang.cn.activity.a_yiyang.YiyangTuTActivity;
import com.yiyang.cn.activity.tongcheng58.adapter.TcHomeTagAdapter;
import com.yiyang.cn.activity.tongcheng58.model.TcHomeModel;
import com.yiyang.cn.basicmvp.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TongchengTagItemFragment extends BaseFragment {

    @BindView(R.id.rv_tag_list)
    RecyclerView rv_tag_list;

    private List<TcHomeModel.DataBean.IconListBean> iconList;
    private TcHomeTagAdapter adapter;

    public TongchengTagItemFragment(List<TcHomeModel.DataBean.IconListBean> iconList) {
        this.iconList = iconList;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.tongcheng_frag_home_tag;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initStart();
        return rootView;
    }

    private void initStart() {
        adapter = new TcHomeTagAdapter(R.layout.tongcheng_item_home_tag, iconList);
        rv_tag_list.setLayoutManager(new GridLayoutManager(getContext(), 5));
        rv_tag_list.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TcHomeModel.DataBean.IconListBean listBean = iconList.get(position);
                if (listBean.isNew()) {
                    switch (position) {
                        case 0:
                            YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_zhihuiyanglao);
                            break;
                        case 1:
                            YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_dianhuajizhen);
                            break;
                        case 2:
                            YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_zaixianyisheng);
                            break;
                        case 3:
                            YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_jibingchaxun);
                            break;
                        case 4:
                            YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_yuyueguahao);
                            break;
                        case 5:
                            YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_jijiufuwu);
                            break;
                        case 6:
                            YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_hujiaozhongxin);
                            break;
                        case 7:
                            YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_huodongzhongxin);
                            break;
                        case 8:
                            YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_yanglaoshitanbg);
                            break;
                        case 9:
                            YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_jiankangshuju);
                            break;
                    }

                } else {
                    GongJiangLieBiaoNewActivity.actionStart(getContext(), listBean.getService_type());
                }
            }
        });
    }
}
