package in.hiaccounts.hinext.sales.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.model.sales_notifications.CustomerNotificationsList;
import in.hiaccounts.hinext.sales.model.sales_notifications.CustomerNotificationsListData;
import in.hiaccounts.hinext.sales.model.sales_notifications.SelectPendingNotifications;

public class MessageAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<CustomerNotificationsList> listMessages;


    public MessageAdapter(Activity activity, List<CustomerNotificationsList> listMessages) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       this.listMessages = listMessages;

    }

    @Override
    public int getCount() {
        return listMessages.size();
    }

    @Override
    public CustomerNotificationsList getItem(int position) {
        return listMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.message_list_row, null);
        }

        TextView txtMessage = (TextView) view.findViewById(R.id.message);
        TextView txtTimestamp = (TextView) view.findViewById(R.id.timestamp);

        CustomerNotificationsList message = listMessages.get(position);
        try
        {
            if (message!=null){
                txtMessage.setText(position +"  .  "+message.getFromCompname());
                txtTimestamp.setText(message.getPostedDate());
              /*  CustomerNotificationsListData custNotificationData = new Gson().fromJson(message.getObjectdata(), new TypeToken<CustomerNotificationsListData>() {
                }.getType());

                if (custNotificationData.getJsonData().getSelectedItemsList() != null && custNotificationData.getJsonData().getSelectedItemsList().size() > 0){
                    for (int j = 0; j < custNotificationData.getJsonData().getSelectedItemsList().size(); j++) {
                        //  Log.e("notificationItemData", String.valueOf(custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemName()));
                        txtMessage.setText(position +"  .  "+custNotificationData.getJsonData().getSelectedItemsList().get(j).getItemName());
                    }
                }*/
            }
        }
        catch (IllegalStateException | JsonSyntaxException exception)
        {

        }







        /*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        txtTimestamp.setText(formatter.format(date));*/

        return view;
    }
}
