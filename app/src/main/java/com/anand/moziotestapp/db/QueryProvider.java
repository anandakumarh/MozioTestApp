package com.anand.moziotestapp.db;

import android.text.TextUtils;

import com.anand.moziotestapp.db.tables.DataBaseTableConstants;
import com.anand.moziotestapp.db.tables.PatientTable;
import com.anand.moziotestapp.ui.dto.Patient;

import java.util.List;

public class QueryProvider implements IDataBaseOperation.DBOperationsID, DataBaseTableConstants.TableID {

    public static DeleteQuery getDeleteAllQuery(IDataBaseResultNotifier dbResultNotifier, byte tableId) {
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setDataBaseResultNotifier(dbResultNotifier);
        deleteQuery.setOperationId(DELETE);
        deleteQuery.setTableId(tableId);

        return deleteQuery;
    }


    public static InsertQuery<Patient> getInsertComplaintQuery(Patient patient) {
        InsertQuery<Patient> insertQuery = new InsertQuery<Patient>();
        insertQuery.setDataBaseResultNotifier(null);
        insertQuery.setOperationId(INSERT);
        insertQuery.setTableId(ID_PATIENT_TABLE);
        insertQuery.setValues(patient);

        return insertQuery;
    }

    public static UpdateQuery<Patient> getUpdateComplaintQuery(IDataBaseResultNotifier dbResultNotifier,
                                                               Patient patient) {
        String whereClause = PatientTable.COLUMN_KEY_PATIENT_NUMBER + " = ?";
        String[] whereArgs = {patient.getId()};

        UpdateQuery<Patient> updateQuery = new UpdateQuery<>();
        updateQuery.setDataBaseResultNotifier(dbResultNotifier);
        updateQuery.setOperationId(UPDATE);
        updateQuery.setTableId(ID_PATIENT_TABLE);
        updateQuery.setWhereClause(whereClause);
        updateQuery.setWhereArgs(whereArgs);
        updateQuery.setValues(patient);

        return updateQuery;
    }

    public static InsertListQuery<Patient> getInsertAllpatientQuery(IDataBaseResultNotifier notifier, List<Patient> patientList) {
        InsertListQuery<Patient> insertQuery = new InsertListQuery<Patient>();
        insertQuery.setDataBaseResultNotifier(notifier);
        insertQuery.setOperationId(INSERT);
        insertQuery.setTableId(ID_PATIENT_TABLE);
        insertQuery.setValues(patientList);
        return insertQuery;
    }

    public static SelectQuery getAllPatientsQuery(IDataBaseResultNotifier dbResultNotifier) {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.setDataBaseResultNotifier(dbResultNotifier);
        selectQuery.setOperationId(SELECT);
        selectQuery.setTableId(ID_PATIENT_TABLE);

        return selectQuery;
    }

    public static SelectQuery getSelectpatientQuery(IDataBaseResultNotifier dbResultNotifier, String complaintNo) {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.setDataBaseResultNotifier(dbResultNotifier);
        selectQuery.setOperationId(SELECT);
        selectQuery.setTableId(ID_PATIENT_TABLE);
        if (!TextUtils.isEmpty(complaintNo)) {
            String whereClause = PatientTable.COLUMN_KEY_PATIENT_NUMBER + " = ?";
            String[] whereArgs = {complaintNo};

            selectQuery.setWhereClause(whereClause);
            selectQuery.setWhereArgs(whereArgs);
        }
        return selectQuery;
    }

}
