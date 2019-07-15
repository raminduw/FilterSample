package com.ramindu.weeraman.filter.sample.di;

import com.ramindu.weeraman.filter.sample.filter.FilterOptionMap;

import dagger.Module;
import dagger.Provides;


@Module
public class FilterOptionModule {
    private FilterOptionMap filterOptionMap;

    @Provides
    public FilterOptionMap provideFilterOptions(){
        filterOptionMap = new FilterOptionMap();
        return filterOptionMap;
    }
}
