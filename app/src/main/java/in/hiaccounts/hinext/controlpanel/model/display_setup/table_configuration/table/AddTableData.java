package in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddTableData implements Serializable {
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
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("chair")
    @Expose
    public String chair;
    @SerializedName("tableid")
    @Expose
    public Long tableid;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }
}
