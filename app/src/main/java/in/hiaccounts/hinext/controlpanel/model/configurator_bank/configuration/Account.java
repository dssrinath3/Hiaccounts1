
package in.hiaccounts.hinext.controlpanel.model.configurator_bank.configuration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod.AgId;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod.AmAccountid;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod.CompanyId;

public class Account implements Serializable {

    @SerializedName("accountid")
    @Expose
    private Long accountid;
    @SerializedName("am_accountid")
    @Expose
    private AmAccountid amAccountid;
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("account_code")
    @Expose
    private String accountCode;
    @SerializedName("ag_id")
    @Expose
    private AgId agId;
    @SerializedName("stringAccountCode")
    @Expose
    private String stringAccountCode;
    @SerializedName("flag")
    @Expose
    private boolean flag;
    @SerializedName("companyId")
    @Expose
    private CompanyId companyId;
    @SerializedName("codecaoId")
    @Expose
    private String codecaoId;
    @SerializedName("aparcode")
    @Expose
    private String aparcode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;

    @SerializedName("parentAccount_name")
    @Expose
    private String parentAccount_name;

    @SerializedName("accountGroup")
    @Expose
    private String accountGroup ;

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public AmAccountid getAmAccountid() {
        return amAccountid;
    }

    public void setAmAccountid(AmAccountid amAccountid) {
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

    public AgId getAgId() {
        return agId;
    }

    public void setAgId(AgId agId) {
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

    public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
        this.companyId = companyId;
    }

    public String getCodecaoId() {
        return codecaoId;
    }

    public void setCodecaoId(String codecaoId) {
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

    public String getParentAccount_name() {
        return parentAccount_name;
    }

    public void setParentAccount_name(String parentAccount_name) {
        this.parentAccount_name = parentAccount_name;
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup;
    }
}
