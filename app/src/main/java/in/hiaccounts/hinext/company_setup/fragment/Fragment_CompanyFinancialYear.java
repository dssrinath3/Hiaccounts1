package in.hiaccounts.hinext.company_setup.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.company_setup.activity.Activity_CompanySubcription;
import in.hiaccounts.hinext.company_setup.model.CompanyPageInfo;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.array.startingPeriod;

/**
 * Created by Prateek on 6/20/2017.
 */

public class Fragment_CompanyFinancialYear extends Fragment implements TextWatcher {

    public static final String TAG = Fragment_CompanyFinancialYear.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edLastAccountingYear)
    EditText edLastAccountingYear;
    @BindView(R.id.spinnerClosingMethod)
    Spinner spinnerClosingMethod;
    @BindView(R.id.spinnerStartingPeriod)
    Spinner spinnerStartingPeriod;
    @BindView(R.id.spinnerClosingPeriod)
    Spinner spinnerClosingPeriod;
    @BindView(R.id.edAccountinStartDate)
    EditText edAccountinStartDate;
    @BindView(R.id.edAccountinEndDate)
    EditText edAccountinEndDate;
    @BindView(R.id.spinnerExtensionPeriod)
    Spinner spinnerExtensionPeriod;
    @BindView(R.id.edExtension)
    EditText edExtension;
    @BindView(R.id.spinnerGSTTaxablePeriod)
    Spinner spinnerGSTTaxablePeriod;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvNext)
    TextView tvNext;
    Unbinder unbinder;
    int year, month, dayOfMonth;
    private Activity mActivity;
    Calendar calendar=null;
    Calendar closingCalender;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity=(Activity)context;
    }

    // call when instace of Fra_GeneralInfo is created.
    public static Fragment_CompanyFinancialYear newInstance() {
        Fragment_CompanyFinancialYear fragment = new Fragment_CompanyFinancialYear();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.compnaysubcription_financialyear, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponentView();
        return view;

    }

    private void initComponentView() {

        toolbar.setTitle(getString(R.string.comapnyFinancialYear));
        toolbar.setTitleTextColor(Color.WHITE);

        edLastAccountingYear.addTextChangedListener(this);

        UtilView.setupAdapter(mActivity,spinnerClosingMethod,getResources().getStringArray(R.array.closingMethod));
        UtilView.setupAdapter(mActivity,spinnerStartingPeriod,getResources().getStringArray(startingPeriod));
        UtilView.setupAdapter(mActivity,spinnerClosingPeriod,getResources().getStringArray(R.array.closingPeriod));
        UtilView.setupAdapter(mActivity,spinnerExtensionPeriod,getResources().getStringArray(R.array.extensionPeriod));
        UtilView.setupAdapter(mActivity,spinnerGSTTaxablePeriod,getResources().getStringArray(R.array.gstTaxablePeriod));

        spinnerStartingPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (calendar!=null) {
                    updateStartingYears();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerClosingPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (calendar!=null)
                updateClosingYears();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerExtensionPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (calendar!=null && closingCalender!=null)
                updateExtensionPeriod(closingCalender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvBack, R.id.tvNext,R.id.edLastAccountingYear})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.edLastAccountingYear:
                getDatePicker(mActivity);
                break;

            case R.id.tvBack:
                ((Activity_CompanySubcription) getActivity()).showCompanyInformation();

                break;
            case R.id.tvNext:

                SharedPreference sharedPreference=SharedPreference.getInstance(mActivity);
                String companyInformationString=sharedPreference.getData(Constant.KEY_COMPANYINFORMATION);

                String yearClosing=edLastAccountingYear.getText().toString();
                String closingMethod="";
                closingMethod= (String) spinnerClosingMethod.getSelectedItem();
                String startingPeriod="";
                startingPeriod= (String) spinnerStartingPeriod.getSelectedItem();
                String closingPeriod="";
                closingPeriod= (String) spinnerClosingPeriod.getSelectedItem();
                String extensionPeriod="";
                extensionPeriod= (String) spinnerExtensionPeriod.getSelectedItem();
                String gstTaxablePeriod="";
                gstTaxablePeriod= (String) spinnerGSTTaxablePeriod.getSelectedItem();

                String startingYear=edAccountinStartDate.getText().toString();
                String endingYear=edAccountinEndDate.getText().toString();
                String extensionYear=edExtension.getText().toString();


                if (yearClosing!=null && !yearClosing.equals("")){

                    Gson gson=new Gson();
                    CompanyPageInfo companyPageInfo=gson.fromJson(companyInformationString, CompanyPageInfo.class);

                    companyPageInfo.setYearclosing(yearClosing);
                    companyPageInfo.setClosingMethod(closingMethod);
                    companyPageInfo.setStartperiod(startingPeriod);
                    companyPageInfo.setClosingperiod(closingPeriod);
                    companyPageInfo.setBufferDays(extensionPeriod);

                    companyPageInfo.setStartyear(startingYear);
                    companyPageInfo.setEndyear(endingYear);
                    companyPageInfo.setBufferDate(extensionYear);

                    if (gstTaxablePeriod.equals("Monthly")){
                        companyPageInfo.setGstreturnalertdue(1);
                    }
                    if (gstTaxablePeriod.equals("Quarterly")){
                        companyPageInfo.setGstreturnalertdue(3);
                    }
                    sharedPreference.saveData(Constant.KEY_COMPANYINFORMATION,gson.toJson(companyPageInfo).toString());
                    ((Activity_CompanySubcription) getActivity()).showCompanyChartsAccount();
                }else {
                    edLastAccountingYear.setError("Please select Last accounting year closing date");
                }

                break;
        }
    }

    public void getDatePicker(Activity mActivity) {
        calendar= Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edLastAccountingYear.setText(String.valueOf(new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year)));
                calendar.set(year, month, dayOfMonth);
                updateStartingYears();
                updateClosingYears();
            }


        }, cYear, cMonth, cDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

    private void updateStartingYears() {
        String startingPeriod= (String) spinnerStartingPeriod.getSelectedItem();
        Calendar startingCalender=Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        startingCalender.set(year,month,dayOfMonth);
        startingCalender.add(Calendar.DAY_OF_MONTH,Integer.parseInt(startingPeriod+1));
        int cYear = startingCalender.get(Calendar.YEAR);
        int cMonth = startingCalender.get(Calendar.MONTH);
        int cDay = startingCalender.get(Calendar.DAY_OF_MONTH);

        edAccountinStartDate.setText(String.valueOf(new StringBuilder().append(cDay).append("/").append(cMonth + 1).append("/").append(cYear)));

    }
    private void  updateClosingYears() {
        String closingPeriod= (String) spinnerClosingPeriod.getSelectedItem();
        closingCalender=Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        closingCalender.set(year,month,dayOfMonth);
        closingCalender.add(Calendar.MONTH,Integer.parseInt(closingPeriod));

        int cYear = closingCalender.get(Calendar.YEAR);
        int cMonth = closingCalender.get(Calendar.MONTH);
        int cDay = closingCalender.get(Calendar.DAY_OF_MONTH);

        edAccountinEndDate.setText(String.valueOf(new StringBuilder().append(cDay).append("/").append(cMonth+1).append("/").append(cYear)));

        updateExtensionPeriod(closingCalender);

    }

    private void updateExtensionPeriod(Calendar closingCalender) {
        String extensionPeriod= (String) spinnerExtensionPeriod.getSelectedItem();
        Calendar extensionCalender=Calendar.getInstance();
        year = closingCalender.get(Calendar.YEAR);
        month = closingCalender.get(Calendar.MONTH);
        dayOfMonth = closingCalender.get(Calendar.DAY_OF_MONTH);
        extensionCalender.set(year,month,dayOfMonth);
        extensionCalender.add(Calendar.MONTH,Integer.parseInt(extensionPeriod));
        int cYear = extensionCalender.get(Calendar.YEAR);
        int cMonth = extensionCalender.get(Calendar.MONTH);
        int cDay = extensionCalender.get(Calendar.DAY_OF_MONTH);
        edExtension.setText(String.valueOf(new StringBuilder().append(cDay).append("/").append(cMonth+1).append("/").append(cYear)));

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        edLastAccountingYear.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
