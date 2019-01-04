package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 12/5/2017.
 */

public class InclusiveJson implements Serializable {


    @SerializedName("purchases")
    @Expose
    private boolean purchases;
    @SerializedName("sales")
    @Expose
    private boolean sales;

    public boolean isPurchases() {
        return purchases;
    }

    public void setPurchases(boolean purchases) {
        this.purchases = purchases;
    }

    public boolean isSales() {
        return sales;
    }

    public void setSales(boolean sales) {
        this.sales = sales;
    }
}
