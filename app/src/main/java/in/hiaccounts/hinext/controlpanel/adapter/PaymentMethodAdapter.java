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

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod.PaymentMethodDatum;
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
public class PaymentMethodAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;

    public PaymentMethodAdapter(Context context, List<Object> objects) {
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

            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_paymentmethod, null);

            holder.setTvPaymentName((TextView) convertView.findViewById(R.id.tvPaymentName));
            holder.setTvPaymentType((TextView) convertView.findViewById(R.id.tvPaymentType));
            holder.setTvDescription((TextView) convertView.findViewById(R.id.tvDescription));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImgviewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final Object obj = getItem(position);
        if (obj instanceof PaymentMethodDatum) {
            final PaymentMethodDatum paymentData = (PaymentMethodDatum) obj;

            if (paymentData != null) {

                if (paymentData.getPaymentmethodName() != null)
                    holder.getTvPaymentName().setText(paymentData.getPaymentmethodName());

                if (paymentData.getPaymentmethodDescription() != null)
                    holder.getTvDescription().setText(paymentData.getPaymentmethodDescription());

                if (paymentData.getPaymentmethodType() != null)
                    holder.getTvPaymentType().setText(paymentData.getPaymentmethodType());

                holder.getTvDescription().setVisibility(View.VISIBLE);
                holder.getTvPaymentType().setVisibility(View.VISIBLE);
                holder.getTvPaymentName().setVisibility(View.VISIBLE);
                holder.getTvEdit().setVisibility(View.GONE);
                holder.getImageViewEdit().setVisibility(View.VISIBLE);
                holder.getImgviewDelete().setVisibility(View.VISIBLE);
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

                    deletePaymentMethod(paymentData, obj);

                }
            });


        }
        return convertView;
    }


    private void deletePaymentMethod(final PaymentMethodDatum paymnetData, final Object obj) {

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

                        String url = serverUrl + "/retail//" + Constant.FUNTION_DELETEPAYMENTMETHOD;
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

                                            PaymentMethodDatum data = gson.fromJson(result.toString(), PaymentMethodDatum.class);
                                            if (data != null) {
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
                        postDataTask.execute(new Gson().toJson(paymnetData).toString(), url, "");


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

        TextView tvDescription;
        TextView tvPaymentName;
        TextView tvPaymentType;
        TextView tvEdit;
        ImageView imageViewEdit;
        ImageView imgviewDelete;

        public TextView getTvDescription() {
            return tvDescription;
        }

        public void setTvDescription(TextView tvDescription) {
            this.tvDescription = tvDescription;
        }

        public TextView getTvPaymentName() {
            return tvPaymentName;
        }

        public void setTvPaymentName(TextView tvPaymentName) {
            this.tvPaymentName = tvPaymentName;
        }

        public TextView getTvPaymentType() {
            return tvPaymentType;
        }

        public void setTvPaymentType(TextView tvPaymentType) {
            this.tvPaymentType = tvPaymentType;
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
