package in.hiaccounts.hinext.restaurant.adapter;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.checkout.activity.Activity_Checkout;
import in.hiaccounts.hinext.checkout.model.BankAcount;
import in.hiaccounts.hinext.checkout.model.MultiBankPaymentList;
import in.hiaccounts.hinext.restaurant.activity.Activity_RestraPayment1;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class BankPayment_Adapter extends BaseAdapter {

    private Activity activity;
    private List<MultiBankPaymentList> bankPaymentList;
    private LayoutInflater layoutInflater;
    private MultiBankPaymentList bankPayment;
    private int pos;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    final List<BankAcount> bankAccountList = new ArrayList<>();

    public BankPayment_Adapter(Activity activity, List<MultiBankPaymentList> bankPaymentList) {
        super();

        this.activity = activity;
        this.bankPaymentList = bankPaymentList;
        layoutInflater = LayoutInflater.from(activity);
        serviceHandler = new ServiceHandler(activity);

    }

    @Override
    public int getCount() {
        return bankPaymentList.size();
    }

    @Override
    public MultiBankPaymentList getItem(int position) {
        return bankPaymentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.adapter_bankpayment, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        pos = position;

        viewHolder.imageview_addbank.setVisibility(View.GONE);
        viewHolder.imageview_removebank.setVisibility(View.GONE);
        viewHolder.ed_transacionno.setVisibility(View.GONE);
        viewHolder.edAccountName.setVisibility(View.GONE);
        viewHolder.edBankAmount.setVisibility(View.GONE);
        viewHolder.edBankDate.setVisibility(View.GONE);
        viewHolder.edBankName.setVisibility(View.GONE);


        bankPayment = bankPaymentList.get(position);

        if (bankPayment != null) {

            BankTextWatcher oldBankNameWatcher = (BankTextWatcher) viewHolder.edBankName.getTag();
            if (oldBankNameWatcher != null)
                viewHolder.edBankName.removeTextChangedListener(oldBankNameWatcher);
            BankTextWatcher bankNameWatcher = new BankTextWatcher(bankPayment, viewHolder);
            viewHolder.edBankName.setTag(bankNameWatcher);
            viewHolder.edBankName.addTextChangedListener(bankNameWatcher);

            TransNoTextWatcher oldTransNoWatcher = (TransNoTextWatcher) viewHolder.ed_transacionno.getTag();
            if (oldTransNoWatcher != null)
                viewHolder.ed_transacionno.removeTextChangedListener(oldTransNoWatcher);
            TransNoTextWatcher transNoWatcher = new TransNoTextWatcher(bankPayment, viewHolder);
            viewHolder.ed_transacionno.setTag(transNoWatcher);
            viewHolder.ed_transacionno.addTextChangedListener(transNoWatcher);

            AmountTextWatcher oldAmountTransNoWatcher = (AmountTextWatcher) viewHolder.edBankAmount.getTag();
            if (oldAmountTransNoWatcher != null)
                viewHolder.edBankAmount.removeTextChangedListener(oldAmountTransNoWatcher);
            AmountTextWatcher amountWatcher = new AmountTextWatcher(bankPayment, viewHolder);
            viewHolder.edBankAmount.setTag(amountWatcher);
            viewHolder.edBankAmount.addTextChangedListener(amountWatcher);


            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.edBankDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDatePicker(activity, finalViewHolder);
                }
            });


            viewHolder.edBankName.setText("");
            viewHolder.ed_transacionno.setText("");
            viewHolder.edBankDate.setText("");


            viewHolder.edBankAmount.setText("" + bankPayment.getAmount());

            if (bankPayment.getBankName() != null)
                viewHolder.edBankName.setText("" + bankPayment.getBankName());

            if (bankPayment.getTransactionNo() != null)
                viewHolder.ed_transacionno.setText("" + bankPayment.getTransactionNo());

            if (bankPayment.getBankAccount() != null)
                viewHolder.edAccountName.setText("" + bankPayment.getBankAccount());

            if (bankPayment.getDate() != null && !bankPayment.getDate().equals("")) {
                final TimeZone utc = TimeZone.getTimeZone("UTC");
                final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                f.setTimeZone(utc);
                try {
                    Date date = f.parse(bankPayment.getDate());
                    int year = date.getYear() + 1900;
                    int month = date.getMonth();
                    final int day = date.getDate();
                    viewHolder.edBankDate.setText(String.valueOf(new StringBuilder().append(day).append("-").append(month + 1).append("-").append(year)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {

            }


            viewHolder.imageview_addbank.setVisibility(View.VISIBLE);
            viewHolder.imageview_removebank.setVisibility(View.VISIBLE);
            viewHolder.ed_transacionno.setVisibility(View.VISIBLE);
            viewHolder.edAccountName.setVisibility(View.VISIBLE);
            viewHolder.edBankAmount.setVisibility(View.VISIBLE);
            viewHolder.edBankDate.setVisibility(View.VISIBLE);
            viewHolder.edBankName.setVisibility(View.VISIBLE);

           // viewHolder.edAccountName.setText("");


            viewHolder.edAccountName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String serverUrl = UtilView.getUrl(activity);
                    isInternetPresent = serviceHandler.isConnectingToInternet();

                    String search = "";
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            final Dialog dialog = new Dialog(activity);
                            dialog.setContentView(R.layout.dialog_bankaccount);
                            dialog.setCancelable(false);
                            dialog.show();

                            final TextView tvNoData = dialog.findViewById(R.id.tvNoData);
                            final LinearLayout llListView = dialog.findViewById(R.id.llListView);
                            ImageView imgViewClose = dialog.findViewById(R.id.imgViewClose);
                            ImageView imgviewSearch = dialog.findViewById(R.id.imgviewSearch);
                            final ListView listview = dialog.findViewById(R.id.listview);
                            final ProgressView progress_bar = dialog.findViewById(R.id.progress_bar);
                            final EditText edSearch = dialog.findViewById(R.id.edSearch);


                            imgViewClose.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (dialog != null && dialog.isShowing())
                                        dialog.dismiss();
                                }
                            });

                            edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                @Override
                                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                                    boolean handled = false;
                                    UtilView.hideSoftKeyboard(activity, edSearch);
                                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                                        handled = true;
                                        String search = edSearch.getText().toString().trim();
                                        if (search == null) {
                                            search = "";

                                        }
                                        getBankAccountList(serverUrl, progress_bar, tvNoData, listview, llListView, search);
                                    }
                                    return handled;
                                }
                            });

                            edSearch.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                }

                                @Override
                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                }

                                @Override
                                public void afterTextChanged(Editable editable) {
                                    if (editable.toString().equals("")) {
                                        UtilView.hideSoftKeyboard(activity, edSearch);
                                        getBankAccountList(serverUrl, progress_bar, tvNoData, listview, llListView, "");
                                    }
                                }
                            });


                            imgviewSearch.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    UtilView.hideSoftKeyboard(activity, edSearch);
                                    String search = edSearch.getText().toString().trim();
                                    if (search == null) {
                                        search = "";
                                    }
                                    getBankAccountList(serverUrl, progress_bar, tvNoData, listview, llListView, search);

                                }
                            });

                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                                    BankAcount bankAcount = bankAccountList.get(position);
                                    if (bankAcount.getAccountName() != null) {
                                        finalViewHolder.edAccountName.setText(bankAcount.getAccountName());
                                        bankPayment.setBankAccount(bankAcount.getAccountName());
                                    }

                                    if (bankAcount.getAccountid() != null) {
                                        bankPayment.setBankAccountId(bankAcount.getAccountid());
                                    }


                                    if (dialog != null && dialog.isShowing()) {
                                        dialog.dismiss();
                                    }
                                }
                            });

                            getBankAccountList(serverUrl, progress_bar, tvNoData, listview, llListView, search);


                        } else {
                            UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
                        }
                    } else {
                        UtilView.gotToLogin(activity);
                    }
                }

                private void getBankAccountList(String serverUrl, final ProgressView progress_bar, final TextView tvNoData, final ListView listview, final LinearLayout llListView, String search) {
                    UtilView.showProgessBar(activity, progress_bar);

                    final String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETBANKACCOUNTLIST + "?accountSearchText=";
                    final GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progress_bar);
                            HideUtil.init(activity);
                            if (result != null) {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(activity, activity.getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(activity, Activity_Login.class);
                                    activity.startActivity(intent);
                                    activity.finish();
                                } else {
                                    try {
                                        JSONArray jsonArray = new JSONArray(result.toString());

                                        if (bankAccountList != null && bankAccountList.size() > 0) {
                                            bankAccountList.clear();
                                        }
                                        Gson gson = new Gson();
                                        if (jsonArray != null && jsonArray.length() > 0) {
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                BankAcount bankAcount = gson.fromJson(jsonObject.toString(), BankAcount.class);
                                                bankAccountList.add(bankAcount);
                                            }
                                        }

                                        if (bankAccountList != null && bankAccountList.size() > 0) {
                                            tvNoData.setVisibility(View.GONE);
                                            llListView.setVisibility(View.VISIBLE);
                                            BankAccount_Adapter accountListAdapter = new BankAccount_Adapter(activity, bankAccountList);
                                            listview.setAdapter(accountListAdapter);
                                            accountListAdapter.notifyDataSetChanged();
                                        } else {
                                            tvNoData.setVisibility(View.VISIBLE);
                                            llListView.setVisibility(View.GONE);
                                        }
                                    } catch (Exception e) {
                                        UtilView.showErrorDialog(activity.getResources().getString(R.string.error_null), activity);
                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(activity.getResources().getString(R.string.error_null), activity);
                            }
                        }
                    }, false);
                    if (search.equals("")) {
                        task.execute(url + search, "");
                    } else if (!search.equals("")) {
                        task.execute(url + search.replace(" ", "%20"), "");
                    }
                }
            });

            viewHolder.imageview_addbank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);
                }
            });
            viewHolder.imageview_removebank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);
                }
            });
        }


        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.imageview_removebank)
        ImageView imageview_removebank;
        @BindView(R.id.imageview_addbank)
        ImageView imageview_addbank;
        @BindView(R.id.edAccountName)
        EditText edAccountName;
        @BindView(R.id.edBankName)
        EditText edBankName;
        @BindView(R.id.edBankAmount)
        EditText edBankAmount;
        @BindView(R.id.ed_transacionno)
        EditText ed_transacionno;
        @BindView(R.id.edBankDate)
        EditText edBankDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private class AmountTextWatcher implements TextWatcher {
        private MultiBankPaymentList bankPayment;
        ViewHolder holder;

        private AmountTextWatcher(MultiBankPaymentList bankPayment, ViewHolder holder) {
            this.bankPayment = bankPayment;
            this.holder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (charSequence.toString().equals("")) {
                holder.edBankAmount.setHint("0.00");
                holder.edBankAmount.setError(null);
                bankPayment.setAmount(0.00);
                if (activity!=null){
                    ((Activity_RestraPayment1) activity).setTenderedAmount();
                }
            } else {
                try {
                    holder.edBankAmount.setError(null);
                    double amount = Double.parseDouble(charSequence.toString().trim());
                    BigDecimal big = new BigDecimal(amount);
                    bankPayment.setAmount(Double.parseDouble(String.valueOf(big.setScale(2, RoundingMode.HALF_UP))));

                    if (activity!=null){
                        ((Activity_RestraPayment1) activity).setTenderedAmount();
                    }



                } catch (NumberFormatException e) {
                    holder.edBankAmount.setError(activity.getResources().getString(R.string.error_numberformate));
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    }

    private class BankTextWatcher implements TextWatcher {
        private MultiBankPaymentList bankPayment;
        ViewHolder holder;

        private BankTextWatcher(MultiBankPaymentList bankPayment, ViewHolder holder) {
            this.bankPayment = bankPayment;
            this.holder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().trim().equalsIgnoreCase("")) {
                bankPayment.setBankName(s.toString());
            } else {

            }
        }
    }

    private class TransNoTextWatcher implements TextWatcher {
        private MultiBankPaymentList bankPayment;
        ViewHolder holder;

        private TransNoTextWatcher(MultiBankPaymentList bankPayment, ViewHolder holder) {
            this.bankPayment = bankPayment;
            this.holder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().trim().equalsIgnoreCase("")) {
                bankPayment.setTransactionNo(s.toString());
            } else {

            }
        }
    }


    public void getDatePicker(Activity mActivity, final ViewHolder finalViewHolder) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);


        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                finalViewHolder.edBankDate.setText(String.valueOf(new StringBuilder().append(dayOfMonth).append("-").append(month + 1).append("-").append(year)));
                calendar.set(year, month, dayOfMonth);
                String selectedDate = f.format(calendar.getTime());
                bankPayment.setDate(selectedDate);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker();
        datePickerDialog.show();


    }
}