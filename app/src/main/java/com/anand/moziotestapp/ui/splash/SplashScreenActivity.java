package com.anand.moziotestapp.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.anand.moziotestapp.R;
import com.anand.moziotestapp.ui.base.ActivityStyle;
import com.anand.moziotestapp.ui.base.BaseActivity;
import com.anand.moziotestapp.ui.base.BaseActivityStyleProvider;
import com.anand.moziotestapp.ui.base.IActivityStyleImpl;
import com.anand.moziotestapp.ui.home.HomeActivity;

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
        }, 1000);

    }

    private void launchHomeScreen() {
        Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public IActivityStyleImpl getActivityStyleImpl() {
        return BaseActivityStyleProvider.getActivityImpl(this, ActivityStyle.DEFAULT);
    }
}
