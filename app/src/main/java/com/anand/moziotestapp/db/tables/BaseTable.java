package com.anand.moziotestapp.db.tables;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.anand.moziotestapp.db.DeleteQuery;
import com.anand.moziotestapp.db.IDataBaseOperation;
import com.anand.moziotestapp.db.SelectQuery;

public abstract class BaseTable implements IDataBaseOperation {

    public void create(SQLiteDatabase db, String sqlStatement) throws SQLException {
        db.execSQL(sqlStatement);
    }

    ;


    protected void drop(SQLiteDatabase db, String tableName) throws SQLException {
        String deletionStatement = "DROP TABLE IF EXISTS " + tableName + ";";
        db.execSQL(deletionStatement);
    }

    ;


    protected Cursor select(String tableName, SQLiteDatabase db, SelectQuery query) {
        Cursor cursor = null;

        if (db != null && query != null) {
            cursor = db.query(tableName, query.getColumns(), query.getWhereClause(),
                    query.getWhereArgs(), query.getGroupBy(), query.getHaving(),
                    query.getOrderBy(), query.getLimit());
        }
        return cursor;
    }


    protected int delete(String tableName, SQLiteDatabase db, DeleteQuery query) {

        int result = 0;

        if (db != null && query != null) {
            result = db.delete(tableName, query.getWhereClause(), query.getWhereArgs());
            //Log.d("TEST" , "tableName = " + tableName + " deleted result = " + result);
//			db.close();
        }
        return result;
    }

}
