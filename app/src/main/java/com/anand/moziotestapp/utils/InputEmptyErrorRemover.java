package com.anand.moziotestapp.utils;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/**
 * Utility class to handle empty inputs.
 * It works with TextInputLayout. And error is set and removed to TextInputLayout.
 */
public class InputEmptyErrorRemover implements TextWatcher {
    private TextInputLayout textInputLayout;

    private InputEmptyErrorRemover() {
    }

    public InputEmptyErrorRemover(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (textInputLayout == null) return;
        if (!TextUtils.isEmpty(charSequence)) {
            textInputLayout.setError("");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}