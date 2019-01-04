
package in.hiaccounts.hinext.sales.model.printlist.payement_receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryId implements Serializable {

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
    public String locationId;
    @SerializedName("useraccount_id")
    @Expose
    public String useraccountId;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("companyId")
    @Expose
    public CompanyId companyId;


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

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
        this.companyId = companyId;
    }
}
