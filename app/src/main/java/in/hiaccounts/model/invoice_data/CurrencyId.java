
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrencyId  implements Serializable{

    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("currencySymbol")
    @Expose
    private String currencySymbol;
    @SerializedName("currencyName")
    @Expose
    private String currencyName;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("currencyDescription")
    @Expose
    private String currencyDescription;
    @SerializedName("currencySymbolPlace")
    @Expose
    private String currencySymbolPlace;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("accountCode")
    @Expose
    private String accountCode;
    @SerializedName("currencyId")
    @Expose
    private long currencyId;

    /**
     * No args constructor for use in serialization
     *
     */
    public CurrencyId() {
    }

    /**
     *
     * @param accountCode
     * @param currencyName
     * @param currencySymbolPlace
     * @param currencyCode
     * @param currencySymbol
     * @param currencyId
     * @param locationId
     * @param currencyDescription
     * @param useraccountId
     */
    public CurrencyId(String currencyCode, String currencySymbol, String currencyName, Object useraccountId, String currencyDescription, String currencySymbolPlace, Object locationId, String accountCode, long currencyId) {
        super();
        this.currencyCode = currencyCode;
        this.currencySymbol = currencySymbol;
        this.currencyName = currencyName;
        this.useraccountId = useraccountId;
        this.currencyDescription = currencyDescription;
        this.currencySymbolPlace = currencySymbolPlace;
        this.locationId = locationId;
        this.accountCode = accountCode;
        this.currencyId = currencyId;
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

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
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

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

}
