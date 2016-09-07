package com.anand.moziotestapp.db;

public class DBQuery {

	private IDataBaseResultNotifier mDataBaseResultNotifier;
	private byte mOperationId;
	private byte mTableId;

	public IDataBaseResultNotifier getDataBaseResultNotifier() {
		return mDataBaseResultNotifier;
	}

	public void setDataBaseResultNotifier(
			IDataBaseResultNotifier mDataBaseResultNotifier) {
		this.mDataBaseResultNotifier = mDataBaseResultNotifier;
	}

	public byte getOperationId() {
		return mOperationId;
	}

	public void setOperationId(byte mOperationId) {
		this.mOperationId = mOperationId;
	}

	public byte getTableId() {
		return mTableId;
	}

	public void setTableId(byte mTableId) {
		this.mTableId = mTableId;
	}
}
