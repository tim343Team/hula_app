package com.hula.myapplication.view.home.adapter.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.home.GroupDao;
import com.hula.myapplication.dao.home.GroupItemDao;
import com.hula.myapplication.dao.home.UsersItem;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.view.home.EventsDetailActivity;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.Objects;

public class GroupsYouMightLikeViewData extends AbsMultiItemViewData {
    private final GroupDao data;

    public GroupsYouMightLikeViewData(GroupDao data) {
        super(R.layout.home_item_groups_you_might_like);
        this.data = data;
    }

    @Override
    public void convert(BaseViewHolder helper) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        if (recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() != data.getGroups().size()) {
            BaseQuickAdapter<GroupItemDao, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<GroupItemDao, BaseViewHolder>(R.layout.item_groups_you_might_like) {
                @Override
                protected void convert(BaseViewHolder helper, GroupItemDao item) {
                    ImageView view1 = helper.getView(R.id.iv_1);
                    ImageView view2 = helper.getView(R.id.iv_2);
                    helper.setText(R.id.tv_num, item.getUserNum() + " people");
                    helper.setText(R.id.tv_title, item.getName());
                    UsersItem user1 = CollectionUtils.getOrNull(item.getUsers(), 0);
                    if (user1 != null) {
                        view1.setVisibility(View.VISIBLE);
                        Glide.with(view1)
                                .load(user1.getProfile())
                                .apply(RequestOptions.circleCropTransform())
                                .into(view1);
                    }else {
                        view1.setVisibility(View.INVISIBLE);
                    }

                    UsersItem user2 = CollectionUtils.getOrNull(item.getUsers(), 1);
                    if (user2 != null) {
                        view2.setVisibility(View.VISIBLE);
                        Glide.with(view2)
                                .load(user2.getProfile())
                                .apply(RequestOptions.circleCropTransform())
                                .into(view2);
                    }else {
                        view2.setVisibility(View.INVISIBLE);

                    }

                }
            };
            baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(view.getContext(), EventsDetailActivity.class);
                    intent.putExtra("id", data.getGroups().get(position).getId());
                    view.getContext().startActivity(intent);
                }
            });
            baseQuickAdapter.setNewData(data.getGroups());
            recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext(), RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(baseQuickAdapter);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupsYouMightLikeViewData)) return false;
        GroupsYouMightLikeViewData that = (GroupsYouMightLikeViewData) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
