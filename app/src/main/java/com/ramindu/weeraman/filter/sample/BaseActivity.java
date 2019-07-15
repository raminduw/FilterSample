package com.ramindu.weeraman.filter.sample;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;


import com.ramindu.weeraman.filter.sample.di.AppComponent;
import com.ramindu.weeraman.filter.sample.data.Repository;
import com.ramindu.weeraman.filter.sample.di.DaggerAppComponent;
import com.ramindu.weeraman.filter.sample.filter.FilterOptionMap;
import com.ramindu.weeraman.filter.sample.ui.viewmodel.UserListViewModel;
import com.ramindu.weeraman.filter.sample.ui.viewmodel.ViewModelFactory;
import com.ramindu.weeraman.filter.sample.utils.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class BaseActivity extends AppCompatActivity {

    protected Activity activity;
    protected Repository repository;
    protected SchedulerProvider schedulerProvider;
    protected CompositeDisposable compositeDisposable;
    protected FilterOptionMap filterOptions;
    private AppComponent appComponent;
    protected UserListViewModel userListViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        initDagger();
    }

    private void initDagger() {
        appComponent = DaggerAppComponent.builder().build();
        repository = appComponent.getRepository();
        schedulerProvider = appComponent.getSchedulerProvider();
        compositeDisposable = appComponent.getDisposable();
        filterOptions = appComponent.getFilterOptions();
        ViewModelFactory factory = new ViewModelFactory(repository,filterOptions ,compositeDisposable,schedulerProvider);
        userListViewModel = ViewModelProviders.of(this, factory).get(UserListViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

