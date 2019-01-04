package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 11/15/2017.
 */

public class ConfigurationMenuType {
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("projectname")
    @Expose
    public String projectname;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("company")
    @Expose
    public Object company;
    /*@SerializedName("selectedStatus")
    @Expose
    public Object selectedStatus;*/

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }
}
