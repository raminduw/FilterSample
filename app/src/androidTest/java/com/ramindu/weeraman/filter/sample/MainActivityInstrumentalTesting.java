package com.ramindu.weeraman.filter.sample;


import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.ramindu.weeraman.filter.sample.filter.ValueFilterOption;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.FilterTypes;
import com.ramindu.weeraman.filter.sample.ui.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("DefaultFileTemplate")
@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityInstrumentalTesting {
    @Rule
    public IntentsTestRule<MainActivity> mainActivityActivityTestRule = new IntentsTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() {

    }

    @Test
    public void testFabButtonVisibility() {
        onView(ViewMatchers.withId(R.id.fabButton))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewVisibility() {
        onView(ViewMatchers.withId(R.id.recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testMenuItemVisibility() {
        onView(ViewMatchers.withId(R.id.action_settings))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }


    @Test
    public void testFilterDialogVisibility() {
        onView(ViewMatchers.withId(R.id.fabButton))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .perform(click());
        onView(withText((mainActivityActivityTestRule.getActivity().getResources().getString(R.string.filters))))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
    }

    @Test
    public void testFilterDialogCloseWithoutApplyFilter() {
        onView(ViewMatchers.withId(R.id.fabButton))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .perform(click());

        onView(ViewMatchers.withId(R.id.closeDialogImg))
                .inRoot(isDialog())
                .perform(click());

        onView(ViewMatchers.withId(R.id.recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testFilterDialogCloseAfterApplyFilter() {
        onView(ViewMatchers.withId(R.id.fabButton))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .perform(click());

        onView(ViewMatchers.withId(R.id.doneDialogImg))
                .inRoot(isDialog())
                .perform(click());

        onView(ViewMatchers.withId(R.id.recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testFilterDialogSeekFilter() {
        mainActivityActivityTestRule.getActivity().filterOptions.getFilterMap().get(FilterTypes.AGE).setMinValue(25);
        mainActivityActivityTestRule.getActivity().filterOptions.getFilterMap().get(FilterTypes.HEIGHT).setMinValue(155);

        onView(ViewMatchers.withId(R.id.fabButton))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .perform(click());

        onView(ViewMatchers.withId(R.id.textAgeMinValue))
                .check(matches(withText(String.valueOf(25))));

        onView(ViewMatchers.withId(R.id.textHeightMinValue))
                .check(matches(withText(String.valueOf(155))));
    }


    @Test
    public void testFilterDialogRadioButtonFilter() {
        mainActivityActivityTestRule.getActivity().filterOptions.getFilterMap().get(FilterTypes.PHOTO).setOption(ValueFilterOption.ALL);

        onView(ViewMatchers.withId(R.id.fabButton))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .perform(click());

        onView(ViewMatchers.withId(R.id.withoutPhoto))
                .check(matches(isNotChecked()));
        onView(ViewMatchers.withId(R.id.withPhoto))
                .check(matches(isNotChecked()));

        onView(ViewMatchers.withId(R.id.allPhoto))
                .check(matches(isChecked()));

    }

    @Test
    public void testFilterDialogWithChangeFilterAndApply() {

        onView(ViewMatchers.withId(R.id.fabButton))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .perform(click());

        onView(ViewMatchers.withId(R.id.withoutPhoto))
                .perform(click());

        onView(ViewMatchers.withId(R.id.allFavourite))
                .perform(click());

        onView(ViewMatchers.withId(R.id.doneDialogImg))
                .inRoot(isDialog())
                .perform(click());

        ValueFilterOption photoFilter = mainActivityActivityTestRule.getActivity().filterOptions.getFilterMap().
                get(FilterTypes.PHOTO).getOption();

        ValueFilterOption favouriteFilter = mainActivityActivityTestRule.getActivity().filterOptions.getFilterMap().
                get(FilterTypes.FAVOURITE).getOption();

        assertEquals(photoFilter, ValueFilterOption.NOT_IN);
        assertEquals(favouriteFilter, ValueFilterOption.ALL);


    }

    @Test
    public void testFilterDialogWithChangeFilterAndNoApply() {

        onView(ViewMatchers.withId(R.id.fabButton))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .perform(click());

        onView(ViewMatchers.withId(R.id.withoutPhoto))
                .perform(click());

        onView(ViewMatchers.withId(R.id.allFavourite))
                .perform(click());

        onView(ViewMatchers.withId(R.id.closeDialogImg))
                .inRoot(isDialog())
                .perform(click());

        ValueFilterOption photoFilter = mainActivityActivityTestRule.getActivity().filterOptions.getFilterMap().
                get(FilterTypes.PHOTO).getOption();

        ValueFilterOption favouriteFilter = mainActivityActivityTestRule.getActivity().filterOptions.getFilterMap().
                get(FilterTypes.FAVOURITE).getOption();

        assertEquals(photoFilter, ValueFilterOption.ALL);
        assertEquals(favouriteFilter, ValueFilterOption.ALL);
    }


}
