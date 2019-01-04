package in.hiaccounts.hinext.inventory.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.activity.Activity_InventoryLocation;
import in.hiaccounts.hinext.inventory.adapter.InventoryLocationAdapter;
import in.hiaccounts.hinext.inventory.model.inventorylocation.InventoryLocation;
import in.hiaccounts.hinext.inventory.model.inventorylocation.InventoryLocationData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventoryLocation extends Fragment implements TextWatcher {

    public static final String TAG = Fragment_InventoryLocation.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvFirst)
    TextView tvFirst;
    @BindView(R.id.tvPrev)
    TextView tvPrev;
    @BindView(R.id.tvPageNo)
    TextView tvPageNo;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.tvLast)
    TextView tvLast;
    @BindView(R.id.llPaginationView)
    LinearLayout llPaginationView;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private List<Object> locationList = new ArrayList<Object>();
    private InventoryLocationAdapter inventoryLocationAdapter;
    private String search = "",serverUrl;
    private Unbinder unbinder;
    private int pageNo = 0;

    public static Fragment_InventoryLocation newInstance() {
        Fragment_InventoryLocation fragment = new Fragment_InventoryLocation();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_inventorylocation, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serverUrl= UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_inventorylocationview, null, false);
        TextView tvLocationName = header.findViewById(R.id.tvLocationName);
        TextView tvContactPerson = header.findViewById(R.id.tvContactPerson);
        TextView tvGSTIN = header.findViewById(R.id.tvGSTIN);
        TextView tvEdit = header.findViewById(R.id.tvEdit);
        tvLocationName.setText("Inv. Location Name");
        tvContactPerson.setText("Contact Person");
        tvGSTIN.setText("GSTIN");
        tvEdit.setText("Edit");
        UtilView.setTextAppearanceSmall(activity, tvLocationName);
        UtilView.setTextAppearanceSmall(activity, tvContactPerson);
        UtilView.setTextAppearanceSmall(activity, tvGSTIN);
        UtilView.setTextAppearanceSmall(activity, tvEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvLocationName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvContactPerson);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvGSTIN);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);
        if (listview != null)
            listview.addHeaderView(header);
        edSearch.addTextChangedListener(this);
        getLocationFromServer("", true, false, "0", false, false);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getLocationFromServer(search, true, false, "0", false, false);
                }
                return handled;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEdit) {
                    if (locationList != null) {
                        InventoryLocation location = (InventoryLocation) locationList.get(position);
                        Intent intent = new Intent(activity, Activity_InventoryLocation.class);
                        intent.putExtra("inventoryLocation", location);
                        intent.putExtra("callingfor", "edit");
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITLOCATION);
                    }

                }
            }
        });
    }

    private void getLocationFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORYLOCATION + "?inventoryLocationSearchText=&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORYLOCATION + "?inventoryLocationSearchText=" + search.replace(" ", "%20") + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {
                            locationList.clear();
                            List<InventoryLocation> list = new ArrayList<>();

                            InventoryLocationData data = gson.fromJson(result.toString(), InventoryLocationData.class);
                            if (data != null) {
                                setUpView(data);
                                if (listview != null) {
                                    if (data.getData() != null && data.getData().size() > 0) {
                                        list = data.getData();
                                        locationList.addAll(list);
                                        inventoryLocationAdapter = new InventoryLocationAdapter(activity, locationList);
                                        listview.setAdapter(inventoryLocationAdapter);
                                        inventoryLocationAdapter.notifyDataSetChanged();
                                    } else {
                                        locationList.clear();
                                        inventoryLocationAdapter = new InventoryLocationAdapter(activity, locationList);
                                        listview.setAdapter(inventoryLocationAdapter);
                                        inventoryLocationAdapter.notifyDataSetChanged();
                                        UtilView.showSuccessDialog(getResources().getString(R.string.inventorylocation_notavailbale), activity);
                                    }
                                }
                            }
                            }catch (Exception e){
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        }else {
            UtilView.gotToLogin(activity);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().equals("")) {
            UtilView.hideSoftKeyboard(activity, edSearch);
            search = "";
            getLocationFromServer(search, true, false, "0", false, false);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_ADDLOCATION && resultCode == Activity.RESULT_OK) {
            getLocationFromServer("", true, false, "0", false, false);
        }
        if (requestCode == Constant.RESQUSTCODE_EDITLOCATION && resultCode == Activity.RESULT_OK) {
            getLocationFromServer("", true, false, "0", false, false);
        }


    }

    @OnClick({R.id.llSearch, R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(activity, edSearch);
                search = edSearch.getText().toString().trim();
                getLocationFromServer(search, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getLocationFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0) {
                    pageNo = pageNo - 1;
                }
                getLocationFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getLocationFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getLocationFromServer("", false, false, "0", false, true);
                break;
        }
    }

    private void setUpView(InventoryLocationData data) {
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);
        if (data.isFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);
            }
        }
        if (!data.isFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }
        if (data.isNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);
            }
        }
        if (!data.isNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }
        if (data.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);
            }
        }
        if (!data.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }
        if (data.isLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
        }
        if (!data.isLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }

        pageNo = data.getPageNo();


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.inventory_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.addMenu:
                Intent intent = new Intent(activity, Activity_InventoryLocation.class);
                intent.putExtra("callingfor", "add");
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDLOCATION);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
