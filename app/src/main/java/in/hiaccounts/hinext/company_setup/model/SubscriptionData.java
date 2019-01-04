package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 11/22/2017.
 */

public class SubscriptionData implements Serializable {
    @SerializedName("validity")
    @Expose
    public String validity;
    @SerializedName("bussinessName")
    @Expose
    public String bussinessName;
    @SerializedName("numberOfUsers")
    @Expose
    public Long numberOfUsers;
    @SerializedName("hiConnect")
    @Expose
    public String hiConnect;
    @SerializedName("hiSync")
    @Expose
    public String hiSync;
    @SerializedName("practitionerName")
    @Expose
    public String practitionerName;

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public Long getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(Long numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public String getHiConnect() {
        return hiConnect;
    }

    public void setHiConnect(String hiConnect) {
        this.hiConnect = hiConnect;
    }

    public String getHiSync() {
        return hiSync;
    }

    public void setHiSync(String hiSync) {
        this.hiSync = hiSync;
    }

    public String getPractitionerName() {
        return practitionerName;
    }

    public void setPractitionerName(String practitionerName) {
        this.practitionerName = practitionerName;
    }
}
