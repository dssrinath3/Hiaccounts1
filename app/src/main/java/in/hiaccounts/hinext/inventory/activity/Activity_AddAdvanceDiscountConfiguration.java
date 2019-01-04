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
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.customer.model.CustomerList;
import in.hiaccounts.hinext.inventory.model.advancediscountconfiguration.AddAdvanceDiscountConfigurationData;
import in.hiaccounts.hinext.inventory.model.advancediscountconfiguration.AdvanceDiscountConfigSelectData;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_AddAdvanceDiscountConfiguration extends AppCompatActivity {
    public static String TAG = Activity_AddAdvanceDiscountConfiguration.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edPromotionName)
    EditText edPromotionName;
    @BindView(R.id.edCustomerName)
    EditText edCustomerName;
    @BindView(R.id.edItemName)
    EditText edItemName;
    @BindView(R.id.edQuantity)
    EditText edQuantity;
    @BindView(R.id.edMinPurchaseAmount)
    EditText edMinPurchaseAmount;
    @BindView(R.id.radioBtnAuto)
    RadioButton radioBtnAuto;
    @BindView(R.id.radioBtnUseCode)
    RadioButton radioBtnUseCode;
    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;
    @BindView(R.id.edPromocodeValue)
    EditText edPromocodeValue;
    @BindView(R.id.idPromoCode)
    LinearLayout idPromoCode;
    @BindView(R.id.radioBtnDiscount)
    RadioButton radioBtnDiscount;
    @BindView(R.id.radioBtnfreeProducts)
    RadioButton radioBtnfreeProducts;
    @BindView(R.id.radioGroup2)
    RadioGroup radioGroup2;
    @BindView(R.id.radioBtnAmount)
    RadioButton radioBtnAmount;
    @BindView(R.id.radioBtnPercentage)
    RadioButton radioBtnPercentage;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.edDiscountValue)
    EditText edDiscountValue;
    @BindView(R.id.edProductName)
    EditText edProductName;
    @BindView(R.id.edRewardQuantity)
    EditText edRewardQuantity;
    @BindView(R.id.edFromDate)
    EditText edFromDate;
    @BindView(R.id.edToDate)
    EditText edToDate;
    @BindView(R.id.spinAdvanceDiscountConfigStatus)
    Spinner spinAdvanceDiscountConfigStatus;
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
    @BindView(R.id.id_DiscountShow)
    LinearLayout idDiscountShow;
    @BindView(R.id.id_FreeProductsShow)
    LinearLayout idFreeProductsShow;


    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    String callingFor, discountType,promoCode;
    Long itemID, customerID;
    String fromDate = "", toDate = "";
    private boolean isAllValid = true;
    String serverUrl, advanceDiscountConfigStatus;
    private List<Object> objectAdvanceDiscountStatusList = new ArrayList<Object>();
    CustomerList cust;
    AdvanceDiscountConfigSelectData advSelectDataDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advance_discount_configuration);
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
                if (callingFor.equals(Constant.CALL_EDITADVANCESDISCOUNTCONFIG)) {
                    advSelectDataDetails = (AdvanceDiscountConfigSelectData) intent.getSerializableExtra("advanceDiscountConfigData");
                    if (advSelectDataDetails != null) {

                        if (advSelectDataDetails.getItemName() != null)
                            edItemName.setText(advSelectDataDetails.getItemName().toString());

                        if (advSelectDataDetails.getCustomerName() != null)
                            edCustomerName.setText(advSelectDataDetails.getCustomerName().toString());

                        if (advSelectDataDetails.getPromotionName() != null)
                            edPromotionName.setText(advSelectDataDetails.getPromotionName().toString());

                        if (advSelectDataDetails.getPromotionName() != null)
                            edPromotionName.setText(advSelectDataDetails.getPromotionName().toString());

                        if (advSelectDataDetails.getMinPurchaseAmt() != null)
                            edMinPurchaseAmount.setText(advSelectDataDetails.getMinPurchaseAmt().toString());

                        if (advSelectDataDetails.getCheckBoxValue().equals("Percentage")) {
                            radioBtnPercentage.setChecked(true);
                            discountType = "Percentage";
                        }
                        if (advSelectDataDetails.getCheckBoxValue().equals("Amount")) {
                            radioBtnAmount.setChecked(true);
                            discountType = "Amount";
                        }


                        if (advSelectDataDetails.getAmountordiscount() != null)
                            edDiscountValue.setText(advSelectDataDetails.getAmountordiscount().toString());

                        objectAdvanceDiscountStatusList.clear();
                        objectAdvanceDiscountStatusList.add("Active");
                        objectAdvanceDiscountStatusList.add("InActive");
                        UtilView.setupItemAdapter(activity, spinAdvanceDiscountConfigStatus, objectAdvanceDiscountStatusList);

                        if (advSelectDataDetails.getStatus() != null) {
                            if (advSelectDataDetails.getStatus().equals("Active")) {
                                spinAdvanceDiscountConfigStatus.setSelection(0);
                            }
                            if (advSelectDataDetails.getStatus().equals("InActive")) {
                                spinAdvanceDiscountConfigStatus.setSelection(1);
                            }
                        }


                        if (advSelectDataDetails.getFromDate() != null) {

                            edFromDate.setText("" + advSelectDataDetails.getFromDate());
                            fromDate = advSelectDataDetails.getFromDate().toString();
                        }


                        if (advSelectDataDetails.getToDate() != null) {
                            edToDate.setText("" + advSelectDataDetails.getToDate());

                            toDate = advSelectDataDetails.getToDate().toString();
                        }

                        itemID = advSelectDataDetails.getItemID();

                        edItemName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getItemData();
                            }
                        });

                        customerID = advSelectDataDetails.getCustomerId();
                      /*  edCustomerName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });*/

                        edProductName.setOnClickListener(new View.OnClickListener() {
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

        edCustomerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                edCustomerName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

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


        edProductName.addTextChangedListener(new TextWatcher() {
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

        spinAdvanceDiscountConfigStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAdvanceDiscountStatusList.get(i);
                if (obj instanceof String) {
                    spinAdvanceDiscountConfigStatus.setSelection(i);
                    advanceDiscountConfigStatus = (String) spinAdvanceDiscountConfigStatus.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        objectAdvanceDiscountStatusList.clear();
        objectAdvanceDiscountStatusList.add("Active");
        objectAdvanceDiscountStatusList.add("InActive");
        UtilView.setupItemAdapter(activity, spinAdvanceDiscountConfigStatus, objectAdvanceDiscountStatusList);

        edProductName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemData();
            }
        });

        edItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemData();
            }
        });
    }

    @OnClick({R.id.edCustomerName, R.id.radioBtnDiscount, R.id.radioBtnfreeProducts, R.id.radioBtnAmount, R.id.radioBtnPercentage, R.id.radioBtnAuto, R.id.radioBtnUseCode, R.id.edFromDate, R.id.edToDate, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edCustomerName:
                Intent intent = new Intent(activity, Activity_Customer.class);
                startActivityForResult(intent, Constant.RESQUSTCODE_CUSTOMERS);
                break;
            case R.id.radioBtnDiscount:
                getRewardType(activity);
                break;
            case R.id.radioBtnfreeProducts:
                getRewardType(activity);
                break;
            case R.id.radioBtnAmount:
                getDiscountType(activity);
                break;
            case R.id.radioBtnPercentage:
                getDiscountType(activity);
                break;
            case R.id.radioBtnUseCode:
                getPromoCodeValue(activity);
                break;
            case R.id.radioBtnAuto:
                getPromoCodeValue(activity);
                break;
            case R.id.edFromDate:
                getFromDatePicker(activity);
                break;
            case R.id.edToDate:
                getToDatePicker(activity);
                break;
            case R.id.btnSave:
                addAdvanceDiscountConfiguration();
                break;
            case R.id.btnClose:
                finish();
                break;
        }

    }

    private void getRewardType(Activity activity) {
        if (radioBtnDiscount.isChecked() == true) {
            idDiscountShow.setVisibility(View.VISIBLE);
            idFreeProductsShow.setVisibility(View.GONE);
        }
        if (radioBtnfreeProducts.isChecked() == true) {
            idFreeProductsShow.setVisibility(View.VISIBLE);
            idDiscountShow.setVisibility(View.GONE);
        }
    }



    private void addAdvanceDiscountConfiguration() {
        String promotionName = edPromotionName.getText().toString().trim();
        String customerName = edCustomerName.getText().toString().trim();
        String itemName = edItemName.getText().toString().trim();
        String productName = edProductName.getText().toString().trim();
        String discountValue = edDiscountValue.getText().toString().trim();
        String minPurchaseAmount = edMinPurchaseAmount.getText().toString().trim();
        String promocodeValue = edPromocodeValue.getText().toString().trim();
        String freeProductQty = edRewardQuantity.getText().toString().trim();
        String qty = edQuantity.getText().toString().trim();

        if (promotionName != null && !promotionName.equals("") && itemName != null && !itemName.equals("") && customerName != null && !customerName.equals("") && isAllValid) {

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddAdvanceDiscountConfigurationData advanceDiscountData = new AddAdvanceDiscountConfigurationData();
                advanceDiscountData.setFromDate(fromDate);
                advanceDiscountData.setToDate(toDate);
                advanceDiscountData.setAmountordiscount(discountValue);
                advanceDiscountData.setCustomerId(null);
                advanceDiscountData.setCustomerName(customerName);
                advanceDiscountData.setItemName(itemName);
                advanceDiscountData.setItemId(null);
                advanceDiscountData.setCheckBoxValue(discountType);
                advanceDiscountData.setCustomerID(customerID);
                advanceDiscountData.setItemID(itemID);
                advanceDiscountData.setQuantity(Long.valueOf(qty));
                advanceDiscountData.setPromotionName(promotionName);
                advanceDiscountData.setMinPurchaseAmt(Long.valueOf(minPurchaseAmount));
                advanceDiscountData.setPromoCode(promoCode);
                advanceDiscountData.setFreeProduct(productName);
                advanceDiscountData.setFreeProductQty(freeProductQty);
                advanceDiscountData.setStatus(advanceDiscountConfigStatus);


                if (callingFor.equals(Constant.CALL_EDITADVANCESDISCOUNTCONFIG)) {

                     advanceDiscountData.setDiscountId(advSelectDataDetails.getDiscountId());
                }

                String url = serverUrl + "/hipos//2/" + Constant.FUNTION_ADDADVANCESDISCOUNTCONFIG;

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
                                                AddAdvanceDiscountConfigurationData addadvanceDiscountData = gson.fromJson(jsonObject.toString(), AddAdvanceDiscountConfigurationData.class);

                                                if (addadvanceDiscountData != null) {
                                                    Intent returnIntent = new Intent();
                                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                                    activity.finish();
                                                    UtilView.showToast(activity, "Advance Discount Configuration added successfully.");

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
                        task.execute(new Gson().toJson(advanceDiscountData), url, "");

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
            if (customerName.equals("") || customerName == null) {
                edCustomerName.setError(getString(R.string.err_msg));
            }
            if (itemName.equals("") || itemName == null) {
                edItemName.setError(getResources().getString(R.string.err_msg));
            }
            if (promotionName.equals("") || promotionName == null) {
                edPromotionName.setError(getResources().getString(R.string.err_msg));
            }


        }
    }

    private void getDiscountType(Activity activity) {
        if (radioBtnAmount.isChecked() == true) {
            discountType = "Amount";

        }
        if (radioBtnPercentage.isChecked() == true) {
            discountType = "Percentage";
        }
    }
    private void getPromoCodeValue(Activity activity) {
        if (radioBtnUseCode.isChecked() == true) {
            promoCode ="usecode";
            idPromoCode.setVisibility(View.VISIBLE);

        }
        if (radioBtnAuto.isChecked() == true) {
            promoCode ="auto";
            idPromoCode.setVisibility(View.GONE);
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
                fromDate = f.format(calendar.getTime());
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
                toDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_CUSTOMERS && resultCode == RESULT_OK) {
            cust = (CustomerList) data.getSerializableExtra("customer");
            if (cust != null)

                customerID = cust.getCustomerId();
            if (cust.getCustomerName() != null)
                edCustomerName.setText(cust.getCustomerName());
        }

        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == RESULT_OK) {
            SelectedItemsList item = (SelectedItemsList) data.getSerializableExtra("item");

            itemID = item.getItemId();

            if (item.getItemName() != null)
                edItemName.setText(item.getItemName());

            if (item.getItemName() != null)
                edProductName.setText(item.getItemName());

        }

    }
}
