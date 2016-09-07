package com.anand.moziotestapp.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.anand.moziotestapp.db.tables.BaseTable;
import com.anand.moziotestapp.db.tables.DataBaseTableConstants;
import com.anand.moziotestapp.db.tables.PatientTable;

import java.text.ParseException;

public class SQLiteHelper extends SQLiteOpenHelper implements IDataBaseOperation.DBOperationsID,
        DataBaseTableConstants.TableID {
    private static final String DATABASE_NAME = "mozioapp";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = SQLiteHelper.class.getName();

    private static SQLiteHelper mSqliteHelperInstance = null;

    private static final byte DB_STATE_READABLE = 111;
    private static final byte DB_STATE_WRITABLE = 112;

    public static SQLiteHelper getHelperInstance(Context context) {
        if (mSqliteHelperInstance == null) {
            mSqliteHelperInstance = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        return mSqliteHelperInstance;
    }

    private SQLiteHelper(Context context, String name,
                         CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Upgrading DataBase :" + DATABASE_NAME
                + "\n OldVersion :" + oldVersion + "\n New Version :"
                + newVersion);
        switch (oldVersion) {
            case 1:
                dropTables(db);
                createTables(db);
        }
    }


    private synchronized SQLiteDatabase getDb(byte state) {
        if (state == DB_STATE_READABLE) {
            return getReadableDatabase();
        } else {
            return getWritableDatabase();
        }
    }

    public synchronized Object executeQuery(DBQuery query) {

        Object data = null;
        switch (query.getOperationId()) {
            case INSERT:
                data = insert(query.getTableId(), query);
                break;

            case SELECT:
                data = select(query.getTableId(), query);
                break;

            case UPDATE:
                data = update(query.getTableId(), query);
                break;

            case DELETE:
                data = delete(query.getTableId(), query);
                break;

            case RAW:
                data = raw(query.getTableId(), query);
                break;

            default:
                // Toast.makeText(mContext, "UNKNOWN DATABASE OPERATION.",
                // Toast.LENGTH_SHORT).show();
                break;
        }
        return data;
    }

    private BaseTable getTable(int tableId) {
        BaseTable table = null;
        switch (tableId) {
            case ID_PATIENT_TABLE:
                table = new PatientTable();
                break;
            default:
                break;
        }
        return table;
    }

    public synchronized long insert(int tableId, DBQuery insertQuery) {
        SQLiteDatabase db = getDb(DB_STATE_WRITABLE);
        BaseTable table = getTable(tableId);
        long data = 0;

        if (db != null && table != null) {

            try {
                data = table.insert(db, insertQuery);
            } catch (ClassCastException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            db.close();
        }

        return data;
    }

    public synchronized <T> T select(int tableId, DBQuery selectQuery) {
        SQLiteDatabase db = getDb(DB_STATE_WRITABLE);
        BaseTable table = getTable(tableId);
        T data = null;

        if (db != null && table != null) {
            data = table.select(db, (SelectQuery) selectQuery);
            db.close();
        }
        return data;
    }

    public synchronized int update(int tableId, DBQuery updateQuery) {
        SQLiteDatabase db = getDb(DB_STATE_WRITABLE);
        BaseTable table = getTable(tableId);
        int data = 0;

        if (db != null && table != null) {
            try {
                data = table.update(db, updateQuery);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
            db.close();
        }
        return data;
    }

    private void createTables(SQLiteDatabase db) {
        Log.d(TAG, "Create Tables");
        try {
            new PatientTable().create(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTables(SQLiteDatabase db) {
        Log.d(TAG, "Create Tables");
        try {
            new PatientTable().drop(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized int delete(int tableId, DBQuery deleteQuery) {
        SQLiteDatabase db = getDb(DB_STATE_WRITABLE);
        BaseTable table = getTable(tableId);
        int data = 0;

        if (db != null && table != null) {
            data = table.delete(db, deleteQuery);
            db.close();
        }

        return data;
    }


    public synchronized Object raw(int tableId, DBQuery rawQuery) {
        SQLiteDatabase db = getDb(DB_STATE_WRITABLE);
        BaseTable table = getTable(tableId);
        Object data = 0;

        if (db != null && table != null) {
            data = table.raw(db, rawQuery);
            db.close();
        }

        return data;
    }


    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }
}
