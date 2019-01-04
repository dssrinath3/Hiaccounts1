
package in.hiaccounts.hinext.sales.model.sales_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExchangeRateList implements Serializable {

    @SerializedName("exchangeRateId")
    @Expose
    public long exchangeRateId;
    @SerializedName("currency")
    @Expose
    public Currency currency;
    @SerializedName("exchangeRateEffectiveDate")
    @Expose
    public String exchangeRateEffectiveDate;
    @SerializedName("exchangeRateValue")
    @Expose
    public String exchangeRateValue;
    @SerializedName("companyId")
    @Expose
    public Object companyId;
    @SerializedName("customsexchangeRateValue")
    @Expose
    public Object customsexchangeRateValue;
    @SerializedName("status")
    @Expose
    public String status;

    public long getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(long exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getExchangeRateEffectiveDate() {
        return exchangeRateEffectiveDate;
    }

    public void setExchangeRateEffectiveDate(String exchangeRateEffectiveDate) {
        this.exchangeRateEffectiveDate = exchangeRateEffectiveDate;
    }

    public String getExchangeRateValue() {
        return exchangeRateValue;
    }

    public void setExchangeRateValue(String exchangeRateValue) {
        this.exchangeRateValue = exchangeRateValue;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }

    public Object getCustomsexchangeRateValue() {
        return customsexchangeRateValue;
    }

    public void setCustomsexchangeRateValue(Object customsexchangeRateValue) {
        this.customsexchangeRateValue = customsexchangeRateValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
