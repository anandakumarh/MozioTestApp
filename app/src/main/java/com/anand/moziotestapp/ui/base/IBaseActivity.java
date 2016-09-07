package com.anand.moziotestapp.ui.base;

import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

/**
 * Created by ananda on 06-09-2016.
 */
public interface IBaseActivity {

    void loadDialogFragment(DialogFragment fragment, @Nullable String tag);

    void loadFragment(int fragmentContainerId, BaseFragment fragment, @Nullable String tag,
                      int enterAnimId, int exitAnimId,
                      IFragmentTransactionType fragmentTransactionType);

    void hideKeyboard();
}
