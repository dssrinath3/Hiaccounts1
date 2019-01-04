package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 27/1/18.
 */

public class SelectItemIDData implements Serializable {
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("itemAccountCode")
    @Expose
    public String itemAccountCode;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("itemDesc")
    @Expose
    public String itemDesc;
    @SerializedName("certificateno")
    @Expose
    public String certificateno;
    @SerializedName("conTentType")
    @Expose
    public String conTentType;
    @SerializedName("fileName")
    @Expose
    public String fileName;
    @SerializedName("serializableStatus")
    @Expose
    public String serializableStatus;

    @SerializedName("locationId")
    @Expose
    public String locationId;
    @SerializedName("useraccount_id")
    @Expose
    public String useraccountId;

    @SerializedName("editableDesc")
    @Expose
    public Boolean editableDesc;
    @SerializedName("excludeSales")
    @Expose
    public Boolean excludeSales;
    @SerializedName("salesAccountId")
    @Expose
    public Long salesAccountId;
    @SerializedName("inventoryAccountId")
    @Expose
    public Long inventoryAccountId;
    @SerializedName("cOGS")
    @Expose
    public Long cOGS;
    @SerializedName("serialIteamCount")
    @Expose
    public Long serialIteamCount;
    @SerializedName("inventoryAdjustmentsAccount")
    @Expose
    public Long inventoryAdjustmentsAccount;
    @SerializedName("imageFile")
    @Expose
    public String imageFile;
    @SerializedName("itemStatus")
    @Expose
    public String itemStatus;
    @SerializedName("stock")
    @Expose
    public Double stock;
    @SerializedName("reorderlevel")
    @Expose
    public Double reorderlevel;
    @SerializedName("stdCost")
    @Expose
    public Double stdCost;

    @SerializedName("productionName")
    @Expose
    public Object productionName;
    @SerializedName("itemQuantityProduction")
    @Expose
    public Long itemQuantityProduction;
    @SerializedName("itemPrice")
    @Expose
    public Double itemPrice;
    @SerializedName("inclusiveJSON")
    @Expose
    public String inclusiveJSON;
    @SerializedName("imageBlob")
    @Expose
    public String imageBlob;

    @SerializedName("attributeJSON")
    @Expose
    public String attributeJSON;
    @SerializedName("tableDetails")
    @Expose
    public String tableDetails;
    @SerializedName("cartStatus")
    @Expose
    public String cartStatus;
    @SerializedName("cartValue")
    @Expose
    public String cartValue;
    @SerializedName("cess")
    @Expose
    public Double cess;


    /*    @SerializedName("companyid")
@Expose
public Companyid companyid;*/
  /*  @SerializedName("idItemCategory")
    @Expose
    public IdItemCategory idItemCategory;
    @SerializedName("idItemType")
    @Expose
    public IdItemType idItemType;
    @SerializedName("idItemTax")
    @Expose
    public IdItemTax idItemTax;
    @SerializedName("inputtax")
    @Expose
    public Inputtax inputtax;
    @SerializedName("countType")
    @Expose
    public CountType countType;
    @SerializedName("ledgerINV")
    @Expose
    public LedgerINV_ ledgerINV;
    @SerializedName("ledgerCOGS")
    @Expose
    public LedgerCOGS_ ledgerCOGS;
    @SerializedName("ledgerIncome")
    @Expose
    public LedgerIncome_ ledgerIncome;
    @SerializedName("ledgerAdjustment")
    @Expose
    public LedgerAdjustment_ ledgerAdjustment;
    @SerializedName("idItemUOM")
    @Expose
    public IdItemUOM idItemUOM;*/
     /* @SerializedName("msiccomid")
    @Expose
    public Msiccomid msiccomid;*/
     /* @SerializedName("brandName")
    @Expose
    public BrandName brandName;*/
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemAccountCode() {
        return itemAccountCode;
    }

    public void setItemAccountCode(String itemAccountCode) {
        this.itemAccountCode = itemAccountCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getCertificateno() {
        return certificateno;
    }

    public void setCertificateno(String certificateno) {
        this.certificateno = certificateno;
    }

    public String getConTentType() {
        return conTentType;
    }

    public void setConTentType(String conTentType) {
        this.conTentType = conTentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Boolean getEditableDesc() {
        return editableDesc;
    }

    public void setEditableDesc(Boolean editableDesc) {
        this.editableDesc = editableDesc;
    }

    public Boolean getExcludeSales() {
        return excludeSales;
    }

    public void setExcludeSales(Boolean excludeSales) {
        this.excludeSales = excludeSales;
    }

    public Long getSalesAccountId() {
        return salesAccountId;
    }

    public void setSalesAccountId(Long salesAccountId) {
        this.salesAccountId = salesAccountId;
    }

    public Long getInventoryAccountId() {
        return inventoryAccountId;
    }

    public void setInventoryAccountId(Long inventoryAccountId) {
        this.inventoryAccountId = inventoryAccountId;
    }

    public Long getcOGS() {
        return cOGS;
    }

    public void setcOGS(Long cOGS) {
        this.cOGS = cOGS;
    }

    public Long getSerialIteamCount() {
        return serialIteamCount;
    }

    public void setSerialIteamCount(Long serialIteamCount) {
        this.serialIteamCount = serialIteamCount;
    }

    public Long getInventoryAdjustmentsAccount() {
        return inventoryAdjustmentsAccount;
    }

    public void setInventoryAdjustmentsAccount(Long inventoryAdjustmentsAccount) {
        this.inventoryAdjustmentsAccount = inventoryAdjustmentsAccount;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getReorderlevel() {
        return reorderlevel;
    }

    public void setReorderlevel(Double reorderlevel) {
        this.reorderlevel = reorderlevel;
    }

    public Double getStdCost() {
        return stdCost;
    }

    public void setStdCost(Double stdCost) {
        this.stdCost = stdCost;
    }

    public Object getProductionName() {
        return productionName;
    }

    public void setProductionName(Object productionName) {
        this.productionName = productionName;
    }

    public Long getItemQuantityProduction() {
        return itemQuantityProduction;
    }

    public void setItemQuantityProduction(Long itemQuantityProduction) {
        this.itemQuantityProduction = itemQuantityProduction;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public String getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(String imageBlob) {
        this.imageBlob = imageBlob;
    }

    public String getAttributeJSON() {
        return attributeJSON;
    }

    public void setAttributeJSON(String attributeJSON) {
        this.attributeJSON = attributeJSON;
    }

    public String getTableDetails() {
        return tableDetails;
    }

    public void setTableDetails(String tableDetails) {
        this.tableDetails = tableDetails;
    }

    public String getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(String cartStatus) {
        this.cartStatus = cartStatus;
    }

    public String getCartValue() {
        return cartValue;
    }

    public void setCartValue(String cartValue) {
        this.cartValue = cartValue;
    }

    public Double getCess() {
        return cess;
    }

    public void setCess(Double cess) {
        this.cess = cess;
    }
}
