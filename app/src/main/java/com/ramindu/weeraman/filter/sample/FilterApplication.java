package com.ramindu.weeraman.filter.sample;

import android.app.Application;

public class FilterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new AppConstants("https://testapi-b0e59.firebaseio.com/");
    }
}