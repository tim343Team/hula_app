package com.hula.myapplication.util;

import android.Manifest;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hula.myapplication.base.picselect.GlideImageEngine;
import com.hula.myapplication.base.picselect.LubanCompressFileEngine;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.view.home.AddNewEventActivity;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.dialog.PermissonDialog;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HUtils {
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static final String emailregex = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

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

    public static boolean isEmail(@Nullable String content) {
        if (content == null) {
            return false;
        }
        return content.matches(emailregex);
    }

    public static <T> String toGetUri(Map<String, T> hashMap) {
        StringBuilder result = new StringBuilder();
        Set<String> strings = hashMap.keySet();
        try {
            for (String next : strings) {
                Object value = hashMap.get(next);
                if (value == null) {
                    continue;
                }
                result.append(URLEncoder.encode(next, "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(value.toString(), "UTF-8"))
                        .append("&");
            }
            if (result.length() == 0) {
                return "";
            }
            return "?" + result.replace(result.length() - 1, result.length(), "").toString();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static void selectPic(FragmentActivity activity, int maxPic, HuCallBack1<List<String>> callBack) {
        new PermissonDialog.Builder()
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .setTitle("“Hula” Would Like to Access Your Photos")
                .setSubTitle("So you can add all your related photos to your event board.")
                .request(activity.getSupportFragmentManager(), new PermissonDialog.Builder.StandardPermissionHand() {
                    @Override
                    public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                        PictureSelector.create(activity)
                                .openGallery(SelectMimeType.ofImage())
                                .setMaxSelectNum(maxPic)
                                .setImageEngine(GlideImageEngine.engine)
                                .setCompressEngine(LubanCompressFileEngine.engine)
                                .forResult(new OnResultCallbackListener<LocalMedia>() {
                                    @Override
                                    public void onResult(ArrayList<LocalMedia> result) {
                                        List<String> data = CollectionUtils.map(result, new HuCallBack1.HuCallBackR<LocalMedia, String>() {
                                            @Override
                                            public String call(LocalMedia localMedia) {
                                                return localMedia.getAvailablePath();
                                            }
                                        });
                                        if (data.isEmpty()) {
                                            return;
                                        }
                                        callBack.call(data);
                                    }

                                    @Override
                                    public void onCancel() {

                                    }
                                });
                    }
                });
    }

    public static <T> void notifyLiveData(MutableLiveData<List<T>> ld, @Nullable List<T> data) {
        if (data == null) {
            return;
        }
        List<T> value = ld.getValue();
        if (value == null) {
            value = new ArrayList<>();
        }
        value.addAll(data);
        ld.setValue(value);
    }


}
