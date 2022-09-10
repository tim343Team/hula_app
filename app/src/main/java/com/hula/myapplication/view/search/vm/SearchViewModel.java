package com.hula.myapplication.view.search.vm;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.SearchItem;
import com.hula.myapplication.dao.SearchSectionsDao;
import com.hula.myapplication.sp.SharedPrefsHelper;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.util.HUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class SearchViewModel extends ViewModel {

    public MutableLiveData<Integer> select = new MutableLiveData<>();


    public MutableLiveData<List<SearchItem>> sortItemLD = new MutableLiveData<>();

    public MutableLiveData<Integer> sortSelectPosition = new MutableLiveData<>();
    private final Gson gson = new Gson();
    public MutableLiveData<List<SearchSectionsDao>> searchSectionsDaoLD = new MutableLiveData<>();

    public MutableLiveData<List<String>> searchHistory = new MutableLiveData<>();
    private boolean isModify = false;

    public SearchViewModel() {
        sortItemLD.setValue(Arrays.asList(new SearchItem(0, "Price (low to high)"),
                new SearchItem(0, "Price (high to low)"),
                new SearchItem(0, "Date (newest to oldest)"),
                new SearchItem(0, "Popularity"),
                new SearchItem(0, "Most buddies joined the matching pool")));

    }


    public void getAllSections() {
        Map<String, String> firstAlltitle = new HashMap<>();
        firstAlltitle.put("DATE", "Anytime");
        firstAlltitle.put("CATEGORY", "All categories");
        firstAlltitle.put("NEIGHBORHOOD", "All areas");
        WonderfulOkhttpUtils.get()
                .url(UrlFactory.getAllSections())
                .addParams("limit", String.valueOf(10))
                .addParams("user_id", HService.getService(ServiceProfile.class).getUserId())
                .build()
                .getCall()
                .enqueue(new GsonWalkDogCallBack<RemoteData<List<SearchSectionsDao>>>() {
                    @Override
                    protected void onRes(RemoteData<List<SearchSectionsDao>> data) {
                        List<SearchSectionsDao> notNullData = data.getNotNullData();
                        for (SearchSectionsDao data1 : notNullData) {
                            String s = firstAlltitle.get(data1.getTitle());
                            if (s == null) {
                                s = "All";
                            }
                            SearchSectionsDao.Item item = new SearchSectionsDao.Item();
                            item.setTitle(s);
                            item.setId(Integer.MAX_VALUE);
                            data1.getItems().add(0, item);
                        }
                        searchSectionsDaoLD.setValue(notNullData);
                    }

                    @Override
                    protected void onFail(Exception e) {
                        super.onFail(e);
                    }
                });
    }

    public void refreshHistory() {
        if (searchHistory.getValue() == null) {
            SharedPreferences sp = SharedPrefsHelper.getInstance().sharedPreferences;
            HUtils.runCatch(new Runnable() {
                @Override
                public void run() {
                    String history = sp.getString(SharedPrefsHelper.SEARCH_HISTORY, "");
                    List<String> historyList = gson.fromJson(history, new TypeToken<List<String>>() {
                    }.getType());
                    if (!CollectionUtils.equals(searchHistory.getValue(), historyList)) {
                        searchHistory.setValue(historyList);
                    }

                }
            });
        }
    }

    public void add(String s) {
        isModify = true;
        List<String> value = searchHistory.getValue();
        if (value == null) {
            value = new ArrayList<>();
        }
        value.add(0, s);
        if (value.size() > 10) {
            value.remove(10);
        }
        searchHistory.setValue(value);
    }

    public void remove(String string) {
        isModify = true;
        List<String> value = searchHistory.getValue();
        if (value == null) {
            value = new ArrayList<>();
        }
        value.remove(string);
        searchHistory.setValue(value);
    }

    public void save() {
        if (isModify) {
            SharedPreferences sp = SharedPrefsHelper.getInstance().sharedPreferences;
            List<String> value = searchHistory.getValue();
            if (value == null) {
                value = new ArrayList<>();
            }
            if (value.size() > 10) {
                value.remove(10);
            }
            sp.edit().putString(SharedPrefsHelper.SEARCH_HISTORY, gson.toJson(value)).apply();
        }
    }
}
