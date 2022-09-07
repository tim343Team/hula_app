package com.hula.myapplication.widget.skeleton;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.hula.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class GridLayoutSkeletonElement extends SkeletonElement {
    private BaseQuickAdapter<String, BaseViewHolder> adapter;
    private boolean isadd = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected View create(ViewGroup viewGroup) {
        RecyclerView view = new RecyclerView(viewGroup.getContext());
        view.setLayoutManager(new GridLayoutManager(viewGroup.getContext(), 2));
        view.setOnTouchListener((v, event) -> true);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.view_list_skeleton) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                ShimmerFrameLayout layout = helper.getView(R.id.shimmerlayout);
                if (isadd) {
                    layout.startShimmer();
                } else {
                    layout.stopShimmer();
                }
            }
        };
        adapter.setNewData(list);
        view.setAdapter(adapter);
        return view;
    }

    @Override
    protected void onAdd(Object argment) {
        super.onAdd(argment);
        isadd = true;
        adapter.notifyItemRangeChanged(0,adapter.getItemCount());
    }

    @Override
    protected void onRemove() {
        super.onRemove();
        isadd = false;
        adapter.notifyItemRangeChanged(0,adapter.getItemCount());
    }
}
