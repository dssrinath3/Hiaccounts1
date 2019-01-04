package in.hiaccounts.hinext.controlpanel.model.configurator_bank.exchange_rate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.controlpanel.model.configurator_bank.currency.CurrencyDatum;

/**
 * Created by Prateek on 8/22/2017.
 */

public class ExchangeRateDatum implements Serializable {

    @SerializedName("exchangeRateId")
    @Expose
    private Long exchangeRateId;
   /* @SerializedName("currency")
    @Expose
    private CurrencyDatum currency;*/
    @SerializedName("currencyDTOList")
    @Expose
    private List<CurrencyDatum> currencyDTOList = null;

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

    public Long getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(Long exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    public List<CurrencyDatum> getCurrencyDTOList() {
        return currencyDTOList;
    }

    public void setCurrencyDTOList(List<CurrencyDatum> currencyDTOList) {
        this.currencyDTOList = currencyDTOList;
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
