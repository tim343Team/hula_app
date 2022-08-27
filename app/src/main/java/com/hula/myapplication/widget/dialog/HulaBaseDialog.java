package com.hula.myapplication.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.hula.myapplication.util.HUtilScreen;


public class HulaBaseDialog extends AppCompatDialog {

    public int marginHorizontal = -1;
    public int mGravity = Gravity.CENTER;

    public HulaBaseDialog(Context context) {
        this(context, 0);
    }

    public HulaBaseDialog(Context context, int theme) {
        super(context, theme);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (marginHorizontal == -1) {
            marginHorizontal = HUtilScreen.dp2px(getContext(), 50F);
        }
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = HUtilScreen.getScreenWH(getContext()).widthPixels - marginHorizontal;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = mGravity;
            window.setAttributes(params);
        }
    }


}
