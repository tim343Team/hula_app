package com.hula.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hula.myapplication.R;

public class HuLaActionBarMenu extends FrameLayout {
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
        if (drawable != null) {
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(drawable);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.gravity = Gravity.CENTER;
            addView(imageView, layoutParams);
        }
        if (string != null) {
            TextView textView = new TextView(context);
            textView.setTextColor(Color.BLACK);
            textView.setText(string);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.gravity = Gravity.CENTER;
            addView(textView, layoutParams);
        }

        ta.recycle();
    }
}
