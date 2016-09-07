package com.anand.moziotestapp.db;

public class SelectQuery extends DBQuery {

	private String[] mColumns = null;
	private String mWhereClause = null;
	private String[] mWhereArgs = null;
	private String mGroupBy = null;
	private String mHaving = null;
	private String mOrderBy = null;
	private String mLimit = null;

	public String[] getColumns() {
		return mColumns;
	}

	public void setColumns(String[] columns) {
		this.mColumns = columns;
	}

	public String getWhereClause() {
		return mWhereClause;
	}

	public void setWhereClause(String whereClause) {
		this.mWhereClause = whereClause;
	}

	public String[] getWhereArgs() {
		return mWhereArgs;
	}

	public void setWhereArgs(String[] whereArgs) {
		this.mWhereArgs = whereArgs;
	}

	public String getGroupBy() {
		return mGroupBy;
	}

	public void setGroupBy(String groupBy) {
		this.mGroupBy = groupBy;
	}

	public String getHaving() {
		return mHaving;
	}

	public void setHaving(String having) {
		this.mHaving = having;
	}

	public String getOrderBy() {
		return mOrderBy;
	}

	public void setOrderBy(String orderBy) {
		this.mOrderBy = orderBy;
	}

	public String getLimit() {
		return mLimit;
	}

	public void setLimit(String limit) {
		this.mLimit = limit;
	}
}