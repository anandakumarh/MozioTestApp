package com.anand.moziotestapp.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anand.moziotestapp.R;
import com.anand.moziotestapp.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientDetailsFragment extends BaseFragment {

    public static final String TAG =PatientDetailsFragment.class.getName();

    public static PatientDetailsFragment getInstance() {
        return new PatientDetailsFragment();
    }

    public PatientDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            resetActionBar();
        }
    }

    private void resetActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setTitle(getString(R.string.patient_detail));
        actionBar.invalidateOptionsMenu();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        resetActionBar();
        return view;
    }

    @Override
    protected void initViews(View rootView) {

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_patient_details;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

}
