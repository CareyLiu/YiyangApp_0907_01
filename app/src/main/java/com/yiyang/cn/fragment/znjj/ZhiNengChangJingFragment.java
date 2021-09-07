package com.yiyang.cn.fragment.znjj;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.zhinengjiaju.ChuangJianZhiNengActivity;
import com.yiyang.cn.activity.zhinengjiaju.ZhiNengChangJingDetailsActivity;
import com.yiyang.cn.adapter.ZhiNengChangJingAdapter;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.fragment.znjj.adapter.ZhiNengChangJingNewAdapter;
import com.yiyang.cn.fragment.znjj.model.ZhiNengModel;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.ChangJingModel;
import com.yiyang.cn.model.ChangJingXiangQingModel;
import com.yiyang.cn.model.ZhiNengHomeBean;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengChangJingFragment extends BaseFragment {

    RecyclerView rlvList;

    private List<ZhiNengModel.DataBean.SceneBean> mDatas = new ArrayList<>();
    private ZhiNengChangJingNewAdapter zhiNengChangJingAdapter;
    private String guanLiYuan = "";//0不是 1 是
    private View viewLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLayout = inflater.inflate(R.layout.layout_changjing, container, false);
        guanLiYuan = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.ZHINENGJIAJUGUANLIYUAN, "0");
        initListView();
        initHuidiao();
        return viewLayout;
    }

    private void initHuidiao() {
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
        mDatas.clear();
        mDatas.addAll(dataBean.get(0).getScene());
        if (zhiNengChangJingAdapter != null) {
            zhiNengChangJingAdapter.notifyDataSetChanged();
        }
    }

    private void initListView() {
        rlvList = viewLayout.findViewById(R.id.rlv_list);
        zhiNengChangJingAdapter = new ZhiNengChangJingNewAdapter(R.layout.item_zhinengjiaju_changjing, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(zhiNengChangJingAdapter);
        View view = View.inflate(getActivity(), R.layout.layout_zhinengchangjing_bottom, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guanLiYuan.equals("1")) {
                    ChuangJianZhiNengActivity.actionStart(getActivity());
                } else {
                    UIHelper.ToastMessage(getActivity(), "您不是管理员无此权限");
                }
            }
        });
        zhiNengChangJingAdapter.addFooterView(view);
        zhiNengChangJingAdapter.setNewData(mDatas);
        zhiNengChangJingAdapter.notifyDataSetChanged();

        zhiNengChangJingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rrl_main:
                        ZhiNengChangJingDetailsActivity.actionStart(getActivity(), mDatas.get(position).getScene_type(), mDatas.get(position).getScene_id(), mDatas.get(position).getFamily_id());
                        break;
                    case R.id.iv_zhixing:
                        zhiXingMingLing(mDatas.get(position).getScene_id());
                        break;
                    case R.id.view:
                        clickSwicth(position);
                        break;
                }
            }
        });
    }

    private void clickSwicth(int position) {
        SwitchButton switchButton = (SwitchButton) zhiNengChangJingAdapter.getViewByPosition(rlvList, position, R.id.csw_switch_button);
        if (switchButton.isChecked()) {
            kaiOrGuanBiChangJing(mDatas.get(position).getScene_id(), mDatas.get(position).getFamily_id(), "2", position);
        } else {
            kaiOrGuanBiChangJing(mDatas.get(position).getScene_id(), mDatas.get(position).getFamily_id(), "3", position);
        }
    }

    private void kaiOrGuanBiChangJing(String scene_id, String familyId, String scene_state, int position) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16060");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("scene_id", scene_id);
        map.put("family_id", familyId);
        map.put("scene_state", scene_state);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        UIHelper.ToastMessage(getActivity(), "场景切换成功");
                        ZhiNengModel.DataBean.SceneBean sceneBean = mDatas.get(position);
                        sceneBean.setScene_state(scene_state);
                        mDatas.set(position, sceneBean);
                        zhiNengChangJingAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(getActivity(), str);
                    }
                });
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_changjing;
    }

    @Override
    protected void initView(View rootView) {

    }

    private void zhiXingMingLing(String scene_id) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16057");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("scene_id", scene_id);

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        UIHelper.ToastMessage(getActivity(), "命令执行成功");

                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(getActivity(), str);
                    }
                });
    }


}
