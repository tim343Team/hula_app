package com.hula.myapplication.adapter;

import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.BuddyInvitesDao;

import java.util.ArrayList;
import java.util.List;

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
        RelativeLayout relativeLayout = helper.getView(R.id.ll_root);
        if (isExpand) {
            helper.getView(R.id.iv_down).setRotation(0);
            relativeLayout.setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.iv_down).setRotation(180);
            relativeLayout.setVisibility(View.GONE);
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
//        if (needRefresh) {
//            adapter = new SubBuddyAdapter(R.layout.item_invite_sub,item.getData(),item.getType());
//            recyclerView.setAdapter(adapter);
//        }
        adapter = new SubBuddyAdapter(R.layout.item_invite_buddy_sub,item.getData(),item.getType());
        recyclerView.setAdapter(adapter);
    }
}
