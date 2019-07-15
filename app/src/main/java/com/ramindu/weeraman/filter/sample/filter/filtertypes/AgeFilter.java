package com.ramindu.weeraman.filter.sample.filter.filtertypes;

import com.ramindu.weeraman.filter.sample.filter.Filter;

import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MAX_AGE;
import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MIN_AGE;

public class AgeFilter extends Filter {


    public AgeFilter(){
        super.setMaxValue(FILTER_MAX_AGE);
        super.setMinValue(FILTER_MIN_AGE);

        super.setMaxRange(FILTER_MAX_AGE);
        super.setMinRange(FILTER_MIN_AGE);
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
