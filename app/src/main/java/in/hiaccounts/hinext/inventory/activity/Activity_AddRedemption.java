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
import android.widget.Toast;

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
import in.hiaccounts.hinext.inventory.model.redemption.AddRedemptionData;
import in.hiaccounts.hinext.inventory.model.redemption.RedemptionSelectData;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_AddRedemption extends AppCompatActivity {

    public static String TAG = Activity_AddRedemption.class.getSimpleName();


    String serverUrl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edRedemptionProgramName)
    EditText edRedemptionProgramName;
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
    @BindView(R.id.edMaxQty)
    EditText edMaxQty;
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
    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    String callingFor, RedeemType, AmountOrPercent;
    Long itemID, maxqty;
    String fromDate = "", toDate = "";
    private boolean isAllValid = true;
    RedemptionSelectData redemptionSelectDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_redemption);
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
                if (callingFor.equals(Constant.CALL_EDITREDEMPTION)) {
                    redemptionSelectDetails = (RedemptionSelectData) intent.getSerializableExtra("redemptionData");
                    if (redemptionSelectDetails != null) {

                        if (redemptionSelectDetails.getRedeemProgramName() != null)
                            edRedemptionProgramName.setText(redemptionSelectDetails.getRedeemProgramName().toString());

                        if(redemptionSelectDetails.getRedeemType() !=null){
                            if(redemptionSelectDetails.getRedeemType().equals("currency")){
                                radioBtnCurrency.setChecked(true);
                                if (redemptionSelectDetails.getAmountOrPercent().equals("amount")){
                                    radioBtnAmount.setChecked(true);
                                    AmountOrPercent = "amount";
                                    idAmountOrPercAmount.setVisibility(View.VISIBLE);
                                    llAmountOrPercPercentage.setVisibility(View.GONE);


                                    if(radioBtnCurrency.isChecked() == true){
                                        idLPTCurrency.setVisibility(View.VISIBLE);
                                        idLPTItem.setVisibility(View.GONE);
                                        idLPTOrder.setVisibility(View.GONE);
                                        RedeemType = "currency";
                                        if (radioBtnAmount.isChecked() == true){
                                            AmountOrPercent = "amount";

                                            idAmountOrPercAmount.setVisibility(View.VISIBLE);
                                            llAmountOrPercPercentage.setVisibility(View.GONE);
                                        }
                                    }

                                }
                                if (redemptionSelectDetails.getAmountOrPercent().equals("percentage")){
                                    radioBtnAmount.setChecked(true);
                                    AmountOrPercent ="percentage";
                                    idAmountOrPercAmount.setVisibility(View.GONE);
                                    llAmountOrPercPercentage.setVisibility(View.VISIBLE);


                                }
                            }
                            else if(redemptionSelectDetails.getRedeemType().equals("order")){
                                radioBtnOrder.setChecked(true);
                                RedeemType = "order";
                                idLPTOrder.setVisibility(View.VISIBLE);
                                idLPTCurrency.setVisibility(View.GONE);
                                idLPTItem.setVisibility(View.GONE);
                                idAmountOrPercAmount.setVisibility(View.GONE);
                                llAmountOrPercPercentage.setVisibility(View.GONE);



                            }else if(redemptionSelectDetails.getRedeemType().equals("item")){
                                radioBtnItem.setChecked(true);
                                RedeemType = "item";
                                idLPTItem.setVisibility(View.VISIBLE);
                                idLPTOrder.setVisibility(View.GONE);
                                idLPTCurrency.setVisibility(View.GONE);
                                idAmountOrPercAmount.setVisibility(View.GONE);
                                llAmountOrPercPercentage.setVisibility(View.GONE);



                                if (redemptionSelectDetails.getPerProductPoints() != null)
                                    edPerProductsPoints.setText(redemptionSelectDetails.getPerProductPoints().toString());

                                if (redemptionSelectDetails.getMaxQty() != null) {
                                    edMaxQty.setText(redemptionSelectDetails.getMaxQty().toString());
                                }
                                Toast.makeText(activity, ""+redemptionSelectDetails.getMaxQty().toString(), Toast.LENGTH_SHORT).show();


                            }

                            if (redemptionSelectDetails.getPerProduct() != null)
                                edItemName.setText(redemptionSelectDetails.getPerProduct());

                            itemID = redemptionSelectDetails.getItemID();


                            edItemName.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    getItemData();
                                }
                            });

                            if(radioBtnCurrency.isChecked() == true){
                                idLPTCurrency.setVisibility(View.VISIBLE);
                                idLPTItem.setVisibility(View.GONE);
                                idLPTOrder.setVisibility(View.GONE);
                                RedeemType = "currency";
                                if (radioBtnAmount.isChecked() == true){
                                    AmountOrPercent = "amount";

                                    idAmountOrPercAmount.setVisibility(View.VISIBLE);
                                    llAmountOrPercPercentage.setVisibility(View.GONE);
                                }
                            }
                        }

                        if (redemptionSelectDetails.getPerCurrency() != null)
                            edAmOfPerCurrency.setText(redemptionSelectDetails.getPerCurrency().toString());

                        if (redemptionSelectDetails.getPerCurrencyPoints() != null)
                            edAmOfPerCurrencyPoint.setText(redemptionSelectDetails.getPerCurrencyPoints().toString());


                        if (redemptionSelectDetails.getPerOrder() != null)
                            edPerOrderPercentage.setText(redemptionSelectDetails.getPerOrder().toString());


                        if (redemptionSelectDetails.getPerOrderPoints() != null)
                            edPerOrderPoints.setText(redemptionSelectDetails.getPerOrderPoints().toString());

                        if (redemptionSelectDetails.getPercentage() != null)
                            edAmOfPerPercentage.setText(redemptionSelectDetails.getPercentage().toString());




                        if (redemptionSelectDetails.getFromDate() != null)
                        {
                            Calendar calendar=Calendar.getInstance();
                            calendar.setTimeInMillis(Long.parseLong(redemptionSelectDetails.getFromDate()));
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



                        if (redemptionSelectDetails.getToDate() != null)
                        {
                            Calendar calendar1=Calendar.getInstance();
                            calendar1.setTimeInMillis(Long.parseLong(redemptionSelectDetails.getToDate()));
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
                addRedemption();
                break;
            case R.id.btnClose:
                finish();
                break;
        }

    }

    private void addRedemption() {
        String redemptionProgramName = edRedemptionProgramName.getText().toString().trim();
        String perCurrency = edAmOfPerCurrency.getText().toString().trim();
        String perCurrencyPoint = edAmOfPerCurrencyPoint.getText().toString().trim();
        String percentage = edAmOfPerPercentage.getText().toString().trim();
        String perOrderPercentage = edPerOrderPercentage.getText().toString().trim();
        String perOrderPoints = edPerOrderPoints.getText().toString().trim();
        String itemName = edItemName.getText().toString().trim();
        String perProductPoint = edPerProductsPoints.getText().toString().trim();
        String maxQty = edMaxQty.getText().toString().trim();

        maxqty = Long.valueOf(maxQty);

        if (redemptionProgramName !=null && !redemptionProgramName.equals("")  && isAllValid) {

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddRedemptionData redemptionData = new AddRedemptionData();
                redemptionData.setItemID(itemID);
                redemptionData.setRedeemProgramName(redemptionProgramName);
                redemptionData.setMaxQty(maxqty);
                redemptionData.setPerCurrency(perCurrency);
                redemptionData.setPerCurrencyPoints(perCurrencyPoint);
                redemptionData.setPerOrder(perOrderPercentage);
                redemptionData.setPerOrderPoints(perOrderPoints);
                redemptionData.setPerProductPoints(perProductPoint);
                redemptionData.setPerProduct(itemName);
                redemptionData.setFromDate(fromDate);
                redemptionData.setToDate(toDate);
                redemptionData.setPercentage(percentage);
                redemptionData.setRedeemType(RedeemType);
                redemptionData.setAmountOrPercent(AmountOrPercent);



                if (callingFor.equals(Constant.CALL_EDITLOYALITY)) {

                    redemptionData.setRedeemId(redemptionSelectDetails.getRedeemId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDREDEMPTION;

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
                                                AddRedemptionData addRedemData = gson.fromJson(jsonObject.toString(), AddRedemptionData.class);

                                                if (addRedemData != null) {

                                                    Intent returnIntent = new Intent();
                                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                                    activity.finish();
                                                    UtilView.showToast(activity, "Redemption added successfully.");

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
                        task.execute(new Gson().toJson(redemptionData), url, "");

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
            if (redemptionProgramName.equals("")) {
                edRedemptionProgramName.setError(getString(R.string.err_msg));
            }






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
            RedeemType = "item";
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
            RedeemType = "order";
            idLPTOrder.setVisibility(View.VISIBLE);
            idLPTCurrency.setVisibility(View.GONE);
            idLPTItem.setVisibility(View.GONE);
            idAmountOrPercAmount.setVisibility(View.GONE);
            llAmountOrPercPercentage.setVisibility(View.GONE);
        }



    }

    private void getCurrency(Activity activity) {
        if(radioBtnCurrency.isChecked() == true){
            idLPTCurrency.setVisibility(View.VISIBLE);
            idLPTItem.setVisibility(View.GONE);
            idLPTOrder.setVisibility(View.GONE);
            RedeemType = "currency";
            if (radioBtnAmount.isChecked() == true){
                AmountOrPercent = "amount";

                idAmountOrPercAmount.setVisibility(View.VISIBLE);
                llAmountOrPercPercentage.setVisibility(View.GONE);
            }
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
