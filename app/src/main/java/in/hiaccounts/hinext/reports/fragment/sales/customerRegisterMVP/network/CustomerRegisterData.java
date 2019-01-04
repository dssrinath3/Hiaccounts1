package in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerRegisterData {
    @SerializedName("customerId")
    @Expose
    public Long customerId;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("customerNumber")
    @Expose
    public String customerNumber;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
