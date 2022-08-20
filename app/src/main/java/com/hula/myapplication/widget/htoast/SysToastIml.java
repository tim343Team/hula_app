package com.hula.myapplication.widget.htoast;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class SysToastIml implements HToast{
    Toast sysToast;


    public SysToastIml(Context context){
        sysToast = new Toast(context);
        sysToast.setGravity(Gravity.CENTER,0,0);
    }


    @Override
    public void setContentView(@NonNull View contentView) {
        sysToast.setView(contentView);
    }

    @Override
    public void show() {
        sysToast.show();
    }

    @Override
    public void cancel() {
        sysToast.cancel();
    }

    @Override
    public void setDuration(int duration) {
        sysToast.setDuration(duration);
    }

    @Override
    public void setOutsideTouchable(boolean touchAble) {
        //nothing
    }
}
