package in.hiaccounts.hinext.purchase.model.purchase_invoicereturn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 7/28/2017.
 */

public class InvoiceReturn_Checkout {
    @SerializedName("cmpyLoc")
    @Expose
    private Long cmpyLoc;
    @SerializedName("suppLoc")
    @Expose
    private Long suppLoc;

    @SerializedName("cessTotalTaxAmt")
    @Expose
    private String cessTotalTaxAmt;
    @SerializedName("totalTenderedAmount")
    @Expose
    private String totalTenderedAmount;
    @SerializedName("dateOfInvoice")
    @Expose
    private String dateOfInvoice;


    @SerializedName("piid")
    @Expose
    private long piid;
    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("totalCheckOutamt")
    @Expose
    private double totalCheckOutamt;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("supplierId")
    @Expose
    private long supplierId;

    public long getPiid() {
        return piid;
    }

    public void setPiid(long piid) {
        this.piid = piid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(String cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public Long getCmpyLoc() {
        return cmpyLoc;
    }

    public void setCmpyLoc(Long cmpyLoc) {
        this.cmpyLoc = cmpyLoc;
    }

    public Long getSuppLoc() {
        return suppLoc;
    }

    public void setSuppLoc(Long suppLoc) {
        this.suppLoc = suppLoc;
    }

    public String getTotalTenderedAmount() {
        return totalTenderedAmount;
    }

    public void setTotalTenderedAmount(String totalTenderedAmount) {
        this.totalTenderedAmount = totalTenderedAmount;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }
}
