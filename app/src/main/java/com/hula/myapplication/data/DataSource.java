package com.hula.myapplication.data;

import com.hula.myapplication.request.CreateProfileParameter;

public interface DataSource {

    interface DataCallback<T> {

        void onDataLoaded(T obj);

        void onDataNotAvailable(Integer code, String toastMessage);
    }

    void createProfileTag(CreateProfileParameter parameter,DataCallback dataCallback);

    void getDefaultProfileTag(DataCallback dataCallback);

}
