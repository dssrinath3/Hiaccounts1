package in.hiaccounts.hinext.controlpanel.activity;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.InventoryData;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.LocationId;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.SaveInventoryData;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_OpeningBalance_Inventory extends AppCompatActivity {


    public static String TAG = Activity_OpeningBalance_Inventory.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.edItemName)
    EditText edItemName;
    @BindView(R.id.edItemQty)
    EditText edItemQty;
    @BindView(R.id.edStockValue)
    EditText edStockValue;
    @BindView(R.id.edCalculatedPrice)
    EditText edCalculatedPrice;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.ll_BottmLayout)
    LinearLayout llBottmLayout;
    @BindView(R.id.spinnerLocation)
    Spinner spinnerLocation;

    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    String callingFor, itemCode, serverUrl;
    double caluclutaedPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opnbalance_inventory);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        isInternatePrsenet = serviceHandler.isConnectingToInternet();
        if (isInternatePrsenet) {
            getPageData();
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
        }
        Intent intent = getIntent();

        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null) {
                toolbar.setTitle(callingFor);
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
            edStockValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    double stockvlaue = 0;
                    double itemQty = 0;
                    boolean isValidValue = false;
                    boolean isValidQty = false;
                    if (s.toString() != null && !s.toString().equals("")) {
                        try {
                            edStockValue.setError(null);
                            stockvlaue = Double.parseDouble(s.toString());
                            isValidValue = true;
                        } catch (NumberFormatException ne) {
                            isValidValue = false;
                        }

                        String qty = edItemQty.getText().toString().trim();
                        if (qty != null && !qty.equals("")) {
                            edItemQty.setError(null);
                            try {
                                itemQty = Double.parseDouble(qty);
                                isValidQty = true;
                            } catch (NumberFormatException ne) {
                                isValidQty = false;
                            }
                        } else {
                            itemQty = 0;
                            isValidQty = true;

                        }


                        if (isValidQty && isValidValue) {
                            calculatePrice(stockvlaue, itemQty);
                        } else {
                            if (!isValidQty) {
                                edItemQty.setError(getResources().getString(R.string.error_numberformate));
                            }

                            if (!isValidValue) {
                                edStockValue.setError(getResources().getString(R.string.error_numberformate));
                            }
                        }


                    } else {
                        edCalculatedPrice.setText("0");
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            edItemQty.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    double stockvlaue = 0;
                    double itemQty = 0;
                    boolean isValidValue = false;
                    boolean isValidQty = false;
                    if (s.toString() != null && !s.toString().equals("")) {
                        try {
                            edItemQty.setError(null);
                            itemQty = Double.parseDouble(s.toString());
                            isValidQty = true;
                        } catch (NumberFormatException ne) {
                            isValidQty = false;
                        }

                        String stkVal = edStockValue.getText().toString().trim();
                        if (stkVal != null && !stkVal.equals("")) {
                            edStockValue.setError(null);
                            try {
                                stockvlaue = Double.parseDouble(stkVal);
                                isValidValue = true;
                            } catch (NumberFormatException ne) {
                                isValidValue = false;
                            }
                        } else {
                            stockvlaue = 0;
                            isValidValue = true;

                        }


                        if (isValidQty && isValidValue) {
                            calculatePrice(stockvlaue, itemQty);
                        } else {
                            if (!isValidQty) {
                                edItemQty.setError(getResources().getString(R.string.error_numberformate));
                            }

                            if (!isValidValue) {
                                edStockValue.setError(getResources().getString(R.string.error_numberformate));
                            }
                        }


                    } else {
                        edCalculatedPrice.setText("0");
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }
    }

    private void getPageData() {
        String url = serverUrl + "/hipos//1/addOpeningBalanceItem";

        if (serverUrl != null) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {
                // prepare the Request
                UtilView.showProgessBar(activity, progressBar);
                PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);

                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(activity, Activity_Login.class);
                                startActivity(intent);
                                finish();
                            } else {

                                List<LocationId> locationIdList = new ArrayList<>();
                                Gson gson = new Gson();
                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        LocationId locationId = gson.fromJson(jsonObject.toString(), LocationId.class);
                                        locationIdList.add(locationId);
                                    }
                                    if (locationIdList != null) {
                                        if (llBottmLayout != null)
                                            llBottmLayout.setVisibility(View.VISIBLE);

                                        if (scrollView != null)
                                            scrollView.setVisibility(View.VISIBLE);
                                        UtilView.setupOpnInvLocationAdapter(activity, spinnerLocation, locationIdList);

                                    }
                                } catch (Exception je) {

                                    UtilView.showLogCat(TAG, "getPadaData Exception " + je.getMessage());
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                task.execute(new Gson().toString(), url, "");
                //task.execute(url, "");
            }
            if (!isInternatePrsenet) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }


    }

    public void calculatePrice(double stockvalue, double qty) {


        try {
            caluclutaedPrice = stockvalue / qty;
            edCalculatedPrice.setText("" + String.format("%.2f", caluclutaedPrice));
        } catch (Exception e) {
            edCalculatedPrice.setText("0");
        }


    }

    private void addInventory() {


        String itemName = edItemName.getText().toString().trim();
        String itemQty = edItemQty.getText().toString().trim();
        String stockValue = edStockValue.getText().toString().trim();
        String calculatedPrice = edCalculatedPrice.getText().toString().trim();

        LocationId locationId = (LocationId) spinnerLocation.getSelectedItem();

        UtilView.showLogCat(TAG, new Gson().toJson(locationId));

        if (itemName != null && !itemName.equals("")
                && itemQty != null && !itemQty.equals("")
                && stockValue != null && !stockValue.equals("")
                && calculatedPrice != null && !calculatedPrice.equals("")
                && locationId != null) {
            try {


                SaveInventoryData inventoryData = new SaveInventoryData();
                inventoryData.setItemName(itemName);
                inventoryData.setInitialQty(itemQty);
                inventoryData.setCalculatedPrice(caluclutaedPrice);
                inventoryData.setItemCode(itemCode);
                inventoryData.setLocationId(locationId);
                inventoryData.setOpeningBalance(stockValue);

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_SAVEINVENTORYOPNING;

                if (serverUrl != null) {
                    isInternatePrsenet = serviceHandler.isConnectingToInternet();
                    if (isInternatePrsenet) {
                        // prepare the Request
                        UtilView.showProgessBar(activity, progressBar);
                        PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                UtilView.hideProgessBar(activity, progressBar);

                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(activity, Activity_Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {

                                        if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {

                                            UtilView.showToast(activity, "Item Already Exists");
                                        } else {

                                            Gson gson = new Gson();
                                            InventoryData data = gson.fromJson(result.toString(), InventoryData.class);

                                            if (data != null) {
                                                UtilView.showToast(activity, "Inventory add Successfully");
                                                Intent returnIntent = new Intent();
                                                setResult(Activity.RESULT_OK, returnIntent);
                                                finish();
                                            }
                                        }

                                    }
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                                }
                            }
                        }, false);
                        task.execute(new Gson().toJson(inventoryData).toString(), url, "");
                        //task.execute(url, "");
                    }
                    if (!isInternatePrsenet) {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                    }
                } else {
                    UtilView.gotToLogin(activity);
                }
            } catch (Exception e) {

            }

        } else {
            if (itemName.equals("") || itemName == null) {
                edItemName.setError(getResources().getString(R.string.err_msg));
            }

            if (itemQty.equals("") || itemQty == null) {
                edItemQty.setError(getResources().getString(R.string.err_msg));
            }

            if (stockValue.equals("") || stockValue == null) {
                edStockValue.setError(getResources().getString(R.string.err_msg));
            }
            if (calculatedPrice.equals("") || calculatedPrice == null) {
                edCalculatedPrice.setError(getResources().getString(R.string.err_msg));
            }
            if (locationId == null) {
                TextView tv = (TextView) spinnerLocation.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }

        }


        UtilView.showLogCat(TAG, " " + itemQty);
        UtilView.showLogCat(TAG, " " + stockValue);
        UtilView.showLogCat(TAG, " " + calculatedPrice);


    }


    @OnClick({R.id.edItemName, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btnSave:
                addInventory();
                break;
            case R.id.btnClose:
                finish();
                break;

            case R.id.edItemName:
                if (serverUrl != null) {
                    String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
                    Intent intent = new Intent(activity, Activity_ShowItemList.class);
                    intent.putExtra("url", url);
                    intent.putExtra("taxType", "");
                    intent.putExtra("itemsearch", "");
                    intent.putExtra("callingfrom", Constant.CALL_ADDINVENTORUOPENING_ITEM);
                    startActivityForResult(intent, Constant.RESQUSTCODE_ITEMSEARCH);
                } else {
                    UtilView.gotToLogin(activity);
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == RESULT_OK) {
            SelectedItemsList item = (SelectedItemsList) data.getSerializableExtra("item");

            if (item.getItemCode() != null)
                itemCode = item.getItemCode();

            if (item.getItemName() != null)
                edItemName.setText(item.getItemName());
        }
    }
}
