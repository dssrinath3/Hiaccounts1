package in.hiaccounts.hinext.sales.model.printlist.duplicate_salesorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 8/12/2017.
 */

public class Duplicate_SalesOrder implements Serializable {

    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("formNo")
    @Expose
    public String formNo;
    @SerializedName("amount")
    @Expose
    public double amount;
    @SerializedName("customerEmail")
    @Expose
    public Object customerEmail;
    @SerializedName("arbalance")
    @Expose
    public Object arbalance;
    @SerializedName("supplierName")
    @Expose
    public Object supplierName;
    @SerializedName("apBalance")
    @Expose
    public double apBalance;
    @SerializedName("supplierEmail")
    @Expose
    public Object supplierEmail;
    @SerializedName("gtAmountPaid")
    @Expose
    public Object gtAmountPaid;
    @SerializedName("totalDebit")
    @Expose
    public Object totalDebit;
    @SerializedName("totalCredit")
    @Expose
    public Object totalCredit;
    @SerializedName("id")
    @Expose
    private Object id;

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

    public Object getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(Object customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Object getArbalance() {
        return arbalance;
    }

    public void setArbalance(Object arbalance) {
        this.arbalance = arbalance;
    }

    public Object getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(Object supplierName) {
        this.supplierName = supplierName;
    }

    public double getApBalance() {
        return apBalance;
    }

    public void setApBalance(double apBalance) {
        this.apBalance = apBalance;
    }

    public Object getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(Object supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Object getGtAmountPaid() {
        return gtAmountPaid;
    }

    public void setGtAmountPaid(Object gtAmountPaid) {
        this.gtAmountPaid = gtAmountPaid;
    }

    public Object getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Object totalDebit) {
        this.totalDebit = totalDebit;
    }

    public Object getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Object totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
