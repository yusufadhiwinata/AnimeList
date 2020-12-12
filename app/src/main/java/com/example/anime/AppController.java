package com.example.anime;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
}
