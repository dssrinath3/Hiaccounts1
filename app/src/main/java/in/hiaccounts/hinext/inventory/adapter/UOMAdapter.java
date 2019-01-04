package in.hiaccounts.hinext.inventory.adapter;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.uom.UOM;
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
public class UOMAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;

    public UOMAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_uomview, null);
            holder.setTvUomName((TextView) convertView.findViewById(R.id.tvUomName));
            holder.setTvUomDescription((TextView) convertView.findViewById(R.id.tvUomDescription));
            holder.setTvUomEdit((TextView) convertView.findViewById(R.id.tvUomEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImgviewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvUomEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getImgviewDelete().setVisibility(View.GONE);
        holder.getTvUomName().setVisibility(View.GONE);
        holder.getTvUomDescription().setVisibility(View.GONE);
        final Object obj = getItem(position);
        if (obj instanceof UOM) {
            final UOM uom = (UOM) obj;
            if (uom != null) {
                if (uom.getUnitofmeasurementname() != null)
                    holder.getTvUomName().setText(uom.getUnitofmeasurementname());
                if (uom.getUnitOfMeasurementDescription() != null)
                    holder.getTvUomDescription().setText(uom.getUnitOfMeasurementDescription());

                holder.getTvUomEdit().setVisibility(View.GONE);
                holder.getImageViewEdit().setVisibility(View.VISIBLE);
                holder.getImgviewDelete().setVisibility(View.VISIBLE);
                holder.getTvUomName().setVisibility(View.VISIBLE);
                holder.getTvUomDescription().setVisibility(View.VISIBLE);
            }

            holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);

                }
            });
            holder.getImgviewDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (serverUrl!=null) {
                        deleteBrand(uom, obj);
                    }else {
                        UtilView.gotToLogin(activity);
                    }

                }
            });


        }
        return convertView;
    }

    private void editBrand(final UOM uom, final Object obj) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_adduom);
        dialog.setCancelable(false);
        final EditText edName = dialog.findViewById(R.id.edName);
        final EditText edDescritpion = dialog.findViewById(R.id.edDescritpion);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);

        if (btnAdd != null)
            btnAdd.setText("Update");

        if (edName != null) {
            edName.setFocusable(false);
            edName.setFocusableInTouchMode(false);
            edName.setBackgroundColor(activity.getResources().getColor(R.color.colorGrey));
            if (uom.getUnitofmeasurementname() != null)
                edName.setText(uom.getUnitofmeasurementname());
        }
        if (edDescritpion != null) {
            if (uom.getUnitOfMeasurementDescription() != null)
                edDescritpion.setText(uom.getUnitOfMeasurementDescription());
        }

        if (btnClose != null)
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) dialog.dismiss();
                }
            });

        edName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        if (btnAdd != null)
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uomName = edName.getText().toString().trim();
                    String uomDescription = edDescritpion.getText().toString().trim();

                    if (uomName == null || uomName.equals("")) {
                        edName.setError("Name can't be empty");

                    } else {
                        String url = "";
                        if (uom.getUnitofmeasurementid() != null)
                            url = serverUrl + "/hipos//" + uom.getUnitofmeasurementid() + "/" + Constant.FUNTION_ADDUOM;
                        if (!url.equals("")) {
                            isInternetPresent = serviceHandler.isConnectingToInternet();
                            if (isInternetPresent) {
                                // prepare the Request
                                if (pgview != null)
                                    pgview.setVisibility(View.VISIBLE);
                                uom.setUnitofmeasurementname(uomName);
                                uom.setUnitOfMeasurementDescription(uomDescription);

                                PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                    @Override
                                    public void onTaskComplete(String result) {
                                        if (pgview != null)
                                            pgview.setVisibility(View.GONE);
                                        if (result != null) {
                                            Gson gson = new Gson();
                                            try {

                                                UOM uom = gson.fromJson(result.toString(), UOM.class);
                                                if (uom != null) {
                                                    remove(obj);
                                                    add(uom);
                                                    notifyDataSetChanged();
                                                    if (dialog != null && dialog.isShowing()) {
                                                        dialog.dismiss();
                                                    }
                                                    UtilView.showToast(activity, "Unit Update Successfully");

                                                } else {
                                                    UtilView.showToast(activity, "Unit not update successfully. Please Try Again.");
                                                }
                                            } catch (Exception e) {
                                                UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                            }

                                        } else {
                                            UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                        }
                                    }
                                }, false);
                                postDataTask.execute(new Gson().toJson(uom).toString(), url, "");
                            }
                            if (!isInternetPresent) {
                                UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
                            }
                        }
                    }
                }
            });
        if (dialog != null)
            dialog.show();
    }

    private void deleteBrand(final UOM uom, final Object obj) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
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
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("unitOfMeasurementDescription", uom.getUnitOfMeasurementDescription());
                        jsonObject.put("unitOfMeasurementId", uom.getUnitofmeasurementid());
                        jsonObject.put("unitOfMeasurementName", uom.getUnitofmeasurementname());
                        jsonObject.put("unitOfMeasurementStatus", uom.getUnitOfMeasurementStatus());
                    } catch (JSONException je) {

                    }

                    String url = "";
                    if (uom.getUnitofmeasurementid() != null) {
                        url = serverUrl + "/hipos//" + uom.getUnitofmeasurementid() + "/" + Constant.FUNTION_DELETEUOM;
                    }


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
                                        UOM uom1 = gson.fromJson(result.toString(), UOM.class);
                                        if (uom1 != null) {
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
                                                sDialog
                                                        .setTitleText(activity.getString(R.string.delfail))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
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
                    postDataTask.execute(jsonObject.toString(), url, "");

                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
        }


    }

    public class Holder {


        TextView tvUomName;
        TextView tvUomDescription;
        TextView tvUomEdit;
        ImageView imageViewEdit;
        ImageView imgviewDelete;

        public TextView getTvUomName() {
            return tvUomName;
        }

        public void setTvUomName(TextView tvUomName) {
            this.tvUomName = tvUomName;
        }

        public TextView getTvUomDescription() {
            return tvUomDescription;
        }

        public void setTvUomDescription(TextView tvUomDescription) {
            this.tvUomDescription = tvUomDescription;
        }

        public TextView getTvUomEdit() {
            return tvUomEdit;
        }

        public void setTvUomEdit(TextView tvUomEdit) {
            this.tvUomEdit = tvUomEdit;
        }

        public ImageView getImageViewEdit() {
            return imageViewEdit;
        }

        public void setImageViewEdit(ImageView imageViewEdit) {
            this.imageViewEdit = imageViewEdit;
        }

        public ImageView getImgviewDelete() {
            return imgviewDelete;
        }

        public void setImgviewDelete(ImageView imgviewDelete) {
            this.imgviewDelete = imgviewDelete;
        }
    }
}
