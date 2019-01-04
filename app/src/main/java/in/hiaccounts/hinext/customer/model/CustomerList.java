
package in.hiaccounts.hinext.customer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerList implements Serializable {

    @SerializedName("customerId")
    @Expose
    public long customerId;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("customerNumber")
    @Expose
    public String customerNumber;
    @SerializedName("customerEmail")
    @Expose
    public String customerEmail;
    @SerializedName("customerContact")
    @Expose
    public String customerContact;
    @SerializedName("gstIn")
    @Expose
    public String gstIn;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("phoneNumber")
    @Expose
    public String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getGstIn() {
        return gstIn;
    }

    public void setGstIn(String gstIn) {
        this.gstIn = gstIn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
