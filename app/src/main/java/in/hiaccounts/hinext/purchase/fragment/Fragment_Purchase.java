package in.hiaccounts.hinext.purchase.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.lowagie.text.DocumentException;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.hinext.checkout.activity.Activity_Checkout;
import in.hiaccounts.hinext.checkout.model.PurchaseItem;
import in.hiaccounts.hinext.checkout.model.PurchaseSaveData;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.purchase.activity.ActivityPurchase_AdvancePayment;
import in.hiaccounts.hinext.purchase.activity.Activity_CreditNote;
import in.hiaccounts.hinext.purchase.activity.Activity_CreditNoteList;
import in.hiaccounts.hinext.purchase.activity.Activity_DebitNote;
import in.hiaccounts.hinext.purchase.activity.Activity_InvokePurchaseOrder;
import in.hiaccounts.hinext.purchase.activity.Activity_InvokeQuotation;
import in.hiaccounts.hinext.purchase.activity.Activity_InvokeReceiveItem;
import in.hiaccounts.hinext.purchase.activity.Activity_PurchaseDuplicateInvoices;
import in.hiaccounts.hinext.purchase.activity.Activity_PurchaseDuplicatePO;
import in.hiaccounts.hinext.purchase.activity.Activity_PurchaseDuplicatePRetrun;
import in.hiaccounts.hinext.purchase.activity.Activity_PurchaseDuplicatePaymnetList;
import in.hiaccounts.hinext.purchase.activity.Activity_PurchaseInvoice_ReturnItem;
import in.hiaccounts.hinext.purchase.activity.Activity_PurchaseQuotationItemList;
import in.hiaccounts.hinext.purchase.activity.Activity_PurchaseRecieveItemList;
import in.hiaccounts.hinext.purchase.activity.Activity_PurchaseReturn;
import in.hiaccounts.hinext.purchase.activity.Activity_ReturnReceiveItem;
import in.hiaccounts.hinext.purchase.activity.Activity_SupplierPayment;
import in.hiaccounts.hinext.purchase.adapter.PurchaseReturn_Adapter;
import in.hiaccounts.hinext.purchase.adapter.Purchasepos_Adapter;
import in.hiaccounts.hinext.purchase.model.purchase_invoicereturn.InvoiceReturn_Checkout;
import in.hiaccounts.hinext.purchase.model.purchase_invoicereturn.PurchaseInvoiceReturnData;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.CmpylocationList;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.HinextPurchasePageData;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.SupplocationList;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCartItem;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCreator;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosHelper;
import in.hiaccounts.hinext.purchase.model.purchase_pos.SelectedItemsList;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseInvokeOrderData;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseInvokeOrderListData;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseOrderData;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseOrderItem;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseOrderSave;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.supplier.activity.Activity_Supplier;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.purchase.purchase_orders.PosA4PurchaseOrderPdfImpl;
import in.hiaccounts.pdfgenerator.purchase.purchase_orders.PosPurchaseOrderPdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.id.progress_bar;
import static in.hiaccounts.R.string.response_error;
import static in.hiaccounts.utility.Constant.OPERATION_RETURN;

/**
 * Created by Prateek on 7/20/2017.
 */

public class Fragment_Purchase extends Fragment {

    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private Checkout_ResponseData invoiceData = null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;

