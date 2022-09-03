package com.hula.myapplication.app.net;

import okhttp3.Call;
import tim.com.libnetwork.network.okhttp.call.JHCall;
import tim.com.libnetwork.network.okhttp.call.JHCallFactory;

public class HuLaHCallFactory implements JHCallFactory {
    @Override
    public JHCall factory(Call call) {
        return new RealHCall(call);
    }
}
