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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.attributrconfigurator.AttributeConfig;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by shrinath on 12/8/2017.
 */

public class AttributeConfiguratorAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;

    public AttributeConfiguratorAdapter(Context context, List<Object> obj) {
        super(context,0, obj);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {
       Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_attribute_configuratorview,null,false);
            holder.setTvAttributeConfigName((TextView) convertView.findViewById(R.id.tvAttributeConfigName));
            holder.setTvAttributeConfigDesc((TextView) convertView.findViewById(R.id.tvAttributeConfigDescription));
            holder.setTvAttributeConfigEdit((TextView) convertView.findViewById(R.id.tvAttributeConfigEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImageViewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));
            convertView.setTag(holder);

        }else {
            holder = (Holder) convertView.getTag();
        }

        holder.getTvAttributeConfigEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getImageViewDelete().setVisibility(View.GONE);
        holder.getTvAttributeConfigName().setVisibility(View.GONE);
        holder.getTvAttributeConfigDesc().setVisibility(View.GONE);

        final  Object obj = getItem(position);
        if(obj instanceof AttributeConfig){
            final AttributeConfig attributeConfig = (AttributeConfig) obj;
            if(attributeConfig.getAttributeConfiguratorName() != null)
                holder.getTvAttributeConfigName().setText(attributeConfig.getAttributeConfiguratorName());

            if(attributeConfig.getAttributeConfiguratorDescription() != null)
                holder.getTvAttributeConfigDesc().setText(attributeConfig.getAttributeConfiguratorDescription());


            holder.getTvAttributeConfigEdit().setVisibility(View.VISIBLE);
            holder.getImageViewEdit().setVisibility(View.VISIBLE);
            holder.getImageViewDelete().setVisibility(View.VISIBLE);
            holder.getTvAttributeConfigName().setVisibility(View.VISIBLE);
            holder.getTvAttributeConfigDesc().setVisibility(View.VISIBLE);

            //final View view = convertView;
            holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListView) parent).performItemClick(view, position, 0);
                }
            });

            holder.getImageViewDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (serverUrl!=null) {
                        deleteAttributeConfig(attributeConfig, obj);
                    }else {
                        UtilView.gotToLogin(activity);
                    }
                }
            });
        }

        return convertView;
    }

    private void deleteAttributeConfig(final AttributeConfig attributeConfigs, final Object obj) {

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
                        jsonObject.put("attributeConfiguratorId", attributeConfigs.getAttributeConfiguratorId());
                        jsonObject.put("attributeConfiguratorName", attributeConfigs.getAttributeConfiguratorName());
                        jsonObject.put("attributeConfiguratorDescription", attributeConfigs.getAttributeConfiguratorDescription());
                        jsonObject.put("status", attributeConfigs.getStatus());
                    } catch (JSONException je) {

                    }

                    String url = "";
                    if (attributeConfigs.getAttributeConfiguratorId() != null)
                        url = serverUrl + "/hipos//undefined/" + Constant.FUNTION_DELETEATTRIBUTECONFIG;
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
                                        AttributeConfig attributeData = gson.fromJson(result.toString(), AttributeConfig.class);
                                        if (attributeData != null) {
                                            if (sDialog != null) {
                                                sDialog.setTitleText(activity.getString(R.string.delconfirm))
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

    private class Holder{
        TextView tvAttributeConfigName;
        TextView tvAttributeConfigDesc;
        TextView tvAttributeConfigEdit;
        ImageView imageViewEdit;
        ImageView imageViewDelete;

        public TextView getTvAttributeConfigName() {
            return tvAttributeConfigName;
        }

        public void setTvAttributeConfigName(TextView tvAttributeConfigName) {
            this.tvAttributeConfigName = tvAttributeConfigName;
        }

        public TextView getTvAttributeConfigDesc() {
            return tvAttributeConfigDesc;
        }

        public void setTvAttributeConfigDesc(TextView tvAttributeConfigDesc) {
            this.tvAttributeConfigDesc = tvAttributeConfigDesc;
        }

        public TextView getTvAttributeConfigEdit() {
            return tvAttributeConfigEdit;
        }

        public void setTvAttributeConfigEdit(TextView tvAttributeConfigEdit) {
            this.tvAttributeConfigEdit = tvAttributeConfigEdit;
        }

        public ImageView getImageViewEdit() {
            return imageViewEdit;
        }

        public void setImageViewEdit(ImageView imageViewEdit) {
            this.imageViewEdit = imageViewEdit;
        }

        public ImageView getImageViewDelete() {
            return imageViewDelete;
        }

        public void setImageViewDelete(ImageView imageViewDelete) {
            this.imageViewDelete = imageViewDelete;
        }
    }
}
