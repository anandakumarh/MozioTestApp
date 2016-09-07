package com.anand.moziotestapp.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.anand.moziotestapp.db.DBQuery;
import com.anand.moziotestapp.db.DeleteQuery;
import com.anand.moziotestapp.db.InsertListQuery;
import com.anand.moziotestapp.db.InsertQuery;
import com.anand.moziotestapp.db.SelectQuery;
import com.anand.moziotestapp.db.UpdateQuery;
import com.anand.moziotestapp.dto.Patient;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PatientTable extends BaseTable {
    private static final String TAG = PatientTable.class.getSimpleName();

    public static final String COLUMN_KEY_PATIENT_NUMBER = "patientNumber";
    public static final String COLUMN_KEY_PATIENT_NAME = "patientName";
    public static final String COLUMN_KEY_AGE = "age";
    public static final String COLUMN_KEY_GENDER = "gender";
    public static final String COLUMN_KEY_MIGRAINES = "migrains";
    public static final String COLUMN_KEY_DRUG = "drug";


    public static final String TABLE_PATIENT = "Complaint";
    private static final String CREATE_TABLE_COMPLAINT = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PATIENT
            + "("
            + COLUMN_KEY_PATIENT_NUMBER
            + " TEXT PRIMARY KEY , "
            + COLUMN_KEY_PATIENT_NAME
            + " TEXT , "
            + COLUMN_KEY_AGE
            + " TEXT , "
            + COLUMN_KEY_GENDER
            + " TEXT , "
            + COLUMN_KEY_MIGRAINES
            + " TEXT , "
            + COLUMN_KEY_DRUG
            + " TEXT ) ";

    @Override
    public void create(SQLiteDatabase db) {
        super.create(db, CREATE_TABLE_COMPLAINT);
        Log.d(TAG, CREATE_TABLE_COMPLAINT);
    }

    @Override
    public void drop(SQLiteDatabase db) {
        super.drop(db, TABLE_PATIENT);
    }

    @Override
    public long insert(SQLiteDatabase db, DBQuery query) throws ClassCastException, ParseException {
        if (query instanceof InsertListQuery) {
            return insertListIntoTable(db, (InsertListQuery<Patient>) query);
        } else if (query instanceof InsertQuery) {
            return insertIntoTable(db, (InsertQuery<Patient>) query);
        } else {
            return -1;
        }
    }

    /**
     * Insert a single Patient in to table
     *
     * @param db
     * @param query
     * @return returns no. of row inserted.
     */
    private long insertIntoTable(SQLiteDatabase db, InsertQuery<Patient> query) {
        long res = 0;
        if (db != null) {
            Patient complaint = query.getValues();
            res = db.insert(TABLE_PATIENT, null, getContentValues(complaint));
            Log.d(TAG, "One row inserted into Complaints table");
        }
        return (res > 0) ? 1 : res;
    }

    /**
     * Insert a list of Patients into table.
     *
     * @param db
     * @param query
     * @return no of rows iserted
     */
    private long insertListIntoTable(SQLiteDatabase db, InsertListQuery<Patient> query) {
        long res = 0;
        if (db != null) {
            List<Patient> patients = query.getValues();
            for (Patient patient : patients) {
                long rowId = db.insert(TABLE_PATIENT, null, getContentValues(patient));
                if (rowId > -1) {
                    res++;
                }
            }
        }
        Log.d(TAG, "Inserted [ " + res + " ] rows into complaint table.");
        return res;
    }

    @Override
    public <T> T select(SQLiteDatabase db, DBQuery query) throws ClassCastException {
        Cursor cursor = null;
        SelectQuery selectQuery = (SelectQuery) query;

        if (db != null) {
            cursor = super.select(TABLE_PATIENT, db, selectQuery);

            if (cursor != null) {
                @SuppressWarnings("unchecked")
                T vlaue = (T) getSuggestionList(cursor);
                cursor.close();
                return vlaue;
            }
        }
        return null;
    }

    @Override
    public int update(SQLiteDatabase db, DBQuery query) throws ClassCastException, ParseException {
        UpdateQuery<Patient> updateQuery = (UpdateQuery<Patient>) query;

        if (db != null) {
            Patient complaint = updateQuery.getValues();
            ContentValues values = getContentValues(complaint);
            if (values.containsKey(COLUMN_KEY_PATIENT_NUMBER)) {
                values.remove(COLUMN_KEY_PATIENT_NUMBER);
            }

            return db.update(TABLE_PATIENT,
                    getContentValues(complaint),
                    updateQuery.getWhereClause(), updateQuery.getWhereArgs());
        }
        return 0;
    }

    @Override
    public int delete(SQLiteDatabase db, DBQuery query) throws ClassCastException {
        DeleteQuery deleteQuery = (DeleteQuery) query;

        return db.delete(TABLE_PATIENT, deleteQuery.getWhereClause(), deleteQuery.getWhereArgs());
    }

    @Override
    public Object raw(SQLiteDatabase db, DBQuery query) {
        return -1;
    }

    private ContentValues getContentValues(Patient complaint) {
        ContentValues contentValues = new ContentValues();
        if (complaint != null) {
            contentValues.put(COLUMN_KEY_PATIENT_NUMBER, complaint.getId());
            contentValues.put(COLUMN_KEY_PATIENT_NAME, complaint.getPatientName());
            contentValues.put(COLUMN_KEY_AGE, complaint.getPatientAge());
            contentValues.put(COLUMN_KEY_GENDER, complaint.getPatientGender());
            contentValues.put(COLUMN_KEY_MIGRAINES, complaint.isHasMigraines());
            contentValues.put(COLUMN_KEY_DRUG, complaint.isConsumedDrug());
        }

        return contentValues;
    }

    private List<Patient> getSuggestionList(Cursor cursor) {

        ArrayList<Patient> serachSuggestions = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Patient patient = new Patient();
                patient.setId(cursor.getString(cursor.getColumnIndex(COLUMN_KEY_PATIENT_NUMBER)));
                patient.setPatientName(cursor.getString(cursor.getColumnIndex(COLUMN_KEY_PATIENT_NAME)));
                patient.setPatientAge(cursor.getInt(cursor.getColumnIndex(COLUMN_KEY_AGE)));
                patient.setPatientGender(cursor.getString(cursor.getColumnIndex(COLUMN_KEY_GENDER)));
                patient.setHasMigraines(cursor.getInt(cursor.getColumnIndex(COLUMN_KEY_MIGRAINES)) == 1 ? true : false);
                patient.setConsumedDrug(cursor.getInt(cursor.getColumnIndex(COLUMN_KEY_DRUG)) == 1 ? true : false);

                serachSuggestions.add(patient);
            }
        }

        return serachSuggestions;
    }
}
