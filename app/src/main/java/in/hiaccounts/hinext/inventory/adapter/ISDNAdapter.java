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
import in.hiaccounts.hinext.inventory.model.isdn.ISDNDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by shrinath on 12/5/2017.
 */

public class ISDNAdapter extends ArrayAdapter<Object> {

    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;

    public ISDNAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.inventory_adapter_isdn, null);

            holder.setTvState((TextView) convertView.findViewById(R.id.tvState));
            holder.setTvISDNNumber((TextView) convertView.findViewById(R.id.tvIsdnno));
            holder.setTvDate((TextView) convertView.findViewById(R.id.tvDate));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImgviewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getImgviewDelete().setVisibility(View.GONE);
        holder.getTvState().setVisibility(View.GONE);
        holder.getTvISDNNumber().setVisibility(View.GONE);
        holder.getTvDate().setVisibility(View.GONE);


        final Object obj = getItem(position);
        if (obj instanceof ISDNDatum) {
            final ISDNDatum isdnDatum = (ISDNDatum) obj;


            if (isdnDatum != null) {
                if (isdnDatum.getStatelist() != null)
                    holder.getTvState().setText(isdnDatum.getStatelist());

                if (isdnDatum.getGstApprovalnumber() != null)
                    holder.getTvISDNNumber().setText(isdnDatum.getGstApprovalnumber());

                holder.getTvDate().setText("From - " + isdnDatum.getFromGstApprovalDate() +"  "+"To - "+isdnDatum.getToGstApprovalDate());
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
                    deleteISDN(isdnDatum, obj);
                }
            });
            holder.getTvEdit().setVisibility(View.GONE);
            holder.getImageViewEdit().setVisibility(View.VISIBLE);
            holder.getImgviewDelete().setVisibility(View.VISIBLE);
            holder.getTvState().setVisibility(View.VISIBLE);
            holder.getTvISDNNumber().setVisibility(View.VISIBLE);
            holder.getTvDate().setVisibility(View.VISIBLE);

        }
        return convertView;
    }

    private void deleteISDN(final ISDNDatum isdnDatum, final Object obj) {
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

                        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_DELETEISDN;
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
                                        ISDNDatum isdnData = gson.fromJson(result.toString(), ISDNDatum.class);
                                        if (isdnData != null) {
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
                            jsonObject.put("gstApprovalDateId", isdnDatum.getGstApprovalDateId());

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
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    public class Holder {


        TextView tvState;
        TextView tvISDNNumber;
        TextView tvDate;
        TextView tvEdit;

        ImageView imageViewEdit;
        ImageView imgviewDelete;

        public TextView getTvState() {
            return tvState;
        }

        public void setTvState(TextView tvState) {
            this.tvState = tvState;
        }

        public TextView getTvISDNNumber() {
            return tvISDNNumber;
        }

        public void setTvISDNNumber(TextView tvISDNNumber) {
            this.tvISDNNumber = tvISDNNumber;
        }

        public TextView getTvDate() {
            return tvDate;
        }

        public void setTvDate(TextView tvDate) {
            this.tvDate = tvDate;
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
