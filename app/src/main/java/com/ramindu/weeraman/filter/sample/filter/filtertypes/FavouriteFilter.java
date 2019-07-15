package com.ramindu.weeraman.filter.sample.filter.filtertypes;

import com.ramindu.weeraman.filter.sample.filter.Filter;
import com.ramindu.weeraman.filter.sample.filter.ValueFilterOption;

public class FavouriteFilter extends Filter {

    public FavouriteFilter() {
        super.setOption(ValueFilterOption.ALL);
    }

    @Override
    public boolean isRangeFilterSatisfy(int value) {
        return false;
    }

    @Override
    public boolean isValueFilterSatisfy(boolean value) {
        if (getOption() == ValueFilterOption.ALL) {
            return true;
        }

        if (value && getOption() == ValueFilterOption.IN ) {
            return true;
        }

        if (!value && getOption() == ValueFilterOption.NOT_IN) {
            return true;
        }

        return false;

    }
}
