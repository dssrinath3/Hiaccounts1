package in.hiaccounts.hinext.inventory.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import com.rey.material.widget.ProgressView;

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
import in.hiaccounts.hinext.inventory.adapter.InventoryItemListAdapter;
import in.hiaccounts.hinext.inventory.model.inventory_pos.InventoryPosCartItem;
import in.hiaccounts.hinext.inventory.model.inventory_pos.InventoryPosCreator;
import in.hiaccounts.hinext.inventory.model.inventory_pos.InventoryPosHelper;
import in.hiaccounts.hinext.inventory.model.inventory_pos.InventorySelectItemData;
import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.AddLocationTransferData;
import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.ItemId;
import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.LTdetail;
import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.LocFromId;
import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.LocList;
import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.LocToId;
import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.MoveList;
import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.SelectInventoryLocationTransferData;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.HinextPurchasePageData;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_InventoryLocationTransfer extends AppCompatActivity {

    public static String TAG = Activity_InventoryLocationTransfer.class.getSimpleName();
    public static List<InventorySelectItemData> selectItemForDelete = new ArrayList<InventorySelectItemData>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edDate)
    EditText edDate;
    @BindView(R.id.btnSelectItem)
    Button btnSelectItem;
    @BindView(R.id.spinFromLocation)
    Spinner spinFromLocation;
    @BindView(R.id.spinToLocation)
    Spinner spinToLocation;
    @BindView(R.id.spinMovementType)
    Spinner spinMovementType;
    @BindView(R.id.tvItemCode)
    TextView tvItemCode;
    @BindView(R.id.tvItemDesc)
    TextView tvItemDesc;
    @BindView(R.id.tvItemQty)
    TextView tvItemQty;
    @BindView(R.id.tvItemStock)
    TextView tvItemStock;
    @BindView(R.id.chkbxSelection)
    CheckBox chkbxSelection;
    @BindView(R.id.listviewHeadr)
    LinearLayout listviewHeadr;
    @BindView(R.id.listviewSelectItems)
    ListView listviewSelectItems;
    @BindView(R.id.rlListview)
    LinearLayout rlListview;
    @BindView(R.id.ll_page)
    LinearLayout llPage;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.background_dimmer)
    View backgroundDimmer;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.btnProcessTransfer)
    Button btnProcessTransfer;
    @BindView(R.id.ll_total)
    LinearLayout llTotal;
    List<Object> objectFromLocationList = new ArrayList<Object>();
    List<Object> objectToLocationList = new ArrayList<Object>();
    List<Object> objectMovementTypeList = new ArrayList<Object>();
    String callingFor, serverUrl, selectedTax;
    SelectInventoryLocationTransferData selectData;
    List<LocList> locationList;
    InventoryItemListAdapter adapter;
    private String itemCodeId ="";

    InventoryPosCreator inventoryPosCreator;
    private  EditText edItemCode,edItemDescritpion,edItemQuantity,edItemStock;
    private Button btnAdd,btnClose;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private ServiceHandler serviceHandler;
    private GetSupplier supplier;
    private Dialog dialog;
    private List<Object> locationTransList = new ArrayList<Object>();
    private String selectedDate = "",itemCode, itemDesc, itemQty, itemStock;
    private HinextPurchasePageData pageData;
    private boolean isAllValid = true;
    private LocList locationData = null;
    private MoveList moveTypeData = null;
    private List<InventoryPosCartItem> posCartItem;
    private InventorySelectItemData itemDatum;
    private String fromLocation="",toLocation="",locationContactName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_location_transfer);
        ButterKnife.bind(this);
        initContentView();
    }


    private void initContentView() {
        ButterKnife.bind(this);
        mActivity = this;
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        dialog = new Dialog(mActivity);
        toolbar.setTitle(getResources().getString(R.string.inventory_addinventorylocationtransfer));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        inventoryPosCreator = InventoryPosHelper.getPosCreator();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        getPopulateData();
        //getPageLoadData();


        listviewSelectItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editPosItem(position);
            }


        });

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

        spinFromLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectFromLocationList.get(i);
                if (obj instanceof LocList) {
                    spinFromLocation.setSelection(i);
                    locationData = (LocList) spinFromLocation.getSelectedItem();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinToLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectFromLocationList.get(i);
                if (obj instanceof LocList) {
                    spinToLocation.setSelection(i);
                    locationData = (LocList) spinToLocation.getSelectedItem();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinMovementType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Object obj = objectMovementTypeList.get(i);
                if (obj instanceof MoveList) {
                    spinMovementType.setSelection(i);
                    moveTypeData = (MoveList) spinMovementType.getSelectedItem();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void editPosItem(final int position) {


        dialog.setContentView(R.layout.dialog_inventory_positem);
        dialog.setCancelable(false);


        edItemCode = dialog.findViewById(R.id.edItemCode);
        edItemDescritpion = dialog.findViewById(R.id.edItemDescritpion);
        edItemQuantity = dialog.findViewById(R.id.edItemQuantity);
        edItemStock = dialog.findViewById(R.id.edItemStock);
        btnAdd = dialog.findViewById(R.id.btnAdd);
        btnClose = dialog.findViewById(R.id.btnClose);


        if (btnAdd != null)
            btnAdd.setText("Edit");


        InventoryPosCartItem posCartItems = inventoryPosCreator.getItems().get(position);

        itemDatum = posCartItems.getItemDatum();

        edItemCode.setText("" + itemDatum.getItemCode());
        edItemDescritpion.setText(itemDatum.getItemId().getItemDesc());

        if (edItemQuantity != null) {
            edItemQuantity.setText("" + itemDatum.getItemId().getItemQuantityProduction());
        } else {
            edItemQuantity.setText("0");
        }


        edItemStock.setText("" + itemDatum.getStock());

        if (dialog != null)
            dialog.show();


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemCode = edItemCode.getText().toString();
                itemDesc = edItemDescritpion.getText().toString();
                itemQty = edItemQuantity.getText().toString();
                itemStock = edItemStock.getText().toString();

                if (edItemQuantity.getText().toString() == null || edItemQuantity.getText().toString().equals("") ||
                        edItemQuantity.getText().toString().equals("0.0") || edItemQuantity.getText().toString().equals("0")) {
                    UtilView.showToast(mActivity, "Quantity can't be Zero");

                } else {

                    itemDatum.getItemId().setItemQuantityProduction(Long.valueOf(itemQty));

                    inventoryPosCreator.update(new InventoryPosCartItem(itemDatum, 1), position);
                    checkCartList();

                    if (dialog != null)
                        dialog.dismiss();
                }



            }
        });

    }

    private void getPopulateData() {

        String url = serverUrl + "/inventory/populate";
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


                                Gson gson = new Gson();
                                selectData = gson.fromJson(result.toString(), SelectInventoryLocationTransferData.class);
                                setupPopulatePageData();
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

    private void setupPopulatePageData() {
        if (selectData.getMoveList() != null) {
            objectMovementTypeList.clear();
            objectMovementTypeList.add("Select");
            objectMovementTypeList.addAll(selectData.getMoveList());
            UtilView.setupItemAdapter(mActivity, spinMovementType, objectMovementTypeList);


        }

        if (selectData.getLocList() != null) {
            objectFromLocationList.clear();
            objectFromLocationList.add("Select");
            objectFromLocationList.addAll(selectData.getLocList());
            UtilView.setupItemAdapter(mActivity, spinFromLocation, objectFromLocationList);

            UtilView.setupItemAdapter(mActivity, spinToLocation, objectFromLocationList);

        }


    }

    private void getPageLoadData() {
        String url = serverUrl + "/hipos//0/" + Constant.FUNCTION_GETPAGELOADDATA;
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

                Gson gson = new Gson();
                sharedPreference.saveData(Constant.SUPPLIER, gson.toJson(supplier));

            }


        }

    }




    @OnClick({R.id.edDate, R.id.btnSelectItem, R.id.btnProcessTransfer})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {


            case R.id.edDate:
                getDatePicker(mActivity);
                break;
            case R.id.btnSelectItem:
                callItemView();
                break;

            case R.id.btnProcessTransfer:
                addInventoryLocationTransfer();

                break;

        }
    }

    private void addInventoryLocationTransfer() {


        if (inventoryPosCreator.getItems() != null && inventoryPosCreator.getItems().size() > 0) {

            if (isAllValid && locationData != null && !locationData.getInventoryLocationName().equals("Select") && locationData != null && !locationData.getInventoryLocationName().equals("Select") && moveTypeData != null && !moveTypeData.getInventoryMovementName().equals("Select")) {

                posCartItem = inventoryPosCreator.getItems();
                LocFromId locFromId = new LocFromId();
                LocToId  locToId = new LocToId();

                AddLocationTransferData locationTransferData = new AddLocationTransferData();
                List<LTdetail> listLoc = new ArrayList<>();
                for (int i = 0; i < posCartItem.size(); i++) {
                    ItemId itemIdDetails = new ItemId();
                    itemIdDetails.setItemId(posCartItem.get(i).getItemDatum().getItemId().getItemId());
                    itemIdDetails.setItemCode(posCartItem.get(i).getItemDatum().getItemId().getItemCode());
                    itemIdDetails.setItemName(posCartItem.get(i).getItemDatum().getItemId().getItemName());
                    itemIdDetails.setItemAccountCode(posCartItem.get(i).getItemDatum().getItemId().getItemAccountCode());
                    itemIdDetails.setItemDesc(posCartItem.get(i).getItemDatum().getItemId().getItemDesc());
                    itemIdDetails.setSerializableStatus(posCartItem.get(i).getItemDatum().getItemId().getSerializableStatus());
                    itemIdDetails.setItemStatus(posCartItem.get(i).getItemDatum().getItemId().getItemStatus());
                    itemIdDetails.setInclusiveJSON(posCartItem.get(i).getItemDatum().getItemId().getInclusiveJSON());

                    LTdetail lTdetail = new LTdetail();
                    lTdetail.setItemCode(posCartItem.get(i).getItemDatum().getItemCode());
                    lTdetail.setItemDesc(posCartItem.get(i).getItemDatum().getItemId().getItemDesc());
                    lTdetail.setStock(posCartItem.get(i).getItemDatum().getStock());
                    lTdetail.setQtyTransferred(posCartItem.get(i).getItemDatum().getItemId().getItemQuantityProduction());
                    lTdetail.setItemId(itemIdDetails);
                    listLoc.add(lTdetail);


                    if (locFromId != null)
                    {
                        locFromId.setInventoryLocationContactPerson(posCartItem.get(i).getItemDatum().getLocationId().getInventoryLocationContactPerson());
                        locFromId.setInventoryLocationId(posCartItem.get(i).getItemDatum().getLocationId().getInventoryLocationId());
                        locFromId.setInventoryLocationName(posCartItem.get(i).getItemDatum().getLocationId().getInventoryLocationName());
                        locFromId.setInventoryLocationStatus(posCartItem.get(i).getItemDatum().getLocationId().getInventoryLocationStatus());
                        locFromId.setCompanyIdForLoc(posCartItem.get(i).getItemDatum().getLocationId().getCompanyIdForLoc());

                    }

                    if (locToId !=null)
                    {
                        locToId.setInventoryLocationContactPerson(posCartItem.get(i).getItemDatum().getLocationId().getInventoryLocationContactPerson());
                        locToId.setInventoryLocationId(posCartItem.get(i).getItemDatum().getLocationId().getInventoryLocationId());
                        locToId.setInventoryLocationName(posCartItem.get(i).getItemDatum().getLocationId().getInventoryLocationName());
                        locToId.setInventoryLocationStatus(posCartItem.get(i).getItemDatum().getLocationId().getInventoryLocationStatus());
                        locToId.setCompanyIdForLoc(posCartItem.get(i).getItemDatum().getLocationId().getCompanyIdForLoc());

                    }







                }
                locationTransferData.setlTdetails(listLoc);
                locationTransferData.setIlocTransDate(selectedDate);
                locationTransferData.setLocFromId(locFromId);
                locationTransferData.setLocToId(locToId);
                String url = serverUrl + "/inventory/saveOrUpdate";
                if (serverUrl != null) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {
                        UtilView.showProgessBar(mActivity, progressBar);
                        PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                UtilView.hideProgessBar(mActivity, progressBar);
                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(mActivity, Activity_Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        if (result.equals(getString(R.string.error_rsonsecode204))) {
                                            UtilView.showToast(mActivity, "Server Error. Please try again");
                                        } else {
                                            inventoryPosCreator.clear();
                                            Gson gson = new Gson();
                                            try {
                                                InventorySelectItemData resutlLocation = gson.fromJson(result.toString(), InventorySelectItemData.class);
                                                if (resutlLocation != null) {
                                                    UtilView.showToast(mActivity, "Inventory Location Transfer add Successfully");
                                                    Intent returnIntent = new Intent();
                                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                                    mActivity.finish();

                                                } else {
                                                    UtilView.showToast(mActivity, "Inventory Location can't add. Please try again.");
                                                }


                                            } catch (Exception e) {
                                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                            }
                                        }
                                    }
                                } else {
                                    UtilView.showToast(mActivity, getResources().getString(R.string.response_error));
                                }

                            }
                        }, false);
                        postDataTask.execute(new Gson().toJson(locationTransferData), url, "");

                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                    }

                } else {
                    UtilView.gotToLogin(mActivity);
                }

            } else {
                if (locationData == null) {
                    TextView tv = (TextView) spinFromLocation.getSelectedView();
                    if (tv != null)
                        tv.setError(getString(R.string.err_msg));

                }
                if (locationData == null) {
                    TextView tv = (TextView) spinToLocation.getSelectedView();
                    if (tv != null)
                        tv.setError(getString(R.string.err_msg));

                }
                if (moveTypeData == null) {
                    TextView tv = (TextView) spinMovementType.getSelectedView();
                    if (tv != null)
                        tv.setError(getString(R.string.err_msg));

                }

            }
        } else {
            UtilView.showToast(mActivity, "Please Select Item first.");
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        inventoryPosCreator.clear();

    }

    private void callItemView() {


            if (isAllValid && locationData != null && !locationData.getInventoryLocationName().toString().equals("Select") && moveTypeData != null && !moveTypeData.getInventoryMovementName().toString().equals("Select") ) {

                if (serverUrl != null) {
                    String url = serverUrl + "/inventory/" + Constant.FUNTION_GETITEMLISTOFLOCATION + "?from=1&moveType=1";
                    Intent intent = new Intent(mActivity, Activity_ShowItemList.class);
                    intent.putExtra("url", url);
                    intent.putExtra("taxType", selectedTax);
                    intent.putExtra("itemsearch", "");
                    intent.putExtra("selectedsupplier", supplier);
                    intent.putExtra("itemCodeId",itemCodeId );
                    intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_INVENTORY);
                    startActivityForResult(intent, Constant.RESQUSTCODE_ITEMSEARCH);
                } else {
                    UtilView.gotToLogin(mActivity);
                }

            } else {
                if (locationData == null) {
                    TextView tv = (TextView) spinFromLocation.getSelectedView();
                    tv.setError(getResources().getString(R.string.err_msg));

                }
                if (locationData == null) {

                    TextView tv1 = (TextView) spinToLocation.getSelectedView();
                    tv1.setError(getResources().getString(R.string.err_msg));
                }

                if (moveTypeData == null) {
                    TextView tv = (TextView) spinMovementType.getSelectedView();
                    tv.setError(getResources().getString(R.string.err_msg));
                }
            }

        if (spinFromLocation.getSelectedItem().toString().equals(spinToLocation.getSelectedItem().toString())){

            Toast.makeText(this,"Select Two Different Locations.",Toast.LENGTH_LONG).show();


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == RESULT_OK) {
            checkCartList();
        }
    }

    private void checkCartList() {
        if (btnProcessTransfer != null)
            btnProcessTransfer.setVisibility(View.VISIBLE);

        if (btnSelectItem != null)
            btnSelectItem.setClickable(true);


        posCartItem = inventoryPosCreator.getItems();

        for (int i = 0; i < posCartItem.size(); i++) {
            if (inventoryPosCreator != null) {
                if (inventoryPosCreator.getItems().size() > 0) {
                    itemCodeId =  inventoryPosCreator.getItems().get(i).getItemDatum().getItemCode();
                    adapter = new InventoryItemListAdapter(mActivity, posCartItem);
                    try {
                        listviewSelectItems.smoothScrollToPosition(posCartItem.size());
                        if (listviewSelectItems != null)
                            listviewSelectItems.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        UtilView.showLogCat(TAG, "checkCartList cart Exception " + e.toString());
                    }
                }

            } else {

                visibilityGone();
                posCartItem = inventoryPosCreator.getItems();
                if (adapter != null) {
                    adapter.updateCartItems(posCartItem);
                    adapter.notifyDataSetChanged();
                }
                mActivity.invalidateOptionsMenu();
            }


        }





        visibilityVisible();

    }


    private void visibilityVisible() {

        if (rlListview != null) {
            rlListview.setVisibility(View.VISIBLE);
        }

        if (listviewSelectItems != null)
            listviewSelectItems.setVisibility(View.VISIBLE);


    }

    private void visibilityGone() {

        if (rlListview != null) {
            rlListview.setVisibility(View.GONE);
        }

        if (listviewSelectItems != null)
            listviewSelectItems.setVisibility(View.GONE);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_inventory, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.delete_items:
                if (inventoryPosCreator != null) {
                    inventoryPosCreator.delete(selectItemForDelete);
                    checkCartList();
                    inventoryPosCreator.clear();
                    selectItemForDelete.clear();
                } else {
                    UtilView.showToast(mActivity, "Please select at leat one item to delete");
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
