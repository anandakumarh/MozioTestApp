package com.anand.moziotestapp.ui.base;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

/**
 * Functions to implement activity Style like: ToolBar , drawer , drawer with toolbar.
 */
public interface IActivityStyleImpl {
    void setBaseContentView();
    void addContentView(@LayoutRes int layout);

    void addContentView(View view);

    void addContentView(View view, ViewGroup.LayoutParams params);

    View getRootView();

    View getContentView();

    void initViews();
}
