package in.hiaccounts.hinext.restaurant.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nikoyuwono.realmbrowser.RealmBrowser;
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
import in.hiaccounts.hinext.application.realm_manager.RealmManager;
import in.hiaccounts.hinext.restaurant.adapter.RecyclerRestuarantFloorAdapter;
import in.hiaccounts.hinext.restaurant.adapter.RecyclerRestuarantTableAdapter;
import in.hiaccounts.hinext.restaurant.model.category_item.RestuarentTableData;
import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.GetTempDataDeatails;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.HinextRestuarantPageData;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.RestuarentFloorData;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.SubRow;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.TableOrdersDetailsData;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.TaxList;
import in.hiaccounts.model.realm_model.data.Realm_SelectItemList;
import in.hiaccounts.model.realm_model.utils.DataGenerator;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class Activity_RestuarentTable extends AppCompatActivity {
    public static final String TAG = Activity_RestuarentTable.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnSelectCategory)
    TextView btnSelectCategory;
    @BindView(R.id.id_recyclerview_floor)
    RecyclerView idRecyclerviewFloor;
    @BindView(R.id.llFloor)
    LinearLayout llFloor;
    @BindView(R.id.id_recyclerview_table)
    RecyclerView idRecyclerviewTable;
    @BindView(R.id.llTable)
    LinearLayout llTable;
    @BindView(R.id.lllist)
    LinearLayout lllist;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.llBottomLayout)
    LinearLayout llBottomLayout;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.btnChangeTable)
    Button btnChangeTable;
    private Dialog dialog;
    private Spinner spinFromTable;
    private Spinner spinToTable;
    private List<RestuarentFloorData> listData;
    private List<RestuarentTableData> tableChangeFromDataList;
    private List<RestuarentTableData> tableChangeToDataList;
    private RestuarentFloorData restuarentFloorData = new RestuarentFloorData();
    private RestuarentTableData restuarentTableData;
    private RestuarentTableData restuarentTableFromDataList = null;
    private RestuarentTableData restuarentTableToDataList = null;
    private List<SubRow> subRowList;
    public SubRow tableData = new SubRow();
    private SubRow subRowData = new SubRow();
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private String serverUrl, floorName, tableName;
    private RecyclerRestuarantTableAdapter tableAdapter;
    private int rowsVal, columnVal;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPreference sharedPreference;
    private boolean isSelected = false;
    private String TYableName = "", tableVal = "",tableId = "",floorId="";
    private String flag;
    ArrayList<String> listdata;
    List<Realm_SelectItemList> tableColorData;
    public static Boolean orderPalced = false;
    private Boolean orderPlaced;
    private List<Object> objectFromTableList = new ArrayList<Object>();
    private List<Object> objectToTableList = new ArrayList<Object>();
    private HinextRestuarantPageData pageData;
    List<SelectedItemsList> selectedItemList;
    private  boolean isClear= false;
    private RealmBrowser realmBrowser;
    private GetDataTask getDataTask;


    @Override
    protected void onResume() {
        super.onResume();

        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        UtilView.showLogCat("@Flow", "onResume");
        realmBrowser = new RealmBrowser();
        realmBrowser.start();
        realmBrowser.showServerAddress(activity);
    }

    @Override
    protected void onPause() {
        super.onPause();
        UtilView.showLogCat(TAG, "onPause");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UtilView.showLogCat(TAG, "onReStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        UtilView.showLogCat(TAG, "onStop");
        if (realmBrowser != null) {
            realmBrowser.stop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarent_table);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        activity = Activity_RestuarentTable.this;
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        sharedPreference = SharedPreference.getInstance(activity);
        toolbar.setTitle(getResources().getString(R.string.menu_restaurenttable));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        dialog = new Dialog(activity);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        orderPlaced = getIntent().getBooleanExtra("selectTableOrder", false);
        //Toast.makeText(activity, "orderPlaced "+orderPlaced, Toast.LENGTH_SHORT).show();

        getRestuarentTableList();


    }

    private void getToTableList() {
        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_RESTUARENTCHANGETABLE + "?type=remaining";
        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            try {

                                tableChangeToDataList = new ArrayList<RestuarentTableData>();
                                tableChangeToDataList.clear();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    restuarentTableData = gson.fromJson(asJson.toString(), RestuarentTableData.class);
                                    tableChangeToDataList.add(restuarentTableData);

                                }

                                getChangeTable();
                            } catch (Exception e) {
                                if (!activity.isFinishing()) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);

                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void getFromTableList() {
        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_RESTUARENTCHANGETABLE + "?type=occupied";

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            try {
                                tableChangeFromDataList = new ArrayList<RestuarentTableData>();
                                tableChangeFromDataList.clear();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    restuarentTableData = gson.fromJson(asJson.toString(), RestuarentTableData.class);
                                    tableChangeFromDataList.add(restuarentTableData);

                                }

                                getToTableList();
                            } catch (Exception e) {
                                if (!activity.isFinishing()) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);

                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void getRestuarentTableList() {

        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_RESTUARENTFLOOR + "?tableConfigSearchText=";

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            try {
                                listData = new ArrayList<RestuarentFloorData>();
                                JSONArray resultJsonArray = new JSONArray(result.toString());

                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    restuarentFloorData = gson.fromJson(asJson.toString(), RestuarentFloorData.class);
                                    listData.add(restuarentFloorData);

                                }
                                if (listData != null && listData.size() > 0) {
                                    if (llFloor != null) {
                                        llFloor.setVisibility(View.VISIBLE);
                                        idRecyclerviewFloor.setHasFixedSize(true);
                                        GridLayoutManager layoutManager = new GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false);

                                        idRecyclerviewFloor.setLayoutManager(layoutManager);

                                        idRecyclerviewFloor.setAdapter(new RecyclerRestuarantFloorAdapter(activity, listData, new RecyclerRestuarantFloorAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(RestuarentFloorData floorData) {

                                                floorName = floorData.getConfigurationname();

                                                getRestuarentTableListData(floorName);


                                            }
                                        }));
                                    }
                                }

                            } catch (Exception e) {
                                if (!activity.isFinishing()) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);

                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }

    }

    private void getRestuarentTableListData(String floorName) {


        String url = serverUrl + "/restaurant/" + Constant.FUNTION_RESTUARENTTABLE + "?configName=" + floorName.replace(" ", "%20");


        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {

                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            // Toast.makeText(activity, "result "+result.toString(), Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject asJson;
                                JSONObject tabelname = null;
                                JSONArray resultJsonArray1 = new JSONArray(result.toString());

                                subRowList = new ArrayList<SubRow>();
                                subRowList.clear();
                                for (int i = 0; i < resultJsonArray1.length(); i++) {
                                    asJson = resultJsonArray1.getJSONObject(i);
                                    JSONArray subRows = asJson.getJSONArray("subRow");

                                    for (int j = 0; j < subRows.length(); j++) {
                                        tabelname = subRows.getJSONObject(j);
                                        SubRow subRowdata = new SubRow();
                                        subRowdata.setTableName(tabelname.getString("tableName"));
                                        subRowdata.setTableValue(tabelname.getString("tableValue"));
                                        subRowdata.setOccupied(tabelname.getString("occupied"));
                                        subRowdata.setFloorId(tabelname.getString("floorId"));
                                        subRowdata.setTableId(tabelname.getString("tableId"));

                                        if (subRowdata!=null && subRowdata.getOccupied().equals("red")){
                                            subRowdata.setSelected(true);
                                        }else if (subRowdata!=null && subRowdata.getOccupied().equals("green")){
                                            subRowdata.setSelected(false);
                                        }else if (subRowdata!=null && subRowdata.getOccupied().equals("yellow")){
                                            subRowdata.setSelected(true);
                                        }

                                        subRowList.add(subRowdata);


                                    }


                                }

                                if (subRowList != null && subRowList.size() > 0) {
                                    for (SubRow subRow :subRowList){
                                        if (subRow!=null){
                                                if (subRow.getSelected()){
                                                    RealmManager.createRestaurentDao().deletServerRealmDataAll(subRow);
                                                    RealmManager.createRestaurentDao().deletServerRealmTempDataAll(subRow);
                                                    isClear = true;
                                                    Log.e("Occuppied1", String.valueOf(subRow.getSelected()));
                                                   if (isClear){
                                                       isClear = false;
                                                       callGetTempData(subRow);
                                                   }

                                                }else{
                                                    Log.e("Occuppied2", String.valueOf(subRow.getSelected()));
                                                    RealmManager.createRestaurentDao().deletServerRealmDataAll(subRow);
                                                    RealmManager.createRestaurentDao().deletServerRealmTempDataAll(subRow);
                                                    isClear = true;
                                                }
                                        }

                                    }
                                }


                                //  Toast.makeText(activity, "columnlist "+subRowList.size(), Toast.LENGTH_SHORT).show();
                                if (subRowList != null && subRowList.size() > 0) {

                                    if (llTable != null) {
                                        llTable.setVisibility(View.VISIBLE);
                                        getRecylerTablePage(subRowList);
                                    }
                                }


                            } catch (Exception e) {
                                Log.e("Table Exception",e.toString());
                               // UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                getDataTask.execute(url, Constant.TABLEBOOKEDDATA);
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }


    private void getRecylerTablePage(List<SubRow> subRowList) {


        idRecyclerviewTable.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false);
        // mLayoutManager = new GridLayoutManager(activity, columnVal);
        idRecyclerviewTable.setLayoutManager(layoutManager);
        //idRecyclerviewTable.setAdapter(activity,subRowList);
        idRecyclerviewTable.setAdapter(new RecyclerRestuarantTableAdapter(activity, subRowList, new RecyclerRestuarantTableAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SubRow item) {
                Log.e("tablename", item.getTableName());
                Log.e("tablenameid", item.getTableValue());
                Log.e("tablenamestate", String.valueOf(item.getSelected()));
                //getTableResponseData(item);
                if (isClear){
                    if (item.getTableName() != null && !item.getTableName().equals("NA")) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("tableData", item);
                        returnIntent.putExtra("selectTableOrder", item.getSelected());
                        setResult(Activity.RESULT_OK, returnIntent);
                        activity.finish();

                        tableName = item.getTableName();
                    } else {
                        Toast.makeText(Activity_RestuarentTable.this, "This Table Not Assigned.Please Select Other Table.!", Toast.LENGTH_SHORT).show();
                    }
                }

            }


        }));


    }

    private void callGetTempData(SubRow subRowdata) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {

                  if (subRowdata!=null){
                      tableName= subRowdata.getTableName();
                      tableId= subRowdata.getTableId();
                      floorId = subRowdata.getFloorId();
                      isClear = true;
                  }


                if (isClear){
                    isClear = false;
                    String url = serverUrl + "/restaurant/" + Constant.FUNTION_RESTARUANTTEMP + "?currTableName=" + tableName.replace(" ", "%20") + "&currTableId=" + tableId;


                    getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (result != null) {
                                JSONArray jsonArray=null;
                                JSONObject jsonObject =null;
                                Gson gson = new Gson();
                                boolean flagStatus=false;
                                try {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(activity, Activity_Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        try {

                                            GetTempDataDeatails details = new Gson().fromJson(result.toString(), GetTempDataDeatails.class);
                                            flagStatus = true;
                                            if (details.getTableName() != null && details.getTableId() !=null){

                                                //if (flagStatus){
                                                SelectedItemsList waiter = new SelectedItemsList();

                                                if (details.getUseraccountId() != null && !details.getUseraccountId().equals("")) {

                                                    Log.e("WaiterName",details.getUseraccountId());
                                                    waiter.setWaiterName(details.getUseraccountId());

                                                }
                                                if (details.getCustomerId() != null) {

                                                    Log.e("customerName", String.valueOf(details.getCustomerId()));
                                                    waiter.setCustomerId(details.getCustomerId());

                                                }

                                                if (details.getTableName() != null && !details.getTableName().equals("")) {
                                                    subRowdata.setTableName(details.getTableName());
                                                }
                                                else {
                                                    subRowdata.setTableName(tableName);
                                                }

                                                if (details.getTableId() != null && !details.getTableId().equals("")) {
                                                    subRowdata.setTableValue(details.getTableId());
                                                }else{
                                                    subRowdata.setTableValue(tableId);
                                                }


                                                if (subRowdata != null) {
                                                    if (flagStatus){
                                                        selectedItemList = new ArrayList<>();
                                                        String selectItemlist="";
                                                        jsonObject = new JSONObject(result.toString());
                                                        selectItemlist = jsonObject.getString("selectedItemsList");
                                                        jsonArray = new JSONArray(selectItemlist.toString());
                                                        Log.e("jsonArray",jsonArray.toString());
                                                        Log.e("TableName",subRowdata.getTableName());
                                                        Log.e("TableNameId",subRowdata.getTableValue());
                                                        Log.e("TableNameSelec", String.valueOf(subRowdata.getSelected()));
                                                        Log.e("TableNameFloor", String.valueOf(subRowdata.getFloorId()));

                                                        // selectedItemList.clear();
                                                        if (jsonArray != null && jsonArray.length()>0) {
                                                            for (int i=0;i<jsonArray.length();i++){
                                                                SelectedItemsList empname = new SelectedItemsList();
                                                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                                                String itemcode = jsonObject1.getString("itemCode");
                                                                empname.setItemCode(itemcode);
                                                                Long itemid = jsonObject1.getLong("itemId");
                                                                empname.setItemId(itemid);
                                                                String itemname = jsonObject1.getString("itemName");
                                                                empname.setItemName(itemname);
                                                                String itemcategoryname = jsonObject1.getString("itemCategoryName");
                                                                empname.setItemCategoryName(itemcategoryname);
                                                                Long itemCategoryId = jsonObject1.getLong("itemCategoryId");
                                                                empname.setItemCategoryId(itemCategoryId);
                                                                Long inputtaxid= jsonObject1.getLong("inputTaxId");
                                                                empname.setInputTaxId(inputtaxid);
                                                                Long outputTaxId = jsonObject1.getLong("outputTaxId");
                                                                empname.setOutputTaxId(outputTaxId);
                                                                Long itemTypeId = jsonObject1.getLong("itemTypeId");
                                                                empname.setItemTypeId(itemTypeId);
                                                                String itemTypeName = jsonObject1.getString("itemTypeName");
                                                                empname.setItemTypeName(itemTypeName);
                                                                Double unitPrice = jsonObject1.getDouble("unitPrice");
                                                                empname.setUnitPrice(Double.valueOf(unitPrice));
                                                                Double unitPriceIn = jsonObject1.getDouble("unitPriceIn");
                                                                empname.setUnitPriceIn(Double.valueOf(unitPriceIn));
                                                                Double gstItemTax = jsonObject1.getDouble("gstItemTax");
                                                                empname.setGstTaxAmt(Double.valueOf(gstItemTax));
                                                                double gstTaxPercantage = 0.00;
                                                                String getPageLoadData = sharedPreference.getData(Constant.HINEXTRESTUARANTDATA_KEY);
                                                                if (getPageLoadData != null) {
                                                                    pageData = gson.fromJson(getPageLoadData, HinextRestuarantPageData.class);
                                                                    if (pageData != null) {
                                                                        List<TaxList> taxList = pageData.getTaxList();
                                                                        if (taxList != null) {
                                                                            for (int j = 0; j < taxList.size(); j++) {
                                                                                if (empname.getOutputTaxId() == taxList.get(j).getTaxid()) {
                                                                                    gstTaxPercantage = taxList.get(j).getTaxPercantage();
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                                empname.setGstTaxPercantage(gstTaxPercantage);
                                                                Double taxamt = jsonObject1.getDouble("taxamt");
                                                                empname.setTaxamt(Double.valueOf(taxamt));
                                                                Double amtinclusivetax = jsonObject1.getDouble("amtinclusivetax");
                                                                empname.setAmtinclusivetax(amtinclusivetax);
                                                                Double amtexclusivetax= jsonObject1.getDouble("amtexclusivetax");
                                                                empname.setAmtexclusivetax(amtexclusivetax);
                                                                Long qty = jsonObject1.getLong("qty");
                                                                empname.setQty(qty);
                                                                empname.setGstTaxAmt((qty * unitPrice  * gstTaxPercantage)/100);
                                                                empname.setTaxamt((qty * unitPrice  * gstTaxPercantage)/100);
                                                                Long taxid = jsonObject1.getLong("taxid");
                                                                empname.setTaxId(taxid);
                                                                String inclusiveJSON= jsonObject1.getString("inclusiveJSON");
                                                                empname.setInclusiveJSON(inclusiveJSON);
                                                                empname.setItemTotalAmount(empname.getAmtinclusivetax());
                                                                empname.setWaiterName(waiter.getWaiterName());
                                                                empname.setCustomerId(waiter.getCustomerId());
                                                                if (jsonObject1.has("itemDescription")) {
                                                                    String itemDescription = jsonObject1 .getString("itemDescription");
                                                                    empname.setItemDescription(itemDescription);
                                                                }
                                                                else {
                                                                    empname.setItemDescription("");
                                                                }
                                                                Log.e("waiterNa",empname.getWaiterName());
                                                                selectedItemList.add(empname);
                                                            }
                                                        }

                                                        if (selectedItemList!=null && selectedItemList.size()>0){
                                                            for (int i = 0; i < selectedItemList.size(); i++) {
                                                                String itemCode = "";
                                                                itemCode = selectedItemList.get(i).getItemCode();
                                                                Log.e("ItemCodeData",itemCode+"TableName  "+subRowdata.getFloorId());
                                                                RealmManager.createRestaurentDao().saveRestaurentServerDataSync(DataGenerator.generateRestaServcerSelectItem(selectedItemList.get(i), subRowdata, itemCode), selectedItemList.get(i), subRowdata);
                                                                RealmManager.createRestaurentDao().saveRestaurentTempSyncdata(DataGenerator.generateRestaTempServcerSelectItem(selectedItemList.get(i), subRowdata), selectedItemList.get(i), subRowdata);
                                                                isClear=true;
                                                            }
                                                        }
                                                        // checkTableCartList();
                                                    }

                                                    //}

                                                }


                                                // }
                                            }else {
                                                //Log.e("tabledId1", tableData.getTableValue());
                                                // checkTableCartList();
                                            }




                                        } catch (Exception ex) {
                                            Log.e("JSONException", ex.toString());
                                            // UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                        }
                                    }
                                } catch (Exception e) {
                                    Log.e("Exception1", e.toString());
                                    //UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);

                            }
                        }

                    }, false);
                    Gson gson = new Gson();
                    getDataTask.execute(url, "");
                }



            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }


    @OnClick({R.id.btnClose,R.id.btnChangeTable})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btnClose:
                finish();
                break;

            case R.id.btnChangeTable:
                    getFromTableList();


                break;
            


        }
    }

    private void getChangeTable() {
        dialog.setContentView(R.layout.dialog_restaurent_table_change);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnSave = dialog.findViewById(R.id.btnSave);
        Spinner spinFromTable = dialog.findViewById(R.id.spinFromTable);
        Spinner spinToTable = dialog.findViewById(R.id.spinToTable);


        if (dialog != null) {
            dialog.show();
        }
        if (tableChangeFromDataList != null && tableChangeFromDataList.size() > 0) {
            objectFromTableList.clear();
            objectFromTableList.add("Select Table");
            objectFromTableList.addAll(tableChangeFromDataList);
            UtilView.setupTableAdapter(activity, spinFromTable, objectFromTableList);

        }
        spinFromTable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object obj = objectFromTableList.get(position);
                if (obj instanceof RestuarentTableData) {
                    spinFromTable.setSelection(position);
                    restuarentTableFromDataList = (RestuarentTableData) spinFromTable.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (tableChangeToDataList != null && tableChangeToDataList.size() > 0) {
            objectToTableList.clear();
            objectToTableList.add("Select Table");
            objectToTableList.addAll(tableChangeToDataList);
            UtilView.setupTableAdapter(activity, spinToTable, objectToTableList);

        }
        spinToTable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object obj = objectToTableList.get(position);
                if (obj instanceof RestuarentTableData) {
                    spinToTable.setSelection(position);
                    restuarentTableToDataList = (RestuarentTableData) spinToTable.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Log.e("restuarentToTable",restuarentTableToDataList.getTableName());
                    if (restuarentTableToDataList!=null && restuarentTableFromDataList!=null){

                        SubRow subRow = new SubRow();
                        subRow.setFloorId(restuarentTableFromDataList.getFloorId()+restuarentTableFromDataList.getTableid());
                        subRow.setTableValue(String.valueOf(restuarentTableFromDataList.getTableid()));
                        subRow.setTableName(restuarentTableFromDataList.getTableName());
                        subRow.setTableId(String.valueOf(restuarentTableFromDataList.getTableid()));
                        subRow.setSelected(true);
                        List<SelectedItemsList> selectedItemsLists = RealmManager.createRestaurentDao().getRestuarentItemList(subRow);
                        Log.e("resFromDataList", String.valueOf(selectedItemsLists.size()));
                        for (SelectedItemsList itemsList : selectedItemsLists){
                           // Log.e("getid", String.valueOf(itemsList.getId()));
                            RealmManager.createRestaurentDao().updateRestaurentKotChangeTableOrder(itemsList,restuarentTableToDataList);
                            RealmManager.createRestaurentDao().updateRestaurentKotTempChangeTableOrder(itemsList,restuarentTableToDataList);
                        }
                        Toast.makeText(Activity_RestuarentTable.this, "Table Changed Successfully.", Toast.LENGTH_SHORT).show();
                        SubRow subRowTo = new SubRow();
                        subRowTo.setFloorId(restuarentTableToDataList.getFloorId()+restuarentTableToDataList.getTableid());
                        subRowTo.setTableValue(String.valueOf(restuarentTableToDataList.getTableid()));
                        subRowTo.setTableId(String.valueOf(restuarentTableToDataList.getTableid()));
                        subRowTo.setTableName(restuarentTableToDataList.getTableName());
                        subRowTo.setSelected(true);

                        updateChangeTable(restuarentTableFromDataList,restuarentTableToDataList,subRowTo);


                   }
                }catch (Exception e){

                }


            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }

        if (getDataTask!=null){
            getDataTask.cancel(true);
        }
    }

    private void updateChangeTable(RestuarentTableData fromTable, RestuarentTableData toTable, SubRow subRowTo) {
        String url = serverUrl + "/restaurant/" + Constant.FUNTION_RESTUARENTCHANGETABLENAME + "?fromTable="+fromTable.getTableName()+"&fromTableId="+fromTable.getTableid()+"&toTable="+toTable.getTableName()+"&toTableId="+toTable.getTableid();

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            try {

                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("tableData", subRowTo);
                                returnIntent.putExtra("selectTableOrder", subRowTo.getSelected());
                                setResult(Activity.RESULT_OK, returnIntent);
                                activity.finish();

                            } catch (Exception e) {
                                if (!activity.isFinishing()) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);

                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }



}
