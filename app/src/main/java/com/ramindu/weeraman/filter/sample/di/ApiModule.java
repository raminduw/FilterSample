package com.ramindu.weeraman.filter.sample.di;

import com.ramindu.weeraman.filter.sample.AppConstants;
import com.ramindu.weeraman.filter.sample.data.remote.UserApi;
import com.ramindu.weeraman.filter.sample.utils.SchedulerProvider;
import com.ramindu.weeraman.filter.sample.utils.SchedulerProviderImp;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    UserApi providesApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(UserApi.class);
    }

}
