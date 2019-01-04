package in.hiaccounts.hinext.inventory.model.inventorylocationtransfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 2/2/18.
 */

public class LTdetail implements Serializable {
    @SerializedName("itemId")
    @Expose
    private ItemId itemId;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("stock")
    @Expose
    private Long stock;
    @SerializedName("qtyTransferred")
    @Expose
    private Long qtyTransferred;

    public ItemId getItemId() {
        return itemId;
    }

    public void setItemId(ItemId itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getQtyTransferred() {
        return qtyTransferred;
    }

    public void setQtyTransferred(Long qtyTransferred) {
        this.qtyTransferred = qtyTransferred;
    }
}
