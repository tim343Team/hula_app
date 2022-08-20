package com.hula.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import com.hula.myapplication.R;
import com.hula.myapplication.util.HUtilScreen;

public class HulaSwitch extends SwitchCompat {
    private final Paint paint = new Paint();
    private int startY;

    public HulaSwitch(@NonNull Context context) {
        this(context, null);
    }

    public HulaSwitch(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.R.attr.switchStyle);
    }

    public HulaSwitch(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setThumbResource(R.drawable.ios_switch_thumb);
        setTrackResource(R.drawable.ios_switch_track_selector);
        paint.setTextSize(HUtilScreen.sp2px(getContext(), 7));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        startY = (int) (getMeasuredHeight() / 2 + (paint.getFontMetrics().descent - paint.getFontMetrics().ascent) / 2 - paint.getFontMetrics().descent);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerx = getPaddingStart() + (getWidth() - getPaddingEnd() - getPaddingStart() - getThumbDrawable().getIntrinsicWidth()) / 2;
        if (!isChecked()) {
            centerx += getThumbDrawable().getIntrinsicWidth();
        }
        if (isChecked()) {
            canvas.drawText("ON", centerx, startY, paint);
        } else {
            canvas.drawText("OFF", centerx, startY, paint);
        }
    }
}
