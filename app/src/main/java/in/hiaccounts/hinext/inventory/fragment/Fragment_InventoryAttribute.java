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
import in.hiaccounts.hinext.inventory.activity.Activity_Attribute;
import in.hiaccounts.hinext.inventory.adapter.AttributeAdapter;
import in.hiaccounts.hinext.inventory.model.attribute.Attribute;
import in.hiaccounts.hinext.inventory.model.attribute.AttributeData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_InventoryAttribute extends Fragment implements TextWatcher {

    public static String TAG = Fragment_InventoryAttribute.class.getSimpleName();
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
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.tvLast)
    TextView tvLast;
    @BindView(R.id.llPaginationView)
    LinearLayout llPaginationView;
    Unbinder unbinder;
    int pageNo = 0;
    AttributeAdapter attributeAdapter;
    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private List<Object> attributeList = new ArrayList<Object>();
    private String attributeSearch = "", serverUrl;

    public static Fragment_InventoryAttribute newInstance() {
        Fragment_InventoryAttribute fragment = new Fragment_InventoryAttribute();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_attribute, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        serverUrl = UtilView.getUrl(mActivity);
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_attributeview,null,false);
        TextView tvAttributeName = header.findViewById(R.id.tvAttributeName);
        TextView tvAttributeDescription = header.findViewById(R.id.tvAttributeDescription);
        TextView tvAttributeEdit = header.findViewById(R.id.tvAttributeEdit);
        tvAttributeName.setText("Name");
        tvAttributeDescription.setText("Description");
        tvAttributeEdit.setText("Action");
        UtilView.setTextAppearanceSmall(mActivity, tvAttributeName);
        UtilView.setTextAppearanceSmall(mActivity, tvAttributeDescription);
        UtilView.setTextAppearanceSmall(mActivity, tvAttributeEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAttributeName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAttributeDescription);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAttributeEdit);

        if(listview != null)
            listview.addHeaderView(header);

        if(edSearch !=null)
        edSearch.addTextChangedListener(this);

        getAttributeFromServer("", true, false, "0", false, false);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    handled = true;
                    attributeSearch = edSearch.getText().toString().trim();
                    getAttributeFromServer(attributeSearch, true, false, "0", false, false);
                }

                return handled;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEdit) {
                    if (attributeList != null) {
                        Attribute attribute = (Attribute) attributeList.get(position);
                        Intent intent = new Intent(mActivity, Activity_Attribute.class);
                        intent.putExtra("attributeData", attribute);
                        intent.putExtra("callingFor", Constant.CALL_EDITATTRIBUTE);
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITATTRIBUTE);
                    }
                }
            }
        });
    }

    private void getAttributeFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETATTRIBUTE + "?AttributeItemSearchText=&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETATTRIBUTE + "?AttributeItemSearchText=" + search.replace(" ", "%20") + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {
                                attributeList.clear();
                                List<Attribute> list = new ArrayList<>();
                                AttributeData data = gson.fromJson(result.toString(), AttributeData.class);
                                if (data != null) {
                                    setUpView(data);
                                    if (data.getData() != null && data.getData().size() > 0) {
                                        list = data.getData();
                                        attributeList.addAll(list);
                                        attributeAdapter = new AttributeAdapter(mActivity, attributeList);
                                        if (listview != null)
                                            listview.setAdapter(attributeAdapter);
                                        attributeAdapter.notifyDataSetChanged();

                                    } else {

                                        attributeList.clear();
                                        attributeAdapter = new AttributeAdapter(mActivity, attributeList);
                                        if (listview != null)
                                            listview.setAdapter(attributeAdapter);
                                        attributeAdapter.notifyDataSetChanged();
                                        UtilView.showSuccessDialog(getResources().getString(R.string.attribute_notavailbale), mActivity);
                                    }
                                }


                            }catch (Exception e){
                                UtilView.showErrorDialog(mActivity.getResources().getString(R.string.response_error),mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDATTRIBUTE) {
            getAttributeFromServer("", true, false, "0", false, false);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITATTRIBUTE) {
            getAttributeFromServer("", true, false, "0", false, false);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                attributeSearch = edSearch.getText().toString().trim();
                pageNo = 0;
                getAttributeFromServer(attributeSearch, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getAttributeFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0) {
                    pageNo = pageNo - 1;
                }
                getAttributeFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getAttributeFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getAttributeFromServer("", false, false, "0", false, true);
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
                Intent intent = new Intent(mActivity, Activity_Attribute.class);
                intent.putExtra("callingFor", Constant.CALL_ADDATTRIBUTE);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDATTRIBUTE);
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
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            attributeSearch = "";
            getAttributeFromServer(attributeSearch, true, false, "0", false, false);
        }

    }

    private void setUpView(AttributeData data) {
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);
        if (data.getFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);
            }
        }
        if (!data.getFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }
        if (data.getNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);
            }
        }
        if (!data.getNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }
        if (data.getPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);
            }
        }
        if (!data.getPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }
        if (data.getLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
        }
        if (!data.getLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }
        pageNo = data.getPageNo();
    }


}
