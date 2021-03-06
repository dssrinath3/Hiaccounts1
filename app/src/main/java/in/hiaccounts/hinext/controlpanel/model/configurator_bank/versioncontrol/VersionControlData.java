package in.hiaccounts.hinext.controlpanel.model.configurator_bank.versioncontrol;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 11/27/2017.
 */

public class VersionControlData implements Serializable {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("projectname")
    @Expose
    private String projectname;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("company")
    @Expose
    private Object company;
    @SerializedName("selectedStatus")
    @Expose
    private Object selectedStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Object getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(Object selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

}
