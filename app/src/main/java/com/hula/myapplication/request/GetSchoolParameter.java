package com.hula.myapplication.request;

public class GetSchoolParameter {
    private int limit=20;
    private int offset;
    private String user_id;

    public GetSchoolParameter(int offset, String user_id) {
        this.offset = offset;
        this.user_id = user_id;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
