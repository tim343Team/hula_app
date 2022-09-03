package com.hula.myapplication.app.service;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.GsonUtils;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.sp.SharedPrefsHelper;
import com.hula.myapplication.util.HUtils;

public class ServiceProfile {

    private final SharedPrefsHelper sharedPrefsHelper = SharedPrefsHelper.getInstance();
    private UserInfoData userInfoData;

    public synchronized String getToken() {
        return sharedPrefsHelper.getToken();
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
            result = userInfoData.copy();
        }
        return result;
    }

    public synchronized void updateUserInfo(UserInfoData userInfoData) {
        if (userInfoData != null) {
            this.userInfoData = userInfoData;
            sharedPrefsHelper.sharedPreferences.edit().putString(SharedPrefsHelper.USER_INFO_KEY, GsonUtils.toJson(userInfoData))
                    .apply();
        }
    }

    public SafeGet<UserInfoData> AsyncGetUserInfo() {
        return new SafeGet<UserInfoData>(getUserInfo());
    }

}
