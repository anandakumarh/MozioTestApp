package com.anand.moziotestapp.db;

import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;

public interface IDataBaseOperation {

	public interface DBOperationsID {
		byte INSERT = 101;
		byte UPDATE = 102;
		byte DELETE = 103;
		byte SELECT = 104;
		byte RAW = 105;
	}

	void create(SQLiteDatabase db);

	void drop(SQLiteDatabase db);

	long insert(SQLiteDatabase db, DBQuery query)
			throws ClassCastException, ParseException;

	<T> T select(SQLiteDatabase db, DBQuery query)
			throws ClassCastException;

	int update(SQLiteDatabase db, DBQuery query)
			throws ClassCastException, ParseException;

	int delete(SQLiteDatabase db, DBQuery query)
			throws ClassCastException;

	Object raw(SQLiteDatabase db, DBQuery query);
	// void create(SQLiteDatabase db, String sqlStatement);
}
