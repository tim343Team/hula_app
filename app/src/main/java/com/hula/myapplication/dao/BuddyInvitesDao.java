package com.hula.myapplication.dao;

import com.hula.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class BuddyInvitesDao {
    private int type = 0;//1.normal 2.7天未失效消息 3.7天已失效
    private List<SubBuddyInvitesDao> data = new ArrayList<>();

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SubBuddyInvitesDao> getData() {
        return data;
    }

    public void setData(List<SubBuddyInvitesDao> data) {
        this.data = data;
    }

    public int getName() {
        int name=R.string.text_null;
        if (type == 1) {
            name= R.string.buddies;
        } else if (type == 2) {
            name= R.string.more_than_7_days;
        } else if (type == 3) {
            name= R.string.more_than_7_days;
        }
        return name;
    }
}
