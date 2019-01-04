package in.hiaccounts.hinext.generaltransaction.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;

import in.hiaccounts.hinext.generaltransaction.model.payto_contact.Contact;

/**
 * Created by Prateek on 2/10/2017.
 */

public class ContactList_Adapter extends BaseAdapter {

    private Activity activity;
    private List<Contact> contactList;
    private LayoutInflater layoutInflater;

    public ContactList_Adapter(Activity activity, List<Contact> contactList) {
        super();
        this.activity = activity;
        this.contactList = contactList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Contact getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = null;
        v = layoutInflater.inflate(R.layout.adapter_generaltransaction_contact, null);

        TextView tvCustomerName = v.findViewById(R.id.tvCustomerName);
        TextView tvCustomerEmail = v.findViewById(R.id.tvCustomerEmail);
        TextView tvCustomerContact = v.findViewById(R.id.tvCustomerContact);

        tvCustomerName.setVisibility(View.GONE);
        tvCustomerEmail.setVisibility(View.GONE);
        tvCustomerContact.setVisibility(View.GONE);

        Contact contact = getItem(position);

        if (contact != null) {
            if (contact.getFullName() != null)
                tvCustomerName.setText(contact.getFullName());
            if (contact.getEmail() != null)
                tvCustomerEmail.setText(contact.getEmail());
            if (contact.getContactNumber() != null)
                tvCustomerContact.setText(contact.getContactNumber());

            tvCustomerName.setVisibility(View.VISIBLE);
            tvCustomerEmail.setVisibility(View.VISIBLE);
            tvCustomerContact.setVisibility(View.VISIBLE);

        }
        return v;
    }

}

