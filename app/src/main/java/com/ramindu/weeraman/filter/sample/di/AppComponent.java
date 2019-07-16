package com.ramindu.weeraman.filter.sample.di;

import com.ramindu.weeraman.filter.sample.data.Repository;
import com.ramindu.weeraman.filter.sample.filter.FilterManager;
import com.ramindu.weeraman.filter.sample.filter.FilterOptionMap;
import com.ramindu.weeraman.filter.sample.utils.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

@Singleton
@Component(modules = {UserRepositoryModule.class, ApiModule.class,SchedulerModule.class,
        DisposableModule.class,FilterOptionModule.class, FilterManager.class, DistanceCalculateModule.class})
public interface AppComponent {
    Repository getRepository() ;
    SchedulerProvider getSchedulerProvider();
    CompositeDisposable getDisposable();
    FilterOptionMap getFilterOptions();
    FilterManager getFilterManager();
    DistanceCalculateModule getGeoLocationCalculator();
}
