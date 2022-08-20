package com.hula.myapplication.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

import androidx.annotation.NonNull;

public class CenterDynamicDrawableSpan extends DynamicDrawableSpan {

    private final Drawable drawable;

    public CenterDynamicDrawableSpan(Drawable drawable) {
        this.drawable = drawable;
    }

    //默认底部对齐，我们居中对齐
    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        int targetY = top + (bottom - top) / 2 - drawable.getBounds().height() / 2;

        int nowTransY = bottom - drawable.getBounds().bottom - paint.getFontMetricsInt().descent;

        canvas.save();
        canvas.translate(0, nowTransY - targetY);
        super.draw(canvas, text, start, end, x, top, y, bottom, paint);
        canvas.restore();
    }

    @Override
    public Drawable getDrawable() {
        return drawable;
    }

}