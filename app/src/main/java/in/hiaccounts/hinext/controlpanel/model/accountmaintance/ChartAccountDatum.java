package in.hiaccounts.hinext.controlpanel.model.accountmaintance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 8/26/2017.
 */

public class ChartAccountDatum implements Serializable{

    @SerializedName("accountid")
    @Expose
    public Long accountid;
    @SerializedName("stringAccountCode")
    @Expose
    public String stringAccountCode;
    @SerializedName("account_name")
    @Expose
    public String account_name;
    @SerializedName("accountGroupName")
    @Expose
    public String accountGroupName;
    @SerializedName("aparcode")
    @Expose
    public String aparcode;

    @SerializedName("account_code")
    @Expose
    public String account_code;


    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public String getStringAccountCode() {
        return stringAccountCode;
    }

    public void setStringAccountCode(String stringAccountCode) {
        this.stringAccountCode = stringAccountCode;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccountGroupName() {
        return accountGroupName;
    }

    public void setAccountGroupName(String accountGroupName) {
        this.accountGroupName = accountGroupName;
    }

    public String getAparcode() {
        return aparcode;
    }

    public void setAparcode(String aparcode) {
        this.aparcode = aparcode;
    }

    public String getAccount_code() {
        return account_code;
    }

    public void setAccount_code(String account_code) {
        this.account_code = account_code;
    }

    @Override
    public String toString() {
        if (account_code!=null) {
            return account_name + ":" + account_code;
        }
        if (account_code==null){
            return account_name;
        }
        return null;
    }
}
