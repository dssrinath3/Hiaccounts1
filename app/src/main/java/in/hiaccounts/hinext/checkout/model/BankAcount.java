package in.hiaccounts.hinext.checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 26/12/17.
 */

public class BankAcount implements Serializable {


    @SerializedName("accountid")
    @Expose
    public Long accountid;
    @SerializedName("amtexclusivetax")
    @Expose
    public double amtexclusivetax;
    @SerializedName("taxid")
    @Expose
    public long taxid;
    @SerializedName("taxpercent")
    @Expose
    public double taxpercent;
    @SerializedName("taxamt")
    @Expose
    public double taxamt;
    @SerializedName("account_name")
    @Expose
    public String accountName;
    @SerializedName("account_code")
    @Expose
    public String accountCode;
    @SerializedName("stringAccountCode")
    @Expose
    public String stringAccountCode;
    @SerializedName("aparcode")
    @Expose
    public String aparcode;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("parentAccount_name")
    @Expose
    public String parentAccountName;
    @SerializedName("accountGroup")
    @Expose
    public String accountGroup;
    @SerializedName("am_accountid")
    @Expose
    public Object amAccountid;
    @SerializedName("ag_id")
    @Expose
    public Object agId;
    @SerializedName("flag")
    @Expose
    public boolean flag;
    @SerializedName("codecaoId")
    @Expose
    public Object codecaoId;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("amtinclusivetax")
    @Expose
    public double amtinclusivetax;
    @SerializedName("accountDescription")
    @Expose
    public Object accountDescription;
    @SerializedName("taxName")
    @Expose
    public Object taxName;
    @SerializedName("creditAmount")
    @Expose
    public Object creditAmount;
    @SerializedName("debitAmount")
    @Expose
    public Object debitAmount;
    @SerializedName("gtAmountExcTax")
    @Expose
    public Object gtAmountExcTax;

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public long getTaxid() {
        return taxid;
    }

    public void setTaxid(long taxid) {
        this.taxid = taxid;
    }

    public double getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(double taxpercent) {
        this.taxpercent = taxpercent;
    }

    public double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(double taxamt) {
        this.taxamt = taxamt;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getStringAccountCode() {
        return stringAccountCode;
    }

    public void setStringAccountCode(String stringAccountCode) {
        this.stringAccountCode = stringAccountCode;
    }

    public String getAparcode() {
        return aparcode;
    }

    public void setAparcode(String aparcode) {
        this.aparcode = aparcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentAccountName() {
        return parentAccountName;
    }

    public void setParentAccountName(String parentAccountName) {
        this.parentAccountName = parentAccountName;
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup;
    }

    public Object getAmAccountid() {
        return amAccountid;
    }

    public void setAmAccountid(Object amAccountid) {
        this.amAccountid = amAccountid;
    }

    public Object getAgId() {
        return agId;
    }

    public void setAgId(Object agId) {
        this.agId = agId;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getCodecaoId() {
        return codecaoId;
    }

    public void setCodecaoId(Object codecaoId) {
        this.codecaoId = codecaoId;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public Object getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(Object accountDescription) {
        this.accountDescription = accountDescription;
    }

    public Object getTaxName() {
        return taxName;
    }

    public void setTaxName(Object taxName) {
        this.taxName = taxName;
    }

    public Object getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Object creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Object getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Object debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Object getGtAmountExcTax() {
        return gtAmountExcTax;
    }

    public void setGtAmountExcTax(Object gtAmountExcTax) {
        this.gtAmountExcTax = gtAmountExcTax;
    }
}
