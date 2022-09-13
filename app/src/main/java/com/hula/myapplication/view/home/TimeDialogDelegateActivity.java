package com.hula.myapplication.view.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.hula.myapplication.R;

import java.lang.reflect.Field;

/**
 * 时间选择代理，如果在dialog弹出时间选择框有问题，所以这么实现
 */
public class TimeDialogDelegateActivity extends Activity {

    private static DialogFactory dialogFactoryRef;

    public interface DialogFactory {
        SingleDateAndTimePickerDialog factory(TimeDialogDelegateActivity activity);
    }

    public static void display(Activity activity, DialogFactory dialogFactory) {
        Intent intent = new Intent(activity, TimeDialogDelegateActivity.class);
        dialogFactoryRef = dialogFactory;
        activity.startActivity(intent);
    }

    private BottomSheetHelper helper;
    private BottomSheetHelper.Listener realHelplistener;
    private SingleDateAndTimePickerDialog dialog;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_dialog_delegate);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);
        if (savedInstanceState != null || dialogFactoryRef == null) {
            dialogFactoryRef = null;
            finish();
            return;
        }
        dialog = dialogFactoryRef.factory(TimeDialogDelegateActivity.this);
        dialogFactoryRef = null;


        Class<?> aClass = dialog.getClass();
        try {
            //bottomSheetHelper
            Field bottomSheetHelperField = aClass.getDeclaredField("bottomSheetHelper");
            bottomSheetHelperField.setAccessible(true);
            helper = (BottomSheetHelper) bottomSheetHelperField.get(dialog);
            if (helper != null) {
                Field helperlistenerField = helper.getClass().getDeclaredField("listener");
                helperlistenerField.setAccessible(true);
                realHelplistener = (BottomSheetHelper.Listener) helperlistenerField.get(helper);
                if (realHelplistener != null) {
                    helper.setListener(new BottomSheetHelper.Listener() {
                        @Override
                        public void onOpen() {
                            realHelplistener.onOpen();
                        }

                        @Override
                        public void onLoaded(View view) {
                            realHelplistener.onLoaded(view);
                        }

                        @Override
                        public void onClose() {
                            realHelplistener.onClose();
                            finish();
                        }
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (helper == null || realHelplistener == null) {
            finish();
            return;
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                dialog.display();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
