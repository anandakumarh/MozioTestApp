package com.anand.moziotestapp.ui.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anand on 9/7/2016.
 */
public class Patient implements Parcelable {
    @SerializedName("patient_id")
    private String mId;
    @SerializedName("patient_name")
    private String mName;
    @SerializedName("patient_age")
    private int mAge;
    @SerializedName("patient_gender")
    private String mGender;
    @SerializedName("has_migraines")
    private boolean mHasMigraines;
    @SerializedName("consumed_drugs")
    private boolean mConsumedDrug;

    protected Patient(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mAge = in.readInt();
        mGender = in.readString();
        mHasMigraines = in.readByte() != 0;
        mConsumedDrug = in.readByte() != 0;
    }

    public Patient() {

    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    public String getPatientName() {
        return mName;
    }

    public void setPatientName(String patientName) {
        this.mName = patientName;
    }

    public int getPatientAge() {
        return mAge;
    }

    public void setPatientAge(int patientAge) {
        this.mAge = patientAge;
    }

    public String getPatientGender() {
        return mGender;
    }

    public void setPatientGender(String patientGender) {
        this.mGender = patientGender;
    }

    public boolean isHasMigraines() {
        return mHasMigraines;
    }

    public void setHasMigraines(boolean hasMigraines) {
        this.mHasMigraines = hasMigraines;
    }

    public boolean isConsumedDrug() {
        return mConsumedDrug;
    }

    public void setConsumedDrug(boolean consumedDrug) {
        this.mConsumedDrug = consumedDrug;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mName);
        parcel.writeInt(mAge);
        parcel.writeString(mGender);
        parcel.writeByte((byte) (mHasMigraines ? 1 : 0));
        parcel.writeByte((byte) (mConsumedDrug ? 1 : 0));
    }
}
