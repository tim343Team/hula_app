package com.hula.myapplication.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.SubBuddyInvitesDao;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.widget.HuCallBack1;

import java.util.List;

public class SubBuddyAdapter extends BaseQuickAdapter<SubBuddyInvitesDao, BaseViewHolder> {

    public SubBuddyAdapter(int layoutResId,List<SubBuddyInvitesDao> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubBuddyInvitesDao item) {

    }
}
