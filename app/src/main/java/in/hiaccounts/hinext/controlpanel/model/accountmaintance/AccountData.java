package in.hiaccounts.hinext.controlpanel.model.accountmaintance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 8/26/2017.
 */

public class AccountData implements Serializable{

    @SerializedName("ag_id")
    @Expose
    public Long agId;
    @SerializedName("ag_name")
    @Expose
    public String agName;
    @SerializedName("ag_desc")
    @Expose
    public String agDesc;
    @SerializedName("ac_id")
    @Expose
    public Object acId;
    @SerializedName("parent")
    @Expose
    public Object parent;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("accGroup")
    @Expose
    public String accGroup;
    @SerializedName("account_code1")
    @Expose
    public String accountCode1;
    @SerializedName("account_code2")
    @Expose
    public Object accountCode2;

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

    public Object getAcId() {
        return acId;
    }

    public void setAcId(Object acId) {
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

    public String getAccGroup() {
        return accGroup;
    }

    public void setAccGroup(String accGroup) {
        this.accGroup = accGroup;
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

    @Override
    public String toString() {
        return agName;
    }
}
