package com.ramindu.weeraman.filter.sample.data;

import com.ramindu.weeraman.filter.sample.data.model.City;
import com.ramindu.weeraman.filter.sample.data.model.UserItem;
import com.ramindu.weeraman.filter.sample.data.remote.UserApi;
import com.ramindu.weeraman.filter.sample.filter.FilterManager;
import com.ramindu.weeraman.filter.sample.filter.FilterOptionMap;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class Repository {
    private FilterManager filterManager;
    private UserApi userApi;
    private List<UserItem> userItemListAll = new ArrayList<>();

    @Inject
    public Repository(UserApi userApi, FilterManager filterManager) {
        this.userApi = userApi;
        this.filterManager = filterManager;
    }

    public Observable<List<UserItem>> getData() {
        return userApi.getProductList();
    }

    public Observable<UserItem> getLocalUserList() {
        return Observable.fromIterable(userItemListAll);
    }

    public void setAllUserList(List<UserItem> allUserList) {
        if (allUserList != null) {
            userItemListAll.addAll(allUserList);
        }
    }

    public boolean isDataAvailable() {
        if (userItemListAll != null && userItemListAll.size() > 0) {
            return true;
        }
        return false;
    }

    public void clearData() {
        if (userItemListAll != null) {
            userItemListAll.clear();
        }
    }

    public boolean isApplicableUser(UserItem userItem, UserItem loggedUser, FilterOptionMap filterOptions) {
        return filterManager.isApplicableUser(userItem, loggedUser, filterOptions);
    }

    /*
    This is use to calculate distance between logged user and users in the list.
    Need to replace with actual implementation.
    */
    public UserItem getLoggedUser() {
        UserItem userItem = new UserItem();
        City city = new City();
        city.setLat(53.801277);
        city.setLon(-1.548567);
        userItem.setCity(city);
        return userItem;
    }
}
