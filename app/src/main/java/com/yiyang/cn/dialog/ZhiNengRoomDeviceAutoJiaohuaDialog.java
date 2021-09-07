package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.ISelectTimeCallback;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.view.WheelView;
import com.yiyang.cn.R;
import com.yiyang.cn.inter.OnItemClickListener;
import com.yiyang.cn.inter.OnItemDialogClickListener;
import com.yiyang.cn.optiobsPickerView.PickerOptions;
import com.yiyang.cn.optiobsPickerView.WheelTime;
import com.yiyang.cn.optiobsPickerView.listener.OnOptionsSelectChangeListener;
import com.yiyang.cn.optiobsPickerView.view.WheelOptions;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ZhiNengRoomDeviceAutoJiaohuaDialog<T> implements View.OnClickListener {

    private Context context;
    private String title = "";
    private List<String> oneList;
    private List<String> twoList;
    private OnItemDialogClickListener onItemDialogClickListener;
    private Dialog mDialog;
    private View view;
    private DisplayMetrics dm;
    private WindowManager windowManager;
    private int mStyle = R.style.ExitDialogStyle;
    private TextView tv_cancel, tv_title, tv_submit;
    protected PickerOptions mPickerOptions;
    private WheelOptions<T> wheelOptions;
    private List<T> options1Items = null;
    private List<T> options2Items = null;
    private List<T> options3Items = null;
    int first = 0;
    int second = 0;
    int third = 0;

    public ZhiNengRoomDeviceAutoJiaohuaDialog(Context context, String title) {
        this.context = context;
        this.title = title;
        mPickerOptions = new PickerOptions();
        initView();
    }

    public void setOnItemDialogClickListener(OnItemDialogClickListener onItemDialogClickListener) {
        this.onItemDialogClickListener = onItemDialogClickListener;
    }

    private void initView() {
        mDialog = new Dialog(context, mStyle);
        view = LayoutInflater.from(context).inflate(R.layout.layout_zhineng_room_device_time_dialog, null);
        dm = new DisplayMetrics();
        tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_title = view.findViewById(R.id.tv_title);
        tv_submit = view.findViewById(R.id.tv_submit);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        Window window = mDialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        mDialog.getWindow().setAttributes(lp);
        mDialog.setContentView(view);
        tv_title.setText(title);
        // ----滚轮布局
        final LinearLayout optionsPicker = view.findViewById(R.id.optionspicker);
        optionsPicker.setBackgroundColor(mPickerOptions.bgColorWheel);

        wheelOptions = new WheelOptions<>(optionsPicker, mPickerOptions.isRestoreItem);
        wheelOptions.setCyclic(false, false, false);
        if (mPickerOptions.optionsSelectChangeListener == null) {
            wheelOptions.setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                @Override
                public void onOptionsSelectChanged(int options1, int options2, int options3) {
                    if (options1Items != null) {
                        first = options1;
                    }
                    if (options2Items != null) {
                        second = options2;
                    }
                    if (options3Items != null) {
                        third = options3;
                    }
                }
            });
        }
        wheelOptions.setDividerColor(Color.rgb(229, 229, 229));
        wheelOptions.setTextColorCenter(context.getResources().getColor(R.color.blue)); //设置选中项文字颜色
        wheelOptions.setTextContentSize(17);
        wheelOptions.setLineSpacingMultiplier(2.0f);//行间距
        tv_cancel.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_submit:
                onItemDialogClickListener.onClick(view, first, second, third);
                dismiss();
                break;
        }
    }

    public void setSelectOptions(int option1, int option2) {
        mPickerOptions.option1 = option1;
        mPickerOptions.option2 = option2;
        reSetCurrentItems();
    }

    public void setSelectOptions(int option1, int option2, int option3) {
        mPickerOptions.option1 = option1;
        mPickerOptions.option2 = option2;
        mPickerOptions.option3 = option3;
        reSetCurrentItems();
    }

    private void reSetCurrentItems() {
        if (wheelOptions != null) {
            wheelOptions.setCurrentItems(mPickerOptions.option1, mPickerOptions.option2, mPickerOptions.option3);
        }
    }

    public void setPicker(List<T> options1Items) {
        this.setPicker(options1Items, null, null);
    }

    public void setPicker(List<T> options1Items, List<T> options2Items) {
        this.setPicker(options1Items, options2Items, null);
    }

    public void setPicker(List<T> options1Items,
                          List<T> options2Items,
                          List<T> options3Items) {

        wheelOptions.setNPicker(options1Items, options2Items, options3Items);
        this.options1Items = options1Items;
        this.options2Items = options2Items;
        this.options3Items = options3Items;
        reSetCurrentItems();
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
