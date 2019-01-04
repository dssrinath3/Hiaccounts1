package in.hiaccounts.hinext.controlpanel.adapter;


import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.activity.Activity_OpeningBalance_BalanceSheet;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.Account;
import in.hiaccounts.utility.ServiceHandler;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author danielme.com
 */
public class OpeningBalanceSheetAddAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private ArrayList accountList;
    Map<Long, Account> accountHashMap;

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public Map.Entry<Long, Account> getItem(int position) {
        return (Map.Entry) accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public OpeningBalanceSheetAddAdapter(Context context, HashMap<Long, Account> accountHasmap) {
        super();
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        this.accountHashMap = accountHasmap;
        accountList = new ArrayList<>();
        accountList.addAll(accountHasmap.entrySet());
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.oepningbalance_adapter_addbalancesheet, null);

            holder.setTvAccount((TextView) convertView.findViewById(R.id.tvAccount));
            holder.setEdDredit((EditText) convertView.findViewById(R.id.edDebit));
            holder.setEdCredit((EditText) convertView.findViewById(R.id.edCredit));


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Map.Entry<Long, Account> accountEntry = getItem(position);
        final Account accountDetail = accountEntry.getValue();

        if (accountDetail!=null) {

            CreditTextWatcher oldCreditWatcher = (CreditTextWatcher) holder.getEdCredit().getTag();
            if (oldCreditWatcher != null)
                holder.getEdCredit().removeTextChangedListener(oldCreditWatcher);
            CreditTextWatcher creditTextWatcher = new CreditTextWatcher(accountDetail, holder);
            holder.getEdCredit().setTag(creditTextWatcher);
            holder.getEdCredit().addTextChangedListener(creditTextWatcher);

            DebitTextWatcher oldDebitWatcher = (DebitTextWatcher) holder.getEdDredit().getTag();
            if (oldDebitWatcher != null)
                holder.getEdDredit().removeTextChangedListener(oldDebitWatcher);
            DebitTextWatcher debitTextWatcher = new DebitTextWatcher(accountDetail, holder);
            holder.getEdDredit().setTag(debitTextWatcher);
            holder.getEdDredit().addTextChangedListener(debitTextWatcher);

            holder.getTvAccount().setVisibility(View.GONE);
            holder.getEdCredit().setVisibility(View.GONE);
            holder.getEdDredit().setVisibility(View.GONE);


            if (accountDetail.getAccountName() != null) {
                holder.getTvAccount().setText(accountDetail.getAccountName());
            }


            holder.getEdCredit().setText("" + accountDetail.getCrdtAmt());
            holder.getEdDredit().setText("" + accountDetail.getDebitAmt());


            holder.getTvAccount().setVisibility(View.VISIBLE);
            holder.getEdCredit().setVisibility(View.VISIBLE);
            holder.getEdDredit().setVisibility(View.VISIBLE);
        }

        return convertView;
    }


    public class Holder {


        TextView tvAccount;
        EditText edDredit;
        EditText edCredit;

        public TextView getTvAccount() {
            return tvAccount;
        }

        public void setTvAccount(TextView tvAccount) {
            this.tvAccount = tvAccount;
        }

        public EditText getEdDredit() {
            return edDredit;
        }

        public void setEdDredit(EditText edDredit) {
            this.edDredit = edDredit;
        }

        public EditText getEdCredit() {
            return edCredit;
        }

        public void setEdCredit(EditText edCredit) {
            this.edCredit = edCredit;
        }
    }

    private class CreditTextWatcher implements TextWatcher {
        private Account accountDetail;
        Holder holder;

        private CreditTextWatcher(Account accountDetail, Holder holder) {
            this.accountDetail = accountDetail;
            this.holder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence == null || charSequence.equals("")) {
                Activity_OpeningBalance_BalanceSheet.isError=true;
                holder.getEdCredit().setError(null);
                accountDetail.setCrdtAmt(0);
                accountDetail.setCreditAmount(""+0);

            }
            if (charSequence != null && !charSequence.equals("")) {
                try {
                    Activity_OpeningBalance_BalanceSheet.isError=false;
                    holder.getEdCredit().setError(null);
                    accountDetail.setCrdtAmt(Integer.parseInt(charSequence.toString()));
                    //accountDetail.setCreditAmount(charSequence.toString());
                    accountDetail.setCreditAmount(""+Integer.parseInt(charSequence.toString()));

                } catch (NumberFormatException ne) {
                    Activity_OpeningBalance_BalanceSheet.isError=true;
                    holder.getEdCredit().setError(activity.getResources().getString(R.string.error_numberformate));
                }

            }


        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (accountDetail.getCrdtAmt() != 0) {
                holder.getEdDredit().setText("0");
                //accountDetail.setDebitAmount("0");
                accountDetail.setDebitAmount(""+0);
                accountDetail.setDebitAmt(0);
            }
            ((Activity_OpeningBalance_BalanceSheet)activity).setTotal();

        }
    }

    private class DebitTextWatcher implements TextWatcher {
        private Account accountDetail;
        Holder holder;

        private DebitTextWatcher(Account accountDetail, Holder holder) {
            this.accountDetail = accountDetail;
            this.holder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (charSequence == null || charSequence.equals("")) {
                holder.getEdDredit().setError(null);

                Activity_OpeningBalance_BalanceSheet.isError=true;
                accountDetail.setDebitAmt(0);
                accountDetail.setDebitAmount(""+0);

            }
            if (charSequence != null && !charSequence.equals("")) {
                try {

                    Activity_OpeningBalance_BalanceSheet.isError=false;
                    holder.getEdDredit().setError(null);
                    accountDetail.setDebitAmt(Integer.parseInt(charSequence.toString()));
//                    accountDetail.setDebitAmount(charSequence.toString());
                    accountDetail.setDebitAmount(""+Integer.parseInt(charSequence.toString()));

                } catch (NumberFormatException ne) {

                    Activity_OpeningBalance_BalanceSheet.isError=true;
                    holder.getEdDredit().setError(activity.getResources().getString(R.string.error_numberformate));
                }

            }


        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (accountDetail.getDebitAmt() != 0) {
                holder.getEdCredit().setText("0");
//                accountDetail.setCreditAmount("0");
                accountDetail.setCreditAmount(""+0);
                accountDetail.setCrdtAmt(0);
            }
            ((Activity_OpeningBalance_BalanceSheet)activity).setTotal();

        }
    }
}