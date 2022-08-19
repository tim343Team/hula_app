package com.hula.myapplication.app;

import android.content.Context;

import com.hula.myapplication.data.DataRepository;
import com.hula.myapplication.data.LocalDataSource;
import com.hula.myapplication.data.RemoteDataSource;

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
