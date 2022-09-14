package com.hula.myapplication.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.SubGroupInvitesDao;

import java.util.List;

public class SubGroupAdapter extends BaseQuickAdapter<SubGroupInvitesDao, BaseViewHolder> {
    private int type = 0;//1.normal 2.7天未失效消息 3.7天已失效

    public SubGroupAdapter(int layoutResId, List<SubGroupInvitesDao> data, int type) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, SubGroupInvitesDao item) {
        View llRequest = helper.getView(R.id.ll_request);
        View llInvite = helper.getView(R.id.ll_invite);
        View llGroupFull = helper.getView(R.id.ll_group_full);
        if (type == 1) {
            llRequest.setVisibility(View.VISIBLE);
            llInvite.setVisibility(View.GONE);
        } else if (type == 2) {
            llRequest.setVisibility(View.GONE);
            llInvite.setVisibility(View.VISIBLE);
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.click(helper.getAbsoluteAdapterPosition());
            }
        });
    }

    OnclickListenerItem itemClick;

    public void OnclickListenerItem(OnclickListenerItem itemClick) {
        this.itemClick = itemClick;
    }

    public interface OnclickListenerItem {
        void click(int position);
    }
}
