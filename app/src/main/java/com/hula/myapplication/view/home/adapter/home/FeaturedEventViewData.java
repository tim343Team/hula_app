package com.hula.myapplication.view.home.adapter.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.util.GsonUtils;
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
import com.hula.myapplication.view.home.vm.Repository;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;
import tim.com.libnetwork.utils.ScreenUtils;

public class FeaturedEventViewData extends AbsMultiItemViewData {
    private final DataItemDao data;
    private final String title;
    private ServiceProfile service = HService.getService(ServiceProfile.class);
    private Repository repository = new Repository();
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
                            repository.findBuddyV2(subEventsItem.getId());
                        }
                    });
                    ImageView iv_love = helper.getView(R.id.iv_love);
                    Runnable runnable = () -> {
                        boolean liked = subEventsItem.isLiked();
                        if (liked) {
                            iv_love.setImageResource(R.mipmap.icon_like);
                        } else {
                            iv_love.setImageResource(R.mipmap.icon_unlove_fff);
                        }
                    };
                    runnable.run();
                    iv_love.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean liked = subEventsItem.isLiked();
                            String url = UrlFactory.getLike();
                            if (liked) {
                                url = UrlFactory.getUnLike();
                            }
                            ToastUtil.showLoading("");
                            Map<String, Object> hashbody = new HashMap<>();
                            hashbody.put("user_id", service.getUserId());
                            hashbody.put("event_id", subEventsItem.getId());
                            WonderfulOkhttpUtils.postJson()
                                    .url(url)
                                    .body(GsonUtils.toJson(hashbody))
                                    .addHeader("Content-Type","application/json")
                                    .build()
                                    .getCall()
                                    .enqueue(new GsonWalkDogCallBack<RemoteData<Object>>() {
                                        @Override
                                        protected void onRes(RemoteData<Object> d) throws Exception {
                                            subEventsItem.setLiked(!subEventsItem.isLiked());
                                            runnable.run();
                                            ToastUtil.hideLoading();
                                        }

                                        @Override
                                        protected void onFail(Exception e) {
                                            super.onFail(e);
                                            ToastUtil.hideLoading();
                                        }
                                    });
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
