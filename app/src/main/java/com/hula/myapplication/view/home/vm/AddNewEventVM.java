package com.hula.myapplication.view.home.vm;

import static com.hula.myapplication.sp.SharedPrefsHelper.EVENT_SAVE;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blankj.utilcode.util.GsonUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;
import com.google.gson.reflect.TypeToken;
import com.hula.myapplication.app.firebase.HulaFirebaseStorage;
import com.hula.myapplication.dao.CityDao;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.sp.SharedPrefsHelper;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.util.ThreadUtils;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddNewEventVM extends ViewModel {


    public MutableLiveData<List<SubCategoriesDao>> subCategoriesDaoMutableLD = new MutableLiveData<>();

    public MutableLiveData<Long[]> timeLD = new MutableLiveData<>();

    public MutableLiveData<CityDao> cityLD = new MutableLiveData<>();

    public MutableLiveData<String> name = new MutableLiveData<>();

    public MutableLiveData<String> location = new MutableLiveData<>();

    public MutableLiveData<String> des = new MutableLiveData<>();

    public MutableLiveData<List<String>> pics = new MutableLiveData<>();

    public MutableLiveData<String> link = new MutableLiveData<>();

    public MutableLiveData<String> price = new MutableLiveData<>();

    public MutableLiveData<Integer> createType = new MutableLiveData<>();

    public AddNewEventVM() {
        restore(new HuCallBack1<Map<String, Object>>() {
            @SuppressWarnings("unchecked")
            @Override
            public void call(Map<String, Object> map) {
                List<SubCategoriesDao> sub_categoryObj = (List<SubCategoriesDao>) map.get("sub_categoryObj");
                if (sub_categoryObj != null) {
                    subCategoriesDaoMutableLD.setValue(sub_categoryObj);
                }
                CityDao cityDao = (CityDao) map.get("cityObj");

                if (cityDao != null) {
                    cityLD.setValue(cityDao);
                }
                String nameStr = (String) map.get("name");
                if (nameStr != null) {
                    name.setValue(nameStr);
                }
                String locationStr = (String) map.get("location");
                if (locationStr != null) {
                    location.setValue(locationStr);
                }
                String descriptionStr = (String) map.get("description");
                if (descriptionStr != null) {
                    des.setValue(descriptionStr);
                }
                String image_link = (String) map.get("image_link");
                if (image_link != null) {
                    String[] split = image_link.split(",");
                    pics.setValue(Arrays.asList(split));
                }
                String event_url = (String) map.get("event_url");
                if (event_url != null) {
                    link.setValue(event_url);
                }
                String ticket_price = (String) map.get("ticket_price");
                if (ticket_price != null) {
                    price.setValue(ticket_price);
                }
                String creating_to_find_buddy = (String) map.get("creating_to_find_buddy");
                if (creating_to_find_buddy != null) {
                    createType.setValue(creating_to_find_buddy.equals("True") ? 0 : 1);
                }
            }
        });
    }

    //{
    //	"starting": "2022-09-13T13:46:30+0800",
    //	"user_id": 6597,
    //	"category": "2",
    //	"ticket_price": "552",
    //	"event_url": "de",
    //	"creating_to_find_buddy": "True",
    //	"name": "Ui",
    //	"location": "Beijing ",
    //	"ending": "2022-09-13T13:46:33+0800",
    //	"description": "明",
    //	"image_link": "https:\/\/cdn.hula-events.com\/Dudu_6597%2Fmy_events%2FC7A6A84B-6EB9-4868-8F26-DBFF17A6D8D7%2F0.jpg",
    //	"sub_category": "50"
    //}
    public void save() {
        Map<String, Object> request = new HashMap<>();
        fillRequest(request);
        if (request.isEmpty()) {
            return;
        }
        request.remove("city");
        request.remove("sub_category");
        String s = GsonUtils.toJson(request);
        SharedPrefsHelper.getInstance().sharedPreferences.edit()
                .putString(EVENT_SAVE, s)
                .apply();
    }

    public void remove() {
        SharedPrefsHelper.getInstance().sharedPreferences.edit()
                .remove(EVENT_SAVE)
                .apply();
    }

    public static void restore(HuCallBack1<Map<String, Object>> huCallBack) {
        Handler handler = new Handler(Looper.getMainLooper());
        ThreadUtils.newCachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = new HashMap<>();
                HUtils.runCatch(new Runnable() {
                    @Override
                    public void run() {
                        String string = SharedPrefsHelper.getInstance().sharedPreferences.getString(EVENT_SAVE, "");
                        Map<String, Object> _map = GsonUtils.fromJson(string, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        map.putAll(_map);
                    }
                });

                HUtils.runCatch(new Runnable() {
                    @Override
                    public void run() {
                        Object cityObj = map.get("cityObj");
                        String s = GsonUtils.toJson(cityObj);
                        CityDao cityDao = GsonUtils.fromJson(s, new TypeToken<CityDao>() {
                        }.getType());
                        map.put("cityObj", cityDao);
                    }
                });

                HUtils.runCatch(new Runnable() {
                    @Override
                    public void run() {
                        Object sub_categoryObj = map.get("sub_categoryObj");
                        String s = GsonUtils.toJson(sub_categoryObj);
                        List<SubCategoriesDao> list = GsonUtils.fromJson(s, new TypeToken<List<SubCategoriesDao>>() {
                        }.getType());
                        map.put("sub_categoryObj", list);
                    }
                });
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        huCallBack.call(map);
                    }
                });
            }
        });
    }

    public boolean fillRequest(Map<String, Object> request) {
        int completeNum = 0;
        String nameStr = name.getValue();
        if (!TextUtils.isEmpty(nameStr)) {
            completeNum++;
            request.put("name", nameStr);
        }

        String locationStr = location.getValue();
        if (!TextUtils.isEmpty(locationStr)) {
            completeNum++;
            request.put("location", locationStr);
        }

        CityDao city = cityLD.getValue();
        if (city != null) {
            completeNum++;
            request.put("city", city.getId());
            request.put("cityObj", city);
        }

        List<SubCategoriesDao> subCategoriesDaos = subCategoriesDaoMutableLD.getValue();
        if (subCategoriesDaos != null && !subCategoriesDaos.isEmpty()) {
            completeNum++;
            request.put("sub_category", CollectionUtils.joinToString(subCategoriesDaos, ",", new HuCallBack1.HuCallBackR<SubCategoriesDao, String>() {
                @Override
                public String call(SubCategoriesDao subCategoriesDao) {
                    return String.valueOf(subCategoriesDao.getId());
                }
            }));
            request.put("sub_categoryObj", subCategoriesDaos);
        }

        Long[] value = timeLD.getValue();
        if (value != null) {
            completeNum++;
            request.put("starting", new Date(value[0]).toString());
            request.put("ending", new Date(value[1]).toString());
        }

        String desStr = des.getValue();
        if (!TextUtils.isEmpty(desStr)) {
            completeNum++;
            request.put("description", desStr);
        }

        List<String> picsList = pics.getValue();
        if (picsList != null && !picsList.isEmpty()) {
            completeNum++;
            request.put("image_link", CollectionUtils.joinToString(picsList, ",", new HuCallBack1.HuCallBackR<String, String>() {
                @Override
                public String call(String s) {
                    return s;
                }
            }));
        }

        String linkStr = link.getValue();
        if (!TextUtils.isEmpty(linkStr)) {
            completeNum++;
            request.put("event_url", linkStr);
        }

        String priceStr = price.getValue();
        if (!TextUtils.isEmpty(priceStr)) {
            completeNum++;
            request.put("ticket_price", priceStr);
        }
        Integer createTypeValue = createType.getValue();

        if (createTypeValue != null) {
            completeNum++;
            request.put("creating_to_find_buddy", createTypeValue == 0 ? "True" : "False");
        }
        return completeNum == 10;
    }

    public void submit() {
        List<String> value = pics.getValue();
        if (value == null) {
            return;
        }
        Map<Integer, String> maps = new HashMap<>();
        for (int i = 0; i < value.size(); i++) {
            maps.put(i,value.get(i));
        }
        updateFile(maps, new HuCallBack1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                ToastUtil.showSuccessToast(String.valueOf(aBoolean));
            }
        });
    }


    private void updateFile(Map<Integer, String> map, HuCallBack1<Boolean> callBack) {
        ToastUtil.showLoading("put photo...");
        final int[] successNum = {0};
        final boolean[] isFail = {false};
        List<UploadTask> uploadTasks = new ArrayList<>();
        for (Integer next : map.keySet()) {
            String filePath = map.get(next);
            if (filePath != null) {
                UploadTask uploadTask = HulaFirebaseStorage.updateEventPhotos(next, filePath);
                uploadTasks.add(uploadTask);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                uploadTasks.remove(uploadTask);
                                ToastUtil.showFailToast("put photo fail");
                                if (!isFail[0]) {
                                    callBack.call(false);
                                    ToastUtil.hideLoading();
                                    isFail[0] = true;
                                    for (int i = 0; i < uploadTasks.size(); i++) {
                                        uploadTasks.get(i).cancel();
                                    }
                                }
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                uploadTasks.remove(uploadTask);
                                if (!isFail[0]) {
                                    successNum[0]++;
                                    if (successNum[0] == map.size()) {
                                        callBack.call(true);
                                        ToastUtil.hideLoading();
                                    }
                                }
                            }
                        });
            }
        }
    }

    public void addPic(List<String> strings) {
        List<String> value = pics.getValue();
        if (value==null){
            value = new ArrayList<>();
        }
        value.addAll(strings);
        pics.setValue(value);
    }
}
