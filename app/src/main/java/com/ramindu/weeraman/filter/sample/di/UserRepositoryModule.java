package com.ramindu.weeraman.filter.sample.di;

import com.ramindu.weeraman.filter.sample.data.remote.UserApi;
import com.ramindu.weeraman.filter.sample.data.Repository;
import com.ramindu.weeraman.filter.sample.filter.FilterManager;
import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class, FilterManager.class})
public class UserRepositoryModule {
    @Provides
    Repository provideRemoteDataSource(UserApi userApi,FilterManager filterManager) {
        return new Repository(userApi,filterManager);
    }
}
