
package in.hiaccounts.model.purchase.hinextpurchase;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 05/08/2017.
 */

public class PageLoadData implements Serializable
{

    @SerializedName("suppliers")
    @Expose
    private List<Supplier> suppliers = null;
    @SerializedName("userRights")
    @Expose
    private UserRights userRights;
    @SerializedName("taxList")
    @Expose
    private List<TaxList> taxList = null;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private String hiPosServiceCharge;
    @SerializedName("tableConfigDetails")
    @Expose
    private List<Object> tableConfigDetails = null;
    @SerializedName("companyLogoPath")
    @Expose
    private String companyLogoPath;
    @SerializedName("itemCategorys")
    @Expose
    private List<Object> itemCategorys = null;
    @SerializedName("supplierAccountList")
    @Expose
    private List<Object> supplierAccountList = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Long status;


    /**
     * No args constructor for use in serialization
     * 
     */
    public PageLoadData() {
    }
    /**
     *
     * @param message
     * @param companyLogoPath
     * @param tableConfigDetails
     * @param status
     * @param taxList
     * @param supplierAccountList
     * @param itemCategorys
     * @param hiPosServiceCharge
     * @param userRights
     * @param suppliers
     */

    public PageLoadData(List<Supplier> suppliers, UserRights userRights, List<TaxList> taxList, String hiPosServiceCharge, List<Object> tableConfigDetails, String companyLogoPath, List<Object> itemCategorys, List<Object> supplierAccountList, String message, Long status) {
        this.suppliers = suppliers;
        this.userRights = userRights;
        this.taxList = taxList;
        this.hiPosServiceCharge = hiPosServiceCharge;
        this.tableConfigDetails = tableConfigDetails;
        this.companyLogoPath = companyLogoPath;
        this.itemCategorys = itemCategorys;
        this.supplierAccountList = supplierAccountList;
        this.message = message;
        this.status = status;
    }


    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
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

    public String getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(String hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public List<Object> getTableConfigDetails() {
        return tableConfigDetails;
    }

    public void setTableConfigDetails(List<Object> tableConfigDetails) {
        this.tableConfigDetails = tableConfigDetails;
    }

    public String getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(String companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public List<Object> getItemCategorys() {
        return itemCategorys;
    }

    public void setItemCategorys(List<Object> itemCategorys) {
        this.itemCategorys = itemCategorys;
    }

    public List<Object> getSupplierAccountList() {
        return supplierAccountList;
    }

    public void setSupplierAccountList(List<Object> supplierAccountList) {
        this.supplierAccountList = supplierAccountList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
