package in.hiaccounts.hinext.inventory.model.isdn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/5/2017.
 */

public class ISDNDatum implements Serializable {

    @SerializedName("gstApprovalDateId")
    @Expose
    private Long gstApprovalDateId;
    @SerializedName("fromGstApprovalDate")
    @Expose
    private String fromGstApprovalDate;
    @SerializedName("toGstApprovalDate")
    @Expose
    private String toGstApprovalDate;
    @SerializedName("gstApprovalnumber")
    @Expose
    private String gstApprovalnumber;

    @SerializedName("state")
    @Expose
    private String statelist;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("locationId")
    @Expose
    private Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Long useraccountId;

    public Long getGstApprovalDateId() {
        return gstApprovalDateId;
    }

    public void setGstApprovalDateId(Long gstApprovalDateId) {
        this.gstApprovalDateId = gstApprovalDateId;
    }

    public String getFromGstApprovalDate() {
        return fromGstApprovalDate;
    }

    public void setFromGstApprovalDate(String fromGstApprovalDate) {
        this.fromGstApprovalDate = fromGstApprovalDate;
    }

    public String getToGstApprovalDate() {
        return toGstApprovalDate;
    }

    public void setToGstApprovalDate(String toGstApprovalDate) {
        this.toGstApprovalDate = toGstApprovalDate;
    }

    public String getGstApprovalnumber() {
        return gstApprovalnumber;
    }

    public void setGstApprovalnumber(String gstApprovalnumber) {
        this.gstApprovalnumber = gstApprovalnumber;
    }

    public String getStatelist() {
        return statelist;
    }

    public void setStatelist(String statelist) {
        this.statelist = statelist;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
