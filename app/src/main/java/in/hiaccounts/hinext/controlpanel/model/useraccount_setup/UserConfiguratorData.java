package in.hiaccounts.hinext.controlpanel.model.useraccount_setup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by srinath on 26/12/17.
 */

public class UserConfiguratorData implements Serializable {

    @SerializedName("unitPriceAccess")
    @Expose
    private String unitPriceAccess;
    @SerializedName("quantityAccess")
    @Expose
    private String quantityAccess;
    @SerializedName("descriptionAccess")
    @Expose
    private String descriptionAccess;
    @SerializedName("cessAccess")
    @Expose
    private String cessAccess;
    @SerializedName("uomAccess")
    @Expose
    private String uomAccess;
    @SerializedName("taxAccess")
    @Expose
    private String taxAccess;
    @SerializedName("dateAccess")
    @Expose
    private String dateAccess;
    @SerializedName("discountAccess")
    @Expose
    private String discountAccess;
    @SerializedName("paymentTypeAccess")
    @Expose
    private String paymentTypeAccess;
    @SerializedName("userAccountSetupID")
    @Expose
    private Long userAccountSetupID;
    @SerializedName("posBankAccount")
    @Expose
    private Long posBankAccount;
    @SerializedName("posCashAccount")
    @Expose
    private Long posCashAccount;
    @SerializedName("printType")
    @Expose
    private String printType;

    public String getUnitPriceAccess() {
        return unitPriceAccess;
    }

    public void setUnitPriceAccess(String unitPriceAccess) {
        this.unitPriceAccess = unitPriceAccess;
    }

    public String getQuantityAccess() {
        return quantityAccess;
    }

    public void setQuantityAccess(String quantityAccess) {
        this.quantityAccess = quantityAccess;
    }

    public String getDescriptionAccess() {
        return descriptionAccess;
    }

    public void setDescriptionAccess(String descriptionAccess) {
        this.descriptionAccess = descriptionAccess;
    }

    public String getCessAccess() {
        return cessAccess;
    }

    public void setCessAccess(String cessAccess) {
        this.cessAccess = cessAccess;
    }

    public String getUomAccess() {
        return uomAccess;
    }

    public void setUomAccess(String uomAccess) {
        this.uomAccess = uomAccess;
    }

    public String getTaxAccess() {
        return taxAccess;
    }

    public void setTaxAccess(String taxAccess) {
        this.taxAccess = taxAccess;
    }

    public String getDateAccess() {
        return dateAccess;
    }

    public void setDateAccess(String dateAccess) {
        this.dateAccess = dateAccess;
    }

    public String getDiscountAccess() {
        return discountAccess;
    }

    public void setDiscountAccess(String discountAccess) {
        this.discountAccess = discountAccess;
    }

    public String getPaymentTypeAccess() {
        return paymentTypeAccess;
    }

    public void setPaymentTypeAccess(String paymentTypeAccess) {
        this.paymentTypeAccess = paymentTypeAccess;
    }

    public Long getUserAccountSetupID() {
        return userAccountSetupID;
    }

    public void setUserAccountSetupID(Long userAccountSetupID) {
        this.userAccountSetupID = userAccountSetupID;
    }

    public Long getPosBankAccount() {
        return posBankAccount;
    }

    public void setPosBankAccount(Long posBankAccount) {
        this.posBankAccount = posBankAccount;
    }

    public Long getPosCashAccount() {
        return posCashAccount;
    }

    public void setPosCashAccount(Long posCashAccount) {
        this.posCashAccount = posCashAccount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }
}
