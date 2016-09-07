package com.anand.moziotestapp.ui.home;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anand.moziotestapp.R;
import com.anand.moziotestapp.ui.base.ActivityStyle;
import com.anand.moziotestapp.ui.base.BaseActivity;
import com.anand.moziotestapp.ui.base.BaseActivityStyleProvider;
import com.anand.moziotestapp.ui.base.IActivityStyleImpl;
import com.anand.moziotestapp.ui.base.IFragmentTransactionType;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);
            actionBar.setTitle(R.string.home);
        }
        loadPatientDetailFragment();
    }

    private void loadPatientDetailFragment() {
        loadFragment(R.id.content_layout, PatientDetailsFragment.getInstance(), PatientDetailsFragment.TAG, 0, 0,
                IFragmentTransactionType.REPLACE);
    }

    @Override
    public IActivityStyleImpl getActivityStyleImpl() {
        return BaseActivityStyleProvider.getActivityImpl(this, ActivityStyle.DRAWER_WITH_TOOLBAR);
    }
}
