package in.hiaccounts.hinext.inventory.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.loyalty.LoyaltySelectData;

/**
 * Created by shrinath on 12/11/2017.
 */

public class LoyaltyAdapter extends ArrayAdapter<LoyaltySelectData> {
    List<LoyaltySelectData> loyaltySelectDataList;
    private LayoutInflater layoutInflater;
    private Activity activity;

    public LoyaltyAdapter(Activity activity,List<LoyaltySelectData> loyaltySelectDataList) {
        super(activity, 0,loyaltySelectDataList);
        this.activity = activity;
        this.loyaltySelectDataList = loyaltySelectDataList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_loyaltyview, null);
            holder.setTv_Redemptionprogram((TextView) convertView.findViewById(R.id.tv_loyaltyRedemptionprogram));
            holder.setTv_Currency((TextView) convertView.findViewById(R.id.tv_loyaltyCurrency));
            holder.setTv_PointsPerCurrency((TextView) convertView.findViewById(R.id.tv_loyaltyPointsPerCurrency));
            holder.setTv_Order((TextView) convertView.findViewById(R.id.tv_loyaltyOrder));
            holder.setTv_PointsPerOrder((TextView) convertView.findViewById(R.id.tv_loyaltyPointsPerOrder));
            holder.setTv_Product((TextView) convertView.findViewById(R.id.tv_loyaltyProduct));
            holder.setTv_PointsPerProduct((TextView) convertView.findViewById(R.id.tv_loyaltyPointsPerProduct));
            holder.setTv_Edit((TextView) convertView.findViewById(R.id.tv_LoyaltyEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.getTv_Redemptionprogram().setVisibility(View.GONE);
        holder.getTv_Currency().setVisibility(View.GONE);
        holder.getTv_PointsPerCurrency().setVisibility(View.GONE);
        holder.getTv_Order().setVisibility(View.GONE);
        holder.getTv_PointsPerOrder().setVisibility(View.GONE);
        holder.getTv_Product().setVisibility(View.GONE);
        holder.getTv_PointsPerProduct().setVisibility(View.GONE);
        holder.getTv_Edit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);


        final LoyaltySelectData loyaltySelectData = getItem(position);
        if (loyaltySelectData != null) {
            if (loyaltySelectData.getLoyaltyProgramName().toString() != null) {
                holder.getTv_Redemptionprogram().setVisibility(View.VISIBLE);
                TextView tv_Redemptionprogram = convertView.findViewById(R.id.tv_TextloyaltyRedemptionprogram);
                tv_Redemptionprogram.setText("Redemption Program");
                holder.getTv_Redemptionprogram().setText(loyaltySelectData.getLoyaltyProgramName());
            }
            if (loyaltySelectData.getPerCurrencyPoints() != null){
                holder.getTv_Currency().setVisibility(View.VISIBLE);
                TextView tv_PerCurrency = convertView.findViewById(R.id.tv_TextloyaltyPointsPerCurrency);
                tv_PerCurrency.setText("Points PerCurrency");
                holder.getTv_Currency().setText(loyaltySelectData.getPerCurrencyPoints());
            }


            if (loyaltySelectData.getPerCurrency() != null){
                holder.getTv_PointsPerCurrency().setVisibility(View.VISIBLE);
                TextView tv_Currency = convertView.findViewById(R.id.tv_TextloyaltyCurrency);
                tv_Currency.setText("Currency");
                holder.getTv_PointsPerCurrency().setText(loyaltySelectData.getPerCurrency());
            }


            if (loyaltySelectData.getPerOrderPoints() != null){
                holder.getTv_Order().setVisibility(View.VISIBLE);
                TextView tv_PointsPerOrder = convertView.findViewById(R.id.tv_TextloyaltyPointsPerOrder);
                tv_PointsPerOrder.setText("Points PerOrder");
                holder.getTv_Order().setText(loyaltySelectData.getPerOrderPoints());
            }


            if (loyaltySelectData.getPerOrder() != null){
                holder.getTv_PointsPerOrder().setVisibility(View.VISIBLE);
                TextView tv_PerOrder = convertView.findViewById(R.id.tv_TextloyaltyOrder);
                tv_PerOrder.setText("Order");
                holder.getTv_PointsPerOrder().setText(loyaltySelectData.getPerOrder());
            }


            if (loyaltySelectData.getPerProductPoints() != null){
                holder.getTv_PointsPerProduct().setVisibility(View.VISIBLE);
                TextView tv_PointsPerProduct = convertView.findViewById(R.id.tv_TextloyaltyPointsPerProduct);
                tv_PointsPerProduct.setText("Points PerProduct ");
                holder.getTv_PointsPerProduct().setText(loyaltySelectData.getPerProductPoints());
            }


            if (loyaltySelectData.getPerProduct() != null){
                holder.getTv_Product().setVisibility(View.VISIBLE);
                TextView tv_Product = convertView.findViewById(R.id.tv_TextloyaltyProduct);
                tv_Product.setText("Product ");
                holder.getTv_Product().setText(loyaltySelectData.getPerProduct());
            }

            holder.getTv_Edit().setVisibility(View.GONE);
            holder.getImageViewEdit().setVisibility(View.VISIBLE);
        }

        holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) viewGroup).performItemClick(v, position, 0);

            }
        });


        return convertView;
    }

    public class Holder{
        TextView tv_Redemptionprogram;
        TextView tv_textRedemptionprogram;
        TextView tv_Currency;
        TextView tv_PointsPerCurrency;
        TextView tv_Order;
        TextView tv_PointsPerOrder;
        TextView tv_Product;
        TextView tv_PointsPerProduct;
        TextView tv_Edit;
        ImageView imageViewEdit;

        public TextView getTv_textRedemptionprogram() {
            return tv_textRedemptionprogram;
        }

        public void setTv_textRedemptionprogram(TextView tv_textRedemptionprogram) {
            this.tv_textRedemptionprogram = tv_textRedemptionprogram;
        }

        public TextView getTv_Redemptionprogram() {
            return tv_Redemptionprogram;
        }

        public void setTv_Redemptionprogram(TextView tv_Redemptionprogram) {
            this.tv_Redemptionprogram = tv_Redemptionprogram;
        }

        public TextView getTv_Currency() {
            return tv_Currency;
        }

        public void setTv_Currency(TextView tv_Currency) {
            this.tv_Currency = tv_Currency;
        }

        public TextView getTv_PointsPerCurrency() {
            return tv_PointsPerCurrency;
        }

        public void setTv_PointsPerCurrency(TextView tv_PointsPerCurrency) {
            this.tv_PointsPerCurrency = tv_PointsPerCurrency;
        }

        public TextView getTv_Order() {
            return tv_Order;
        }

        public void setTv_Order(TextView tv_Order) {
            this.tv_Order = tv_Order;
        }

        public TextView getTv_PointsPerOrder() {
            return tv_PointsPerOrder;
        }

        public void setTv_PointsPerOrder(TextView tv_PointsPerOrder) {
            this.tv_PointsPerOrder = tv_PointsPerOrder;
        }

        public TextView getTv_Product() {
            return tv_Product;
        }

        public void setTv_Product(TextView tv_Product) {
            this.tv_Product = tv_Product;
        }

        public TextView getTv_PointsPerProduct() {
            return tv_PointsPerProduct;
        }

        public void setTv_PointsPerProduct(TextView tv_PointsPerProduct) {
            this.tv_PointsPerProduct = tv_PointsPerProduct;
        }

        public TextView getTv_Edit() {
            return tv_Edit;
        }

        public void setTv_Edit(TextView tv_Edit) {
            this.tv_Edit = tv_Edit;
        }

        public ImageView getImageViewEdit() {
            return imageViewEdit;
        }

        public void setImageViewEdit(ImageView imageViewEdit) {
            this.imageViewEdit = imageViewEdit;
        }
    }

}
