
package in.hiaccounts.hinext.sales.model.printlist.payement_receipt;

import java.io.Serializable;

public class PaymentRecieptDatumData implements Serializable {

    public String formNumber;
    public String customerName;
    public String totalAmount;
    public String amountPaid;
    public String remaininAmt;
    public Long paymenetId;

    public Long getPaymenetId() {
        return paymenetId;
    }

    public void setPaymenetId(Long paymenetId) {
        this.paymenetId = paymenetId;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getRemaininAmt() {
        return remaininAmt;
    }

    public void setRemaininAmt(String remaininAmt) {
        this.remaininAmt = remaininAmt;
    }
}
