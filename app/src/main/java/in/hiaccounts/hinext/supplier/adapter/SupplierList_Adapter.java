package in.hiaccounts.hinext.supplier.adapter;

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
import in.hiaccounts.hinext.supplier.activity.Activity_EditSupplier;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.utility.Constant;


/**
 * Created by Prateek on 05/08/2017.
 */

public class SupplierList_Adapter extends BaseAdapter {

    private Activity activity;
    private List<GetSupplier> supplierList;
    private LayoutInflater layoutInflater;

    public SupplierList_Adapter(Activity activity, List<GetSupplier> supplierList) {
        super();
        this.activity = activity;
        this.supplierList = supplierList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return supplierList.size();
    }

    @Override
    public GetSupplier getItem(int position) {
        return supplierList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = null;
        v = layoutInflater.inflate(R.layout.adapter_purchase_supplier, null);

        TextView tvSContact = v.findViewById(R.id.tvSContact);
        TextView tvSEmail = v.findViewById(R.id.tvSEmail);
        TextView tvSName = v.findViewById(R.id.tvSName);
        TextView tvEdit = v.findViewById(R.id.tvEdit);
        ImageView imgviewEdit = v.findViewById(R.id.imgviewEdit);

        tvSContact.setVisibility(View.GONE);
        tvSEmail.setVisibility(View.GONE);
        tvSName.setVisibility(View.GONE);
        tvEdit.setVisibility(View.GONE);
        imgviewEdit.setVisibility(View.GONE);


        final GetSupplier supplier = getItem(position);

        if (supplier != null) {
            if (supplier.getSupplierName() != null) {
                tvSName.setText(supplier.getSupplierName());
            }

            if (supplier.getSupplierEmail() != null) {
                tvSEmail.setText(supplier.getSupplierEmail());
            }

            if (supplier.getPhoneNumber1() != null) {
                tvSContact.setText(supplier.getPhoneNumber1());
            }
            tvSContact.setVisibility(View.VISIBLE);
            tvSEmail.setVisibility(View.VISIBLE);
            tvSName.setVisibility(View.VISIBLE);
            tvEdit.setVisibility(View.GONE);
            imgviewEdit.setVisibility(View.VISIBLE);
        }
        imgviewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Activity_EditSupplier.class);
                intent.putExtra("callingFrom", Constant.SUPPLIER_EDITPROFILE);
                intent.putExtra("supplierId", supplier.getSupplierId());
                activity.startActivityForResult(intent, Constant.RESQUSTCODE_EDITSUUPLIER);
            }
        });


        return v;
    }

}

