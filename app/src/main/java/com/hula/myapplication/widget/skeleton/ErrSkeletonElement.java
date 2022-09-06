package com.hula.myapplication.widget.skeleton;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.hula.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ErrSkeletonElement extends SkeletonElement {
    public static ErrSkeletonElement getInstance(View.OnClickListener onClickListener) {
        ErrSkeletonElement errSkeletonElement = new ErrSkeletonElement();
        errSkeletonElement.onClickListener = onClickListener;
        return errSkeletonElement;
    }

    private View view;
    public View.OnClickListener onClickListener;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected View create(ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_err_skeleton, viewGroup, false);
        view.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onClick(v);
            }
        });
        return view;
    }

    @Override
    protected void onAdd(Object argment) {
        super.onAdd(argment);
        if (argment instanceof Exception) {
            TextView textView = view.findViewById(R.id.tv_err);
            textView.setText(((Exception) argment).getMessage());
        }
    }
}
