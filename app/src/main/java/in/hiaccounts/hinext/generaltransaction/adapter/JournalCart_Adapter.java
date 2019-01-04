package in.hiaccounts.hinext.generaltransaction.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.hiaccounts.R;
import in.hiaccounts.hinext.generaltransaction.fragment.Fragment_TransactionJournalEntry;
import in.hiaccounts.hinext.generaltransaction.model.payee_account.Account;

/**
 * Created by Prateek on 2/10/2017.
 */

public class JournalCart_Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater layoutInflater;
    private Fragment_TransactionJournalEntry fragment;
    private ViewHolder finalViewHolder;
    private ArrayList accountList;
    private Map<Long, Account> accountHashMap;

    public JournalCart_Adapter(Activity activity, HashMap<Long, Account> accountHashMap, Fragment_TransactionJournalEntry fragment) {
        super();
        this.activity = activity;
        this.accountHashMap = accountHashMap;
        layoutInflater = LayoutInflater.from(activity);
        this.fragment = fragment;
        accountList = new ArrayList<>();
        accountList.addAll(accountHashMap.entrySet());
    }

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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.adapter_generaltransaction_journal, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        finalViewHolder = viewHolder;
        viewHolder.edDebitAmount.setVisibility(View.GONE);
        viewHolder.edCreditAmount.setVisibility(View.GONE);
        viewHolder.edAccountName.setVisibility(View.GONE);
        viewHolder.edDescription.setVisibility(View.GONE);

        Map.Entry<Long, Account> accountEntry = getItem(position);
        final Account account = accountEntry.getValue();

        if (account != null) {
            DescritpionTextWatcher oldDescriptionWatcher = (DescritpionTextWatcher) viewHolder.edDescription.getTag();
            if (oldDescriptionWatcher != null)
                viewHolder.edDescription.removeTextChangedListener(oldDescriptionWatcher);
            DescritpionTextWatcher descriptionTextWatcher = new DescritpionTextWatcher(account, viewHolder);
            viewHolder.edDescription.setTag(descriptionTextWatcher);
            viewHolder.edDescription.addTextChangedListener(descriptionTextWatcher);

            CreditTextWatcher oldCreditWatcher = (CreditTextWatcher) viewHolder.edCreditAmount.getTag();
            if (oldCreditWatcher != null)
                viewHolder.edCreditAmount.removeTextChangedListener(oldCreditWatcher);
            CreditTextWatcher creditTextWatcher = new CreditTextWatcher(account, viewHolder);
            viewHolder.edCreditAmount.setTag(creditTextWatcher);
            viewHolder.edCreditAmount.addTextChangedListener(creditTextWatcher);
            DebitTextWatcher oldDebitWatcher = (DebitTextWatcher) viewHolder.edDebitAmount.getTag();
            if (oldDebitWatcher != null)
                viewHolder.edDebitAmount.removeTextChangedListener(oldDebitWatcher);
            DebitTextWatcher debitTextWatcher = new DebitTextWatcher(account, viewHolder);
            viewHolder.edDebitAmount.setTag(debitTextWatcher);
            viewHolder.edDebitAmount.addTextChangedListener(debitTextWatcher);
        }
        if (account.getCreditAmount() != 0) {
            viewHolder.edCreditAmount.setText("" + account.getCreditAmount());
            viewHolder.edDebitAmount.setText("0");
        }
        if (account.getDebitAmount() != 0) {
            viewHolder.edDebitAmount.setText("" + account.getDebitAmount());
            viewHolder.edCreditAmount.setText("0");
        }

        if (account.getParentAccountName() != null)
            viewHolder.edAccountName.setText(account.getParentAccountName());

        if (account.getAccountDescription() != null) {
            viewHolder.edDescription.setText(account.getAccountDescription());
        }

        viewHolder.edDebitAmount.setVisibility(View.VISIBLE);
        viewHolder.edCreditAmount.setVisibility(View.VISIBLE);
        viewHolder.edAccountName.setVisibility(View.VISIBLE);
        viewHolder.edDescription.setVisibility(View.VISIBLE);


        return convertView;
    }


    public static class ViewHolder {

        EditText edDescription;
        EditText edAccountName;
        EditText edDebitAmount;
        EditText edCreditAmount;

        public ViewHolder(View v) {
            edDescription = v.findViewById(R.id.edDescription1);
            edDebitAmount = v.findViewById(R.id.edDebitAmount);
            edCreditAmount = v.findViewById(R.id.edCreditAmount);
            edAccountName = v.findViewById(R.id.edAccountName);


        }


    }

    private class CreditTextWatcher implements TextWatcher {
        private Account accountDetail;
        ViewHolder holder;

        private CreditTextWatcher(Account accountDetail, ViewHolder holder) {
            this.accountDetail = accountDetail;
            this.holder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence == null || charSequence.equals("")) {
                Fragment_TransactionJournalEntry.isError = true;
                holder.edCreditAmount.setError(null);
                accountDetail.setCreditAmount(0);

            }
            if (charSequence != null && !charSequence.equals("")) {
                try {
                    Fragment_TransactionJournalEntry.isError = false;
                    holder.edCreditAmount.setError(null);
                    accountDetail.setCreditAmount(Double.parseDouble(charSequence.toString()));

                } catch (NumberFormatException ne) {
                    Fragment_TransactionJournalEntry.isError = true;
                    holder.edCreditAmount.setError(activity.getResources().getString(R.string.error_numberformate));
                }

            }


        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (accountDetail.getCreditAmount() != 0) {
                holder.edDebitAmount.setText("0");
                accountDetail.setDebitAmount(0);
            }
            fragment.setTotal();
        }
    }

    private class DebitTextWatcher implements TextWatcher {
        private Account accountDetail;
        ViewHolder holder;

        private DebitTextWatcher(Account accountDetail, ViewHolder holder) {
            this.accountDetail = accountDetail;
            this.holder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (charSequence == null || charSequence.equals("")) {
                holder.edDebitAmount.setError(null);

                Fragment_TransactionJournalEntry.isError = true;
                accountDetail.setDebitAmount(0);

            }
            if (charSequence != null && !charSequence.equals("")) {
                try {
                    Fragment_TransactionJournalEntry.isError = false;
                    holder.edDebitAmount.setError(null);
                    accountDetail.setDebitAmount(Double.parseDouble(charSequence.toString()));

                } catch (NumberFormatException ne) {

                    Fragment_TransactionJournalEntry.isError = true;
                    holder.edDebitAmount.setError(activity.getResources().getString(R.string.error_numberformate));
                }

            }


        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (accountDetail.getDebitAmount() != 0) {
                holder.edCreditAmount.setText("0");
                accountDetail.setCreditAmount(0);
            }
            fragment.setTotal();

        }
    }

    private class DescritpionTextWatcher implements TextWatcher {
        private Account accountDetail;
        ViewHolder holder;

        private DescritpionTextWatcher(Account accountDetail, ViewHolder holder) {
            this.accountDetail = accountDetail;
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
                accountDetail.setAccountDescription(s.toString());
            } else {

            }
        }
    }
}

