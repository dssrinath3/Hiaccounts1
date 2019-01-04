
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CashPayment  implements Serializable{

    @SerializedName("totalCPAmountRefunded")
    @Expose
    private String totalCPAmountRefunded;
    @SerializedName("totalCPDiscount")
    @Expose
    private String totalCPDiscount;
    @SerializedName("totalCPAmountTendered")
    @Expose
    private String totalCPAmountTendered;


    public CashPayment(String totalCPAmountRefunded, String totalCPDiscount, String totalCPAmountTendered) {
        this.totalCPAmountRefunded = totalCPAmountRefunded;
        this.totalCPDiscount = totalCPDiscount;
        this.totalCPAmountTendered = totalCPAmountTendered;
    }

    public String getTotalCPAmountRefunded() {
        return totalCPAmountRefunded;
    }

    public void setTotalCPAmountRefunded(String totalCPAmountRefunded) {
        this.totalCPAmountRefunded = totalCPAmountRefunded;
    }

    public String getTotalCPDiscount() {
        return totalCPDiscount;
    }

    public void setTotalCPDiscount(String totalCPDiscount) {
        this.totalCPDiscount = totalCPDiscount;
    }

    public String getTotalCPAmountTendered() {
        return totalCPAmountTendered;
    }

    public void setTotalCPAmountTendered(String totalCPAmountTendered) {
        this.totalCPAmountTendered = totalCPAmountTendered;
    }
}
