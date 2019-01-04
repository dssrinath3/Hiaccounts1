package in.hiaccounts.hinext.item.activity;

import android.Manifest;
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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rey.material.widget.ProgressView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

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
import in.hiaccounts.hinext.item.adapter.AttributeConfigAdapter;
import in.hiaccounts.hinext.item.model.AddNewItem;
import in.hiaccounts.hinext.item.model.AddedCartsList;
import in.hiaccounts.hinext.item.model.AttributeConfiguratorDTOList;
import in.hiaccounts.hinext.item.model.ItemBrandDTOList;
import in.hiaccounts.hinext.item.model.ItemCountTypeDTOList;
import in.hiaccounts.hinext.item.model.ItemIPTaxDTOList;
import in.hiaccounts.hinext.item.model.ItemModelData;
import in.hiaccounts.hinext.item.model.ItemOPTaxDTOList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Prateek on 5/17/2017.
 */

public class Activity_EditItem extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, AdapterView.OnItemClickListener {

    private static final int READ_REQUEST_CODE = 300;
    public static String TAG = Activity_EditItem.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edItemCode)
    EditText edItemCode;
    @BindView(R.id.edItemName)
    EditText edItemName;
    @BindView(R.id.edItemDescritpion)
    EditText edItemDescritpion;
    @BindView(R.id.edItemCategory)
    EditText edItemCategory;
    @BindView(R.id.edItemType)
    EditText edItemType;
    @BindView(R.id.edItemUOM)
    EditText edItemUOM;
    @BindView(R.id.edItemHsnCode)
    EditText edItemHsnCode;
    @BindView(R.id.spinItemCountType)
    Spinner spinItemCountType;
    @BindView(R.id.spinnerItemBrandname)
    Spinner spinnerItemBrandname;
    @BindView(R.id.spinItemStatus)
    Spinner spinItemStatus;
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
    @BindView(R.id.edItemCessPercentage)
    EditText edItemCessPercentage;
    @BindView(R.id.edSelectPhoto)
    EditText edSelectPhoto;
    @BindView(R.id.imgViewPhoto)
    ImageView imgViewPhoto;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    String encodedString;
    Bitmap bitmap;
    Uri selectedImage;
    String imageData = "", baseImage;
    File file = null;
    List<AttributeConfiguratorDTOList> itemAttributeList;
    private List<Object> objectBrandNameList = new ArrayList<Object>();
    private List<Object> objectItemInputTaxList = new ArrayList<Object>();
    private List<Object> objectItemOutputTaxList = new ArrayList<Object>();
    private List<Object> objectItemStatusList = new ArrayList<Object>();
    private List<Object> objectItemCountypeList = new ArrayList<Object>();
    private String callingfrom = "",checboxVal = null;
    private boolean isAllValid = true;
    private ServiceHandler serviceHandler;
    private String itemStatus,serverUrl;
    private ItemModelData item;
    private Activity activity;
    //SharedPreference sharedPreference;
    private Boolean isInternetPresent = false;
    private Uri uri;
    List<AddedCartsList> cartsLists;
    List<AddedCartsList> history;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edititem);
        ButterKnife.bind(this);
        initContentView();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

    }

    private void initContentView() {
        ButterKnife.bind(this);
        activity = Activity_EditItem.this;
        serverUrl=UtilView.getUrl(activity);
        Intent intent = getIntent();
        callingfrom = intent.getStringExtra("callingfrom");
        item = (ItemModelData) intent.getSerializableExtra("item");
        serviceHandler = new ServiceHandler(activity);

        toolbar.setTitle(getResources().getString(R.string.editItem));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (item != null) {
            getItemDetail();
        }

        edItemCessPercentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString() == null || s.toString().equals("")) {
                    edItemCessPercentage.setError(null);
                    isAllValid = true;
                } else {
                    try {
                        edItemCessPercentage.setError(null);
                        isAllValid = true;
                        Double purchasePrice = Double.parseDouble(s.toString().trim());
                    } catch (NumberFormatException e) {
                        edItemCessPercentage.setError(getResources().getString(R.string.error_numberformate));
                        isAllValid = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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
        spinItemCountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemCountypeList.get(i);
                if (obj instanceof ItemCountTypeDTOList) {
                    spinItemCountType.setSelection(i);
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

    private void getItemDetail() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("itemSearchText", item.getItemId());
        } catch (Exception e) {

        }
        progressBar.setVisibility(View.VISIBLE);
        String url = "";
        url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETNEWITEMDETAILS_EDIT + item.getItemId();


        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            if (result.equals("No Response Body")) {

                            } else {
                                Gson gson = new Gson();
                                try {
                                    SelectedItemsList itemData = gson.fromJson(result.toString(), SelectedItemsList.class);
                                    if (itemData != null) {
                                        setupItemDetail(itemData);
                                    } else {
                                        //UtilView.showToast(activity, "Some Error. Please try Again");
                                    }
                                }catch (Exception e){
                                    // UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                }
                            }
                        } else {
                            //UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                        }
                    }
                }, false);
                postDataTask.execute(jsonObject.toString(), url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.nocontact_available), activity);
            }
        }else {
            UtilView.gotToLogin(activity);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @OnClick({R.id.btnClose, R.id.btnSave, R.id.edSelectPhoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edSelectPhoto:
                getPhoto();
                break;
            case R.id.btnClose:
                finish();
                break;
            case R.id.btnSave:
                updateItem();
                break;
        }
    }

    private void getPhoto() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_EditItem.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
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
                    edSelectPhoto.setText("" + temp.getName());
                    if (temp.getName().equals("photo.jpg")) {
                        f = temp;
                        break;
                    }
                }

                try {
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    // Log.e("path of image from Camera......******************.........", f.getAbsolutePath()+"");
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);
                    baseImage = ConvertBitmapToString(bitmap);
                    imgViewPhoto.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                uri = data.getData();
                if (EasyPermissions.hasPermissions(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    String filePath1 = getRealPathFromURIPath(uri, activity);
                    file = new File(filePath1);
                    if (file != null) {
                        edSelectPhoto.setText("" + file.getName());
                        if (file.getName() != null) {
                            bitmap = (BitmapFactory.decodeFile(filePath1));
                            baseImage = ConvertBitmapToString(bitmap);
                            // Log.e("path of image from gallery......******************.........", filePath1 + "");
                            Log.e("image Data", baseImage);
                            imgViewPhoto.setImageBitmap(bitmap);
                        }
                    }
                } else {
                    EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
                }
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
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (uri != null) {
            String filePath = getRealPathFromURIPath(uri, activity);
            file = new File(filePath);

        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }

    private String ConvertBitmapToString(Bitmap bitmap) {
        String encodedImage = "";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodedImage;
    }
    private void updateItem() {

        String itemCode = edItemCode.getText().toString().trim();
        final String itemName = edItemName.getText().toString().trim();
        String itemDescription = edItemDescritpion.getText().toString().trim();
        String itemPurchasePrice = edItemPurchasePrice.getText().toString().trim();
        String itemSalesPrice = edItemSalesPrice.getText().toString().trim();
        String cess = edItemCessPercentage.getText().toString().trim();

        imageData += "data:image/png;base64," + baseImage;

        ItemBrandDTOList itemBrandDTOList = (ItemBrandDTOList) spinnerItemBrandname.getSelectedItem();
        ItemOPTaxDTOList itemOPTaxDTOList = (ItemOPTaxDTOList) spinItemOutputtax.getSelectedItem();
        ItemIPTaxDTOList itemIPTaxDTOList = (ItemIPTaxDTOList) spinItemInputtax.getSelectedItem();
        ItemCountTypeDTOList itemCountTypeList = (ItemCountTypeDTOList) spinItemCountType.getSelectedItem();

        Long itemBrandID = itemBrandDTOList.getBrandId();
        Long itemIPTaxID = itemIPTaxDTOList.getTaxIPId();
        Long itemOPTaxID = itemOPTaxDTOList.getTaxOPId();
        Long itemCountTypeID = itemCountTypeList.getInventoryMovementId();

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


        double itemSales = 0, itemPurchase = 0, itemCess = 0;
        if (itemSalesPrice.equals("") || itemSalesPrice == null) {
            isAllValid = true;
            edItemSalesPrice.setError(null);
        } else {
            try {
                itemSales = Double.parseDouble(itemSalesPrice);
                isAllValid = true;
            } catch (NumberFormatException e) {
                edItemSalesPrice.setError(getResources().getString(R.string.error_numberformate));
                isAllValid = false;
            }
        }

        if (itemPurchasePrice.equals("") || itemPurchasePrice == null) {
            isAllValid = true;
            edItemPurchasePrice.setError(null);
        } else {
            try {
                itemPurchase = Double.parseDouble(itemPurchasePrice);
                isAllValid = true;
            } catch (NumberFormatException e) {
                edItemPurchasePrice.setError(getResources().getString(R.string.error_numberformate));
                isAllValid = false;
            }
        }
        if (cess.equals("") || cess == null) {
            isAllValid = true;
            edItemCessPercentage.setError(null);
        } else {
            try {
                itemCess = Double.parseDouble(cess);
                isAllValid = true;
            } catch (NumberFormatException e) {
                edItemCessPercentage.setError(getResources().getString(R.string.error_numberformate));
                isAllValid = false;
            }
        }


        if (isAllValid) {

            AddNewItem addNewItem = new AddNewItem();
            addNewItem.setItemId(item.getItemId());
            addNewItem.setItemCode(itemCode);
            addNewItem.setItemName(itemName);
            addNewItem.setItemCountTypeDTO(itemCountTypeList);
            addNewItem.setItemDisc(itemDescription);
            addNewItem.setSalesPrice(String.valueOf(itemSales));
            addNewItem.setPurchasePrice(String.valueOf(itemPurchase));
            addNewItem.setItemBrandDTO(itemBrandDTOList);
            addNewItem.setItemIPTaxDTO(itemIPTaxDTOList);
            addNewItem.setItemOPTaxDTO(itemOPTaxDTOList);
            addNewItem.setCess(String.valueOf(itemCess));
            addNewItem.setItemStatus(itemStatus);
            addNewItem.setInclusiveJSON(checboxVal);
            addNewItem.setProductionItem(productionItemStatus());
            addNewItem.setAttributeConfiguratorDTOList(itemAttributeList);
            addNewItem.setItemImage(imageData);
            addNewItem.setAddedCartsList(history);
            final Gson gson = new Gson();
            String itemJson = gson.toJson(addNewItem);

            String url = serverUrl + "/hipos//0/saveUpdateItem";
            if (serverUrl!=null) {
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    UtilView.showProgessBar(activity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);
                            if (result != null) {
                                try {
                                    List<SelectedItemsList> itemsLists = new ArrayList<>();

                                    SelectedItemsList item = gson.fromJson(result.toString(), SelectedItemsList.class);
                                    itemsLists.add(item);

                                    if (itemsLists.size() > 0) {
                                        UtilView.showToast(activity, itemName + " updated successfully.");

                                    }
                                    Intent in = new Intent();
                                    activity.setResult(Activity.RESULT_OK, in);
                                    activity.finish();

                                }catch (Exception e){
                                    Intent in = new Intent();
                                    activity.setResult(Activity.RESULT_OK, in);
                                    activity.finish();
                                    UtilView.showToast(activity, itemName + " updated successfully.");
                                    // UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                                }
                            } else {

                                UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error),activity);
                            }

                        }
                    }, false);
                    postDataTask.execute(itemJson.toString(), url, "");

                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.nocontact_available), activity);
                }
            }else {
                UtilView.gotToLogin(activity);
            }
        }
    }

    public void setupItemDetail(SelectedItemsList itemData) {

        if (ll!=null){
            ll.setVisibility(View.VISIBLE);
        }

        if (itemData.getImageLocation() != null) {
            String url = UtilView.getUrl(activity);

            Log.e("image url   ", itemData.getImageLocation());
            if (url != null) {
                Picasso.with(activity).load(url + itemData.getImageLocation())
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .placeholder(R.drawable.ic_progress)
                        .error(R.drawable.ic_app_icon)
                        .into(imgViewPhoto);
            } else {
                UtilView.gotToLogin(activity);
            }
        }

        if (itemData.getItemCode() != null)
            edItemCode.setText(itemData.getItemCode());

        if (itemData.getItemName() != null)
            edItemName.setText(itemData.getItemName());

        if (itemData.getItemDesc() != null)
            edItemDescritpion.setText(itemData.getItemDesc());


        if (itemData.getItemTypeName() != null)
            edItemType.setText(itemData.getItemTypeName());

        if (itemData.getUomName() != null)
            edItemUOM.setText(itemData.getUomName());

        if (itemData.getHsnCode() != null)
            edItemHsnCode.setText(itemData.getHsnCode());

        edItemPurchasePrice.setText("" + itemData.getPurchasePrice());
        edItemSalesPrice.setText("" + itemData.getSalesPrice());

        if (itemData.getCess() != null)
            edItemCessPercentage.setText(itemData.getCess());

        if (itemData.getHsnCode() != null)
            edItemHsnCode.setText(itemData.getHsnCode());

        if(itemData.getInclusiveJSON() !=null) {

            JSONObject json = null;
            try {
                json = new JSONObject(itemData.getInclusiveJSON());
                String purc = json.getString("purchases");
                String sale = json.getString("sales");


                if (purc.toString() == "true" ){
                    idCheckPrice.setChecked(true);

                } else {
                    idCheckPrice.setChecked(false);
                }
                if (sale.toString() == "true"){
                    idCheckSales.setChecked(true);
                }else{
                    idCheckSales.setChecked(false);
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        if (itemData.getItemCategoryDTOList() != null) {
            for (int i = 0; i < itemData.getItemCategoryDTOList().size(); i++) {
                if (itemData.getItemCategoryId() == itemData.getItemCategoryDTOList().get(i).getItemCategoryId()) {
                    if (itemData.getItemCategoryDTOList().get(i).getItemCategoryName() != null)
                        edItemCategory.setText(itemData.getItemCategoryDTOList().get(i).getItemCategoryName());
                }
            }
        }

        if (itemData.getItemTypeDTOList() != null) {
            for (int i = 0; i < itemData.getItemTypeDTOList().size(); i++) {
                if (itemData.getItemTypeId() == itemData.getItemTypeDTOList().get(i).getItemTypeId()) {
                    if (itemData.getItemTypeDTOList().get(i).getItemTypeName() != null)
                        edItemType.setText(itemData.getItemTypeDTOList().get(i).getItemTypeName());
                }
            }
        }
        if (itemData.getItemUOMTypeDTOList() != null) {
            for (int i = 0; i < itemData.getItemUOMTypeDTOList().size(); i++) {
                if (itemData.getUnitOfMeasurementId() == itemData.getItemUOMTypeDTOList().get(i).getUnitOfMeasurementId()) {
                    if (itemData.getItemUOMTypeDTOList().get(i).getUnitOfMeasurementName() != null)
                        edItemUOM.setText(itemData.getItemUOMTypeDTOList().get(i).getUnitOfMeasurementName());
                }
            }
        }
        if (itemData.getItemMSICDTOList() != null) {
            for (int i = 0; i < itemData.getItemMSICDTOList().size(); i++) {
                if (itemData.getMscid() == itemData.getItemMSICDTOList().get(i).getMscid()) {
                    if (itemData.getItemMSICDTOList().get(i).getDescription() != null)
                        edItemHsnCode.setText(itemData.getItemMSICDTOList().get(i).getDescription());
                }
            }
        }

        if (itemData.getItemCountTypeDTOList() != null) {
            objectItemCountypeList.clear();
            objectItemCountypeList.addAll(itemData.getItemCountTypeDTOList());
            UtilView.setupItemAdapter(activity, spinItemCountType, objectItemCountypeList);
            for (int i = 0; i < itemData.getItemCountTypeDTOList().size(); i++) {
                if (itemData.getInventoryMovementId() == itemData.getItemCountTypeDTOList().get(i).getInventoryMovementId()) {
                    spinItemCountType.setSelection(i);
                }
            }

        }

        if (itemData.getItemBrandDTOList() != null) {
            objectBrandNameList.clear();
            objectBrandNameList.addAll(itemData.getItemBrandDTOList());
            UtilView.setupItemAdapter(activity, spinnerItemBrandname, objectBrandNameList);

            for (int i = 0; i < itemData.getItemBrandDTOList().size(); i++) {
                if (itemData.getBrandId() == itemData.getItemBrandDTOList().get(i).getBrandId()) {
                    spinnerItemBrandname.setSelection(i);
                }

            }
        }


        if (itemData.getItemIPTaxDTOList() != null) {
            objectItemInputTaxList.clear();
            objectItemInputTaxList.addAll(itemData.getItemIPTaxDTOList());
            UtilView.setupItemAdapter(activity, spinItemInputtax, objectItemInputTaxList);

            for (int i = 0; i < itemData.getItemIPTaxDTOList().size(); i++) {
                if (itemData.getInputTaxId() == itemData.getItemIPTaxDTOList().get(i).getTaxIPId()) {
                    spinItemInputtax.setSelection(i);
                }

            }

        }
        if (itemData.getItemOPTaxDTOList() != null) {
            objectItemOutputTaxList.clear();
            objectItemOutputTaxList.addAll(itemData.getItemOPTaxDTOList());
            UtilView.setupItemAdapter(activity, spinItemOutputtax, objectItemOutputTaxList);
            for (int i = 0; i < itemData.getItemOPTaxDTOList().size(); i++) {
                UtilView.showLogCat(TAG, "OutPut Id " + itemData.getOuputTaxId());
                if (itemData.getOuputTaxId() == itemData.getItemOPTaxDTOList().get(i).getTaxOPId()) {
                    spinItemOutputtax.setSelection(i);
                }

            }
        }

        if (itemData.getAttributeConfiguratorDTOList() != null) {

            itemAttributeList = new ArrayList<>();


            //JSONArray jsonArray = new JSONArray(itemData.getAttributeConfiguratorDTOList());

            if (itemData.getAttributeConfiguratorDTOList() != null) {
                for (int i = 0; i < itemData.getAttributeConfiguratorDTOList().size(); i++) {
                    AttributeConfiguratorDTOList pageItemData = itemData.getAttributeConfiguratorDTOList().get(i);

                    itemAttributeList.add(pageItemData);
                }

                if (itemAttributeList != null && itemAttributeList.size() > 0) {

                    setupPageItemData();

                }

            }

        }



        objectItemStatusList.clear();
        objectItemStatusList.add("Active");
        objectItemStatusList.add("InActive");
        UtilView.setupItemAdapter(activity, spinItemStatus, objectItemStatusList);
        if (itemData.getStatus() != null) {

            if (itemData.getStatus().equals("Active")) {
                spinItemStatus.setSelection(0);
            }
            if (itemData.getStatus().equals("InActive")) {
                spinItemStatus.setSelection(1);
            }
        }

        if (itemData.getAttributeJSON() != null) {

            JSONObject json = null;
            JSONArray jsonArray = null;
            int attributeConfiguratorId;
            String attributeConfiguratorName, status;
            try {
                jsonArray = new JSONArray(itemData.getAttributeJSON());
                for (int i = 0; i < jsonArray.length(); i++) {
                    json = jsonArray.getJSONObject(i);
                    attributeConfiguratorId = json.getInt("attributeConfiguratorId");
                    attributeConfiguratorName = json.getString("attributeConfiguratorName");
                    status = json.getString("status");
                    CheckBox checkBox = (CheckBox) findViewById(R.id.id_moduleCheck);
                    if (status.equals("true")) {
                        checkBox.setChecked(true);

                    } else {

                        checkBox.setChecked(false);
                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        idCheckProductionItem.setChecked(true);
        productionItemStatus();


        if (itemData.getImageLocation() != null) {
            edSelectPhoto.setText(itemData.getImageLocation());

        }
        if (itemData.getItemImage() != null) {
            edSelectPhoto.setText(itemData.getItemImage());
        }

        if (itemData.getCartValue() != null){

            Gson gson = new Gson();
            cartsLists = new ArrayList<>();

            Type listType = new TypeToken< ArrayList<AddedCartsList> >(){}.getType();
             history = gson.fromJson(itemData.getCartValue(), listType);

      /*      AddedCartsList cartsList = gson.fromJson(itemData.getCartValue(),AddedCartsList.class);
            AddedCartsList cart = new AddedCartsList();
            cart.setCartId(cartsList.getCartId());
            cart.setCartName(cartsList.getCartName());
            cart.setStatus(cartsList.getStatus());

            cartsLists.add(cart);*/
        }

    }

    private void setupPageItemData() {
        AttributeConfigAdapter adapter = new AttributeConfigAdapter(activity, itemAttributeList);
        idListAttribute.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        idListAttribute.setOnItemClickListener(this);
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
