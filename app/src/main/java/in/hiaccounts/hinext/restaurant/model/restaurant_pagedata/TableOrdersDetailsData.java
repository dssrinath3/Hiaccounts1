package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administrator on 17/2/18.
 */

public class TableOrdersDetailsData implements Serializable {
    @SerializedName("currTableName")
    @Expose
    public String currTableName;
    @SerializedName("currTableId")
    @Expose
    public String currTableId;
    @SerializedName("prevTableName")
    @Expose
    public String prevTableName;
    @SerializedName("prevTableId")
    @Expose
    public String prevTableId;
    @SerializedName("selectedItemsList")
    @Expose
    public List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("customerId")
    @Expose
    public Long customerId;

    public String getCurrTableName() {
        return currTableName;
    }

    public void setCurrTableName(String currTableName) {
        this.currTableName = currTableName;
    }

    public String getCurrTableId() {
        return currTableId;
    }

    public void setCurrTableId(String currTableId) {
        this.currTableId = currTableId;
    }

    public String getPrevTableName() {
        return prevTableName;
    }

    public void setPrevTableName(String prevTableName) {
        this.prevTableName = prevTableName;
    }

    public String getPrevTableId() {
        return prevTableId;
    }

    public void setPrevTableId(String prevTableId) {
        this.prevTableId = prevTableId;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
