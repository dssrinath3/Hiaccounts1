package in.hiaccounts.hinext.controlpanel.model.display_setup.chairs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddChairsData implements Serializable {
    @SerializedName("chairsId")
    @Expose
    public Long chairsId;
    @SerializedName("chairsName")
    @Expose
    public Object chairsName;
    @SerializedName("table")
    @Expose
    public Long table;
    @SerializedName("chairNames")
    @Expose
    public List<String> chairNames = null;
    @SerializedName("config")
    @Expose
    public String config;

    public Long getChairsId() {
        return chairsId;
    }

    public void setChairsId(Long chairsId) {
        this.chairsId = chairsId;
    }

    public Object getChairsName() {
        return chairsName;
    }

    public void setChairsName(Object chairsName) {
        this.chairsName = chairsName;
    }

    public Long getTable() {
        return table;
    }

    public void setTable(Long table) {
        this.table = table;
    }

    public List<String> getChairNames() {
        return chairNames;
    }

    public void setChairNames(List<String> chairNames) {
        this.chairNames = chairNames;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
