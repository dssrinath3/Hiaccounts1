package in.hiaccounts.hinext.sales.model.sales_notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedItemsdata implements Serializable {
    @SerializedName("map")
    @Expose
    public Map map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
