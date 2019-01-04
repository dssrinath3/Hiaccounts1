package in.hiaccounts.hinext.generaltransaction.model.payee_account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 8/3/2017.
 */

public class Account implements Serializable {

    @SerializedName("accountid")
    @Expose
    private Long accountid;
    @SerializedName("am_accountid")
    @Expose
    private Long amAccountid;
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("account_code")
    @Expose
    private String accountCode;
    @SerializedName("ag_id")
    @Expose
    private Long agId;
    @SerializedName("stringAccountCode")
    @Expose
    private String stringAccountCode;
    @SerializedName("flag")
    @Expose
    private boolean flag;
    @SerializedName("codecaoId")
    @Expose
    private Long codecaoId;
    @SerializedName("aparcode")
    @Expose
    private String aparcode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("locationId")
    @Expose
    private Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Long useraccountId;
    @SerializedName("parentAccount_name")
    @Expose
    private String parentAccountName;
    @SerializedName("accountGroup")
    @Expose
    private String accountGroup;
    @SerializedName("amtinclusivetax")
    @Expose
    private double amtinclusivetax;
    @SerializedName("accountDescription")
    @Expose
    private String accountDescription;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("amtexclusivetax")
    @Expose
    private double amtexclusivetax;
    @SerializedName("taxid")
    @Expose
    private Long taxid;
    @SerializedName("taxpercent")
    @Expose
    private double taxpercent;
    @SerializedName("taxamt")
    @Expose
    private double taxamt;
    @SerializedName("creditAmount")
    @Expose
    private double creditAmount;
    @SerializedName("debitAmount")
    @Expose
    private double debitAmount;

    @SerializedName("supplierId")
    @Expose
    private Long supplierId;

    private String invoiceNo;

    @SerializedName("taxAmountSplit")
    @Expose
    private double taxAmountSplit;


    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public double getTaxAmountSplit() {
        return taxAmountSplit;
    }

    public void setTaxAmountSplit(double taxAmountSplit) {
        this.taxAmountSplit = taxAmountSplit;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
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

    public Long getAgId() {
        return agId;
    }

    public void setAgId(Long agId) {
        this.agId = agId;
    }

    public String getStringAccountCode() {
        return stringAccountCode;
    }

    public void setStringAccountCode(String stringAccountCode) {
        this.stringAccountCode = stringAccountCode;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Long getCodecaoId() {
        return codecaoId;
    }

    public void setCodecaoId(Long codecaoId) {
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

    public double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
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

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }
}
