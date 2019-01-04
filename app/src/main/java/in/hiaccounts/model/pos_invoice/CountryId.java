
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryId implements Serializable {

    @SerializedName("countryId")
    @Expose
    private int countryId;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("flag")
    @Expose
    private boolean flag;

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
     */
    public CountryId(int countryId, String countryName, boolean flag) {
        super();
        this.countryId = countryId;
        this.countryName = countryName;
        this.flag = flag;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
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

}
