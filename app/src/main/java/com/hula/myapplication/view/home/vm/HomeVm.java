package com.hula.myapplication.view.home.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.home.Anthing;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.dao.home.GroupDao;
import com.hula.myapplication.dao.home.RecommendedDao;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.util.ThreadUtils;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class HomeVm extends ViewModel {
    public ServiceProfile service = HService.getService(ServiceProfile.class);
    public MutableLiveData<List<Anthing>> allEventLD = new MutableLiveData<>();
    public int page = 0;
    private final Gson gson;

    public HomeVm() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Anthing.class, new JsonDeserializer<Object>() {
                    @Override
                    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        JsonObject asJsonObject = json.getAsJsonObject();
                        JsonArray groups = asJsonObject.getAsJsonArray("groups");
                        if (groups != null) {
                            return GsonUtils.getGson().fromJson(json, GroupDao.class);
                        }
                        JsonArray events = asJsonObject.getAsJsonArray("events");
                        if (events != null) {
                            return GsonUtils.getGson().fromJson(json, DataItemDao.class);
                        }
                        JsonArray profiles = asJsonObject.getAsJsonArray("profiles");
                        if (profiles!=null){
                            return GsonUtils.getGson().fromJson(json, RecommendedDao.class);
                        }
                        return null;
                    }
                })
                .create();
    }


    public MutableLiveData<Boolean> loadFirst() {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        ThreadUtils.newCachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("user_id", service.getUserId());
                    hashMap.put("page", 0);
                    ResponseBody body = WonderfulOkhttpUtils.get()
                            .url(UrlFactory.getAllHomeEvents() + HUtils.toGetUri(hashMap))
                            .build()
                            .getCall().execute().body();
                    if (body == null) {
                        throw new Exception("net err");
                    }
                    String string = body.string();
                    RemoteData<List<Anthing>> cover = gson.fromJson(string, new TypeToken<RemoteData<List<Anthing>>>() {
                    }.getType());
                    GsonWalkDogCallBack.safeCheck(cover, GsonWalkDogCallBack.huLaBusinessHand);
                    HashMap<String, Object> hashMap1 = new HashMap<>();
                    hashMap1.put("user_id", service.getUserId());
                    hashMap1.put("page", 1);
                    ResponseBody body1 = WonderfulOkhttpUtils.get()
                            .url(UrlFactory.getAllHomeEvents() + HUtils.toGetUri(hashMap1))
                            .build()
                            .getCall().execute().body();
                    if (body1 == null) {
                        throw new Exception("net err");
                    }
                    String string1 = body1.string();
                    RemoteData<List<Anthing>> cover1 = gson.fromJson(string1, new TypeToken<RemoteData<List<Anthing>>>() {
                    }.getType());
                    GsonWalkDogCallBack.safeCheck(cover1, GsonWalkDogCallBack.huLaBusinessHand);

                    List<Anthing> notNullData = cover.getNotNullData();
                    List<Anthing> notNullData1 = cover1.getNotNullData();

                    notNullData.addAll(notNullData1);

                    allEventLD.postValue(notNullData);
                    page = 2;
                    result.postValue(!notNullData.isEmpty());
                } catch (Exception e) {
//                    ToastUtil.showFailToast(e.getMessage());
                    result.postValue(true);
                }
            }
        });


        return result;
    }

    public MutableLiveData<Boolean> loadEvents() {
        if (page == 0) {
            return loadFirst();
        }

        MutableLiveData<Boolean> result = new MutableLiveData<>();
        String userId = service.getUserId();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user_id", userId);
        hashMap.put("page", page);
        WonderfulOkhttpUtils.get()
                .url(UrlFactory.getAllHomeEvents() + HUtils.toGetUri(hashMap))
                .build()
                .getCall()
                .callbackExecutor(ThreadUtils.newCachedThreadPool)
                .enqueue(new GsonWalkDogCallBack<RemoteData<List<Anthing>>>() {
                    @Override
                    protected void onRes(RemoteData<List<Anthing>> data) throws Exception {
                        HUtils.notifyLiveData(allEventLD, data.getNotNullData());
                        result.setValue(!data.getNotNullData().isEmpty());
                        page += 1;
                    }

                    @Override
                    protected void onFail(Exception e) {
                        super.onFail(e);
                        result.setValue(true);
                    }
                }.setGson(gson));

        return result;
    }


    public MutableLiveData<Boolean> loadLike() {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        WonderfulOkhttpUtils.get()
                .url(UrlFactory.specificEventsList())
                .addParams("is_like", "True")
                .addParams("user_id",service.getUserId())
                .build()
                .getCall()
                .enqueue(new GsonWalkDogCallBack<RemoteData<List<Object>>>() {
                    @Override
                    protected void onRes(RemoteData<List<Object>> data) throws Exception {
                        result.setValue(!data.getNotNullData().isEmpty());
                    }

                    @Override
                    protected void onFail(Exception e) {
                        super.onFail(e);
                        result.setValue(false);
                    }
                });
        return result;
    }
}
