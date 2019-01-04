package in.hiaccounts.hinext.controlpanel.model.openingbalance;

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
    private long taxid;
    @SerializedName("taxpercent")
    @Expose
    private double taxpercent;
    @SerializedName("taxamt")
    @Expose
    private double taxamt;
    @SerializedName("creditAmount")
    @Expose
    private String creditAmount;
    @SerializedName("debitAmount")
    @Expose
    private String debitAmount;

    private String invoiceNo;
    private int debitAmt;
    private int crdtAmt;

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

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getDebitAmt() {
        return debitAmt;
    }

    public void setDebitAmt(int debitAmt) {
        this.debitAmt = debitAmt;
    }

    public int getCrdtAmt() {
        return crdtAmt;
    }

    public void setCrdtAmt(int crdtAmt) {
        this.crdtAmt = crdtAmt;
    }
}
