package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 11/11/2017.
 */

public class CountryList implements Serializable {


    @SerializedName("countryName")
    @Expose
    public String countryName;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("countryId")
    @Expose
    public Long countryId;
    @SerializedName("flag")
    @Expose
    public String flag;
    @SerializedName("locationId")
    @Expose
    public Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Long useraccount_id;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUseraccount_id() {
        return useraccount_id;
    }

    public void setUseraccount_id(Long useraccount_id) {
        this.useraccount_id = useraccount_id;
    }

    @Override
    public String toString() {
        return  countryName;
    }
}
