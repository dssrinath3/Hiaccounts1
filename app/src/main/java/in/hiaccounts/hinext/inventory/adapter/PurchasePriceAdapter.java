package in.hiaccounts.hinext.inventory.adapter;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.purchasepricing.InventoryPurchasePricing;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class PurchasePriceAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;

    public PurchasePriceAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_purchasepriceview, null);
            holder.setTvItemName((TextView) convertView.findViewById(R.id.tvItemName));
            holder.setTvSupplierName((TextView) convertView.findViewById(R.id.tvSupplierName));
            holder.setTvPurchasePrice((TextView) convertView.findViewById(R.id.tvPurchasePrice));
            holder.setImgviewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvItemName().setVisibility(View.GONE);
        holder.getTvSupplierName().setVisibility(View.GONE);
        holder.getTvPurchasePrice().setVisibility(View.GONE);
        holder.getImgviewEdit().setVisibility(View.GONE);
        holder.getTvEdit().setVisibility(View.GONE);


        final Object obj = getItem(position);
        if (obj instanceof InventoryPurchasePricing) {
            final InventoryPurchasePricing purchasePrice = (InventoryPurchasePricing) obj;
            if (purchasePrice != null) {
                holder.getTvItemName().setText(purchasePrice.getItemName());
                holder.getTvSupplierName().setText(purchasePrice.getSupplierName());
                holder.getTvPurchasePrice().setText("" + purchasePrice.getPrice());

                holder.getTvItemName().setVisibility(View.VISIBLE);
                holder.getTvSupplierName().setVisibility(View.VISIBLE);
                holder.getTvPurchasePrice().setVisibility(View.VISIBLE);
                holder.getImgviewEdit().setVisibility(View.VISIBLE);
                holder.getTvEdit().setVisibility(View.GONE);
            }
            holder.getImgviewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (serverUrl != null) {
                        editPurchasePrice(purchasePrice, obj);
                    } else {
                        UtilView.gotToLogin(activity);
                    }
                }
            });
        }
        return convertView;
    }

    private void editPurchasePrice(final InventoryPurchasePricing purchasePrice, final Object obj) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_purchaseprice);
        dialog.setCancelable(false);
        final EditText edSupplierName = dialog.findViewById(R.id.edSupplierName);
        final EditText edItemName = dialog.findViewById(R.id.edItemName);
        final EditText edPurchasePrice = dialog.findViewById(R.id.edPurchasePrice);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);

        if (btnAdd != null)
            btnAdd.setText("Update");

        if (edSupplierName != null) {
            edSupplierName.setFocusable(false);
            edSupplierName.setFocusableInTouchMode(false);
            edSupplierName.setBackgroundColor(activity.getResources().getColor(R.color.colorGrey));
            if (purchasePrice.getSupplierName() != null)
                edSupplierName.setText(purchasePrice.getSupplierName());
        }

        if (edItemName != null) {
            edItemName.setFocusable(false);
            edItemName.setFocusableInTouchMode(false);
            edItemName.setBackgroundColor(activity.getResources().getColor(R.color.colorGrey));
            if (purchasePrice.getItemName() != null)
                edItemName.setText(purchasePrice.getItemName());
        }

        if (edPurchasePrice != null) {
            edPurchasePrice.setText("" + purchasePrice.getPrice());
        }

        if (btnClose != null)
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) dialog.dismiss();
                }
            });

        if (btnAdd != null)
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String price = edPurchasePrice.getText().toString().trim();
                    boolean isUpdatble = true;
                    double doubleprice = 0;
                    if (price == null || price.equals("")) {
                        edPurchasePrice.setError("Price can't be empty");
                    } else {

                        try {
                            doubleprice = Double.parseDouble(price);
                            isUpdatble = true;
                        } catch (NumberFormatException ne) {
                            edPurchasePrice.setError("Please enter valid price");
                            isUpdatble = false;

                        }

                        if (isUpdatble) {
                            String url = serverUrl + "/hipos//" + purchasePrice.getPurchasePriceId() + "/" + Constant.FUNTION_GETINVENTORY_UPDATEPRUCHASEPRICING;

                            if (!url.equals("")) {
                                isInternetPresent = serviceHandler.isConnectingToInternet();
                                if (isInternetPresent) {
                                    // prepare the Request
                                    if (pgview != null)
                                        pgview.setVisibility(View.VISIBLE);
                                    InventoryPurchasePricing purchasePriceData = new InventoryPurchasePricing();

                                    purchasePriceData.setPurchasePriceId(purchasePrice.getPurchasePriceId());
                                    purchasePriceData.setSupplierID(purchasePrice.getSupplierId());
                                    purchasePriceData.setSupplierName(purchasePrice.getSupplierName());
                                    purchasePriceData.setItemID(purchasePrice.getItemId());
                                    purchasePriceData.setItemName(purchasePrice.getItemName());
                                    purchasePriceData.setPrice(doubleprice);


                                    PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                        @Override
                                        public void onTaskComplete(String result) {
                                            if (pgview != null)
                                                pgview.setVisibility(View.GONE);
                                            if (result != null) {
                                                Gson gson = new Gson();
                                                try {

                                                    InventoryPurchasePricing purchasePrice = gson.fromJson(result.toString(), InventoryPurchasePricing.class);
                                                    if (purchasePrice != null) {
                                                        remove(obj);
                                                        add(purchasePrice);
                                                        notifyDataSetChanged();
                                                        if (dialog != null && dialog.isShowing()) {
                                                            dialog.dismiss();
                                                        }
                                                        UtilView.showToast(activity, "Purchase Price Update Successfully");

                                                    } else {
                                                        UtilView.showToast(activity, "Purchase Price not update successfully. Please Try Again.");
                                                    }

                                                } catch (Exception e) {
                                                    UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                                }
                                            } else {
                                                UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                            }
                                        }
                                    }, false);
                                    postDataTask.execute(new Gson().toJson(purchasePriceData).toString(), url, "");
                                }
                                if (!isInternetPresent) {
                                    UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
                                }
                            }
                        }
                    }
                }
            });
        if (dialog != null)
            dialog.show();
    }

    public class Holder {
        TextView tvItemName;
        TextView tvSupplierName;
        TextView tvPurchasePrice;
        TextView tvEdit;
        ImageView imgviewEdit;

        public TextView getTvItemName() {
            return tvItemName;
        }

        public void setTvItemName(TextView tvItemName) {
            this.tvItemName = tvItemName;
        }

        public TextView getTvSupplierName() {
            return tvSupplierName;
        }

        public void setTvSupplierName(TextView tvSupplierName) {
            this.tvSupplierName = tvSupplierName;
        }

        public TextView getTvPurchasePrice() {
            return tvPurchasePrice;
        }

        public void setTvPurchasePrice(TextView tvPurchasePrice) {
            this.tvPurchasePrice = tvPurchasePrice;
        }

        public TextView getTvEdit() {
            return tvEdit;
        }

        public void setTvEdit(TextView tvEdit) {
            this.tvEdit = tvEdit;
        }

        public ImageView getImgviewEdit() {
            return imgviewEdit;
        }

        public void setImgviewEdit(ImageView imgviewEdit) {
            this.imgviewEdit = imgviewEdit;
        }
    }
}
