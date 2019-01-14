package com.amitaggrawal.thefactore;

import android.app.Application;
import android.content.Context;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */
public class MainApp extends Application {

    private static Context context;

    private static final String URL = "http://oneteam.in/appadmin_testing/mobile/dashboard/";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static String getURL() {
        return URL;
    }
}
