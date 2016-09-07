package com.anand.moziotestapp.ui.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import com.anand.moziotestapp.R;

/**
 * Provides a drawer. Currently supporting only left drawer.
 */
public class DrawerActivityStyleImpl extends DefaultActivityStyleImpl {
    private DrawerLayout mDrawerLayout;


    protected DrawerActivityStyleImpl(@NonNull Activity activity) {
        super(activity);
    }

    @Override
    protected int getBaseLayout() {
        return R.layout.base_drawer_activity_layout;
    }

    @Override
    public void initViews() {
        super.initViews();
        if (rootView == null) return;
        mDrawerLayout = (DrawerLayout) rootView.findViewById(R.id.base_drawer_layout);
    }

    public void closeDrawer(@OxigneEdgeGravity int gravity) {
        if (mDrawerLayout == null) return;
        if (isDrawerOpen(gravity)) {
            mDrawerLayout.closeDrawer(gravity);
        }
    }

    public boolean isDrawerOpen(@OxigneEdgeGravity int gravity) {
        if (mDrawerLayout == null) return false;
        return mDrawerLayout.isDrawerOpen(gravity);
    }

    public void openDrawer(@OxigneEdgeGravity int gravity) {
        if (mDrawerLayout == null) return;
        if (!isDrawerOpen(gravity)) {
            mDrawerLayout.openDrawer(gravity);
        }
    }

    public void lockDrawer(@OxigneEdgeGravity int gravity) {
        if (mDrawerLayout == null) return;
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, gravity);
    }

    public void unlockDrawer(@OxigneEdgeGravity int gravity) {
        if (mDrawerLayout == null) return;
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, gravity);
    }
}
