package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administrator on 17/2/18.
 */

public class RestuarentTableData implements Serializable {
    @SerializedName("row")
    @Expose
    public String row;
    @SerializedName("subRow")
    @Expose
    public List<SubRow> subRow = null;


    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public List<SubRow> getSubRow() {
        return subRow;
    }

    public void setSubRow(List<SubRow> subRow) {
        this.subRow = subRow;
    }
}
