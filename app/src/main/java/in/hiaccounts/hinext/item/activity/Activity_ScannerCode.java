package in.hiaccounts.hinext.item.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCartItem;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCreator;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosHelper;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCreator;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosHelper;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

import static in.hiaccounts.utility.Constant.NAVIGATION_GROUP_RETAIL;


/**
 * Created by Prateek on 2/27/2017.
 */

public class Activity_ScannerCode extends AppCompatActivity implements ZBarScannerView.ResultHandler {


    public static final int MY_PERMISSIONS_REQUEST_GETCAMERA = 123;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.edSearchItem)
    EditText edSearchItem;
    @BindView(R.id.cameraPreview)
    FrameLayout cameraPreview;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.btnScan)
    Button btnScan;
    @BindView(R.id.llBottomView)
    LinearLayout llBottomView;
    @BindView(R.id.progress_bar)
    ProgressView progressView;
    private SharedPreference sharedPreference;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String callingFrom,serverUrl;
    private Intent intent;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    private ZBarScannerView mScannerView;
    private String searchType = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponets();
    }

    private void initComponets() {
        setContentView(R.layout.activity_item_scanner);
        ButterKnife.bind(this);

        toolbar.setTitle(getResources().getString(R.string.scan_item));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mActivity = this;
        serverUrl=UtilView.getUrl(mActivity);

        serviceHandler = new ServiceHandler(this);
        sharedPreference = SharedPreference.getInstance(this);
        intent = getIntent();
        callingFrom = intent.getStringExtra("callingfrom");

        mScannerView = new ZBarScannerView(this);


    }

    @OnClick({R.id.btnSearch, R.id.btnScan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                setSearchBackground();
                searchItemQuery();
                break;
            case R.id.btnScan:
                setScanBackground();
                scanItemQuery();
                break;
        }
    }

    private void scanItemQuery() {
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            checkPermission();
        } else {
            if (cameraPreview != null) {
                cameraPreview.removeAllViews();
            }
            mScannerView.resumeCameraPreview(Activity_ScannerCode.this);
            cameraPreview.addView(mScannerView);
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();


        }

    }

    private void searchItemQuery() {

        RadioButton rb = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        searchType = rb.getText().toString();
        String code = edSearchItem.getText().toString();
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (code != null && !code.equals("")) {
            if (serverUrl!=null){
            if (isInternetPresent) {
                if (callingFrom != null) {
                    if (callingFrom.equals(NAVIGATION_GROUP_RETAIL)) {

                        if (searchType.equals(getResources().getString(R.string.searchItemCode))) {
                            UtilView.showProgessBar(mActivity, progressView);
                            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEM + "?itemCode=";
                            GetDataTask task = new GetDataTask(this, new GetDataTaskListenr(code), false);
                            task.execute(url + code, "");
                        }
                        if (searchType.equals(getResources().getString(R.string.searchSerialCode))) {
                            UtilView.showProgessBar(mActivity, progressView);
                            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETSERIALITEM + "?itemCode=";
                            GetDataTask task = new GetDataTask(this, new GetDataTaskListenr(code), false);
                            task.execute(url + code, "");
                        }
                    }
                    if (callingFrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                        if (searchType.equals(getResources().getString(R.string.searchItemCode))) {
                            UtilView.showProgessBar(mActivity, progressView);
                            String url =serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEM + "?itemCode=";
                            GetDataTask task = new GetDataTask(this, new GetDataTaskListenr(code), false);
                            task.execute(url + code, "");
                        }
                        if (searchType.equals(getResources().getString(R.string.searchSerialCode))) {
                            UtilView.showProgessBar(mActivity, progressView);
                            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETSERIALITEM + "?itemCode=";
                            GetDataTask task = new GetDataTask(this, new GetDataTaskListenr(code), false);
                            task.execute(url + code, "");
                        }
                    }
                }
            } else {
                UtilView.showToast(mActivity, getResources().getString(R.string.intertnet_status));
            }
            }else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, "Please enter code first");
        }
    }


    void setScanBackground() {

        edSearchItem.setText("");

        btnScan.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        btnScan.setTextColor(getResources().getColor(R.color.colorBlack));

        btnSearch.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        btnSearch.setTextColor(getResources().getColor(R.color.colorWhite));

    }


    void setSearchBackground() {

        if (cameraPreview != null) {
            cameraPreview.removeAllViews();
        }
        btnSearch.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        btnSearch.setTextColor(getResources().getColor(R.color.colorBlack));

        btnScan.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        btnScan.setTextColor(getResources().getColor(R.color.colorWhite));
    }


    @Override
    public void handleResult(final Result rawResult) {

        RadioButton rb = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        searchType = rb.getText().toString();


        UtilView.showLogCat("", "" + rawResult.getContents());
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(Activity_ScannerCode.this);
                if (searchType.equals(getResources().getString(R.string.searchItemCode))) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl!=null) {
                        if (isInternetPresent) {
                            //String url = UtilView.getHiposUrlwithComapnyId(sharedPreference) + "/getItem?itemCode=";
                            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEM + "?itemCode=";
                            UtilView.showProgessBar(mActivity, progressView);
                            GetDataTask task = new GetDataTask(Activity_ScannerCode.this, new GetDataTaskListenr(rawResult.getContents()), false);
                            task.execute(url + rawResult.getContents(), "");
                        } else {
                            UtilView.showToast(mActivity, getResources().getString(R.string.intertnet_status));
                        }
                    }else {
                        UtilView.gotToLogin(mActivity);
                    }

                }
                if (searchType.equals(getResources().getString(R.string.searchSerialCode))) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl!=null) {
                        if (isInternetPresent) {
                            //String url = UtilView.getHiposUrlwithComapnyId(sharedPreference) + "/getItem?itemCode=";
                            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETSERIALITEM + "?itemCode=";
                            UtilView.showProgessBar(mActivity, progressView);
                            GetDataTask task = new GetDataTask(Activity_ScannerCode.this, new GetDataTaskListenr(rawResult.getContents()), false);
                            task.execute(url + rawResult.getContents(), "");
                        } else {
                            UtilView.showToast(mActivity, getResources().getString(R.string.intertnet_status));
                        }
                    }else {
                        UtilView.gotToLogin(mActivity);
                    }

                }

            }
        }, 2000);

    }

    private void checkPermission() {

        String permission = Manifest.permission.CAMERA;
        if (ContextCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(mActivity, new String[]{permission}, MY_PERMISSIONS_REQUEST_GETCAMERA);
            } else {
                ActivityCompat.requestPermissions(mActivity, new String[]{permission}, MY_PERMISSIONS_REQUEST_GETCAMERA);
            }
        } else {
            {
                if (cameraPreview != null) {
                    cameraPreview.removeAllViews();

                }

                cameraPreview.addView(mScannerView);
                mScannerView.setResultHandler(this);
                mScannerView.startCamera();


            }

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(mActivity, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {

                case MY_PERMISSIONS_REQUEST_GETCAMERA:

                    if (cameraPreview != null) {
                        cameraPreview.removeAllViews();
                    }
                    cameraPreview.addView(mScannerView);
                    mScannerView.setResultHandler(this);
                    mScannerView.startCamera();

                    break;
            }


        } else {
            showAlertDialog();
        }
    }

    private void showAlertDialog() {
        SweetAlertDialog pDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.WARNING_TYPE);
        pDialog.setTitleText(getResources().getString(R.string.pemission_denied));
        pDialog.setCancelable(false);
        pDialog.show();

    }


    private class GetDataTaskListenr implements AsyncTaskCompleteListener<String> {
        String serachingSerialNumber;

        public GetDataTaskListenr(String itemSerialcode) {
            serachingSerialNumber = itemSerialcode;
        }


        @Override
        public void onTaskComplete(String result) {
            UtilView.hideProgessBar(mActivity, progressView);
            if (result != null) {
                if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {
                    UtilView.showToast(mActivity, "No Item Found. Try Again");
                } else {
                    Gson gson = new Gson();
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        SalesPosCreator salesPosCreator = SalesPosHelper.getPosCreator();
                        List<SelectedItemsList> selectedList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            selectedList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), SelectedItemsList.class));
                        }
                        if (selectedList.size() > 0) {
                            for (int i = 0; i < 1; i++) {
                                SelectedItemsList itemDatum = selectedList.get(i);
                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                                    in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList salesItem = new in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList();
                                    salesItem.setItemId(itemDatum.getItemId());
                                    salesItem.setItemCode(itemDatum.getItemCode());
                                    salesItem.setItemName(itemDatum.getItemName());
                                    salesItem.setTaxid(itemDatum.getTaxId());
                                    salesItem.setStock(itemDatum.getStock());
                                    salesItem.setDiscountAmountRpercent(itemDatum.getDiscountAmountRpercent());
                                    salesItem.setDiscountInPercent(itemDatum.isDiscountInPercent());
                                    salesItem.setUnitPrice(itemDatum.getSalesPrice());
                                    try {
                                        salesItem.setCess(Double.parseDouble(itemDatum.getCess()));
                                    } catch (NumberFormatException ne) {
                                        salesItem.setCess(0);
                                    }
                                    salesItem.setHsnCode(itemDatum.getHsnCode());
                                    salesItem.setUomName(itemDatum.getUomName());
                                    salesItem.setSerializableStatus(itemDatum.getSerializableStatus());
                                    salesItem.setItemDescription(itemDatum.getItemDesc());
                                    salesItem.setItemTypeName(itemDatum.getItemTypeName());
                                    salesItem.setItemQuantity(1);
                                    salesItem.setAmtexclusivetax(itemDatum.getSalesPrice());
                                    //salesItem.setItemTotalAmount();
                                    salesPosCreator.addItem(new SalesPosCartItem(salesItem, 1));
                                }
                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                                    PurchasePosCreator purchasePosCreator = PurchasePosHelper.getPosCreator();
                                    in.hiaccounts.hinext.purchase.model.purchase_pos.SelectedItemsList purchaseItem = new in.hiaccounts.hinext.purchase.model.purchase_pos.SelectedItemsList();

                                    purchaseItem.setItemId(itemDatum.getItemId());
                                    purchaseItem.setItemCode(itemDatum.getItemCode());
                                    purchaseItem.setItemName(itemDatum.getItemName());
                                    purchaseItem.setTaxid(itemDatum.getTaxid());
                                    purchaseItem.setDiscountAmt(itemDatum.getDiscountAmountRpercent());
                                    purchaseItem.setDiscountInPercent(itemDatum.isDiscountInPercent());

                                    purchaseItem.setUnitPrice(itemDatum.getPurchasePrice());

                                    try {
                                        purchaseItem.setCess(Double.parseDouble(itemDatum.getCess()));
                                    } catch (NumberFormatException ex) {
                                        purchaseItem.setCess(0.0);
                                    }
                                    purchaseItem.setHsnCode(itemDatum.getHsnCode());
                                    purchaseItem.setUomName(itemDatum.getUomName());
                                    purchaseItem.setSerializableStatus(itemDatum.getSerializableStatus());
                                    purchaseItem.setItemDescription(itemDatum.getItemDesc());
                                    purchaseItem.setItemTypeName(itemDatum.getItemTypeName());
                                    purchaseItem.setItemQuantity(1);
                                    purchaseItem.setAmtexclusivetax(itemDatum.getPurchasePrice());


                                        /*purchaseItem.setAmtexclusivetax(itemDatum.getAmtexclusivetax());
                                        purchaseItem.setAmtinclusivetax(itemDatum.getAmtinclusivetax());
                                        purchaseItem.setCessTaxAmt(Double.parseDouble(itemDatum.getCessTaxAmt()));

                                        purchaseItem.setIgTax(Double.parseDouble(itemDatum.getCgstsgstsplitvalues()));
                                        purchaseItem.setItemDescription(itemDatum.getItemDesc());

                                        purchaseItem.setItemQuantity(1);
                                        purchaseItem.setRemainingQty("" + itemDatum.getItemQuantity());
                                        purchaseItem.setItemTotalAmount(itemDatum.getItemTotalAmount());
                                        purchaseItem.setPrice(itemDatum.getPrice());
                                        purchaseItem.setReturnQty(itemDatum.getReturnQty());
                                        purchaseItem.setSelectSerialItem(itemDatum.isSelectSerialItem());
                                        purchaseItem.setSerializableStatus(itemDatum.getSerializableStatus());
                                        purchaseItem.setQty("1");
                                        purchaseItem.setTaxAmountSplit(itemDatum.getTaxpercent());
                                        purchaseItem.setTaxamt(itemDatum.getTaxamt());
                                        purchaseItem.setTaxName(itemDatum.getTaxName());
                                        purchaseItem.setTaxTypeSelection(itemDatum.getTaxTypeSelection());
                                        purchaseItem.setTaxpercent(itemDatum.getTaxpercent());
                                       purchaseItem.setItemTypeName(itemDatum.getItemTypeName());*/


                                    purchasePosCreator.addItem(new PurchasePosCartItem(purchaseItem, 1), purchaseItem.getItemId());

                                }
                            }
                        } else {
                            UtilView.showToast(mActivity, "No Item Found. Try Again");
                        }

                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                            Intent intent = new Intent(mActivity, NavigationDrawer_Activity.class);
                            intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_RETAIL);
                            startActivity(intent);
                            finish();
                        }

                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                            Intent intent = new Intent(mActivity, NavigationDrawer_Activity.class);
                            intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_PURCHASE);
                            startActivity(intent);
                            finish();
                        }
                    } catch (Exception e) {
                        UtilView.showErrorDialog(getResources().getString(R.string.response_error),mActivity);
                    }
                }
            } else {
                UtilView.showToast(mActivity, getResources().getString(R.string.error_null));
            }
        }
    }

}