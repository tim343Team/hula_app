package com.hula.myapplication.view.home.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;

import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;
import com.hula.myapplication.widget.adapter.MutiAdapter;

import java.util.List;

public class DiffMutiAdapter extends MutiAdapter {


    private boolean mHeadAndEmptyEnable;
    private boolean mFootAndEmptyEnable;

    @Override
    public void setHeaderFooterEmpty(boolean isHeadAndEmpty, boolean isFootAndEmpty) {
        super.setHeaderFooterEmpty(isHeadAndEmpty, isFootAndEmpty);
        this.mHeadAndEmptyEnable = isFootAndEmpty;
        this.mFootAndEmptyEnable = isFootAndEmpty;
    }

    private final AsyncListDiffer<AbsMultiItemViewData> differ = new AsyncListDiffer<>(this, new DiffUtil.ItemCallback<AbsMultiItemViewData>() {
        @Override
        public boolean areItemsTheSame(@NonNull AbsMultiItemViewData oldItem, @NonNull AbsMultiItemViewData newItem) {
            return oldItem.equals(newItem);
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull AbsMultiItemViewData oldItem, @NonNull AbsMultiItemViewData newItem) {
            return oldItem.equals(newItem);
        }
    });

    @Override
    public int getItemCount() {
        int count;
        if (getEmptyViewCount() == 1) {
            count = 1;
            if (mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                count++;
            }
            if (mFootAndEmptyEnable && getFooterLayoutCount() != 0) {
                count++;
            }
        } else {
            count = getHeaderLayoutCount() + differ.getCurrentList().size() + getFooterLayoutCount() + getLoadMoreViewCount();
        }
        return count;
    }

    public void submit(List<AbsMultiItemViewData> viewdatas) {
        getData().clear();
        getData().addAll(viewdatas);
        differ.submitList(viewdatas);
    }
}
