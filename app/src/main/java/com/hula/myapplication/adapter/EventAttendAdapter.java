package com.hula.myapplication.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.EventAttendDao;

import java.util.List;

public class EventAttendAdapter extends BaseQuickAdapter<EventAttendDao, BaseViewHolder> {

    public EventAttendAdapter(int layoutResId, @Nullable List<EventAttendDao> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventAttendDao item) {
        TextView tv = helper.getView(R.id.tv);
        ImageView iv = helper.getView(R.id.iv);
        tv.setText("test");
    }
}
