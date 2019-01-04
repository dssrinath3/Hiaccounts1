package in.hiaccounts.hinext.inventory.model.salespricing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 8/17/2017.
 */

public class InventorySalesPricing implements Serializable {

    @SerializedName("salesPriceId")
    @Expose
    private Long salesPriceId;
    @SerializedName("accountid")
    @Expose
    private Long accountid;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("customerId")
    @Expose
    private Long customerId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("customerID")
    @Expose
    private Long customerID;
    @SerializedName("itemID")
    @Expose
    private Long itemID;
    @SerializedName("currencyName")
    @Expose
    private String currencyName;
    @SerializedName("itemId")
    @Expose
    private Long itemId;

    @SerializedName("custId")
    @Expose
    private Long custId;
    @SerializedName("customerDTOList")
    @Expose
    private String customerDTOList;
    @SerializedName("itemDTOList")
    @Expose
    private String itemDTOList;
    @SerializedName("itemid")
    @Expose
    private Long itemid;

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getSalesPriceId() {
        return salesPriceId;
    }

    public void setSalesPriceId(Long salesPriceId) {
        this.salesPriceId = salesPriceId;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
