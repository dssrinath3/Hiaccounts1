package in.hiaccounts.hinext.restaurant.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mocoo.hang.rtprinter.driver.Contants;
import com.mocoo.hang.rtprinter.driver.HsBluetoothPrintDriver;
import com.nikoyuwono.realmbrowser.RealmBrowser;
import com.rey.material.widget.ProgressView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.util.stream.Collectors;
import java.util.stream.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.Activity_Splash;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.application.model.LoginDetail;
import in.hiaccounts.hinext.application.realm_manager.RealmManager;
import in.hiaccounts.hinext.checkout.model.BankPayment;
import in.hiaccounts.hinext.checkout.model.CardPaymentList;
import in.hiaccounts.hinext.checkout.model.CashPayment;
import in.hiaccounts.hinext.checkout.model.CreditPayment;
import in.hiaccounts.hinext.checkout.model.MultiBankPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiCashPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiVoucherPayment;
import in.hiaccounts.hinext.checkout.model.VoucherPayment;
import in.hiaccounts.hinext.controlpanel.activity.Activity_ControlPanelSubmenu;
import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeDatum;
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.customer.model.CustomerList;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.restaurant.adapter.RecyclerRestuarantCategoryAdapter;
import in.hiaccounts.hinext.restaurant.adapter.RecyclerRestuarantItemsAdapter;
import in.hiaccounts.hinext.restaurant.adapter.RestraposTableOrders_Adapter;
import in.hiaccounts.hinext.restaurant.adapter.Restrapos_Adapter1;
import in.hiaccounts.hinext.restaurant.adapter.SearchAdapter;
import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutData;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutItem;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraSaveCheckoutData;
import in.hiaccounts.hinext.restaurant.model.checkout.TableNamesData;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.DefaultTable;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.GetTempDataDeatails;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.HinextRestuarantPageData;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.InclusiveJson;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.ItemCategory;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.RestuarentFloorData;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.SubRow;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.TaxList;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.getTempDataDeatails;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCartItem;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCreator;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosHelper;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosTableOrderCreator;
import in.hiaccounts.hinext.restaurant.model.restra_pos.exception.RestraPosTableOrderCartItem;
import in.hiaccounts.hinext.sales.adapter.MessageAdapter;
import in.hiaccounts.hinext.sales.model.checkout.UomConvertorList;
import in.hiaccounts.hinext.sales.model.sales_notifications.CustomerNotificationsList;
import in.hiaccounts.hinext.sales.model.sales_notifications.CustomerNotificationsListData;
import in.hiaccounts.hinext.sales.model.sales_notifications.NotificationData;
import in.hiaccounts.hinext.sales.model.sales_notifications.NotificationItemList;
import in.hiaccounts.hinext.sales.model.sales_notifications.NotificationListData;
import in.hiaccounts.hinext.sales.pos_printer.DeviceListActivity;
import in.hiaccounts.hinext.sales.pos_printer.PrintReceipt;
import in.hiaccounts.model.realm_model.data.RealmTemp_SelectItemList;
import in.hiaccounts.model.realm_model.data.Realm_SelectItemList;
import in.hiaccounts.model.realm_model.data.Realm_TempOrders;
import in.hiaccounts.model.realm_model.utils.DataGenerator;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.AsyncTaskCompleteListener1;
import in.hiaccounts.task.GetCompanyLogoTask;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.qty;
import static in.hiaccounts.R.string.response_error;


