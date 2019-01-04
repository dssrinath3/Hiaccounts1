package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DefaultTable implements Serializable{
    @SerializedName("tableid")
    @Expose
    public Long tableid;
    @SerializedName("tableName")
    @Expose
    public String tableName;
    @SerializedName("configurationname")
    @Expose
    public String configurationname;
    @SerializedName("gridLocationH")
    @Expose
    public Long gridLocationH;
    @SerializedName("gridLocationV")
    @Expose
    public Long gridLocationV;
    @SerializedName("tableValue")
    @Expose
    public String tableValue;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("floorId")
    @Expose
    public String floorId;
    @SerializedName("occupied")
    @Expose
    public Boolean occupied;

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Long getTableid() {
        return tableid;
    }

    public void setTableid(Long tableid) {
        this.tableid = tableid;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getConfigurationname() {
        return configurationname;
    }

    public void setConfigurationname(String configurationname) {
        this.configurationname = configurationname;
    }

    public Long getGridLocationH() {
        return gridLocationH;
    }

    public void setGridLocationH(Long gridLocationH) {
        this.gridLocationH = gridLocationH;
    }

    public Long getGridLocationV() {
        return gridLocationV;
    }

    public void setGridLocationV(Long gridLocationV) {
        this.gridLocationV = gridLocationV;
    }

    public String getTableValue() {
        return tableValue;
    }

    public void setTableValue(String tableValue) {
        this.tableValue = tableValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
