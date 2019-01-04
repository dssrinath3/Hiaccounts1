package in.hiaccounts.hinext.sales.model.sales_notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InclusiveJSON {
    @SerializedName("purchases")
    @Expose
    public Boolean purchases;
    @SerializedName("sales")
    @Expose
    public Boolean sales;

    public Boolean getPurchases() {
        return purchases;
    }

    public void setPurchases(Boolean purchases) {
        this.purchases = purchases;
    }

    public Boolean getSales() {
        return sales;
    }

    public void setSales(Boolean sales) {
        this.sales = sales;
    }
}
