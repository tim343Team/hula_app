package com.hula.myapplication.util;

import android.os.Handler;
import android.os.Looper;

import com.hula.myapplication.widget.HuCallBack1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HUtils {
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void runOnUi(Runnable runnable) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            handler.post(runnable);
        } else {
            runnable.run();
        }
    }

    public static void runCatch(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception ignored) {
        }
    }
}
