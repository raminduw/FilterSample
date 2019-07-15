package com.ramindu.weeraman.filter.sample;

import com.ramindu.weeraman.filter.sample.data.model.UserItem;
import com.ramindu.weeraman.filter.sample.data.remote.UserApi;
import com.ramindu.weeraman.filter.sample.filter.Filter;
import com.ramindu.weeraman.filter.sample.filter.FilterManager;
import com.ramindu.weeraman.filter.sample.filter.FilterOptionMap;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.AgeFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.CompatibilityFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.ContactFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.DistanceFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.FavouriteFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.FilterTypes;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.HeightFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.PhotoFilter;
import com.ramindu.weeraman.filter.sample.utils.GeoDistanceCalculator;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterManagerTest {
    UserApi userApi;
    FilterOptionMap filterOptions;
    FilterManager filterManager;
    UserItem loggedUser;
    UserItem userItem;
    GeoDistanceCalculator geoDistanceCalculator;

    @Before
    public void before() throws Exception {
        userApi = mock(UserApi.class);
        filterOptions = mock(FilterOptionMap.class);
        loggedUser = mock(UserItem.class);
        userItem = mock(UserItem.class);
        geoDistanceCalculator = mock(GeoDistanceCalculator.class);
        filterManager = new FilterManager(geoDistanceCalculator);
    }

    @Test
    public void testIsApplicableUserWhenAgeNotMatch() {
        HashMap<FilterTypes, Filter> filterMap = mock(HashMap.class);
        AgeFilter ageFilter = mock(AgeFilter.class);

        when(filterOptions.getFilterMap())
                .thenReturn(filterMap);

        when(filterMap.get(FilterTypes.AGE))
                .thenReturn(ageFilter);
        when(ageFilter.isRangeFilterSatisfy(anyInt()))
                .thenReturn(false);

        UserItem userItem = mock(UserItem.class);

        assertFalse(filterManager.isApplicableUser(userItem,loggedUser, filterOptions));
    }

    @Test
    public void testIsApplicableUserWhenCompatibilityNotMatch() {
        HashMap<FilterTypes, Filter> filterMap = mock(HashMap.class);
        AgeFilter ageFilter = mock(AgeFilter.class);
        CompatibilityFilter compatibilityFilter = mock(CompatibilityFilter.class);

        when(filterOptions.getFilterMap())
                .thenReturn(filterMap);

        when(filterMap.get(FilterTypes.AGE))
                .thenReturn(ageFilter);
        when(ageFilter.isRangeFilterSatisfy(anyInt()))
                .thenReturn(true);


        when(filterMap.get(FilterTypes.COMPATIBILITY))
                .thenReturn(compatibilityFilter);
        when(ageFilter.isRangeFilterSatisfy(anyInt()))
                .thenReturn(false);

        UserItem userItem = mock(UserItem.class);

        assertFalse(filterManager.isApplicableUser(userItem,loggedUser, filterOptions));
    }


    @Test
    public void testIsApplicableUserWhenAllMatch() {
        HashMap<FilterTypes, Filter> filterMap = mock(HashMap.class);
        AgeFilter ageFilter = mock(AgeFilter.class);
        CompatibilityFilter compatibilityFilter = mock(CompatibilityFilter.class);
        HeightFilter heightFilter = mock(HeightFilter.class);
        DistanceFilter distanceFilter = mock(DistanceFilter.class);
        FavouriteFilter favouriteFilter = mock(FavouriteFilter.class);
        PhotoFilter photoFilter = mock(PhotoFilter.class);
        ContactFilter contactFilter = mock(ContactFilter.class);

        when(filterOptions.getFilterMap())
                .thenReturn(filterMap);

        when(filterMap.get(FilterTypes.AGE))
                .thenReturn(ageFilter);
        when(ageFilter.isRangeFilterSatisfy(anyInt()))
                .thenReturn(true);

        when(filterMap.get(FilterTypes.COMPATIBILITY))
                .thenReturn(compatibilityFilter);
        when(compatibilityFilter.isRangeFilterSatisfy(anyInt()))
                .thenReturn(true);

        when(filterMap.get(FilterTypes.HEIGHT))
                .thenReturn(heightFilter);
        when(heightFilter.isRangeFilterSatisfy(anyInt()))
                .thenReturn(true);

        when(filterMap.get(FilterTypes.DISTANCE))
                .thenReturn(distanceFilter);
        when(geoDistanceCalculator.calculateDistanceToUser(userItem,loggedUser))
                .thenReturn(anyInt());
        when(distanceFilter.isRangeFilterSatisfy(100))
                .thenReturn(true);


        when(filterMap.get(FilterTypes.PHOTO))
                .thenReturn(photoFilter);
        when(photoFilter.isValueFilterSatisfy(anyBoolean()))
                .thenReturn(true);

        when(filterMap.get(FilterTypes.FAVOURITE))
                .thenReturn(favouriteFilter);
        when(favouriteFilter.isValueFilterSatisfy(anyBoolean()))
                .thenReturn(true);

        when(filterMap.get(FilterTypes.CONTACT))
                .thenReturn(contactFilter);
        when(contactFilter.isValueFilterSatisfy(anyBoolean()))
                .thenReturn(true);

        UserItem userItem = mock(UserItem.class);

        assertTrue(filterManager.isApplicableUser(userItem, loggedUser,filterOptions));
    }

}
