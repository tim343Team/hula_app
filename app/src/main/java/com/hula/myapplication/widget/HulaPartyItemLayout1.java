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

public class HulaPartyItemLayout1 extends RelativeLayout {
    private boolean showFindBuddy;
    private boolean showPersion;
    private ServiceProfile profile = HService.getService(ServiceProfile.class);
    private Repository repository = new Repository();

    public HulaPartyItemLayout1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleRes) {
        super(context, attrs, defStyleRes);
        init();
    }

    public HulaPartyItemLayout1(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HulaPartyItemLayout1(@NonNull Context context) {
        this(context, null);
    }

    public View childView;

    private void init() {
        childView = LayoutInflater.from(getContext()).inflate(R.layout.view_party1, this, true);
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
    }


    @SuppressLint("SetTextI18n")
    public void setData(SubEventsItem data) {
        TextView tv1 = childView.findViewById(R.id.tv_1);
        TextView tv2 = childView.findViewById(R.id.tv_2);
        TextView tv3 = childView.findViewById(R.id.tv_3);
        TextView tv4 = childView.findViewById(R.id.tv_4);
        TextView tv5 = childView.findViewById(R.id.tv_5);
        TextView tv6 = childView.findViewById(R.id.tv_6);
        TextView tv7 = childView.findViewById(R.id.tv_7);
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
                ToastUtil.showLoading("");
                Map<String, Object> hashbody = new HashMap<>();
                hashbody.put("user_id", profile.getUserId());
                hashbody.put("event_id", data.getId());
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

        tv1.setText(data.getName());
        HUtils.runCatch(() -> tv2.setText(data.getSubCategory().get(0).getCategory().getName()));
        tv3.setText(data.getStarting());
        tv4.setText(data.getLocation());
        tv5.setText("$ " + data.getPrice());
        tv6.setText(data.getInterestedCount() + " interested");
        tv7.setText(data.getJoinedMatchingPoolCount() + " buddies in the matching pool");

        HUtils.runCatch(() -> Glide.with(iv)
                .load(BusinessUtils.getFirst(data.getImageLink()))
                .into(iv));

        TextView findBuddy = findViewById(R.id.tv_find_buddy);
        findBuddy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.findBuddyV2(data.getId());
            }
        });


    }
}
