package in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TableDataList implements Serializable {
    @SerializedName("tableid")
    @Expose
    public Long tableid;
    @SerializedName("tableId")
    @Expose
    public Object tableId;
    @SerializedName("tableName")
    @Expose
    public String tableName;
    @SerializedName("mergeTableName")
    @Expose
    public Object mergeTableName;
    @SerializedName("configurationname")
    @Expose
    public String configurationname;
    @SerializedName("floorId")
    @Expose
    public Object floorId;
    @SerializedName("occupied")
    @Expose
    public Boolean occupied;
    @SerializedName("gridLocationH")
    @Expose
    public Long gridLocationH;
    @SerializedName("gridLocationV")
    @Expose
    public Long gridLocationV;
    @SerializedName("tableValue")
    @Expose
    public Object tableValue;
    @SerializedName("chair")
    @Expose
    public Long chair;
    @SerializedName("status")
    @Expose
    public String status;

    public Long getTableid() {
        return tableid;
    }

    public void setTableid(Long tableid) {
        this.tableid = tableid;
    }

    public Object getTableId() {
        return tableId;
    }

    public void setTableId(Object tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Object getMergeTableName() {
        return mergeTableName;
    }

    public void setMergeTableName(Object mergeTableName) {
        this.mergeTableName = mergeTableName;
    }

    public String getConfigurationname() {
        return configurationname;
    }

    public void setConfigurationname(String configurationname) {
        this.configurationname = configurationname;
    }

    public Object getFloorId() {
        return floorId;
    }

    public void setFloorId(Object floorId) {
        this.floorId = floorId;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
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

    public Object getTableValue() {
        return tableValue;
    }

    public void setTableValue(Object tableValue) {
        this.tableValue = tableValue;
    }

    public Long getChair() {
        return chair;
    }

    public void setChair(Long chair) {
        this.chair = chair;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return tableName;
    }
}
