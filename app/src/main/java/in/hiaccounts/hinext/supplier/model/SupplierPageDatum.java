package in.hiaccounts.hinext.supplier.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Prateek on 7/26/2017.
 */

public class SupplierPageDatum {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("accountmasterid")
    @Expose
    private Object accountmasterid;
    @SerializedName("linkedaccount")
    @Expose
    private Object linkedaccount;
    @SerializedName("accountid")
    @Expose
    private Object accountid;
    @SerializedName("account_name")
    @Expose
    private Object accountName;
    @SerializedName("stringAccountCode")
    @Expose
    private Object stringAccountCode;
    @SerializedName("supplierName")
    @Expose
    private Object supplierName;
    @SerializedName("supplierNumber")
    @Expose
    private Object supplierNumber;
    @SerializedName("supplierEmail")
    @Expose
    private Object supplierEmail;
    @SerializedName("supplierBillingAddress")
    @Expose
    private Object supplierBillingAddress;
    @SerializedName("supplierWebsite")
    @Expose
    private Object supplierWebsite;
    @SerializedName("supplierPhoneNumber1")
    @Expose
    private Object supplierPhoneNumber1;
    @SerializedName("supplierPhoneNumber2")
    @Expose
    private Object supplierPhoneNumber2;
    @SerializedName("supplierFax")
    @Expose
    private Object supplierFax;
    @SerializedName("supplierContactName")
    @Expose
    private Object supplierContactName;
    @SerializedName("supplierGeneralNote")
    @Expose
    private Object supplierGeneralNote;
    @SerializedName("gstDate")
    @Expose
    private Object gstDate;
    @SerializedName("companyNumber")
    @Expose
    private Object companyNumber;
    @SerializedName("supplierCode")
    @Expose
    private Object supplierCode;
    @SerializedName("companyCode")
    @Expose
    private Object companyCode;
    @SerializedName("shipToAddress")
    @Expose
    private Object shipToAddress;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("attention")
    @Expose
    private Object attention;
    @SerializedName("terms")
    @Expose
    private Object terms;
    @SerializedName("creditLimit")
    @Expose
    private Object creditLimit;
    @SerializedName("companyRegNo")
    @Expose
    private Object companyRegNo;
    @SerializedName("notificationFlag")
    @Expose
    private Object notificationFlag;
    @SerializedName("from_Reg_Comp")
    @Expose
    private Object fromRegComp;
    @SerializedName("to_Reg_Comp")
    @Expose
    private Object toRegComp;
    @SerializedName("gstIn")
    @Expose
    private Object gstIn;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("stateDTOList")
    @Expose
    private List<StateDTOList> stateDTOList = null;
    @SerializedName("countryDTOList")
    @Expose
    private List<CountryDTOList> countryDTOList = null;
    @SerializedName("currencyDTOList")
    @Expose
    private List<CurrencyDTOList> currencyDTOList = null;
    @SerializedName("bankName")
    @Expose
    private Object bankName;
    @SerializedName("accountNo")
    @Expose
    private Object accountNo;
    @SerializedName("iFSCCode")
    @Expose
    private Object iFSCCode;
    @SerializedName("branchName")
    @Expose
    private Object branchName;
    @SerializedName("personIncharge")
    @Expose
    private Object personIncharge;
    @SerializedName("website")
    @Expose
    private Object website;
    @SerializedName("currency")
    @Expose
    private Object currency;
    @SerializedName("cmpyCountry")
    @Expose
    private String cmpyCountry;
    @SerializedName("cmpyCurrency")
    @Expose
    private String cmpyCurrency;
    @SerializedName("cmpyState")
    @Expose
    private String cmpyState;
    @SerializedName("panNumber")
    @Expose
    private Object panNumber;

    public List<StateDTOList> getStateDTOList() {
        return stateDTOList;
    }

    public void setStateDTOList(List<StateDTOList> stateDTOList) {
        this.stateDTOList = stateDTOList;
    }

    public List<CountryDTOList> getCountryDTOList() {
        return countryDTOList;
    }

    public void setCountryDTOList(List<CountryDTOList> countryDTOList) {
        this.countryDTOList = countryDTOList;
    }

    public List<CurrencyDTOList> getCurrencyDTOList() {
        return currencyDTOList;
    }

    public void setCurrencyDTOList(List<CurrencyDTOList> currencyDTOList) {
        this.currencyDTOList = currencyDTOList;
    }
}
