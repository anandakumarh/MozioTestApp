package com.anand.moziotestapp.ui.home;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anand.moziotestapp.R;
import com.anand.moziotestapp.ui.base.BaseFragment;
import com.anand.moziotestapp.utils.Constants;

public class SyndromeResultFragment extends BaseFragment {

    public static final String TAG = SyndromeResultFragment.class.getName();

    public static SyndromeResultFragment getInstance(String patientName, int percentage) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BundleKeys.KEY_PATIENT_NAME, patientName);
        bundle.putInt(Constants.BundleKeys.KEY_SYNDROME_PERCENTAGE, percentage);
        SyndromeResultFragment fragment = new SyndromeResultFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public SyndromeResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        resetActionBar();
        return view;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_syndrome_result;
    }

    @Override
    public boolean onBackPressed() {
        return false;
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
        actionBar.setTitle(getString(R.string.syndrome_result));
        actionBar.invalidateOptionsMenu();
    }

    @Override
    protected void initViews(View rootView) {
        TextView resultView = (TextView) rootView.findViewById(R.id.result_view);
        String patientName;
        int percentage;
        if (getArguments() != null && !TextUtils.isEmpty(getArguments().getString(Constants.BundleKeys.KEY_PATIENT_NAME))
                && getArguments().getInt(Constants.BundleKeys.KEY_SYNDROME_PERCENTAGE) >= 0) {
            patientName = getArguments().getString(Constants.BundleKeys.KEY_PATIENT_NAME);
            percentage = getArguments().getInt(Constants.BundleKeys.KEY_SYNDROME_PERCENTAGE);
        } else {
            return;
        }
        switch (percentage) {
            case 0:
                setResultMessage(resultView, getSyndromeNotFoundMessage(patientName));
                break;
            case 100:
            case 75:
            case 50:
            case 25:
                setResultMessage(resultView, getSyndromeProbabilityMessage(patientName, percentage));
                break;
            default:
                break;
        }

    }

    private void setResultMessage(TextView resultView, String message) {
        resultView.setText(message);
    }

    private String getSyndromeNotFoundMessage(String patientName) {
        return patientName + " does not seem to have any Todd's Syndrome";
    }

    private String getSyndromeProbabilityMessage(String patientName, int percentage) {
        return patientName + " has " + percentage + "% probability of Todd's Syndrome";
    }

}
