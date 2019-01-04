
package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubRow implements Serializable {



    @SerializedName("tableName")
    @Expose
    private String tableName;
    @SerializedName("tableValue")
    @Expose
    private String tableValue;

    @SerializedName("occupied")
    @Expose
    private String occupied;
    @SerializedName("floorId")
    @Expose
    public String floorId;
    @SerializedName("tableId")
    @Expose
    public String tableId;
    private Boolean isSelected;

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableValue() {
        return tableValue;
    }

    public void setTableValue(String tableValue) {
        this.tableValue = tableValue;
    }
}
