
package in.hiaccounts.hinext.generaltransaction.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedAccountList implements Serializable {

    @SerializedName("accountid")
    @Expose
    public Long accountid;
    @SerializedName("am_accountid")
    @Expose
    public Long amAccountid;
    @SerializedName("account_name")
    @Expose
    public String accountName;
    @SerializedName("account_code")
    @Expose
    public String accountCode;
    @SerializedName("ag_id")
    @Expose
    public Long agId;
    @SerializedName("stringAccountCode")
    @Expose
    public String stringAccountCode;
    @SerializedName("flag")
    @Expose
    public Boolean flag;
    @SerializedName("codecaoId")
    @Expose
    public Object codecaoId;
    @SerializedName("aparcode")
    @Expose
    public String aparcode;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("locationId")
    @Expose
    public Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Long useraccountId;
    @SerializedName("parentAccount_name")
    @Expose
    public String parentAccountName;
    @SerializedName("accountGroup")
    @Expose
    public String accountGroup;
    @SerializedName("amtinclusivetax")
    @Expose
    public Double amtinclusivetax;
    @SerializedName("accountDescription")
    @Expose
    public String accountDescription;
    @SerializedName("taxName")
    @Expose
    public Object taxName;
    @SerializedName("amtexclusivetax")
    @Expose
    public Double amtexclusivetax;
    @SerializedName("taxid")
    @Expose
    public Long taxid;
    @SerializedName("taxpercent")
    @Expose
    public Double taxpercent;
    @SerializedName("taxAmountSplit")
    @Expose
    public String taxAmountSplit;

    @SerializedName("taxamt")
    @Expose
    public Double taxamt;
    @SerializedName("creditAmount")
    @Expose
    public Double creditAmount;
    @SerializedName("debitAmount")
    @Expose
    public Double debitAmount;
    @SerializedName("invoiceNumber")
    @Expose
    public String invoiceNumber;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getTaxAmountSplit() {
        return taxAmountSplit;
    }

    public void setTaxAmountSplit(String taxAmountSplit) {
        this.taxAmountSplit = taxAmountSplit;
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

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Object getCodecaoId() {
        return codecaoId;
    }

    public void setCodecaoId(Object codecaoId) {
        this.codecaoId = codecaoId;
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

    public Double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(Double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public Object getTaxName() {
        return taxName;
    }

    public void setTaxName(Object taxName) {
        this.taxName = taxName;
    }

    public Double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(Double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }


    public Double getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(Double taxpercent) {
        this.taxpercent = taxpercent;
    }

    public Double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(Double taxamt) {
        this.taxamt = taxamt;
    }

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public Long getAmAccountid() {
        return amAccountid;
    }

    public void setAmAccountid(Long amAccountid) {
        this.amAccountid = amAccountid;
    }

    public Long getAgId() {
        return agId;
    }

    public void setAgId(Long agId) {
        this.agId = agId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }
}
