package com.hula.myapplication.app;

import androidx.annotation.NonNull;

import com.hula.myapplication.app.net.HuLaHCallFactory;
import com.hula.myapplication.app.net.HuLaHeaderInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class HulaInit {


    public static void init() {
        initNet();

    }

    private static void initNet() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS).readTimeout(50, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                        cookieStore.put(httpUrl.host(), list);
                    }

                    @NonNull
                    @Override
                    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<>();
                    }
                })
                .addInterceptor(new HuLaHeaderInterceptor())
                .build();
        WonderfulOkhttpUtils.getInstance().config(mOkHttpClient, new HuLaHCallFactory());
    }
}
