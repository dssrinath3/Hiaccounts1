
package in.hiaccounts.hinext.controlpanel.model.useraccount_setup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StateId implements Serializable{

    @SerializedName("id")
    @Expose
    public Long id;
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
    public CountryId countryId;
    @SerializedName("status")
    @Expose
    public String status;

}
