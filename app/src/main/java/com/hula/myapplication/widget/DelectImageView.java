package com.hula.myapplication.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.imageview.ShapeableImageView;
import com.hula.myapplication.R;
import com.hula.myapplication.util.HUtilScreen;

public class DelectImageView extends ShapeableImageView {

    private final Drawable delectDrawable;
    private OnClickListener delectOnClick;
    private boolean showDelect = false;
    private int delectMargin = 0;

    public void setShowDelect(boolean showDelect) {
        this.showDelect = showDelect;
        invalidate();
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
        if (delectDrawable != null) {
            delectDrawable.setBounds(0, 0, delectDrawable.getIntrinsicWidth(), delectDrawable.getIntrinsicHeight());
            delectMargin = HUtilScreen.dp2px(context,1F);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showDelect){
            canvas.translate(getWidth() - delectDrawable.getIntrinsicWidth() - delectMargin, getHeight() - delectDrawable.getIntrinsicHeight() - delectMargin);
            delectDrawable.draw(canvas);
        }

    }



    private boolean handDrawableTouch = false;

    private boolean endDrawableTouchHand(MotionEvent event) {
        if (!showDelect) {
            handDrawableTouch = false;
            return false;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            int w = getWidth() - delectDrawable.getIntrinsicWidth() - delectMargin;
            float y = event.getY();
            int h = getHeight() - delectDrawable.getIntrinsicHeight() - delectMargin;
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
