
package in.hiaccounts.hinext.sales.model.save_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryId implements Serializable {

    @SerializedName("countryId")
    @Expose
    private Long countryId;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("flag")
    @Expose
    private Boolean flag;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("status")
    @Expose
    private Object status;


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
