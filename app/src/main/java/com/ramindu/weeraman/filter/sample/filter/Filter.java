package com.ramindu.weeraman.filter.sample.filter;

public abstract class Filter {
    private int maxValue;
    private int minValue;

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    private int maxRange;
    private int minRange;

    public ValueFilterOption getOption() {
        return option;
    }

    public void setOption(ValueFilterOption option) {
        this.option = option;
    }

    private ValueFilterOption option;

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public abstract boolean isRangeFilterSatisfy(int value);

    public abstract boolean isValueFilterSatisfy(boolean value);


}
