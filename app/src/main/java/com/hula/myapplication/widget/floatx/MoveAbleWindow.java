package com.hula.myapplication.widget.floatx;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ScreenUtils;
import com.hula.myapplication.R;

public class MoveAbleWindow extends PopupWindow {

    private int lastx = 0;
    private int lasty = 0;

    public MoveAbleWindow(View contentView) {
        super(contentView.getContext());
        setClippingEnabled(false);
        setTouchable(true);
        setFocusable(false);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(contentView.getContext(), R.color.transparent)));
        MoveAbleView moveAbleView = new MoveAbleView(contentView.getContext());
        moveAbleView.addView(contentView);
        setContentView(moveAbleView);
        int screenWidth = ScreenUtils.getAppScreenWidth();
        int screenHeight = ScreenUtils.getAppScreenHeight();
        moveAbleView.measure(View.MeasureSpec.makeMeasureSpec(screenWidth, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(screenHeight, View.MeasureSpec.AT_MOST));
        setWidth(moveAbleView.getMeasuredWidth());
        setHeight(moveAbleView.getMeasuredHeight());
        setTouchInterceptor(new View.OnTouchListener() {
            private boolean hand = false;
            private MotionEvent eventDown = null;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    hand = false;
                    lastx = 0;
                    lasty = 0;
                    eventDown = MotionEvent.obtain(event);
                    touchDown(event);
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (eventDown != null && checkTouchSlop(event, eventDown)) {
                        hand = true;
                    }
                    if (hand) {
                        int[] ints = touchMove(eventDown, event);
                        update(ints[0], ints[1], -1, -1);
                        lasty = ints[1];
                        lastx = ints[0];
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (hand) {
                        touchUp(lastx, lasty);
                    }
                    eventDown = null;
                }
                return hand;
            }
        });
    }



    private boolean checkTouchSlop(MotionEvent eventmove, MotionEvent eventDown) {
        float rawX = eventmove.getRawX();
        float rawY = eventmove.getRawY();
        float rawX1 = eventDown.getRawX();
        float rawY1 = eventDown.getRawY();
        return Math.abs(rawX - rawX1) > 8 || Math.abs(rawY - rawY1) > 8;
    }

    protected void touchDown(MotionEvent event) {
    }



    protected int[] touchMove(MotionEvent e1, MotionEvent e2) {
        return new int[]{0, 0};
    }

    protected void touchUp(int lastx, int lasty) {

    }

    protected void onShow() {
    }

    protected void onDismiss() {
    }


    class MoveAbleView extends FrameLayout {
        public MoveAbleView(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            onShow();
        }


        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            onDismiss();
        }
    }

}
