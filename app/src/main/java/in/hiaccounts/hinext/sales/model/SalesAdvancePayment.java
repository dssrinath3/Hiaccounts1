package in.hiaccounts.hinext.sales.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 7/22/2017.
 */

public class SalesAdvancePayment implements Serializable,Comparable<Object> {

    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("formNo")
    @Expose
    private String formNo;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("arbalance")
    @Expose
    private double arbalance;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("apBalance")
    @Expose
    private double apBalance;
    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getArbalance() {
        return arbalance;
    }

    public void setArbalance(double arbalance) {
        this.arbalance = arbalance;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public double getApBalance() {
        return apBalance;
    }

    public void setApBalance(double apBalance) {
        this.apBalance = apBalance;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    @Override
    public int compareTo(@NonNull Object object) {
        if (object instanceof  SalesAdvancePayment) {
            SalesAdvancePayment another = (SalesAdvancePayment) object;
            if (another.getCustomerName()!=null && this.getCustomerName()!=null){
                return this.getCustomerName().toUpperCase().compareTo(another.getCustomerName().toUpperCase());
            }
        }
        return 0;

    }
}
