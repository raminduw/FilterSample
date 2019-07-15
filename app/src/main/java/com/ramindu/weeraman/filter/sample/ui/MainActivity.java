package com.ramindu.weeraman.filter.sample.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ramindu.weeraman.filter.sample.BaseActivity;
import com.ramindu.weeraman.filter.sample.R;
import com.ramindu.weeraman.filter.sample.data.model.UserItem;
import com.ramindu.weeraman.filter.sample.filter.Filter;
import com.ramindu.weeraman.filter.sample.filter.FilterOptionMap;
import com.ramindu.weeraman.filter.sample.filter.ValueFilterOption;
import com.ramindu.weeraman.filter.sample.filter.filtertypes.FilterTypes;
import com.ramindu.weeraman.filter.sample.ui.adapter.UserAdapter;
import com.ramindu.weeraman.filter.sample.ui.recyclerview.MarginDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainActivityUIHelper.FilterDialogActionListener {
    private FloatingActionButton filterFabButton;
    private FilterOptionMap filterOptionMap;
    private RecyclerView recyclerView;
    private List<UserItem> userItems = new ArrayList<>();
    private UserAdapter userAdapter;
    private MainActivityUIHelper mainActivityUIHelper;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainActivityUIHelper = new MainActivityUIHelper(this);
        progressDialog = new ProgressDialog(this);

        filterFabButton = findViewById(R.id.fabButton);
        filterFabButton.setOnClickListener(view ->
                showFilterDialog()
        );

        initRecyclerView();
        setObservables();
        userListViewModel.getDataFromRemote();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new MarginDecoration(this));
        recyclerView.setHasFixedSize(true);
        userAdapter = new UserAdapter(userItems);
        recyclerView.setAdapter(userAdapter);
    }

    private void setObservables() {
        userListViewModel.getSelectedFilter().observe(this, filterOptionMap ->
                MainActivity.this.filterOptionMap = filterOptionMap
        );

        userListViewModel.getUserList().observe(this, userItems -> showList(userItems));

        userListViewModel.getStatusObserver().observe(this, loadingStatus -> {
            if (LoadingStatus.LOADING == loadingStatus) {
                showProgress();
            } else if (LoadingStatus.FAIL == loadingStatus) {
                showToastMessage(getString(R.string.user_list_loading_failed));
                hideProgress();
            } else if (LoadingStatus.SUCCESS == loadingStatus) {
                hideProgress();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            reloadData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFilterDialog() {

        final View dialogView = View.inflate(this, R.layout.filter_dialog, null);
        final Dialog dialog = new Dialog(this, R.style.MyAlertDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);

        ImageView cancelBtn = (ImageView) dialog.findViewById(R.id.closeDialogImg);
        ImageView doneBtn = (ImageView) dialog.findViewById(R.id.doneDialogImg);

        // ui range filter elements
        final CrystalRangeSeekbar compatibilityFilter = (CrystalRangeSeekbar) dialog.findViewById(R.id.compatibilitySeekBar);
        final TextView textCompatibilityMinValue = (TextView) dialog.findViewById(R.id.textCompatibilityMinValue);
        final TextView textCompatibilityMaxValue = (TextView) dialog.findViewById(R.id.textCompatibilityMaxValue);
        initMultiValRangeFilter(FilterTypes.COMPATIBILITY, compatibilityFilter, textCompatibilityMinValue, textCompatibilityMaxValue);

        final CrystalRangeSeekbar ageFilter = (CrystalRangeSeekbar) dialog.findViewById(R.id.ageSeekBar);
        final TextView textAgeMinValue = (TextView) dialog.findViewById(R.id.textAgeMinValue);
        final TextView textAgeMaxValue = (TextView) dialog.findViewById(R.id.textAgeMaxValue);
        initMultiValRangeFilter(FilterTypes.AGE, ageFilter, textAgeMinValue, textAgeMaxValue);

        final CrystalRangeSeekbar heightFilter = (CrystalRangeSeekbar) dialog.findViewById(R.id.heightSeekBar);
        final TextView textHeightMinValue = (TextView) dialog.findViewById(R.id.textHeightMinValue);
        final TextView textHeightMaxValue = (TextView) dialog.findViewById(R.id.textHeightMaxValue);
        initMultiValRangeFilter(FilterTypes.HEIGHT, heightFilter, textHeightMinValue, textHeightMaxValue);

        final CrystalSeekbar distanceFilter = (CrystalSeekbar) dialog.findViewById(R.id.distanceSeekBar);
        final TextView textDistanceMinValue = (TextView) dialog.findViewById(R.id.textDistanceMinValue);
        textDistanceMinValue.setText(String.valueOf(filterOptionMap.getFilterMap().get(FilterTypes.DISTANCE).getMinValue()));
        final TextView textDistanceMaxValue = (TextView) dialog.findViewById(R.id.textDistanceMaxValue);
        initSingleValRangeFilter(FilterTypes.DISTANCE, distanceFilter, textDistanceMaxValue);

        // ui radio button filter set elements
        RadioButton withPhoto = (RadioButton) dialog.findViewById(R.id.withPhoto);
        RadioButton withoutPhoto = (RadioButton) dialog.findViewById(R.id.withoutPhoto);
        RadioButton allPhoto = (RadioButton) dialog.findViewById(R.id.allPhoto);
        initRadioButtonFilterSet(FilterTypes.PHOTO, withPhoto, withoutPhoto, allPhoto);

        RadioButton inContact = (RadioButton) dialog.findViewById(R.id.inContact);
        RadioButton notInContact = (RadioButton) dialog.findViewById(R.id.notInContact);
        RadioButton allContact = (RadioButton) dialog.findViewById(R.id.allContact);
        initRadioButtonFilterSet(FilterTypes.CONTACT, inContact, notInContact, allContact);

        RadioButton inFavourite = (RadioButton) dialog.findViewById(R.id.inFavourite);
        RadioButton notInFavourite = (RadioButton) dialog.findViewById(R.id.notInFavourite);
        RadioButton allFavourite = (RadioButton) dialog.findViewById(R.id.allFavourite);
        initRadioButtonFilterSet(FilterTypes.FAVOURITE, inFavourite, notInFavourite, allFavourite);

        cancelBtn.setOnClickListener(v ->
                mainActivityUIHelper.revealShow(dialogView, filterFabButton, false, false, dialog));


        doneBtn.setOnClickListener(v -> {
            userListViewModel.setRangeFilterValues(FilterTypes.AGE, ageFilter.getSelectedMaxValue().intValue(),
                    ageFilter.getSelectedMinValue().intValue());
            userListViewModel.setRangeFilterValues(FilterTypes.COMPATIBILITY,
                    compatibilityFilter.getSelectedMaxValue().intValue(), compatibilityFilter.getSelectedMinValue().intValue());
            userListViewModel.setRangeFilterValues(FilterTypes.HEIGHT, heightFilter.getSelectedMaxValue().intValue(),
                    heightFilter.getSelectedMinValue().intValue());
            userListViewModel.setRangeFilterValues(FilterTypes.DISTANCE, distanceFilter.getSelectedMinValue().intValue(), -1);

            setRadioButtonValues(FilterTypes.FAVOURITE, inFavourite, notInFavourite, allFavourite);
            setRadioButtonValues(FilterTypes.CONTACT, inContact, notInContact, allContact);
            setRadioButtonValues(FilterTypes.PHOTO, withPhoto, withoutPhoto, allPhoto);

            mainActivityUIHelper.revealShow(dialogView, filterFabButton, false, true, dialog);
        });

        dialog.setOnShowListener(dialogInterface ->
                mainActivityUIHelper.revealShow(dialogView, filterFabButton, true, false, null)
        );

        dialog.setOnKeyListener((dialogInterface, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_BACK) {
                mainActivityUIHelper.revealShow(dialogView, filterFabButton, false, false, dialog);
                return true;
            }
            return false;
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    private void showList(List<UserItem> userItems) {
        this.userItems.clear();
        this.userItems.addAll(userItems);
        userAdapter.notifyDataSetChanged();

        if (userItems.size() == 0) {
            showToastMessage(getString(R.string.no_user_available));
        }
    }

    private void setRangeFilter(CrystalRangeSeekbar rangeSeekBar, FilterTypes filterType) {

        Filter filter = filterOptionMap.getFilterMap().get(filterType);
        rangeSeekBar.setMinStartValue(filter.getMinValue())
                .setMaxStartValue(filter.getMaxValue())
                .setMinValue(filter.getMinRange()).
                setMaxValue(filter.getMaxRange()).apply();
    }

    private void setValueFilter(CrystalSeekbar seekBar, FilterTypes filterType) {

        Filter filter = filterOptionMap.getFilterMap().get(filterType);
        seekBar.setMinStartValue(filter.getMaxValue())
                .setMinValue(filter.getMinRange()).
                setMaxValue(filter.getMaxRange()).apply();
    }

    private void initMultiValRangeFilter(FilterTypes filterType,
                                         CrystalRangeSeekbar rangeSeekBar,
                                         TextView minValTextView,
                                         TextView maxValTextView) {
        setRangeFilter(rangeSeekBar, filterType);

        rangeSeekBar.setOnRangeSeekbarChangeListener((minValue, maxValue) -> {
            minValTextView.setText(String.valueOf(minValue));
            maxValTextView.setText(String.valueOf(maxValue));
        });

    }

    private void initSingleValRangeFilter(FilterTypes filterType,
                                          CrystalSeekbar seekBar,
                                          TextView maxValTextView) {
        setValueFilter(seekBar, filterType);

        seekBar.setOnSeekbarChangeListener(minValue -> maxValTextView.setText(String.valueOf(minValue)));
    }

    private void initRadioButtonFilterSet(FilterTypes filterType,
                                          RadioButton withOption,
                                          RadioButton withoutOption,
                                          RadioButton all) {

        ValueFilterOption filterOption = filterOptionMap.getFilterMap().get(filterType).getOption();

        if (filterOption == ValueFilterOption.ALL) {
            all.setChecked(true);
            withoutOption.setChecked(false);
            withOption.setChecked(false);
        } else if (filterOption == ValueFilterOption.IN) {
            all.setChecked(false);
            withoutOption.setChecked(false);
            withOption.setChecked(true);
        } else if (filterOption == ValueFilterOption.NOT_IN) {
            all.setChecked(false);
            withoutOption.setChecked(true);
            withOption.setChecked(false);
        }
    }

    private void setRadioButtonValues(FilterTypes filterType,
                                      RadioButton withOption,
                                      RadioButton withoutOption,
                                      RadioButton allOption) {
        if (allOption.isChecked()) {
            userListViewModel.setFilterValues(filterType, ValueFilterOption.ALL);
        } else if (withOption.isChecked()) {
            userListViewModel.setFilterValues(filterType, ValueFilterOption.IN);
        } else if (withoutOption.isChecked()) {
            userListViewModel.setFilterValues(filterType, ValueFilterOption.NOT_IN);
        }
    }


    private void reloadData() {
        userListViewModel.resetData();
        userListViewModel.getDataFromRemote();
    }

    @Override
    public void onFilterApply() {
        userListViewModel.getDataFromLocalWithFilters();
    }


    public void showProgress() {
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();
    }


    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void showToastMessage(String toastMsg) {
        Toast.makeText(this, toastMsg,
                Toast.LENGTH_LONG).show();
    }

}
