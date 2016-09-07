package com.anand.moziotestapp.ui.home;

/**
 * Created by ananda on 7/9/16.
 */
public interface HomeFragmentInteractionListener {

    void loadSyndromeResultScreen(String patientName, int syndromePercentage);
    void loadPatientDetailScreen();
}
