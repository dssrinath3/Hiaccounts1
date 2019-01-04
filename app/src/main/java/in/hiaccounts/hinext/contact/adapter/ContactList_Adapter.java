package in.hiaccounts.hinext.contact.adapter;

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
import in.hiaccounts.hinext.contact.activity.Activity_AddContact;
import in.hiaccounts.hinext.contact.model.ContactDatum;
import in.hiaccounts.utility.Constant;

/**
 * Created by Prateek on 2/10/2017.
 */

public class ContactList_Adapter extends BaseAdapter {

    Activity activity;
    List<ContactDatum> contactList;
    LayoutInflater layoutInflater;


    public ContactList_Adapter(Activity activity, List<ContactDatum> contactList) {

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
    public ContactDatum getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = null;
        v = layoutInflater.inflate(R.layout.adapter_contact, null);

        TextView tvName = v.findViewById(R.id.tvName);
        TextView tvEmail = v.findViewById(R.id.tvEmail);
        TextView tvContact = v.findViewById(R.id.tvContact);
        TextView tvEdit = v.findViewById(R.id.tvEdit);
        ImageView imgviewDelete = v.findViewById(R.id.imgviewDelete);
        ImageView imgviewEdit = v.findViewById(R.id.imgviewEdit);


        tvName.setVisibility(View.GONE);
        tvEmail.setVisibility(View.GONE);
        tvContact.setVisibility(View.GONE);
        tvEdit.setVisibility(View.GONE);
        imgviewDelete.setVisibility(View.GONE);
        imgviewEdit.setVisibility(View.GONE);


        final ContactDatum contact = getItem(position);

        if (contact.getFullName() != null) {
            tvName.setText(contact.getFullName());
        }

        if (contact.getContactNumber() != null) {
            tvContact.setText(contact.getContactNumber());
        }
        if (contact.getEmail() != null) {
            tvEmail.setText(contact.getEmail());
        }
        tvName.setVisibility(View.VISIBLE);
        tvEmail.setVisibility(View.VISIBLE);
        tvContact.setVisibility(View.VISIBLE);
        tvEdit.setVisibility(View.GONE);
        imgviewDelete.setVisibility(View.GONE);
        imgviewEdit.setVisibility(View.VISIBLE);


        imgviewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,Activity_AddContact.class);
                intent.putExtra("callingFrom",activity.getResources().getString(R.string.title_editcontact));
                intent.putExtra("contactId",contact.getOtherContactId());
                activity.startActivityForResult(intent, Constant.RESQUSTCODE_EDITCONTACT);
            }
        });




        return v;
    }

}

