package in.hiaccounts.hinext.sales.model.sales_notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NotificationData implements Serializable{
    @SerializedName("sassCustomerList")
    @Expose
    public List<Object> sassCustomerList = null;
    @SerializedName("customerNotificationsList")
    @Expose
    public List<CustomerNotificationsList> customerNotificationsList = null;
    @SerializedName("transactionsDataList")
    @Expose
    public List<Object> transactionsDataList = null;


    public List<Object> getSassCustomerList() {
        return sassCustomerList;
    }

    public void setSassCustomerList(List<Object> sassCustomerList) {
        this.sassCustomerList = sassCustomerList;
    }

    public List<CustomerNotificationsList> getCustomerNotificationsList() {
        return customerNotificationsList;
    }

    public void setCustomerNotificationsList(List<CustomerNotificationsList> customerNotificationsList) {
        this.customerNotificationsList = customerNotificationsList;
    }

    public List<Object> getTransactionsDataList() {
        return transactionsDataList;
    }

    public void setTransactionsDataList(List<Object> transactionsDataList) {
        this.transactionsDataList = transactionsDataList;
    }
}
