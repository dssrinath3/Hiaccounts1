package in.hiaccounts.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.ProgressView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.company_setup.model.BuisnessType;
import in.hiaccounts.hinext.company_setup.model.CountryList;
import in.hiaccounts.hinext.company_setup.model.CurrencyList;
import in.hiaccounts.hinext.company_setup.model.IndustryClassificationList;
import in.hiaccounts.hinext.company_setup.model.StateList;
import in.hiaccounts.hinext.controlpanel.model.accountmaintance.AccountData;
import in.hiaccounts.hinext.controlpanel.model.accountmaintance.ChartAccountDatum;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.currency.CurrencyDatum;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.TableDataList;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.LocationId;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.Location;
import in.hiaccounts.hinext.customer.model.CountryDTOList;
import in.hiaccounts.hinext.customer.model.CurrencyDTOList;
import in.hiaccounts.hinext.customer.model.StateDTOList;
import in.hiaccounts.hinext.item.model.ItemBrandDTOList;
import in.hiaccounts.hinext.item.model.ItemCategoryDTOList;
import in.hiaccounts.hinext.item.model.ItemCountTypeDTOList;
import in.hiaccounts.hinext.item.model.ItemIPTaxDTOList;
import in.hiaccounts.hinext.item.model.ItemMSICDTOList;
import in.hiaccounts.hinext.item.model.ItemOPTaxDTOList;
import in.hiaccounts.hinext.item.model.ItemTypeDTOList;
import in.hiaccounts.hinext.item.model.ItemUOMTypeDTOList;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.SupplocationList;
import in.hiaccounts.hinext.reports.model.LocationList;
import in.hiaccounts.hinext.reports.model.SalesList;
import in.hiaccounts.hinext.restaurant.model.category_item.RestuarentTableData;
import in.hiaccounts.hinext.sales.model.sales_pagedata.CmpylocationList;
import in.hiaccounts.hinext.sales.model.sales_pagedata.CustlocationList;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxClassId;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxGroupId;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxTypeDatum;
import in.hiaccounts.model.pos.SerializableItemsDTOList;
import in.hiaccounts.model.retailpos.TaxList;


/**
 * Created by Prateek on  21/12/16.
 */
public class UtilView {

    public static final String PREFS_NAME = "ITEMNAMESEARCH";

    public static final String KEY_ITEMNAME = "ItemName";

    public static final int REQUEST_CODE = 1234;

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    public static ProgressDialog showProgressDialog(Activity context) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait ...");

