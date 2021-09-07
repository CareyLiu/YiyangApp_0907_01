package com.yiyang.cn.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.CheLianWangNoticeActvity;
import com.yiyang.cn.activity.dingdan.DaiFuKuanDingDanActivity;
import com.yiyang.cn.activity.dingdan.XiaoXiEnterDingDanActivity;
import com.yiyang.cn.adapter.MessageListAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.common.UIHelper;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_Delete;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.MessageModel;
import com.yiyang.cn.model.OrderListModel;
import com.yiyang.cn.util.NetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yiyang.cn.config.MyApplication.getApp;
import static com.yiyang.cn.get_net.Urls.MESSAGE_URL;


public class MessageListFragment extends BaseFragment {


    private RecyclerView recyclerView;
    List<MessageModel.DataBean> mDatas = new ArrayList<>();
    private MessageListAdapter messageListAdapter;

    SmartRefreshLayout srLSmart;

    String notifyId = "";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03003");
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(getActivity()).getString("app_token", "0"));
        map.put("notify_id", notifyId);
        if (strTitle.equals("全部")) {

        } else if (strTitle.equals("未读")) {
            map.put("notify_read", "1");
        } else if (strTitle.equals("已读")) {
            map.put("notify_read", "2");

        } else if (strTitle.equals("已处理")) {
            map.put("notify_state", "2");
        } else if (strTitle.equals("未处理")) {
            map.put("notify_state", "2");
        }
        NetUtils<MessageModel.DataBean> netUtils = new NetUtils<>();
        if (getActivity() == null) {
            return;
        }
        netUtils.requestData(map, Urls.MESSAGE_URL, getActivity(), new JsonCallback<AppResponse<MessageModel.DataBean>>() {
            @Override
            public void onSuccess(Response<AppResponse<MessageModel.DataBean>> response) {
                if (response.body().data.size() > 0) {
                    if (StringUtils.isEmpty(notifyId)) {
                        mDatas.clear();
                        mDatas.addAll(response.body().data);
                        messageListAdapter.setNewData(mDatas);

                    } else {
                        srLSmart.setEnableLoadMore(true);
                        mDatas.addAll(response.body().data);
                    }

                    notifyId = mDatas.get(mDatas.size() - 1).getNotify_id();
                    messageListAdapter.notifyDataSetChanged();

                    recyclerView.setVisibility(View.VISIBLE);
                    ivNone.setVisibility(View.GONE);
                    srLSmart.setEnableLoadMore(true);
                } else {
                    srLSmart.setEnableLoadMore(false);
                }

                if (mDatas.size() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    ivNone.setVisibility(View.VISIBLE);
                }
                srLSmart.finishLoadMore();
                srLSmart.finishRefresh();
            }

            @Override
            public void onError(Response<AppResponse<MessageModel.DataBean>> response) {
                super.onError(response);

            }

            @Override
            public void onStart(Request<AppResponse<MessageModel.DataBean>, ? extends Request> request) {
                super.onStart(request);
            }
        });
    }

    String strTitle;
    ImageView ivNone;

    @Override
    protected void initLogic() {
        Bundle args = getArguments();
        strTitle = args.getString("title");
        getNet();
    }

    RelativeLayout rlMain;
    View view;


    @Override
    protected int getLayoutRes() {
        return R.layout.messagelist;
    }

    TishiDialog tishiDialog;

    @Override
    protected void initView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rlMain = rootView.findViewById(R.id.rl_main);
        ivNone = rootView.findViewById(R.id.iv_none);
        view = rootView.findViewById(R.id.view);
        srLSmart = rootView.findViewById(R.id.srL_smart);
        initAdapter();
        srLSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                notifyId = "";
                getNet();
            }
        });
        messageListAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                //UIHelper.ToastMessage(getActivity(), "长按");


                MyCarCaoZuoDialog_Delete myCarCaoZuoDialog_delete = new MyCarCaoZuoDialog_Delete(getActivity(), new MyCarCaoZuoDialog_Delete.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {

                    }

                    @Override
                    public void clickRight() {

                        MessageModel.DataBean dataBean = (MessageModel.DataBean) adapter.getData().get(position);
                        String notifyId = dataBean.getNotify_id();
                        String of_user_id = PreferenceHelper.getInstance(getActivity()).getString("of_user_id", "");
                        deleteMessageNet(notifyId, of_user_id);
                    }
                });
                myCarCaoZuoDialog_delete.show();
                return false;
            }
        });
        messageListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.constrain:
                        /**
                         * 通知类型：1.风暖加热器报警
                         * 2.水暖加热器故障报警3.汽车报警
                         * 8.普通消息 9.广告消息 11.商城消息
                         * 12.商城订单消息 13.拼单消息
                         */

                        MessageModel.DataBean dataBean = (MessageModel.DataBean) adapter.getData().get(position);
                        switch (mDatas.get(position).getNotify_type()) {

                            case "1":
                            case "2":
                            case "3":
                                getRead(mDatas.get(position).getNotify_id());
                                MessageModel.DataBean dataBean1 = mDatas.get(position);
                                dataBean1.setNotify_read("2");
                                mDatas.set(position, dataBean1);

                                CheLianWangNoticeActvity.actionStart(getActivity(), dataBean.getNotify_id());
                                break;
                            case "8":
                                break;
                            case "9":
                                break;
                            case "11":
                                break;
                            case "12":
//                                OrderListModel.DataBean dataBean = new OrderListModel.DataBean();
//                                dataBean.setShop_form_id(mDatas.get(position).getOper_id());
                                getRead(mDatas.get(position).getNotify_id());
                                MessageModel.DataBean dataBean2 = mDatas.get(position);
                                dataBean2.setNotify_read("2");
                                mDatas.set(position, dataBean2);
                                XiaoXiEnterDingDanActivity.actionStart(getActivity(), mDatas.get(position).getOper_id());
                                break;
                            case "13":
                                break;

                            case "15":
                                tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
                                    @Override
                                    public void onClickCancel(View v, TishiDialog dialog) {
                                    }

                                    @Override
                                    public void onClickConfirm(View v, TishiDialog dialog) {
                                        getRead(mDatas.get(position).getNotify_id());
                                        MessageModel.DataBean dataBean1 = mDatas.get(position);
                                        dataBean1.setNotify_read("2");
                                        mDatas.set(position, dataBean1);
                                        messageListAdapter.notifyDataSetChanged();

                                    }

                                    @Override
                                    public void onDismiss(TishiDialog dialog) {

                                    }
                                });
                                tishiDialog.setTextCancel("");
                                tishiDialog.setTextConfirm("知道了");
                                tishiDialog.setTextContent(mDatas.get(position).getNotify_text());
                                tishiDialog.show();
                                break;

                        }
                        break;
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageListAdapter = new MessageListAdapter(R.layout.item_messagelist, mDatas);
        messageListAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(messageListAdapter);
    }

    public void deleteMessageNet(String notifyId, String of_user_id) {
        //访问网络获取数据 下面的列表数据
        Map<String, Object> map = new HashMap<>();
        map.put("code", "03002");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getApp()).getAppToken());
        map.put("notify_id", notifyId);
        map.put("of_user_id", of_user_id);

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse>post(MESSAGE_URL)
                .tag(this)
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(Response<AppResponse> response) {
                        showLoadSuccess();
                        srLSmart.autoRefresh();
                    }

                    @Override
                    public void onStart(Request<AppResponse, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }
                });
    }

    public void getRead(String notifyId) {
        //访问网络获取数据 下面的列表数据
        Map<String, Object> map = new HashMap<>();
        map.put("code", "03516");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getApp()).getAppToken());
        map.put("notify_id", notifyId);

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse>post(MESSAGE_URL)
                .tag(this)
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(Response<AppResponse> response) {
                        //showLoadSuccess();
                        getNet();
                    }

                    @Override
                    public void onStart(Request<AppResponse, ? extends Request> request) {
                        super.onStart(request);
                        //  showLoading();
                    }
                });
    }
}

