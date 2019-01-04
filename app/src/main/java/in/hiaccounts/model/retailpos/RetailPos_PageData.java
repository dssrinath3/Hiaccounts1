package in.hiaccounts.model.retailpos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2/13/2017.
 */

public class RetailPos_PageData implements Serializable{


    @SerializedName("customers")
    @Expose
    private List<Customers> customers = null;

    @SerializedName("userRights")
    @Expose
    private UserRights userRights;
    @SerializedName("taxList")
    @Expose
    private List<TaxList> taxList = null;
    @SerializedName("companyLogoPath")
    @Expose
    private String companyLogoPath;
    @SerializedName("tableConfigDetails")
    @Expose
    private List<Object> tableConfigDetails = null;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private String  hiPosServiceCharge;
    @SerializedName("customerAccountList")
    @Expose
    private List<Object> customerAccountList = null;
    @SerializedName("itemCategorys")
    @Expose
    private List<Object> itemCategorys = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private int status;


    public RetailPos_PageData(List<Customers> customers, UserRights userRights, List<TaxList> taxList, String companyLogoPath, List<Object> tableConfigDetails, String hiPosServiceCharge, List<Object> customerAccountList, List<Object> itemCategorys, String message, int status) {
        this.customers = customers;
        this.userRights = userRights;
        this.taxList = taxList;
        this.companyLogoPath = companyLogoPath;
        this.tableConfigDetails = tableConfigDetails;
        this.hiPosServiceCharge = hiPosServiceCharge;
        this.customerAccountList = customerAccountList;
        this.itemCategorys = itemCategorys;
        this.message = message;
        this.status = status;
    }

    public List<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customers> customers) {
        this.customers = customers;
    }

    public UserRights getUserRights() {
        return userRights;
    }

    public void setUserRights(UserRights userRights) {
        this.userRights = userRights;
    }

    public List<TaxList> getTaxList() {
        return taxList;
    }

    public void setTaxList(List<TaxList> taxList) {
        this.taxList = taxList;
    }

    public String getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(String companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public List<Object> getTableConfigDetails() {
        return tableConfigDetails;
    }

    public void setTableConfigDetails(List<Object> tableConfigDetails) {
        this.tableConfigDetails = tableConfigDetails;
    }

    public String getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(String hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public List<Object> getCustomerAccountList() {
        return customerAccountList;
    }

    public void setCustomerAccountList(List<Object> customerAccountList) {
        this.customerAccountList = customerAccountList;
    }

    public List<Object> getItemCategorys() {
        return itemCategorys;
    }

    public void setItemCategorys(List<Object> itemCategorys) {
        this.itemCategorys = itemCategorys;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
