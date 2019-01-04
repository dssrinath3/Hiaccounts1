package in.hiaccounts.hinext.sales.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lowagie.text.DocumentException;
import com.rey.material.widget.ProgressView;
import com.vistrav.ask.Ask;
import com.vistrav.ask.annotations.AskDenied;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.hinext.checkout.activity.Activity_Checkout;
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.controlpanel.fragment.companysetup.UploadImageInterface;
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.customer.model.CustomerList;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.sales.activity.Activity_CreditNote;
import in.hiaccounts.hinext.sales.activity.Activity_DebitNote;
import in.hiaccounts.hinext.sales.activity.Activity_InvokeDeliveryOrder;
import in.hiaccounts.hinext.sales.activity.Activity_InvokeLoanDeliveryOrder;
import in.hiaccounts.hinext.sales.activity.Activity_InvokeSalesOrder;
import in.hiaccounts.hinext.sales.activity.Activity_InvokeSalesQuotation;
import in.hiaccounts.hinext.sales.activity.Activity_PaymentReceipt;
import in.hiaccounts.hinext.sales.activity.Activity_Printlist_SalesAdvancedPayment;
import in.hiaccounts.hinext.sales.activity.Activity_Printlist_SalesDebitNote;
import in.hiaccounts.hinext.sales.activity.Activity_Printlist_SalesDeliveryOrder;
import in.hiaccounts.hinext.sales.activity.Activity_Printlist_SalesLoanDeliveryOrder;
import in.hiaccounts.hinext.sales.activity.Activity_Printlist_SalesProForma;
import in.hiaccounts.hinext.sales.activity.Activity_Printlist_SalesQuotation;
import in.hiaccounts.hinext.sales.activity.Activity_ReturnDeliveryOrder;
import in.hiaccounts.hinext.sales.activity.Activity_ReturnLoanDeliveryOrder;
import in.hiaccounts.hinext.sales.activity.Activity_SalesAdvancePayment;
import in.hiaccounts.hinext.sales.activity.Activity_SalesDuplicateInvoices;
import in.hiaccounts.hinext.sales.activity.Activity_SalesDuplicateSalesOrder;
import in.hiaccounts.hinext.sales.activity.Activity_SalesDuplicateSalesReturn;
import in.hiaccounts.hinext.sales.activity.Activity_SalesRecievePayment;
import in.hiaccounts.hinext.sales.activity.Activity_SalesReturn;
import in.hiaccounts.hinext.sales.activity.Activity_SalesReturnItemList;
import in.hiaccounts.hinext.sales.adapter.MessageAdapter;
import in.hiaccounts.hinext.sales.adapter.SalesReturn_Adapter;
import in.hiaccounts.hinext.sales.adapter.SalesShowStockItems_Adapter;
import in.hiaccounts.hinext.sales.adapter.Salespos_Adapter;
import in.hiaccounts.hinext.sales.model.checkout.SalesSaveData;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.checkout.UomConvertorList;
import in.hiaccounts.hinext.sales.model.invoke_draft.InvokeDraftData;
import in.hiaccounts.hinext.sales.model.invoke_draft.InvokeDraftList;
import in.hiaccounts.hinext.sales.model.invoke_salesorder.InvokeSalesOrderItem;
import in.hiaccounts.hinext.sales.model.return_posinvoices.Retrun_PosInvocies;
import in.hiaccounts.hinext.sales.model.return_posinvoices.Return_PosInvoiceData;
import in.hiaccounts.hinext.sales.model.sales_invoice.ResponseDataFromServer;
import in.hiaccounts.hinext.sales.model.sales_notifications.CustomerNotificationsList;
import in.hiaccounts.hinext.sales.model.sales_notifications.CustomerNotificationsListData;
import in.hiaccounts.hinext.sales.model.sales_notifications.NotificationData;
import in.hiaccounts.hinext.sales.model.sales_notifications.NotificationListData;
import in.hiaccounts.hinext.sales.model.sales_notifications.SelectPendingNotifications;
import in.hiaccounts.hinext.sales.model.sales_pagedata.CmpylocationList;
import in.hiaccounts.hinext.sales.model.sales_pagedata.CustlocationList;
import in.hiaccounts.hinext.sales.model.sales_pagedata.HinextSalesPageData;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCreator;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosHelper;
import in.hiaccounts.hinext.sales.model.sales_quotation.Sales_QuoationResponseData;
import in.hiaccounts.hinext.sales.model.sales_quotation.Sales_QuotationListData;
import in.hiaccounts.hinext.sales.model.save_salesorder.SaveSalesOrderData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.sales.salesorder.PosA4SalesSaveOrderPdfImpl;
import in.hiaccounts.pdfgenerator.sales.salesorder.PosSalesSaveOrderPdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.BadgeCounter;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.NonScrollListView;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static in.hiaccounts.R.string.response_error;
import static in.hiaccounts.utility.Constant.RESQUSTCODE_CHECKOUT;

/**
 * Created by Prateek on 7/14/2017.
 */

public class Fragment_Sales extends Fragment {
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    public static String TAG = Fragment_Sales.class.getSimpleName();
    private int mNotificationCounter=0;
    public static Fragment_Sales fragment_createPOS;
    public static boolean discountRight = false;
    public static boolean isCheckoutable = true;
    public static int flag = 0;
    public static boolean isAdvancePayment = false;
    public static List<SelectedItemsList> selectItemForDelete = new ArrayList<SelectedItemsList>();
    public static int PERMISSION_ID = 1;
    public static int CAMERA_REQUEST = 1;
    private final CustlocationList[] selectedCustomerLocation = {null};
    private final CmpylocationList[] selectedCompanyLocation = {null};
    public String isReverseChargeInvoice = "";
    @BindView(R.id.edDate)
    EditText edDate;
    @BindView(R.id.btnSelectItem)
    Button btnSelectItem;
    @BindView(R.id.tvSelectCustomer)
    TextView tvSelectCustomer;
    @BindView(R.id.tvSelectTax)
    TextView tvSelectTax;
    @BindView(R.id.spinnerTaxSelection)
    Spinner spinnerTaxSelection;
    @BindView(R.id.spinnerCompanyLocation)
    Spinner spinnerCompanyLocation;
    @BindView(R.id.spinnerCustomerLocation)
    Spinner spinnerCustomerLocation;
    @BindView(R.id.chkbxSelection)
    CheckBox chkbxSelection;
    @BindView(R.id.listviewSelectItems)
    ListView listviewSelectItems;
    @BindView(R.id.list_view_notifications)
    ListView list_view_notifications;
    @BindView(R.id.edTotalAmount)
    EditText edTotalAmount;
    @BindView(R.id.chkBoxAdvancePayments)
    CheckBox chkBoxAdvancePayments;
    @BindView(R.id.chkBoxReverseCharge)
    CheckBox chkBoxReverseCharge;
    @BindView(R.id.btnCheckout)
    Button btnCheckout;
    @BindView(R.id.btnReturnSales)
    Button btnReturnSales;
    @BindView(R.id.ll_total)
    LinearLayout llTotal;
    @BindView(R.id.rlListview)
    RelativeLayout rlListview;
    @BindView(R.id.ll_page)
    LinearLayout llPage;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.background_dimmer)
    View backgroundDimmer;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.fabclearall)
    FloatingActionButton fabclearall;
    @BindView(R.id.fabreturn)
    FloatingActionButton fabreturn;
    @BindView(R.id.fabrecievepayment)
    FloatingActionButton fabrecievepayment;
    @BindView(R.id.fabadvancepayment)
    FloatingActionButton fabadvancepayment;
    @BindView(R.id.fabInvokeSalesOrder)
    FloatingActionButton fabInvokeSalesOrder;
    @BindView(R.id.fabsavesalesorder)
    FloatingActionButton fabsavesalesorder;
    @BindView(R.id.fabScan)
    FloatingActionButton fabScan;
    @BindView(R.id.fabMenus)
    FloatingActionMenu fabMenus;
    Unbinder unbinder;
    EditText edCessAmt;
    EditText edItemName;
    EditText edItemDescritpion;
    EditText edItemUnitPrice;
    EditText edItemQuantity;
    EditText edReturnQuatity;
    LinearLayout llReturnQuantity;
    EditText edItemAmtExTax;
    TextView textView10;
    Spinner spinItemInputtax;
    TextView tvTaxType, tvItemInputtax,tvQuantity;
    EditText edTaxTypeAmount;
    EditText edCessPercantage;
    EditText edItemTotalTaxamt;
    EditText edItemDiscount;
    EditText itemAmtIncTax;
    Spinner spinItemSerialNumber;
    Button btnClose;
    Button btnAdd,btnMore,btnLess;
    LinearLayout llMoreOrLess;
    Boolean isInternetPresent = false;
    boolean[] isPermissionGranted = new boolean[2];
    SalesReturn_Adapter adapter;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    private Checkout_Sales_ResponseData saveSalesOrderResponse = null;
    private Sales_QuoationResponseData saveSalesResponse = null;
    private String selectedTax;
    private SharedPreference sharedPreference;
    private Customer selected_customer = null;
    private Salespos_Adapter cartItemAdapter;
    private String taxString = "",serverUrl;
    private BigDecimal itemTotalamt = BigDecimal.ZERO;
    private BigDecimal totalTaxAmt = BigDecimal.ZERO;
    private BigDecimal cessPercantage = BigDecimal.ZERO;
    private BigDecimal taxPercantage = BigDecimal.ZERO;
    private BigDecimal cgstTaxAmtValue = BigDecimal.ZERO;
    private BigDecimal cessTaxamt = BigDecimal.ZERO;
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private HinextSalesPageData pageData;
    private SalesPosCreator salesposCreator;
    private Return_PosInvoiceData posInoviceData;
    private List<String> orderList = new ArrayList<String>();
    private ArrayList<InvokeDraftList> invokeDraftOrderList = new ArrayList<>();
    private Dialog dialog;
    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();
    private String checkoutType = null;
    private Long quotationId,siid,invokeOrderId;
    private String operationType = "",saleOrderid="" ,selectedDate = "",salesdeliveryorderid="", invoiceDraftName="",salesorderId="",proFormaId="",returnItem="",invokeOrderName="",sino="";
    private SalesSaveData salesReturnData;
    private MenuItem filterMenuItem;
    private List<CustomerNotificationsList> listMessages = new ArrayList<>();
    private MessageAdapter messageAdapter;
    private NotificationData notificationData;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity=(Activity)context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_hinextsales, container, false);

        unbinder = ButterKnife.bind(this, view);
        initComponents(view,savedInstanceState);
        return view;
    }

    private void initComponents(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        serverUrl=UtilView.getUrl(mActivity);
        dialog = new Dialog(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        salesposCreator = SalesPosHelper.getPosCreator();

        //callGetNotifications();

        /*StrictMode.VmPolicy.Builder newbuilder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(newbuilder.build());*/

        visibilityGone();
        callPermission();
        if (fabMenus != null) {
            fabMenus.hideMenuButton(false);
            menus.add(fabMenus);
            int delay = 400;
            for (final FloatingActionMenu menu : menus) {
                mUiHandler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {

                                menu.showMenuButton(true);
                            }
                        }, delay);
                delay += 150;
            }
        }


        selectedDate = UtilView.setCurrentDate(edDate);
        edDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edDate.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        fabMenus.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    backgroundDimmer.setVisibility(View.VISIBLE);
                } else {
                    backgroundDimmer.setVisibility(View.GONE);
                }
            }
        });
        if (savedInstanceState != null) {
            if (pageData != null)
                setupPageData();
        } else {
            getPageLoadData();

        }

        listviewSelectItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editPosItem(position);
                checkCartList("");
            }
        });


    }
