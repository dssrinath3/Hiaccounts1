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
import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.assests.AssestsData;
import in.hiaccounts.hinext.inventory.model.assests.AssestsSelectData;
import in.hiaccounts.hinext.inventory.model.assests.ItemBrandDTOList;
import in.hiaccounts.hinext.inventory.model.assests.ItemCountTypeDTOList;
import in.hiaccounts.hinext.inventory.model.assests.ItemIPTaxDTOList;
import in.hiaccounts.hinext.inventory.model.assests.ItemOPTaxDTOList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;


public class Activity_EditAssests extends AppCompatActivity {


    public static String TAG = Activity_EditAssests.class.getSimpleName();
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
    @BindView(R.id.edAssestsCategory)
    EditText edAssestsCategory;
    @BindView(R.id.edAssestsType)
    EditText edAssestsType;
    @BindView(R.id.edAssestsUOM)
    EditText edAssestsUOM;
    @BindView(R.id.edAssestsMsicCode)
    EditText edAssestsMsicCode;
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
    private String callingfrom = "",checboxVal = null;
    private boolean isAllValid = true;
    private ServiceHandler serviceHandler;
    private String itemStatus, serverUrl;
    private AssestsData assestData;
    private AssestsSelectData addSelectData;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private List<Object> objectBrandNameList = new ArrayList<Object>();
    private List<Object> objectItemInputTaxList = new ArrayList<Object>();
    private List<Object> objectItemOutputTaxList = new ArrayList<Object>();
    private List<Object> objectItemStatusList = new ArrayList<Object>();
    private List<Object> objectItemCountypeList = new ArrayList<Object>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assests);
        ButterKnife.bind(this);
        initContentView();
    }

    private void initContentView() {
        ButterKnife.bind(this);
        activity = Activity_EditAssests.this;
        serverUrl = UtilView.getUrl(activity);
        Intent intent = getIntent();
        callingfrom = intent.getStringExtra("callingfrom");
        addSelectData = (AssestsSelectData) intent.getSerializableExtra("assests");
        serviceHandler = new ServiceHandler(activity);

        toolbar.setTitle(getResources().getString(R.string.editAssests));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Toast.makeText(activity, ""+addSelectData.getAssestId(), Toast.LENGTH_SHORT).show();
        if (addSelectData != null) {
            getAssestsDetail();
        }
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
        spinAssestsCountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemCountypeList.get(i);
                if (obj instanceof ItemCountTypeDTOList) {
                    spinAssestsCountType.setSelection(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerAssestsBrandname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectBrandNameList.get(i);
                if (obj instanceof ItemBrandDTOList) {
                    spinnerAssestsBrandname.setSelection(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinAssestsInputtax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemInputTaxList.get(i);
                if (obj instanceof ItemIPTaxDTOList) {
                    spinAssestsInputtax.setSelection(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinAssestsOutputtax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemOutputTaxList.get(i);
                if (obj instanceof ItemOPTaxDTOList) {
                    spinAssestsOutputtax.setSelection(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinAssestsStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectItemStatusList.get(i);
                if (obj instanceof String) {
                    spinAssestsStatus.setSelection(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void getAssestsDetail() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("itemSearchText", addSelectData.getAssestId());
        } catch (Exception e) {

        }
        progressBar.setVisibility(View.VISIBLE);
        String url = "";
        url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETNEWASSESTSDETAILS_EDIT + addSelectData.getAssestId();


        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            if (result.equals("No Response Body")) {
                                UtilView.showToast(activity, "Some Error. Please try Again");
                            } else {
                                Gson gson = new Gson();
                                try {
                                    AssestsSelectData aData = gson.fromJson(result.toString(), AssestsSelectData.class);
                                    if (aData != null) {
                                        setupAssestDetail(aData);
                                    } else {
                                        UtilView.showToast(activity, "Some Error. Please try Again");
                                    }
                                } catch (Exception e) {
                                    UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                        }
                    }
                }, false);
                postDataTask.execute(jsonObject.toString(), url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.nocontact_available), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void setupAssestDetail(AssestsSelectData aDatass) {

        if (ll != null) {
            ll.setVisibility(View.VISIBLE);
        }
        if (aDatass.getAssestCode() != null)
        edAssestsCode.setText(aDatass.getAssestCode());



        if (aDatass.getAssestName() != null)
            edAssestsName.setText(aDatass.getAssestName());

        if (aDatass.getCertificateno() != null)
            edAssestsPartno.setText(aDatass.getCertificateno());

        if (aDatass.getAssestDesc() != null)
            edAssestsDescritpion.setText(aDatass.getAssestDesc());

        if(aDatass.getSalesPrice() !=null){
            edAssestsSalesPrice.setText("" + aDatass.getSalesPrice());
        }
        if(aDatass.getPurchasePrice() !=null){
            edAssestsPurchasePrice.setText("" + aDatass.getPurchasePrice());
        }

        if(aDatass.getInclusiveJSON() !=null) {
            JSONObject json = null;
            try {
                json = new JSONObject(aDatass.getInclusiveJSON());
                String purc = json.getString("purchases");
                String sale = json.getString("sales");


                if (purc.toString() == "true" ){
                    idCheckPrice.setChecked(true);
                }
                else{
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

        objectItemStatusList.clear();
        objectItemStatusList.add("Active");
        objectItemStatusList.add("InActive");
        UtilView.setupItemAdapter(activity, spinAssestsStatus, objectItemStatusList);
        if (aDatass.getStatus() != null) {
            if (aDatass.getStatus().equals("Active")) {
                spinAssestsStatus.setSelection(0);
            }
            if (aDatass.getStatus().equals("InActive")) {
                spinAssestsStatus.setSelection(1);
            }
        }

        if (aDatass.getItemCategoryDTOList() != null) {
            for (int i = 0; i < aDatass.getItemCategoryDTOList().size(); i++) {
                if (aDatass.getAssestCategoryId() == aDatass.getItemCategoryDTOList().get(i).getAssestsCategoryId()) {
                    if (aDatass.getItemCategoryDTOList().get(i).getAssestsCategoryName() != null)
                        edAssestsCategory.setText(aDatass.getItemCategoryDTOList().get(i).getAssestsCategoryName());
                }
            }
        }

        if (aDatass.getItemTypeDTOList() != null) {
            for (int i = 0; i < aDatass.getItemTypeDTOList().size(); i++) {
                if (aDatass.getItemTypeId() == aDatass.getItemTypeDTOList().get(i).getItemTypeId()) {
                    if (aDatass.getItemTypeDTOList().get(i).getItemTypeName() != null)
                        edAssestsType.setText(aDatass.getItemTypeDTOList().get(i).getItemTypeName());
                }
            }
        }


        if (aDatass.getItemUOMTypeDTOList() != null) {
            for (int i = 0; i < aDatass.getItemUOMTypeDTOList().size(); i++) {
                if (aDatass.getUnitOfMeasurementId() == aDatass.getItemUOMTypeDTOList().get(i).getUnitOfMeasurementId()) {
                    if (aDatass.getItemUOMTypeDTOList().get(i).getUnitOfMeasurementName() != null)
                        edAssestsUOM.setText(aDatass.getItemUOMTypeDTOList().get(i).getUnitOfMeasurementName());
                }
            }
        }
        if (aDatass.getItemMSICDTOList() != null) {
            for (int i = 0; i < aDatass.getItemMSICDTOList().size(); i++) {
                if (aDatass.getMscid() == aDatass.getItemMSICDTOList().get(i).getMscid()) {
                    if (aDatass.getItemMSICDTOList().get(i).getCode() != null)
                        edAssestsMsicCode.setText(aDatass.getItemMSICDTOList().get(i).getCode());
                }
            }
        }


        if (aDatass.getItemBrandDTOList() != null) {
            objectBrandNameList.clear();
            objectBrandNameList.addAll(aDatass.getItemBrandDTOList());
            UtilView.setupItemAdapter(activity, spinnerAssestsBrandname, objectBrandNameList);

            for (int i = 0; i < aDatass.getItemBrandDTOList().size(); i++) {
                if (aDatass.getBrandId() == aDatass.getItemBrandDTOList().get(i).getBrandName()) {
                    spinnerAssestsBrandname.setSelection(i);
                }

            }
        }

        if (aDatass.getItemCountTypeDTOList() != null) {
            objectItemCountypeList.clear();
            objectItemCountypeList.addAll(aDatass.getItemCountTypeDTOList());
            UtilView.setupItemAdapter(activity, spinAssestsCountType, objectItemCountypeList);
            for (int i = 0; i < aDatass.getItemCountTypeDTOList().size(); i++) {
                if (aDatass.getInventoryMovementId() == aDatass.getItemCountTypeDTOList().get(i).getInventoryMovementId()) {
                  spinAssestsCountType.setSelection(i);
                }
            }

        }


        if (aDatass.getItemIPTaxDTOList() != null) {
            objectItemInputTaxList.clear();
            objectItemInputTaxList.addAll(aDatass.getItemIPTaxDTOList());
            UtilView.setupItemAdapter(activity, spinAssestsInputtax, objectItemInputTaxList);

            for (int i = 0; i < aDatass.getItemIPTaxDTOList().size(); i++) {
                if (aDatass.getInputTaxId() == aDatass.getItemIPTaxDTOList().get(i).getTaxIPId()) {
                    spinAssestsInputtax.setSelection(i);
                }

            }

        }
        if (aDatass.getItemOPTaxDTOList() != null) {
            objectItemOutputTaxList.clear();
            objectItemOutputTaxList.addAll(aDatass.getItemOPTaxDTOList());
            UtilView.setupItemAdapter(activity, spinAssestsOutputtax, objectItemOutputTaxList);
            for (int i = 0; i < aDatass.getItemOPTaxDTOList().size(); i++) {
                if (aDatass.getOutputTaxId() == aDatass.getItemOPTaxDTOList().get(i).getTaxOPId()) {
                    spinAssestsOutputtax.setSelection(i);
                }

            }
        }





    }
    @OnClick({R.id.btnClose, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnClose:
                finish();
                break;
            case R.id.btnSave:
                updateAssests();
                break;
        }
    }

    private void updateAssests() {
        String assestsCode = edAssestsCode.getText().toString().trim();
        final String assestsName = edAssestsName.getText().toString().trim();
        String assestsDescription = edAssestsDescritpion.getText().toString().trim();
        String assestsPurchasePrice = edAssestsPurchasePrice.getText().toString().trim();
        String assestsSalesPrice = edAssestsSalesPrice.getText().toString().trim();
        String Certificateno = edAssestsPartno.getText().toString().trim();

        itemStatus = (String) spinAssestsStatus.getSelectedItem();
        ItemBrandDTOList itemBrandDTOList = (ItemBrandDTOList) spinnerAssestsBrandname.getSelectedItem();
        ItemOPTaxDTOList itemOPTaxDTOList = (ItemOPTaxDTOList) spinAssestsOutputtax.getSelectedItem();
        ItemIPTaxDTOList itemIPTaxDTOList = (ItemIPTaxDTOList) spinAssestsInputtax.getSelectedItem();
        ItemCountTypeDTOList itemCountTypeList = (ItemCountTypeDTOList) spinAssestsCountType.getSelectedItem();
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
        if (assestsSalesPrice.equals("") || assestsSalesPrice == null) {
            isAllValid = true;
            edAssestsSalesPrice.setError(null);
        } else {
            try {
                itemSales = Double.parseDouble(assestsSalesPrice);
                isAllValid = true;
            } catch (NumberFormatException e) {
                edAssestsSalesPrice.setError(getResources().getString(R.string.error_numberformate));
                isAllValid = false;
            }
        }

        if (assestsPurchasePrice.equals("") || assestsPurchasePrice == null) {
            isAllValid = true;
            edAssestsPurchasePrice.setError(null);
        } else {
            try {
                itemPurchase = Double.parseDouble(assestsPurchasePrice);
                isAllValid = true;
            } catch (NumberFormatException e) {
                edAssestsPurchasePrice.setError(getResources().getString(R.string.error_numberformate));
                isAllValid = false;
            }
        }



        if (isAllValid) {

            JSONObject jsonObject = new JSONObject();


                try {
                    jsonObject.put("assestId", addSelectData.getAssestId());
                    jsonObject.put("assestCode", assestsCode);
                    jsonObject.put("assestName", assestsName);
                    jsonObject.put("assestDesc", assestsDescription);
                    jsonObject.put("Status", itemStatus);
                    jsonObject.put("salesPrice", itemSales);
                    jsonObject.put("purchasePrice", itemPurchase);
                    jsonObject.put("itemBrandID", itemBrandID);
                    jsonObject.put("itemIPTaxID", itemIPTaxID);
                    jsonObject.put("itemOPTaxID", itemOPTaxID);
                    jsonObject.put("itemCountTypeDTO", itemCountTypeID);
                    jsonObject.put("certificateno", Certificateno);
                    jsonObject.put("inclusiveJSON", checboxVal);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            String url = serverUrl + "/hipos//0/saveUpdateAssest";
            if (serverUrl!=null) {
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    UtilView.showProgessBar(activity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);
                            if (result != null) {
                                UtilView.showProgessBar(activity, progressBar);
                                Gson gson = new Gson();
                                try {

                                    AssestsSelectData item = gson.fromJson(result.toString(), AssestsSelectData.class);
                                    if (item != null) {
                                        UtilView.showToast(activity, assestsName + " update successfully.");
                                        Intent returnIntent = new Intent();
                                        activity.setResult(Activity.RESULT_OK, returnIntent);
                                        activity.finish();

                                    } else {
                                        UtilView.showToast(activity, assestsName + " doesn't update successfully.");
                                    }

                                }catch (Exception e){
                                    UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error),activity);
                                }
                            } else {
                                UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error),activity);
                            }

                        }
                    }, false);
                    postDataTask.execute(jsonObject.toString(), url, "");

                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            }else {
                UtilView.gotToLogin(activity);
            }
        }
    }


    private boolean priceCheckMehtod() {
        return idCheckPrice.isChecked();
    }
    private boolean salesCheckMehtod() {
        return idCheckSales.isChecked();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
