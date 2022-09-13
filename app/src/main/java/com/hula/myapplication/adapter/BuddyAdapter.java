package com.hula.myapplication.adapter;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.BuddyInvitesDao;
import com.hula.myapplication.dao.SubBuddyInvitesDao;
import com.hula.myapplication.dao.SubCategoriesDao;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.utils.DateTimeUtil;

public class BuddyAdapter extends BaseQuickAdapter<BuddyInvitesDao, BaseViewHolder> {
    private final List<Integer> expand = new ArrayList<>();

    public BuddyAdapter(List<BuddyInvitesDao> data) {
        super(R.layout.item_invite_root,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BuddyInvitesDao item) {
        boolean isExpand = expand.contains(helper.getAbsoluteAdapterPosition());
        helper.setText(R.id.tv_title, item.getName());
        helper.getView(R.id.ll_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpand) {
                    expand.remove(Integer.valueOf(helper.getAbsoluteAdapterPosition()));
                } else {
                    expand.add(helper.getAbsoluteAdapterPosition());
                }
                notifyItemChanged(helper.getAbsoluteAdapterPosition());
            }
        });
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        if (isExpand) {
            helper.getView(R.id.iv_down).setRotation(0);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
            helper.getView(R.id.iv_down).setRotation(180);
        }
        if (recyclerView.getLayoutManager() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext(), LinearLayoutManager.VERTICAL, false));
        }
        boolean needRefresh = false;
        SubBuddyAdapter adapter = (SubBuddyAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            needRefresh = true;
        }
//        if (adapter != null && !adapter.getData().isEmpty()) {
//            SubBuddyInvitesDao data = adapter.getData().get(0);
//            if (data.getCategory().getId() != item.getId()) {
//                needRefresh = true;
//            }
//        }
        if (needRefresh) {
            adapter = new SubBuddyAdapter(R.layout.item_invite_sub,item.getData());
            recyclerView.setAdapter(adapter);
        }
    }
}
