package in.hiaccounts.hinext.controlpanel.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import in.hiaccounts.hinext.controlpanel.activity.Activity_Config_UserConfigurator;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.UserAccountData;
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
public class UserAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;

    public UserAdapter(Context context, List<Object> objects) {
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


            convertView = layoutInflater.inflate(R.layout.useraccount_adapter_user, null);

            holder.setTvUserName((TextView) convertView.findViewById(R.id.tvUserName));
            holder.setTvEmail((TextView) convertView.findViewById(R.id.tvEmail));
            holder.setTvInventory((TextView) convertView.findViewById(R.id.tvInventory));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImgviewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));
            holder.setImgviewVisible((ImageView) convertView.findViewById(R.id.imgviewPermission));
            holder.setImgviewConfiguartor((ImageView) convertView.findViewById(R.id.imgviewConfigurator));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvUserName().setVisibility(View.GONE);
        holder.getTvEmail().setVisibility(View.GONE);
        holder.getTvInventory().setVisibility(View.GONE);
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getImgviewDelete().setVisibility(View.GONE);
        holder.getImgviewVisible().setVisibility(View.GONE);
        holder.getImgviewConfiguartor().setVisibility(View.GONE);

        final Object obj = getItem(position);
        if (obj instanceof UserAccountData) {
            final UserAccountData userData = (UserAccountData) obj;

            if (userData != null) {
                if (userData.getUserLoginId() != null)
                    holder.getTvUserName().setText(userData.getUserLoginId());

                if (userData.getEmail() != null)
                    holder.getTvEmail().setText(userData.getEmail());

                if (userData.getLocation() != null) {
                    if (userData.getLocation().getInventoryLocationName() != null) {
                        holder.getTvInventory().setText(userData.getLocation().getInventoryLocationName());
                    }
                }


                holder.getTvUserName().setVisibility(View.VISIBLE);
                holder.getTvEmail().setVisibility(View.VISIBLE);
                holder.getTvInventory().setVisibility(View.VISIBLE);
                holder.getTvEdit().setVisibility(View.GONE);
                holder.getImageViewEdit().setVisibility(View.VISIBLE);
                holder.getImgviewDelete().setVisibility(View.VISIBLE);
                holder.getImgviewVisible().setVisibility(View.VISIBLE);
                holder.getImgviewConfiguartor().setVisibility(View.VISIBLE);
            }

            holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);

                }
            });
            holder.getImgviewVisible().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);

                }
            });

            holder.getImgviewConfiguartor().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(activity,Activity_Config_UserConfigurator.class);
                    in.putExtra("configuaratorData",userData);
                    in.putExtra("callingFor", Constant.CALL_EDITUSERCONFIGUARTOR);

                    activity.startActivity(in);

                }
            });

            holder.getImgviewDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (serverUrl != null) {
                        deleteUser(userData, obj);
                    } else {
                        UtilView.gotToLogin(activity);
                    }

                }
            });


        }
        return convertView;
    }


    private void deleteUser(final UserAccountData userData, final Object obj) {

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

                    String url = serverUrl + "/hipos//0/" + Constant.FUNTION_DELETEUSER;
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

                                        UserAccountData userData1 = gson.fromJson(result.toString(), UserAccountData.class);
                                        if (userData1 != null) {
                                            if (sDialog != null) {
                                                sDialog.setTitleText(activity.getString(R.string.delconfirm))
                                                        .setContentText("User " + userData.getFullName())
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
                                                        .setContentText("User " + userData1.getFullName())
                                                        .setConfirmText(activity.getString(R.string.bntok))
                                                        .setConfirmClickListener(null)
                                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                            }
                                        }

                                    } catch (Exception e) {

                                        if (sDialog != null) {
                                            sDialog
                                                    .setTitleText(activity.getString(R.string.delfail))
                                                    .setContentText("User " + userData.getFullName())
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
                        jsonObject.put("useraccount_id", userData.getUseraccountId());


                        postDataTask.execute(jsonObject.toString().toString(), url, "");
                    } catch (JSONException je) {

                    }


                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
        }


    }

    public class Holder {


        TextView tvUserName;
        TextView tvEmail;
        TextView tvInventory;
        TextView tvEdit;

        ImageView imageViewEdit;
        ImageView imgviewDelete;
        ImageView imgviewVisible;
        ImageView imgviewConfiguartor;

        public ImageView getImgviewConfiguartor() {
            return imgviewConfiguartor;
        }

        public void setImgviewConfiguartor(ImageView imgviewConfiguartor) {
            this.imgviewConfiguartor = imgviewConfiguartor;
        }

        public ImageView getImgviewVisible() {
            return imgviewVisible;
        }

        public void setImgviewVisible(ImageView imgviewVisible) {
            this.imgviewVisible = imgviewVisible;
        }

        public TextView getTvUserName() {
            return tvUserName;
        }

        public void setTvUserName(TextView tvUserName) {
            this.tvUserName = tvUserName;
        }

        public TextView getTvEmail() {
            return tvEmail;
        }

        public void setTvEmail(TextView tvEmail) {
            this.tvEmail = tvEmail;
        }

        public TextView getTvInventory() {
            return tvInventory;
        }

        public void setTvInventory(TextView tvInventory) {
            this.tvInventory = tvInventory;
        }

        public TextView getTvEdit() {
            return tvEdit;
        }

        public void setTvEdit(TextView tvEdit) {
            this.tvEdit = tvEdit;
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
