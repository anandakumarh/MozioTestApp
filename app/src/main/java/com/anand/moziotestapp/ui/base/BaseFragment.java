package com.anand.moziotestapp.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ananda on 06-09-2016.
 */
public abstract class BaseFragment extends Fragment {
    private BaseActivity mBaseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null && context instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) context;
        }
    }

    protected ActionBar getSupportActionBar() {
        ActionBar actionBar = null;
        if (mBaseActivity != null) {
            actionBar = mBaseActivity.getSupportActionBar();
        }
        return actionBar;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        @LayoutRes int contentLayout = getContentLayout();
        if (contentLayout != 0) {
            rootView = inflater.inflate(contentLayout, null, false);
        } else {
            rootView = super.onCreateView(inflater, container, savedInstanceState);
        }
        if (rootView != null) {
            initViews(rootView);
        }

        return rootView;
    }

    protected abstract void initViews(View rootView);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = false;
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!onBackPressed()) {
                    //First handle home button click in fragment using onBackPressed().
                    //If it is not consumed. Dispatch to activity.
                    dispatchOnBackPressedToActivity();
                }
                handled = true;
                break;
        }
        return handled;
    }

    private void dispatchOnBackPressedToActivity() {
        if (mBaseActivity != null) {
            mBaseActivity.onBackPressed();
        }
    }

    public
    abstract
    @LayoutRes
    int getContentLayout();

    /**
     * Override this method. if you want to do any other work other than normal back press process.
     *
     * @return true if event is consumed. otherwise false
     */
    public abstract boolean onBackPressed();
}
