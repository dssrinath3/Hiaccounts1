package in.hiaccounts.hinext.inventory.model.itemcommission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/13/2017.
 */

public class AddItemCommissionData implements Serializable {
    @SerializedName("itemCommId")
    @Expose
    private Long itemCommId;
    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("toDate")
    @Expose
    private String toDate;
    @SerializedName("amountordiscount")
    @Expose
    private Long amountordiscount;
    @SerializedName("customerId")
    @Expose
    private Long customerId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemId")
    @Expose
    private Long itemId;
    @SerializedName("checkBoxValue")
    @Expose
    private String checkBoxValue;
    @SerializedName("customerID")
    @Expose
    private Long customerID;
    @SerializedName("itemID")
    @Expose
    private Long itemID;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("customerDTOList")
    @Expose
    private String customerDTOList;
    @SerializedName("itemDTOList")
    @Expose
    private String itemDTOList;

    public Long getItemCommId() {
        return itemCommId;
    }

    public void setItemCommId(Long itemCommId) {
        this.itemCommId = itemCommId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Long getAmountordiscount() {
        return amountordiscount;
    }

    public void setAmountordiscount(Long amountordiscount) {
        this.amountordiscount = amountordiscount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getCheckBoxValue() {
        return checkBoxValue;
    }

    public void setCheckBoxValue(String checkBoxValue) {
        this.checkBoxValue = checkBoxValue;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerDTOList() {
        return customerDTOList;
    }

    public void setCustomerDTOList(String customerDTOList) {
        this.customerDTOList = customerDTOList;
    }

    public String getItemDTOList() {
        return itemDTOList;
    }

    public void setItemDTOList(String itemDTOList) {
        this.itemDTOList = itemDTOList;
    }
}
