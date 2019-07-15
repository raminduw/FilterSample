package com.ramindu.weeraman.filter.sample.di;

import com.ramindu.weeraman.filter.sample.utils.SchedulerProvider;
import com.ramindu.weeraman.filter.sample.utils.SchedulerProviderImp;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SchedulerModule {

    @Binds
    @Singleton
    abstract SchedulerProvider providerScheduler(SchedulerProviderImp schedulerProvider) ;
}
