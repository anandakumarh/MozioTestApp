package com.anand.moziotestapp.db;

public class UpdateQuery<T> extends DBQuery {

	private T mValues;
	private String mWhereClause;
	private String[] mWhereArgs;

	public T getValues() {
		return mValues;
	}

	public void setValues(T values) {
		this.mValues = values;
	}

	public String getWhereClause() {
		return mWhereClause;
	}

	public void setWhereClause(String mWhereClause) {
		this.mWhereClause = mWhereClause;
	}

	public String[] getWhereArgs() {
		return mWhereArgs;
	}

	public void setWhereArgs(String[] mWhereArgs) {
		this.mWhereArgs = mWhereArgs;
	}
}
