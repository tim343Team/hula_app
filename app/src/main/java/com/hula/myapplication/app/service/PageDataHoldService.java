package com.hula.myapplication.app.service;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class PageDataHoldService {
    private final HashMap<String, Object> hashMap = new HashMap<>();


    public void add(String key, Object data) {
        hashMap.put(key, data);
    }

    @Nullable
    public Object get(String key) {
        return hashMap.get(key);
    }

    public Object remove(String key) {
        return hashMap.remove(key);
    }


}
