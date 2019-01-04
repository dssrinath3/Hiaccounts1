
package in.hiaccounts.hinext.generaltransaction.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CashPayment {

    @SerializedName("totalCPAmountRefunded")
    @Expose
    public Double totalCPAmountRefunded;
    @SerializedName("totalCPDiscount")
    @Expose
    public Double totalCPDiscount;
    @SerializedName("totalCPAmountTendered")
    @Expose
    public Double totalCPAmountTendered;
    @SerializedName("paymentType")
    @Expose
    public Object paymentType;

}
