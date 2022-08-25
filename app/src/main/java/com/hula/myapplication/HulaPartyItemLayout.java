package com.hula.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HulaPartyItemLayout extends FrameLayout {
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
}
