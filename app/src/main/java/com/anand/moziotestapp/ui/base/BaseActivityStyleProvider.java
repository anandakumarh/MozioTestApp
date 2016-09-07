package com.anand.moziotestapp.ui.base;

import android.app.Activity;
import android.support.annotation.NonNull;

public class BaseActivityStyleProvider {
    public static IActivityStyleImpl getActivityImpl(@NonNull Activity activity, @NonNull ActivityStyle type) {
        IActivityStyleImpl implementation = new DefaultActivityStyleImpl(activity);
        switch (type) {
            case DEFAULT:
                implementation = new DefaultActivityStyleImpl(activity);
                break;
            case TOOL_BAR:
                implementation = new ToolBarActivityStyleImpl(activity);
                break;
            case DRAWER:
                implementation = new DrawerActivityStyleImpl(activity);
                break;
            case DRAWER_WITH_TOOLBAR:
                implementation = new DrawerWithToolBarActivityStyleImpl(activity);
                break;
        }
        return implementation;
    }
}
