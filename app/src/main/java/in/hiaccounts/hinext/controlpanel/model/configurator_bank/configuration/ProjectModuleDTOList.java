package in.hiaccounts.hinext.controlpanel.model.configurator_bank.configuration;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 11/24/2017.
 */

public class ProjectModuleDTOList implements Serializable {

    @SerializedName("company")
    @Expose
    private Object company;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("projectname")
    @Expose
    private String projectname;
    @SerializedName("selectedStatus")
    @Expose
    private Object selectedStatus;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Object getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(Object selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
