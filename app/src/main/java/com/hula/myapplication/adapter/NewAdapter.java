package com.hula.myapplication.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.NewInfoDao;

import java.util.List;

public class NewAdapter extends BaseQuickAdapter<NewInfoDao, BaseViewHolder> {

    public NewAdapter(int layoutResId, @Nullable List<NewInfoDao> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewInfoDao item) {
        helper.setText(R.id.tv_time,"");
        helper.setText(R.id.tv_content,"");
    }

    OnclickListenerItem itemClick;

    public void OnclickListenerItem(OnclickListenerItem itemClick) {
        this.itemClick = itemClick;
    }

    public interface OnclickListenerItem {
        void click(int position);
    }
}
