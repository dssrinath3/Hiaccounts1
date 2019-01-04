package in.hiaccounts.hinext.service.fragment;


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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lowagie.text.DocumentException;
import com.rey.material.widget.ProgressView;

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
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.customer.model.CustomerList;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.sales.model.checkout.SalesSaveData;
import in.hiaccounts.hinext.sales.model.sales_pagedata.CmpylocationList;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.service.activity.Activity_DeliveryList;
import in.hiaccounts.hinext.service.activity.Activity_InvoiceList;
import in.hiaccounts.hinext.service.activity.Activity_RepairInvokeList;
import in.hiaccounts.hinext.service.activity.Activity_ReplaceInvokeList;
import in.hiaccounts.hinext.service.activity.Activity_ReplaceList;
import in.hiaccounts.hinext.service.adapter.Servicepos_Adapter;
import in.hiaccounts.hinext.service.model.SelectedItemsList;
import in.hiaccounts.hinext.service.model.service_invoice.ServiceSaveData;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCartItem;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCreator;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosHelper;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.service.serviceinvoice.PosA4ServiceInvociePdfImpl;
import in.hiaccounts.pdfgenerator.service.serviceinvoice.PosServiceInvoicePdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import static in.hiaccounts.R.string.response_error;

/**
 * Srinath 23-02-2018.
 */
