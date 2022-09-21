package com.hula.myapplication.data;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.hula.myapplication.request.AddSchoolParameter;
import com.hula.myapplication.request.CreateProfileParameter;
import com.hula.myapplication.request.GetSchoolParameter;
import com.hula.myapplication.request.UpdateProfileParameter;

public class LocalDataSource implements DataSource {
    private static LocalDataSource INSTANCE;
    private Context context;
    private Handler handler = new Handler(Looper.getMainLooper());

    public LocalDataSource(Context context) {
        this.context = context;
    }

    public static LocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void addSchool(AddSchoolParameter parameter, DataCallback dataCallback) {

    }

    @Override
    public void getSchoolList(GetSchoolParameter parameter, DataCallback dataCallback) {

    }

    @Override
    public void deleteProfileTag(CreateProfileParameter parameter, DataCallback dataCallback) {

    }

    @Override
    public void createProfileTag(CreateProfileParameter parameter, DataCallback dataCallback) {

    }

    @Override
    public void getDefaultProfileTag(DataCallback dataCallback) {

    }

    @Override
    public void updateProfile(UpdateProfileParameter parameter, DataCallback dataCallback) {

    }
}
