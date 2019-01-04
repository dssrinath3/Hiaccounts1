package in.hiaccounts.hinext.checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administrator on 10/1/18.
 */

public class RedeemPayment implements Serializable {
    @SerializedName("multiRedeemPayments")
    @Expose
    private List<MultiRedeemPaymentList> multiRedeemPaymentList;

    public List<MultiRedeemPaymentList> getMultiRedeemPaymentList() {
        return multiRedeemPaymentList;
    }

    public void setMultiRedeemPaymentList(List<MultiRedeemPaymentList> multiRedeemPaymentList) {
        this.multiRedeemPaymentList = multiRedeemPaymentList;
    }
}
