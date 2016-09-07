package com.anand.moziotestapp.ui.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.anand.moziotestapp.R;

/**
 * Provides Toolbar and Drawer. Currently provides only left side drawer.
 */
public class DrawerWithToolBarActivityStyleImpl extends DrawerActivityStyleImpl {
    private Toolbar mToolBar;

    protected DrawerWithToolBarActivityStyleImpl(@NonNull Activity activity) {
        super(activity);
    }

    @Override
    protected int getBaseLayout() {
        return R.layout.base_drawer_with_tool_bar_activity;
    }

    @Override
    public void initViews() {
        super.initViews();
        if (rootView == null) return;
        mToolBar = (Toolbar) rootView.findViewById(R.id.tool_bar);
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }
}
