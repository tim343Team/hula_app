package com.hula.myapplication.data;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;
import tim.com.libnetwork.network.okhttp.StringCallBack;
import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;
import tim.com.libnetwork.utils.WonderfulLogUtils;
import static com.hula.myapplication.app.GlobalConstant.JSON_ERROR;
import static com.hula.myapplication.app.GlobalConstant.OKHTTP_ERROR;

import com.hula.myapplication.app.UrlFactory;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    private RemoteDataSource() {

    }

    @Override
    public void getDefaultProfileTag(DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getDefaultProfileTags())
                .build()
                .execute(new StringCallBack() {
                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                        WonderfulLogUtils.logi("获取默认profileTag:", e.getMessage());
                        dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
                    }

                    @Override
                    public void onResponse(String response) {
                        WonderfulLogUtils.logi("获取默认profileTag:", response.toString());
                        try {
                            JSONObject object = new JSONObject(response);
//                            if (object.optInt("code") == 0) {
//                                dataCallback.onDataLoaded(object.optString("message"));
//                            } else {
//                                dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dataCallback.onDataNotAvailable(JSON_ERROR, null);
                        }
                    }
                });
    }
}
