package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 11/11/2017.
 */

public class CurrencyList implements Serializable {

    @SerializedName("currencyId")
    @Expose
    public Long currencyId;

    @SerializedName("locationId")
    @Expose
    public Long locationId;

    @SerializedName("useraccount_id")
    @Expose
    public Long useraccount_id;

    @SerializedName("currencyName")
    @Expose
    public String currencyName;

    @SerializedName("currencyCode")
    @Expose
    public String currencyCode;

    @SerializedName("currencySymbol")
    @Expose
    public String currencySymbol;

    @SerializedName("currencyDescription")
    @Expose
    public String currencyDescription;

    @SerializedName("currencySymbolPlace")
    @Expose
    public String currencySymbolPlace;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("accountCode")
    @Expose
    public String accountCode;

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUseraccount_id() {
        return useraccount_id;
    }

    public void setUseraccount_id(Long useraccount_id) {
        this.useraccount_id = useraccount_id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencyDescription() {
        return currencyDescription;
    }

    public void setCurrencyDescription(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }

    public String getCurrencySymbolPlace() {
        return currencySymbolPlace;
    }

    public void setCurrencySymbolPlace(String currencySymbolPlace) {
        this.currencySymbolPlace = currencySymbolPlace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    @Override
    public String toString() {
        return currencyCode;
    }
}
