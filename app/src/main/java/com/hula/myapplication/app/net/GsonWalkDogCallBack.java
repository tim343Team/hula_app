package com.hula.myapplication.app.net;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

public abstract class GsonWalkDogCallBack<T> extends AbsWalkDogCallBack<T> {
    final public static String UNKNOWERR = "Unknow Err";
    final static Gson gson = new Gson();
    final static HuLaNetCodeHand huLaBusinessHand = new HuLaNetCodeHand();

    private HuLaNetCodeHand hand = huLaBusinessHand;

    public GsonWalkDogCallBack<T> setHand(HuLaNetCodeHand hand) {
        this.hand = hand;
        return this;
    }

    @Override
    T conver(String string) throws ApiException {
        Type type = getSuperclassTypeParameter(this.getClass());
        T o = gson.fromJson(string, type);
        if (o instanceof RemoteData) {
            int code = ((RemoteData<?>) o).getCode();
            boolean success = ((RemoteData<?>) o).isSuccess();
            if (code != 200 || !success) {
                if (!hand.Hand(code)) {
                    throw new ApiException(((RemoteData<?>) o).getMessage());
                }
            }
        }
        return o;
    }

    @Override
    protected void onFail(Exception e) {
        String message = e.getMessage();
        if (TextUtils.isEmpty(message)) {
            message = UNKNOWERR;
        }
        if (Objects.equals(message, "cancel")) {
            return;
        }
        if (message != null) {
            ToastUtil.showFailToast(message);
        }
    }

    private static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        assert parameterized != null;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }


    static class ApiException extends Exception {
        public ApiException(String messgae) {
            super(messgae);
        }
    }
}
