package com.ramindu.weeraman.filter.sample;

import com.ramindu.weeraman.filter.sample.filter.ValueFilterOption;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.CompatibilityFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.DistanceFilter;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.PhotoFilter;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class FilterTypeTest {

    CompatibilityFilter compatibilityFilter;
    PhotoFilter photoFilter;
    DistanceFilter distanceFilter;


    @Before
    public void before() throws Exception {
        compatibilityFilter = new CompatibilityFilter();
        distanceFilter = new DistanceFilter();
        photoFilter = new PhotoFilter();
    }

    @Test
    public void testCompatibilityFilter() {
        assertTrue(compatibilityFilter.isRangeFilterSatisfy(40));
        assertTrue(compatibilityFilter.isRangeFilterSatisfy(0));
        assertTrue(compatibilityFilter.isRangeFilterSatisfy(99));
        assertFalse(compatibilityFilter.isRangeFilterSatisfy(300));
    }

    @Test
    public void testDistanceFilter() {
        assertTrue(distanceFilter.isRangeFilterSatisfy(100));
        assertFalse(distanceFilter.isRangeFilterSatisfy(23));
        assertTrue(distanceFilter.isRangeFilterSatisfy(300));
        assertFalse(distanceFilter.isRangeFilterSatisfy(500));
    }

    @Test
    public void testPhotoFilterALLOption() {
        photoFilter.setOption(ValueFilterOption.ALL);
        assertTrue(photoFilter.isValueFilterSatisfy(true));
        assertTrue(photoFilter.isValueFilterSatisfy(false));
    }

    @Test
    public void testPhotoFilterINOption() {
        photoFilter.setOption(ValueFilterOption.IN);
        assertTrue(photoFilter.isValueFilterSatisfy(true));
        assertFalse(photoFilter.isValueFilterSatisfy(false));
    }

    @Test
    public void testPhotoFilterNOTINOption() {
        photoFilter.setOption(ValueFilterOption.NOT_IN);
        assertTrue(photoFilter.isValueFilterSatisfy(false));
        assertFalse(photoFilter.isValueFilterSatisfy(true));
    }
}
