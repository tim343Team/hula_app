package com.hula.myapplication.view.home.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.dao.home.EventsItem;
import com.hula.myapplication.dao.home.SubEventsItem;
import com.hula.myapplication.util.BusinessUtils;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.view.home.EventsDetailActivity;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;
import tim.com.libnetwork.utils.ScreenUtils;

public class FeaturedEventViewData extends AbsMultiItemViewData {
    private final DataItemDao data;
    private final String title;
    private ServiceProfile service = HService.getService(ServiceProfile.class);

    public FeaturedEventViewData(DataItemDao data) {
        super(R.layout.home_item_featured_event);
        this.data = data;
        this.title = "Featured Event";
    }

    @Override
    public void convert(BaseViewHolder grouphelper) {
        grouphelper.setText(R.id.tv_title, title);
        ViewPager2 viewPager2 = grouphelper.getView(R.id.viewpager);
        if (viewPager2.getAdapter() == null || viewPager2.getAdapter().getItemCount() != data.getEvents().size()) {
            BaseQuickAdapter<EventsItem, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<EventsItem, BaseViewHolder>(R.layout.item_featured_event) {
                @Override
                protected void convert(BaseViewHolder helper, EventsItem item) {
                    List<SubEventsItem> subEvents = item.getSubEvents();
                    if (subEvents == null || subEvents.isEmpty()) {
                        return;
                    }
                    SubEventsItem subEventsItem = subEvents.get(0);
                    Glide.with(helper.itemView)
                            .load(BusinessUtils.getFirst(subEventsItem.getImageLink()))
                            .into((ImageView) helper.getView(R.id.iv));
                    helper.setText(R.id.tv_interesterd, item.getSearchCount() + " interested");
                    helper.setText(R.id.tv_name, title);
                    helper.setText(R.id.tv_title, item.getName());
                    helper.setText(R.id.tv_title1, "Present by " + item.getVendorName());
                    helper.setText(R.id.tv_time, item.getCreatedAt());
                    helper.setText(R.id.tv_des, subEventsItem.getDescription());
                    helper.getView(R.id.tv_find_buddy).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            findBuddyV2(item.getId());
                        }
                    });
                }
            };
            baseQuickAdapter.setNewData(data.getEvents());
            baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(view.getContext(), EventsDetailActivity.class);
                    intent.putExtra("id", data.getEvents().get(position).getId());
                    view.getContext().startActivity(intent);
                }
            });
            viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            viewPager2.setAdapter(baseQuickAdapter);
            viewPager2.setPageTransformer(new MarginPageTransformer(ScreenUtils.dip2px(viewPager2.getContext(), 8F)));
        }
    }

    private void findBuddyV2(int id) {
        ToastUtil.showLoading("");
        String userId = service.getUserId();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user_id", userId);
        hashMap.put("event_id", id);
        WonderfulOkhttpUtils.get()
                .url(UrlFactory.findBuddyV2() + HUtils.toGetUri(hashMap))
                .build()
                .getCall()
                .enqueue(new GsonWalkDogCallBack<RemoteData<Object>>() {
                    @Override
                    protected void onRes(RemoteData<Object> data) {
                        ToastUtil.showLoading("");
                        ToastUtil.showSuccessToast("Find Buddy Success");
                    }

                    @Override
                    protected void onFail(Exception e) {
                        super.onFail(e);
                        ToastUtil.showLoading("");
                    }
                });
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeaturedEventViewData)) return false;
        FeaturedEventViewData that = (FeaturedEventViewData) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
