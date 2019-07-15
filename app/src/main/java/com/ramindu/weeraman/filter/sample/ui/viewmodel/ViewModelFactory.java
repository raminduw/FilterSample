package com.ramindu.weeraman.filter.sample.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ramindu.weeraman.filter.sample.filter.FilterOptionMap;
import com.ramindu.weeraman.filter.sample.utils.SchedulerProvider;
import com.ramindu.weeraman.filter.sample.data.Repository;

import io.reactivex.disposables.CompositeDisposable;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository filterApi;
    private CompositeDisposable compositeDisposable;
    private SchedulerProvider schedulerProvider;
    private FilterOptionMap filterOptions;

    public ViewModelFactory(Repository filterApi, FilterOptionMap filterOptions , CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider) {
        this.filterApi = filterApi;
        this.compositeDisposable = compositeDisposable;
        this.schedulerProvider = schedulerProvider;
        this.filterOptions=filterOptions;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserListViewModel(filterApi,filterOptions, compositeDisposable, schedulerProvider);
    }
}
