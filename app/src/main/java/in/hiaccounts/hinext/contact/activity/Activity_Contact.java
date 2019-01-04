package in.hiaccounts.hinext.contact.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.contact.adapter.ContactList_Adapter;
import in.hiaccounts.hinext.contact.model.ContactData;
import in.hiaccounts.hinext.contact.model.ContactDatum;
import in.hiaccounts.hinext.contact.model.RequestParam;
import in.hiaccounts.hinext.contact.model.RequestParamData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 7/25/2017.
 */

public class Activity_Contact extends AppCompatActivity implements TextWatcher {


    private static String TAG = Activity_Contact.class.getSimpleName();
    List<ContactDatum> contactList = new ArrayList<ContactDatum>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.rlSearch)
    RelativeLayout rlSearch;
    @BindView(R.id.lvContact)
    ListView lvContact;
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
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;

    @BindView(R.id.llListView)
    LinearLayout llListView;
    String serverUrl;
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String searchContact = "";
    private int pageNo=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        initCompenents();

    }

    private void initCompenents() {
        ButterKnife.bind(this);
        mActivity = this;

        edSearch.addTextChangedListener(this);
        toolbar.setTitle(getResources().getString(R.string.contact));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        HideUtil.init(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        serviceHandler = new ServiceHandler(mActivity);

        serverUrl=UtilView.getUrl(mActivity);

      /*  LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_contact, null, false);

        TextView tvName = (TextView) header.findViewById(R.id.tvName);
        TextView tvEmail = (TextView) header.findViewById(R.id.tvEmail);
        TextView tvContact = (TextView) header.findViewById(R.id.tvContact);
        TextView tvEdit = (TextView) header.findViewById(R.id.tvEdit);


        tvName.setText(getResources().getString(R.string.c_name));
        tvContact.setText(getResources().getString(R.string.c_number));
        tvEmail.setText(getResources().getString(R.string.c_email));
        tvEdit.setText("Action");
        UtilView.setTextAppearanceSmall(mActivity, tvName);
        UtilView.setTextAppearanceSmall(mActivity, tvEmail);
        UtilView.setTextAppearanceSmall(mActivity, tvContact);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEmail);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvContact);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        UtilView.setTexttypeFace(null, Typeface.BOLD, tvName);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvEmail);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvContact);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvEdit);

        if (lvContact!=null)
            lvContact.addHeaderView(header);*/


        getContactFromserver(searchContact, true, false, 0, false, false);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getContactFromserver(edSearch.getText().toString().trim(), true, false, 0, false, false);
                }
                return handled;
            }
        });

    }

    private void getContactFromserver(String search, boolean isFirst, boolean isPrev, int pageNumber, boolean isNext, boolean isLast) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {

            String url = "";
            if (search.equals("")) {
                url = serverUrl + "/hipos//0/getPaginatedContactListSearch?searchContactText=&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
            }
            if (!search.equals("")) {
                url = serverUrl + "/hipos//0/getPaginatedContactListSearch?searchContactText=" + search.replace(" ", "%20") + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
            }

            if (serverUrl==null){
                UtilView.gotToLogin(mActivity);
            }else {
                UtilView.showProgessBar(mActivity, progressBar);

                RequestParam param = new RequestParam(isFirst, isLast, pageNumber, isPrev, isNext);
                RequestParamData dataParam = new RequestParamData();
                dataParam.setParams(param);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        HideUtil.init(mActivity);

                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(mActivity, Activity_Login.class);
                                mActivity.startActivity(intent);
                                mActivity.finish();
                            } else {
                                Gson gson = new Gson();
                                contactList.clear();
                                List<ContactDatum> list = new ArrayList<>();
                                try {

                                    ContactData data = gson.fromJson(result.toString(), ContactData.class);
                                    if (data != null) {
                                        if (lvContact != null) {
                                            setUpView(data);
                                            if (data.getData() != null && data.getData().size() > 0) {
                                                llListView.setVisibility(View.VISIBLE);
                                                list = data.getData();
                                                contactList.addAll(list);
                                                ContactList_Adapter contactrListAdapter = new ContactList_Adapter(mActivity, contactList);
                                                lvContact.setAdapter(contactrListAdapter);
                                                contactrListAdapter.notifyDataSetChanged();
                                            } else {

                                                llListView.setVisibility(View.GONE);
                                                contactList.clear();
                                                ContactList_Adapter contactrListAdapter = new ContactList_Adapter(mActivity, contactList);
                                                lvContact.setAdapter(contactrListAdapter);
                                                contactrListAdapter.notifyDataSetChanged();
                                               // edSearch.setText("");
                                                UtilView.showErrorDialog(getResources().getString(R.string.nocontact_available), mActivity);

                                            }
                                        }

                                    } else {
                                        UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                    }
                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                }
                            }
                        }
                    }

                }, false);
                task.execute(url, "");
            }
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
        }

    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast,R.id.rlSearch})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.rlSearch:
                searchContact = edSearch.getText().toString().trim();
                getContactFromserver(searchContact, true, false, 0, false, false);

                break;

            case R.id.tvFirst:
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    pageNo = 0;
                    getContactFromserver("", true, false, 0, false, false);
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
                break;
            case R.id.tvPrev:
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {

                    if (pageNo > 0) {
                        pageNo = pageNo - 1;
                    }
                    getContactFromserver("", false, true, pageNo, false, false);


                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
                break;
            case R.id.tvNext:
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    pageNo = pageNo + 1;
                    getContactFromserver("", false, false, pageNo, true, false);
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
                break;
            case R.id.tvLast:
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    pageNo = 0;
                    getContactFromserver("", false, false, 0, false, true);
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_ADDCONTACT && resultCode == RESULT_OK) {
            getContactFromserver("", true, false, 0, false, false);
        }
        if (requestCode == Constant.RESQUSTCODE_EDITCONTACT && resultCode == RESULT_OK) {
            getContactFromserver("", true, false, 0, false, false);
        }
    }

    private void setUpView(ContactData data) {

        if (rlContent != null)
            rlContent.setVisibility(View.VISIBLE);
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);

        if (tvFirst != null) {
            if (data.isFirst()) {
                //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);

            }
            if (!data.isFirst()) {
                // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }

        if (tvNext != null) {
            if (data.isNext()) {
                // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);

            }
            if (!data.isNext()) {
                //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }

        if (tvPrev != null) {
            if (data.isPrev()) {
                //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);

            }
            if (!data.isPrev()) {
                //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }

        if (tvLast != null) {
            if (data.isLast()) {
                //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
            if (!data.isLast()) {
                //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }
        pageNo = data.getPageNo();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.inventory_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.addMenu:
                Intent intent = new Intent(this, Activity_AddContact.class);
                intent.putExtra("callingFrom", getResources().getString(R.string.title_addcontact));
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDCONTACT);

                break;


        }
        return super.onOptionsItemSelected(item);
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
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            getContactFromserver("", true, false, 0, false, false);

        }
    }


}
