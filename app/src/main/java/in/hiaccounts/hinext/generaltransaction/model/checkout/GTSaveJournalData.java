package in.hiaccounts.hinext.generaltransaction.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.generaltransaction.model.payee_account.Account;

/**
 * Created by Prateek on 8/5/2017.
 */

public class GTSaveJournalData implements Serializable {

    @SerializedName("journalEntryDetailsList")
    @Expose
    public List<Account> journalEntryDetailsList = null;
    @SerializedName("totalDebit")
    @Expose
    public double totalDebit;
    @SerializedName("totalCredit")
    @Expose
    public double totalCredit;
    @SerializedName("memo")
    @Expose
    public String memo;
    @SerializedName("jeDate")
    @Expose
    public String jeDate;

    public List<Account> getJournalEntryDetailsList() {
        return journalEntryDetailsList;
    }

    public void setJournalEntryDetailsList(List<Account> journalEntryDetailsList) {
        this.journalEntryDetailsList = journalEntryDetailsList;
    }


    public String getJeDate() {
        return jeDate;
    }

    public void setJeDate(String jeDate) {
        this.jeDate = jeDate;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
