package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 11/11/2017.
 */

public class StateList implements Serializable {

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
    public CountryList countryId;

    @SerializedName("status")
    @Expose
    public String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getVehicleSeries() {
        return vehicleSeries;
    }

    public void setVehicleSeries(String vehicleSeries) {
        this.vehicleSeries = vehicleSeries;
    }

    public CountryList getCountryId() {
        return countryId;
    }

    public void setCountryId(CountryList countryId) {
        this.countryId = countryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return stateName;
    }
}
