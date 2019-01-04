package in.hiaccounts.hinext.controlpanel.model.configurator_bank.configuration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Prateek on 8/23/2017.
 */

public class ConfigurationData  implements Serializable{

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("printType")
    @Expose
    private String printType;
    @SerializedName("posBankAccountForSaving")
    @Expose
    private Long posBankAccountForSaving;
    @SerializedName("posCashAccountForSaving")
    @Expose
    private Long posCashAccountForSaving;
    @SerializedName("projectModuleDTOList")
    @Expose
    private List projectModuleDTOList = null;
    @SerializedName("bankAccountName")
    @Expose
    private String bankAccountName;
    @SerializedName("cashAccountName")
    @Expose
    private String cashAccountName;
    @SerializedName("bankAccountId")
    @Expose
    private Long bankAccountId;
    @SerializedName("cashAccountId")
    @Expose
    private Long cashAccountId;
    @SerializedName("unitPrice")
    @Expose
    private String unitPrice;
    @SerializedName("receiptFooter")
    @Expose
    private Object receiptFooter;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("defaultPrinter")
    @Expose
    private Object defaultPrinter;
    @SerializedName("restaurentIndustry")
    @Expose
    private Object restaurentIndustry;
    @SerializedName("restaurent")
    @Expose
    private Object restaurent;
    @SerializedName("retail")
    @Expose
    private Object retail;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public Long getPosBankAccountForSaving() {
        return posBankAccountForSaving;
    }

    public void setPosBankAccountForSaving(Long posBankAccountForSaving) {
        this.posBankAccountForSaving = posBankAccountForSaving;
    }

    public Long getPosCashAccountForSaving() {
        return posCashAccountForSaving;
    }

    public void setPosCashAccountForSaving(Long posCashAccountForSaving) {
        this.posCashAccountForSaving = posCashAccountForSaving;
    }
    public List<ProjectModuleDTOList> getProjectModuleDTOList() {
        return projectModuleDTOList;
    }

    public void setProjectModuleDTOList(List<ProjectModuleDTOList> projectModuleDTOList) {
        this.projectModuleDTOList = projectModuleDTOList;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getCashAccountName() {
        return cashAccountName;
    }

    public void setCashAccountName(String cashAccountName) {
        this.cashAccountName = cashAccountName;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Long getCashAccountId() {
        return cashAccountId;
    }

    public void setCashAccountId(Long cashAccountId) {
        this.cashAccountId = cashAccountId;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Object getReceiptFooter() {
        return receiptFooter;
    }

    public void setReceiptFooter(Object receiptFooter) {
        this.receiptFooter = receiptFooter;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Object getDefaultPrinter() {
        return defaultPrinter;
    }

    public void setDefaultPrinter(Object defaultPrinter) {
        this.defaultPrinter = defaultPrinter;
    }

    public Object getRestaurentIndustry() {
        return restaurentIndustry;
    }

    public void setRestaurentIndustry(Object restaurentIndustry) {
        this.restaurentIndustry = restaurentIndustry;
    }

    public Object getRestaurent() {
        return restaurent;
    }

    public void setRestaurent(Object restaurent) {
        this.restaurent = restaurent;
    }

    public Object getRetail() {
        return retail;
    }

    public void setRetail(Object retail) {
        this.retail = retail;
    }
}
