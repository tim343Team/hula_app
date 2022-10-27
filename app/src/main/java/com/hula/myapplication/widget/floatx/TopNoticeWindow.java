package com.hula.myapplication.widget.floatx;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import com.hula.myapplication.util.HUtilScreen;

public class TopNoticeWindow extends MoveAbleWindow {

    private int top = -1;
    private final int[] moveXY = new int[]{0, 0};
    private ValueAnimator animator;
    private DismissRun dismissRun = new DismissRun();

    class DismissRun implements Runnable{

        private final Handler handler = new Handler();
        void dismissDelay5(){
            removeDismiss();
            handler.postDelayed(this,5000);
        }
        void removeDismiss(){
            handler.removeCallbacks(this);
        }

        @Override
        public void run() {
            dismiss();
        }
    }

    public TopNoticeWindow(View contentView) {
        super(contentView);
    }


    public void show(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        View contentView = getContentView();
        contentView.setVisibility(View.INVISIBLE);
        top = HUtilScreen.dp2px(activity, 70);
        showAtLocation(decorView, Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, top);
        dismissRun.dismissDelay5();
    }

    @Override
    public void dismiss() {
        int yofShowing = top;
        int endY = -getContentView().getMeasuredHeight();
        startAnimator(yofShowing, endY);
    }



    private void startAnimator(int startY, int endY) {
        if (animator != null) {
            animator.cancel();
        }
        animator = ValueAnimator.ofInt(startY, endY);
        animator.addUpdateListener(animation -> update(0, (int) animation.getAnimatedValue(), -1, -1));
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (startY < endY) {
                    getContentView().setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (startY > endY) {
                    TopNoticeWindow.super.dismiss();
                }
            }
        });
        animator.start();
    }

    @Override
    protected void touchDown(MotionEvent event) {
        super.touchDown(event);
        dismissRun.removeDismiss();
    }

    @Override
    protected int[] touchMove(MotionEvent e1, MotionEvent e2) {
        float touchToViewTop = e1.getY();
        float rawY = e2.getRawY();
        int moveY = (int) Math.min(rawY - touchToViewTop, top);
        moveXY[0] = 0;
        moveXY[1] = moveY;
        return moveXY;
    }

    @Override
    protected void touchUp(int lastx, int lasty) {
        dismissRun.dismissDelay5();
        int yofShowing = top;
        int endY = -getContentView().getMeasuredHeight();
        if (yofShowing - lasty < 100) {
            endY = yofShowing;
        }
        startAnimator(lasty, endY);
    }


    @Override
    protected void onShow() {
        super.onShow();
        int measuredHeight = getContentView().getMeasuredHeight();
        startAnimator(-measuredHeight, top);
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        if (animator != null) {
            animator.cancel();
        }
        dismissRun.removeDismiss();
    }
}
