package com.ramindu.weeraman.filter.sample.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ramindu.weeraman.filter.sample.data.model.UserItem;
import com.ramindu.weeraman.filter.sample.filter.FilterOptionMap;
import com.ramindu.weeraman.filter.sample.filter.ValueFilterOption;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.FilterTypes;
import com.ramindu.weeraman.filter.sample.ui.LoadingStatus;
import com.ramindu.weeraman.filter.sample.utils.SchedulerProvider;
import com.ramindu.weeraman.filter.sample.data.Repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class UserListViewModel extends ViewModel {

    public MutableLiveData<FilterOptionMap> getSelectedFilter() {
        return selectedFilter;
    }
    public MutableLiveData<List<UserItem>> getUserList() {
        return userList;
    }
    public MutableLiveData<LoadingStatus> getStatusObserver() {
        return statusObserver;
    }

    private MutableLiveData<LoadingStatus> statusObserver = new MutableLiveData<>();
    private MutableLiveData<FilterOptionMap> selectedFilter = new MutableLiveData<>();
    private MutableLiveData<List<UserItem>> userList = new MutableLiveData<>();
    private Repository repository;
    private CompositeDisposable compositeDisposable;
    private SchedulerProvider schedulerProvider;
    private FilterOptionMap filterOptionMap;

    public UserListViewModel(Repository repository, FilterOptionMap filterOptionMap,
                             CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
        this.schedulerProvider = schedulerProvider;
        this.filterOptionMap = filterOptionMap;
    }


    public void setRangeFilterValues(FilterTypes filterTypeEnum, int maxVal, int minVal) {
        if (maxVal > 0) {
            filterOptionMap.getFilterMap().get(filterTypeEnum).setMaxValue(maxVal);
        }
        if (minVal > 0) {
            filterOptionMap.getFilterMap().get(filterTypeEnum).setMinValue(minVal);
        }
    }

    public void setFilterValues(FilterTypes filterTypeEnum, ValueFilterOption valueFilterOptionsEnum) {
        filterOptionMap.getFilterMap().get(filterTypeEnum).setOption(valueFilterOptionsEnum);
    }

    public void getDataFromRemote() {
        if (!repository.isDataAvailable()) {
            getDataFromNetwork();
        }
    }

    private void getDataFromNetwork() {
        repository.getData().
                subscribeOn(schedulerProvider.getBackgroundScheduler())
                .observeOn(schedulerProvider.getMainScheduler())
                .subscribe(new Observer<List<UserItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                        statusObserver.setValue(LoadingStatus.LOADING);
                    }

                    @Override
                    public void onNext(List<UserItem> userItems) {
                        selectedFilter.setValue(filterOptionMap);
                        statusObserver.setValue(LoadingStatus.SUCCESS);
                        userList.setValue(userItems);
                        repository.setAllUserList(userItems);
                    }

                    @Override
                    public void onError(Throwable e) {
                        statusObserver.setValue(LoadingStatus.FAIL);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getDataFromLocalWithFilters() {

        repository.getLocalUserList()
                .subscribeOn(schedulerProvider.getBackgroundScheduler())
                .filter(new Predicate<UserItem>() {
                    @Override
                    public boolean test(UserItem v) {
                        return repository.isApplicableUser(v,repository.getLoggedUser(), filterOptionMap);
                    }
                })
                .toList()
                .observeOn(schedulerProvider.getMainScheduler())
                .subscribe(new SingleObserver<List<UserItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<UserItem> userItems) {
                        selectedFilter.setValue(filterOptionMap);
                        statusObserver.setValue(LoadingStatus.SUCCESS);
                        userList.setValue(userItems);
                    }

                    @Override
                    public void onError(Throwable e) {
                        statusObserver.setValue(LoadingStatus.FAIL);
                    }
                });

    }

    public void resetData() {
        repository.clearData();
        filterOptionMap.clearFilters();
        selectedFilter.setValue(filterOptionMap);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
