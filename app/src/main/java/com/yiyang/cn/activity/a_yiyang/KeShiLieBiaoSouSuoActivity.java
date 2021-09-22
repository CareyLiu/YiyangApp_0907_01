package com.yiyang.cn.activity.a_yiyang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.flyco.roundview.RoundRelativeLayout;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;

import butterknife.BindView;

public class KeShiLieBiaoSouSuoActivity extends BaseActivity {
    @BindView(R.id.iv_icon_sousuo)
    ImageView ivIconSousuo;
    @BindView(R.id.rrl_sousuo)
    RoundRelativeLayout rrlSousuo;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.et_text)
    EditText etText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etText.setFocusable(true);
        showInput(etText);
       // initAdapter();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_keshiliebiaosousuo;

    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, KeShiLieBiaoSouSuoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 显示键盘
     *
     * @param et 输入焦点
     */
    public void showInput(final EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏键盘
     */
    public void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


}
