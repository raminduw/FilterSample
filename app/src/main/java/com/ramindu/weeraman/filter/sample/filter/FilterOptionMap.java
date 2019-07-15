package com.ramindu.weeraman.filter.sample.filter;

import com.ramindu.weeraman.filter.sample.filter.filtertypes.AgeFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.CompatibilityFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.ContactFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.DistanceFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.FavouriteFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.FilterTypes;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.HeightFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.PhotoFilter;

import java.util.HashMap;

public class FilterOptionMap {
    private HashMap<FilterTypes, Filter> filterMap = new HashMap<>();
    public FilterOptionMap(){
        setDefaultFilters();
    }

    private void setDefaultFilters(){
        filterMap.put(FilterTypes.AGE,new AgeFilter());
        filterMap.put(FilterTypes.COMPATIBILITY,new CompatibilityFilter());
        filterMap.put(FilterTypes.HEIGHT,new HeightFilter());
        filterMap.put(FilterTypes.DISTANCE,new DistanceFilter());
        filterMap.put(FilterTypes.PHOTO,new PhotoFilter());
        filterMap.put(FilterTypes.CONTACT,new ContactFilter());
        filterMap.put(FilterTypes.FAVOURITE,new FavouriteFilter());
    }


    public HashMap<FilterTypes, Filter> getFilterMap() {
        return filterMap;
    }
    public void clearFilters(){
        filterMap.clear();
        setDefaultFilters();
    }

}
