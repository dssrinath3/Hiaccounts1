
package in.hiaccounts.hinext.checkout.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.hiaccounts.hinext.checkout.model.CardPaymentList;

public class CreditPayment implements Serializable{



    @SerializedName("cardPaymentList")
    @Expose
    private List<CardPaymentList> cardPaymentList = null;

    @SerializedName("paymentType")
    @Expose
    private String paymentTypeCash;

    public String getPaymentTypeCash() {
        return paymentTypeCash;
    }

    public void setPaymentTypeCash(String paymentTypeCash) {
        this.paymentTypeCash = paymentTypeCash;
    }

    public List<CardPaymentList> getCardPaymentList() {
        return cardPaymentList;
    }

    public void setCardPaymentList(List<CardPaymentList> cardPaymentList) {
        this.cardPaymentList = cardPaymentList;
    }

}
