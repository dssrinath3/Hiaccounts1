package in.hiaccounts.hinext.controlpanel.model.openingbalance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/27/2017.
 */

public class BalanceSheetData implements Serializable {



    @SerializedName("opId")
    @Expose
    public Long opId;
    @SerializedName("accountid")
    @Expose
    public String accountid;
    @SerializedName("account_code")
    @Expose
    public String accountCode;
    @SerializedName("begin_Year")
    @Expose
    public String beginYear;
    @SerializedName("end_Year")
    @Expose
    public String endYear;
    @SerializedName("trancation_date")
    @Expose
    public Object trancationDate;
    @SerializedName("amount")
    @Expose
    public double amount;
    @SerializedName("stringAccountCode")
    @Expose
    public String stringAccountCode;
    @SerializedName("financialYear")
    @Expose
    public Object financialYear;
    @SerializedName("compId")
    @Expose
    public Object compId;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("selectedAccountList")
    @Expose
    public Object selectedAccountList;
    @SerializedName("totalDebit")
    @Expose
    public Object totalDebit;
    @SerializedName("totalCredit")
    @Expose
    public String totalCredit;
    @SerializedName("debitAmount")
    @Expose
    public String debitAmount;
    @SerializedName("creditAmount")
    @Expose
    public String creditAmount;

    public Long getOpId() {
        return opId;
    }

    public void setOpId(Long opId) {
        this.opId = opId;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(String beginYear) {
        this.beginYear = beginYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public Object getTrancationDate() {
        return trancationDate;
    }

    public void setTrancationDate(Object trancationDate) {
        this.trancationDate = trancationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStringAccountCode() {
        return stringAccountCode;
    }

    public void setStringAccountCode(String stringAccountCode) {
        this.stringAccountCode = stringAccountCode;
    }

    public Object getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(Object financialYear) {
        this.financialYear = financialYear;
    }

    public Object getCompId() {
        return compId;
    }

    public void setCompId(Object compId) {
        this.compId = compId;
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

    public Object getSelectedAccountList() {
        return selectedAccountList;
    }

    public void setSelectedAccountList(Object selectedAccountList) {
        this.selectedAccountList = selectedAccountList;
    }

    public Object getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Object totalDebit) {
        this.totalDebit = totalDebit;
    }

    public String getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(String totalCredit) {
        this.totalCredit = totalCredit;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }
}
