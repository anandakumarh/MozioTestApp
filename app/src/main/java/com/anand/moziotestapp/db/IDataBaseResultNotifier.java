package com.anand.moziotestapp.db;

public interface IDataBaseResultNotifier {

	public <T> void OnDataBaseDataUpdated(T data);
}
