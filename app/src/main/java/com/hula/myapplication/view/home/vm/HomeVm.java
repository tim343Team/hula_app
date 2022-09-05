package com.hula.myapplication.view.home.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.util.HUtils;

import java.util.HashMap;
import java.util.List;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class HomeVm extends ViewModel {
    public ServiceProfile service = HService.getService(ServiceProfile.class);
    public MutableLiveData<List<DataItemDao>> allEventLD = new MutableLiveData<>();
    public int page = 0;

    public MutableLiveData<Boolean> loadEvents() {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        String userId = service.getUserId();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user_id", userId);
        hashMap.put("page",page);
        WonderfulOkhttpUtils.get()
                .url(UrlFactory.getAllHomeEventsV3() + HUtils.toGetUri(hashMap))
                .build()
                .getCall()
                .enqueue(new GsonWalkDogCallBack<RemoteData<List<DataItemDao>>>() {
                    @Override
                    protected void onRes(RemoteData<List<DataItemDao>> data) throws Exception {
                        HUtils.notifyLiveData(allEventLD, data.getNotNullData());
                        result.setValue(data.getNotNullData().isEmpty());
                        page += 1;
                    }

                    @Override
                    protected void onFail(Exception e) {
                        super.onFail(e);
                        result.setValue(true);
                    }
                });

        return result;
    }


}
