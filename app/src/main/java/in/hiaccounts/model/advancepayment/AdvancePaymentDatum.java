
package in.hiaccounts.model.advancepayment;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdvancePaymentDatum implements Serializable
{

    @SerializedName("arbalance")
    @Expose
    private double arbalance;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("apBalance")
    @Expose
    private double apBalance;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("formNo")
    @Expose
    private String formNo;
    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;
    private final static long serialVersionUID = 29554587717426460L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AdvancePaymentDatum() {
    }

    /**
     * 
     * @param amount
     * @param customerName
     * @param supplierName
     * @param formNo
     * @param supplierEmail
     * @param apBalance
     * @param customerEmail
     * @param arbalance
     */
    public AdvancePaymentDatum(double arbalance, String customerName, String customerEmail, double apBalance, double amount, String supplierName, String formNo, String supplierEmail) {
        super();
        this.arbalance = arbalance;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.apBalance = apBalance;
        this.amount = amount;
        this.supplierName = supplierName;
        this.formNo = formNo;
        this.supplierEmail = supplierEmail;
    }

    public double getArbalance() {
        return arbalance;
    }

    public void setArbalance(double arbalance) {
        this.arbalance = arbalance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getApBalance() {
        return apBalance;
    }

    public void setApBalance(double apBalance) {
        this.apBalance = apBalance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }
}
