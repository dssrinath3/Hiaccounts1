package in.hiaccounts.hinext.reports.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyList {
    @SerializedName("currencyId")
    @Expose
    public Long currencyId;
    @SerializedName("currencyName")
    @Expose
    public String currencyName;
    @SerializedName("currencyCode")
    @Expose
    public String currencyCode;

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
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

    @Override
    public String toString() {
        return currencyName;
    }
}
