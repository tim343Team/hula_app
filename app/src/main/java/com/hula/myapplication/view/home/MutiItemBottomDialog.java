package com.hula.myapplication.view.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.util.HUtilScreen;
import com.hula.myapplication.widget.ColorItemDecoration;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;
import com.hula.myapplication.widget.adapter.MutiAdapter;
import com.hula.myapplication.widget.dialog.BaseBottomDialog;

import java.util.ArrayList;
import java.util.List;

public class MutiItemBottomDialog extends BaseBottomDialog {

    public List<AbsMultiItemViewData> absMultiItemViewData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = new RecyclerView(requireContext());
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        recyclerView.setBackgroundResource(R.drawable.shape_radius_top20_fff);
        recyclerView.setPadding(HUtilScreen.dp2px(requireContext(), 25), 0, HUtilScreen.dp2px(requireContext(), 25), 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new ColorItemDecoration());
        MutiAdapter mutiAdapter = new MutiAdapter();
        recyclerView.setAdapter(mutiAdapter);
        if (absMultiItemViewData == null || absMultiItemViewData.isEmpty()) {
            dismiss();
        } else {
            mutiAdapter.setNewData(absMultiItemViewData);
        }
        return recyclerView;
    }

    public static class SimTextMultiItemViewData extends AbsMultiItemViewData {

        private final String text;
        private final int color;
        private final int size;
        private final boolean isBold;
        private final View.OnClickListener clickListener;

        public SimTextMultiItemViewData(String text,View.OnClickListener clickListener) {
            this(text, Color.BLACK,clickListener);
        }

        public SimTextMultiItemViewData(String text, int color,View.OnClickListener clickListener) {
            this(text, color, 14, false, clickListener);
        }

        public SimTextMultiItemViewData(String text, int color, int sizeSp, boolean isBold, View.OnClickListener clickListener) {
            super(R.layout.item_simple_text);
            this.text = text;
            this.color = color;
            this.size = sizeSp;
            this.isBold = isBold;
            this.clickListener = clickListener;
        }

        @Override
        public void convert(BaseViewHolder helper) {
            TextView tv = helper.getView(R.id.tv);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv.getLayoutParams();
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            tv.setPadding(0, HUtilScreen.dp2px(tv.getContext(), 5), 0, HUtilScreen.dp2px(tv.getContext(), 5));
            tv.setLayoutParams(layoutParams);
            tv.setText(text);
            tv.setTextColor(color);
            tv.setTextSize(size);
            tv.getPaint().setFakeBoldText(isBold);
            helper.itemView.setOnClickListener(clickListener);
        }
    }
}