        return progressDialog;
    }

    public static void showToast(Activity context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLogCat(String tag, String message) {
        Log.e("@Flow " + tag + ": ", message);
    }

    public static void hideSoftKeyboard(Activity activity, View view) {
        Context c = activity.getBaseContext();
        View v = view.findFocus();
        if (v == null)
            return;
        InputMethodManager inputManager = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    public static void gotToLogin(Activity mActivity) {


        UtilView.showToast(mActivity,"Session Expired. Please Login again.");
        SharedPreference sharedPreference=SharedPreference.getInstance(mActivity);


        sharedPreference.setRemovePrefrence(Constant.LOGINDATA_KEY);
        sharedPreference.setRemovePrefrence(Constant.LOGINDETAIL);
        sharedPreference.setRemovePrefrence(Constant.ACCESSTOKEN);
        sharedPreference.setRemovePrefrence(Constant.USERNAME);
        sharedPreference.setRemovePrefrence(Constant.SERVER_URL);
        Intent intent = new Intent(mActivity, Activity_Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mActivity.startActivity(intent);
        mActivity.finish();


    }

    public static void showProgessBar(Activity mActivity, ProgressView progressBar) {
        if (progressBar!=null){
            progressBar.setVisibility(View.VISIBLE);
            mActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public static void hideProgessBar(Activity mActivity, ProgressView progressBar) {
        if (progressBar!=null){
            progressBar.setVisibility(View.GONE);
            mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

    }

    public static String setCurrentDate(EditText edDate) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        edDate.setText(String.valueOf(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year)));
        return f.format(calendar.getTime());
    }


    public static String getUrl(Activity mActivity){
        SharedPreference sharedPreference=SharedPreference.getInstance(mActivity);
        String base_url= sharedPreference.getData(Constant.SERVER_URL);
        return base_url;
    }


    public static ArrayAdapter<String> setupReturnReasonAdapter(Activity activity, Spinner spinner, String[] stringArray) {


        String[] adapter_array = new String[0];
        if (stringArray.length > 0) {
            adapter_array = new String[stringArray.length + 1];
            adapter_array[0] = activity.getResources().getString(R.string.select_reason);
            for (int i = 0; i < stringArray.length; i++) {
                adapter_array[i + 1] = stringArray[i];
            }
        }
        if (stringArray.length <= 0) {
            adapter_array = new String[1];
            adapter_array[0] = activity.getResources().getString(R.string.select_reason);

        }
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_textitem, adapter_array) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<String> setupOrderListAdapter(Activity activity, Spinner spinner, String[] stringArray) {


        String[] adapter_array = new String[0];
        if (stringArray.length > 0) {
            adapter_array = new String[stringArray.length + 1];
            adapter_array[0] = activity.getResources().getString(R.string.select_proformanumber);
            for (int i = 0; i < stringArray.length; i++) {
                adapter_array[i + 1] = stringArray[i];
            }
        }
        if (stringArray.length <= 0) {
            adapter_array = new String[1];
            adapter_array[0] = activity.getResources().getString(R.string.select_proformanumber);

        }
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_textitem, adapter_array) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<TaxClassId> setupTaxClassAdapter(Activity activity, Spinner spinner, final List<TaxClassId> taxClassList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<TaxClassId> spinnerArrayAdapter = new ArrayAdapter<TaxClassId>(activity, R.layout.spinner_textitem, taxClassList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(taxClassList.get(position).getTaxClassName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<TaxGroupId> setupTaxGroupAdapter(Activity activity, Spinner spinner, final List<TaxGroupId> taxGroupList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<TaxGroupId> spinnerArrayAdapter = new ArrayAdapter<TaxGroupId>(activity, R.layout.spinner_textitem, taxGroupList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(taxGroupList.get(position).getTaxGroupName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<TaxTypeDatum> setupTaxTypeAdapter(Activity activity, Spinner spinner, final List<TaxTypeDatum> taxTypeList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<TaxTypeDatum> spinnerArrayAdapter = new ArrayAdapter<TaxTypeDatum>(activity, R.layout.spinner_textitem, taxTypeList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(taxTypeList.get(position).getTaxTypeName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<CountryList> setupCountryAdapter(Activity activity, Spinner spinner, final List<CountryList> countryList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<CountryList> spinnerArrayAdapter = new ArrayAdapter<CountryList>(activity, R.layout.spinner_textitem, countryList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);
                tv.setText(countryList.get(position).getCountryName());
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<CurrencyList> setupCurrencyAdapter(Activity activity, Spinner spinner, final List<CurrencyList> currencyList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<CurrencyList> spinnerArrayAdapter = new ArrayAdapter<CurrencyList>(activity, R.layout.spinner_textitem, currencyList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);
                tv.setText(currencyList.get(position).getCurrencyCode());
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }
    public static ArrayAdapter<Object> setupStateAdapter(Activity activity, Spinner spinner, final List<Object> objectList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<Object> spinnerArrayAdapter = new ArrayAdapter<Object>(activity, R.layout.spinner_textitem, objectList) {

            @Override
            public boolean isEnabled(int position) {

                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = view.findViewById(R.id.textView1);
                Object obj = objectList.get(position);
                if (obj instanceof String) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                if (obj instanceof StateList) {
                    tv.setTextColor(Color.BLACK);
                    StateList stateList = (StateList) obj;
                    tv.setText(stateList.getStateName());
                }
                if (obj instanceof IndustryClassificationList) {
                    tv.setTextColor(Color.BLACK);
                    IndustryClassificationList classificationList = (IndustryClassificationList) obj;
                    tv.setText(classificationList.getIndustryClassificationName());
                }
                if (obj instanceof BuisnessType) {
                    tv.setTextColor(Color.BLACK);
                    BuisnessType buisnessType= (BuisnessType) obj;
                    tv.setText(buisnessType.getBusinessTypeName());
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }



    public static ArrayAdapter<String> setupAdapter(Activity activity, Spinner spinner, String[] stringArray) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_textitem, stringArray) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }
    public static ArrayAdapter<String> setupStringArraylistAdapter(Activity activity, Spinner spinner, List<String> stringArray) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_textitem, stringArray) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.hinext.sales.model.sales_pagedata.TaxList> setupHinextSalesTaxAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.sales.model.sales_pagedata.TaxList> taxListArray) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.sales.model.sales_pagedata.TaxList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.sales.model.sales_pagedata.TaxList>(activity, R.layout.spinner_textitem, taxListArray) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(taxListArray.get(position).getTaxString());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }
    public static ArrayAdapter<in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList> setupHinextServiceTaxAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList> taxListArray) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList>(activity, R.layout.spinner_textitem, taxListArray) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(taxListArray.get(position).getTaxString());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList> setupHinextPurchaseTaxAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList> taxListArray) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList>(activity, R.layout.spinner_textitem, taxListArray) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(taxListArray.get(position).getTaxString());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<TaxList> setupTaxAdapter(Activity activity, Spinner spinner, final List<TaxList> taxListArray) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<TaxList> spinnerArrayAdapter = new ArrayAdapter<TaxList>(activity, R.layout.spinner_textitem, taxListArray) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(taxListArray.get(position).getTaxString());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.hinext.generaltransaction.model.pagedata.TaxList> setupGTTaxAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.generaltransaction.model.pagedata.TaxList> taxListArray) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.generaltransaction.model.pagedata.TaxList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.generaltransaction.model.pagedata.TaxList>(activity, R.layout.spinner_textitem, taxListArray) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(taxListArray.get(position).getTaxString());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<SupplocationList> setupPurchaseCompLocationAdapter(Activity activity, Spinner spinner, final List<SupplocationList> custLocationList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<SupplocationList> spinnerArrayAdapter = new ArrayAdapter<SupplocationList>(activity, R.layout.spinner_textitem, custLocationList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(custLocationList.get(position).getInventoryLocationName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.hinext.purchase.model.purchase_pagedata.CmpylocationList> setupPurchaseCompanyLocationAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.purchase.model.purchase_pagedata.CmpylocationList> companyLocationList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.purchase.model.purchase_pagedata.CmpylocationList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.purchase.model.purchase_pagedata.CmpylocationList>(activity, R.layout.spinner_textitem, companyLocationList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(companyLocationList.get(position).getInventoryLocationName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<CustlocationList> setupInventoryLocationAdapter(Activity activity, Spinner spinner, final List<CustlocationList> custLocationList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<CustlocationList> spinnerArrayAdapter = new ArrayAdapter<CustlocationList>(activity, R.layout.spinner_textitem, custLocationList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(custLocationList.get(position).getInventoryLocationName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<CmpylocationList> setupCompanyLocationAdapter(Activity activity, Spinner spinner, final List<CmpylocationList> companyLocationList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<CmpylocationList> spinnerArrayAdapter = new ArrayAdapter<CmpylocationList>(activity, R.layout.spinner_textitem, companyLocationList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(companyLocationList.get(position).getInventoryLocationName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }
    public static ArrayAdapter<TableDataList> setupTableListAdapter(Activity activity, Spinner spinner, final List<TableDataList> tableDataLists) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<TableDataList> spinnerArrayAdapter = new ArrayAdapter<TableDataList>(activity, R.layout.spinner_textitem, tableDataLists) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(tableDataLists.get(position).getTableName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.model.purchase.hinextpurchase.TaxList> setupPurchaseTaxAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.model.purchase.hinextpurchase.TaxList> taxListArray) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.model.purchase.hinextpurchase.TaxList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.model.purchase.hinextpurchase.TaxList>(activity, R.layout.spinner_textitem, taxListArray) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(taxListArray.get(position).getTaxString());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<Object> setupSerialItemAdapter(Activity activity, Spinner spinner, final List<Object> objectList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<Object> spinnerArrayAdapter = new ArrayAdapter<Object>(activity, R.layout.spinner_textitem, objectList) {

            @Override
            public boolean isEnabled(int position) {

                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = view.findViewById(R.id.textView1);
                Object obj = objectList.get(position);
                if (obj instanceof String) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }

                if (obj instanceof SerializableItemsDTOList) {
                    tv.setTextColor(Color.BLACK);
                    SerializableItemsDTOList serializableItemsDTOList = (SerializableItemsDTOList) obj;
                    tv.setText(serializableItemsDTOList.getSerializableNumbers());
                }


                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<Object> setupItemAdapter(Activity activity, Spinner spinner, final List<Object> objectList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<Object> spinnerArrayAdapter = new ArrayAdapter<Object>(activity, R.layout.spinner_textitem, objectList) {

            @Override
            public boolean isEnabled(int position) {

                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = view.findViewById(R.id.textView1);
                Object obj = objectList.get(position);
                if (obj instanceof String) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                    tv.setText("");
                }

                if (obj instanceof StateDTOList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(((StateDTOList) obj).getStateName());
                }

                if (obj instanceof ItemCategoryDTOList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(((ItemCategoryDTOList) obj).getItemCategoryName());
                }

                if (obj instanceof ItemTypeDTOList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(((ItemTypeDTOList) obj).getItemTypeName());
                }
                if (obj instanceof ItemUOMTypeDTOList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(((ItemUOMTypeDTOList) obj).getUnitOfMeasurementName());
                }

                if (obj instanceof ItemMSICDTOList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(((ItemMSICDTOList) obj).getCode());
                }


                if (obj instanceof ItemBrandDTOList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(((ItemBrandDTOList) obj).getBrandName());
                }
                if (obj instanceof ItemCountTypeDTOList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(((ItemCountTypeDTOList) obj).getInventoryMovementName());
                }
                if (obj instanceof ItemIPTaxDTOList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(obj.toString());
                }

                if (obj instanceof ItemOPTaxDTOList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(obj.toString());
                }
                if (obj instanceof String) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(obj.toString());
                }


                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<Object> setupSalesReportAdapter(Activity activity, Spinner spinner, final List<Object> objectList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<Object> spinnerArrayAdapter = new ArrayAdapter<Object>(activity, R.layout.spinner_textitem, objectList) {

            @Override
            public boolean isEnabled(int position) {

                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = view.findViewById(R.id.textView1);
                Object obj = objectList.get(position);
                if (obj instanceof String) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                    tv.setText("");
                }

                if (obj instanceof LocationList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(((LocationList) obj).getInventoryLocationName());
                }
                if (obj instanceof SalesList) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(((SalesList) obj).getSqno());
                }

                if (obj instanceof String) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(obj.toString());
                }


                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }
    public static ArrayAdapter<Object> setupTableAdapter(Activity activity, Spinner spinner, final List<Object> objectList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<Object> spinnerArrayAdapter = new ArrayAdapter<Object>(activity, R.layout.spinner_textitem, objectList) {

            @Override
            public boolean isEnabled(int position) {

                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = view.findViewById(R.id.textView1);
                Object obj = objectList.get(position);
                if (obj instanceof String) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                    tv.setText("");
                }

                if (obj instanceof RestuarentTableData) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(((RestuarentTableData) obj).getTableName());
                }


                if (obj instanceof String) {
                    tv.setTextColor(Color.GRAY);
                    tv.setText(obj.toString());
                }


                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }
    public static ArrayAdapter<StateDTOList> setupCustomerStateAdapter(Activity activity, Spinner spinner, final List<StateDTOList> stateList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<StateDTOList> spinnerArrayAdapter = new ArrayAdapter<StateDTOList>(activity, R.layout.spinner_textitem, stateList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(stateList.get(position).getStateName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<CurrencyDTOList> setupCustomerCurrencyAdapter(Activity activity, Spinner spinner, final List<CurrencyDTOList> currencyList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<CurrencyDTOList> spinnerArrayAdapter = new ArrayAdapter<CurrencyDTOList>(activity, R.layout.spinner_textitem, currencyList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(currencyList.get(position).getCurrencyName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<CountryDTOList> setupCustomerCountryAdapter(Activity activity, Spinner spinner, final List<CountryDTOList> countryList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<CountryDTOList> spinnerArrayAdapter = new ArrayAdapter<CountryDTOList>(activity, R.layout.spinner_textitem, countryList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(countryList.get(position).getCountryName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.hinext.supplier.model.StateDTOList> setupSupplierStateAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.supplier.model.StateDTOList> stateList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.supplier.model.StateDTOList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.supplier.model.StateDTOList>(activity, R.layout.spinner_textitem, stateList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(stateList.get(position).getStateName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.hinext.supplier.model.CurrencyDTOList> setupSupplierCurrencyAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.supplier.model.CurrencyDTOList> currencyList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.supplier.model.CurrencyDTOList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.supplier.model.CurrencyDTOList>(activity, R.layout.spinner_textitem, currencyList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(currencyList.get(position).getCurrencyName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.hinext.supplier.model.CountryDTOList> setupSupplierCountryAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.supplier.model.CountryDTOList> countryList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.supplier.model.CountryDTOList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.supplier.model.CountryDTOList>(activity, R.layout.spinner_textitem, countryList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(countryList.get(position).getCountryName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.hinext.contact.model.StateDTOList> setupContactStateAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.contact.model.StateDTOList> stateList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.contact.model.StateDTOList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.contact.model.StateDTOList>(activity, R.layout.spinner_textitem, stateList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(stateList.get(position).getStateName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<CurrencyDatum> setupCurrencyDataAdapter(Activity activity, Spinner spinner, final List<CurrencyDatum> currencyList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<CurrencyDatum> spinnerArrayAdapter = new ArrayAdapter<CurrencyDatum>(activity, R.layout.spinner_textitem, currencyList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(currencyList.get(position).getCurrencyName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.hinext.contact.model.CurrencyDTOList> setupContactCurrencyAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.contact.model.CurrencyDTOList> currencyList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.contact.model.CurrencyDTOList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.contact.model.CurrencyDTOList>(activity, R.layout.spinner_textitem, currencyList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(currencyList.get(position).getCurrencyName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<in.hiaccounts.hinext.contact.model.CountryDTOList> setupContactCountryAdapter(Activity activity, Spinner spinner, final List<in.hiaccounts.hinext.contact.model.CountryDTOList> countryList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<in.hiaccounts.hinext.contact.model.CountryDTOList> spinnerArrayAdapter = new ArrayAdapter<in.hiaccounts.hinext.contact.model.CountryDTOList>(activity, R.layout.spinner_textitem, countryList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(countryList.get(position).getCountryName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static String getFinalUrl(String base_url, String port_number, String application_name, String api_path, String app_module, String user_key, String user_id, String compnay_id) {
        String final_url = base_url + ":" + port_number + "/" + application_name + "/" + api_path + "/" + Constant.APP_MODULE_HIPOS + "/" + user_key + "/" + user_id + "/" + compnay_id;

        return final_url;
    }

    public static String getRetailUrl(SharedPreference sharedPreference) {
        String base_url = sharedPreference.getData(Constant.SERVER_APP_BASEURL);
        String port_number = sharedPreference.getData(Constant.SERVER_APP_PORTNUMBER);
        String application_name = sharedPreference.getData(Constant.SERVER_APP_NAME);
        String api_path = sharedPreference.getData(Constant.SERVER_APP_APIPATH);
        String app_module = "retail";//sharedPreference.getData(Constant.SERVER_APP_MODULE);
        String user_key = sharedPreference.getData(Constant.SERVER_APP_USERKEY);
        String user_id = sharedPreference.getData(Constant.SERVER_APP_USERID);
        String compnay_id = sharedPreference.getData(Constant.SERVER_APP_USER_COMPANYID);
        String url = base_url + ":" + port_number + "/" + application_name + "/" + api_path + "/" + app_module + "/" + user_key + "/" + user_id;

        return url;
    }

    public static String getRetailUrlwithComapnyId(SharedPreference sharedPreference) {
        String base_url = sharedPreference.getData(Constant.SERVER_APP_BASEURL);
        String port_number = sharedPreference.getData(Constant.SERVER_APP_PORTNUMBER);
        String application_name = sharedPreference.getData(Constant.SERVER_APP_NAME);
        String api_path = sharedPreference.getData(Constant.SERVER_APP_APIPATH);
        String app_module = "retail";//sharedPreference.getData(Constant.SERVER_APP_MODULE);
        String user_key = sharedPreference.getData(Constant.SERVER_APP_USERKEY);
        String user_id = sharedPreference.getData(Constant.SERVER_APP_USERID);
        String compnay_id = sharedPreference.getData(Constant.SERVER_APP_USER_COMPANYID);
        String url = base_url + ":" + port_number + "/" + application_name + "/" + api_path + "/" + app_module + "/" + user_key + "/" + user_id + "/" + compnay_id;

        return url;
    }

    public static String getHiPosUrl(SharedPreference sharedPreference) {
        String base_url = sharedPreference.getData(Constant.SERVER_APP_BASEURL);
        String port_number = sharedPreference.getData(Constant.SERVER_APP_PORTNUMBER);
        String application_name = sharedPreference.getData(Constant.SERVER_APP_NAME);
        String api_path = sharedPreference.getData(Constant.SERVER_APP_APIPATH);
        String app_module = "hipos";//sharedPreference.getData(Constant.SERVER_APP_MODULE);
        String user_key = sharedPreference.getData(Constant.SERVER_APP_USERKEY);
        String user_id = sharedPreference.getData(Constant.SERVER_APP_USERID);
        String compnay_id = sharedPreference.getData(Constant.SERVER_APP_USER_COMPANYID);
        String url = base_url + ":" + port_number + "/" + application_name + "/" + api_path + "/" + app_module + "/" + user_key + "/" + user_id;

        return url;
    }

    public static String getHiposUrlwithComapnyId(SharedPreference sharedPreference) {
        String base_url = sharedPreference.getData(Constant.SERVER_APP_BASEURL);
        String port_number = sharedPreference.getData(Constant.SERVER_APP_PORTNUMBER);
        String application_name = sharedPreference.getData(Constant.SERVER_APP_NAME);
        String api_path = sharedPreference.getData(Constant.SERVER_APP_APIPATH);
        String app_module = "hipos";//sharedPreference.getData(Constant.SERVER_APP_MODULE);
        String user_key = sharedPreference.getData(Constant.SERVER_APP_USERKEY);
        String user_id = sharedPreference.getData(Constant.SERVER_APP_USERID);
        String compnay_id = sharedPreference.getData(Constant.SERVER_APP_USER_COMPANYID);
        String url = base_url + ":" + port_number + "/" + application_name + "/" + api_path + "/" + app_module + "/" + user_key + "/" + user_id + "/" + compnay_id;

        return url;
    }

    public static void showErrorDialog(String error, Activity activity) {
        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE);
        pDialog.setTitleText(error);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void showSuccessDialog(String message, Activity activity) {
        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setTitleText(message);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void setTextAppearanceMedium(Activity activity, TextView tv) {
        tv.setTextAppearance(activity, android.R.style.TextAppearance_Medium);
    }

    public static void setTextAppearanceSmall(Activity activity, TextView tv) {
        tv.setTextAppearance(activity, android.R.style.TextAppearance_Small);
    }

    public static void setTextColor(int color, TextView textView) {

        textView.setTextColor(color);
    }

    public static void setTexttypeFace(Typeface typrFace, int bold, TextView textView) {
        textView.setTypeface(typrFace, bold);


    }

    public static ArrayAdapter<AccountData> setupGroupActAdapter(Activity activity, Spinner spinner, final List<AccountData> accountList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<AccountData> spinnerArrayAdapter = new ArrayAdapter<AccountData>(activity, R.layout.spinner_textitem, accountList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);

                tv.setText(accountList.get(position).getAgName());

                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<ChartAccountDatum> setupGroupActLevelAdapter(Activity activity, Spinner spinner, final List<ChartAccountDatum> accountList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<ChartAccountDatum> spinnerArrayAdapter = new ArrayAdapter<ChartAccountDatum>(activity, R.layout.spinner_textitem, accountList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);
                if (accountList.get(position).getStringAccountCode()!=null){
                    tv.setText(accountList.get(position).getAccount_name()+":"+accountList.get(position).getStringAccountCode());
                }else {
                    tv.setText(accountList.get(position).getAccount_name());
                }



                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<LocationId> setupOpnInvLocationAdapter(Activity activity, Spinner spinner, final List<LocationId> locationList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<LocationId> spinnerArrayAdapter = new ArrayAdapter<LocationId>(activity, R.layout.spinner_textitem, locationList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);
                tv.setText(locationList.get(position).getInventoryLocationName());




                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    public static ArrayAdapter<Location> setUserLocationAdapter(Activity activity, Spinner spinner, final List<Location> locationList) {


        // Initializing an ArrayAdapter
        final ArrayAdapter<Location> spinnerArrayAdapter = new ArrayAdapter<Location>(activity, R.layout.spinner_textitem, locationList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);
                tv.setText(locationList.get(position).getInventoryLocationName());




                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }


}