package com.anand.moziotestapp.utils;

/**
 * Created by ananda on 7/9/16.
 */
public class MedicalUtil {

    /**
     * Function which returns the syndrome probability.
     * @param age
     * @param gender
     * @param hasMigraines
     * @param consumedDrug
     * @return syndrome probability
     */
    public static int getDiseasePercentage(int age, String gender, boolean hasMigraines, boolean consumedDrug) {
        int percentage = 0;
        if (age <= 15) {
            percentage = 25;
        }
        if (gender.equals(Constants.Gender.MALE)) {
            percentage += 25;
        }
        if (hasMigraines) {
            percentage += 25;
        }
        if (consumedDrug) {
            percentage += 25;
        }
        return percentage;
    }
}
