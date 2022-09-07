package com.hula.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hula.myapplication.R;

public class HuLaActionBarMenu extends FrameLayout {
    private TextView textView;
    private ImageView imageView;

    public HuLaActionBarMenu(@NonNull Context context) {
        this(context, null);
    }

    public HuLaActionBarMenu(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HuLaActionBarMenu(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HuLaActionBarMenu);
        Drawable drawable = ta.getDrawable(R.styleable.HuLaActionBarMenu_menu_src);
        String string = ta.getString(R.styleable.HuLaActionBarMenu_menu_text);
        int color = ta.getColor(R.styleable.HuLaActionBarMenu_menu_textColor, Color.BLACK);
        imageView = new ImageView(context);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = Gravity.CENTER;
        addView(imageView, layoutParams);
        textView = new TextView(context);
        textView.setTextColor(color);
        LayoutParams layoutParams1 = new LayoutParams(-2, -2);
        layoutParams1.gravity = Gravity.CENTER;
        addView(textView, layoutParams);
        setSrc(drawable);
        setText(string);
        ta.recycle();
    }

    private void setText(String string) {
        if (string != null) {
            textView.setText(string);
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }
    }

    private void setSrc(Drawable drawable) {
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.INVISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
