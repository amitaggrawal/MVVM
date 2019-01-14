package com.amitaggrawal.thefactore.utilities;

import android.content.Context;

import com.amitaggrawal.thefactore.data.local.MyDatabase;
import com.amitaggrawal.thefactore.data.remote.RemoteDataFetchingOperation;
import com.amitaggrawal.thefactore.repository.MyRepository;
import com.amitaggrawal.thefactore.viewmodel.MainFragmentViewModelFactory;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */
public class InjectorUtils {

    public static MyRepository provideRepository(Context context) {
        MyDatabase database = MyDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        RemoteDataFetchingOperation remoteDataFetchingOperation = RemoteDataFetchingOperation.getInstance(executors);

        return MyRepository.getInstance(database.myDao(), remoteDataFetchingOperation, executors);
    }

    public static RemoteDataFetchingOperation provideRemoteInstance(Context context) {
        provideRepository(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return RemoteDataFetchingOperation.getInstance(executors);
    }

    public static MainFragmentViewModelFactory provideMainActivityViewModelFactory(Context context) {
        MyRepository repository = provideRepository(context.getApplicationContext());
        return new MainFragmentViewModelFactory(repository);
    }

}
