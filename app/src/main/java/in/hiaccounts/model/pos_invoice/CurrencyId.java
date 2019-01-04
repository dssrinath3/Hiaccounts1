
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrencyId implements Serializable {

    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("currencySymbol")
    @Expose
    private String currencySymbol;
    @SerializedName("currencyName")
    @Expose
    private String currencyName;
    @SerializedName("currencySymbolPlace")
    @Expose
    private String currencySymbolPlace;
    @SerializedName("currencyDescription")
    @Expose
    private String currencyDescription;
    @SerializedName("currencyId")
    @Expose
    private int currencyId;
    @SerializedName("accountCode")
    @Expose
    private String accountCode;

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
     * @param currencyDescription
     */
    public CurrencyId(String currencyCode, String currencySymbol, String currencyName, String currencySymbolPlace, String currencyDescription, int currencyId, String accountCode) {
        super();
        this.currencyCode = currencyCode;
        this.currencySymbol = currencySymbol;
        this.currencyName = currencyName;
        this.currencySymbolPlace = currencySymbolPlace;
        this.currencyDescription = currencyDescription;
        this.currencyId = currencyId;
        this.accountCode = accountCode;
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

    public String getCurrencySymbolPlace() {
        return currencySymbolPlace;
    }

    public void setCurrencySymbolPlace(String currencySymbolPlace) {
        this.currencySymbolPlace = currencySymbolPlace;
    }

    public String getCurrencyDescription() {
        return currencyDescription;
    }

    public void setCurrencyDescription(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

}
