package com.yiyang.cn.fragment.znjj;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.ZhiNengRoomManageActivity;
import com.yiyang.cn.activity.ZhiNengRoomSettingActivity;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.adapter.ZhiNengRoomListAdapter;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.fragment.znjj.adapter.ZhiNengRoomListNewAdapter;
import com.yiyang.cn.fragment.znjj.model.ZhiNengModel;
import com.yiyang.cn.view.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class ZhiNengRoomFragment extends BaseFragment implements View.OnClickListener {
    private View viewLayout;
    private View footerView;
    private XRecyclerView recyclerView;
    private ZhiNengRoomListNewAdapter zhiNengRoomListAdapter;
    private List<ZhiNengModel.DataBean.RoomBean> mDatas = new ArrayList<>();
    private LinearLayout ll_room_add;
    private TextView tv_room_add;
    private String member_type = "";
    private String family_id = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        viewLayout = inflater.inflate(R.layout.fragment_zhineng_room, container, false);
        init(viewLayout);
        initHudiao();
        return viewLayout;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_zhineng_room;
    }

    @Override
    protected void initView(View rootView) {

    }

    private void initHudiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_ZI_SHUAXIN) {
                    List<ZhiNengModel.DataBean> dataBean = (List<ZhiNengModel.DataBean>) message.content;
                    getData(dataBean);
                }
            }
        }));
    }

    public void getData(List<ZhiNengModel.DataBean> dataBean) {
        PreferenceHelper.getInstance(getActivity()).putString(AppConfig.FAMILY_ID, dataBean.get(0).getFamily_id());
        member_type = dataBean.get(0).getMember_type();

        mDatas.clear();
        mDatas.addAll(dataBean.get(0).getRoom());
        if (zhiNengRoomListAdapter != null) {
            zhiNengRoomListAdapter.notifyDataSetChanged();
        }
//        if (recyclerView != null) {
//            for (int i = 0; i < recyclerView.getItemDecorationCount(); i++) {
//                recyclerView.removeItemDecorationAt(i);
//            }
//            if (mDatas.size() == 0) {
//                recyclerView.addItemDecoration(new GridAverageUIDecoration(0, 10));
//            } else {
//                recyclerView.addItemDecoration(new GridAverageUIDecoration(14, 10));
//            }
//        }
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        footerView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_zhineng_room_footer, null);
        ll_room_add = footerView.findViewById(R.id.ll_room_add);
        ll_room_add.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        zhiNengRoomListAdapter = new ZhiNengRoomListNewAdapter(R.layout.item_zhineng_room, mDatas);
        zhiNengRoomListAdapter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.activity_zhineng_room_none, null));
        tv_room_add = zhiNengRoomListAdapter.getEmptyView().findViewById(R.id.tv_room_add);
        tv_room_add.setOnClickListener(this);
        zhiNengRoomListAdapter.openLoadAnimation();//默认为渐显效果
        zhiNengRoomListAdapter.addFooterView(footerView);
        recyclerView.setAdapter(zhiNengRoomListAdapter);
        zhiNengRoomListAdapter.setNewData(mDatas);
        zhiNengRoomListAdapter.notifyDataSetChanged();

        zhiNengRoomListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZhiNengModel.DataBean.RoomBean roomBean = (ZhiNengModel.DataBean.RoomBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("room_id", roomBean.getRoom_id());
                bundle.putString("family_id", roomBean.getFamily_id());
                bundle.putString("room_name", roomBean.getRoom_name());
                bundle.putString("member_type", member_type);
                startActivity(new Intent(getActivity(), ZhiNengRoomSettingActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_room_add:
                Bundle bundle1 = new Bundle();
                bundle1.putString("member_type", member_type);
                bundle1.putString("family_id", family_id);
                startActivity(new Intent(getActivity(), ZhiNengRoomManageActivity.class).putExtras(bundle1));
                break;
            case R.id.ll_room_add:
                Bundle bundle = new Bundle();
                bundle.putString("member_type", member_type);
                bundle.putString("family_id", family_id);
                startActivity(new Intent(getActivity(), ZhiNengRoomManageActivity.class).putExtras(bundle));
                break;
        }
    }
}
