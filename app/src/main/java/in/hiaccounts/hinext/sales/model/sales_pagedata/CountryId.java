
package in.hiaccounts.hinext.sales.model.sales_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryId implements Serializable{

    @SerializedName("countryId")
    @Expose
    public long countryId;
    @SerializedName("countryName")
    @Expose
    public String countryName;
    @SerializedName("flag")
    @Expose
    public boolean flag;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("status")
    @Expose
    public Object status;

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

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
}
