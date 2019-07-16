package com.ramindu.weeraman.filter.sample;

import android.app.Application;

public class MockFilterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new AppConstants("https://testmockapi-d23bc.firebaseio.com/");
    }
}
