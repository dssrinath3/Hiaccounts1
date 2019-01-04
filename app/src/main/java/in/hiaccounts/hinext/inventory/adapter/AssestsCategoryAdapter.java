package in.hiaccounts.hinext.inventory.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.assestscategory.AssestsCategory;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by shrinath on 12/4/2017.
 */

public class AssestsCategoryAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsent = false;
    private String serverUrl;
    CheckBox edCheckBox;


    public AssestsCategoryAdapter(Context context, List<Object> objects) {
        super(context,0, objects);
        activity = (Activity) context;
        layoutInflater = LayoutInflater.from(context);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
       Holder holder = null;
        if (convertView == null){
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_assests_categoryview,null);
            holder.setTv_AssestCategoryCode((TextView) convertView.findViewById(R.id.tvAssestsCategoryCode));
            holder.setTv_AssestCategoryName((TextView) convertView.findViewById(R.id.tvAssestsCategoryName));
            holder.setTv_AssestCategoryDesc((TextView) convertView.findViewById(R.id.tvAssestsCategoryDescrip));
            holder.setTv_ImageViewEdit((TextView) convertView.findViewById(R.id.tvAssestsCategoryEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImageViewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));
            convertView.setTag(holder);

        }
        else{
            holder = (Holder) convertView.getTag();

        }
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getImageViewDelete().setVisibility(View.GONE);
        holder.getTv_ImageViewEdit().setVisibility(View.GONE);
        holder.getTv_AssestCategoryCode().setVisibility(View.GONE);
        holder.getTv_AssestCategoryName().setVisibility(View.GONE);
        holder.getTv_AssestCategoryDesc().setVisibility(View.GONE);

        final  Object obj = getItem(position);
        if(obj instanceof AssestsCategory){
           final AssestsCategory assestsCategory = (AssestsCategory) obj;
            if(assestsCategory != null){
                if (assestsCategory.getAssestsCategoryCode()!=null )
                    holder.getTv_AssestCategoryCode().setText(assestsCategory.getAssestsCategoryCode());

                if (assestsCategory.getAssestsCategoryName()!=null )
                    holder.getTv_AssestCategoryName().setText(assestsCategory.getAssestsCategoryName());

                if (assestsCategory.getAssestsCategoryDesc()!=null )
                    holder.getTv_AssestCategoryDesc().setText(assestsCategory.getAssestsCategoryDesc());

                holder.getImageViewEdit().setVisibility(View.VISIBLE);
                holder.getImageViewDelete().setVisibility(View.VISIBLE);
                holder.getTv_AssestCategoryCode().setVisibility(View.VISIBLE);
                holder.getTv_AssestCategoryName().setVisibility(View.VISIBLE);
                holder.getTv_AssestCategoryDesc().setVisibility(View.VISIBLE);


            }
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
                        deleteAssestsCategory(assestsCategory, obj);
                    }else {
                        UtilView.gotToLogin(activity);
                    }
                }
            });
        }
        return convertView;
    }




    private void deleteAssestsCategory(final AssestsCategory assestsCategory, final Object obj) {
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

                    String url = serverUrl+ "/hipos/" + assestsCategory.getAssestsCategoryId() + "/" + Constant.FUNTION_DELETEASSESTSCATEGORY;
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

                                        AssestsCategory category = gson.fromJson(result.toString(), AssestsCategory.class);
                                        if (category != null) {
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
                    postDataTask.execute(new Gson().toJson(assestsCategory).toString(), url, "");

                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
        }
    }



    private class Holder{
        private TextView tv_AssestCategoryCode;
        private TextView tv_AssestCategoryName;
        private TextView tv_AssestCategoryDesc;
        private TextView tv_ImageViewEdit;
        private ImageView imageViewEdit;
        private ImageView imageViewDelete;

        public TextView getTv_AssestCategoryCode() {
            return tv_AssestCategoryCode;
        }

        public void setTv_AssestCategoryCode(TextView tv_AssestCategoryCode) {
            this.tv_AssestCategoryCode = tv_AssestCategoryCode;
        }

        public TextView getTv_AssestCategoryName() {
            return tv_AssestCategoryName;
        }

        public void setTv_AssestCategoryName(TextView tv_AssestCategoryName) {
            this.tv_AssestCategoryName = tv_AssestCategoryName;
        }

        public TextView getTv_AssestCategoryDesc() {
            return tv_AssestCategoryDesc;
        }

        public void setTv_AssestCategoryDesc(TextView tv_AssestCategoryDesc) {
            this.tv_AssestCategoryDesc = tv_AssestCategoryDesc;
        }

        public TextView getTv_ImageViewEdit() {
            return tv_ImageViewEdit;
        }

        public void setTv_ImageViewEdit(TextView tv_ImageViewEdit) {
            this.tv_ImageViewEdit = tv_ImageViewEdit;
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

    private boolean defaultTypeCheckMehtod() {
        return edCheckBox.isChecked();
    }
}
