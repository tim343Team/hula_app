package com.hula.myapplication.data;

import com.hula.myapplication.request.AddSchoolParameter;
import com.hula.myapplication.request.CreateProfileParameter;
import com.hula.myapplication.request.GetSchoolParameter;
import com.hula.myapplication.request.UpdateProfileParameter;

public class DataRepository implements DataSource {
    private static DataRepository INSTANCE = null;
    private final DataSource mRemoteDataSource;
    private final DataSource mLocalDataSource;
    private boolean isLocal = false;

    private DataRepository(DataSource mRemoteDataSource, DataSource mLocalDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
        this.mLocalDataSource = mLocalDataSource;
    }

    public static DataRepository getInstance(DataSource mRemoteDataSource, DataSource mLocalDataSource) {
        if (INSTANCE == null) INSTANCE = new DataRepository(mRemoteDataSource, mLocalDataSource);
        return INSTANCE;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }


    @Override
    public void addSchool(AddSchoolParameter parameter, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.addSchool(parameter,dataCallback);
        else mRemoteDataSource.addSchool(parameter,dataCallback);
    }

    @Override
    public void getSchoolList(GetSchoolParameter parameter, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getSchoolList(parameter,dataCallback);
        else mRemoteDataSource.getSchoolList(parameter,dataCallback);
    }

    @Override
    public void deleteProfileTag(CreateProfileParameter parameter, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.deleteProfileTag(parameter,dataCallback);
        else mRemoteDataSource.deleteProfileTag(parameter,dataCallback);
    }

    @Override
    public void createProfileTag(CreateProfileParameter parameter, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.createProfileTag(parameter,dataCallback);
        else mRemoteDataSource.createProfileTag(parameter,dataCallback);
    }

    @Override
    public void getDefaultProfileTag(DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getDefaultProfileTag(dataCallback);
        else mRemoteDataSource.getDefaultProfileTag(dataCallback);
    }

    @Override
    public void updateProfile(UpdateProfileParameter parameter, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.updateProfile(parameter,dataCallback);
        else mRemoteDataSource.updateProfile(parameter,dataCallback);
    }
}
