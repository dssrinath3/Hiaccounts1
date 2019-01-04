package in.hiaccounts.hinext.controlpanel.fragment.useraccountsetup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
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
import in.hiaccounts.hinext.controlpanel.activity.Activity_Config_User;
import in.hiaccounts.hinext.controlpanel.activity.Activity_Config_UserPermission;
import in.hiaccounts.hinext.controlpanel.activity.Activity_Config_UserPermission1;
import in.hiaccounts.hinext.controlpanel.adapter.UserAdapter;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.UserAccountData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_UserActSetup extends Fragment implements TextWatcher {


    public static String TAG = Fragment_UserActSetup.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    Unbinder unbinder;

    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private List<Object> userList = new ArrayList<Object>();
    private UserAdapter userAdapter;
    private String search = "", serverUrl;

    public static Fragment_UserActSetup newInstance() {
        Fragment_UserActSetup fragment = new Fragment_UserActSetup();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_useractsetup, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fabAdd, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabAdd:

                Intent intent = new Intent(mActivity, Activity_Config_User.class);
                intent.putExtra("callingFor", Constant.CALL_ADDUSER);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDUSER);

                break;
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString().trim();
                getUserFromServer();
                break;
        }
    }


    private void initComponents(View view) {
        serverUrl = UtilView.getUrl(mActivity);

        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.useraccount_adapter_user, null, false);

        TextView tvUserName = header.findViewById(R.id.tvUserName);
        TextView tvEmail = header.findViewById(R.id.tvEmail);
        TextView tvInventory = header.findViewById(R.id.tvInventory);
        TextView tvEdit = header.findViewById(R.id.tvEdit);

        tvUserName.setText("User Name");
        tvEmail.setText("Email");
        tvInventory.setText("Inv. Location");
        tvEdit.setText("Action");

        UtilView.setTextAppearanceSmall(mActivity, tvUserName);
        UtilView.setTextAppearanceSmall(mActivity, tvEmail);
        UtilView.setTextAppearanceSmall(mActivity, tvInventory);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);


        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvUserName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEmail);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvInventory);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        if (listview != null)
            listview.addHeaderView(header);
        if (fabAdd != null)
            fabAdd.setVisibility(View.VISIBLE);


        edSearch.addTextChangedListener(this);

        getUserFromServer();

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getUserFromServer();
                }
                return handled;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (view.getId() == R.id.imgviewEdit) {
                    if (userList != null) {
                        Intent intent = new Intent(mActivity, Activity_Config_User.class);
                        intent.putExtra("callingFor", Constant.CALL_EDITUSER);
                        intent.putExtra("userData", (UserAccountData) userList.get(position));
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITUSER);
                    }
                }

                if (view.getId() == R.id.imgviewPermission) {
                    if (userList != null) {
                        Intent intent = new Intent(mActivity, Activity_Config_UserPermission1.class);
                        intent.putExtra("userData", (UserAccountData) userList.get(position));
                        startActivityForResult(intent, Constant.RESQUSTCODE_USERPERMISSION);
                    }
                }
            }
        });


    }




    private void getUserFromServer() {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETUSERLIST+"&type=Active";

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);

                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(mActivity, Activity_Login.class);
                                mActivity.startActivity(intent);
                                mActivity.finish();
                            } else {

                                Gson gson = new Gson();
                                userList.clear();
                                List<UserAccountData> list = new ArrayList<>();
                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        UserAccountData user = gson.fromJson(jsonObject.toString(), UserAccountData.class);
                                        list.add(user);

                                    }
                                    if (list.size() > 0) {
                                        userList.addAll(list);
                                        userAdapter = new UserAdapter(mActivity, userList);
                                        listview.setAdapter(userAdapter);
                                        userAdapter.notifyDataSetChanged();
                                    } else {
                                        userList.clear();
                                        userAdapter = new UserAdapter(mActivity, userList);
                                        listview.setAdapter(userAdapter);
                                        userAdapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog(getResources().getString(R.string.user_notavailbale), mActivity);

                                    }


                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url + search.replace(" ", "%20"), "");
                //task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
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
            progressBar.setVisibility(View.VISIBLE);
            search = "";
            getUserFromServer();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDUSER) {
            getUserFromServer();

        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITUSER) {
            getUserFromServer();

        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_USERPERMISSION) {
            getUserFromServer();

        }

    }
}
