package in.hiaccounts.hinext.controlpanel.activity;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.cart.AddCartDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_Config_Cart extends AppCompatActivity implements TextWatcher {
    public static String TAG = Activity_Config_Cart.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.edName)
    EditText edName;
    @BindView(R.id.llName)
    LinearLayout llName;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.edUserName)
    EditText edUserName;
    @BindView(R.id.llUserName)
    LinearLayout llUserName;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.llPassword)
    LinearLayout llPassword;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.edUrl)
    EditText edUrl;
    @BindView(R.id.llUrl)
    LinearLayout llUrl;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.edApi)
    EditText edApi;
    @BindView(R.id.llApi)
    LinearLayout llApi;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;
    @BindView(R.id.llstatus)
    LinearLayout llstatus;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    String callingFor,serverUrl,status;
    private AddCartDatum cartDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_cart);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_project_status));
        Intent intent = getIntent();
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");


            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITCART)) {
                    cartDetails = (AddCartDatum) intent.getSerializableExtra("cartData");

                    if (cartDetails != null) {
                        if (cartDetails.getCartName() != null)
                            edName.setText(cartDetails.getCartName());

                        if (cartDetails.getUserName() != null)
                            edUserName.setText(cartDetails.getUserName());

                        if (cartDetails.getPassword() != null)
                            edPassword.setText(cartDetails.getPassword());

                        if (cartDetails.getUrl() != null)
                            edUrl.setText(cartDetails.getUrl());

                        if (cartDetails.getApiName() != null)
                            edApi.setText(cartDetails.getApiName());

                        if (cartDetails.getStatus() !=null){
                            if (cartDetails.getStatus().equals("Active"))
                            {
                                spinnerStatus.setSelection(0);
                            }
                            if (cartDetails.getStatus().equals("InActive"))
                            {
                                spinnerStatus.setSelection(1);
                            }
                        }


                    }

                }
            }
        }

        getCountryDetails();

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        edName.addTextChangedListener(this);
        edUserName.addTextChangedListener(this);

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void getCountryDetails() {

    }


    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addCart();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addCart() {
        final String name = edName.getText().toString().trim();
        String userName = edUserName.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        String urls = edUrl.getText().toString().trim();
        String api = edApi.getText().toString().trim();


        if (!name.equals("")  && !userName.equals("") && !password.equals("")) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {

                UtilView.showProgessBar(activity, progressBar);
                AddCartDatum addCartDatum = new AddCartDatum();
                if (callingFor.equals(Constant.CALL_EDITCART)) {
                    addCartDatum.setCartId(cartDetails.getCartId());
                }
                addCartDatum.setCartName(name);
                addCartDatum.setUserName(userName);
                addCartDatum.setPassword(password);
                addCartDatum.setUrl(urls);
                addCartDatum.setApiName(api);
                addCartDatum.setStatus((String) spinnerStatus.getSelectedItem());


                final Gson gson = new Gson();
                String itemJson = gson.toJson(addCartDatum);
                String url = "";
                if (url != null) {
                    url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDCART;
                }
                if (serverUrl != null) {
                    PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);
                            if (result != null) {
                                try {
                                    List<AddCartDatum> itemsLists=new ArrayList<>();

                                    AddCartDatum item=gson.fromJson(result.toString(),AddCartDatum.class);
                                    itemsLists.add(item);
                                    UtilView.showToast(activity, name + "create successfully.");
                                }catch (Exception e){

                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                                }

                                Intent returnIntent = new Intent();
                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                activity.finish();


                            } else {
                                UtilView.showToast(activity, name + " doesn't create successfully.");
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
            if (name == null || name.equals("")) {
                edName.setError(getResources().getString(R.string.err_msg));
            }

            if (userName == null || userName.equals("")) {
                edUserName.setError(getResources().getString(R.string.err_msg));
            }
            if (password == null || password.equals("")) {
                edPassword.setError(getResources().getString(R.string.err_msg));
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
