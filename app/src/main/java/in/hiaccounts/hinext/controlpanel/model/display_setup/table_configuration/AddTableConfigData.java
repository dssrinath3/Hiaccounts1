package in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddTableConfigData implements Serializable {
    @SerializedName("configurationname")
    @Expose
    public String configurationname;
    @SerializedName("rowtableconfig")
    @Expose
    public Long rowtableconfig;
    @SerializedName("columntableconfig")
    @Expose
    public Long columntableconfig;
    @SerializedName("tableconfigid")
    @Expose
    public Long tableconfigid;

    public Long getTableconfigid() {
        return tableconfigid;
    }

    public void setTableconfigid(Long tableconfigid) {
        this.tableconfigid = tableconfigid;
    }

    public String getConfigurationname() {
        return configurationname;
    }

    public void setConfigurationname(String configurationname) {
        this.configurationname = configurationname;
    }

    public Long getRowtableconfig() {
        return rowtableconfig;
    }

    public void setRowtableconfig(Long rowtableconfig) {
        this.rowtableconfig = rowtableconfig;
    }

    public Long getColumntableconfig() {
        return columntableconfig;
    }

    public void setColumntableconfig(Long columntableconfig) {
        this.columntableconfig = columntableconfig;
    }
}
