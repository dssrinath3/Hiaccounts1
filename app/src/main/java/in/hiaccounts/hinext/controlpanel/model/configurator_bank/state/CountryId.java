package in.hiaccounts.hinext.controlpanel.model.configurator_bank.state;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 30/1/18.
 */

public class CountryId implements Serializable {
    @SerializedName("countryId")
    @Expose
    public Long countryId;
    @SerializedName("countryName")
    @Expose
    public String countryName;
    @SerializedName("flag")
    @Expose
    public Boolean flag;
    @SerializedName("locationId")
    @Expose
    public Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Long useraccountId;
    @SerializedName("status")
    @Expose
    public String status;



    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return countryName;
    }
}
