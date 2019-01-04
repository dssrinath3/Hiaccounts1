
package in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChartsListData implements Serializable{

    @SerializedName("industryName")
    @Expose
    public String industryName;
    @SerializedName("businessTypeName")
    @Expose
    public String businessTypeName;
    @SerializedName("balanceSheetAccnts")
    @Expose
    public List<BalanceSheetAccnt> balanceSheetAccnts = null;
    @SerializedName("profitLossAccnts")
    @Expose
    public List<ProfitLossAccnt> profitLossAccnts = null;

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public List<BalanceSheetAccnt> getBalanceSheetAccnts() {
        return balanceSheetAccnts;
    }

    public void setBalanceSheetAccnts(List<BalanceSheetAccnt> balanceSheetAccnts) {
        this.balanceSheetAccnts = balanceSheetAccnts;
    }

    public List<ProfitLossAccnt> getProfitLossAccnts() {
        return profitLossAccnts;
    }

    public void setProfitLossAccnts(List<ProfitLossAccnt> profitLossAccnts) {
        this.profitLossAccnts = profitLossAccnts;
    }
}
