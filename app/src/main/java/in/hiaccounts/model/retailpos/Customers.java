package in.hiaccounts.model.retailpos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 2/13/2017.
 */

public class Customers implements Serializable {


    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("customerId")
    @Expose
    private int customerId;
    @SerializedName("customerContact")
    @Expose
    private String customerContact;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerNumber")
    @Expose
    private String customerNumber;


    public Customers(String customerEmail, int customerId, String customerContact, String customerName, String customerNumber) {
        this.customerEmail = customerEmail;
        this.customerId = customerId;
        this.customerContact = customerContact;
        this.customerName = customerName;
        this.customerNumber = customerNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
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

