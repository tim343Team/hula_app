package com.hula.myapplication.bus_event;

import com.hula.myapplication.dao.UserInfoData;

public class UpdateUserInfoEvent {
    private UserInfoData userInfoData;

    public UpdateUserInfoEvent(UserInfoData userInfoData) {
        this.userInfoData = userInfoData;
    }

    public UserInfoData getUserInfoData() {
        return userInfoData;
    }

    public void setUserInfoData(UserInfoData userInfoData) {
        this.userInfoData = userInfoData;
    }
}
