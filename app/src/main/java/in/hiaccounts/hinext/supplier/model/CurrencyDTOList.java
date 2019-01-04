package in.hiaccounts.hinext.supplier.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 7/26/2017.
 */

public class CurrencyDTOList {

    @SerializedName("currencyId")
    @Expose
    private long currencyId;
    @SerializedName("currencyName")
    @Expose
    private String currencyName;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("currencySymbol")
    @Expose
    private String currencySymbol;
    @SerializedName("currencyDescription")
    @Expose
    private String currencyDescription;
    @SerializedName("currencySymbolPlace")
    @Expose
    private String currencySymbolPlace;
    @SerializedName("locationId")
    @Expose
    private long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private long useraccountId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("accountCode")
    @Expose
    private String accountCode;

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
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

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(long useraccountId) {
        this.useraccountId = useraccountId;
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
        return currencyName;
    }
}
