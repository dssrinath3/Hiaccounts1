
package in.hiaccounts.hinext.sales.model.printlist.payement_receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AmAccountid implements Serializable {

    @SerializedName("accountid")
    @Expose
    public long accountid;
    @SerializedName("am_accountid")
    @Expose
    public Object amAccountid;
    @SerializedName("account_name")
    @Expose
    public String accountName;
    @SerializedName("account_code")
    @Expose
    public String accountCode;
    @SerializedName("ag_id")
    @Expose
    public AgId agId;
    @SerializedName("stringAccountCode")
    @Expose
    public String stringAccountCode;
    @SerializedName("flag")
    @Expose
    public boolean flag;
    @SerializedName("companyId")
    @Expose
    public CompanyId companyId;
    @SerializedName("codecaoId")
    @Expose
    public String codecaoId;
    @SerializedName("aparcode")
    @Expose
    public String aparcode;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;

    public long getAccountid() {
        return accountid;
    }

    public void setAccountid(long accountid) {
        this.accountid = accountid;
    }

    public Object getAmAccountid() {
        return amAccountid;
    }

    public void setAmAccountid(Object amAccountid) {
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
}
