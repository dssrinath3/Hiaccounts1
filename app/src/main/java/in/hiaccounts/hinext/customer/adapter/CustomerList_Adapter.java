package in.hiaccounts.hinext.customer.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.customer.activity.Activity_EditCustomer;
import in.hiaccounts.hinext.customer.model.CustomerList;
import in.hiaccounts.utility.Constant;

/**
 * Created by Prateek on 2/10/2017.
 */

public class CustomerList_Adapter extends BaseAdapter {

    Activity activity;
    List<CustomerList> customerList;
    LayoutInflater layoutInflater;


    public CustomerList_Adapter(Activity activity, List<CustomerList> customerList) {

        super();
        this.activity = activity;
        this.customerList = customerList;

        layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return customerList.size();
    }

    @Override
    public CustomerList getItem(int position) {
        return customerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = null;
        v = layoutInflater.inflate(R.layout.adapter_sales_customer, null);

        TextView tvName = v.findViewById(R.id.tvName);
        TextView tvEmail = v.findViewById(R.id.tvEmail);
        TextView tvContact = v.findViewById(R.id.tvContact);
        TextView tvEdit = v.findViewById(R.id.tvEdit);
        ImageView imgviewEdit = v.findViewById(R.id.imgviewEdit);

        tvName.setVisibility(View.GONE);
        tvEmail.setVisibility(View.GONE);
        tvContact.setVisibility(View.GONE);
        tvEdit.setVisibility(View.GONE);
        imgviewEdit.setVisibility(View.GONE);

        final CustomerList customer = customerList.get(position);

        if (customer != null) {
            if (customer.getCustomerName() != null) {
                tvName.setText(customer.getCustomerName());
            }

       /* if (customer.getCustomerNumber()!=null){
            tvContact.setText(customer.getCustomerNumber());
        }
*/
            if (customer.getCustomerEmail() != null) {
                tvEmail.setText(customer.getCustomerEmail());
            }

            if (customer.getPhoneNumber() != null) {
                tvContact.setText(customer.getPhoneNumber());
            }

            tvName.setVisibility(View.VISIBLE);
            tvEmail.setVisibility(View.VISIBLE);
            tvContact.setVisibility(View.VISIBLE);
            tvEdit.setVisibility(View.GONE);
            imgviewEdit.setVisibility(View.VISIBLE);

        }

        imgviewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Activity_EditCustomer.class);
                intent.putExtra("callingFrom", Constant.CUSTOMER_EDITPROFILE);
                intent.putExtra("customerId", customer.getCustomerId());
                activity.startActivityForResult(intent, Constant.RESQUSTCODE_EDITCUSOTMER);
            }
        });
        return v;
    }

}

