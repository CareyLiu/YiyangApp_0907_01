package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yiyang.cn.R;
import com.yiyang.cn.adapter.LanMuListAdapter;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class FaBuLanMuDialog extends Dialog {
    RecyclerView rlvList;
    LanMuListAdapter lanMuListAdapter;

    List<String> list = new ArrayList<>();

    public FaBuLanMuDialog(@NonNull Context context) {
        super(context);

        initview();
    }

    private void initview() {
        setContentView(R.layout.layout_fabulanmu_dialog);
        list.add("打工赚钱");
        list.add("大逆不道");
        rlvList = findViewById(R.id.rlv_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rlvList.setLayoutManager(linearLayoutManager);
        LanMuListAdapter lanMuListAdapter = new LanMuListAdapter(R.layout.item_lanmu, list);
        rlvList.setAdapter(lanMuListAdapter);
        lanMuListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }


}
