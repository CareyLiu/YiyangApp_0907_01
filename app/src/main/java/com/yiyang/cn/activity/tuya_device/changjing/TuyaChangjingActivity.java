package com.yiyang.cn.activity.tuya_device.changjing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.app.BaseActivity;
import com.tuya.smart.api.MicroContext;
import com.tuya.smart.commonbiz.bizbundle.family.api.AbsBizBundleFamilyService;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.scene.SceneBean;
import com.tuya.smart.home.sdk.bean.scene.SceneCondition;
import com.tuya.smart.home.sdk.bean.scene.SceneTask;
import com.tuya.smart.home.sdk.callback.ITuyaResultCallback;
import com.tuya.smart.scene.business.api.ITuyaSceneBusinessService;
import com.tuya.smart.utils.ToastUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuyaChangjingActivity extends BaseActivity {

    @BindView(R.id.bt_add)
    Button bt_add;
    @BindView(R.id.bt_edit)
    Button bt_edit;

    private ITuyaSceneBusinessService iTuyaSceneBusinessService;
    private AbsBizBundleFamilyService mServiceByInterface;

    private static final int ADD_SCENE_REQUEST_CODE = 1001;
    private static final int EDIT_SCENE_REQUEST_CODE = 1002;

    private List<SceneBean> sceneBeans = new ArrayList<>();

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TuyaChangjingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_changjing;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("智能场景");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        iTuyaSceneBusinessService = MicroContext.findServiceByInterface(ITuyaSceneBusinessService.class.getName());
        mServiceByInterface = MicroContext.getServiceManager().findServiceByInterface(AbsBizBundleFamilyService.class.getName());

        if (mServiceByInterface.getCurrentHomeId() == 0) {
            return;
        }
        TuyaHomeSdk.getSceneManagerInstance().getSceneList(mServiceByInterface.getCurrentHomeId(), new ITuyaResultCallback<List<SceneBean>>() {
            @Override
            public void onSuccess(List<SceneBean> result) {
                sceneBeans = result;
                Y.e("我是多少啊啊啊啊啊" + result.size());
                for (int i = 0; i < result.size(); i++) {
                    SceneBean sceneBean = result.get(i);
                    String name = sceneBean.getName();
                    String arrowIconUrl = sceneBean.getArrowIconUrl();
                    String coverIcon = sceneBean.getCoverIcon();
                    String background = sceneBean.getBackground();
                    String code = sceneBean.getCode();
                    String displayColor = sceneBean.getDisplayColor();
                    long disableTime = sceneBean.getDisableTime();
                    int matchType = sceneBean.getMatchType();
                    boolean enabled = sceneBean.isEnabled();

                    Y.e("我的名字是      name:" + name +
                            "   arrowIconUrl:" + arrowIconUrl +
                            "   coverIcon:" + coverIcon +
                            "   background:" + background +
                            "   code:" + code +
                            "   displayColor:" + displayColor +
                            "   disableTime:" + disableTime +
                            "   matchType:" + matchType +
                            "   enabled:" + enabled);


                    List<SceneCondition> conditions = sceneBean.getConditions();
                    List<SceneTask> actions = sceneBean.getActions();

                    if (conditions == null) {
                        Y.e(name + "没有条件");
                    } else {
                        Y.e(name + "有了条件");
                        Y.e("conditions有多少 " + conditions.size());
                        for (int j = 0; j < conditions.size(); j++) {
                            SceneCondition sceneCondition = conditions.get(j);
                            int entityType = sceneCondition.getEntityType();
                            Integer condType = sceneCondition.getCondType();
                            String entityName = sceneCondition.getEntityName();
                            String iconUrl = sceneCondition.getIconUrl();

                            Y.e("sceneCondition我是什么啊啊  " +
                                    "   entityType:" + entityType +
                                    "   condType:" + condType +
                                    "   iconUrl:" + iconUrl +
                                    "   entityName:" + entityName);
                        }
                    }

                    if (actions == null) {
                        Y.e(name + "没有任务啦");
                    } else {
                        Y.e(name + "执行的任务  " + actions.size());
                        for (int j = 0; j < actions.size(); j++) {
                            SceneTask sceneTask = actions.get(j);
                            String entityName = sceneTask.getEntityName();
                            String devIcon = sceneTask.getDevIcon();
                            String defaultIconUrl = sceneTask.getDefaultIconUrl();
                            String deleteDevIcon = sceneTask.getDeleteDevIcon();
                            Y.e("克劳福德是否  " + entityName);
                            Y.e("devIcon:" + devIcon);
                            Y.e("defaultIconUrl:" + defaultIconUrl);
                            Y.e("deleteDevIcon:" + deleteDevIcon);
                        }
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMessage) {

            }
        });
    }


    private void addScene() {
        if (null != iTuyaSceneBusinessService && mServiceByInterface.getCurrentHomeId() != 0) {
            iTuyaSceneBusinessService.addScene(this, mServiceByInterface.getCurrentHomeId(), ADD_SCENE_REQUEST_CODE);
        }
    }


    private void editScene() {
//        if (!sceneBeans.isEmpty()) {
//            SceneBean sceneBean = sceneBeans.get(0);
//            if (null != iTuyaSceneBusinessService) {
//               iTuyaSceneBusinessService .editScene(TuyaChangjingActivity.this, mServiceByInterface.getCurrentHomeId(), sceneBean, EDIT_SCENE_REQUEST_CODE);
//            }
//        }
        TuyaHomeSdk.getSceneManagerInstance().getSceneList(mServiceByInterface.getCurrentHomeId(), new ITuyaResultCallback<List<SceneBean>>() {
            @Override
            public void onSuccess(List<SceneBean> result) {
                if (!result.isEmpty()) {
                    SceneBean sceneBean = result.get(0);
                    if (null != iTuyaSceneBusinessService) {
                        iTuyaSceneBusinessService.editScene(TuyaChangjingActivity.this, mServiceByInterface.getCurrentHomeId(), sceneBean, EDIT_SCENE_REQUEST_CODE);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMessage) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_SCENE_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    onAddSuc(data);
                }
                break;
            case EDIT_SCENE_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    onEditSuc(data);
                }
                break;
            default:
                break;
        }
    }

    /**
     * edit scene success
     *
     * @param data
     */
    private void onEditSuc(Intent data) {
        SceneBean sceneBean = (SceneBean) data.getSerializableExtra("sceneBean");
        if (null != sceneBean) {
            ToastUtil.shortToast(this, "Scene：" + sceneBean.getName() + "edit success!");
        }
    }

    /**
     * add scene success
     *
     * @param data
     */
    private void onAddSuc(Intent data) {
        SceneBean sceneBean = (SceneBean) data.getSerializableExtra("sceneBean");
        if (null != sceneBean) {
            ToastUtil.shortToast(this, "Scene：" + sceneBean.getName() + "create success!");
        }
    }

    @OnClick({R.id.bt_add, R.id.bt_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                addScene();
                break;
            case R.id.bt_edit:
                editScene();
                break;
        }
    }
}
