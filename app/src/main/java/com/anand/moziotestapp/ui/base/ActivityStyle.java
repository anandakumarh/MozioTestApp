package com.anand.moziotestapp.ui.base;

/**
 * Enum to decide Activity UI
 */
public enum ActivityStyle {
    /**
     * By default BaseActivity does not provide toolbar or drawer.
     */
    DEFAULT,
    /**
     * BaseActivity provides toolbar.
     */
    TOOL_BAR,
    /**
     * BaseActivity provides Drawer.
     */
    DRAWER,
    /**
     * BaseActivity provides drawer with toolbar.
     */
    DRAWER_WITH_TOOLBAR;
}
