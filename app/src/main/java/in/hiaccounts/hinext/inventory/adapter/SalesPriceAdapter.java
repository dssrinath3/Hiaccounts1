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
import in.hiaccounts.hinext.inventory.model.salespricing.InventorySalesPricing;
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
public class SalesPriceAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;

    public SalesPriceAdapter(Context context, List<Object> objects) {
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
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_salespriceview, null);
            holder.setTvItemName((TextView) convertView.findViewById(R.id.tvItemName));
            holder.setTvCustomerName((TextView) convertView.findViewById(R.id.tvCustomerName));
            holder.setTvSalesPrice((TextView) convertView.findViewById(R.id.tvSalesPrice));
            holder.setImgviewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.getTvItemName().setVisibility(View.GONE);
        holder.getTvCustomerName().setVisibility(View.GONE);
        holder.getTvSalesPrice().setVisibility(View.GONE);
        holder.getImgviewEdit().setVisibility(View.GONE);
        holder.getTvEdit().setVisibility(View.GONE);


        final Object obj = getItem(position);
        if (obj instanceof InventorySalesPricing) {
            final InventorySalesPricing salesPrice = (InventorySalesPricing) obj;
            if (salesPrice != null) {
                if (salesPrice.getItemName() != null)
                    holder.getTvItemName().setText(salesPrice.getItemName());
                if (salesPrice.getCustomerName() != null)
                    holder.getTvCustomerName().setText(salesPrice.getCustomerName());

                holder.getTvSalesPrice().setText("" + salesPrice.getPrice());

                holder.getTvItemName().setVisibility(View.VISIBLE);
                holder.getTvCustomerName().setVisibility(View.VISIBLE);
                holder.getTvSalesPrice().setVisibility(View.VISIBLE);
                holder.getImgviewEdit().setVisibility(View.VISIBLE);
                holder.getTvEdit().setVisibility(View.GONE);
            }

            holder.getImgviewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (serverUrl != null) {
                        editSalesPrice(salesPrice, obj);
                    } else {
                        UtilView.gotToLogin(activity);
                    }
                }
            });


        }
        return convertView;
    }

    private void editSalesPrice(final InventorySalesPricing salesPrice, final Object obj) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_salesprice);
        dialog.setCancelable(false);
        final EditText edCustomerName = dialog.findViewById(R.id.edCustomerName);
        final EditText edItemName = dialog.findViewById(R.id.edItemName);
        final EditText edPrice = dialog.findViewById(R.id.edPrice);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);


        btnAdd.setText("Update");

        edCustomerName.setFocusable(false);
        edCustomerName.setFocusableInTouchMode(false);
        edCustomerName.setBackgroundColor(activity.getResources().getColor(R.color.colorGrey));

        edItemName.setFocusable(false);
        edItemName.setFocusableInTouchMode(false);
        edItemName.setBackgroundColor(activity.getResources().getColor(R.color.colorGrey));


        edItemName.setText(salesPrice.getItemName());
        edCustomerName.setText(salesPrice.getCustomerName());
        edPrice.setText("" + salesPrice.getPrice());


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price = edPrice.getText().toString().trim();
                boolean isUpdatble=true;
                double doubleprice=0;
                if (price == null || price.equals("")) {
                    edPrice.setError("Price can't be empty");
                }else {

                    try {
                        doubleprice = Double.parseDouble(price);
                        isUpdatble=true;
                    } catch (NumberFormatException ne) {
                        edPrice.setError("Please enter valid price");
                        isUpdatble = false;

                    }

                    if (isUpdatble){
                        String url = serverUrl + "/hipos//" + salesPrice.getSalesPriceId() + "/" + Constant.FUNTION_GETINVENTORY_UPDATESALESPRICING;


                        if (!url.equals("")) {
                            isInternetPresent = serviceHandler.isConnectingToInternet();
                            if (isInternetPresent) {

                                pgview.setVisibility(View.VISIBLE);
                                InventorySalesPricing salesPriceData = new InventorySalesPricing();


                                salesPriceData.setSalesPriceId(salesPrice.getSalesPriceId());
                                salesPriceData.setCustomerID(salesPrice.getCustId());
                                salesPriceData.setCustomerName(salesPrice.getCustomerName());
                                salesPriceData.setItemID(salesPrice.getItemid());
                                salesPriceData.setItemName(salesPrice.getItemName());
                                salesPriceData.setPrice(doubleprice);


                                PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                    @Override
                                    public void onTaskComplete(String result) {
                                        pgview.setVisibility(View.GONE);
                                        if (result != null) {
                                            Gson gson = new Gson();
                                            InventorySalesPricing salesPrice = gson.fromJson(result.toString(), InventorySalesPricing.class);
                                            if (salesPrice != null) {

                                                remove(obj);
                                                add(salesPrice);
                                                notifyDataSetChanged();
                                                if (dialog != null && dialog.isShowing()) {
                                                    dialog.dismiss();
                                                }
                                                UtilView.showToast(activity, "Sales Price Update Successfully");

                                            } else {
                                                UtilView.showToast(activity, "Sales Price not update successfully. Please Try Again.");
                                            }
                                        } else {
                                            UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                        }
                                    }
                                }, false);
                                postDataTask.execute(new Gson().toJson(salesPriceData).toString(), url, "");


                            }
                            if (!isInternetPresent) {
                                UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
                            }
                        }
                    }

                }
            }
        });


        dialog.show();
    }

    public class Holder {
        TextView tvItemName;
        TextView tvCustomerName;
        TextView tvSalesPrice;
        TextView tvEdit;
        ImageView imgviewEdit;

        public TextView getTvItemName() {
            return tvItemName;
        }

        public void setTvItemName(TextView tvItemName) {
            this.tvItemName = tvItemName;
        }

        public TextView getTvCustomerName() {
            return tvCustomerName;
        }

        public void setTvCustomerName(TextView tvCustomerName) {
            this.tvCustomerName = tvCustomerName;
        }

        public TextView getTvSalesPrice() {
            return tvSalesPrice;
        }

        public void setTvSalesPrice(TextView tvSalesPrice) {
            this.tvSalesPrice = tvSalesPrice;
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
