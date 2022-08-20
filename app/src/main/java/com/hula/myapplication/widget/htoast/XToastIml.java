//package com.hula.myapplication.widget.htoast;
//
//import android.app.Activity;
//import android.app.Application;
//import android.view.View;
//import android.view.WindowManager;
//import androidx.annotation.NonNull;
//
//public class XToastIml implements HToast{
//    XToast<XToast<?>> xToastXToast;
//
//
//    public XToastIml(Activity activity){
//        xToastXToast = new XToast<>(activity);
//        xToastXToast.setWindowType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
//    }
//
//    public XToastIml(Application application){
//        xToastXToast = new XToast<>(application);
//    }
//
//    @Override
//    public void setContentView(@NonNull View contentView) {
//        xToastXToast.setContentView(contentView);
//    }
//
//    @Override
//    public void show() {
//        xToastXToast.show();
//    }
//
//    @Override
//    public void cancel() {
//        xToastXToast.cancel();
//    }
//
//    @Override
//    public void setDuration(int duration) {
//        xToastXToast.setDuration(duration);
//    }
//
//    @Override
//    public void setOutsideTouchable(boolean touchAble) {
//        xToastXToast.setOutsideTouchable(touchAble);
//    }
//}
