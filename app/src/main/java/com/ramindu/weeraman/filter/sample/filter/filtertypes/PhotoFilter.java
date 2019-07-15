package com.ramindu.weeraman.filter.sample.filter.filtertypes;

import com.ramindu.weeraman.filter.sample.filter.Filter;
import com.ramindu.weeraman.filter.sample.filter.ValueFilterOption;

public class PhotoFilter extends Filter {

    public PhotoFilter(){
        super.setOption(ValueFilterOption.ALL);
    }

    @Override
    public boolean isRangeFilterSatisfy(int value) {
        return false;
    }

    @Override
    public  boolean isValueFilterSatisfy(boolean value) {
        if (getOption() == ValueFilterOption.ALL) {
            return true;
        }

        if (getOption() == ValueFilterOption.IN && value) {
            return true;
        }

        if (getOption() == ValueFilterOption.NOT_IN && !value) {
            return true;
        }

        return false;
    }
}