public class Fragment_Service extends Fragment {
    public static String TAG = Fragment_Service.class.getSimpleName();
    private ServiceSaveData invoiceData = null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
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
    @BindView(R.id.tvItmename)
    TextView tvItmename;
    @BindView(R.id.tvItemQty)
    TextView tvItemQty;
    @BindView(R.id.tvItemUnitprice)
    TextView tvItemUnitprice;
    @BindView(R.id.tvItmeTotalAmt)
    TextView tvItmeTotalAmt;
    @BindView(R.id.chkbxSelection)
    CheckBox chkbxSelection;
    @BindView(R.id.listviewHeadr)
    LinearLayout listviewHeadr;
    @BindView(R.id.listviewSelectItems)
    ListView listviewSelectItems;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.edTotalAmount)
    EditText edTotalAmount;
    @BindView(R.id.btnDelivery)
    Button btnDelivery;
    @BindView(R.id.ll_total)
    LinearLayout llTotal;
    @BindView(R.id.rlListview)
    RelativeLayout rlListview;
    @BindView(R.id.ll_page)
    LinearLayout llPage;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    Unbinder unbinder;

    private boolean isAllValid = true;
    private PageLoadDataForAll pageData;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private ServiceHandler serviceHandler;
    private String selectedTax, serverUrl;
    private String selectedDate = "",operationType = "",taxString = "",servicedelivaryId="";
    private Customer selected_customer = null;
    private ServicePosCreator servicePosCreator;
    private Servicepos_Adapter cartItemAdapter;
    public static boolean isCheckoutable = true;
    public static int flag = 0;
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
    private String replaceItem="";

    private final CmpylocationList[] selectedCompanyLocation = {null};

    private BigDecimal itemTotalamt = BigDecimal.ZERO;
    private BigDecimal totalTaxAmt = BigDecimal.ZERO;
    private BigDecimal cessPercantage = BigDecimal.ZERO;
    private BigDecimal taxPercantage = BigDecimal.ZERO;
    private BigDecimal cgstTaxAmtValue = BigDecimal.ZERO;
    private BigDecimal cessTaxamt = BigDecimal.ZERO;
    public static List<SelectedItemsList> selectItemForDelete = new ArrayList<SelectedItemsList>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view, savedInstanceState);

        return view;
    }

    private void initComponents(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        servicePosCreator = ServicePosHelper.getPosCreator();
        getPageLoadData();
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

        listviewSelectItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editPosItem(position);
            }
        });
    }
    public String getTaxString() {
        return taxString;
    }

    public void setTaxString(String taxString) {
        this.taxString = taxString;
    }
    private void editPosItem(final int position) {
        Gson gson = new Gson();
        String hinextpagedatajson = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
        pageData = gson.fromJson(hinextpagedatajson.toString(), PageLoadDataForAll.class);
       /* if (position == 0) {
        } else {*/
        final Dialog dialog = new Dialog(mActivity);
        dialog.setContentView(R.layout.dialog_service_positem);
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
        ServicePosCartItem posCartItem = servicePosCreator.getItems().get(position);
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

        if (operationType.equals(Constant.OPERATION_RETURNDELIVERY)) {
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
            tvQuantity.setText("Delivery Quantity");
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
      /*  edTaxTypeAmount.setText("" + itemDatum.getIgTax());*/
        edCessAmt.setText("" + itemDatum.getCessTaxAmt());
        tvTaxType.setText(selectedTax);
        BigDecimal taxAmt = new BigDecimal("0.0");
       /* if (selectedTax.equals(getResources().getString(R.string.igst))) {
            edTaxTypeAmount.setText("" + taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
        }*/
       /* if (selectedTax.equals(getResources().getString(R.string.cgst))) {
            BigDecimal bgTax = taxAmt.divide(BigDecimal.valueOf(2));
            edTaxTypeAmount.setText("" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP) + "+" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP));
        }*/

        edCessPercantage.setText("" + itemDatum.getCess());
        edItemTotalTaxamt.setText("" + itemDatum.getTaxamt());
        edItemDiscount.setText("" + itemDatum.getDiscountAmt());
        itemAmtIncTax.setText("" + itemDatum.getAmtinclusivetax());
        if (dialog != null)
            dialog.show();
        String defaultTax = "";
        if (pageData != null) {
            if (pageData.getTaxList().size() > 0) {
                UtilView.setupHinextServiceTaxAdapter(mActivity, spinItemInputtax, pageData.getTaxList());
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
                        if (operationType.equals(Constant.OPERATION_RETURNDELIVERY)) {
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

                                discount = Double.parseDouble(discountPrice);

                            }
                            else{
                                discount = 0.0;
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
                    Double disc =Double.parseDouble(discount);
                    itemDatum.setDiscountAmt(disc);

                    String amtEx = edItemAmtExTax.getText().toString().trim();
                    Double amtExc = Double.parseDouble(amtEx);
                    Double amTax = Double.parseDouble(String.valueOf(totalTaxAmt));

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
                    String itemQty = edItemQuantity.getText().toString().trim();
                    Double d = Double.parseDouble(itemQty);

                    Long itmQuantity = (new Double(d)).longValue();



                    String unitPrice = edItemUnitPrice.getText().toString().trim();
                    double amtExTX = Double.parseDouble(edItemAmtExTax.getText().toString().trim());
                    double amtTotalTax = Double.parseDouble(edItemTotalTaxamt.getText().toString().trim());
                    String discountPrice = edItemDiscount.getText().toString().trim();
                    double discount = 0;
                    if (discountPrice == null || discountPrice.equals("")) {
                        discount = 0;
                    } else {
                        discount = Double.parseDouble(discountPrice);
                    }

                    double amtIncTax;
                    if (itemAmtIncTax.getText().toString().trim() == null || itemAmtIncTax.getText().toString().trim().equals("")) {
                        amtIncTax = 0;
                    } else {
                        amtIncTax = Double.parseDouble(itemAmtIncTax.getText().toString().trim());
                    }
                    itemDatum.setItemDescription(description);
                    if (operationType.equals(Constant.OPERATION_RETURNDELIVERY)){
                        long invoQty=Long.parseLong(edReturnQuatity.getText().toString());
                        itemDatum.setItemQuantity(invoQty);
                        itemDatum.setQty(Double.valueOf(invoQty));
                        itemDatum.setReturnQty(Double.valueOf(itmQuantity));
                        itemDatum.setRemainingQty(Double.valueOf(invoQty-itmQuantity));
                    }else {
                        itemDatum.setItemQuantity(itmQuantity);
                        itemDatum.setQty(Double.valueOf(itmQuantity));
                    }

                    itemDatum.setUnitPrice(Double.parseDouble(String.valueOf(unitPrice)));
                    itemDatum.setPrice(new BigDecimal(unitPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
                    itemDatum.setAmtexclusivetax(amtExTX);
                    itemDatum.setTaxTypeSelection(selectedTax);
                  /*  itemDatum.setIgTax(Double.parseDouble(String.valueOf(cgstTaxAmtValue)));
                    itemDatum.setTaxAmountSplit(edTaxTypeAmount.getText().toString().trim());*/
                    try {
                        itemDatum.setCess(Double.parseDouble(edCessPercantage.getText().toString().trim()));
                    } catch (NumberFormatException ne) {
                        itemDatum.setCess(Double.valueOf(0));
                    }
                    itemDatum.setCessTaxAmt(Double.parseDouble(String.valueOf(cessTaxamt)));
                    itemDatum.setTaxamt(amtTotalTax);
                    itemDatum.setDiscountAmt(discount);
                    itemDatum.setAmtinclusivetax(amtIncTax);
                    itemDatum.setItemTotalAmount(amtIncTax);
                    servicePosCreator.update(new ServicePosCartItem(itemDatum, 1), position);
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
            itemDatum.setTaxpercent(Double.valueOf(String.valueOf(taxValue)));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }
    private void getPageLoadData() {
        String getPageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
        if (getPageLoadData != null) {

            Gson gson = new Gson();
            pageData = gson.fromJson(getPageLoadData, PageLoadDataForAll.class);
            if (pageData != null) {
                if (pageData.getCustomers() != null && pageData.getCustomers().size() > 0) {

                    selected_customer = new Customer();
                    if (pageData.getCustomers().get(0).getCustomerName() != null) {
                        selected_customer.setCustomerName(pageData.getCustomers().get(0).getCustomerName());
                        tvSelectCustomer.setText(pageData.getCustomers().get(0).getCustomerName() + "|" + pageData.getCustomers().get(0).getCustomerNumber());
                    }
                    if (pageData.getCustomers().get(0).getCustomerId() != null) {
                        selected_customer.setCustomerId(pageData.getCustomers().get(0).getCustomerId());

                    }

                }
            }
        }
    }

    @OnClick({R.id.edDate, R.id.btnSelectItem,R.id.btnDelivery,R.id.tvSelectCustomer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edDate:
                getDatePicker(mActivity);
                break;
            case R.id.btnSelectItem:
                callItemView();
                break;
            case R.id.tvSelectCustomer:
                Intent intent = new Intent(mActivity, Activity_Customer.class);
                startActivityForResult(intent, Constant.RESQUSTCODE_CUSTOMERS);
                break;
            case R.id.btnDelivery:
                callServiceDelivery();
                break;
        }
    }








    private void callItemView() {
        if (serverUrl!=null) {
            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
            Intent intent = new Intent(mActivity, Activity_ShowItemList.class);
            intent.putExtra("url", url);
            intent.putExtra("taxType", selectedTax);
            intent.putExtra("itemsearch", "");
            intent.putExtra("selectedcustomer", selected_customer);
            intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_SERVICE);
            startActivityForResult(intent, Constant.RESQUSTCODE_ITEMSEARCH);
        }else {
            UtilView.gotToLogin(mActivity);
        }
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

    public void callInvoiceList(){
        Intent intent = new Intent(mActivity, Activity_InvoiceList.class);
        startActivityForResult(intent, Constant.REQUESTCODE_INVOICELIST);

    }

    public void callDeliveredList(){
        Intent intent = new Intent(mActivity, Activity_DeliveryList.class);
        startActivityForResult(intent, Constant.REQUESTCODE_DELIVERYLIST);
    }

    public void  callReplaceDraft(){
        if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0) {

             if (replaceItem.equals("Replace Item"))
             {
                 final String saveData = saveposData("multiPayment", "", selected_customer);

                 String url = serverUrl + "/service//" + Constant.FUNTION_VALIDATESERVICECHECKOUT + "?type=DraftReplace" ;
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
                                         if (result.toString().equals("")){

                                         }else{
                                             callReplaceDraftCheckout();
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

             }else {
                 UtilView.showToast(mActivity, "Please Select Replace Item.");
             }





        } else {
            UtilView.showToast(mActivity, "Please Add Item first.");
        }

    }

    private void callReplaceDraftCheckout() {
        if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0) {
            final String saveData = saveposData("multiPayment", "", selected_customer);

            String url = serverUrl + "/service//" + Constant.FUNTION_SAVESERVICEREPAIR + "?type=DraftReplace" ;
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
                                    Gson gson = new Gson();
                                    invoiceData = gson.fromJson(result.toString(), ServiceSaveData.class);
                                    if (invoiceData != null) {
                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            createPdf();
                                        }
                                        UtilView.showToast(mActivity, "Draft Saved successfully.");
                                    } else {
                                        UtilView.showToast(mActivity, "Please try again");
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

    public void callPrintlistRepair(){
        Intent intent = new Intent(mActivity, Activity_RepairInvokeList.class);
        startActivityForResult(intent, Constant.REQUESTCODE_REPAIRINVOKELIST);
    }
    public void callPrintlistReplace(){
        Intent intent = new Intent(mActivity, Activity_ReplaceInvokeList.class);
        startActivityForResult(intent, Constant.REQUESTCODE_REPLACEINVOKELIST);
    }
    public void callPrintlistDeliver(){
        Toast.makeText(mActivity, "printlist deliver", Toast.LENGTH_SHORT).show();
    }


    public void  callReplaceInvoke(){
        Intent intent = new Intent(mActivity, Activity_ReplaceInvokeList.class);
        startActivityForResult(intent, Constant.REQUESTCODE_REPLACEINVOKELIST);
    }
    public void callReplacing(){
        if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0) {
            Intent intent = new Intent(mActivity, Activity_ReplaceList.class);
            intent.putExtra("customer",selected_customer);
            startActivityForResult(intent, Constant.REQUESTCODE_REPLACEITEMS);
        }else {
            UtilView.showToast(mActivity, "Please Add Item first.");
        }
    }
    public void callReplacSave(){
        if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0) {
            final String saveData = saveposData("multiPayment", "", selected_customer);
            String invokeOrderId = "";
            String url = serverUrl + "/service//" + Constant.FUNTION_VALIDATESERVICECHECKOUT + "?type=Replace" ;
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
                                    if (result.toString().equals("")){

                                    }else{
                                        callReplaceCheckout();
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

    private void callReplaceCheckout() {
        if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0) {
            final String saveData = saveposData("multiPayment", "", selected_customer);
            String invokeOrderId = "";
            String url = serverUrl + "/service//" + Constant.FUNTION_SAVESERVICEREPAIR + "?type=Replace" ;
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
                                    Gson gson = new Gson();
                                    invoiceData = gson.fromJson(result.toString(), ServiceSaveData.class);
                                    if (invoiceData != null) {
                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            createPdf();
                                        }
                                        UtilView.showToast(mActivity, "Draft Saved successfully.");
                                    } else {
                                        UtilView.showToast(mActivity, "Please try again");
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

    public  void callRepairDraft(){
        if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0) {
            final String saveData = saveposData("multiPayment", "", selected_customer);
            String invokeOrderId = "";
            String url = serverUrl + "/service//" + Constant.FUNTION_VALIDATESERVICECHECKOUT + "?type=DraftRepair" ;
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
                                    if (result.toString().equals("")){

                                    }else{
                                        callDraftCheckout();
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

    private void callDraftCheckout() {
        if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0) {
            final String saveData = saveposData("multiPayment", "", selected_customer);
            String invokeOrderId = "";
            String url = serverUrl + "/service//" + Constant.FUNTION_SAVESERVICEREPAIR + "?type=DraftRepair" ;
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
                                    Gson gson = new Gson();
                                    invoiceData = gson.fromJson(result.toString(), ServiceSaveData.class);
                                    if (invoiceData != null) {
                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            createPdf();
                                        }
                                        UtilView.showToast(mActivity, "Draft Saved successfully.");
                                    } else {
                                        UtilView.showToast(mActivity, "Please try again");
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

    public  void callRepairInvoke(){
        Intent intent = new Intent(mActivity, Activity_RepairInvokeList.class);
        startActivityForResult(intent, Constant.REQUESTCODE_REPAIRINVOKELIST);

    }
    public void callRepairSave(){

        if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0) {
            final String saveData = saveposData("multiPayment", "", selected_customer);
            String invokeOrderId = "";
            String url = serverUrl + "/service//" + Constant.FUNTION_VALIDATESERVICECHECKOUT + "?type=Repair" ;
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
                                    if (result.toString().equals("")){

                                    }else{
                                        callCheckout();
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

    private void callCheckout() {
        if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0) {
            final String saveData = saveposData("multiPayment", "", selected_customer);
            String invokeOrderId = "";
            String url = serverUrl + "/service//" + Constant.FUNTION_SAVESERVICEREPAIR + "?type=Repair" ;
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
                                    Gson gson = new Gson();
                                    invoiceData = gson.fromJson(result.toString(), ServiceSaveData.class);
                                    if (invoiceData != null) {
                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            createPdf();
                                        }
                                        UtilView.showToast(mActivity, "Saved successfully.");
                                    } else {
                                        UtilView.showToast(mActivity, "Please try again");
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

private void callServiceDelivery(){
    if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0) {
        final String saveData = saveposData("", "", selected_customer);

        String url = serverUrl + "/service//" + Constant.FUNTION_SAVESERVICEDELIVERY ;
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
                                Gson gson = new Gson();
                                invoiceData = gson.fromJson(result.toString(), ServiceSaveData.class);
                                if (invoiceData != null) {
                                    if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                        checkPermission();
                                    } else {
                                        createPdf();
                                    }
                                    UtilView.showToast(mActivity, "Saved successfully.");
                                } else {
                                    UtilView.showToast(mActivity, "Please try again");
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

                Toast.makeText(mActivity, ""+cust.getCustomerId(), Toast.LENGTH_SHORT).show();
            }
            if (selected_customer.getCustomerNumber() != null) {
                if (!selected_customer.getCustomerNumber().equals(""))
                    tvSelectCustomer.setText(selected_customer.getCustomerName() + "|" + selected_customer.getCustomerNumber());
                else
                    tvSelectCustomer.setText(selected_customer.getCustomerName() + "|0");
            } else {
                tvSelectCustomer.setText(selected_customer.getCustomerName() + "|0");
            }

        }

        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == Activity.RESULT_OK) {
            checkCartList("");
        }
        if (requestCode == Constant.REQUESTCODE_INVOICELIST && resultCode == Activity.RESULT_OK) {
          checkCartList("");
        }
        if (requestCode == Constant.REQUESTCODE_DELIVERYLIST && resultCode == Activity.RESULT_OK) {
            servicedelivaryId = data.getStringExtra("serviceDelivaeryId");

            checkCartList(Constant.OPERATION_RETURNDELIVERY);
        }
        if (requestCode == Constant.REQUESTCODE_REPAIRINVOKELIST && resultCode == Activity.RESULT_OK) {
            servicedelivaryId = data.getStringExtra("serviceDelivaeryId");
            checkCartList("");
        }
        if (requestCode == Constant.REQUESTCODE_REPLACEITEMS && resultCode == Activity.RESULT_OK) {
            replaceItem = data.getStringExtra("ReplaceItem");
            checkCartList("");
        }
        if (requestCode == Constant.REQUESTCODE_REPLACEINVOKELIST && resultCode == Activity.RESULT_OK) {
            replaceItem = data.getStringExtra("ReplaceItem");
            checkCartList("");
        }





    }



    public void checkCartList(String functionOperation) {
        operationType=functionOperation;
        if (servicePosCreator != null) {
            if (servicePosCreator.getItems().size() > 0) {

                if (functionOperation.equals(Constant.OPERATION_RETURNDELIVERY)) {
                    setViewUnFocusable();
                } else {
                    setViewFocusable();
                }
                mActivity.invalidateOptionsMenu();
                showTotalPrice();
                List<ServicePosCartItem> posCartItems = servicePosCreator.getItems();
                cartItemAdapter = new Servicepos_Adapter(mActivity, posCartItems, functionOperation);
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

                visibilityGone();
                flag = 0;
                isCheckoutable = true;

                List<ServicePosCartItem> posCartItems = servicePosCreator.getItems();
                if (cartItemAdapter != null) {
                    cartItemAdapter.updateCartItems(posCartItems);
                    cartItemAdapter.notifyDataSetChanged();
                }
                mActivity.invalidateOptionsMenu();
                if (functionOperation.equals(Constant.OPERATION_RETURNDELIVERY)) {
                    setViewUnFocusable();
                } else {
                    setViewFocusable();
                }
            }

        } else {
            mActivity.invalidateOptionsMenu();
               if (functionOperation.equals(Constant.OPERATION_RETURNDELIVERY)) {
                setViewUnFocusable();
            } else {
                setViewFocusable();
            }
        }
    }





    private void visibilityVisible() {
        if (llPage != null)
            llPage.setVisibility(View.VISIBLE);

        if (rlListview!=null){
            rlListview.setVisibility(View.VISIBLE);
        }
        if (listviewSelectItems != null)
            listviewSelectItems.setVisibility(View.VISIBLE);

        if (llTotal != null)
            llTotal.setVisibility(View.VISIBLE);


    }
    private void visibilityGone() {
        if (llPage != null)
            llPage.setVisibility(View.VISIBLE);

        if (rlListview!=null){
            rlListview.setVisibility(View.VISIBLE);
        }
        if (listviewSelectItems != null)
            listviewSelectItems.setVisibility(View.VISIBLE);

        if (llTotal != null)
            llTotal.setVisibility(View.GONE);
    }

    public void showTotalPrice() {
        Double posCartTotal = 0.00;
        for (int i = 0; i < servicePosCreator.getItems().size(); i++) {
            posCartTotal += servicePosCreator.getItems().get(i).getItem().getItemTotalAmount();
        }
        edTotalAmount.setText(String.valueOf(new BigDecimal(posCartTotal).setScale(2, BigDecimal.ROUND_HALF_UP)));
        if (llTotal != null)
            llTotal.setVisibility(View.VISIBLE);
        servicePosCreator.setTotalPrice(posCartTotal);
    }

    private void setViewFocusable() {
        if (btnDelivery != null)
            btnDelivery.setVisibility(View.VISIBLE);

        if (btnSelectItem != null)
            btnSelectItem.setClickable(true);

        if (tvSelectCustomer != null)
            tvSelectCustomer.setClickable(true);

    }

    private void setViewUnFocusable() {
        if (btnDelivery != null)
            btnDelivery.setVisibility(View.VISIBLE);

        if (btnSelectItem != null)
            btnSelectItem.setClickable(false);
        if (tvSelectCustomer != null)
            tvSelectCustomer.setClickable(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_service, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {



            case R.id.action_settings:
                ((NavigationDrawer_Activity)mActivity).openRightDrawer(Constant.MODULE_SERVICE);
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
                                servicePosCreator.delete(selectItemForDelete);
                                selectItemForDelete.clear();
                                checkCartList("");
                                servicePosCreator.clear();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        servicePosCreator.clear();
        flag = 0;
        isCheckoutable = true;

    }

    private String saveposData(String paymentType, String opertaion, Customer customer) {


        List<ServicePosCartItem> posCartItems = servicePosCreator.getItems();
        List<SelectedItemsList> selectedItemsList = new ArrayList<>();
        Iterator<ServicePosCartItem> itr = posCartItems.iterator();
        double totalTaxAmt = 0.00;
        double totalCessTaxAmt = 0.00;
        double totalDiscount = 0.00;
        String salesOrderId = null;


        while (itr.hasNext()) {
            ServicePosCartItem posCartItem = itr.next();
            SelectedItemsList itemDatum = posCartItem.getItem();
            itemDatum.setQty(Double.valueOf(itemDatum.getItemQuantity()));
            itemDatum.setRemainingQty(Double.valueOf(itemDatum.getItemQuantity()));

            selectedItemsList.add(itemDatum);
            totalTaxAmt += itemDatum.getTaxamt();
            //totalCessTaxAmt += itemDatum.getCessTaxAmt();
            totalDiscount+=itemDatum.getDiscountAmt();




        }
        ServiceSaveData serviceSaveData = new ServiceSaveData();
        serviceSaveData.setSelectedItemsList(selectedItemsList);
        serviceSaveData.setTotalCheckOutamt(servicePosCreator.getTotalPrice());
        serviceSaveData.setCessTotalTaxAmt(totalCessTaxAmt);
        serviceSaveData.setOperation("Service");
        serviceSaveData.setCustomerEmail(selected_customer.getCustomerEmail());
        serviceSaveData.setCustomerId(selected_customer.getCustomerId());
        serviceSaveData.setCutomerName(selected_customer.getCustomerName());
       // serviceSaveData.setPaymentType(paymentType);
        serviceSaveData.setTaxType(selectedTax);
        serviceSaveData.setTotalTaxAmt(totalTaxAmt);
        serviceSaveData.setDateOfInvoice(selectedDate);
        serviceSaveData.setShippingDate(selectedDate);

        if (replaceItem.equals("")){
            serviceSaveData.setServiceDeliveryId(servicedelivaryId);
        }

        serviceSaveData.setDiscountAmount(totalDiscount);

        if (selectedCompanyLocation[0] != null) {
            serviceSaveData.setCustLocation(String.valueOf(selectedCompanyLocation[0].getStateId().getId()));
        }
        if (selectedCompanyLocation[0] != null) {
            serviceSaveData.setCmpyLocation(String.valueOf(selectedCompanyLocation[0].getInventoryLocationId()));
        }

        Gson gson = new Gson();
        return gson.toJson(serviceSaveData);

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

                    break;
            }


        } else {
            Toast.makeText(mActivity, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }

    private void createPdf() {
        File pdfFile = null;

        pdfFile = PdfUtils.createFile(invoiceData.getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
        if (pdfFile != null) {

            progressBar.setVisibility(View.VISIBLE);


            InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);
            if (logoInputStream != null) {
                progressBar.setVisibility(View.GONE);
                try {
                    PosServiceInvoicePdf invoicePdf = new PosA4ServiceInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(invoiceData.getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosServiceInvoicePdf invoicePdf = new PosA4ServiceInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(invoiceData.getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
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
