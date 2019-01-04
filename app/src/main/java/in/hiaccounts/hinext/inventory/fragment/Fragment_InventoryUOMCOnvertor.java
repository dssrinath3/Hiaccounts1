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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.inventory.activity.Activity_UOMConverter;
import in.hiaccounts.hinext.inventory.adapter.UOMConverterAdapter;
import in.hiaccounts.hinext.inventory.model.uomconverter.UOMConvSelectData;
import in.hiaccounts.hinext.inventory.model.uomconverter.UomConverterDatum;
import in.hiaccounts.hinext.inventory.model.uomconverter.UomConverterDatumData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * created by srinath 29-11-2017.
 */
public class Fragment_InventoryUOMCOnvertor extends Fragment implements TextWatcher {

    public static String TAG = Fragment_InventoryUOMCOnvertor.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvUOM)
    ListView lvUOM;
    @BindView(R.id.edSearchUOM)
    EditText edSearchUOM;
    @BindView(R.id.llSearchUOM)
    LinearLayout llSearchUOM;
    @BindView(R.id.ll)
    LinearLayout ll;
    Unbinder unbinder;
    List<UomConverterDatumData> uomselctList;
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private List<Object> uomList = new ArrayList<Object>();
    private String uomSearch = "", serverUrl;

    public static Fragment_InventoryUOMCOnvertor newInstance() {
        Fragment_InventoryUOMCOnvertor fragment = new Fragment_InventoryUOMCOnvertor();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_uomconvertor, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_uom_converterview, null, false);
        TextView tv_uom = header.findViewById(R.id.tvUom);
        TextView tv_uomName = header.findViewById(R.id.tvUomConvName);
        TextView tv_uomValue = header.findViewById(R.id.tvUomConvValue);
        TextView tv_uomEdit = header.findViewById(R.id.tvUomEdit);
        tv_uom.setText("UOM");
        tv_uomName.setText("UOM Name");
        tv_uomValue.setText("UOM Value");
        tv_uomEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tv_uom);
        UtilView.setTextAppearanceSmall(activity, tv_uomName);
        UtilView.setTextAppearanceSmall(activity, tv_uomValue);
        UtilView.setTextAppearanceSmall(activity, tv_uomEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_uom);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_uomName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_uomValue);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_uomEdit);
        if (lvUOM != null)
            lvUOM.addHeaderView(header);
        if(edSearchUOM !=null)
        edSearchUOM.addTextChangedListener(this);

        getUOMConverterFromServer("");

        edSearchUOM.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchUOM);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    uomSearch = edSearchUOM.getText().toString().trim();
                    getUOMConverterFromServer(uomSearch);
                }
                return handled;
            }
        });
    }

    private void getUOMConverterFromServer(String search) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETUOMCOVERTERlISTINVENTORY + "?uomConvertorSearchText=&type=Active";
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETUOMCOVERTERlISTINVENTORY + "?uomConvertorSearchText=" + uomSearch.replace(" ", "%20") + "&type=Active";
        }

        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(activity, Activity_Login.class);
                                activity.startActivity(intent);
                                activity.finish();
                            } else {
                                Gson gson = new Gson();
                                UomConverterDatum uomConverterDatum = gson.fromJson(result.toString(),UomConverterDatum.class);

                                if (uomConverterDatum!=null){
                                    uomselctList = new ArrayList<UomConverterDatumData>();
                                    uomselctList = uomConverterDatum.getData();
                                    if (uomselctList != null && uomselctList.size() > 0) {
                                        UOMConverterAdapter adapter = new UOMConverterAdapter(activity, uomselctList);
                                        lvUOM.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        uomselctList.clear();
                                        UOMConverterAdapter adapter = new UOMConverterAdapter(activity, uomselctList);
                                        lvUOM.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog(getResources().getString(R.string.uom_notavailbale), activity);

                                    }
                                }


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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDUOMCONVERTER) {
            getUOMConverterFromServer("");
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITUOMCONVERTER) {
            getUOMConverterFromServer("");
        }

    }

    @OnClick({R.id.llSearchUOM})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchUOM:
                UtilView.hideSoftKeyboard(activity, edSearchUOM);
                uomSearch = edSearchUOM.getText().toString().trim();
                getUOMConverterFromServer(uomSearch);
                break;

        }
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
                Intent intent = new Intent(activity, Activity_UOMConverter.class);
                intent.putExtra("callingFor", Constant.CALL_ADDUOMCONVERTER);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDUOMCONVERTER);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            UtilView.hideSoftKeyboard(activity, edSearchUOM);
            uomSearch = "";
            getUOMConverterFromServer(uomSearch);
        }
    }




}
