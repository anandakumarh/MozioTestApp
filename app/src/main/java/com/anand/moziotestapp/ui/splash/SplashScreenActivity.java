package com.anand.moziotestapp.ui.splash;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anand.moziotestapp.R;
import com.anand.moziotestapp.ui.base.ActivityStyle;
import com.anand.moziotestapp.ui.base.BaseActivity;
import com.anand.moziotestapp.ui.base.BaseActivityStyleProvider;
import com.anand.moziotestapp.ui.base.IActivityStyleImpl;

public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        launchNextScreen();
    }

    private void launchNextScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchHomeScreen();
            }
        }, 2000);

    }

    private void launchHomeScreen() {

    }

    @Override
    public IActivityStyleImpl getActivityStyleImpl() {
        return BaseActivityStyleProvider.getActivityImpl(this, ActivityStyle.DEFAULT);
    }
}
