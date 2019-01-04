
package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TableConfigDetail implements Serializable {

    @SerializedName("row")
    @Expose
    private String row;
    @SerializedName("subRow")
    @Expose
    private List<SubRow> subRow = null;

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
