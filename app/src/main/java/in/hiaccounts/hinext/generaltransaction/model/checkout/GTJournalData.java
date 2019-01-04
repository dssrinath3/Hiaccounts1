package in.hiaccounts.hinext.generaltransaction.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.generaltransaction.model.payee_account.Account;
import in.hiaccounts.hinext.generaltransaction.model.response.CompanyData;

/**
 * Created by Prateek on 8/29/2017.
 */

public class GTJournalData implements Serializable {

    @SerializedName("jeId")
    @Expose
    public Long jeId;
    @SerializedName("jeNo")
    @Expose
    public String jeNo;
    @SerializedName("jeDate")
    @Expose
    public Long jeDate;
    @SerializedName("memo")
    @Expose
    public String memo;
    @SerializedName("totalDebit")
    @Expose
    public double totalDebit;
    @SerializedName("totalCredit")
    @Expose
    public double totalCredit;
    @SerializedName("companyId")
    @Expose
    public Object companyId;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("journalEntryDetailsList")
    @Expose
    public List<Account> journalEntryDetailsList = null;
    @SerializedName("jepDate")
    @Expose
    public String jepDate;
    @SerializedName("companyData")
    @Expose
    public CompanyData companyData;

    public Long getJeId() {
        return jeId;
    }

    public void setJeId(Long jeId) {
        this.jeId = jeId;
    }

    public String getJeNo() {
        return jeNo;
    }

    public void setJeNo(String jeNo) {
        this.jeNo = jeNo;
    }

    public Long getJeDate() {
        return jeDate;
    }

    public void setJeDate(Long jeDate) {
        this.jeDate = jeDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
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

    public List<Account> getJournalEntryDetailsList() {
        return journalEntryDetailsList;
    }

    public void setJournalEntryDetailsList(List<Account> journalEntryDetailsList) {
        this.journalEntryDetailsList = journalEntryDetailsList;
    }

    public String getJepDate() {
        return jepDate;
    }

    public void setJepDate(String jepDate) {
        this.jepDate = jepDate;
    }

    public CompanyData getCompanyData() {
        return companyData;
    }

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }
}
