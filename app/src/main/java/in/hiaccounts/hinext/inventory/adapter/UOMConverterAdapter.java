package in.hiaccounts.hinext.inventory.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.activity.Activity_UOMConverter;
import in.hiaccounts.hinext.inventory.model.uomconverter.UOMConvSelectData;
import in.hiaccounts.hinext.inventory.model.uomconverter.UomConverterDatumData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by shrinath on 12/6/2017.
 */

public class UOMConverterAdapter extends ArrayAdapter<UomConverterDatumData> {

    private LayoutInflater layoutInflater;
    private Activity activity;
    private String serverUrl;
    private boolean isInternatePrsent = false;
    private ServiceHandler serviceHandler;

    public UOMConverterAdapter(Activity activity, List<UomConverterDatumData> obj) {
        super(activity,0, obj);
        this.activity = activity;
        layoutInflater = LayoutInflater.from(activity);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }



    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_uom_converterview, null);
            holder.setTv_UOM((TextView) convertView.findViewById(R.id.tvUom));
            holder.setTv_UOMConVName((TextView) convertView.findViewById(R.id.tvUomConvName));
            holder.setTv_UOMConVValue((TextView) convertView.findViewById(R.id.tvUomConvValue));
            holder.setTv_UOMsedit((TextView) convertView.findViewById(R.id.tvUomEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImageViewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.getTv_UOM().setVisibility(View.GONE);
        holder.getTv_UOMConVName().setVisibility(View.GONE);
        holder.getTv_UOMConVValue().setVisibility(View.GONE);
        holder.getTv_UOMsedit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getImageViewDelete().setVisibility(View.GONE);


        final UomConverterDatumData uomConvSelectData = getItem(position);

            if (uomConvSelectData != null) {
                if (uomConvSelectData.getUnitOfMeasurement() != null)
                    holder.getTv_UOM().setText(uomConvSelectData.getUnitOfMeasurement().getUnitOfMeasurementName());
                if (uomConvSelectData.getUomConvertedName() != null)
                    holder.getTv_UOMConVName().setText(uomConvSelectData.getUomConvertedName());
                if (uomConvSelectData.getUomValue() != null)
                    holder.getTv_UOMConVValue().setText(uomConvSelectData.getUomValue());


                holder.getTv_UOM().setVisibility(View.VISIBLE);
                holder.getTv_UOMConVName().setVisibility(View.VISIBLE);
                holder.getTv_UOMConVValue().setVisibility(View.VISIBLE);
                holder.getTv_UOMsedit().setVisibility(View.VISIBLE);
                holder.getImageViewEdit().setVisibility(View.VISIBLE);
                holder.getImageViewDelete().setVisibility(View.VISIBLE);
            }

            holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (uomConvSelectData != null) {
                        Intent intent = new Intent(activity, Activity_UOMConverter.class);
                        intent.putExtra("uomData", uomConvSelectData);
                        intent.putExtra("callingFor", Constant.CALL_EDITUOMCONVERTER);
                        activity.startActivityForResult(intent, Constant.RESQUSTCODE_EDITUOMCONVERTER);
                    }
                }
            });

            holder.getImageViewDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (serverUrl != null) {
                        deleteUOMConverter(uomConvSelectData);
                    } else {
                        UtilView.gotToLogin(activity);
                    }
                }
            });


        return convertView;
    }

    private void deleteUOMConverter(final UomConverterDatumData uomConvSelectData) {
        isInternatePrsent = serviceHandler.isConnectingToInternet();
        if (isInternatePrsent) {
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

                    String url = serverUrl+ "/hipos/" + uomConvSelectData.getUomConvertorId() + "/" + Constant.FUNTION_DELETEUOMCOVERTER;
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

                                        UOMConvSelectData uomData = gson.fromJson(result.toString(), UOMConvSelectData.class);
                                        if (uomData != null) {
                                            if (sDialog != null) {
                                                sDialog.setTitleText(activity.getString(R.string.delconfirm))
                                                        .setConfirmText(activity.getString(R.string.bntok))
                                                        .setConfirmClickListener(null)
                                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                remove(uomConvSelectData);
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
                    postDataTask.execute(new Gson().toJson(uomConvSelectData).toString(), url, "");

                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
        }
    }

    public class Holder{
        TextView tv_UOM;
        TextView tv_UOMConVName;
        TextView tv_UOMConVValue;
        TextView tv_UOMsedit;
        ImageView imageViewEdit;
        ImageView imageViewDelete;


        public TextView getTv_UOM() {
            return tv_UOM;
        }

        public void setTv_UOM(TextView tv_UOM) {
            this.tv_UOM = tv_UOM;
        }

        public TextView getTv_UOMConVName() {
            return tv_UOMConVName;
        }

        public void setTv_UOMConVName(TextView tv_UOMConVName) {
            this.tv_UOMConVName = tv_UOMConVName;
        }

        public TextView getTv_UOMConVValue() {
            return tv_UOMConVValue;
        }

        public void setTv_UOMConVValue(TextView tv_UOMConVValue) {
            this.tv_UOMConVValue = tv_UOMConVValue;
        }

        public TextView getTv_UOMsedit() {
            return tv_UOMsedit;
        }

        public void setTv_UOMsedit(TextView tv_UOMsedit) {
            this.tv_UOMsedit = tv_UOMsedit;
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
