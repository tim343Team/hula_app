package com.hula.myapplication.view.search.vm;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hula.myapplication.dao.SearchItem;
import com.hula.myapplication.sp.SharedPrefsHelper;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.util.HUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchViewModel extends ViewModel {

    public MutableLiveData<Integer> select = new MutableLiveData<>();


    public MutableLiveData<List<SearchItem>> sortItemLD = new MutableLiveData<>();

    public MutableLiveData<Integer> sortSelectPosition = new MutableLiveData<>();
    private final Gson gson = new Gson();


    public MutableLiveData<List<String>> searchHistory = new MutableLiveData<>();
    private boolean isModify = false;

    public SearchViewModel() {
        sortItemLD.setValue(Arrays.asList(new SearchItem(0, "Price (low to high)"),
                new SearchItem(0, "Price (high to low)"),
                new SearchItem(0, "Date (newest to oldest)"),
                new SearchItem(0, "Popularity"),
                new SearchItem(0, "Most buddies joined the matching pool")));

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
