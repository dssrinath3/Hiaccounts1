package in.hiaccounts.hinext.controlpanel.model.display_setup.chairs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChairsDataList implements Serializable {
    @SerializedName("chairsId")
    @Expose
    public Long chairsId;
    @SerializedName("chairsName")
    @Expose
    public String chairsName;
    @SerializedName("table")
    @Expose
    public String table;
    @SerializedName("config")
    @Expose
    public String config;
    @SerializedName("occupied")
    @Expose
    public Boolean occupied;
    @SerializedName("checkbox")
    @Expose
    public Boolean checkbox;
    @SerializedName("noOfChairs")
    @Expose
    public Long noOfChairs;
    @SerializedName("chairNames")
    @Expose
    public Object chairNames;

    public Long getChairsId() {
        return chairsId;
    }

    public void setChairsId(Long chairsId) {
        this.chairsId = chairsId;
    }

    public String getChairsName() {
        return chairsName;
    }

    public void setChairsName(String chairsName) {
        this.chairsName = chairsName;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkbox) {
        this.checkbox = checkbox;
    }

    public Long getNoOfChairs() {
        return noOfChairs;
    }

    public void setNoOfChairs(Long noOfChairs) {
        this.noOfChairs = noOfChairs;
    }

    public Object getChairNames() {
        return chairNames;
    }

    public void setChairNames(Object chairNames) {
        this.chairNames = chairNames;
    }
}
