package com.koolearn.android.kooreader;

import android.content.Context;

import com.koolearn.klibrary.ui.android.library.ZLAndroidApplication;


public class KooReaderApplication extends ZLAndroidApplication {

    public static KooReaderApplication app;

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        app = this;
    }

    public static Context getContext(){return context;}
}