package com.hula.myapplication.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;

import com.blankj.utilcode.util.KeyboardUtils;
import com.hula.myapplication.util.HUtilScreen;

public class VCEditText extends AppCompatEditText implements TextWatcher {

    public float gap;
    public int vcnum = 6;
    public int pattern = 1;//图案可以底线（0）或者是方块（1）。
    public int selectColor;
    public int defColor;

    public ColorStateList colorStateList;//当前选择的颜色

    public int rectanglePatternSIZE = 5;
    public int linePatternSIZE;

    private int radius;

    public VCEditText(Context context) {
        super(context);
        init();
        initCleanPaint();
    }

    private void init() {
        gap = HUtilScreen.dp2px(getContext(), 9);
        radius = HUtilScreen.dp2px(getContext(), 15);
        linePatternSIZE = HUtilScreen.dp2px(getContext(), 1);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(vcnum)});
        defColor = Color.parseColor("#8E73D3");

        selectColor = Color.parseColor("#8E73D3");

        int[] colors = new int[]{selectColor, defColor};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_focused};
        states[1] = new int[]{};
        colorStateList = new ColorStateList(states, colors);
    }

    public VCEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initCleanPaint();
    }

    public VCEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initCleanPaint();
    }

    Paint cleanpaint;
    Paint bgPaint;

    void initCleanPaint() {
        cleanpaint = new Paint();
        cleanpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        cleanpaint.setColor(0xFFFFFFFF);
        // cleanpaint.setColor(AttUtil.getTypeValueColor(getContext(), android.R.attr.background));

        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(defColor);
        switch (pattern) {
            case 0:
                bgPaint.setStyle(Paint.Style.FILL);
                break;
            case 1:
                bgPaint.setStyle(Paint.Style.STROKE);
                bgPaint.setStrokeWidth(linePatternSIZE);
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        _vcwidth = (getMeasuredWidth() - (vcnum - 1) * gap) / vcnum;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        //1、我们将AppCompatEditText的文字和背景都用一层白布清理掉
        //  canvas.drawPaint(cleanpaint);
        //2、我们根据当前的文字重新绘制一个自己想要的界面
        //2.1、先画背景
        canvas.save();
        drawPatterns(canvas);
        canvas.restore();
        //2.2、画文字
        canvas.save();
        drawText(canvas);
        canvas.restore();
    }

    /**
     * 画文字
     *
     * @param
     */
    private void drawText(Canvas canvas) {
        char[] chars = getText().toString().toCharArray();
        if (chars.length == 0) {
            return;
        }

        TextPaint paint = getPaint();
        float dx = _vcwidth / 2 - paint.measureText(String.valueOf(chars[0])) / 2;
        float dy = getHeight()/2F + getFontHeight(paint)/2F - paint.getFontMetrics().descent;
        canvas.translate(dx, dy);

        for (char aChar : chars) {
            canvas.drawText(String.valueOf(aChar), 0, 0, paint);
            canvas.translate(_vcwidth + gap, 0);
        }
    }

    private float _vcwidth;

    /**
     * 画当前的背景文案
     */
    private void drawPatterns(Canvas canvas) {
        //测算背景的宽高

        RectF rect = new RectF();
        switch (pattern) {
            case 0:
                rect.set(linePatternSIZE, getMeasuredHeight() - linePatternSIZE, _vcwidth, getHeight() - linePatternSIZE);
                break;
            case 1:
                rect.set(linePatternSIZE, linePatternSIZE, _vcwidth - linePatternSIZE, getHeight() - linePatternSIZE/2F);
                break;
        }
        for (int i = 0; i < vcnum; i++) {
            int index = getText().toString().length() - 1;
            if (index >= i) {
                bgPaint.setColor(colorStateList.getColorForState(new int[]{android.R.attr.state_focused}, bgPaint.getColor()));
            } else {
                bgPaint.setColor(colorStateList.getColorForState(new int[]{}, bgPaint.getColor()));
            }
            canvas.drawRoundRect(rect, radius, radius, bgPaint);
            canvas.translate(_vcwidth + gap, 0);
        }

    }

    /**
     * @return 返回指定的文字高度
     */
    public float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        //文字基准线的下部距离-文字基准线的上部距离 = 文字高度
        return fm.descent - fm.ascent;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        setSelection(getText().length());//设置光标位置始终在最后
        return b;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() == vcnum) {
            KeyboardUtils.hideSoftInput(this);
        }
    }
}
