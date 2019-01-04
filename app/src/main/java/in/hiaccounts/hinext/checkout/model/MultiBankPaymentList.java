package in.hiaccounts.hinext.checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 12/13/2017.
 */

public class MultiBankPaymentList implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("transactionNo")
    @Expose
    private String transactionNo;
    @SerializedName("amount")
    @Expose
    private double amount;

    @SerializedName("bankAccountId")
    @Expose
    private Long bankAccountId;

    @SerializedName("bankName")
    @Expose
    private String bankName;

    @SerializedName("bankAccount")
    @Expose
    private String bankAccount;


    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("paymentType")
    @Expose
    private Long paymentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }
}
