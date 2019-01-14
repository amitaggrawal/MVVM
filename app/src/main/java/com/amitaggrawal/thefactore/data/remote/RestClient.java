package com.amitaggrawal.thefactore.data.remote;

import android.util.Log;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */
public class RestClient {
    private static final String LOG_TAG = RestClient.class.getCanonicalName();

    private static OkHttpClient sOkHttpClient;
    private static final String CONTENT_TYPE = "content-type";

    private static final MediaType JSON = MediaType.parse("application/json");

    private static OkHttpClient getOkHttpClientInstance() {
        if (null == sOkHttpClient) {
            sOkHttpClient = new OkHttpClient.Builder()
                    .build();
            Log.d(LOG_TAG, "New okHttpClient instance created");
        }
        return sOkHttpClient;
    }

    public static Call prepareOkHttpClient(String url) {
        sOkHttpClient = getOkHttpClientInstance();

        RequestBody requestBody = RequestBody.create(JSON, "{\"user_id\" : \"165\"}");

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(CONTENT_TYPE, "application/json")
                .method("POST", requestBody)
                .build();

        return sOkHttpClient.newCall(request);
    }

}
