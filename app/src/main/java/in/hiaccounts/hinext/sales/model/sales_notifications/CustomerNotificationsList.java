package in.hiaccounts.hinext.sales.model.sales_notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerNotificationsList implements Serializable {
    private int id;
    @SerializedName("custNotiId")
    @Expose
    public Long custNotiId;
    @SerializedName("fromRegno")
    @Expose
    public String fromRegno;
    @SerializedName("toRegno")
    @Expose
    public String toRegno;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("typeDoc")
    @Expose
    public String typeDoc;
    @SerializedName("fromCompname")
    @Expose
    public String fromCompname;
    @SerializedName("typeFlag")
    @Expose
    public String typeFlag;
    @SerializedName("viewStatus")
    @Expose
    public String viewStatus;
    @SerializedName("objectdata")
    @Expose
    public String objectdata;
    @SerializedName("totaltax")
    @Expose
    public String totaltax;
    @SerializedName("totalcheckoutamt")
    @Expose
    public String totalcheckoutamt;
    @SerializedName("piNo")
    @Expose
    public String piNo;
    @SerializedName("subscriptionType")
    @Expose
    public String subscriptionType;
    @SerializedName("postedDate")
    @Expose
    public String postedDate;

    private long timestamp;

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getCustNotiId() {
        return custNotiId;
    }

    public void setCustNotiId(Long custNotiId) {
        this.custNotiId = custNotiId;
    }

    public String getFromRegno() {
        return fromRegno;
    }

    public void setFromRegno(String fromRegno) {
        this.fromRegno = fromRegno;
    }

    public String getToRegno() {
        return toRegno;
    }

    public void setToRegno(String toRegno) {
        this.toRegno = toRegno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getFromCompname() {
        return fromCompname;
    }

    public void setFromCompname(String fromCompname) {
        this.fromCompname = fromCompname;
    }

    public String getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

    public String getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }

    public String getObjectdata() {
        return objectdata;
    }

    public void setObjectdata(String objectdata) {
        this.objectdata = objectdata;
    }

    public String getTotaltax() {
        return totaltax;
    }

    public void setTotaltax(String totaltax) {
        this.totaltax = totaltax;
    }

    public String getTotalcheckoutamt() {
        return totalcheckoutamt;
    }

    public void setTotalcheckoutamt(String totalcheckoutamt) {
        this.totalcheckoutamt = totalcheckoutamt;
    }

    public String getPiNo() {
        return piNo;
    }

    public void setPiNo(String piNo) {
        this.piNo = piNo;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
