package in.hiaccounts.hinext.controlpanel.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod.AccountMaster;

/**
 * Created by Prateek on 2/10/2017.
 */

public class PaymentMethod_AccountList_Adapter extends BaseAdapter {

    Activity activity;
    List<AccountMaster> accountList;
    LayoutInflater layoutInflater;


    public PaymentMethod_AccountList_Adapter(Activity activity, List<AccountMaster> accountList) {

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
    public AccountMaster getItem(int position) {
        return accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = null;
        v = layoutInflater.inflate(R.layout.controlpanel_adapterpaymentmethod_accountmaster, null);


        TextView tvAccountCode = v.findViewById(R.id.tvAccountCode);
        TextView tvAccountName = v.findViewById(R.id.tvAccountName);
        TextView tvAccountType = v.findViewById(R.id.tvAccountType);


        tvAccountCode.setVisibility(View.GONE);
        tvAccountType.setVisibility(View.GONE);
        tvAccountName.setVisibility(View.GONE);


        AccountMaster account = accountList.get(position);

        if (account != null) {
            if (account.getStringAccountCode() != null) {
                tvAccountCode.setText(account.getStringAccountCode());
            }
            if (account.getAccountGroup() != null) {
                tvAccountType.setText(account.getAccountGroup());
            }
            if (account.getAccountName() != null) {
                tvAccountName.setText(account.getAccountName());
            }

            tvAccountCode.setVisibility(View.VISIBLE);
            tvAccountType.setVisibility(View.VISIBLE);
            tvAccountName.setVisibility(View.VISIBLE);

        }
        return v;
    }

}
