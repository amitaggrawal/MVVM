package com.amitaggrawal.thefactore.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.amitaggrawal.thefactore.MainApp;
import com.amitaggrawal.thefactore.data.local.MyEntity;
import com.amitaggrawal.thefactore.utilities.AppExecutors;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */
public class RemoteDataFetchingOperation {
    private static final String LOG_TAG = RemoteDataFetchingOperation.class.getSimpleName();
    private static final Object LOCK = new Object();
    private final AppExecutors mExecutors;
    private final MutableLiveData<MyEntity[]> mListItems;
    private static RemoteDataFetchingOperation sRemoteDataFetchingOperation = null;

    private RemoteDataFetchingOperation(AppExecutors appExecutors) {
        mExecutors = appExecutors;
        mListItems = new MutableLiveData<MyEntity[]>();
    }

    public static RemoteDataFetchingOperation getInstance(AppExecutors executors) {
        if (null == sRemoteDataFetchingOperation) {
            synchronized (LOCK) {
                sRemoteDataFetchingOperation = new RemoteDataFetchingOperation(executors);
            }
        }
        return sRemoteDataFetchingOperation;
    }

    public LiveData<MyEntity[]> fetch() {
        return mListItems;
    }

    public void performRemoteFetching() {
        mExecutors.networkIO().execute(() -> {
            RestClient.prepareOkHttpClient(MainApp.getURL()).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(LOG_TAG, "Network operation failed for some reason.");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    switch (response.code()) {
                        case 200:
                            parseServerResponse(response.body());
                            break;
                    }
                }
            });
        });
    }

    private void parseServerResponse(ResponseBody body) {
        if (null != body) {
            parseResponse(body);
        } else {
            Log.e(LOG_TAG, "received blank response from server");
        }
    }

    private void parseResponse(ResponseBody body) {
        String responseBody = null;
        try {
            responseBody =  body.string();//body.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RemoteResponse remoteResponse = null;
        try {
            remoteResponse = new RemoteResponseParser().parse(responseBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (null != remoteResponse) {
            mListItems.postValue(remoteResponse.getData());
        }

        Log.d(LOG_TAG, "Data sent successfully.");
    }

}
