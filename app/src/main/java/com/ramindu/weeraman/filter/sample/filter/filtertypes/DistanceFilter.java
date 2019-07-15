package com.ramindu.weeraman.filter.sample.filter.filtertypes;

import com.ramindu.weeraman.filter.sample.filter.Filter;

import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MAX_DISTANCE;
import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MAX_HEIGHT;
import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MIN_DISTANCE;
import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MIN_HEIGHT;

public class DistanceFilter extends Filter {

    public DistanceFilter(){
        super.setMinValue(FILTER_MIN_DISTANCE);
        super.setMaxValue(FILTER_MAX_DISTANCE);

        super.setMaxRange(FILTER_MAX_DISTANCE);
        super.setMinRange(FILTER_MIN_DISTANCE);
    }

    @Override
    public boolean isRangeFilterSatisfy(int value) {
        if (!(getMinValue() <= value && getMaxValue() >= value)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isValueFilterSatisfy(boolean value) {
        return false;
    }
}
