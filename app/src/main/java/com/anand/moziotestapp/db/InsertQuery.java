package com.anand.moziotestapp.db;

public class InsertQuery<T> extends DBQuery {

	private T mValues;

	public T getValues() {
		return mValues;
	}

	public void setValues(T values) {
		this.mValues = values;
	}

}
