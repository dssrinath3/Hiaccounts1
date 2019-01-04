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
import in.hiaccounts.hinext.inventory.model.itemcommission.AddItemCommissionData;
import in.hiaccounts.hinext.inventory.model.itemcommission.ItemCommissionSelectData;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.id.radioBtnAmount;

public class Activity_AddItemCommission extends AppCompatActivity {
    public static String TAG = Activity_AddItemCommission.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edItemName)
    EditText edItemName;
    @BindView(R.id.radioBtnAmount)
    RadioButton radioBtnAmount;
    @BindView(R.id.radioBtnPercentage)
    RadioButton radioBtnPercentage;
    @BindView(R.id.radioGroupAop)
    RadioGroup radioGroupAop;
    @BindView(R.id.edCommissionValue)
    EditText edCommissionValue;
    @BindView(R.id.edFromDate)
    EditText edFromDate;
    @BindView(R.id.edToDate)
    EditText edToDate;
    @BindView(R.id.spinItemCommissionStatus)
    Spinner spinItemCommissionStatus;
    @BindView(R.id.id_itemCommissionStatus)
    LinearLayout idItemCommissionStatus;
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
    String callingFor,commissiontype;
    Long itemID;
    String fromDate = "", toDate = "";
    private boolean isAllValid = true;
    String serverUrl,itemcommisionStatus;
    private List<Object> objectitemcommisionStatusList = new ArrayList<Object>();
    ItemCommissionSelectData itemCommissionSelectDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_commission);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);

        objectitemcommisionStatusList.clear();
        objectitemcommisionStatusList.add("Select");
        objectitemcommisionStatusList.add("Active");
        objectitemcommisionStatusList.add("InActive");
        UtilView.setupItemAdapter(activity, spinItemCommissionStatus, objectitemcommisionStatusList);

        Intent intent = getIntent();
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITITEMCOMMISSION)) {
                    itemCommissionSelectDetails = (ItemCommissionSelectData) intent.getSerializableExtra("itemCommissionData");
                    if (itemCommissionSelectDetails != null) {

                        if (itemCommissionSelectDetails.getItemName() != null)
                            edItemName.setText(itemCommissionSelectDetails.getItemName().toString());



                        if(itemCommissionSelectDetails.getCheckBoxValue().equals("Percentage") ){
                            radioBtnPercentage.setChecked(true);
                            commissiontype = "Percentage";
                        }
                        if(itemCommissionSelectDetails.getCheckBoxValue().equals("Amount")){
                            radioBtnAmount.setChecked(true);
                            commissiontype = "Amount";
                        }


                        if (itemCommissionSelectDetails.getAmountordiscount() != null)
                            edCommissionValue.setText(itemCommissionSelectDetails.getAmountordiscount().toString());


                        if (itemCommissionSelectDetails.getStatus() != null) {
                            if (itemCommissionSelectDetails.getStatus().equals("Active")) {
                                spinItemCommissionStatus.setSelection(1);
                            }
                            if (itemCommissionSelectDetails.getStatus().equals("InActive")) {
                                spinItemCommissionStatus.setSelection(2);
                            }
                        }


                        if (itemCommissionSelectDetails.getFromDate() != null)
                        {

                             edFromDate.setText(""+itemCommissionSelectDetails.getFromDate());
                            fromDate = itemCommissionSelectDetails.getFromDate().toString();
                        }



                        if (itemCommissionSelectDetails.getToDate() != null)
                        {
                          edToDate.setText(""+itemCommissionSelectDetails.getToDate());

                            toDate = itemCommissionSelectDetails.getToDate().toString();
                        }

                        itemID = itemCommissionSelectDetails.getItemID();

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

        spinItemCommissionStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectitemcommisionStatusList.get(i);
                if (obj instanceof String) {
                    spinItemCommissionStatus.setSelection(i);
                    itemcommisionStatus = (String) spinItemCommissionStatus.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

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
                getCommissionType(activity);
                break;
            case R.id.radioBtnPercentage:
                getCommissionType(activity);
                break;
            case R.id.edFromDate:
                getFromDatePicker(activity);
                break;
            case R.id.edToDate:
                getToDatePicker(activity);
                break;
            case R.id.btnSave:
                addItemCommission();
                break;
            case R.id.btnClose:
                finish();
                break;
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

    public void getCommissionType(Activity activity) {
        if(radioBtnAmount.isChecked() == true){
            commissiontype ="Amount";

        }
        if (radioBtnPercentage.isChecked() == true){
            commissiontype ="Percentage";
        }
    }

    private void addItemCommission() {
        String commissionValue = edCommissionValue.getText().toString().trim();
        String itemName = edItemName.getText().toString().trim();


        if (itemName != null && !itemName.equals("") && commissionValue !=null && !commissionValue.equals("")  && isAllValid) {

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddItemCommissionData itemData = new AddItemCommissionData();
                itemData.setItemID(itemID);
                itemData.setCustomerName("");
                itemData.setItemName(itemName);
                itemData.setCheckBoxValue(commissiontype);
                itemData.setAmountordiscount(Long.valueOf(commissionValue));
                itemData.setFromDate(fromDate);
                itemData.setToDate(toDate);
                itemData.setStatus(itemcommisionStatus);



                if (callingFor.equals(Constant.CALL_EDITITEMCOMMISSION)) {

                    itemData.setItemCommId(itemCommissionSelectDetails.getItemCommId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDITEMCOMMISSION;

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
                                                AddItemCommissionData additemData = gson.fromJson(jsonObject.toString(), AddItemCommissionData.class);

                                                if (additemData != null) {
                                                    Intent returnIntent = new Intent();
                                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                                    activity.finish();
                                                    UtilView.showToast(activity, "Item Commission added successfully.");

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
                        task.execute(new Gson().toJson(itemData), url, "");

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
            if (commissionValue.equals("") || commissionValue == null) {
                edCommissionValue.setError(getString(R.string.err_msg));
            }
            if (itemName.equals("") || itemName == null) {
                edItemName.setError(getResources().getString(R.string.err_msg));
            }


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
