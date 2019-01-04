
package in.hiaccounts.hinext.purchase.model.purchase_saveresponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CashPayment implements Serializable {

    @SerializedName("totalCPAmountRefunded")
    @Expose
    public double totalCPAmountRefunded;
    @SerializedName("totalCPDiscount")
    @Expose
    public double totalCPDiscount;
    @SerializedName("totalCPAmountTendered")
    @Expose
    public double totalCPAmountTendered;
    @SerializedName("paymentType")
    @Expose
    public String paymentType;

}
