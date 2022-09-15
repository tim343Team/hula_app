package com.hula.myapplication.view.home.adapter.home;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.dao.home.ProfilesItem;
import com.hula.myapplication.dao.home.RecommendedDao;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.ArrayList;
import java.util.Objects;

public class RecommendedBuddiesViewData extends AbsMultiItemViewData {
    private final RecommendedDao data;

    public RecommendedBuddiesViewData(RecommendedDao data) {
        super(R.layout.home_item_recommended);
        this.data = data;
    }

    @Override
    public void convert(BaseViewHolder helper) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        if (recyclerView.getAdapter() == null) {

            BaseQuickAdapter<ProfilesItem, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<ProfilesItem, BaseViewHolder>(R.layout.item_recommended) {
                @Override
                protected void convert(BaseViewHolder helper, ProfilesItem item) {
                    helper.setText(R.id.tv_name, item.getDisplayName());
                    ImageView view = helper.getView(R.id.iv);
                    Glide.with(view)
                            .load(item.getProfile())
                            .apply(RequestOptions.circleCropTransform())
                            .into(view);
                }
            };
            baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });
            baseQuickAdapter.setNewData(data.getProfiles());
            recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext(), RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(baseQuickAdapter);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecommendedBuddiesViewData)) return false;
        RecommendedBuddiesViewData that = (RecommendedBuddiesViewData) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
