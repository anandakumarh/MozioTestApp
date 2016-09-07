package com.anand.moziotestapp.ui.base;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import com.anand.moziotestapp.R;


/**
 * Created by ananda on 06-09-2016.
 */

public class ToolBarActivityStyleImpl extends DefaultActivityStyleImpl {
    private Toolbar mToolBar;

    public ToolBarActivityStyleImpl(Activity activity) {
        super(activity);
    }

    @Override
    protected int getBaseLayout() {
        return R.layout.base_tool_bar_activity;
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
