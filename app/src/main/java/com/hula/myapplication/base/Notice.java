package com.hula.myapplication.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.hula.myapplication.R;
import com.hula.myapplication.app.ActivityLifecycleManager;
import com.hula.myapplication.util.HUtilScreen;
import com.hula.myapplication.widget.floatx.TopNoticeWindow;

public class Notice {

    public static void showEventAlert(EventAlertConfig config) {
        config.safe();
        Activity activity = ActivityLifecycleManager.get().curActivity();
        if (activity == null) {
            return;
        }
        View view = LayoutInflater.from(activity).inflate(R.layout.alert_event, null, false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) (ScreenUtils.getAppScreenWidth() * 0.8), -2);
        int dp8 = HUtilScreen.dp2px(activity,8f);
        layoutParams.setMargins(dp8,dp8,dp8,dp8);
        view.setLayoutParams(layoutParams);
        TopNoticeWindow topNoticeWindow = new TopNoticeWindow(view);
        ImageView iv = view.findViewById(R.id.iv);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_subtitle = view.findViewById(R.id.tv_subtitle);
        TextView tv_btn = view.findViewById(R.id.tv_btn);
        Glide.with(iv)
                .load(config.url)
                .into(iv);
        tv_title.setText(config.title);
        tv_subtitle.setText(config.subtitle);
        tv_btn.setVisibility(View.INVISIBLE);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (config.touchClick != null) {
                    config.touchClick.run();
                }
                topNoticeWindow.dismiss();
            }
        });
        topNoticeWindow.show(activity);
    }


    public static class EventAlertConfig {
        private String url;
        private String title;
        private String subtitle;
        private Runnable touchClick;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setRunnable(Runnable touchClick) {
            this.touchClick = touchClick;
        }

        private void safe() {
            if (url == null) {
                url = "";
            }
            if (title == null) {
                title = "";
            }
            if (subtitle == null) {
                subtitle = "";
            }
        }
    }


}
