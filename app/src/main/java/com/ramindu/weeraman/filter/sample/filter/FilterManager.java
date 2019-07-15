package com.ramindu.weeraman.filter.sample.filter;

import com.ramindu.weeraman.filter.sample.data.model.UserItem;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.FilterTypes;
import com.ramindu.weeraman.filter.sample.utils.GeoDistanceCalculator;

import java.util.HashMap;

import javax.inject.Inject;

import dagger.Module;

@Module
public class FilterManager {

    private GeoDistanceCalculator geoDistanceCalculator;

    @Inject
    public FilterManager(GeoDistanceCalculator geoDistanceCalculator) {
        this.geoDistanceCalculator = geoDistanceCalculator;
    }

    public boolean isApplicableUser(UserItem userItem,UserItem loggedUser, FilterOptionMap filterOptions) {
        HashMap<FilterTypes, Filter> filterMap = filterOptions.getFilterMap();

        int age = userItem.getAge();
        if (!filterMap.get(FilterTypes.AGE).isRangeFilterSatisfy(age)) {
            return false;
        }

        int compatibilityScore = (int) (userItem.getCompatibilityScore() * 100);
        if (!filterMap.get(FilterTypes.COMPATIBILITY).isRangeFilterSatisfy(compatibilityScore)) {
            return false;
        }

        int height = userItem.getHeightInCm();
        if (!filterMap.get(FilterTypes.HEIGHT).isRangeFilterSatisfy(height)) {
            return false;
        }

        int distance = distanceToUser(userItem,loggedUser);
        if (!filterMap.get(FilterTypes.DISTANCE).isRangeFilterSatisfy(distance)) {
            return false;
        }

        boolean favourite = userItem.getFavourite();
        if (!filterMap.get(FilterTypes.FAVOURITE).isValueFilterSatisfy(favourite)) {
            return false;
        }

        boolean hasPhoto = isUserHasPhoto(userItem);
        if (!filterMap.get(FilterTypes.PHOTO).isValueFilterSatisfy(hasPhoto)) {
            return false;
        }

        boolean contact = isUserInContact(userItem);
        if (!filterMap.get(FilterTypes.CONTACT).isValueFilterSatisfy(contact)) {
            return false;
        }
        return true;
    }

    private boolean isUserHasPhoto(UserItem userItem) {
        return userItem.getMainPhoto() != null;
    }

    private boolean isUserInContact(UserItem userItem) {
        return userItem.getContactsExchanged() != 0;
    }

    private int distanceToUser(UserItem userItem, UserItem loggedUser) {
        return geoDistanceCalculator.calculateDistanceToUser(userItem,loggedUser);
    }

}
