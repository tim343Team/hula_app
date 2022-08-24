package com.hula.myapplication.view.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class MutiAdapter extends BaseQuickAdapter<AbsMultiItemViewData, BaseViewHolder> {


    @Override
    protected int getDefItemViewType(int position) {
        return getData().get(position).getItemType();
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));
    }

    public MutiAdapter() {
        super(0);
    }

    @Override
    protected void convert(BaseViewHolder helper, AbsMultiItemViewData item) {
        item.convert(helper);
    }

}