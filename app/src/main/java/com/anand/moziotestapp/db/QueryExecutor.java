package com.anand.moziotestapp.db;

import android.content.Context;
import android.os.AsyncTask;

public class QueryExecutor extends AsyncTask<Void, Void, Void> {

	private Object mData;
	private Context mContext = null;
	private DBQuery mQuery = null;

	public QueryExecutor(Context context, DBQuery query) {
		mContext = context;
		mQuery = query;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Void doInBackground(Void... params) {
		SQLiteHelper sqliteHelper = SQLiteHelper.getHelperInstance(mContext);
		mData = sqliteHelper.executeQuery(mQuery);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (mQuery != null && mQuery.getDataBaseResultNotifier() != null) {
			mQuery.getDataBaseResultNotifier().OnDataBaseDataUpdated(mData);
		}
	}

	public Object executeSynchronously() {
		SQLiteHelper sqLiteHelper = SQLiteHelper.getHelperInstance(mContext);
		Object data = sqLiteHelper.executeQuery(mQuery);
		if (mQuery != null) {
			return data;
		}
		return null;
	}
}