    @BindView(R.id.edDate)
    EditText edDate;
    @BindView(R.id.btnSelectItem)
    Button btnSelectItem;
    @BindView(R.id.tvSelectSupplier)
    TextView tvSelectSupplier;
    @BindView(R.id.spinnerTaxSelection)
    Spinner spinnerTaxSelection;
    @BindView(R.id.spinnerCompanyLocation)
    Spinner spinnerCompanyLocation;
    @BindView(R.id.spinnerCustomerLocation)
    Spinner spinnerCustomerLocation;
    @BindView(R.id.listviewSelectItems)
    ListView listviewSelectItems;
    @BindView(R.id.edTotalAmount)
    EditText edTotalAmount;
    @BindView(R.id.chkBoxAdvancePayments)
    CheckBox chkBoxAdvancePayments;
    @BindView(R.id.chkBoxReverseCharge)
    CheckBox chkBoxReverseCharge;
    @BindView(R.id.btnCheckout)
    Button btnCheckout;
    @BindView(R.id.ll_total)
    LinearLayout llTotal;
    @BindView(R.id.ll_page)
    LinearLayout llPage;
    @BindView(progress_bar)
    ProgressView progressBar;
    @BindView(R.id.fabclearall)
    FloatingActionButton fabclearall;
    @BindView(R.id.fabPIreturn)
    FloatingActionButton fabPIreturn;
    @BindView(R.id.fabsupplierpayment)
    FloatingActionButton fabsupplierpayment;
    @BindView(R.id.fabadvancepayment)
    FloatingActionButton fabadvancepayment;
    @BindView(R.id.fabInvokePurchaseOrder)
    FloatingActionButton fabInvokePurchaseOrder;
    @BindView(R.id.fabsavepurchaseorder)
    FloatingActionButton fabsavepurchaseorder;
    @BindView(R.id.fabcreditnote)
    FloatingActionButton fabcreditnote;
    @BindView(R.id.fabsavequotation)
    FloatingActionButton fabsavequotation;
    @BindView(R.id.fabinvokequotation)
    FloatingActionButton fabinvokequotation;
    @BindView(R.id.fabreturnreceiveitem)
    FloatingActionButton fabreturnreceiveitem;
    @BindView(R.id.fabinvokereceiveitem)
    FloatingActionButton fabinvokereceiveitem;
    @BindView(R.id.fabsavereceiveitem)
    FloatingActionButton fabsavereceiveitem;
    @BindView(R.id.fabMenus)
    FloatingActionMenu fabMenus;
    @BindView(R.id.btnReturnPurchase)
    Button btnReturnPurchase;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.background_dimmer)
    View backgroundDimmer;
    @BindView(R.id.fabprintlist)
    FloatingActionButton fabprintlist;
    @BindView(R.id.tvSelectTax)
    TextView tvSelectTax;
    @BindView(R.id.rlListview)
    RelativeLayout rlListview;
    Unbinder unbinder;


    EditText edReturnQuatity;
    LinearLayout llReturnQuantity;
    EditText edCessAmt;
    EditText edItemName;
    EditText edItemDescritpion;
    EditText edItemUnitPrice;
    EditText edItemQuantity;
    EditText edItemAmtExTax;
    TextView textView10;
    Spinner spinItemInputtax;
    TextView tvTaxType, tvItemInputtax, tvQuantity;
    EditText edTaxTypeAmount;
    EditText edCessPercantage;
    EditText edItemTotalTaxamt;
    EditText edItemDiscount;
    EditText itemAmtIncTax;
    Spinner spinItemSerialNumber;
    Button btnClose;
    Button btnAdd, btnMore, btnLess;
    LinearLayout llMoreOrLess;
    private boolean isAllValid = true;

    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private ServiceHandler serviceHandler;
    public static String TAG = Fragment_Purchase.class.getSimpleName();
    public static List<SelectedItemsList> selectItemForDelete = new ArrayList<SelectedItemsList>();
    private HinextPurchasePageData pageData;
    private GetSupplier supplier;
    private PurchaseOrderData orderData;
    private String selectedTax, serverUrl;
    private PurchasePosCreator purchasePosCreator;
    private Purchasepos_Adapter cartItemAdapter;
    private PurchaseInvoiceReturnData purchaseReturnData = null;
    private List<PurchaseInvokeOrderListData> purchaseOrderList;

    BigDecimal cgstTaxAmtValue = BigDecimal.ZERO;
    BigDecimal cessTaxamt = BigDecimal.ZERO;
    String taxString = "";
    BigDecimal itemTotalamt = BigDecimal.ZERO;

    BigDecimal totalTaxAmt = BigDecimal.ZERO;
    BigDecimal cessPercantage = BigDecimal.ZERO;
    BigDecimal taxPercantage = BigDecimal.ZERO;
    public static boolean discountRight = false;
    public static boolean isCheckoutable = true;
    public static int flag = 0;
    boolean isAdvancePayment = false;
    boolean  isReverseChargeInvoice= false;

    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();
    private Dialog dialog;
    private PurchaseInvoiceReturnData purchaseInvoiceData;
    private String selectedDate = "", operationType = "",receiveItemId ="",formNo="",returnItem="";
    Long recieveItemId,quotationId;
    Integer piid;
    final SupplocationList[] selectedSupplierLocation = {null};
    final CmpylocationList[] selectedCompanyLocation = {null};

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_hinextpurchase, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view, savedInstanceState);
        return view;
    }

    private void initComponents(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        purchasePosCreator = PurchasePosHelper.getPosCreator();
        visibilityGone();
        dialog = new Dialog(mActivity);
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
            if (pageData != null) {
                setupPageData();
            }
        } else {
            getPageLoadData();
        }



        listviewSelectItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editPosItem(position);
            }


        });

        checkCartList("");
    }

    private void editPosItem(final int position) {

        dialog.setContentView(R.layout.dialog_sales_positem);
        dialog.setCancelable(false);
        btnMore = dialog.findViewById(R.id.btnMore);
        btnLess = dialog.findViewById(R.id.btnLess);
        llMoreOrLess = dialog.findViewById(R.id.llMoreOrLess);
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
        llReturnQuantity = dialog.findViewById(R.id.llReturnQuantity);
        edReturnQuatity = dialog.findViewById(R.id.edReturnQuatity);
        tvItemInputtax = dialog.findViewById(R.id.tvItemInputtax);
        tvQuantity = dialog.findViewById(R.id.tvQuantity);

        LinearLayout llDiscount = dialog.findViewById(R.id.llDiscount);
        llDiscount.setVisibility(View.GONE);

        if (btnAdd != null)
            btnAdd.setText("Edit");

        PurchasePosCartItem posCartItem = purchasePosCreator.getItems().get(position);
        final SelectedItemsList itemDatum = posCartItem.getItem();
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
            edItemQuantity.setText("" + itemDatum.getReturnQty());
        } else {
            llReturnQuantity.setVisibility(View.GONE);
            tvQuantity.setText("Quantity");
            edItemQuantity.setText("" + itemDatum.getItemQuantity());
        }


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
        if (itemDatum.getItemName() != null)
            edItemName.setText(itemDatum.getItemName());
        if (itemDatum.getItemDescription() != null)
            edItemDescritpion.setText(itemDatum.getItemDescription());
        //  edItemQuantity.setText("" + itemDatum.getItemQuantity());
        if (itemDatum.getUnitPrice() != null)
        {
            BigDecimal unitPrice = new BigDecimal(itemDatum.getUnitPrice());
            edItemUnitPrice.setText("" + unitPrice.setScale(2,BigDecimal.ROUND_HALF_UP));
        }
        if (itemDatum.getAmtexclusivetax() != 0)
        {
            BigDecimal itemAmtExTax = new BigDecimal(itemDatum.getAmtexclusivetax());
            edItemAmtExTax.setText("" + itemAmtExTax.setScale(2,BigDecimal.ROUND_HALF_UP));
        }

        if (itemDatum.getTaxAmountSplit() != null)
            edTaxTypeAmount.setText("" + itemDatum.getTaxAmountSplit());
        if (selectedTax != null) {
            tvTaxType.setText(selectedTax);
            BigDecimal taxAmt = new BigDecimal(itemDatum.getIgTax());
            if (selectedTax.equals(getResources().getString(R.string.igst))) {
                edTaxTypeAmount.setText("" + taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if (selectedTax.equals(getResources().getString(R.string.cgst))) {
                BigDecimal bgTax = taxAmt.divide(BigDecimal.valueOf(2));
                edTaxTypeAmount.setText("" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP) + "+" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        }

        edCessPercantage.setText("" + itemDatum.getCess());
        edItemTotalTaxamt.setText("" + itemDatum.getTaxamt());
        edItemDiscount.setText("" + itemDatum.getDiscountAmt());
        itemAmtIncTax.setText("" + itemDatum.getAmtinclusivetax());
        edCessAmt.setText("" + itemDatum.getCessTaxAmt());

        if (dialog != null)
            dialog.show();

        String defaultTax = "";

        if (pageData != null) {
            if (pageData.getTaxList() != null && pageData.getTaxList().size() > 0) {
                UtilView.setupHinextPurchaseTaxAdapter(mActivity, spinItemInputtax, pageData.getTaxList());
                for (int i = 0; i < pageData.getTaxList().size(); i++) {
                    if (itemDatum.getTaxid() == (pageData.getTaxList().get(i).getTaxid())) {
                        spinItemInputtax.setSelection(i);
                        defaultTax = pageData.getTaxList().get(i).getTaxString();
                        setTaxString(defaultTax);
                        tvItemInputtax.setText(defaultTax);
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
                                updateByQuantityTotalExTax(itemDatum, quantity);
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


                    String amtEx = edItemAmtExTax.getText().toString().trim();

                    double amtExc = Double.parseDouble(amtEx);
                    double amTax = Double.parseDouble(String.valueOf(totalTaxAmt));

                    if ((amtExc + amTax) > Double.parseDouble(discount)) {
                        updateDiscountPrice(itemDatum, Double.parseDouble(discount));
                    } else {
                        if (s.toString() == null || s.toString().equals("")) {
                            edItemDiscount.setError(null);
                            flag = 0;
                            isCheckoutable = true;

                        } else {
                            flag = 7;
                            isCheckoutable = false;

                            edItemDiscount.setError("Amount should be less than total amount.");
                        }
                    }
                } catch (NumberFormatException e) {
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
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0 && isCheckoutable) {

                    if (edItemUnitPrice.getText().toString() == null || edItemUnitPrice.getText().toString().equals("") ||
                            edItemUnitPrice.getText().toString().equals("0.00") || edItemUnitPrice.getText().toString().equals("0")) {
                        UtilView.showToast(mActivity, "Unit price can't be Zero");
                    }

                    if (edItemUnitPrice.getText().toString() == null || edItemUnitPrice.getText().toString().equals("") ||
                            edItemUnitPrice.getText().toString().equals("0.00") || edItemUnitPrice.getText().toString().equals("0")) {
                        UtilView.showToast(mActivity, "Quantity can't be Zero");
                    } else {

                        String description = edItemDescritpion.getText().toString().trim();

                        Long itmQuantity = Long.parseLong(edItemQuantity.getText().toString().trim());
                        if (operationType.equals(Constant.OPERATION_RETURN)) {
                            long invoQty = Long.parseLong(edReturnQuatity.getText().toString());
                            itemDatum.setItemQuantity(invoQty);
                            itemDatum.setQty("" + invoQty);
                            itemDatum.setReturnQty(itmQuantity);
                            itemDatum.setRemainingQty("" + (invoQty - itmQuantity));
                        } else {
                            itemDatum.setItemQuantity(itmQuantity);
                            itemDatum.setQty("" + itmQuantity);
                        }


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
                        //  itemDatum.setItemQuantity(itmQuantity);
                        itemDatum.setQty("" + itmQuantity);
                        itemDatum.setUnitPrice(Double.valueOf(unitPrice));
                        itemDatum.setPrice(new BigDecimal(unitPrice).setScale(2, BigDecimal.ROUND_HALF_UP));
                        itemDatum.setAmtexclusivetax(amtExTX);

                        itemDatum.setTaxTypeSelection(selectedTax);
                        //itemDatum.setCgstsgstsplitvalues(String.valueOf(cgstTaxAmtValue));
                        itemDatum.setTaxAmountSplit(edTaxTypeAmount.getText().toString().trim());

                        itemDatum.setCess(Double.parseDouble(edCessPercantage.getText().toString().trim()));

                        itemDatum.setCessTaxAmt(Double.parseDouble(String.valueOf(cessTaxamt)));
                        itemDatum.setTaxamt(amtTotalTax);
                        itemDatum.setDiscountAmt(discount);
                        //itemDatum.setDiscountAmountRpercent(discount);
                        itemDatum.setAmtinclusivetax(amtIncTax);
                        itemDatum.setItemTotalAmount(amtIncTax);


                        purchasePosCreator.update(new PurchasePosCartItem(itemDatum, 1), position);
                        checkCartList(operationType);
                        showTotalPrice();
                        dialog.dismiss();
                    }
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
                }


            }
        });

    }

    public String getTaxString() {
        return taxString;
    }

    public void setTaxString(String taxString) {
        this.taxString = taxString;
    }

    private void updateTaxAmount(BigDecimal taxPercantage, SelectedItemsList itemDatum, EditText edTaxTypeAmount, EditText edItemAmtExTax, BigDecimal cessPercantage) {
        try {

            String discout = "";
            if (edItemDiscount != null)
                discout = edItemDiscount.getText().toString().trim();
            if (discout.equals("")) {
                discout = "0";
            }
            BigDecimal taxAmt = BigDecimal.valueOf(0);
            String amtEx = "";
            if (edItemAmtExTax != null)
                amtEx = edItemAmtExTax.getText().toString().trim();
            BigDecimal amtExTax;
            if (amtEx.equals("") || amtEx == null) {
                amtExTax = BigDecimal.ZERO;
            } else {
                amtExTax = BigDecimal.valueOf(Double.parseDouble(amtEx));
            }
            taxAmt = taxAmt.add(amtExTax.subtract(new BigDecimal(discout)).multiply(taxPercantage.divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_UP));

            cgstTaxAmtValue = taxAmt;
            if (selectedTax != null) {
                if (selectedTax.equals(getResources().getString(R.string.igst))) {
                    edTaxTypeAmount.setText("" + taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (selectedTax.equals(getResources().getString(R.string.cgst))) {
                    BigDecimal bgTax = taxAmt.divide(BigDecimal.valueOf(2));
                    edTaxTypeAmount.setText("" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP) + "+" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
            }


            cessTaxamt = amtExTax.subtract(new BigDecimal(discout)).multiply(cessPercantage.divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_UP);
            edCessAmt.setText("" + cessTaxamt);
            itemDatum.setCessTaxAmt(Double.parseDouble(String.valueOf(cessTaxamt)));

            totalTaxAmt = taxAmt.add(cessTaxamt);
            edItemTotalTaxamt.setText("" + totalTaxAmt);
            itemTotalamt = amtExTax.add(totalTaxAmt).subtract(new BigDecimal(discout));

            itemDatum.setAmtinclusivetax(Double.parseDouble(String.valueOf(itemTotalamt.setScale(2, BigDecimal.ROUND_HALF_UP))));
            if (itemAmtIncTax != null)
                itemAmtIncTax.setText(String.valueOf(itemTotalamt.setScale(2, BigDecimal.ROUND_HALF_UP)));
            itemDatum.setTaxamt(Double.parseDouble(String.valueOf(totalTaxAmt)));

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
        BigDecimal totalPrice = BigDecimal.ZERO;


        totalPrice = totalPrice.add(itemDatum.getPrice().multiply(BigDecimal.valueOf(quantity)).setScale(2, BigDecimal.ROUND_HALF_UP));
        if (edItemAmtExTax != null)
            edItemAmtExTax.setText("" + totalPrice);
        if (quantity == 0) {
            if (edItemQuantity != null)
                edItemQuantity.setText("0");
        }
        String discount = "";
        if (edItemDiscount != null)
            discount = edItemDiscount.getText().toString().trim();
        if (discount.equals("")) {
            discount = "0";
        }
        double amtIncTax;
        if (itemAmtIncTax != null) {
            if (itemAmtIncTax.getText().toString().trim() == null || itemAmtIncTax.getText().toString().trim().equals("")) {
                amtIncTax = 0;
            } else {
                amtIncTax = Double.parseDouble(itemAmtIncTax.getText().toString().trim());
            }
            itemAmtIncTax.setText("" + (amtIncTax - Double.parseDouble(discount)));
        }
    }

    private void updateDiscountPrice(SelectedItemsList itemDatum, double discount) {
        String amtEx = "";
        if (edItemAmtExTax != null)
            amtEx = edItemAmtExTax.getText().toString().trim();

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


    private void getPageLoadData() {
        String url = serverUrl + "/purchase//0/" + Constant.FUNCTION_GETPAGELOADDATA;
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                sharedPreference.saveData(Constant.HINEXTPURCHASESPAGEDATA_KEY, result.toString());
                                if (llPage != null)
                                    llPage.setVisibility(View.VISIBLE);
                                Gson gson = new Gson();
                                pageData = gson.fromJson(result.toString(), HinextPurchasePageData.class);
                                setupPageData();
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            } else {
                UtilView.showErrorDialog(mActivity.getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setupPageData() {
        if (pageData != null) {
            if (pageData.getSuppliers() != null && pageData.getSuppliers().size() > 0) {
                supplier = new GetSupplier();

                if (pageData.getSuppliers().get(0).getSupplierEmail() != null)
                    supplier.setSupplierEmail(pageData.getSuppliers().get(0).getSupplierEmail());
                if (pageData.getSuppliers().get(0).getSupplierNumber() != null)
                    supplier.setSupplierNumber(pageData.getSuppliers().get(0).getSupplierNumber());
                if (pageData.getSuppliers().get(0).getSupplierName() != null)
                    supplier.setSupplierName(pageData.getSuppliers().get(0).getSupplierName());
                if (pageData.getSuppliers().get(0).getSupplierId() != null)
                    supplier.setSupplierId(pageData.getSuppliers().get(0).getSupplierId());
                if (supplier.getSupplierNumber() != null) {
                    tvSelectSupplier.setText(supplier.getSupplierName() + "|" + supplier.getSupplierNumber());
                } else {
                    tvSelectSupplier.setText(supplier.getSupplierName() + "|0");
                }
                Gson gson = new Gson();
                sharedPreference.saveData(Constant.SUPPLIER, gson.toJson(supplier));
                callSupplierLocation(supplier.getSupplierId());
            }
            if (pageData.getCmpylocationList() != null) {
                ArrayAdapter<CmpylocationList> spinnerCompanyLocationAdapter = UtilView.setupPurchaseCompanyLocationAdapter(mActivity, spinnerCompanyLocation, pageData.getCmpylocationList());
                selectedCompanyLocation[0] = spinnerCompanyLocationAdapter.getItem(0);
            }

        }
        spinnerCustomerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSupplierLocation[0] = (SupplocationList) adapterView.getSelectedItem();
                updateGstTax();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
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
        Long companyLocationId = null, supplierLocationId = null;
        if (selectedCompanyLocation[0] != null) {
            if (selectedCompanyLocation[0].getStateId() != null) {
                companyLocationId = selectedCompanyLocation[0].getStateId().getId();
            }
        }
        if (selectedSupplierLocation[0] != null) {
            if (selectedSupplierLocation[0].getNextref() != null) {
                supplierLocationId = selectedSupplierLocation[0].getNextref();
            }
        }

        if (companyLocationId.equals(supplierLocationId)) {
            tvSelectTax.setText(getResources().getString(R.string.cgst));
            selectedTax = tvSelectTax.getText().toString().trim();
        }
        if (!companyLocationId.equals(supplierLocationId)) {
            tvSelectTax.setText(getResources().getString(R.string.igst));
            selectedTax = tvSelectTax.getText().toString().trim();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    private void callItemView() {
        if (serverUrl != null) {
            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
            Intent intent = new Intent(mActivity, Activity_ShowItemList.class);
            intent.putExtra("url", url);
            intent.putExtra("taxType", selectedTax);
            intent.putExtra("itemsearch", "");
            intent.putExtra("selectedsupplier", supplier);
            intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_PURCHASE);
            startActivityForResult(intent, Constant.RESQUSTCODE_ITEMSEARCH);
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_purchase, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (purchasePosCreator != null && purchasePosCreator.getItems().size() > 0) {
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

            case R.id.action_settings:
                ((NavigationDrawer_Activity)mActivity).openRightDrawer(Constant.MODULE_PURCHASE);
                break;
            case R.id.delete_items:
                String getPageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
                if (getPageLoadData !=null) {

                    Gson gson = new Gson();
                    PageLoadDataForAll pageData = gson.fromJson(getPageLoadData, PageLoadDataForAll.class);
                    if (pageData != null) {
                        if (pageData.getUserAccessRights().getPurRemoveItem() == true)
                        {
                            if (selectItemForDelete != null && selectItemForDelete.size() > 0) {
                                purchasePosCreator.delete(selectItemForDelete);
                                checkCartList("");
                                selectItemForDelete.clear();
                            } else {
                                UtilView.showToast(mActivity, "Please select at leat one item to delete");
                            }
                        }

                    }
                }

                break;

        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_SUPPLIER && resultCode == Activity.RESULT_OK) {
            supplier = (GetSupplier) data.getSerializableExtra("supplier");
            if (supplier != null) {
                if (supplier.getSupplierName() != null) {
                    if (supplier.getSupplierNumber() != null) {
                        tvSelectSupplier.setText(supplier.getSupplierName() + "|" + supplier.getSupplierNumber());
                    } else {
                        tvSelectSupplier.setText(supplier.getSupplierName() + "|0");
                    }

                }
                if (supplier.getSupplierId() != null) {
                    callSupplierLocation(supplier.getSupplierId());
                }
            }
        }
        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == Activity.RESULT_OK) {
            checkCartList("");
        }

        if (requestCode == Constant.RESQUSTCODE_INVOKEQUOTATION && resultCode == Activity.RESULT_OK) {

            checkCartList("");
        }

        if (requestCode == Constant.RESQUSTCODE_INVOKEPURCHASEORDER && resultCode == Activity.RESULT_OK) {

            checkCartList("");
        }

        if (requestCode == Constant.RESQUSTCODE_ADDRECIEVEITEM && resultCode == Activity.RESULT_OK) {
            checkCartList("");
        }
        if (requestCode == Constant.RESQUSTCODE_RETRUNRECIEVEITEM && resultCode == Activity.RESULT_OK) {
            returnItem = "Return Recieve Item";
            recieveItemId = data.getLongExtra("returnitemid",0);
            formNo = data.getStringExtra("invoiceNo");
            checkCartList("Return");
        }

        if (requestCode == Constant.RESQUSTCODE_PURCHASEINVOICERETURNITEM && resultCode == Activity.RESULT_OK) {
            returnItem ="PI Return Item";
            purchaseReturnData = (PurchaseInvoiceReturnData) data.getSerializableExtra("returnItemData");
            piid = purchaseReturnData.getPiid();
            operationType = purchaseReturnData.getOperation();

            checkCartList("Return");
        }




        if (requestCode == Constant.RESQUSTCODE_ADDPURCHASEORDER && resultCode == Activity.RESULT_OK){
            checkCartList("");
        }
        if (requestCode == Constant.RESQUSTCODE_ADDPURCHASERECEIVEITEM && resultCode == Activity.RESULT_OK){
            recieveItemId = data.getLongExtra("recieveitemId",0);
            checkCartList("");
        }
        if (requestCode == Constant.RESQUSTCODE_ADDPURCHASEQUOTATIONITEM && resultCode == Activity.RESULT_OK){
            quotationId = data.getLongExtra("quotationId",0);
            checkCartList("");
        }





        if (requestCode == Constant.RESQUSTCODE_CHECKOUT && resultCode == Activity.RESULT_OK) {
            if (purchasePosCreator != null)
                purchasePosCreator.clear();
            checkCartList("");
        }
        if (requestCode == 101) {
            if (purchasePosCreator != null)
                purchasePosCreator.clear();
            checkCartList("");
         /*   Intent intent = new Intent(mActivity, NavigationDrawer_Activity.class);
            intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_PURCHASE);
            startActivity(intent);*/
        }
    }

    private void callSupplierLocation(Long supplierId) {

        String url = serverUrl + "/purchase//" + supplierId + "/supplierLocation";
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            List<SupplocationList> locationList = new ArrayList<>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                if (jsonArray != null) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        SupplocationList location = gson.fromJson(jsonObject.toString(), SupplocationList.class);
                                        locationList.add(location);
                                    }
                                    if (locationList != null && locationList.size() > 0) {
                                        ArrayAdapter<SupplocationList> spinnerInventoryLocationAdapter = UtilView.setupPurchaseCompLocationAdapter(mActivity, spinnerCustomerLocation, locationList);
                                        selectedSupplierLocation[0] = spinnerInventoryLocationAdapter.getItem(0);
                                        updateGstTax();
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
                    jsonObject.put("supplierIdForLoc", supplier.getSupplierId());
                    postDataTask.execute(jsonObject.toString(), url, "");
                } catch (JSONException je) {
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    public void checkCartList(String opration) {
        operationType = opration;
        if (fabMenus != null)
            fabMenus.close(true);
        if (purchasePosCreator != null) {
            if (purchasePosCreator.getItems().size() > 0) {
                if (opration.equals(OPERATION_RETURN)) {
                    setViewUnFocusable();
                } else {
                    setViewFocusable();
                }
                mActivity.invalidateOptionsMenu();
                showTotalPrice();
                List<PurchasePosCartItem> posCartItems = purchasePosCreator.getItems();
                cartItemAdapter = new Purchasepos_Adapter(mActivity, posCartItems, operationType);
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
                List<PurchasePosCartItem> posCartItems = purchasePosCreator.getItems();
                if (cartItemAdapter != null) {
                    cartItemAdapter.updateCartItems(posCartItems);
                    cartItemAdapter.notifyDataSetChanged();
                }
                mActivity.invalidateOptionsMenu();

                if (opration.equals("Return")) {
                    setViewUnFocusable();
                } else {
                    setViewFocusable();
                }
            }
        } else {
            mActivity.invalidateOptionsMenu();
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
        if (tvSelectSupplier != null)
            tvSelectSupplier.setClickable(true);
        if (btnReturnPurchase != null)
            btnReturnPurchase.setVisibility(View.GONE);

    }

    private void setViewUnFocusable() {
        if (btnReturnPurchase != null)
            btnReturnPurchase.setVisibility(View.VISIBLE);
        if (btnCheckout != null)
            btnCheckout.setVisibility(View.GONE);
        if (chkBoxAdvancePayments != null)
            chkBoxAdvancePayments.setVisibility(View.GONE);
        if (chkBoxReverseCharge != null)
            chkBoxReverseCharge.setVisibility(View.GONE);
        if (btnSelectItem != null)
            btnSelectItem.setClickable(false);
        if (tvSelectSupplier != null)
            tvSelectSupplier.setClickable(false);


    }

    private void visibilityGone() {
        if (llTotal != null)
            llTotal.setVisibility(View.GONE);
        if (listviewSelectItems != null)
            listviewSelectItems.setVisibility(View.GONE);
        if (rlListview != null) {
            rlListview.setVisibility(View.GONE);
        }
    }

    private void visibilityVisible() {

        if (rlListview != null) {
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
        if (purchasePosCreator != null) {
            for (int i = 0; i < purchasePosCreator.getItems().size(); i++) {
                posCartTotal += purchasePosCreator.getItems().get(i).getItem().getItemTotalAmount();
            }
            edTotalAmount.setText(String.valueOf(new BigDecimal(posCartTotal).setScale(2, BigDecimal.ROUND_HALF_UP)));
            if (llTotal != null)
                llTotal.setVisibility(View.VISIBLE);
            purchasePosCreator.setTotalPrice(posCartTotal);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        purchasePosCreator.clear();
        flag = 0;
        isCheckoutable = true;
    }


    private String saveposData(String paymentType, String opertaion) {
        List<PurchasePosCartItem> posCartItems = purchasePosCreator.getItems();
        List<PurchaseItem> purchaseItemsList = new ArrayList<>();
        Iterator<PurchasePosCartItem> itr = posCartItems.iterator();
        float totalTaxAmt = 0.00f;
        double totalCessTaxAmt = 0.00;
        double totalDiscountAmt = 0.00;

        Gson gson = new Gson();
        while (itr.hasNext()) {
            PurchasePosCartItem posCartItem = itr.next();
            SelectedItemsList itemDatum = posCartItem.getItem();
            PurchaseItem purchaseItem = new PurchaseItem();

            purchaseItem.setAmtexclusivetax(itemDatum.getAmtexclusivetax());
            purchaseItem.setAmtinclusivetax(itemDatum.getAmtinclusivetax());
            purchaseItem.setCess(itemDatum.getCess());
            purchaseItem.setCessTaxAmt(itemDatum.getCessTaxAmt());
            purchaseItem.setDiscountAmt(itemDatum.getDiscountAmt());
            purchaseItem.setHsnCode(itemDatum.getHsnCode());
            purchaseItem.setIgTax(itemDatum.getIgTax());
            purchaseItem.setDiscountInPercent(itemDatum.isDiscountInPercent());
            purchaseItem.setItemCode(itemDatum.getItemCode());
            purchaseItem.setItemDescription(itemDatum.getItemDescription());
            purchaseItem.setItemId(itemDatum.getItemId());
            purchaseItem.setItemName(itemDatum.getItemName());
            purchaseItem.setQty(itemDatum.getItemQuantity());
            purchaseItem.setRemainingQty(itemDatum.getItemQuantity());
            purchaseItem.setReturnQty(itemDatum.getReturnQty());
            purchaseItem.setSerializableStatus(itemDatum.getSerializableStatus());
            purchaseItem.setTaxamt(itemDatum.getTaxamt());
            purchaseItem.setTaxid(itemDatum.getTaxid());
            purchaseItem.setTaxName(itemDatum.getTaxName());
            purchaseItem.setTaxpercent(itemDatum.getTaxpercent());
            purchaseItem.setUnitPrice(Double.valueOf(itemDatum.getUnitPrice()));
            purchaseItem.setUomName(itemDatum.getUomName());
            purchaseItem.setUom(itemDatum.getUom());
            purchaseItem.setUomConvertorList(itemDatum.getUomConvertorList());
            purchaseItem.setItemDescription(itemDatum.getItemDescription());
            purchaseItem.setTaxName(itemDatum.getTaxName());
            purchaseItem.setPrice(BigDecimal.valueOf(itemDatum.getUnitPrice()));
            purchaseItem.setPurchaseOrderId(itemDatum.getPurchaseOrderDetailsId());
            purchaseItem.setPurchaseQuotationId(itemDatum.getPurchaseQuotationId());
            purchaseItem.setReceiveItemId(itemDatum.getReceiveItemDetailsId());
            purchaseItem.setInclusiveJSON(itemDatum.getInclusiveJSON());
            purchaseItem.setItemCategoryName(itemDatum.getItemCategoryName());
            purchaseItem.setItemCategoryId(itemDatum.getItemCategoryId());
            purchaseItem.setDiscountConfigAmt(0.00);
            if (selectedTax != null) {
                if (selectedTax.equals(getResources().getString(R.string.igst)))
                    purchaseItem.setTaxAmountSplit("" + itemDatum.getIgTax());
                if (selectedTax.equals(getResources().getString(R.string.cgst)))
                    purchaseItem.setTaxAmountSplit("" + itemDatum.getIgTax() / 2 + "+" + itemDatum.getIgTax() / 2);
            }
            purchaseItemsList.add(purchaseItem);
            totalTaxAmt += itemDatum.getTaxamt();
            totalCessTaxAmt += itemDatum.getCessTaxAmt();
            totalDiscountAmt += itemDatum.getDiscountAmt();
        }

        PurchaseSaveData purchaseSaveData = new PurchaseSaveData();

        purchaseSaveData.setPurchaseItemList(purchaseItemsList);
        purchaseSaveData.setTotalCheckOutamt(purchasePosCreator.getTotalPrice());
        purchaseSaveData.setPaymentType(paymentType);
        purchaseSaveData.setTotalTaxAmt(totalTaxAmt);
        purchaseSaveData.setTaxType(selectedTax);
        purchaseSaveData.setSupplierId(supplier.getSupplierId());
        purchaseSaveData.setSupplierEmail(supplier.getSupplierEmail());
        purchaseSaveData.setCutomerName(supplier.getSupplierName() + "|" + supplier.getSupplierNumber());
        purchaseSaveData.setAdvancepayment(isAdvancePayment);
        purchaseSaveData.setSelfBuildInvoice(isReverseChargeInvoice);
        purchaseSaveData.setDateOfInvoice(selectedDate);
        purchaseSaveData.setCessTotalTaxAmt(totalCessTaxAmt);
        purchaseSaveData.setTo_reg("");
        purchaseSaveData.setFrom_reg("");
        purchaseSaveData.setDiscountAmount(totalDiscountAmt);
        if (selectedSupplierLocation[0] != null) {
            purchaseSaveData.setSuppLoc(selectedSupplierLocation[0].getInventoryLocationId());
        }
        if (selectedCompanyLocation[0] != null) {
            purchaseSaveData.setCmpyLoc(selectedCompanyLocation[0].getInventoryLocationId());
        }
        return gson.toJson(purchaseSaveData);
    }

    private String savePurchaseOrderData() {
        List<PurchasePosCartItem> posCartItems = purchasePosCreator.getItems();
        List<PurchaseOrderItem> poItemlist = new ArrayList<>();
        Iterator<PurchasePosCartItem> itr = posCartItems.iterator();
        float totalTaxAmt = 0.00f;
        double totalCessTaxAmt = 0.00;
        Gson gson = new Gson();
        while (itr.hasNext()) {
            PurchasePosCartItem posCartItem = itr.next();
            SelectedItemsList itemDatum = posCartItem.getItem();
            PurchaseOrderItem purchaseOrderItem = new PurchaseOrderItem();
            purchaseOrderItem.setItemCode(itemDatum.getItemCode());
            purchaseOrderItem.setItemId(itemDatum.getItemId());
            purchaseOrderItem.setItemName(itemDatum.getItemName());
            purchaseOrderItem.setSerializableStatus(itemDatum.getSerializableStatus());

            purchaseOrderItem.setUnitPrice(String.valueOf(itemDatum.getUnitPrice()));

            purchaseOrderItem.setItemDescription(itemDatum.getItemDescription());
            purchaseOrderItem.setDiscountAmt(itemDatum.getDiscountAmt());
            purchaseOrderItem.setCess("" + itemDatum.getCess());
            purchaseOrderItem.setDiscountInPercent(itemDatum.isDiscountInPercent());
            purchaseOrderItem.setQty("" + itemDatum.getItemQuantity());
            purchaseOrderItem.setRemainingQty("" + itemDatum.getItemQuantity());
            purchaseOrderItem.setReturnQty(itemDatum.getReturnQty());
            purchaseOrderItem.setAmtexclusivetax(itemDatum.getAmtexclusivetax());
            purchaseOrderItem.setTaxid(itemDatum.getTaxid());
            purchaseOrderItem.setTaxamt(itemDatum.getTaxamt());
            purchaseOrderItem.setTaxName(itemDatum.getTaxName());
            purchaseOrderItem.setTaxpercent(String.valueOf(itemDatum.getTaxpercent()));
            purchaseOrderItem.setIgTax(itemDatum.getIgTax());
            purchaseOrderItem.setAmtinclusivetax(itemDatum.getAmtinclusivetax());
            purchaseOrderItem.setUomName(itemDatum.getUomName());
            purchaseOrderItem.setUomConvertorList(itemDatum.getUomConvertorList());
            purchaseOrderItem.setHsnCode(itemDatum.getHsnCode());
            purchaseOrderItem.setCessTaxAmt(itemDatum.getCessTaxAmt());
            purchaseOrderItem.setTaxAmountSplit(itemDatum.getTaxAmountSplit());
            purchaseOrderItem.setInclusiveJSON(itemDatum.getInclusiveJSON());
            purchaseOrderItem.setItemCategoryName(itemDatum.getItemCategoryName());
            purchaseOrderItem.setItemCategoryId(itemDatum.getItemCategoryId());
            purchaseOrderItem.setDiscountConfigAmt(0.00);
            poItemlist.add(purchaseOrderItem);
            totalTaxAmt += itemDatum.getTaxamt();
            totalCessTaxAmt += itemDatum.getCessTaxAmt();
        }
        PurchaseOrderSave purchaseOrderSaveData = new PurchaseOrderSave();
        purchaseOrderSaveData.setOperation(operationType);
        purchaseOrderSaveData.setPiid(piid);
        purchaseOrderSaveData.setInvoiceNo(formNo);
        purchaseOrderSaveData.setExchangerateValue(1l);
        purchaseOrderSaveData.setCurrencyId(1l);
        purchaseOrderSaveData.setReceiveItemId(recieveItemId);
        purchaseOrderSaveData.setPurchaseQuotationId(quotationId);
        purchaseOrderSaveData.setSelectedItemsList(poItemlist);
        purchaseOrderSaveData.setTotalCheckOutamt(purchasePosCreator.getTotalPrice());
        purchaseOrderSaveData.setPaymentType("");
        purchaseOrderSaveData.setTotalTaxAmt(totalTaxAmt);
        purchaseOrderSaveData.setCessTotalTaxAmt("" + totalCessTaxAmt);
        purchaseOrderSaveData.setTaxType(selectedTax);
        purchaseOrderSaveData.setSupplierId(supplier.getSupplierId());
        purchaseOrderSaveData.setSupplierEmail(supplier.getSupplierEmail());
        purchaseOrderSaveData.setSupplierName(supplier.getSupplierName() + "|" + supplier.getSupplierNumber());
        purchaseOrderSaveData.setSupplierInvNo("");
        purchaseOrderSaveData.setDateOfInvoice(selectedDate);
        purchaseOrderSaveData.setShippingDate(selectedDate);
        purchaseOrderSaveData.setTypeOfInvoice("Invoice");


        purchaseOrderSaveData.setTypeDoc("PO");
        purchaseOrderSaveData.setFromReg("");
        purchaseOrderSaveData.setToReg("");
        if (selectedSupplierLocation[0] != null) {
            purchaseOrderSaveData.setSuppLoc(selectedSupplierLocation[0].getInventoryLocationId());
        }
        if (selectedCompanyLocation[0] != null) {
            purchaseOrderSaveData.setCmpyLoc(selectedCompanyLocation[0].getInventoryLocationId());
        }
        return gson.toJson(purchaseOrderSaveData);
    }

    @OnClick({R.id.edDate, R.id.btnReturnPurchase, R.id.btnSelectItem, R.id.chkBoxAdvancePayments,R.id.chkBoxReverseCharge, R.id.btnCheckout, R.id.tvSelectSupplier, R.id.fabclearall, R.id.fabPIreturn, R.id.fabsupplierpayment, R.id.fabadvancepayment, R.id.fabInvokePurchaseOrder, R.id.fabsavepurchaseorder,R.id.fabsavequotation,R.id.fabinvokereceiveitem,R.id.fabinvokequotation,R.id.fabsavereceiveitem,R.id.fabreturnreceiveitem,R.id.fabcreditnote, R.id.fabprintlist})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.fabclearall:
                callClearAll();
                break;
            case R.id.fabprintlist:
                fabMenus.close(true);
                initPrintListDailog();
                break;
            case R.id.fabPIreturn:
                callPIReturnItem();



                //initGetPurchaseInovice();
                break;

            case R.id.fabsupplierpayment:

                callSupplierPaymet();


                break;
            case R.id.fabadvancepayment:
                callPurchaseAdvancePayment();


                break;

            case R.id.fabsavepurchaseorder:
                callSavePurchaseOrderApi();
                break;
            case R.id.fabInvokePurchaseOrder:
                callInvokePurchaseOrderApi();


                break;
            case R.id.fabsavequotation:
                callSaveQuotation();
                break;
            case R.id.fabinvokequotation:
                callInvokeQuotation();

                break;
            case R.id.fabsavereceiveitem:
                callSaveRecieveItem();
                break;
            case R.id.fabinvokereceiveitem:
                callInvokeRecieveItem();

                break;
            case R.id.fabreturnreceiveitem:

                callReturnRecieveItem();


               //returnRecieveItem();
                break;
            case R.id.btnReturnPurchase:

                switch (returnItem){
                    case "Return Recieve Item" :
                        getReturnItemCheckout();
                        break;
                    case "PI Return Item" :
                        initReturnRecieveItem();
                        break;

                }





                break;
            
            case R.id.fabcreditnote:

                callCreditNoteItem();


                break;



            case R.id.edDate:
                getDatePicker(mActivity);
                break;
            case R.id.btnSelectItem:
                callItemView();
                break;

            case R.id.chkBoxAdvancePayments:
                isAdvancePayment = chkBoxAdvancePayments.isChecked() == true;
                break;

            case R.id.chkBoxReverseCharge:

                isReverseChargeInvoice = chkBoxReverseCharge.isChecked() == true;
                break;
            case R.id.btnCheckout:
                fabMenus.close(true);
                if (purchasePosCreator.getItems() != null && purchasePosCreator.getItems().size() > 0) {
                    String saveData = saveposData("multiPayment", "");
                    intent = new Intent(mActivity, Activity_Checkout.class);
                    intent.putExtra("purchaseSaveData", saveData);
                    intent.putExtra("checkoutType", "PurchaseCheckout");
                    intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_PURCHASE);
                    mActivity.startActivityForResult(intent, Constant.RESQUSTCODE_CHECKOUT);
                } else {
                    UtilView.showToast(mActivity, "Please Add Item first.");
                }
                break;
            case R.id.tvSelectSupplier:
                intent = new Intent(mActivity, Activity_Supplier.class);
                startActivityForResult(intent, Constant.RESQUSTCODE_SUPPLIER);
        }
    }

    public void callClearAll() {
        purchasePosCreator.clear();
        checkCartList("");
        fabMenus.close(true);
    }

    public void callPIReturnItem() {
        fabMenus.close(true);
        Intent intent = new Intent(mActivity, Activity_PurchaseInvoice_ReturnItem.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_PURCHASEINVOICERETURNITEM);

    }

    public void callCreditNoteItem() {
        fabMenus.close(true);
        if (supplier != null) {
            Intent intent = new Intent(mActivity, Activity_CreditNote.class);
            mActivity.startActivity(intent);
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.selectCustomerError));
        }
    }

    public void callDebitNoteItem() {
        fabMenus.close(true);
        if (supplier != null) {
            Intent intent = new Intent(mActivity, Activity_DebitNote.class);
            mActivity.startActivity(intent);
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.selectCustomerError));
        }
    }

    public void callReturnRecieveItem() {
        if (fabMenus != null)
            fabMenus.close(true);

        Intent intent = new Intent(mActivity, Activity_ReturnReceiveItem.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_RETRUNRECIEVEITEM);
    }

    public void callInvokeRecieveItem() {
        if (fabMenus != null)
            fabMenus.close(true);

        Intent intent = new Intent(mActivity, Activity_InvokeReceiveItem.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_ADDRECIEVEITEM);
    }

    public void callPurchaseAdvancePayment() {
        fabMenus.close(true);
        if (supplier != null) {
            Intent intent = new Intent(mActivity, ActivityPurchase_AdvancePayment.class);
            intent.putExtra("supplier", supplier);
            intent.putExtra("selectedTax", selectedTax);
            intent.putExtra("selectedDate", selectedDate);
            mActivity.startActivity(intent);
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.selectCustomerError));
        }
    }

    public void callSupplierPaymet() {
        fabMenus.close(true);
        if (supplier != null) {
            Intent intent = new Intent(mActivity, Activity_SupplierPayment.class);
            intent.putExtra("supplier", supplier);
            intent.putExtra("selectedTax", selectedTax);
            intent.putExtra("selectedDate", selectedDate);
            mActivity.startActivity(intent);
        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.selectCustomerError));
        }
    }

    public void callInvokeQuotation() {
        if (fabMenus != null)
            fabMenus.close(true);

        Intent intent = new Intent(mActivity, Activity_InvokeQuotation.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_INVOKEQUOTATION);
    }


    private void initReturnRecieveItem() {
        fabMenus.close(true);
        int count = 0;
        if (purchasePosCreator != null && purchasePosCreator.getItems().size() > 0) {
            for (int i = 0; i < purchasePosCreator.getItems().size(); i++) {
                PurchasePosCartItem pos = purchasePosCreator.getItems().get(i);
                SelectedItemsList posItem = pos.getItem();
                if (pos.getItem().getReturnQty() == 0) {
                    count = 1;
                }


            }
        }
        if (count == 1) {
            UtilView.showToast(mActivity, "Return Quantity can't be zero");
        } else {
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);




                            if(result != null){

                                initReturnDialog();
                            }else{

                                Gson gson = new Gson();
                                try {

                                    PurchaseOrderData purchaseOrderData = gson.fromJson(result.toString(), PurchaseOrderData.class);
                                    if (purchaseOrderData == null) {

                                    } else {
                                        Toast.makeText(mActivity, "No Stock", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }


                            }


                        }
                    }, false);

                    if (piid != null){
                        postDataTask.execute(savePurchaseOrderData(), serverUrl + "/purchase//0/" + Constant.FUNTION_RETURNRETURNITEMCHECKOUTVALIDATE+"?invokeOrderId="+piid, "");
                    }
                    else{
                        postDataTask.execute(savePurchaseOrderData(), serverUrl + "/purchase//0/" + Constant.FUNTION_RETURNRECEIVEITEMCHECKOUTVALIDATE+"/?invokeOrderId="+recieveItemId, "");
                    }



                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }

        }


    }

    private void getReturnItemCheckout() {
        if (purchasePosCreator.getItems().size() > 0) {
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

                                    invoiceData = gson.fromJson(result.toString(), Checkout_ResponseData.class);
                                    if (invoiceData != null) {
                                        if (invoiceData.getPiData() != null) {
                                            purchasePosCreator.clear();
                                            checkCartList("");
                                            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                checkPermission();
                                            } else {
                                                createPdf();
                                            }
                                        }
                                    } else {
                                        UtilView.showToast(mActivity, "Save Purchase Order not placed. Please try again.");
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);
                    postDataTask.execute(savePurchaseOrderData(), serverUrl + "/purchase//0/" + Constant.FUNTION_RETURNRECEIVEITEMS, "");
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

    private void returnRecieveItem() {
        if (fabMenus != null)
            fabMenus.close(true);

        dialog.setContentView(R.layout.dialog_purchase_retrun_receiveitemno);
        dialog.setCancelable(false);
        ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
        final EditText edReciecveItemNo = dialog.findViewById(R.id.edReciecveItemNo);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnSave = dialog.findViewById(R.id.btnSave);
        final ProgressView progressBar1 = dialog.findViewById(progress_bar);

        if (dialog != null)
            dialog.show();

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String receicveNo = edReciecveItemNo.getText().toString().trim();
                    if (receicveNo !=null && !receicveNo.equals("") && isAllValid)
                    {
                            isInternetPresent = serviceHandler.isConnectingToInternet();
                            if (serverUrl != null) {
                                if (isInternetPresent) {
                                    if (progressBar1 != null)
                                        progressBar1.setVisibility(View.VISIBLE);


                                    String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETRETURNRECIEVEITEMlIST + "/" + receicveNo;
                                    GetDataTask getretrunItemDetail = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                        @Override
                                        public void onTaskComplete(String result) {
                                            if (result != null) {
                                                if (progressBar1 != null)
                                                    progressBar1.setVisibility(View.GONE);
                                                try {
                                                    purchasePosCreator.clear();
                                                    Gson gson = new Gson();

                                                    PurchaseOrderData orderData = gson.fromJson(result.toString(), PurchaseOrderData.class);

                                                    if (orderData!=null){

                                                        if (orderData.getSelectedItemsList()!=null && orderData.getSelectedItemsList().size()>0){
                                                            for (int i=0;i<orderData.getSelectedItemsList().size();i++){
                                                                PurchaseInvokeOrderData item=orderData.getSelectedItemsList().get(i);
                                                                SelectedItemsList invokeItems = new SelectedItemsList();
                                                                invokeItems.setItemName(item.getItemName());
                                                                invokeItems.setItemId(item.getItemId());
                                                                invokeItems.setUnitPrice(item.getUnitPrice());
                                                                invokeItems.setQty("" + item.getQty());
                                                                invokeItems.setItemQuantity(item.getQty());
                                                                invokeItems.setReturnQty(item.getReturnQty());
                                                                invokeItems.setRemainingQty("" + item.getRemainingQty());
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

                                                                purchasePosCreator.addItem(new PurchasePosCartItem(invokeItems, 1), invokeItems.getItemId());
                                                            }
                                                        }


                                                        if (dialog != null)
                                                            dialog.dismiss();


                                                        checkCartList(orderData.getOperation());


                                                    } else {
                                                        UtilView.showToast(mActivity, "No Items for the selected Return Item Number");
                                                    }

                                                } catch (Exception e) {
                                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                                }
                                            } else {
                                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                            }
                                        }
                                    }, false);
                                    getretrunItemDetail.execute(url, "");
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                                }
                            } else {
                                UtilView.gotToLogin(mActivity);
                            }


                } else{
                        if (receicveNo.equals("") || receicveNo == null) {
                            edReciecveItemNo.setError(getString(R.string.err_msg));
                        }
            }

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


    }

    public void callSaveRecieveItem() {
        if (fabMenus != null)
            fabMenus.close(true);

        if (purchasePosCreator.getItems().size() > 0) {
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

                                    invoiceData = gson.fromJson(result.toString(), Checkout_ResponseData.class);
                                    if (invoiceData != null) {
                                        if (invoiceData.getPiData() != null) {
                                            purchasePosCreator.clear();
                                            checkCartList("");
                                            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                checkPermission();
                                            } else {
                                                createPdf();
                                            }
                                        }
                                    } else {
                                        UtilView.showToast(mActivity, "Save Purchase Receive item not placed. Please try again.");
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);

                    postDataTask.execute(savePurchaseOrderData(), serverUrl + "/purchase//0/" + Constant.FUNTION_SAVEPURCHASERECEIVEITEM+"?flag=false", "");
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



    public void callSaveQuotation() {
        if (fabMenus != null)
            fabMenus.close(true);

        if (purchasePosCreator.getItems().size() > 0) {
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

                                    invoiceData = gson.fromJson(result.toString(), Checkout_ResponseData.class);
                                    if (invoiceData != null) {
                                        if (invoiceData.getPiData() != null) {
                                            purchasePosCreator.clear();
                                            checkCartList("");
                                            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                checkPermission();
                                            } else {
                                                createPdf();
                                            }
                                        }
                                    } else {
                                        UtilView.showToast(mActivity, "Save Purchase Quotation not placed. Please try again.");
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);

                    postDataTask.execute(savePurchaseOrderData(), serverUrl + "/purchase//0/" + Constant.FUNTION_SAVEPURCHASEQUOTATION, "");
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



    private void initPrintListDailog() {
        dialog.setContentView(R.layout.dialog_purchase_printlist);
        CardView cardViewCreditNote = dialog.findViewById(R.id.cardViewCreditNote);
        CardView cardViewInvoiceList = dialog.findViewById(R.id.cardViewInvoiceList);
        CardView cardViewPaymentList = dialog.findViewById(R.id.cardViewPaymentList);
        CardView cardViewPurchaseOrder = dialog.findViewById(R.id.cardViewPurchaseOrder);
        CardView cardViewPurchaseQuotation = dialog.findViewById(R.id.cardViewPurchaseQuotation);
        CardView cardViewReceiveItem = dialog.findViewById(R.id.cardViewRecieveItem);
        CardView cardViewPurchaseReturn = dialog.findViewById(R.id.cardViewPurchaseReturn);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
        final ProgressView progress_bar = dialog.findViewById(R.id.progress_bar);

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

        cardViewCreditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing()) {

                    callPrintListCreditNote();

                    dialog.dismiss();
                }
            }
        });
        
        cardViewInvoiceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {

                    callPrintListInvoice();


                    dialog.dismiss();
                }

            }
        });
        cardViewPurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    callPrintListPurchaseOrder();


                    dialog.dismiss();
                }

            }
        });
        cardViewPurchaseReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    callPrintListPurchaseReturn();

                    dialog.dismiss();
                }
            }


        });

       

        cardViewPurchaseQuotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing()) {
                    callPrintListPurchaseQuotation();

                    dialog.dismiss();
                }
                
            }
        });

        cardViewReceiveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing()) {
                    callPrintListReceiveItem();

                    dialog.dismiss();
                }
            }
        });

        cardViewPaymentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    callPrintListPaymentlist();

                    dialog.dismiss();
                }
            }


        });
        if (dialog != null)
            dialog.show();
    }

    public void callPrintListPurchaseOrder() {
        Intent intent = new Intent(mActivity, Activity_PurchaseDuplicatePO.class);
        mActivity.startActivityForResult(intent,Constant.RESQUSTCODE_ADDPURCHASEORDER);
    }

    public void callPrintListPurchaseReturn() {
        Intent intent = new Intent(mActivity, Activity_PurchaseDuplicatePRetrun.class);
        mActivity.startActivityForResult(intent,Constant.RESQUSTCODE_ADDPURCHASERETURNITEM);
    }

    public void callPrintListPurchaseQuotation() {
        Intent intent = new Intent(mActivity, Activity_PurchaseQuotationItemList.class);
        mActivity.startActivityForResult(intent,Constant.RESQUSTCODE_ADDPURCHASEQUOTATIONITEM);
    }

    public void callPrintListReceiveItem() {
        Intent intent = new Intent(mActivity, Activity_PurchaseRecieveItemList.class);
        mActivity.startActivityForResult(intent,Constant.RESQUSTCODE_ADDPURCHASERECEIVEITEM);
    }

    public void callPrintListPaymentlist() {
        Intent intent = new Intent(mActivity, Activity_PurchaseDuplicatePaymnetList.class);
        mActivity.startActivityForResult(intent,Constant.RESQUSTCODE_ADDPURCHASEPAYMENTLIST);
    }

    public void callPrintListInvoice() {
        Intent intent = new Intent(mActivity, Activity_PurchaseDuplicateInvoices.class);
        mActivity.startActivity(intent);
    }

    public void callPrintListCreditNote() {
        Intent intent = new Intent(mActivity, Activity_CreditNoteList.class);
        mActivity.startActivityForResult(intent,Constant.RESQUSTCODE_ADDPURCHASECREDITNOTE);
    }

    public void callInvokePurchaseOrderApi() {
        if (fabMenus != null)
            fabMenus.close(true);

        Intent intent = new Intent(mActivity, Activity_InvokePurchaseOrder.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_INVOKEPURCHASEORDER);


    }

    public void callSavePurchaseOrderApi() {
        if (fabMenus != null)
            fabMenus.close(true);
        if (purchasePosCreator.getItems().size() > 0) {
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

                                    invoiceData = gson.fromJson(result.toString(), Checkout_ResponseData.class);
                                    if (invoiceData != null) {
                                        if (invoiceData.getPiData() != null) {
                                            purchasePosCreator.clear();
                                            checkCartList("");
                                            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                checkPermission();
                                            } else {
                                                createPdf();
                                            }
                                        }
                                    } else {
                                        UtilView.showToast(mActivity, "Save Purchase Order not placed. Please try again.");
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);

                    postDataTask.execute(savePurchaseOrderData(), serverUrl + "/purchase//0/" + Constant.FUNTION_SAVEPURCHASEORDER, "");
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
    PurchaseReturn_Adapter adapter;

/*    private void initGetPurchaseInovice() {

        fabMenus.close(true);
        if (serverUrl!=null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getOrdersTAsk = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        final ArrayList<PurchaseInvoiceReturnData> posInoviceList = new ArrayList<>();
                        if (result != null) {
                            try {
                                if (posInoviceList.size() > 0) {
                                    posInoviceList.clear();
                                }
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String invoiceJsonString = jsonArray.getString(i);
                                    Gson gson = new Gson();
                                    PurchaseInvoiceReturnData returnPosInvoice = gson.fromJson(invoiceJsonString, PurchaseInvoiceReturnData.class);
                                    posInoviceList.add(returnPosInvoice);
                                }
                                if (posInoviceList.size() > 0) {
                                    final Dialog dialog = new Dialog(mActivity);
                                    dialog.setCancelable(false);
                                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                                    dialog.setContentView(R.layout.dialog_purchase_return);
                                    ImageView imgviewClose = (ImageView) dialog.findViewById(R.id.imgviewClose);
                                    Button btnClose = (Button) dialog.findViewById(R.id.btnClose);
                                    final EditText edSearch = (EditText) dialog.findViewById(R.id.edSearch);
                                    final ProgressView progressBar1 = (ProgressView) dialog.findViewById(R.id.progress_bar);
                                    final ListView lvPosInvocies = (ListView) dialog.findViewById(R.id.lvPosInvocies);


                                    LayoutInflater inflater = mActivity.getLayoutInflater();
                                    ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_salesreturn, null, false);
                                    TextView tvFormNumber = (TextView) header.findViewById(R.id.tvFormNumber);
                                    TextView tvCustomerName = (TextView) header.findViewById((R.id.tvCustomerName));
                                    TextView tvTotalAmount = (TextView) header.findViewById((R.id.tvTotalAmount));
                                    tvFormNumber.setText(getResources().getString(R.string.formnumber));
                                    tvCustomerName.setText(getResources().getString(R.string.suppliername));
                                    tvTotalAmount.setText(getResources().getString(R.string.totalamount));

                                    adapter = new PurchaseReturn_Adapter(mActivity, posInoviceList);
                                    lvPosInvocies.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();


                                    edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                        @Override
                                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                            boolean handled = false;
                                            UtilView.hideSoftKeyboard(mActivity, edSearch);
                                            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                                                handled = true;
                                                String s= edSearch.getText().toString().trim();

                                                if (s.toString() != null && !s.toString().equals("")) {
                                                    adapter.getFilter().filter(s.toString());

                                                } else {
                                                    // Do something when there's no input
                                                    adapter = new PurchaseReturn_Adapter(mActivity, posInoviceList);
                                                    lvPosInvocies.setAdapter(adapter);
                                                    adapter.notifyDataSetChanged();
                                                }
                                            }
                                            return handled;
                                        }
                                    });

                                    edSearch.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            if (s.toString() != null && !s.toString().equals("")) {
                                                adapter.getFilter().filter(s.toString());

                                            } else {
                                                // Do something when there's no input
                                                adapter = new PurchaseReturn_Adapter(mActivity, posInoviceList);
                                                lvPosInvocies.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();
                                            }
                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {

                                        }
                                    });


                                    if (dialog != null)
                                        dialog.show();
                                    btnClose.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (dialog != null)
                                                dialog.dismiss();
                                        }
                                    });

                                    imgviewClose.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            if (dialog != null)
                                                dialog.dismiss();
                                        }
                                    });

                                    lvPosInvocies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                            if (posInoviceList.get(position).getPiNo()!=null) {
                                                String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEINVOICE + "/" + posInoviceList.get(position).getPiNo().toUpperCase();
                                                if (serverUrl != null) {
                                                    isInternetPresent = serviceHandler.isConnectingToInternet();
                                                    if (isInternetPresent) {

                                                        UtilView.showProgessBar(mActivity, progressBar1);
                                                        GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                                            @Override
                                                            public void onTaskComplete(String result) {
                                                                if (result != null) {
                                                                    UtilView.hideProgessBar(mActivity, progressBar1);
                                                                    try {
                                                                        sharedPreference.saveData("purchaseInvoiceData", result.toString());
                                                                        Gson gson = new Gson();
                                                                        purchaseInvoiceData = gson.fromJson(result.toString(), PurchaseInvoiceReturnData.class);
                                                                        if (purchaseInvoiceData != null) {
                                                                            purchasePosCreator.clear();

                                                                            if (purchaseInvoiceData.getSelectedItemsList() != null && purchaseInvoiceData.getSelectedItemsList().size() > 0) {
                                                                                SelectedItemsList purchaseItem = new SelectedItemsList();

                                                                                for (int i = 0; i < purchaseInvoiceData.getSelectedItemsList().size(); i++) {

                                                                                    purchaseItem.setAmtexclusivetax(purchaseInvoiceData.getSelectedItemsList().get(i).getAmtexclusivetax());
                                                                                    purchaseItem.setAmtinclusivetax(purchaseInvoiceData.getSelectedItemsList().get(i).getAmtinclusivetax());
                                                                                    purchaseItem.setCessTaxAmt(purchaseInvoiceData.getSelectedItemsList().get(i).getCessTaxAmt());
                                                                                    purchaseItem.setDiscountAmt(purchaseInvoiceData.getSelectedItemsList().get(i).getDiscountAmt());
                                                                                    purchaseItem.setHsnCode(purchaseInvoiceData.getSelectedItemsList().get(i).getHsnCode());
                                                                                    purchaseItem.setUomName(purchaseInvoiceData.getSelectedItemsList().get(i).getUomName());
                                                                                    purchaseItem.setIgTax(purchaseInvoiceData.getSelectedItemsList().get(i).getCgstsgstsplitvalues());
                                                                                    purchaseItem.setItemCode(purchaseInvoiceData.getSelectedItemsList().get(i).getItemCode());
                                                                                    purchaseItem.setItemDescription(purchaseInvoiceData.getSelectedItemsList().get(i).getItemDescription());
                                                                                    purchaseItem.setItemName(purchaseInvoiceData.getSelectedItemsList().get(i).getItemName());
                                                                                    purchaseItem.setItemId(purchaseInvoiceData.getSelectedItemsList().get(i).getItemId());
                                                                                    purchaseItem.setItemQuantity(purchaseInvoiceData.getSelectedItemsList().get(i).getQty());
                                                                                    purchaseItem.setRemainingQty("" + purchaseInvoiceData.getSelectedItemsList().get(i).getRemainingQty());
                                                                                    purchaseItem.setItemTotalAmount(purchaseInvoiceData.getSelectedItemsList().get(i).getAmtinclusivetax());
                                                                                    purchaseItem.setPrice(BigDecimal.valueOf(purchaseInvoiceData.getSelectedItemsList().get(i).getUnitPrice()));
                                                                                    purchaseItem.setUnitPrice("" + purchaseInvoiceData.getSelectedItemsList().get(i).getUnitPrice());
                                                                                    purchaseItem.setReturnQty(purchaseInvoiceData.getSelectedItemsList().get(i).getReturnQty());
                                                                                    //purchaseItem.setSelectSerialItem(purchaseInvoiceData.getSelectedItemsList().get(i).isSelectSerialItem());
                                                                                    //purchaseItem.setSerializableStatus(purchaseInvoiceData.getSelectedItemsList().get(i).getSerializableStatus());
                                                                                    purchaseItem.setQty("" + purchaseInvoiceData.getSelectedItemsList().get(i).getQty());
                                                                                    purchaseItem.setTaxAmountSplit("" + purchaseInvoiceData.getSelectedItemsList().get(i).getTaxPercentageSplit());
                                                                                    purchaseItem.setTaxamt(purchaseInvoiceData.getSelectedItemsList().get(i).getTaxamt());
                                                                                    purchaseItem.setTaxName(purchaseInvoiceData.getSelectedItemsList().get(i).getTaxName());
                                                                                    purchaseItem.setTaxTypeSelection(purchaseInvoiceData.getTaxType());
                                                                                    purchaseItem.setTaxpercent("" + purchaseInvoiceData.getSelectedItemsList().get(i).getTaxpercent());
                                                                                    purchaseItem.setTaxid(purchaseInvoiceData.getSelectedItemsList().get(i).getTaxid());

                                                                                    purchasePosCreator.addItem(new PurchasePosCartItem(purchaseItem, 1), purchaseItem.getItemId());
                                                                                }
                                                                                dialog.dismiss();

                                                                                checkCartList(Constant.OPERATION_RETURN);
                                                                            } else {
                                                                                UtilView.showToast(mActivity, "No Item in the invoice. ");
                                                                            }
                                                                        } else {
                                                                            UtilView.showToast(mActivity, "Can't able to return order ");
                                                                        }
                                                                    } catch (Exception e) {
                                                                        UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                                                    }
                                                                } else {
                                                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                                                }
                                                            }
                                                        }, false);
                                                        getDataTask.execute(url, "");


                                                    } else {
                                                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                                                    }
                                                } else {
                                                    UtilView.gotToLogin(mActivity);
                                                }

                                            }
                                        }
                                    });

                                    if (dialog != null)
                                        dialog.show();

                                } else {
                                    UtilView.showToast(mActivity, "No Purchase Invoice available.");
                                }
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                                }
                    }
                    }, false);
                getOrdersTAsk.execute(serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEINVOICESLIST+"?itemSearchText=", "");

            }else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }*/


    private void initReturnDialog() {

            int count = 0;
            double totalCessTax = 0;

            List<SelectedItemsList> itemList = new ArrayList<>();
            for (int i = 0; i < purchasePosCreator.getItems().size(); i++) {
           SelectedItemsList itemDatum = new SelectedItemsList();

                SelectedItemsList itemPos = purchasePosCreator.getItems().get(i).getItem();

                if (itemPos.getReturnQty() == 0) {
                    count = 1;
                }

                itemDatum.setAmtexclusivetax(itemPos.getAmtexclusivetax());
                itemDatum.setAmtinclusivetax(itemPos.getAmtinclusivetax());
                itemDatum.setCess(itemPos.getCess());
                itemDatum.setCessTaxAmt(itemPos.getCessTaxAmt());
                itemDatum.setDiscountAmt(itemPos.getDiscountAmt());
                itemDatum.setHsnCode(itemPos.getHsnCode());
                itemDatum.setIgTax(itemPos.getIgTax());
                itemDatum.setItemCode(itemPos.getItemCode());
                itemDatum.setItemDescription(itemPos.getItemDescription());
                itemDatum.setItemId(itemPos.getItemId());
                itemDatum.setItemName(itemPos.getItemName());
                itemDatum.setQty(itemPos.getQty());
                itemDatum.setRemainingQty(itemPos.getRemainingQty());
                itemDatum.setReturnQty(Long.parseLong(itemPos.getQty()));
                itemDatum.setTaxAmountSplit(itemPos.getTaxAmountSplit());
                itemDatum.setTaxamt(itemPos.getTaxamt());
                itemDatum.setTaxid(itemPos.getTaxid());
                itemDatum.setTaxName(itemPos.getTaxName());
                itemDatum.setTaxpercent(String.valueOf(itemPos.getTaxpercent()));
                itemDatum.setUnitPrice(itemPos.getUnitPrice());
                itemDatum.setUomName(itemPos.getUomName());
                itemDatum.setUomConvertorList(itemPos.getUomConvertorList());
                totalCessTax += itemPos.getCessTaxAmt();


                itemList.add(itemDatum);
            }

        purchaseReturnData.setSelectedItemsList(itemList);

        purchaseReturnData.setAmountReturned(purchasePosCreator.getTotalPrice());
            if (count == 1) {
                UtilView.showToast(mActivity, "Return Quantity can't be zero");
            } else {
                purchaseReturnData.setCmpyLoc(selectedCompanyLocation[0].getInventoryLocationId());
                purchaseReturnData.setSuppLoc(selectedSupplierLocation[0].getInventoryLocationId());
                purchaseReturnData.setSupplierId(Integer.valueOf(supplier.getSupplierId().toString()));
                purchaseReturnData.setDateOfInvoice(selectedDate);
                Intent intent = new Intent(mActivity, Activity_PurchaseReturn.class);
                intent.putExtra("returnInvoiceData", purchaseReturnData);
                startActivityForResult(intent, Constant.RESQUSTCODE_PURCHASERETURNCHECKOUT);
            }

    }

    private void initCashPaymetDialog() {

         /*    dialog.setContentView(R.layout.dialog_purchase_returncheckout);
        LinearLayout ll_cash = (LinearLayout) dialog.findViewById(R.id.ll_cash);
        LinearLayout ll_cheque = (LinearLayout) dialog.findViewById(R.id.ll_cheque);
        Button btnClose = (Button) dialog.findViewById(R.id.btn_close);
        ImageView imageview_close = (ImageView) dialog.findViewById(R.id.imageview_close);
        TextView btn_cash = (TextView) dialog.findViewById(R.id.btn_cash);
        Typeface font = Typeface.createFromAsset(mActivity.getAssets(), "fonts/fontawesome-webfont.ttf");
        btn_cash.setTypeface(font);
        if (dialog != null)
            dialog.show();
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
            }
        });
        imageview_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
            }
        });
        ll_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCashPaymetDialog();
            }

        });

        ll_cheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCashPaymetDialog();
            }

        });*/

        dialog.setContentView(R.layout.dialog_sales_returncash);
        final ProgressView progressView = dialog.findViewById(progress_bar);
        ImageView imageview_close = dialog.findViewById(R.id.imageview_close);
        EditText ed_return_totalamount = dialog.findViewById(R.id.ed_return_totalamount);
        Button btn_returnamt = dialog.findViewById(R.id.btn_returnamt);
        Button btn_close = dialog.findViewById(R.id.btn_close);
        ed_return_totalamount.setText("" + purchasePosCreator.getTotalPrice());
        btn_returnamt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (serverUrl != null) {
                    if (isInternetPresent) {
                        String url = serverUrl + "/purchase//0/" + Constant.FUNTION_RETURNINVOICE;
                        String post_json = getReturnPuurchaseData();
                        if (post_json != null) {
                            if (progressView != null)
                                progressView.setVisibility(View.VISIBLE);
                            PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    if (progressView != null)
                                        progressView.setVisibility(View.GONE);
                                    if (result != null) {
                                        try {
                                            purchasePosCreator.clear();
                                            purchaseInvoiceData = null;
                                            checkCartList("");
                                            if (dialog != null && dialog.isShowing()) {
                                                dialog.dismiss();
                                            }
                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                        }
                                    } else {
                                        UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                    }
                                }
                            }, false);
                            postDataTask.execute(post_json, url, "");
                        }
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                    }
                } else {
                    UtilView.gotToLogin(mActivity);
                }

            }
        });
        imageview_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
            }
        });
        if (dialog != null)
            dialog.show();
    }

    private String getReturnPuurchaseData() {
        if (purchaseInvoiceData != null) {
            double totalCessTax = 0;
            InvoiceReturn_Checkout returnSalesData = new InvoiceReturn_Checkout();
            List<in.hiaccounts.hinext.purchase.model.purchase_invoicereturn.SelectedItemsList> itemList = new ArrayList<>();
            for (int i = 0; i < purchasePosCreator.getItems().size(); i++) {
                in.hiaccounts.hinext.purchase.model.purchase_invoicereturn.SelectedItemsList itemDatum = new in.hiaccounts.hinext.purchase.model.purchase_invoicereturn.SelectedItemsList();

                SelectedItemsList itemPos = purchasePosCreator.getItems().get(i).getItem();

                itemDatum.setAmtexclusivetax(itemPos.getAmtexclusivetax());
                itemDatum.setAmtinclusivetax(itemPos.getAmtinclusivetax());
                itemDatum.setCess(itemPos.getCess());
                itemDatum.setCessTaxAmt(itemPos.getCessTaxAmt());
                itemDatum.setDiscountAmt(itemPos.getDiscountAmt());
                itemDatum.setHsnCode(itemPos.getHsnCode());
                itemDatum.setIgTax(itemPos.getIgTax());
                itemDatum.setItemCode(itemPos.getItemCode());
                itemDatum.setItemDescription(itemPos.getItemDescription());
                itemDatum.setItemId(itemPos.getItemId());
                itemDatum.setItemName(itemPos.getItemName());
                itemDatum.setQty(Long.parseLong(itemPos.getQty()));
                itemDatum.setRemainingQty(Long.parseLong(itemPos.getRemainingQty()));
                itemDatum.setReturnQty(Long.parseLong(itemPos.getQty()));
                itemDatum.setTaxAmountSplit(itemPos.getTaxAmountSplit());
                itemDatum.setTaxamt(itemPos.getTaxamt());
                itemDatum.setTaxid(itemPos.getTaxid());
                itemDatum.setTaxName(itemPos.getTaxName());
                itemDatum.setTaxpercent(String.valueOf(itemPos.getTaxpercent()));
                itemDatum.setUnitPrice(Double.parseDouble(String.valueOf(itemPos.getUnitPrice())));
                itemDatum.setUomName(itemPos.getUomName());

                totalCessTax += itemPos.getCessTaxAmt();


                itemList.add(itemDatum);
            }

            returnSalesData.setSelectedItemsList(itemList);


            returnSalesData.setPiid(purchaseInvoiceData.getPiid());
            returnSalesData.setOperation("Return");
            returnSalesData.setTotalCheckOutamt(purchaseInvoiceData.getTotalCheckOutamt());
            returnSalesData.setPaymentType("cashPayment");
            returnSalesData.setTotalTaxAmt(purchaseInvoiceData.getTotalTaxAmt());
            returnSalesData.setTaxType(purchaseInvoiceData.getTaxType());
            returnSalesData.setSupplierId(purchaseInvoiceData.getSupplierId());
            returnSalesData.setCessTotalTaxAmt("" + totalCessTax);
            returnSalesData.setCmpyLoc(selectedCompanyLocation[0].getInventoryLocationId());
            returnSalesData.setSuppLoc(selectedSupplierLocation[0].getInventoryLocationId());
            returnSalesData.setDateOfInvoice(selectedDate);


            return new Gson().toJson(returnSalesData);
        }
        return null;
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
            if (invoiceData != null) {
                createPdf();
            }
           /* if (paymentInvoice != null) {
                createPaymentPdf();
            }*/

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(mActivity, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {

                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                    if (invoiceData != null) {
                        createPdf();
                    }
                    /*if (paymentInvoice != null) {
                        createPaymentPdf();
                    }
*/
                    break;
            }


        } else {
            Toast.makeText(mActivity, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }

    private void createPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEORDER);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);


            if (logoInputStream != null) {
                try {
                    PosPurchaseOrderPdf invoicePdf = new PosA4PurchaseOrderPdfImpl();
                    invoicePdf.generatePurchaseOrderInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);

                    openPdfInvoice(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEORDER);


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosPurchaseOrderPdf invoicePdf = new PosA4PurchaseOrderPdfImpl();
                    invoicePdf.generatePurchaseOrderInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                    openPdfInvoice(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEORDER);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }


        }
    }

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


}
