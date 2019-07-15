package com.ramindu.weeraman.filter.sample.filter.filtertypes;

import com.ramindu.weeraman.filter.sample.filter.Filter;

import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MAX_COMPATIBILITY_SCORE;
import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MAX_HEIGHT;
import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MIN_COMPATIBILITY_SCORE;
import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MIN_HEIGHT;

public class HeightFilter extends Filter {

    public HeightFilter(){
        super.setMaxValue(FILTER_MAX_HEIGHT);
        super.setMinValue(FILTER_MIN_HEIGHT);

        super.setMaxRange(FILTER_MAX_HEIGHT);
        super.setMinRange(FILTER_MIN_HEIGHT);
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
