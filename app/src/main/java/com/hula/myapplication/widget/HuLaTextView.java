
package com.hula.myapplication.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannedString;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import com.hula.myapplication.R;


public class HuLaTextView extends androidx.appcompat.widget.AppCompatTextView {
    private final Drawable startHintDrawble;
    private OnClickListener endDrawableListener;
    private boolean handDrawableTouch = false;

    public HuLaTextView(Context context) {
        this(context, null);
    }

    public HuLaTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public HuLaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER_VERTICAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HuLaTextView);
        startHintDrawble = typedArray.getDrawable(R.styleable.HuLaTextView_tv_hintStartDrawable);
        typedArray.recycle();
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        CharSequence hint = getHint();
        if (startHintDrawble != null && !(hint instanceof SpannedString)) {
            if (hint == null) {
                hint = "";
            }
            int intrinsicWidth = startHintDrawble.getIntrinsicWidth();
            int intrinsicHeight = startHintDrawble.getIntrinsicHeight();
            int realh = (int) (getPaint().getFontMetrics().descent - getPaint().getFontMetrics().ascent);
            int realw = realh * intrinsicWidth / intrinsicHeight;
            startHintDrawble.setBounds(0, 0, realw, realh);
            SpannableString hintSpan = new SpannableString(" x  " + hint);
            hintSpan.setSpan(new CenterDynamicDrawableSpan(startHintDrawble), 1, 2, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
            setHint(hintSpan);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public void setEndDrawableListener(OnClickListener endDrawableListener) {
        this.endDrawableListener = endDrawableListener;
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

    private boolean endDrawableTouchHand(MotionEvent event) {
        if (endDrawableListener == null || getCompoundDrawables()[2] == null) {
            handDrawableTouch = false;
            return false;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            int w = getCompoundDrawablePadding() * 2 + getCompoundDrawables()[2].getIntrinsicWidth();
            if (x >= getWidth() - w) {
                handDrawableTouch = true;
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (handDrawableTouch && endDrawableListener != null) {
                endDrawableListener.onClick(this);
            }
        }
        return handDrawableTouch;
    }
}
