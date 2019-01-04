package in.hiaccounts.hinext.generaltransaction.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.generaltransaction.model.payee_account.Account;

/**
 * Created by Prateek on 2/10/2017.
 */

public class ExpensesAccountCartList_Adapter extends BaseAdapter {

    private Activity activity;
    private List<Account> accountList;
    private LayoutInflater layoutInflater;

    public ExpensesAccountCartList_Adapter(Activity activity, List<Account> accountList) {
        super();
        this.activity = activity;
        this.accountList = accountList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public Account getItem(int position) {
        return accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_generaltransaction_expenses, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvActName.setVisibility(View.GONE);
        viewHolder.edDescription.setVisibility(View.GONE);
        viewHolder.edInoviceNumber.setVisibility(View.GONE);
        viewHolder.edAmtExTax.setVisibility(View.GONE);
        viewHolder.edTax.setVisibility(View.GONE);
        viewHolder.edAmountInTax.setVisibility(View.GONE);

        Account account = getItem(position);


        if (account != null) {

            if (account.getAccountName() != null)
                viewHolder.tvActName.setText(account.getAccountName());


            if (account.getAccountDescription() != null)
                viewHolder.edDescription.setText(account.getAccountDescription());

            if (account.getInvoiceNo() != null)
                viewHolder.edInoviceNumber.setText(account.getInvoiceNo());


            viewHolder.edAmountInTax.setText("" + account.getAmtinclusivetax());
            viewHolder.edAmtExTax.setText("" + account.getAmtexclusivetax());
            viewHolder.edTax.setText("" + account.getTaxamt());

            viewHolder.tvActName.setVisibility(View.VISIBLE);
            viewHolder.edDescription.setVisibility(View.VISIBLE);
            viewHolder.edInoviceNumber.setVisibility(View.VISIBLE);
            viewHolder.edAmtExTax.setVisibility(View.VISIBLE);
            viewHolder.edTax.setVisibility(View.VISIBLE);
            viewHolder.edAmountInTax.setVisibility(View.VISIBLE);

        }
        return convertView;
    }


    public static class ViewHolder {
        TextView tvActName;
        EditText edDescription;
        EditText edInoviceNumber;
        EditText edAmtExTax;
        EditText edTax;
        EditText edAmountInTax;

        public ViewHolder(View v) {
            tvActName = v.findViewById(R.id.tvActName);
            edDescription = v.findViewById(R.id.edDescription);
            edInoviceNumber = v.findViewById(R.id.edInoviceNumber);
            edAmtExTax = v.findViewById(R.id.edAmtExTax);
            edTax = v.findViewById(R.id.edTax);
            edAmountInTax = v.findViewById(R.id.edAmountInTax);
        }


    }


}

