package in.hiaccounts.hinext.inventory.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.paymentvoucher.PaymentVoucherSelectData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by shrinath on 12/9/2017.
 */

public class PaymentVoucherAdapter extends ArrayAdapter<PaymentVoucherSelectData> {
    List<PaymentVoucherSelectData> paymentVoucherSelectDataList;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;

    public PaymentVoucherAdapter(Activity activity,List<PaymentVoucherSelectData> paymentVoucherSelectDataList) {
        super(activity, 0,paymentVoucherSelectDataList);
        this.activity = activity;
        this.paymentVoucherSelectDataList = paymentVoucherSelectDataList;
        layoutInflater = LayoutInflater.from(activity);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_payment_voucherview, null);
            holder.setTv_amount((TextView) convertView.findViewById(R.id.tv_paymentVoucherAmount));
            holder.setTv_reason((TextView) convertView.findViewById(R.id.tv_paymentVoucherReason));
            holder.setTv_voucherno((TextView) convertView.findViewById(R.id.tv_paymentVoucherNo));
            holder.setTv_edit((TextView) convertView.findViewById(R.id.tv_paymentVoucherEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImgViewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));
            holder.setImgViewPrint((ImageView) convertView.findViewById(R.id.imgviewPrint));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.getTv_amount().setVisibility(View.GONE);
        holder.getTv_reason().setVisibility(View.GONE);
        holder.getTv_voucherno().setVisibility(View.GONE);
        holder.getTv_edit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getImgViewDelete().setVisibility(View.GONE);
        holder.getImgViewPrint().setVisibility(View.GONE);

            final PaymentVoucherSelectData paymentVoucherSelectData = getItem(position);
            if (paymentVoucherSelectData != null) {

                if (paymentVoucherSelectData.getVoucherNo() != null) {
                    holder.getTv_voucherno().setText(paymentVoucherSelectData.getVoucherNo().toString());
                }
                if (paymentVoucherSelectData.getAmount() != null) {
                    holder.getTv_amount().setText(paymentVoucherSelectData.getAmount().toString());
                }
                if (paymentVoucherSelectData.getReason() != null)
                    holder.getTv_reason().setText(paymentVoucherSelectData.getReason());


          /*      Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(Long.parseLong(paymentVoucherSelectData.getFromDate()));
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                String fromDate = String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(day));

                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTimeInMillis(Long.parseLong(paymentVoucherSelectData.getToDate()));
                int year1 = calendar1.get(Calendar.YEAR);
                int month1 = calendar1.get(Calendar.MONTH);
                final int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                String ToDate = String.valueOf(new StringBuilder().append(year1).append("-").append(month1 + 1).append("-").append(day1));

                holder.getTv_date().setText("From - " + fromDate + " " + "To - " + ToDate);
*/

                holder.getTv_amount().setVisibility(View.VISIBLE);
                holder.getTv_reason().setVisibility(View.VISIBLE);
                holder.getTv_voucherno().setVisibility(View.VISIBLE);
                holder.getTv_edit().setVisibility(View.GONE);
                holder.getImageViewEdit().setVisibility(View.VISIBLE);
                holder.getImgViewDelete().setVisibility(View.VISIBLE);
                holder.getImgViewPrint().setVisibility(View.VISIBLE);
            }

            holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) viewGroup).performItemClick(v, position, 0);

                }
            });

            holder.getImgViewDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (serverUrl != null) {
                            deletePaymentVoucher(paymentVoucherSelectData);
                    } else {
                        UtilView.gotToLogin(activity);
                    }

                }
            });

        holder.getImgViewPrint().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((ListView) viewGroup).performItemClick(view, position, 0);
            }
        });


        return convertView;
    }

    private void deletePaymentVoucher(final PaymentVoucherSelectData paymentVoucherSelectData) {
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
                        jsonObject.put("pvId", paymentVoucherSelectData.getPvId());
                    } catch (JSONException je) {

                    }

                    String url = "";
                    if (paymentVoucherSelectData.getPvId() != null)
                        url = serverUrl + "/hipos//1/" + Constant.FUNTION_DELETEPAYMENTVOUCHER + "?pvId=" + paymentVoucherSelectData.getPvId();
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
                                        JSONObject obj = new JSONObject();
                                            obj.put("pvId",result.toString());
                                        PaymentVoucherSelectData vData = gson.fromJson(obj.toString(), PaymentVoucherSelectData.class);
                                        if (vData != null) {
                                            if (sDialog != null) {
                                                sDialog.setTitleText(activity.getString(R.string.delconfirm))
                                                        .setConfirmText(activity.getString(R.string.bntok))
                                                        .setConfirmClickListener(null)
                                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                remove(paymentVoucherSelectData);
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

    public class Holder{
        TextView tv_voucherno;
        TextView tv_amount;
        TextView tv_reason;
        TextView tv_date;
        TextView tv_edit;
        ImageView imageViewEdit;
        ImageView imgViewDelete;
        ImageView imgViewPrint;

        public TextView getTv_amount() {
            return tv_amount;
        }

        public void setTv_amount(TextView tv_amount) {
            this.tv_amount = tv_amount;
        }

        public TextView getTv_reason() {
            return tv_reason;
        }

        public void setTv_reason(TextView tv_reason) {
            this.tv_reason = tv_reason;
        }

        public TextView getTv_date() {
            return tv_date;
        }

        public void setTv_date(TextView tv_date) {
            this.tv_date = tv_date;
        }

        public TextView getTv_edit() {
            return tv_edit;
        }

        public void setTv_edit(TextView tv_edit) {
            this.tv_edit = tv_edit;
        }

        public ImageView getImageViewEdit() {
            return imageViewEdit;
        }

        public void setImageViewEdit(ImageView imageViewEdit) {
            this.imageViewEdit = imageViewEdit;
        }

        public ImageView getImgViewDelete() {
            return imgViewDelete;
        }

        public void setImgViewDelete(ImageView imgViewDelete) {
            this.imgViewDelete = imgViewDelete;
        }

        public TextView getTv_voucherno() {
            return tv_voucherno;
        }

        public void setTv_voucherno(TextView tv_voucherno) {
            this.tv_voucherno = tv_voucherno;
        }

        public ImageView getImgViewPrint() {
            return imgViewPrint;
        }

        public void setImgViewPrint(ImageView imgViewPrint) {
            this.imgViewPrint = imgViewPrint;
        }
    }
}
