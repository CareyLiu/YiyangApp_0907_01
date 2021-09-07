package com.yiyang.cn.activity.tuya_device.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shuinuan.Y;
import com.yiyang.cn.activity.tuya_device.TuyaBaseCameraDeviceActivity;
import com.yiyang.cn.activity.tuya_device.TuyaBaseDeviceActivity;
import com.yiyang.cn.activity.tuya_device.camera.ceshi.TimePieceBean;
import com.yiyang.cn.activity.tuya_device.camera.model.TuyaPlayDataModel;
import com.yiyang.cn.activity.tuya_device.camera.model.TuyaPlayPieceModel;
import com.yiyang.cn.activity.tuya_device.utils.manager.TuyaDeviceManagerTwo;
import com.tuya.smart.android.camera.timeline.OnBarMoveListener;
import com.tuya.smart.android.camera.timeline.TimeBean;
import com.tuya.smart.android.camera.timeline.TimelineUnitMode;
import com.tuya.smart.android.camera.timeline.TuyaTimelineView;
import com.tuya.smart.camera.camerasdk.typlayer.callback.AbsP2pCameraListener;
import com.tuya.smart.camera.camerasdk.typlayer.callback.OperationDelegateCallBack;
import com.tuya.smart.camera.middleware.p2p.ITuyaSmartCameraP2P;
import com.tuya.smart.camera.middleware.p2p.TuyaSmartCameraP2PFactory;
import com.tuya.smart.camera.middleware.widget.AbsVideoViewCallback;
import com.tuya.smart.camera.middleware.widget.TuyaCameraView;
import com.tuya.smart.camera.utils.AudioUtils;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraHuifangActivity extends TuyaBaseCameraDeviceActivity implements OnBarMoveListener {

    @BindView(R.id.camera_video_view)
    TuyaCameraView mVideoView;
    @BindView(R.id.iv_switch_shengyin)
    ImageView ivSwitchShengyin;
    @BindView(R.id.iv_switch_quanping)
    ImageView ivSwitchQuanping;
    @BindView(R.id.tuya_time_line)
    TuyaTimelineView timelineView;

    private ITuyaSmartCameraP2P mCameraP2P;
    private int p2pType;
    private DeviceBean deviceBeen;
    private TuyaPlayPieceModel playPieceModel;
    private List<TimeBean> items = new ArrayList<>();
    private int playCount;
    private boolean isPlayback;

    protected Map<String, List<String>> mBackDataMonthCache;
    protected Map<String, List<TimePieceBean>> mBackDataDayCache;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, int p2pType) {
        Intent intent = new Intent(context, CameraHuifangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("p2pType", p2pType);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera_huifang;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("回放");
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
        initCamera();
        initTimeView();
    }

    private void initTimeView() {
        timelineView.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        timelineView.setUnitMode(TimelineUnitMode.Mode_3600);
        timelineView.setOnBarMoveListener(this);
    }

    private void init() {
        p2pType = TuyaDeviceManagerTwo.getDeviceManager().getP2pType();
        deviceBeen = TuyaDeviceManagerTwo.getDeviceManager().getDeviceBeen();
        mBackDataMonthCache = new HashMap<>();
        mBackDataDayCache = new HashMap<>();
    }

    private void initCamera() {
        mCameraP2P = TuyaSmartCameraP2PFactory.createCameraP2P(p2pType, deviceBeen.getDevId());
        mVideoView.setViewCallback(new AbsVideoViewCallback() {
            @Override
            public void onCreated(Object o) {
                super.onCreated(o);
                //渲染视图构造完成时回调
                if (null != mCameraP2P) {
                    mCameraP2P.generateCameraView(o);
                }
            }
        });
        mVideoView.createVideoView(p2pType);
        mCameraP2P.registerP2PCameraListener(p2pCameraListener);
        cameraConnect();
    }

    private AbsP2pCameraListener p2pCameraListener = new AbsP2pCameraListener() {
        @Override
        public void onReceiveFrameYUVData(int i, ByteBuffer byteBuffer, ByteBuffer byteBuffer1, ByteBuffer byteBuffer2, int i1, int i2, int i3, int i4, long l, long l1, long l2, Object o) {
            super.onReceiveFrameYUVData(i, byteBuffer, byteBuffer1, byteBuffer2, i1, i2, i3, i4, l, l1, l2, o);
            timelineView.setCurrentTimeInMillisecond(l * 1000L);
        }
    };

    private void cameraConnect() {
        showProgressDialog();
        if (!mCameraP2P.isConnecting()) {
            mCameraP2P.connect(deviceBeen.getDevId(), new OperationDelegateCallBack() {
                @Override
                public void onSuccess(int sessionId, int requestId, String data) {
                    getPlyaData();
                }

                @Override
                public void onFailure(int sessionId, int requestId, int errCode) {
                    dismissProgressDialog();
                }
            });
        }
    }

    private void getPlyaData() {
        mCameraP2P.queryRecordDaysByMonth(2020, 12, new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                Y.e("获取播放日期 " + data);
                TuyaPlayDataModel model = JSON.parseObject(data, TuyaPlayDataModel.class);
                List<String> dataDays = model.getDataDays();
                mBackDataMonthCache.put(mCameraP2P.getMonthKey(), dataDays);
                if (dataDays.size() > 0) {
                    int day = Y.getInt(dataDays.get(dataDays.size() - 1));
                    mCameraP2P.queryRecordTimeSliceByDay(2020, 12, day, new OperationDelegateCallBack() {
                        @Override
                        public void onSuccess(int sessionId, int requestId, String data) {
                            playZhunbei(data);
                        }

                        @Override
                        public void onFailure(int sessionId, int requestId, int errCode) {
                            dismissProgressDialog();
                            Y.e("获取片段失败   " + errCode);
                        }
                    });
                }
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                dismissProgressDialog();
                Y.e("获取日期失败");
            }
        });
    }

    private void playZhunbei(String data) {
        playPieceModel = JSON.parseObject(data, TuyaPlayPieceModel.class);
        items = playPieceModel.getItems();
        timelineView.setCurrentTimeConfig(items.get(0).getEndTimeInMillisecond());
        timelineView.setRecordDataExistTimeClipsList(items);
        if (items.size() != 0) {
            playCount = 0;
            int startTime = items.get(playCount).getStartTime();
            int endTime = items.get(playCount).getEndTime();
            playback(startTime, endTime, startTime);
        } else {
            dismissProgressDialog();
        }
    }

    @Override
    public void onBarMove(long screenLeftTime, long screenRightTime, long currentTime) {

    }

    @Override
    public void onBarMoveFinish(long startTime, long endTime, long currentTime) {
        Y.e("移动结束了么  " + currentTime);
        timelineView.setCanQueryData();
        timelineView.setQueryNewVideoData(false);
        if (startTime != -1 && endTime != -1) {
            showProgressDialog();
            playback((int) startTime, (int) endTime, (int) currentTime);
        }
    }

    @Override
    public void onBarActionDown() {

    }

    private void playback(int startTime, int endTime, int playTime) {
        mCameraP2P.startPlayBack(startTime,
                endTime,
                playTime, new OperationDelegateCallBack() {
                    @Override
                    public void onSuccess(int sessionId, int requestId, String data) {
                        dismissProgressDialog();
                        isPlayback = true;
                    }

                    @Override
                    public void onFailure(int sessionId, int requestId, int errCode) {
                        dismissProgressDialog();
                        isPlayback = false;
                    }
                }, new OperationDelegateCallBack() {
                    @Override
                    public void onSuccess(int sessionId, int requestId, String data) {
                        isPlayback = false;
                    }

                    @Override
                    public void onFailure(int sessionId, int requestId, int errCode) {
                        isPlayback = false;
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mCameraP2P) {
            AudioUtils.getModel(this);
            resumePlayBack();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isPlayback) {
            pausePlayBack();
        }
        AudioUtils.changeToNomal(this);
    }

    private void pausePlayBack() {
        mCameraP2P.pausePlayBack(null);
    }

    private void resumePlayBack() {
        mCameraP2P.resumePlayBack(null);
    }
}
