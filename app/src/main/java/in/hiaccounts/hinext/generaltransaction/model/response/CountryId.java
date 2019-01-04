
package in.hiaccounts.hinext.generaltransaction.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryId {

    @SerializedName("countryId")
    @Expose
    public Integer countryId;
    @SerializedName("countryName")
    @Expose
    public String countryName;
    @SerializedName("flag")
    @Expose
    public Boolean flag;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("status")
    @Expose
    public Object status;

}
