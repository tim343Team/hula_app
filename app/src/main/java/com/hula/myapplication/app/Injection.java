package com.p47_70.myapplication.app;

import android.content.Context;

import com.p47_70.myapplication.data.DataRepository;
import com.p47_70.myapplication.data.LocalDataSource;
import com.p47_70.myapplication.data.RemoteDataSource;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/6/9
 */
public class Injection {
    public static DataRepository provideTasksRepository(Context context) {
        return DataRepository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(context));
    }
}
