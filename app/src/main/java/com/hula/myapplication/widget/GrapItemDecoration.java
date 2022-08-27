package com.hula.myapplication.widget;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hula.myapplication.app.RootApplication;
import com.hula.myapplication.util.HUtilScreen;

public class GrapItemDecoration extends RecyclerView.ItemDecoration {
    private final int scape;

    public GrapItemDecoration(int grapdp) {
        scape = HUtilScreen.dp2px(RootApplication.getApp(), grapdp);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        RecyclerView.Adapter<?> adapter = parent.getAdapter();

        if (layoutManager == null || adapter == null) {
            return;
        }
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;

            int childAdapterPosition = parent.getChildAdapterPosition(view);
            if (childAdapterPosition == adapter.getItemCount() - 1) {
                return;
            }
            if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                outRect.set(0, 0, 0, scape);
            } else {
                outRect.set(0, 0, scape, 0);
            }
        }

    }
}
