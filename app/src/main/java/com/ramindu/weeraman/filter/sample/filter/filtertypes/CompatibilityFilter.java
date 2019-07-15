package com.ramindu.weeraman.filter.sample.filter.filtertypes;

import com.ramindu.weeraman.filter.sample.filter.Filter;

import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MAX_AGE;
import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MAX_COMPATIBILITY_SCORE;
import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MIN_AGE;
import static com.ramindu.weeraman.filter.sample.AppConstants.FILTER_MIN_COMPATIBILITY_SCORE;

public class CompatibilityFilter extends Filter {
    public CompatibilityFilter(){
        super.setMaxValue(FILTER_MAX_COMPATIBILITY_SCORE);
        super.setMinValue(FILTER_MIN_COMPATIBILITY_SCORE);

        super.setMaxRange(FILTER_MAX_COMPATIBILITY_SCORE);
        super.setMinRange(FILTER_MIN_COMPATIBILITY_SCORE);
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
