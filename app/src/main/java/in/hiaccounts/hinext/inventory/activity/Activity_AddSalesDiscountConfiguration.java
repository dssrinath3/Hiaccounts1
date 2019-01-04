package in.hiaccounts.hinext.inventory.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.inventory.model.salesdiscountconfiguration.AddSalesCountConfigData;
import in.hiaccounts.hinext.inventory.model.salesdiscountconfiguration.SalesDiscountConfigDataDatum;
import in.hiaccounts.hinext.inventory.model.salesdiscountconfiguration.SalesDiscountConfigSelectData;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_AddSalesDiscountConfiguration extends AppCompatActivity {
    public static String TAG = Activity_AddSalesDiscountConfiguration.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edItemName)
    EditText edItemName;
    @BindView(R.id.radioBtnAmount)
    RadioButton radioBtnAmount;
    @BindView(R.id.radioBtnPercentage)
    RadioButton radioBtnPercentage;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.edDiscountValue)
    EditText edDiscountValue;
    @BindView(R.id.edFromDate)
    EditText edFromDate;
    @BindView(R.id.edToDate)
    EditText edToDate;
    @BindView(R.id.spinSalesDiscountConfigStatus)
    Spinner spinSalesDiscountConfigStatus;
    @BindView(R.id.id_SalesDiscountConfigStatus)
    LinearLayout idSalesDiscountConfigStatus;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.llBottomLayout)
    LinearLayout llBottomLayout;

    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    String callingFor,discountType ="";
    Long itemID;
    String fromDate = "", toDate = "";
    private boolean isAllValid = true;
    String serverUrl,salesDiscountConfigStatus;
    private List<Object> objectSalesDiscountStatusList = new ArrayList<Object>();
    SalesDiscountConfigDataDatum salesDiscountConfigSelectDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_discount_configuration);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        Intent intent = getIntent();
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITSALESDISCOUNTCONFIG)) {
                    salesDiscountConfigSelectDetails = (SalesDiscountConfigDataDatum) intent.getSerializableExtra("salesDiscountConfigData");
                    if (salesDiscountConfigSelectDetails != null) {

                        if (salesDiscountConfigSelectDetails.getItemName() != null)
                            edItemName.setText(salesDiscountConfigSelectDetails.getItemName().toString());



                        if(salesDiscountConfigSelectDetails.getCheckBoxValue().equals("Percentage") ){
                            radioBtnPercentage.setChecked(true);
                            discountType = "Percentage";
                        }
                        if(salesDiscountConfigSelectDetails.getCheckBoxValue().equals("Amount")){
                            radioBtnAmount.setChecked(true);
                            discountType = "Amount";
                        }


                        if (salesDiscountConfigSelectDetails.getAmountordiscount() != null)
                            edDiscountValue.setText(salesDiscountConfigSelectDetails.getAmountordiscount().toString());

                        objectSalesDiscountStatusList.clear();
                        objectSalesDiscountStatusList.add("Active");
                        objectSalesDiscountStatusList.add("InActive");
                        UtilView.setupItemAdapter(activity, spinSalesDiscountConfigStatus, objectSalesDiscountStatusList);

                        if (salesDiscountConfigSelectDetails.getStatus() != null) {
                            if (salesDiscountConfigSelectDetails.getStatus().equals("Active")) {
                                spinSalesDiscountConfigStatus.setSelection(0);
                            }
                            if (salesDiscountConfigSelectDetails.getStatus().equals("InActive")) {
                                spinSalesDiscountConfigStatus.setSelection(1);
                            }
                        }


                        if (salesDiscountConfigSelectDetails.getFromDate() != null)
                        {

                            edFromDate.setText(""+salesDiscountConfigSelectDetails.getFromDate());
                            fromDate = salesDiscountConfigSelectDetails.getFromDate().toString();
                        }



                        if (salesDiscountConfigSelectDetails.getToDate() != null)
                        {
                            edToDate.setText(""+salesDiscountConfigSelectDetails.getToDate());

                            toDate = salesDiscountConfigSelectDetails.getToDate().toString();
                        }

                        itemID = salesDiscountConfigSelectDetails.getItemID();

                        edItemName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getItemData();
                            }
                        });

                    }

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




        edItemName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        spinSalesDiscountConfigStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectSalesDiscountStatusList.get(i);
                if (obj instanceof String) {
                    spinSalesDiscountConfigStatus.setSelection(i);
                    salesDiscountConfigStatus = (String) spinSalesDiscountConfigStatus.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        objectSalesDiscountStatusList.clear();
        objectSalesDiscountStatusList.add("Active");
        objectSalesDiscountStatusList.add("InActive");
        UtilView.setupItemAdapter(activity, spinSalesDiscountConfigStatus, objectSalesDiscountStatusList);


        edItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemData();
            }
        });
    }

    @OnClick({R.id.radioBtnAmount, R.id.radioBtnPercentage, R.id.edFromDate, R.id.edToDate, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radioBtnAmount:
                getDiscountType(activity);
                break;
            case R.id.radioBtnPercentage:
                getDiscountType(activity);
                break;
            case R.id.edFromDate:
                getFromDatePicker(activity);
                break;
            case R.id.edToDate:
                getToDatePicker(activity);
                break;
            case R.id.btnSave:
                addSalesDiscountConfiguration();
                break;
            case R.id.btnClose:
                finish();
                break;
        }

    }

    private void addSalesDiscountConfiguration() {
        String discountValue = edDiscountValue.getText().toString().trim();
        String itemName = edItemName.getText().toString().trim();


        if (itemName != null && !itemName.equals("") && discountValue !=null && !discountValue.equals("")  && isAllValid) {

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddSalesCountConfigData salesDiscountData = new AddSalesCountConfigData();
                salesDiscountData.setItemID(itemID);
                salesDiscountData.setCustomerName("");
                salesDiscountData.setItemName(itemName);
                salesDiscountData.setCheckBoxValue(discountType);
                salesDiscountData.setAmountordiscount(Long.valueOf(discountValue));
                salesDiscountData.setFromDate(fromDate);
                salesDiscountData.setToDate(toDate);
                salesDiscountData.setStatus(salesDiscountConfigStatus);



                if (callingFor.equals(Constant.CALL_EDITSALESDISCOUNTCONFIG)) {

                    salesDiscountData.setDiscountId(salesDiscountConfigSelectDetails.getDiscountId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDSALESDISCOUNTCONFIG;

                if (serverUrl != null) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {
                        UtilView.showProgessBar(activity, progressBar);
                        PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                UtilView.hideProgessBar(activity, progressBar);

                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(activity, Activity_Login.class);
                                        activity.startActivity(intent);
                                        activity.finish();
                                    } else {
                                        if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {

                                            UtilView.showToast(activity, "Item Already Exists");
                                        } else {

                                            Gson gson = new Gson();
                                            try {
                                                JSONObject jsonObject = new JSONObject(result.toString());
                                                AddSalesCountConfigData addsalesDiscountData = gson.fromJson(jsonObject.toString(), AddSalesCountConfigData.class);

                                                if (addsalesDiscountData != null) {
                                                    Intent returnIntent = new Intent();
                                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                                    activity.finish();
                                                    UtilView.showToast(activity, "Sales Discount Configuration added successfully.");

                                                } else {
                                                    UtilView.showToast(activity, "Something Error.");
                                                }


                                            } catch (Exception e) {
                                                UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                            }
                                        }
                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                                }
                            }
                        }, false);
                        task.execute(new Gson().toJson(salesDiscountData), url, "");

                    }
                    if (!isInternetPresent) {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                    }
                } else {
                    UtilView.getUrl(activity);
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            if (discountValue.equals("") || discountValue == null) {
                edDiscountValue.setError(getString(R.string.err_msg));
            }
            if (itemName.equals("") || itemName == null) {
                edItemName.setError(getResources().getString(R.string.err_msg));
            }


        }
    }

    private void getDiscountType(Activity activity) {
        if(radioBtnAmount.isChecked() == true){
            discountType ="Amount";

        }
        if (radioBtnPercentage.isChecked() == true){
            discountType ="Percentage";
        }
    }

    private void getItemData() {
        if (serverUrl != null) {
            String url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
            Intent intent = new Intent(activity, Activity_ShowItemList.class);
            intent.putExtra("url", url);
            intent.putExtra("taxType", "");
            intent.putExtra("itemsearch", "");
            intent.putExtra("callingfrom", Constant.CALL_ADDINVENTORUOPENING_ITEM);
            startActivityForResult(intent, Constant.RESQUSTCODE_ITEMSEARCH);
        } else {
            UtilView.gotToLogin(activity);
        }
    }


    public void getFromDatePicker(Activity mActivity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edFromDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                fromDate =f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

    public void getToDatePicker(Activity mActivity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edToDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                toDate =f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == RESULT_OK) {
            SelectedItemsList item = (SelectedItemsList) data.getSerializableExtra("item");

            itemID = item.getItemId();

            if (item.getItemName() != null)
                edItemName.setText(item.getItemName());
        }
    }
}
