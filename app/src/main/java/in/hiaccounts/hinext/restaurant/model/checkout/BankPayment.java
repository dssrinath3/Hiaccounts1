package in.hiaccounts.hinext.restaurant.model.checkout;

/**
 * Created by Admin on 11/22/2017.
 */

public class BankPayment {
    Long paymentType;
    String bankAccountName;
    String bankName;
    Long bankAccountid;
    String transactionNo;
    double amount;
    String date;

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getBankAccountid() {
        return bankAccountid;
    }

    public void setBankAccountid(Long bankAccountid) {
        this.bankAccountid = bankAccountid;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
