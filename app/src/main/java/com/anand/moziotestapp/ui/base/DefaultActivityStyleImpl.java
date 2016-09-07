package com.anand.moziotestapp.ui.base;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This implementation provides simple default activity style.
 * This does not contain toolBar or drawer.
 */
public class DefaultActivityStyleImpl implements IActivityStyleImpl {
    protected Activity activity;
    protected View rootView;
    protected View contentView;
    protected ViewGroup contentViewContainer;

    /**
     * @hide
     */
    @IntDef({Gravity.LEFT, Gravity.RIGHT, GravityCompat.START, GravityCompat.END})
    @Retention(RetentionPolicy.SOURCE)
    protected @interface OxigneEdgeGravity {
    }


    private DefaultActivityStyleImpl() {
    }

    protected DefaultActivityStyleImpl(@NonNull Activity activity) {
        this.activity = activity;
    }

    protected Activity getActivity() {
        return activity;
    }


    protected
    @LayoutRes
    int getBaseLayout() {
        return 0;
    }

    @Override
    public void addContentView(View view) {
        contentView = view;
        if (rootView == null) {
            rootView = view;
        } else if (contentView != null && contentViewContainer != null) {
            contentViewContainer.addView(contentView);
        }
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        contentView = view;
        if (rootView == null) {
            rootView = contentView;
        } else if (contentViewContainer != null && contentView != null) {
            contentViewContainer.addView(contentView, params);
        }
    }


    @Override
    public void setBaseContentView() {
        int baseLayout = getBaseLayout();
        if (baseLayout != 0) {
            rootView = View.inflate(activity, baseLayout, null);
            contentViewContainer = (ViewGroup) rootView.findViewById(R.id.content_layout);
        }
        initViews();
    }

    @Override
    public void addContentView(@LayoutRes int layout) {
        if (layout != 0) {
            contentView = View.inflate(activity, layout, null);
        }
        if (rootView == null) {
            rootView = contentView;
        } else if (contentView != null && contentViewContainer != null) {
            contentViewContainer.addView(contentView);
        }

    }

    @Override
    public View getRootView() {
        return rootView;
    }

    public View getContentView() {
        return contentView;
    }

    @Override
    public void initViews() {
        //Nothig to init here... Because it is default style.
    }
}
