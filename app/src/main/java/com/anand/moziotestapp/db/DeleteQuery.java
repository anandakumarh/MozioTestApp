package com.anand.moziotestapp.db;

public class DeleteQuery extends DBQuery {

	private String mWhereClause;
	private String[] mWhereArgs;

	/**
	 * @return the mWhereClause
	 */
	public String getWhereClause() {
		return mWhereClause;
	}

	/**
	 * @param mWhereClause the mWhereClause to set
	 */
	public void setWhereClause(String mWhereClause) {
		this.mWhereClause = mWhereClause;
	}

	/**
	 * @return the mWhereArgs
	 */
	public String[] getWhereArgs() {
		return mWhereArgs;
	}

	/**
	 * @param mWhereArgs the mWhereArgs to set
	 */
	public void setWhereArgs(String[] mWhereArgs) {
		this.mWhereArgs = mWhereArgs;
	}
}

