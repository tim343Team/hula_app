package com.hula.myapplication.app.net;


import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class AbsWalkDogCallBack<T> implements okhttp3.Callback {
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    final public void onFailure(@NonNull Call call, @NonNull IOException e) {
        Exception exception = converException(e);
        fail(exception);
    }

    protected final void fail(Exception e) {
        handler.post(() -> onFail(e));
    }

    protected final void success(T t) {
        handler.post(() -> {
            try {
                onRes(t);
            }catch (Exception e){
                onFail(converException(e));
            }
        });
    }

    //子类复写，多语言适配
    public Exception converException(Exception e) {
        return e;
    }


    @Override
    final public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        ResponseBody body = response.body();
        if (body == null) {
            fail(converException(new Exception("net err")));
            return;
        }
        if (!response.isSuccessful()){
            fail(converException(new IOException("net err")));
            return;
        }
        String string = body.string();
        try {
            success(conver(string));
        } catch (Exception e) {
            fail(converException(e));
        }
    }

    abstract T conver(String string) throws Exception;

    protected abstract void onFail(Exception e);

    protected abstract void onRes(T data) throws Exception;
}
