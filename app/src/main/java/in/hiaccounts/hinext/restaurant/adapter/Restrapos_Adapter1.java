package in.hiaccounts.hinext.restaurant.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.restaurant.activity.RestuarantActivity;
import in.hiaccounts.hinext.restaurant.fragment.Fragment_Restaurant;
import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCartItem;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCreator;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosHelper;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 6/15/2017.
 */

public class Restrapos_Adapter1 extends BaseAdapter {
    private List<RestraPosCartItem> posCartItems;
    private LayoutInflater layoutInflater;
    private static final String TAG = Restrapos_Adapter1.class.getSimpleName();
    private final Context context;
    RestraPosCreator posCreator = RestraPosHelper.getPosCreator();
    RestraPosCartItem cartItem;
    SharedPreference sharedPreference;
    Activity mActivity;

    public Restrapos_Adapter1( Context context, List<RestraPosCartItem> posCartItems) {
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
    public RestraPosCartItem getItem(int position) {
        return posCartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.restaurent_list_tem1, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final RestraPosCartItem posCartItem = getItem(position);
        final SelectedItemsList item = posCartItem.getItem();
        viewHolder.edItemName.setVisibility(View.GONE);
       // viewHolder.edItemPrice.setVisibility(View.GONE);
        viewHolder.edItemQty.setVisibility(View.GONE);
       // viewHolder.edGstTax.setVisibility(View.GONE);
    //    viewHolder.edAmountExTax.setVisibility(View.GONE);
        viewHolder.edAmountInTax.setVisibility(View.GONE);
     //   viewHolder.chkbxSelection.setVisibility(View.GONE);
        viewHolder.imgViewDelete.setVisibility(View.GONE);

        if (posCartItem != null) {
            if (item != null) {



                QuantityTextWatcher oldQtyWatcher = (QuantityTextWatcher) viewHolder.edItemQty.getTag();
                if (oldQtyWatcher != null)
                    viewHolder.edItemQty.removeTextChangedListener(oldQtyWatcher);
                QuantityTextWatcher qtyTextWatcher = new QuantityTextWatcher(item, viewHolder);
                viewHolder.edItemQty.setTag(qtyTextWatcher);
                viewHolder.edItemQty.addTextChangedListener(qtyTextWatcher);

               /* PriceTextWatcher oldPriceWatcher = (PriceTextWatcher) viewHolder.edItemPrice.getTag();
                if (oldPriceWatcher != null)
                    viewHolder.edItemPrice.removeTextChangedListener(oldPriceWatcher);
                PriceTextWatcher priceTextWatcher = new PriceTextWatcher(item, viewHolder);
                viewHolder.edItemPrice.setTag(priceTextWatcher);
                viewHolder.edItemPrice.addTextChangedListener(priceTextWatcher);*/

                if (item.getItemName() != null)
                    viewHolder.edItemName.setText(item.getItemName());

                viewHolder.edItemQty.setText("" + item.getItemQuantity());

               /* if (posCartItem.getItem().getUnitPrice() != null)
                    viewHolder.edItemPrice.setText("" + String.format("%.2f",item.getUnitPrice()));*/


                updateAmount(viewHolder, item, item.getItemQuantity());

                viewHolder.imgViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<SelectedItemsList> selectItemForDelete = new ArrayList<SelectedItemsList>();
                        selectItemForDelete.add(item);
                        posCreator.delete(selectItemForDelete);
                        ((RestuarantActivity)mActivity).checkCartList();



                       /* posCreator.delete(selectItemForDelete);
                        notifyDataSetChanged();*/


                     /*   boolean deleteStatus=posCreator.delete(item);
                        if (deleteStatus) {
                            notifyDataSetChanged();
                        }*/
                    }
                });


/*
                viewHolder.chkbxSelection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            posCartItem.getItem().setSelect(isChecked);
                            RestuarantActivity.selectItemForDelete.add(posCartItem.getItem());
                        } else {
                            posCartItem.getItem().setSelect(isChecked);
                            RestuarantActivity.selectItemForDelete.remove(posCartItem.getItem());
                        }
                    }
                });*/
                viewHolder.edItemName.setVisibility(View.VISIBLE);
                viewHolder.edItemQty.setVisibility(View.VISIBLE);
                /*viewHolder.edItemPrice.setVisibility(View.GONE);
                viewHolder.edGstTax.setVisibility(View.GONE);*/
        //        viewHolder.edAmountExTax.setVisibility(View.VISIBLE);
                viewHolder.edAmountInTax.setVisibility(View.VISIBLE);
              //  viewHolder.chkbxSelection.setVisibility(View.GONE);
                viewHolder.imgViewDelete.setVisibility(View.VISIBLE);

            }
        }

        return convertView;
    }

    private void updateAmount(ViewHolder viewHolder, SelectedItemsList item, long itemQuantity) {

        double gstTaxPercantage =item.getGstTaxPercantage();
        double gstTaxAmt;
        gstTaxAmt = ((itemQuantity * item.getUnitPrice() * gstTaxPercantage) / 100);
       // viewHolder.edGstTax.setText("" +String.format("%.2f",gstTaxAmt) );
        item.setGstTaxPercantage(gstTaxPercantage);
        item.setGstTaxAmt(gstTaxAmt);

        double amountExTax = item.getItemQuantity() * item.getUnitPrice();
        double amountInTax = amountExTax + gstTaxAmt;

        //viewHolder.edAmountExTax.setText("" + String.format("%.2f",amountExTax));
        viewHolder.edAmountInTax.setText("" + String.format("%.2f",amountInTax));

        item.setItemTotalAmountExTax(amountExTax);
        item.setItemTotalAmountInTax(amountInTax);

    }

    public void updateCartItems(List<RestraPosCartItem> posCartItems) {
        this.posCartItems = posCartItems;
        notifyDataSetChanged();
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private class QuantityTextWatcher implements TextWatcher {

        ViewHolder holder;
        SelectedItemsList item;

        private QuantityTextWatcher(SelectedItemsList item, ViewHolder holder) {

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
                    } else {
                       /* fragment_createPOS.flag=0;
                        fragment_createPOS.isCheckoutable=true;*/
                        holder.edItemQty.setError(null);
                        item.setItemQuantity(quantity);
                        //posCreator.update(item, quantity);
                        updateAmount(holder, item, quantity);
                        posCreator.updateitemPriceIncludeTax(item, Double.parseDouble(holder.edAmountInTax.getText().toString().trim()));

                        try {
                            ((RestuarantActivity)context).showTotalPrice();
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

        }
    }

    /*private class PriceTextWatcher implements TextWatcher {
        ViewHolder holder;
        SelectedItemsList item;

        private PriceTextWatcher(SelectedItemsList item, ViewHolder holder) {

            this.holder = holder;
            this.item = item;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            if (s.toString().equals("")) {
               *//* fragment_createPOS.isCheckoutable=false;
                fragment_createPOS.flag=1;*//*
                holder.edItemPrice.setError(null);
            //    holder.edAmountExTax.setText("0.00");
                holder.edAmountInTax.setText("0.00");
                holder.edGstTax.setText("0.00");


            } else {
                try {
                   *//* fragment_createPOS.flag=0;
                    fragment_createPOS.isCheckoutable=true;*//*
                    holder.edItemPrice.setError(null);
                    posCreator.updateUnitPrice(item, new BigDecimal(s.toString()));
                    updateAmount(holder, item, item.getItemQuantity());
                    posCreator.updateitemPriceIncludeTax(item, Double.parseDouble(holder.edAmountInTax.getText().toString().trim()));

                    ((RestuarantActivity)context).showTotalPrice();

                } catch (NumberFormatException e) {
                    *//*fragment_createPOS.flag=5;
                    fragment_createPOS.isCheckoutable=false;*//*
                    holder.edItemPrice.setError("Must be numeric value with only one .");
                }
            }


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }*/
}

