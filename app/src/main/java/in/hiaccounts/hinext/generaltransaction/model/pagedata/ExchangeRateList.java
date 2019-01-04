
package in.hiaccounts.hinext.generaltransaction.model.pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeRateList {

    @SerializedName("exchangeRateId")
    @Expose
    private Integer exchangeRateId;
    @SerializedName("currency")
    @Expose
    private Currency currency;
    @SerializedName("exchangeRateEffectiveDate")
    @Expose
    private String exchangeRateEffectiveDate;
    @SerializedName("exchangeRateValue")
    @Expose
    private String exchangeRateValue;
    @SerializedName("companyId")
    @Expose
    private Object companyId;
    @SerializedName("customsexchangeRateValue")
    @Expose
    private String customsexchangeRateValue;
    @SerializedName("status")
    @Expose
    private Object status;

    /**
     * No args constructor for use in serialization
     *
     */
    public ExchangeRateList() {
    }

    /**
     *
     * @param status
     * @param exchangeRateValue
     * @param exchangeRateId
     * @param customsexchangeRateValue
     * @param companyId
     * @param exchangeRateEffectiveDate
     * @param currency
     */
    public ExchangeRateList(Integer exchangeRateId, Currency currency, String exchangeRateEffectiveDate, String exchangeRateValue, Object companyId, String customsexchangeRateValue, Object status) {
        super();
        this.exchangeRateId = exchangeRateId;
        this.currency = currency;
        this.exchangeRateEffectiveDate = exchangeRateEffectiveDate;
        this.exchangeRateValue = exchangeRateValue;
        this.companyId = companyId;
        this.customsexchangeRateValue = customsexchangeRateValue;
        this.status = status;
    }

    public Integer getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(Integer exchangeRateId) {
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

    public String getCustomsexchangeRateValue() {
        return customsexchangeRateValue;
    }

    public void setCustomsexchangeRateValue(String customsexchangeRateValue) {
        this.customsexchangeRateValue = customsexchangeRateValue;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

}
