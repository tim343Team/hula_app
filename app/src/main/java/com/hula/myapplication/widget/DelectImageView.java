package com.hula.myapplication.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.imageview.ShapeableImageView;
import com.hula.myapplication.R;

public class DelectImageView extends androidx.appcompat.widget.AppCompatImageView {

    private final Drawable delectDrawable;
    private OnClickListener delectOnClick;
    private boolean showDelect = true;
    private Runnable showDelectIconRun = null;
    private boolean handDrawableTouch = false;


    public void setShowDelect(boolean showDelect) {
        this.showDelect = showDelect;
        if (!showDelect){
            getOverlay().remove(delectDrawable);
            return;
        }
        showDelectIconRun = () -> {
            ViewParent parent = getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).setClipChildren(false);
                ((ViewGroup) parent).setClipToPadding(false);
                int width = getWidth();
                int height = getHeight();
                int w = delectDrawable.getIntrinsicWidth();
                int h = delectDrawable.getIntrinsicHeight();
                Rect location = new Rect();
                location.left = width - w / 2;
                location.top = height - h / 2;
                location.right = location.left + w;
                location.bottom = location.top + h;
                delectDrawable.setBounds(location.left, location.top, location.right, location.bottom);
                getOverlay().add(delectDrawable);
            }
        };
        if (getWidth() != 0) {
            showDelectIconRun.run();
            showDelectIconRun = null;
        }
    }

    public void setDelectOnClick(OnClickListener delectOnClick) {
        this.delectOnClick = delectOnClick;
    }

    public DelectImageView(Context context) {
        this(context, null);
    }

    public DelectImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DelectImageView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        delectDrawable = ContextCompat.getDrawable(context, R.mipmap.icon_delect);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (showDelectIconRun != null && changed) {
            showDelectIconRun.run();
            showDelectIconRun = null;
        }
    }


    private boolean endDrawableTouchHand(MotionEvent event) {
        if (!showDelect) {
            handDrawableTouch = false;
            return false;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            int w = getWidth() - delectDrawable.getIntrinsicWidth();
            float y = event.getY();
            int h = getHeight() - delectDrawable.getIntrinsicHeight();
            if (x >= w && y >= h) {
                handDrawableTouch = true;
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (handDrawableTouch && delectOnClick != null) {
                delectOnClick.onClick(this);
            }
        }
        return handDrawableTouch;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean hand = endDrawableTouchHand(event) || super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            handDrawableTouch = false;
        }
        return hand;
    }
}
