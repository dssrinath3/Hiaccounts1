
package in.hiaccounts.hinext.generaltransaction.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyId {

    @SerializedName("currencyId")
    @Expose
    public Integer currencyId;
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
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("status")
    @Expose
    public Object status;
    @SerializedName("accountCode")
    @Expose
    public String accountCode;

}
