package in.hiaccounts.hinext.sales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 12/13/2017.
 */

public class MultiCashPaymentList implements Serializable {


    @SerializedName("paymentType")
    @Expose
    private Long paymentType;
    @SerializedName("cashAmt")
    @Expose
    private double cashAmt;

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }

    public double getCashAmt() {
        return cashAmt;
    }

    public void setCashAmt(double cashAmt) {
        this.cashAmt = cashAmt;
    }
}
