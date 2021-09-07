package com.yiyang.cn.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.DiagnosisActivity;
import com.yiyang.cn.activity.HomeActivity;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_Notify;

import static com.yiyang.cn.config.MyApplication.getAppContext;

public class GuZhangUtils {

    public static void guZhangFaLaEr(String guZhangStr, Context context) {//温志微家的

        switch (guZhangStr) {
            case "1":
                // 05	风暖加热器：点火塞开路或短路
                playMusic(context, R.raw.ch_sound5);
                break;
            case "2":

                //  08	风暖加热器：风机传感器开路或短路
                playMusic(context, R.raw.ch_sound8);
                break;
            case "3":
                //  08	风暖加热器：风机传感器开路或短路
                playMusic(context, R.raw.ch_sound8);
                break;
            case "5":
                //      02	风暖加热器：油泵开路或短路
                playMusic(context, R.raw.ch_sound2);
                break;
            case "6":
                //  02	风暖加热器：油泵开路或短路
                playMusic(context, R.raw.ch_sound2);
                break;
            case "7":
                //01	风暖加热器：电压过低或过高
                playMusic(context, R.raw.ch_sound1);
                break;
            case "8":
                //01	风暖加热器：电压过低或过高
                playMusic(context, R.raw.ch_sound1);
                break;
            case "9":
                //06	风暖加热器：入风口传感器高温报警
                playMusic(context, R.raw.ch_sound6);
                break;
            case "10":
                // 04	风暖加热器：入风口传感器开路或短路
                playMusic(context, R.raw.ch_sound4);
                break;
            case "11":
                // 04	风暖加热器：入风口传感器开路或短路
                playMusic(context, R.raw.ch_sound4);
                break;
            case "12":
                //11	风暖加热器：壳体高温报警
                playMusic(context, R.raw.ch_sound11);
                break;
            case "13":
                //03	风暖加热器：壳体温度传感器开路或短路
                playMusic(context, R.raw.ch_sound3);
                break;
            case "14":
                //03	风暖加热器：壳体温度传感器开路或短路
                playMusic(context, R.raw.ch_sound3);
                break;
            case "18":
                //18	风暖加热器：晶屏与主机失联故障
                playMusic(context, R.raw.ch_sound18);
                break;
            case "19":
                //09	风暖加热器：火焰熄灭故障
                //10	风暖加热器：点火失败故障
                playMusic(context, R.raw.falaer_19);
                break;
        }
    }

    public static void playMusic(Context context, int res) {


        Activity currentActivity = AppManager.getAppManager().currentActivity();
        if (currentActivity != null) {
            if (!currentActivity.getClass().getSimpleName().equals(DiagnosisActivity.class.getSimpleName())) {
                MyCarCaoZuoDialog_Notify myCarCaoZuoDialog_notify = new MyCarCaoZuoDialog_Notify(getAppContext(), new MyCarCaoZuoDialog_Notify.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {
                        // player.stop();
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }

                    }

                    @Override
                    public void clickRight() {
                        DiagnosisActivity.actionStart(context);
                        //SoundPoolUtils.soundPool.release();
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }

                    }
                }
                );

                myCarCaoZuoDialog_notify.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
                myCarCaoZuoDialog_notify.show();
                myCarCaoZuoDialog_notify.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }
                    }
                });

            }
        }


        SoundPoolUtils.soundPool(context, res);

    }


    public static void guZhangTongYong(String guZhangStr, Context context) {

        switch (guZhangStr) {
            case "1":
                playMusic(context, R.raw.ch_sound1);
                break;
            case "2":
                playMusic(context, R.raw.ch_sound2);
                break;
            case "3":
                playMusic(context, R.raw.ch_sound3);
                break;
            case "4":
                playMusic(context, R.raw.ch_sound4);
                break;
            case "5":
                playMusic(context, R.raw.ch_sound5);
                break;
            case "6":
                playMusic(context, R.raw.ch_sound6);
                break;
//            case "7":
//                playMusic(context, R.raw.ch_sound7);
//                break;
            case "8":
                playMusic(context, R.raw.ch_sound8);
                break;
            case "9":
                playMusic(context, R.raw.ch_sound9);
                break;
            case "10":
                playMusic(context, R.raw.ch_sound10);
                break;
            case "11":
                playMusic(context, R.raw.ch_sound11);
                break;
            case "15":
                playMusic(context, R.raw.ch_sound15_shuibeng);
                break;
            case "16":
                playMusic(context, R.raw.ch_sound16);
                break;
            case "18":
                playMusic(context, R.raw.ch_sound18);
                break;
        }
    }
}
