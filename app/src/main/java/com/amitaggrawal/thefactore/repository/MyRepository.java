package com.amitaggrawal.thefactore.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.amitaggrawal.thefactore.data.local.MyDao;
import com.amitaggrawal.thefactore.data.local.MyEntity;
import com.amitaggrawal.thefactore.data.remote.ListMyEntry;
import com.amitaggrawal.thefactore.data.remote.RemoteDataFetchingOperation;
import com.amitaggrawal.thefactore.utilities.AppExecutors;

import java.util.List;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */
public class MyRepository {
    private static final String LOG_TAG = MyRepository.class.getSimpleName();

    private static final Object LOCK = new Object();

    private static MyRepository sInstance;

    private MyDao mDao;
    private RemoteDataFetchingOperation mRemoteDataFetchingOperation;
    private AppExecutors mAppExecutors;

    MyRepository(MyDao dao, RemoteDataFetchingOperation remoteDataFetchingOperation, AppExecutors appExecutors) {
        this.mDao = dao;
        this.mRemoteDataFetchingOperation = remoteDataFetchingOperation;
        this.mAppExecutors = appExecutors;

        LiveData<MyEntity[]> networkData = mRemoteDataFetchingOperation.fetch();
        networkData.observeForever(myEntities -> {
            mAppExecutors.diskIO().execute(() -> {
                deleteOldData(myEntities);
                Log.d(LOG_TAG, "Old values deleted");

                mDao.insetData(myEntities);
                Log.d(LOG_TAG, "New values inserted");
            });
        });
    }

    public static MyRepository getInstance(MyDao dao, RemoteDataFetchingOperation remoteDataFetchingOperation, AppExecutors appExecutors) {
        if (null == sInstance) {
            synchronized (LOCK) {
                sInstance = new MyRepository(dao, remoteDataFetchingOperation, appExecutors);
            }
        }

        return sInstance;
    }

    public LiveData<List<ListMyEntry>> getData() {
        mRemoteDataFetchingOperation.performRemoteFetching();
        return mDao.getData();
    }

    private void deleteOldData(MyEntity[] myEntities) {
        mDao.delete(myEntities);
    }


}

