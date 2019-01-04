package in.hiaccounts.hinext.application.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.model.company.ContactUs;

/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author danielme.com
 */
public class ContactUs_Adapter extends BaseAdapter {

    Activity activity;
    List<ContactUs> companyContactList;
    LayoutInflater layoutInflater;


    public ContactUs_Adapter(Activity activity, List<ContactUs> companyContactList) {

        super();
        this.activity = activity;
        this.companyContactList = companyContactList;

        layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return companyContactList.size();
    }

    @Override
    public ContactUs getItem(int position) {
        return companyContactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.adapter_contactus, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvCompanyCountry.setVisibility(View.GONE);
        viewHolder.tvCompanyname.setVisibility(View.GONE);
        viewHolder.tvCompnayAddress.setVisibility(View.GONE);
        viewHolder.tvCompanyNumber.setVisibility(View.GONE);



        if (companyContactList.get(position).getCompanyCountry()!=null && !companyContactList.get(position).getCompanyCountry().equals("")){
            viewHolder.tvCompanyCountry.setText(companyContactList.get(position).getCompanyCountry());
            viewHolder.tvCompanyCountry.setVisibility(View.VISIBLE);
        }

        if (companyContactList.get(position).getCompanyName()!=null && !companyContactList.get(position).getCompanyName().equals("")){
            viewHolder.tvCompanyname.setText(companyContactList.get(position).getCompanyName());
            viewHolder.tvCompanyname.setVisibility(View.VISIBLE);


        }

        if (companyContactList.get(position).getCompanyAddress()!=null && !companyContactList.get(position).getCompanyAddress().equals("")){
            viewHolder.tvCompnayAddress.setText(companyContactList.get(position).getCompanyAddress());
            viewHolder.tvCompnayAddress.setVisibility(View.VISIBLE);
        }

        if (companyContactList.get(position).getCompanContactNumber()!=null && !companyContactList.get(position).getCompanContactNumber().equals("")){

            viewHolder.tvCompanyNumber.setText(companyContactList.get(position).getCompanContactNumber());
            viewHolder.tvCompanyNumber.setVisibility(View.VISIBLE);
        }

        return convertView;
    }



    static class ViewHolder {
        @BindView(R.id.tvCompanyCountry)
        TextView tvCompanyCountry;
        @BindView(R.id.tvCompanyname)
        TextView tvCompanyname;
        @BindView(R.id.tvCompnayAddress)
        TextView tvCompnayAddress;
        @BindView(R.id.tvCompanyNumber)
        TextView tvCompanyNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}