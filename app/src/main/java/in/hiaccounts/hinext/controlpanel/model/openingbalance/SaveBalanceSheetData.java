package in.hiaccounts.hinext.controlpanel.model.openingbalance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Prateek on 8/28/2017.
 */

public class SaveBalanceSheetData implements Serializable  {

    @SerializedName("selectedAccountList")
    @Expose
    public List<Account> selectedAccountList = null;
    @SerializedName("totalDebit")
    @Expose
    public double totalDebit;
    @SerializedName("totalCredit")
    @Expose
    public double totalCredit;

    public List<Account> getSelectedAccountList() {
        return selectedAccountList;
    }

    public void setSelectedAccountList(List<Account> selectedAccountList) {
        this.selectedAccountList = selectedAccountList;
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
}
