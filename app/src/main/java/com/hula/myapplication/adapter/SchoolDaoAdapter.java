package com.hula.myapplication.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.NewInfoDao;
import com.hula.myapplication.dao.SchoolDao;
import com.hula.myapplication.widget.HuCallBack1;

import java.util.List;

public class SchoolDaoAdapter extends BaseQuickAdapter<SchoolDao, BaseViewHolder> {

    public SchoolDaoAdapter(List<SchoolDao> data) {
        super(R.layout.item_school,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SchoolDao item) {

    }
}
