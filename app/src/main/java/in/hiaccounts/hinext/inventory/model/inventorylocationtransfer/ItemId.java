package in.hiaccounts.hinext.inventory.model.inventorylocationtransfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 2/2/18.
 */

public class ItemId implements Serializable {
    @SerializedName("itemId")
    @Expose
    private Long itemId;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("itemAccountCode")
    @Expose
    private String itemAccountCode;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("certificateno")
    @Expose
    private String certificateno;
    @SerializedName("conTentType")
    @Expose
    private String conTentType;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("itemStatus")
    @Expose
    private String itemStatus;
    @SerializedName("stock")
    @Expose
    private Long stock;
    @SerializedName("reorderlevel")
    @Expose
    private Long reorderlevel;
    @SerializedName("productionName")
    @Expose
    private String productionName;
    @SerializedName("itemQuantityProduction")
    @Expose
    private Long itemQuantityProduction;
    @SerializedName("itemPrice")
    @Expose
    private Long itemPrice;
    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;
  /*  @SerializedName("companyid")
    @Expose
    private Companyid companyid;
    @SerializedName("locationId")
    @Expose
    private String locationId;
    @SerializedName("useraccount_id")
    @Expose
    private String useraccountId;
    @SerializedName("idItemCategory")
    @Expose
    private IdItemCategory idItemCategory;
    @SerializedName("idItemType")
    @Expose
    private IdItemType idItemType;
    @SerializedName("idItemTax")
    @Expose
    private IdItemTax idItemTax;
    @SerializedName("inputtax")
    @Expose
    private Inputtax inputtax;
    @SerializedName("countType")
    @Expose
    private CountType countType;
    @SerializedName("ledgerINV")
    @Expose
    private LedgerINV_ ledgerINV;
    @SerializedName("ledgerCOGS")
    @Expose
    private LedgerCOGS_ ledgerCOGS;
    @SerializedName("ledgerIncome")
    @Expose
    private LedgerIncome_ ledgerIncome;
    @SerializedName("ledgerAdjustment")
    @Expose
    private LedgerAdjustment_ ledgerAdjustment;
    @SerializedName("idItemUOM")
    @Expose
    private IdItemUOM idItemUOM;
    @SerializedName("editableDesc")
    @Expose
    private Boolean editableDesc;
    @SerializedName("excludeSales")
    @Expose
    private Boolean excludeSales;
    @SerializedName("salesAccountId")
    @Expose
    private Object salesAccountId;
    @SerializedName("inventoryAccountId")
    @Expose
    private Object inventoryAccountId;
    @SerializedName("cOGS")
    @Expose
    private Object cOGS;
    @SerializedName("serialIteamCount")
    @Expose
    private Object serialIteamCount;
    @SerializedName("inventoryAdjustmentsAccount")
    @Expose
    private Object inventoryAdjustmentsAccount;
    @SerializedName("imageFile")
    @Expose
    private String imageFile;
    @SerializedName("itemStatus")
    @Expose
    private String itemStatus;
    @SerializedName("stock")
    @Expose
    private Integer stock;
    @SerializedName("reorderlevel")
    @Expose
    private Integer reorderlevel;
    @SerializedName("stdCost")
    @Expose
    private Integer stdCost;
    @SerializedName("msiccomid")
    @Expose
    private Msiccomid msiccomid;
    @SerializedName("productionName")
    @Expose
    private String productionName;
    @SerializedName("itemQuantityProduction")
    @Expose
    private Integer itemQuantityProduction;
    @SerializedName("itemPrice")
    @Expose
    private Integer itemPrice;
    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;
    @SerializedName("imageBlob")
    @Expose
    private Object imageBlob;
    @SerializedName("brandName")
    @Expose
    private BrandName brandName;
    @SerializedName("attributeJSON")
    @Expose
    private String attributeJSON;
    @SerializedName("tableDetails")
    @Expose
    private Object tableDetails;
    @SerializedName("cartStatus")
    @Expose
    private Object cartStatus;
    @SerializedName("cartValue")
    @Expose
    private Object cartValue;
    @SerializedName("cess")
    @Expose
    private Integer cess;*/

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getReorderlevel() {
        return reorderlevel;
    }

    public void setReorderlevel(Long reorderlevel) {
        this.reorderlevel = reorderlevel;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public Long getItemQuantityProduction() {
        return itemQuantityProduction;
    }

    public void setItemQuantityProduction(Long itemQuantityProduction) {
        this.itemQuantityProduction = itemQuantityProduction;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

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
}
