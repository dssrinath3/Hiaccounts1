package in.hiaccounts.hinext.controlpanel.model.openingbalance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 8/26/2017.
 */

public class SalesInvoiceData implements Serializable {

    @SerializedName("obSIID")
    @Expose
    public Long obSIID;
    @SerializedName("customerId")
    @Expose
    public Object customerId;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("customerID")
    @Expose
    public Object customerID;
    @SerializedName("formNo")
    @Expose
    public String formNo;
    @SerializedName("siDate")
    @Expose
    public String siDate;
    @SerializedName("totalReceivable")
    @Expose
    public double totalReceivable;
    @SerializedName("totalReceivableBC")
    @Expose
    public double totalReceivableBC;
    @SerializedName("taxamount")
    @Expose
    public double taxamount;
    @SerializedName("amountPaidTillDt")
    @Expose
    public double amountPaidTillDt;
    @SerializedName("balanceAmount")
    @Expose
    public double balanceAmount;
    @SerializedName("customerDTOList")
    @Expose
    public Object customerDTOList;

    public Long getObSIID() {
        return obSIID;
    }

    public void setObSIID(Long obSIID) {
        this.obSIID = obSIID;
    }

    public Object getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Object customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Object getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Object customerID) {
        this.customerID = customerID;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getSiDate() {
        return siDate;
    }

    public void setSiDate(String siDate) {
        this.siDate = siDate;
    }

    public double getTotalReceivable() {
        return totalReceivable;
    }

    public void setTotalReceivable(double totalReceivable) {
        this.totalReceivable = totalReceivable;
    }

    public double getTotalReceivableBC() {
        return totalReceivableBC;
    }

    public void setTotalReceivableBC(double totalReceivableBC) {
        this.totalReceivableBC = totalReceivableBC;
    }

    public double getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(double taxamount) {
        this.taxamount = taxamount;
    }

    public double getAmountPaidTillDt() {
        return amountPaidTillDt;
    }

    public void setAmountPaidTillDt(double amountPaidTillDt) {
        this.amountPaidTillDt = amountPaidTillDt;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Object getCustomerDTOList() {
        return customerDTOList;
    }

    public void setCustomerDTOList(Object customerDTOList) {
        this.customerDTOList = customerDTOList;
    }
}
