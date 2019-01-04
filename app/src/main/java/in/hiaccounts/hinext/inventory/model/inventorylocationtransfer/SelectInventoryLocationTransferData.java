package in.hiaccounts.hinext.inventory.model.inventorylocationtransfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administrator on 27/1/18.
 */

public class SelectInventoryLocationTransferData implements Serializable {
    @SerializedName("locList")
    @Expose
    public List<LocList> locList = null;
    @SerializedName("moveList")
    @Expose
    public List<MoveList> moveList = null;
    @SerializedName("ltNo")
    @Expose
    public String ltNo;
    @SerializedName("prefix")
    @Expose
    public String prefix;
    @SerializedName("ilocTransDetailsId")
    @Expose
    public Long ilocTransDetailsId;
    @SerializedName("ltdetails")
    @Expose
    public String ltdetails;
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("itemDesc")
    @Expose
    public String itemDesc;
    @SerializedName("qtyTransferred")
    @Expose
    public Long qtyTransferred;
    @SerializedName("units")
    @Expose
    public String units;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("memo")
    @Expose
    public String memo;
    @SerializedName("locationId")
    @Expose
    public Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Long useraccountId;
    @SerializedName("stock")
    @Expose
    public Long stock;
    @SerializedName("date")
    @Expose
    public String date;

    public List<LocList> getLocList() {
        return locList;
    }

    public void setLocList(List<LocList> locList) {
        this.locList = locList;
    }

    public List<MoveList> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<MoveList> moveList) {
        this.moveList = moveList;
    }

    public String getLtNo() {
        return ltNo;
    }

    public void setLtNo(String ltNo) {
        this.ltNo = ltNo;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getIlocTransDetailsId() {
        return ilocTransDetailsId;
    }

    public void setIlocTransDetailsId(Long ilocTransDetailsId) {
        this.ilocTransDetailsId = ilocTransDetailsId;
    }

    public String getLtdetails() {
        return ltdetails;
    }

    public void setLtdetails(String ltdetails) {
        this.ltdetails = ltdetails;
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

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Long getQtyTransferred() {
        return qtyTransferred;
    }

    public void setQtyTransferred(Long qtyTransferred) {
        this.qtyTransferred = qtyTransferred;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
