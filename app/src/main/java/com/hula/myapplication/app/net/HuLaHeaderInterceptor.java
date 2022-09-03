package com.hula.myapplication.app.net;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HuLaHeaderInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        ServiceProfile serviceProfile = HService.getService(ServiceProfile.class);

        String token = serviceProfile.getToken();
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader("Authorization", "Token " + serviceProfile.getToken());
        }
        return chain.proceed(builder.build());
    }
}
