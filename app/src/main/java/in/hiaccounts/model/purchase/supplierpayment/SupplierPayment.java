
package in.hiaccounts.model.purchase.supplierpayment;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupplierPayment implements Serializable
{

    @SerializedName("arbalance")
    @Expose
    private Double arbalance;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("formNo")
    @Expose
    private String formNo;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("apBalance")
    @Expose
    private Double apBalance;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;


    /**
     * No args constructor for use in serialization
     * 
     */
    public SupplierPayment() {
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
    public SupplierPayment(Double arbalance, String customerEmail, String customerName, String formNo, String supplierName, Double apBalance, Double amount, String supplierEmail) {
        this.arbalance = arbalance;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.formNo = formNo;
        this.supplierName = supplierName;
        this.apBalance = apBalance;
        this.amount = amount;
        this.supplierEmail = supplierEmail;
    }

    public Double getArbalance() {
        return arbalance;
    }

    public void setArbalance(Double arbalance) {
        this.arbalance = arbalance;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getApBalance() {
        return apBalance;
    }

    public void setApBalance(Double apBalance) {
        this.apBalance = apBalance;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }
}
