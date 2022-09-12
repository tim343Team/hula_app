package com.hula.myapplication.view.home.vm;

import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.util.HashMap;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class Repository {
    public void findBuddyV2(int id) {
        ToastUtil.showLoading("");
        String userId = HService.getService(ServiceProfile.class).getUserId();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user_id", userId);
        hashMap.put("event_id", id);
        WonderfulOkhttpUtils.get()
                .url(UrlFactory.findBuddyV2() + HUtils.toGetUri(hashMap))
                .build()
                .getCall()
                .enqueue(new GsonWalkDogCallBack<RemoteData<Object>>() {
                    @Override
                    protected void onRes(RemoteData<Object> data) {
                        ToastUtil.hideLoading();
                        ToastUtil.showSuccessToast("Find Buddy Success");
                    }

                    @Override
                    protected void onFail(Exception e) {
                        super.onFail(e);
                        ToastUtil.hideLoading();
                    }
                });
    }
}
