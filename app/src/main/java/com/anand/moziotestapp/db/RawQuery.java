package com.anand.moziotestapp.db;

public class RawQuery extends DBQuery {

    private byte rawOperationId = 0;

    private String mSqlStatement = null;

    private String[] mSelctionArgs = null;

    public String getmSqlStatement() {
        return mSqlStatement;
    }

    public void setmSqlStatement(String mSqlStatement) {
        this.mSqlStatement = mSqlStatement;
    }

    public String[] getmSelctionArgs() {
        return mSelctionArgs;
    }

    public void setmSelctionArgs(String[] mSelctionArgs) {
        this.mSelctionArgs = mSelctionArgs;
    }

    public byte getRawOperationId() {
        return rawOperationId;
    }

    public void setRawOperationId(byte rawOperationId) {
        this.rawOperationId = rawOperationId;
    }
}
