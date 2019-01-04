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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.cart.AddCartDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by administrator on 30/1/18.
 */

public class CartAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;

    public CartAdapter(Context context, List<Object> objects) {
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

            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_cart, null);

            holder.setTvName((TextView) convertView.findViewById(R.id.tvName));
            holder.setTvUserName((TextView) convertView.findViewById(R.id.tvUserName));
            holder.setTvUrl((TextView) convertView.findViewById(R.id.tvUrl));
            holder.setTvApi((TextView) convertView.findViewById(R.id.tvApi));
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
        holder.getTvName().setVisibility(View.GONE);
        holder.getTvUserName().setVisibility(View.GONE);
        holder.getTvUrl().setVisibility(View.GONE);
        holder.getTvApi().setVisibility(View.GONE);

        final Object obj = getItem(position);
        if (obj instanceof AddCartDatum) {
            final AddCartDatum cartData = (AddCartDatum) obj;


            if (cartData != null) {
                if (cartData.getCartName() != null)
                    holder.getTvName().setText(cartData.getCartName());

                if (cartData.getUserName() != null)
                    holder.getTvUserName().setText(cartData.getUserName());

                if (cartData.getUrl() != null)
                    holder.getTvUrl().setText(cartData.getUrl());

                if (cartData.getApiName() != null)
                    holder.getTvApi().setText(cartData.getApiName());


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

                    deleteCart(cartData, obj);

                }
            });
            holder.getTvEdit().setVisibility(View.GONE);
            holder.getImageViewEdit().setVisibility(View.VISIBLE);
            holder.getImgviewDelete().setVisibility(View.VISIBLE);
            holder.getTvName().setVisibility(View.VISIBLE);
            holder.getTvUserName().setVisibility(View.VISIBLE);
            holder.getTvUrl().setVisibility(View.VISIBLE);
            holder.getTvApi().setVisibility(View.VISIBLE);

        }
        return convertView;
    }

    private void deleteCart(final AddCartDatum cartData, final Object obj) {
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

                        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_DELETECART;
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
                                        AddCartDatum cart = gson.fromJson(result.toString(), AddCartDatum.class);
                                        if (cart != null) {
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
                            jsonObject.put("cartId", cartData.getCartId());

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


        TextView tvName;
        TextView tvUserName;
        TextView tvUrl;
        TextView tvApi;
        TextView tvEdit;

        ImageView imageViewEdit;
        ImageView imgviewDelete;

        public TextView getTvName() {
            return tvName;
        }

        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }

        public TextView getTvUserName() {
            return tvUserName;
        }

        public void setTvUserName(TextView tvUserName) {
            this.tvUserName = tvUserName;
        }

        public TextView getTvUrl() {
            return tvUrl;
        }

        public void setTvUrl(TextView tvUrl) {
            this.tvUrl = tvUrl;
        }

        public TextView getTvApi() {
            return tvApi;
        }

        public void setTvApi(TextView tvApi) {
            this.tvApi = tvApi;
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
