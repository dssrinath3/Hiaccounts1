
package in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AgId implements Serializable{

    @SerializedName("ag_id")
    @Expose
    private Long agId;
    @SerializedName("ag_name")
    @Expose
    private String agName;
    @SerializedName("ag_desc")
    @Expose
    private String agDesc;
    @SerializedName("ac_id")
    @Expose
    private AcId acId;
    @SerializedName("parent")
    @Expose
    private Object parent;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("totalBalance")
    @Expose
    private double totalBalance;
    @SerializedName("fiscalYear")
    @Expose
    private FiscalYear fiscalYear;
    @SerializedName("account_code1")
    @Expose
    private String accountCode1;
    @SerializedName("account_code2")
    @Expose
    private Object accountCode2;

    public Long getAgId() {
        return agId;
    }

    public void setAgId(Long agId) {
        this.agId = agId;
    }

    public String getAgName() {
        return agName;
    }

    public void setAgName(String agName) {
        this.agName = agName;
    }

    public String getAgDesc() {
        return agDesc;
    }

    public void setAgDesc(String agDesc) {
        this.agDesc = agDesc;
    }

    public AcId getAcId() {
        return acId;
    }

    public void setAcId(AcId acId) {
        this.acId = acId;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public FiscalYear getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(FiscalYear fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getAccountCode1() {
        return accountCode1;
    }

    public void setAccountCode1(String accountCode1) {
        this.accountCode1 = accountCode1;
    }

    public Object getAccountCode2() {
        return accountCode2;
    }

    public void setAccountCode2(Object accountCode2) {
        this.accountCode2 = accountCode2;
    }
}
