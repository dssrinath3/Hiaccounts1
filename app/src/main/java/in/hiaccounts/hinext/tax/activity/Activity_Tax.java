package in.hiaccounts.hinext.tax.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.tax.model.taxconfig.AddTax;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxClassId;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxDatum;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxGroupId;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxTypeDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/19/2017.
 */

public class Activity_Tax extends AppCompatActivity {

    public static String TAG = Activity_Tax.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edTaxCode)
    EditText edTaxCode;
    @BindView(R.id.edActCode)
    EditText edActCode;
    @BindView(R.id.edTaxName)
    EditText edTaxName;
    @BindView(R.id.spinnerTaxClass)
    Spinner spinnerTaxClass;
    @BindView(R.id.spinnerTaxGroup)
    Spinner spinnerTaxGroup;
    @BindView(R.id.spinnerTaxType)
    Spinner spinnerTaxType;
    @BindView(R.id.edTaxPercatnge)
    EditText edTaxPercatnge;
    @BindView(R.id.edTaxDescritpion)
    EditText edTaxDescritpion;
    @BindView(R.id.spinnerTaxStatus)
    Spinner spinnerTaxStatus;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private boolean isSaveable = false;
    private List<TaxClassId> taxClassList = new ArrayList<>();
    private List<TaxGroupId> taxGroupList = new ArrayList<>();
    private List<TaxTypeDatum> taxTypeList = new ArrayList<>();
    private TaxDatum taxDatum;
    private TaxClassId taxClass;
    private TaxGroupId taxGroup;
    private TaxTypeDatum taxType;
    private String taxStatus;
    private String callingFor, serverUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        mActivity = this;
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        Intent intent = getIntent();
        callingFor = intent.getStringExtra("callingFor");
        if (callingFor != null) {
            if (callingFor.equals(Constant.CALL_ADDTAX)) {
                toolbar.setTitle(Constant.CALL_ADDTAX);
            }
            if (callingFor.equals(Constant.CALL_EDITTAX)) {
                toolbar.setTitle(Constant.CALL_EDITTAX);

                taxDatum = (TaxDatum) intent.getSerializableExtra("taxDatum");

                if (taxDatum != null) {
                    if (taxDatum.getTaxCode() != null)
                        edTaxCode.setText(taxDatum.getTaxCode());
                    if (taxDatum.getTaxName() != null)
                        edTaxName.setText(taxDatum.getTaxName());
                    if (taxDatum.getTaxDescription() != null)
                        edTaxDescritpion.setText(taxDatum.getTaxDescription());
                    edTaxPercatnge.setText("" + taxDatum.getTaxPer());
                }
            }
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        callTaxGroupServer();

        UtilView.setupAdapter(mActivity, spinnerTaxStatus, getResources().getStringArray(R.array.status));
        spinnerTaxStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerTaxStatus.setSelection(position);
                taxStatus = (String) spinnerTaxStatus.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        edTaxName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString() != null && !s.toString().equals("")) {
                    isSaveable = true;
                    edTaxName.setError(null);
                } else {
                    isSaveable = false;
                    edTaxName.setError(getResources().getString(R.string.err_msg));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edTaxCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString() != null && !s.toString().equals("")) {
                    isSaveable = true;
                    edTaxCode.setError(null);
                } else {
                    isSaveable = false;
                    edTaxCode.setError(getResources().getString(R.string.err_msg));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edTaxPercatnge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString() != null && !s.toString().equals("")) {
                    try {
                        Double.parseDouble(s.toString());
                        isSaveable = true;
                        edTaxPercatnge.setError(null);
                    } catch (NumberFormatException ne) {
                        isSaveable = true;
                        edTaxPercatnge.setError(getResources().getString(R.string.error_numberformate));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private void callTaxClassServer() {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                String url = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_GETTAXCLASS;
                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask getTaxClassTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (result != null) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (taxClassList != null)
                                taxClassList.clear();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    TaxClassId taxClassId = new Gson().fromJson(jsonObject.toString(), TaxClassId.class);
                                    taxClassList.add(taxClassId);
                                }
                                setupSpinnerValue();
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }


                }, false);
                getTaxClassTask.execute(new Gson().toString(), url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.nocontact_available), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void callTaxGroupServer() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                String url1 = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_GETTAXGROUP;
                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask getTaxGroupTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (result != null) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (taxGroupList != null) {
                                taxGroupList.clear();
                            }

                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    TaxGroupId taxGroupId = new Gson().fromJson(jsonObject.toString(), TaxGroupId.class);
                                    taxGroupList.add(taxGroupId);
                                }
                                callTaxClassServer();

                            } catch (Exception je) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);

                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                        }
                    }
                }, false);
                getTaxGroupTask.execute(new Gson().toString(), url1, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }

    }

    private void getTaxDetailsFromServer(TaxGroupId taxGroup) {
        String url = serverUrl + Constant.URL_TAXPATH + "getTaxType?type=" + taxGroup.getTaxGroupId();
        if (!url.equals("")) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    // prepare the Request
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask task = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                Gson gson = new Gson();
                                try {
                                    if (taxTypeList != null && taxTypeList.size() > 0) {
                                        taxTypeList.clear();
                                    }
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        TaxTypeDatum taxTypeDatum = gson.fromJson(jsonObject.toString(), TaxTypeDatum.class);
                                        taxTypeList.add(taxTypeDatum);
                                    }
                                    if (taxTypeList != null && taxTypeList.size() > 0)
                                        UtilView.setupTaxTypeAdapter(mActivity, spinnerTaxType, taxTypeList);
                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        }
                    }, false);
                    task.execute(new Gson().toString(), url, "");
                }
                if (!isInternetPresent) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        }
    }

    private void setupSpinnerValue() {
        UtilView.hideProgessBar(mActivity, progressBar);
        if (taxClassList != null && taxClassList.size() > 0)
            UtilView.setupTaxClassAdapter(mActivity, spinnerTaxClass, taxClassList);
        if (taxGroupList != null && taxGroupList.size() > 0)
            UtilView.setupTaxGroupAdapter(mActivity, spinnerTaxGroup, taxGroupList);
        if (taxTypeList != null && taxTypeList.size() > 0)
            UtilView.setupTaxTypeAdapter(mActivity, spinnerTaxType, taxTypeList);

        spinnerTaxClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerTaxClass.setSelection(position);
                taxClass = (TaxClassId) spinnerTaxClass.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerTaxGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerTaxGroup.setSelection(position);
                taxGroup = (TaxGroupId) spinnerTaxGroup.getSelectedItem();
                if (callingFor != null) {
                    if (callingFor.equals(Constant.CALL_ADDTAX)) {
                        if (taxGroup != null) {
                            callAccountCodeApi(taxGroup);
                            getTaxDetailsFromServer(taxGroup);
                        }
                    }
                    if (callingFor.equals(Constant.CALL_EDITTAX)) {

                        if (taxDatum != null) {
                            if (taxDatum.getTaxTypeId() != null && taxDatum.getTaxTypeId().getTaxGroupId() != null) {
                                callAccountCodeApi(taxDatum.getTaxTypeId().getTaxGroupId());
                                getTaxDetailsFromServer(taxDatum.getTaxTypeId().getTaxGroupId());
                            }
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerTaxType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerTaxType.setSelection(position);
                taxType = (TaxTypeDatum) spinnerTaxType.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (callingFor != null) {
            if (callingFor.equals(Constant.CALL_EDITTAX)) {
                if (taxDatum != null) {
                    if (taxDatum.getTaxGroup() != null) {
                        if (taxGroupList != null && taxGroupList.size() > 0) {
                            for (int i = 0; i < taxGroupList.size(); i++) {
                                if (Long.parseLong(taxDatum.getTaxGroup()) == taxGroupList.get(i).getTaxGroupId()) {
                                    spinnerTaxGroup.setSelection(i);
                                }
                            }
                        }
                    }
                    if (taxDatum.getTcid().getTaxClassId() != null) {
                        if (taxClassList != null && taxClassList.size() > 0) {
                            for (int i = 0; i < taxClassList.size(); i++) {
                                if (taxDatum.getTcid().getTaxClassId() == taxClassList.get(i).getTaxClassId()) {
                                    spinnerTaxClass.setSelection(i);
                                }
                            }
                        }
                    }
                    if (taxDatum.getTaxTypeId() != null && taxDatum.getTaxTypeId().getTaxTypeId() != null) {
                        if (taxTypeList != null && taxTypeList.size() > 0) {
                            for (int i = 0; i < taxTypeList.size(); i++) {
                                if (taxDatum.getTaxTypeId().getTaxTypeId() == taxTypeList.get(i).getTaxTypeId()) {
                                    spinnerTaxType.setSelection(i);
                                }
                            }
                        }
                    }

                    if (taxDatum.getTaxStatus() != null) {
                        if (taxDatum.getTaxStatus().equals("Active")) {
                            spinnerTaxStatus.setSelection(0);
                        }
                        if (taxDatum.getTaxStatus().equals("InActive")) {
                            spinnerTaxStatus.setSelection(1);
                        }
                    }
                }
            }
        }
    }

    private void callAccountCodeApi(TaxGroupId taxGroup) {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                String url1 = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_GETACCOUNTCODE + "?grp=" + taxGroup.getTaxGroupId() + "&typ=0";
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getTaxGroupTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            edActCode.setText(result.toString());
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                getTaxGroupTask.execute(url1, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.nocontact_available), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    @OnClick({R.id.btnClose, R.id.btnAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnClose:
                finish();
                break;
            case R.id.btnAdd:
                addTax();
                break;
        }
    }

    private void addTax() {
        String taxCode = edTaxCode.getText().toString().trim();
        String accountCode = edActCode.getText().toString().trim();
        String taxName = edTaxName.getText().toString().trim();
        String taxPercan = edTaxPercatnge.getText().toString().trim();
        String taxDescription = edTaxDescritpion.getText().toString().trim();
        double taxPercantage = 0;
        if (taxPercan != null && !taxPercan.equals("")) {
            try {
                isSaveable = true;
                taxPercantage = Double.parseDouble(taxPercan);
            } catch (NumberFormatException e) {
                isSaveable = false;
                edTaxPercatnge.setError(getResources().getString(R.string.error_numberformate));
            }

        }
        if (taxCode != null && !taxCode.equals("") &&
                accountCode != null && !accountCode.equals("") &&
                taxName != null && !taxName.equals("") &&
                isSaveable && taxClass != null && taxGroup != null && taxType != null && taxStatus != null && !taxStatus.equals("")) {
            AddTax addTax = new AddTax();
            if (taxType.getTaxTypeId() != null)
                addTax.setTaxTypeId(taxType.getTaxTypeId());
            if (taxClass.getTaxClassId() != null)
                addTax.setTaxClassId(taxClass.getTaxClassId());
            if (taxGroup.getTaxGroupId() != null)
                addTax.setTaxGroup(taxGroup.getTaxGroupId());
            addTax.setTaxCode(taxCode);
            addTax.setTaxName(taxName);
            addTax.setTaxPercentage(taxPercantage);
            addTax.setTaxDescription(taxDescription);
            addTax.setAccountCode(accountCode);
            addTax.setTaxStatus(taxStatus);

            if (callingFor != null) {
                if (callingFor.equals(Constant.CALL_EDITTAX))
                    addTax.setTaxid(taxDatum.getTaxId());
            }

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    String url = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_SAVETAX;
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {

                                if (result.toString().equals(getString(R.string.error_rsonsecode204))) {
                                    if (callingFor != null) {
                                        if (callingFor.equals(Constant.CALL_EDITTAX)) {
                                            UtilView.showToast(mActivity, "Tax Edit Successfully");
                                        }
                                        if (callingFor.equals(Constant.CALL_ADDTAX)) {
                                            UtilView.showToast(mActivity, "Tax Add Successfully");
                                        }
                                        Intent returnIntent = new Intent();
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                        mActivity.finish();
                                    }
                                }

                                UtilView.showToast(mActivity, "Tax Add Successfully");
                                Intent returnIntent = new Intent();
                                mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                mActivity.finish();

                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        }


                    }, false);
                    postDataTask.execute(new Gson().toJson(addTax).toString(), url, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.nocontact_available), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }

        } else {
            if (taxCode == null || taxCode.equals("")) {
                edTaxCode.setError(getResources().getString(R.string.err_msg));
            }

            if (accountCode == null || accountCode.equals("")) {
                edActCode.setError(getResources().getString(R.string.err_msg));
            }

            if (taxName == null || taxName.equals("")) {
                edTaxName.setError(getResources().getString(R.string.err_msg));
            }

            if (taxClass == null) {
                TextView tv = (TextView) spinnerTaxClass.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }

            if (taxGroup == null) {
                TextView tv = (TextView) spinnerTaxGroup.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }

            if (taxType == null) {
                TextView tv = (TextView) spinnerTaxType.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }
            if (taxStatus == null) {
                TextView tv = (TextView) spinnerTaxStatus.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }
        }

    }
}
