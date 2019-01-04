package in.hiaccounts.hinext.restaurant.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.realm_manager.RealmManager;
import in.hiaccounts.hinext.restaurant.activity.RestuarantActivity;
import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosHelper;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosTableOrderCreator;
import in.hiaccounts.hinext.restaurant.model.restra_pos.exception.RestraPosTableOrderCartItem;
import in.hiaccounts.model.realm_model.data.RealmTemp_SelectItemList;
import in.hiaccounts.model.realm_model.data.Realm_SelectItemList;
import in.hiaccounts.model.realm_model.utils.DataGenerator;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class RestraposTableOrders_Adapter extends BaseAdapter {
    private List<SelectedItemsList> posCartItems;
    private LayoutInflater layoutInflater;
    private static final String TAG = RestraposTableOrders_Adapter.class.getSimpleName();
    private final Context context;
    RestraPosTableOrderCreator posCreator = RestraPosHelper.getPosTableOrderCreator();
    RestraPosTableOrderCartItem cartItem;
    SharedPreference sharedPreference;
    Activity mActivity;
    private Realm_SelectItemList selctData;



    public RestraposTableOrders_Adapter(Context context, List<SelectedItemsList> posCartItems) {
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

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {



        RestraposTableOrders_Adapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.restaurent_list_tem1, parent, false);
            viewHolder = new RestraposTableOrders_Adapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RestraposTableOrders_Adapter.ViewHolder) convertView.getTag();
        }



       final SelectedItemsList item = getItem(position);
        viewHolder.edItemName.setVisibility(View.GONE);
        // viewHolder.edItemPrice.setVisibility(View.GONE);
        viewHolder.edItemQty.setVisibility(View.GONE);
        // viewHolder.edGstTax.setVisibility(View.GONE);
        //    viewHolder.edAmountExTax.setVisibility(View.GONE);
        viewHolder.edAmountInTax.setVisibility(View.GONE);
        //   viewHolder.chkbxSelection.setVisibility(View.GONE);
        viewHolder.imgViewDelete.setVisibility(View.GONE);

        selctData =  RealmManager.createRestaurentDao().getItemCodeDetails(item.getItemCode(),item.getTableId(), item.getFloorId());




            if (item != null) {
                    RestraposTableOrders_Adapter.QuantityTextWatcher oldQtyWatcher = (RestraposTableOrders_Adapter.QuantityTextWatcher) viewHolder.edItemQty.getTag();
                    if (oldQtyWatcher != null)
                        viewHolder.edItemQty.removeTextChangedListener(oldQtyWatcher);
                    RestraposTableOrders_Adapter.QuantityTextWatcher qtyTextWatcher = new RestraposTableOrders_Adapter.QuantityTextWatcher(item, viewHolder);
                    viewHolder.edItemQty.setTag(qtyTextWatcher);
                    viewHolder.edItemQty.addTextChangedListener(qtyTextWatcher);


                    RestraposTableOrders_Adapter.PriceTextWatcher oldPriceWatcher = (RestraposTableOrders_Adapter.PriceTextWatcher) viewHolder.edAmountInTax.getTag();
                    if (oldPriceWatcher != null)
                        viewHolder.edAmountInTax.removeTextChangedListener(oldPriceWatcher);
                    RestraposTableOrders_Adapter.PriceTextWatcher priceTextWatcher = new RestraposTableOrders_Adapter.PriceTextWatcher(item, viewHolder);
                    viewHolder.edAmountInTax.setTag(priceTextWatcher);
                    viewHolder.edAmountInTax.addTextChangedListener(priceTextWatcher);



                    if (item.getItemName() != null)
                        viewHolder.edItemName.setText(item.getItemName());

                    viewHolder.edItemQty.setText("" + item.getItemQuantity());
                    updateAmount(viewHolder, item, item.getItemQuantity());


                viewHolder.imgViewDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try{
                                selctData =  RealmManager.createRestaurentDao().getItemCodeDetails(item.getItemCode(),item.getTableId(), item.getFloorId());

                                if (selctData.getId()!=0){
                                    int id = selctData.getId();
                                    if (!selctData.isKotSelect()){
                                        RealmManager.createRestaurentDao().deletRealmDataOfItem(selctData.getItemCode());
                                        notifyDataSetChanged();
                                        ((RestuarantActivity)mActivity).checkTableCartList();
                                    }else{
                                        Log.e("tableIdd", String.valueOf(selctData.getId()));
                                        Log.e("tableDesc", String.valueOf(selctData.getItemDesc()));
                                        //RealmManager.createRestaurentDao().updateRestaurentKotTableOrderStatus(id);

                                        RealmManager.createRestaurentDao().deletRealmDataTempOfItem(selctData.getItemCode());
                                        RealmManager.createRestaurentDao().deletRealmDataOfItem(selctData.getItemCode());
                                        RealmManager.createRestaurentDao().saveRestaurentKotCancelOrders(DataGenerator.generateRestaKotCancelOrder(item));
                                        notifyDataSetChanged();
                                        Toast.makeText(mActivity, "You order has been cancelled.", Toast.LENGTH_SHORT).show();
                                        // Toast.makeText(mActivity, "You Cannot Cancel The Order.", Toast.LENGTH_SHORT).show();

                                        ((RestuarantActivity)mActivity).checkTableCartList();

                                    }

                                }
                            }catch (Exception e){
                                Log.e("delete",e.toString());
                            }


                        }
                    });

                    viewHolder.edItemName.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            callItemDescDialog(v,item);
                            return false;
                        }
                    });


                    viewHolder.edItemName.setVisibility(View.VISIBLE);
                    viewHolder.edItemQty.setVisibility(View.VISIBLE);
                    viewHolder.edAmountInTax.setVisibility(View.VISIBLE);
                    viewHolder.imgViewDelete.setVisibility(View.VISIBLE);



              /*  if (selctData!=null){
                    if (selctData.isKotSelect()){
                        viewHolder.edItemQty.setFocusable(false);
                        viewHolder.edItemQty.setEnabled(false);

                    }
                }*/

            }


        return convertView;
    }

    private void callItemDescDialog(View mView, SelectedItemsList item) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(mActivity);
         mView = layoutInflaterAndroid.inflate(R.layout.restuarent_item_description_layout, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(mActivity);
        alertDialogBuilderUserInput.setView(mView);

        final EditText description = (EditText) mView.findViewById(R.id.edItemDescription);
        if (item.getItemDesc()!=null){
            description.setText(item.getItemDesc());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                        item.setItemDesc(description.getText().toString());

                        if (item!=null){
                            RealmManager.createRestaurentDao().updateRestaurentTableItemDescription(item);

                        }
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }


    static class ViewHolder {
        @BindView(R.id.edItemName)
        EditText edItemName;
        /*@BindView(R.id.edItemPrice)
        EditText edItemPrice;*/
        @BindView(R.id.edItemQty)
        EditText edItemQty;
        /*@BindView(R.id.edGstTax)
        EditText edGstTax;*/
       /* @BindView(R.id.edAmountExTax)
        EditText edAmountExTax;*/
        @BindView(R.id.edAmountInTax)
        EditText edAmountInTax;
       /* @BindView(R.id.chkbxSelection)
        CheckBox chkbxSelection;*/

        @BindView(R.id.imgViewDelete)
        ImageView imgViewDelete;
        @BindView(R.id.imgViewCancelOrder)
        ImageView imgViewCancelOrder;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private class QuantityTextWatcher implements TextWatcher {

        RestraposTableOrders_Adapter.ViewHolder holder;
        SelectedItemsList item;

        private QuantityTextWatcher(SelectedItemsList item, RestraposTableOrders_Adapter.ViewHolder holder) {

            this.holder = holder;
            this.item = item;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            if (s.toString().equals("")) {
                //fragment_createPOS.isCheckoutable=false;
                holder.edItemQty.setError("Quantity can't be empty.");
                // fragment_createPOS.flag=4;
            } else {
                try {
                    int quantity = Integer.parseInt(s.toString());
                    if (quantity == 0) {
                        holder.edItemQty.setError("Quantity can't be zero.");
                       /* fragment_createPOS.isCheckoutable=false;
                        fragment_createPOS.flag=3;*/
                       // item.setItemQuantity(quantity);
                    } else {
                       /* fragment_createPOS.flag=0;
                        fragment_createPOS.isCheckoutable=true;*/
                        holder.edItemQty.setError(null);



                        item.setItemQuantity(quantity);
                        updateAmount(holder, item, quantity);
                        //posCreator.updateitemPriceIncludeTax(item, Double.parseDouble(holder.edAmountInTax.getText().toString().trim()));

                          // Log.e("Items.. ", String.valueOf(item.getInclusiveJSON()));

                        item.setItemTotalAmount(item.getItemTotalAmountInTax());

                        //item.setItemTotalAmount(Double.parseDouble(holder.edAmountInTax.getText().toString().trim()));
                        try {
                         // Toast.makeText(mActivity, "item Quantity "+item.getItemQuantity(), Toast.LENGTH_SHORT).show();

                        if (!selctData.isKotSelect()){
                            RealmManager.createRestaurentDao().updateRestaurentTableData(DataGenerator.updateRestaSelectItem(item),item);
                            //notifyDataSetChanged();
                            ((RestuarantActivity)context).showTotalTableOrderPrice(posCartItems);

                           /* RealmManager.createRestaurentDao().updateRestaurentTableQty(item);
                            ((RestuarantActivity)context).showTotalTableOrderPrice(posCartItems);*/


                        }
                        else{

                            RealmTemp_SelectItemList restuarentTempItemList = RealmManager.createRestaurentDao().getRestuarentTempItemList(item);
                            if (restuarentTempItemList != null) {
                                if (item.getItemQuantity()<restuarentTempItemList.getQty()){
                                    if (item!=null){

                                        item.setType("Order");
                                        RealmManager.createRestaurentDao().updateRestaurentTableData(DataGenerator.updateRestaSelectItem(item),item);
                                       // notifyDataSetChanged();
                                        ((RestuarantActivity)context).showTotalTableOrderPrice(posCartItems);

                                    }
                                }else{
                                    item.setType("Order");
                                    RealmManager.createRestaurentDao().updateRestaurentTableData(DataGenerator.updateRestaSelectItem(item),item);
                                    //notifyDataSetChanged();
                                    ((RestuarantActivity)context).showTotalTableOrderPrice(posCartItems);

                                }
                            }


                        }



                        }catch (Exception e){

                        }



                    }

                } catch (NumberFormatException e) {
                   /* fragment_createPOS.isCheckoutable=false;
                    fragment_createPOS.flag=5;*/
                    UtilView.showToast((Activity) context, "Enter only digits.");
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
           // Toast.makeText(mActivity, "After changed"+s.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateAmount(RestraposTableOrders_Adapter.ViewHolder viewHolder, SelectedItemsList item, long itemQuantity) {

        double gstTaxPercantage =item.getGstTaxPercantage();
        double gstTaxAmt;
        gstTaxAmt = ((itemQuantity * item.getUnitPrice() * gstTaxPercantage) / 100);
        item.setGstTaxPercantage(gstTaxPercantage);
        DecimalFormat df2 = new DecimalFormat(".##");


        item.setGstTaxAmt(Double.parseDouble(df2.format(gstTaxAmt)));
        double amountExTax = item.getItemQuantity() * item.getUnitPrice();
        double amountInTax = Double.valueOf(item.getItemQuantity() * item.getUnitPrice()+gstTaxAmt);

        BigDecimal bigamountInTax = new BigDecimal( amountInTax);
        BigDecimal bigamountExTax = new BigDecimal( amountExTax);
        viewHolder.edAmountInTax.setText("" + String.format("%.2f",item.getUnitPrice()));
        item.setItemTotalAmountExTax(amountExTax);
        item.setItemTotalAmountInTax(amountInTax);
    }

    public void updateCartItems(List<SelectedItemsList> posCartItems) {
        this.posCartItems = posCartItems;
        notifyDataSetChanged();
    }
    private static double round (double value, int precision,boolean up) {
        int scale = (int) Math.pow(10, precision);
        if (up) {
            return (double) Math.ceil(value * scale) / scale;
        } else {
            return (double) Math.floor(value * scale) / scale;
        }
    }
    public class PriceTextWatcher implements TextWatcher{
        RestraposTableOrders_Adapter.ViewHolder holder;
        SelectedItemsList item;
        private boolean skipOnChange;

        private PriceTextWatcher(SelectedItemsList item, RestraposTableOrders_Adapter.ViewHolder holder) {

            this.holder = holder;
            this.item = item;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            if (s.toString().equals("")) {
                holder.edAmountInTax.setError("Price can't be empty.");
            } else {
                try {
                    double price = Double.parseDouble(s.toString());
                    if (price == 0.00) {
                        holder.edAmountInTax.setError("Price can't be zero.");
                    } else {
                        holder.edAmountInTax.setError(null);

                      //  double itemUnitPrice = price / (1 + (item.getGstTaxPercantage() * 0.01));
                       // Log.e("itemUnitPriceUpdate2", String.valueOf(itemUnitPrice));

                        item.setUnitPrice(price);
                        try {
                            if (item!=null){
                                RealmManager.createRestaurentDao().updateRestaurentTableUnitPrice(item);
                                //notifyDataSetChanged();
                                ((RestuarantActivity)context).showTotalTableOrderPrice(posCartItems);

                            }
                        }catch (Exception e){

                        }

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

}
