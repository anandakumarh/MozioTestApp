package com.anand.moziotestapp.ui.home;

import android.support.v7.app.ActionBar;
import android.os.Bundle;

import com.anand.moziotestapp.R;
import com.anand.moziotestapp.ui.base.ActivityStyle;
import com.anand.moziotestapp.ui.base.BaseActivity;
import com.anand.moziotestapp.ui.base.BaseActivityStyleProvider;
import com.anand.moziotestapp.ui.base.IActivityStyleImpl;
import com.anand.moziotestapp.ui.base.IFragmentTransactionType;

public class HomeActivity extends BaseActivity implements HomeFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);
            actionBar.setTitle(R.string.home);
        }
        loadHomeFragment();
    }

    /**
     * Method which loads HomeFragment
     */
    private void loadHomeFragment() {
        loadFragment(R.id.content_layout, HomeFragment.getInstance(), HomeFragment.TAG, 0, 0,
                IFragmentTransactionType.REPLACE);
    }

    @Override
    public IActivityStyleImpl getActivityStyleImpl() {
        return BaseActivityStyleProvider.getActivityImpl(this, ActivityStyle.DRAWER_WITH_TOOLBAR);
    }

    @Override
    public void loadSyndromeResultScreen(String patientName, int syndromePercentage) {
        loadFragment(R.id.content_layout, SyndromeResultFragment.getInstance(patientName,
                syndromePercentage), SyndromeResultFragment.TAG, 0, 0,
                IFragmentTransactionType.ADD_TO_BACK_STACK_AND_REPLACE);
    }

    @Override
    public void loadPatientDetailScreen() {
        loadFragment(R.id.content_layout, PatientDetailsFragment.getInstance(), PatientDetailsFragment.TAG, 0, 0,
                IFragmentTransactionType.ADD_TO_BACK_STACK_AND_REPLACE);
    }
}
