package in.hiaccounts.hinext.controlpanel.model.openingbalance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 8/26/2017.
 */

public class PurchaseInvoiceData implements Serializable {

    @SerializedName("obPIID")
    @Expose
    public Long obPIID;
    @SerializedName("supplierId")
    @Expose
    public Object supplierId;
    @SerializedName("formNo")
    @Expose
    public String formNo;
    @SerializedName("supplierName")
    @Expose
    public String supplierName;
    @SerializedName("supplierID")
    @Expose
    public Object supplierID;
    @SerializedName("piDate")
    @Expose
    public String piDate;
    @SerializedName("supplierINVNo")
    @Expose
    public Object supplierINVNo;
    @SerializedName("totalPayable")
    @Expose
    public double totalPayable;
    @SerializedName("taxamount")
    @Expose
    public double taxamount;
    @SerializedName("amountPaidTillDt")
    @Expose
    public double amountPaidTillDt;
    @SerializedName("dueDate")
    @Expose
    public Object dueDate;
    @SerializedName("balanceAmount")
    @Expose
    public double balanceAmount;
    @SerializedName("totalPayableBC")
    @Expose
    public double totalPayableBC;
    @SerializedName("supplierDTOList")
    @Expose
    public Object supplierDTOList;

    public Long getObPIID() {
        return obPIID;
    }

    public void setObPIID(Long obPIID) {
        this.obPIID = obPIID;
    }

    public Object getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Object supplierId) {
        this.supplierId = supplierId;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Object getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Object supplierID) {
        this.supplierID = supplierID;
    }

    public String getPiDate() {
        return piDate;
    }

    public void setPiDate(String piDate) {
        this.piDate = piDate;
    }

    public Object getSupplierINVNo() {
        return supplierINVNo;
    }

    public void setSupplierINVNo(Object supplierINVNo) {
        this.supplierINVNo = supplierINVNo;
    }

    public double getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(double totalPayable) {
        this.totalPayable = totalPayable;
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

    public Object getDueDate() {
        return dueDate;
    }

    public void setDueDate(Object dueDate) {
        this.dueDate = dueDate;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public double getTotalPayableBC() {
        return totalPayableBC;
    }

    public void setTotalPayableBC(double totalPayableBC) {
        this.totalPayableBC = totalPayableBC;
    }

    public Object getSupplierDTOList() {
        return supplierDTOList;
    }

    public void setSupplierDTOList(Object supplierDTOList) {
        this.supplierDTOList = supplierDTOList;
    }
}
