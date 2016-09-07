package com.anand.moziotestapp.ui.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anand.moziotestapp.R;
import com.anand.moziotestapp.db.IDataBaseResultNotifier;
import com.anand.moziotestapp.db.QueryExecutor;
import com.anand.moziotestapp.db.QueryProvider;
import com.anand.moziotestapp.db.SelectQuery;
import com.anand.moziotestapp.dto.Patient;
import com.anand.moziotestapp.ui.base.BaseFragment;
import com.anand.moziotestapp.utils.InputEmptyErrorRemover;
import com.anand.moziotestapp.utils.MedicalUtil;

import java.util.List;


public class HomeFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = HomeFragment.class.getName();

    private EditText mPatientNo;
    private Button mSubmit;
    private FloatingActionButton mAddNewPatient;
    private CardView mPatientDetailHolder;
    private HomeFragmentInteractionListener mFragmentInteraction;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        resetActionBar();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            resetActionBar();
        }
    }

    private void resetActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setTitle(getString(R.string.home));
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setListeners();
    }

    private void setListeners() {
        mSubmit.setOnClickListener(this);
        mAddNewPatient.setOnClickListener(this);
    }

    @Override
    public void initViews(View view) {
        mPatientNo = (EditText) view.findViewById(R.id.pat_no);
        mPatientNo.addTextChangedListener(new
                InputEmptyErrorRemover(((TextInputLayout) mPatientNo.getParent())));
        mSubmit = (Button) view.findViewById(R.id.submit);
        mAddNewPatient = (FloatingActionButton) view.findViewById(R.id.add_patient);
        mPatientDetailHolder = (CardView) view.findViewById(R.id.detail_holder);
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                onSubmitButtonClick();
                break;
            case R.id.add_patient:
                if (mFragmentInteraction != null) {
                    mFragmentInteraction.loadPatientDetailScreen();
                }
                break;
        }

    }

    private void onSubmitButtonClick() {
        if (validateInputs()) {
            final SelectQuery patientInsertQuery = QueryProvider.
                    getSelectpatientQuery(new IDataBaseResultNotifier() {
                        @Override
                        public <T> void OnDataBaseDataUpdated(T data) {
                            if (getActivity() == null || getView() == null) return;
                            List<Patient> patientList = (List<Patient>) data;
                            if (patientList != null && patientList.size() > 0)
                                setDataToUI(patientList.get(0));
                        }
                    }, mPatientNo.getText().toString());
            QueryExecutor selectQueryExecutor = new QueryExecutor(
                    getActivity().getApplicationContext(), patientInsertQuery);
            selectQueryExecutor.execute();
        }
    }

    private void setDataToUI(Patient data) {
        if (data != null) {
            getView().findViewById(R.id.detail_holder).setVisibility(View.VISIBLE);

            TextView patientNo = (TextView) getView().findViewById(R.id.patient_no);
            patientNo.setText(data.getId());

            TextView name = (TextView) getView().findViewById(R.id.name);
            name.setText(data.getPatientName());

            TextView age = (TextView) getView().findViewById(R.id.age);
            age.setText("" + data.getPatientAge());

            TextView gender = (TextView) getView().findViewById(R.id.gender);
            gender.setText(data.getPatientGender());

            String previousTestResult = getPreviousTestResult(MedicalUtil.getDiseasePercentage(data.getPatientAge(),
                    data.getPatientGender(), data.isHasMigraines(), data.isConsumedDrug()), data.getPatientName());
            TextView previousResult = (TextView) getView().findViewById(R.id.result);
            previousResult.setText(previousTestResult);
        }
    }

    private String getPreviousTestResult(int percentage, String patientName) {
        switch (percentage) {
            case 0:
                return getSyndromeNotFoundMessage(patientName);
            case 100:
            case 75:
            case 50:
            case 25:
                return getSyndromeProbabilityMessage(patientName, percentage);
            default:
                return "";
        }
    }

    private String getSyndromeNotFoundMessage(String patientName) {
        return patientName + " does not seem to have any Todd's Syndrome";
    }

    private String getSyndromeProbabilityMessage(String patientName, int percentage) {
        return patientName + " has " + percentage + "% probability of Todd's Syndrome";
    }

    private boolean validateInputs() {
        boolean validUserId;

        validUserId = validatePatientNo();
        if (!validUserId && mPatientNo != null) {
            mPatientNo.requestFocus();
        }
        return validUserId;
    }

    private boolean validatePatientNo() {
        if (mPatientNo == null) return false;
        boolean valid = !TextUtils.isEmpty(mPatientNo.getText().toString());
        if (!valid) {
            if ((mPatientNo.getParent()) != null && mPatientNo.getParent() instanceof TextInputLayout) {
                ((TextInputLayout) mPatientNo.getParent())
                        .setError(getString(R.string.error_enter_patient_no));
            }
        }
        return valid;
    }
}
