package com.hula.myapplication.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.SubBuddyInvitesDao;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.widget.HuCallBack1;

import java.util.List;

public class SubBuddyAdapter extends BaseQuickAdapter<SubBuddyInvitesDao, BaseViewHolder> {
    private int type = 0;//1.normal 2.7天未失效消息 3.7天已失效

    public SubBuddyAdapter(int layoutResId, List<SubBuddyInvitesDao> data, int type) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, SubBuddyInvitesDao item) {
        View llResponse = helper.getView(R.id.ll_response);
        View llReactive = helper.getView(R.id.ll_reactive);
        View llViewLess = helper.getView(R.id.ll_view_less);
        if (type == 1) {
            llResponse.setVisibility(View.VISIBLE);
            llReactive.setVisibility(View.GONE);
            llViewLess.setVisibility(View.GONE);
        } else if (type == 2) {
            llResponse.setVisibility(View.GONE);
            llReactive.setVisibility(View.VISIBLE);
            llViewLess.setVisibility(View.GONE);
        } else if (type == 3) {
            llResponse.setVisibility(View.GONE);
            llReactive.setVisibility(View.GONE);
            llViewLess.setVisibility(View.VISIBLE);
        }
    }
}
