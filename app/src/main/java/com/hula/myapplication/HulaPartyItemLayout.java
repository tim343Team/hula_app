package com.hula.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.hula.myapplication.dao.home.EventsItem;
import com.hula.myapplication.dao.home.SubEventsItem;
import com.hula.myapplication.util.BusinessUtils;
import com.hula.myapplication.util.HUtils;

import java.util.List;

public class HulaPartyItemLayout extends RelativeLayout {
    private boolean showFindBuddy;
    private boolean showPersion;

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


    @SuppressLint("SetTextI18n")
    public void setData(EventsItem data) {
        TextView tv1 = childView.findViewById(R.id.tv_1);
        TextView tv2 = childView.findViewById(R.id.tv_2);
        TextView tv3 = childView.findViewById(R.id.tv_3);
        TextView tv4 = childView.findViewById(R.id.tv_4);
        ImageView iv = childView.findViewById(R.id.iv);

        HUtils.runCatch(() -> tv1.setText(data.getSubEvents().get(0).getSubCategory().get(0).getCategory().getName()));
        tv2.setText(data.getName());
        tv3.setText(data.getCreatedAt());
        tv4.setText(data.getSearchCount() + " interested");
        HUtils.runCatch(() -> Glide.with(iv)
                .load(BusinessUtils.getFirst(data.getSubEvents().get(0).getImageLink()))
                .into(iv));

        TextView viewById = findViewById(R.id.tv_find_buddy);
        ImageView viewById1 = findViewById(R.id.iv_persion);
        TextView viewById2 = findViewById(R.id.tv_persion_info);

        if (showPersion){
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

        if (showFindBuddy){
            viewById.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 接口请求
                }
            });
        }
    }
}
