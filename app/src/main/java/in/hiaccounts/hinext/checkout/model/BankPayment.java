
package in.hiaccounts.hinext.checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.checkout.model.MultiBankPaymentList;


public class BankPayment  implements Serializable{

    @SerializedName("multiBankPaymentList")
    @Expose
    private List<MultiBankPaymentList> multiBankPaymentList;
    @SerializedName("paymentType")
    @Expose
    private String paymentTypeCash;

    public String getPaymentTypeCash() {
        return paymentTypeCash;
    }

    public void setPaymentTypeCash(String paymentTypeCash) {
        this.paymentTypeCash = paymentTypeCash;
    }

    public List<MultiBankPaymentList> getMultiBankPaymentList() {
        return multiBankPaymentList;
    }

    public void setMultiBankPaymentList(List<MultiBankPaymentList> multiBankPaymentList) {
        this.multiBankPaymentList = multiBankPaymentList;
    }
}
