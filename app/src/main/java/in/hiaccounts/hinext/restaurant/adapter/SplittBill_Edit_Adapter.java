package in.hiaccounts.hinext.restaurant.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.realm_manager.RealmManager;
import in.hiaccounts.hinext.restaurant.activity.Activity_SplitBill;
import in.hiaccounts.hinext.restaurant.activity.RestuarantActivity;
import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.SubRow;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCartItem;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCreator;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosHelper;
import in.hiaccounts.model.realm_model.data.RealmTemp_SelectItemList;
import in.hiaccounts.model.realm_model.data.Realm_SelectItemList;
import in.hiaccounts.model.realm_model.utils.DataGenerator;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class SplittBill_Edit_Adapter extends BaseAdapter {
    private List<SelectedItemsList> posCartItems;
    private LayoutInflater layoutInflater;
    private static final String TAG = SplittBill_Edit_Adapter.class.getSimpleName();
    private final Context context;
    SharedPreference sharedPreference;
    Activity mActivity;
    List<SelectedItemsList> posCartTableItems;
    int quantity =0;
    int listPosititon;
    public SplittBill_Edit_Adapter(Context context, List<SelectedItemsList> posCartItems) {
        super();
        this.posCartItems = posCartItems;
        this.context = context;
        mActivity= (Activity) context;
        layoutInflater = LayoutInflater.from(context);
        sharedPreference=SharedPreference.getInstance(context);


    }

    @Override
    public int getCount() {
        return posCartItems.size();
    }

    @Override
    public SelectedItemsList getItem(int position) {
        return posCartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void updateCartItems(List<SelectedItemsList> posCartItems) {
        this.posCartItems = posCartItems;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        SplittBill_Edit_Adapter.ViewHolder viewHolder = null;
        listPosititon = position;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.restaurent_splittbill_edit_items, parent, false);
            viewHolder = new SplittBill_Edit_Adapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SplittBill_Edit_Adapter.ViewHolder) convertView.getTag();
            viewHolder.edItemName.setVisibility(View.GONE);
        }

        final SelectedItemsList posCartItem = getItem(position);

        viewHolder.edPrice.setVisibility(View.GONE);
        viewHolder.edItemQty.setVisibility(View.GONE);

        if (posCartItem != null) {
            if (posCartItem != null) {
                if (posCartItem.getItemName() != null)
                    viewHolder.edItemName.setText(posCartItem.getItemName());
                if (posCartItem.getUnitPrice() != null){
                    viewHolder.edPrice.setText(String.format("%.2f",posCartItem.getUnitPrice()));
                }

                if (posCartItem.getItemQuantity()!=0){
                    viewHolder.edItemQty.setText("" + posCartItem.getItemQuantity());
                }

                viewHolder.edItemName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((ListView) parent).performItemClick(view, position, 0);
                    }
                });


                ViewHolder finalViewHolder = viewHolder;
                viewHolder.edItemQty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {
                            SplittBill_Edit_Adapter.QuantityTextWatcher oldQtyWatcher = (SplittBill_Edit_Adapter.QuantityTextWatcher) finalViewHolder.edItemQty.getTag();
                            if (oldQtyWatcher != null)
                                finalViewHolder.edItemQty.removeTextChangedListener(oldQtyWatcher);

                            SplittBill_Edit_Adapter.QuantityTextWatcher qtyTextWatcher = new SplittBill_Edit_Adapter.QuantityTextWatcher(posCartItem, finalViewHolder);
                            finalViewHolder.edItemQty.setTag(qtyTextWatcher);
                            finalViewHolder.edItemQty.addTextChangedListener(qtyTextWatcher);


                    }
                });



                viewHolder.edItemName.setVisibility(View.VISIBLE);
                viewHolder.edPrice.setVisibility(View.VISIBLE);
                viewHolder.edItemQty.setVisibility(View.VISIBLE);

            }
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.edItemName)
        EditText edItemName;
        @BindView(R.id.edPrice)
        EditText edPrice;
        @BindView(R.id.edItemQty)
        EditText edItemQty;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    private class QuantityTextWatcher implements TextWatcher {

        SplittBill_Edit_Adapter.ViewHolder holder;
        SelectedItemsList item;
        EditText mEditText;

        private QuantityTextWatcher(SelectedItemsList item, SplittBill_Edit_Adapter.ViewHolder holder) {

            this.holder = holder;
            this.item = item;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            if (s.toString().equals("")) {
                holder.edItemQty.setError("Quantity can't be empty.");
            } else {
                try {

                   int quantity = Integer.parseInt(s.toString());
                    if (quantity == 0) {
                        holder.edItemQty.setError("Quantity can't be zero.");
                    } else {
                        holder.edItemQty.setError(null);
             /*           if (posCartItems!=null && posCartItems.size()>0){
                            posCartItems.get(listPosititon).setItemQuantity(posCartItems.get(listPosititon).getItemQuantity());

                            if (posCartItems.get(listPosititon).getItemId()!=null){
                                SubRow subRow = new SubRow();
                                subRow.setTableName(posCartItems.get(listPosititon).getTableName());
                                subRow.setFloorId(posCartItems.get(listPosititon).getFloorId());
                                subRow.setTableId(posCartItems.get(listPosititon).getTableId());

                                Realm_SelectItemList selctData =  RealmManager.createRestaurentDao().getItemCodeDetails(posCartItems.get(listPosititon).getItemCode(),posCartItems.get(listPosititon).getTableId(), posCartItems.get(listPosititon).getFloorId());
                                Log.e("itemQtyrealm", String.valueOf(selctData.getQty()));
                                if (quantity<selctData.getQty()){
                                    Log.e("itemQuantity", String.valueOf(posCartItems.get(listPosititon).getItemQuantity()));
                                    Log.e("itemQty", String.valueOf(posCartItems.get(listPosititon).getQty()));

                                    if (quantity > 0){
                                        posCartItems.get(listPosititon).setItemQuantity(quantity);
                                        posCartItems.get(listPosititon).setItemTotalAmount(posCartItems.get(listPosititon).getItemTotalAmountInTax());
                                        RealmManager.createRestaurentDao().updateRestSplitCheckOutTableData(posCartItems.get(listPosititon));

                                        quantity = (int) (selctData.getQty() - quantity);
                                        Log.e("current", String.valueOf(quantity));
                                        Log.e("prev", String.valueOf(posCartItems.get(listPosititon).getQty()));
                                        posCartItems.get(listPosititon).setQty(Long.valueOf(quantity));
                                        posCartItems.get(listPosititon).setItemQuantity(quantity);
                                        posCartItems.get(listPosititon).setItemTotalAmount(posCartItems.get(listPosititon).getItemTotalAmountInTax());
                                        updateAmount(holder, posCartItems.get(listPosititon), quantity);
                                        RealmManager.createRestaurentDao().saveRestaurentSplitItem(DataGenerator.generateRestaSplitOrder(posCartItems.get(listPosititon),subRow,posCartItems.get(listPosititon).getItemCode()),posCartItems.get(listPosititon).getItemCode(),subRow);
                                        ((Activity_SplitBill)mActivity).checkSplitTable1();
                                        // notifyDataSetChanged();

                                    }else{
                                        holder.edItemQty.setError("Quantity should not be greater than previous qty and not be zero.");
                                    }

                                }else{
                                    holder.edItemQty.setError("Quantity should not be greater than previous qty.");
                                }

                            }
                        }*/

                    }

                } catch (NumberFormatException e) {
                    UtilView.showToast((Activity) context, "Enter only digits.");
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    private void updateAmount(SplittBill_Edit_Adapter.ViewHolder viewHolder, SelectedItemsList item, long itemQuantity) {

        double gstTaxPercantage =item.getGstTaxPercantage();
        double gstTaxAmt;
        gstTaxAmt = ((itemQuantity * item.getUnitPrice() * gstTaxPercantage) / 100);
        item.setGstTaxPercantage(gstTaxPercantage);
        DecimalFormat df2 = new DecimalFormat(".##");


        item.setGstTaxAmt(Double.parseDouble(df2.format(gstTaxAmt)));
        double amountExTax = itemQuantity * item.getUnitPrice();
        double amountInTax = Double.valueOf(itemQuantity * item.getUnitPrice()+gstTaxAmt);

        item.setItemTotalAmountExTax(amountExTax);
        item.setItemTotalAmountInTax(amountInTax);
    }
}
