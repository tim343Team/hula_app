package com.hula.myapplication.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.dao.BothLikeDao;
import com.hula.myapplication.dao.EventAttendDao;

import java.util.List;

public class BothLikeAdapter extends BaseQuickAdapter<BothLikeDao, BaseViewHolder> {

    public BothLikeAdapter(int layoutResId, @Nullable List<BothLikeDao> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BothLikeDao item) {

    }
}
