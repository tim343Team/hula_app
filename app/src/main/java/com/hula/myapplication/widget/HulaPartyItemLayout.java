package com.hula.myapplication.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.hula.myapplication.R;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.home.EventsItem;
import com.hula.myapplication.dao.home.SubEventsItem;
import com.hula.myapplication.util.BusinessUtils;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.view.home.vm.Repository;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class HulaPartyItemLayout extends RelativeLayout {
    private boolean showFindBuddy;
    private boolean showPersion;
    private ServiceProfile profile = HService.getService(ServiceProfile.class);
    private Repository repository = new Repository();

    public HulaPartyItemLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleRes) {
        super(context, attrs, defStyleRes);
        init();
    }

    public HulaPartyItemLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HulaPartyItemLayout(@NonNull Context context) {
        this(context, null);
    }

    public View childView;

    private void init() {
        childView = LayoutInflater.from(getContext()).inflate(R.layout.view_party, this, true);
    }


    public void setShow(boolean showFindBuddy, boolean showPersion) {
        this.showFindBuddy = showFindBuddy;
        this.showPersion = showPersion;
        View viewById = findViewById(R.id.tv_find_buddy);
        View viewById1 = findViewById(R.id.iv_persion);
        View viewById2 = findViewById(R.id.tv_persion_info);
        if (showFindBuddy) {
            viewById.setVisibility(View.VISIBLE);
        } else {
            viewById.setVisibility(View.INVISIBLE);
        }
        if (showPersion) {
            viewById1.setVisibility(View.VISIBLE);
            viewById2.setVisibility(View.VISIBLE);
        } else {
            viewById1.setVisibility(View.INVISIBLE);
            viewById2.setVisibility(View.INVISIBLE);
        }
    }

    public void setEventShow() {
        View viewById = findViewById(R.id.iv_love);
        View viewById1 = findViewById(R.id.tv_3);
        View viewById2 = findViewById(R.id.tv_4);
        viewById.setVisibility(View.GONE);
        viewById1.setVisibility(View.GONE);
        viewById2.setVisibility(View.GONE);
    }


    @SuppressLint("SetTextI18n")
    public void setData(EventsItem data) {
        TextView tv1 = childView.findViewById(R.id.tv_1);
        TextView tv2 = childView.findViewById(R.id.tv_2);
        TextView tv3 = childView.findViewById(R.id.tv_3);
        TextView tv4 = childView.findViewById(R.id.tv_4);
        ImageView iv = childView.findViewById(R.id.iv);
        ImageView iv_love = childView.findViewById(R.id.iv_love);
        Runnable runnable = () -> {
            boolean liked = data.isLiked();
            if (liked) {
                iv_love.setImageResource(R.mipmap.icon_like);
            } else {
                iv_love.setImageResource(R.mipmap.icon_unlove_fff);
            }
        };
        runnable.run();
        iv_love.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean liked = data.isLiked();
                String url = UrlFactory.getLike();
                if (liked) {
                    url = UrlFactory.getUnLike();
                }
                SubEventsItem subEventsItem = CollectionUtils.getOrNull(data.getSubEvents(), 0);
                if (subEventsItem == null) {
                    return;
                }
                ToastUtil.showLoading("");
                Map<String, Object> hashbody = new HashMap<>();
                hashbody.put("user_id", profile.getUserId());
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
                                data.setLiked(!data.isLiked());
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

        HUtils.runCatch(() -> tv1.setText(data.getSubEvents().get(0).getSubCategory().get(0).getCategory().getName()));
        tv2.setText(data.getName());
        tv3.setText(data.getCreatedAt());
        HUtils.runCatch(() -> tv4.setText(data.getSubEvents().get(0).getInterestedCount() + " interested"));
        HUtils.runCatch(() -> Glide.with(iv)
                .load(BusinessUtils.getFirst(data.getSubEvents().get(0).getImageLink()))
                .into(iv));

        TextView viewById = findViewById(R.id.tv_find_buddy);
        ImageView viewById1 = findViewById(R.id.iv_persion);
        TextView viewById2 = findViewById(R.id.tv_persion_info);

        if (showPersion) {
            HUtils.runCatch(() -> {
                List<SubEventsItem> subEvents = data.getSubEvents();
                if (subEvents == null || subEvents.isEmpty()) {
                    return;
                }
                Glide.with(HulaPartyItemLayout.this)
                        .load(subEvents.get(0).getProfile())
                        .into(viewById1);
                viewById2.setText("Recommended by" + subEvents.get(0).getFirstName());
            });
        }

        if (showFindBuddy) {
            viewById.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    SubEventsItem subEventsItem = CollectionUtils.getOrNull(data.getSubEvents(), 0);
                    if (subEventsItem == null) {
                        return;
                    }
                    repository.findBuddyV2(subEventsItem.getId());
                }
            });
        }

    }
}
