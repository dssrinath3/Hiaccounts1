package in.hiaccounts.hinext.controlpanel.adapter;


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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.email_server.EmailServerDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author danielme.com
 */
public class EmailServerAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;

    public EmailServerAdapter(Context context, List<Object> objects) {
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

            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_emailserver, null);

            holder.setTvEmailServer((TextView) convertView.findViewById(R.id.tvEmailServer));
            holder.setTvhost((TextView) convertView.findViewById(R.id.tvhost));
            holder.setTvPortNumber((TextView) convertView.findViewById(R.id.tvPortNumber));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setImgviewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getTvEmailServer().setVisibility(View.GONE);
        holder.getTvPortNumber().setVisibility(View.GONE);
        holder.getTvhost().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getImgviewDelete().setVisibility(View.GONE);
        final Object obj = getItem(position);
        if (obj instanceof EmailServerDatum) {

            final EmailServerDatum emailData = (EmailServerDatum) obj;

            if (emailData != null) {

                if (emailData.getUserName() != null)
                    holder.getTvEmailServer().setText(emailData.getUserName());
                if (emailData.getPort() != null)
                    holder.getTvPortNumber().setText(emailData.getPort());
                if (emailData.getSmtpHost() != null)
                    holder.getTvhost().setText(emailData.getSmtpHost());

                holder.getTvEdit().setVisibility(View.GONE);
                holder.getImageViewEdit().setVisibility(View.VISIBLE);
                holder.getImgviewDelete().setVisibility(View.VISIBLE);
                holder.getTvEmailServer().setVisibility(View.VISIBLE);
                holder.getTvPortNumber().setVisibility(View.VISIBLE);
                holder.getTvhost().setVisibility(View.VISIBLE);
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

                    deleteEmail(emailData, obj);

                }
            });


        }
        return convertView;
    }


    private void deleteEmail(final EmailServerDatum emailData, final Object obj) {

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

                        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_DELETEMAILSERVER;
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

                                            EmailServerDatum emailServerData1 = gson.fromJson(result.toString(), EmailServerDatum.class);
                                            if (emailServerData1 != null) {
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
                                            if (sDialog != null) {
                                                sDialog
                                                        .setTitleText(activity.getString(R.string.delfail))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                        .setConfirmText(activity.getString(R.string.bntok))
                                                        .setConfirmClickListener(null)
                                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                            }

                                        }
                                    }
                                }
                            }
                        }, false);
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("id", emailData.getId());
                            postDataTask.execute(jsonObject.toString().toString(), url, "");
                        } catch (JSONException je) {

                        }


                    }
                });
                pDialog.show();
            } else {
                UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }


    }

    public class Holder {


        TextView tvEmailServer, tvhost, tvEdit, tvPortNumber;
        ImageView imageViewEdit;
        ImageView imgviewDelete;

        public TextView getTvEmailServer() {
            return tvEmailServer;
        }

        public void setTvEmailServer(TextView tvEmailServer) {
            this.tvEmailServer = tvEmailServer;
        }

        public TextView getTvhost() {
            return tvhost;
        }

        public void setTvhost(TextView tvhost) {
            this.tvhost = tvhost;
        }

        public TextView getTvEdit() {
            return tvEdit;
        }

        public void setTvEdit(TextView tvEdit) {
            this.tvEdit = tvEdit;
        }

        public TextView getTvPortNumber() {
            return tvPortNumber;
        }

        public void setTvPortNumber(TextView tvPortNumber) {
            this.tvPortNumber = tvPortNumber;
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
