package in.hiaccounts.hinext.inventory.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.customer.model.CustomerList;
import in.hiaccounts.hinext.inventory.model.inventorylocation.InventoryLocation;
import in.hiaccounts.hinext.inventory.model.inventorylocation.StateDTOList;
import in.hiaccounts.hinext.supplier.activity.Activity_Supplier;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 5/17/2017.
 */

public class Activity_InventoryLocation extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edLocationName)
    EditText edLocationName;
    @BindView(R.id.edLocationPrefix)
    EditText edLocationPrefix;

    @BindView(R.id.soinnerState)
    Spinner soinnerState;
    @BindView(R.id.edLocationCPerson)
    EditText edLocationCPerson;
    @BindView(R.id.edLocationAddress)
    EditText edLocationAddress;
    @BindView(R.id.edLocationEmail)
    EditText edLocationEmail;
    @BindView(R.id.edLocationContactNo)
    EditText edLocationContactNo;
    @BindView(R.id.edLocationFaxNo)
    EditText edLocationFaxNo;
    @BindView(R.id.edLocationGSTIN)
    EditText edLocationGSTIN;
    @BindView(R.id.edLocationSupplier)
    EditText edLocationSupplier;
    @BindView(R.id.edLocationCustomer)
    EditText edLocationCustomer;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;


    public static String TAG = Activity_InventoryLocation.class.getSimpleName();
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    List<Object> objectStateList = new ArrayList<Object>();
    StateDTOList stateDto;
    private CustomerList selected_customer = null;
    private GetSupplier supplier = null;
    Long customerId, locationId;
    String callingFor, serverUrl,supplierID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_activity_invlocation);
        initContentView();
    }

    private void initContentView() {
        ButterKnife.bind(this);
        activity = this;
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));

        Intent intent = getIntent();
        callingFor = intent.getStringExtra("callingfor");
        if (callingFor != null) {
            if (callingFor.equals("add")) {
                toolbar.setTitle(getResources().getString(R.string.inventory_addinventorylocation));
            }
            if (callingFor.equals("edit")) {
                toolbar.setTitle(getResources().getString(R.string.inventory_editinventorylocation));
            }
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getInventoryLocationData();

        soinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectStateList.get(i);
                if (obj instanceof StateDTOList) {
                    soinnerState.setSelection(i);
                    stateDto = (StateDTOList) soinnerState.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void getInventoryLocationData() {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORYLOCATIONDETAILS;

        if (serverUrl != null) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {
                // prepare the Request
                UtilView.showProgessBar(activity, progressBar);
                PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
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
                                try {
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    InventoryLocation invLocation = gson.fromJson(jsonObject.toString(), InventoryLocation.class);
                                    if (invLocation != null) {
                                        setUpPageData(invLocation);
                                    }
                                } catch (Exception e) {

                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                task.execute(new JSONObject().toString(), url, "");
                //task.execute(url, "");
            }
            if (!isInternatePrsenet) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void setUpPageData(InventoryLocation invLocation) {

        if (invLocation.getStateDTOList() != null) {
            objectStateList.clear();
            objectStateList.add("Select");
            objectStateList.addAll(invLocation.getStateDTOList());
            if (soinnerState != null)
                UtilView.setupItemAdapter(activity, soinnerState, objectStateList);
        }

        Intent intent = getIntent();

        if (intent != null) {
            callingFor = intent.getStringExtra("callingfor");
            if (callingFor != null) {
                if (callingFor.equals("add")) {
                    if (edLocationSupplier != null)
                        edLocationSupplier.setVisibility(View.VISIBLE);
                    if (edLocationCustomer != null)
                        edLocationCustomer.setVisibility(View.VISIBLE);
                }

                if (callingFor.equals("edit")) {
                    InventoryLocation location = (InventoryLocation) intent.getSerializableExtra("inventoryLocation");

                    if (edLocationSupplier != null)
                        edLocationSupplier.setVisibility(View.GONE);
                    if (edLocationCustomer != null)
                        edLocationCustomer.setVisibility(View.GONE);

                    if (location != null) {

                        locationId = location.getInventoryLocationId();

                        if (edLocationCustomer != null) {
                            edLocationCustomer.setFocusable(false);
                            edLocationCustomer.setFocusableInTouchMode(false);
                            edLocationCustomer.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                            edLocationCustomer.setText("");
                        }

                        if (edLocationSupplier != null) {
                            edLocationSupplier.setFocusable(false);
                            edLocationSupplier.setFocusableInTouchMode(false);
                            edLocationSupplier.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                            edLocationSupplier.setText("");
                        }

                        if (edLocationName != null) {
                            edLocationName.setFocusable(false);
                            edLocationName.setFocusableInTouchMode(false);
                            edLocationName.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                        }

                        if (edLocationAddress != null)
                            edLocationAddress.setText(location.getInventoryLocationAddress());
                        if (edLocationContactNo != null)
                            edLocationContactNo.setText(location.getInventoryLocationContact());
                        if (edLocationCPerson != null)
                            edLocationCPerson.setText(location.getInventoryLocationContactPerson());
                        if (edLocationEmail != null)
                            edLocationEmail.setText(location.getInventoryLocationEmail());
                        if (edLocationFaxNo != null)
                            edLocationFaxNo.setText(location.getInventoryLocationFaxNo());
                        if (edLocationGSTIN != null)
                            edLocationGSTIN.setText(location.getgSTIN());
                        if (edLocationName != null)
                            edLocationName.setText(location.getInventoryLocationName());
                        if (edLocationPrefix!=null)
                            edLocationPrefix.setText(location.getPrefix());

                        supplierID = location.getSupplierIdForLoc();
                        customerId = Long.valueOf(location.getCustomerIdForLoc());

                        if (invLocation.getStateDTOList() != null) {
                            objectStateList.clear();
                            objectStateList.add("Select");
                            objectStateList.addAll(invLocation.getStateDTOList());
                            UtilView.setupItemAdapter(activity, soinnerState, objectStateList);
                        }
                        for (int i = 0; i < invLocation.getStateDTOList().size(); i++) {
                            if (location.getStateIdForLoc() == invLocation.getStateDTOList().get(i).getId()) {
                                if (invLocation.getStateDTOList()!= null)
                                    soinnerState.setSelection(i+1);
                            }
                        }

                        if (location.getInventoryLocationStatus() != null) {
                            if (location.getInventoryLocationStatus().equals("Active")) {
                                spinnerStatus.setSelection(0);
                            }

                            if (location.getInventoryLocationStatus().equals("InActive")) {
                                spinnerStatus.setSelection(1);
                            }
                        }
                    }
                }
            }

        }


    }


    @OnClick({R.id.edLocationSupplier, R.id.edLocationCustomer, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.edLocationSupplier:
                intent = new Intent(activity, Activity_Supplier.class);
                activity.startActivityForResult(intent, Constant.RESQUSTCODE_SUPPLIER);
                break;
            case R.id.edLocationCustomer:
                intent = new Intent(activity, Activity_Customer.class);
                activity.startActivityForResult(intent, Constant.RESQUSTCODE_CUSTOMERS);

                break;
            case R.id.btnSave:

                String customerName = edLocationCustomer.getText().toString().trim();
                String SupplierName = edLocationSupplier.getText().toString().trim();
                String address = edLocationAddress.getText().toString().trim();
                String contactNumbere = edLocationContactNo.getText().toString().trim();
                String contactPerson = edLocationCPerson.getText().toString().trim();
                String email = edLocationEmail.getText().toString().trim();
                String faxNumber = edLocationFaxNo.getText().toString().trim();
                String gstin = edLocationGSTIN.getText().toString().trim();
                String locationName = edLocationName.getText().toString().trim();
                String locationPrefix = edLocationPrefix.getText().toString().trim();

                String stateName = "";
                if (stateDto != null) {
                    stateName = stateDto.getStateName();
                }

                if (locationName != null && !locationName.equals("") && stateDto != null) {
                    InventoryLocation inventoryLocation = new InventoryLocation();
                    inventoryLocation.setInventoryLocationAddress(address);
                    inventoryLocation.setInventoryLocationName(locationName);
                    inventoryLocation.setPrefix(locationPrefix);
                    inventoryLocation.setgSTIN(gstin);
                    inventoryLocation.setInventoryLocationContact(contactNumbere);
                    inventoryLocation.setInventoryLocationEmail(email);
                    inventoryLocation.setInventoryLocationContactPerson(contactPerson);
                    inventoryLocation.setInventoryLocationFaxNo(faxNumber);
                    inventoryLocation.setStateName(stateName);
                    inventoryLocation.setCompanyIdForLoc("");
                    inventoryLocation.setInventoryLocationStatus((String) spinnerStatus.getSelectedItem());


                    if (callingFor.equals("edit")) {
                        inventoryLocation.setInventoryLocationId(locationId);
                        inventoryLocation.setCustomerIdForLoc(String.valueOf(customerId));
                        inventoryLocation.setSupplierIdForLoc(supplierID);
                        inventoryLocation.setStateIdForLoc(stateDto.getId());

                    }

                    if (callingFor.equals("add")) {
                        if (stateDto != null)
                            inventoryLocation.setStateIdForLoc(stateDto.getId());

                        if (selected_customer != null) {
                            inventoryLocation.setCustomerIdForLoc(String.valueOf(selected_customer.getCustomerId()));
                        }
                        if (supplier != null) {
                            inventoryLocation.setSupplierIdForLoc(String.valueOf(supplier.getSupplierId()));
                        }

                    }

                    UtilView.showProgessBar(activity, progressBar);
                    String url = serverUrl + "/hipos//0/" + Constant.FUNTION_SAVEINVENTORYLOCATION;
                    if (serverUrl != null) {
                        isInternatePrsenet = serviceHandler.isConnectingToInternet();
                        if (isInternatePrsenet) {
                            PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.hideProgessBar(activity, progressBar);
                                    if (result != null) {
                                        if (result.equals("invalid")) {
                                            UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                            Intent intent = new Intent(activity, Activity_Login.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            if (result.equals(getString(R.string.error_rsonsecode204))) {
                                                UtilView.showToast(activity, "Server Error. Please try again");
                                            } else {
                                                Gson gson = new Gson();
                                                try {
                                                    InventoryLocation resutlLocation = gson.fromJson(result.toString(), InventoryLocation.class);
                                                    if (resutlLocation != null) {
                                                        UtilView.showToast(activity, "Inventory Location add Successfully");
                                                        Intent returnIntent = new Intent();
                                                        activity.setResult(Activity.RESULT_OK, returnIntent);
                                                        activity.finish();
                                                    } else {
                                                        UtilView.showToast(activity, "Inventory Location can't add. Please try again.");
                                                    }


                                                } catch (Exception e) {
                                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                                }
                                            }
                                        }
                                    } else {
                                        UtilView.showToast(activity, getResources().getString(R.string.response_error));
                                    }

                                }
                            }, false);
                            postDataTask.execute(new Gson().toJson(inventoryLocation), url, "");

                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                        }

                    } else {
                        UtilView.gotToLogin(activity);
                    }
                } else {

                    if (stateDto == null) {
                        TextView tv = (TextView) soinnerState.getSelectedView();
                        if (tv != null)
                            tv.setError(getString(R.string.err_msg));

                    }
                    if (locationName == null || locationName.equals("")) {
                        if (edLocationName != null)
                            edLocationName.setError(getString(R.string.err_msg));
                    }
                    if (locationPrefix == null || locationPrefix.equals("") || locationPrefix.length() < 4) {
                        if (edLocationPrefix != null)
                            edLocationPrefix.setError("Allow first 3 charecters only");
                    }


                }


                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_SUPPLIER && resultCode == RESULT_OK) {
            supplier = (GetSupplier) data.getSerializableExtra("supplier");
            if (supplier != null) {
                if (supplier.getSupplierName() != null)
                    edLocationSupplier.setText(supplier.getSupplierName());
                edLocationCustomer.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                edLocationCustomer.setFocusable(false);
                edLocationCustomer.setFocusableInTouchMode(false);
            }

        }
        if (requestCode == Constant.RESQUSTCODE_CUSTOMERS && resultCode == RESULT_OK) {
            selected_customer = (CustomerList) data.getSerializableExtra("customer");
            if (selected_customer != null) {


                edLocationSupplier.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                edLocationSupplier.setFocusable(false);
                edLocationSupplier.setFocusableInTouchMode(false);

                if (selected_customer.getCustomerEmail() == null) {
                    selected_customer.setCustomerEmail("");
                }

                if (selected_customer.getCustomerContact() == null) {
                    selected_customer.setCustomerNumber("");
                }

                if (selected_customer.getCustomerContact() == null) {
                    selected_customer.setCustomerContact("");
                }
                if (selected_customer.getCustomerName() != null)
                    edLocationCustomer.setText(selected_customer.getCustomerName() + "|" + selected_customer.getCustomerNumber());
            }
        }
    }
}


