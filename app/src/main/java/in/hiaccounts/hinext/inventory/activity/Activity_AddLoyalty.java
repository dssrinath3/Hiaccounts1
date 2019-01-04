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
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.inventory.model.loyalty.AddLoyaltyData;
import in.hiaccounts.hinext.inventory.model.loyalty.LoyaltySelectData;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_AddLoyalty extends AppCompatActivity {
    public static String TAG = Activity_AddLoyalty.class.getSimpleName();


    String serverUrl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edLoyaltyProgramName)
    EditText edLoyaltyProgramName;
    @BindView(R.id.llAmount)
    LinearLayout llAmount;
    @BindView(R.id.edFromDate)
    EditText edFromDate;
    @BindView(R.id.llFromDate)
    LinearLayout llFromDate;
    @BindView(R.id.edToDate)
    EditText edToDate;
    @BindView(R.id.llToDate)
    LinearLayout llToDate;
    @BindView(R.id.radioBtnCurrency)
    RadioButton radioBtnCurrency;
    @BindView(R.id.radioBtnOrder)
    RadioButton radioBtnOrder;
    @BindView(R.id.radioBtnItem)
    RadioButton radioBtnItem;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.pointType)
    LinearLayout pointType;
    @BindView(R.id.radioBtnAmount)
    RadioButton radioBtnAmount;
    @BindView(R.id.radioBtnPercentage)
    RadioButton radioBtnPercentage;
    @BindView(R.id.radioGroupAop)
    RadioGroup radioGroupAop;
    @BindView(R.id.id_AmountOFInterest)
    LinearLayout idAmountOFInterest;
    @BindView(R.id.id_LPT_Currency)
    LinearLayout idLPTCurrency;
    @BindView(R.id.edAmOfPerCurrency)
    EditText edAmOfPerCurrency;
    @BindView(R.id.edAmOfPerCurrencyPoint)
    EditText edAmOfPerCurrencyPoint;
    @BindView(R.id.id_AmountOrPerc_Amount)
    LinearLayout idAmountOrPercAmount;
    @BindView(R.id.edAmOfPerPercentage)
    EditText edAmOfPerPercentage;
    @BindView(R.id.ll_AmountOrPerc_Percentage)
    LinearLayout llAmountOrPercPercentage;
    @BindView(R.id.edPerOrderPercentage)
    EditText edPerOrderPercentage;
    @BindView(R.id.edPerOrderPoints)
    EditText edPerOrderPoints;
    @BindView(R.id.id_LPT_Order)
    LinearLayout idLPTOrder;
    @BindView(R.id.edItemName)
    EditText edItemName;
    @BindView(R.id.edPerProductsPoints)
    EditText edPerProductsPoints;
    @BindView(R.id.edMinQty)
    EditText edMinQty;
    @BindView(R.id.id_LPT_Item)
    LinearLayout idLPTItem;
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
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;
    String callingFor,LoyaltyType,AmountOrPercent;
    Long itemID,minqty;
    String fromDate = "", toDate = "";
    LoyaltySelectData loyaltySelectDetails;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private boolean isAllValid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loyalty);
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
                if (callingFor.equals(Constant.CALL_EDITLOYALITY)) {
                    loyaltySelectDetails = (LoyaltySelectData) intent.getSerializableExtra("loyaltyData");
                    if (loyaltySelectDetails != null) {

                        if (loyaltySelectDetails.getLoyaltyProgramName() != null)
                            edLoyaltyProgramName.setText(loyaltySelectDetails.getLoyaltyProgramName().toString());

                        if(loyaltySelectDetails.getLoyaltyType() !=null){

                            if(loyaltySelectDetails.getLoyaltyType().equals("currency")){
                                radioBtnCurrency.setChecked(true);

                                if (loyaltySelectDetails.getAmountOrPercent().equals("amount")){
                                    radioBtnAmount.setChecked(true);
                                    AmountOrPercent = "amount";
                                    idAmountOrPercAmount.setVisibility(View.VISIBLE);
                                    llAmountOrPercPercentage.setVisibility(View.GONE);


                                    if(radioBtnCurrency.isChecked() == true){
                                        idLPTCurrency.setVisibility(View.VISIBLE);
                                        idLPTItem.setVisibility(View.GONE);
                                        idLPTOrder.setVisibility(View.GONE);
                                        LoyaltyType = "currency";
                                        if (radioBtnAmount.isChecked() == true){
                                            AmountOrPercent = "amount";

                                            idAmountOrPercAmount.setVisibility(View.VISIBLE);
                                            llAmountOrPercPercentage.setVisibility(View.GONE);
                                        }
                                    }

                                }
                                else if (loyaltySelectDetails.getAmountOrPercent().equals("percentage")){
                                    radioBtnPercentage.setChecked(true);
                                    AmountOrPercent ="percentage";
                                    idAmountOrPercAmount.setVisibility(View.GONE);
                                    llAmountOrPercPercentage.setVisibility(View.VISIBLE);


                                }
                            }
                            else if(loyaltySelectDetails.getLoyaltyType().equals("order")){
                                radioBtnOrder.setChecked(true);
                                LoyaltyType = "order";
                                idLPTOrder.setVisibility(View.VISIBLE);
                                idLPTCurrency.setVisibility(View.GONE);
                                idLPTItem.setVisibility(View.GONE);
                                idAmountOrPercAmount.setVisibility(View.GONE);
                                llAmountOrPercPercentage.setVisibility(View.GONE);



                            }else if(loyaltySelectDetails.getLoyaltyType().equals("item")){
                                radioBtnItem.setChecked(true);
                                LoyaltyType = "item";
                                idLPTItem.setVisibility(View.VISIBLE);
                                idLPTOrder.setVisibility(View.GONE);
                                idLPTCurrency.setVisibility(View.GONE);
                                idAmountOrPercAmount.setVisibility(View.GONE);
                                llAmountOrPercPercentage.setVisibility(View.GONE);



                                if (loyaltySelectDetails.getPerProductPoints() != null)
                                    edPerProductsPoints.setText(loyaltySelectDetails.getPerProductPoints().toString());


                                edMinQty.setText(loyaltySelectDetails.getMinQty().toString());


                            }

                            if (loyaltySelectDetails.getPerProduct() != null)
                                edItemName.setText(loyaltySelectDetails.getPerProduct());

                            itemID = loyaltySelectDetails.getItemID();


                            edItemName.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    getItemData();
                                }
                            });








                        }

                        if (loyaltySelectDetails.getPerCurrency() != null)
                            edAmOfPerCurrency.setText(loyaltySelectDetails.getPerCurrency().toString());

                        if (loyaltySelectDetails.getPerCurrencyPoints() != null)
                            edAmOfPerCurrencyPoint.setText(loyaltySelectDetails.getPerCurrencyPoints().toString());


                        if (loyaltySelectDetails.getPerOrder() != null)
                            edPerOrderPercentage.setText(loyaltySelectDetails.getPerOrder().toString());


                        if (loyaltySelectDetails.getPerOrderPoints() != null)
                            edPerOrderPoints.setText(loyaltySelectDetails.getPerOrderPoints().toString());

                        if (loyaltySelectDetails.getPercentage() != null)
                            edAmOfPerPercentage.setText(loyaltySelectDetails.getPercentage().toString());

                        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));
                        if (loyaltySelectDetails.getStatus() != null) {
                            if (loyaltySelectDetails.getStatus().equals("Active")) {
                                spinnerStatus.setSelection(0);
                            }

                            if (loyaltySelectDetails.getStatus().equals("InActive")) {
                                spinnerStatus.setSelection(1);
                            }
                        }



                        if (loyaltySelectDetails.getFromDate() != null)
                        {
                            Calendar calendar=Calendar.getInstance();
                            calendar.setTimeInMillis(Long.parseLong(loyaltySelectDetails.getFromDate()));
                            int year = calendar.get(Calendar.YEAR);
                            int month = calendar.get(Calendar.MONTH);
                            final int day = calendar.get(Calendar.DAY_OF_MONTH);
                            String FromDate = String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(day));
                            edFromDate.setText(FromDate);

                            final TimeZone utc = TimeZone.getTimeZone("UTC");
                            final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                            f.setTimeZone(utc);
                            fromDate =f.format(calendar.getTime());
                        }



                        if (loyaltySelectDetails.getToDate() != null)
                        {
                            Calendar calendar1=Calendar.getInstance();
                            calendar1.setTimeInMillis(Long.parseLong(loyaltySelectDetails.getToDate()));
                            int year1 = calendar1.get(Calendar.YEAR);
                            int month1 = calendar1.get(Calendar.MONTH);
                            final int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                            String ToDate = String.valueOf(new StringBuilder().append(year1).append("-").append(month1 + 1).append("-").append(day1));
                            edToDate.setText(ToDate);

                            final TimeZone utc = TimeZone.getTimeZone("UTC");
                            final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                            f.setTimeZone(utc);
                            toDate =f.format(calendar1.getTime());
                        }
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

        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));

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

    }
    @OnClick({R.id.radioBtnCurrency, R.id.radioBtnOrder, R.id.radioBtnItem, R.id.radioBtnAmount, R.id.radioBtnPercentage, R.id.edFromDate, R.id.edToDate, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radioBtnCurrency:
                getCurrency(activity);
                break;
            case R.id.radioBtnOrder:
                getOrder(activity);
                break;
            case R.id.radioBtnItem:
                getItem(activity);
                break;
            case R.id.radioBtnAmount:
                getAmount(activity);
                break;
            case R.id.radioBtnPercentage:
                getPercentage(activity);
                break;
            case R.id.edFromDate:
                getFromDatePicker(activity);
                break;
            case R.id.edToDate:
                getToDatePicker(activity);
                break;
            case R.id.btnSave:
                addLoyalty();
                break;
            case R.id.btnClose:
                finish();
                break;
        }

    }



    private void getPercentage(Activity activity) {
        if (radioBtnPercentage.isChecked() == true){
            idAmountOrPercAmount.setVisibility(View.GONE);
            llAmountOrPercPercentage.setVisibility(View.VISIBLE);
            AmountOrPercent ="percentage";
        }
    }

    private void getAmount(Activity activity) {
        if (radioBtnAmount.isChecked() == true){
            AmountOrPercent = "amount";
            idAmountOrPercAmount.setVisibility(View.VISIBLE);
            llAmountOrPercPercentage.setVisibility(View.GONE);
        }
    }

    private void getItem(Activity activity) {
        if (radioBtnItem.isChecked() == true){
            LoyaltyType = "item";
            idLPTItem.setVisibility(View.VISIBLE);
            idLPTOrder.setVisibility(View.GONE);
            idLPTCurrency.setVisibility(View.GONE);
            idAmountOrPercAmount.setVisibility(View.GONE);
            llAmountOrPercPercentage.setVisibility(View.GONE);
        }


        edItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemData();
            }
        });
    }

    private void getOrder(Activity activity) {

            if (radioBtnOrder.isChecked() == true){
                LoyaltyType = "order";
                idLPTOrder.setVisibility(View.VISIBLE);
                idLPTCurrency.setVisibility(View.GONE);
                idLPTItem.setVisibility(View.GONE);
                idAmountOrPercAmount.setVisibility(View.GONE);
                llAmountOrPercPercentage.setVisibility(View.GONE);
            }



    }

    private void getCurrency(Activity activity) {
     /*  if(radioBtnCurrency.isChecked() == true){
           idLPTCurrency.setVisibility(View.VISIBLE);
           idLPTItem.setVisibility(View.GONE);
           idLPTOrder.setVisibility(View.GONE);
           LoyaltyType = "currency";*/




           if(radioBtnCurrency.isChecked() == true){
               idLPTCurrency.setVisibility(View.VISIBLE);
               idLPTItem.setVisibility(View.GONE);
               idLPTOrder.setVisibility(View.GONE);
               LoyaltyType = "currency";

               //if (loyaltySelectDetails.getAmountOrPercent().equals("amount")){
               /*        radioBtnAmount.setChecked(true);
                       AmountOrPercent = "amount";
                       idAmountOrPercAmount.setVisibility(View.VISIBLE);
                       llAmountOrPercPercentage.setVisibility(View.GONE);*/

               // }
               //  else  if (loyaltySelectDetails.getAmountOrPercent().equals("percentage")){

                   /*    radioBtnPercentage.setChecked(true);
                       AmountOrPercent ="percentage";
                       idAmountOrPercAmount.setVisibility(View.GONE);
                       llAmountOrPercPercentage.setVisibility(View.VISIBLE);

*/
               //}
               // else{
                 /*  if (radioBtnAmount.isChecked() == true){
                       AmountOrPercent = "amount";

                       idAmountOrPercAmount.setVisibility(View.VISIBLE);
                       llAmountOrPercPercentage.setVisibility(View.GONE);
                   }*/
               // }
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

    private void addLoyalty() {
        String loyaltyProgramName = edLoyaltyProgramName.getText().toString().trim();
        String perCurrency = edAmOfPerCurrency.getText().toString().trim();
        String perCurrencyPoint = edAmOfPerCurrencyPoint.getText().toString().trim();
        String percentage = edAmOfPerPercentage.getText().toString().trim();
        String perOrderPercentage = edPerOrderPercentage.getText().toString().trim();
        String perOrderPoints = edPerOrderPoints.getText().toString().trim();
        String itemName = edItemName.getText().toString().trim();
        String perProductPoint = edPerProductsPoints.getText().toString().trim();
        String minQty = edMinQty.getText().toString().trim();


        if (minqty != null && minqty > 0) {

            minqty = Long.parseLong(minQty);
        } else {
            minqty = 0L;
        }

        if (loyaltyProgramName !=null && !loyaltyProgramName.equals("")  && isAllValid) {

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddLoyaltyData loyaltyData = new AddLoyaltyData();
                loyaltyData.setItemID(itemID);
                loyaltyData.setLoyaltyProgramName(loyaltyProgramName);
                loyaltyData.setMinQty(minqty);
                loyaltyData.setPerCurrency(perCurrency);
                loyaltyData.setPerCurrencyPoints(perCurrencyPoint);
                loyaltyData.setPerOrder(perOrderPercentage);
                loyaltyData.setPerOrderPoints(perOrderPoints);
                loyaltyData.setPerProductPoints(perProductPoint);
                loyaltyData.setPerProduct(itemName);
                loyaltyData.setFromDate(fromDate);
                loyaltyData.setToDate(toDate);
                loyaltyData.setPercentage(percentage);
                loyaltyData.setLoyaltyType(LoyaltyType);
                loyaltyData.setAmountOrPercent(AmountOrPercent);
                loyaltyData.setStatus((String) spinnerStatus.getSelectedItem());
                loyaltyData.setType("Loyalty");

                if (callingFor.equals(Constant.CALL_EDITLOYALITY)) {

                    loyaltyData.setLoyaltyId(loyaltySelectDetails.getLoyaltyId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDLOYALITY;

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


                                            Gson gson = new Gson();
                                            try {
                                                JSONObject jsonObject = new JSONObject(result.toString());
                                                AddLoyaltyData addLoyaltyData = gson.fromJson(jsonObject.toString(), AddLoyaltyData.class);

                                                if (addLoyaltyData != null) {

//                                                    if (addLoyaltyData.getLoyaltyId()!=null){
                                                        Intent returnIntent = new Intent();
                                                        activity.setResult(Activity.RESULT_OK, returnIntent);
                                                        activity.finish();
                                                        UtilView.showToast(activity, "Loyalty added successfully.");
                                                    /*}else {
                                                        UtilView.showToast(activity, "Already added.");
                                                    }*/


                                                }else {
                                                    UtilView.showToast(activity, "Something Error.");
                                                }


                                            } catch (Exception e) {
                                                UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                            }

                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                                }
                            }
                        }, false);
                        task.execute(new Gson().toJson(loyaltyData), url, "");

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
            if (loyaltyProgramName.equals("")) {
                edLoyaltyProgramName.setError(getString(R.string.err_msg));
            }
          /*  if (fromDate.equals("")) {
                edFromDate.setError(getString(R.string.err_msg));
            }
            if (toDate.equals("")) {
                edToDate.setError(getString(R.string.err_msg));
            }*/





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
