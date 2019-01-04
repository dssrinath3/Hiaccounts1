
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryId implements Serializable {

    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("flag")
    @Expose
    private boolean flag;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("countryId")
    @Expose
    private long countryId;
    @SerializedName("countryName")
    @Expose
    private String countryName;

    /**
     * No args constructor for use in serialization
     *
     */
    public CountryId() {
    }

    /**
     *
     * @param countryId
     * @param countryName
     * @param flag
     * @param locationId
     * @param useraccountId
     */
    public CountryId(Object useraccountId, boolean flag, Object locationId, long countryId, String countryName) {
        super();
        this.useraccountId = useraccountId;
        this.flag = flag;
        this.locationId = locationId;
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
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

}
