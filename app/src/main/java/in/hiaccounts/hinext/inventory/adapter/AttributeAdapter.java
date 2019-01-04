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
import in.hiaccounts.hinext.inventory.model.attribute.Attribute;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by shrinath on 12/8/2017.
 */

public class AttributeAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;


    public AttributeAdapter(Context context, List<Object> objectList) {
        super(context, 0,objectList);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_attributeview, null);
            holder.setTvAttributeName((TextView) convertView.findViewById(R.id.tvAttributeName));
            holder.setTvAttributeDesc((TextView) convertView.findViewById(R.id.tvAttributeDescription));
            holder.setTvAttributeEdit((TextView) convertView.findViewById(R.id.tvAttributeEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImgviewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvAttributeEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getImgviewDelete().setVisibility(View.GONE);
        holder.getTvAttributeName().setVisibility(View.GONE);
        holder.getTvAttributeDesc().setVisibility(View.GONE);
        final Object obj = getItem(position);
        if (obj instanceof Attribute) {
            final Attribute attribute = (Attribute) obj;
            if (attribute != null) {
                if (attribute.getAttributeName() != null)
                    holder.getTvAttributeName().setText(attribute.getAttributeName());
                if (attribute.getAttributeDescription() != null)
                    holder.getTvAttributeDesc().setText(attribute.getAttributeDescription());

                holder.getTvAttributeEdit().setVisibility(View.VISIBLE);
                holder.getImageViewEdit().setVisibility(View.VISIBLE);
                holder.getImgviewDelete().setVisibility(View.VISIBLE);
                holder.getTvAttributeName().setVisibility(View.VISIBLE);
                holder.getTvAttributeDesc().setVisibility(View.VISIBLE);
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
                        deleteAttribute(attribute, obj);
                    }else {
                        UtilView.gotToLogin(activity);
                    }

                }
            });


        }

        return convertView;
    }

    private void deleteAttribute(final Attribute attribute, final Object obj) {

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
                        jsonObject.put("attributeId", attribute.getAttributeId());
                    } catch (JSONException je) {

                    }

                    String url = "";
                    if (attribute.getAttributeId() != null)
                        url = serverUrl + "/hipos//1/" + Constant.FUNTION_DELETEATTRIBUTE + "?attributeId=" + attribute.getAttributeId();
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

                                        JSONObject objs = new JSONObject();
                                        objs.put("attributeId",result.toString());
                                        Attribute attributeData = gson.fromJson(objs.toString(), Attribute.class);
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
        TextView tvAttributeName;
        TextView tvAttributeDesc;
        TextView tvAttributeEdit;
        ImageView imageViewEdit;
        ImageView imgviewDelete;

        public TextView getTvAttributeName() {
            return tvAttributeName;
        }

        public void setTvAttributeName(TextView tvAttributeName) {
            this.tvAttributeName = tvAttributeName;
        }

        public TextView getTvAttributeDesc() {
            return tvAttributeDesc;
        }

        public void setTvAttributeDesc(TextView tvAttributeDesc) {
            this.tvAttributeDesc = tvAttributeDesc;
        }

        public TextView getTvAttributeEdit() {
            return tvAttributeEdit;
        }

        public void setTvAttributeEdit(TextView tvAttributeEdit) {
            this.tvAttributeEdit = tvAttributeEdit;
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
