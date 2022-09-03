package com.hula.myapplication.app.service;

import java.util.HashMap;

/**
 * 提供全局单例
 */
public class HService {
    static HashMap<String, Object> refre = new HashMap<>();

    @SuppressWarnings("unchecked")
    public synchronized static <T> T getService(Class<T> clazz) {
        T o = (T) refre.get(clazz.getCanonicalName());
        if (o == null) {
            try {
                o = clazz.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            if (o == null) {
                throw new NullPointerException(clazz.getCanonicalName());
            }
            refre.put(clazz.getCanonicalName(), o);
        }
        return o;
    }


}
