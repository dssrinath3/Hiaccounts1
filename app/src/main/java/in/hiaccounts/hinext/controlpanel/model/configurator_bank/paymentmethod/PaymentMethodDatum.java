package in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/22/2017.
 */

public class PaymentMethodDatum implements Serializable {


    @SerializedName("paymentmethodId")
    @Expose
    private Long paymentmethodId;
    @SerializedName("paymentmethodName")
    @Expose
    private String paymentmethodName;
    @SerializedName("paymentmethodDescription")
    @Expose
    private String paymentmethodDescription;
    @SerializedName("paymentmethodType")
    @Expose
    private String paymentmethodType;
    @SerializedName("defaultType")
    @Expose
    private Boolean defaultType;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("companyId")
    @Expose
    private Object companyId;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;

    @SerializedName("accountMaster")
    @Expose
    private String accountMaster;
    @SerializedName("accountMasterId")
    @Expose
    private Long accountMasterId;

    @SerializedName("validateVoucher")
    @Expose
    private String validateVoucher;
    @SerializedName("accountMasterDTOList")
    @Expose
    private String accountMasterDTOList;




    public Long getPaymentmethodId() {
        return paymentmethodId;
    }

    public void setPaymentmethodId(Long paymentmethodId) {
        this.paymentmethodId = paymentmethodId;
    }

    public String getPaymentmethodName() {
        return paymentmethodName;
    }

    public void setPaymentmethodName(String paymentmethodName) {
        this.paymentmethodName = paymentmethodName;
    }

    public String getPaymentmethodDescription() {
        return paymentmethodDescription;
    }

    public void setPaymentmethodDescription(String paymentmethodDescription) {
        this.paymentmethodDescription = paymentmethodDescription;
    }

    public String getPaymentmethodType() {
        return paymentmethodType;
    }

    public void setPaymentmethodType(String paymentmethodType) {
        this.paymentmethodType = paymentmethodType;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
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

    public String getAccountMaster() {
        return accountMaster;
    }

    public void setAccountMaster(String accountMaster) {
        this.accountMaster = accountMaster;
    }

    public Long getAccountMasterId() {
        return accountMasterId;
    }

    public void setAccountMasterId(Long accountMasterId) {
        this.accountMasterId = accountMasterId;
    }

    public String getValidateVoucher() {
        return validateVoucher;
    }

    public void setValidateVoucher(String validateVoucher) {
        this.validateVoucher = validateVoucher;
    }

    public String getAccountMasterDTOList() {
        return accountMasterDTOList;
    }

    public void setAccountMasterDTOList(String accountMasterDTOList) {
        this.accountMasterDTOList = accountMasterDTOList;
    }

    public Boolean getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(Boolean defaultType) {
        this.defaultType = defaultType;
    }


}
