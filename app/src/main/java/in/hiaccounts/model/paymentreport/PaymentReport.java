
package in.hiaccounts.model.paymentreport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentReport {

    @SerializedName("amountApplied")
    @Expose
    private double amountApplied;
    @SerializedName("formNo")
    @Expose
    private String formNo;
    @SerializedName("invoiceAmount")
    @Expose
    private double invoiceAmount;
    @SerializedName("invoiceDate")
    @Expose
    private String invoiceDate;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("arAmount")
    @Expose
    private double arAmount;


    public PaymentReport(double amountApplied, String formNo, double invoiceAmount, String invoiceDate, String customerName, double arAmount) {
        this.amountApplied = amountApplied;
        this.formNo = formNo;
        this.invoiceAmount = invoiceAmount;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.arAmount = arAmount;
    }

    public double getAmountApplied() {
        return amountApplied;
    }

    public void setAmountApplied(double amountApplied) {
        this.amountApplied = amountApplied;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getArAmount() {
        return arAmount;
    }

    public void setArAmount(double arAmount) {
        this.arAmount = arAmount;
    }
}