/*    private void getPendingNotifications(CustomerNotificationsList customerNotificationsList, int position) {

        listMessages.remove(position);
        messageAdapter.notifyDataSetChanged();
        CustomerNotificationsListData custNotificationData = new Gson().fromJson(customerNotificationsList.getObjectdata().toString(), new TypeToken<CustomerNotificationsListData>() {
        }.getType());

        if (custNotificationData.getJsonData().getSelectedItemsList()!=null && custNotificationData.getJsonData().getSelectedItemsList().size()>0)
                for (int j=0;j<custNotificationData.getJsonData().getSelectedItemsList().size();j++){
                    Log.e("notificationItemData", String.valueOf(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemName()));

                    SelectedItemsList salesItem = new SelectedItemsList();

                    salesItem.setItemCode(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemCode());
                   // salesItem.setItemDescription(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemDescription());
                   *//* salesItem.setItemName(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemName());
                    salesItem.setItemId(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemId());
                    salesItem.setItemQuantity(custNotificationData.getJsonData().getSelectedItemsList().get(j).getQty());
                    salesItem.setRemainingQty(custNotificationData.getJsonData().getSelectedItemsList().get(j).getRemainingQty());
                    salesItem.setItemTotalAmount(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemTotalAmount());
                    salesItem.setPrice(BigDecimal.valueOf(custNotificationData.getJsonData().getSelectedItemsList().get(j).getAmtexclusivetax()));
                    salesItem.setUnitPrice(custNotificationData.getJsonData().getSelectedItemsList().get(j).getUnitPrice());
                    salesItem.setUnitPriceIn(custNotificationData.getJsonData().getSelectedItemsList().get(j).getUnitPriceIn());
                    salesItem.setReturnQty(custNotificationData.getJsonData().getSelectedItemsList().get(j).getReturnQty());
                    salesItem.setSelectSerialItem(false);
                    salesItem.setSerializableStatus(custNotificationData.getJsonData().getSelectedItemsList().get(j).getSerializableStatus());
                    salesItem.setQty(custNotificationData.getJsonData().getSelectedItemsList().get(j).getQty());
                    salesItem.setTaxAmountSplit(custNotificationData.getJsonData().getSelectedItemsList().get(j).getTaxAmountSplit());
                    salesItem.setTaxamt(custNotificationData.getJsonData().getSelectedItemsList().get(j).getTaxamt());
                    salesItem.setTaxName(custNotificationData.getJsonData().getSelectedItemsList().get(j).getTaxName());
                    salesItem.setTaxTypeSelection(custNotificationData.getJsonData().getSelectedItemsList().get(j).getTaxTypeSelection());
                    salesItem.setTaxpercent(custNotificationData.getJsonData().getSelectedItemsList().get(j).getTaxpercent());
                    salesItem.setTaxid(custNotificationData.getJsonData().getSelectedItemsList().get(j).getTaxid());
                    salesItem.setItemTypeName(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemTypeName());
                    salesItem.setAmtexclusivetax(custNotificationData.getJsonData().getSelectedItemsList().get(j).getAmtexclusivetax());
                    salesItem.setItemTotalAmount(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemTotalAmount());
                    salesItem.setAmtinclusivetax(custNotificationData.getJsonData().getSelectedItemsList().get(j).getAmtinclusivetax());
                    salesItem.setCessTaxAmt(custNotificationData.getJsonData().getSelectedItemsList().get(j).getCessTaxAmt());
                    salesItem.setDiscountAmt(custNotificationData.getJsonData().getSelectedItemsList().get(j).getDiscountAmt());
                    salesItem.setDiscountInPercent(false);
                    salesItem.setHsnCode(custNotificationData.getJsonData().getSelectedItemsList().get(j).getHsnCode());
                    salesItem.setUomName(custNotificationData.getJsonData().getSelectedItemsList().get(j).getUomName());
                    List<UomConvertorList> uomConvertorList = new ArrayList<>();
                    UomConvertorList uomData = new UomConvertorList();

                    for(int k=0;k<custNotificationData.getJsonData().getSelectedItemsList().get(j).getUomConvertorList().size();k++){

                        uomData.setUomConvertedName(custNotificationData.getJsonData().getSelectedItemsList().get(j).getUomConvertorList().get(k).getUomConvertedName());
                        uomData.setUomConvertorId(custNotificationData.getJsonData().getSelectedItemsList().get(j).getUomConvertorList().get(k).getUomConvertorId());
                        uomData.setUomValue(custNotificationData.getJsonData().getSelectedItemsList().get(j).getUomConvertorList().get(k).getUomValue());
                        uomConvertorList.add(uomData);
                    }


                    salesItem.setUomConvertorList(uomConvertorList);
                    salesItem.setIgTax(custNotificationData.getJsonData().getSelectedItemsList().get(j).getIgTax());
                    salesItem.setInclusiveJSON(custNotificationData.getJsonData().getSelectedItemsList().get(j).getInclusiveJSON());
                    try {
                        salesItem.setCess(custNotificationData.getJsonData().getSelectedItemsList().get(j).getCess());
                    } catch (NumberFormatException ne) {
                        salesItem.setCess(0);
                    }*//*

                    salesposCreator.addItem(new SalesPosCartItem(salesItem, 1));
                    Log.e("salesposCreator1", String.valueOf(salesposCreator.getItems().size()));

                }

                String url ="http://cloud.hiaccounts.com:8888/hiConnectService/v4/getallpendingnotifications";
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if(url!=null) {

                    if (isInternetPresent) {
                        UtilView.showProgessBar(mActivity, progressBar);
                        PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                UtilView.hideProgessBar(mActivity, progressBar);
                                if (result != null) {
                                    Log.e("AcceptNotification",result.toString());
                                    Gson gson = new Gson();
                                    try {


                                    }catch (Exception e){
                                        Log.e("Notification Result",e.toString());
                                    }


                                } else {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            }


                        }, false);
                        try {
                            JSONObject jsonObject = new JSONObject();
                            if (customerNotificationsList!=null){
                                jsonObject.put("fromRegno", customerNotificationsList.getFromRegno());
                                jsonObject.put("toRegno", customerNotificationsList.getToRegno());
                                //jsonObject.put("user_id", customerNotificationsList.getPiNo());
                                jsonObject.put("typeDoc", customerNotificationsList.getTypeDoc());
                                jsonObject.put("typeFlag", customerNotificationsList.getTypeFlag());
                                jsonObject.put("custNotiId", customerNotificationsList.getCustNotiId());
                                jsonObject.put("status", customerNotificationsList.getViewStatus());
                            }


                            postDataTask.execute(jsonObject.toString(), url, "");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                    }
                }else {
                    UtilView.gotToLogin(mActivity);
                }



    }*/

    @OnClick({R.id.edDate,R.id.chkBoxAdvancePayments,R.id.chkBoxReverseCharge,R.id.btnSelectItem, R.id.tvSelectCustomer, R.id.btnCheckout, R.id.btnReturnSales, R.id.fabclearall, R.id.fabreturn, R.id.fabrecievepayment, R.id.fabadvancepayment, R.id.fabInvokeSalesOrder, R.id.fabsavesalesorder,R.id.fabScan})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {

            case R.id.chkBoxAdvancePayments:
                isAdvancePayment = chkBoxAdvancePayments.isChecked() == true;

                break;
            case R.id.chkBoxReverseCharge:
                if (chkBoxReverseCharge.isChecked() == true) {
                    if (dialog != null) dialog.show();
                    dialog.setContentView(R.layout.dialog_sales_draftalert);
                    final ProgressView progressView = dialog.findViewById(R.id.progress_bar);
                    ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
                    Button btnCancel = dialog.findViewById(R.id.btnCancel);
                    Button btnOk = dialog.findViewById(R.id.btnOk);
                    imgviewClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog != null) dialog.dismiss();
                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog != null) dialog.dismiss();
                        }
                    });
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog != null) dialog.dismiss();

                            Gson gson = new Gson();
                            String hinextpagedatajson = sharedPreference.getData(Constant.HINEXTSALESPAGEDATA_KEY);
                            final HinextSalesPageData pageData = gson.fromJson(hinextpagedatajson.toString(), HinextSalesPageData.class);
                            if (pageData != null) {
                                String taxname = "0.0 | RC-0 | REVERSE CHARGE SUPPLIES WITH GST 0% CHARGED";

                                for (int i = 0; i < pageData.getTaxList().size(); i++) {
                                    if(taxname.equals(pageData.getTaxList().get(i).getTaxString()))
                                    {
                                        isReverseChargeInvoice = pageData.getTaxList().get(i).getTaxString();

                                    }

                                }

                            }

                            btnSalesCheckout();

                        }
                    });





                }
                break;
            case R.id.edDate:
                getDatePicker(mActivity);
                break;
            case R.id.btnSelectItem:
                callItemView();
                break;
            case R.id.tvSelectCustomer:
                intent = new Intent(mActivity, Activity_Customer.class);
                startActivityForResult(intent, Constant.RESQUSTCODE_CUSTOMERS);
                break;
            case R.id.btnCheckout:

                btnSalesCheckout();

                break;
            case R.id.btnReturnSales:


                switch (returnItem){
                    case "Return Item" :
                            initReturnDialog();
                        break;
                    case "Delivery Order" :
                            initReturnCheckout();
                        break;
                    case "Loan Delivery Order" :
                            initReturnCheckout();
                        break;
                }




                break;
            case R.id.fabclearall:
                callClearAll();

                break;
            case R.id.fabreturn:
                invokeOrdersforReturn();
                break;
            case R.id.fabrecievepayment:
                callRecievePayment();

                break;
            case R.id.fabadvancepayment:

                callSalesAdvancePayment();

                break;
            case R.id.fabInvokeSalesOrder:

                callInvokeSalesOrder();
               // invokeSalesOrder();
                break;
            case R.id.fabsavesalesorder:
                callSaveSalesOrder();
                break;

            case R.id.fabScan:
                if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    callPermission();
                }else {
                    takeImageFromCamera();
                }


                break;
        }

    }

    public void callClearAll() {
        salesposCreator.clear();
        checkCartList("");
        fabMenus.close(true);
    }

 /*   private void initSalesReturnItem() {
        int count = 0;
        if (salesposCreator != null && salesposCreator.getItems().size() > 0) {
            for (int i = 0; i < salesposCreator.getItems().size(); i++) {
                SalesPosCartItem pos = salesposCreator.getItems().get(i);
                SelectedItemsList posItem = pos.getItem();
                if (pos.getItem().getReturnQty() == 0) {
                    count = 1;
                }


            }

            if (count == 1) {
                UtilView.showToast(mActivity, "Return Quantity can't be zero");
            } else {
                returnItemSalesCheckout();
            }
        }
    }

    private void returnItemSalesCheckout() {
        if (salesposCreator.getItems().size() > 0) {
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                Gson gson = new Gson();
                                try {

                                    saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_ResponseData.class);
                                    if (saveSalesOrderResponse != null) {
                                        if (saveSalesOrderResponse.getSiData() != null) {
                                            salesposCreator.clear();
                                            checkCartList("");
                                            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                checkPermission();
                                            } else {
                                                createPdf();
                                            }
                                        }
                                    } else {
                                        UtilView.showToast(mActivity, "Save Sales Quotation not placed. Please try again.");
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);

                    postDataTask.execute(saveSalesOrderPostData("","Return", selected_customer), serverUrl + "/retail//" + Constant.FUNTION_SAVESALESRETURNITEM, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.cartempty));
        }
    }*/

    private void btnSalesCheckout() {
        if (salesposCreator.getItems() != null && salesposCreator.getItems().size() > 0) {
            final String saveData = saveposData("multiPayment", "", selected_customer);
            String invokeOrderId = "";
            String url = serverUrl + "/retail//" + Constant.FUNTION_VALIDATECHECKOUT + "?invokeOrderId=" + invokeOrderId;
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (result != null) {

                                UtilView.hideProgessBar(mActivity, progressBar);
                                try {
                                    JSONArray jsonArray = new JSONArray(result);
                                    if (jsonArray.length() > 0) {
                                        JSONArray itemsJsonArray = new JSONArray(result);
                                        List<SelectedItemsList> itemsList = new ArrayList<>();
                                        itemsList.clear();
                                        for (int i = 0; i < itemsJsonArray.length(); i++) {
                                            Gson gson = new Gson();
                                            SelectedItemsList item = gson.fromJson(itemsJsonArray.getJSONObject(i).toString(), SelectedItemsList.class);
                                            itemsList.add(item);

                                        }
                                        final Dialog dialog = new Dialog(mActivity);
                                        dialog.setContentView(R.layout.dialog_stockitems);
                                        NonScrollListView listview_items;
                                        listview_items = dialog.findViewById(R.id.listview_items);
                                        ArrayList<Object> objectList = new ArrayList<>();
                                        objectList.addAll(itemsList);
                                        LayoutInflater inflater = mActivity.getLayoutInflater();
                                        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_showstockitems, listview_items, false);
                                        listview_items.addHeaderView(header);
                                        SalesShowStockItems_Adapter itemAdapter = new SalesShowStockItems_Adapter(mActivity, objectList);
                                        listview_items.setAdapter(itemAdapter);
                                        itemAdapter.notifyDataSetChanged();
                                        Button dialogButton = dialog.findViewById(R.id.btn_close);
                                        ImageView imageview_close = dialog.findViewById(R.id.imageview_close);
                                        dialogButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (dialog != null)
                                                    dialog.dismiss();
                                            }
                                        });
                                        imageview_close.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if (dialog != null)
                                                    dialog.dismiss();
                                            }
                                        });
                                        if (dialog != null)
                                            dialog.show();
                                    } else {
                                        Intent intent = new Intent(mActivity, Activity_Checkout.class);
                                        intent.putExtra("saveData", saveData);
                                        intent.putExtra("checkoutType", "SalesCheckout");
                                        intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_RETAIL);
                                        mActivity.startActivityForResult(intent, RESQUSTCODE_CHECKOUT);
                                    }
                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        }


                    }, false);
                    postDataTask.execute(saveData, url, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, "Please Add Item first.");
        }
    }

    private void initReturnCheckout() {
        int count = 0;


        if (salesposCreator != null && salesposCreator.getItems().size() > 0) {
            for (int i = 0; i < salesposCreator.getItems().size(); i++) {
                SalesPosCartItem pos = salesposCreator.getItems().get(i);
                SelectedItemsList posItem = pos.getItem();
                if (pos.getItem().getReturnQty() == 0) {
                    count = 1;
                }


            }

            if (count == 1) {
                UtilView.showToast(mActivity, "Return Quantity can't be zero");
            } else {
                returnCheckout();
            }
        }


    }

    private void returnCheckout() {
        if (salesposCreator.getItems().size() > 0) {
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                Gson gson = new Gson();
                                try {

                                    saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                    if (saveSalesOrderResponse != null) {
                                        if (saveSalesOrderResponse.getSiData() != null) {
                                            salesposCreator.clear();
                                            checkCartList("");
                                            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                checkPermission();
                                            } else {
                                                createSalesPdf();
                                            }
                                        }
                                    } else {
                                        UtilView.showToast(mActivity, "Save Sales Return not placed. Please try again.");
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);

                    postDataTask.execute(saveSalesOrderPostData("","Return", selected_customer), serverUrl + "/retail//" + Constant.FUNTION_SAVESALESRETURNDELIVERYORDER, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.cartempty));
        }
    }

    public void callDraftInvoice(){
        if (salesposCreator.getItems() != null && salesposCreator.getItems().size() > 0) {
            final String saveData = saveposData("multiPayment", "", selected_customer);
            String invokeOrderId = "";
            String url = serverUrl + "/retail//" + Constant.FUNTION_VALIDATECHECKOUT + "?invokeOrderId=" + invokeOrderId;
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (result != null) {

                                UtilView.hideProgessBar(mActivity, progressBar);
                                try {
                                    JSONArray jsonArray = new JSONArray(result);
                                    if (jsonArray.length() > 0) {
                                        JSONArray itemsJsonArray = new JSONArray(result);
                                        List<SelectedItemsList> itemsList = new ArrayList<>();
                                        itemsList.clear();
                                        for (int i = 0; i < itemsJsonArray.length(); i++) {
                                            Gson gson = new Gson();
                                            SelectedItemsList item = gson.fromJson(itemsJsonArray.getJSONObject(i).toString(), SelectedItemsList.class);
                                            itemsList.add(item);

                                        }
                                        final Dialog dialog = new Dialog(mActivity);
                                        dialog.setContentView(R.layout.dialog_stockitems);
                                        NonScrollListView listview_items;
                                        listview_items = dialog.findViewById(R.id.listview_items);
                                        ArrayList<Object> objectList = new ArrayList<>();
                                        objectList.addAll(itemsList);
                                        LayoutInflater inflater = mActivity.getLayoutInflater();
                                        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_showstockitems, listview_items, false);
                                        listview_items.addHeaderView(header);
                                        SalesShowStockItems_Adapter itemAdapter = new SalesShowStockItems_Adapter(mActivity, objectList);
                                        listview_items.setAdapter(itemAdapter);
                                        itemAdapter.notifyDataSetChanged();
                                        Button dialogButton = dialog.findViewById(R.id.btn_close);
                                        ImageView imageview_close = dialog.findViewById(R.id.imageview_close);
                                        dialogButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (dialog != null)
                                                    dialog.dismiss();
                                            }
                                        });
                                        imageview_close.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if (dialog != null)
                                                    dialog.dismiss();
                                            }
                                        });
                                        if (dialog != null)
                                            dialog.show();
                                    } else {
                                        Intent intent = new Intent(mActivity, Activity_Checkout.class);
                                        intent.putExtra("saveData", saveData);
                                        intent.putExtra("checkoutType", Constant.SALESDRAFTINVOICE);
                                        intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_RETAIL);
                                        mActivity.startActivityForResult(intent, RESQUSTCODE_CHECKOUT);
                                    }
                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        }


                    }, false);
                    postDataTask.execute(saveData, url, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, "Please Add Item first.");
        }
    }

    public void callSalesReturnItem(){
        Intent intent = new Intent(mActivity, Activity_SalesReturnItemList.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_SALESRETURNITEM);
    }

    public void callExportInvoice(){

        if (salesposCreator.getItems() != null && salesposCreator.getItems().size() > 0) {
            final String saveData = saveposData("multiPayment", "", selected_customer);
            String invokeOrderId = "";
            String url = serverUrl + "/retail//" + Constant.FUNTION_VALIDATECHECKOUT + "?invokeOrderId=" + invokeOrderId;
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (result != null) {

                                UtilView.hideProgessBar(mActivity, progressBar);
                                try {
                                    JSONArray jsonArray = new JSONArray(result);
                                    if (jsonArray.length() > 0) {
                                        JSONArray itemsJsonArray = new JSONArray(result);
                                        List<SelectedItemsList> itemsList = new ArrayList<>();
                                        itemsList.clear();
                                        for (int i = 0; i < itemsJsonArray.length(); i++) {
                                            Gson gson = new Gson();
                                            SelectedItemsList item = gson.fromJson(itemsJsonArray.getJSONObject(i).toString(), SelectedItemsList.class);
                                            itemsList.add(item);

                                        }
                                        final Dialog dialog = new Dialog(mActivity);
                                        dialog.setContentView(R.layout.dialog_stockitems);
                                        NonScrollListView listview_items;
                                        listview_items = dialog.findViewById(R.id.listview_items);
                                        ArrayList<Object> objectList = new ArrayList<>();
                                        objectList.addAll(itemsList);
                                        LayoutInflater inflater = mActivity.getLayoutInflater();
                                        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_showstockitems, listview_items, false);
                                        listview_items.addHeaderView(header);
                                        SalesShowStockItems_Adapter itemAdapter = new SalesShowStockItems_Adapter(mActivity, objectList);
                                        listview_items.setAdapter(itemAdapter);
                                        itemAdapter.notifyDataSetChanged();
                                        Button dialogButton = dialog.findViewById(R.id.btn_close);
                                        ImageView imageview_close = dialog.findViewById(R.id.imageview_close);
                                        dialogButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (dialog != null)
                                                    dialog.dismiss();
                                            }
                                        });
                                        imageview_close.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if (dialog != null)
                                                    dialog.dismiss();
                                            }
                                        });
                                        if (dialog != null)
                                            dialog.show();
                                    } else {
                                        Intent intent = new Intent(mActivity, Activity_Checkout.class);
                                        intent.putExtra("saveData", saveData);
                                        intent.putExtra(Constant.EXPORTINVOICE, true);
                                        intent.putExtra("checkoutType", "SalesCheckout");
                                        intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_RETAIL);
                                        mActivity.startActivityForResult(intent, RESQUSTCODE_CHECKOUT);
                                    }
                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        }


                    }, false);
                    postDataTask.execute(saveData, url, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, "Please Add Item first.");
        }
    }

    public void callSalesAdvancePayment() {
        fabMenus.close(true);
        if (selected_customer != null) {
            Intent intent = new Intent(mActivity, Activity_SalesAdvancePayment.class);
            intent.putExtra("customer", selected_customer);
            intent.putExtra("selectedTax", selectedTax);
            intent.putExtra("selectedDate", selectedDate);
            mActivity.startActivity(intent);
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.selectCustomerError));
        }
    }

    public void callInvokeSalesOrder() {
        if (fabMenus != null)
            fabMenus.close(true);
        Intent intent = new Intent(mActivity, Activity_InvokeSalesOrder.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_INVOKESALESORDER);

    }

    private void callPermission() {
        try {
            Ask.on(mActivity)
                    .id(PERMISSION_ID) // in case you are invoking multiple time Ask from same activity or fragment
                    .forPermissions(WRITE_EXTERNAL_STORAGE,
                            CAMERA)
                    .withRationales(getResources().getString(R.string.permission_write_storage),
                            getResources().getString(R.string.permission_camera)) //optional
                    .go();
        }catch (Exception e){

        }
    }

    //optional
    @AskDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void writefileAccessDenied(int id) {
        isPermissionGranted[0] = false;
    }

    //optional
    @AskDenied(Manifest.permission.CAMERA)
    public void cameraAccessDenied(int id) {
        isPermissionGranted[1] = false;
    }

    private void takeImageFromCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);




    }

    private void takeImageFromGallery() {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setType("image/*");
        startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
    }

    private void invokeOrdersforReturn() {
        fabMenus.close(true);
        if (serverUrl!=null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getOrdersTAsk = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        final ArrayList<Retrun_PosInvocies> posInoviceList = new ArrayList<>();
                        if (result != null) {
                            try {
                                if (posInoviceList.size() > 0) {
                                    posInoviceList.clear();
                                }
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String invoiceJsonString = jsonArray.getString(i);
                                    Gson gson = new Gson();
                                    Retrun_PosInvocies returnPosInvoice = gson.fromJson(invoiceJsonString, Retrun_PosInvocies.class);
                                    posInoviceList.add(returnPosInvoice);
                                }
                                if (posInoviceList.size() > 0) {

                                    final Dialog dialog = new Dialog(mActivity);
                                    dialog.setContentView(R.layout.dialog_sales_retuninvoiceorders);
                                    dialog.setCancelable(false);
                                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                                    ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
                                    Button btnClose = dialog.findViewById(R.id.btnClose);
                                    EditText edSearch = dialog.findViewById(R.id.edSearch);
                                    final ProgressView progressBar1 = dialog.findViewById(R.id.progress_bar);
                                    final ListView lvPosInvocies = dialog.findViewById(R.id.lvPosInvocies);
                                    LayoutInflater inflater = mActivity.getLayoutInflater();
                                    ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_salesreturn, null, false);
                                    TextView tvFormNumber = header.findViewById(R.id.tvFormNumber);
                                    TextView tvCustomerName = header.findViewById((R.id.tvCustomerName));
                                    TextView tvTotalAmount = header.findViewById((R.id.tvTotalAmount));
                                    tvFormNumber.setText(getResources().getString(R.string.formnumber));
                                    tvCustomerName.setText(getResources().getString(R.string.customername));
                                    tvTotalAmount.setText(getResources().getString(R.string.totalamount));

                                    adapter = new SalesReturn_Adapter(mActivity, posInoviceList);
                                    lvPosInvocies.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();

                                    edSearch.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            if (s.toString() !=null && !s.toString().equals("")) {
                                                adapter.getFilter().filter(s.toString());

                                            } else {
                                                // Do something when there's no input
                                                adapter = new SalesReturn_Adapter(mActivity, posInoviceList);
                                                lvPosInvocies.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();
                                            }
                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {

                                        }
                                    });
/*
                                    UtilView.setTextAppearanceSmall(mActivity, tvFormNumber);
                                    UtilView.setTextAppearanceSmall(mActivity, tvCustomerName);
                                    UtilView.setTextAppearanceSmall(mActivity, tvTotalAmount);
                                    UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvFormNumber);
                                    UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCustomerName);
                                    UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvTotalAmount);
                                    UtilView.setTexttypeFace(null, Typeface.BOLD, tvFormNumber);
                                    UtilView.setTexttypeFace(null, Typeface.BOLD, tvCustomerName);
                                    UtilView.setTexttypeFace(null, Typeface.BOLD, tvTotalAmount);
                                    lvPosInvocies.addHeaderView(header);
*/

                                    lvPosInvocies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                           /* if (position == 0) {
                                            } else {*/
                                            isInternetPresent = serviceHandler.isConnectingToInternet();
                                            if (serverUrl != null) {
                                                if (isInternetPresent) {
                                                    if (progressBar1 != null)
                                                        progressBar1.setVisibility(View.VISIBLE);
                                                    String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESINVOICE_RETURN + "/" + posInoviceList.get(position).getFormNo();
                                                    GetDataTask getSalesOrderDetail = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                                        @Override
                                                        public void onTaskComplete(String result) {
                                                            if (result != null) {
                                                                if (progressBar1 != null)
                                                                    progressBar1.setVisibility(View.GONE);
                                                                try {
                                                                    Gson gson = new Gson();
                                                                    JSONObject jsonObject = new JSONObject(result.toString());
                                                                    UtilView.showLogCat(TAG, "Result " + result.toString());
                                                                    salesposCreator.clear();
                                                                    posInoviceData = gson.fromJson(jsonObject.toString(), Return_PosInvoiceData.class);
                                                                    if (posInoviceData.getSelectedItemsList() != null && posInoviceData.getSelectedItemsList().size() > 0) {
                                                                        for (int i = 0; i < posInoviceData.getSelectedItemsList().size(); i++) {
                                                                            in.hiaccounts.hinext.sales.model.return_posinvoices.SelectedItemsList item = posInoviceData.getSelectedItemsList().get(i);
                                                                            SelectedItemsList invokeItems = new SelectedItemsList();
                                                                            invokeItems.setItemName(item.getItemName());
                                                                            invokeItems.setItemId(item.getItemId());
                                                                            invokeItems.setUnitPrice(item.getUnitPrice());
                                                                            invokeItems.setQty(item.getQty());
                                                                            invokeItems.setItemQuantity(item.getQty());
                                                                            invokeItems.setReturnQty(item.getReturnQty());
                                                                            invokeItems.setRemainingQty(item.getRemainingQty());
                                                                            invokeItems.setAmtexclusivetax(item.getAmtexclusivetax());
                                                                            invokeItems.setTaxid(item.getTaxid());
                                                                            invokeItems.setTaxpercent("" + item.getTaxpercent());
                                                                            invokeItems.setTaxamt(item.getTaxamt());
                                                                            invokeItems.setAmtinclusivetax(item.getAmtinclusivetax());
                                                                            invokeItems.setDiscountAmt(item.getDiscountAmt());
                                                                            invokeItems.setItemDescription(item.getItemDescription());
                                                                            invokeItems.setTaxName(item.getTaxName());
                                                                            invokeItems.setItemCode(item.getItemCode());
                                                                            invokeItems.setCess(item.getCess());
                                                                            invokeItems.setTaxAmountSplit("" + item.getTaxPercentageSplit());
                                                                            invokeItems.setCessTaxAmt(item.getCessTaxAmt());
                                                                            invokeItems.setHsnCode(item.getHsnCode());
                                                                            invokeItems.setUomName(item.getUomName());
                                                                            invokeItems.setItemTotalAmount(item.getAmtinclusivetax());
                                                                            invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                                                                            salesposCreator.addItem(new SalesPosCartItem(invokeItems, 1));
                                                                        }
                                                                        if (dialog != null)
                                                                            dialog.dismiss();
                                                                        checkCartList(Constant.OPERATION_RETURN);
                                                                    } else {
                                                                        UtilView.showToast(mActivity, "No Items for the slected Order Number");
                                                                    }
                                                                } catch (JSONException e) {
                                                                    UtilView.showLogCat(TAG, "GetSalesOrderDetailListener Exception " + e.toString());
                                                                }
                                                            } else {
                                                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                                            }
                                                        }
                                                    }, false);
                                                    getSalesOrderDetail.execute(url, "");
                                                } else {
                                                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                                                }
                                            } else {
                                                UtilView.gotToLogin(mActivity);
                                            }
                                            // }
                                        }
                                    });

                                    imgviewClose.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (dialog != null)
                                                dialog.dismiss();
                                        }
                                    });

                                    btnClose.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (dialog != null)
                                                dialog.dismiss();
                                        }
                                    });
                                    if (dialog != null)
                                        dialog.show();

                                } else {
                                    UtilView.showToast(mActivity, "No Sales Order avaialble.");
                                }
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }
                }, false);
                getOrdersTAsk.execute(serverUrl + "/retail//" + Constant.FUNTION_GETINVOICES, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    public void callInvokeQuotation() {

        Intent intent = new Intent(mActivity, Activity_InvokeSalesQuotation.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_INVOKEQUOTATION);
    }

    public void callSaveQuotation() {

        if (salesposCreator.getItems().size() > 0) {
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                Gson gson = new Gson();
                                try {

                                saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                if (saveSalesOrderResponse != null) {
                                    if (saveSalesOrderResponse.getSiData() != null) {
                                        salesposCreator.clear();
                                        checkCartList("");
                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            createSalesPdf();
                                        }
                                    }
                                } else {
                                    UtilView.showToast(mActivity, "Save Sales Quotation not placed. Please try again.");
                                }

                               } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);

                    postDataTask.execute(saveSalesOrderPostData("","SO", selected_customer), serverUrl + "/retail//" + Constant.FUNTION_SAVESALESQUOTATION, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.cartempty));
        }
    }

    public void callSaveProForma() {

        if (salesposCreator.getItems().size() > 0) {
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                Gson gson = new Gson();
                                try {

                                    saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                    if (saveSalesOrderResponse != null) {
                                        if (saveSalesOrderResponse.getSiData() != null) {
                                            salesposCreator.clear();
                                            checkCartList("");
                                            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                checkPermission();
                                            } else {
                                                createSalesPdf();
                                            }
                                        }
                                    } else {
                                        UtilView.showToast(mActivity, "Save Sales Pro Forma not placed. Please try again.");
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);

                    postDataTask.execute(saveSalesOrderPostData("","SO", selected_customer), serverUrl + "/retail//" + Constant.FUNTION_SAVESALESPROFORMA, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.cartempty));
        }
    }

    public void callInvokeProForma() {
        fabMenus.close(true);
        if (isInternetPresent) {
            UtilView.showProgessBar(mActivity, progressBar);

            String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESSELECTINVOKEPROFORMA + "/?itemSearchText=";

            GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                @Override
                public void onTaskComplete(String result) {
                    fabMenus.close(true);
                    if (result != null) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        try {
                            if (orderList.size() > 0) {
                                orderList.clear();
                            }
                            JSONArray jsonArray = new JSONArray(result.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json = jsonArray.getJSONObject(i);
                                Gson gson = new Gson();
                                Sales_QuotationListData paymentInvoice = gson.fromJson(json.toString(), Sales_QuotationListData.class);
                                orderList.add(paymentInvoice.getFormNo());
                            }


                            if (orderList.size() > 0) {
                                final Dialog dialog = new Dialog(mActivity);
                                dialog.setContentView(R.layout.dialog_sales_invokesalesproforma);
                                dialog.setCancelable(false);
                                ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
                                Button btnClose = dialog.findViewById(R.id.btnClose);
                                Spinner spinnerSalesProFormaNo = dialog.findViewById(R.id.spinProForma);
                                final ProgressView progressBar1 = dialog.findViewById(R.id.progress_bar);
                                String[] orderListArray = new String[orderList.size()];


                                for (int i = 0; i < orderList.size(); i++) {
                                    orderListArray[i] = orderList.get(i);
                                }
                                UtilView.setupOrderListAdapter(mActivity, spinnerSalesProFormaNo, orderListArray);
                                spinnerSalesProFormaNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        final String salesProFormaNo = (String) adapterView.getSelectedItem();
                                        if (i == 0) {

                                        } else {
                                            isInternetPresent = serviceHandler.isConnectingToInternet();
                                            if (serverUrl!=null) {
                                                if (isInternetPresent) {
                                                    if (progressBar1 != null)
                                                        progressBar1.setVisibility(View.VISIBLE);
                                                    String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESINVOKEPROFORMA + "?quotationNo=" + salesProFormaNo;
                                                    PostDataTask getSalesOrderDetail = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                                        @Override
                                                        public void onTaskComplete(String result) {
                                                            if (result != null) {
                                                                if (progressBar1 != null)
                                                                    progressBar1.setVisibility(View.GONE);
                                                                if (result != null) {
                                                                    if (result.equals("invalid")) {
                                                                        UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                                                        Intent intent = new Intent(mActivity, Activity_Login.class);
                                                                        mActivity.startActivity(intent);
                                                                        mActivity.finish();
                                                                    } else {


                                                                        salesposCreator.clear();
                                                                        Gson gson = new Gson();
                                                                        try {
                                                                            SalesSaveData orderData = gson.fromJson(result.toString(), SalesSaveData.class);
                                                                            if (orderData!=null){

                                                                                if (orderData.getSelectedItemsList()!=null && orderData.getSelectedItemsList().size()>0){
                                                                                    for (int i=0;i<orderData.getSelectedItemsList().size();i++){
                                                                                        SelectedItemsList item = orderData.getSelectedItemsList().get(i);
                                                                                        SelectedItemsList invokeItems = new SelectedItemsList();
                                                                                        invokeItems.setItemName(item.getItemName());
                                                                                        invokeItems.setItemId(item.getItemId());
                                                                                        invokeItems.setUnitPrice(item.getUnitPrice());
                                                                                        invokeItems.setQty(item.getQty());
                                                                                        invokeItems.setItemQuantity(item.getQty());
                                                                                        invokeItems.setReturnQty(item.getReturnQty());
                                                                                        invokeItems.setRemainingQty(item.getRemainingQty());
                                                                                        invokeItems.setAmtexclusivetax(item.getAmtexclusivetax());
                                                                                        invokeItems.setTaxid(item.getTaxid());
                                                                                        invokeItems.setTaxpercent(String.valueOf(item.getTaxpercent()));
                                                                                        invokeItems.setTaxamt(item.getTaxamt());
                                                                                        invokeItems.setAmtinclusivetax(item.getAmtinclusivetax());
                                                                                        invokeItems.setDiscountAmt(item.getDiscountAmt());
                                                                                        invokeItems.setItemDescription(item.getItemDescription());
                                                                                        invokeItems.setTaxName(item.getTaxName());
                                                                                        invokeItems.setItemCode(item.getItemCode());
                                                                                        invokeItems.setCess(item.getCess());
                                                                                        invokeItems.setTaxAmountSplit("" + item.getTaxPercentageSplit());
                                                                                        invokeItems.setCessTaxAmt(item.getCessTaxAmt());
                                                                                        invokeItems.setHsnCode(item.getHsnCode());
                                                                                        invokeItems.setUomName(item.getUomName());
                                                                                        invokeItems.setInclusiveJSON(item.getInclusiveJSON());
                                                                                        invokeItems.setSerializableStatus(item.getSerializableStatus());
                                                                                        invokeItems.setItemTotalAmount(item.getAmtinclusivetax());
                                                                                        invokeItems.setUomConvertorList(item.getUomConvertorList());
                                                                                        invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                                                                                        invokeItems.setSalesQuotationId(item.getSalesQuotationId());
                                                                                        invokeItems.setSalesQuotationDetailsId(item.getSalesQuotationDetailsId());
                                                                                        salesposCreator.addItem(new SalesPosCartItem(invokeItems, 1));
                                                                                    }
                                                                                }

                                                                                if (dialog != null)
                                                                                    dialog.dismiss();

                                                                                checkCartList("");


                                                                            } else {
                                                                                UtilView.showToast(mActivity, "No Sales Pro Forma Available");
                                                                            }

                                                                        } catch (Exception e) {
                                                                            UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                                                        }

                                                                    }
                                                                } else {
                                                                    progressBar.setVisibility(View.GONE);
                                                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                                                }
                                                            } else {
                                                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                                            }
                                                        }
                                                    }, false);
                                                    JSONObject json = new JSONObject();
                                                    getSalesOrderDetail.execute(json.toString(), url, "");
                                                } else {
                                                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                                                }

                                            }else {
                                                UtilView.gotToLogin(mActivity);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                                imgviewClose.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (dialog != null)
                                            dialog.dismiss();
                                    }
                                });
                                btnClose.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (dialog != null)
                                            dialog.dismiss();
                                    }
                                });
                                if (dialog != null)
                                    dialog.show();
                            } else {
                                UtilView.showToast(mActivity, "No Sales Order avaialble.");
                            }
                        } catch (Exception e) {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    } else {
                        UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                    }
                }
            }, false);
            if (selected_customer != null) {
                if (selected_customer.getCustomerId() != 0) {
                    if (serverUrl!=null) {
                        getDataTask.execute(url, "");
                    }else {
                        UtilView.gotToLogin(mActivity);
                    }
                }
            }
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
        }
    }

    private void invokeSalesOrder() {
        fabMenus.close(true);
        if (isInternetPresent) {
            UtilView.showProgessBar(mActivity, progressBar);
            PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                @Override
                public void onTaskComplete(String result) {
                    fabMenus.close(true);
                    if (result != null) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        try {
                            if (orderList.size() > 0) {
                                orderList.clear();
                            }
                            JSONArray jsonArray = new JSONArray(result.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                orderList.add(jsonArray.getString(i));
                            }
                            if (orderList.size() > 0) {
                                final Dialog dialog = new Dialog(mActivity);
                                dialog.setContentView(R.layout.dialog_sales_invokesalesorder);
                                dialog.setCancelable(false);
                                ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
                                Button btnClose = dialog.findViewById(R.id.btnClose);
                                Spinner spinnerSalesOrderNo = dialog.findViewById(R.id.spinnerSalesOrderNumber);
                                final ProgressView progressBar1 = dialog.findViewById(R.id.progress_bar);
                                String[] orderListArray = new String[orderList.size()];
                                for (int i = 0; i < orderList.size(); i++) {
                                    orderListArray[i] = orderList.get(i);
                                }
                                UtilView.setupOrderListAdapter(mActivity, spinnerSalesOrderNo, orderListArray);
                                spinnerSalesOrderNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        final String salesOrderNo = (String) adapterView.getSelectedItem();
                                        if (i == 0) {

                                        } else {
                                            isInternetPresent = serviceHandler.isConnectingToInternet();
                                            if (serverUrl!=null) {
                                                if (isInternetPresent) {
                                                    if (progressBar1 != null)
                                                        progressBar1.setVisibility(View.VISIBLE);
                                                    String url = serverUrl + "/retail//" + Constant.FUNTION_GETINVOKESALESORDER + "/" + salesOrderNo;
                                                    PostDataTask getSalesOrderDetail = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                                        @Override
                                                        public void onTaskComplete(String result) {
                                                            if (result != null) {
                                                                if (progressBar1 != null)
                                                                    progressBar1.setVisibility(View.GONE);
                                                                try {
                                                                    Gson gson = new Gson();
                                                                    JSONArray jsonArray = new JSONArray(result.toString());
                                                                    salesposCreator.clear();
                                                                    if (jsonArray.length() > 0) {
                                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                                            InvokeSalesOrderItem item = gson.fromJson(jsonObject.toString(), InvokeSalesOrderItem.class);
                                                                            SelectedItemsList invokeItems = new SelectedItemsList();
                                                                            invokeItems.setItemName(item.getItemName());
                                                                            invokeItems.setItemId(item.getItemId());
                                                                            invokeItems.setUnitPrice(item.getUnitPrice());
                                                                            invokeItems.setQty(item.getQty());
                                                                            invokeItems.setItemQuantity(item.getQty());
                                                                            invokeItems.setReturnQty(item.getReturnQty());
                                                                            invokeItems.setRemainingQty(item.getRemainingQty());
                                                                            invokeItems.setAmtexclusivetax(item.getAmtexclusivetax());
                                                                            invokeItems.setTaxid(item.getTaxid());
                                                                            invokeItems.setTaxpercent("" + item.getTaxpercent());
                                                                            invokeItems.setTaxamt(item.getTaxamt());
                                                                            invokeItems.setAmtinclusivetax(item.getAmtinclusivetax());
                                                                            invokeItems.setDiscountAmt(item.getDiscountAmt());
                                                                            invokeItems.setItemDescription(item.getItemDescription());
                                                                            invokeItems.setTaxName(item.getTaxName());
                                                                            invokeItems.setItemCode(item.getItemCode());
                                                                            invokeItems.setCess(item.getCess());
                                                                            invokeItems.setTaxAmountSplit("" + item.getTaxPercentageSplit());
                                                                            invokeItems.setCessTaxAmt(item.getCessTaxAmt());
                                                                            invokeItems.setHsnCode(item.getHsnCode());
                                                                            invokeItems.setUomName(item.getUomName());
                                                                            invokeItems.setItemTotalAmount(item.getAmtinclusivetax());
                                                                            invokeItems.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                                                                            invokeItems.setSalesOrderId(item.getSalesOrderId());
                                                                            invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                                                                            salesposCreator.addItem(new SalesPosCartItem(invokeItems, 1));
                                                                        }
                                                                        if (dialog != null)
                                                                            dialog.dismiss();
                                                                        checkoutType = Constant.CHECKOUTYPE_INVOKESALESORDER;
                                                                        checkCartList("");
                                                                    } else {
                                                                        UtilView.showToast(mActivity, "No Items for the slected Order Number");
                                                                    }
                                                                } catch (JSONException e) {
                                                                    UtilView.showLogCat(TAG, "GetSalesOrderDetailListener Exception " + e.toString());
                                                                }
                                                            } else {
                                                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                                            }
                                                        }
                                                    }, false);
                                                    JSONObject json = new JSONObject();
                                                    getSalesOrderDetail.execute(json.toString(), url, "");
                                                } else {
                                                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                                                }

                                            }else {
                                                UtilView.gotToLogin(mActivity);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                                imgviewClose.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (dialog != null)
                                            dialog.dismiss();
                                    }
                                });
                                btnClose.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (dialog != null)
                                            dialog.dismiss();
                                    }
                                });
                                if (dialog != null)
                                    dialog.show();
                            } else {
                                UtilView.showToast(mActivity, "No Sales Order avaialble.");
                            }
                        } catch (Exception e) {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    } else {
                        UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                    }
                }
            }, false);
            if (selected_customer != null) {
                if (selected_customer.getCustomerId() != 0) {
                    if (serverUrl!=null) {
                        postDataTask.execute(new JsonObject().toString(), serverUrl + "/retail//" + Constant.FUNTION_GETSALESINVOKEORDER + "/?itemSearchText=", "");
                    }else {
                        UtilView.gotToLogin(mActivity);
                    }
                }
            }
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
        }
    }

    public void callSaveSalesOrder() {
        fabMenus.close(true);
        if (salesposCreator.getItems().size() > 0) {
            if (serverUrl!=null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            Gson gson = new Gson();
                            try {
                                saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                if (saveSalesOrderResponse != null) {
                                    salesposCreator.clear();
                                    checkCartList("");
                                    if (saveSalesOrderResponse.getSiData() != null) {
                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            createSalesPdf();
                                        }
                                    } else {
                                        UtilView.showToast(mActivity, "Save Sales Order doesn't save. Please try again");
                                    }
                                } else {
                                    UtilView.showLogCat(TAG, "Some Error");
                                }
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        }
                    }, false);
                    postDataTask.execute(saveSalesOrderPostData("", "SO", selected_customer), serverUrl + "/retail//" + Constant.FUNTION_SAVESALESORDER, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            }else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.cartempty));
        }
    }

    public void callValidateDeliveryOrder() {
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);

                        if (result != null) {

                            if(!result.toString().isEmpty() && result.toString() !=null){
                                callSaveDeliveryOrder();
                            }else{

                                Gson gson = new Gson();
                                try {

                                    saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                    if (saveSalesOrderResponse != null) {

                                    } else {
                                        Toast.makeText(mActivity, "No Stock Avialble.", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }


                            }



                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                        }


                    }
                }, false);


                    postDataTask.execute(saveSalesOrderPostData("", "SO", selected_customer), serverUrl + "/retail//" + Constant.FUNTION_DELIVERYORDERCHECKOUTVALIDATE+"?invokeOrderId=", "");





            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }

    }

    public void callValidateLoanDeliveryOrder() {
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);

                        if (result != null) {

                            if(!result.toString().isEmpty() && result.toString() !=null){
                                callSaveLoanDeliveryOrder();
                            }else{

                                Gson gson = new Gson();
                                try {

                                    saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                    if (saveSalesOrderResponse != null) {

                                    } else {
                                        Toast.makeText(mActivity, "No Stock Avialble.", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }


                            }



                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                        }


                    }
                }, false);


                postDataTask.execute(saveSalesOrderPostData("", "SO", selected_customer), serverUrl + "/retail//" + Constant.FUNTION_DELIVERYORDERCHECKOUTVALIDATE+"?invokeOrderId=", "");





            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }

    }

    private void callSaveLoanDeliveryOrder() {
        fabMenus.close(true);
        if (salesposCreator.getItems().size() > 0) {
            if (serverUrl!=null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            Gson gson = new Gson();
                            try {
                                saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                if (saveSalesOrderResponse != null) {
                                    salesposCreator.clear();
                                    checkCartList("");
                                    if (saveSalesOrderResponse.getSiData() != null) {
                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            createSalesPdf();
                                        }
                                    } else {
                                        UtilView.showToast(mActivity, "Save Delivery Order doesn't save. Please try again");
                                    }
                                } else {
                                    UtilView.showLogCat(TAG, "Some Error");
                                }
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        }
                    }, false);
                    postDataTask.execute(saveSalesOrderPostData("", "SO", selected_customer), serverUrl + "/retail//" + Constant.FUNTION_SAVEDELIVERYORDER+"?type=Loan", "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            }else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.cartempty));
        }
    }

    public void callSaveDeliveryOrder() {
        fabMenus.close(true);
        if (salesposCreator.getItems().size() > 0) {
            if (serverUrl!=null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            Gson gson = new Gson();
                            try {
                                saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                if (saveSalesOrderResponse != null) {
                                    salesposCreator.clear();
                                    checkCartList("");
                                    if (saveSalesOrderResponse.getSiData() != null) {
                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            createSalesPdf();
                                        }
                                    } else {
                                        UtilView.showToast(mActivity, "Save Delivery Order doesn't save. Please try again");
                                    }
                                } else {
                                    UtilView.showLogCat(TAG, "Some Error");
                                }
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        }
                    }, false);
                    postDataTask.execute(saveSalesOrderPostData("", "SO", selected_customer), serverUrl + "/retail//" + Constant.FUNTION_SAVEDELIVERYORDER+"?type=Normal", "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            }else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.cartempty));
        }
    }

    public void callInvokeDeliveryOrder() {

        Intent intent = new Intent(mActivity, Activity_InvokeDeliveryOrder.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_INVOKEDELIVERYORDER);
    }

    public void callInvokeLoanDeliveryOrder() {

        Intent intent = new Intent(mActivity, Activity_InvokeLoanDeliveryOrder.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_INVOKELOANDELIVERYORDER);
    }

    public void callReturnDeliveryOrder() {

        Intent intent = new Intent(mActivity, Activity_ReturnDeliveryOrder.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_RETURNDELIVERYORDER);
    }
    public void callReturnLoanDeliveryOrder() {

        Intent intent = new Intent(mActivity, Activity_ReturnLoanDeliveryOrder.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_RETURNLOANDELIVERYORDER);
    }
    private void checkPermission() {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (ContextCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(mActivity, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(mActivity, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {

            if (saveSalesOrderResponse != null) {
                createSalesPdf();
            }


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(mActivity, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                    if (saveSalesOrderResponse != null) {
                        createSalesPdf();
                    }

                    break;
            }
        } else {
            Toast.makeText(mActivity, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }


    private void createSalesPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(saveSalesOrderResponse.getSoNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SAVESALESORDER);
        if (pdfFile != null) {
            InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);
            if (logoInputStream != null) {
                try {
                    PosSalesSaveOrderPdf invoicePdf = new PosA4SalesSaveOrderPdfImpl();
                    invoicePdf.generateSalesSaveOrder(mActivity, saveSalesOrderResponse, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(saveSalesOrderResponse.getSoNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SAVESALESORDER);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosSalesSaveOrderPdf invoicePdf = new PosA4SalesSaveOrderPdfImpl();
                    invoicePdf.generateSalesSaveOrder(mActivity, saveSalesOrderResponse, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(saveSalesOrderResponse.getSoNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SAVESALESORDER);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }
/*    private void createPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(saveSalesResponse.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);

            if (logoInputStream != null) {
                try {
                    PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                    invoicePdf.generateTaxInvoiceForQuoattion(mActivity, saveSalesResponse, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(saveSalesResponse.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                    invoicePdf.generateTaxInvoiceForQuoattion(mActivity, saveSalesResponse, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(saveSalesResponse.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    public void openPdfInvoice(String fileName, String groupDirectory, String childDirectory) {
        File file = PdfUtils.getFile(fileName, groupDirectory, childDirectory);
        if (file != null) {
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setDataAndType(Uri.fromFile(file), "application/pdf");
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            try {
                startActivityForResult(target, 101);
            } catch (ActivityNotFoundException e) {
                UtilView.showToast(mActivity, "Your device is compatible for view Pdf File.");
            }
        } else {
            UtilView.showToast(mActivity, "No Invoice generated.");
        }

    }


    private void initReturnDialog() {
        int count = 0;


            List<SelectedItemsList> retutnItemList = new ArrayList<>();

            if (salesposCreator != null && salesposCreator.getItems().size() > 0) {
                for (int i = 0; i < salesposCreator.getItems().size(); i++) {
                    SalesPosCartItem pos = salesposCreator.getItems().get(i);
                    SelectedItemsList posItem = pos.getItem();
                    if (pos.getItem().getReturnQty() == 0) {
                        count = 1;
                    }

                    SelectedItemsList retutnItem = new SelectedItemsList();
                    retutnItem.setAmtexclusivetax(posItem.getAmtexclusivetax());
                    retutnItem.setAmtinclusivetax(posItem.getAmtinclusivetax());
                    retutnItem.setCess(posItem.getCess());
                    retutnItem.setCessTaxAmt(posItem.getCessTaxAmt());
                    retutnItem.setDiscountAmt(posItem.getDiscountAmt());
                    retutnItem.setHsnCode(posItem.getHsnCode());
                    retutnItem.setIgTax(posItem.getIgTax());
                    retutnItem.setItemCode(posItem.getItemCode());
                    retutnItem.setItemDescription(posItem.getItemDescription());
                    retutnItem.setItemId(posItem.getItemId());
                    retutnItem.setItemName(posItem.getItemName());
                    retutnItem.setQty(posItem.getQty());
                    retutnItem.setReturnQty(posItem.getReturnQty());
                    retutnItem.setRemainingQty(posItem.getRemainingQty());
                    retutnItem.setSalesOrderDetailsId(posItem.getSalesOrderDetailsId());
                    retutnItem.setSalesOrderId(posItem.getSalesOrderId());
                    retutnItem.setTaxAmountSplit("" + posItem.getTaxAmountSplit());
                    retutnItem.setTaxamt(posItem.getTaxamt());
                    retutnItem.setTaxid(posItem.getTaxid());
                    retutnItem.setTaxpercent("" + posItem.getTaxpercent());
                    retutnItem.setTaxName(posItem.getTaxName());
                    retutnItem.setUomName(posItem.getUomName());
                    retutnItem.setItemTotalAmount(posItem.getAmtinclusivetax());
                    retutnItem.setUnitPrice(posItem.getUnitPrice());
                    retutnItem.setUnitPriceIn(posItem.getUnitPrice());
                    retutnItem.setUom(posItem.getUom());
                    retutnItem.setUomConvertorList(posItem.getUomConvertorList());
                    retutnItem.setPrice(BigDecimal.valueOf(posItem.getUnitPrice()));

                    retutnItemList.add(retutnItem);

                }
                salesReturnData.setSelectedItemsList(retutnItemList);
                salesReturnData.setAmountReturned(String.valueOf(salesposCreator.getTotalPrice()));
                if (count == 1) {
                    UtilView.showToast(mActivity, "Return Quantity can't be zero");
                } else {
                    salesReturnData.setDateOfInvoice(selectedDate);
                    salesReturnData.setCmpyLocation(selectedCompanyLocation[0].getInventoryLocationId());
                    Intent intent = new Intent(mActivity, Activity_SalesReturn.class);
                    intent.putExtra("returnInvoiceData", salesReturnData);
                    startActivityForResult(intent, Constant.RESQUSTCODE_SALESRETURNCHECKOUT);
                }
            }


    }
    private void visibilityGone() {
        if (llTotal != null)
            llTotal.setVisibility(View.GONE);
        if (listviewSelectItems != null)
            listviewSelectItems.setVisibility(View.GONE);
        if (rlListview!=null){
            rlListview.setVisibility(View.GONE);
        }
    }

    private void visibilityVisible() {
        if (rlListview!=null){
            rlListview.setVisibility(View.VISIBLE);
        }
        if (listviewSelectItems != null)
            listviewSelectItems.setVisibility(View.VISIBLE);
        if (llTotal != null)
            llTotal.setVisibility(View.VISIBLE);
        if (fabMenus != null)
            fabMenus.close(true);

    }

    public void showTotalPrice() {
        Double posCartTotal = 0.00;
        for (int i = 0; i < salesposCreator.getItems().size(); i++) {
            posCartTotal += salesposCreator.getItems().get(i).getItem().getItemTotalAmount();
        }
        edTotalAmount.setText(String.valueOf(new BigDecimal(posCartTotal).setScale(2, BigDecimal.ROUND_HALF_UP)));
        if (llTotal != null)
            llTotal.setVisibility(View.VISIBLE);
        salesposCreator.setTotalPrice(posCartTotal);
    }

    public String getTaxString() {
        return taxString;
    }

    public void setTaxString(String taxString) {
        this.taxString = taxString;
    }

    private void editPosItem(final int position) {
        Gson gson = new Gson();
        String hinextpagedatajson = sharedPreference.getData(Constant.HINEXTSALESPAGEDATA_KEY);
        final HinextSalesPageData pageData = gson.fromJson(hinextpagedatajson.toString(), HinextSalesPageData.class);
       /* if (position == 0) {
        } else {*/
        final Dialog dialog = new Dialog(mActivity);
        dialog.setContentView(R.layout.dialog_sales_positem);
        dialog.setCancelable(false);
        btnMore= dialog.findViewById(R.id.btnMore);
        btnLess= dialog.findViewById(R.id.btnLess);
        llMoreOrLess= dialog.findViewById(R.id.llMoreOrLess);

        llReturnQuantity= dialog.findViewById(R.id.llReturnQuantity);
        edReturnQuatity = dialog.findViewById(R.id.edReturnQuatity);
        edCessAmt = dialog.findViewById(R.id.edCessAmt);
        edItemTotalTaxamt = dialog.findViewById(R.id.edItemTotalTaxamt);
        spinItemSerialNumber = dialog.findViewById(R.id.spinItemSerialNumber);
        edItemName = dialog.findViewById(R.id.edItemName);
        edItemDescritpion = dialog.findViewById(R.id.edItemDescritpion);
        edItemUnitPrice = dialog.findViewById(R.id.edItemUnitPrice);
        edItemQuantity = dialog.findViewById(R.id.edItemQuantity);
        edItemAmtExTax = dialog.findViewById(R.id.edItemAmtExTax);
        spinItemInputtax = dialog.findViewById(R.id.spinItemInputtax);
        tvTaxType = dialog.findViewById(R.id.tvTaxType);
        edCessPercantage = dialog.findViewById(R.id.edCessPercantage);
        edItemDiscount = dialog.findViewById(R.id.edItemDiscount);
        itemAmtIncTax = dialog.findViewById(R.id.itemAmtIncTax);
        edTaxTypeAmount = dialog.findViewById(R.id.edTaxTypeAmount);
        btnAdd = dialog.findViewById(R.id.btnAdd);
        btnClose = dialog.findViewById(R.id.btnClose);
        tvItemInputtax = dialog.findViewById(R.id.tvItemInputtax);
        tvQuantity= dialog.findViewById(R.id.tvQuantity);

        btnAdd.setText("Edit");
        SalesPosCartItem posCartItem = salesposCreator.getItems().get(position);
        final SelectedItemsList itemDatum = posCartItem.getItem();
        if (itemDatum.getSerializableStatus() != null) {
            if (itemDatum.getSerializableStatus().equals(Constant.ITEM_BULK)) {
                edItemQuantity.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                edItemQuantity.setFocusableInTouchMode(true);
                edItemQuantity.setFocusable(true);
            }
            if (itemDatum.getSerializableStatus().equals(Constant.ITEM_SERIALIZABLE)) {
                edItemQuantity.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                edItemQuantity.setFocusableInTouchMode(false);
                edItemQuantity.setFocusable(false);
            }
        }

        if (operationType.equals(Constant.OPERATION_RETURN)) {
            edItemTotalTaxamt.setFocusableInTouchMode(false);
            edItemTotalTaxamt.setFocusable(false);
            edItemTotalTaxamt.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            spinItemInputtax.setVisibility(View.GONE);
            tvItemInputtax.setVisibility(View.VISIBLE);
            edItemName.setFocusableInTouchMode(false);
            edItemName.setFocusable(false);
            edItemName.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            edItemUnitPrice.setFocusableInTouchMode(false);
            edItemUnitPrice.setFocusable(false);
            edItemUnitPrice.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            edItemAmtExTax.setFocusableInTouchMode(false);
            edItemAmtExTax.setFocusable(false);
            edItemAmtExTax.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            tvTaxType.setFocusableInTouchMode(false);
            tvTaxType.setFocusable(false);
            tvTaxType.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            edCessPercantage.setFocusableInTouchMode(false);
            edCessPercantage.setFocusable(false);
            edCessPercantage.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            edItemDiscount.setFocusableInTouchMode(false);
            edItemDiscount.setFocusable(false);
            edItemDiscount.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            itemAmtIncTax.setFocusableInTouchMode(false);
            itemAmtIncTax.setFocusable(false);
            itemAmtIncTax.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            edTaxTypeAmount.setFocusableInTouchMode(false);
            edTaxTypeAmount.setFocusable(false);
            edTaxTypeAmount.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            edReturnQuatity.setText("" + itemDatum.getItemQuantity());
            llReturnQuantity.setVisibility(View.VISIBLE);
            tvQuantity.setText("Return Quantity");
            edItemQuantity.setText(""+  itemDatum.getReturnQty());
        }else {
            llReturnQuantity.setVisibility(View.GONE);
            tvQuantity.setText("Quantity");
            edItemQuantity.setText("" + itemDatum.getItemQuantity());
        }
        edItemName.setText(itemDatum.getItemName());
        edItemDescritpion.setText(itemDatum.getItemDescription());


        BigDecimal unitPrice = new BigDecimal(itemDatum.getUnitPrice());
        edItemUnitPrice.setText("" + unitPrice.setScale(2,BigDecimal.ROUND_HALF_UP));
        BigDecimal itemAmtExTax = new BigDecimal(itemDatum.getAmtexclusivetax());
        edItemAmtExTax.setText("" + itemAmtExTax.setScale(2,BigDecimal.ROUND_HALF_UP));
        edTaxTypeAmount.setText("" + itemDatum.getIgTax());
        edCessAmt.setText("" + itemDatum.getCessTaxAmt());
        tvTaxType.setText(selectedTax);
        BigDecimal taxAmt = new BigDecimal(itemDatum.getIgTax());
        if (selectedTax.equals(getResources().getString(R.string.igst))) {
            edTaxTypeAmount.setText("" + taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if (selectedTax.equals(getResources().getString(R.string.cgst))) {
            BigDecimal bgTax = taxAmt.divide(BigDecimal.valueOf(2));
            edTaxTypeAmount.setText("" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP) + "+" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        edCessPercantage.setText("" + itemDatum.getCess());
        edItemTotalTaxamt.setText("" + itemDatum.getTaxamt());
        edItemDiscount.setText("" + itemDatum.getDiscountAmt());
        itemAmtIncTax.setText("" + itemDatum.getAmtinclusivetax());
        if (dialog != null)
            dialog.show();
        String defaultTax = "";
        if (pageData != null) {
            if (pageData.getTaxList().size() > 0) {
                UtilView.setupHinextSalesTaxAdapter(mActivity, spinItemInputtax, pageData.getTaxList());
                for (int i = 0; i < pageData.getTaxList().size(); i++) {
                    if (itemDatum.getTaxid() == (pageData.getTaxList().get(i).getTaxid())) {
                        spinItemInputtax.setSelection(i);
                        defaultTax = pageData.getTaxList().get(i).getTaxString();
                        tvItemInputtax.setText(defaultTax);
                        setTaxString(defaultTax);
                        String cessString = edCessPercantage.getText().toString();
                        if (cessString.equals("") || cessString == null) {
                            cessString = "0";
                        } else {
                            cessString = edCessPercantage.getText().toString().trim();
                        }
                        updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                    }
                }
            }
        }
        spinItemInputtax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinItemInputtax.setSelection(i);
                itemDatum.setTaxid(Long.valueOf(pageData.getTaxList().get(i).getTaxid()));
                String tax = pageData.getTaxList().get(i).getTaxString();
                setTaxString(tax);
                String cessString = edCessPercantage.getText().toString();
                if (cessString.equals("") || cessString == null) {
                    cessString = "0";
                } else {
                    cessString = edCessPercantage.getText().toString().trim();
                }
                updateTaxAmount(getTax(tax, itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        edItemQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    edItemQuantity.setError("Quantity can't be empty.");
                    isCheckoutable = false;
                    flag = 1;
                } else {
                    try {
                        int quantity = Integer.parseInt(s.toString());
                        if (operationType.equals(Constant.OPERATION_RETURN)) {
                            if (quantity == 0 || quantity > itemDatum.getItemQuantity()) {
                                if (quantity == 0) {
                                    isCheckoutable = false;
                                    flag = 1;
                                    edItemQuantity.setError("Quantity can't be zero.");
                                }
                                if (quantity > itemDatum.getItemQuantity()) {
                                    isCheckoutable = false;
                                    flag = 9;
                                    edItemQuantity.setError("Return Quantity should be lest than or equal to quantity.");
                                }
                            } else {
                                edItemQuantity.setError(null);
                                isCheckoutable = true;
                                flag = 0;
                                String cessString = edCessPercantage.getText().toString();
                                if (cessString.equals("") || cessString == null) {
                                    cessString = "0";
                                } else {
                                    cessString = edCessPercantage.getText().toString().trim();
                                }
                                try {
                                    updateByQuantityTotalExTax(itemDatum, quantity);

                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "Quantity " + e.fillInStackTrace().toString());
                                }
                                updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                            }
                        } else {
                            if (quantity == 0) {
                                isCheckoutable = false;
                                flag = 1;
                                edItemQuantity.setError("Quantity can't be zero.");
                            } else {
                                edItemQuantity.setError(null);
                                isCheckoutable = true;
                                flag = 0;
                                String cessString = edCessPercantage.getText().toString();
                                if (cessString.equals("") || cessString == null) {
                                    cessString = "0";
                                } else {
                                    cessString = edCessPercantage.getText().toString().trim();
                                }
                                try {
                                    updateByQuantityTotalExTax(itemDatum, quantity);
                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "Quantity " + e.fillInStackTrace().toString());
                                }
                                updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                            }
                        }
                    } catch (NumberFormatException e) {
                        UtilView.showToast(mActivity, "Enter only digits.");
                        isCheckoutable = false;
                        flag = 3;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edItemUnitPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    edItemAmtExTax.setText("0");
                    itemAmtIncTax.setText("0");
                    edTaxTypeAmount.setText("0");
                    edItemDiscount.setText("0");
                    edItemUnitPrice.setError("Unit price can't be empty.");
                    flag = 4;
                    isCheckoutable = false;
                } else {
                    try {
                        if (s.toString().equals("0.00")) {
                            flag = 4;
                            isCheckoutable = false;

                        } else {
                            edItemUnitPrice.setError(null);
                            flag = 0;
                            isCheckoutable = true;
                            updateItemTotalExTax(itemDatum, new BigDecimal(s.toString()));
                            String cessString = edCessPercantage.getText().toString();
                            if (cessString.equals("") || cessString == null) {
                                cessString = "0";
                            } else {
                                cessString = edCessPercantage.getText().toString().trim();
                            }
                            updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                            String amtEx = edItemAmtExTax.getText().toString().trim();
                            String discountPrice = edItemDiscount.getText().toString().trim();
                            double amtExc = Double.parseDouble(amtEx);
                            double amTax = Double.parseDouble(String.valueOf(totalTaxAmt));
                            double discount = 0;
                            if (discountPrice != null && !discountPrice.equals("")) {
                                if (flag == 5) {
                                } else {
                                    discount = Double.parseDouble(discountPrice);
                                }
                            }
                            if ((amtExc + amTax) > discount) {
                                edItemDiscount.setError(null);
                                updateDiscountPrice(itemDatum, discount);
                                flag = 0;
                                isCheckoutable = true;
                            } else {
                                if (discount != 0) {
                                    flag = 7;
                                    isCheckoutable = false;
                                    edItemDiscount.setError("Amount should be less than total amount.");
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        flag = 5;
                        isCheckoutable = false;
                        edItemUnitPrice.setError("Must be numeric value with only one .");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edCessPercantage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    edCessPercantage.setError(null);
                    isCheckoutable = true;
                    flag = 0;
                    cessPercantage = BigDecimal.ZERO;
                    updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, cessPercantage);
                } else {
                    try {
                        isCheckoutable = true;
                        flag = 0;
                        edCessPercantage.setError(null);
                        cessPercantage = getCessPercantage(s.toString());
                        updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, cessPercantage);
                    } catch (NumberFormatException e) {
                        edCessPercantage.setError("Must be numeric value with only one .");
                        isCheckoutable = false;
                        flag = 8;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edItemDiscount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {

                    String discount;
                    if (s.toString().equals("")) {
                        discount = "0";
                    } else {
                        discount = s.toString().trim();
                    }
                    String cessString = edCessPercantage.getText().toString();
                    if (cessString.equals("") || cessString == null) {
                        cessString = "0";
                    } else {
                        cessString = edCessPercantage.getText().toString().trim();
                    }

                    String amtEx = edItemAmtExTax.getText().toString().trim();
                    double amtExc = Double.parseDouble(amtEx);
                    double amTax = Double.parseDouble(String.valueOf(totalTaxAmt));
                    //itemDatum.setDiscountAmt(Double.parseDouble(discount));
                    if ((amtExc + amTax) > Double.parseDouble(discount)) {
                        updateDiscountPrice(itemDatum, Double.parseDouble(discount));
                        updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));

                    } else {
                        if (s.toString() == null || s.toString().equals("")) {
                            edItemDiscount.setError(null);
                            flag = 0;
                            isCheckoutable = true;
                        } else {
                            flag = 7;
                            isCheckoutable = false;
                            edItemDiscount.setError("Disocunt Amount should be less than total amount.");
                        }
                    }
                }
                catch (NumberFormatException e) {
                    edItemDiscount.setError("Must be numeric value with only one .");
                    flag = 6;
                    isCheckoutable = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLess.setVisibility(View.VISIBLE);
                btnMore.setVisibility(View.GONE);
                llMoreOrLess.setVisibility(View.VISIBLE);
            }
        });

        btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnLess.setVisibility(View.GONE);
                btnMore.setVisibility(View.VISIBLE);
                llMoreOrLess.setVisibility(View.GONE);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                flag = 0;
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edItemUnitPrice.getText().toString() == null || edItemUnitPrice.getText().toString().equals("") ||
                        edItemUnitPrice.getText().toString().equals("0.00") || edItemUnitPrice.getText().toString().equals("0")) {
                    UtilView.showToast(mActivity, "Unit price can't be Zero");
                    isCheckoutable = false;
                } else {
                    isCheckoutable = true;
                }
                if (flag == 0 && isCheckoutable) {
                    String description = edItemDescritpion.getText().toString().trim();
                    Long itmQuantity = Long.parseLong(edItemQuantity.getText().toString().trim());
                    String unitPrice = edItemUnitPrice.getText().toString().trim();
                    double amtExTX = Double.parseDouble(edItemAmtExTax.getText().toString().trim());
                    double amtTotalTax = Double.parseDouble(edItemTotalTaxamt.getText().toString().trim());
                    String dis = edItemDiscount.getText().toString().trim();
                    double discount = 0;
                    if (dis == null || dis.equals("")) {
                        discount = 0;
                    } else {
                        discount = Double.parseDouble(edItemDiscount.getText().toString().trim());
                    }

                    double amtIncTax;
                    if (itemAmtIncTax.getText().toString().trim() == null || itemAmtIncTax.getText().toString().trim().equals("")) {
                        amtIncTax = 0;
                    } else {
                        amtIncTax = Double.parseDouble(itemAmtIncTax.getText().toString().trim());
                    }
                    itemDatum.setItemDescription(description);
                    if (operationType.equals(Constant.OPERATION_RETURN)){
                        long invoQty=Long.parseLong(edReturnQuatity.getText().toString());
                        itemDatum.setItemQuantity(invoQty);
                        itemDatum.setQty(invoQty);
                        itemDatum.setReturnQty(itmQuantity);
                        itemDatum.setRemainingQty(invoQty-itmQuantity);
                    }else {
                        itemDatum.setItemQuantity(itmQuantity);
                        itemDatum.setQty(itmQuantity);
                    }

                    itemDatum.setUnitPrice(Double.parseDouble(String.valueOf(unitPrice)));
                    itemDatum.setPrice(new BigDecimal(unitPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
                    itemDatum.setAmtexclusivetax(amtExTX);
                    itemDatum.setTaxTypeSelection(selectedTax);
                    itemDatum.setIgTax(Double.parseDouble(String.valueOf(cgstTaxAmtValue)));
                    itemDatum.setTaxAmountSplit(edTaxTypeAmount.getText().toString().trim());
                    try {
                        itemDatum.setCess(Double.parseDouble(edCessPercantage.getText().toString().trim()));
                    } catch (NumberFormatException ne) {
                        itemDatum.setCess(0);
                    }
                    itemDatum.setCessTaxAmt(Double.parseDouble(String.valueOf(cessTaxamt)));
                    itemDatum.setTaxamt(amtTotalTax);
                    itemDatum.setDiscountAmt(discount);
                    itemDatum.setAmtinclusivetax(amtIncTax);
                    itemDatum.setItemTotalAmount(amtIncTax);
                    salesposCreator.update(new SalesPosCartItem(itemDatum, 1), position);
                    checkCartList(operationType);
                    showTotalPrice();
                    if (dialog != null)
                        dialog.dismiss();
                } else {
                    if (flag == 1) {
                        UtilView.showToast(mActivity, " Item Quantity can't be empty");
                    }
                    if (flag == 2) {
                        UtilView.showToast(mActivity, " Item Quantity can't be zero");
                    }
                    if (flag == 3) {
                        UtilView.showToast(mActivity, " Enter valid quantity");
                    }
                    if (flag == 4) {
                        UtilView.showToast(mActivity, " Unit Price can't be empty");
                    }
                    if (flag == 5) {
                        UtilView.showToast(mActivity, " Enter valid price.");
                    }
                    if (flag == 6) {
                        UtilView.showToast(mActivity, " Enter valid discount price.");
                    }
                    if (flag == 7) {
                        UtilView.showToast(mActivity, " Discount amt should be less than Total Amount.");
                    }
                    if (flag == 8) {
                        UtilView.showToast(mActivity, " Enter valid cess percentage.");
                    }
                    if (flag == 9) {
                        UtilView.showToast(mActivity, "Return Quantity should be lestt than or equal to quantity.");
                    }
                }
            }
        });
        // }
    }

    private void updateTaxAmount(BigDecimal taxPercantage, SelectedItemsList itemDatum, EditText edTaxTypeAmount, EditText edItemAmtExTax, BigDecimal cessPercantage) {
        try {
            String discout = edItemDiscount.getText().toString().trim();
            if (discout.equals("")) {
                discout = "0";
            }
            BigDecimal taxAmt = BigDecimal.valueOf(0);
            String amtEx = edItemAmtExTax.getText().toString().trim();
            BigDecimal amtExTax;
            if (amtEx.equals("") || amtEx == null) {
                amtExTax = BigDecimal.ZERO;
            } else {
                amtExTax = BigDecimal.valueOf(Double.parseDouble(amtEx));
            }
            taxAmt = taxAmt.add(amtExTax.subtract(new BigDecimal(discout)).multiply(taxPercantage.divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_UP));

            cgstTaxAmtValue = taxAmt;
            if (selectedTax.equals(getResources().getString(R.string.igst))) {
                edTaxTypeAmount.setText("" + taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if (selectedTax.equals(getResources().getString(R.string.cgst))) {
                BigDecimal bgTax = taxAmt.divide(BigDecimal.valueOf(2));
                edTaxTypeAmount.setText("" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP) + "+" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP));

            }
            cessTaxamt = amtExTax.subtract(new BigDecimal(discout)).multiply(cessPercantage.divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_UP);
            itemDatum.setCessTaxAmt(Double.parseDouble(String.valueOf(cessTaxamt)));
            edCessAmt.setText(""+cessTaxamt);
            totalTaxAmt = taxAmt.add(cessTaxamt);
            edItemTotalTaxamt.setText("" + totalTaxAmt);
            itemTotalamt = amtExTax.add(totalTaxAmt).subtract(new BigDecimal(discout));
            itemDatum.setAmtinclusivetax(Double.parseDouble(String.valueOf(itemTotalamt.setScale(2, BigDecimal.ROUND_HALF_UP))));
            itemAmtIncTax.setText(String.valueOf(itemTotalamt.setScale(2, BigDecimal.ROUND_HALF_UP)));
            itemDatum.setTaxamt(Double.parseDouble(String.valueOf(totalTaxAmt)));
            itemDatum.setDiscountAmt(Double.parseDouble(discout));
            double amtIncTax;
            if (itemAmtIncTax.getText().toString().trim() == null || itemAmtIncTax.getText().toString().trim().equals("")) {
                amtIncTax = 0;
            } else {
                amtIncTax = Double.parseDouble(itemAmtIncTax.getText().toString().trim());
            }
            itemDatum.setItemTotalAmount(amtIncTax);
        } catch (Exception e) {
            UtilView.showLogCat(TAG, "updateTaxAmount Exception " + e.toString());
        }
    }

    private BigDecimal getTax(String tax, SelectedItemsList itemDatum) {
        try {
            int pos = tax.indexOf("|");
            BigDecimal taxValue = new BigDecimal(tax.substring(0, pos).trim());
            String subString = tax.substring(pos + 1);
            String taxName = subString.substring(0, subString.indexOf("|"));
            itemDatum.setTaxName(taxName);
            UtilView.showLogCat("tax name", taxName);
            itemDatum.setTaxpercent(String.valueOf(taxValue));
            return taxValue;
        } catch (Exception e) {
        }
        return new BigDecimal(0.00);
    }

    private BigDecimal getCessPercantage(String cessPercanteString) {
        return BigDecimal.valueOf(Double.parseDouble(cessPercanteString.toString()));
    }

    private void updateByQuantityTotalExTax(SelectedItemsList itemDatum, int quantity) {
        if (itemDatum != null) {
            BigDecimal totalPrice = BigDecimal.ZERO;
            totalPrice = totalPrice.add(itemDatum.getPrice().multiply(BigDecimal.valueOf(quantity)).setScale(2, BigDecimal.ROUND_HALF_UP));
            edItemAmtExTax.setText("" + totalPrice);
            if (quantity == 0) {
                edItemQuantity.setText("0");
            }
            String discount = edItemDiscount.getText().toString().trim();
            if (discount.equals("")) {
                discount = "0";
            }
            double amtIncTax;
            if (itemAmtIncTax.getText().toString().trim() == null || itemAmtIncTax.getText().toString().trim().equals("")) {
                amtIncTax = 0;
            } else {
                amtIncTax = Double.parseDouble(itemAmtIncTax.getText().toString().trim());
            }
            itemAmtIncTax.setText("" + (amtIncTax - Double.parseDouble(discount)));
        }
    }


    private void updateDiscountPrice(SelectedItemsList itemDatum, double discount) {
        String amtEx = edItemAmtExTax.getText().toString().trim();
        double amtExc;
        if (amtEx == null || amtEx.equals("")) {
            amtExc = 0;
        } else {
            amtExc = Double.parseDouble(amtEx);
        }
        double amtTaxd;
        amtTaxd = Double.parseDouble(String.valueOf(totalTaxAmt));
        BigDecimal finalIncusiveTaxAmt = new BigDecimal(amtExc).add(new BigDecimal(amtTaxd)).subtract(new BigDecimal(discount));
        itemAmtIncTax.setText("" + finalIncusiveTaxAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private void updateItemTotalExTax(SelectedItemsList itemDatum, BigDecimal unitPrice) {
        double itemQuantity;
        itemQuantity = Double.valueOf(edItemQuantity.getText().toString().trim());
        BigDecimal totalPrice = BigDecimal.ZERO;
        totalPrice = totalPrice.add(unitPrice.multiply(BigDecimal.valueOf(itemQuantity)));
        edItemAmtExTax.setText(String.valueOf(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
    }

    private void callItemView() {
        if (serverUrl!=null) {
            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
            Intent intent = new Intent(mActivity, Activity_ShowItemList.class);
            intent.putExtra("url", url);
            intent.putExtra("taxType", selectedTax);
            intent.putExtra("itemsearch", "");
            intent.putExtra("selectedcustomer", selected_customer);
            intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_RETAIL);
            startActivityForResult(intent, Constant.RESQUSTCODE_ITEMSEARCH);
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void getPageLoadData() {
        String url = serverUrl+ "/hipos//0/" + Constant.FUNCTION_GETPAGELOADDATA;
        isInternetPresent=serviceHandler.isConnectingToInternet();
        if (serverUrl!=null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                UtilView.gotToLogin(mActivity);
                            } else {
                                sharedPreference.saveData(Constant.HINEXTSALESPAGEDATA_KEY, result.toString());
                                if (llPage != null)
                                    llPage.setVisibility(View.VISIBLE);
                                Gson gson = new Gson();
                                try {
                                    pageData = gson.fromJson(result.toString(), HinextSalesPageData.class);
                                    setupPageData();
                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setupPageData() {
        if (pageData.getCustomers() != null && pageData.getCustomers().size() > 0) {
            discountRight = pageData.getUserRights().isDiscount();
            Constant.discountRight = pageData.getUserRights().isDiscount();
            selected_customer = new Customer();
            if (pageData.getCustomers().get(0).getCustomerName() != null) {
                selected_customer.setCustomerName(pageData.getCustomers().get(0).getCustomerName());
                tvSelectCustomer.setText(pageData.getCustomers().get(0).getCustomerName() + "|" + pageData.getCustomers().get(0).getCustomerNumber());
            } else {
                selected_customer.setCustomerName("");
            }
            if (pageData.getCustomers().get(0).getCustomerEmail() != null) {
                selected_customer.setCustomerEmail(pageData.getCustomers().get(0).getCustomerEmail());
            } else {
                selected_customer.setCustomerEmail("");
            }
            if (pageData.getCustomers().get(0).getCustomerContact() != null) {
                selected_customer.setCustomerContact(pageData.getCustomers().get(0).getCustomerContact());
            } else {
                selected_customer.setCustomerContact("");
            }
            selected_customer.setCustomerId(pageData.getCustomers().get(0).getCustomerId());
            if (pageData.getCustomers().get(0).getCustomerNumber() != null) {
                selected_customer.setCustomerNumber(pageData.getCustomers().get(0).getCustomerNumber());
            } else {
                selected_customer.setCustomerNumber("");
            }
            Gson gson = new Gson();
            sharedPreference.saveData(Constant.CUSTOMER, gson.toJson(selected_customer));
            if (selected_customer.getCustomerId() != null)
                callCustomerLocation(selected_customer.getCustomerId());
        }

        spinnerCustomerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCustomerLocation[0] = (CustlocationList) adapterView.getSelectedItem();
                updateGstTax();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ArrayAdapter<CmpylocationList> spinnerCompanyLocationAdapter = UtilView.setupCompanyLocationAdapter(mActivity, spinnerCompanyLocation, pageData.getCmpylocationList());
        selectedCompanyLocation[0] = spinnerCompanyLocationAdapter.getItem(0);
        spinnerCompanyLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCompanyLocation[0] = (CmpylocationList) adapterView.getSelectedItem();
                updateGstTax();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void updateGstTax() {
        Long companyLocationId = null, customerLocationId = null;
        if (selectedCompanyLocation[0] != null) {
            if (selectedCompanyLocation[0].getStateId() != null) {
                companyLocationId = selectedCompanyLocation[0].getStateId().getId();
            }
        }
        if (selectedCustomerLocation[0] != null) {
            if (selectedCustomerLocation[0].getNextref() != null) {
                customerLocationId = selectedCustomerLocation[0].getNextref();
            }
        }
        if (companyLocationId == customerLocationId) {
            tvSelectTax.setText(getResources().getString(R.string.cgst));
            selectedTax = tvSelectTax.getText().toString().trim();
        }
        if (companyLocationId != customerLocationId) {
            tvSelectTax.setText(getResources().getString(R.string.igst));
            selectedTax = tvSelectTax.getText().toString().trim();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (EasyPermissions.hasPermissions(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, mActivity);
                File file = new File(filePath);
                RequestBody mFile = null;
                MultipartBody.Part fileToUpload = null;
                if (file != null) {
                    //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    mFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
                    fileToUpload = MultipartBody.Part.createFormData("myFile", file.getName(), mFile);
                } else {
                    //mFile = RequestBody.create(MediaType.parse("image/*"), new File(""));
                    mFile = RequestBody.create(MediaType.parse("application/octet-stream"), "");
                    fileToUpload = MultipartBody.Part.createFormData("myFile", "", mFile);
                }

                final SharedPreference sharedPreference = SharedPreference.getInstance(mActivity);
                final String accessToken = sharedPreference.getData(Constant.ACCESSTOKEN);

                if (accessToken != null && !accessToken.equals("invalid")) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.VISIBLE);
                    }


                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                            .connectTimeout(5, TimeUnit.MINUTES)
                            .readTimeout(5, TimeUnit.MINUTES)
                            .writeTimeout(5, TimeUnit.MINUTES);
                    httpClient.addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();

                            Request request = original.newBuilder()
                                    .header("Content-Type", "multipart/form-data")
                                    .header("Accept", "application/json")
                                    .header("Cookie", "accessToken=" + accessToken)
                                    .method(original.method(), original.body())
                                    .build();

                            return chain.proceed(request);
                        }
                    });
                    serverUrl = UtilView.getUrl(mActivity);
                    UtilView.showLogCat(TAG, "Server Url for okHttp " + serverUrl);
                    OkHttpClient client = httpClient.build();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(serverUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                    UploadImageInterface uploadImage = retrofit.create(UploadImageInterface.class);

                    Call<ResponseDataFromServer> serverResponse = uploadImage.uploadSalesInvoice(fileToUpload);
                    serverResponse.enqueue(new Callback<ResponseDataFromServer>() {
                        @Override
                        public void onResponse(Call<ResponseDataFromServer> call, Response<ResponseDataFromServer> response) {
                                 /*  Toast.makeText(MainActivity.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
                         Toast.makeText(MainActivity.this, "Success " + response.body().getSuccess(), Toast.LENGTH_LONG).show();*/
                            if (progressBar != null) {
                                progressBar.setVisibility(View.GONE);
                            }
                            if (response != null) {
                                UtilView.showLogCat(TAG, "Respnse " + response.body().getSelectedItemsList().size());
                                if (response.body().getSelectedItemsList() != null && response.body().getSelectedItemsList().size() > 0) {
                                    for (int i = 0; i < response.body().getSelectedItemsList().size(); i++) {
                                        in.hiaccounts.hinext.sales.model.sales_invoice.SelectedItemsList itemDatum = response.body().getSelectedItemsList().get(i);
                                        SelectedItemsList salesItem = new SelectedItemsList();
                                        try {
                                            try {
                                                salesItem.setCess(itemDatum.getCess());
                                            } catch (NumberFormatException ne) {
                                                salesItem.setCess(0);
                                            }

                                            salesItem.setAmtexclusivetax(itemDatum.getAmtexclusivetax());
                                            salesItem.setItemTotalAmount(itemDatum.getAmtexclusivetax());
                                            salesItem.setAmtinclusivetax(itemDatum.getAmtinclusivetax());
                                            salesItem.setCessTaxAmt(itemDatum.getCessTaxAmt());
                                            salesItem.setDiscountAmt(itemDatum.getDiscountAmt());
                                            // salesItem.setDiscountInPercent(itemDatum.isDiscountInPercent());
                                            salesItem.setHsnCode(itemDatum.getHsnCode());
                                            salesItem.setUomName(itemDatum.getUomName());


                                            if (itemDatum.getUomConvertorList() != null) {
                                                salesItem.setUomConvertorList(itemDatum.getUomConvertorList());
                                            } else {
                                                salesItem.setUomConvertorList(itemDatum.getUomConvertorList());
                                            }

                                            salesItem.setIgTax(itemDatum.getCgstsgstsplitvalues());
                                            salesItem.setItemCode(itemDatum.getItemCode());
                                            salesItem.setItemDescription(itemDatum.getItemDescription());
                                            salesItem.setItemName(itemDatum.getItemName());
                                            salesItem.setItemId(itemDatum.getItemId());
                                            salesItem.setItemQuantity(itemDatum.getQty());
                                            salesItem.setRemainingQty(itemDatum.getQty());
                                            //  salesItem.setItemTotalAmount(itemDatum.getItemTotalAmount());
                                            salesItem.setPrice(BigDecimal.valueOf(itemDatum.getUnitPrice()));
                                            salesItem.setUnitPrice(Double.parseDouble("" + itemDatum.getUnitPrice()));
                                            salesItem.setUnitPriceIn(Double.parseDouble("" + itemDatum.getUnitPrice()));
                                            salesItem.setRemainingQty(itemDatum.getRemainingQty());
                                            salesItem.setReturnQty(itemDatum.getReturnQty());
                                            //   salesItem.setSelectSerialItem(itemDatum.isSelectSerialItem());
                                            salesItem.setSerializableStatus(itemDatum.getSerializableStatus());
                                            salesItem.setQty(itemDatum.getQty());
                                            salesItem.setTaxAmountSplit("" + itemDatum.getTaxPercentageSplit());
                                            salesItem.setTaxamt(itemDatum.getTaxamt());
                                            salesItem.setTaxName(itemDatum.getTaxName());
                                            salesItem.setTaxTypeSelection("" + itemDatum.getTaxpercent());
                                            salesItem.setTaxpercent("" + itemDatum.getTaxpercent());
                                            salesItem.setTaxid(itemDatum.getTaxid());
                                            salesItem.setItemTypeName(itemDatum.getItemType());


                                        } catch (Exception e) {

                                        }
                                        salesposCreator.addItem(new SalesPosCartItem(salesItem, 1));
                                    }
                                    checkCartList("");
                                } else {
                                    UtilView.showToast(mActivity, "Items not found.");
                                }
                            } else {
                                UtilView.showLogCat(TAG, "Respnse null");
                                UtilView.showToast(mActivity, "Please provide clear image. Data can't able to fetch");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseDataFromServer> call, Throwable t) {
                            if (progressBar != null) {
                                progressBar.setVisibility(View.GONE);
                            }
                            UtilView.showToast(mActivity, "Please provide clear image. Data can't able to fetch");
                        }
                    });
                } else {
                    UtilView.gotToLogin(mActivity);
                }
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }



        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            if(mphoto!=null){
                try {
                    File file = new File(mActivity.getCacheDir(), "invoice");
                    file.createNewFile();
                //encode image to base64 string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                mphoto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);


                    //write the bytes in file
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(imageBytes);
                    fos.flush();
                    fos.close();

                   /* JSONObject requestJson = new JSONObject();
                    requestJson.put("email", "kumar.kundan280@gmail.com");
                    requestJson.put("company_name", "prateek");
                    requestJson.put("industry", "");
                    requestJson.put("invoice_id", "2121");
                    requestJson.put("image_serial_number", "2");
                    requestJson.put("imagedata", "data:image/jpeg;base64,"+imageString);
                    UtilView.showLogCat(TAG,"Request Json  "+requestJson.toString());
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask1 postDataTask = new PostDataTask1(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);

                            UtilView.showLogCat(TAG,"onCaptureImagesned "+result);

                        }
                    }, false);
                    postDataTask.execute(requestJson.toString(),"http://hiaccounts.in/services/v1/Scan/submitImage", "");*/

                    RequestBody mFile = null;
                    MultipartBody.Part fileToUpload = null;


                    if (file != null) {
                        //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        mFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
                        fileToUpload = MultipartBody.Part.createFormData("myFile", file.getName(), mFile);
                    } else {
                        //mFile = RequestBody.create(MediaType.parse("image/*"), new File(""));
                        mFile = RequestBody.create(MediaType.parse("application/octet-stream"), "");
                        fileToUpload = MultipartBody.Part.createFormData("myFile", "", mFile);
                    }

                    final SharedPreference sharedPreference = SharedPreference.getInstance(mActivity);
                    final String accessToken = sharedPreference.getData(Constant.ACCESSTOKEN);

                    if (accessToken != null && !accessToken.equals("invalid")) {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.VISIBLE);
                        }


                        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                   /* .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES);*/
                        httpClient.addInterceptor(new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                Request request = original.newBuilder()
                                        .header("Content-Type", "multipart/form-data")
                                       /* .header("Accept", "application/json")*/
                                        .header("Cookie", "accessToken=" + accessToken)
                                        .method(original.method(), original.body())
                                        .build();

                                return chain.proceed(request);
                            }
                        });
                        serverUrl = UtilView.getUrl(mActivity);
                        UtilView.showLogCat(TAG, "Server Url for okHttp " + serverUrl);
                        OkHttpClient client = httpClient.build();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(serverUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(client)
                                .build();
                        UploadImageInterface uploadImage = retrofit.create(UploadImageInterface.class);

                        Call<ResponseDataFromServer> serverResponse = uploadImage.uploadSalesInvoice(fileToUpload);
                        serverResponse.enqueue(new Callback<ResponseDataFromServer>() {
                            @Override
                            public void onResponse(Call<ResponseDataFromServer> call, Response<ResponseDataFromServer> response) {
                                 /*  Toast.makeText(MainActivity.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
                         Toast.makeText(MainActivity.this, "Success " + response.body().getSuccess(), Toast.LENGTH_LONG).show();*/
                                if (progressBar != null) {
                                    progressBar.setVisibility(View.GONE);
                                }
                                if (response != null) {

                                    UtilView.showLogCat(TAG, "Respnse " + response.toString());
                                } else {
                                    UtilView.showLogCat(TAG, "Respnse null");
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseDataFromServer> call, Throwable t) {
                                if (progressBar != null) {
                                    progressBar.setVisibility(View.GONE);
                                }
                                Log.e(TAG, "onFailure Error ==> " + t.getMessage());
                            }
                        });
                    } else {
                        UtilView.gotToLogin(mActivity);
                    }


                } catch (Exception e) {
                    UtilView.showLogCat(TAG, "callLoginUrl exception " + e.toString());

                }


            }
        }




        if (requestCode == Constant.RESQUSTCODE_SALESRETURNCHECKOUT) {
            checkCartList(Constant.OPERATION_RETURN);
        }
        if (requestCode == Constant.RESQUSTCODE_RETURNCHECKOUT && resultCode == Activity.RESULT_OK) {
            salesposCreator.clear();
            checkCartList("");
        }
        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == Activity.RESULT_OK) {
            checkCartList("");
        }

        if (requestCode == Constant.RESQUSTCODE_INVOKESALESORDER && resultCode == Activity.RESULT_OK) {
            saleOrderid =  data.getStringExtra("invokesalesOrder");
            checkoutType = Constant.CHECKOUTYPE_INVOKESALESORDER;
            checkCartList("");
        }
        if (requestCode == Constant.RESQUSTCODE_INVOKEQUOTATION && resultCode == Activity.RESULT_OK) {

            checkCartList("");
        }
        if (requestCode == Constant.REQUESTCODE_PRINTLISTSALESINVOICE && resultCode == Activity.RESULT_OK) {
            sino = data.getStringExtra("salesInvoiceNo");
            siid = data.getLongExtra("salesInvoiceSiid",0);
            checkCartList("");
        }



        if (requestCode == Constant.RESQUSTCODE_SALESRETURNITEM && resultCode == Activity.RESULT_OK) {

            returnItem = data.getStringExtra("ReturnItem");
            salesReturnData = (SalesSaveData) data.getSerializableExtra("returnItemData");
            checkCartList("Return");

        }



        if (requestCode == Constant.REQUESTCODE_PRINTLISTSALESQUOTATION && resultCode == Activity.RESULT_OK) {
            quotationId = data.getLongExtra("quotationId",0);

            checkCartList("");
        }
        if (requestCode == Constant.REQUESTCODE_PRINTLISTSALESORDER && resultCode == Activity.RESULT_OK) {
            salesorderId = data.getStringExtra("salesorderId");

            checkCartList("");
        }
        if (requestCode == Constant.REQUESTCODE_PRINTLISTPROFORMA && resultCode == Activity.RESULT_OK) {
            proFormaId = data.getStringExtra("proformaId");

            checkCartList("");
        }


        if (requestCode == Constant.REQUESTCODE_PRINTLISTSALESDELIVERYORDER && resultCode == Activity.RESULT_OK) {
            salesdeliveryorderid = data.getStringExtra("salesDeliveryOrderId");

            checkCartList("");
        }
        if (requestCode == Constant.REQUESTCODE_PRINTLISTSALESLOANDELIVERYORDER && resultCode == Activity.RESULT_OK) {
            salesdeliveryorderid = data.getStringExtra("salesDeliveryOrderId");

            checkCartList("");
        }



        if (requestCode == Constant.RESQUSTCODE_INVOKEDELIVERYORDER && resultCode == Activity.RESULT_OK) {

            checkCartList("");
        }
        if (requestCode == Constant.RESQUSTCODE_INVOKELOANDELIVERYORDER && resultCode == Activity.RESULT_OK) {

            checkCartList("");
        }


        if (requestCode == Constant.RESQUSTCODE_RETURNDELIVERYORDER && resultCode == Activity.RESULT_OK) {

            returnItem = data.getStringExtra("DeliveryOrder");
            salesdeliveryorderid = data.getStringExtra("salesdeliveryorderid");
            checkCartList("Return");
        }
        if (requestCode == Constant.RESQUSTCODE_RETURNLOANDELIVERYORDER && resultCode == Activity.RESULT_OK) {
            returnItem = data.getStringExtra("LoanDeliveryOrder");
            salesdeliveryorderid = data.getStringExtra("salesdeliveryorderid");
            checkCartList("Return");
        }



        if (requestCode == RESQUSTCODE_CHECKOUT && resultCode == Activity.RESULT_OK) {
            salesposCreator.clear();
            checkCartList("");
        }
        if (requestCode == Constant.RESQUSTCODE_CHECKOUT && resultCode == Activity.RESULT_OK) {
            chkBoxAdvancePayments.setChecked(false);
            chkBoxReverseCharge.setChecked(false);
            if (salesposCreator != null)
                salesposCreator.clear();
            checkCartList("");
        }
        if (requestCode == 101) {
            if (salesposCreator != null)
                salesposCreator.clear();
            checkCartList("");

        }
        if (requestCode == Constant.RESQUSTCODE_CUSTOMERS && resultCode == Activity.RESULT_OK) {
            CustomerList cust = (CustomerList) data.getSerializableExtra("customer");
            if (cust != null) {
                selected_customer = new Customer();
                if (cust.getCustomerEmail() == null) {
                    selected_customer.setCustomerEmail("");
                } else {
                    selected_customer.setCustomerEmail(cust.getCustomerEmail());
                }

                if (cust.getCustomerNumber() == null) {
                    selected_customer.setCustomerNumber("");
                } else {
                    selected_customer.setCustomerNumber(cust.getCustomerNumber());
                }

                if (cust.getCustomerContact() == null) {
                    selected_customer.setCustomerContact("");
                } else {
                    selected_customer.setCustomerContact(cust.getCustomerContact());
                }
                if (cust.getCustomerName() == null) {
                    selected_customer.setCustomerName("");
                } else {
                    selected_customer.setCustomerName(cust.getCustomerName());
                }

                selected_customer.setCustomerId(cust.getCustomerId());
            }
            if (selected_customer.getCustomerNumber() != null) {
                if (!selected_customer.getCustomerNumber().equals(""))
                    tvSelectCustomer.setText(selected_customer.getCustomerName() + "|" + selected_customer.getCustomerNumber());
                else
                    tvSelectCustomer.setText(selected_customer.getCustomerName() + "|0");
            } else {
                tvSelectCustomer.setText(selected_customer.getCustomerName() + "|0");
            }
            if (selected_customer.getCustomerId() != null) {
                callCustomerLocation(selected_customer.getCustomerId());
            }
        }
    }


    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    private void callCustomerLocation(Long customerId) {
        String url =serverUrl + "/hipos//" + customerId + "/customerLocation";
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if(serverUrl!=null) {

            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            List<CustlocationList> locationList = new ArrayList<>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                if (jsonArray != null) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        CustlocationList location = gson.fromJson(jsonObject.toString(), CustlocationList.class);
                                        locationList.add(location);
                                    }
                                    if (locationList != null && locationList.size() > 0) {
                                        ArrayAdapter<CustlocationList> spinnerInventoryLocationAdapter = UtilView.setupInventoryLocationAdapter(mActivity, spinnerCustomerLocation, locationList);
                                        selectedCustomerLocation[0] = spinnerInventoryLocationAdapter.getItem(0);
                                    }
                                }
                            } catch (Exception je) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }


                }, false);
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("customerIdForLoc", selected_customer.getCustomerId());
                    postDataTask.execute(jsonObject.toString(), url, "");
                } catch (JSONException je) {
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }
    public void checkCartList(String functionOperation) {
        operationType=functionOperation;
        if (fabMenus != null)
            fabMenus.close(true);
        if (salesposCreator != null) {
            if (salesposCreator.getItems().size() >= 0) {
           /*     mNotificationCounter--;
                BadgeCounter.update(mActivity,
                        filterMenuItem,
                        R.drawable.ic_bell_white_24dp,
                       null,
                        mNotificationCounter);*/
              //  BadgeCounter.update(mActivity,filterMenuItem, R.drawable.ic_bell_white_24dp, mNotificationCounter);
                llPage.setVisibility(View.VISIBLE);
                list_view_notifications.setVisibility(View.GONE);
                Log.e("salesposCreator", String.valueOf(salesposCreator.getItems().size()));
                if (functionOperation.equals(Constant.OPERATION_RETURN)) {
                    setViewUnFocusable();
                } else {
                    setViewFocusable();
                }
                mActivity.invalidateOptionsMenu();
                showTotalPrice();
                List<SalesPosCartItem> posCartItems = salesposCreator.getItems();
                cartItemAdapter = new Salespos_Adapter(mActivity, posCartItems, functionOperation);
                try {
                    listviewSelectItems.smoothScrollToPosition(posCartItems.size());
                    if (listviewSelectItems != null)
                        listviewSelectItems.setAdapter(cartItemAdapter);
                    cartItemAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    UtilView.showLogCat(TAG, "checkCartList cart Exception " + e.toString());
                }
                visibilityVisible();
            } else {
                flag = 0;
                isCheckoutable = true;
                visibilityGone();
                List<SalesPosCartItem> posCartItems = salesposCreator.getItems();
                if (cartItemAdapter != null) {
                    cartItemAdapter.updateCartItems(posCartItems);
                    cartItemAdapter.notifyDataSetChanged();
                }
                mActivity.invalidateOptionsMenu();
                if (functionOperation.equals(Constant.OPERATION_RETURN)) {
                    setViewUnFocusable();
                } else {
                    setViewFocusable();
                }
            }

        } else {
            mActivity.invalidateOptionsMenu();
          /*  if (functionOperation.equals(Constant.OPERATION_RETURN)) {
                setViewUnFocusable();
            } else {
                setViewFocusable();
            }*/
        }
    }

    private void setViewFocusable() {

        if (btnCheckout != null)
            btnCheckout.setVisibility(View.VISIBLE);
        if (chkBoxAdvancePayments != null)
            chkBoxAdvancePayments.setVisibility(View.VISIBLE);
        if (chkBoxReverseCharge != null)
            chkBoxReverseCharge.setVisibility(View.VISIBLE);
        if (btnSelectItem != null)
            btnSelectItem.setClickable(true);
        if (tvSelectCustomer != null)
            tvSelectCustomer.setClickable(true);
        if (btnReturnSales != null)
            btnReturnSales.setVisibility(View.GONE);
    }

    private void setViewUnFocusable() {
        if (btnReturnSales != null)
            btnReturnSales.setVisibility(View.VISIBLE);
        if (btnCheckout != null)
            btnCheckout.setVisibility(View.GONE);
        if (chkBoxAdvancePayments != null)
            chkBoxAdvancePayments.setVisibility(View.GONE);
        if (chkBoxReverseCharge != null)
            chkBoxReverseCharge.setVisibility(View.GONE);
        if (btnSelectItem != null)
            btnSelectItem.setClickable(false);
        if (tvSelectCustomer != null)
            tvSelectCustomer.setClickable(false);

    }
    public void getDatePicker(Activity mActivity) {
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
                edDate.setText(String.valueOf(new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year)));
                calendar.set(year, month, dayOfMonth);
                selectedDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    private String saveposData(String paymentType, String opertaion, Customer customer) {


        List<SalesPosCartItem> posCartItems = salesposCreator.getItems();
        List<SelectedItemsList> selectedItemsList = new ArrayList<>();
        Iterator<SalesPosCartItem> itr = posCartItems.iterator();
        double totalTaxAmt = 0.00;
        double totalCessTaxAmt = 0.00;
        double totalDiscount = 0.00;
        String salesOrderId = null;


        while (itr.hasNext()) {
            SalesPosCartItem posCartItem = itr.next();
            SelectedItemsList itemDatum = posCartItem.getItem();
            itemDatum.setQty(itemDatum.getItemQuantity());
            itemDatum.setRemainingQty(itemDatum.getItemQuantity());
            itemDatum.setTaxName(isReverseChargeInvoice);
            selectedItemsList.add(itemDatum);
            totalTaxAmt += itemDatum.getTaxamt();
            totalCessTaxAmt += itemDatum.getCessTaxAmt();
            totalDiscount+=itemDatum.getDiscountAmt();

        }
        SalesSaveData salesSaveData = new SalesSaveData();
        salesSaveData.setSelectedItemsList(selectedItemsList);
        salesSaveData.setTotalCheckOutamt(Double.valueOf(String.valueOf(new BigDecimal(salesposCreator.getTotalPrice()).setScale(2, BigDecimal.ROUND_HALF_UP))));
        Log.e("TotalChecOutData", String.valueOf(salesSaveData.getTotalCheckOutamt()));
        salesSaveData.setAdvancepayment(isAdvancePayment);
        salesSaveData.setSelfBuildInvoice(Boolean.valueOf(isReverseChargeInvoice));
        salesSaveData.setCessTotalTaxAmt(totalCessTaxAmt);
        salesSaveData.setCustLocation(0);
        salesSaveData.setCustomerEmail(selected_customer.getCustomerEmail());
        salesSaveData.setCustomerId(selected_customer.getCustomerId());
        salesSaveData.setCutomerName(selected_customer.getCustomerName());
        salesSaveData.setPaymentType(paymentType);
        salesSaveData.setTaxType(selectedTax);
        salesSaveData.setTotalTaxAmt(totalTaxAmt);
        salesSaveData.setDateOfInvoice(selectedDate);
        salesSaveData.setShippingDate(selectedDate);
        salesSaveData.setSalesOrderId(saleOrderid);
        salesSaveData.setDiscountAmount(totalDiscount);
        salesSaveData.setSalesDeliveryOrderNO(salesdeliveryorderid);
        salesSaveData.setSiNo(sino);
        salesSaveData.setSiid(siid);


        if (selectedCompanyLocation[0] != null) {
            salesSaveData.setCustLocation(selectedCompanyLocation[0].getStateId().getId());
        }
        if (selectedCompanyLocation[0] != null) {
            salesSaveData.setCmpyLocation(selectedCompanyLocation[0].getInventoryLocationId());
        }
        if (opertaion.equals("SO")) {
            salesSaveData.setType_doc("SO");
            salesSaveData.setTo_reg("");
            salesSaveData.setFrom_reg("");
        }
        if (opertaion.equals("InvokeOrder")) {
            salesSaveData.setType_doc("SO");
            salesSaveData.setTo_reg("");
            salesSaveData.setFrom_reg("");
            if (invokeOrderId !=null){
                salesSaveData.setInvokeOrderId(invokeOrderId);
                salesSaveData.setInvokeOrderName(invokeOrderName);
            }
            else{
                salesSaveData.setInvokeOrderId(-1);
                salesSaveData.setInvokeOrderName(invoiceDraftName);
            }

            salesSaveData.setOperation("InvokeOrder");

        }
        Gson gson = new Gson();
        return gson.toJson(salesSaveData);

    }



    private String saveSalesOrderPostData(String paymentType, String opertaion, Customer customer) {
        List<SalesPosCartItem> posCartItems = salesposCreator.getItems();
        List<SelectedItemsList> selectedItemsList = new ArrayList<>();
        Iterator<SalesPosCartItem> itr = posCartItems.iterator();
        double totalTaxAmt = 0.00;
        double totalCessTaxAmt = 0.00;
        String salesOrderId = null;
        String salesDeliveryOrderNO = null;
        while (itr.hasNext()) {
            SalesPosCartItem posCartItem = itr.next();
            SelectedItemsList itemDatum = posCartItem.getItem();
            itemDatum.setQty(itemDatum.getItemQuantity());
            itemDatum.setRemainingQty(itemDatum.getItemQuantity());
            itemDatum.setTaxName(isReverseChargeInvoice);
            selectedItemsList.add(itemDatum);
            totalTaxAmt += itemDatum.getTaxamt();
            totalCessTaxAmt += itemDatum.getCessTaxAmt();
            salesOrderId = itemDatum.getSalesOrderId();
        }
        SaveSalesOrderData saveSalesOrderData = new SaveSalesOrderData();
        saveSalesOrderData.setSelectedItemsList(selectedItemsList);
        saveSalesOrderData.setTotalCheckOutamt(String.valueOf(Double.valueOf(String.valueOf(new BigDecimal(salesposCreator.getTotalPrice()).setScale(2, BigDecimal.ROUND_HALF_UP)))));
        Log.e("TotalChecOutData", String.valueOf(saveSalesOrderData.getTotalCheckOutamt()));
        saveSalesOrderData.setAdvancepayment(isAdvancePayment);
        saveSalesOrderData.setCessTotalTaxAmt("" + totalCessTaxAmt);
        //saveSalesOrderData.setCustLocation();
        saveSalesOrderData.setCustomerEmail(selected_customer.getCustomerEmail());
        saveSalesOrderData.setCustomerId(selected_customer.getCustomerId());
        saveSalesOrderData.setCutomerName(selected_customer.getCustomerName());
        saveSalesOrderData.setPaymentType(paymentType);
        saveSalesOrderData.setTaxType(selectedTax);
        saveSalesOrderData.setTotalTaxAmt(totalTaxAmt);
        saveSalesOrderData.setShippingDate(selectedDate);
        saveSalesOrderData.setDateOfInvoice(selectedDate);
        saveSalesOrderData.setSalesOrderId(salesorderId);
        saveSalesOrderData.setSalesDeliveryOrderNO(salesdeliveryorderid);
        saveSalesOrderData.setSalesQuotationId(quotationId);
        saveSalesOrderData.setProFormaId(proFormaId);

        saveSalesOrderData.setSiid(siid);
        saveSalesOrderData.setExchangerateValue("1");
        saveSalesOrderData.setCurrencyIdOfInvoice("1");




        if (selectedCompanyLocation[0] != null) {
            saveSalesOrderData.setCustLocation(selectedCompanyLocation[0].getStateId().getId());
        }
        if (selectedCompanyLocation[0] != null) {
            saveSalesOrderData.setCmpyLocation(selectedCompanyLocation[0].getInventoryLocationId());
        }
        if (opertaion.equals("SO")) {
            saveSalesOrderData.setType_doc("SO");
            saveSalesOrderData.setTo_reg("");
            saveSalesOrderData.setFrom_reg("");
            //salesSaveData.setOperation("SO");
        }

        if (opertaion.equals("Return")) {
            saveSalesOrderData.setOperation("Return");
        }
        Gson gson = new Gson();
        return gson.toJson(saveSalesOrderData);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_sales, menu);
/*        filterMenuItem = menu.findItem(R.id.id_notification);
        if (mNotificationCounter > 0) {
            BadgeCounter.update(mActivity,
                    filterMenuItem,
                    R.drawable.ic_bell_white_24dp,
                    BadgeCounter.BadgeColor.RED,
                    mNotificationCounter);
        } else {
            BadgeCounter.update(mActivity,
                    filterMenuItem,
                    R.drawable.ic_bell_white_24dp,
                    BadgeCounter.BadgeColor.RED,
                    mNotificationCounter);
        }

        filterMenuItem.getActionView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llPage.setVisibility(View.GONE);
                list_view_notifications.setVisibility(View.VISIBLE);
                messageAdapter = new MessageAdapter(mActivity,listMessages);
                list_view_notifications.setAdapter(messageAdapter);
                messageAdapter.notifyDataSetChanged();

                list_view_notifications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (listMessages.get(position)!=null){
                            confirmationDailog(listMessages.get(position),position);
                        }

                    }
                });

            }
        });*/

        super.onCreateOptionsMenu(menu, inflater);

    }
/*
    private void callGetNotifications() {
        String url ="http://cloud.hiaccounts.com:8888/hiConnectService/v4/getnotifications";
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if(url!=null) {

            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {

                                NotificationListData notificationListData = gson.fromJson(result.toString(), NotificationListData.class);
                                if (notificationListData != null) {
                                    Log.e("Notification Result2",notificationListData.getObject().toString());
                                    if (notificationListData.getObject().toString() != null) {
                                         notificationData = new Gson().fromJson(notificationListData.getObject().toString(), new TypeToken<NotificationData>() {
                                        }.getType());


                                        if (notificationData.getCustomerNotificationsList().size()>0 && notificationData.getCustomerNotificationsList()!=null)
                                        mNotificationCounter = notificationData.getCustomerNotificationsList().size();
                                        setHasOptionsMenu(true);
                                        for (CustomerNotificationsList nData: notificationData.getCustomerNotificationsList()){
                                            listMessages.add(nData);
                                            CustomerNotificationsListData custNotificationData = new Gson().fromJson(nData.getObjectdata().toString(), new TypeToken<CustomerNotificationsListData>() {
                                            }.getType());

                                            if (custNotificationData.getJsonData().getSelectedItemsList()!=null && custNotificationData.getJsonData().getSelectedItemsList().size()>0)
                                            for (int j=0;j<custNotificationData.getJsonData().getSelectedItemsList().size();j++){
                                                Log.e("notificationItemData", String.valueOf(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemName()));

                                            }

                                        }
                                    }

                               }
                            }catch (Exception e){
                                Log.e("Notification Result",e.toString());
                            }


                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }


                }, false);
                try {


                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("regno", "22E8A09E34994AE2");
                    jsonObject.put("user_id", "1");
                    jsonObject.put("senderrequest", "to");
                    jsonObject.put("type_flag", "Sales");
                    postDataTask.execute(jsonObject.toString(), url, "");
                } catch (JSONException je) {
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void confirmationDailog(CustomerNotificationsList customerNotificationsList, int position) {


        //BadgeCounter.update(filterMenuItem, mNotificationCounter);
        if (mNotificationCounter > 0){

            BadgeCounter.update(mActivity,
                    filterMenuItem,
                    R.drawable.ic_bell_white_24dp,
                    BadgeCounter.BadgeColor.RED,
                    mNotificationCounter);
           // BadgeCounter.hide(filterMenuItem);
            llPage.setVisibility(View.GONE);
            list_view_notifications.setVisibility(View.VISIBLE);
        }

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle("Notification");
        //alertDialog.setMessage("Notification");
       // alertDialog.setIcon(R.drawable.delete);
        alertDialog.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                llPage.setVisibility(View.GONE);
                list_view_notifications.setVisibility(View.VISIBLE);
                getPendingNotifications(customerNotificationsList,position);
                checkCartList("");
            }
        });
        alertDialog.setNegativeButton("Reject", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                llPage.setVisibility(View.GONE);
                list_view_notifications.setVisibility(View.VISIBLE);
                getRejectNotifications(customerNotificationsList,position);
                checkCartList("");
                dialog.cancel();

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    private void getRejectNotifications(CustomerNotificationsList customerNotificationsList, int position) {
        listMessages.remove(position);
        messageAdapter.notifyDataSetChanged();
        String url ="http://cloud.hiaccounts.com:8888/hiConnectService/v4/rejectSpecificNotification";
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if(url!=null) {

            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                             Log.e("RejectNotification",result.toString());
                            Gson gson = new Gson();
                            try {


                            }catch (Exception e){
                                Log.e("Notification Result",e.toString());
                            }


                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }


                }, false);
                try {

                    JSONObject jsonObject = new JSONObject();
                    if (customerNotificationsList!=null){
                        jsonObject.put("fromRegno", customerNotificationsList.getFromRegno());
                        jsonObject.put("toRegno", customerNotificationsList.getToRegno());
                       // jsonObject.put("user_id", "1");
                        jsonObject.put("typeDoc", customerNotificationsList.getTypeDoc());
                        jsonObject.put("typeFlag", customerNotificationsList.getTypeFlag());
                        jsonObject.put("custNotiId", customerNotificationsList.getCustNotiId());
                        jsonObject.put("status", "Rejected");
                    }
                    postDataTask.execute(jsonObject.toString(), url, "");
                } catch (JSONException je) {
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }*/


    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        if (salesposCreator != null && salesposCreator.getItems().size() > 0) {
            menu.findItem(R.id.delete_items).setVisible(true);
        } else {
            menu.findItem(R.id.delete_items).setVisible(false);
        }


        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.action_scan:

                dialog.setContentView(R.layout.dialog_selectoption);
                //dialog.setCancelable(false);
                // final TextView tvCamera = (TextView) dialog.findViewById(R.id.tvCamera);
                TextView tvGallery = dialog.findViewById(R.id.tvGallery);



               /* tvCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            callPermission();
                        }else {
                            takeImageFromCamera();
                        }
                    }
                });*/

                tvGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            callPermission();
                        } else {
                            takeImageFromGallery();
                        }
                    }
                });

                dialog.show();

                break;

            case R.id.action_settings:
                ((NavigationDrawer_Activity)mActivity).openRightDrawer(Constant.MODULE_SALES);
                break;

            case R.id.menuClearDraft:
                invokeClearDraft();
                break;
            case R.id.menuInvokeDraft:
                invokeDraft();
                break;
            case R.id.delete_items:

                String getPageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
                if (getPageLoadData !=null) {

                    Gson gson = new Gson();
                    PageLoadDataForAll  pageData = gson.fromJson(getPageLoadData, PageLoadDataForAll.class);
                    if (pageData != null) {
                        if (pageData.getUserAccessRights().getSalesRemoveItem() == true)
                        {

                            if (selectItemForDelete != null && selectItemForDelete.size() > 0) {
                                salesposCreator.delete(selectItemForDelete);
                                checkCartList("");
                                selectItemForDelete.clear();
                            } else {
                                UtilView.showToast(mActivity, "Please select at leat one item to delete");
                            }
                        }

                    }
                }


                break;
            case R.id.menuSaveDraft:

                callDraftSave();

                break;
            case R.id.menuPrintList:
                initPrintListDailog();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void callDraftSave() {
        if (selected_customer == null) {
            UtilView.showErrorDialog(getResources().getString(R.string.selectCustomerError), mActivity);
        } else {
            if (salesposCreator.getItems().size() > 0) {
                initSaveOrderDialog(selected_customer);
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.cartempty), mActivity);
            }
        }
    }

    private void initSaveOrderDialog(final Customer customer) {
        // custom dialog
        dialog.setContentView(R.layout.dialog_saveorder);
        dialog.setCancelable(false);
        final ProgressView progressView = dialog.findViewById(R.id.progress_bar);
        final EditText ed_saveordername = dialog.findViewById(R.id.ed_saveordername);
        Button btn_save = dialog.findViewById(R.id.btn_save);
        Button btn_close = dialog.findViewById(R.id.btn_close);

        ed_saveordername.setText(invokeOrderName);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invoiceDraftName = ed_saveordername.getText().toString();
                if (invoiceDraftName.equals("")) {
                    UtilView.showErrorDialog(getResources().getString(R.string.error_ordername), mActivity);
                } else {
                    String url =serverUrl+ "/retail//" + Constant.FUNTION_SAVEORDER;
                    String saveData = saveposData("", "InvokeOrder", null);
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl!=null) {
                        if (isInternetPresent) {
                            if (progressView != null)
                                progressView.setVisibility(View.VISIBLE);
                            PostDataTask postDataTask = new PostDataTask(mActivity, new SaveOrderTaskListener(progressView), false);
                            postDataTask.execute(saveData, url, "");
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                        }
                    }else {
                        UtilView.gotToLogin(mActivity);
                    }

                }
            }


        });
        ed_saveordername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            boolean handled = false;

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                handled = true;
                UtilView.hideSoftKeyboard(mActivity, ed_saveordername);
                invoiceDraftName = ed_saveordername.getText().toString();
                if (invoiceDraftName.equals("")) {
                    UtilView.showErrorDialog(getResources().getString(R.string.error_ordername), mActivity);
                } else {
                    String url =serverUrl+ "/retail//" + Constant.FUNTION_SAVEORDER;
                    String saveData = saveposData("", "InvokeOrder", null);
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    progressView.setVisibility(View.VISIBLE);
                    if (serverUrl!=null) {
                        if (isInternetPresent) {
                            PostDataTask postDataTask = new PostDataTask(mActivity, new SaveOrderTaskListener(progressView), false);
                            postDataTask.execute(saveData, url, "");
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                        }
                    }else {
                        UtilView.gotToLogin(mActivity);
                    }
                }
                return handled;
            }
        });
        if (dialog != null)
            dialog.show();
    }
    private void initPrintListDailog() {
        dialog.setContentView(R.layout.dialog_sales_printlist);
        CardView cardViewSalesInvoiceList = dialog.findViewById(R.id.cardViewSalesInvoiceList);
        CardView cardViewSalesQuotation = dialog.findViewById(R.id.cardViewSalesQuotation);
        CardView cardViewSalesOrder = dialog.findViewById(R.id.cardViewSalesOrder);
        CardView cardViewDeliveryOrder = dialog.findViewById(R.id.cardViewDeliveryOrder);
        CardView cardViewLoanDeliveryOrder = dialog.findViewById(R.id.cardViewLoanDeliveryOrder);
        CardView cardViewRecievedPaymentList = dialog.findViewById(R.id.cardViewRecievedPaymentList);
        CardView cardViewAdvancePaymentList = dialog.findViewById(R.id.cardViewAdvancePaymentList);
        CardView cardViewSalesReturn = dialog.findViewById(R.id.cardViewSalesReturn);
        CardView cardViewSalesProforma = dialog.findViewById(R.id.cardViewSalesProforma);
        CardView cardViewDebitNote = dialog.findViewById(R.id.cardViewDebitNote);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
        final ProgressView progress_bar = dialog.findViewById(R.id.progress_bar);

        cardViewRecievedPaymentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    callPrintlistRecievedPaymentList();
                    dialog.dismiss();
                }
            }


        });

        cardViewAdvancePaymentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    callPrintlistAdvancePaymentList();
                    dialog.dismiss();
                }
            }


        });
        cardViewSalesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    callPrintlistSalesOrder();
                    dialog.dismiss();
                }
            }


        });
        cardViewSalesProforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    callPrintlistProForma();
                    dialog.dismiss();
                }
            }


        });


        cardViewDebitNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    callPrintlistSalesDebitNote();
                    dialog.dismiss();
                }
            }


        });

        cardViewSalesReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    callPrintlistSalesReturn();
                    dialog.dismiss();
                }
            }


        });

        cardViewSalesInvoiceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    callPrintlistInvoice();
                    dialog.dismiss();
                }
            }
        });
        cardViewSalesQuotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    callPrintlistSalesQuotation();
                    dialog.dismiss();
                }
            }
        });
        cardViewDeliveryOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    callPrintlistDeliveryOrder();
                    dialog.dismiss();
                }
            }
        });

        cardViewLoanDeliveryOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    callPrintlistLoanDeliveryOrder();
                    dialog.dismiss();
                }
            }
        });





        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
            }
        });

        imgviewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
            }
        });


        if (dialog != null)
            dialog.show();
    }

    public void callPrintlistSalesDebitNote() {
        Intent intent = new Intent(mActivity, Activity_Printlist_SalesDebitNote.class);
        mActivity.startActivityForResult(intent,Constant.REQUESTCODE_PRINTLISTSALESDEBITNOTE);


    }


    public void callPrintlistRecievedPaymentList() {
        Intent intent = new Intent(mActivity, Activity_PaymentReceipt.class);
        mActivity.startActivityForResult(intent,Constant.REQUESTCODE_PRINTLISTSALESRECIEVEDPAYMENT);
    }


    public void callPrintlistLoanDeliveryOrder() {
        Intent intent = new Intent(mActivity, Activity_Printlist_SalesLoanDeliveryOrder.class);
        mActivity.startActivityForResult(intent,Constant.REQUESTCODE_PRINTLISTSALESLOANDELIVERYORDER);
    }

    public void callPrintlistSalesOrder() {
        Intent intent = new Intent(mActivity, Activity_SalesDuplicateSalesOrder.class);
        mActivity.startActivityForResult(intent,Constant.REQUESTCODE_PRINTLISTSALESORDER);
    }

    public void callPrintlistDeliveryOrder() {
        Intent intent = new Intent(mActivity, Activity_Printlist_SalesDeliveryOrder.class);
        mActivity.startActivityForResult(intent,Constant.REQUESTCODE_PRINTLISTSALESDELIVERYORDER);
    }


    public void callPrintlistInvoice() {
        Intent intent = new Intent(mActivity, Activity_SalesDuplicateInvoices.class);
        mActivity.startActivityForResult(intent,Constant.REQUESTCODE_PRINTLISTSALESINVOICE);

    }

    public void callPrintlistSalesQuotation() {
        Intent intent = new Intent(mActivity, Activity_Printlist_SalesQuotation.class);
        mActivity.startActivityForResult(intent,Constant.REQUESTCODE_PRINTLISTSALESQUOTATION);
    }

    public void callPrintlistSalesReturn() {
        Intent intent = new Intent(mActivity, Activity_SalesDuplicateSalesReturn.class);
        mActivity.startActivityForResult(intent,Constant.REQUESTCODE_PRINTLISTSALESRETURN);

    }
    public void callPrintlistAdvancePaymentList() {
        Intent intent = new Intent(mActivity, Activity_Printlist_SalesAdvancedPayment.class);
        mActivity.startActivityForResult(intent,Constant.REQUESTCODE_PRINTLISTSALESADVANCEDPAYMENT);

    }

    public void callPrintlistProForma() {
        Intent intent = new Intent(mActivity, Activity_Printlist_SalesProForma.class);
        mActivity.startActivityForResult(intent,Constant.REQUESTCODE_PRINTLISTPROFORMA);

    }


    public void callDebitNote() {
        Intent intent = new Intent(mActivity, Activity_DebitNote.class);
        mActivity.startActivity(intent);

    }
    public void callCreditNote() {
        Intent intent = new Intent(mActivity, Activity_CreditNote.class);
        mActivity.startActivity(intent);

    }


    public void invokeDraft() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl!=null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALEDRAFTINVOKE;
                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (result != null) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            try {
                                if (invokeDraftOrderList.size() > 0) {
                                    invokeDraftOrderList.clear();
                                }
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    InvokeDraftList order = gson.fromJson(jsonObject.toString(), InvokeDraftList.class);
                                    invokeDraftOrderList.add(order);
                                }
                                String[] orderListArray = new String[invokeDraftOrderList.size()];
                                if (invokeDraftOrderList.size() > 0) {
                                    for (int i = 0; i < invokeDraftOrderList.size(); i++) {
                                        orderListArray[i] = invokeDraftOrderList.get(i).getInvokeOrderNo();
                                    }
                                    dialog.setContentView(R.layout.dialog_sales_invokeorder);
                                    final ProgressView progressView = dialog.findViewById(R.id.progress_bar);
                                    final Spinner spinnerInvokeOrder = dialog.findViewById(R.id.spinnerInvokeOrder);
                                    ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
                                    Button btnClose = dialog.findViewById(R.id.btn_close);
                                    UtilView.setupOrderListAdapter(mActivity, spinnerInvokeOrder, orderListArray);
                                    imgviewClose.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (dialog != null) dialog.dismiss();
                                        }
                                    });
                                    btnClose.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (dialog != null) dialog.dismiss();
                                        }
                                    });
                                    spinnerInvokeOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            String order_no = (String) adapterView.getSelectedItem();
                                            for (int j = 0; j < invokeDraftOrderList.size(); j++) {
                                                if (invokeDraftOrderList.get(j).getInvokeOrderNo().equals(order_no)) {
                                                    invokeOrderId = invokeDraftOrderList.get(j).getId();
                                                    invokeOrderName = invokeDraftOrderList.get(j).getInvokeOrderNo();


                                                    if(serverUrl!=null) {
                                                        if (isInternetPresent) {
                                                            if (progressView != null)
                                                                progressView.setVisibility(View.VISIBLE);
                                                            String url = serverUrl + "/retail//" + Constant.FUNTION_GETINVOKEORDER+"?invokeOrderId="+invokeOrderId;
                                                            GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                                                @Override
                                                                public void onTaskComplete(String result) {
                                                                    if (progressView != null)
                                                                        progressView.setVisibility(View.GONE);
                                                                    if (result != null) {
                                                                        try {
                                                                            Gson gson = new Gson();
                                                                            JSONObject jsonObject = new JSONObject(result.toString());
                                                                            InvokeDraftData invokeDraftData = gson.fromJson(jsonObject.toString(), InvokeDraftData.class);
                                                                            salesposCreator.clear();
                                                                            if (invokeDraftData != null) {
                                                                                if (invokeDraftData.getSelectedItemsList().size() > 0) {
                                                                                    for (int i=0;i<invokeDraftData.getSelectedItemsList().size();i++){
                                                                                        SelectedItemsList item = invokeDraftData.getSelectedItemsList().get(i);
                                                                                        SelectedItemsList invokeItems = new SelectedItemsList();
                                                                                        invokeItems.setItemName(item.getItemName());
                                                                                        invokeItems.setItemId(item.getItemId());
                                                                                        invokeItems.setQty(item.getQty());
                                                                                        invokeItems.setItemQuantity(item.getQty());
                                                                                        invokeItems.setReturnQty(item.getReturnQty());
                                                                                        invokeItems.setRemainingQty(item.getRemainingQty());
                                                                                        invokeItems.setAmtexclusivetax(item.getAmtexclusivetax());
                                                                                        invokeItems.setTaxid(item.getTaxid());
                                                                                        invokeItems.setTaxpercent(String.valueOf(item.getTaxpercent()));
                                                                                        invokeItems.setTaxamt(item.getTaxamt());
                                                                                        invokeItems.setAmtinclusivetax(item.getAmtinclusivetax());
                                                                                        invokeItems.setDiscountAmt(item.getDiscountAmt());
                                                                                        invokeItems.setItemDescription(item.getItemDescription());
                                                                                        invokeItems.setTaxName(item.getTaxName());
                                                                                        invokeItems.setItemCode(item.getItemCode());
                                                                                        invokeItems.setCess(item.getCess());
                                                                                        invokeItems.setTaxAmountSplit("" + item.getTaxPercentageSplit());
                                                                                        invokeItems.setCessTaxAmt(item.getCessTaxAmt());
                                                                                        invokeItems.setHsnCode(item.getHsnCode());
                                                                                        invokeItems.setUomName(item.getUomName());
                                                                                        invokeItems.setItemTotalAmount(item.getAmtinclusivetax());
                                                                                        invokeItems.setUnitPrice(item.getUnitPrice());
                                                                                        invokeItems.setUnitPriceIn(item.getUnitPrice());
                                                                                        invokeItems.setUom(item.getUom());
                                                                                        invokeItems.setUomConvertorList(item.getUomConvertorList());
                                                                                        invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                                                                                        invokeItems.setInclusiveJSON(item.getInclusiveJSON());
                                                                                        invokeItems.setSerializableStatus(item.getSerializableStatus());
                                                                                        salesposCreator.addItem(new SalesPosCartItem(invokeItems, 1));
                                                                                    }
                                                                                    checkCartList("");
                                                                                    if (dialog != null && dialog.isShowing()) {
                                                                                        dialog.dismiss();
                                                                                    }
                                                                                } else {
                                                                                    UtilView.showToast(mActivity, getResources().getString(R.string.items_notavailbale));
                                                                                }
                                                                            } else {
                                                                                UtilView.showToast(mActivity, getResources().getString(R.string.items_notavailbale));
                                                                            }
                                                                        } catch (Exception e) {
                                                                            UtilView.showLogCat(TAG, "DataTaskListener Exception " + e.toString());
                                                                        }
                                                                    }
                                                                }
                                                            }, false);
                                                            getOrderList.execute(url, "");
                                                        } else {
                                                            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                                                        }
                                                    }else {
                                                        UtilView.gotToLogin(mActivity);
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {
                                        }
                                    });
                                    if (dialog != null)
                                        dialog.show();
                                } else {
                                    UtilView.showToast(mActivity, getResources().getString(R.string.no_orders_msg));
                                }
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                        }
                    }

                }, false);
                getOrderList.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    public void invokeClearDraft() {

        String url = serverUrl + "/retail//" + Constant.FUNTION_CLEARINVOKEORDER;
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl!=null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (result != null) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            try {
                                UtilView.showToast(mActivity, "Draft Cleared Successfuly.");
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                        }
                    }
                }, false);
                postDataTask.execute(new Gson().toString(), url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        salesposCreator.clear();
        flag = 0;
        isCheckoutable = true;

    }

    @Override
    public void onResume() {
        super.onResume();
        fabMenus.close(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    public void callRecievePayment(){
        if (selected_customer != null) {
            Intent intent = new Intent(mActivity, Activity_SalesRecievePayment.class);
            intent.putExtra("customer", selected_customer);
            intent.putExtra("selectedTax", selectedTax);
            intent.putExtra("selectedDate", selectedDate);
            mActivity.startActivity(intent);
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.selectCustomerError));
        }
    }

    private class SaveOrderTaskListener implements AsyncTaskCompleteListener<String> {
        ProgressView pg;

        public SaveOrderTaskListener(ProgressView progressView) {
            pg = progressView;
        }

        @Override
        public void onTaskComplete(String result) {
            if (pg != null)
                pg.setVisibility(View.GONE);
            if (result != null) {
                if (result.toString().equals(getResources().getString(R.string.error_rsonsecode204))) {
                    UtilView.showSuccessDialog("Order saved successfully", mActivity);
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    salesposCreator.clear();
                    flag = 0;
                    isCheckoutable = true;
                    checkCartList("");
                } else {
                    UtilView.showErrorDialog("Order not saved successfully", mActivity);
                }
            }
        }
    }
}
