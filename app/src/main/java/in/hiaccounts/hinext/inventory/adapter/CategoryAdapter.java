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
import in.hiaccounts.hinext.inventory.model.category.Category;
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
public class CategoryAdapter extends ArrayAdapter<Object> {
    Category category = null;
    CheckBox edCheckBox;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsent = false;
    private String serverUrl;

    public CategoryAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        activity = (Activity) context;
        layoutInflater = LayoutInflater.from(context);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.inventory_adapter_categoryview, null);

            holder.setTvCategoryEdit((TextView) convertView.findViewById(R.id.tvCategoryEdit));
            holder.setTvCategoryCode((TextView) convertView.findViewById(R.id.tvCategoryCode));
            holder.setTvCategoryName((TextView) convertView.findViewById(R.id.tvCategoryName));
            holder.setTvCategoryDescrip((TextView) convertView.findViewById(R.id.tvCategoryDescrip));
            holder.setImgviewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImgviewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvCategoryEdit().setVisibility(View.GONE);
        holder.getImgviewDelete().setVisibility(View.GONE);
        holder.getImgviewEdit().setVisibility(View.GONE);
        holder.getTvCategoryCode().setVisibility(View.GONE);
        holder.getTvCategoryName().setVisibility(View.GONE);
        holder.getTvCategoryDescrip().setVisibility(View.GONE);

        final Object obj = getItem(position);
        if (obj instanceof Category) {
          final Category category = (Category) obj;

            if (category != null) {

                if (category.getItemCategoryCode() != null)
                    holder.getTvCategoryCode().setText(category.getItemCategoryCode());
                if (category.getItemCategoryName() != null)
                    holder.getTvCategoryName().setText(category.getItemCategoryName());
                if (category.getItemCategoryDesc() != null)
                    holder.getTvCategoryDescrip().setText("" + category.getItemCategoryDesc());

                    holder.getImgviewEdit().setVisibility(View.VISIBLE);
                     holder.getImgviewDelete().setVisibility(View.VISIBLE);
                    holder.getTvCategoryCode().setVisibility(View.VISIBLE);
                    holder.getTvCategoryName().setVisibility(View.VISIBLE);
                    holder.getTvCategoryDescrip().setVisibility(View.VISIBLE);

            }

            holder.getImgviewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);

                }
            });

            holder.getImgviewDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (serverUrl!=null) {
                        deleteCategory(category, obj);
                    }else {
                        UtilView.gotToLogin(activity);
                    }
                }
            });


        }
        return convertView;
    }

    public void editCategory(final Category category, final Object obj) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_addcategory);
        dialog.setCancelable(false);
        final EditText edCategoryName = dialog.findViewById(R.id.edCategoryName);
        final EditText edCategoryDescritpion = dialog.findViewById(R.id.edCategoryDescritpion);
        TextView defaultType = dialog.findViewById(R.id.id_default_type);
        edCheckBox = dialog.findViewById(R.id.checkbox_type);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);

        edCategoryName.setFocusable(false);
        edCategoryName.setFocusableInTouchMode(false);
        edCategoryName.setBackgroundColor(activity.getResources().getColor(R.color.colorGrey));

        if (edCategoryDescritpion != null) {
                edCategoryDescritpion.setText(category.getItemCategoryDesc());
        }

        if (edCategoryName != null) {
                edCategoryName.setText(category.getItemCategoryName());
        }

            if(category.getDefaultType() != null){

                    if (category.getDefaultType() == true){
                        edCheckBox.setChecked(true);
                    }else{
                        edCheckBox.setChecked(false);
                    }
            }
            else {
                edCheckBox.setChecked(false);
            }

        if (btnAdd != null)
            btnAdd.setText("Update");

        if (btnClose != null)
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) dialog.dismiss();
                }
            });

        edCategoryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edCategoryName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (btnAdd != null)
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isInternatePrsent = serviceHandler.isConnectingToInternet();
                    if (isInternatePrsent) {
                        String cName = edCategoryName.getText().toString().trim();
                        String cDescription = edCategoryDescritpion.getText().toString().trim();

                        category.setItemCategoryName(cName);
                        category.setItemCategoryDesc(cDescription);
                        category.setDefaultType(defaultTypeCheckMehtod());

                        if (pgview != null)
                            pgview.setVisibility(View.VISIBLE);
                        String url = serverUrl+ "/hipos//" + category.getItemCategoryId() + "/" + Constant.FUNTION_ADDCATEGORY;
                        PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                if (pgview != null)
                                    pgview.setVisibility(View.GONE);
                                if (result != null) {
                                    try {

                                        Gson gson = new Gson();
                                        Category category = gson.fromJson(result.toString(), Category.class);
                                        if (category != null) {
                                            remove(obj);
                                            add(category);
                                            notifyDataSetChanged();
                                            UtilView.showToast(activity, "Category update Successfully");
                                            if (dialog != null)
                                                dialog.dismiss();

                                        } else {
                                            UtilView.showToast(activity, "Category not update. Please try again.");
                                        }

                                    } catch (Exception e) {
                                        UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                    }
                                } else {
                                    UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                }
                            }
                        }, false);
                        postDataTask.execute(new Gson().toJson(category).toString(), url, "");
                    } else {
                        UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
                    }
                }
            });
        if (dialog != null)
            dialog.show();
    }

    public void deleteCategory(final Category category, final Object obj) {
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

                    String url = serverUrl+ "/hipos//" + category.getItemCategoryId() + "/" + Constant.FUNTION_DELETECATEGORY;
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

                                        Category category = gson.fromJson(result.toString(), Category.class);
                                        if (category != null) {
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
                                        UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                    }
                                }
                            } else {
                                UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                            }
                        }
                    }, false);
                    postDataTask.execute(new Gson().toJson(category).toString(), url, "");

                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
        }

    }

    private boolean defaultTypeCheckMehtod() {
        return edCheckBox.isChecked();
    }

    public class Holder {
        TextView tvCategoryCode;
        TextView tvCategoryName;
        TextView tvCategoryDescrip;
        TextView tvCategoryEdit;
        ImageView imgviewEdit;
        ImageView imgviewDelete;

        public TextView getTvCategoryEdit() {
            return tvCategoryEdit;
        }

        public void setTvCategoryEdit(TextView tvCategoryEdit) {
            this.tvCategoryEdit = tvCategoryEdit;
        }

        public TextView getTvCategoryCode() {
            return tvCategoryCode;
        }

        public void setTvCategoryCode(TextView tvCategoryCode) {
            this.tvCategoryCode = tvCategoryCode;
        }

        public TextView getTvCategoryName() {
            return tvCategoryName;
        }

        public void setTvCategoryName(TextView tvCategoryName) {
            this.tvCategoryName = tvCategoryName;
        }

        public TextView getTvCategoryDescrip() {
            return tvCategoryDescrip;
        }

        public void setTvCategoryDescrip(TextView tvCategoryDescrip) {
            this.tvCategoryDescrip = tvCategoryDescrip;
        }

        public ImageView getImgviewEdit() {
            return imgviewEdit;
        }

        public void setImgviewEdit(ImageView imgviewEdit) {
            this.imgviewEdit = imgviewEdit;
        }

        public ImageView getImgviewDelete() {
            return imgviewDelete;
        }

        public void setImgviewDelete(ImageView imgviewDelete) {
            this.imgviewDelete = imgviewDelete;
        }
    }
}
