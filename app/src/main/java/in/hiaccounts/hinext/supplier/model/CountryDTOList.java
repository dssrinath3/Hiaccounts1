package in.hiaccounts.hinext.supplier.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 7/26/2017.
 */

public class CountryDTOList {

    @SerializedName("countryId")
    @Expose
    private long countryId;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("flag")
    @Expose
    private boolean flag;
    @SerializedName("locationId")
    @Expose
    private long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private long useraccountId;
    @SerializedName("status")
    @Expose
    private String status;

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(long useraccountId) {
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
