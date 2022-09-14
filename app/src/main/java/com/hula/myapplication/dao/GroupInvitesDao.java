package com.hula.myapplication.dao;

import com.hula.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class GroupInvitesDao {
    private int type = 0;//1.request 2.invite
    private List<SubGroupInvitesDao> data = new ArrayList<>();

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SubGroupInvitesDao> getData() {
        return data;
    }

    public void setData(List<SubGroupInvitesDao> data) {
        this.data = data;
    }

    public int getName() {
        int name= R.string.text_null;
        if (type == 1) {
            name= R.string.requests;
        } else if (type == 2) {
            name= R.string.interests;
        }
        return name;
    }
}
