package com.hula.myapplication.data;

import com.hula.myapplication.request.CreateProfileParameter;

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
    public void createProfileTag(CreateProfileParameter parameter, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.createProfileTag(parameter,dataCallback);
        else mRemoteDataSource.createProfileTag(parameter,dataCallback);
    }

    @Override
    public void getDefaultProfileTag(DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getDefaultProfileTag(dataCallback);
        else mRemoteDataSource.getDefaultProfileTag(dataCallback);
    }
}
