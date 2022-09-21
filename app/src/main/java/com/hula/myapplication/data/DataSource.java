package com.hula.myapplication.data;

import com.hula.myapplication.request.AddSchoolParameter;
import com.hula.myapplication.request.CreateProfileParameter;
import com.hula.myapplication.request.GetSchoolParameter;
import com.hula.myapplication.request.UpdateProfileParameter;

public interface DataSource {

    interface DataCallback<T> {

        void onDataLoaded(T obj);

        void onDataNotAvailable(Integer code, String toastMessage);
    }

    void addSchool(AddSchoolParameter parameter, DataCallback dataCallback);

    void getSchoolList(GetSchoolParameter parameter, DataCallback dataCallback);

    void deleteProfileTag(CreateProfileParameter parameter,DataCallback dataCallback);

    void createProfileTag(CreateProfileParameter parameter,DataCallback dataCallback);

    void getDefaultProfileTag(DataCallback dataCallback);

    void updateProfile(UpdateProfileParameter parameter,DataCallback dataCallback);

}
