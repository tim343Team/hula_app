package com.hula.myapplication.app.service;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.GsonUtils;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.User;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.sp.SharedPrefsHelper;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.util.SafeGet;
import com.hula.myapplication.widget.HuCallBack1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class ServiceProfile {

    private final SharedPrefsHelper sharedPrefsHelper = SharedPrefsHelper.getInstance();
    private UserInfoData userInfoData;
    private final List<HuCallBack1<UserInfoData>> callBacks = Collections.synchronizedList(new ArrayList<>());

    public synchronized String getToken() {
        return sharedPrefsHelper.getToken();
    }

    public synchronized String getUserId() {
        UserInfoData userInfo = getUserInfo();
        if (userInfo != null) {
            User user = userInfo.getUser();
            if (user != null) {
                return String.valueOf(user.getId());
            }
        }
        return "";
    }


    @Nullable
    public synchronized UserInfoData getUserInfo() {
        String str = sharedPrefsHelper.sharedPreferences.getString(SharedPrefsHelper.USER_INFO_KEY, "");
        UserInfoData result = null;
        if (userInfoData == null) {
            HUtils.runCatch(new Runnable() {
                @Override
                public void run() {
                    userInfoData = GsonUtils.fromJson(str, UserInfoData.class);
                }
            });
        }
        if (userInfoData != null) {
            result = userInfoData;
        }
        return result;
    }

    public synchronized void cleanUserInfo(){
        this.userInfoData = null;
        sharedPrefsHelper.sharedPreferences.edit().remove(SharedPrefsHelper.USER_INFO_KEY).apply();
    }


    public synchronized void updateUserInfo(@NonNull UserInfoData userInfoData) {
        this.userInfoData = userInfoData;
        sharedPrefsHelper.sharedPreferences.edit().putString(SharedPrefsHelper.USER_INFO_KEY, GsonUtils.toJson(userInfoData))
                .apply();
        for (int i = 0; i < callBacks.size(); i++) {
            callBacks.get(i).call(userInfoData);
        }
    }

    public SafeGet<UserInfoData> asyncGetUserInfo() {
        return new SafeGet<UserInfoData>(getUserInfo());
    }


    public void refresh() {
        String userId = getUserId();
        if (TextUtils.isEmpty(userId)) {
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user_id", getUserId());
        WonderfulOkhttpUtils.get()
                .url(UrlFactory.getProfile() + HUtils.toGetUri(hashMap))
                .build()
                .getCall()
                .enqueue(new GsonWalkDogCallBack<RemoteData<UserInfoData>>() {
                    @Override
                    protected void onRes(RemoteData<UserInfoData> data) throws Exception {
                        updateUserInfo(data.getNotNullData());
                    }

                    @Override
                    protected void onFail(Exception e) {

                    }
                });
    }

    public void addRefreshListener(HuCallBack1<UserInfoData> callback) {
        callBacks.add(callback);
    }

    public void removeRefreshListener(HuCallBack1<UserInfoData> callback) {
        callBacks.remove(callback);
    }

    public void addRefreshListener(LifecycleOwner owner, HuCallBack1<UserInfoData> callBack) {
        callBacks.add(callBack);
        owner.getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (event.equals(Lifecycle.Event.ON_DESTROY)) {
                    callBacks.remove(callBack);
                }
            }
        });
    }

}