public class RestuarantActivity extends AppCompatActivity {
    @BindView(R.id.card_view0)
    CardView cardView0;
    private List<SelectedItemsList> mItemNameSearch;

    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    public static HsBluetoothPrintDriver BLUETOOTH_PRINTER = null;
    public static List<SelectedItemsList> selectItemForDelete = new ArrayList<SelectedItemsList>();
    public static String TAG = RestuarantActivity.class.getSimpleName();
    private static BluetoothDevice device;
    private static TextView tvPrinterStatus;
    @BindView(R.id.badge_notification)
    TextView badgeNotification;
    private int mNotificationCounter;
    public SubRow tableData = new SubRow();
    Intent serverIntent = null;
    double discountAmt;
    double tenderedAmt;
    double returnAmount;
    boolean isCheckoutable = true;
    RestraCheckoutData getCheckout;
    RestraSaveCheckoutData saveResponseData = null;
    @BindView(R.id.tvTableName)
    TextView tvTableName;
    @BindView(R.id.tvTableData)
    TextView tvTableData;
    @BindView(R.id.tvWaiterName)
    TextView tvWaiterName;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.imgViewLogo)
    ImageView imgViewLogo;
    @BindView(R.id.tvCompanyName)
    TextView tvCompanyName;
    @BindView(R.id.rlTopBar)
    RelativeLayout rlTopBar;
    @BindView(R.id.btnSelectCategory)
    TextView btnSelectCategory;
    @BindView(R.id.id_recyclerview_category)
    RecyclerView idRecyclerviewCategory;
    @BindView(R.id.llCategory)
    LinearLayout llCategory;
    @BindView(R.id.id_recyclerview_Item)
    RecyclerView idRecyclerviewItem;
    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.listviewSelec)
    ListView listviewSelec;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.fabsaveorder)
    FloatingActionButton fabsaveorder;
    @BindView(R.id.edCartQty)
    EditText edCartQty;
    @BindView(R.id.edCartTotalamount)
    EditText edCartTotalamount;
    @BindView(R.id.fabPayment)
    FloatingActionButton fabPayment;
    @BindView(R.id.rlBottomLayout)
    RelativeLayout rlBottomLayout;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.chkBoxImage)
    CheckBox chkBoxImage;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.restaaurent)
    FrameLayout restaaurent;
    @BindView(R.id.idSettings)
    ImageView idSettings;
    @BindView(R.id.idSettings1)
    ImageView idSettings1;
    @BindView(R.id.imgviewSelectTable)
    ImageView imgviewSelectTable;
    @BindView(R.id.selectCustomer)
    TextView selectCustomer;
    @BindView(R.id.clearall)
    TextView clearall;
    @BindView(R.id.fabMenus)
    FloatingActionMenu fabMenus;
    /* @BindView(R.id.fabSelecttable)
     FloatingActionButton fabSelecttable;*/
    @BindView(R.id.fabPrintList)
    FloatingActionButton fabPrintList;
    @BindView(R.id.fabsplitbill)
    FloatingActionButton fabsplitbill;
    @BindView(R.id.background_dimmer)
    View backgroundDimmer;
    @BindView(R.id.fabDailyReport)
    FloatingActionButton fabDailyReport;
    @BindView(R.id.fabPrinterConfigurartion)
    FloatingActionButton fabPrinterConfigurartion;
    List<SelectedItemsList> posServerTableCart = new ArrayList<>();
    /*  @BindView(R.id.imgviewDelete)
      ImageView imgviewDelete;*/
    Map<String, List<RestraPosTableOrderCartItem>> map = new HashMap<String, List<RestraPosTableOrderCartItem>>();
    Boolean isInternetPresent = false;
    List<SelectedItemsList> itemList;
    List<SelectedItemsList> searchItemList;
    String customerName, printerType = "", searchitem,customerAddress="",customerNo="";
    List<SubRow> tablesdata = new ArrayList<>();
    RestraPosTableOrderCreator posTableOrderCreator;
    RestraPosCreator posCreator;
    RestraPosCreator splitPosCreator;
    Restrapos_Adapter1 cartItemAdapter;
    RestraposTableOrders_Adapter restraposTableOrdersAdapter;
    List<SelectedItemsList> selectedItemData;
    List<SelectedItemsList> posCartTableItems;
    List<SelectedItemsList> posCartTableCancelItems;
    Long customerId = 2L;
    RecyclerRestuarantItemsAdapter itemsAdapter;
    String checkoutType = "", tableVal = "";
    CashPayment cashPayment = new CashPayment();
    @BindView(R.id.edItemCode)
    EditText edItemCodeSearch;
    @BindView(R.id.llSearchedItemCode)
    LinearLayout edItemSearch;
    @BindView(R.id.idNotifications)
    ImageView idNotifications;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    private android.app.AlertDialog.Builder alertDlgBuilder;
    private BluetoothAdapter mBluetoothAdapter = null;
    private Checkout_ResponseData checkoutResponseData;
    private Boolean orderPalced;
    private String taxString = "", serverUrl, tableName = "", tableId = "";
    private Activity mActivity;
    private Dialog dialog;
    private ServiceHandler serviceHandler;
    private SharedPreference sharedPreference;
    private HinextRestuarantPageData pageData;
    private Boolean tableSelected = false;
    private Boolean tableKotOrderDone = false;
    private Customer selected_customer;
    private Long categoryId = null;
    private List<RestraPosTableOrderCartItem> posCartItemsd;
    private RealmBrowser realmBrowser;
    private EmployeeDatum employeeDetail;
    private CompanyData companyData;
    private List<SelectedItemsList> previousKotData;
    private List<RestuarentFloorData> listData;
    private List<CardPaymentList> cardPaymentList = new ArrayList<CardPaymentList>();
    private List<MultiVoucherPayment> voucherPaymentList = new ArrayList<MultiVoucherPayment>();
    private List<MultiBankPaymentList> bankPaymentList = new ArrayList<MultiBankPaymentList>();
    private RestuarentFloorData restuarentFloorData = new RestuarentFloorData();
    private Boolean bluetoothStatus;
    private List<RestraCheckoutItem> itemListKot;
    private String empName = "",empId="", itemCodeSearch = "";
    private List<CustomerNotificationsList> listMessages = new ArrayList<>();
    private MessageAdapter messageAdapter;
    private NotificationData notificationData;
    private ListView list_view_notifications;
    private Boolean splitBillStatus=false;
    private Realm_SelectItemList waiterName;
    boolean kotCheck=false;
    private SearchAdapter searchAdapter;
    private String notificationOrderNo="",notificationagentId="";
    private Boolean notificationAppend =false;
    private Boolean dualKotPrint =true;
    private Boolean reprintKot =false;
    private RestraCheckoutData saveResponseDataa;
    boolean flagKot = false;
    List<Integer> msgCount = new ArrayList<>();
    Map<String,String> tableChairData=new HashMap<String,String>();
    private GetDataTask getDataTask;
    private PostDataTask postDataTask;

    @Override
    protected void onStart() {
        super.onStart();
        UtilView.showLogCat(TAG, "onStart");
        Log.e(TAG, "++ ON START ++");
        try {

            String singlePrinter = sharedPreference.getData(Constant.SINGLE_PRINTER);
            String multiPrinter = sharedPreference.getData(Constant.MULTIPLE_PRINTER);

            if (singlePrinter!=null && singlePrinter.equals("singlePrinter")) {
                try {

                    String kotaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSBILL);
                    String printerTypekot1 = sharedPreference.getData(Constant.BLUETOOTHTYPEBILL);
                    if (printerTypekot1!=null && printerTypekot1.equals("2-inch")){
                        printerType = "2-inch";
                    }else {
                        printerType = "3-inch";
                    }
                    Log.e("singlekotaddress",kotaddress);
                    device = mBluetoothAdapter.getRemoteDevice(kotaddress);
                    if(BLUETOOTH_PRINTER!=null){
                        BLUETOOTH_PRINTER.start();
                        BLUETOOTH_PRINTER.connect(device);
                    }


                }catch (Exception e){
                    Log.e("kotprinter excption",e.toString());
                }

            }else if (multiPrinter!=null && multiPrinter.equals("multiplePrinter")){
                String kotaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSKOT1);
                String printerTypekot1 = sharedPreference.getData(Constant.BLUETOOTHTYPEKOT1);
                if (printerTypekot1!=null && printerTypekot1.equals("kot1-2-inch")){
                    printerType = "2-inch";
                }else {
                    printerType = "3-inch";
                }
                Log.e("multikotaddress",kotaddress);
                device = mBluetoothAdapter.getRemoteDevice(kotaddress);
                BLUETOOTH_PRINTER.start();
                BLUETOOTH_PRINTER.connect(device);

            }



        }catch (Exception e){
            Log.e("kotprinter excption",e.toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        UtilView.showLogCat(TAG, "onResume");
        realmBrowser = new RealmBrowser();
        realmBrowser.start();
        realmBrowser.showServerAddress(mActivity);
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

            RealmManager.createRestaurentDao().deletRealmSplitTableAllData();
            RealmManager.createRestaurentDao().deletRealmSplitCheckoutTableAllData();

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
    public void onDestroy() {
        super.onDestroy();
        UtilView.showLogCat(TAG, "onDestroy");

        if (dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }

        if(getDataTask != null) {
            getDataTask.cancel(true);
        }

        if (postDataTask!=null) {
            postDataTask.cancel(true);
        }
        if (posCreator != null) {
            posCreator.clear();
            checkCartList();
        }

  /*      if (BLUETOOTH_PRINTER != null) {
            if (BLUETOOTH_PRINTER.IsNoConnection())
                BLUETOOTH_PRINTER.stop();
        }*/

    }

    /* public void onConfigurationChanged(Configuration newConfig) {
         super.onConfigurationChanged(newConfig);

         UtilView.showLogCat(TAG,"onConfigurationChanged");
         int orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
         // or = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
         // or = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
         setRequestedOrientation(orientation);

     }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarant);
        UtilView.showLogCat(TAG, "onCreate");
       /* mActivity=this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            UtilView.showLogCat(TAG,"onCreate portrait ");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setContentView(R.layout.activity_restuarant);
            UtilView.showLogCat(TAG,"onCreate landsacpe");
        }*/

        ButterKnife.bind(this);
        initComponentView();

        fabMenus.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    backgroundDimmer.setVisibility(View.VISIBLE);
                } else {
                    fabMenus.setVisibility(View.GONE);
                    backgroundDimmer.setVisibility(View.GONE);
                }
            }
        });


    }


    private void initComponentView() {
        mActivity = RestuarantActivity.this;
        // tvPrinterStatus = (TextView) findViewById(R.id.tvPrinterStatus);

        posCreator = RestraPosHelper.getPosCreator();
        posTableOrderCreator = RestraPosHelper.getPosTableOrderCreator();
        splitPosCreator = RestraPosHelper.getSplitPosCreator();
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        dialog = new Dialog(mActivity);
   /*     badgeNotification.setVisibility(View.VISIBLE);
        badgeNotification.setText(String.valueOf(mNotificationCounter));*/

       // cardView0.setVisibility(View.GONE);
        getPageLoadData();
        getRestuarentDefaultTableList();
        // getRestuarentTableList();
        callItemCodeSearchMethod("");
        callGetNotifications();


        if (rlBottomLayout != null)
            rlBottomLayout.setVisibility(View.VISIBLE);

        tvTableData.setVisibility(View.GONE);
        tvTableName.setVisibility(View.GONE);
        tvWaiterName.setVisibility(View.GONE);
        // Toast.makeText(mActivity, "tableName...  "+tableName, Toast.LENGTH_SHORT).show();

        chkBoxImage.setVisibility(View.GONE);
        tv.setVisibility(View.GONE);
        RealmManager.open();

        UtilView.showLogCat("@Flow", "relam " + RealmManager.open().getPath());

        edItemCodeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvTableData.getText().toString()!=null && !tvTableData.getText().toString().equals("")){
                    loadToolBarSearch();
                }else{
                    Toast.makeText(RestuarantActivity.this, "Please select the table", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void loadToolBarSearch() {

        View view = RestuarantActivity.this.getLayoutInflater().inflate(R.layout.view_toolbar_search, null);
        LinearLayout parentToolbarSearch = (LinearLayout) view.findViewById(R.id.parent_toolbar_search);
        ImageView imgToolBack = (ImageView) view.findViewById(R.id.img_tool_back);
        final EditText edtToolSearch = (EditText) view.findViewById(R.id.edt_tool_search);
        ImageView imgToolMic = (ImageView) view.findViewById(R.id.img_tool_mic);
        final ListView listSearch = (ListView) view.findViewById(R.id.list_search);
        final TextView txtEmpty = (TextView) view.findViewById(R.id.txt_empty);

        UtilView.setListViewHeightBasedOnChildren(listSearch);

        edtToolSearch.setHint("Search your ItemCode or ItemName");

        final Dialog toolbarSearchDialog = new Dialog(RestuarantActivity.this, R.style.MaterialSearch);
        toolbarSearchDialog.setContentView(view);
        toolbarSearchDialog.setCancelable(false);
        toolbarSearchDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        toolbarSearchDialog.getWindow().setGravity(Gravity.BOTTOM);
        toolbarSearchDialog.show();

        toolbarSearchDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        if (searchItemList!=null && searchItemList.size()>0){
            searchAdapter = new SearchAdapter(RestuarantActivity.this, searchItemList, false);
            listSearch.setVisibility(View.VISIBLE);
            listSearch.setAdapter(searchAdapter);
        }



        listSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (tvTableData.getText().toString()!=null && !tvTableData.getText().toString().equals("")){
                    String itemname = String.valueOf(adapterView.getItemAtPosition(position));
                    SharedPreference.addList(RestuarantActivity.this, UtilView.PREFS_NAME, UtilView.KEY_ITEMNAME, itemname);
                    edtToolSearch.setText(itemname);
                    listSearch.setVisibility(View.GONE);
                    itemCodeSearch = edtToolSearch.getText().toString().trim();
                    callSearchItemCode(itemCodeSearch);
                    toolbarSearchDialog.dismiss();
                }else{
                    Toast.makeText(RestuarantActivity.this, "Please select the table", Toast.LENGTH_SHORT).show();
                }

            }
        });
        edtToolSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                mItemNameSearch = searchItemList;
                listSearch.setVisibility(View.VISIBLE);
                searchAdapter.updateList(mItemNameSearch, true);


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<SelectedItemsList> filterList = new ArrayList<SelectedItemsList>();
                boolean isNodata = false;
                if (s.length() > 0) {
                    for (int i = 0; i < mItemNameSearch.size(); i++) {


                        if (mItemNameSearch.get(i).getItemName().toLowerCase().startsWith(s.toString().trim().toLowerCase()) || mItemNameSearch.get(i).getItemCode().toLowerCase().startsWith(s.toString().trim().toLowerCase())) {

                            filterList.add(mItemNameSearch.get(i));

                            listSearch.setVisibility(View.VISIBLE);
                            searchAdapter.updateList(filterList, true);
                            isNodata = true;
                        }
                    }
                    if (!isNodata) {
                        listSearch.setVisibility(View.GONE);
                        txtEmpty.setVisibility(View.VISIBLE);
                        txtEmpty.setText("No data found");
                    }
                } else {
                    listSearch.setVisibility(View.GONE);
                    txtEmpty.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgToolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarSearchDialog.dismiss();
            }
        });

        imgToolMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtToolSearch.setText("");

            }
        });


    }

    private void callGetNotifications() {
        String getTableData = sharedPreference.getData(Constant.HINEXTRESTUARANTTABLE_KEY);
        if (getTableData != null) {
            Gson gson = new Gson();
            DefaultTable data = gson.fromJson(getTableData, DefaultTable.class);
            if (data!=null){
                Log.e("asJsonTableName",data.getTableName());

                tvTableData.setVisibility(View.VISIBLE);
                tvTableName.setVisibility(View.VISIBLE);
                tvWaiterName.setVisibility(View.VISIBLE);
                tvTableData.setText(data.getTableName());
                tvWaiterName.setText(empName);
                tableData.setTableName(data.getTableName());
                tableData.setTableValue(String.valueOf(data.getTableid()));
                tableData.setTableId(String.valueOf(data.getTableid()));
                tableData.setSelected(false);
                tableData.setFloorId("111");
                tableSelected = true;
                orderPalced = false;
            }

        }

        String url = "https://www.hiaccounts.in/services/v3/Notification/getNotification";
        //String url = "http://cloud.hiaccounts.com:8888/hiConnectService/v4/getnotifications";
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (url != null) {

            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                 postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {

                                NotificationListData notificationListData = gson.fromJson(result.toString(), NotificationListData.class);
                                if (notificationListData != null) {
                                   // Log.e("Notification Result2", notificationListData.getObject().toString());
                                    if (notificationListData.getObject().toString() != null) {
                                        notificationData = new Gson().fromJson(notificationListData.getObject().toString(), new TypeToken<NotificationData>() {
                                        }.getType());



                                        if (notificationData.getCustomerNotificationsList().size() > 0 && notificationData.getCustomerNotificationsList() != null)
                                            mNotificationCounter = notificationData.getCustomerNotificationsList().size();
                                       // Log.e("Notification size", String.valueOf(mNotificationCounter));


                                        if (mNotificationCounter > 0) {
                                            int counter = sharedPreference.getNotifyCounter("countMsg");
                                            if (counter!=0){
                                                Log.e("countData", String.valueOf(counter));
                                                Log.e("mNotificationCounter", String.valueOf(mNotificationCounter));
                                                if (mNotificationCounter!=counter){
                                                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                                    r.play();
                                                }
                                            }



                                            badgeNotification.setVisibility(View.VISIBLE);
                                            Handler handler = new Handler();
                                            handler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    badgeNotification.setText(String.valueOf(mNotificationCounter));
                                                    sharedPreference.SaveNotifyCounter("countMsg", mNotificationCounter);
                                                }
                                            });



                                        } else {
                                            badgeNotification.setVisibility(View.GONE);
                                        }

                                        if (listMessages.size() > 0) {
                                            listMessages.clear();
                                            messageAdapter.notifyDataSetChanged();
                                        }
                                        int i = 0;
                                        for (CustomerNotificationsList nData : notificationData.getCustomerNotificationsList()) {
                                            nData.setId(i);
                                           // Log.e("Notification id", String.valueOf(i));

                                            i++;
                                            listMessages.add(nData);

                                        }
                                    }

                                }
                            } catch (Exception e) {
                                Log.e("Notification Result", e.toString());
                            }


                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }


                }, false);
                try {

                    String regData = sharedPreference.getData(Constant.LOGINDETAIL);
                    if (regData != null) {
                        Gson gson = new Gson();
                        LoginDetail logData = gson.fromJson(regData, LoginDetail.class);
                        JSONObject jsonObject = new JSONObject();
                        // jsonObject.put("regno", "jIsCxAHvqG");
                        jsonObject.put("regno", logData.getCompanyRegNo());
                        jsonObject.put("user_id", logData.getUserId());
                        jsonObject.put("senderrequest", "to");
                        jsonObject.put("type_flag", "Sales");
                        postDataTask.execute(jsonObject.toString(), url, "");
                    }


                } catch (JSONException je) {
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void confirmationDailog(CustomerNotificationsList customerNotificationsList, int position, final Dialog dialogList) {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle("Notification");
        //alertDialog.setMessage("Notification");
        // alertDialog.setIcon(R.drawable.delete);
        alertDialog.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                dialogList.hide();
                badgeNotification.setVisibility(View.VISIBLE);
                callNotificationTable(customerNotificationsList, position);
                mNotificationCounter--;
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        badgeNotification.setText(String.valueOf(mNotificationCounter));
                        sharedPreference.SaveNotifyCounter("countMsg", mNotificationCounter);
                    }
                });

            }
        });
        alertDialog.setNegativeButton("Reject", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                badgeNotification.setVisibility(View.VISIBLE);
                dialog.cancel();
                dialogList.hide();
                getRejectNotifications(customerNotificationsList, position);
                mNotificationCounter--;
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        badgeNotification.setText(String.valueOf(mNotificationCounter));
                        sharedPreference.SaveNotifyCounter("countMsg", mNotificationCounter);
                    }
                });

            }
        });

        // Showing Alert Message
        alertDialog.show();


    }

    private void callNotificationTable(CustomerNotificationsList customerNotificationsList, int position) {
        if (serverUrl != null) {
            if (isInternetPresent) {
                    // UtilView.showProgessBar(mActivity, progressBar);
                JSONObject jsonObject= new JSONObject();
                     postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            //  UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                Log.e("resultTable", result.toString());
                                Gson gson = new Gson();
                                HideUtil.init(RestuarantActivity.this);
                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(mActivity, Activity_Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Log.e("DefaultTableName", result.toString());

                                        try {
                                            JSONObject jsonObject1 = new JSONObject(result.toString());
                                            String tableName = jsonObject1.getString("tableName");
                                            String tableId = jsonObject1.getString("tableid");

                                            tvTableData.setText(tableName);
                                            tableData.setTableValue(tableId);
                                            tableData.setTableName(tableName);
                                            tableData.setSelected(true);
                                            tableData.setFloorId(tableId);
                                            tableData.setTableId(tableId);
                                            Log.e("DefaultTableName1", tableData.getTableValue());
                                            Log.e("DefaultTableName2", tableData.getTableName());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        getPendingNotifications(customerNotificationsList, position,tableData);
                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);


                    postDataTask.execute(jsonObject.toString(), serverUrl + "/hipos/1/saveNewTable", "");

            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void getPendingNotifications(CustomerNotificationsList customerNotificationsList, int position, SubRow tableData) {

        this.listMessages.remove(position);
        messageAdapter.notifyDataSetChanged();
        list_view_notifications.invalidateViews();

        CustomerNotificationsListData custNotificationData =null;
       try {
          // Log.e("getObjectdata",customerNotificationsList.getObjectdata().toString());
           String data = customerNotificationsList.getObjectdata().trim();
            custNotificationData = new Gson().fromJson(data.toString(), new TypeToken<CustomerNotificationsListData>() {}.getType());

            if (custNotificationData.getJsonData().getSelectedItemsList() != null && custNotificationData.getJsonData().getSelectedItemsList().size() > 0){
                for (int j = 0; j < custNotificationData.getJsonData().getSelectedItemsList().size(); j++) {
                    //   Log.e("notificationItemData", String.valueOf(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemName()));
                    NotificationItemList notificationItemList = custNotificationData.getJsonData().getSelectedItemsList().get(j);

                    if (notificationItemList!=null){
                    List<SelectedItemsList> notifItemList = new ArrayList<>();

                    SelectedItemsList salesItem = new SelectedItemsList();

                    Gson gson = new Gson();
                    double gstTaxPercantage = 0.00;
                    String getPageLoadData = sharedPreference.getData(Constant.HINEXTRESTUARANTDATA_KEY);
                    if (getPageLoadData != null) {

                        pageData = gson.fromJson(getPageLoadData, HinextRestuarantPageData.class);
                        if (pageData != null) {

                            List<TaxList> taxList = pageData.getTaxList();
                            if (taxList != null) {
                                for (int i = 0; i < taxList.size(); i++) {
                                    Log.e("taxid", String.valueOf(taxList.get(i).getTaxid()));
                                    Log.e("OutputTaxId", String.valueOf(notificationItemList.getOutputTaxId()));
                                    if (notificationItemList.getOutputTaxId() == taxList.get(i).getTaxid()) {
                                        gstTaxPercantage = taxList.get(i).getTaxPercantage();
                                    }
                                }
                            }
                        }
                    }


                        Log.e("gstTaxPercantagedata", String.valueOf(gstTaxPercantage));
                    double itemUnitPrice = 0.00;

                    if (notificationItemList.getInclusiveJSON() != null) {
                        Boolean isInclusiveJSON=false;
                        try {

                            String jsonString = gson.toJson(notificationItemList.getInclusiveJSON() );
                            //Log.e("InclusiveJson1",jsonString);
                            JSONObject  jsonObject = new JSONObject(jsonString.toString());
                            if (jsonObject!=null){
                                isInclusiveJSON =  jsonObject.getBoolean("sales");
                                //Log.e("InclusiveJson1", String.valueOf(jsonObject.getBoolean("sales")));
                            }
                            if (isInclusiveJSON){
                                itemUnitPrice = Double.valueOf(notificationItemList.getUnitPrice()) ;
                            }else{

                                itemUnitPrice = Double.valueOf(notificationItemList.getUnitPrice());

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                    salesItem.setUnitPrice(itemUnitPrice);
                    double gstTaxAmt;

                        Log.e("gstTaxAmtData", String.valueOf(itemUnitPrice));
                    gstTaxAmt = ((notificationItemList.getQty() * itemUnitPrice * gstTaxPercantage) / 100);

                        Log.e("gstTaxAmtData", String.valueOf(gstTaxAmt));

                        salesItem.setGstTaxPercantage(gstTaxPercantage);
                    salesItem.setGstTaxAmt(gstTaxAmt);
                    double amountExTax = notificationItemList.getQty() * itemUnitPrice;
                    double amountInTax = Double.parseDouble(String.format("%.2f", amountExTax + gstTaxAmt));

                    salesItem.setItemCode(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemCode());
                    salesItem.setItemName(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemName());
                    salesItem.setItemId(Long.valueOf(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemId()));
                    salesItem.setQty(custNotificationData.getJsonData().getSelectedItemsList().get(j).getQty());
                    salesItem.setItemTotalAmount(Double.parseDouble(custNotificationData.getJsonData().getSelectedItemsList().get(j).getAmtinclusivetax()));
                    salesItem.setItemTypeId(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemTypeId());
                    salesItem.setInputTaxId(custNotificationData.getJsonData().getSelectedItemsList().get(j).getInputTaxId());
                    salesItem.setOutputTaxId(custNotificationData.getJsonData().getSelectedItemsList().get(j).getOutputTaxId());
                    salesItem.setType(custNotificationData.getJsonData().getSelectedItemsList().get(j).getType());
                    salesItem.setUnitPriceIn(Double.valueOf(custNotificationData.getJsonData().getSelectedItemsList().get(j).getUnitPriceIn()));
                    salesItem.setSerializableStatus(custNotificationData.getJsonData().getSelectedItemsList().get(j).getSerializableStatus());
                    salesItem.setTaxamt(salesItem.getGstTaxAmt());
                    salesItem.setTaxid(custNotificationData.getJsonData().getSelectedItemsList().get(j).getTaxid());
                    salesItem.setItemTypeName(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemTypeName());
                    salesItem.setAmtexclusivetax(amountExTax);
                    salesItem.setAmtinclusivetax(amountInTax);
                    salesItem.setHsnCode(custNotificationData.getJsonData().getSelectedItemsList().get(j).getHsnCode());
                    salesItem.setItemCategoryId(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemCategoryId());
                    salesItem.setItemCategoryName(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemCategoryName());
                    salesItem.setWaiterName(empName);
                    salesItem.setCustomerId(customerId);

                    // salesItem.setInclusiveJSON(custNotificationData.getJsonData().getSelectedItemsList().get(j).getInclusiveJSON());

                    notifItemList.add(salesItem);


                    RealmManager.createRestaurentDao().saveRestaurentItemDetails(DataGenerator.generateNotificationRestaSelectItem(notifItemList, tableData, salesItem.getItemCode()), salesItem.getItemCode(), tableData);

                    checkTableCartList();

                    notificationOrderNo = customerNotificationsList.getFromRegno();
                    notificationagentId = customerNotificationsList.getFromCompname();
                    notificationAppend =true;
                }
                }
            }

        }
        catch (IllegalStateException | JsonSyntaxException exception)
        {
            Log.e("IllegalStateException",exception.toString());
        }


       // String url = "http://cloud.hiaccounts.com:8888/hiConnectService/v4/getallpendingnotifications";
        String url = "https://www.hiaccounts.in/services/v3/Notification/accept";
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (url != null) {

            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                 postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Log.e("AcceptNotification", result.toString());
                            Gson gson = new Gson();
                            try {


                            } catch (Exception e) {
                                Log.e("Notification Result", e.toString());
                            }


                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }


                }, false);
                try {
                    JSONObject jsonObject = new JSONObject();
                    if (customerNotificationsList != null) {
                        jsonObject.put("fromRegno", customerNotificationsList.getFromRegno());
                        jsonObject.put("toRegno", customerNotificationsList.getToRegno());
                        //jsonObject.put("user_id", customerNotificationsList.getPiNo());
                        jsonObject.put("typeDoc", customerNotificationsList.getTypeDoc());
                        jsonObject.put("typeFlag", customerNotificationsList.getTypeFlag());
                        jsonObject.put("custNotiId", customerNotificationsList.getCustNotiId());
                        jsonObject.put("status","Accept");
                    }


                    postDataTask.execute(jsonObject.toString(), url, "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }


    }

    private void getRejectNotifications(CustomerNotificationsList customerNotificationsList, int position) {
        listMessages.remove(position);
        messageAdapter.notifyDataSetChanged();
        String url = "https://www.hiaccounts.in/services/v3/Notification/accept";
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (url != null) {

            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                 postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Log.e("RejectNotification", result.toString());
                            Gson gson = new Gson();
                            try {


                            } catch (Exception e) {
                                Log.e("Notification Result", e.toString());
                            }


                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }


                }, false);
                try {

                    JSONObject jsonObject = new JSONObject();
                    if (customerNotificationsList != null) {
                        jsonObject.put("fromRegno", customerNotificationsList.getFromRegno());
                        jsonObject.put("toRegno", customerNotificationsList.getToRegno());
                        // jsonObject.put("user_id", "1");
                        jsonObject.put("typeDoc", customerNotificationsList.getTypeDoc());
                        jsonObject.put("typeFlag", customerNotificationsList.getTypeFlag());
                        jsonObject.put("custNotiId", customerNotificationsList.getCustNotiId());
                        jsonObject.put("status", "Reject");
                    }
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

    private void callItemCodeSearchMethod(String itemCodeSearch) {
        String url = serverUrl + "/hipos/1/" + Constant.FUNTION_RESTARUANTITEMDATALIST;
        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(mActivity, progressBar);
                 getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                Log.e("ItemCode Result", result.toString());
                                Gson gson = new Gson();
                                searchItemList = new ArrayList<>();

                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());

                                    SelectedItemsList item = null;
                                    if (jsonArray != null) {
                                        for (int in = 0; in < jsonArray.length(); in++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(in);
                                            item = gson.fromJson(jsonObject.toString(), SelectedItemsList.class);

                                            double gstTaxPercantage = 0.00;
                                            String getPageLoadData = sharedPreference.getData(Constant.HINEXTRESTUARANTDATA_KEY);
                                            if (getPageLoadData != null) {

                                                pageData = gson.fromJson(getPageLoadData, HinextRestuarantPageData.class);
                                                if (pageData != null) {

                                                    List<TaxList> taxList = pageData.getTaxList();
                                                    if (taxList != null) {
                                                        for (int i = 0; i < taxList.size(); i++) {
                                                            if (item.getOutputTaxId() == taxList.get(i).getTaxid()) {
                                                                gstTaxPercantage = taxList.get(i).getTaxPercantage();
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                            double itemUnitPrice = 0.00;
                                            if (item.getInclusiveJSON() != null) {

                                                InclusiveJson inclusiveJson = gson.fromJson(item.getInclusiveJSON(), InclusiveJson.class);
                                                if (inclusiveJson.isSales()) {
                                                    itemUnitPrice = (item.getSalesPrice() / (1 + (gstTaxPercantage * 0.01)));
                                                } else {
                                                    if (item.getSalesPrice() != null)
                                                        itemUnitPrice = item.getSalesPrice();
                                                }
                                            }

                                            item.setGstTaxPercantage(gstTaxPercantage);
                                            item.setUnitPrice(itemUnitPrice);
                                            double gstTaxAmt;

                                            item.setQty(1L);

                                            gstTaxAmt = ((item.getQty() * item.getUnitPrice() * gstTaxPercantage) / 100);

                                            item.setGstTaxPercantage(gstTaxPercantage);
                                            item.setGstTaxAmt(gstTaxAmt);
                                            item.setWaiterName(empName);
                                            if (customerId!=null){
                                                item.setCustomerId(customerId);
                                            }
                                            searchItemList.add(item);
                                        }


                                    } else {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.response_error));
                                    }


                                } catch (Exception e) {
                                Log.e("ExceptionItemSearch",e.toString());
                                }


                            } catch (Exception e) {
                                UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void callSearchItemCode(String itemCodeSearch) {
        tableSelected = true;
        if (tableSelected){
            orderPalced = false;
            if (searchItemList != null && searchItemList.size() > 0 ) {
                // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<SelectedItemsList> filtered1 = new ArrayList<>();
                List<SelectedItemsList> filtered2 = new ArrayList<>();

                for (SelectedItemsList itemsList: searchItemList) {
                    if (itemsList.getItemName().equalsIgnoreCase(itemCodeSearch)) {
                        filtered1.add(itemsList);
                    }
                }

                for (SelectedItemsList itemsList: searchItemList) {
                    if (itemsList.getItemCode().equalsIgnoreCase(itemCodeSearch)) {
                        filtered2.add(itemsList);
                    }
                }

                if (filtered1 != null && filtered1.size() > 0 || filtered2 != null && filtered2.size() > 0) {
                    if (filtered1 != null && filtered1.size() > 0) {
                        RealmManager.createRestaurentDao().saveRestaurentTableData(DataGenerator.generateTableData(filtered1, tableData), tableVal);
                    } else if (filtered2 != null && filtered2.size() > 0) {
                        RealmManager.createRestaurentDao().saveRestaurentTableData(DataGenerator.generateTableData(filtered2, tableData), tableVal);
                    }
                    checkTableCartList();
                }


                // }

            }
        }

    }

    private void getPageLoadData() {
        String url = serverUrl + "/restaurant/" + Constant.FUNTION_RESTRAGETPAGELOADDATA;
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                 getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
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
                                try {
                                    Gson gson = new Gson();
                                    pageData = gson.fromJson(result.toString(), HinextRestuarantPageData.class);
                                    sharedPreference.saveData(Constant.HINEXTRESTUARANTDATA_KEY, new Gson().toJson(pageData));
                                    setupPageData(pageData);
                                } catch (Exception e) {
                                    Log.e("exception ", e.toString());
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
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setupPageData(HinextRestuarantPageData pageData) {

        String getPageLoadData = sharedPreference.getData(Constant.HINEXTRESTUARANTDATA_KEY);
        if (getPageLoadData != null) {
            Gson gson = new Gson();
            pageData = gson.fromJson(getPageLoadData, HinextRestuarantPageData.class);
            if (pageData != null) {
                if (pageData.getCustomers() != null) {
                    selectCustomer.setText(pageData.getCustomers().get(0).getCustomerName());
                    customerId = pageData.getCustomers().get(0).getCustomerId();
                    customerName = pageData.getCustomers().get(0).getCustomerName();

                }
                if (pageData.getCustomers() != null) {
                    if (pageData.getCustomers().get(0).getAddress()!=null && !pageData.getCustomers().get(0).getAddress().equals("")){
                        customerAddress = pageData.getCustomers().get(0).getAddress();
                    }
                }
                if (pageData.getCustomers() != null) {
                    if (pageData.getCustomers().get(0).getPhoneNumber()!=null && !pageData.getCustomers().get(0).getPhoneNumber().equals("")){
                        customerNo = pageData.getCustomers().get(0).getPhoneNumber();
                    }

                }



                if (pageData.getEmployeeList() != null) {
                    empName = pageData.getEmployeeList().get(0).getEmployeeName();
                }
            }
        }


        String applicationDataJson = sharedPreference.getData(Constant.COMPANYDATA);


        if (applicationDataJson != null) {
            Gson gson = new Gson();
            companyData = gson.fromJson(applicationDataJson, CompanyData.class);


            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    tvCompanyName.setText(companyData.getCompanyName());
                }

                String userName = sharedPreference.getData(Constant.USERNAME);
                if (userName != null)
                    tvUserName.setText(userName);

                //if (companyData.getFileName() != null) {
                String url = UtilView.getUrl(mActivity);

                if (url != null) {
                    Picasso.with(this).load(url + companyData.getFileName())
                            .networkPolicy(NetworkPolicy.NO_CACHE)
                            .memoryPolicy(MemoryPolicy.NO_CACHE)
                            .placeholder(R.drawable.ic_progress)
                            .error(R.drawable.ic_app_icon)
                            .into(imgViewLogo);
                } else {
                    UtilView.gotToLogin(mActivity);
                }
                // }
            } else {
                imgViewLogo.setImageDrawable(getResources().getDrawable(R.drawable.ic_app_icon));
            }
        }


        if (llCategory != null) {
            llCategory.setVisibility(View.VISIBLE);
            idRecyclerviewCategory.setHasFixedSize(true);
            GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2, GridLayoutManager.HORIZONTAL, false);
            //linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
            idRecyclerviewCategory.setLayoutManager(layoutManager);
            // Create a custom SpanSizeLookup where the first item spans both columns
            /*layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == 0 ? 2 : 1;
                }
            });*/
            idRecyclerviewCategory.setAdapter(new RecyclerRestuarantCategoryAdapter(mActivity, this.pageData.getItemCategorys(), new RecyclerRestuarantCategoryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ItemCategory item) {
                    // Toast.makeText(getContext(), "Item Clicked "+item.getItemCategoryId(), Toast.LENGTH_LONG).show();
                    categoryId = item.getItemCategoryId();
                    getPageLoadItemData(categoryId);
                }
            }));
        }


    }

    private void getPageLoadItemData(Long categoryId) {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_RESTRAITEMLISTINCCATEGOERY + "?" + Constant.FUNTION_RESTRAITEMLISTINCCATEGOERYID + "=" + categoryId+"&searchText=";

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                 getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
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
                                Gson gson = new Gson();
                                itemList = new ArrayList<>();

                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());

                                    SelectedItemsList item = null;
                                    if (jsonArray != null) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            item = gson.fromJson(jsonObject.toString(), SelectedItemsList.class);
                                            itemList.add(item);
                                        }

                                        if (itemList != null && itemList.size() > 0) {

                                            if (llItem != null) {
                                                llItem.setVisibility(View.VISIBLE);
                                                setupPageItemData();

                                            }
                                        } else {
                                            UtilView.showToast(mActivity, "No items in Cart.Please add item .");
                                        }

                                    } else {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.response_error));
                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
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
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setupPageItemData() {


        idRecyclerviewItem.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 4, GridLayoutManager.VERTICAL, false);
        //linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        idRecyclerviewItem.setLayoutManager(layoutManager);
        // idRecyclerviewCategory.setBackgroundColor(getResources().getColor(R.color.colorLassi));
        chkBoxImage.setVisibility(View.VISIBLE);
        tv.setVisibility(View.VISIBLE);
        itemsAdapter = new RecyclerRestuarantItemsAdapter(chkBoxImage.isChecked(), mActivity, itemList, new RecyclerRestuarantItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelectedItemsList item) {
                Gson gson = new Gson();

                if (tvTableData.getText().toString()!=null && !tvTableData.getText().toString().equals("")){
                    orderPalced = false;
                    try {
                        if (tablesdata != null && tablesdata.size() == 0) {
                            if (tableData != null) {
                                SubRow data = new SubRow();
                                data.setSelected(true);
                                if (tableData.getTableName() != null) {
                                    data.setTableName(tableData.getTableName());
                                }
                                if (tableData.getTableValue() != null) {
                                    data.setTableValue(tableData.getTableValue());
                                }
                                if (tableData.getFloorId() != null) {
                                    data.setFloorId(tableData.getFloorId());
                                }

                                tablesdata.add(data);

                                Set<SubRow> hash = new HashSet<>(tablesdata);
                                tablesdata.clear();
                                tablesdata.addAll(hash);
                            }


                        }


                        if (tablesdata != null && tablesdata.size() > 0) {
                            for (int i = 0; i < tablesdata.size(); i++) {
                                if (tablesdata.get(i).getFloorId().contains(tableData.getFloorId())) {
                                    break;
                                } else if (tablesdata.size() > 0) {
                                    SubRow data = new SubRow();
                                    data.setSelected(true);
                                    data.setTableName(tableData.getTableName());
                                    data.setTableValue(tableData.getTableValue());
                                    data.setFloorId(tableData.getFloorId());
                                    tablesdata.add(data);

                                }


                            }


                            Set<SubRow> hash1 = new HashSet<>(tablesdata);
                            tablesdata.clear();
                            tablesdata.addAll(hash1);
                        }
                    } catch (Exception e) {

                    }


                    double gstTaxPercantage = 0.00;
                    if (pageData != null) {

                        List<TaxList> taxList = pageData.getTaxList();
                        if (taxList != null) {
                            for (int i = 0; i < taxList.size(); i++) {
                                if (item.getOutputTaxId() == taxList.get(i).getTaxid()) {
                                    gstTaxPercantage = taxList.get(i).getTaxPercantage();
                                }
                            }
                        }
                    }
                    double itemUnitPrice = 0.00;
                    if (item.getInclusiveJSON() != null) {

                        InclusiveJson inclusiveJson = gson.fromJson(item.getInclusiveJSON(), InclusiveJson.class);
                        if (inclusiveJson.isSales()) {

                            itemUnitPrice = item.getSalesPrice() / (1 + (gstTaxPercantage * 0.01));

                            Log.e("itemUnitPrice", String.valueOf(itemUnitPrice));
                            item.setUnitPrice(itemUnitPrice);
                            Log.e("itemUnitgetTaxPer", String.valueOf(gstTaxPercantage));
                        } else {
                            if (item.getSalesPrice() != null)
                                itemUnitPrice = item.getSalesPrice();

                            item.setUnitPrice(itemUnitPrice);
                        }
                    }

                    item.setGstTaxPercantage(gstTaxPercantage);

                    long itemQty = posTableOrderCreator.checkItemQty(item);
                    item.setItemQuantity(itemQty);
                    if (customerId!=null){
                        item.setCustomerId(customerId);
                    }


                    if (tableData != null) {
                        tableVal = tableData.getTableValue();
                        item.setFloorId(tableData.getFloorId());
                        item.setTableName(tableData.getTableName());
                        item.setTableId(tableData.getTableValue());
                        item.setSelect(true);
                        item.setWaiterName(empName);
                    }

                    selectedItemData = new ArrayList<>();
                    selectedItemData.add(item);


                    if (selectedItemData != null && selectedItemData.size() > 0) {
                        RealmManager.createRestaurentDao().saveRestaurentTableData(DataGenerator.generateTableData(selectedItemData, tableData), tableVal);

                    }
                    checkTableCartList();

                }else{
                    Toast.makeText(RestuarantActivity.this, "Please select the table", Toast.LENGTH_SHORT).show();
                }




            }
        });

        idRecyclerviewItem.setAdapter(itemsAdapter);


    }

    private void getRestuarentDefaultTableList() {

        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETRESTUARANTTABLE + "?tableSearchText=&type=Active";

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(mActivity, progressBar);
                 getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(mActivity, Activity_Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    JSONArray resultJsonArray = new JSONArray(result.toString());
                                    JSONObject asJson = resultJsonArray.getJSONObject(0);
                                    if (asJson!=null){
                                        Log.e("asJson",asJson.toString());
                                        sharedPreference.saveData(Constant.HINEXTRESTUARANTTABLE_KEY, asJson.toString());
                                        String getTableData = sharedPreference.getData(Constant.HINEXTRESTUARANTTABLE_KEY);
                                        if (getTableData != null) {
                                            Gson gson = new Gson();
                                            DefaultTable data = gson.fromJson(getTableData, DefaultTable.class);
                                            if (data!=null){
                                               // Log.e("asJsonTableName",data.getTableName());

                                                tvTableData.setVisibility(View.VISIBLE);
                                                tvTableName.setVisibility(View.VISIBLE);
                                                tvWaiterName.setVisibility(View.VISIBLE);
                                                tvTableData.setText(data.getTableName());
                                                tvWaiterName.setText(empName);
                                                tableData.setTableName(data.getTableName());
                                                tableData.setTableValue(String.valueOf(data.getTableid()));
                                                tableData.setSelected(false);
                                                tableData.setFloorId("111");
                                                tableData.setTableId(String.valueOf(data.getTableid()));
                                                tableSelected = true;
                                                orderPalced = false;
                                            }

                                        }
                                    }
                               }


                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }

    }
    public void checkTableCartList() {

/*
        if (item != null) {
            if (item.size() > 0) {*/

       /*

        if (ll != null)
            ll.setVisibility(View.VISIBLE);*/

        if (llListview != null)
            llListview.setVisibility(View.VISIBLE);


        if (rlBottomLayout != null)
            rlBottomLayout.setVisibility(View.VISIBLE);


        if (tableData != null) {
            try {

                waiterName = RealmManager.createRestaurentDao().getWaiterName(tableData);
                if (waiterName!=null){
                    empName = waiterName.getWaiterName();
                    tvWaiterName.setText(empName);

                    if (waiterName.isKotSelect()){
                        //tvWaiterName.setClickable(false);
                        Log.e("waiterName1",waiterName.getWaiterName());
                    }
                    callCustomerName(waiterName);
                }

            }catch (Exception e){

            }
            Log.e("TableName1",tableData.getTableName());
            Log.e("TableNameId1",tableData.getTableValue());
            Log.e("TableNameSelec1", String.valueOf(tableData.getSelected()));
            Log.e("TableNameFloor1", String.valueOf(tableData.getFloorId()));

            try {
                //posCartTableItems.clear();
               posCartTableItems = RealmManager.createRestaurentDao().getRestuarentItemList(tableData);
                Log.e("posCartTableItemssize", String.valueOf(posCartTableItems.size()));
                if (posCartTableItems != null && posCartTableItems.size() > 0) {
                    restraposTableOrdersAdapter = new RestraposTableOrders_Adapter(mActivity, posCartTableItems);
                    try {
                        listviewSelec.smoothScrollToPosition(posCartTableItems.size());
                        listviewSelec.setAdapter(restraposTableOrdersAdapter);
                        restraposTableOrdersAdapter.notifyDataSetChanged();

                        showTotalTableOrderPrice(posCartTableItems);

                    } catch (Exception e) {
                        UtilView.showLogCat(TAG, "checkCartList cart Exception " + e.toString());
                    }
                } else {
                    posCartTableItems.clear();
                    restraposTableOrdersAdapter = new RestraposTableOrders_Adapter(mActivity, posCartTableItems);
                    try {
                        listviewSelec.smoothScrollToPosition(posCartTableItems.size());
                        if (listviewSelec != null)
                            listviewSelec.setAdapter(restraposTableOrdersAdapter);
                        restraposTableOrdersAdapter.notifyDataSetChanged();
                        showTotalTableOrderPrice(posCartTableItems);
                    } catch (Exception e) {
                        UtilView.showLogCat(TAG, "checkCartList cart Exception " + e.toString());
                    }
                }


            } catch (Exception e) {
                Log.e("table exception", e.toString());
            }

        }
    }

    private void callCustomerName(Realm_SelectItemList custmer) {
        String getPageLoadData = sharedPreference.getData(Constant.HINEXTRESTUARANTDATA_KEY);
        if (getPageLoadData != null) {
            Gson gson = new Gson();
            pageData = gson.fromJson(getPageLoadData, HinextRestuarantPageData.class);
            if (pageData != null) {
                if (pageData.getCustomers() != null) {

                    for (in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.Customer customer :pageData.getCustomers()){
                        if (custmer!=null){
                            if (custmer.getCustomerId() == customer.getCustomerId()){
                                selectCustomer.setText(customer.getCustomerName());
                                customerId = customer.getCustomerId();
                                Log.e("CustomerId3", String.valueOf(customerId));
                                customerName = customer.getCustomerName();

                                    if (customer.getAddress()!=null && !customer.getAddress().equals("")){
                                        customerAddress = customer.getAddress();
                                    }

                                    if (customer.getPhoneNumber()!=null && !customer.getPhoneNumber().equals("")){
                                        customerNo = customer.getPhoneNumber();
                                    }


                            }
                        }

                    }



                }

            }
        }
    }

    public SelectedItemsList showUpdateItem(SelectedItemsList item) {
        SelectedItemsList selected = new SelectedItemsList();
        selected.setItemQuantity(item.getItemQuantity());
        selected.setItemCode(item.getItemCode());
        return selected;
    }


    public void showTotalTableOrderPrice(List<SelectedItemsList> posCartTableItems) {
        Double posCartTotal = 0.00;
        int posTotalItem = 0;


        for (int i = 0; i < posCartTableItems.size(); i++) {
            posCartTotal += (posCartTableItems.get(i).getUnitPrice() * posCartTableItems.get(i).getItemQuantity());
            //posCartTotal +=posCartTableItems.get(i).getItemTotalAmountInTax();
            // Log.e("Amount ", String.valueOf(posTableOrderCreator.getItems().get(i).getItem().getItemTotalAmount()));
            posTotalItem += posCartTableItems.get(i).getItemQuantity();
            //Log.e("AmountQty ", String.valueOf(posTableOrderCreator.getItems().get(i).getItem().getItemQuantity()));

        }


        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        UtilView.showLogCat(TAG, "" + df.format(posCartTotal));
       // UtilView.showLogCat(TAG, "" + (int) Math.ceil(posCartTotal));
        //edCartTotalamount.setText("" + Math.ceil(posCartTotal));
       // edCartTotalamount.setText("" + posCartTotal);
        edCartTotalamount.setText(String.valueOf(new BigDecimal(posCartTotal).setScale(2,RoundingMode.HALF_UP)));
        edCartQty.setText("" + posTotalItem);

        posTableOrderCreator.setTotalItems(posTotalItem);
        posTableOrderCreator.setTotalPrice(posCartTotal);


    }

    public void checkCartList() {


        if (posCreator != null) {
            if (posCreator.getItems().size() > 0) {

                if (llListview != null)
                    llListview.setVisibility(View.VISIBLE);


                if (rlBottomLayout != null)
                    rlBottomLayout.setVisibility(View.VISIBLE);
                List<RestraPosCartItem> posCartItems = posCreator.getItems();


                cartItemAdapter = new Restrapos_Adapter1(mActivity, posCartItems);
                try {
                    listviewSelec.smoothScrollToPosition(posCartItems.size());
                    if (listviewSelec != null)
                        listviewSelec.setAdapter(cartItemAdapter);
                    cartItemAdapter.notifyDataSetChanged();
                    showTotalPrice();
                } catch (Exception e) {
                    UtilView.showLogCat(TAG, "checkCartList cart Exception " + e.toString());
                }


            } else {

                List<RestraPosCartItem> posCartItems = posCreator.getItems();
                if (cartItemAdapter != null) {
                    cartItemAdapter.updateCartItems(posCartItems);
                    cartItemAdapter.notifyDataSetChanged();
                }
                showTotalPrice();
            }
        }

    }


    public void showTotalPrice() {
        Double posCartTotal = 0.00;
        int posTotalItem = 0;
        for (int i = 0; i < posCreator.getItems().size(); i++) {
            posCartTotal += posCreator.getItems().get(i).getItem().getItemTotalAmount();
            posTotalItem += posCreator.getItems().get(i).getItem().getItemQuantity();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        UtilView.showLogCat(TAG, "" + df.format(posCartTotal));
        UtilView.showLogCat(TAG, "" + (int) Math.ceil(posCartTotal));
        edCartTotalamount.setText("" + Math.floor(posCartTotal));
        //edCartTotalamount.setText(String.valueOf(new BigDecimal(posCartTotal).setScale(2,RoundingMode.HALF_UP)));
        edCartQty.setText("" + posTotalItem);
        //Log.e("AmountQty1 ", String.valueOf(posTotalItem));
        //  Log.e("Amount1 ", String.valueOf(posCartTotal));


        posCreator.setTotalItems(posTotalItem);
        posCreator.setTotalPrice(posCartTotal);
    }

    private void saveKotTableSelected(SubRow tableData) {
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                     postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                Log.e("resultTable", result.toString());
                                Gson gson = new Gson();
                                HideUtil.init(RestuarantActivity.this);
                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(mActivity, Activity_Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        try {
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        initializeBluetoothDevice();
                                                        if (BLUETOOTH_PRINTER != null) {
                                                            if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                                                                UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());

                                                                if (BLUETOOTH_PRINTER.getState() == 3) {
                                                                    Log.e("kot connection", "connected");
                                                                    if (dualKotPrint){
                                                                        tablesdata.clear();
                                                                        bluetoothStatus = true;
                                                                        tableOrderKOTCheckout();

                                                                    }
                                                                }
                                                            }
                                                        }else {
                                                            UtilView.showToast(mActivity, "Please setup the printer connection first1.");
                                                        }
                                                    }catch (Exception e){
                                                        Log.e("kotprinter excption",e.toString());
                                                    }

                                                }
                                            }, 2000);


                                               // callKotTempData();
                                        } catch (Exception e) {
                                            Log.e("tableExeception", e.toString());
                                        }
                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);

                    if (tableData != null) {
                        tableName = tableData.getTableName();
                        tableId = tableData.getTableId();
                    }

                    if (tableData != null) {
                        tableName = tableData.getTableName();
                        tableId = tableData.getTableId();
                    }

                    String employeeName = "";
                    if (employeeDetail != null  && !notificationAppend) {
                        employeeName = employeeDetail.getEmployeeName();
                    } else if (employeeDetail != null && notificationAppend){
                        employeeName = "";
                    }else{
                        employeeName = empName;
                    }

                    postDataTask.execute(saveOrderPostData("", "", selected_customer), serverUrl + "/restaurant/saveTableDataTempMobile?currTableName=" + tableName.replace(" ", "%20") + "&currTableId=" + tableId + "&prevTableName=" + tableName.replace(" ", "%20") + "&prevTableId=" + tableId + "&employeeName=" + employeeName.replace(" ", "%20") + "&customerId=" + customerId+"&orderNo="+notificationOrderNo+"&agentId="+notificationagentId, "");
                   // postDataTask.execute(saveOrderPostData("", "", selected_customer), serverUrl + "/restaurant/saveTableDataTemp?currTableName=" + tableName.replace(" ", "%20") + "&currTableId=" + tableId + "&prevTableName=" + tableName.replace(" ", "%20") + "&prevTableId=" + tableId + "&employeeName=" + employeeName.replace(" ", "%20") + "&customerId=" + customerId+"&orderNo="+orderNo, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }

    }

    private void tableOrderKOTWithoutCheckout() {
        if (posCartTableItems != null && posCartTableItems.size() > 0 || posCreator != null && posCreator.getItems().size() > 0) {
            final String saveData = saveKOtOrderPostData("", "", selected_customer);
            updateKotItems();

            if (serverUrl != null) {
                if (isInternetPresent) {
                    if (saveData!=null && itemListKot != null && itemListKot.size() > 0) {
                        // UtilView.showProgessBar(mActivity, progressBar);
                         postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                //  UtilView.hideProgessBar(mActivity, progressBar);
                                if (result != null) {
                                    Log.e("resultTable", result.toString());
                                    Gson gson = new Gson();
                                    HideUtil.init(RestuarantActivity.this);
                                    if (result != null) {
                                        if (result.equals("invalid")) {
                                            UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                            Intent intent = new Intent(mActivity, Activity_Login.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Log.e("PlaceOrder", result.toString());
                                            try {
                                                RestraCheckoutData saveResponseData1 = gson.fromJson(result.toString(), RestraCheckoutData.class);
                                                if (saveResponseData1 != null) {
                                                    if (!saveResponseData1.getSelectedItemsList().isEmpty()) {
                                                        checkTableCartList();
                                                    }
                                                }

                                            } catch (Exception e) {
                                                Log.e("table", e.toString());
                                            }

                                            //JSONObject jsonObject = new JSONObject(result.toString());



                                        }
                                    }
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                                }
                            }
                        }, false);
                        if (tableData != null) {
                            tableName = tableData.getTableName();
                            tableId = tableData.getTableValue();
                        }

                        String employeeName = "";
                        if (employeeDetail != null) {
                            employeeName = employeeDetail.getEmployeeName();
                        } else {
                            employeeName = empName;
                        }

                        postDataTask.execute(saveData, serverUrl + "/hipos/mobile/placeOrder", "");
                    } else {
                       // Toast.makeText(mActivity, "No items change in order.", Toast.LENGTH_SHORT).show();
                    }


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

    @OnClick({R.id.idNotifications, R.id.idSettings1, R.id.imgviewSelectTable, R.id.fabPrintList, R.id.fabsplitbill, R.id.fabDailyReport, R.id.fabPrinterConfigurartion, R.id.fabsaveorder, R.id.clearall, R.id.fabPayment, R.id.chkBoxImage, R.id.idSettings, R.id.selectCustomer, R.id.tvWaiterName})
    public void onViewClicked(View view) {
        switch (view.getId()) {

          /*  case R.id.imgviewDelete:
                if (selectItemForDelete != null && selectItemForDelete.size() > 0) {
                    posCreator.delete(selectItemForDelete);
                    checkCartList();
                    selectItemForDelete.clear();
                } else {
                    UtilView.showToast(mActivity, "Please select at least one item to delete");
                }
                break;*/

            case R.id.idNotifications:

                callNotificationListItems();

                if (mNotificationCounter == 0){
                    callGetNotifications();
                }


                if (listMessages != null && listMessages.size() > 0) {
                    RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                    RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                    RealmManager.createRestaurentDao().deletRealmTempOrderDataAllTable();
                    //listMessages.clear();
                    callGetNotifications();
                    callNotificationListItems();
                }

                break;
            case R.id.idSettings:
                restaSettings();
                break;
            case R.id.imgviewSelectTable:
                if (notificationOrderNo!=null && !notificationOrderNo.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            RestuarantActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("would you like to clear the orders");
                    builder.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                }
                            });
                    builder.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                                    RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                                    RealmManager.createRestaurentDao().deletRealmTempOrderDataAllTable();
                                    restuarentSelectTable();
                                }
                            });
                    builder.show();

                }else {
                    restuarentSelectTable();
                }

                break;

            case R.id.idSettings1:
                fabMenus.setVisibility(View.VISIBLE);
                fabMenus.open(true);

                break;
            case R.id.fabPrintList:
                callPrintList();
                break;
            case R.id.fabsplitbill:
                //fabsplitbill.setEnabled(false);
                callSplitBill();
                break;
            case R.id.fabDailyReport:
                callDialyReport();
                break;
            case R.id.fabPrinterConfigurartion:
                fabMenus.setVisibility(View.GONE);
                fabMenus.close(true);
                callPrinterConfiguration();
              break;
            case R.id.selectCustomer:
                Intent intent1 = new Intent(mActivity, Activity_Customer.class);
                intent1.putExtra("callingFrom", "Restra");
                startActivityForResult(intent1, Constant.RESQUSTCODE_CUSTOMERS);
                break;

            case R.id.tvWaiterName:
                Intent intent2 = new Intent(mActivity, Activity_ControlPanelSubmenu.class);
                intent2.putExtra("callingFrom", "Restra");
                startActivityForResult(intent2, Constant.RESQUSTCODE_ADDEMPLOYEE);
                break;


          /*  case R.id.tvSignOut:
                // logout();
                break;*/
            case R.id.clearall:

                posCartTableItems = RealmManager.createRestaurentDao().getRestuarentItemList(tableData);
                if (posCartTableItems != null && posCartTableItems.size()>0) {
                    try {
                        for (int i = 0; i < posCartTableItems.size(); i++) {
                            if (!posCartTableItems.get(i).getKotSelect()) {
                                RealmManager.createRestaurentDao().deletRealmDataAllClear(posCartTableItems.get(i));
                            }else {
                                String singlePrinter = sharedPreference.getData(Constant.SINGLE_PRINTER);
                                if (singlePrinter!=null && singlePrinter.equals("singlePrinter")) {
                                    RealmManager.createRestaurentDao().saveRestaurentKotCancelOrders(DataGenerator.generateRestaKotCancelOrder(posCartTableItems.get(i)));
                                    Log.e("kotSelect", String.valueOf(posCartTableItems.get(i).getKotSelect()));

                                }
                            }
                        }

                        RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                        RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                        clearAllOrders();

                        posCartTableItems.clear();
                        if (selectedItemData != null) {
                            selectedItemData.clear();
                        }
                        checkTableCartList();

                    } catch (Exception e) {
                        Log.e("Exception", e.toString());
                    }


                }
                if (posCreator != null) {
                    posCreator.clear();
                    checkCartList();
                }
                break;

            case R.id.fabsaveorder:
                callSaveOrder();

                break;
            case R.id.fabPayment:
