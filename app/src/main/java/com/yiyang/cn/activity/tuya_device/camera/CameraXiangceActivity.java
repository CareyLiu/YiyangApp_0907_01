package com.yiyang.cn.activity.tuya_device.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.TuyaBaseCameraDeviceActivity;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.camera.adapter.TuyaXiangceAdapter;
import com.yiyang.cn.activity.tuya_device.camera.model.TuyaFilesModel;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.util.phoneview.sample.ImageShowActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraXiangceActivity extends TuyaBaseCameraDeviceActivity {

    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_right_title)
    TextView tv_right_title;
    @BindView(R.id.tv_left_title)
    TextView tv_left_title;
    private List<TuyaFilesModel> filesModels = new ArrayList<>();
    private TuyaXiangceAdapter adapter;

    private boolean isXuanze;
    private boolean isQuanxuan;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CameraXiangceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera_xiangce;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        isXuanze = false;
        initAdapter();
    }

    private void initAdapter() {
        String picPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Camera/";
        filesModels = getFilesAllName(picPath);
        adapter = new TuyaXiangceAdapter(R.layout.item_tuya_camera_xiangce, filesModels);
        rv_content.setLayoutManager(new GridLayoutManager(mContext, 4));
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TuyaFilesModel model = filesModels.get(position);
                if (isXuanze) {
                    model.setSelect(!model.isSelect());
                    filesModels.set(position, model);
                    adapter.notifyDataSetChanged();

                    tv_right_title.setText("取消全选");
                    isQuanxuan = true;
                    for (int i = 0; i < filesModels.size(); i++) {
                        TuyaFilesModel ceModel = filesModels.get(i);
                        boolean select = ceModel.isSelect();
                        if (!select) {
                            tv_right_title.setText("全选");
                            isQuanxuan = false;
                        }
                    }
                } else {
                    String filePath = model.getFilePath();
                    int type = model.getType();
                    if (type == 1) {
                        ArrayList<String> imgs = new ArrayList<>();
                        imgs.add(filePath);
                        ImageShowActivity.actionStart(mContext, imgs);
                    } else {
                        Y.t("是录像");
                    }
                }
            }
        });
    }

    public List<TuyaFilesModel> getFilesAllName(String path) {
        //传入指定文件夹的路径
        File file = new File(path);
        File[] files = file.listFiles();
        List<TuyaFilesModel> filesModels = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getPath();
            if (fileName.contains(".png")) {
                TuyaFilesModel model = new TuyaFilesModel(1, fileName);
                filesModels.add(model);
            } else if (fileName.contains(".mp4")) {
                TuyaFilesModel model = new TuyaFilesModel(2, fileName);
                filesModels.add(model);
            }
        }
        return filesModels;

    }

    @OnClick({R.id.rl_back, R.id.tv_right_title, R.id.tv_left_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_right_title:
                clickRight();
                break;
            case R.id.tv_left_title:
                clickLeft();
                break;
        }
    }

    private void clickRight() {
        if (isXuanze) {
            if (isQuanxuan) {
                for (int i = 0; i < filesModels.size(); i++) {
                    TuyaFilesModel model = filesModels.get(i);
                    model.setSelect(false);
                    filesModels.set(i, model);
                }
                adapter.notifyDataSetChanged();

                tv_right_title.setText("全选");
            } else {
                for (int i = 0; i < filesModels.size(); i++) {
                    TuyaFilesModel model = filesModels.get(i);
                    model.setSelect(true);
                    filesModels.set(i, model);
                }
                adapter.notifyDataSetChanged();

                tv_right_title.setText("取消全选");
            }
            isQuanxuan = !isQuanxuan;
        } else {
            isXuanze = true;
            tv_left_title.setVisibility(View.VISIBLE);
            tv_right_title.setText("全选");
            rl_back.setVisibility(View.GONE);
            adapter.setSeletMode(true);
        }
    }

    private void clickLeft() {
        isXuanze = false;
        tv_left_title.setVisibility(View.GONE);
        tv_right_title.setText("选择");
        rl_back.setVisibility(View.VISIBLE);
        adapter.setSeletMode(false);
    }
}
