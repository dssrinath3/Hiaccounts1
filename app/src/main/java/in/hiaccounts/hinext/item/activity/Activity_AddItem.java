package in.hiaccounts.hinext.item.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.item.adapter.AttributeConfigAdapter;
import in.hiaccounts.hinext.item.model.AddItemData;
import in.hiaccounts.hinext.item.model.AddNewItem;
import in.hiaccounts.hinext.item.model.AddedCartsList;
import in.hiaccounts.hinext.item.model.AttributeConfiguratorDTOList;
import in.hiaccounts.hinext.item.model.ItemBrandDTOList;
import in.hiaccounts.hinext.item.model.ItemCategoryDTOList;
import in.hiaccounts.hinext.item.model.ItemCountTypeDTOList;
import in.hiaccounts.hinext.item.model.ItemIPTaxDTOList;
import in.hiaccounts.hinext.item.model.ItemMSICDTOList;
import in.hiaccounts.hinext.item.model.ItemOPTaxDTOList;
import in.hiaccounts.hinext.item.model.ItemTypeDTOList;
import in.hiaccounts.hinext.item.model.ItemUOMTypeDTOList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 5/17/2017.
 */

public class Activity_AddItem extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static String TAG = Activity_AddItem.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.edItemCode)
    EditText edItemCode;
    @BindView(R.id.edItemName)
    EditText edItemName;
    @BindView(R.id.edItemDescritpion)
    EditText edItemDescritpion;
    @BindView(R.id.edSelectPhoto)
    EditText edSelectPhoto;
    @BindView(R.id.imgViewPhoto)
    ImageView imgViewPhoto;
    @BindView(R.id.spinItemCategory)
    Spinner spinItemCategory;
    @BindView(R.id.spinItemType)
    Spinner spinItemType;
    @BindView(R.id.spinItemUnitMeasurement)
    Spinner spinItemUnitMeasurement;
    @BindView(R.id.spinItemMsicCode)
    Spinner spinItemMsicCode;
    @BindView(R.id.spinnerItemBrandname)
    Spinner spinnerItemBrandname;
    @BindView(R.id.spinItemCountType)
    Spinner spinItemCountType;
    @BindView(R.id.edItemPurchasePrice)
    EditText edItemPurchasePrice;
    @BindView(R.id.idCheckPrice)
    CheckBox idCheckPrice;
    @BindView(R.id.edItemSalesPrice)
    EditText edItemSalesPrice;
    @BindView(R.id.idCheckSales)
    CheckBox idCheckSales;
    @BindView(R.id.idCheckProductionItem)
    CheckBox idCheckProductionItem;
    @BindView(R.id.id_list_Attribute)
    ListView idListAttribute;
    @BindView(R.id.spinItemInputtax)
    Spinner spinItemInputtax;
    @BindView(R.id.spinItemOutputtax)
    Spinner spinItemOutputtax;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.spinItemStatus)
    Spinner spinItemStatus;
    @BindView(R.id.edItemCessPercentage)
    EditText edItemCessPercentage;
    String imageData = "", baseImage;
    Bitmap bitmap;
    Uri selectedImage;
    List<AttributeConfiguratorDTOList> itemAttributeList;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private List<Object> objectItemCategoryList = new ArrayList<Object>();
    private List<Object> objectItemTypeList = new ArrayList<Object>();
    private List<Object> objectUnitMeasurementList = new ArrayList<Object>();
    private List<Object> objectMsicList = new ArrayList<Object>();
    private List<Object> objectBrandNameList = new ArrayList<Object>();
    private List<Object> objectItemCountypeList = new ArrayList<Object>();
    private List<Object> objectItemInputTaxList = new ArrayList<Object>();
    private List<Object> objectItemOutputTaxList = new ArrayList<Object>();
    private List<Object> objectItemStatusList = new ArrayList<Object>();
    private ItemCategoryDTOList itemCategoryDTOList = null;
    private ItemTypeDTOList itemTypeDTOList = null;
    private ItemUOMTypeDTOList itemUOMTypeDTOList = null;
    private ItemMSICDTOList itemMSICDTOList = null;
    private ItemBrandDTOList itemBrandDTOList = null;
    private ItemCountTypeDTOList itemCountTypeDTOList = null;
    private ItemIPTaxDTOList itemIPTaxDTOList = null;
    private ItemOPTaxDTOList itemOPTaxDTOList = null;
    private String callingfrom = "",checboxVal = null;
    private boolean isAllValid = true;
    private ServiceHandler serviceHandler;
    private String itemStatus, serverUrl;
    private SelectedItemsList item;
    List<AddedCartsList> cartsLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_additem);
        ButterKnife.bind(this);
        initContentView();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    private void initContentView() {
        ButterKnife.bind(this);
        activity = Activity_AddItem.this;
        serverUrl = UtilView.getUrl(activity);
        Intent intent = getIntent();
        callingfrom = intent.getStringExtra("callingfrom");
        item = (SelectedItemsList) intent.getSerializableExtra("item");

        sharedPreference = SharedPreference.getInstance(activity);
        serviceHandler = new ServiceHandler(activity);

        toolbar.setTitle(getResources().getString(R.string.addItem));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callingfrom != null) {
                    if (callingfrom.equals(Constant.NAVIGATION_INVENTORY_ITEM_EDIT)) {
                        finish();
                    } else if (callingfrom.equals(Constant.NAVIGATION_GROUP_INVENTORY)){
                        callActivityShowItem();
                    }else {
                        callActivityShowItem();
                    }
                } else {
                    callActivityShowItem();
                }

            }
        });

        getNewItemDetail();

        edItemPurchasePrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString() == null || s.toString().equals("")) {
                    edItemPurchasePrice.setError(null);
                    isAllValid = true;
                } else {
                    try {
                        edItemPurchasePrice.setError(null);
                        isAllValid = true;
                        Double purchasePrice = Double.parseDouble(s.toString().trim());
                    } catch (NumberFormatException e) {
                        edItemPurchasePrice.setError(getResources().getString(R.string.error_numberformate));
                        isAllValid = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edItemSalesPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString() == null || s.toString().equals("")) {
                    edItemSalesPrice.setError(null);
                    isAllValid = true;

                } else {
                    try {
                        edItemSalesPrice.setError(null);
                        isAllValid = true;
                        Double purchasePrice = Double.parseDouble(s.toString().trim());
                    } catch (NumberFormatException e) {

                        edItemSalesPrice.setError(getResources().getString(R.string.error_numberformate));
                        isAllValid = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        spinItemType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemTypeList.get(i);
                if (obj instanceof ItemTypeDTOList) {
                    spinItemType.setSelection(i);
                    itemTypeDTOList = (ItemTypeDTOList) spinItemType.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinItemCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemCategoryList.get(i);
                if (obj instanceof ItemCategoryDTOList) {
                    spinItemCategory.setSelection(i);
                    itemCategoryDTOList = (ItemCategoryDTOList) spinItemCategory.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinItemUnitMeasurement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectUnitMeasurementList.get(i);
                if (obj instanceof ItemUOMTypeDTOList) {
                    spinItemUnitMeasurement.setSelection(i);

                    itemUOMTypeDTOList = (ItemUOMTypeDTOList) spinItemUnitMeasurement.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinItemMsicCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectMsicList.get(i);
                if (obj instanceof ItemMSICDTOList) {
                    spinItemMsicCode.setSelection(i);
                    itemMSICDTOList = (ItemMSICDTOList) spinItemMsicCode.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerItemBrandname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectBrandNameList.get(i);
                if (obj instanceof ItemBrandDTOList) {
                    spinnerItemBrandname.setSelection(i);
                    itemBrandDTOList = (ItemBrandDTOList) spinnerItemBrandname.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinItemCountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemCountypeList.get(i);
                if (obj instanceof ItemCountTypeDTOList) {
                    spinItemCountType.setSelection(i);
                    itemCountTypeDTOList = (ItemCountTypeDTOList) spinItemCountType.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinItemInputtax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemInputTaxList.get(i);
                if (obj instanceof ItemIPTaxDTOList) {
                    spinItemInputtax.setSelection(i);
                    itemIPTaxDTOList = (ItemIPTaxDTOList) spinItemInputtax.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinItemOutputtax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemOutputTaxList.get(i);
                if (obj instanceof ItemOPTaxDTOList) {
                    spinItemOutputtax.setSelection(i);
                    itemOPTaxDTOList = (ItemOPTaxDTOList) spinItemOutputtax.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinItemStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemStatusList.get(i);
                if (obj instanceof String) {
                    spinItemStatus.setSelection(i);
                    itemStatus = (String) spinItemStatus.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void setupPageItemData() {
        AttributeConfigAdapter adapter = new AttributeConfigAdapter(activity, itemAttributeList);
        idListAttribute.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        idListAttribute.setOnItemClickListener(this);
    }

    private void getNewItemDetail() {
        JsonObject json = null;
        try {
            json = new JsonObject();
        } catch (Exception e) {

        }
        String url = "";
        if (callingfrom != null && callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETNEWITEMDETAILS;
        }
        if (callingfrom != null && callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETNEWITEMDETAILS;
        }
        if (callingfrom != null && callingfrom.equals(Constant.NAVIGATION_INVENTORY_ITEM)) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETNEWITEMDETAILS;
        }
        if (callingfrom != null && callingfrom.equals(Constant.NAVIGATION_INVENTORY_ITEM_EDIT)) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETNEWITEMDETAILS_EDIT;
        }
        if (callingfrom != null && callingfrom.equals(Constant.CALL_ADDINVENTORUOPENING_ITEM)) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETNEWITEMDETAILS;
        } else {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETNEWITEMDETAILS;
        }

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            if (ll != null)
                                ll.setVisibility(View.VISIBLE);
                            try {
                                Gson gson = new Gson();
                                AddItemData itemData = gson.fromJson(result.toString(), AddItemData.class);

                                if (itemData != null) {

                                    objectItemStatusList.clear();
                                    objectItemStatusList.add("Active");
                                    objectItemStatusList.add("InActive");
                                    UtilView.setupItemAdapter(activity, spinItemStatus, objectItemStatusList);

                                    if (itemData.getItemCategoryDTOList() != null) {
                                        objectItemCategoryList.clear();
                                        objectItemCategoryList.add("Select");
                                        objectItemCategoryList.addAll(itemData.getItemCategoryDTOList());
                                        UtilView.setupItemAdapter(activity, spinItemCategory, objectItemCategoryList);
                                    }

                                    if (itemData.getItemTypeDTOList() != null) {
                                        objectItemTypeList.clear();
                                        objectItemTypeList.add("Select");
                                        objectItemTypeList.addAll(itemData.getItemTypeDTOList());
                                        UtilView.setupItemAdapter(activity, spinItemType, objectItemTypeList);
                                    }

                                    if (itemData.getItemUOMTypeDTOList() != null) {
                                        objectUnitMeasurementList.clear();
                                        objectUnitMeasurementList.add("Select");
                                        objectUnitMeasurementList.addAll(itemData.getItemUOMTypeDTOList());
                                        UtilView.setupItemAdapter(activity, spinItemUnitMeasurement, objectUnitMeasurementList);
                                    }

                                    if (itemData.getItemMSICDTOList() != null) {
                                        objectMsicList.clear();
                                        objectMsicList.add("Select");
                                        objectMsicList.addAll(itemData.getItemMSICDTOList());
                                        UtilView.setupItemAdapter(activity, spinItemMsicCode, objectMsicList);
                                    }
                                    if (itemData.getItemBrandDTOList() != null) {
                                        objectBrandNameList.clear();
                                        objectBrandNameList.add("Select");
                                        objectBrandNameList.addAll(itemData.getItemBrandDTOList());
                                        UtilView.setupItemAdapter(activity, spinnerItemBrandname, objectBrandNameList);
                                    }
                                    if (itemData.getItemCountTypeDTOList() != null) {
                                        objectItemCountypeList.clear();
                                        objectItemCountypeList.add("Select");
                                        objectItemCountypeList.addAll(itemData.getItemCountTypeDTOList());
                                        UtilView.setupItemAdapter(activity, spinItemCountType, objectItemCountypeList);
                                    }

                                    if (itemData.getItemIPTaxDTOList() != null) {
                                        objectItemInputTaxList.clear();
                                        objectItemInputTaxList.add("Select");
                                        objectItemInputTaxList.addAll(itemData.getItemIPTaxDTOList());
                                        UtilView.setupItemAdapter(activity, spinItemInputtax, objectItemInputTaxList);
                                    }
                                    if (itemData.getItemOPTaxDTOList() != null) {
                                        objectItemOutputTaxList.clear();
                                        objectItemOutputTaxList.add("Select");
                                        objectItemOutputTaxList.addAll(itemData.getItemOPTaxDTOList());
                                        UtilView.setupItemAdapter(activity, spinItemOutputtax, objectItemOutputTaxList);
                                    }

                                    if (itemData.getAddedCartsList() != null){
                                        cartsLists.clear();
                                         cartsLists= new ArrayList<>();
                                        cartsLists.addAll(itemData.getAddedCartsList());
                                    }

                                    if (itemData.getAttributeConfiguratorDTOList() != null) {

                                        itemAttributeList = new ArrayList<>();


                                        //JSONArray jsonArray = new JSONArray(itemData.getAttributeConfiguratorDTOList());

                                        if (itemData.getAttributeConfiguratorDTOList() != null) {
                                            for (int i = 0; i < itemData.getAttributeConfiguratorDTOList().size(); i++) {
                                                AttributeConfiguratorDTOList pageItemData = itemData.getAttributeConfiguratorDTOList().get(i);
                                                // AttributeConfiguratorDTOList pageItemData = gson.fromJson(jsonObject.toString(), AttributeConfiguratorDTOList.class);
                                                itemAttributeList.add(pageItemData);
                                            }

                                            if (itemAttributeList != null && itemAttributeList.size() > 0) {

                                                setupPageItemData();

                                            }

                                        }

                                    }

                                }


                            } catch (Exception e) {
                               // UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                            }
                        } else {
                           // UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                        }
                    }
                }, false);
                postDataTask.execute(json.toString(), url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }


    @OnClick({R.id.btnClose, R.id.btnSave, R.id.edSelectPhoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edSelectPhoto:
                getPhoto();
                break;
            case R.id.btnClose:
                if (callingfrom != null) {
                    if (callingfrom.equals(Constant.NAVIGATION_INVENTORY_ITEM_EDIT)) {
                        finish();
                    } else {
                        callActivityShowItem();
                    }
                } else {
                    callActivityShowItem();
                }

                break;
            case R.id.btnSave:

                final String itemCode = edItemCode.getText().toString().trim();
                final String itemName = edItemName.getText().toString().trim();
                String itemDescription = edItemDescritpion.getText().toString().trim();
                String itemPurchasePrice = edItemPurchasePrice.getText().toString().trim();
                String itemSalesPrice = edItemSalesPrice.getText().toString().trim();
                String cess = edItemCessPercentage.getText().toString().trim();


                boolean checkPurchase = priceCheckMehtod();
                boolean checkSales = salesCheckMehtod();
                JSONObject json = new JSONObject();
                try {
                    json.put("purchases", checkPurchase);
                    json.put("sales", checkSales);
                    checboxVal = json.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (itemSalesPrice.equals("") || itemSalesPrice == null) {
                    isAllValid = true;
                    edItemSalesPrice.setError(null);
                } else {
                    try {
                        double itemSales = Double.parseDouble(itemSalesPrice);
                        isAllValid = true;
                    } catch (NumberFormatException e) {
                        edItemSalesPrice.setError(getResources().getString(R.string.error_numberformate));
                        isAllValid = false;
                    }
                }


                if (itemSalesPrice.equals("") || itemSalesPrice == null) {
                    isAllValid = true;
                    edItemSalesPrice.setError(null);
                } else {
                    try {
                        double itemPurchase = Double.parseDouble(itemPurchasePrice);
                        isAllValid = true;
                    } catch (NumberFormatException e) {
                        edItemPurchasePrice.setError(getResources().getString(R.string.error_numberformate));
                        isAllValid = false;
                    }
                }

                List<Object> al = new ArrayList<>();

                if (!itemCode.equals("") && !itemName.equals("")
                        && itemCategoryDTOList != null
                        && itemTypeDTOList != null
                        && itemUOMTypeDTOList != null
                        && itemMSICDTOList != null
                        && itemBrandDTOList != null
                        && itemCountTypeDTOList != null
                        && itemIPTaxDTOList != null
                        && itemOPTaxDTOList != null
                        && isAllValid) {


                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {

                        UtilView.showProgessBar(activity, progressBar);
                        AddNewItem addNewItem = new AddNewItem();
                        addNewItem.setItemCode(itemCode);
                        addNewItem.setItemName(itemName);
                        addNewItem.setItemDisc(itemDescription);
                        addNewItem.setSalesPrice(itemSalesPrice);
                        addNewItem.setPurchasePrice(itemPurchasePrice);
                        addNewItem.setItemCategoryDTO(itemCategoryDTOList);
                        addNewItem.setItemTypeDTO(itemTypeDTOList);
                        addNewItem.setItemUOMTypeDTO(itemUOMTypeDTOList);
                        addNewItem.setItemMSICDTO(itemMSICDTOList);
                        addNewItem.setItemBrandDTO(itemBrandDTOList);
                        addNewItem.setItemCountTypeDTO(itemCountTypeDTOList);
                        addNewItem.setItemIPTaxDTO(itemIPTaxDTOList);
                        addNewItem.setItemOPTaxDTO(itemOPTaxDTOList);
                        addNewItem.setCess(cess);
                        addNewItem.setItemStatus(itemStatus);
                        addNewItem.setInclusiveJSON(checboxVal);
                        addNewItem.setProductionItem(productionItemStatus());
                        addNewItem.setAttributeConfiguratorDTOList(itemAttributeList);
                        addNewItem.setAddedCartsList(cartsLists);
                        addNewItem.setItemImage(imageData);
                        final Gson gson = new Gson();
                        String itemJson = gson.toJson(addNewItem);
                        String url = "";
                        if (callingfrom != null && callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                            url = serverUrl + "/hipos//0/" + Constant.FUNTION_SAVENEWITEM;
                        }
                        if (callingfrom != null && callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                            // url = sharedPreference.getData(Constant.SERVER_URL) + Constant.POS_URLPATH + "purchase/0/" + Constant.FUNTION_SAVENEWITEM;
                            url = serverUrl + "/hipos//0/" + Constant.FUNTION_SAVENEWITEM;
                        }
                        if (callingfrom != null && callingfrom.equals(Constant.NAVIGATION_INVENTORY_ITEM)) {
                            url = serverUrl + "/hipos//1/" + Constant.FUNTION_SAVENEWITEM;
                        }
                        if (callingfrom != null && callingfrom.equals(Constant.CALL_ADDINVENTORUOPENING_ITEM)) {
                            url = serverUrl + "/hipos//1/" + Constant.FUNTION_SAVENEWITEM;
                        }
                        if (serverUrl != null) {
                            PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.hideProgessBar(activity, progressBar);
                                    if (result != null) {
                                        try {
                                            List<SelectedItemsList>itemsLists=new ArrayList<>();

                                            SelectedItemsList item=gson.fromJson(result.toString(),SelectedItemsList.class);
                                            itemsLists.add(item);

                                        /*    List<SelectedItemsList> itemsLists=new ArrayList<>();
                                            JSONArray jsonArray=new JSONArray(result.toString());
                                            for (int i=0;i<jsonArray.length();i++){
                                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                                SelectedItemsList item=gson.fromJson(jsonObject.toString(),SelectedItemsList.class);
                                                itemsLists.add(item);
                                            }*/
                                            if (itemsLists.size()>0){
                                                UtilView.showToast(activity, itemName + " create successfully.");
                                                //resetView();
                                            }
                                            UtilView.showToast(activity, "Item create successfully.");

                                            Intent in = new Intent();
                                            activity.setResult(Activity.RESULT_OK, in);
                                            activity.finish();

                                        }catch (Exception e){
                                            UtilView.showToast(activity, "Item create successfully.");

                                            Intent in = new Intent();
                                            activity.setResult(Activity.RESULT_OK, in);
                                            activity.finish();
                                           // UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                                        }


                                    } else {
                                        UtilView.showToast(activity, itemName + " doesn't create successfully.");
                                    }
                                }
                            }, false);
                            postDataTask.execute(itemJson.toString(), url, "");
                        } else {
                            UtilView.gotToLogin(activity);
                        }
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                    }
                } else {
                    if (itemCode.equals("")) {
                        edItemCode.setError(getString(R.string.err_msg));
                    }
                    if (itemName.equals("")) {
                        edItemName.setError(getString(R.string.err_msg));
                    }
                    if (itemCategoryDTOList == null) {
                        TextView tv = (TextView) spinItemCategory.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (itemTypeDTOList == null) {
                        TextView tv = (TextView) spinItemType.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (itemUOMTypeDTOList == null) {
                        TextView tv = (TextView) spinItemUnitMeasurement.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (itemMSICDTOList == null) {
                        TextView tv = (TextView) spinItemMsicCode.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (itemBrandDTOList == null) {
                        TextView tv = (TextView) spinnerItemBrandname.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (itemCountTypeDTOList == null) {
                        TextView tv = (TextView) spinItemCountType.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (itemIPTaxDTOList == null) {
                        TextView tv = (TextView) spinItemInputtax.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (itemOPTaxDTOList == null) {
                        TextView tv = (TextView) spinItemOutputtax.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                }
                break;
        }
    }

    private void getPhoto() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_AddItem.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }

        });

        builder.show();
    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }

                try {

                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    //  Log.e("path of image from Camera......******************.........", f.getAbsolutePath() + "");
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);
                    baseImage = ConvertBitmapToString(bitmap);
                    imgViewPhoto.setImageBitmap(bitmap);
                    imageData += "data:image/png;base64," + baseImage;


                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                bitmap = (BitmapFactory.decodeFile(picturePath));
                baseImage = ConvertBitmapToString(bitmap);
                // Log.e("path of image from gallery......******************.........", picturePath + "");
                imgViewPhoto.setImageBitmap(bitmap);
                imageData += "data:image/png;base64," + baseImage;

            }

        }

    }

    private String ConvertBitmapToString(Bitmap bitmap) {
        String encodedImage = "";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        try {
            encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodedImage;
    }

    private void resetView() {

        edItemCode.setText("");
        edItemName.setText("");
        edItemDescritpion.setText("");
        edItemSalesPrice.setText("");
        edItemPurchasePrice.setText("");

        spinItemCategory.setSelection(0);
        spinItemType.setSelection(0);
        spinItemCountType.setSelection(0);
        spinItemOutputtax.setSelection(0);
        spinItemInputtax.setSelection(0);
        spinItemMsicCode.setSelection(0);
        spinItemUnitMeasurement.setSelection(0);
        spinnerItemBrandname.setSelection(0);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        callActivityShowItem();

    }

    private void callActivityShowItem() {
        Intent intent = new Intent(this, Activity_ShowItemList.class);
        String url1 = "";

        if (callingfrom != null) {
            if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                url1 = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
            }
            if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                url1 = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
            }
            if (callingfrom.equals(Constant.NAVIGATION_INVENTORY_ITEM)) {
                Intent intent1 = new Intent(this, NavigationDrawer_Activity.class);
                intent1.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_INVENTORYMENU_ITEM);
                startActivity(intent1);
                finish();
            }
            if (callingfrom.equals(Constant.CALL_ADDINVENTORUOPENING_ITEM)) {
                url1 = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
            }
            if (callingfrom.equals(Constant.NAVIGATION_GROUP_INVENTORY)) {
                url1 = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
            }

        }
        if (!url1.equals("")) {
            intent.putExtra("url", url1);
            intent.putExtra("itemsearch", "");
            intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_RETAIL);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }


    private boolean priceCheckMehtod() {
        return idCheckPrice.isChecked();
    }
    private boolean salesCheckMehtod() {
        return idCheckSales.isChecked();
    }

    private String productionItemStatus() {
        String status = "";
        if (idCheckProductionItem.isChecked() == true) {
            status = "true";
        } else {
            status = "false";
        }
        return status;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int viewId = view.getId();
        AttributeConfiguratorDTOList items = itemAttributeList.get(i);
        if (viewId == R.id.id_moduleCheck) {
            CheckBox checkBox = view.findViewById(R.id.id_moduleCheck);
            items.setStatus(checkBox.isChecked());
        }
    }
}
