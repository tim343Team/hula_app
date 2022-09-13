package com.hula.myapplication.util;

import androidx.annotation.Nullable;

import com.hula.myapplication.dao.home.UsersItem;
import com.hula.myapplication.widget.HuCallBack1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionUtils {
    public static <T, O> List<O> map(Collection<T> collection, HuCallBack1.HuCallBackR<T, O> map) {
        List<O> result = new ArrayList<>();
        for (T next : collection) {
            result.add(map.call(next));
        }
        return result;
    }


    public static <T> String joinToString(Iterable<T> iterable, CharSequence separator, HuCallBack1.HuCallBackR<T, String> callBackR) {
        StringBuilder buffer = new StringBuilder();
        int count = 0;
        for (T next : iterable) {
            if (++count > 1) {
                buffer.append(separator);
            }
            buffer.append(callBackR.call(next));
        }
        return buffer.toString();
    }

    public static boolean equals(@Nullable Collection<?> collection, @Nullable Collection<?> collection1) {
        if (collection == null && collection1 == null) {
            return true;
        }
        if (collection == null) {
            return false;
        }
        if (collection1 == null) {
            return false;
        }
        for (Object next : collection) {
            for (Object next1 : collection1) {
                if (!next1.equals(next)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Nullable
    public static <T> T getOrNull(List<T> data, int index) {
        if (data == null) {
            return null;
        }
        try {
            return data.get(index);
        } catch (Exception ignored) {
        }
        return null;
    }
}
