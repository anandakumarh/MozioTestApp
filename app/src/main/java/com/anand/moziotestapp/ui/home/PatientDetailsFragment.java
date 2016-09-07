package com.anand.moziotestapp.ui.home;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.anand.moziotestapp.R;
import com.anand.moziotestapp.db.InsertQuery;
import com.anand.moziotestapp.db.QueryExecutor;
import com.anand.moziotestapp.db.QueryProvider;
import com.anand.moziotestapp.dto.Patient;
import com.anand.moziotestapp.ui.base.BaseFragment;
import com.anand.moziotestapp.utils.Constants;
import com.anand.moziotestapp.utils.InputEmptyErrorRemover;
import com.anand.moziotestapp.utils.MedicalUtil;
import com.anand.moziotestapp.utils.PreferenceUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientDetailsFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    public static final String TAG = PatientDetailsFragment.class.getName();

    private EditText mPatientName;
    private EditText mPatientAge;
    private AppCompatCheckBox mMigraineCheckBox;
    private AppCompatCheckBox mDrugCheckBox;

    private Button mSubmitButton;
    private String mGender;

    private HomeFragmentInteractionListener mFragmentInteraction;


    public static PatientDetailsFragment getInstance() {
        return new PatientDetailsFragment();
    }

    public PatientDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            resetActionBar();
        }
    }

    /**
     *
     */
    private void resetActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setTitle(getString(R.string.patient_detail));
        actionBar.invalidateOptionsMenu();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null && context instanceof HomeFragmentInteractionListener) {
            mFragmentInteraction = (HomeFragmentInteractionListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        resetActionBar();
        return view;
    }

    @Override
    protected void initViews(View rootView) {
        mPatientName = (EditText) rootView.findViewById(R.id.patient_name_tiet);
        mPatientName.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        mPatientName.addTextChangedListener(new
                InputEmptyErrorRemover(((TextInputLayout) mPatientName.getParent())));
        mPatientAge = (EditText) rootView.findViewById(R.id.patient_age_tiet);
        mPatientAge.addTextChangedListener(new
                InputEmptyErrorRemover(((TextInputLayout) mPatientAge.getParent())));
        mMigraineCheckBox = (AppCompatCheckBox) rootView.findViewById(R.id.migraines_cb);
        mDrugCheckBox = (AppCompatCheckBox) rootView.findViewById(R.id.drug_cb);
        mSubmitButton = (Button) rootView.findViewById(R.id.submit);

        ((RadioGroup) rootView.findViewById(R.id.gender_container)).
                setOnCheckedChangeListener(this);
        mSubmitButton.setOnClickListener(this);
        ((AppCompatRadioButton) rootView.findViewById(R.id.male_rb)).setChecked(true);
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_patient_details;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.male_rb:
                mGender = Constants.Gender.MALE;
                break;
            case R.id.female_rb:
                mGender = Constants.Gender.FEMALE;
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                onSubmitButtonClick();
                break;
            default:
                break;
        }
    }

    private void onSubmitButtonClick() {
        if (validateInputs()) {
            savePatientDetails();
            launchResultScreen();
        }
    }

    /**
     * Launch Result Screen with calculated percentage.
     */
    private void launchResultScreen() {
        if (mFragmentInteraction != null) {
            mFragmentInteraction.loadSyndromeResultScreen(mPatientName.getText().toString(),
                    MedicalUtil.getDiseasePercentage(getAge(), mGender,
                            mMigraineCheckBox.isChecked(), mDrugCheckBox.isChecked()));
            clearData();
        }
    }

    private void clearData() {
        mPatientName.setText("");
        mPatientAge.setText("");
        mMigraineCheckBox.setChecked(false);
        mDrugCheckBox.setChecked(false);
    }

    /**
     * Save Patient Details to Database and Update Patient Number in Preference.
     */
    private void savePatientDetails() {
        PreferenceUtils.updatePatientNumber(getActivity());
        final InsertQuery<Patient> patientInsertQuery = QueryProvider.
                getInsertPatientQuery(getPatientDetails());
        QueryExecutor insertQueryExecutor = new QueryExecutor(
                getActivity().getApplicationContext(), patientInsertQuery);
        insertQueryExecutor.execute();
    }

    /**
     * @return Patient Details
     */
    private Patient getPatientDetails() {
        Patient patient = new Patient();
        patient.setId("PN" + String.format("%05d",
                PreferenceUtils.getLastPatientNumber(getActivity()) + 1));
        patient.setPatientName(mPatientName.getText().toString());
        patient.setPatientAge(Integer.parseInt(mPatientAge.getText().toString()));
        patient.setPatientGender(mGender);
        patient.setHasMigraines(mMigraineCheckBox.isChecked());
        patient.setConsumedDrug(mDrugCheckBox.isChecked());
        return patient;
    }

    /**
     * @return
     */
    private boolean validateInputs() {
        boolean validUserId;
        boolean validPassword;

        validUserId = validatePatientName();
        validPassword = validatePatientAge();
        if (!validUserId && mPatientName != null) {
            mPatientName.requestFocus();
        } else if (!validPassword && mPatientAge != null) {
            mPatientAge.requestFocus();
        }
        return validUserId && validPassword;
    }

    /**
     * @return
     */
    private boolean validatePatientName() {
        if (mPatientName == null) return false;
        boolean valid = !TextUtils.isEmpty(mPatientName.getText().toString());
        if (!valid) {
            if ((mPatientName.getParent()) != null && mPatientName.getParent() instanceof TextInputLayout) {
                ((TextInputLayout) mPatientName.getParent())
                        .setError(getString(R.string.error_enter_patient_name));
            }
        }
        return valid;
    }

    /**
     * @return
     */
    private boolean validatePatientAge() {
        if (mPatientAge == null) return false;
        boolean valid = !TextUtils.isEmpty(mPatientAge.getText().toString().trim());
        if (!valid) {
            if (mPatientAge.getParent() != null &&
                    mPatientAge.getParent() instanceof TextInputLayout) {
                ((TextInputLayout) mPatientAge.getParent()).setError(getString(R.string.error_enter_age));
            }
        }
        return valid;
    }

    /**
     * Read age from EditText and convert it to integer. If EditText have empty value then it will
     * return 0.
     *
     * @return age
     */
    private int getAge() {
        if (TextUtils.isEmpty(mPatientAge.getText().toString())) {
            return 0;
        } else {
            return Integer.parseInt(mPatientAge.getText().toString().trim());
        }
    }

}
