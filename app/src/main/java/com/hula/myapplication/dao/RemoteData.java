package com.hula.myapplication.dao;

import com.google.gson.annotations.SerializedName;

public class RemoteData<T> {
    private boolean success;
    @SerializedName("status_code")
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }
}
