package in.hiaccounts.hinext.controlpanel.model.configurator_bank.bank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/21/2017.
 */

public class BankDatum implements Serializable{

    @SerializedName("bankId")
    @Expose
    private Long bankId;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("iFSCCode")
    @Expose
    private String iFSCCode;
    @SerializedName("bankEmail")
    @Expose
    private String bankEmail;
    @SerializedName("branchName")
    @Expose
    private String branchName;
    @SerializedName("bankPhoneNo")
    @Expose
    private String bankPhoneNo;
    @SerializedName("bankAccountNo")
    @Expose
    private String bankAccountNo;
    @SerializedName("linkedAccount")
    @Expose
    private Object linkedAccount;
    @SerializedName("linkedAccountForBankCharges")
    @Expose
    private Object linkedAccountForBankCharges;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("linkedAccountMasterDTOList")
    @Expose
    private Object linkedAccountMasterDTOList;
    @SerializedName("linkedBankChargesAccountMasterDTOList")
    @Expose
    private Object linkedBankChargesAccountMasterDTOList;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getiFSCCode() {
        return iFSCCode;
    }

    public void setiFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }

    public String getBankEmail() {
        return bankEmail;
    }

    public void setBankEmail(String bankEmail) {
        this.bankEmail = bankEmail;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBankPhoneNo() {
        return bankPhoneNo;
    }

    public void setBankPhoneNo(String bankPhoneNo) {
        this.bankPhoneNo = bankPhoneNo;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }
}
