package in.hiaccounts.hinext.restaurant.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mocoo.hang.rtprinter.driver.HsBluetoothPrintDriver;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class Activity_printer_configurations extends AppCompatActivity {
    private static final String TAG = "printer_configurations";

    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.kotPrinter1)
    TextView kotPrinter1;
    @BindView(R.id.spinnerKotPrinter1)
    Spinner spinnerKotPrinter1;
    @BindView(R.id.radioBtn3PosKot1)
    RadioButton radioBtn3PosKot1;
    @BindView(R.id.radioBtn2PosKot1)
    RadioButton radioBtn2PosKot1;
    @BindView(R.id.radioGroupKot1)
    RadioGroup radioGroupKot1;
    @BindView(R.id.kotPrinter2)
    TextView kotPrinter2;
    @BindView(R.id.spinnerKotPrinter2)
    Spinner spinnerKotPrinter2;
    @BindView(R.id.radioBtn3PosKot2)
    RadioButton radioBtn3PosKot2;
    @BindView(R.id.radioBtn2PosKot2)
    RadioButton radioBtn2PosKot2;
    @BindView(R.id.radioGroupKot2)
    RadioGroup radioGroupKot2;
    @BindView(R.id.billPrinter)
    TextView billPrinter;
    @BindView(R.id.spinnerBillPrinter)
    Spinner spinnerBillPrinter;
    @BindView(R.id.radioBtn3PosBill)
    RadioButton radioBtn3PosBill;
    @BindView(R.id.radioBtn2PosBill)
    RadioButton radioBtn2PosBill;
    @BindView(R.id.radioGroupBill)
    RadioGroup radioGroupBill;
    @BindView(R.id.spinnerPrinter)
    Spinner spinnerPrinter;
    @BindView(R.id.radioBtn3Pos)
    RadioButton radioBtn3Pos;
    @BindView(R.id.radioBtn2Pos)
    RadioButton radioBtn2Pos;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.singlePrinter)
    LinearLayout singlePrinterLayout;
    @BindView(R.id.multiplePrinter)
    LinearLayout multiplePrinterLayout;
    private Activity mActivity;
    public static HsBluetoothPrintDriver BLUETOOTH_PRINTER = null;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl;
    private int pageNo = 0;
    private String callingFrom = "", printerTypeKot1 = "", printerTypeKot2 = "", printerTypeBill = "";
    private BluetoothAdapter mBluetoothAdapter = null;
    private static BluetoothDevice device;
    private static final boolean D = true;
    // Member fields
    private BluetoothAdapter mBtAdapter;
    List<String> pairKotDevices;
    List<String> pairBillDevices;
    List<String> unPairedDevices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_configurations);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mActivity = this;
        serverUrl = UtilView.getUrl(mActivity);
        toolbar.setTitle(getResources().getString(R.string.select_printer));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sharedPreference = SharedPreference.getInstance(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        try {

            String singlePrinter = sharedPreference.getData(Constant.SINGLE_PRINTER);
            String multiPrinter = sharedPreference.getData(Constant.MULTIPLE_PRINTER);

            if (singlePrinter!=null && singlePrinter.equals("singlePrinter")){

                singlePrinterLayout.setVisibility(View.VISIBLE);
                multiplePrinterLayout.setVisibility(View.GONE);
                String billType = sharedPreference.getData(Constant.BLUETOOTHTYPEBILL);
                if (billType != null && billType.equals("2-inch")) {
                    radioBtn2Pos.setChecked(true);
                } else if (billType != null && billType.equals("3-inch")) {
                    radioBtn3Pos.setChecked(true);
                }
            }else if (multiPrinter!=null && multiPrinter.equals("multiplePrinter")){
                singlePrinterLayout.setVisibility(View.GONE);
                multiplePrinterLayout.setVisibility(View.VISIBLE);
                String kotType1 = sharedPreference.getData(Constant.BLUETOOTHTYPEKOT1);
                if (kotType1 != null && kotType1.equals("kot1-2-inch")) {
                    radioBtn2PosKot1.setChecked(true);
                } else if (kotType1 != null && kotType1.equals("kot1-3-inch")) {
                    radioBtn3PosKot1.setChecked(true);
                }

                String kotType2 = sharedPreference.getData(Constant.BLUETOOTHTYPEKOT2);
                if (kotType2 != null && kotType2.equals("kot2-2-inch")) {
                    radioBtn2PosKot2.setChecked(true);
                } else if (kotType2 != null && kotType2.equals("kot2-3-inch")) {
                    radioBtn3PosKot2.setChecked(true);
                }

                String billType = sharedPreference.getData(Constant.BLUETOOTHTYPEBILL);
                if (billType != null && billType.equals("2-inch")) {
                    radioBtn2PosBill.setChecked(true);
                } else if (billType != null && billType.equals("3-inch")) {
                    radioBtn3PosBill.setChecked(true);
                }
            }


        } catch (Exception e) {
            Log.e("setchecked", e.toString());
        }


        // Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);

        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);

        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // Get a set of currently paired devices
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        pairKotDevices = new ArrayList<>();
        pairBillDevices = new ArrayList<>();
        pairKotDevices.add("Select");
        pairBillDevices.add("Select");
        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                pairKotDevices.add(device.getName() + "," + device.getAddress());
                pairBillDevices.add(device.getName() + "," + device.getAddress());
            }

        } else {
            String noDevices = getResources().getText(R.string.none_paired).toString();
            pairKotDevices.add(noDevices);
            pairBillDevices.add(noDevices);
        }


        String singlePrinter = sharedPreference.getData(Constant.SINGLE_PRINTER);
        String multiPrinter = sharedPreference.getData(Constant.MULTIPLE_PRINTER);

        if (singlePrinter!=null && singlePrinter.equals("singlePrinter")){
            singlePrinterLayout.setVisibility(View.VISIBLE);
            multiplePrinterLayout.setVisibility(View.GONE);
            if (pairBillDevices != null && pairBillDevices.size() > 0) {
                //String[] billPrinerNames={"Bp201","Bluetooth printer","MoobilePrinter"};
                ArrayAdapter<String> bill = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pairBillDevices);
                bill.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPrinter.setAdapter(bill);

                spinnerPrinter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(mActivity, ""+ pairBillDevices.get(position), Toast.LENGTH_SHORT).show();
                        try {
                            String[] billPrintAddress = pairBillDevices.get(position).split(",");
                            sharedPreference.saveData(Constant.BILL_PRINTER, pairBillDevices.get(position));
                            sharedPreference.saveData(Constant.BLUETOOTHADDRESSBILL, billPrintAddress[1]);

                        /* device = mBluetoothAdapter.getRemoteDevice(billPrintAddress[1]);
                        BLUETOOTH_PRINTER.start();
                        BLUETOOTH_PRINTER.connect(device);*/

                        } catch (Exception e) {
                            Log.e("billprinter", e.toString());
                        }


                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                String billaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSBILL);
                String billname = sharedPreference.getData(Constant.BILL_PRINTER);

                Log.e("compareBill1", billname);
                String compareBill = billname;
                if (compareBill != null && !compareBill.equals("")) {
                    String compareBill2 = compareBill;
                    spinnerPrinter.setSelection(((ArrayAdapter<String>) spinnerPrinter.getAdapter()).getPosition(compareBill2));

                }
            }
        }else if (multiPrinter!=null && multiPrinter.equals("multiplePrinter")){
            singlePrinterLayout.setVisibility(View.GONE);
            multiplePrinterLayout.setVisibility(View.VISIBLE);
            if (pairKotDevices != null && pairKotDevices.size() > 0) {
                ArrayAdapter<String> kot = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pairKotDevices);
                kot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKotPrinter1.setAdapter(kot);

                spinnerKotPrinter1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(mActivity, ""+ pairKotDevices.get(position), Toast.LENGTH_SHORT).show();

                        try {
                            sharedPreference.saveData(Constant.KOT_PRINTER1, pairKotDevices.get(position));
                            String[] kotPrintAddress = pairKotDevices.get(position).split(",");
                            if (kotPrintAddress != null) {
                                Log.e("kotprinter1", kotPrintAddress[1]);
                                sharedPreference.saveData(Constant.BLUETOOTHADDRESSKOT1, kotPrintAddress[1]);


                            }

                        } catch (Exception e) {
                            Log.e("kotprinter1", e.toString());
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                String kotname = sharedPreference.getData(Constant.KOT_PRINTER1);
                Log.e("compareValue1", kotname);
                String compareValue = kotname;
                if (compareValue != null && !compareValue.equals("")) {
                    String compareValue2 = compareValue;
                    spinnerKotPrinter1.setSelection(((ArrayAdapter<String>) spinnerKotPrinter1.getAdapter()).getPosition(compareValue2));

                }
            }

            if (pairKotDevices != null && pairKotDevices.size() > 0) {
                ArrayAdapter<String> kot = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pairKotDevices);
                kot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKotPrinter2.setAdapter(kot);

                spinnerKotPrinter2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(mActivity, ""+ pairKotDevices.get(position), Toast.LENGTH_SHORT).show();

                        try {
                            sharedPreference.saveData(Constant.KOT_PRINTER2, pairKotDevices.get(position));
                            String[] kotPrintAddress = pairKotDevices.get(position).split(",");
                            if (kotPrintAddress != null) {
                                Log.e("kotprinter2", kotPrintAddress[1]);
                                sharedPreference.saveData(Constant.BLUETOOTHADDRESSKOT2, kotPrintAddress[1]);


                            }

                        } catch (Exception e) {
                            Log.e("kotprinter2", e.toString());
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                String kotname = sharedPreference.getData(Constant.KOT_PRINTER2);
                Log.e("compareValue1", kotname);
                String compareValue = kotname;
                if (compareValue != null && !compareValue.equals("")) {
                    String compareValue2 = compareValue;
                    spinnerKotPrinter2.setSelection(((ArrayAdapter<String>) spinnerKotPrinter2.getAdapter()).getPosition(compareValue2));

                }
            }

            if (pairBillDevices != null && pairBillDevices.size() > 0) {
                //String[] billPrinerNames={"Bp201","Bluetooth printer","MoobilePrinter"};
                ArrayAdapter<String> bill = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pairBillDevices);
                bill.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBillPrinter.setAdapter(bill);

                spinnerBillPrinter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(mActivity, ""+ pairBillDevices.get(position), Toast.LENGTH_SHORT).show();
                        try {
                            String[] billPrintAddress = pairBillDevices.get(position).split(",");
                            sharedPreference.saveData(Constant.BILL_PRINTER, pairBillDevices.get(position));
                            sharedPreference.saveData(Constant.BLUETOOTHADDRESSBILL, billPrintAddress[1]);

                        /* device = mBluetoothAdapter.getRemoteDevice(billPrintAddress[1]);
                        BLUETOOTH_PRINTER.start();
                        BLUETOOTH_PRINTER.connect(device);*/

                        } catch (Exception e) {
                            Log.e("billprinter", e.toString());
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                String billaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSBILL);
                String billname = sharedPreference.getData(Constant.BILL_PRINTER);

                Log.e("compareBill1", billname);
                String compareBill = billname;
                if (compareBill != null && !compareBill.equals("")) {
                    String compareBill2 = compareBill;
                    spinnerBillPrinter.setSelection(((ArrayAdapter<String>) spinnerBillPrinter.getAdapter()).getPosition(compareBill2));

                }
            }
        }






    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
    }

    /**
     * The BroadcastReceiver that listens for discovered devices and
     * changes the title when discovery is finished
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                pairKotDevices = new ArrayList<>();
                pairBillDevices = new ArrayList<>();
                pairKotDevices.add("Select");
                pairBillDevices.add("Select");
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {

                    pairKotDevices.add(device.getName() + "," + device.getAddress());

                    pairBillDevices.add(device.getName() + "," + device.getAddress());
                }
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_device);
                if (pairKotDevices.size() == 0) {
                    String noDevices = getResources().getText(R.string.none_found).toString();
                    pairKotDevices.add(noDevices);
                }
                if (pairBillDevices.size() == 0) {
                    String noDevices = getResources().getText(R.string.none_found).toString();
                    pairBillDevices.add(noDevices);
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Make sure we're not doing discovery anymore
        if (mBtAdapter != null) {
            mBtAdapter.cancelDiscovery();
        }

        // Unregister broadcast listeners
        // unregisterReceiver(mReceiver);
    }

    /**
     * Start device discover with the BluetoothAdapter
     */
    private void doDiscovery() {
        if (D) Log.d(TAG, "doDiscovery()");

        // Indicate scanning in the title
        setProgressBarIndeterminateVisibility(true);
        setTitle(R.string.scanning);

        // Turn on sub-title for new devices
        findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);

        // If we're already discovering, stop it
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }

        // Request discover from BluetoothAdapter
        mBtAdapter.startDiscovery();
    }


    public void onRadioButtonKot1Clicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioBtn2PosKot1:
                if (checked) {
                    printerTypeKot1 = "kot1-2-inch";
                    sharedPreference.saveData(Constant.BLUETOOTHTYPEKOT1, printerTypeKot1);
                    String kotType1 = sharedPreference.getData(Constant.BLUETOOTHTYPEKOT1);
                    Log.e("kotType..bill1", String.valueOf(kotType1));
                }
                break;
            case R.id.radioBtn3PosKot1:
                if (checked) {
                    printerTypeKot1 = "kot1-3-inch";
                    sharedPreference.saveData(Constant.BLUETOOTHTYPEKOT1, printerTypeKot1);
                    String kotType1 = sharedPreference.getData(Constant.BLUETOOTHTYPEKOT1);
                    Log.e("kotType..bill1", String.valueOf(kotType1));
                }
                break;


        }
    }


    public void onRadioButtonKot2Clicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {

            case R.id.radioBtn3PosKot2:
                if (checked) {
                    printerTypeKot2 = "kot2-3-inch";
                    sharedPreference.saveData(Constant.BLUETOOTHTYPEKOT2, printerTypeKot2);
                    String kotType1 = sharedPreference.getData(Constant.BLUETOOTHTYPEKOT2);
                    Log.e("kotType..bill2", String.valueOf(kotType1));
                }
                break;
            case R.id.radioBtn2PosKot2:
                if (checked) {
                    printerTypeKot2 = "kot2-2-inch";
                    sharedPreference.saveData(Constant.BLUETOOTHTYPEKOT2, printerTypeKot2);
                    String kotType1 = sharedPreference.getData(Constant.BLUETOOTHTYPEKOT2);
                    Log.e("kotType..bill2", String.valueOf(kotType1));
                }
                break;

        }
    }

    public void onRadioButtonBillClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioBtn3PosBill:
                if (checked) {
                    printerTypeBill = "3-inch";
                    sharedPreference.saveData(Constant.BLUETOOTHTYPEBILL, printerTypeBill);
                }

                break;
            case R.id.radioBtn2PosBill:
                if (checked) {
                    printerTypeBill = "2-inch";
                    sharedPreference.saveData(Constant.BLUETOOTHTYPEBILL, printerTypeBill);
                }
                break;
        }
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioBtn3Pos:
                if (checked) {
                    printerTypeBill = "3-inch";
                    sharedPreference.saveData(Constant.BLUETOOTHTYPEBILL, printerTypeBill);
                }

                break;
            case R.id.radioBtn2Pos:
                if (checked) {
                    printerTypeBill = "2-inch";
                    sharedPreference.saveData(Constant.BLUETOOTHTYPEBILL, printerTypeBill);
                }
                break;
        }
    }

}
