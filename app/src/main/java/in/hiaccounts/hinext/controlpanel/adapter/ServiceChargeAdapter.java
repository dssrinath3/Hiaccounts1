package in.hiaccounts.hinext.controlpanel.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.servicecharge.ServiceChargeData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by shrinath on 11/28/2017.
 */

public class ServiceChargeAdapter extends ArrayAdapter<ServiceChargeData> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    List<ServiceChargeData> serviceChargeDataList;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;

    public ServiceChargeAdapter(Activity activity, List<ServiceChargeData> serviceChargeDataList) {
        super(activity, 0, serviceChargeDataList);
        this.activity = activity;
        this.serviceChargeDataList = serviceChargeDataList;
        layoutInflater = LayoutInflater.from(activity);
        serviceHandler = new ServiceHandler(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_servicecharge, null);

            holder.setTvServiceChargeName((TextView) convertView.findViewById(R.id.tvServiceChargeName));
            holder.setTvServiceChargePercentage((TextView) convertView.findViewById(R.id.tvServicePercentage));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setImgviewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImgviewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvServiceChargeName().setVisibility(View.GONE);
        holder.getTvServiceChargePercentage().setVisibility(View.GONE);
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getImgviewEdit().setVisibility(View.GONE);
        holder.getImgviewDelete().setVisibility(View.GONE);
        final ServiceChargeData servcieData = getItem(position);

        if (servcieData != null) {
            if (servcieData.getServiceChargeName() != null)
                holder.getTvServiceChargeName().setText(servcieData.getServiceChargeName());
            if (servcieData.getServicePercentage() != null)
                holder.getTvServiceChargePercentage().setText("" +servcieData.getServicePercentage());


            holder.getImgviewEdit().setVisibility(View.VISIBLE);
            holder.getImgviewDelete().setVisibility(View.VISIBLE);
            holder.getTvServiceChargeName().setVisibility(View.VISIBLE);
            holder.getTvServiceChargePercentage().setVisibility(View.VISIBLE);
        }

        holder.getImgviewEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) viewGroup).performItemClick(v, position, 0);

            }
        });
        holder.getImgviewDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Object obj = getItem(position);
                deleteServiceCharge(servcieData, obj);
            }
        });



        return convertView;
    }

    private void deleteServiceCharge(final ServiceChargeData servcieData, final Object objData) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        final String serverUrl = UtilView.getUrl(activity);
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
                        pDialog.show();


                        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_DELETESERVICE;
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

                                        JSONObject jsonObject = null;
                                        try {
                                            jsonObject = new JSONObject(result.toString());
                                            ServiceChargeData serviceCharge = gson.fromJson(jsonObject.toString(), ServiceChargeData.class);


                                            if (serviceCharge != null) {
                                                if (sDialog != null) {
                                                    sDialog.setTitleText(activity.getString(R.string.delconfirm))
                                                            .setConfirmText(activity.getString(R.string.bntok))
                                                            .setConfirmClickListener(null)
                                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                                    remove((ServiceChargeData) objData);
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
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                            }
                        }, false);
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("serviceChargeId", servcieData.getServiceChargeId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        postDataTask.execute(jsonObject.toString().toString(), url, "");


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


        TextView tvServiceChargeName;
        TextView tvServiceChargePercentage;
        TextView tvEdit;
        ImageView imgviewEdit;
        ImageView imgviewDelete;

        public ImageView getImgviewDelete() {
            return imgviewDelete;
        }

        public void setImgviewDelete(ImageView imgviewDelete) {
            this.imgviewDelete = imgviewDelete;
        }

        public TextView getTvServiceChargeName() {
            return tvServiceChargeName;
        }

        public void setTvServiceChargeName(TextView tvServiceChargeName) {
            this.tvServiceChargeName = tvServiceChargeName;
        }

        public TextView getTvServiceChargePercentage() {
            return tvServiceChargePercentage;
        }

        public void setTvServiceChargePercentage(TextView tvServiceChargePercentage) {
            this.tvServiceChargePercentage = tvServiceChargePercentage;
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
