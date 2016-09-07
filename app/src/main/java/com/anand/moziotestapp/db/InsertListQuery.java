package com.anand.moziotestapp.db;

import java.util.List;

public class InsertListQuery<T> extends DBQuery {
    private List<T> mValues;

    public List<T> getValues() {
        return mValues;
    }

    public void setValues(List<T> values) {
        this.mValues = values;
    }

}
