
package in.hiaccounts.hinext.generaltransaction.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("stateCode")
    @Expose
    public String stateCode;
    @SerializedName("stateName")
    @Expose
    public String stateName;
    @SerializedName("vehicleSeries")
    @Expose
    public String vehicleSeries;
    @SerializedName("countryId")
    @Expose
    public CountryId_ countryId;
    @SerializedName("status")
    @Expose
    public String status;

}
