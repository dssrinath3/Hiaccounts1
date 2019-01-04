package in.hiaccounts.hinext.inventory.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.inventorylocation.InventoryLocation;
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
public class InventoryLocationAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;

    public InventoryLocationAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_inventorylocationview, null);
            holder.setTvLocationName((TextView) convertView.findViewById(R.id.tvLocationName));
            holder.setTvContactPerson((TextView) convertView.findViewById(R.id.tvContactPerson));
            holder.setTvGSTIN((TextView) convertView.findViewById(R.id.tvGSTIN));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setImgviewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImgviewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvLocationName().setVisibility(View.GONE);
        holder.getTvContactPerson().setVisibility(View.GONE);
        holder.getTvGSTIN().setVisibility(View.GONE);
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getImgviewDelete().setVisibility(View.GONE);
        holder.getImgviewEdit().setVisibility(View.GONE);

        final Object obj = getItem(position);
        if (obj instanceof InventoryLocation) {
            final InventoryLocation location = (InventoryLocation) obj;

            if (location != null) {
                holder.getTvLocationName().setText(location.getInventoryLocationName());
                holder.getTvContactPerson().setText(location.getInventoryLocationContactPerson());
                holder.getTvGSTIN().setText(location.getgSTIN());

                holder.getTvLocationName().setVisibility(View.VISIBLE);
                holder.getTvContactPerson().setVisibility(View.VISIBLE);
                holder.getTvGSTIN().setVisibility(View.VISIBLE);
                holder.getTvEdit().setVisibility(View.GONE);
                holder.getImgviewDelete().setVisibility(View.VISIBLE);
                holder.getImgviewEdit().setVisibility(View.VISIBLE);
            }
            holder.getImgviewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);

                }
            });

            holder.getImgviewDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteLocation(location, obj);
                }
            });
        }
        return convertView;
    }

    private void deleteLocation(final InventoryLocation location, final Object obj) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText(activity.getString(R.string.delwarningmsg));
                pDialog.setConfirmText(activity.getString(R.string.yesdel));
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(final SweetAlertDialog sDialog) {

                        final SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(activity.getResources().getColor(R.color.colorPrimary));
                        pDialog.setTitleText(activity.getString(R.string.pleasewait));
                        pDialog.setCancelable(false);
                        if (pDialog != null)
                            pDialog.show();


                        String url = serverUrl + "/hipos//" + location.getInventoryLocationId() + "/" + Constant.FUNTION_DELETEINVENTORYLOCATION;
                        PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                if (pDialog != null)
                                    pDialog.dismiss();

                                if (result != null) {
                                    if (result.toString().equals(activity.getString(R.string.error_rsonsecode204))) {
                                        if (sDialog != null)
                                            sDialog.dismissWithAnimation();
                                        UtilView.showToast(activity, activity.getResources().getString(R.string.cantdelete));

                                    }
                                    if (result.toString() != null && !result.toString().equals(activity.getString(R.string.error_rsonsecode204))) {
                                        Gson gson = new Gson();
                                        try {
                                            InventoryLocation inventoryLocation = gson.fromJson(result.toString(), InventoryLocation.class);
                                            if (inventoryLocation != null) {
                                                if (sDialog != null) {
                                                    sDialog.setTitleText(activity.getString(R.string.delconfirm))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                            .setConfirmText(activity.getString(R.string.bntok))
                                                            .setConfirmClickListener(null)
                                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                    remove(obj);
                                                    notifyDataSetChanged();
                                                }
                                            } else {
                                                if (sDialog != null) {
                                                    sDialog.setTitleText(activity.getString(R.string.delfail))
                                                            .setConfirmText(activity.getString(R.string.bntok))
                                                            .setConfirmClickListener(null)
                                                            .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                                }
                                            }


                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                        }
                                    }
                                } else {
                                    UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                }
                            }
                        }, false);
                        postDataTask.execute(new Gson().toJson(location).toString(), url, "");

                    }
                });
                if (pDialog != null)
                    pDialog.show();
            } else {
                UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    public class Holder {
        TextView tvLocationName;
        TextView tvContactPerson;
        TextView tvGSTIN;
        TextView tvEdit;
        ImageView imgviewEdit;
        ImageView imgviewDelete;

        public TextView getTvLocationName() {
            return tvLocationName;
        }

        public void setTvLocationName(TextView tvLocationName) {
            this.tvLocationName = tvLocationName;
        }

        public TextView getTvContactPerson() {
            return tvContactPerson;
        }

        public void setTvContactPerson(TextView tvContactPerson) {
            this.tvContactPerson = tvContactPerson;
        }

        public TextView getTvGSTIN() {
            return tvGSTIN;
        }

        public void setTvGSTIN(TextView tvGSTIN) {
            this.tvGSTIN = tvGSTIN;
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

        public ImageView getImgviewDelete() {
            return imgviewDelete;
        }

        public void setImgviewDelete(ImageView imgviewDelete) {
            this.imgviewDelete = imgviewDelete;
        }
    }
}
