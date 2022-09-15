package com.hula.myapplication.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.imageview.ShapeableImageView;
import com.hula.myapplication.R;

public class DelectImageView extends ShapeableImageView {

    private final Drawable delectDrawable;
    private OnClickListener delectOnClick;
    private boolean showDelect = false;
    private boolean handDrawableTouch = false;
    private final Rect rect = new Rect();


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
            rect.top = 0;
            rect.left = 0;
            rect.right = delectDrawable.getIntrinsicWidth();
            rect.bottom = delectDrawable.getIntrinsicHeight();
            delectDrawable.setBounds(rect.left, rect.top, rect.right, rect.bottom);
        }
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight() + rect.width()/2, getPaddingBottom() + rect.height()/2);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showDelect && delectDrawable != null) {
            canvas.save();
            canvas.translate(getWidth() - rect.width(), getHeight() - rect.height());
            delectDrawable.draw(canvas);
            canvas.restore();
        }
    }


    private boolean endDrawableTouchHand(MotionEvent event) {
        if (!showDelect) {
            handDrawableTouch = false;
            return false;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            int w = getWidth() - rect.width();
            float y = event.getY();
            int h = getHeight() - rect.height();
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
