package com.anand.moziotestapp.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {
    private IActivityStyleImpl mActivityStyleImplementation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityStyleImplementation = getActivityStyleImpl();
        mActivityStyleImplementation.setBaseContentView();
        if (mActivityStyleImplementation.getRootView() != null) {
            super.setContentView(mActivityStyleImplementation.getRootView());
        }
        initToolBar();
        initDrawer();
    }

    private void initDrawer() {
        if (mActivityStyleImplementation == null) return;
        if (mActivityStyleImplementation instanceof DrawerActivityStyleImpl) {
            //TODO: Drawer config if any.
        }
    }


    private void initToolBar() {
        if (mActivityStyleImplementation == null) return;
        Toolbar toolbar = null;
        if (mActivityStyleImplementation instanceof ToolBarActivityStyleImpl) {
            toolbar = ((ToolBarActivityStyleImpl) mActivityStyleImplementation).getToolBar();
        } else if (mActivityStyleImplementation instanceof DrawerWithToolBarActivityStyleImpl) {
            toolbar = ((DrawerWithToolBarActivityStyleImpl) mActivityStyleImplementation).getToolBar();
        }
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    public abstract IActivityStyleImpl getActivityStyleImpl();

    @Override
    public void setContentView(View view) {
        if (view == null) return;
        if (mActivityStyleImplementation == null) return;
        if (mActivityStyleImplementation.getRootView() == null) {
            mActivityStyleImplementation.addContentView(view);
            if (mActivityStyleImplementation.getRootView() != null) {
                super.setContentView(mActivityStyleImplementation.getRootView());
            }
        } else {
            mActivityStyleImplementation.addContentView(view);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (layoutResID == 0) return;
        if (mActivityStyleImplementation == null) return;
        if (mActivityStyleImplementation.getRootView() == null) {
            mActivityStyleImplementation.addContentView(layoutResID);
            if (mActivityStyleImplementation.getRootView() != null) {
                super.setContentView(mActivityStyleImplementation.getRootView());
            }
        } else {
            mActivityStyleImplementation.addContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (view == null) return;
        if (mActivityStyleImplementation == null) return;
        if (mActivityStyleImplementation.getRootView() == null) {
            mActivityStyleImplementation.addContentView(view, params);
            if (mActivityStyleImplementation.getRootView() != null) {
                super.setContentView(mActivityStyleImplementation.getRootView());
            }
        } else {
            mActivityStyleImplementation.addContentView(view, params);
        }
    }

    @Override
    public void loadDialogFragment(DialogFragment fragment, @Nullable String tag) {
        fragment.show(getSupportFragmentManager(), tag);
    }

    @Override
    public void loadFragment(int fragmentContainerId, BaseFragment fragment, @Nullable String tag,
                             int enterAnimId, int exitAnimId,
                             IFragmentTransactionType fragmentTransactionType) {

        performFragmentTranscation(fragmentContainerId, fragment, tag,
                (enterAnimId == 0) ? 0 : enterAnimId,
                (exitAnimId == 0) ? 0 : exitAnimId,
                fragmentTransactionType);
    }

    private void performFragmentTranscation(int fragmentContainerId,
                                            Fragment fragment, String tag,
                                            int enterAnimId, int exitAnimId,
                                            IFragmentTransactionType fragmentTransactionType) {
        switch (fragmentTransactionType) {
            case ADD:
                addFragment(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            case REPLACE:
                replaceFragment(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            case ADD_TO_BACK_STACK_AND_REPLACE:
                addToBackStackAndReplace(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            case POP_BACK_STACK_AND_REPLACE:
                popBackStackAndReplace(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            case CLEAR_BACK_STACK_AND_REPLACE:
                clearBackStackAndReplace(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            default:
                replaceFragment(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
        }

    }


    protected void addFragment(int fragmentContainerId, Fragment fragment, String tag, int enterAnimId, int exitAnimId) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .add(fragmentContainerId, fragment, tag)
                .commit();
    }

    private void replaceFragment(int fragmentContainerId, Fragment fragment, @Nullable String tag,
                                 int enterAnimId, int exitAnimId) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit();
    }

    private void popBackStackAndReplace(int fragmentContainerId, Fragment fragment,
                                        @Nullable String tag, int enterAnimId, int exitAnimId) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit();
    }

    private void addToBackStackAndReplace(int fragmentContainerId, Fragment fragment,
                                          @Nullable String tag, int enterAnimId, int exitAnimId) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(enterAnimId, 0, 0, exitAnimId)
                .replace(fragmentContainerId, fragment, tag)
                .commit();
    }

    private void clearBackStackAndReplace(int fragmentContainerId, Fragment fragment,
                                          @Nullable String tag, int enterAnimId, int exitAnimId) {
        clearBackStack(FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit();
    }

    private void clearBackStack(int flag) {
        FragmentManager fm = getSupportFragmentManager();

        if (fm.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = fm.getBackStackEntryAt(0);
            fm.popBackStack(first.getId(), flag);
            fm.executePendingTransactions();
        }
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
