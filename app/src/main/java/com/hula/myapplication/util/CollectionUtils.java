package com.hula.myapplication.util;

import androidx.annotation.Nullable;

import com.hula.myapplication.widget.HuCallBack1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtils {
    public static <T, O> List<O> map(Collection<T> collection, HuCallBack1.HuCallBackR<T, O> map) {
        List<O> result = new ArrayList<>();
        for (T next : collection) {
            result.add(map.call(next));
        }
        return result;
    }

    public static boolean equals(@Nullable Collection<?> collection,@Nullable Collection<?> collection1) {
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

}