//                   fabPayment.setEnabled(false);
//                   Toast.makeText(mActivity, "Access Denied.", Toast.LENGTH_SHORT).show();
                if (posCartTableItems != null && posCartTableItems.size() > 0) {
                        Intent intent = new Intent(mActivity, Activity_RestraPayment1.class);
                        intent.putExtra("checkoutData", getCheckoutDataForTable());
                        checkoutType = "multiPayment";
                        intent.putExtra("checkoutType", checkoutType);
                        startActivityForResult(intent, Constant.RESQUSTCODE_CHECKOUT);

                } else {
                    UtilView.showToast(mActivity, "No items in Cart. Please add item.");
                }
                break;
            case R.id.chkBoxImage:
                if (itemsAdapter != null) {
                    itemsAdapter.showItemWithImgaes(chkBoxImage.isChecked());
                    itemsAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void clearAllOrders() {
        try {

            String kotaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSBILL);
            String printerTypekot1 = sharedPreference.getData(Constant.BLUETOOTHTYPEBILL);
            if (printerTypekot1!=null && printerTypekot1.equals("2-inch")){
                printerType = "2-inch";
            }else {
                printerType = "3-inch";
            }
            Log.e("kotaddress",kotaddress);
            device = mBluetoothAdapter.getRemoteDevice(kotaddress);
            BLUETOOTH_PRINTER.start();
            BLUETOOTH_PRINTER.connect(device);
        }catch (Exception e){
            Log.e("kotprinter excption",e.toString());
        }

        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                 postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Log.e("resultTable", result.toString());
                            Gson gson = new Gson();
                            if (result.equals("invalid")) {
                                UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(mActivity, Activity_Login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                try {
                                    if (BLUETOOTH_PRINTER != null) {
                                        if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                                            UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());

                                            if (BLUETOOTH_PRINTER.getState() == 3) {
                                                bluetoothStatus = true;
                                                callAllCancelOrders();
                                            } else if (BLUETOOTH_PRINTER.getState() == 2) {

                                            } else {
                                                UtilView.showToast(mActivity, "Please setup the printer connection first3.");
                                            }

                                        } else {
                                            UtilView.showToast(mActivity, "Please setup the printer connection first2.");
                                        }
                                    }else {
                                        callAllCancelOrders();
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

                if (tableData != null) {
                    tableName = tableData.getTableName();
                    tableId = tableData.getTableId();
                }

                if (tableData != null) {
                    tableName = tableData.getTableName();
                    tableId = tableData.getTableId();
                }

                String employeeName = "";
                if (employeeDetail != null  && !notificationAppend) {
                    employeeName = employeeDetail.getEmployeeName();
                } else if (employeeDetail != null && notificationAppend){
                    employeeName = "";
                }else{
                    employeeName = empName;
                }

                postDataTask.execute(saveOrderPostData("", "", selected_customer), serverUrl + "/restaurant/saveTableDataTempMobile?currTableName=" + tableName.replace(" ", "%20") + "&currTableId=" + tableId + "&prevTableName=" + tableName.replace(" ", "%20") + "&prevTableId=" + tableId + "&employeeName=" + employeeName.replace(" ", "%20") + "&customerId=" + customerId+"&orderNo="+notificationOrderNo+"&agentId="+notificationagentId, "");
                // postDataTask.execute(saveOrderPostData("", "", selected_customer), serverUrl + "/restaurant/saveTableDataTemp?currTableName=" + tableName.replace(" ", "%20") + "&currTableId=" + tableId + "&prevTableName=" + tableName.replace(" ", "%20") + "&prevTableId=" + tableId + "&employeeName=" + employeeName.replace(" ", "%20") + "&customerId=" + customerId+"&orderNo="+orderNo, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }


    }

    private void callAllCancelOrders() {
        List<SelectedItemsList>  posTempOrders = RealmManager.createRestaurentDao().getRestuarentTempraryOrderItemList(tableData);
        if (posTempOrders != null && posTempOrders.size() > 0) {
            if (serverUrl != null) {
                if (isInternetPresent) {
                    final String saveData = clearKOtCancelOrderPostData("", "", selected_customer);
                    if (saveData!=null && itemListKot != null && itemListKot.size() > 0) {
                        updateKotItems();
                         postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                if (result != null) {
                                    Gson gson = new Gson();
                                    if (result != null) {
                                        if (result.equals("invalid")) {
                                            UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                            Intent intent = new Intent(mActivity, Activity_Login.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            try {
                                                RestraCheckoutData saveResponseData = gson.fromJson(result.toString(), RestraCheckoutData.class);
                                                if (saveResponseData != null) {
                                                    Log.e("PlaceOrder", result.toString());
                                                    if (bluetoothStatus) {
                                                        callPrinservices(saveResponseData);
                                                        RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                                                        RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                                                        RealmManager.createRestaurentDao().deletServerRealmTempCancelOrder(tableData);
                                                        checkTableCartList();
                                                        tvTableData.setText("");

                                                    } else if (!bluetoothStatus){
                                                        Toast.makeText(mActivity, "Table Updated to server", Toast.LENGTH_SHORT).show();

                                                        RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                                                        RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                                                        RealmManager.createRestaurentDao().deletServerRealmTempCancelOrder(tableData);
                                                        checkTableCartList();
                                                        tvTableData.setText("");
                                                    }

                                                }
                                            } catch (Exception e) {
                                                Log.e("table", e.toString());
                                            }

                                        }
                                    }
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                                }
                            }
                        }, false);
                        if (tableData != null) {
                            tableName = tableData.getTableName();
                            tableId = tableData.getTableValue();
                        }

                        String employeeName = "";
                        if (employeeDetail != null) {
                            employeeName = employeeDetail.getEmployeeName();
                        } else {
                            employeeName = empName;
                        }

                        postDataTask.execute(saveData, serverUrl + "/hipos/mobile/placeOrder", "");
                    } else {
                        Toast.makeText(mActivity, "No items change in order.", Toast.LENGTH_SHORT).show();
                    }


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

    private void callSaveOrder() {
        String singlePrinter = sharedPreference.getData(Constant.SINGLE_PRINTER);
        String multiPrinter = sharedPreference.getData(Constant.MULTIPLE_PRINTER);

        if (singlePrinter!=null && singlePrinter.equals("singlePrinter")) {
            callSinglePrinter();

        }else if (multiPrinter!=null && multiPrinter.equals("multiplePrinter")){
            callMultiPrinter();
        }else{
            callServerKotPrint();
        }
        //Toast.makeText(mActivity, ""+posTableOrderCreator.getItems().size(), Toast.LENGTH_SHORT).show();

    }

    private void callServerKotPrint() {

        posCartTableItems = RealmManager.createRestaurentDao().getRestuarentAllOrderItemList(tableData);
        List<SelectedItemsList>  posTempOrders = RealmManager.createRestaurentDao().getRestuarentTempraryOrderItemList(tableData);
        if (posCartTableItems != null && posCartTableItems.size() > 0 || posCreator != null && posCreator.getItems().size() > 0 || posTempOrders != null && posTempOrders.size() > 0) {
            saveSingleKotTableSelected(tableData);
        } else {
            UtilView.showToast(mActivity, "Please select some orders with table.");
        }
    }

    private void callSinglePrinter() {
        try {

            String kotaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSBILL);
            String printerTypekot1 = sharedPreference.getData(Constant.BLUETOOTHTYPEBILL);
            if (printerTypekot1!=null && printerTypekot1.equals("2-inch")){
                printerType = "2-inch";
            }else {
                printerType = "3-inch";
            }
            Log.e("kotaddress",kotaddress);
            device = mBluetoothAdapter.getRemoteDevice(kotaddress);
            BLUETOOTH_PRINTER.start();
            BLUETOOTH_PRINTER.connect(device);
        }catch (Exception e){
            Log.e("kotprinter excption",e.toString());
        }


        posCartTableItems = RealmManager.createRestaurentDao().getRestuarentAllOrderItemList(tableData);
        List<SelectedItemsList>  posTempOrders = RealmManager.createRestaurentDao().getRestuarentTempraryOrderItemList(tableData);
        if (posCartTableItems != null && posCartTableItems.size() > 0 || posCreator != null && posCreator.getItems().size() > 0 || posTempOrders != null && posTempOrders.size() > 0) {
            saveSingleKotTableSelected(tableData);
        } else {
            UtilView.showToast(mActivity, "Please select some orders with table.");
        }
    }

    private void saveSingleKotTableSelected(SubRow tableData) {
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                 postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Log.e("resultTable", result.toString());
                            Gson gson = new Gson();
                                if (result.equals("invalid")) {
                                    UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(mActivity, Activity_Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    try {

                                        alertDlgBuilder = new android.app.AlertDialog.Builder(mActivity);
                                        alertDlgBuilder.setTitle(getResources().getString(R.string.alert_title_connect));
                                        alertDlgBuilder.setMessage(getResources().getString(R.string.alert_message_connect));
                                        View mView = getLayoutInflater().inflate(R.layout.dialog_reprint, null);
                                        CheckBox mCheckBox = mView.findViewById(R.id.checkBox);
                                        alertDlgBuilder.setView(mView);
                                        alertDlgBuilder.setNegativeButton(getResources().getString(R.string.alert_btn_negative), new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        tableSelected = true;
                                                        if (tableSelected) {
                                                            tablesdata.clear();
                                                            bluetoothStatus = false;
                                                            tableOrderSingleKOTCheckout();

                                                        }
                                                    }
                                                }
                                        );
                                        alertDlgBuilder.setPositiveButton(getResources().getString(R.string.alert_btn_positive), new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        if (BLUETOOTH_PRINTER != null) {
                                                            if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                                                                UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());

                                                                if (BLUETOOTH_PRINTER.getState() == 3) {

                                                                    tableSelected = true;
                                                                    if (tableSelected) {
                                                                        tablesdata.clear();
                                                                        bluetoothStatus = true;
                                                                        tableOrderSingleKOTCheckout();

                                                                    }


                                                                } else if (BLUETOOTH_PRINTER.getState() == 2) {


                                                                } else {
                                                                    UtilView.showToast(mActivity, "Please setup the printer connection first3.");
                                                                }

                                                            } else {
                                                                UtilView.showToast(mActivity, "Please setup the printer connection first2.");
                                                            }
                                                        }else {
                                                            UtilView.showToast(mActivity, "Please setup the printer connection first1.");
                                                        }
                                                    }
                                                }
                                        );
                                        alertDlgBuilder.create().show();

                                        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                if(compoundButton.isChecked()){
                                                    reprintKot =true;
                                                }else{
                                                    reprintKot = false;
                                                }
                                            }
                                        });


                                    } catch (Exception e) {
                                        UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                    }

                                }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                        }
                    }
                }, false);

                if (tableData != null) {
                    tableName = tableData.getTableName();
                    tableId = tableData.getTableId();
                }

                if (tableData != null) {
                    tableName = tableData.getTableName();
                    tableId = tableData.getTableId();
                }

                String employeeName = "";
                if (employeeDetail != null  && !notificationAppend) {
                    employeeName = employeeDetail.getEmployeeName();
                } else if (employeeDetail != null && notificationAppend){
                    employeeName = "";
                }else{
                    employeeName = empName;
                }

                postDataTask.execute(saveOrderPostData("", "", selected_customer), serverUrl + "/restaurant/saveTableDataTempMobile?currTableName=" + tableName.replace(" ", "%20") + "&currTableId=" + tableId + "&prevTableName=" + tableName.replace(" ", "%20") + "&prevTableId=" + tableId + "&employeeName=" + employeeName.replace(" ", "%20") + "&customerId=" + customerId+"&orderNo="+notificationOrderNo+"&agentId="+notificationagentId, "");
                // postDataTask.execute(saveOrderPostData("", "", selected_customer), serverUrl + "/restaurant/saveTableDataTemp?currTableName=" + tableName.replace(" ", "%20") + "&currTableId=" + tableId + "&prevTableName=" + tableName.replace(" ", "%20") + "&prevTableId=" + tableId + "&employeeName=" + employeeName.replace(" ", "%20") + "&customerId=" + customerId+"&orderNo="+orderNo, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void tableOrderSingleKOTCheckout() {
        for (int i = 0; i < posCartTableItems.size(); i++) {
            RealmManager.createRestaurentDao().updateRestaurentKotTableData(posCartTableItems.get(i));
        }
        posCartTableItems = RealmManager.createRestaurentDao().getRestuarentAllOrderItemList(tableData);
        Log.e("posCartTableItemsSize", String.valueOf(posCartTableItems.size()));
        List<SelectedItemsList>  posTempOrders = RealmManager.createRestaurentDao().getRestuarentTempraryOrderItemList(tableData);

        if (posCartTableItems != null && posCartTableItems.size() > 0 || posCreator != null && posCreator.getItems().size() > 0 || posTempOrders != null && posTempOrders.size() > 0) {
            if (serverUrl != null) {
                if (isInternetPresent) {
                    final String saveData = saveKOtOrderPostData("", "", selected_customer);
                    if (saveData!=null && itemListKot != null && itemListKot.size() > 0) {
                        updateKotItems();
                        // UtilView.showProgessBar(mActivity, progressBar);
                         postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                //  UtilView.hideProgessBar(mActivity, progressBar);
                                if (result != null) {
                                    Gson gson = new Gson();
                                    // HideUtil.init(RestuarantActivity.this);
                                    if (result != null) {
                                        if (result.equals("invalid")) {
                                            UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                            Intent intent = new Intent(mActivity, Activity_Login.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Log.e("PlaceOrder", result.toString());
                                            try {
                                                RestraCheckoutData saveResponseData = gson.fromJson(result.toString(), RestraCheckoutData.class);
                                                if (saveResponseData != null) {
                                                    Log.e("PlaceOrder1", result.toString());
                                                    if (bluetoothStatus) {

                                                        if (reprintKot){
                                                            callPrinservices(saveResponseData);
                                                            reprintKot =false;
                                                            if (!reprintKot){
                                                                Toast.makeText(mActivity, "Order Saved", Toast.LENGTH_SHORT).show();
                                                                callPrinservices(saveResponseData);
                                                                RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                                                                RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                                                                RealmManager.createRestaurentDao().deletServerRealmTempCancelOrder(tableData);
                                                                checkTableCartList();
                                                                tvTableData.setText("");
                                                            }

                                                        }else {

                                                            callPrinservices(saveResponseData);

                                                            RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                                                            RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                                                            RealmManager.createRestaurentDao().deletServerRealmTempCancelOrder(tableData);
                                                            checkTableCartList();
                                                            tvTableData.setText("");
                                                        }

                                                    } else if (!bluetoothStatus){
                                                        Toast.makeText(mActivity, "Table Updated to server", Toast.LENGTH_SHORT).show();

                                                        RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                                                        RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                                                        RealmManager.createRestaurentDao().deletServerRealmTempCancelOrder(tableData);
                                                        checkTableCartList();
                                                        tvTableData.setText("");
                                                    }

                                                }
                                            } catch (Exception e) {
                                                Log.e("table", e.toString());
                                            }

                                        }
                                    }
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                                }
                            }
                        }, false);
                        if (tableData != null) {
                            tableName = tableData.getTableName();
                            tableId = tableData.getTableValue();
                        }

                        String employeeName = "";
                        if (employeeDetail != null) {
                            employeeName = employeeDetail.getEmployeeName();
                        } else {
                            employeeName = empName;
                        }

                        postDataTask.execute(saveData, serverUrl + "/hipos/mobile/placeOrder", "");
                    } else {
                        Toast.makeText(mActivity, "No items change in order.", Toast.LENGTH_SHORT).show();
                    }


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

    private void callMultiPrinter() {
        try {
            BLUETOOTH_PRINTER.stop();
            String kotaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSKOT1);
            String printerTypekot1 = sharedPreference.getData(Constant.BLUETOOTHTYPEKOT1);
            if (printerTypekot1!=null && printerTypekot1.equals("kot1-2-inch")){
                printerType = "2-inch";
            }else {
                printerType = "3-inch";
            }
            Log.e("kotaddress",kotaddress);
            device = mBluetoothAdapter.getRemoteDevice(kotaddress);
            BLUETOOTH_PRINTER.start();
            BLUETOOTH_PRINTER.connect(device);
        }catch (Exception e){
            Log.e("kotprinter excption",e.toString());
        }


        posCartTableItems = RealmManager.createRestaurentDao().getRestuarentAllOrderItemList(tableData);
        List<SelectedItemsList>  posTempOrders = RealmManager.createRestaurentDao().getRestuarentTempraryOrderItemList(tableData);
        if (posCartTableItems != null && posCartTableItems.size() > 0 || posCreator != null && posCreator.getItems().size() > 0 || posTempOrders != null && posTempOrders.size() > 0) {
            saveKotTableSelected(tableData);
        } else {
            UtilView.showToast(mActivity, "Please select some orders with table.");
        }
    }

    private void callKOt2ndPrint(RestraCheckoutData saveResponseData) {
            try {
                if (saveResponseData != null) {
                    if (bluetoothStatus) {
                        callPrinservices(saveResponseData);
                        Toast.makeText(mActivity, "Order Saved", Toast.LENGTH_SHORT).show();
                        RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                        RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                        RealmManager.createRestaurentDao().deletServerRealmTempCancelOrder(tableData);
                        checkTableCartList();
                        dualKotPrint =true;
                        tvTableData.setText("");
                    } else {
                        Toast.makeText(mActivity, "Table Updated to server", Toast.LENGTH_SHORT).show();
                        dualKotPrint =true;
                        RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                        RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                        RealmManager.createRestaurentDao().deletServerRealmTempCancelOrder(tableData);
                        checkTableCartList();
                        tvTableData.setText("");

                    }

                }



            }catch (Exception e){
                Log.e("kotprinter excption",e.toString());
            }
    }

    private void callPrinterConfiguration() {
        // Get device's Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // If the adapter is null, then Bluetooth is not available in your device
        if (mBluetoothAdapter == null) {
            UtilView.showToast(mActivity, "Bluetooth is not available");
        }
        alertDlgBuilder = new android.app.AlertDialog.Builder(mActivity);
        alertDlgBuilder.setTitle(getResources().getString(R.string.alert_title_connect));
        alertDlgBuilder.setMessage(getResources().getString(R.string.alert_message_connect));
        alertDlgBuilder.setNegativeButton(getResources().getString(R.string.alert_btn_negativeKotPrinter), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mBluetoothAdapter != null) {
                            if (!mBluetoothAdapter.isEnabled()) {
                                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                startActivityForResult(enableIntent, REQUEST_ENABLE_BT);

                            } else {
                                if (BLUETOOTH_PRINTER == null) {
                                    //BLUETOOTH_PRINTER.stop();
                                    sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSKOT1);
                                    sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSKOT2);
                                    initializeBluetoothDevice();
                                    sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSKOT1);
                                    sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSKOT2);
                                    sharedPreference.saveData(Constant.MULTIPLE_PRINTER, "multiplePrinter");
                                    sharedPreference.saveData(Constant.SINGLE_PRINTER, "");
                                    Intent intentC = new Intent(mActivity, Activity_printer_configurations.class);
                                    startActivityForResult(intentC, Constant.RESQUSTCODE_PRINETER_CONFIGURATIONS);


                                } else {

                                    if (BLUETOOTH_PRINTER.IsNoConnection()) {
                                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSKOT1);
                                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSKOT2);
                                        sharedPreference.saveData(Constant.MULTIPLE_PRINTER, "multiplePrinter");
                                        sharedPreference.saveData(Constant.SINGLE_PRINTER, "");
                                        Intent intentC = new Intent(mActivity, Activity_printer_configurations.class);
                                        startActivityForResult(intentC, Constant.RESQUSTCODE_PRINETER_CONFIGURATIONS);

                                    } else if (!BLUETOOTH_PRINTER.IsNoConnection()){
                                        BLUETOOTH_PRINTER.stop();
                                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSKOT1);
                                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSKOT2);
                                        sharedPreference.saveData(Constant.MULTIPLE_PRINTER, "multiplePrinter");
                                        sharedPreference.saveData(Constant.SINGLE_PRINTER, "");
                                        Intent intentC = new Intent(mActivity, Activity_printer_configurations.class);
                                        startActivityForResult(intentC, Constant.RESQUSTCODE_PRINETER_CONFIGURATIONS);

                                    }
                                }

                            }
                        } else {
                            tvPrinterStatus.setText(R.string.bluettoth_doesnt);
                        }
                    }
                }
        );
        alertDlgBuilder.setPositiveButton(getResources().getString(R.string.alert_btn_positiveKotPrinter), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (mBluetoothAdapter != null) {
                    if (!mBluetoothAdapter.isEnabled()) {
                        Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableIntent, REQUEST_ENABLE_BT);

                    } else {
                        if (BLUETOOTH_PRINTER == null) {
                            initializeBluetoothDevice();
                            sharedPreference.saveData(Constant.SINGLE_PRINTER, "singlePrinter");
                            sharedPreference.saveData(Constant.MULTIPLE_PRINTER, "");
                            Intent intentC = new Intent(mActivity, Activity_printer_configurations.class);
                            startActivityForResult(intentC, Constant.RESQUSTCODE_PRINETER_CONFIGURATIONS);


                        } else {
                            if (BLUETOOTH_PRINTER.IsNoConnection()) {
                                sharedPreference.saveData(Constant.SINGLE_PRINTER, "singlePrinter");
                                sharedPreference.saveData(Constant.MULTIPLE_PRINTER, "");
                                Intent intentC = new Intent(mActivity, Activity_printer_configurations.class);
                                startActivityForResult(intentC, Constant.RESQUSTCODE_PRINETER_CONFIGURATIONS);

                            } else if (!BLUETOOTH_PRINTER.IsNoConnection()){
                                BLUETOOTH_PRINTER.stop();
                                sharedPreference.saveData(Constant.SINGLE_PRINTER, "singlePrinter");
                                sharedPreference.saveData(Constant.MULTIPLE_PRINTER, "");
                                Intent intentC = new Intent(mActivity, Activity_printer_configurations.class);
                                startActivityForResult(intentC, Constant.RESQUSTCODE_PRINETER_CONFIGURATIONS);

                            }
                        }

                    }
                } else {
                    //tvPrinterStatus.setText(R.string.bluettoth_doesnt);
                }


         }
       });
        alertDlgBuilder.show();

    }

    private void callNotificationListItems() {
        dialog.setContentView(R.layout.dialog_restaurent_notifications);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        list_view_notifications = dialog.findViewById(R.id.id_listview_notifications);
        TextView notAvailable= dialog.findViewById(R.id.id_notification_text);


        if (dialog != null) {
            dialog.show();
        }

        if (listMessages!=null && listMessages.size()>0){

            list_view_notifications.setVisibility(View.VISIBLE);
            notAvailable.setVisibility(View.GONE);
            messageAdapter = new MessageAdapter(mActivity, listMessages);
            list_view_notifications.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();

            list_view_notifications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (listMessages.get(position) != null) {
                        confirmationDailog(listMessages.get(position), position, dialog);
                    }

                }
            });
        }else {
            list_view_notifications.setVisibility(View.GONE);
            notAvailable.setVisibility(View.VISIBLE);
        }




        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    private void tableOrderKOTCheckout() {
        for (int i = 0; i < posCartTableItems.size(); i++) {
            RealmManager.createRestaurentDao().updateRestaurentKotTableData(posCartTableItems.get(i));
        }
        posCartTableItems = RealmManager.createRestaurentDao().getRestuarentAllOrderItemList(tableData);
        Log.e("posCartTableItemsSize", String.valueOf(posCartTableItems.size()));
        List<SelectedItemsList>  posTempOrders = RealmManager.createRestaurentDao().getRestuarentTempraryOrderItemList(tableData);

        if (posCartTableItems != null && posCartTableItems.size() > 0 || posCreator != null && posCreator.getItems().size() > 0 || posTempOrders != null && posTempOrders.size() > 0) {

            callkot1Print();

        } else {
            UtilView.showToast(mActivity, getResources().getString(R.string.cartempty));
        }
    }

    private void callkot1Print() {

        if (serverUrl != null) {
            if (isInternetPresent) {
                final String saveData = saveKOtOrderPostData("", "", selected_customer);
                if (saveData!=null && itemListKot != null && itemListKot.size() > 0) {
                    updateKotItems();
                    // UtilView.showProgessBar(mActivity, progressBar);
                     postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            //  UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                Gson gson = new Gson();
                               // HideUtil.init(RestuarantActivity.this);
                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(mActivity, Activity_Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Log.e("PlaceOrder", result.toString());
                                        try {
                                             saveResponseDataa = gson.fromJson(result.toString(), RestraCheckoutData.class);
                                            if (saveResponseDataa != null) {
                                                if (!saveResponseDataa.getSelectedItemsList().isEmpty()) {
                                                        if (bluetoothStatus) {
                                                            callPrinservices(saveResponseDataa);
                                                            checkTableCartList();
                                                            flagKot =true;
                                                            bluetoothStatus =false;
                                                            //Toast.makeText(mActivity, "Order Saved", Toast.LENGTH_SHORT).show();
                                                            BLUETOOTH_PRINTER.stop();

                                                            String billaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSKOT2);

                                                            String printerTypekot2 = sharedPreference.getData(Constant.BLUETOOTHTYPEKOT2);
                                                            if (printerTypekot2!=null && printerTypekot2.equals("kot2-2-inch")){
                                                              printerType = "2-inch";
                                                            }else {
                                                              printerType = "3-inch";
                                                            }


                                                            device = mBluetoothAdapter.getRemoteDevice(billaddress);
                                                            // Attempt to connect to the device
                                                            BLUETOOTH_PRINTER.start();
                                                            BLUETOOTH_PRINTER.connect(device);
                                                        }
                                                      if (!bluetoothStatus) {
                                                        if (flagKot){
                                                            new Handler().postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    try {
                                                                        initializeBluetoothDevice();
                                                                        bluetoothStatus =true;
                                                                        if (BLUETOOTH_PRINTER != null) {
                                                                            if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                                                                                UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());

                                                                                if (BLUETOOTH_PRINTER.getState() == 3) {
                                                                                    Log.e("bill connection", "connected");
                                                                                    callKOt2ndPrint(saveResponseDataa);
                                                                                }
                                                                            }
                                                                        }
                                                                    }catch (Exception e){
                                                                        Log.e("kotprinter excption",e.toString());
                                                                    }

                                                                }
                                                            }, 4000);

                                                            dualKotPrint =true;
                                                        }
                                                    }

                                                } else {
                                                    Toast.makeText(mActivity, "No items change in order.", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        } catch (Exception e) {
                                            Log.e("table", e.toString());
                                        }

                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);
                    if (tableData != null) {
                        tableName = tableData.getTableName();
                        tableId = tableData.getTableValue();
                    }

                    String employeeName = "";
                    if (employeeDetail != null) {
                        employeeName = employeeDetail.getEmployeeName();
                    } else {
                        employeeName = empName;
                    }

                    postDataTask.execute(saveData, serverUrl + "/hipos/mobile/placeOrder", "");
                } else {
                    Toast.makeText(mActivity, "No items change in order.", Toast.LENGTH_SHORT).show();
                }


            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void updateKotItems() {
        boolean cancelOrder=false;
        if (posCartTableItems != null && posCartTableItems.size() > 0) {
            for (int i = 0; i < posCartTableItems.size(); i++) {
                RealmManager.createRestaurentDao().updateRestaurentKotTableStatus(posCartTableItems.get(i));
                RealmManager.createRestaurentDao().updateRestaurentKotTableData(posCartTableItems.get(i));
                RealmManager.createRestaurentDao().saveRestaurentTempItemDetails(DataGenerator.generateRestaTempSelectItem(posCartTableItems.get(i), tableData), posCartTableItems.get(i).getItemCode(), tableData);
                cancelOrder = true;
            }
            if (cancelOrder){
                RealmManager.createRestaurentDao().deletRealmTempOrderDataAllTable();

            }
        }
        RealmManager.createRestaurentDao().deletRealmTempOrderDataAllTable();
    }


    private String saveOrderPostData(String paymentType, String opertaion, Customer customer) {
        RestraCheckoutData data = new RestraCheckoutData();
        List<RestraCheckoutItem> itemList = new ArrayList<>();
        double totalTaxAmt = 0;
        double totalAmt = 0;
        Long qty = 0l, tempQty = 0l;
        posCartTableItems = RealmManager.createRestaurentDao().getRestuarentItemList(tableData);

        if (posCartTableItems != null && posCartTableItems.size() > 0) {

            for (int i = 0; i < posCartTableItems.size(); i++) {
                RestraCheckoutItem item = new RestraCheckoutItem();
                item.setItemCode(posCartTableItems.get(i).getItemCode());
                item.setItemId(posCartTableItems.get(i).getItemId());
                item.setItemName(posCartTableItems.get(i).getItemName());
                item.setItemDesc(posCartTableItems.get(i).getItemDesc());
                item.setItemDescription(posCartTableItems.get(i).getItemDesc());
                item.setQty(posCartTableItems.get(i).getItemQuantity());
                item.setUnitPrice(posCartTableItems.get(i).getUnitPrice());
                item.setUnitPriceIn(posCartTableItems.get(i).getUnitPrice());
                item.setGstItemTax(posCartTableItems.get(i).getGstTaxAmt());
                item.setAmtexclusivetax(posCartTableItems.get(i).getItemTotalAmountExTax());
                item.setAmtinclusivetax(posCartTableItems.get(i).getItemTotalAmountInTax());
                item.setGstTaxPercantage(posCartTableItems.get(i).getGstTaxPercantage());
                item.setItemCategoryId(posCartTableItems.get(i).getItemCategoryId());
                item.setItemCategoryName(posCartTableItems.get(i).getItemCategoryName());
                item.setInclusiveJSON(posCartTableItems.get(i).getInclusiveJSON());
                item.setTaxId(posCartTableItems.get(i).getTaxId());
                item.setTaxid(posCartTableItems.get(i).getOutputTaxId());
                item.setInputTaxId(posCartTableItems.get(i).getInputTaxId());
                item.setOutputTaxId(posCartTableItems.get(i).getOutputTaxId());
                item.setItemTypeId(posCartTableItems.get(i).getItemTypeId());
                item.setItemTypeName(posCartTableItems.get(i).getItemTypeName());
                item.setType(posCartTableItems.get(i).getType());
                item.setTaxamt(posCartTableItems.get(i).getGstTaxAmt());
                totalTaxAmt += posCartTableItems.get(i).getGstTaxAmt();
                totalAmt += posCartTableItems.get(i).getItemTotalAmount();
                itemList.add(item);
            }
        }
        Gson gson = new Gson();
        return gson.toJson(itemList);
    }

    private String saveKOtOrderPostData(String paymentType, String opertaion, Customer customer) {
        RestraCheckoutData data = new RestraCheckoutData();
        itemListKot = new ArrayList<>();
        double totalTaxAmt = 0;
        double totalAmt = 0;

        try {
            if (tableData!=null){
                //itemListKot.clear();
                Log.e("tabledId121", tableData.getTableValue());
                Log.e("tabledIdfloor121", tableData.getFloorId());
                //posCartTableItems.clear();
                posCartTableItems = RealmManager.createRestaurentDao().getRestuarentItemList(tableData);
                Long qty = 0l, tempQty = 0l;
                if (posCartTableItems != null && posCartTableItems.size() > 0) {

                    for (int i = 0; i < posCartTableItems.size(); i++) {
                        RestraCheckoutItem item = new RestraCheckoutItem();
                        item.setItemCode(posCartTableItems.get(i).getItemCode());
                        item.setItemId(posCartTableItems.get(i).getItemId());
                        item.setItemName(posCartTableItems.get(i).getItemName());
                        item.setItemDesc(posCartTableItems.get(i).getItemDesc());
                        item.setItemDescription(posCartTableItems.get(i).getItemDesc());
                        item.setUnitPrice(posCartTableItems.get(i).getUnitPrice());
                        item.setUnitPriceIn(posCartTableItems.get(i).getUnitPrice());
                        item.setGstItemTax(posCartTableItems.get(i).getGstTaxAmt());
                        item.setAmtexclusivetax(posCartTableItems.get(i).getItemTotalAmountExTax());
                        item.setAmtinclusivetax(posCartTableItems.get(i).getItemTotalAmountInTax());
                        item.setGstTaxPercantage(posCartTableItems.get(i).getGstTaxPercantage());
                        item.setItemCategoryId(posCartTableItems.get(i).getItemCategoryId());
                        item.setItemCategoryName(posCartTableItems.get(i).getItemCategoryName());
                        item.setInclusiveJSON(posCartTableItems.get(i).getInclusiveJSON());
                        item.setTaxId(posCartTableItems.get(i).getTaxId());
                        item.setTaxid(posCartTableItems.get(i).getOutputTaxId());
                        item.setInputTaxId(posCartTableItems.get(i).getInputTaxId());
                        item.setOutputTaxId(posCartTableItems.get(i).getOutputTaxId());
                        item.setItemTypeId(posCartTableItems.get(i).getItemTypeId());
                        item.setItemTypeName(posCartTableItems.get(i).getItemTypeName());
                        item.setType(posCartTableItems.get(i).getType());
                        item.setTaxamt(posCartTableItems.get(i).getGstTaxAmt());
                        totalTaxAmt += posCartTableItems.get(i).getGstTaxAmt();
                        totalAmt += posCartTableItems.get(i).getItemTotalAmount();


                        RealmTemp_SelectItemList restuarentTempItemList = RealmManager.createRestaurentDao().getRestuarentTempItemList(posCartTableItems.get(i));
                        if (restuarentTempItemList != null) {
                            qty = posCartTableItems.get(i).getItemQuantity();
                            Log.e("tempQtyNormal", String.valueOf(tempQty));
                            tempQty = restuarentTempItemList.getQty();
                            Log.e("tempQtyTemp", String.valueOf(tempQty));
                            if (posCartTableItems.get(i).getItemCode().equals(restuarentTempItemList.getItemCode()) && qty == tempQty) {
                                item.setQty(0);
                            } else if (qty > tempQty) {
                                item.setQty(qty - tempQty);
                            } else if (qty < tempQty) {
                                item.setQty(tempQty - qty);
                                item.setType("Cancel");
                                Log.e("itemListKotsize1", String.valueOf(itemListKot.size()));
                            }
                            Log.e("restuarentTempItemList2", String.valueOf(restuarentTempItemList.getQty()));
                            Log.e("restuarentTempItemList2", String.valueOf(posCartTableItems.get(i).getItemQuantity()));

                        } else {
                            qty = posCartTableItems.get(i).getItemQuantity();
                            if (qty != tempQty && qty > tempQty) {
                                item.setQty(qty - tempQty);
                            }else {
                                item.setQty(qty);
                            }
                            Log.e("tempQtyNormal", String.valueOf(tempQty));
                            Log.e("restuarentTempItemList1", String.valueOf(tempQty));
                            Log.e("restuarentTempItemList1", String.valueOf(posCartTableItems.get(i).getItemQuantity()));

                        }

                        try {

                            if (item.getQty() != 0) {
                                itemListKot.add(item);
                            }


                        } catch (Exception e) {

                        }

                    }
                }

                List<SelectedItemsList>  posCartTableCancelOrders= RealmManager.createRestaurentDao().getRestuarentTempCancelOrders(tableData);

                Log.e("posCartTableCancelItems", String.valueOf(posCartTableCancelOrders.size()));
                if (posCartTableCancelOrders != null && posCartTableCancelOrders.size() > 0) {

                    for (int k = 0; k < posCartTableCancelOrders.size(); k++) {
                        RestraCheckoutItem item = new RestraCheckoutItem();
                        item.setItemCode(posCartTableCancelOrders.get(k).getItemCode());
                        item.setItemId(posCartTableCancelOrders.get(k).getItemId());
                        item.setItemName(posCartTableCancelOrders.get(k).getItemName());
                        item.setItemDesc(posCartTableCancelOrders.get(k).getItemDesc());
                        item.setQty(posCartTableCancelOrders.get(k).getItemQuantity());
                        item.setItemDescription(posCartTableCancelOrders.get(k).getItemDesc());
                        item.setUnitPrice(posCartTableCancelOrders.get(k).getUnitPrice());
                        item.setGstItemTax(posCartTableCancelOrders.get(k).getGstTaxAmt());
                        item.setAmtexclusivetax(posCartTableCancelOrders.get(k).getItemTotalAmountExTax());
                        item.setAmtinclusivetax(posCartTableCancelOrders.get(k).getItemTotalAmountInTax());
                        item.setGstTaxPercantage(posCartTableCancelOrders.get(k).getGstTaxPercantage());
                        item.setItemCategoryId(posCartTableCancelOrders.get(k).getItemCategoryId());
                        item.setItemCategoryName(posCartTableCancelOrders.get(k).getItemCategoryName());
                        item.setInclusiveJSON(posCartTableCancelOrders.get(k).getInclusiveJSON());
                        item.setTaxId(posCartTableCancelOrders.get(k).getTaxId());
                        item.setTaxid(posCartTableCancelOrders.get(k).getOutputTaxId());
                        item.setInputTaxId(posCartTableCancelOrders.get(k).getInputTaxId());
                        item.setOutputTaxId(posCartTableCancelOrders.get(k).getOutputTaxId());
                        item.setItemTypeId(posCartTableCancelOrders.get(k).getItemTypeId());
                        item.setItemTypeName(posCartTableCancelOrders.get(k).getItemTypeName());
                        item.setType(posCartTableCancelOrders.get(k).getType());
                        itemListKot.add(item);

                    }


                }



                List<MultiCashPaymentList> multiCashPaymentLists = new ArrayList<>();
                MultiCashPaymentList cashPaymentList = new MultiCashPaymentList();
                cashPaymentList.setCashAmt(0.00);
                cashPaymentList.setPaymentType(1L);
                multiCashPaymentLists.add(cashPaymentList);
                cashPayment.setMultiCashPaymentList(multiCashPaymentLists);

                CreditPayment creditPayment = new CreditPayment();
                creditPayment.setCardPaymentList(cardPaymentList);

                VoucherPayment voucherPayment = new VoucherPayment();
                voucherPayment.setMultiVoucherPayments(voucherPaymentList);

                BankPayment bankPayment = new BankPayment();
                bankPayment.setMultiBankPaymentList(bankPaymentList);

                // data.setCashPayment(cashPayment);
                //data.setBankPayment(bankPayment);
                // data.setVoucherPayment(voucherPayment);
                //  data.setCreditPayment(creditPayment);
                data.setMobileOrder(true);
                //data.setTaxType("CGST:SGST/UGST");
                //data.setPaymentType("multiPayment");
                data.setCustomerId(customerId);
                data.setSelectedItemsList(itemListKot);
                //data.setTotalCheckOutamt(totalAmt);
                // data.setTotalTaxAmt(totalTaxAmt);
                // data.setRoundingOffValue("0.00");
                data.setBluetoothStatus(bluetoothStatus);
                data.setCustomerName(customerName);
                data.setCustomerId(customerId);
                String username = sharedPreference.getData(Constant.USERNAME);
                Log.e("usename", username.toString());
                data.setUserId(username);
                if (tableData != null) {

                /*    try {
                        Gson gson = new Gson();

                        tableChairData.put(tableData.getTableName(),"");

                        TableNamesData namesData = new TableNamesData();
                        namesData.setTableName(tableChairData);
                        data.setTableName(namesData);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/

                    data.setTableName(tableData.getTableName());
                    data.setTableVal(String.valueOf(tableData.getTableId()));
                }
                try {

                    if (posCartTableItems!=null && posCartTableItems.size()>0){
                        waiterName= RealmManager.createRestaurentDao().getWaiterName(tableData);
                        if (waiterName!=null && !notificationAppend){
                            data.setWaiterName( waiterName.getWaiterName());

                        }else {
                            data.setWaiterName("");
                        }
                    }else{

                        Realm_TempOrders  waiterName= RealmManager.createRestaurentDao().getWaiterNameTemp(tableData);
                        if (waiterName!=null && !notificationAppend){
                            data.setWaiterName( waiterName.getWaiterName());

                        }else {
                            data.setWaiterName("");
                        }
                    }


                }catch (Exception e){
                    Log.e("Exception ", e.toString());
                }
            }
        }catch (Exception e){

        }

        Gson gson = new Gson();
        return gson.toJson(data);
    }

    private String clearKOtCancelOrderPostData(String paymentType, String opertaion, Customer customer) {
        RestraCheckoutData data = new RestraCheckoutData();
        itemListKot = new ArrayList<>();
        double totalTaxAmt = 0;
        double totalAmt = 0;

        try {
            if (tableData!=null){
                Log.e("cancelTableid", tableData.getTableValue());
                Log.e("cancelTableFloorid", tableData.getFloorId());


                List<SelectedItemsList>  posCartTableCancelOrders= RealmManager.createRestaurentDao().getRestuarentTempCancelOrders(tableData);

                Log.e("posCartTableCancelItems", String.valueOf(posCartTableCancelOrders.size()));
                if (posCartTableCancelOrders != null && posCartTableCancelOrders.size() > 0) {

                    for (int k = 0; k < posCartTableCancelOrders.size(); k++) {
                        RestraCheckoutItem item = new RestraCheckoutItem();
                        item.setItemCode(posCartTableCancelOrders.get(k).getItemCode());
                        item.setItemId(posCartTableCancelOrders.get(k).getItemId());
                        item.setItemName(posCartTableCancelOrders.get(k).getItemName());
                        item.setItemDesc(posCartTableCancelOrders.get(k).getItemDesc());
                        item.setQty(posCartTableCancelOrders.get(k).getItemQuantity());
                        item.setItemDescription(posCartTableCancelOrders.get(k).getItemDesc());
                        item.setUnitPrice(posCartTableCancelOrders.get(k).getUnitPrice());
                        item.setGstItemTax(posCartTableCancelOrders.get(k).getGstTaxAmt());
                        item.setAmtexclusivetax(posCartTableCancelOrders.get(k).getItemTotalAmountExTax());
                        item.setAmtinclusivetax(posCartTableCancelOrders.get(k).getItemTotalAmountInTax());
                        item.setGstTaxPercantage(posCartTableCancelOrders.get(k).getGstTaxPercantage());
                        item.setItemCategoryId(posCartTableCancelOrders.get(k).getItemCategoryId());
                        item.setItemCategoryName(posCartTableCancelOrders.get(k).getItemCategoryName());
                        item.setInclusiveJSON(posCartTableCancelOrders.get(k).getInclusiveJSON());
                        item.setTaxId(posCartTableCancelOrders.get(k).getTaxId());
                        item.setTaxid(posCartTableCancelOrders.get(k).getOutputTaxId());
                        item.setInputTaxId(posCartTableCancelOrders.get(k).getInputTaxId());
                        item.setOutputTaxId(posCartTableCancelOrders.get(k).getOutputTaxId());
                        item.setItemTypeId(posCartTableCancelOrders.get(k).getItemTypeId());
                        item.setItemTypeName(posCartTableCancelOrders.get(k).getItemTypeName());
                        item.setType(posCartTableCancelOrders.get(k).getType());
                        itemListKot.add(item);

                    }


                }



                List<MultiCashPaymentList> multiCashPaymentLists = new ArrayList<>();
                MultiCashPaymentList cashPaymentList = new MultiCashPaymentList();
                cashPaymentList.setCashAmt(0.00);
                cashPaymentList.setPaymentType(1L);
                multiCashPaymentLists.add(cashPaymentList);
                cashPayment.setMultiCashPaymentList(multiCashPaymentLists);

                CreditPayment creditPayment = new CreditPayment();
                creditPayment.setCardPaymentList(cardPaymentList);

                VoucherPayment voucherPayment = new VoucherPayment();
                voucherPayment.setMultiVoucherPayments(voucherPaymentList);

                BankPayment bankPayment = new BankPayment();
                bankPayment.setMultiBankPaymentList(bankPaymentList);

                // data.setCashPayment(cashPayment);
                //data.setBankPayment(bankPayment);
                // data.setVoucherPayment(voucherPayment);
                //  data.setCreditPayment(creditPayment);
                data.setMobileOrder(true);
                //data.setTaxType("CGST:SGST/UGST");
                //data.setPaymentType("multiPayment");
                data.setCustomerId(customerId);
                data.setSelectedItemsList(itemListKot);
                //data.setTotalCheckOutamt(totalAmt);
                // data.setTotalTaxAmt(totalTaxAmt);
                // data.setRoundingOffValue("0.00");
                data.setBluetoothStatus(bluetoothStatus);
                data.setCustomerName(customerName);
                data.setCustomerId(customerId);
                String username = sharedPreference.getData(Constant.USERNAME);
                Log.e("usename", username.toString());
                data.setUserId(username);
                if (tableData != null) {
                    data.setTableName(tableData.getTableName());
                    data.setTableVal(String.valueOf(tableData.getTableId()));
                }
                try {

                    if (posCartTableItems!=null && posCartTableItems.size()>0){
                        waiterName= RealmManager.createRestaurentDao().getWaiterName(tableData);
                        if (waiterName!=null && !notificationAppend){
                            data.setWaiterName( waiterName.getWaiterName());

                        }else {
                            data.setWaiterName("");
                        }
                    }else{

                        Realm_TempOrders  waiterName= RealmManager.createRestaurentDao().getWaiterNameTemp(tableData);
                        if (waiterName!=null && !notificationAppend){
                            data.setWaiterName( waiterName.getWaiterName());

                        }else {
                            data.setWaiterName("");
                        }
                    }


                }catch (Exception e){
                    Log.e("Exception ", e.toString());
                }
            }
        }catch (Exception e){

        }

        Gson gson = new Gson();
        return gson.toJson(data);
    }

    private void restuarentSelectTable() {
        Intent intent1 = new Intent(mActivity, Activity_RestuarentTable.class);
        intent1.putExtra("selectTableOrder", orderPalced);
        startActivityForResult(intent1, Constant.RESQUSTCODE_RESTUARENTTABLE);

    }


    private void callSplitBill() {
        fabMenus.setVisibility(View.GONE);
        fabMenus.close(true);
        String waitername="";
        if (tableData != null) {
            try {
                waiterName = RealmManager.createRestaurentDao().getWaiterName(tableData);
                waitername = waiterName.getWaiterName();
                employeeDetail.setEmployeeName(waitername);
            }catch (Exception e){

            }

        }

        boolean flag = false;
        posCartTableItems = RealmManager.createRestaurentDao().getRestuarentItemList(tableData);
       if (posCartTableItems != null && posCartTableItems.size() > 0) {
           if(!flag){
               getCheckoutDataForTable();
               for (int i = 0; i < posCartTableItems.size(); i++) {
                   String itemCode = posCartTableItems.get(i).getItemCode();
                   RealmManager.createRestaurentDao().saveRestaurentSplitItem(DataGenerator.generateRestaSplitOrder(posCartTableItems.get(i),tableData,itemCode),itemCode,tableData);

               }

               flag = true;
           }
            if (flag){
               //sharedPreference.saveData("splitBill","splitBillData");
                Intent intent = new Intent(mActivity, Activity_SplitBill.class);
                intent.putExtra("customerId", customerId);
                intent.putExtra("tableData", tableData);
                intent.putExtra("employeeDetail", employeeDetail);
                mActivity.startActivityForResult(intent, Constant.RESQUSTCODE_SPLITTBILL);
            }

        } else {
            UtilView.showToast(mActivity, "Please add atleast one item in cart.");
        }
    }

    private void callDialyReport() {
        fabMenus.setVisibility(View.GONE);
        fabMenus.close(true);
        Intent intent = new Intent(mActivity, Activity_PrintList_DailyReport.class);
        intent.putExtra("DailyReport", "DailyReport");
        mActivity.startActivityForResult(intent, Constant.REQUESTCODE_PRINTLISTSALESINVOICE);

    }



    private void callPrinservices(final RestraCheckoutData saveResponseData) {
        if (saveResponseData != null) {
            SharedPreference sharedPreference = SharedPreference.getInstance(mActivity);
            if (sharedPreference != null) {

                String applicationDataJson = sharedPreference.getData(Constant.COMPANYDATA);
                String imageLogoUrl;

                if (applicationDataJson != null) {
                    Gson gson = new Gson();
                    String serverUrl = UtilView.getUrl(mActivity);

                    CompanyData companyData = gson.fromJson(applicationDataJson, CompanyData.class);
                    if (companyData != null) {
                        if (companyData.getFileName() != null) {
                            imageLogoUrl = serverUrl + companyData.getFileName();
                            GetCompanyLogoTask task = new GetCompanyLogoTask(mActivity, new AsyncTaskCompleteListener1<Bitmap>() {
                                @Override
                                public void onTaskComplete(Bitmap bmp) {
                                    makePrint(bmp, saveResponseData, companyData);
                                }
                            }, false);
                            task.execute(imageLogoUrl, "");

                        } else {
                            makePrint(null, saveResponseData, companyData);
                        }
                    } else {
                        makePrint(null, saveResponseData, companyData);
                    }
                } else {
                    makePrint(null, saveResponseData, companyData);
                }
            } else {
                makePrint(null, saveResponseData, companyData);
            }
            //     InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);


        }
    }


    private void makePrint(Bitmap bmp, RestraCheckoutData saveResponseData, CompanyData companyData) {
        boolean printStatus = false;
        boolean printCancelStatus = false;
        UtilView.showToast(mActivity, "Printed Successfully");
        Gson gson = new Gson();
        String responseData =  gson.toJson(saveResponseData);
        RestraCheckoutData restraCheckoutData = gson.fromJson(responseData, RestraCheckoutData.class);
        List<RestraCheckoutItem> orderItemsList = new ArrayList<>();
        List<RestraCheckoutItem> cancelItemsList = new ArrayList<>();
        orderItemsList.clear();
        cancelItemsList.clear();
        if (restraCheckoutData!=null){
            if (restraCheckoutData.getSelectedItemsList()!=null && restraCheckoutData.getSelectedItemsList().size()>0){
                for (int i=0;i<restraCheckoutData.getSelectedItemsList().size();i++){
                    if (restraCheckoutData.getSelectedItemsList().get(i).getType()!=null && restraCheckoutData.getSelectedItemsList().get(i).getType().equals("Order")){
                        orderItemsList.add(restraCheckoutData.getSelectedItemsList().get(i));
                        Log.e("OrderItemsListsize", String.valueOf(orderItemsList.size()));

                    }else if (restraCheckoutData.getSelectedItemsList().get(i).getType()!=null && restraCheckoutData.getSelectedItemsList().get(i).getType().equals("Cancel")){
                        cancelItemsList.add(restraCheckoutData.getSelectedItemsList().get(i));
                        Log.e("CancelItemsListsize", String.valueOf(cancelItemsList.size()));


                    }
                }


            }
        }


        if (!printerType.equals("") && printerType.equals(getResources().getString(R.string.pos_3inch))) {
            if (orderItemsList!=null && orderItemsList.size()>0){
                printStatus = PrintReceipt.printInvoiceTableOrder3Inch(mActivity, saveResponseData, bmp, tableName, companyData,orderItemsList,notificationAppend);

            }

            if (cancelItemsList !=null && cancelItemsList.size()>0){
                printCancelStatus = PrintReceipt.printInvoiceTableCancelOrder3Inch(mActivity, saveResponseData, bmp, tableName, companyData,cancelItemsList,notificationAppend);

            }
        } else {
            if (orderItemsList!=null && orderItemsList.size()>0){
                printStatus = PrintReceipt.printInvoiceTableOrder2Inch(mActivity, saveResponseData, bmp, tableName, companyData,orderItemsList,notificationAppend);

            }

            if (cancelItemsList !=null && cancelItemsList.size()>0){
                printCancelStatus = PrintReceipt.printInvoiceTableCancelOrder2Inch(mActivity, saveResponseData, bmp, tableName, companyData,cancelItemsList,notificationAppend);

            }

        }
        if (printStatus) {

            //BLUETOOTH_PRINTER.stop();
            // Intent returnIntent = new Intent();
            // setResult(Activity.RESULT_OK, returnIntent);
            //finish();

        }

    }


    private void callPrintList() {
        Intent intent = new Intent(mActivity, Activity_PrintList.class);
        intent.putExtra("DailyReport", "printList");
        mActivity.startActivityForResult(intent, Constant.REQUESTCODE_PRINTLISTSALESINVOICE);
        fabMenus.setVisibility(View.GONE);
        fabMenus.close(true);
    }

    private void restaSettings() {
        dialog.setContentView(R.layout.dialog_restaurent_settings);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        tvPrinterStatus = dialog.findViewById(R.id.tvPrinterStatus);
        Button resetConnection = dialog.findViewById(R.id.resetConnection);
        Button btnSignout = dialog.findViewById(R.id.btnSignout);
        tvPrinterStatus.setVisibility(View.VISIBLE);
        final EditText edSearchItem = dialog.findViewById(R.id.edSearchItem);
        /* EditText edItemName = dialog.findViewById(R.id.edItemName);*/
        // EditText edSelectTax = dialog.findViewById(R.id.edSelectTax);
        EditText edTableName = dialog.findViewById(R.id.edTableName);
        ImageView imageviewSearch = dialog.findViewById(R.id.imageviewSearch);

        if (dialog != null) {
            dialog.show();
        }


        /*if (tableName != null) {
            edTableName.setText(tableName);
        }*/
        resetConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printerSetup();


            }
        });

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                logout();
            }
        });


        edSearchItem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearchItem);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    searchitem = edSearchItem.getText().toString().trim();


                    getItemSearch(searchitem);
                }
                return handled;
            }
        });

        String getPageLoadData = sharedPreference.getData(Constant.HINEXTRESTUARANTDATA_KEY);
        if (getPageLoadData != null) {
            String taxname = getResources().getString(R.string.cgst);
            Gson gson = new Gson();
            pageData = gson.fromJson(getPageLoadData, HinextRestuarantPageData.class);
            if (pageData != null) {

                for (int i = 0; i < pageData.getTaxList().size(); i++) {
                 /*   edSelectTax.setText(taxname);
                    if (taxname.equals(pageData.getTaxList().get(i).getTaxString())) {

                        edSelectTax.setText(pageData.getTaxList().get(i).getTaxString());

                    }*/

                }
                if (pageData.getCustomers() != null) {
                    selectCustomer.setText(pageData.getCustomers().get(0).getCustomerName());
                    customerId = pageData.getCustomers().get(0).getCustomerId();
                    customerName = pageData.getCustomers().get(0).getCustomerName();
                }
                if (pageData.getCustomers() != null) {
                    if (pageData.getCustomers().get(0).getAddress()!=null && !pageData.getCustomers().get(0).getAddress().equals("")){
                        customerAddress = pageData.getCustomers().get(0).getAddress();
                    }
                }
                if (pageData.getCustomers() != null) {
                    if (pageData.getCustomers().get(0).getPhoneNumber()!=null && !pageData.getCustomers().get(0).getPhoneNumber().equals("")){
                        customerNo = pageData.getCustomers().get(0).getPhoneNumber();
                    }

                }

            }
        }

        imageviewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchitem = edSearchItem.getText().toString().trim();
                if (!searchitem.equals("")) {
                    getItemSearch(searchitem);
                }


            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void getItemSearch(String search) {

        String url = "";

        if (!search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEM + "?itemCode=" + search.replace(" ", "%20");
        }

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                 getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {


                            if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                UtilView.gotToLogin(mActivity);
                            } else {
                                Gson gson = new Gson();
                                itemList = new ArrayList<>();

                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    SelectedItemsList item = null;
                                    if (jsonArray != null) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            item = gson.fromJson(jsonObject.toString(), SelectedItemsList.class);
                                            itemList.add(item);
                                        }

                                        if (itemList != null && itemList.size() > 0) {

                                            if (llItem != null) {
                                                llItem.setVisibility(View.GONE);
                                            }


                                            if (llListview != null)
                                                llListview.setVisibility(View.VISIBLE);


                                            double gstTaxPercantage = 0.00;
                                            if (pageData != null) {

                                                List<TaxList> taxList = pageData.getTaxList();
                                                if (taxList != null) {
                                                    for (int i = 0; i < taxList.size(); i++) {
                                                        if (item.getOutputTaxId() == taxList.get(i).getTaxid()) {
                                                            gstTaxPercantage = taxList.get(i).getTaxPercantage();
                                                        }
                                                    }
                                                }
                                            }
                                            double itemUnitPrice = 0.00;
                                            if (item.getInclusiveJSON() != null) {
                                                gson = new Gson();
                                                InclusiveJson inclusiveJson = gson.fromJson(item.getInclusiveJSON(), InclusiveJson.class);
                                                if (inclusiveJson.isSales()) {
                                                    itemUnitPrice = item.getSalesPrice() / (1 + (gstTaxPercantage * 0.01));
                                                } else {
                                                    if (item.getSalesPrice() != null)
                                                        itemUnitPrice = item.getSalesPrice();
                                                }
                                            }

                                            item.setGstTaxPercantage(gstTaxPercantage);
                                            item.setUnitPrice(itemUnitPrice);
                                            long itemQty = posCreator.checkItemQty(item);
                                            item.setItemQuantity(itemQty);
                                            posCreator.addItem(new RestraPosCartItem(item, 1), item.getItemId());

                                            checkCartList();

                                            if (dialog != null)
                                                dialog.dismiss();

                                        } else {
                                            UtilView.showToast(mActivity, "No Items Available.");
                                        }

                                    } else {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.response_error));
                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
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
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }


    private RestraCheckoutData getCheckoutDataForTable() {
        RestraCheckoutData data = new RestraCheckoutData();
        List<RestraCheckoutItem> itemList = new ArrayList<>();
        double totalTaxAmt = 0;
        double totalAmt = 0;

        if (posCartTableItems != null && posCartTableItems.size() > 0) {
            for (int i = 0; i < posCartTableItems.size(); i++) {
                RestraCheckoutItem item = new RestraCheckoutItem();
                item.setItemCode(posCartTableItems.get(i).getItemCode());
                item.setItemId(posCartTableItems.get(i).getItemId());
                item.setItemName(posCartTableItems.get(i).getItemName());
                item.setUnitPrice(posCartTableItems.get(i).getUnitPrice());
                item.setGstItemTax(posCartTableItems.get(i).getGstTaxAmt());
                item.setTaxamt(posCartTableItems.get(i).getGstTaxAmt());
                item.setGstTaxPercantage(posCartTableItems.get(i).getGstTaxPercantage());
                item.setAmtexclusivetax(posCartTableItems.get(i).getItemTotalAmountExTax());
                item.setAmtinclusivetax(posCartTableItems.get(i).getItemTotalAmountInTax());
                item.setQty(posCartTableItems.get(i).getItemQuantity());
                item.setItemCategoryId(posCartTableItems.get(i).getItemCategoryId());
                item.setItemCategoryName(posCartTableItems.get(i).getItemCategoryName());
                item.setInclusiveJSON(posCartTableItems.get(i).getInclusiveJSON());
                item.setTaxId(posCartTableItems.get(i).getTaxId());
                item.setUom(posCartTableItems.get(i).getUom());
                item.setTaxid(posCartTableItems.get(i).getOutputTaxId());
                item.setInputTaxId(posCartTableItems.get(i).getInputTaxId());
                item.setOutputTaxId(posCartTableItems.get(i).getOutputTaxId());
                item.setItemTypeId(posCartTableItems.get(i).getItemTypeId());
                item.setItemTypeName(posCartTableItems.get(i).getItemTypeName());
                item.setTableId(posCartTableItems.get(i).getTableId());
                item.setFloorId(posCartTableItems.get(i).getFloorId());
                item.setWaiterName(posCartTableItems.get(i).getWaiterName());
                item.setFlag(false);
                totalTaxAmt += posCartTableItems.get(i).getGstTaxAmt();
                totalAmt += posCartTableItems.get(i).getUnitPrice()*item.getQty();

                if (!posCartTableItems.get(i).getKotSelect()){
                    kotCheck = true;
                    itemList.add(item);
                }else{
                    itemList.add(item);

                }


            }

            if (kotCheck){
                Log.e("kotCheck", String.valueOf(kotCheck));

                saveKotTableIfNotSelected(tableData);
            }

            List<MultiCashPaymentList> multiCashPaymentLists = new ArrayList<>();
            MultiCashPaymentList cashPaymentList = new MultiCashPaymentList();
            cashPaymentList.setCashAmt(0.00);
            cashPaymentList.setPaymentType(1L);
            multiCashPaymentLists.add(cashPaymentList);
            cashPayment.setMultiCashPaymentList(multiCashPaymentLists);

            CreditPayment creditPayment = new CreditPayment();
            creditPayment.setCardPaymentList(cardPaymentList);

            VoucherPayment voucherPayment = new VoucherPayment();
            voucherPayment.setMultiVoucherPayments(voucherPaymentList);

            BankPayment bankPayment = new BankPayment();
            bankPayment.setMultiBankPaymentList(bankPaymentList);
            data.setRoundingOffValue("0.00");
            data.setCashPayment(cashPayment);
            data.setBankPayment(bankPayment);
            data.setVoucherPayment(voucherPayment);
            data.setCreditPayment(creditPayment);

            data.setTaxType("CGST:SGST/UGST");
            data.setPaymentType("multiPayment");
            data.setCustomerName(customerName);
            data.setCustomerId(customerId);
            data.setCustomerAddress(customerAddress);
            data.setCustomerNo(customerNo);
            data.setNotificationAppend(notificationAppend);
            data.setSelectedItemsList(itemList);
            data.setTotalCheckOutamt(totalAmt);
            data.setTotalTenderedAmount(totalAmt);
            data.setTotalRemaininBalance(0l);
            data.setTotalTaxAmt(totalTaxAmt);
            bluetoothStatus = true;
            data.setBluetoothStatus(bluetoothStatus);

            if (tableData != null) {
                Log.e("tableId", tableData.getTableId());
                Log.e("tableName", tableData.getTableName());

                data.setTableVal(tableData.getTableId());
                data.setTableName(tableData.getTableName());

             /*   try {
                    Gson gson = new Gson();

                    tableChairData.put(tableData.getTableName(),"");

                    TableNamesData namesData = new TableNamesData();
                    namesData.setTableName(tableChairData);
                    data.setTableName(namesData);
                    Log.e("datadata", String.valueOf(data.getTableName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            } else {
                data.setTableName(empName);
                data.setTableVal(tableData.getTableId());

              /*  try {
                    Gson gson = new Gson();

                    tableChairData.put(tableData.getTableName(),"");

                    TableNamesData namesData = new TableNamesData();
                    namesData.setTableName(tableChairData);
                    data.setTableName(namesData);
                    Log.e("datadata", String.valueOf(data.getTableName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
            if (employeeDetail != null) {
                Log.e("employee", employeeDetail.getEmployeeName());
                empName = employeeDetail.getEmployeeName();
                data.setWaiterName(empName);
            } else {
                data.setWaiterName(empName);
            }
        }
        return data;
    }

    private void saveKotTableIfNotSelected(SubRow tableData) {
        if (posCartTableItems != null && posCartTableItems.size() > 0) {
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                     postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                Log.e("resultTable", result.toString());
                                Gson gson = new Gson();
                                HideUtil.init(RestuarantActivity.this);
                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(mActivity, Activity_Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        try {

                                            for (int i = 0; i < posCartTableItems.size(); i++) {
                                                RealmManager.createRestaurentDao().updateRestaurentKotTableData(posCartTableItems.get(i));
                                            }
                                            if (tableSelected) {
                                                tablesdata.clear();
                                                bluetoothStatus = true;
                                                kotCheck =true;
                                                tableOrderKOTWithoutCheckout();

                                            }

                                        } catch (Exception e) {
                                            Log.e("tableExeception", e.toString());
                                        }


                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);

                            }
                        }
                    }, false);

                    if (tableData != null) {
                        tableName = tableData.getTableName();
                        tableId = tableData.getTableId();
                    }

                    if (tableData != null) {
                        tableName = tableData.getTableName();
                        tableId = tableData.getTableId();
                    }

                    String employeeName = "";
                    if (employeeDetail != null) {
                        employeeName = employeeDetail.getEmployeeName();
                    } else {
                        employeeName = empName;
                    }
                    String orderNo="";
                    postDataTask.execute(saveOrderPostData("", "", selected_customer), serverUrl + "/restaurant/saveTableDataTempMobile?currTableName=" + tableName.replace(" ", "%20") + "&currTableId=" + tableId + "&prevTableName=" + tableName.replace(" ", "%20") + "&prevTableId=" + tableId + "&employeeName=" + employeeName.replace(" ", "%20") + "&customerId=" + customerId+"&orderNo="+notificationOrderNo+"&agentId="+notificationagentId, "");
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

    private void printerSetup() {
        // tvPrinterStatus.setVisibility(View.VISIBLE);
        // Get device's Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not available in your device
        if (mBluetoothAdapter == null) {
            UtilView.showToast(mActivity, "Bluetooth is not available");
        }
        // invalidateOptionsMenu();

        // tvPrinterStatus.setVisibility(View.VISIBLE);
        tvPrinterStatus.setText(R.string.title_connection);
        if (mBluetoothAdapter != null) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                // Otherwise, setup the chat session
            } else {
                if (BLUETOOTH_PRINTER == null) {
                    initializeBluetoothDevice();

                } else {
                    if (BLUETOOTH_PRINTER.IsNoConnection()) {
                        tvPrinterStatus.setText("Printer is offline");
                        BLUETOOTH_PRINTER.stop();
                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSKOT1);
                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSKOT2);
                        //sharedPreference.setRemovePrefrence(Constant.BLUETOOTHTYPEKOT1);
                       // sharedPreference.setRemovePrefrence(Constant.BLUETOOTHTYPEKOT2);
                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESSBILL);
                        //sharedPreference.setRemovePrefrence(Constant.BLUETOOTHTYPEBILL);
                        serverIntent = new Intent(mActivity, DeviceListActivity.class);
                        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                    } else {
                        tvPrinterStatus.setText(R.string.title_connected_to);

                        if (device != null) {
                            tvPrinterStatus.append(device.getName());
                        }

                        callBluetoothConnection();
                    }
                }

            }
        } else {
            tvPrinterStatus.setText(R.string.bluettoth_doesnt);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    // Get the device MAC address
                    String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    printerType = data.getExtras().getString(DeviceListActivity.EXTRA_PRINTER_TYPE);
                    // Get the BLuetoothDevice object
                    sharedPreference.saveData(Constant.BLUETOOTHADDRESS, address);
                    sharedPreference.saveData(Constant.BLUETOOTHTYPE, printerType);
                    device = mBluetoothAdapter.getRemoteDevice(address);
                    // Attempt to connect to the device
                    BLUETOOTH_PRINTER.start();
                    BLUETOOTH_PRINTER.connect(device);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a chat session
                    initializeBluetoothDevice();
                } else {
                    // User did not enable Bluetooth or an error occured
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                    //finish();
                }

                break;

            case 101:

                Intent intent = new Intent(this, NavigationDrawer_Activity.class);
                intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_RETAIL);
                startActivity(intent);
                finish();
                break;

            case Constant.RESQUSTCODE_CUSTOMERS:
                if (requestCode == Constant.RESQUSTCODE_CUSTOMERS && resultCode == RESULT_OK) {
                    CustomerList cust = (CustomerList) data.getSerializableExtra("customer");
                    if (cust != null) {
                        selected_customer = new Customer();
                        customerId = cust.getCustomerId();

                        customerName = cust.getCustomerName();
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

                        if (customerName != null) {
                            selectCustomer.setText(customerName);
                        }
                    }
                }
                break;
            case Constant.RESQUSTCODE_SPLITTBILL:
                if (requestCode == Constant.RESQUSTCODE_SPLITTBILL && resultCode == RESULT_OK) {
                    try {
                        checkTableCartList();
                        getCheckoutDataForTable();


                      /*  if (getCheckout != null) {
                            if (getCheckout.getSelectedItemsList() != null && getCheckout.getSelectedItemsList().size() > 0) {
                                for (int i = 0; i < getCheckout.getSelectedItemsList().size(); i++) {
                                    String tableId = getCheckout.getSelectedItemsList().get(i).getItemCode();
                                    Log.e("splittableId", tableId);
                                    Log.e("splitId", getCheckout.getSelectedItemsList().get(i).getFloorId());
                                    Log.e("splittableCode", getCheckout.getSelectedItemsList().get(i).getItemCode());
                                    Log.e("splittableName", getCheckout.getSelectedItemsList().get(i).getItemName());
                                    RealmManager.createRestaurentDao().deletRealmDataAll(getCheckout.getSelectedItemsList().get(i));
                                    RealmManager.createRestaurentDao().deletRealmTempDataAll(getCheckout.getSelectedItemsList().get(i));
                                    RealmManager.createRestaurentDao().deletRealmTempOrderDataAllTable();
                                }
                                checkTableCartList();
                            }

                        }*/

                    } catch (Exception e) {
                        Log.e("splitPosCreator", e.toString());
                    }
                }
                break;

            case Constant.RESQUSTCODE_CHECKOUT:
                if (requestCode == Constant.RESQUSTCODE_CHECKOUT && resultCode == RESULT_OK) {
                    getCheckout = (RestraCheckoutData) data.getSerializableExtra("paidTableName");
                    splitBillStatus = getIntent().getBooleanExtra("splitBillStatusPaym", false);
                    notificationOrderNo ="";
                    fabMenus.setVisibility(View.GONE);
                    fabMenus.close(true);
                   // tvWaiterName.setClickable(true);
                    try {
                        if (posCartTableItems != null) {
                            if (tableData != null) {

                                posCartTableItems.clear();
                                if (getCheckout.getSelectedItemsList() != null && getCheckout.getSelectedItemsList().size() > 0) {


                                    for (int i = 0; i < getCheckout.getSelectedItemsList().size(); i++) {

                                        String tableId = getCheckout.getTableVal();
                                        String itemCode = getCheckout.getSelectedItemsList().get(i).getItemCode();
                                        Log.e("splitPosCreatorItemname", getCheckout.getSelectedItemsList().get(i).getItemName());
                                        RealmManager.createRestaurentDao().deletRealmDataAll(getCheckout.getSelectedItemsList().get(i));
                                        RealmManager.createRestaurentDao().deletRealmTempDataAll(getCheckout.getSelectedItemsList().get(i));
                                        RealmManager.createRestaurentDao().deletRealmTempOrderDataAllTable();
                                    }

                                    orderPalced = true;
                                    if (orderPalced) {
                                        tableSelected = false;
                                        tablesdata.clear();
                                    }
                                    checkTableCartList();
                                }
                            }
                        }
                    }catch (Exception e){
                        Log.e("Checkout Exception",e.toString());
                    }
                }

                break;
            case Constant.RESQUSTCODE_RESTUARENTTABLE:
                if (requestCode == Constant.RESQUSTCODE_RESTUARENTTABLE && resultCode == RESULT_OK) {
                    orderPalced = data.getBooleanExtra("selectTableOrder", false);
                    tableData = (SubRow) data.getSerializableExtra("tableData");
                    notificationOrderNo="";
                    Log.e("getTableName", tableData.getTableName());
                    Log.e("getorderPalced", String.valueOf(orderPalced));
                    /* if (posCreator != null) {
                        posCreator.clear();
                        checkCartList();
                    }*/
                   // tvWaiterName.setClickable(true);


                    if (tableData != null) {


                        if (!tableData.getSelected()) {
                            Log.e("getTableName1", tableData.getTableName());
                            RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                            RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                            RealmManager.createRestaurentDao().deletRealmTempOrderDataAllTable();
                            checkTableCartList();
                        } else{
                            Log.e("getTableName2", tableData.getTableName());
                            //UtilView.showProgessBar(mActivity, progressBar);
                            RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                            RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                            RealmManager.createRestaurentDao().deletRealmTempOrderDataAllTable();
                            callGetTempData(tableData);
                           // checkTableCartList();
                           // UtilView.hideProgessBar(mActivity, progressBar);
                        }



                        tableSelected = true;
                        orderPalced = false;
                        tvTableData.setVisibility(View.VISIBLE);
                        tvTableName.setVisibility(View.VISIBLE);
                        tvWaiterName.setVisibility(View.VISIBLE);



                        tableName = tableData.getTableName();
                        tvTableData.setText(tableName);
                    }
                }
                break;
            case Constant.RESQUSTCODE_ADDEMPLOYEE:
                if (requestCode == Constant.RESQUSTCODE_ADDEMPLOYEE && resultCode == RESULT_OK) {
                    try {
                        employeeDetail = (EmployeeDatum) data.getSerializableExtra("employeeData");
                        if (employeeDetail != null) {
                            // Toast.makeText(mActivity, "emp "+employeeDetail.getEmployeeName(), Toast.LENGTH_SHORT).show();
                            empName = employeeDetail.getEmployeeName();
                            tvWaiterName.setText(empName);
                        }
                    }catch (Exception e){
                        Log.e("Employee Exception",e.toString());
                    }


                }
                break;

        }


    }

    private void callGetTempData(SubRow subRowdata) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {

                tableName= subRowdata.getTableName();
                tableId= subRowdata.getTableId();
                Log.e("TableName",subRowdata.getTableName());
                Log.e("TableNameId",subRowdata.getTableId());
                Log.e("TableNameSelec", String.valueOf(subRowdata.getSelected()));
                Log.e("TableNameFloor", String.valueOf(subRowdata.getFloorId()));

               /* if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);*/
                // UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/restaurant/" + Constant.FUNTION_RESTARUANTTEMP + "?currTableName=" + tableName.replace(" ", "%20") + "&currTableId=" + tableId;

                String finalTableId = tableId;
                 getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                     /*   if (progressBar != null)
                            progressBar.setVisibility(View.GONE);*/
                        // UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            JSONArray jsonArray=null;
                            JSONObject jsonObject =null;
                            Gson gson = new Gson();
                            try {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(mActivity, Activity_Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    try {
                                        GetTempDataDeatails details = new Gson().fromJson(result.toString(), GetTempDataDeatails.class);

                                        boolean flagStatus=false;

                                        if (tableData!=null){
                                            RealmManager.createRestaurentDao().deletServerRealmDataAll(tableData);
                                            RealmManager.createRestaurentDao().deletServerRealmTempDataAll(tableData);
                                            checkTableCartList();
                                            flagStatus = true;
                                        }


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
                                                    tableData.setTableName(details.getTableName());
                                                }
                                                else {
                                                    tableData.setTableName(tableName);
                                                }

                                                if (details.getTableId() != null && !details.getTableId().equals("")) {
                                                    tableData.setTableValue(details.getTableId());
                                                }else{
                                                    tableData.setTableValue(tableId);
                                                }
                                                if (tableData != null) {
                                                        if (flagStatus){
                                                            String selectItemlist="";
                                                            jsonObject = new JSONObject(result.toString());
                                                            selectItemlist = jsonObject.getString("selectedItemsList");
                                                            jsonArray = new JSONArray(selectItemlist.toString());
                                                            Log.e("jsonArray",jsonArray.toString());

                                                            List<SelectedItemsList> selectedItemList = new ArrayList<>();
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
                                                                    Long taxid = jsonObject1.getLong("taxid");
                                                                    empname.setTaxId(taxid);
                                                                    String inclusiveJSON= jsonObject1.getString("inclusiveJSON");
                                                                    empname.setInclusiveJSON(inclusiveJSON);
                                                                    empname.setItemTotalAmount(empname.getAmtinclusivetax());
                                                                    if (jsonObject1.has("itemDescription")) {
                                                                        String itemDescription = jsonObject1 .getString("itemDescription");
                                                                        empname.setItemDescription(itemDescription);
                                                                    }
                                                                    else {
                                                                        empname.setItemDescription("");
                                                                    }

                                                                    selectedItemList.add(empname);
                                                                }

                                                                checkTableCartList();


                                                            }

                                                            for (int i = 0; i < selectedItemList.size(); i++) {
                                                                selectedItemList.get(i).setWaiterName(waiter.getWaiterName());
                                                                selectedItemList.get(i).setCustomerId(waiter.getCustomerId());
                                                                String itemCode = "";
                                                                itemCode = selectedItemList.get(i).getItemCode();
                                                                RealmManager.createRestaurentDao().saveRestaurentServerItemDetails(DataGenerator.generateRestaServcerSelectItem(selectedItemList.get(i), tableData, itemCode), itemCode, tableData);
                                                                RealmManager.createRestaurentDao().saveRestaurentTempItemDetails(DataGenerator.generateRestaTempServcerSelectItem(selectedItemList.get(i), tableData), itemCode, tableData);

                                                            }
                                                            checkTableCartList();
                                                        }

                                                    //}

                                                }


                                           // }
                                        }else {
                                            //Log.e("tabledId1", tableData.getTableValue());
                                            checkTableCartList();
                                        }




                                    } catch (Exception ex) {
                                        Log.e("JSONException", ex.toString());
                                        // UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                    }
                                }
                            } catch (Exception e) {
                                Log.e("Exception1", e.toString());
                                //UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);

                        }
                    }

                }, false);
                Gson gson = new Gson();
                getDataTask.execute(url, "");


            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }


    private void initializeBluetoothDevice() {
        Log.e(TAG, "setupChat()");
        // Initialize HsBluetoothPrintDriver class to perform bluetooth connections
        BLUETOOTH_PRINTER = HsBluetoothPrintDriver.getInstance();//
        BLUETOOTH_PRINTER.setHandler(new BluetoothHandler(RestuarantActivity.this));

    }

    public void logout() {
        /*PosCreator posCreator = new PosCreator();
        posCreator.clear();
        sharedPreference.setRemovePrefrence(Constant.LOGINDATA_KEY);
        sharedPreference.setRemovePrefrence(Constant.COMPANYDETAIL);
        sharedPreference.setRemovePrefrence(Constant.ACCESSTOKEN);
        sharedPreference.setRemovePrefrence(Constant.USERNAME);
        Intent intent = new Intent(mActivity, Activity_Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();*/
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Constant.ACCESSTOKEN, sharedPreference.getData(Constant.ACCESSTOKEN));
                UtilView.showLogCat(TAG, sharedPreference.getData(Constant.ACCESSTOKEN));

                String url = UtilView.getUrl(mActivity);
                if (url != null) {

                    UtilView.showProgessBar(mActivity, progressBar);
                     postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(mActivity, Activity_Login.class);
                                    startActivity(intent);
                                    mActivity.finish();
                                } else {
                                    try {
                                        JSONObject jsonObject = new JSONObject(result.toString());
                                        if (jsonObject.getString("message").equals("success")) {
                                            UtilView.gotToLogin(mActivity);
                                        } else {
                                            UtilView.showToast(mActivity, "Some error in logout.");
                                        }
                                    } catch (Exception e) {
                                        UtilView.showLogCat(TAG, "DataTaskListener Exception " + e.toString());
                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        }

                    }, false);
                    postDataTask.execute(jsonObject.toString(), url + "/hipos/" + Constant.FUNTION_LOGOUT, "");

                } else {
                    UtilView.gotToLogin(mActivity);
                }
            } catch (Exception e) {

            }
        } else {

            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
        }

    }

    private void callBluetoothConnection() {
        //If an existing connection is still alive then ask user to kill it and re-connect again
        alertDlgBuilder = new android.app.AlertDialog.Builder(mActivity);
        alertDlgBuilder.setTitle(getResources().getString(R.string.alert_title));
        alertDlgBuilder.setMessage(getResources().getString(R.string.alert_message));
        alertDlgBuilder.setNegativeButton(getResources().getString(R.string.alert_btn_negative), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );
        alertDlgBuilder.setPositiveButton(getResources().getString(R.string.alert_btn_positive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BLUETOOTH_PRINTER.stop();
                        serverIntent = new Intent(mActivity, DeviceListActivity.class);
                        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                    }
                }
        );
        alertDlgBuilder.show();

    }


    /**
     * The Handler that gets information back from Bluetooth Devices
     */
    static class BluetoothHandler extends Handler {
        private final WeakReference<RestuarantActivity> myWeakReference;

        //Creating weak reference of BluetoothPrinterActivity class to avoid any leak
        BluetoothHandler(RestuarantActivity weakReference) {
            myWeakReference = new WeakReference<RestuarantActivity>(weakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            RestuarantActivity bluetoothPrinterActivity = myWeakReference.get();
            // tvPrinterStatus.setVisibility(View.VISIBLE);
            if (bluetoothPrinterActivity != null) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                switch (data.getInt("flag")) {
                    case Contants.FLAG_STATE_CHANGE:
                        int state = data.getInt("state");
                        Log.e(TAG, "MESSAGE_STATE_CHANGE: " + state);

                        switch (state) {
                            case HsBluetoothPrintDriver.CONNECTED_BY_BLUETOOTH:

                               // tvPrinterStatus.setText(R.string.title_connected_to);
                                if (device!=null){
                                    Toast.makeText(bluetoothPrinterActivity, device.getName()+" connected", Toast.LENGTH_SHORT).show();
                                   // tvPrinterStatus.append(device.getName());
                                }

                                Constant.isPrinterConnected = true;
                                //Toast.makeText(CONTEXT,"Connection successful.", Toast.LENGTH_SHORT).show();

                                break;
                            case HsBluetoothPrintDriver.FLAG_SUCCESS_CONNECT:
                            //    tvPrinterStatus.setText(R.string.title_connecting);
                                break;

                            case HsBluetoothPrintDriver.UNCONNECTED:
                             //   tvPrinterStatus.setText(R.string.no_printer_connected);
                                break;
                        }
                        break;
                    case Contants.FLAG_SUCCESS_CONNECT:
                      //  tvPrinterStatus.setText(R.string.title_connecting);
                        break;
                    case Contants.FLAG_FAIL_CONNECT:
                        // Toast.makeText(CONTEXT,"Connection failed.",Toast.LENGTH_SHORT).show();
                     //   tvPrinterStatus.setText("Connection failed.");
                        Constant.isPrinterConnected = false;
                        break;
                    default:
                        break;

                }
            }
        }

    }


}




