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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.bank.BankDatum;
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
public class BankAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;

    public BankAdapter(Context context, List<Object> objects) {
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

            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_bank, null);


            holder.setTvBankName((TextView) convertView.findViewById(R.id.tvBankName));
            holder.setTvBranch((TextView) convertView.findViewById(R.id.tvBranch));
            holder.setTvAccountNumber((TextView) convertView.findViewById(R.id.tvAccountNumber));
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
        holder.getTvAccountNumber().setVisibility(View.GONE);
        holder.getTvBankName().setVisibility(View.GONE);
        holder.getTvBranch().setVisibility(View.GONE);


        final Object obj = getItem(position);
        if (obj instanceof BankDatum) {
            final BankDatum bankData = (BankDatum) obj;

            if (bankData != null) {
                if (bankData.getAccountNo() != null)
                    holder.getTvAccountNumber().setText(bankData.getBankAccountNo());

                if (bankData.getBankName() != null)
                    holder.getTvBankName().setText(bankData.getBankName());

                if (bankData.getBranchName() != null)
                    holder.getTvBranch().setText(bankData.getBranchName());

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

                    deleteBank(bankData, obj);

                }
            });

            holder.getImageViewEdit().setVisibility(View.VISIBLE);
            holder.getImgviewDelete().setVisibility(View.VISIBLE);
            holder.getTvAccountNumber().setVisibility(View.VISIBLE);
            holder.getTvBankName().setVisibility(View.VISIBLE);
            holder.getTvBranch().setVisibility(View.VISIBLE);

        }
        return convertView;
    }


    private void deleteBank(final BankDatum bankData, final Object obj) {

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
                        pDialog.show();

                        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_DELETEBANK;
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


                                            BankDatum bankData = gson.fromJson(result.toString(), BankDatum.class);
                                            if (bankData != null) {
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
                            jsonObject.put("bankId", bankData.getBankId());
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

        TextView tvBankName;
        TextView tvBranch;
        TextView tvAccountNumber;
        TextView tvEdit;

        ImageView imageViewEdit;
        ImageView imgviewDelete;

        public TextView getTvBankName() {
            return tvBankName;
        }

        public void setTvBankName(TextView tvBankName) {
            this.tvBankName = tvBankName;
        }

        public TextView getTvBranch() {
            return tvBranch;
        }

        public void setTvBranch(TextView tvBranch) {
            this.tvBranch = tvBranch;
        }

        public TextView getTvAccountNumber() {
            return tvAccountNumber;
        }

        public void setTvAccountNumber(TextView tvAccountNumber) {
            this.tvAccountNumber = tvAccountNumber;
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
