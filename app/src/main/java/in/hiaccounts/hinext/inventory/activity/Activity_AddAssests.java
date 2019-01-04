package in.hiaccounts.hinext.inventory.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.assests.AddNewAssests;
import in.hiaccounts.hinext.inventory.model.assests.AssestCategoryDTOList;
import in.hiaccounts.hinext.inventory.model.assests.AssestsData;
import in.hiaccounts.hinext.inventory.model.assests.ItemBrandDTOList;
import in.hiaccounts.hinext.inventory.model.assests.ItemCountTypeDTOList;
import in.hiaccounts.hinext.inventory.model.assests.ItemIPTaxDTOList;
import in.hiaccounts.hinext.inventory.model.assests.ItemMSICDTOList;
import in.hiaccounts.hinext.inventory.model.assests.ItemOPTaxDTOList;
import in.hiaccounts.hinext.inventory.model.assests.ItemTypeDTOList;
import in.hiaccounts.hinext.inventory.model.assests.ItemUOMTypeDTOList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class Activity_AddAssests extends AppCompatActivity {

    public static String TAG = Activity_AddAssests.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edAssestsCode)
    EditText edAssestsCode;
    @BindView(R.id.edAssestsPartno)
    EditText edAssestsPartno;
    @BindView(R.id.edAssestsName)
    EditText edAssestsName;
    @BindView(R.id.edAssestsDescritpion)
    EditText edAssestsDescritpion;
    @BindView(R.id.spinAssestsCategory)
    Spinner spinAssestsCategory;
    @BindView(R.id.spinAssestsType)
    Spinner spinAssestsType;
    @BindView(R.id.spinAssestsUnitMeasurement)
    Spinner spinAssestsUnitMeasurement;
    @BindView(R.id.spinAssestsMsicCode)
    Spinner spinAssestsMsicCode;
    @BindView(R.id.spinnerAssestsBrandname)
    Spinner spinnerAssestsBrandname;
    @BindView(R.id.spinAssestsCountType)
    Spinner spinAssestsCountType;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.spinAssestsInputtax)
    Spinner spinAssestsInputtax;
    @BindView(R.id.spinAssestsOutputtax)
    Spinner spinAssestsOutputtax;
    @BindView(R.id.edAssestsPurchasePrice)
    EditText edAssestsPurchasePrice;
    @BindView(R.id.idCheckPrice)
    CheckBox idCheckPrice;
    @BindView(R.id.edAssestsSalesPrice)
    EditText edAssestsSalesPrice;
    @BindView(R.id.idCheckSales)
    CheckBox idCheckSales;
    @BindView(R.id.spinAssestsStatus)
    Spinner spinAssestsStatus;
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
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private ServiceHandler serviceHandler;
    private String itemStatus, serverUrl;
    private String callingfrom = "",checboxVal = null;
    private boolean isAllValid = true;
    private List<Object> objectAssestsCategoryList = new ArrayList<Object>();
    private List<Object> objectAssestsTypeList = new ArrayList<Object>();
    private List<Object> objectAssestsUnitMeasurementList = new ArrayList<Object>();
    private List<Object> objectAssestsMsicList = new ArrayList<Object>();
    private List<Object> objectAssestsBrandNameList = new ArrayList<Object>();
    private List<Object> objectAssestsCountypeList = new ArrayList<Object>();
    private List<Object> objectAssestsInputTaxList = new ArrayList<Object>();
    private List<Object> objectAssestsOutputTaxList = new ArrayList<Object>();
    private List<Object> objectAssestsStatusList = new ArrayList<Object>();
    private AssestCategoryDTOList assestsCategoryDTOList = null;
    private ItemTypeDTOList assestsTypeDTOList = null;
    private ItemUOMTypeDTOList assestsUOMTypeDTOList = null;
    private ItemMSICDTOList assestsMSICDTOList = null;
    private ItemBrandDTOList assestsBrandDTOList = null;
    private ItemCountTypeDTOList assestsCountTypeDTOList = null;
    private ItemIPTaxDTOList assestsIPTaxDTOList = null;
    private ItemOPTaxDTOList assestsOPTaxDTOList = null;
    private AssestsData assestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assests);
        ButterKnife.bind(this);
        initContentView();
    }

    private void initContentView() {
        activity = Activity_AddAssests.this;
        serverUrl = UtilView.getUrl(activity);
        Intent intent = getIntent();
        callingfrom = intent.getStringExtra("callingfrom");
        assestData = (AssestsData) intent.getSerializableExtra("assests");

        sharedPreference = SharedPreference.getInstance(activity);
        serviceHandler = new ServiceHandler(activity);

        toolbar.setTitle(getResources().getString(R.string.addAssests));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callingfrom != null) {
                    if (callingfrom.equals(Constant.NAVIGATION_INVENTORY_ASSESTS_EDIT)) {
                        finish();
                    } else {

                    }
                } else {

                }

            }
        });

        getNewAssestsDetail();
        edAssestsPurchasePrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString() == null || s.toString().equals("")) {
                    edAssestsPurchasePrice.setError(null);
                    isAllValid = true;
                } else {
                    try {
                        edAssestsPurchasePrice.setError(null);
                        isAllValid = true;
                        Double purchasePrice = Double.parseDouble(s.toString().trim());
                    } catch (NumberFormatException e) {
                        edAssestsPurchasePrice.setError(getResources().getString(R.string.error_numberformate));
                        isAllValid = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edAssestsSalesPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString() == null || s.toString().equals("")) {
                    edAssestsSalesPrice.setError(null);
                    isAllValid = true;

                } else {
                    try {
                        edAssestsSalesPrice.setError(null);
                        isAllValid = true;
                        Double purchasePrice = Double.parseDouble(s.toString().trim());
                    } catch (NumberFormatException e) {

                        edAssestsSalesPrice.setError(getResources().getString(R.string.error_numberformate));
                        isAllValid = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        spinAssestsCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAssestsCategoryList.get(i);
                if (obj instanceof AssestCategoryDTOList) {
                    spinAssestsCategory.setSelection(i);
                    assestsCategoryDTOList = (AssestCategoryDTOList) spinAssestsCategory.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinAssestsType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAssestsTypeList.get(i);
                if (obj instanceof ItemTypeDTOList) {
                    spinAssestsType.setSelection(i);
                    assestsTypeDTOList = (ItemTypeDTOList) spinAssestsType.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       spinAssestsUnitMeasurement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAssestsUnitMeasurementList.get(i);
                if (obj instanceof ItemUOMTypeDTOList) {
                    spinAssestsUnitMeasurement.setSelection(i);

                    assestsUOMTypeDTOList = (ItemUOMTypeDTOList) spinAssestsUnitMeasurement.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinAssestsMsicCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAssestsMsicList.get(i);
                if (obj instanceof ItemMSICDTOList) {
                    spinAssestsMsicCode.setSelection(i);
                    assestsMSICDTOList = (ItemMSICDTOList) spinAssestsMsicCode.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerAssestsBrandname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAssestsBrandNameList.get(i);
                if (obj instanceof ItemBrandDTOList) {
                    spinnerAssestsBrandname.setSelection(i);
                    assestsBrandDTOList = (ItemBrandDTOList) spinnerAssestsBrandname.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinAssestsCountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAssestsCountypeList.get(i);
                if (obj instanceof ItemCountTypeDTOList) {
                    spinAssestsCountType.setSelection(i);
                    assestsCountTypeDTOList = (ItemCountTypeDTOList) spinAssestsCountType.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinAssestsInputtax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAssestsInputTaxList.get(i);
                if (obj instanceof ItemIPTaxDTOList) {
                    spinAssestsInputtax.setSelection(i);
                   assestsIPTaxDTOList = (ItemIPTaxDTOList) spinAssestsInputtax.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinAssestsOutputtax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAssestsOutputTaxList.get(i);
                if (obj instanceof ItemOPTaxDTOList) {
                    spinAssestsOutputtax.setSelection(i);
                    assestsOPTaxDTOList = (ItemOPTaxDTOList) spinAssestsOutputtax.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinAssestsStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAssestsStatusList.get(i);
                if (obj instanceof String) {
                    spinAssestsStatus.setSelection(i);
                    itemStatus = (String) spinAssestsStatus.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void getNewAssestsDetail() {
        JsonObject json = null;
        try {
            json = new JsonObject();
        } catch (Exception e) {

        }
        String url = "";

        if (callingfrom != null && callingfrom.equals(Constant.NAVIGATION_INVENTORY_ASSESTS)) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETNEWASSESTSDETAILS;
        }
        if (callingfrom != null && callingfrom.equals(Constant.NAVIGATION_INVENTORY_ASSESTS_EDIT)) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETNEWASSESTSDETAILS_EDIT;
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
                                AssestsData assestsData = gson.fromJson(result.toString(), AssestsData.class);

                                if (assestsData != null) {
                                    if (assestsData.getAssestCategoryDTOList() != null) {
                                        objectAssestsCategoryList.clear();
                                        objectAssestsCategoryList.add("Select");
                                        objectAssestsCategoryList.addAll(assestsData.getAssestCategoryDTOList());
                                        UtilView.setupItemAdapter(activity, spinAssestsCategory, objectAssestsCategoryList);
                                    }

                                    if (assestsData.getItemTypeDTOList()!= null) {
                                        objectAssestsTypeList.clear();
                                        objectAssestsTypeList.add("Select");
                                        objectAssestsTypeList.addAll(assestsData.getItemTypeDTOList());
                                        UtilView.setupItemAdapter(activity, spinAssestsType, objectAssestsTypeList);
                                    }

                                    if (assestsData.getItemUOMTypeDTOList() != null) {
                                        objectAssestsUnitMeasurementList.clear();
                                        objectAssestsUnitMeasurementList.add("Select");
                                        objectAssestsUnitMeasurementList.addAll(assestsData.getItemUOMTypeDTOList());
                                        UtilView.setupItemAdapter(activity, spinAssestsUnitMeasurement, objectAssestsUnitMeasurementList);
                                    }

                                    if (assestsData.getItemMSICDTOList() != null) {
                                        objectAssestsMsicList.clear();
                                        objectAssestsMsicList.add("Select");
                                        objectAssestsMsicList.addAll(assestsData.getItemMSICDTOList());
                                        UtilView.setupItemAdapter(activity, spinAssestsMsicCode, objectAssestsMsicList);
                                    }
                                    if (assestsData.getItemBrandDTOList() != null) {
                                        objectAssestsBrandNameList.clear();
                                        objectAssestsBrandNameList.add("Select");
                                        objectAssestsBrandNameList.addAll(assestsData.getItemBrandDTOList());
                                        UtilView.setupItemAdapter(activity, spinnerAssestsBrandname, objectAssestsBrandNameList);
                                    }
                                    if (assestsData.getItemCountTypeDTOList() != null) {
                                        objectAssestsCountypeList.clear();
                                        objectAssestsCountypeList.add("Select");
                                        objectAssestsCountypeList.addAll(assestsData.getItemCountTypeDTOList());
                                        UtilView.setupItemAdapter(activity, spinAssestsCountType, objectAssestsCountypeList);
                                    }

                                    if (assestsData.getItemIPTaxDTOList() != null) {
                                        objectAssestsInputTaxList.clear();
                                        objectAssestsInputTaxList.add("Select");
                                        objectAssestsInputTaxList.addAll(assestsData.getItemIPTaxDTOList());
                                        UtilView.setupItemAdapter(activity, spinAssestsInputtax, objectAssestsInputTaxList);
                                    }
                                    if (assestsData.getItemOPTaxDTOList() != null) {
                                        objectAssestsOutputTaxList.clear();
                                        objectAssestsOutputTaxList.add("Select");
                                        objectAssestsOutputTaxList.addAll(assestsData.getItemOPTaxDTOList());
                                        UtilView.setupItemAdapter(activity, spinAssestsOutputtax, objectAssestsOutputTaxList);
                                    }

                                }

                                objectAssestsStatusList.clear();
                                objectAssestsStatusList.add("Active");
                                objectAssestsStatusList.add("InActive");
                                UtilView.setupItemAdapter(activity, spinAssestsStatus, objectAssestsStatusList);



                            } catch (Exception e) {
                                UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                            }
                        } else {
                            UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
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

    @OnClick({R.id.btnClose, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnClose:
                if (callingfrom != null) {
                        finish();
                }

                break;
            case R.id.btnSave:

                final String assestsCode = edAssestsCode.getText().toString().trim();
                final String assestsName = edAssestsName.getText().toString().trim();
                String assestsDescription = edAssestsDescritpion.getText().toString().trim();
                String assetsPurchasePrice = edAssestsPurchasePrice.getText().toString().trim();
                String assestsSalesPrice = edAssestsSalesPrice.getText().toString().trim();
                String assestsPartno = edAssestsPartno.getText().toString().trim();

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



                if (assestsSalesPrice.equals("") || assestsSalesPrice == null) {
                    isAllValid = true;
                    edAssestsSalesPrice.setError(null);
                } else {
                    try {
                        double assestsSales = Double.parseDouble(assestsSalesPrice);
                        isAllValid = true;
                    } catch (NumberFormatException e) {
                        edAssestsSalesPrice.setError(getResources().getString(R.string.error_numberformate));
                        isAllValid = false;
                    }
                }


                if (assetsPurchasePrice.equals("") || assetsPurchasePrice == null) {
                    isAllValid = true;
                    edAssestsPurchasePrice.setError(null);
                } else {
                    try {
                        double itemPurchase = Double.parseDouble(assetsPurchasePrice);
                        isAllValid = true;
                    } catch (NumberFormatException e) {
                        edAssestsPurchasePrice.setError(getResources().getString(R.string.error_numberformate));
                        isAllValid = false;
                    }
                }


                if (!assestsCode.equals("") && !assestsCode.equals("")
                        && assestsCategoryDTOList != null
                        && assestsTypeDTOList != null
                        && assestsUOMTypeDTOList != null
                        && assestsMSICDTOList != null
                        && assestsBrandDTOList != null
                        && assestsCountTypeDTOList != null
                        && assestsIPTaxDTOList != null
                        && assestsOPTaxDTOList != null
                        && isAllValid) {


                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {

                        UtilView.showProgessBar(activity, progressBar);
                        AddNewAssests addNewAssests = new AddNewAssests();
                        addNewAssests.setAssestCode(assestsCode);
                        addNewAssests.setAssestName(assestsName);
                        addNewAssests.setCertificateno(assestsPartno);
                        addNewAssests.setAssestDesc(assestsDescription);
                        addNewAssests.setSalesPrice(assestsSalesPrice);
                        addNewAssests.setPurchasePrice(assetsPurchasePrice);
                        addNewAssests.setAssestsCategoryDTO(assestsCategoryDTOList);
                        addNewAssests.setItemTypeDTO(assestsTypeDTOList);
                        addNewAssests.setItemUOMTypeDTO(assestsUOMTypeDTOList);
                        addNewAssests.setItemMSICDTO(assestsMSICDTOList);
                        addNewAssests.setItemBrandDTO(assestsBrandDTOList);
                        addNewAssests.setItemCountTypeDTO(assestsCountTypeDTOList);
                        addNewAssests.setItemIPTaxDTO(assestsIPTaxDTOList);
                        addNewAssests.setItemOPTaxDTO(assestsOPTaxDTOList);
                        addNewAssests.setStatus(itemStatus);
                        addNewAssests.setInclusiveJSON(checboxVal);


                        final Gson gson = new Gson();
                        String itemJson = gson.toJson(addNewAssests);
                        String url = "";

                        if (callingfrom != null && callingfrom.equals(Constant.NAVIGATION_INVENTORY_ASSESTS)) {
                            url = serverUrl + "/hipos//1/" + Constant.FUNTION_SAVENEWASSESTS;
                        }
                        if (serverUrl != null) {
                            PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.hideProgessBar(activity, progressBar);
                                    if (result != null) {
                                        try {
                                            List<AssestsData>itemsLists=new ArrayList<>();

                                            AssestsData item=gson.fromJson(result.toString(),AssestsData.class);
                                            itemsLists.add(item);

                                            if (itemsLists.size()>0){

                                                UtilView.showToast(activity, assestsName + " create successfully.");
                                                //resetView();
                                                Intent returnIntent = new Intent();
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
                                            }

                                        }catch (Exception e){

                                            UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                                        }


                                    } else {
                                        UtilView.showToast(activity, assestsName + " doesn't create successfully.");
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
                    if (assestsCode.equals("")) {
                        edAssestsCode.setError(getString(R.string.err_msg));
                    }
                    if (assestsName.equals("")) {
                        edAssestsName.setError(getString(R.string.err_msg));
                    }
                    if (assestsCategoryDTOList == null) {
                        TextView tv = (TextView) spinAssestsCategory.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (assestsTypeDTOList == null) {
                        TextView tv = (TextView) spinAssestsType.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (assestsUOMTypeDTOList == null) {
                        TextView tv = (TextView) spinAssestsUnitMeasurement.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (assestsMSICDTOList == null) {
                        TextView tv = (TextView) spinAssestsMsicCode.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (assestsBrandDTOList == null) {
                        TextView tv = (TextView) spinnerAssestsBrandname.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (assestsCountTypeDTOList == null) {
                        TextView tv = (TextView) spinAssestsCountType.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (assestsIPTaxDTOList == null) {
                        TextView tv = (TextView) spinAssestsInputtax.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                    if (assestsOPTaxDTOList == null) {
                        TextView tv = (TextView) spinAssestsOutputtax.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }
                }
                break;
        }
    }
    private boolean priceCheckMehtod() {
        return idCheckPrice.isChecked();
    }
    private boolean salesCheckMehtod() {
        return idCheckSales.isChecked();
    }
    private void resetView() {

        edAssestsCode.setText("");
        edAssestsName.setText("");
        edAssestsDescritpion.setText("");
        edAssestsSalesPrice.setText("");
        edAssestsPurchasePrice.setText("");

        spinAssestsCategory.setSelection(0);
        spinAssestsType.setSelection(0);
        spinAssestsCountType.setSelection(0);
        spinAssestsOutputtax.setSelection(0);
        spinAssestsInputtax.setSelection(0);
        spinAssestsMsicCode.setSelection(0);
        spinAssestsUnitMeasurement.setSelection(0);
        spinnerAssestsBrandname.setSelection(0);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
