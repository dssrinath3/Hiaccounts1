package in.hiaccounts.hinext.inventory.model.isdn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/5/2017.
 */

public class ISDNdata implements Serializable {
    @SerializedName("gstApprovalDateId")
    @Expose
    private Long gstApprovalDateId;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("fromGstApprovalDate")
    @Expose
    private String fromGstApprovalDate;
    @SerializedName("toGstApprovalDate")
    @Expose
    private String toGstApprovalDate;
    @SerializedName("gstApprovalnumber")
    @Expose
    private String gstApprovalnumber;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("stateId")
    @Expose
    private Long stateId;

    public Long getGstApprovalDateId() {
        return gstApprovalDateId;
    }

    public void setGstApprovalDateId(Long gstApprovalDateId) {
        this.gstApprovalDateId = gstApprovalDateId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}
